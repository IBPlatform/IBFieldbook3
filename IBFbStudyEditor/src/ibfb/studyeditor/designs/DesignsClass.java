package ibfb.studyeditor.designs;

import com.csvreader.CsvReader;
import ibfb.domain.core.Factor;
import ibfb.domain.core.Workbook;
import ibfb.studyeditor.core.model.GermplasmEntriesTableModel;
import ibfb.studyeditor.core.model.ObservationsTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
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
    public static List<FactorsForDesign> facDesign;         //TRIAL, ENTRY, PLOT, BLOCK, REP, COL, ROW 
    
    /**
     * workbook object to read if template contains plot nested + number
     */
    private Workbook workbook;
    

    public DesignsClass() {
        if (OSUtils.isMacOS()) {
            //pathR = OSUtils.getDocumentsPath();
            //pathRWD = OSUtils.getPathRWD();
            pathR = "/Applications/IBFIELDBOOK/RforMac/Resources";
            pathRWD = "/Applications/IBFIELDBOOK/Rdata";

        } else {
            pathR = OSUtils.getRPATH();
            pathRWD = "C:" + File.separator + "R";
        }
        
    }

    public void runR_latticeWindows(int treatments, int rep, int blocksize) {

        Random aleatorio = new Random(System.currentTimeMillis());
        int newSeed = (int) (aleatorio.nextDouble() * 200 + 1);

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
            pw.println("planLattice <- design.lattice(k, seed=" + newSeed + ",type=\"" + type + "\", number=1)");
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

    public void runR_alphaWindows(int trial, int treatments, int rep, int blocksize) {

        Random aleatorio = new Random(System.currentTimeMillis());
        int newSeed = (int) (aleatorio.nextDouble() * 200 + 1);


        String myCSVFile = "alpha" + trial + ".csv";
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
            pw.println("planAlpha <- design.alpha(t,k,r,seed=" + newSeed + ")");
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


        Random aleatorio = new Random(System.currentTimeMillis());
        int newSeed = (int) (aleatorio.nextDouble() * 200 + 1);

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
            pw.println("planAlpha <- design.alpha(t,k,r,seed=" + newSeed + ")");
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


        Random aleatorio = new Random(System.currentTimeMillis());
        int newSeed = (int) (aleatorio.nextDouble() * 200 + 1);

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
            pw.println("planLattice <- design.lattice(k, seed=" + newSeed + ",type=\"" + type + "\", number=1)");
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

        Random aleatorio = new Random(System.currentTimeMillis());
        int newSeed = (int) (aleatorio.nextDouble() * 200 + 1);

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
            pw.println("planAlpha <- design.alpha(t,k,r,seed=" + newSeed + ")");
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
        String file = this.pathRWD + File.separator + myDesign + trial + ".csv";

        int maxPlotsForTrial = getMaxPlotsForTrial(file);
        int zerosToAdd = 1;
        if (maxPlotsForTrial < 100 ) {
            zerosToAdd = 2;
        }if (maxPlotsForTrial < 1000 ) {
            zerosToAdd = 3;
        }
        
        int numericPlot = 0;
        int numericPlotCounter = 1;
        String currentReplicate = "-1";
        
        boolean hasFieldPlotNested = workbook.hasPlotNestedNumber();
        
        
        
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

                if (!currentReplicate.equals(rep)) {
                    numericPlotCounter =1;
                    currentReplicate = rep;
                }
                
                if (hasFieldPlotNested) {
                    numericPlot = Integer.valueOf(plot);
                    plot = "" + trial + ConvertUtils.getZeroLeading(numericPlot, zerosToAdd);
                }
                
                Object[] rowToAdd = new Object[model.getColumnCount()];
                rowToAdd[model.getHeaderIndex(ObservationsTableModel.TRIAL)] = trial;

                if (model.getHeaderIndex(ObservationsTableModel.REPLICATION) > 0) {
                    rowToAdd[model.getHeaderIndex(ObservationsTableModel.REPLICATION)] = rep;
                }
                if (model.getHeaderIndex(ObservationsTableModel.BLOCK) > 0) {
                    rowToAdd[model.getHeaderIndex(ObservationsTableModel.BLOCK)] = block;
                }
                
                

                if (model.getHeaderIndex(ObservationsTableModel.PLOT) > 0) {
                    rowToAdd[model.getHeaderIndex(ObservationsTableModel.PLOT)] = plot;
                } else if (model.getHeaderIndex(ObservationsTableModel.PLOTNUMBER) > 0) {
                    rowToAdd[model.getHeaderIndex(ObservationsTableModel.PLOTNUMBER)] = plot;
                } else {
                    rowToAdd[model.getHeaderIndex(ObservationsTableModel.PLOTNUMBER)] = plot;
                }

                int entriesColIndex = 0;
                for (Factor factor : entriesTableModel.getFactorHeaders()) {
                    String columnHeader = Workbook.getStringWithOutBlanks(factor.getProperty() + factor.getScale());
                    rowToAdd[model.getHeaderIndex(columnHeader)] = entriesTableModel.getValueAt(entryIntValue, entriesColIndex);
                    entriesColIndex++;
                }

                numericPlotCounter++;
                
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
        //TRIAL, ENTRY, PLOT, BLOCK, REP, COL, ROW 

        GermplasmEntriesTableModel entriesTableModel = (GermplasmEntriesTableModel) germplasmEntries.getModel();
        System.out.println("reading user defined design file : " + fileName);

        String tr = NbPreferences.forModule(MacthColumsWizardPanel1.class).get("TRIAL", "");



        try {
            CsvReader csvReader = new CsvReader(fileName.toString());
            csvReader.readHeaders();
            String[] headers = csvReader.getHeaders();



            while (csvReader.readRecord()) {
                //  String trial = csvReader.get(NbPreferences.forModule(MacthColumsWizardPanel1.class).get("TRIAL", "TRIAL"));
                String trial = csvReader.get(tr);
                //if (Integer.valueOf(trial).intValue() != currentTrial) {
                if (ConvertUtils.getValueAsInteger(trial) != currentTrial) {
                    //    if (trial.equals(Integer.toString(currentTrial))) {
                    continue; //skip this row
                }
//
//                String rep = csvReader.get(NbPreferences.forModule(MacthColumsWizardPanel1.class).get("REP", "REP"));
//                String block = csvReader.get(NbPreferences.forModule(MacthColumsWizardPanel1.class).get("BLOCK", "BLOCK"));
//                String plot = csvReader.get(NbPreferences.forModule(MacthColumsWizardPanel1.class).get("PLOT", "PLOT"));
//                String entry = csvReader.get(NbPreferences.forModule(MacthColumsWizardPanel1.class).get("ENTRY", "ENTRY"));
//                    try {
//                    row = csvReader.get(NbPreferences.forModule(MacthColumsWizardPanel1.class).get("ROW", "ROW")).toUpperCase();
//                    tenemosRow = true;
//                } catch (IOException e) {
//                    tenemosRow = false;
//
//                }
//
//                try {
//                    col = csvReader.get(NbPreferences.forModule(MacthColumsWizardPanel1.class).get("COL", "COLUMN")).toUpperCase();
//                    tenemosCol = true;
//
//
//                } catch (IOException e) {
//                    tenemosCol = false;
//
//                }
//               
                String tl = NbPreferences.forModule(MacthColumsWizardPanel1.class).get("TRIAL", "");
                String rp = NbPreferences.forModule(MacthColumsWizardPanel1.class).get("REP", "");
                String bl = NbPreferences.forModule(MacthColumsWizardPanel1.class).get("BLOCK", "");
                String pl = NbPreferences.forModule(MacthColumsWizardPanel1.class).get("PLOT", "");
                String en = NbPreferences.forModule(MacthColumsWizardPanel1.class).get("ENTRY", "");
                String rw = NbPreferences.forModule(MacthColumsWizardPanel1.class).get("ROW", "");
                String cl = NbPreferences.forModule(MacthColumsWizardPanel1.class).get("COL", "");

                String rep = "";
                String block = "";
                String plot = "";
                String entry = "";
                String row = "";
                String col = "";

                boolean tenemosRow = Arrays.asList(headers).contains(rw);
                boolean tenemosCol = Arrays.asList(headers).contains(cl);
                boolean tenemosRep = Arrays.asList(headers).contains(rp);
                boolean tenemosBlock = Arrays.asList(headers).contains(bl);
                boolean tenemosPlot = Arrays.asList(headers).contains(pl);
                boolean tenemosEntry = Arrays.asList(headers).contains(en);
                boolean tenemosTrial = Arrays.asList(headers).contains(tl);


                if (tenemosRep) {
                    rep = csvReader.get(rp);
                } else {
                    rep = "1";
                }

                if (tenemosBlock) {
                    block = csvReader.get(bl);
                } else {
                    block = "1";

                }

                if (tenemosPlot) {
                    plot = csvReader.get(pl);
                } else {
                    plot = "1";
                }


                if (tenemosEntry) {
                    entry = csvReader.get(en);
                } else {
                    entry = "1";
                }


                if (tenemosRow) {
                    row = csvReader.get(rw);
                } else {
                    row = "1";
                }


                if (tenemosCol) {
                    col = csvReader.get(cl);
                } else {
                    col = "1";
                }


                int entryIntValue = Integer.parseInt(entry) - 1;

                Object[] rowToAdd = new Object[model.getColumnCount()];

                rowToAdd[model.getHeaderIndex(ObservationsTableModel.TRIAL)] = trial;


                if (tenemosRep) {
                    rowToAdd[model.getHeaderIndex(ObservationsTableModel.REPLICATION)] = rep;
                }

                if (tenemosBlock) {
                    rowToAdd[model.getHeaderIndex(ObservationsTableModel.BLOCK)] = block;
                }

                if (tenemosPlot) {
                    if (model.getHeaderIndex(ObservationsTableModel.PLOTNUMBER) > 0) {
                        rowToAdd[model.getHeaderIndex(ObservationsTableModel.PLOTNUMBER)] = plot;
                    } else {
                        rowToAdd[model.getHeaderIndex(ObservationsTableModel.PLOT)] = plot;
                    }
                }

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

    private int getMaxPlotsForTrial(String fileName) {
        int maxPlotForTrial = 0;
        CsvReader csvReader = null;
        try {
            csvReader = new CsvReader(fileName);
            csvReader.readHeaders();
            //     String[] headers = csvReader.getHeaders();

            while (csvReader.readRecord()) {
                String plot = csvReader.get("book.plots");
                Integer intPlot = Integer.valueOf(plot).intValue();
                maxPlotForTrial = intPlot;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           if (csvReader != null) csvReader.close();
        }
        System.out.println("*****Max plot for trial = " + maxPlotForTrial);
        return maxPlotForTrial;
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }
    
    
}
