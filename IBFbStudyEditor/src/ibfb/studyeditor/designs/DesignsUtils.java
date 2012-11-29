package ibfb.studyeditor.designs;

import com.csvreader.CsvReader;
import ibfb.domain.core.DesignBean;
import ibfb.domain.core.Workbook;
import ibfb.studyeditor.core.model.DesignTableModel;
import ibfb.studyeditor.roweditors.AlphaDesignsRowEditor;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import org.cimmyt.cril.ibwb.commongui.ConvertUtils;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.cimmyt.cril.ibwb.commongui.FileUtils;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

/**
 * Class to centralize all designs algorithms for designs
 *
 * @author OZIEL
 */
public class DesignsUtils {

    private JTable jTableDesign;
    private JTextField jTextFieldEntries;
    ArrayList<KSValues> rep2;
    ArrayList<KSValues> rep3;
    ArrayList<KSValues> rep4;
    String[] cabeceras;
    int[] posiciones;
    int germplasmEntries = 0;
    private Workbook myWorkbook;
    private List<FactorsForDesign> facDesign;

    public DesignsUtils(JTable jTableDesign, JTextField jTextFieldEntries) {
        this.jTableDesign = jTableDesign;
        this.jTextFieldEntries = jTextFieldEntries;

    }

    public int getGermplasmEntries() {
        return germplasmEntries;
    }

    public void setGermplasmEntries(int germplasmEntries) {
        this.germplasmEntries = germplasmEntries;
    }

    /**
     * Assigns main editor for design
     *
     * @param jTableDesign
     * @param conAlpha
     * @param conLattice
     * @return
     */
    public String assignMainCellEditor(boolean conAlpha, boolean conLattice, boolean conRCBD, boolean conUnreplicated, boolean conIndividual) {
        String inicio = "";
        JComboBox comboBox = new JComboBox();

        if (conAlpha) {
            comboBox.addItem(DesignsClass.ALFA_DESIGN);
        }
        if (conLattice) {
            comboBox.addItem(DesignsClass.LATICE_DESIGN);
        }

        if (conUnreplicated) {
            comboBox.addItem(DesignsClass.UNREPLICATED_DESIGH_WITHOUT_RANDOMIZATION);
            comboBox.addItem(DesignsClass.UNREPLICATED_DESIGH_WITH_RANDOMIZATION);
        }


        if (conRCBD) {
            comboBox.addItem(DesignsClass.RANDOMIZE_COMPLETE_BLOCK);
        }

        if (conIndividual) {
            comboBox.addItem(DesignsClass.USER_DEFINED_DESIGN);
        }
        


        if (comboBox.getItemCount() > 0) {
            inicio = comboBox.getItemAt(0).toString();
            comboBox.setSelectedItem(comboBox.getItemAt(0));
            comboBox.setSelectedIndex(0);
        }

        TableColumn valueColumn = jTableDesign.getColumnModel().getColumn(1);
        valueColumn.setCellEditor(new DefaultCellEditor(comboBox));

        TableColumn designColumn = jTableDesign.getColumnModel().getColumn(1);
        DefaultTableCellRenderer designCellRenderer = new DefaultTableCellRenderer();
        designCellRenderer.setToolTipText("Choose a design from list");
        designCellRenderer.setBackground(Color.YELLOW);
        designColumn.setCellRenderer(designCellRenderer);

        TableColumn replicatesColumn = jTableDesign.getColumnModel().getColumn(2);
        DefaultTableCellRenderer replicationCellRenderrer = new DefaultTableCellRenderer();
        replicationCellRenderrer.setToolTipText("Choose replication number");
        replicationCellRenderrer.setBackground(Color.YELLOW);
        replicatesColumn.setCellRenderer(replicationCellRenderrer);

        TableColumn blockSizeColumn = jTableDesign.getColumnModel().getColumn(3);
        DefaultTableCellRenderer blockCellRenderer = new DefaultTableCellRenderer();
        blockCellRenderer.setToolTipText("Choose block size");
        blockCellRenderer.setBackground(Color.YELLOW);
        blockSizeColumn.setCellRenderer(blockCellRenderer);

        return inicio;
    }

    public ArrayList<KSValues> getRep2() {
        return rep2;
    }

    public ArrayList<KSValues> getRep3() {
        return rep3;
    }

    public ArrayList<KSValues> getRep4() {
        return rep4;
    }

    /**
     *
     * @param jTableDesign
     */
    public void assignCellEditorAlpha() {
        JComboBox comboBoxRep = new JComboBox();
        for (int i = 1; i < 4; i++) {
            comboBoxRep.addItem(Integer.valueOf(i + 1));
        }
        comboBoxRep.setEnabled(true);
        comboBoxRep.setEditable(false);
        comboBoxRep.setSelectedIndex(0);
        TableColumn repColumn = jTableDesign.getColumnModel().getColumn(2);
        repColumn.setCellEditor(new DefaultCellEditor(comboBoxRep));
    }

