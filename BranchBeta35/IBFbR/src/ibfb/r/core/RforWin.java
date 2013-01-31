package ibfb.r.core;

import ibfb.r.api.RInterface;
import ibfb.r.ui.ScriptsWindowTopComponent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import org.cimmyt.cril.ibwb.commongui.OSUtils;

public class RforWin extends Thread implements RInterface {

    private String archivo = "";
    private String scriptR = "";

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
    public void setFile(String elFile) {
        this.archivo = elFile;
    }

    @Override
    public void run() {
        leeExcelyEjecutaR(archivo);
    }

    @Override
    public void leeExcelyEjecutaR(String file) {

        String miArchivo = file.toString();
        int inicio = 0;
        String sep=File.separator;
        inicio = miArchivo.indexOf(sep);
        
        while (inicio >= 0) {
            inicio = miArchivo.indexOf(sep);
            miArchivo = miArchivo.substring(inicio + 1);
        }

        miArchivo = miArchivo.substring(0, miArchivo.length() - 4);
        Calendar calendario = GregorianCalendar.getInstance();
        Date fecha = calendario.getTime();
        //SimpleDateFormat formatoDeFecha = new SimpleDateFormat("MMMddyyyy_HHmm");
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyyMMdd_HHmm");
        String folder = miArchivo + "_" + formatoDeFecha.format(fecha);
        creaDirectorio(folder);
        creaArchivoR(folder);
        copiaArchivoCSV(file, folder);
        ejecutaR(folder);
        ScriptsWindowTopComponent.hiloEspera.espera.setEnabled(true);
        ScriptsWindowTopComponent.hiloEspera.espera.setVisible(false);
        abreExplorador(folder);
    }
    
     @Override
    public void creaDirectorio(String archivo) {
        try {
            File dir = new File("C:\\DataR\\" + archivo);
            boolean resultado = dir.mkdirs();
            System.out.println("" + resultado);

        } catch (Exception e) {
            System.out.println("ya existe el directorio");
        }

    }

   @Override
    public void creaArchivoR(String myFile) {
        FileWriter fichero = null;
        PrintWriter pw = null;      
        String path = OSUtils.getRPATH();
        try {
            File inFile = new File(path + File.separator + "oziel" + File.separator + scriptR);
            File outFile = new File("c:\\DataR\\" + myFile + "\\" + myFile + ".R");

            FileInputStream in = new FileInputStream(inFile);
            FileOutputStream out = new FileOutputStream(outFile);
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            System.err.println("Hubo un error de entrada/salida!!!");
        }

        try {
            fichero = new FileWriter("c:\\DataR\\" + myFile + "\\" + myFile + ".R", true);
            pw = new PrintWriter(fichero);

            pw.println();
            pw.println("setwd" + "(\"C:/DataR/" + myFile + "\")");
            pw.println("archivo<-\"" + myFile + ".csv" + "\"");
            pw.println("GLOBAL(archivo)");
            pw.println();

        } catch (FileNotFoundException e) {

            JOptionPane.showMessageDialog(null, "File not found", "ERROR", JOptionPane.ERROR_MESSAGE);


        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, "I/O Error", "ERROR", JOptionPane.ERROR_MESSAGE);


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
            String dataRFolderStr = "c:\\DataR\\";
            File dataRFolder = new File(dataRFolderStr);
            if (!dataRFolder.exists()) {
                dataRFolder.mkdir();
            }
            File inFile = new File(myFile);
            File outFile = new File("c:\\DataR\\" + myArchivo + "\\" + myArchivo + ".csv");
            FileInputStream in = new FileInputStream(inFile);
            FileOutputStream out = new FileOutputStream(outFile);
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            System.err.println("Hubo un error de entrada/salida al copiar CSV");
        }
    }


    @Override
    public void ejecutaR(String myFile) {
        FileWriter ficheroBat = null;
        PrintWriter pwBat = null;
        // String path = "C:" + File.separator + "Program Files" + File.separator + "ibfieldbook"  + File.separator + "R-2.11.1";
        String path = OSUtils.getRPATH();

        try {
            ficheroBat = new FileWriter(path + File.separator + "bat" + File.separator + "ScriptR.bat");
            pwBat = new PrintWriter(ficheroBat);
            pwBat.println("C:");
            pwBat.println("cd " + path + File.separator + "bin");
            pwBat.println("R CMD BATCH C:\\DataR\\" + myFile + "\\" + myFile + ".R");

        } catch (FileNotFoundException e) {

            JOptionPane.showMessageDialog(null, "File not found", "ERROR", JOptionPane.ERROR_MESSAGE);

            return;
        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, "I/O Error", "ERROR", JOptionPane.ERROR_MESSAGE);

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
            Process p = Runtime.getRuntime().exec(path + File.separator + "bat" + File.separator + "ScriptR.bat");
            p.waitFor();
            System.out.println("Finalizo R...");

            ScriptsWindowTopComponent.hiloEspera.espera.setVisible(false);

        } catch (Exception er) {
            System.out.println("Error al ejecutar el .bat de R");
        }
    }

    


     @Override
    public void abreExplorador(String myFolder) {
        try {
            Process proceso = Runtime.getRuntime().exec("explorer.exe c:\\DataR\\" + myFolder);
        } catch (IOException ex) {
            System.out.println("Error al abrir el explorador");
        }
    }
  

}