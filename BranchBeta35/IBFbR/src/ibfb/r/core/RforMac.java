package ibfb.r.core;

import ibfb.r.api.RInterface;
import ibfb.r.ui.JDEspera;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.cimmyt.cril.ibwb.commongui.FileUtils;
import org.cimmyt.cril.ibwb.commongui.OSUtils;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.netbeans.api.progress.ProgressUtils;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.rosuda.JRI.Rengine;

public class RforMac extends Thread implements Runnable, RInterface {

    public static MuestraEspera_Thread waitThread;
    public JDEspera espera = new JDEspera(null, true);
    public static String RHOME = "/Applications/IBFIELDBOOK/RforMac/Resources";
    public static Rengine re;
    String[] Rargs = {"--vanilla"};
    private String pathR = "/Applications/IBFIELDBOOK/RforMac/Resources";
    private String scriptR = "";
    private String inputFile = "";
    private String pathRScript = "/Applications/IBFIELDBOOK/RforMac/Resources/oziel";
    private String pathDataR = System.getProperty("user.dir") + File.separator + "DataR";
    private ProgressHandle handle;
    private ResourceBundle bundle = NbBundle.getBundle(RforMac.class);

    @Override
    public void setScript(int elScript) {
        switch (elScript) {
            case 1:
                this.scriptR = "onesite.R";
                break;
            case 2:
                this.scriptR = "multilocation.R";
                break;
            case 3:
                this.scriptR = "gregAmmiMulti.R";
                break;
            case 4:
                this.scriptR = "LineByTester.R";
                break;
        }
    }

    @Override
    public void setFile(String inputtFile) {
        this.inputFile = inputtFile;
    }

    @Override
    public void run() {

        ejecute();


    }

    private void ejecute() {
        handle = ProgressHandleFactory.createHandle(bundle.getString("RforMac.loading"));
        ProgressUtils.showProgressDialogAndRun(new Runnable() {

            @Override
            public void run() {

                handle.start(100);
                leeExcelyEjecutaR(inputFile);
            }
        }, handle, true);


    }

    @Override
    public void leeExcelyEjecutaR(String file) {
        String miArchivo = file.toString();
        int inicio = 0;
        String sep = File.separator;
        inicio = miArchivo.indexOf(sep);

        while (inicio >= 0) {
            inicio = miArchivo.indexOf(sep);
            miArchivo = miArchivo.substring(inicio + 1);
        }

        miArchivo = miArchivo.substring(0, miArchivo.length() - 4);
        Calendar calendario = GregorianCalendar.getInstance();
        Date fecha = calendario.getTime();
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyyMMdd_HHmm");

        String folder = miArchivo + "_" + formatoDeFecha.format(fecha);

        creaDirectorio(folder);
        creaArchivoR(folder);
        copiaArchivoCSV(file, folder);
        copiaArchivoOziel();
        ejecutaR(folder);

        //  ScriptsWindowTopComponent.hiloEspera.espera.setEnabled(true);
        //  ScriptsWindowTopComponent.hiloEspera.espera.setVisible(false);
        abreExplorador(folder);

    }

    @Override
    public void creaDirectorio(String archivo) {

        try {
            File dir = new File(pathDataR + File.separator + archivo);
            boolean resultado = dir.mkdir();
            System.out.println("SE CREO DIRECTORIO" + resultado);

        } catch (Exception e) {
            System.out.println("NO SE CREO DIRECTORIO " + e);
        }

    }

