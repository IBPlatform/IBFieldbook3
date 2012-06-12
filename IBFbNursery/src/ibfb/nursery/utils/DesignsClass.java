package ibfb.nursery.utils;

import com.csvreader.CsvReader;
import ibfb.domain.core.Factor;
import ibfb.domain.core.Workbook;
import ibfb.nursery.models.GermplasmEntriesTableModel;
import ibfb.nursery.models.ObservationsTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
public class DesignsClass {

    public static final String ALFA_DESIGN = "Alpha design";
    public static final String LATICE_DESIGN = "Lattice design";
    public static final String UNREPLICATED_DESIGH_WITH_RANDOMIZATION = "Unreplicated design with randomization";
    public static final String UNREPLICATED_DESIGH_WITHOUT_RANDOMIZATION = "Unreplicated design without randomization";
    private String pathR = "/Users/ozieluz/Desktop/RforMac/Resources";
    private String pathRWD = "/Users/ozieluz/Desktop/RforMac/Resources/ScriptsOZIEL";

    public DesignsClass() {
        if (OSUtils.isMacOS()) {
            pathR = "/Users/ozieluz/Desktop/RforMac/Resources";
            pathRWD = "/Users/ozieluz/Desktop/RforMac/Resources/ScriptsOZIEL";
        } else {
            pathR = OSUtils.getRPATH();
            pathRWD = "C:" + File.separator + "R";
          
        }
    }

    public void runR_latticeWindows(int treatments, int rep, int blocksize) {

        String myCSVFile = "lattice.csv";
        FileWriter fichero = null;
        PrintWriter pw = null;
        String path = "";
        String type = "";

        if (rep == 2) {
            type = "simple";
        } else {
            type = "triple";
        }

        try {
            File dir = new File(pathRWD);
            boolean resultado = dir.mkdirs();
            System.out.println("" + resultado);
        } catch (Exception e) {
            System.out.println("ya existe el directorio");
        }

        try {
            File miArchivo = new File(pathRWD + File.separator + myCSVFile);

        } catch (Exception e) {
            System.out.println("error al crear el archivo");
        }

        try {
            File miArchivo = new File(pathRWD + File.separator + "lattice.R");

        } catch (Exception e) {
            System.out.println("error al crear el archivo");
        }


        try {
            fichero = new FileWriter(pathRWD + File.separator + "lattice.R");

            pw = new PrintWriter(fichero);
            pw.println();
            pw.println("library(agricolae)");
            pw.println("k <-" + blocksize);
            pw.println("planLattice <- design.lattice(k, seed=55,type=\"" + type + "\", number=1)");
            pw.println("setwd" + "(\"C:/R\")");
            pw.println("write.csv(planLattice,\"" + myCSVFile + "\",row.names=FALSE)");

        } catch (FileNotFoundException e) {

            NotifyDescriptor d = new NotifyDescriptor.Message("File not found", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d); 

        } catch (IOException e) {

            NotifyDescriptor d = new NotifyDescriptor.Message("I/O Error", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d); 


        } finally {
            try {

                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
            }
        }

        ejecutaRforWindows("lattice.R", myCSVFile);

    }

