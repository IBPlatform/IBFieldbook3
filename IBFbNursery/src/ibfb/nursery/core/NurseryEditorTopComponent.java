package ibfb.nursery.core;

import ibfb.domain.core.*;
import ibfb.nursery.advance.AdvanceLineTopComponent;
import ibfb.nursery.advance.MethodsClass;
import ibfb.nursery.advancewizard.AdvanceWizardIterator;
import ibfb.nursery.advancewizard.AdvanceWizardPanel1;
import ibfb.nursery.editors.AlphaDesignsRowEditor;
import ibfb.nursery.editors.ConditionsRowEditor;
import ibfb.nursery.editors.NurseryConditionsRowEditor;
import ibfb.nursery.exportwizard.FieldBookExcelExporter;
import ibfb.nursery.exportwizard.exportVisualPanel3;
import ibfb.nursery.exportwizard.exportWizardIterator;
import ibfb.nursery.exportwizard.exportWizardPanelGYTrait;
import ibfb.nursery.filters.ExcelFiltro;
import ibfb.nursery.importwizard.ImportData;
import ibfb.nursery.importwizard.importingVisualPanel2;
import ibfb.nursery.importwizard.importingWizardIterator;
import ibfb.nursery.maize.MaizeEntry;
import ibfb.nursery.maize.MaizeMethods;
import ibfb.nursery.maize.PolinizationWizardIterator;
import ibfb.nursery.maize.PolinizationWizardPanel1;
import ibfb.nursery.models.*;
import ibfb.nursery.savewizard.saveVisualPanel2;
import ibfb.nursery.utils.*;
import ibfb.traits.traits.TraitsExplorerTopComponent;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.*;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.utils.ToolTipUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationUtils;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.ConvertUtils;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.cimmyt.cril.ibwb.commongui.select.list.DoubleListPanel;
import org.cimmyt.cril.ibwb.commongui.select.list.DropTargetCommand;
import org.cimmyt.cril.ibwb.commongui.select.list.SelectCommand;
import org.cimmyt.cril.ibwb.domain.GermplasmSearch;
import org.cimmyt.cril.ibwb.domain.Names;
import org.cimmyt.cril.ibwb.domain.Traits;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.WizardDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Exceptions;
import org.openide.util.Mutex;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

@ConvertAsProperties(dtd = "-//ibfb.nursey.core//NurseryEditor//EN",
autostore = false)
@TopComponent.Description(preferredID = "NurseryEditorTopComponent",
iconBase = "ibfb/nursery/images/newNursery16.png",
persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "ibfb.nursery.core.NurseryEditorTopComponent")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_NurseryEditorAction",
preferredID = "NurseryEditorTopComponent")
public final class NurseryEditorTopComponent extends TopComponent {

    final static String badchars = "`~!@#$%^&*()_+=\\|\"':;?/>.<, ";
    private JFileChooser selectorArchivo = new JFileChooser();
    private TableDataExporterHelper helper = new TableDataExporterHelper();
    private int fila = 0;
    TableRowSorter<TableModel> sorterNurseryInformation;
    TableRowSorter<TableModel> sorterConstants;
    TableRowSorter<TableModel> sorterMeasurements;
    TableRowSorter<TableModel> sorterDesign;
    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel modelConstants = new DefaultTableModel();
    DefaultTableModel modelMeasurements = new DefaultTableModel();
    DefaultTableModel modelDesign = new DefaultTableModel();
    DefaultTableModel modelObs = new DefaultTableModel();
    private CSVOziel csv;
    AlphaDesignsRowEditor alphaRowEditorStudy;
    private Workbook myWorkbook;
    private String fileTemplate;
    DefaultListModel listModelSelected = new DefaultListModel();
    DefaultListModel listModelUnSelected = new DefaultListModel();
    private TransferHandler transfer = new ListItemTransferHandler(2);
    java.util.ArrayList<Variate> traits = new ArrayList();
    DefaultListModel listModelA = new DefaultListModel();
    DefaultListModel listModelB = new DefaultListModel();
    public int trialStart = 0;
    public int trialEnd = 0;
    public int trialSelected = 0;
    public int triallOption = 0; //0=all trial, 1=selectedTrial, 2=rango de trials
    public String trialFile = "";
    public File nurseryFile;
    public int opcionFiltro = 0;
    public int opcionExport = 0; //0=fieldlog, 1=to r, 2=excel file
    public int opcionImport = 0; //0=fieldlog, 1=excel 
    private int extraColumns = 0;
    private ArrayList extraColumnsHeaders = new ArrayList<String>();
    private ArrayList headers = new ArrayList<String>();
    private ArrayList headersNew = new ArrayList<String>();
    private DesignsClass disenios = new DesignsClass();
    private static final int STUDY_PROPERTY_COL = 2;
    private static final int STUDY_SCALE_COL = 3;
    private static final int STUDY_VALUE_COL = 4;
    private static final int TRIAL_INSTANCE_COL = 0;
    private static final int TRIAL_PROPERTY_COL = 3;
    private static final int TRIAL_SCALE_COL = 4;
    private static final int TRIAL_VALUE_COL = 5;
    private int combinations = 0;
    private String[][] factorsDesignCad;
    private ArrayList<String> otherFactors;
    private ArrayList<String> children;
    private Study study;
    private List<Variate> availableTraits = new ArrayList<Variate>();
    private List<Variate> selectedTraits = new ArrayList<Variate>();
    private DoubleListPanel<Variate> doubleListPanel;
    ArrayList<Integer> posiciones;
    private boolean studyAlreadyExists;
    private Study studyInfo;
    private BalloonTip tipSavingNursery;
    private boolean byPosition = false;
    private JTable tableChecks;
    private String method;
    public static Configuration config = null;
    private Variate traitToEvaluate;
    private String stringTraitToEvaluate = "GY";
    private boolean checksInSequence = false;
    private ArrayList<SequenceEntry> sequenceList;