    public boolean alphaIsValidWithOutConstraints(int entries) {

        rep2 = validaRep2(entries);
        rep3 = validaRep3(entries);
        rep4 = validaRep4(entries);

        if ((rep2.size() > 0) || (rep3.size() > 0) || (rep4.size() > 0)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean alphaIsValid(int entries) {

        rep2 = validaRep2(entries);
        rep3 = validaRep3(entries);
        rep4 = validaRep4(entries);

        //imprimeValores(rep2,rep3,rep4);

        if ((rep2.size() > 0) || (rep3.size() > 0) || (rep4.size() > 0)) {
            return true;
        } else {
            return false;
        }

    }

    public void imprimeValores(ArrayList<KSValues> repe2, ArrayList<KSValues> repe3, ArrayList<KSValues> repe4) {
        for (int i = 0; i < repe2.size(); i++) {
            System.out.println("Entradas validas rep2: k=" + repe2.get(i).getBlockSize() + "   s=" + repe2.get(i).getBlocksNumber());
        }
        System.out.println("----------------------------------------");

        for (int i = 0; i < repe3.size(); i++) {
            System.out.println("Entradas validas rep3: k=" + repe3.get(i).getBlockSize() + "   s=" + repe3.get(i).getBlocksNumber());
        }

        System.out.println("----------------------------------------");
        for (int i = 0; i < repe4.size(); i++) {
            System.out.println("Entradas validas rep4: k=" + repe4.get(i).getBlockSize() + "   s=" + repe4.get(i).getBlocksNumber());
        }
    }

    public int assignCellEditorAlpha(int entries) {

        JComboBox comboBoxRep = new JComboBox();
        int selected = 0;

        if (rep2.size() > 0) {
            comboBoxRep.addItem(2);
            selected = 2;
        }

        if (rep3.size() > 0) {
            comboBoxRep.addItem(3);
            if (selected == 0) {
                selected = 3;
            }
        }

        if (rep4.size() > 0) {
            comboBoxRep.addItem(4);
            if (selected == 0) {
                selected = 4;
            }
        }

        comboBoxRep.setEnabled(true);
        comboBoxRep.setEditable(false);

        if (comboBoxRep.getItemCount() > 0) {
            comboBoxRep.setSelectedIndex(0);
        }
        TableColumn repColumn = jTableDesign.getColumnModel().getColumn(2);
        repColumn.setCellEditor(new DefaultCellEditor(comboBoxRep));
        return selected;
    }

    /**
     *
     * @param jTableDesign
     */
    public void assignCellEditorLattice() {
        JComboBox comboBoxRep = new JComboBox();
        for (int i = 1; i < 3; i++) {
            comboBoxRep.addItem(Integer.valueOf(i + 1));
        }
        comboBoxRep.setEnabled(true);
        comboBoxRep.setEditable(false);
        comboBoxRep.setSelectedIndex(0);
        TableColumn repColumn = jTableDesign.getColumnModel().getColumn(2);
        repColumn.setCellEditor(new DefaultCellEditor(comboBoxRep));
    }

    /**
     *
     * @param jTableDesign
     */
    public void assignCellEditorReplicated() {
        JComboBox comboBoxRep = new JComboBox();
        for (int i = 1; i < 20; i++) {
            comboBoxRep.addItem(Integer.valueOf(i + 1));
        }
        comboBoxRep.setEnabled(true);
        comboBoxRep.setEditable(false);
        comboBoxRep.setSelectedIndex(0);
        TableColumn repColumn = jTableDesign.getColumnModel().getColumn(2);
        repColumn.setCellEditor(new DefaultCellEditor(comboBoxRep));
    }

    public void generateBlocksSizeLattice() {
        final JComboBox comboBoxSize = new JComboBox();
        int entries = Integer.parseInt(jTextFieldEntries.getText());
        int con = entries;
        try {
            comboBoxSize.addItem((int) Math.sqrt(entries));
        } catch (NumberFormatException ex) {
            comboBoxSize.removeAllItems();
        }
        comboBoxSize.setEnabled(true);
        comboBoxSize.setEditable(false);
        TableColumn valueColumn = jTableDesign.getColumnModel().getColumn(3);
        valueColumn.setCellEditor(new DefaultCellEditor(comboBoxSize));
    }

    public void generateBlocksSize(int selected) {

        final JComboBox comboBoxSize = new JComboBox();

        switch (selected) {
            case 2:

                for (int i = 0; i < rep2.size(); i++) {
                    comboBoxSize.addItem(rep2.get(i).getBlockSize());

                }


                break;
            case 3:

                for (int i = 0; i < rep3.size(); i++) {
                    comboBoxSize.addItem(rep3.get(i).getBlockSize());

                }

                break;
            case 4:
                for (int i = 0; i < rep4.size(); i++) {
                    comboBoxSize.addItem(rep4.get(i).getBlockSize());

                }
                break;

        }



        if (comboBoxSize.getItemCount() == 0) {
            return;
        }

        comboBoxSize.setEnabled(true);
        comboBoxSize.setEditable(false);
        comboBoxSize.setSelectedIndex(0);

        TableColumn valueColumn = jTableDesign.getColumnModel().getColumn(3);
        valueColumn.setCellEditor(new DefaultCellEditor(comboBoxSize));
    }

    public void assignCellEditorBlockSize() {
        AlphaDesignsRowEditor alphaRowEditor = new AlphaDesignsRowEditor(jTableDesign, Integer.parseInt(jTextFieldEntries.getText()), rep2, rep3, rep4);


        TableColumn valueColumn = jTableDesign.getColumnModel().getColumn(3);
        valueColumn.setCellEditor(alphaRowEditor);
        jTextFieldEntries.repaint();
    }

    /**
     *
     * @param useSameDesignForAll
     */
    public void useSameDesignForTrials(boolean useSameDesignForAll) {
        if (useSameDesignForAll) {
            Object design = jTableDesign.getValueAt(0, 1);
            Object replicates = jTableDesign.getValueAt(0, 2);
            Object blockSize = jTableDesign.getValueAt(0, 3);
            Object blocksPerReplicate = jTableDesign.getValueAt(0, 4);
            Object userDefinedDesign = jTableDesign.getValueAt(0, 5);
            for (int row = 1; row < jTableDesign.getRowCount(); row++) {
                jTableDesign.setValueAt(design, row, 1);
                jTableDesign.setValueAt(replicates, row, 2);
                jTableDesign.setValueAt(blockSize, row, 3);
                jTableDesign.setValueAt(blocksPerReplicate, row, 4);
                jTableDesign.setValueAt(userDefinedDesign, row, 5);
            }

        }
    }

    public void quitaCellEditors() {
        JTextField jtf = new JTextField();
        TableColumn valueColumn = this.jTableDesign.getColumnModel().getColumn(2);
        valueColumn.setCellEditor(new DefaultCellEditor(jtf));
        valueColumn = this.jTableDesign.getColumnModel().getColumn(3);
        valueColumn.setCellEditor(new DefaultCellEditor(jtf));
        valueColumn = this.jTableDesign.getColumnModel().getColumn(4);
        valueColumn.setCellEditor(new DefaultCellEditor(jtf));
    }

    public void checkDesignTable(int colEditing, int fila, boolean useSameDesignForAll) {
        DesignTableModel designTableModel = (DesignTableModel) jTableDesign.getModel();
        DesignBean designBean = designTableModel.getDesignBean(fila);

        if(jTableDesign.getModel().getRowCount()<=0){
            return;
        }
        
        if (designBean.getDesign().equals(DesignsClass.ALFA_DESIGN)) {
            assignCellEditorAlpha(Integer.parseInt(jTextFieldEntries.getText()));
            generateBlocksSize(designBean.getReplications());
            assignCellEditorBlockSize();
            if (colEditing == 1) {
                jTableDesign.setValueAt(null, fila, 2); //repetitions
                jTableDesign.setValueAt(null, fila, 3);
                jTableDesign.setValueAt(null, fila, 4);
                jTableDesign.setValueAt(null, fila, 5);
                return;
            }
            if (colEditing == 2) {
                jTableDesign.setValueAt(null, fila, 3);
                jTableDesign.setValueAt(null, fila, 4);
                jTableDesign.setValueAt(null, fila, 5);
                useSameDesignForTrials(useSameDesignForAll);
                return;
            }
            Object blockSize = this.jTableDesign.getValueAt(fila, 3).toString();
            int entries = Integer.parseInt(this.jTextFieldEntries.getText());
            int size = Integer.parseInt(blockSize.toString());
            this.jTableDesign.setValueAt((entries / size), fila, 4);

            useSameDesignForTrials(useSameDesignForAll);
            return;
        }

        if (designBean.getDesign().equals(DesignsClass.LATICE_DESIGN)) {
            assignCellEditorLattice();
            generateBlocksSizeLattice();
            if (colEditing == 1) {
                jTableDesign.setValueAt(null, fila, 2); //repetitions
                jTableDesign.setValueAt(null, fila, 3);
                jTableDesign.setValueAt(null, fila, 4);
                jTableDesign.setValueAt(null, fila, 5);
                useSameDesignForTrials(useSameDesignForAll);
                return;
            }

            if (colEditing == 2) {
                jTableDesign.setValueAt(null, fila, 3);
                jTableDesign.setValueAt(null, fila, 4);
                jTableDesign.setValueAt(null, fila, 5);
                useSameDesignForTrials(useSameDesignForAll);
                return;
            }

            Object blockSize = this.jTableDesign.getValueAt(fila, 3).toString();
            int entries = Integer.parseInt(this.jTextFieldEntries.getText());
            int size = Integer.parseInt(blockSize.toString());
            this.jTableDesign.setValueAt((entries / size), fila, 4);

            useSameDesignForTrials(useSameDesignForAll);
            return;
        }



        if (designBean.getDesign().equals(DesignsClass.UNREPLICATED_DESIGH_WITHOUT_RANDOMIZATION)) {
            quitaCellEditors();
            this.jTableDesign.setValueAt(1, fila, 2);
            this.jTableDesign.setValueAt(Integer.parseInt(this.jTextFieldEntries.getText()), fila, 3);
            this.jTableDesign.setValueAt(1, fila, 4);
            jTableDesign.setValueAt(null, fila, 5);
            useSameDesignForTrials(useSameDesignForAll);
            return;
        }

        if (designBean.getDesign().equals(DesignsClass.UNREPLICATED_DESIGH_WITH_RANDOMIZATION)) {
            quitaCellEditors();
            this.jTableDesign.setValueAt(1, fila, 2);
            this.jTableDesign.setValueAt(Integer.parseInt(this.jTextFieldEntries.getText()), fila, 3);
            this.jTableDesign.setValueAt(1, fila, 4);
            jTableDesign.setValueAt(null, fila, 5);
            useSameDesignForTrials(useSameDesignForAll);
            return;
        }



        if (designBean.getDesign().equals(DesignsClass.RANDOMIZE_COMPLETE_BLOCK)) {
            quitaCellEditors();
            assignCellEditorReplicated();
            this.jTableDesign.setValueAt(Integer.parseInt(this.jTextFieldEntries.getText()), fila, 3);
            this.jTableDesign.setValueAt(1, fila, 4);
            jTableDesign.setValueAt(null, fila, 5);
            if (colEditing == 1) {
                this.jTableDesign.setValueAt(null, fila, 2);
                useSameDesignForTrials(useSameDesignForAll);
                return;
            }
            useSameDesignForTrials(useSameDesignForAll);

        }


        if (designBean.getDesign().equals(DesignsClass.USER_DEFINED_DESIGN)) {
            designBean.setUserDefinedDesign(FileUtils.openFile());

            if (designBean.getUserDefinedDesign() != null) {

                File userDefinedDesign = designBean.getUserDefinedDesign();


                readFactorsForDesign();
                for (int i = 0; i < facDesign.size(); i++) {
                    System.out.println("FACTOR STANDAR: " + facDesign.get(i).getFactorNameDefault());
                    if (facDesign.get(i).isFounded()) {
                        System.out.println("FOUNDED:  " + facDesign.get(i).getFactorName());
                    }
                }


                /*     if (hasCompleteHeaders(designBean.getUserDefinedDesign())) {
                 //TRIAL, ENTRY, PLOT, BLOCK, REP, COL, ROW 
                 int[] designValues = getDesignValues(Integer.parseInt(jTableDesign.getValueAt(fila, 0).toString()), userDefinedDesign, "TRIAL", "REP", "BLOCK");
                 facDesign.get(i)
                 int rep = designValues[0];
                 int block = designValues[1];
                 int blockPerReplicate = designValues[3];

                 jTableDesign.setValueAt(rep, fila, 2);
                 jTableDesign.setValueAt(block, fila, 3);
                 jTableDesign.setValueAt(blockPerReplicate, fila, 4);
                 jTableDesign.setValueAt(userDefinedDesign, fila, 5);

                 //                    NbPreferences.forModule(MacthColumsWizardPanel1.class).put("TRIAL", "TRIAL");
                 //                    NbPreferences.forModule(MacthColumsWizardPanel1.class).put("ENTRY", "ENTRY");
                 //                    NbPreferences.forModule(MacthColumsWizardPanel1.class).put("PLOT", "PLOT");
                 //                    NbPreferences.forModule(MacthColumsWizardPanel1.class).put("REP", "REP");
                 //                    NbPreferences.forModule(MacthColumsWizardPanel1.class).put("BLOCK", "BLOCK");
                 //                    NbPreferences.forModule(MacthColumsWizardPanel1.class).put("ROW", "ROW");
                 //                    NbPreferences.forModule(MacthColumsWizardPanel1.class).put("COL", "COLUMN");

                 } else {*/


                String[] hs = getHeadersFromFile(designBean.getUserDefinedDesign());

                 // System.out.println("TAM HEADER UTIL= "+hs.length);
                
                if (hs.length < 3) {

                    DialogUtil.displayError(DesignsUtils.class, "DesignsUtils.errorColumns");

                } else {


                    WizardDescriptor wiz = new WizardDescriptor(new MacthColumsWizardIterator());
                    wiz.setTitleFormat(new MessageFormat("{0} ({1})"));
                    MacthColumsWizardIterator.headers = hs;
                    MacthColumsWizardIterator.facDesign = facDesign;



                    wiz.setTitle(NbBundle.getMessage(DesignsUtils.class, "DesignsUtils.title"));

                    if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {

                        String tr = NbPreferences.forModule(MacthColumsWizardPanel1.class).get("TRIAL", "");
                        String rp = NbPreferences.forModule(MacthColumsWizardPanel1.class).get("REP", "");
                        String bl = NbPreferences.forModule(MacthColumsWizardPanel1.class).get("BLOCK", "");


                        int[] designValues = getDesignValues(Integer.parseInt(jTableDesign.getValueAt(fila, 0).toString()), userDefinedDesign, tr, rp, bl);

                        int rep = designValues[0];
                        int block = designValues[1];
                        int blockPerReplicate = designValues[3];

                        jTableDesign.setValueAt(rep, fila, 2);
                        jTableDesign.setValueAt(block, fila, 3);
                        jTableDesign.setValueAt(blockPerReplicate, fila, 4);
                        jTableDesign.setValueAt(userDefinedDesign, fila, 5);

//                        if (this.getGermplasmEntries() != block) {
//                            DialogUtil.displayWarning(DesignsUtils.class, "DesignsUtils.noMathEntryNumber");
//                        }

                    } else {

                        jTableDesign.setValueAt("", fila, 2);
                        jTableDesign.setValueAt("", fila, 3);
                        jTableDesign.setValueAt("", fila, 4);
                        jTableDesign.setValueAt("", fila, 5);
                    }
                }
                //  }

                useSameDesignForTrials(useSameDesignForAll);
            }
        }

    }

    public int[] getDesignValues(int currentTrial, File fileName, String TOZ, String ROZ, String BOZ) {
        int[] maxValues = {0, 0, 0, 0, 0, 0};
        int rowTrialCount = 0, blkCounter = 1, blkRepCounter = 1;

        //  String file = OSUtils.getPathRWD() + File.separator + fileName;
        if (!verifyCsv(currentTrial, fileName, TOZ)) {
            DialogUtil.displayError("No trial " + currentTrial + " in this CSV file.");

        } else {

            try {
                CsvReader csvReader = new CsvReader(fileName.toString());
                csvReader.readHeaders();

                String[] cabeceras = csvReader.getHeaders();

                while (csvReader.readRecord()) {

                    int trial = ConvertUtils.getValueAsInteger(csvReader.get(TOZ));
                    int rep = 1;
                    int block = 1;

                    if (Arrays.asList(cabeceras).contains(ROZ)) {
                        rep = ConvertUtils.getValueAsInteger(csvReader.get(ROZ));
                    } else {
                        rep = 1;
                    }

                    if (Arrays.asList(cabeceras).contains(BOZ)) {
                        block = ConvertUtils.getValueAsInteger(csvReader.get(BOZ));
                    } else {
                        block = 1;
                    }





                    if (trial == currentTrial) {
                        rowTrialCount++;

                        if (maxValues[2] < trial) {
                            maxValues[2] = trial;
                        }
                        if (maxValues[0] < rep) {
                            maxValues[0] = rep;
                            maxValues[3] = blkCounter;
                            blkCounter = 1;
                        } else if (maxValues[0] == rep) {
                            if (maxValues[1] < block) {
                                maxValues[1] = block;
                                maxValues[3] = blkRepCounter;
                                blkRepCounter = 1;
                            } else {
                                blkRepCounter++;
                            }
                        }

                    }
                }

                csvReader.close();

            } catch (FileNotFoundException ex) {
                System.out.println("FILE NOT FOUND. readDATAcsv.\n\t " + ex);

            } catch (IOException e) {
                System.out.println("IO EXCEPTION. readDATAcsv.\n\t " + e);
            }

            System.out.println("rowTrialCount: " + rowTrialCount);
            System.out.println("maxValues[0]: " + maxValues[0]);
            System.out.println("maxValues[3]: " + maxValues[3]);
            maxValues[1] = rowTrialCount / maxValues[0] / maxValues[3];
        }
        return maxValues;
    }

    public int[] getDesignValues(int currentTrial, File fileName, String TOZ, String BOZ) {
        int[] maxValues = {0, 0, 0, 0, 0, 0};
        int rowTrialCount = 0, blkCounter = 1, blkRepCounter = 1;

        //  String file = OSUtils.getPathRWD() + File.separator + fileName;
        if (!verifyCsv(currentTrial, fileName, TOZ)) {
            DialogUtil.displayError("No trial " + currentTrial + " in this CSV file.");

        } else {

            try {
                CsvReader csvReader = new CsvReader(fileName.toString());
                csvReader.readHeaders();


                while (csvReader.readRecord()) {

                    int trial = ConvertUtils.getValueAsInteger(csvReader.get(TOZ));
                    int rep = 1;
                    int block = ConvertUtils.getValueAsInteger(csvReader.get(BOZ));


                    if (trial == currentTrial) {
                        rowTrialCount++;

                        if (maxValues[2] < trial) {
                            maxValues[2] = trial;
                        }
                        if (maxValues[0] < rep) {
                            maxValues[0] = rep;
                            maxValues[3] = blkCounter;
                            blkCounter = 1;
                        } else if (maxValues[0] == rep) {
                            if (maxValues[1] < block) {
                                maxValues[1] = block;
                                maxValues[3] = blkRepCounter;
                                blkRepCounter = 1;
                            } else {
                                blkRepCounter++;
                            }
                        }

                    }
                }

                csvReader.close();

            } catch (FileNotFoundException ex) {
                System.out.println("FILE NOT FOUND. readDATAcsv.\n\t " + ex);

            } catch (IOException e) {
                System.out.println("IO EXCEPTION. readDATAcsv.\n\t " + e);
            }

            maxValues[1] = rowTrialCount / maxValues[0] / maxValues[3];
        }
        return maxValues;
    }

    public String[] getHeadersFromFile(File fileName) {
        String[] headers = null;
        try {
            CsvReader csvReader = new CsvReader(fileName.toString());
            csvReader.readHeaders();
            headers = csvReader.getHeaders();
//            for (int i = 0; i < headers.length; i++) {
//                System.out.println("HEADER: "+headers[i]);
//                
//            }
            
            csvReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("FILE NOT FOUND. readDATAcsv.\n\t " + ex);

        } catch (IOException e) {
            System.out.println("IO EXCEPTION. readDATAcsv.\n\t " + e);
        }
        return headers;
    }

    public boolean hasCompleteHeaders(File fileName) {

        cabeceras = new String[7];
        posiciones = new int[7];

        boolean hasComplete = false;
        int total = 0;

        try {
            CsvReader csvReader = new CsvReader(fileName.toString());
            csvReader.readHeaders();
            String[] headers = csvReader.getHeaders();

            ArrayList<String> columnas = new ArrayList<String>();

            if (csvReader.getHeaderCount() > 0) {

                for (int i = 0; i < headers.length; i++) {
                    columnas.add(headers[i].toUpperCase().trim());
                }

                if (columnas.contains(facDesign.get(0).getFactorName())) {
                    cabeceras[0] = "TRIAL";
                    posiciones[0] = columnas.indexOf(facDesign.get(0).getFactorName());
                    total++;
                }
                if (columnas.contains(facDesign.get(1).getFactorName())) {
                    cabeceras[1] = "ENTRY";
                    posiciones[1] = columnas.indexOf(facDesign.get(1).getFactorName());
                    total++;
                }
                if (columnas.contains(facDesign.get(2).getFactorName())) {
                    cabeceras[2] = "PLOT";
                    posiciones[2] = columnas.indexOf(facDesign.get(2).getFactorName());
                    total++;
                }


                if (columnas.contains("REP")) {
                    cabeceras[3] = "REP";
                    posiciones[3] = columnas.indexOf("REP");

                }
                if (columnas.contains("BLOCK")) {
                    cabeceras[4] = "BLOCK";
                    posiciones[4] = columnas.indexOf("BLOCK");

                }
                if (columnas.contains("ROW")) {
                    cabeceras[5] = "ROW";
                    posiciones[5] = columnas.indexOf("ROW");

                }
                if (columnas.contains("COLUMN")) {
                    cabeceras[6] = "COLUMN";
                    posiciones[6] = columnas.indexOf("COLUMN");

                }

                if (total == 3) {
                    hasComplete = true;
                }
            }

            csvReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("FILE NOT FOUND. readDATAcsv.\n" + ex);
            hasComplete = false;

        } catch (IOException e) {
            System.out.println("IO EXCEPTION. readDATAcsv.\n " + e);
            hasComplete = false;
        }

        System.out.println("HEADERS MATCH: " + total);

        return hasComplete;
    }

    public boolean hasCompleteHeaders2(File fileName) {

        cabeceras = new String[7];
        posiciones = new int[7];

        boolean hasComplete = false;
        int total = 0;

        try {
            CsvReader csvReader = new CsvReader(fileName.toString());
            csvReader.readHeaders();
            String[] headers = csvReader.getHeaders();

            //System.out.println("TOTAL CSV HEADERS: " + csvReader.getHeaderCount());

            ArrayList<String> columnas = new ArrayList<String>();

            if (csvReader.getHeaderCount() > 0) {

                for (int i = 0; i < headers.length; i++) {
                    columnas.add(headers[i].toUpperCase().trim());
                }

                if (columnas.contains("TRIAL")) {
                    cabeceras[0] = "TRIAL";
                    posiciones[0] = columnas.indexOf("TRIAL");
                    total++;
                }
                if (columnas.contains("ENTRY")) {
                    cabeceras[1] = "ENTRY";
                    posiciones[1] = columnas.indexOf("ENTRY");
                    total++;
                }
                if (columnas.contains("PLOT")) {
                    cabeceras[2] = "PLOT";
                    posiciones[2] = columnas.indexOf("PLOT");
                    total++;
                }
                if (columnas.contains("REP")) {
                    cabeceras[3] = "REP";
                    posiciones[3] = columnas.indexOf("REP");
                    total++;
                }
                if (columnas.contains("BLOCK")) {
                    cabeceras[4] = "BLOCK";
                    posiciones[4] = columnas.indexOf("BLOCK");
                    total++;
                }
                if (columnas.contains("ROW")) {
                    cabeceras[5] = "ROW";
                    posiciones[5] = columnas.indexOf("ROW");
                    total++;
                }
                if (columnas.contains("COLUMN")) {
                    cabeceras[6] = "COLUMN";
                    posiciones[6] = columnas.indexOf("COLUMN");
                    total++;
                }

                if (total == 7) {
                    hasComplete = true;
                }
            }

            csvReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("FILE NOT FOUND. readDATAcsv.\n" + ex);
            hasComplete = false;

        } catch (IOException e) {
            System.out.println("IO EXCEPTION. readDATAcsv.\n " + e);
            hasComplete = false;
        }

        System.out.println("HEADERS MATCH: " + total);

        return hasComplete;
    }

    public static boolean verifyCsv(int currentTrial, File fileName, String TR) {
        boolean isValid = false;

        try {
            CsvReader csvReader = new CsvReader(fileName.toString());
            csvReader.readHeaders();

            while (csvReader.readRecord()) {

                //int trial = Integer.valueOf(csvReader.get("TRIAL")).intValue();
                int trial = ConvertUtils.getValueAsInteger(csvReader.get(TR));
                if (trial == currentTrial) {
                    isValid = true;
                    break;

                }
            }
            csvReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("FILE NOT FOUND. readDATAcsv.\n\t " + ex);

        } catch (IOException e) {
            System.out.println("IO EXCEPTION. readDATAcsv.\n\t " + e);
        }


        return isValid;
    }

    private ArrayList<KSValues> validaRep2(int entries) {
        ArrayList<KSValues> repet2 = new ArrayList();
        int con = entries;
        for (int i = entries; i > 0; i--) {
            if ((entries % i) == 0) {
                int s = entries / i;
                if (KisLessThanS(i, s)) {
                    // if (cumpleConArrays(i, s)) {
                    KSValues valoresKS = new KSValues();
                    valoresKS.setBlockSize(i);
                    valoresKS.setBlocksNumber(s);
                    repet2.add(valoresKS);
                    // }
                }
            }
        }
        return repet2;
    }

    public boolean KisLessThanS(int k, int s) {
        if (k <= s) {
            return true;
        } else {
            return false;
        }
    }

    private ArrayList<KSValues> validaRep3(int entries) {
        ArrayList<KSValues> repet3 = new ArrayList();
        for (int i = entries; i > 0; i--) {
            if ((entries % i) == 0) {
                int s = entries / i;

                if (isImpar(s)) {
                    if (KisLessThanS(i, s)) {
                        //  if (cumpleConArrays(i, s)) {
                        KSValues valoresKS = new KSValues();
                        valoresKS.setBlockSize(i);
                        valoresKS.setBlocksNumber(s);
                        repet3.add(valoresKS);
                        // }
                    }

                } else {

                    if (KisLessThanSminusOne(i, s)) {
                        //  if (cumpleConArrays(i, s)) {
                        KSValues valoresKS = new KSValues();
                        valoresKS.setBlockSize(i);
                        valoresKS.setBlocksNumber(s);
                        repet3.add(valoresKS);
                        //  }
                    }


                }
            }
        }
        return repet3;
    }

    private ArrayList<KSValues> validaRep4(int entries) {
        ArrayList<KSValues> repet4 = new ArrayList();
        for (int i = entries; i > 0; i--) {
            if ((entries % i) == 0) {
                int s = entries / i;

                if (isImparButNotMultipleOfThree(s)) {
                    if (KisLessThanS(i, s)) {
                        // if (cumpleConArrays(i, s)) {
                        KSValues valoresKS = new KSValues();
                        valoresKS.setBlockSize(i);
                        valoresKS.setBlocksNumber(s);
                        repet4.add(valoresKS);
                        //  }

                    }

                }
            }
        }
        return repet4;
    }

    private boolean isImpar(int s) {
        if (s % 2 == 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean KisLessThanSminusOne(int k, int s) {
        if (k <= s - 1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isImparButNotMultipleOfThree(int s) {
        if ((s % 2 != 0) && (s % 3 != 0)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean cumpleConArrays(int k, int s) {
        boolean cumple = false;

        switch (s) {

            case 5:
                if (s == k) {
                    return true;
                } else {
                    return false;
                }

            case 6:
                if (s == k) {
                    return true;
                } else {
                    return false;
                }
            case 7:
                if (s == k) {
                    return true;
                } else {
                    return false;
                }
            case 8:
                if (s == k) {
                    return true;
                } else {
                    return false;
                }
            case 9:
                if (s == k) {
                    return true;
                } else {
                    return false;
                }
            case 10:
                if (s == k) {
                    return true;
                } else {
                    return false;
                }

            case 11:
                if (k == 9) {
                    return true;
                } else {
                    return false;
                }

            case 12:
                if (k == 8) {
                    return true;
                } else {
                    return false;
                }

            case 13:
                if (k == 7) {
                    return true;
                } else {
                    return false;
                }

            case 14:

                if (k == 7) {
                    return true;
                } else {
                    return false;
                }

            case 15:

                if (k == 6) {
                    return true;
                } else {
                    return false;
                }
        }

        return cumple;
    }

    public void setWorkbook(Workbook theWorkbook) {
        this.myWorkbook = theWorkbook;
    }

    public void readFactorsForDesign() {
        System.out.println("LEEMOS FACTORES PARA DISEÑO");
        facDesign = new ArrayList<FactorsForDesign>();
        //TRIAL, ENTRY, PLOT, BLOCK, REP, COL, ROW 

        for (int i = 0; i < 7; i++) {
            FactorsForDesign f = new FactorsForDesign();
            f.setFounded(false);
            switch (i) {
                case 0:
                    f.setFactorNameDefault("TRIAL");
                    break;
                case 1:
                    f.setFactorNameDefault("ENTRY");
                    break;
                case 2:
                    f.setFactorNameDefault("PLOT");
                    break;
                case 3:
                    f.setFactorNameDefault("BLOCK");
                    break;
                case 4:
                    f.setFactorNameDefault("REP");
                    break;
                case 5:
                    f.setFactorNameDefault("COL");
                    break;
                case 6:
                    f.setFactorNameDefault("ROW");
                    break;
            }
            facDesign.add(f);

        }

        
        
        for (int i = 0; i < myWorkbook.getConditions().size(); i++) {
            if (myWorkbook.getConditions().get(i).getProperty().toUpperCase().equals("TRIAL INSTANCE") && (myWorkbook.getConditions().get(i).getScale().toUpperCase().equals("NUMBER"))) {
                facDesign.get(0).setFactorName(myWorkbook.getConditions().get(i).getConditionName());
                facDesign.get(0).setFactorLabel(myWorkbook.getConditions().get(i).getLabel());
                facDesign.get(0).setFounded(true);
                break;
            }
 
            else if (myWorkbook.getConditions().get(i).getProperty().toUpperCase().equals("TRIAL INSTANCE") && (myWorkbook.getConditions().get(i).getScale().toUpperCase().equals("NESTED NUMBER"))) {
                facDesign.get(0).setFactorName(myWorkbook.getConditions().get(i).getConditionName());
                facDesign.get(0).setFactorLabel(myWorkbook.getConditions().get(i).getLabel());
                facDesign.get(0).setFounded(true);
                break;
            }

        }
        
        
        

        for (int i = 0; i < myWorkbook.getFactors().size(); i++) {

            if (myWorkbook.getFactors().get(i).getProperty().toUpperCase().equals("GERMPLASM ENTRY") && (myWorkbook.getFactors().get(i).getScale().toUpperCase().equals("NUMBER"))) {
                facDesign.get(1).setFactorName(myWorkbook.getFactors().get(i).getFactorName());
                facDesign.get(1).setFactorLabel(myWorkbook.getFactors().get(i).getLabel());
                facDesign.get(1).setFounded(true);
            }

            if (myWorkbook.getFactors().get(i).getProperty().toUpperCase().equals("FIELD PLOT") && (myWorkbook.getFactors().get(i).getScale().toUpperCase().equals("NESTED NUMBER"))) {
                facDesign.get(2).setFactorName(myWorkbook.getFactors().get(i).getFactorName());
                facDesign.get(2).setFactorLabel(myWorkbook.getFactors().get(i).getLabel());
                facDesign.get(2).setFounded(true);
            }

            if (myWorkbook.getFactors().get(i).getProperty().toUpperCase().equals("BLOCK") && (myWorkbook.getFactors().get(i).getScale().toUpperCase().equals("NUMBER"))) {
                facDesign.get(3).setFactorName(myWorkbook.getFactors().get(i).getFactorName());
                facDesign.get(3).setFactorLabel(myWorkbook.getFactors().get(i).getLabel());
                facDesign.get(3).setFounded(true);
            }
            if (myWorkbook.getFactors().get(i).getProperty().toUpperCase().equals("REPLICATION") && (myWorkbook.getFactors().get(i).getScale().toUpperCase().equals("NUMBER"))) {
                facDesign.get(4).setFactorName(myWorkbook.getFactors().get(i).getFactorName());
                facDesign.get(4).setFactorLabel(myWorkbook.getFactors().get(i).getLabel());
                facDesign.get(4).setFounded(true);
            }

            if (myWorkbook.getFactors().get(i).getProperty().toUpperCase().equals("COLUMN IN LAYOUT") && (myWorkbook.getFactors().get(i).getScale().toUpperCase().equals("NUMBER"))) {
                facDesign.get(5).setFactorName(myWorkbook.getFactors().get(i).getFactorName());
                facDesign.get(5).setFactorLabel(myWorkbook.getFactors().get(i).getLabel());
                facDesign.get(5).setFounded(true);
            }

            if (myWorkbook.getFactors().get(i).getProperty().toUpperCase().equals("ROW IN LAYOUT") && (myWorkbook.getFactors().get(i).getScale().toUpperCase().equals("NUMBER"))) {
                facDesign.get(6).setFactorName(myWorkbook.getFactors().get(i).getFactorName());
                facDesign.get(6).setFactorLabel(myWorkbook.getFactors().get(i).getLabel());
                facDesign.get(6).setFounded(true);
            }

        }
        DesignsClass.facDesign = facDesign;
    }
}