    public void runR_alphaWindows(int treatments, int rep, int blocksize) {

        String myCSVFile = "alpha.csv";
        FileWriter fichero = null;
        PrintWriter pw = null;
        String path = "";

        //   pathRWD="C:"+File.separator+"R";

        try {
            File dir = new File(pathRWD);
            boolean resultado = dir.mkdirs();
            System.out.println("" + resultado);
        } catch (Exception e) {
            System.out.println("ya existe el directorio");
        }

        try {
            File miArchivo = new File(pathRWD + File.separator + myCSVFile);

        } catch (Exception e) {
            System.out.println("error al crear el archivo");
        }

        try {
            File miArchivo = new File(pathRWD + File.separator + "alpha.R");

        } catch (Exception e) {
            System.out.println("error al crear el archivo");
        }


        try {

            fichero = new FileWriter(pathRWD + File.separator + "alpha.R", false);
            pw = new PrintWriter(fichero);
            pw.println();
            pw.println("library(agricolae)");
            pw.println("library(MASS)");
            pw.println("t <- 1:" + treatments);
            pw.println("k <- " + blocksize);
            pw.println("r <- " + rep);
            pw.println("s<-t/k");
            pw.println("planAlpha <- design.alpha(t,k,r,seed=55)");
            pw.println("setwd" + "(\"C:/R\")");
            pw.println("write.csv(planAlpha,\"" + myCSVFile + "\",row.names=FALSE)");


        } catch (FileNotFoundException e) {

            
            NotifyDescriptor d = new NotifyDescriptor.Message("File not found", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d); 


        } catch (IOException e) {

            NotifyDescriptor d = new NotifyDescriptor.Message("I/O Error", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d); 


        } finally {
            try {

                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
            }
        }


        ejecutaRforWindows("alpha.R", myCSVFile);

    }

    public void runR_lattice(int treatments, int rep, int blocksize) {
        //type=simple, triple
        String myCSVFile = "lattice.csv";
        FileWriter fichero = null;
        PrintWriter pw = null;
        String path = "";
        String type = "";

        if (rep == 2) {
            type = "simple";
        } else {
            type = "triple";
        }




        try {
            File dir = new File(pathRWD);
            boolean resultado = dir.mkdirs();
            System.out.println("" + resultado);
        } catch (Exception e) {
            System.out.println("ya existe el directorio");
        }

        try {
            File miArchivo = new File(pathRWD + File.separator + myCSVFile);

        } catch (Exception e) {
            System.out.println("error al crear el archivo");
        }

        try {
            fichero = new FileWriter(pathRWD + File.separator + "lattice.R", false);

            pw = new PrintWriter(fichero);
            pw.println();
            pw.println("library(agricolae)");
            pw.println("k <-" + blocksize);
            pw.println("planLattice <- design.lattice(k, seed=55,type=\"" + type + "\", number=1)");
            pw.println("setwd" + "(\"" + pathRWD + "\")");
            pw.println("write.csv(planLattice,\"" + myCSVFile + "\",row.names=FALSE)");

        } catch (FileNotFoundException e) {

            NotifyDescriptor d = new NotifyDescriptor.Message("File not found", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d); 

        } catch (IOException e) {

     
            NotifyDescriptor d = new NotifyDescriptor.Message("I/O Error", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d); 


        } finally {
            try {

                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
            }
        }

        ejecutaRforMac("lattice.R", myCSVFile);

    }

    public void runR_alpha(int treatments, int rep, int blocksize) {

        String myCSVFile = "alpha.csv";
        FileWriter fichero = null;
        PrintWriter pw = null;
        String path = "";

        try {
            File dir = new File(pathRWD);
            boolean resultado = dir.mkdirs();
            System.out.println("" + resultado);
        } catch (Exception e) {
            System.out.println("ya existe el directorio");
        }

        try {
            File miArchivo = new File(pathRWD + File.separator + myCSVFile);

        } catch (Exception e) {
            System.out.println("error al crear el archivo");
        }



        try {

            fichero = new FileWriter(pathRWD + File.separator + "alpha.R", false);
            pw = new PrintWriter(fichero);
            pw.println();
            pw.println("library(agricolae)");
            pw.println("library(MASS)");
            pw.println("t <- 1:" + treatments);
            pw.println("k <- " + blocksize);
            pw.println("r <- " + rep);
            pw.println("s<-t/k");
            pw.println("planAlpha <- design.alpha(t,k,r,seed=55)");
            pw.println("setwd" + "(\"" + pathRWD + "\")");
            pw.println("write.csv(planAlpha,\"" + myCSVFile + "\",row.names=FALSE)");

        } catch (FileNotFoundException e) {


            NotifyDescriptor d = new NotifyDescriptor.Message("File not found", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d); 

        } catch (IOException e) {
            NotifyDescriptor d = new NotifyDescriptor.Message("I/O Error", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d); 


        } finally {
            try {

                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
            }
        }
        ejecutaRforMac("alpha.R", myCSVFile);
    }