    public NurseryEditorTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(NurseryEditorTopComponent.class, "CTL_NurseryEditorTopComponent"));
        setToolTipText(NbBundle.getMessage(NurseryEditorTopComponent.class, "HINT_NurseryEditorTopComponent"));
        modelMeasurements = (DefaultTableModel) this.jTableObservations.getModel();
        sorterMeasurements = new TableRowSorter<TableModel>(modelMeasurements);
        this.jTableObservations.setRowSorter(sorterMeasurements);
        model = (DefaultTableModel) this.jTableNurseryConditions.getModel();
        sorterNurseryInformation = new TableRowSorter<TableModel>(model);
        this.jTableNurseryConditions.setRowSorter(sorterNurseryInformation);
        modelConstants = (DefaultTableModel) this.jTableConstants.getModel();
        sorterConstants = new TableRowSorter<TableModel>(modelConstants);
        this.jTableConstants.setRowSorter(sorterConstants);
        modelObs = (DefaultTableModel) this.jTableObservations.getModel();
        assignCellEditorTrialCndition();
        assignCellEditorStudyConditions();
        this.jTableStudyConditions.getTableHeader().addMouseListener(new ColumnFitAdapter());
        this.jTableNurseryConditions.getTableHeader().addMouseListener(new ColumnFitAdapter());
        this.jTableConstants.getTableHeader().addMouseListener(new ColumnFitAdapter());
        this.jTableEntries.getTableHeader().addMouseListener(new ColumnFitAdapter());
        this.jTableObservations.getTableHeader().addMouseListener(new ColumnFitAdapter());
        csv = new CSVOziel(this.jTableObservations, new JList());
        deshabilitaSorters();
        doubleListPanel = new DoubleListPanel<Variate>(availableTraits, selectedTraits, unselectedCommand, selectedCommand);
        doubleListPanel.setSourceLabel(NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.unselected"));
        doubleListPanel.setTargetLabel(NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.selected"));
        doubleListPanel.setVisible(true);
        doubleListPanel.setDropTargetCommand(new TraitsDropTargetCommand());
        pnlSelectList.add(doubleListPanel);
        studyAlreadyExists = false;
        studyInfo = new Study();
        createBallonTips();
        posiciones = new ArrayList<Integer>();
    }
    private SelectCommand unselectedCommand = new SelectCommand() {

        @Override
        public void execute() {
            Variate variate = doubleListPanel.getSelectedSourceItem();
            jTextFieldDescription.setText(variate.getDescription());
        }
    };
    private SelectCommand selectedCommand = new SelectCommand() {

        @Override
        public void execute() {
            Variate variate = doubleListPanel.getSelectedTargetItem();
            jTextFieldDescriptionSelected.setText(variate.getDescription());
        }
    };

    public boolean isChecksInSequence() {
        return checksInSequence;
    }

    public void setChecksInSequence(boolean checksInSequence) {
        this.checksInSequence = checksInSequence;
    }

    public ArrayList<SequenceEntry> getSequenceList() {
        return sequenceList;
    }

    public void setSequenceList(ArrayList<SequenceEntry> sequenceList) {
        this.sequenceList = sequenceList;
    }

    public ArrayList<Integer> getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(ArrayList<Integer> posiciones) {
        this.posiciones = posiciones;
    }

    public boolean isByPosition() {
        return byPosition;
    }

    public void setByPosition(boolean byPosition) {
        this.byPosition = byPosition;
    }

    public List<Variate> getSelectedTraits() {
        return selectedTraits;
    }

    public void setSelectedTraits(List<Variate> selectedTraits) {
        this.selectedTraits = selectedTraits;
    }

    public Variate getTraitToEvaluate() {
        return traitToEvaluate;
    }

    public void setTraitToEvaluate(Variate traitToEvaluate) {
        this.traitToEvaluate = traitToEvaluate;
    }

    public String getStringTraitToEvaluate() {
        return stringTraitToEvaluate;
    }

    public void setStringTraitToEvaluate(String stringTraitToEvaluate) {
        this.stringTraitToEvaluate = stringTraitToEvaluate;
    }

    public boolean hasGYbyDefault() {
        boolean hasGY = false;
        ObservationsTableModel modeloOriginal = (ObservationsTableModel) jTableObservations.getModel();
        if (modeloOriginal.findColumn("GY") >= 0) {
            hasGY = true;
        }
        return hasGY;
    }

    private void importFromFieldroid() {
        try {

            CSVOziel csvOziel = new CSVOziel(this.jTableObservations, new JList());

            if (!csvOziel.isValid(nurseryFile)) {

                DialogUtil.displayError(NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.fileInvalid"));
                return;
            }

            csvOziel.readDATAnew(nurseryFile);

            DialogUtil.displayInfo(ImportData.class, "ImportData.importsuccess");
        } catch (Exception e) {

            DialogUtil.displayWarning(ImportData.class, "ImportData.importfail");

        }
    }

    private void importFromExcel() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void setTableChechs(JTable jTableFinalList) {
        this.tableChecks = jTableFinalList;
    }

    private void fillChecksValue() {

        ObservationsTableModel tableModel = (ObservationsTableModel) this.jTableObservations.getModel();

        int checkColum = tableModel.findColumn("CHECK");
        if (checkColum > 0) {
            for (int i = 0; i < posiciones.size(); i++) {
                jTableObservations.setValueAt("OK", posiciones.get(i), checkColum);
            }

        }
    }

    private ArrayList<Integer> giveMeNewPosition(int[] vector) {
        ArrayList<Integer> temporal = new ArrayList<Integer>();
        ArrayList<Integer> nuevas = new ArrayList<Integer>();


        for (int i = 0; i < vector.length; i++) {
            temporal.add(vector[i]);
        }

        for (int i = 0; i < posiciones.size(); i++) {
            int pos = temporal.indexOf(posiciones.get(i));
            nuevas.add(pos);

        }


        Collections.sort(nuevas);

        for (int i = 0; i < nuevas.size(); i++) {
            System.out.print("NUEVAS: " + " " + nuevas.get(i));

        }

        return nuevas;
    }

    private int giveMeSelectionValue(int i, int colSelection, ObservationsTableModel modelo) {

        int selection = 0;
        try {
            Double doubleValue = Double.parseDouble(modelo.getValueAt(i, colSelection).toString());
            selection = doubleValue.intValue();

        } catch (Exception e) {

            selection = 0;
        }

        return selection;

    }

    private boolean selectionValue(int i, int colSelection, ObservationsTableModel modelo) {

        boolean isValid = false;
        try {
            //int value = Integer.parseInt(modelo.getValueAt(i, colSelection).toString());
            int value = ConvertUtils.getValueAsInteger(modelo.getValueAt(i, colSelection).toString());
            isValid = true;

        } catch (Exception e) {

            isValid = false;
        }

        return isValid;

    }

    private boolean hayValoresParaProcesar(ObservationsTableModel modelo, int colSelection) {
        boolean hayValores = false;

        for (int i = 0; i < modelo.getRowCount(); i++) {
            try {

                Double doubleValue = Double.parseDouble(modelo.getValueAt(i, colSelection).toString());

                int valor = doubleValue.intValue();
                if (valor > 0) {
                    return true;
                }

            } catch (Exception e) {
            }



        }

        return hayValores;
    }

    private String giveMeDefaultLocation() {

        String location = "";
        if (loadFileConfig()) {

            if (config.getString("location") != null) {
                return config.getString("location");
            }

        }

        return location;
    }

    private void showMaizeWizard() {

        WizardDescriptor wiz = new WizardDescriptor(new PolinizationWizardIterator());
        // {0} will be replaced by WizardDescriptor.Panel.getComponent().getName()
        // {1} will be replaced by WizardDescriptor.Iterator.name()
        wiz.setTitleFormat(new MessageFormat("{0} ({1})"));
        wiz.setTitle("...dialog title...");
        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
        }

    }

    private class TraitsDropTargetCommand implements DropTargetCommand {

        @Override
        public void onDropExecute(String droppedText) {
            String[] rows = droppedText.split("\n");
            for (String droppedRow : rows) {
                String[] values = droppedRow.split("\t");
                Integer traitId = Integer.parseInt(values[0].trim());
                Traits trait = AppServicesProxy.getDefault().appServices().getTraits(traitId);
                Variate variate = new Variate();
                variate.setVariateName(trait.getTrname().trim());
                variate.setDescription(trait.getTrdesc().trim());
                variate.setScale("UNASSINGED");
                variate.setMethod("UNNASSIGNED");
                variate.setDataType("N");

                if (trait.getMeasuredin() != null) {
                    if (trait.getMeasuredin().getScales() != null) {
                        variate.setScale(trait.getMeasuredin().getScales().getScname());
                        variate.setDataType(trait.getMeasuredin().getScales().getDtype());
                    }
                    if (trait.getMeasuredin().getTmsMethod() != null) {
                        variate.setMethod(trait.getMeasuredin().getTmsMethod().getTmname());
                    }
                }

                doubleListPanel.addToTarget(variate);
            }
        }
    }

    public JTextField getjTextFieldNurseryName() {
        return jTextFieldNurseryName;
    }

    public void setjTextFieldNurseryName(JTextField jTextFieldNurseryName) {
        this.jTextFieldNurseryName = jTextFieldNurseryName;
    }

    public ArrayList<String> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<String> children) {
        this.children = children;
    }

    public ArrayList<String> getOtherFactors() {
        return otherFactors;
    }

    public void setOtherFactors(ArrayList<String> otherFactors) {
        this.otherFactors = otherFactors;
    }

    public int getCombinations() {
        return combinations;
    }

    public void setCombinations(int combinations) {
        this.combinations = combinations;
    }

    public String getFileTemplate() {
        return fileTemplate;
    }

    public void setFileTemplate(String fileTemplate) {
        this.fileTemplate = fileTemplate;
    }

    public Workbook getMyWorkbook() {
        return myWorkbook;
    }

    public void setMyWorkbook(Workbook myWorkbook) {
        this.myWorkbook = myWorkbook;
    }

    public JTextField getjTextFieldEntries() {
        return jTextFieldEntries;
    }

    public void setjTextFieldEntries(JTextField jTextFieldEntries) {
        this.jTextFieldEntries = jTextFieldEntries;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupTrInformation = new javax.swing.ButtonGroup();
        buttonGroupExpConditions = new javax.swing.ButtonGroup();
        buttonGroupDesign = new javax.swing.ButtonGroup();
        buttonGroupMeasurements = new javax.swing.ButtonGroup();
        jPopupMenuTraits = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPopupMenuConstants = new javax.swing.JPopupMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jPopupMenuUnSelect = new javax.swing.JPopupMenu();
        jMenuItemUnSelect = new javax.swing.JMenuItem();
        jMenuItemUnselectAll = new javax.swing.JMenuItem();
        jPopupMenuSelect = new javax.swing.JPopupMenu();
        jMenuItemSelect = new javax.swing.JMenuItem();
        jMenuItemSelectAll = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableStudyConditions = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        JPanelData = new javax.swing.JPanel();
        jTextFieldObjective = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldStudy = new javax.swing.JTextField();
        jTextFieldTitle = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxStudyType = new javax.swing.JComboBox();
        jTextFieldPMKey = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jDateChooserStart = new com.toedter.calendar.JDateChooser();
        jDateChooserEnd = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableNurseryConditions = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableConstants = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableEntries = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jButtonCopyGID = new javax.swing.JButton();
        jButtonImportCrossInfo = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabelTotalGermp = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jRadioButtonNormal = new javax.swing.JRadioButton();
        jRadioButtonRandom = new javax.swing.JRadioButton();
        jLabelEntries = new javax.swing.JLabel();
        jTextFieldEntries = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanelTraits = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldDescription = new javax.swing.JTextField();
        jTextFieldDescriptionSelected = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        pnlSelectList = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jButtonSelectTraits = new javax.swing.JButton();
        jButtonSync = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableObservations = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButtonSaveNursery = new javax.swing.JButton();
        jButtonCSVTraitsExport1 = new javax.swing.JButton();
        jButtonCSVTraitsImport1 = new javax.swing.JButton();
        jButtonAdvance = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNurseryName = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabelObsEntries = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItem1, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jMenuItem1.text")); // NOI18N
        jMenuItem1.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jMenuItem1.toolTipText")); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenuTraits.add(jMenuItem1);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItem2, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jMenuItem2.text")); // NOI18N
        jMenuItem2.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jMenuItem2.toolTipText")); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenuTraits.add(jMenuItem2);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItem3, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jMenuItem3.text")); // NOI18N
        jMenuItem3.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jMenuItem3.toolTipText")); // NOI18N
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jPopupMenuConstants.add(jMenuItem3);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItem4, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jMenuItem4.text")); // NOI18N
        jMenuItem4.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jMenuItem4.toolTipText")); // NOI18N
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jPopupMenuConstants.add(jMenuItem4);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemUnSelect, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jMenuItemUnSelect.text")); // NOI18N
        jMenuItemUnSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUnSelectActionPerformed(evt);
            }
        });
        jPopupMenuUnSelect.add(jMenuItemUnSelect);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemUnselectAll, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jMenuItemUnselectAll.text")); // NOI18N
        jMenuItemUnselectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUnselectAllActionPerformed(evt);
            }
        });
        jPopupMenuUnSelect.add(jMenuItemUnselectAll);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemSelect, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jMenuItemSelect.text")); // NOI18N
        jMenuItemSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSelectActionPerformed(evt);
            }
        });
        jPopupMenuSelect.add(jMenuItemSelect);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemSelectAll, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jMenuItemSelectAll.text")); // NOI18N
        jMenuItemSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSelectAllActionPerformed(evt);
            }
        });
        jPopupMenuSelect.add(jMenuItemSelectAll);

        jTabbedPane1.setMinimumSize(new java.awt.Dimension(0, 0));

        jTableStudyConditions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Condition", "Description", "Property", "Scale", "Value"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableStudyConditions.getTableHeader().setReorderingAllowed(false);
        jTableStudyConditions.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableStudyConditionsPropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(jTableStudyConditions);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jLabel2.text")); // NOI18N

        JPanelData.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldObjective.setText(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jTextFieldObjective.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jLabel10.text")); // NOI18N

        jTextFieldStudy.setText(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jTextFieldStudy.text")); // NOI18N
        jTextFieldStudy.setMinimumSize(new java.awt.Dimension(14, 50));

        jTextFieldTitle.setText(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jTextFieldTitle.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jLabel4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jLabel6.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jLabel7.text")); // NOI18N

        jComboBoxStudyType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "International", "National", "Bank", "Foreign" }));

        jTextFieldPMKey.setText(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jTextFieldPMKey.text")); // NOI18N
        jTextFieldPMKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPMKeyActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jLabel9.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jLabel11.text")); // NOI18N

        jDateChooserStart.setDateFormatString(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jDateChooserStart.dateFormatString")); // NOI18N
        jDateChooserStart.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jDateChooserStartInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jDateChooserStart.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserStartPropertyChange(evt);
            }
        });

        jDateChooserEnd.setDateFormatString(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jDateChooserEnd.dateFormatString")); // NOI18N
        jDateChooserEnd.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jDateChooserEndInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jDateChooserEnd.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserEndPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout JPanelDataLayout = new javax.swing.GroupLayout(JPanelData);
        JPanelData.setLayout(JPanelDataLayout);
        JPanelDataLayout.setHorizontalGroup(
            JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelDataLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JPanelDataLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(JPanelDataLayout.createSequentialGroup()
                        .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldTitle)
                    .addComponent(jTextFieldObjective)
                    .addComponent(jTextFieldStudy, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(90, 90, 90)
                .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxStudyType, 0, 150, Short.MAX_VALUE)
                    .addComponent(jTextFieldPMKey, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jDateChooserEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooserStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(394, 394, 394))
        );

        JPanelDataLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextFieldObjective, jTextFieldStudy, jTextFieldTitle});

        JPanelDataLayout.setVerticalGroup(
            JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanelDataLayout.createSequentialGroup()
                        .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jDateChooserStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jDateChooserEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPanelDataLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanelDataLayout.createSequentialGroup()
                                .addComponent(jComboBoxStudyType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldPMKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(JPanelDataLayout.createSequentialGroup()
                        .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(jTextFieldStudy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldObjective, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        JPanelDataLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel3, jLabel4, jTextFieldObjective, jTextFieldTitle});

        JPanelDataLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel6, jLabel7, jTextFieldStudy});

        JPanelDataLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBoxStudyType, jLabel11, jLabel9, jTextFieldPMKey});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1106, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JPanelData, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPanelData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jTableNurseryConditions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Trial", "Condition", "Description", "Property", "Scale", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableNurseryConditions.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableNurseryConditions.getTableHeader().setReorderingAllowed(false);
        jTableNurseryConditions.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableNurseryConditionsPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(jTableNurseryConditions);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1138, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jScrollPane5.setComponentPopupMenu(jPopupMenuConstants);

        jTableConstants.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Trial", "Constant", "Description", "Scale", "Value"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableConstants.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableConstants.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(jTableConstants);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1138, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        jTableEntries.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Entry"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableEntries.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableEntries.getTableHeader().setReorderingAllowed(false);
        jTableEntries.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableEntriesPropertyChange(evt);
            }
        });
        jScrollPane6.setViewportView(jTableEntries);

        jButtonCopyGID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/copyGID.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonCopyGID, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jButtonCopyGID.text")); // NOI18N
        jButtonCopyGID.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jButtonCopyGID.toolTipText")); // NOI18N
        jButtonCopyGID.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCopyGID.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonCopyGID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCopyGIDActionPerformed(evt);
            }
        });

        jButtonImportCrossInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/crossInfoSmall.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonImportCrossInfo, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jButtonImportCrossInfo.text")); // NOI18N
        jButtonImportCrossInfo.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jButtonImportCrossInfo.toolTipText")); // NOI18N
        jButtonImportCrossInfo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonImportCrossInfo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonImportCrossInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImportCrossInfoActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel15, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jLabel15.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelTotalGermp, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jLabelTotalGermp.text")); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonCopyGID, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonImportCrossInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTotalGermp, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonImportCrossInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabelTotalGermp))
                        .addGap(25, 25, 25))
                    .addComponent(jButtonCopyGID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1138, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jPanel7.TabConstraints.tabTitle"), jPanel7); // NOI18N

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/random.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jLabel8.text")); // NOI18N

        buttonGroupDesign.add(jRadioButtonNormal);
        jRadioButtonNormal.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonNormal, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jRadioButtonNormal.text")); // NOI18N
        jRadioButtonNormal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonNormalMouseClicked(evt);
            }
        });

        buttonGroupDesign.add(jRadioButtonRandom);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonRandom, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jRadioButtonRandom.text")); // NOI18N
        jRadioButtonRandom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonRandomMouseClicked(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelEntries, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jLabelEntries.text")); // NOI18N

        jTextFieldEntries.setEditable(false);
        jTextFieldEntries.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jTextFieldEntries.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldEntries.setText(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jTextFieldEntries.text")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(261, 261, 261)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabelEntries)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldEntries, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jRadioButtonNormal)
                    .addComponent(jRadioButtonRandom))
                .addGap(311, 311, 311))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldEntries)
                            .addComponent(jLabelEntries))
                        .addGap(47, 47, 47)
                        .addComponent(jRadioButtonNormal)
                        .addGap(58, 58, 58)
                        .addComponent(jRadioButtonRandom)
                        .addGap(86, 86, 86))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(321, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabelEntries, jTextFieldEntries});

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(167, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jPanel8.TabConstraints.tabTitle"), jPanel8); // NOI18N

        jPanel6.setMaximumSize(new java.awt.Dimension(1000, 650));
        jPanel6.setMinimumSize(new java.awt.Dimension(1000, 650));

        jPanelTraits.setPreferredSize(new java.awt.Dimension(900, 650));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jLabel5.text")); // NOI18N

        jTextFieldDescription.setEditable(false);
        jTextFieldDescription.setText(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jTextFieldDescription.text")); // NOI18N

        jTextFieldDescriptionSelected.setEditable(false);
        jTextFieldDescriptionSelected.setText(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jTextFieldDescriptionSelected.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jLabel13.text")); // NOI18N

        jButtonSelectTraits.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jButtonSelectTraits.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/searchDB.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSelectTraits, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jButtonSelectTraits.text")); // NOI18N
        jButtonSelectTraits.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jButtonSelectTraits.toolTipText")); // NOI18N
        jButtonSelectTraits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelectTraitsActionPerformed(evt);
            }
        });

        jButtonSync.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jButtonSync.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/sync.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSync, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jButtonSync.text")); // NOI18N
        jButtonSync.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jButtonSync.toolTipText")); // NOI18N
        jButtonSync.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSyncActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSync, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonSelectTraits, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSelectTraits, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSync, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelTraitsLayout = new javax.swing.GroupLayout(jPanelTraits);
        jPanelTraits.setLayout(jPanelTraitsLayout);
        jPanelTraitsLayout.setHorizontalGroup(
            jPanelTraitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTraitsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTraitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlSelectList, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1106, Short.MAX_VALUE)
                    .addGroup(jPanelTraitsLayout.createSequentialGroup()
                        .addGroup(jPanelTraitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldDescription, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE))
                        .addGap(124, 124, 124)
                        .addGroup(jPanelTraitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldDescriptionSelected, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)))
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanelTraitsLayout.setVerticalGroup(
            jPanelTraitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTraitsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlSelectList, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addGroup(jPanelTraitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTraitsLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTraitsLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDescriptionSelected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTraits, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTraits, javax.swing.GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jPanel6.TabConstraints.tabTitle"), jPanel6); // NOI18N

        jPanel21.setPreferredSize(new java.awt.Dimension(900, 650));

        jScrollPane7.setAutoscrolls(true);
        jScrollPane7.setMinimumSize(new java.awt.Dimension(0, 0));
        jScrollPane7.setPreferredSize(new java.awt.Dimension(450, 400));

        jTableObservations.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableObservations.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTableObservations.getTableHeader().setReorderingAllowed(false);
        jTableObservations.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableObservationsPropertyChange(evt);
            }
        });
        jScrollPane7.setViewportView(jTableObservations);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jPanel5.border.title"))); // NOI18N
        jPanel5.setPreferredSize(new java.awt.Dimension(187, 85));

        jButtonSaveNursery.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/save.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSaveNursery, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jButtonSaveNursery.text")); // NOI18N
        jButtonSaveNursery.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jButtonSaveNursery.toolTipText")); // NOI18N
        jButtonSaveNursery.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSaveNursery.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSaveNursery.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jButtonSaveNurseryStateChanged(evt);
            }
        });
        jButtonSaveNursery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveNurseryActionPerformed(evt);
            }
        });

        jButtonCSVTraitsExport1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/export.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonCSVTraitsExport1, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jButtonCSVTraitsExport1.text")); // NOI18N
        jButtonCSVTraitsExport1.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jButtonCSVTraitsExport1.toolTipText")); // NOI18N
        jButtonCSVTraitsExport1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCSVTraitsExport1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonCSVTraitsExport1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCSVTraitsExport1ActionPerformed(evt);
            }
        });

        jButtonCSVTraitsImport1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/csvFile.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonCSVTraitsImport1, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jButtonCSVTraitsImport1.text")); // NOI18N
        jButtonCSVTraitsImport1.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jButtonCSVTraitsImport1.toolTipText")); // NOI18N
        jButtonCSVTraitsImport1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCSVTraitsImport1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonCSVTraitsImport1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCSVTraitsImport1ActionPerformed(evt);
            }
        });

        jButtonAdvance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/advance.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonAdvance, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jButtonAdvance.text")); // NOI18N
        jButtonAdvance.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jButtonAdvance.toolTipText")); // NOI18N
        jButtonAdvance.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonAdvance.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonAdvance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdvanceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonSaveNursery, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButtonCSVTraitsExport1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jButtonCSVTraitsImport1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jButtonAdvance, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSaveNursery)
                .addGap(31, 31, 31)
                .addComponent(jButtonCSVTraitsExport1)
                .addGap(28, 28, 28)
                .addComponent(jButtonCSVTraitsImport1)
                .addGap(28, 28, 28)
                .addComponent(jButtonAdvance)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jLabel1.text")); // NOI18N

        jTextFieldNurseryName.setText(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jTextFieldNurseryName.text")); // NOI18N
        jTextFieldNurseryName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldNurseryNameKeyReleased(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel14, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jLabel14.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelObsEntries, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jLabelObsEntries.text")); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNurseryName, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelObsEntries)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldNurseryName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabelObsEntries))
                .addContainerGap())
        );

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/logoIBF.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jLabel12.text")); // NOI18N

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1041, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jPanel10.TabConstraints.tabTitle"), jPanel10); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void exportToExcel() {
        List<Constant> selectedConstants = WorkbookSavingHelper.getSelectedConstants(this);
        FieldBookExcelExporter excelExporter = new FieldBookExcelExporter(jTableObservations, fileTemplate, trialFile, study, myWorkbook, selectedConstants);
        excelExporter.setStudyConditionsTable(this.jTableStudyConditions);
        excelExporter.setTrialConditionsTable(this.jTableNurseryConditions);
        excelExporter.setConstantsTable(this.jTableConstants);
        excelExporter.exportToExcel(0, trialStart, trialEnd, trialSelected);

    }

    private void exportToFieldroid() {
        FieldbookCSVExporter.exportToFieldlog(jTableObservations, trialFile, csv, triallOption, trialStart, trialEnd, trialSelected);
    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        excludeColumns();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        includeColumns();

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        //EXCLUDE
        int columna = 5;

        if (jTableConstants.getCellEditor() != null) {
            jTableConstants.getCellEditor().stopCellEditing(); //para el cellEdior
        }

        int seleccion[] = this.jTableConstants.getSelectedRows();

        for (int i = 0; i < seleccion.length; i++) {
            int j = seleccion[i];
            this.jTableConstants.setValueAt(false, j, columna);

        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // INCLUDE
        int columna = 5;

        if (jTableConstants.getCellEditor() != null) {
            jTableConstants.getCellEditor().stopCellEditing(); //para el cellEdior
        }

        int seleccion[] = this.jTableConstants.getSelectedRows();

        for (int i = 0; i < seleccion.length; i++) {
            int j = seleccion[i];
            this.jTableConstants.setValueAt(true, j, columna);

        }
}//GEN-LAST:event_jMenuItem4ActionPerformed

    private void exportToR() {
        FieldbookRExport.exportToR(jTableObservations, trialFile, csv, triallOption, trialStart, trialEnd, trialSelected, this.getStringTraitToEvaluate());
    }

    private void jMenuItemUnSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUnSelectActionPerformed
}//GEN-LAST:event_jMenuItemUnSelectActionPerformed

    private void jMenuItemUnselectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUnselectAllActionPerformed
}//GEN-LAST:event_jMenuItemUnselectAllActionPerformed

    private void jMenuItemSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSelectActionPerformed
    }//GEN-LAST:event_jMenuItemSelectActionPerformed

    private void jMenuItemSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSelectAllActionPerformed
}//GEN-LAST:event_jMenuItemSelectAllActionPerformed

    public void exportData() {
        if (!iniciaExportWizard2()) {
            return;
        }
    }

    private void RefreshDesign() {
        int opcion = JOptionPane.showConfirmDialog(null, NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.changeDesign"), NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.caution"), JOptionPane.YES_NO_OPTION);

        if (opcion == 0) {
            final ProgressHandle handle = ProgressHandleFactory.createHandle(NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.generatingDesign"));
            handle.start();
            (new SwingWorker<String, Object>() {

                @Override
                protected String doInBackground() throws Exception {
                    fillObservationsData();
                    return "";
                }

                @Override
                protected void done() {
                    super.done();
                    try {
                        String valor = get();
                    } catch (InterruptedException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (ExecutionException ex) {
                        Exceptions.printStackTrace(ex);
                    } finally {
                        handle.finish();
                    }
                }
            }).execute();
        }

    }

    public void selectNormalDesign() {
        this.jRadioButtonNormal.setSelected(true);
    }

    public void selectRandomDesign() {
        this.jRadioButtonRandom.setSelected(true);
    }

    private void jTableNurseryConditionsPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableNurseryConditionsPropertyChange

        if (this.jTableNurseryConditions.getCellEditor() != null) {
            jTableNurseryConditions.getCellEditor().stopCellEditing();
        }
        this.fila = this.jTableNurseryConditions.getEditingRow();
        if (fila == -1) {
            return;
        }
        try {
            if (evt.getOldValue() == null) {
                System.out.println("NO CAMBIO VALOR \n");
                return;
            }
            if (evt.getNewValue() == null) {
                Object valor = this.jTableNurseryConditions.getValueAt(this.fila, 5).toString();
                if (valor.equals("")) {
                    return;
                }
                System.out.println("FILA: " + fila);
                System.out.println("Valor: " + valor + "\n");
                Object condition = this.jTableNurseryConditions.getValueAt(this.fila, 1).toString().toUpperCase();
                Object property = this.jTableNurseryConditions.getValueAt(this.fila, TRIAL_PROPERTY_COL).toString().toUpperCase();
                Object scale = this.jTableNurseryConditions.getValueAt(this.fila, TRIAL_SCALE_COL).toString().toUpperCase();
                int instancia = Integer.parseInt(this.jTableNurseryConditions.getValueAt(this.fila, TRIAL_INSTANCE_COL).toString());
                if (property.equals(LookupUtil.PERSON) && scale.equals(LookupUtil.DBID)) {
                    LookupUtil.lookupPersonName(jTableNurseryConditions, instancia, valor, TRIAL_PROPERTY_COL, TRIAL_SCALE_COL, TRIAL_VALUE_COL);
                }
                if (property.equals(LookupUtil.PERSON) && scale.equals(LookupUtil.DBCV)) {
                    LookupUtil.lookupPersonId(jTableNurseryConditions, instancia, valor, TRIAL_PROPERTY_COL, TRIAL_SCALE_COL, TRIAL_VALUE_COL);
                }
                if (property.equals(LookupUtil.LOCATION) && scale.equals(LookupUtil.DBID)) {
                    LookupUtil.lookupLocationName(jTableNurseryConditions, instancia, valor, TRIAL_PROPERTY_COL, TRIAL_SCALE_COL, TRIAL_VALUE_COL);
                }
                if (property.equals(LookupUtil.LOCATION) && scale.equals(LookupUtil.DBCV)) {
                    LookupUtil.lookupLocationId(jTableNurseryConditions, instancia, valor, TRIAL_PROPERTY_COL, TRIAL_SCALE_COL, TRIAL_VALUE_COL);
                }

                if (property.equals(LookupUtil.METHOD) && scale.equals(LookupUtil.DBID)) {
                    LookupUtil.lookupMethodName(jTableNurseryConditions, instancia, valor, TRIAL_PROPERTY_COL, TRIAL_SCALE_COL, TRIAL_VALUE_COL);
                }

                if (property.equals(LookupUtil.METHOD) && scale.equals(LookupUtil.DBCV)) {
                    LookupUtil.lookupMethodId(jTableNurseryConditions, instancia, valor, TRIAL_PROPERTY_COL, TRIAL_SCALE_COL, TRIAL_VALUE_COL);
                }


                if (condition.equals("PDATE-1 LATE")) {
                    DateUtil.compareDates(instancia, jTableNurseryConditions, fila);
                    return;
                }
                if (condition.equals("PDATE-2 LATE")) {
                    DateUtil.compareDates(instancia, jTableNurseryConditions, fila);
                    return;
                }
            }
        } catch (NullPointerException error) {
        }
    }
