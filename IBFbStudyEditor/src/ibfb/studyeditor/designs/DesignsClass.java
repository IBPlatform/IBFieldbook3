package ibfb.studyeditor.designs;

import com.csvreader.CsvReader;
import ibfb.domain.core.Factor;
import ibfb.domain.core.Workbook;
import ibfb.studyeditor.core.model.GermplasmEntriesTableModel;
import ibfb.studyeditor.core.model.ObservationsTableModel;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.cimmyt.cril.ibwb.commongui.ConvertUtils;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.cimmyt.cril.ibwb.commongui.OSUtils;
import org.openide.util.NbPreferences;

/**
 * Class to manage Designs using R calls
 *
 * * @author OZIEL
 */
public class DesignsClass {

    public static final String ALFA_DESIGN = "Alpha design";
    public static final String LATICE_DESIGN = "Lattice design";
    public static final String UNREPLICATED_DESIGH_WITH_RANDOMIZATION = "Unreplicated design with randomization";
    public static final String UNREPLICATED_DESIGH_WITHOUT_RANDOMIZATION = "Unreplicated design without randomization";
    public static final String RANDOMIZE_COMPLETE_BLOCK = "Randomized complete block design";
    public static final String USER_DEFINED_DESIGN = "Use my own design";
    public static final String MAC_RWD = "Fieldbook_R";
    private String pathR = "";
    private String pathRWD = "";