    public void ejecutaRforMac(String scriptR, String myFile) {
        FileWriter ficheroBat = null;
        PrintWriter pwBat = null;
        try {
            ficheroBat = new FileWriter(pathR + File.separator + "bat" + File.separator + "ozielR", true);
            pwBat = new PrintWriter(ficheroBat);

            pwBat.println("#!/bin/sh");
            pwBat.println("cd " + pathR + File.separator + "bin");
            pwBat.println("sh R CMD BATCH " + pathR + File.separator + "ScriptsOZIEL" + File.separator + scriptR);

        } catch (FileNotFoundException e) { 
            NotifyDescriptor d = new NotifyDescriptor.Message("File not found", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d); 

            return;
        } catch (IOException e) {
            NotifyDescriptor d = new NotifyDescriptor.Message("I/O Error", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d); 

            return;
        } finally {
            try {

                if (null != ficheroBat) {
                    ficheroBat.close();
                }
            } catch (Exception e2) {
                System.out.println("ERROR UTILS: " + e2);
            }
        }

        try {
            System.out.println("Se esta ejecutando R...");

            String[] data = new String[3];
            data[0] = "/bin/sh";
            data[1] = "-c";
            data[2] = "#!/bin/sh \n cd " + pathR + File.separator + "bin \n sh R CMD BATCH " + pathRWD + File.separator + scriptR;

            Process p = Runtime.getRuntime().exec(data);
            p.waitFor();

            InputStream output = p.getInputStream();
            System.out.println(output);
            p.waitFor();

            System.out.println("Finalizo R...");

        } catch (Exception er) {
            System.out.println("Error al ejecutar el .bat de R" + er);
        }
    }

    public void ejecutaRforWindows(String scriptR, String myFile) {

        FileWriter ficheroBat = null;
        PrintWriter pwBat = null;

        try {


            File archivoBat = new File(pathRWD + File.separator + "scriptR.bat");


            ficheroBat = new FileWriter(pathRWD + File.separator + "scriptR.bat");
            pwBat = new PrintWriter(ficheroBat);

            pwBat.println("cd " + pathR + File.separator + "bin");
            pwBat.println("R CMD BATCH C:\\R\\" + scriptR);

        } catch (FileNotFoundException e) {


            NotifyDescriptor d = new NotifyDescriptor.Message("File not found", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);

            return;
        } catch (IOException e) {
            NotifyDescriptor d = new NotifyDescriptor.Message("I/O Error", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);

            return;
        } finally {
            try {

                if (null != ficheroBat) {
                    ficheroBat.close();
                }
            } catch (Exception e2) {
                System.out.println("ERROR ejecutaRforWindows: " + e2);
            }
        }

        try {
            System.out.println("Se esta ejecutando R...");

            Process p = Runtime.getRuntime().exec(pathRWD + File.separator + "scriptR.bat");
            p.waitFor();
            System.out.println("Finalizo R...");


        } catch (Exception er) {
            System.out.println("Error al ejecutar el .bat de R for windows" + er);
        }
    }

    public int findColumn(String name, DefaultTableModel model) {
        int colEntry = 0;

        colEntry = model.findColumn(name);

        return colEntry;

    }