    @Override
    public void creaArchivoR(String myFile) {

        FileWriter fichero = null;
        PrintWriter pw = null;
        String path = OSUtils.getRPATH();

        try {
            File inFile = new File(pathR + File.separator + "oziel" + File.separator + scriptR);

            File outFile = new File(pathDataR + File.separator + myFile + File.separator + myFile + ".R");

            FileInputStream in = new FileInputStream(inFile);
            FileOutputStream out = new FileOutputStream(outFile);
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            System.err.println("Hubo un error de entrada/salida " + e);
        }

        try {
            fichero = new FileWriter(pathDataR + File.separator + myFile + File.separator + myFile + ".R", true);
            pw = new PrintWriter(fichero);

            pw.println();
            pw.println("setwd" + "(\"" + pathDataR + File.separator + myFile + "\")");
            pw.println("archivo<-\"" + myFile + ".csv" + "\"");
            pw.println("GLOBAL(archivo)");
            pw.println();

        } catch (FileNotFoundException e) {
            DialogUtil.displayError("File not found");
        } catch (IOException e) {
            DialogUtil.displayError("File not found");
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
            }
        }


    }

    @Override
    public void copiaArchivoCSV(String myFile, String myArchivo) {
        try {
            File inFile = new File(myFile);
            File outFile = new File(pathDataR + File.separator + myArchivo + File.separator + myArchivo + ".csv");
            FileInputStream in = new FileInputStream(inFile);
            FileOutputStream out = new FileOutputStream(outFile);
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            System.err.println("R for MAC. Hubo un error de entrada/salida al copiar CSV " + e);
        }
    }

    public void copiaArchivoOziel() {
        try {
            File inFile = new File(pathR + File.separator + "bat" + File.separator + "oziel");
            File outFile = new File(pathR + File.separator + "bat" + File.separator + "ozielR");
            FileInputStream in = new FileInputStream(inFile);
            FileOutputStream out = new FileOutputStream(outFile);
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            System.err.println("R for MAC. Hubo un error de entrada/salida al copiar CSV OZIEL " + e);
        }
    }

    @Override
    public void ejecutaR(String myFile) {
        FileWriter ficheroBat = null;
        PrintWriter pwBat = null;
        try {
            ficheroBat = new FileWriter(pathR + File.separator + "bat" + File.separator + "ozielR", true);
            pwBat = new PrintWriter(ficheroBat);

            pwBat.println("#!/bin/sh");
            pwBat.println("cd " + pathR + File.separator + "bin");
            pwBat.println("sh R CMD BATCH " + pathDataR + File.separator + myFile + File.separator + myFile + ".R");

        } catch (FileNotFoundException e) {
            DialogUtil.displayError("File not found");
            return;
        } catch (IOException e) {
            DialogUtil.displayError("I/O Error");
            return;
        } finally {
            try {

                if (null != ficheroBat) {
                    ficheroBat.close();
                }
            } catch (Exception e2) {
                //   e2.printStackTrace();
            }
        }

        try {
            System.out.println("Se esta ejecutando R...");

            String[] data = new String[3];
            data[0] = "/bin/sh";
            data[1] = "-c";
            data[2] = "#!/bin/sh \n sh " + pathR + File.separator + "bat" + File.separator + "ozielR";


            Process p = Runtime.getRuntime().exec(data);
            p.waitFor();

            InputStream output = p.getInputStream();
            System.out.println(output);

            p.waitFor();

            System.out.println("Finalizo R...");

            //   ScriptsWindowTopComponent.hiloEspera.espera.setVisible(false);

        } catch (Exception er) {
            System.out.println("Error al ejecutar el .bat de R" + er);
        }


    }

    @Override
    public void abreExplorador(String myFile) {
        try {

            File file = new File(pathDataR + File.separator + myFile);
            Desktop.getDesktop().open(file);

            //Process proceso = Runtime.getRuntime().exec("explorer.exe c:\\DataR\\" + myFile);
        } catch (IOException ex) {
            System.out.println("Error al abrir el explorador en MAC");
        }

    }

    private boolean existeDirectorioR() {

        File folder = new File(pathR);
        if (folder.exists() && folder.isDirectory()) {
            return true;
        } else {
            return false;
        }


    }

    public void copyFile(String sourcePath, String file) {
        try {
            File inFile = new File(file);
            File outFile = new File(sourcePath + File.separator + inFile.getName());
            FileInputStream in = new FileInputStream(inFile);
            FileOutputStream out = new FileOutputStream(outFile);
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            DialogUtil.displayError("Cannot copy file to working directory : " + e);
        }

    }

    public void executeR(String scriptFile, String inputFile) {

        try {
            if (re == null) {
                re.jriLoadHistory(scriptR);
                re = new Rengine(Rargs, false, null);
                System.out.println("Rengine created, waiting for R");

                if (!re.waitForR()) {
                    System.out.println("Cannot load R");
                    return;
                }
            }

        } catch (Exception ex) {
            System.out.println("ERR " + ex);
        }


        try {
            EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    // espera.setVisible(true);
                }
            });

            if (OSUtils.createDirectory(OSUtils.getDocumentsPath(), "DataR")) {
                pathDataR = OSUtils.getDocumentsPath() + File.separator + "DataR";
            }

            String setwd = "";
            String newScriptFolder = createFolderName(scriptFile);
            String newScriptDirectory = pathDataR;
            if (OSUtils.createDirectory(pathDataR, newScriptFolder)) {
                newScriptDirectory = pathDataR + File.separator + newScriptFolder;
                setwd = "setwd(\"" + newScriptDirectory + "\")";
                copyFile(newScriptDirectory, inputFile);
            }

            File inFile = new File(inputFile);
            String source = "source(\"" + pathRScript + "/" + scriptFile + "\")";
            String global = "GLOBAL(\"" + inFile.getName() + "\")";

            synchronized (this) {
                re.eval(setwd);
                re.eval("rm(list=ls())");
                re.eval("source(\"" + pathRScript + "/" + scriptFile + "\",echo=T)");
                re.eval("GLOBAL(\"" + inFile.getName() + "\")");

                espera.setVisible(false);
                FileUtils.openDirectory(newScriptDirectory);
            }
        } catch (Exception e) {
            Exceptions.printStackTrace(e);
            DialogUtil.displayError("Having errors running the scripts");
        }

    }

    private String createFolderName(String inputFile) {


        Calendar calendar = GregorianCalendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy_HHmm");

        String newFolder = inputFile + "_" + dateFormat.format(date);
        return newFolder;
    }
}