//GEN-LAST:event_jTableNurseryConditionsPropertyChange
        private void jTextFieldPMKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPMKeyActionPerformed
        // TODO add your handling code here:
    }

    private String getMethodName() {
        String metodo = "";

        try {
            for (int i = 0; i < jTableStudyConditions.getRowCount(); i++) {
                if (jTableStudyConditions.getValueAt(i, 2).equals("METHOD")
                        && (jTableStudyConditions.getValueAt(i, 3).equals("DBCV"))) {
                    metodo = jTableStudyConditions.getValueAt(i, 4).toString();
                }

            }
        } catch (java.lang.NullPointerException ex) {
            metodo = "";
        }

        return metodo;
    }

//GEN-LAST:event_jTextFieldPMKeyActionPerformed
        private void jTableStudyConditionsPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableStudyConditionsPropertyChange

            if (this.jTableStudyConditions.getCellEditor() != null) {
                jTableStudyConditions.getCellEditor().stopCellEditing();
            }
            this.fila = this.jTableStudyConditions.getEditingRow();
            if (fila == -1) {
                return;
            }
            try {
                if (evt.getOldValue() == null) {
                    // System.out.println("NO CAMBIO VALOR \n");
                    return;
                }
                if (evt.getNewValue() == null) {
                    Object valor = this.jTableStudyConditions.getValueAt(this.fila, STUDY_VALUE_COL).toString();
                    if (valor.equals("")) {
                        return;
                    }

                    Object property = this.jTableStudyConditions.getValueAt(this.fila, STUDY_PROPERTY_COL).toString().toUpperCase();
                    Object scale = this.jTableStudyConditions.getValueAt(this.fila, STUDY_SCALE_COL).toString().toUpperCase();
                    if (property.equals(LookupUtil.PERSON) && scale.equals(LookupUtil.DBID)) {
                        LookupUtil.lookupPersonName(jTableStudyConditions, valor, STUDY_PROPERTY_COL, STUDY_SCALE_COL, STUDY_VALUE_COL);
                    }
                    if (property.equals(LookupUtil.PERSON) && scale.equals(LookupUtil.DBCV)) {
                        LookupUtil.lookupPersonId(jTableStudyConditions, valor, STUDY_PROPERTY_COL, STUDY_SCALE_COL, STUDY_VALUE_COL);
                    }
                    if (property.equals(LookupUtil.LOCATION) && scale.equals(LookupUtil.DBID)) {
                        LookupUtil.lookupLocationName(jTableStudyConditions, valor, STUDY_PROPERTY_COL, STUDY_SCALE_COL, STUDY_VALUE_COL);
                    }
                    if (property.equals(LookupUtil.LOCATION) && scale.equals(LookupUtil.DBCV)) {
                        LookupUtil.lookupLocationId(jTableStudyConditions, valor, STUDY_PROPERTY_COL, STUDY_SCALE_COL, STUDY_VALUE_COL);
                    }

                    if (property.equals(LookupUtil.METHOD) && scale.equals(LookupUtil.DBID)) {
                        LookupUtil.lookupMethodName(jTableStudyConditions, valor, STUDY_PROPERTY_COL, STUDY_SCALE_COL, STUDY_VALUE_COL);

                    }
                    if (property.equals(LookupUtil.METHOD) && scale.equals(LookupUtil.DBCV)) {
                        LookupUtil.lookupMethodId(jTableStudyConditions, valor, STUDY_PROPERTY_COL, STUDY_SCALE_COL, STUDY_VALUE_COL);
                    }


                }
            } catch (NullPointerException error) {
            }
        }//GEN-LAST:event_jTableStudyConditionsPropertyChange

    private void jRadioButtonNormalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonNormalMouseClicked
        int opcion = JOptionPane.showConfirmDialog(null, NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.changeDesign"), NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.caution"), JOptionPane.YES_NO_OPTION);
        if (opcion == 0) {
            fillObservationsData();
        } else {
            jRadioButtonRandom.setSelected(true);
        }
    }//GEN-LAST:event_jRadioButtonNormalMouseClicked

    private void jRadioButtonRandomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonRandomMouseClicked
        int opcion = JOptionPane.showConfirmDialog(null, NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.changeDesign"), NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.caution"), JOptionPane.YES_NO_OPTION);
        if (opcion == 0) {
            fillObservationsData();
        } else {
            jRadioButtonNormal.setSelected(true);
        }
    }//GEN-LAST:event_jRadioButtonRandomMouseClicked

    private void jDateChooserStartInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jDateChooserStartInputMethodTextChanged
    }//GEN-LAST:event_jDateChooserStartInputMethodTextChanged

    private void jDateChooserEndInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jDateChooserEndInputMethodTextChanged
    }//GEN-LAST:event_jDateChooserEndInputMethodTextChanged

    private void jDateChooserStartPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserStartPropertyChange
        if (evt.getPropertyName().equals("date")) {

            Date startValue = jDateChooserStart.getDate();
            Date endValue = jDateChooserEnd.getDate();

            try {

                if (startValue.compareTo(endValue) > 0) {

                    NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.late"), NotifyDescriptor.INFORMATION_MESSAGE);
                    DialogDisplayer.getDefault().notify(d);
                    jDateChooserStart.setDate(null);
                }
            } catch (NullPointerException e) {
                System.out.println("ERROR FECHAS " + e);
            }
        }

    }//GEN-LAST:event_jDateChooserStartPropertyChange

    private void jDateChooserEndPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserEndPropertyChange

        if (evt.getPropertyName().equals("date")) {


            Date startValue = jDateChooserStart.getDate();
            Date endValue = jDateChooserEnd.getDate();

            try {

                if (startValue.compareTo(endValue) > 0) {

                    NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.late"), NotifyDescriptor.INFORMATION_MESSAGE);
                    DialogDisplayer.getDefault().notify(d);
                    jDateChooserEnd.setDate(null);
                }
            } catch (NullPointerException e) {
                System.out.println("ERROR FECHAS " + e);
            }
        }
    }//GEN-LAST:event_jDateChooserEndPropertyChange

    private void jButtonSaveNurseryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveNurseryActionPerformed
        saveNursery();
    }//GEN-LAST:event_jButtonSaveNurseryActionPerformed

    private void doSaveWorkbook() {
        WorkbookSavingHelper.saveFieldbook(this);
        disableTraitsSelection();
        jTextFieldNurseryName.setEnabled(false);
    }

    private void saveNursery() {
        storeCellsInEditMode();
        if (jTextFieldNurseryName.getText().trim().isEmpty()) {
            DialogUtil.displayError(NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.fillName"));
            jTextFieldNurseryName.requestFocusInWindow();
            return;
        }

        if (!studyAlreadyExists && nurseryNameAlreadyExists(jTextFieldNurseryName.getText().trim())) {
            String message = NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.nurseryNameAlreadyExists");
            DialogUtil.displayError(message);
            return;
        }

        String studyName = jTextFieldStudy.getText();
        FieldbookCSVUtil fieldbookCSVUtil = new FieldbookCSVUtil(jTableObservations, studyName);
        fieldbookCSVUtil.saveToCsv();


        final ProgressHandle handle = ProgressHandleFactory.createHandle(NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.saving"));
        handle.start();
        (new SwingWorker<String, Object>() {

            @Override
            protected String doInBackground() throws Exception {
                doSaveWorkbook();
                return "";
            }

            @Override
            protected void done() {
                super.done();
                try {
                    String valor = get();
                    DialogUtil.displayInfo(NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.successfully"));
                    RefreshBrowserHelper.refreshStudyBrowser();
                    checkAdvanceStatus();
                } catch (InterruptedException ex) {
                    Exceptions.printStackTrace(ex);
                } catch (ExecutionException ex) {
                    Exceptions.printStackTrace(ex);
                } finally {
                    // close the progress handler
                    handle.finish();
                }
            }
        }).execute();
        return;
    }

    private void jButtonCSVTraitsExport1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCSVTraitsExport1ActionPerformed
        storeCellsInEditMode();
        NbPreferences.forModule(exportWizardPanelGYTrait.class).put("traitIndex", "-1");

        if (!iniciaExportWizard2()) {
            this.setStringTraitToEvaluate("GY");
            NbPreferences.forModule(exportWizardPanelGYTrait.class).put("traitIndex", "-1");
        }
    }//GEN-LAST:event_jButtonCSVTraitsExport1ActionPerformed

    private void jButtonCSVTraitsImport1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCSVTraitsImport1ActionPerformed
        if (!launchImportWizard()) {
            return;
        }
    }//GEN-LAST:event_jButtonCSVTraitsImport1ActionPerformed

    public void assignTraits(List<Variate> unselectedVariates, List<Variate> selectedVariates) {
        doubleListPanel.setSourceList(unselectedVariates);
        doubleListPanel.setTargetList(selectedVariates);
        doubleListPanel.fillListItems();

    }

private void jButtonSelectTraitsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelectTraitsActionPerformed
    TopComponent traitsExplorer = WindowManager.getDefault().findTopComponent("TraitsExplorerTopComponent");
    if (traitsExplorer == null) {
        traitsExplorer = new TraitsExplorerTopComponent();
    }
    traitsExplorer.open();
    TraitsExplorerTopComponent traitsExplorerTopComponent = (TraitsExplorerTopComponent) traitsExplorer;
    traitsExplorerTopComponent.updateTraitsTable();
    traitsExplorer.requestActive();
}//GEN-LAST:event_jButtonSelectTraitsActionPerformed

    private void jButtonSyncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSyncActionPerformed
        fillObservationsData();
        DialogUtil.displayInfo(NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.datasyncronized"));

        //  this.jTabbedPane1.setSelectedIndex(7);
    }//GEN-LAST:event_jButtonSyncActionPerformed

    private void jTextFieldNurseryNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNurseryNameKeyReleased
        studyInfo.setStudy(jTextFieldNurseryName.getText());
    }//GEN-LAST:event_jTextFieldNurseryNameKeyReleased

    private void jButtonAdvanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdvanceActionPerformed

        int maizeMtd = 0;
        int whithParentheses = 0;

        if (this.jTableObservations.getCellEditor() != null) {
            jTableObservations.getCellEditor().stopCellEditing();
        }

        int entrada = 0;

        if (existeTopComponent(this.getName() + " F1")) {
            JOptionPane.showMessageDialog(null, NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.already"), NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.caution"), JOptionPane.OK_OPTION);

        } else {
            ObservationsTableModel modelo = (ObservationsTableModel) this.jTableObservations.getModel();
            MethodsClass metodos = new MethodsClass();
            MaizeMethods maizeMethod = null;
            WizardDescriptor wiz = new WizardDescriptor(new AdvanceWizardIterator());
            wiz.setTitleFormat(new MessageFormat("{0} ({1})"));
            wiz.setTitle(NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.wizard"));
            AdvanceWizardIterator.metodo = getMethodName();
            AdvanceWizardIterator.breedingMethod = getSelectedBreedingMethod();

            if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
                int convention = Integer.parseInt(NbPreferences.forModule(AdvanceWizardPanel1.class).get("methodIndex", "0")); //0=wheat  1=maize  1=other crops
                int samples = Integer.parseInt(NbPreferences.forModule(AdvanceWizardPanel1.class).get("samples", "1"));
                int samplesMethod = Integer.parseInt(NbPreferences.forModule(AdvanceWizardPanel1.class).get("samplesMethod", "0"));
                int methodId = NbPreferences.forModule(AdvanceWizardPanel1.class).getInt("MethodId", 0);
                int locationId = NbPreferences.forModule(AdvanceWizardPanel1.class).getInt("LocationId", 0);
                int harvestDate = NbPreferences.forModule(AdvanceWizardPanel1.class).getInt("HarvestDate", 0);
                String locationAbbr = NbPreferences.forModule(AdvanceWizardPanel1.class).get("LAbbr", "");
                int numberOfParents = NbPreferences.forModule(AdvanceWizardPanel1.class).getInt("NumberOfParents", 0);

                metodos.setConvention(convention);
                metodos.setSuffix(locationAbbr);

                List<Integer> sourceGidList = new ArrayList<Integer>();
                List<GermplasmSearch> listToSearchBCID = new ArrayList<GermplasmSearch>();





                AdvanceLineTopComponent advanceEditor = new AdvanceLineTopComponent();
                advanceEditor.setModelo(modelo);
                advanceEditor.setConvection(convention);
                advanceEditor.setMethodId(methodId);
                advanceEditor.setLocationId(locationId);
                advanceEditor.setHarvestDate(harvestDate);
                advanceEditor.setNurseryName(jTextFieldNurseryName.getText());
                advanceEditor.setNumberOfParents(numberOfParents);

                advanceEditor.clearEntries();




                GermplasmEntriesTableModel tableModel = (GermplasmEntriesTableModel) this.jTableEntries.getModel();
                GermplasmEntriesTableModel tableModelAdvance = new GermplasmEntriesTableModel();

                List<List<Object>> germplasmData = tableModel.getGermplasmData();
                List<List<Object>> germplasmDataAdvance = tableModelAdvance.getGermplasmData();

                int colEntry = tableModel.getHeaderIndex(GermplasmEntriesTableModel.ENTRY);
                //System.out.println("COL ENTRY=" + colEntry + "\n");
                if (colEntry < 0) {
                    return;
                }

                int colDesig = tableModel.getHeaderIndex(GermplasmEntriesTableModel.DESIG);
                //System.out.println("COL DESIG=" + colDesig + "\n");
                if (colDesig < 0) {

                    colDesig = tableModel.findColumn("CROSS NAME");
                    //System.out.println("COL CROSS=" + colDesig + "\n");
                    if (colDesig < 0) {
                        return;
                    }
                }

                int colGID = tableModel.getHeaderIndex(GermplasmEntriesTableModel.GID);
                //System.out.println("COL GID=" + colGID + "\n");


                if (colEntry < 0) {
                    return;
                }

                int colSelection = 0;

                if (samplesMethod == 1) {//Diferent number of plants selected
                    colSelection = modelo.getHeaderIndex(GermplasmEntriesTableModel.PLANTS_SELECTED);
                    if (colSelection < 0) {
                        String msgSaving = "PLANTS SELECTED COLUMN IS MISSING";
                        NotifyDescriptor d = new NotifyDescriptor.Message(msgSaving, NotifyDescriptor.ERROR_MESSAGE);
                        DialogDisplayer.getDefault().notify(d);
                        return;
                    }


                    if (convention > 0) {//PARA TRIGO PUEDE EXISTIR 0 Y VALORES NEGATIVOS
                        if (!hayValoresParaProcesar(modelo, colSelection)) {
                            String msgSaving = "THERE ARE NOT VALID VALUES IN PLANTS SELECTED COLUMN";
                            NotifyDescriptor d = new NotifyDescriptor.Message(msgSaving, NotifyDescriptor.ERROR_MESSAGE);
                            DialogDisplayer.getDefault().notify(d);
                            return;
                        }
                    }
                }

                int contador = 0;
                boolean thereIsValue = false;

                org.cimmyt.cril.ibwb.domain.Study estudio = AppServicesProxy.getDefault().appServices().getStudyByName(jTextFieldStudy.getText());
                int currentStudy = estudio.getStudyid();


                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    GermplasmSearch gsF = new GermplasmSearch();
                    gsF.setStudyId(currentStudy);
                    gsF.setTrial(1); //BECAUSE THIS IS A NURSERY
                    gsF.setPlot(ConvertUtils.getValueAsInteger(germplasmData.get(i).get(colEntry)));
                    listToSearchBCID.add(gsF);
                }


                if (convention == 1) {//MAIZE

                    maizeMethod = new MaizeMethods();

                    maizeMtd = NbPreferences.forModule(PolinizationWizardPanel1.class).getInt("maizeMethod", 0);
                    whithParentheses = NbPreferences.forModule(PolinizationWizardPanel1.class).getInt("whitParentheses", 0);

                    if (whithParentheses == 0) {
                        maizeMethod.setWithParentheses(false);
                    } else {
                        maizeMethod.setWithParentheses(true);
                    }

                    maizeMethod.setNurseryName(estudio.getSname());
                    maizeMethod.setCycle(getCycle());
                }

                Object[] nuevo = null;
                ArrayList<String> data = null;
                ArrayList<MaizeEntry> dataMaize = null;
                Integer sourceGid = 0;


                for (int i = 0; i < tableModel.getRowCount(); i++) {

                    //   for (int i = 0; i < 1; i++) {

                    nuevo = germplasmData.get(i).toArray();
                    data = null;
                    dataMaize = null;
                    sourceGid = 0;

                    try {
                        sourceGid = ConvertUtils.getValueAsInteger(germplasmData.get(i).get(colGID));
                    } catch (Exception ex) {
                        sourceGid = 0;
                    }

                    if (samplesMethod == 0) { //SAME NUMBER OF PLANT SELECTED FOR ALL SEEDS

                        switch (convention) {
                            case 0: //WHEAT

                                Integer gidToSearch = ConvertUtils.getValueAsInteger(germplasmData.get(i).get(colGID));
                                Names cimmytName = AppServicesProxy.getDefault().appServices().getCimmytWheatName(gidToSearch);
                                if (cimmytName == null) {
                                    data = metodos.giveMeDataDerivative(nuevo[colDesig].toString(), samples);
                                } else {
                                    data = metodos.giveMeDataDerivative(cimmytName.getNval(), samples);
                                }

                                break;


                            case 1: //MAIZE                               

                                maizeMethod.setPolinizationNumber(i + 1);

                                switch (maizeMtd) {
                                    case 0:
                                        dataMaize = maizeMethod.procesaAutofecundadasIndividualmente(nuevo[colDesig].toString(), samples);
                                        break;

                                    case 1:
                                        dataMaize = maizeMethod.procesaAutofecundadasBulked(nuevo[colDesig].toString(), samples);
                                        break;

                                    case 2:
                                        dataMaize = maizeMethod.procesaSibIncrease(nuevo[colDesig].toString(), samples);
                                        break;

                                    case 3:
                                        dataMaize = maizeMethod.procesaColchicinize(nuevo[colDesig].toString(), samples);
                                        break;
                                }
                                break;

                            case 2: //OTHER CROPS

                                data = metodos.giveMeDataDerivative(nuevo[colDesig].toString(), samples);
                                break;

                        }






                    } else {  // VALUES FROM PLANTS SELECTED TRAIT









                        switch (convention) {
                            case 0: //WHEAT
                                thereIsValue = selectionValue(i, colSelection, modelo);
                                if (!thereIsValue) {
                                    continue;
                                }
                                samples = giveMeSelectionValue(i, colSelection, modelo);


                                Integer gidToSearch = ConvertUtils.getValueAsInteger(germplasmData.get(i).get(colGID).toString());
                                Names cimmytName = AppServicesProxy.getDefault().appServices().getCimmytWheatName(gidToSearch);
                                if (cimmytName != null) {
                                    data = metodos.giveMeDataDerivative(cimmytName.getNval(), samples);
                                } else {
                                    data = metodos.giveMeDataDerivative(nuevo[colDesig].toString(), samples);
                                }



                                break;

                            case 1: //MAIZE      

                                samples = giveMeSelectionValue(i, colSelection, modelo);
                                if (samples <= 0) {
                                    continue;
                                }


                                maizeMethod.setPolinizationNumber(i + 1);

                                switch (maizeMtd) {
                                    case 0:
                                        dataMaize = maizeMethod.procesaAutofecundadasIndividualmente(nuevo[colDesig].toString(), samples);
                                        break;

                                    case 1:
                                        dataMaize = maizeMethod.procesaAutofecundadasBulked(nuevo[colDesig].toString(), samples);
                                        break;

                                    case 2:
                                        dataMaize = maizeMethod.procesaSibIncrease(nuevo[colDesig].toString(), samples);
                                        break;

                                    case 3:
                                        dataMaize = maizeMethod.procesaColchicinize(nuevo[colDesig].toString(), samples);
                                        break;
                                }

                                break;

                            case 2: //OTHER CROPS  

                                samples = giveMeSelectionValue(i, colSelection, modelo);
                                if (samples <= 0) {
                                    continue;
                                }

                                data = metodos.giveMeDataDerivative(nuevo[colDesig].toString(), samples);

                                break;
                        }

                    }





                    //ADD DATA TO NEW GERMPLASM MODEL (ADVANCE)
                    switch (convention) {
                        case 0: //WHEAT
                            for (int j = 0; j < data.size(); j++) {
                                List<Object> newData = new ArrayList<Object>();
                                // add each GID source from GRID

                                sourceGidList.add(sourceGid);
                                Object[] temp = nuevo.clone();
                                entrada++;
                                temp[colEntry] = entrada;
                                temp[colDesig] = data.get(j);
                                temp[colGID] = "Not assigned yet";

                                for (int z = 0; z < temp.length; z++) {
                                    newData.add(z, temp[z]);
                                }
                                //  newData.add(germplasmSearchs.get(i).getNames().getNval());//FOR INVENTORY   -  DESIG
                                newData.add("");//FOR INVENTORY   -  DESIG
                                newData.add("");//FOR INVENTORY   -  LOCATION
                                newData.add("");  //FOR INVENTORY       -  COMMENTS  
                                newData.add("");  //FOR INVENTORY       -  AMOUNT  
                                newData.add("");  //FOR INVENTORY       -  SCALE  

                                germplasmDataAdvance.add(newData);
                            }
                            break;

                        case 1: //MAIZE   

                            for (int j = 0; j < dataMaize.size(); j++) {
                                List<Object> newData = new ArrayList<Object>();
                                // add each GID source from GRID

                                sourceGidList.add(sourceGid);
                                Object[] temp = nuevo.clone();
                                entrada++;
                                temp[colEntry] = entrada;
                                temp[colDesig] = dataMaize.get(j).getGenealogy();
                                temp[colGID] = "Not assigned yet";

                                for (int z = 0; z < temp.length; z++) {
                                    newData.add(z, temp[z]);
                                }
                                //  newData.add(germplasmSearchs.get(i).getNames().getNval());//FOR INVENTORY   -  DESIG
                                newData.add("OZIEL");//FOR INVENTORY   -  DESIG
                                newData.add("");//FOR INVENTORY   -  LOCATION
                                newData.add("");  //FOR INVENTORY       -  COMMENTS  
                                newData.add("");  //FOR INVENTORY       -  AMOUNT  
                                newData.add("");  //FOR INVENTORY       -  SCALE  

                                germplasmDataAdvance.add(newData);
                            }

                            break;

                        case 2: //OTHER CROPS  
                            for (int j = 0; j < data.size(); j++) {
                                List<Object> newData = new ArrayList<Object>();
                                // add each GID source from GRID

                                sourceGidList.add(sourceGid);
                                Object[] temp = nuevo.clone();
                                entrada++;
                                temp[colEntry] = entrada;
                                temp[colDesig] = data.get(j);
                                temp[colGID] = "Not assigned yet";

                                for (int z = 0; z < temp.length; z++) {
                                    newData.add(z, temp[z]);
                                }
                                //  newData.add(germplasmSearchs.get(i).getNames().getNval());//FOR INVENTORY   -  DESIG
                                newData.add("OZIEL");//FOR INVENTORY   -  DESIG
                                newData.add("");//FOR INVENTORY   -  LOCATION
                                newData.add("");  //FOR INVENTORY       -  COMMENTS  
                                newData.add("");  //FOR INVENTORY       -  AMOUNT  
                                newData.add("");  //FOR INVENTORY       -  SCALE  

                                germplasmDataAdvance.add(newData);
                            }

                            break;

                    }

                }

                List<Factor> factores = tableModel.getFactorHeaders();

                // assign source gid sources to advanceeditor
                advanceEditor.setSourceGidList(sourceGidList);
                advanceEditor.setListToSearchBCID(listToSearchBCID);

                advanceEditor.assignGermplasmEntries(factores, germplasmDataAdvance);
                advanceEditor.open();
                advanceEditor.setName(this.getName() + " F1");
                advanceEditor.requestActive();
            }
        }
    }//GEN-LAST:event_jButtonAdvanceActionPerformed

    private void checkAdvanceStatus() {
        if (this.jTextFieldNurseryName.isEnabled()) {
            this.jButtonAdvance.setEnabled(false);
            this.jButtonCSVTraitsExport1.setEnabled(false);
            this.jButtonCSVTraitsImport1.setEnabled(false);
        } else {
            this.jButtonAdvance.setEnabled(true);
            this.jButtonCSVTraitsExport1.setEnabled(true);
            this.jButtonCSVTraitsImport1.setEnabled(true);
        }
    }

    private List<Factor> addColumnsForInventory(List<Factor> factores) {
        List<Factor> losFactores = factores;

        Factor factor = new Factor();
        factor.setFactorName("LOCATION");
        factor.setProperty("GERMPLASM ENTRY");
        factor.setScale("TEXT");
        factor.setDataType("C");
        losFactores.add(factor);

        factor = new Factor();
        factor.setFactorName("COMMENT");
        factor.setProperty("GERMPLASM ENTRY");
        factor.setScale("TEXT");
        factor.setDataType("C");
        losFactores.add(factor);

        return losFactores;
    }

    private void jTableObservationsPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableObservationsPropertyChange
        this.jLabelObsEntries.setText(String.valueOf(this.jTableObservations.getRowCount()));
    }//GEN-LAST:event_jTableObservationsPropertyChange

    private void jTableEntriesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableEntriesPropertyChange
        this.jLabelTotalGermp.setText(String.valueOf(this.jTableEntries.getRowCount()));
    }//GEN-LAST:event_jTableEntriesPropertyChange

    private void jButtonCopyGIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCopyGIDActionPerformed
        System.out.println("INICIANDO LECTURA DE GID");
        System.out.println("------------------------------");
        GermplasmEntriesTableModel entriesTableModel = (GermplasmEntriesTableModel) this.jTableEntries.getModel();
        int colGID = entriesTableModel.findColumn("GID");
        System.out.println("COL GID FOUND IN: " + colGID);

        if (colGID > 0) {
            String str = "GID\n";

            for (int i = 0; i < entriesTableModel.getRowCount(); i++) {


                boolean hayValue = false;
                int num;

                try {
                    num = (int) Double.parseDouble(entriesTableModel.getValueAt(i, colGID).toString());
                    System.out.println("NUM LOADED: " + num);
                    hayValue = true;
                } catch (NumberFormatException ex) {
                    num = 0;
                    hayValue = false;
                    System.out.println("ERROR DE FORMATO CON RENGLON: " + i);

                }
                if (hayValue) {
                    str = str + String.valueOf(num) + "\n";
                }
            }
            StringSelection ss = new StringSelection(str);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

            DialogUtil.display(NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.copied"));

        } else {

            DialogUtil.displayError(NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.noGIDColumn"));
        }
    }//GEN-LAST:event_jButtonCopyGIDActionPerformed

    private void jButtonImportCrossInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImportCrossInfoActionPerformed

        ImportData importData = new ImportData(jTableEntries);

        FileFilter[] filtros = new FileFilter[10];
        filtros = selectorArchivo.getChoosableFileFilters();
        for (int i = 0; i < filtros.length; i++) {
            FileFilter filtro = filtros[i];
            selectorArchivo.removeChoosableFileFilter(filtro);
        }
        File archivoNulo = new File("");
        selectorArchivo.setSelectedFile(archivoNulo);
        selectorArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        selectorArchivo.addChoosableFileFilter(new ExcelFiltro());


        int resultado = selectorArchivo.showOpenDialog(null);
        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }
        importData.importFromCrossInfoToGermplasm(selectorArchivo.getSelectedFile());

        fillObservationsData();
        DialogUtil.displayInfo(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.datasyncronized");

    }//GEN-LAST:event_jButtonImportCrossInfoActionPerformed

    private void jButtonSaveNurseryStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jButtonSaveNurseryStateChanged

        checkAdvanceStatus();

    }//GEN-LAST:event_jButtonSaveNurseryStateChanged

    public void enabledAdvance(boolean estado) {
        this.jButtonAdvance.setEnabled(estado);
    }

    @Override
    public void componentOpened() {
        if (this.jTextFieldNurseryName.getText().trim().isEmpty()) {
            this.jTextFieldNurseryName.requestFocusInWindow();
        }
        checkAdvanceStatus();
    }

    @Override
    public void componentClosed() {
    }

    @Override
    protected void componentActivated() {
        if (this.jTextFieldNurseryName.getText().trim().isEmpty()) {
            this.jTextFieldNurseryName.requestFocusInWindow();
        }
        checkAdvanceStatus();

    }

    @Override
    public void componentDeactivated() {
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    private void assignCellEditorTrialCndition() {
        NurseryConditionsRowEditor trialConditionsRowEditor = new NurseryConditionsRowEditor(jTableNurseryConditions);
        TableColumn valueColumn = this.jTableNurseryConditions.getColumnModel().getColumn(5);
        valueColumn.setCellEditor(trialConditionsRowEditor);
    }

    private void assignCellEditorStudyConditions() {
        ConditionsRowEditor trialConditionsRowEditor = new ConditionsRowEditor(jTableStudyConditions);
        TableColumn valueColumn = this.jTableStudyConditions.getColumnModel().getColumn(4);
        valueColumn.setCellEditor(trialConditionsRowEditor);
    }

    private void MSG_NotImplementedYet() {
        String msg = "NOT IMPLEMENTED YET";
        int msgType = NotifyDescriptor.INFORMATION_MESSAGE;
        NotifyDescriptor d = new NotifyDescriptor.Message(msg, msgType);
        DialogDisplayer.getDefault().notify(d);
    }

    private void includeColumns() {
        int opcion = JOptionPane.showConfirmDialog(null, "Are you sure to include this Traits? The -Measurements Table- will be modified ", "Caution!", JOptionPane.YES_NO_OPTION);
        if (opcion == 0) {
            int total = this.jTableObservations.getColumnCount();
            TableColumn myColumn = new TableColumn();
            myColumn.setHeaderValue("name");
            this.jTableObservations.addColumn(myColumn);
        }
    }

    private void cleanValuesForColumn(int col) {
        System.out.println("BORRAR COL " + col);
        for (int k = 0; k < jTableObservations.getRowCount(); k++) {
            this.jTableObservations.setValueAt("", k, col);
        }
    }

    private void excludeColumns() {
        int opcion = JOptionPane.showConfirmDialog(null, "Are you sure to exclude this Traits? The -Measurements Table- will be modified ", "Caution!", JOptionPane.YES_NO_OPTION);
        if (opcion == 0) {
        }
    }

    public void fillObservationsData() {
        List<Variate> selectedVariates = doubleListPanel.getTargetList();
        ObservationsTableModel tableModel = new ObservationsTableModel(myWorkbook, selectedVariates);
        jTableObservations.setModel(tableModel);




        sorterMeasurements = new TableRowSorter<TableModel>(tableModel);
        this.jTableObservations.setRowSorter(sorterMeasurements);

        int nursery = 1;
        String rep = "";
        String blockSize = "";
        String blocksPerRep = "";
        Integer entries = 0;
        entries = this.jTableEntries.getRowCount();




        if (this.jRadioButtonRandom.isSelected()) {
            generateUnreplicatedDesignWithRandomization(tableModel);
        } else {
            generateUnreplicatedDesignWithoutRandomization(tableModel);
        }


        int entriesTot = this.jTableEntries.getRowCount();
        System.out.println("ENTRADAS TOTALES: " + entriesTot);

        this.jTextFieldEntries.setText(String.valueOf(entriesTot));


        ajustaColumnsTable(this.jTableObservations, 2);

        for (int i = 0; i < jTableObservations.getColumnCount(); i++) {
            sorterMeasurements.setSortable(i, false);
        }

        this.jTabbedPane1.setEnabledAt(6, true);
        this.jTabbedPane1.setSelectedIndex(6);
        changeCursorWaitStatus(false);

        ObservationTableTooltips.assignTooltips(jTableObservations);

    }

    private void generateUnreplicatedDesignWithoutRandomization(ObservationsTableModel model) {
        TableColumnModel tcm = this.jTableEntries.getColumnModel();
        GermplasmEntriesTableModel entriesTableModel = (GermplasmEntriesTableModel) this.jTableEntries.getModel();


        int total = Integer.parseInt(this.jTextFieldEntries.getText());
        int colEntry = entriesTableModel.getHeaderIndex(ObservationsTableModel.ENTRY);

        for (int i = 0; i < total; i++) {

            Object[] rowToAdd = new Object[model.getColumnCount()];
            rowToAdd[model.getHeaderIndex(ObservationsTableModel.PLOT)] = i + 1;

//            if (posiciones.size() > 0) {
//                System.out.println("ENTRAMOS A PONER ISCHECK");
//                if (posiciones.contains(i + 1)) {
//                    System.out.println("CAMBIAMOS VALOR A 1");
//
//                    rowToAdd[model.getHeaderIndex(ObservationsTableModel.CHECK)] = 1;
//                }
//            }

            int entriesColIndex = 0;

            for (Factor factor : entriesTableModel.getFactorHeaders()) {
                String columnHeader = Workbook.getStringWithOutBlanks(factor.getProperty() + factor.getScale());

                System.out.println("COLUMNA:" + columnHeader);
                rowToAdd[model.getHeaderIndex(columnHeader)] = entriesTableModel.getValueAt(i, entriesColIndex);
                entriesColIndex++;
            }


            if (posiciones.size() > 0) {
                if (posiciones.contains(Integer.parseInt(rowToAdd[colEntry].toString()))) {
                    rowToAdd[model.getHeaderIndex(ObservationsTableModel.ISCHECKNUMBER)] = "is check";
                }
            }


            model.addRow(rowToAdd);
        }

        // fillChecksValue();
        markChecksInGermplasmList();
    }

    @Override
    public boolean canClose() {
        boolean result = true;
        String closeTitle = NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.closeFieldbook.title");
        String closeQuestion = NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.closeFieldbook.question");
        String provideTrialName = NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.closeFieldbook.provideTrialName");
        boolean saveFieldbook = DialogUtil.displayConfirmation(closeQuestion, closeTitle, NotifyDescriptor.YES_NO_CANCEL_OPTION);
        if (saveFieldbook) {
            if (jTextFieldNurseryName.getText().trim().isEmpty()) {
                DialogUtil.displayError(provideTrialName);
                result = false;
            } else {
                jButtonSaveNurseryActionPerformed(null);
            }

        }
        return result;
    }

    public int findColumn(String name, DefaultTableModel model) {
        int colEntry = 0;

        colEntry = model.findColumn(name);

        return colEntry;

    }

    private void generateUnreplicatedDesignWithRandomization(ObservationsTableModel model) {
        GermplasmEntriesTableModel entriesTableModel = (GermplasmEntriesTableModel) this.jTableEntries.getModel();
        int total = Integer.parseInt(this.jTextFieldEntries.getText());
        int vector[] = randomize(total);
        int colEntry = entriesTableModel.getHeaderIndex(ObservationsTableModel.ENTRY);
        for (int i = 0; i < total; i++) {
            Object[] rowToAdd = new Object[model.getColumnCount()];
            rowToAdd[model.getHeaderIndex(ObservationsTableModel.PLOT)] = i + 1;

            int entriesColIndex = 0;

            for (Factor factor : entriesTableModel.getFactorHeaders()) {
                String columnHeader = Workbook.getStringWithOutBlanks(factor.getProperty() + factor.getScale());
                rowToAdd[model.getHeaderIndex(columnHeader)] = entriesTableModel.getValueAt(vector[i], entriesColIndex);
                entriesColIndex++;
            }

            if (posiciones.size() > 0) {
                if (posiciones.contains(Integer.parseInt(rowToAdd[colEntry].toString()))) {
                    rowToAdd[model.getHeaderIndex(ObservationsTableModel.ISCHECKNUMBER)] = "is check";
                }
            }
            model.addRow(rowToAdd);

        }
        markChecksInGermplasmList();
    }

    private String giveMaxString(int maximo) {
        String cadena;
        DecimalFormat format = new DecimalFormat("000000");
        cadena = format.format(maximo);
        return cadena;
    }

    private int[] randomize(int tam) {
        int vector[] = new int[tam];
        int i = 0, j;
        vector[i] = (int) (Math.random() * tam);
        for (i = 1; i < tam; i++) {
            vector[i] = (int) (Math.random() * tam);
            for (j = 0; j < i; j++) {
                if (vector[i] == vector[j]) {
                    i--;
                }
            }
        }
        return vector;

    }

    private void deshabilitaSorters() {
        for (int i = 0; i < 5; i++) {
            sorterNurseryInformation.setSortable(i, false);
            sorterConstants.setSortable(i, false);

        }
    }

    public void ajustaColumnsTable(JTable table, int margin) {
        for (int c = 0; c < table.getColumnCount(); c++) {
            ajustaColumn(table, c, 2);
        }
    }

    public void ajustaColumn(JTable table, int vColIndex, int margin) {
        TableModel modelPack = table.getModel();
        DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
        TableColumn col = colModel.getColumn(vColIndex);
        int width = 0;
        TableCellRenderer renderer = col.getHeaderRenderer();
        if (renderer == null) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }
        Component comp = renderer.getTableCellRendererComponent(
                table, col.getHeaderValue(), false, false, 0, 0);
        width = comp.getPreferredSize().width;
        for (int r = 0; r < table.getRowCount(); r++) {
            renderer = table.getCellRenderer(r, vColIndex);
            comp = renderer.getTableCellRendererComponent(
                    table, table.getValueAt(r, vColIndex), false, false, r,
                    vColIndex);
            width = Math.max(width, comp.getPreferredSize().width);
        }
        width += 2 * margin;
        col.setPreferredWidth(width);
    }

    public void configMyList() {

        cleanFields();
        traits = (java.util.ArrayList<Variate>) myWorkbook.getVariates();
    }

    public void cleanFields() {
        jTextFieldDescription.setText("");
        jTextFieldDescriptionSelected.setText("");
    }

    public void cleanList() {
        listModelUnSelected.clear();
        listModelSelected.clear();
    }

    private static void changeCursorWaitStatus(final boolean isWaiting) {
        Mutex.EVENT.writeAccess(new Runnable() {

            @Override
            public void run() {
                try {
                    JFrame mainFrame =
                            (JFrame) WindowManager.getDefault().getMainWindow();
                    Component glassPane = mainFrame.getGlassPane();
                    if (isWaiting) {
                        glassPane.setVisible(true);

                        glassPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    } else {
                        glassPane.setVisible(false);

                        glassPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    }
                } catch (Exception e) {
                }
            }
        });
    }

    public boolean launchImportWizard() {
        ImportData importData = new ImportData(jTableObservations, csv);
        WizardDescriptor.Iterator iterator = new importingWizardIterator();
        WizardDescriptor wizardDescriptor = new WizardDescriptor(iterator);
        importingVisualPanel2.setWizardDescriptor(wizardDescriptor);

        wizardDescriptor.setTitleFormat(new MessageFormat("{0} ({1})"));
        wizardDescriptor.setTitle("Import Data");
        Dialog dialog = DialogDisplayer.getDefault().createDialog(wizardDescriptor);
        dialog.setVisible(true);
        dialog.toFront();
        boolean cancelled = wizardDescriptor.getValue() != WizardDescriptor.FINISH_OPTION;
        if (!cancelled) {

            switch (opcionImport) {
                case 0:
                    //importFromExcel();

                    importData.importFromExcel(nurseryFile);

                    break;
                case 1:
                    // importFromFieldroid();
                    importData.importFromFieldroid(nurseryFile);
                    break;

            }

            return true;

        } else {
            return false;
        }


    }

    @SuppressWarnings("unchecked")
    private boolean iniciaExportWizard2() {

        WizardDescriptor.Iterator iterator = new exportWizardIterator();
        WizardDescriptor wizardDescriptor = new WizardDescriptor(iterator);

        exportVisualPanel3.setWizardDescriptor(wizardDescriptor);
        saveVisualPanel2.setWizardDescriptor(wizardDescriptor);

        wizardDescriptor.setTitleFormat(new MessageFormat("{0} ({1})"));
        wizardDescriptor.setTitle("Save Data");
        Dialog dialog = DialogDisplayer.getDefault().createDialog(wizardDescriptor);
        dialog.setVisible(true);
        dialog.toFront();
        boolean cancelled = wizardDescriptor.getValue() != WizardDescriptor.FINISH_OPTION;
        if (!cancelled) {

            switch (opcionExport) {
                case 0:
                    exportToFieldroid();

                    break;
                case 1://to R
                    exportToR();
                    break;
                case 2:// to excel file
                    exportToExcel();
                    break;
            }
            return true;
        } else {
            return false;
        }


    }

    public int getMaxTrial() {
        return 1;
    }

    public void loadDataFromCsv() {
        String studyName = jTextFieldStudy.getText();
        FieldbookCSVUtil fieldbookCSVUtil = new FieldbookCSVUtil(jTableObservations, studyName);
        fieldbookCSVUtil.populateFiedlbook(jTableObservations, studyName);
    }

    public String getCycle() {
        String ciclo = "";
        try {
            StudyConditionsTableModel studyConditionsTableModel = (StudyConditionsTableModel) this.jTableStudyConditions.getModel();
            List<Condition> condiciones = studyConditionsTableModel.getStudyConditions();

            for (Condition condition : condiciones) {
                if (condition.getConditionName().toUpperCase().equals("CYCLE")) {
                    ciclo = condition.getValue().toString();
                    return ciclo;
                }

                ciclo = "";
            }

        } catch (Exception ex) {
            System.out.println("ERROR AL OBTENER CICLO " + ex);

        }

        return ciclo;
    }

    public void assignStudyConditions(List<Condition> studyConditions) {
        StudyConditionsTableModel studyConditionsTableModel = new StudyConditionsTableModel(studyConditions);
        jTableStudyConditions.setModel(studyConditionsTableModel);
        assignCellEditorStudyConditions();
        deshabilitaSorters();
    }

    public void assignNurseryConditions(List<Condition> trialConditions) {
        NurseryConditionsTableModel tableModel = new NurseryConditionsTableModel(trialConditions);
        this.jTableNurseryConditions.setModel(tableModel);

        sorterNurseryInformation = new TableRowSorter<TableModel>(tableModel);
        this.jTableNurseryConditions.setRowSorter(sorterNurseryInformation);
        assignCellEditorTrialCndition();
        deshabilitaSorters();
    }

    public void assignExperimentalConditions(List<Constant> constantList) {
        ExperimentalConditionsTableModel tableModel = new ExperimentalConditionsTableModel(constantList);
        jTableConstants.setModel(tableModel);
        sorterConstants = new TableRowSorter<TableModel>(tableModel);
        jTableConstants.setRowSorter(sorterConstants);
        deshabilitaSorters();
    }

    public void assignGermplasmEntries(List<Factor> factorHeaders, List<List<Object>> germplasmData) {
        GermplasmEntriesTableModel tableModel = new GermplasmEntriesTableModel(factorHeaders, germplasmData);
        this.jTableEntries.setModel(tableModel);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int col = 0; col < this.jTableEntries.getColumnCount(); col++) {

            this.jTableEntries.getColumnModel().getColumn(col).setCellRenderer(tcr);
        }
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public void disableTraitsSelection() {
        jButtonSync.setEnabled(false);
        doubleListPanel.setEnabled(false);
    }

    public DoubleListPanel<Variate> getDoubleListPanel() {
        return doubleListPanel;
    }

    public void setDoubleListPanel(DoubleListPanel<Variate> doubleListPanel) {
        this.doubleListPanel = doubleListPanel;
    }

    public boolean isStudyAlreadyExists() {
        return studyAlreadyExists;
    }

    public void setStudyAlreadyExists(boolean studyAlreadyExists) {
        this.studyAlreadyExists = studyAlreadyExists;
    }

    public Study getStudyInfo() {
        return studyInfo;
    }

    public void setStudyInfo(Study studyInfo) {
        this.studyInfo = studyInfo;
    }

    public void loadDataFromDB() {
        jTextFieldNurseryName.setEnabled(false);
        jButtonSaveNursery.setEnabled(true);
        ObservationsTableModel tableModel = new ObservationsTableModel(myWorkbook, myWorkbook.getVariates());
        jTableObservations.setModel(tableModel);
        sorterMeasurements = new TableRowSorter<TableModel>(tableModel);
        this.jTableObservations.setRowSorter(sorterMeasurements);
        int factorLabelColumn = 0;
        for (Measurement measurement : myWorkbook.getMeasurements()) {
            Object[] rowToAdd = new Object[tableModel.getColumnCount()];
            factorLabelColumn = 0;
            for (Object factorLabel : measurement.getFactorLabelData()) {
                rowToAdd[factorLabelColumn] = factorLabel;
                factorLabelColumn++;
            }
            for (MeasurementData data : measurement.getMeasurementsData()) {
                if (data.getVariateid() != null) {
                    int variateColumIndex = tableModel.getHeaderIndexForVariate(data.getVariateid());
                    if (variateColumIndex != -1) {
                        rowToAdd[variateColumIndex] = data.getValueData();
                    }
                }
            }
            tableModel.addRow(rowToAdd);
        }

        int entriesTot = this.jTableEntries.getRowCount();
        this.jTextFieldEntries.setText(String.valueOf(entriesTot));



        ajustaColumnsTable(this.jTableObservations, 2);

        for (int i = 0; i < jTableObservations.getColumnCount(); i++) {
            sorterMeasurements.setSortable(i, false);
        }

        changeCursorWaitStatus(false);
        ObservationTableTooltips.assignTooltips(jTableObservations);
    }

    private void createBallonTips() {
        String msg = NbBundle.getMessage(NurseryEditorTopComponent.class, "NurseryEditorTopComponent.jTextFieldNurseryName.Tip");
        tipSavingNursery = new BalloonTip(jTextFieldNurseryName, msg);
        ToolTipUtils.balloonToToolTip(tipSavingNursery, 500, 3000);
    }

    public void setMainProperties(Study estudio) {
        jTextFieldStudy.setText(estudio.getStudy());
        jTextFieldObjective.setText(estudio.getObjective());
        jTextFieldTitle.setText(estudio.getTitle());
        Date start = estudio.getStarDate();
        Date end = estudio.getEndDate();
        String formato = "dd-MMM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(formato);

        try {
            jDateChooserStart.setDateFormatString("yyyyMMdd");
            jDateChooserStart.setDate(start);
        } catch (NullPointerException ex) {
        }
        try {
            jDateChooserEnd.setDateFormatString("yyyyMMdd");
            jDateChooserEnd.setDate(end);
        } catch (NullPointerException ex) {
        }
        try {
            jTextFieldPMKey.setText(estudio.getPmkey());
        } catch (NullPointerException ex) {
        }
        try {
            jComboBoxStudyType.setSelectedItem(estudio.getStudyType());
        } catch (NullPointerException ex) {
        }
    }

    private void addChecksByPosition2() {
        GermplasmEntriesTableModel tableModelChecks = (GermplasmEntriesTableModel) this.tableChecks.getModel();
        List<List<Object>> germplasmDataChecks = tableModelChecks.getGermplasmData();

        GermplasmEntriesTableModel tableModel = (GermplasmEntriesTableModel) this.jTableEntries.getModel();
        List<List<Object>> germplasmData = tableModel.getGermplasmData();

        int colEntry = tableModel.findColumn("ENTRY");
        if (colEntry < 0) {
            return;
        }

        int colPosition = tableModelChecks.findColumn("Position");
        if (colPosition < 0) {
            return;
        }

        ArrayList<Object> posiciones = new ArrayList();

        for (int i = 0; i < tableChecks.getRowCount(); i++) {
            posiciones.add(tableChecks.getValueAt(i, colPosition));

        }

        for (int i = 0; i < posiciones.size(); i++) {
            System.out.println("Posicion: -->> " + posiciones.get(i));
            int pos = Integer.parseInt(posiciones.get(i).toString());
            germplasmData.add(pos - 1, germplasmDataChecks.get(i));
            //  recorreIndices(pos, germplasmData, colEntry);
        }

        assignGermplasmEntries(tableModel.getFactorHeaders(), tableModel.getGermplasmData());
    }

    public class MyIntComparable implements Comparator<SequenceEntry> {

        @Override
        public int compare(SequenceEntry o1, SequenceEntry o2) {
            return (o1.getPosicion() < o2.getPosicion() ? -1 : (o1.getPosicion() == o2.getPosicion() ? 0 : 1));
        }
    }

    private void fillChecks() {
        GermplasmEntriesTableModel tableModelChecks = (GermplasmEntriesTableModel) this.tableChecks.getModel();
        List<List<Object>> germplasmDataChecks = tableModelChecks.getGermplasmData();

        GermplasmEntriesTableModel tableModel = (GermplasmEntriesTableModel) this.jTableEntries.getModel();
        List<List<Object>> germplasmData = tableModel.getGermplasmData();

        //int colEntry = tableModel.findColumn("ENTRY");
        int colEntry = tableModel.getHeaderIndex(GermplasmEntriesTableModel.ENTRY);
        if (colEntry < 0) {
            return;
        }

        int contador = 0;
        int avance = 0;



        if (isChecksInSequence()) {


            Collections.sort(sequenceList, new MyIntComparable());

            for (int j = 0; j < sequenceList.size(); j++) {

                List<Object> newData = new ArrayList<Object>();

                Object[] check = germplasmDataChecks.get(sequenceList.get(j).getEntrada()).toArray();
                Object[] temp = check.clone();

                for (int k = 0; k < temp.length; k++) {
                    newData.add(k, temp[k]);
                }


                // System.out.println("Insertando entrada "+sequenceList.get(j).getEntrada() +"   en pos "+(sequenceList.get(j).getPosicion() - 1));

                int posToAdd = sequenceList.get(j).getPosicion() - 1;
                //  System.out.println("POS TO ADD "+posToAdd);

                germplasmData.add(posToAdd, newData);


            }


        } else {



            for (int i = 0; i < posiciones.size(); i++) {

                List<Object> newData = new ArrayList<Object>();
                Object[] nuevo = germplasmDataChecks.get(contador).toArray();
                Object[] temp = nuevo.clone();


                for (int j = 0; j < temp.length; j++) {
                    newData.add(j, temp[j]);
                }



                int pos = 0;

                pos = (posiciones.get(i));
                germplasmData.add(pos - 1, newData);
                contador++;

                if (contador > tableModelChecks.getRowCount() - 1) {
                    contador = 0;
                }



            }
        }

        recorreIndices(germplasmData, colEntry);
        assignGermplasmEntries(tableModel.getFactorHeaders(), tableModel.getGermplasmData());

    }

    public void addChecks() {

        if (this.tableChecks.getRowCount() == 0) {
            return;
        }

        fillChecks();
    }

    private void recorreIndices(List<List<Object>> germplasmData, int colEntry) {

        for (int j = 0; j < germplasmData.size(); j++) {
            List<Object> gsm = germplasmData.get(j);
            gsm.set(colEntry, j + 1);

        }

        /*
         * for (int j = 0; j < this.jTableEntries.getRowCount(); j++) {
         * jTableEntries.setValueAt(j+1, 0, colEntry); } *
         *
         */
    }

    public boolean existeTopComponent(String topName) {
        boolean existe = false;
        try {
            ArrayList<TopComponent> opened = new ArrayList<TopComponent>(WindowManager.getDefault().getRegistry().getOpened());

            for (TopComponent t : opened) {

                if (t.getName().equals(topName)) {
                    existe = true;
                }
            }
        } catch (NullPointerException ex) {
            existe = false;
        }
        return existe;
    }

    public boolean loadFileConfig() {
        boolean existe = false;

        try {
            config = new PropertiesConfiguration(ConfigurationUtils.locate("favorites.properties"));
            existe = true;
        } catch (ConfigurationException e) {
            System.out.println("NO HAY FAVORITES FILE");
        }

        return existe;

    }

    public int getSelectedBreedingMethod() {
        StudyConditionsTableModel model = (StudyConditionsTableModel) jTableStudyConditions.getModel();
        return model.getBreedingMethod();
    }

    private boolean nurseryNameAlreadyExists(String nurseryName) {
        boolean result = false;


        if (AppServicesProxy.getDefault().appServices().getStudyByName(nurseryName) != null) {
            result = true;
        }

        return result;

    }

    /**
     * Stores all cell that are in editing mode
     */
    private void storeCellsInEditMode() {
        storeCellsInEditMode(jTableStudyConditions);
        storeCellsInEditMode(jTableNurseryConditions);
        storeCellsInEditMode(jTableObservations);
        storeCellsInEditMode(jTableConstants);
    }

    /**
     * Stores a cell that is in editing mode
     *
     * @param jtable JTable to verify
     */
    private void storeCellsInEditMode(JTable jtable) {
        if (jtable.getCellEditor() != null) {
            jtable.getCellEditor().stopCellEditing();
        }
    }

    private void markChecksInGermplasmList() {

        GermplasmEntriesTableModel tableModel = (GermplasmEntriesTableModel) this.jTableEntries.getModel();
        tableModel.setMarkGermplasmAsCheck(true);

        int checkColum = tableModel.getHeaderIndex(GermplasmEntriesTableModel.ISCHECKNUMBER);
        if (checkColum > 0) {
            // first clear all "is check values
            for (int row = 0; row < tableModel.getRowCount(); row++) {
                tableModel.setValueAt("", row, checkColum);
            }
        }
        // then assign check positions
        if (checkColum > 0) {
            for (int i = 0; i < posiciones.size(); i++) {
                tableModel.setValueAt("is check", posiciones.get(i) - 1, checkColum);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel JPanelData;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroupDesign;
    private javax.swing.ButtonGroup buttonGroupExpConditions;
    private javax.swing.ButtonGroup buttonGroupMeasurements;
    private javax.swing.ButtonGroup buttonGroupTrInformation;
    private javax.swing.JButton jButtonAdvance;
    private javax.swing.JButton jButtonCSVTraitsExport1;
    private javax.swing.JButton jButtonCSVTraitsImport1;
    private javax.swing.JButton jButtonCopyGID;
    private javax.swing.JButton jButtonImportCrossInfo;
    private javax.swing.JButton jButtonSaveNursery;
    public static javax.swing.JButton jButtonSelectTraits;
    public static javax.swing.JButton jButtonSync;
    public javax.swing.JComboBox jComboBoxStudyType;
    public com.toedter.calendar.JDateChooser jDateChooserEnd;
    public com.toedter.calendar.JDateChooser jDateChooserStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JLabel jLabelEntries;
    private javax.swing.JLabel jLabelObsEntries;
    private javax.swing.JLabel jLabelTotalGermp;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItemSelect;
    private javax.swing.JMenuItem jMenuItemSelectAll;
    private javax.swing.JMenuItem jMenuItemUnSelect;
    private javax.swing.JMenuItem jMenuItemUnselectAll;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelTraits;
    private javax.swing.JPopupMenu jPopupMenuConstants;
    private javax.swing.JPopupMenu jPopupMenuSelect;
    private javax.swing.JPopupMenu jPopupMenuTraits;
    private javax.swing.JPopupMenu jPopupMenuUnSelect;
    private javax.swing.JRadioButton jRadioButtonNormal;
    private javax.swing.JRadioButton jRadioButtonRandom;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    public javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTable jTableConstants;
    public javax.swing.JTable jTableEntries;
    public javax.swing.JTable jTableNurseryConditions;
    public javax.swing.JTable jTableObservations;
    public javax.swing.JTable jTableStudyConditions;
    public javax.swing.JTextField jTextFieldDescription;
    public javax.swing.JTextField jTextFieldDescriptionSelected;
    public javax.swing.JTextField jTextFieldEntries;
    private javax.swing.JTextField jTextFieldNurseryName;
    public javax.swing.JTextField jTextFieldObjective;
    public javax.swing.JTextField jTextFieldPMKey;
    public javax.swing.JTextField jTextFieldStudy;
    public javax.swing.JTextField jTextFieldTitle;
    private javax.swing.JPanel pnlSelectList;
    // End of variables declaration//GEN-END:variables
}