    public void readAlphaDesign(int nursery, String myDesign, ObservationsTableModel model, JTable germplasmEntries) {
        GermplasmEntriesTableModel entriesTableModel = (GermplasmEntriesTableModel) germplasmEntries.getModel();
        System.out.println("Iniciando lectura de csv");
        String file = this.pathRWD + File.separator + myDesign + ".csv";

        try {
            CsvReader csvReader = new CsvReader(file);
            csvReader.readHeaders();
            String[] headers = csvReader.getHeaders();

            while (csvReader.readRecord()) {
                String rep = csvReader.get("book.replication");
                String block = csvReader.get("book.block");
                String plot = csvReader.get("book.plots");
                String entry = csvReader.get("book.t");
                int entryIntValue = Integer.parseInt(entry) - 1;

                Object[] rowToAdd = new Object[model.getColumnCount()];
                rowToAdd[model.getHeaderIndex(ObservationsTableModel.NURSERY)] = nursery;
                rowToAdd[model.getHeaderIndex(ObservationsTableModel.REPLICATION)] = rep;
                rowToAdd[model.getHeaderIndex(ObservationsTableModel.BLOCK)] = block;
                rowToAdd[model.getHeaderIndex(ObservationsTableModel.PLOT)] = plot;


                int entriesColIndex = 0;
                for (Factor factor : entriesTableModel.getFactorHeaders()) {
                    String columnHeader = Workbook.getStringWithOutBlanks(factor.getProperty() + factor.getScale());
                    rowToAdd[model.getHeaderIndex(columnHeader)] = entriesTableModel.getValueAt(entryIntValue, entriesColIndex);
                    entriesColIndex++;
                }

                model.addRow(rowToAdd);
            }

            csvReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("FILE NOT FOUND. readDATAcsv. " + ex);

        } catch (IOException e) {
            System.out.println("IO EXCEPTION. readDATAcsv. " + e);
        }

        System.out.println(
                "Finalizando lectura de csv");
    }

    public void readLatticeDesign(int nursery, String myDesign, ObservationsTableModel model, JTable germplasmEntries, ArrayList<String> otherFactors, String[][] factorsDesignCad, int total) {
        GermplasmEntriesTableModel entriesTableModel = (GermplasmEntriesTableModel) germplasmEntries.getModel();
        System.out.println("Iniciando lectura de csv");


        String file = this.pathRWD + File.separator + myDesign + ".csv";

        try {
            CsvReader csvReader = new CsvReader(file);
            csvReader.readHeaders();
            String[] headers = csvReader.getHeaders();

            while (csvReader.readRecord()) {


                String rep = csvReader.get("plan.sqr");
                String block = csvReader.get("plan.block");
                String plot = csvReader.get("plan.plots");
                String entry = csvReader.get("plan.trt");
                int entryIntValue = Integer.parseInt(entry) - 1;

                for (int i = 0; i < total; i++) {
                    Object[] rowToAdd = new Object[model.getColumnCount()];
                    rowToAdd[model.getHeaderIndex(ObservationsTableModel.NURSERY)] = nursery;
                    rowToAdd[model.getHeaderIndex(ObservationsTableModel.REPLICATION)] = rep;
                    rowToAdd[model.getHeaderIndex(ObservationsTableModel.BLOCK)] = block;
                    rowToAdd[model.getHeaderIndex(ObservationsTableModel.PLOT)] = plot;


                    int entriesColIndex = 0;
                    for (Factor factor : entriesTableModel.getFactorHeaders()) {
                        String columnHeader = Workbook.getStringWithOutBlanks(factor.getProperty() + factor.getScale());
                        rowToAdd[model.getHeaderIndex(columnHeader)] = entriesTableModel.getValueAt(entryIntValue, entriesColIndex);
                        entriesColIndex++;
                    }
                    model.addRow(rowToAdd);
                }
            }

            csvReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("FILE NOT FOUND. readDATAcsv. " + ex);

        } catch (IOException e) {
            System.out.println("IO EXCEPTION. readDATAcsv. " + e);
        }

        System.out.println("Finalizando lectura de csv");
    }

    public void deleteWD(File path) {


        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {

                if (files[i].isDirectory()) {
                    deleteWD(files[i]);
                } else {
                    files[i].delete();
                }

            }
        }
        path.delete();
    }
}