    public DesignsClass() {
        if (OSUtils.isMacOS()) {
            //pathR = OSUtils.getDocumentsPath();
            //pathRWD = OSUtils.getPathRWD();
            pathR = "/Users/Oziel/Desktop/RforMac/Resources";
            pathRWD = "/Users/Oziel/Desktop/R";

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


        ejecutaRforWindows("alpha.R", myCSVFile);

    }

    public void runR_alphaWindowsExtra(int treatments, int rep, int blocksize, int seed) {

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
            pw.println("planAlpha <- design.alpha(t,k,r,seed=" + seed + ")");
            pw.println("setwd" + "(\"C:/R\")");
            pw.println("write.csv(planAlpha,\"" + myCSVFile + "\",row.names=FALSE)");


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


        ejecutaRforWindows("alpha.R", myCSVFile);

    }

    public void runR_lattice(int trial, int treatments, int rep, int blocksize) {
        String fileName = "lattice" + trial;
        String myCSVFile = fileName + ".csv";
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
            fichero = new FileWriter(pathRWD + File.separator + "lattice" + trial + ".R", false);
            pw = new PrintWriter(fichero);
            pw.println();
            pw.println("library(agricolae)");
            pw.println("k <-" + blocksize);
            pw.println("planLattice <- design.lattice(k, seed=55,type=\"" + type + "\", number=1)");
            pw.println("setwd" + "(\"" + pathRWD + "\")");
            pw.println("write.csv(planLattice,\"" + myCSVFile + "\",row.names=FALSE)");
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
        ejecutaRforMac(fileName);
    }

    public void runR_alpha(int trial, int treatments, int rep, int blocksize) {

        String fileName = "alpha" + trial;
        String myCSVFile = fileName + ".csv";
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
            fichero = new FileWriter(pathRWD + File.separator + fileName + ".R", false);
            pw = new PrintWriter(fichero);
            pw.println();
            pw.println("library(agricolae)");
            pw.println("library(MASS)");
            pw.println("t <- 1:" + treatments);
            pw.println("k <- " + blocksize);
            pw.println("r <- " + rep);
            pw.println("s<-t/k");
            pw.println("planAlpha <- design.alpha(t,k,r,seed=55)");
            // pw.println("planAlpha <- design.alpha(t,k,r)");
            pw.println("setwd" + "(\"" + pathRWD + "\")");
            pw.println("write.csv(planAlpha,\"" + myCSVFile + "\",row.names=FALSE)");

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
        ejecutaRforMac(fileName);
    }

    public void ejecutaRforMac(String fileName) {
        FileWriter ficheroBat = null;
        PrintWriter pwBat = null;
        try {
            ficheroBat = new FileWriter(pathR + File.separator + "bat" + File.separator + "ozielR", true);
            pwBat = new PrintWriter(ficheroBat);

            pwBat.println("#!/bin/sh");
            pwBat.println("cd " + pathR + File.separator + "bin");
            pwBat.println("sh R CMD BATCH " + pathRWD + File.separator + fileName + ".R");

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
                System.out.println("ERROR -ejecutaRforMac- : " + e2);
            }
        }

        try {
            System.out.println("Se esta ejecutando R...");


            String[] data = new String[3];
            data[0] = "/bin/sh";
            data[1] = "-c";
            data[2] = "#!/bin/sh \n cd " + pathR + File.separator + "bin \n sh R CMD BATCH " + pathRWD + File.separator + fileName + ".R";
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

    public void readAlphaDesign(int trial, String myDesign, ObservationsTableModel model, JTable germplasmEntries) {

        GermplasmEntriesTableModel entriesTableModel = (GermplasmEntriesTableModel) germplasmEntries.getModel();
        System.out.println("Iniciando lectura de csv");
        //String file = this.pathRWD + File.separator + myDesign+trial + ".csv";
        String file = this.pathRWD + File.separator + myDesign + ".csv";



        try {
            CsvReader csvReader = new CsvReader(file);
            csvReader.readHeaders();
            //     String[] headers = csvReader.getHeaders();

            while (csvReader.readRecord()) {
                String rep = csvReader.get("book.replication");
                String block = csvReader.get("book.block");
                String plot = csvReader.get("book.plots");
                String entry = csvReader.get("book.t");
                int entryIntValue = Integer.parseInt(entry) - 1;

                Object[] rowToAdd = new Object[model.getColumnCount()];
                rowToAdd[model.getHeaderIndex(ObservationsTableModel.TRIAL)] = trial;
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
            System.out.println("FILE NOT FOUND. readDATAcsv.\n\t " + ex);

        } catch (IOException e) {
            System.out.println("IO EXCEPTION. readDATAcsv.\n\t " + e);
        }

        System.out.println(
                "Finalizando lectura de csv");
    }

    public void readLatticeDesign(int trial, String myDesign, ObservationsTableModel model, JTable germplasmEntries, ArrayList<String> otherFactors, String[][] factorsDesignCad, int total) {
        GermplasmEntriesTableModel entriesTableModel = (GermplasmEntriesTableModel) germplasmEntries.getModel();
        System.out.println("Iniciando lectura de csv");


        String file = this.pathRWD + File.separator + myDesign + ".csv";

        try {
            CsvReader csvReader = new CsvReader(file);
            csvReader.readHeaders();
            //    String[] headers = csvReader.getHeaders();

            while (csvReader.readRecord()) {


                String rep = csvReader.get("plan.sqr");
                String block = csvReader.get("plan.block");
                String plot = csvReader.get("plan.plots");
                String entry = csvReader.get("plan.trt");
                int entryIntValue = Integer.parseInt(entry) - 1;

                for (int i = 0; i < total; i++) {
                    Object[] rowToAdd = new Object[model.getColumnCount()];
                    rowToAdd[model.getHeaderIndex(ObservationsTableModel.TRIAL)] = trial;
                    rowToAdd[model.getHeaderIndex(ObservationsTableModel.REPLICATION)] = rep;
                    rowToAdd[model.getHeaderIndex(ObservationsTableModel.BLOCK)] = block;
                    rowToAdd[model.getHeaderIndex(ObservationsTableModel.PLOT)] = plot;


                    int entriesColIndex = 0;
                    for (Factor factor : entriesTableModel.getFactorHeaders()) {
                        String columnHeader = Workbook.getStringWithOutBlanks(factor.getProperty() + factor.getScale());
                        rowToAdd[model.getHeaderIndex(columnHeader)] = entriesTableModel.getValueAt(entryIntValue, entriesColIndex);
                        entriesColIndex++;
                    }

                    //tmsanchez
//                    if (otherFactors != null) {
//                        for (int j = 0; j < otherFactors.size(); j++) {
//                            myRow[findColumn(otherFactors.get(j), model)] = factorsDesignCad[j][i];
//                        }
//                    }


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

    public void readUserDefinedDesign(int currentTrial, File fileName, ObservationsTableModel model, JTable germplasmEntries) {

        GermplasmEntriesTableModel entriesTableModel = (GermplasmEntriesTableModel) germplasmEntries.getModel();

        //TODO 
        // validate uploaded  fileName
        // check if trial1=max(trial) entries


        //      String file = this.pathRWD + File.separator + fileName;


        System.out.println("reading user defined design file : " + fileName);

        try {
            CsvReader csvReader = new CsvReader(fileName.toString());
            csvReader.readHeaders();
            String[] headers = csvReader.getHeaders();

            while (csvReader.readRecord()) {
                String trial = csvReader.get(NbPreferences.forModule(MacthColumsWizardPanel1.class).get("TRIAL", "TRIAL"));
                //if (Integer.valueOf(trial).intValue() != currentTrial) {
                if (ConvertUtils.getValueAsInteger(trial) != currentTrial) {
                    //    if (trial.equals(Integer.toString(currentTrial))) {
                    continue; //skip this row
                }

                String rep = csvReader.get(NbPreferences.forModule(MacthColumsWizardPanel1.class).get("REP", "REP"));
                String block = csvReader.get(NbPreferences.forModule(MacthColumsWizardPanel1.class).get("BLOCK", "BLOCK"));
                String plot = csvReader.get(NbPreferences.forModule(MacthColumsWizardPanel1.class).get("PLOT", "PLOT"));
                String entry = csvReader.get(NbPreferences.forModule(MacthColumsWizardPanel1.class).get("ENTRY", "ENTRY"));
                String row = "";
                String col = "";
                boolean tenemosRow = false;
                boolean tenemosCol = false;

                try {
                    row = csvReader.get(NbPreferences.forModule(MacthColumsWizardPanel1.class).get("ROW", "ROW")).toUpperCase();
                    tenemosRow = true;
                } catch (IOException e) {
                    tenemosRow = false;

                }

                try {
                    col = csvReader.get(NbPreferences.forModule(MacthColumsWizardPanel1.class).get("COL", "COLUMN")).toUpperCase();
                    tenemosCol = true; 
      
                
                } catch (IOException e) {
                    tenemosCol = false;

                }



                int entryIntValue = Integer.parseInt(entry) - 1;

                Object[] rowToAdd = new Object[model.getColumnCount()];
                rowToAdd[model.getHeaderIndex(ObservationsTableModel.TRIAL)] = trial;
                rowToAdd[model.getHeaderIndex(ObservationsTableModel.REPLICATION)] = rep;
                rowToAdd[model.getHeaderIndex(ObservationsTableModel.BLOCK)] = block;
                rowToAdd[model.getHeaderIndex(ObservationsTableModel.PLOT)] = plot;

                if (tenemosRow) {
                    rowToAdd[model.getHeaderIndex(ObservationsTableModel.COL)] = col;
                }
                if (tenemosCol) {
                    rowToAdd[model.getHeaderIndex(ObservationsTableModel.ROW)] = row;
                }

                /*
                 * if (entryIntValue==entriesTableModel.getRowCount()) { String
                 * columnHeader =
                 * Workbook.getStringWithOutBlanks(factor.getProperty() +
                 * factor.getScale());
                 */
                if (entryIntValue < entriesTableModel.getRowCount()) {
                    int entriesColIndex = 0;
                    for (Factor factor : entriesTableModel.getFactorHeaders()) {
                        String columnHeader = Workbook.getStringWithOutBlanks(factor.getProperty() + factor.getScale());
                        rowToAdd[model.getHeaderIndex(columnHeader)] = entriesTableModel.getValueAt(entryIntValue, entriesColIndex);
                        entriesColIndex++;
                    }
                }

                model.addRow(rowToAdd);
            }

            csvReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("FILE NOT FOUND. readDATAcsv.\n\t " + ex);

        } catch (IOException e) {
            System.out.println("IO EXCEPTION. readDATAcsv.\n\t " + e);
        }

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

    public void deleteWDforMac() {

        File folderMac = new File(pathRWD);

        if (folderMac.exists()) {
            File[] files = folderMac.listFiles();
            for (int i = 0; i < files.length; i++) {

                if (files[i].isDirectory()) {
                    deleteWD(files[i]);
                } else {
                    files[i].delete();
                }

            }
        }
        folderMac.delete();
    }

    public boolean existeArchivo(String myDesign) {

        boolean existe = false;

        String file = this.pathRWD + File.separator + myDesign + ".csv";

        File archivo = new File(file);
        if (archivo.exists() && archivo.length() > 0) {

            existe = true;
        } else {
        }


        return existe;
    }
}
