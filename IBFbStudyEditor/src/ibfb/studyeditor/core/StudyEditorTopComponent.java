package ibfb.studyeditor.core;

import ibfb.domain.core.Condition;
import ibfb.domain.core.Constant;
import ibfb.domain.core.DesignBean;
import ibfb.domain.core.Factor;
import ibfb.domain.core.FactorLabel;
import ibfb.domain.core.Measurement;
import ibfb.domain.core.MeasurementData;
import ibfb.domain.core.Study;
import ibfb.domain.core.Variate;
import ibfb.studyeditor.reports.TraitsReportHelper;
import ibfb.studyeditor.roweditors.AlphaDesignsRowEditor;
import ibfb.studyeditor.roweditors.CSVOziel;
import ibfb.studyeditor.roweditors.ColumnFitAdapter;
import ibfb.studyeditor.roweditors.TrialConditionsRowEditor;
import ibfb.studyexplorer.filters.CSVFiltro;
import ibfb.domain.core.Workbook;
import ibfb.studyeditor.core.db.FieldbookCSVUtil;
import ibfb.studyeditor.core.db.WorkbookSavingHelper;
import ibfb.studyeditor.core.model.DesignTableModel;
import ibfb.studyeditor.core.model.ExperimentalConditionsTableModel;
import ibfb.studyeditor.core.model.GermplasmEntriesTableModel;
import ibfb.studyeditor.core.model.JTableUtils;
import ibfb.studyeditor.core.model.ObservationTableTooltips;
import ibfb.studyeditor.core.model.ObservationsTableModel;
import ibfb.studyeditor.core.model.OtherTreatmentFactorsTableModel;
import ibfb.studyeditor.core.model.StudyConditionsTableModel;
import ibfb.studyeditor.core.model.TreatmentLabelsTableModel;
import ibfb.studyeditor.core.model.TrialConditionsTableModel;
import ibfb.studyeditor.designs.DesignsClass;
import ibfb.studyeditor.designs.DesignsUtils;
import ibfb.studyeditor.export.FieldBookExcelExporter;
import ibfb.studyeditor.export.FieldbookCSVExporter;
import ibfb.studyeditor.export.FieldbookRExport;
import ibfb.studyeditor.exportwizard.exportWizardIterator;
import ibfb.studyeditor.importwizard.ImportData;
import ibfb.studyeditor.importwizard.importingVisualPanel2;
import ibfb.studyeditor.importwizard.importingWizardIterator;
import ibfb.studyeditor.roweditors.ConditionsRowEditor;
import ibfb.studyeditor.roweditors.ListItemTransferHandler;
import ibfb.studyeditor.util.Clipboard;
import ibfb.studyeditor.util.DateUtil;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import ibfb.studyeditor.util.LookupUtil;
import ibfb.studyeditor.util.RefreshBrowserHelper;
import ibfb.traits.traits.TraitsExplorerTopComponent;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.PatternSyntaxException;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.TransferHandler;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.utils.ToolTipUtils;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.OSUtils;
import org.cimmyt.cril.ibwb.commongui.select.list.DoubleListPanel;
import org.cimmyt.cril.ibwb.commongui.select.list.DropTargetCommand;
import org.cimmyt.cril.ibwb.commongui.select.list.SelectCommand;
import org.cimmyt.cril.ibwb.domain.Traits;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.WizardDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Exceptions;
import org.openide.util.Mutex;
import org.openide.windows.WindowManager;

@ConvertAsProperties(dtd = "-//ibfb.studyeditor.core//StudyEditor//EN",
autostore = false)
@TopComponent.Description(preferredID = "StudyEditorTopComponent",
iconBase = "ibfb/studyeditor/images/newTrial16.png",
persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "ibfb.studyeditor.core.StudyEditorTopComponent")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_StudyEditorAction",
preferredID = "StudyEditorTopComponent")
public final class StudyEditorTopComponent extends TopComponent {

    final static String badchars = "`~!@#$%^&*()_+=\\|\"':;?/>.<, ";
    private JFileChooser selectorArchivo = new JFileChooser();
    private TableDataExporterHelper helper = new TableDataExporterHelper();
    private int fila = 0;
    TableRowSorter<TableModel> sorterTrialInformation;
    TableRowSorter<TableModel> sorterConstants;
    TableRowSorter<TableModel> sorterMeasurements;
    TableRowSorter<TableModel> sorterDesign;
    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel modelConstants = new DefaultTableModel();
    DefaultTableModel modelMeasurements = new DefaultTableModel();
    DesignTableModel modelDesign = new DesignTableModel();
    DefaultTableModel modelObs = new DefaultTableModel();
    private CSVOziel csv;
    AlphaDesignsRowEditor alphaRowEditorStudy;
    private Workbook myWorkbook;
    private String fileTemplate;
    private DefaultListModel listModelSelected = new DefaultListModel();
    private DefaultListModel listModelUnSelected = new DefaultListModel();
    private TransferHandler transfer = new ListItemTransferHandler(2);
    java.util.ArrayList<Variate> traits = new ArrayList<Variate>();
    DefaultListModel listModelA = new DefaultListModel();
    DefaultListModel listModelB = new DefaultListModel();
    public int trialStart = 0;
    public int trialEnd = 0;
    public int trialSelected = 0;
    public int triallOption = 0; //0=all trial, 1=selectedTrial, 2=rango de trials
    public String trialFile = "";
    public int opcionFiltro = 0;
    public int opcionExport = 0; //0=fieldlog, 1=to r, 2=excel file
    public int opcionImport = 0; //0=fieldlog, 1=excel 
    public File trialImportFile;
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
    private DesignsUtils designsUtils;
    private List<Variate> availableTraits = new ArrayList<Variate>();
    private List<Variate> selectedTraits = new ArrayList<Variate>();
    private DoubleListPanel<Variate> doubleListPanel;
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
    /**
     * Current study already exists?
     */
    private boolean studyAlreadyExists;
    /**
     * Particular information about Trial
     */
    private Study studyInfo;
    private BalloonTip tipSavingTrial;

    public StudyEditorTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(StudyEditorTopComponent.class, "CTL_StudyEditorTopComponent"));
        setToolTipText(NbBundle.getMessage(StudyEditorTopComponent.class, "HINT_StudyEditorTopComponent"));
        modelMeasurements = (DefaultTableModel) this.jTableObservations.getModel();
        sorterMeasurements = new TableRowSorter<TableModel>(modelMeasurements);
        this.jTableObservations.setRowSorter(sorterMeasurements);
        model = (DefaultTableModel) this.jTableTrialConditions.getModel();
        sorterTrialInformation = new TableRowSorter<TableModel>(model);
        this.jTableTrialConditions.setRowSorter(sorterTrialInformation);
        modelConstants = (DefaultTableModel) this.jTableConstants.getModel();
        sorterConstants = new TableRowSorter<TableModel>(modelConstants);
        this.jTableConstants.setRowSorter(sorterConstants);
        modelDesign = (DesignTableModel) this.jTableDesign.getModel();
        sorterDesign = new TableRowSorter<TableModel>(modelDesign);
        this.jTableDesign.setRowSorter(sorterDesign);
        modelObs = (DefaultTableModel) this.jTableObservations.getModel();
        assignCellEditorTrialCndition();
        assignCellEditorStudyConditions();
        this.jTableStudyConditions.getTableHeader().addMouseListener(new ColumnFitAdapter());
        this.jTableTrialConditions.getTableHeader().addMouseListener(new ColumnFitAdapter());
        this.jTableConstants.getTableHeader().addMouseListener(new ColumnFitAdapter());
        this.jTableOtherFactorLabels.getTableHeader().addMouseListener(new ColumnFitAdapter());
        this.jTableEntries.getTableHeader().addMouseListener(new ColumnFitAdapter());
        this.jTableDesign.getTableHeader().addMouseListener(new ColumnFitAdapter());
        this.jTableObservations.getTableHeader().addMouseListener(new ColumnFitAdapter());
        csv = new CSVOziel(this.jTableObservations, new JList());
        deshabilitaSorters();
        designsUtils = new DesignsUtils(jTableDesign, jTextFieldEntries);
        doubleListPanel = new DoubleListPanel<Variate>(availableTraits, selectedTraits, unselectedCommand, selectedCommand);
        doubleListPanel.setSourceLabel("Unselected Traits (from template)");
        doubleListPanel.setTargetLabel("Selected Traits");
        doubleListPanel.setVisible(true);
        doubleListPanel.setDropTargetCommand(new TraitsDropTargetCommand());
        pnlSelectList.add(doubleListPanel);

        studyAlreadyExists = false;

        studyInfo = new Study();

        createBallonTips();

        jRadioButtonFilterTrialStudy.setVisible(false);
        jRadioButtonViewAllTrialStudy.setVisible(false);
        jSpinnerTrialStudy.setVisible(false);
        jLabel8.setVisible(false);
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

    public String[][] getFactorsDesignCad() {
        return factorsDesignCad;
    }

    public void setFactorsDesignCad(String[][] factorsDesignCad) {
        this.factorsDesignCad = factorsDesignCad;
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

    public JSpinner getjSpinnerConstants() {
        return jSpinnerExpConditions;
    }

    public void setjSpinnerConstants(JSpinner jSpinnerConstants) {
        this.jSpinnerExpConditions = jSpinnerConstants;
    }

    public JSpinner getjSpinnerTrInformation() {
        return jSpinnerTrialInformation;
    }

    public void setjSpinnerTrInformation(JSpinner jSpinnerTrInformation) {
        this.jSpinnerTrialInformation = jSpinnerTrInformation;
    }

    public JTextField getjTextFieldEntries() {
        return jTextFieldEntries;
    }

    public void setjTextFieldEntries(JTextField jTextFieldEntries) {
        this.jTextFieldEntries = jTextFieldEntries;
    }

    public void asignRowEditor() {
        alphaRowEditorStudy = new AlphaDesignsRowEditor(jTableDesign, Integer.parseInt(this.jTextFieldEntries.getText()), designsUtils.getRep2(), designsUtils.getRep3(), designsUtils.getRep4());
        TableColumn valueColumn = jTableDesign.getColumnModel().getColumn(3);
        valueColumn.setCellEditor(alphaRowEditorStudy);

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

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
        jLabel19 = new javax.swing.JLabel();
        jLabelInstances = new javax.swing.JLabel();
        jDateChooserStart = new javax.swing.JTextField();
        jDateChooserEnd = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jRadioButtonFilterTrialInfo = new javax.swing.JRadioButton();
        jRadioButtonAllTrials = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        jSpinnerTrialInformation = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTrialConditions = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jRadioButtonFilterExpConditions = new javax.swing.JRadioButton();
        jRadioButtonAllExpCondition = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        jSpinnerExpConditions = new javax.swing.JSpinner();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableConstants = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableEntries = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableOtherFactorLabels = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableOtherFactors = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jRadioButtonFilterTrialStudy = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jSpinnerTrialStudy = new javax.swing.JSpinner();
        jRadioButtonViewAllTrialStudy = new javax.swing.JRadioButton();
        jButtonRefreshDesign = new javax.swing.JButton();
        jLabelEntries = new javax.swing.JLabel();
        jTextFieldEntries = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        rbtnIndividualDesign = new javax.swing.JRadioButton();
        rbtnUseSameDesign = new javax.swing.JRadioButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableDesign = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanelTraits = new javax.swing.JPanel();
        jTextFieldDescription = new javax.swing.JTextField();
        jTextFieldDescriptionSelected = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jButtonSelectTraits = new javax.swing.JButton();
        jButtonSync = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        pnlSelectList = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableObservations = new javax.swing.JTable();
        jPanel25 = new javax.swing.JPanel();
        jRadioButtonFilterTrial = new javax.swing.JRadioButton();
        jRadioButtonAllTrials2 = new javax.swing.JRadioButton();
        jSpinnerTrial = new javax.swing.JSpinner();
        jRadioButtonFilterEntry = new javax.swing.JRadioButton();
        jSpinnerEntry = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jTextTrialName = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnPrintLabels = new javax.swing.JButton();
        jButtonSaveToExcel = new javax.swing.JButton();
        jButtonCSVTraitsExport1 = new javax.swing.JButton();
        jButtonCSVTraitsImport1 = new javax.swing.JButton();
        jButtonCopyGID = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItem1, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jMenuItem1.text")); // NOI18N
        jMenuItem1.setToolTipText(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jMenuItem1.toolTipText")); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenuTraits.add(jMenuItem1);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItem2, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jMenuItem2.text")); // NOI18N
        jMenuItem2.setToolTipText(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jMenuItem2.toolTipText")); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenuTraits.add(jMenuItem2);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItem3, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jMenuItem3.text")); // NOI18N
        jMenuItem3.setToolTipText(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jMenuItem3.toolTipText")); // NOI18N
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jPopupMenuConstants.add(jMenuItem3);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItem4, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jMenuItem4.text")); // NOI18N
        jMenuItem4.setToolTipText(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jMenuItem4.toolTipText")); // NOI18N
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jPopupMenuConstants.add(jMenuItem4);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemUnSelect, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jMenuItemUnSelect.text")); // NOI18N
        jMenuItemUnSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUnSelectActionPerformed(evt);
            }
        });
        jPopupMenuUnSelect.add(jMenuItemUnSelect);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemUnselectAll, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jMenuItemUnselectAll.text")); // NOI18N
        jMenuItemUnselectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUnselectAllActionPerformed(evt);
            }
        });
        jPopupMenuUnSelect.add(jMenuItemUnselectAll);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemSelect, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jMenuItemSelect.text")); // NOI18N
        jMenuItemSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSelectActionPerformed(evt);
            }
        });
        jPopupMenuSelect.add(jMenuItemSelect);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemSelectAll, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jMenuItemSelectAll.text")); // NOI18N
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

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jLabel2.text")); // NOI18N

        JPanelData.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldObjective.setText(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jTextFieldObjective.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jLabel10.text")); // NOI18N

        jTextFieldStudy.setText(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jTextFieldStudy.text")); // NOI18N
        jTextFieldStudy.setMinimumSize(new java.awt.Dimension(14, 50));

        jTextFieldTitle.setText(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jTextFieldTitle.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jLabel4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jLabel6.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jLabel7.text")); // NOI18N

        jComboBoxStudyType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "International", "National", "Bank", "Foreign" }));

        jTextFieldPMKey.setText(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jTextFieldPMKey.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jLabel9.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jLabel11.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel19, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jLabel19.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelInstances, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jLabelInstances.text")); // NOI18N

        jDateChooserStart.setEditable(false);
        jDateChooserStart.setText(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jDateChooserStart.text")); // NOI18N

        jDateChooserEnd.setEditable(false);
        jDateChooserEnd.setText(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jDateChooserEnd.text")); // NOI18N

        javax.swing.GroupLayout JPanelDataLayout = new javax.swing.GroupLayout(JPanelData);
        JPanelData.setLayout(JPanelDataLayout);
        JPanelDataLayout.setHorizontalGroup(
            JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelDataLayout.createSequentialGroup()
                .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanelDataLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanelDataLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanelDataLayout.createSequentialGroup()
                        .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                            .addComponent(jTextFieldObjective, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                            .addComponent(jTextFieldStudy, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
                        .addGap(140, 140, 140))
                    .addGroup(JPanelDataLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelInstances, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooserEnd)
                    .addComponent(jDateChooserStart)
                    .addComponent(jComboBoxStudyType, 0, 150, Short.MAX_VALUE)
                    .addComponent(jTextFieldPMKey, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addGap(250, 250, 250))
        );
        JPanelDataLayout.setVerticalGroup(
            JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanelDataLayout.createSequentialGroup()
                        .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(jTextFieldStudy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPanelDataLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JPanelDataLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel10)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldObjective, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jLabelInstances)))
                    .addGroup(JPanelDataLayout.createSequentialGroup()
                        .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jDateChooserStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooserEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(6, 6, 6)
                        .addGroup(JPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPanelDataLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanelDataLayout.createSequentialGroup()
                                .addComponent(jComboBoxStudyType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldPMKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1049, Short.MAX_VALUE)
                    .addComponent(JPanelData, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jPanel23.border.title"))); // NOI18N

        buttonGroupTrInformation.add(jRadioButtonFilterTrialInfo);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonFilterTrialInfo, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jRadioButtonFilterTrialInfo.text")); // NOI18N
        jRadioButtonFilterTrialInfo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonFilterTrialInfoItemStateChanged(evt);
            }
        });

        buttonGroupTrInformation.add(jRadioButtonAllTrials);
        jRadioButtonAllTrials.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonAllTrials, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jRadioButtonAllTrials.text")); // NOI18N
        jRadioButtonAllTrials.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonAllTrialsItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jLabel12.text")); // NOI18N

        jSpinnerTrialInformation.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerTrialInformationStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonFilterTrialInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinnerTrialInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 812, Short.MAX_VALUE)
                .addComponent(jRadioButtonAllTrials)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jRadioButtonAllTrials)
                    .addComponent(jSpinnerTrialInformation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(jRadioButtonFilterTrialInfo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTableTrialConditions.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableTrialConditions.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableTrialConditions.getTableHeader().setReorderingAllowed(false);
        jTableTrialConditions.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableTrialConditionsPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(jTableTrialConditions);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1041, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jPanel24.border.title"))); // NOI18N

        buttonGroupExpConditions.add(jRadioButtonFilterExpConditions);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonFilterExpConditions, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jRadioButtonFilterExpConditions.text")); // NOI18N
        jRadioButtonFilterExpConditions.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonFilterExpConditionsItemStateChanged(evt);
            }
        });

        buttonGroupExpConditions.add(jRadioButtonAllExpCondition);
        jRadioButtonAllExpCondition.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonAllExpCondition, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jRadioButtonAllExpCondition.text")); // NOI18N
        jRadioButtonAllExpCondition.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonAllExpConditionItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel15, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jLabel15.text")); // NOI18N

        jSpinnerExpConditions.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerExpConditionsStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonFilterExpConditions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinnerExpConditions, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 812, Short.MAX_VALUE)
                .addComponent(jRadioButtonAllExpCondition)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jRadioButtonAllExpCondition)
                    .addComponent(jSpinnerExpConditions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButtonFilterExpConditions)
                        .addComponent(jLabel15)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1041, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

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
        jTableEntries.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTableEntries.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(jTableEntries);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1041, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jPanel7.TabConstraints.tabTitle"), jPanel7); // NOI18N

        jTableOtherFactorLabels.setModel(new ibfb.studyeditor.core.model.TreatmentLabelsTableModel());
        jTableOtherFactorLabels.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableOtherFactorLabels.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTableOtherFactorLabels);

        jTableOtherFactors.setModel(new ibfb.studyeditor.core.model.OtherTreatmentFactorsTableModel());
        jScrollPane4.setViewportView(jTableOtherFactors);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1041, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1049, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroupDesign.add(jRadioButtonFilterTrialStudy);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonFilterTrialStudy, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jRadioButtonFilterTrialStudy.text")); // NOI18N
        jRadioButtonFilterTrialStudy.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonFilterTrialStudyItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jLabel8.text")); // NOI18N

        jSpinnerTrialStudy.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerTrialStudyStateChanged(evt);
            }
        });

        buttonGroupDesign.add(jRadioButtonViewAllTrialStudy);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonViewAllTrialStudy, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jRadioButtonViewAllTrialStudy.text")); // NOI18N
        jRadioButtonViewAllTrialStudy.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonViewAllTrialStudyItemStateChanged(evt);
            }
        });

        jButtonRefreshDesign.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jButtonRefreshDesign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/sync.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonRefreshDesign, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jButtonRefreshDesign.text")); // NOI18N
        jButtonRefreshDesign.setToolTipText(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jButtonRefreshDesign.toolTipText")); // NOI18N
        jButtonRefreshDesign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshDesignActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelEntries, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jLabelEntries.text")); // NOI18N

        jTextFieldEntries.setEditable(false);
        jTextFieldEntries.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jTextFieldEntries.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldEntries.setText(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jTextFieldEntries.text")); // NOI18N

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jPanel11.border.title"))); // NOI18N

        buttonGroupDesign.add(rbtnIndividualDesign);
        org.openide.awt.Mnemonics.setLocalizedText(rbtnIndividualDesign, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.rbtnIndividualDesign.text")); // NOI18N
        rbtnIndividualDesign.setToolTipText(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.rbtnIndividualDesign.toolTipText")); // NOI18N

        buttonGroupDesign.add(rbtnUseSameDesign);
        rbtnUseSameDesign.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(rbtnUseSameDesign, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.rbtnUseSameDesign.text")); // NOI18N
        rbtnUseSameDesign.setToolTipText(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.rbtnUseSameDesign.toolTipText")); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(rbtnIndividualDesign)
                        .addContainerGap())
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(rbtnUseSameDesign, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(54, 54, 54))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(rbtnUseSameDesign)
                .addGap(3, 3, 3)
                .addComponent(rbtnIndividualDesign))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonFilterTrialStudy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinnerTrialStudy, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonViewAllTrialStudy)
                .addGap(45, 45, 45)
                .addComponent(jButtonRefreshDesign, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(313, 313, 313)
                .addComponent(jLabelEntries)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldEntries, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldEntries)
                    .addComponent(jLabelEntries))
                .addGap(46, 46, 46))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonRefreshDesign)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSpinnerTrialStudy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButtonFilterTrialStudy)
                            .addComponent(jLabel8))
                        .addComponent(jRadioButtonViewAllTrialStudy, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel14Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabelEntries, jTextFieldEntries});

        jPanel14Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel8, jSpinnerTrialStudy});

        jTableDesign.setModel(new DesignTableModel());
        jTableDesign.getTableHeader().setReorderingAllowed(false);
        jTableDesign.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableDesignPropertyChange(evt);
            }
        });
        jScrollPane8.setViewportView(jTableDesign);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jPanel8.TabConstraints.tabTitle"), jPanel8); // NOI18N

        jPanel6.setMaximumSize(new java.awt.Dimension(1000, 650));
        jPanel6.setMinimumSize(new java.awt.Dimension(1000, 650));

        jPanelTraits.setPreferredSize(new java.awt.Dimension(900, 650));

        jTextFieldDescription.setEditable(false);
        jTextFieldDescription.setText(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jTextFieldDescription.text")); // NOI18N

        jTextFieldDescriptionSelected.setEditable(false);
        jTextFieldDescriptionSelected.setText(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jTextFieldDescriptionSelected.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jLabel13.text")); // NOI18N

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonSelectTraits.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jButtonSelectTraits.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/searchDB.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSelectTraits, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jButtonSelectTraits.text")); // NOI18N
        jButtonSelectTraits.setToolTipText(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jButtonSelectTraits.toolTipText")); // NOI18N
        jButtonSelectTraits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelectTraitsActionPerformed(evt);
            }
        });

        jButtonSync.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jButtonSync.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/sync.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSync, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jButtonSync.text")); // NOI18N
        jButtonSync.setToolTipText(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jButtonSync.toolTipText")); // NOI18N
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
                .addComponent(jButtonSync)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonSelectTraits, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSelectTraits, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSync, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelTraitsLayout = new javax.swing.GroupLayout(jPanelTraits);
        jPanelTraits.setLayout(jPanelTraitsLayout);
        jPanelTraitsLayout.setHorizontalGroup(
            jPanelTraitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTraitsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTraitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTraitsLayout.createSequentialGroup()
                        .addComponent(jTextFieldDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                        .addGap(162, 162, 162)
                        .addGroup(jPanelTraitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldDescriptionSelected, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                            .addComponent(jLabel13)))
                    .addGroup(jPanelTraitsLayout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 777, Short.MAX_VALUE))
                    .addComponent(pnlSelectList, javax.swing.GroupLayout.DEFAULT_SIZE, 949, Short.MAX_VALUE))
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelTraitsLayout.setVerticalGroup(
            jPanelTraitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTraitsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTraitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTraitsLayout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlSelectList, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE))
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTraitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTraitsLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDescriptionSelected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldDescription, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTraits, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1053, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTraits, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jPanel6.TabConstraints.tabTitle"), jPanel6); // NOI18N

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
        jScrollPane7.setViewportView(jTableObservations);

        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jPanel25.border.title"))); // NOI18N

        buttonGroupMeasurements.add(jRadioButtonFilterTrial);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonFilterTrial, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jRadioButtonFilterTrial.text")); // NOI18N
        jRadioButtonFilterTrial.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonFilterTrialItemStateChanged(evt);
            }
        });

        buttonGroupMeasurements.add(jRadioButtonAllTrials2);
        jRadioButtonAllTrials2.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonAllTrials2, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jRadioButtonAllTrials2.text")); // NOI18N
        jRadioButtonAllTrials2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonAllTrials2ItemStateChanged(evt);
            }
        });

        jSpinnerTrial.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerTrialStateChanged(evt);
            }
        });

        buttonGroupMeasurements.add(jRadioButtonFilterEntry);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonFilterEntry, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jRadioButtonFilterEntry.text")); // NOI18N
        jRadioButtonFilterEntry.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonFilterEntryItemStateChanged(evt);
            }
        });

        jSpinnerEntry.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerEntryStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jLabel1.text")); // NOI18N

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${studyInfo.study}"), jTextTrialName, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jTextTrialName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextTrialNameKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonFilterTrial)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinnerTrial, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jRadioButtonFilterEntry)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinnerEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextTrialName, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 276, Short.MAX_VALUE)
                .addComponent(jRadioButtonAllTrials2)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonFilterTrial)
                    .addComponent(jSpinnerTrial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonFilterEntry)
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jSpinnerEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jTextTrialName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jRadioButtonAllTrials2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel25Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jRadioButtonFilterEntry, jSpinnerEntry});

        jPanel25Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jRadioButtonFilterTrial, jSpinnerTrial});

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jPanel5.border.title"))); // NOI18N
        jPanel5.setPreferredSize(new java.awt.Dimension(187, 85));

        btnPrintLabels.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/print24.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnPrintLabels, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.btnPrintLabels.text")); // NOI18N
        btnPrintLabels.setToolTipText(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.btnPrintLabels.toolTipText")); // NOI18N
        btnPrintLabels.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPrintLabels.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPrintLabels.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintLabelsActionPerformed(evt);
            }
        });

        jButtonSaveToExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/save.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSaveToExcel, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jButtonSaveToExcel.text")); // NOI18N
        jButtonSaveToExcel.setToolTipText(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jButtonSaveToExcel.toolTipText")); // NOI18N
        jButtonSaveToExcel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSaveToExcel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSaveToExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveToExcelActionPerformed(evt);
            }
        });

        jButtonCSVTraitsExport1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/export.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonCSVTraitsExport1, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jButtonCSVTraitsExport1.text")); // NOI18N
        jButtonCSVTraitsExport1.setToolTipText(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jButtonCSVTraitsExport1.toolTipText")); // NOI18N
        jButtonCSVTraitsExport1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCSVTraitsExport1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonCSVTraitsExport1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCSVTraitsExport1ActionPerformed(evt);
            }
        });

        jButtonCSVTraitsImport1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/csvFile.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonCSVTraitsImport1, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jButtonCSVTraitsImport1.text")); // NOI18N
        jButtonCSVTraitsImport1.setToolTipText(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jButtonCSVTraitsImport1.toolTipText")); // NOI18N
        jButtonCSVTraitsImport1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCSVTraitsImport1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonCSVTraitsImport1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCSVTraitsImport1ActionPerformed(evt);
            }
        });

        jButtonCopyGID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/copyGID.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonCopyGID, org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jButtonCopyGID.text")); // NOI18N
        jButtonCopyGID.setToolTipText(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jButtonCopyGID.toolTipText")); // NOI18N
        jButtonCopyGID.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCopyGID.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonCopyGID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCopyGIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonCSVTraitsImport1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCSVTraitsExport1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSaveToExcel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPrintLabels, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButtonCopyGID, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSaveToExcel)
                .addGap(18, 18, 18)
                .addComponent(btnPrintLabels, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonCSVTraitsExport1)
                .addGap(18, 18, 18)
                .addComponent(jButtonCSVTraitsImport1)
                .addGap(18, 18, 18)
                .addComponent(jButtonCopyGID, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnPrintLabels, jButtonCSVTraitsExport1, jButtonCSVTraitsImport1, jButtonSaveToExcel});

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, 1053, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jPanel10.TabConstraints.tabTitle"), jPanel10); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1074, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableTrialConditionsPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableTrialConditionsPropertyChange
        if (this.jTableTrialConditions.getCellEditor() != null) {
            jTableTrialConditions.getCellEditor().stopCellEditing();
        }
        this.fila = this.jTableTrialConditions.getEditingRow();
        if (fila == -1) {
            return;
        }
        try {
            if (evt.getOldValue() == null) {

                System.out.println("NO CAMBIO VALOR \n");
                return;
            }


            if (evt.getNewValue() == null) {
                Object valor = this.jTableTrialConditions.getValueAt(this.fila, 5).toString();
                if (valor.equals("")) {
                    return;
                }
                System.out.println("FILA: " + fila);
                System.out.println("Valor: " + valor + "\n");
                Object condition = this.jTableTrialConditions.getValueAt(this.fila, 1).toString().toUpperCase();
                Object property = this.jTableTrialConditions.getValueAt(this.fila, TRIAL_PROPERTY_COL).toString().toUpperCase();
                Object scale = this.jTableTrialConditions.getValueAt(this.fila, TRIAL_SCALE_COL).toString().toUpperCase();
                int instancia = Integer.parseInt(this.jTableTrialConditions.getValueAt(this.fila, TRIAL_INSTANCE_COL).toString());
                if (property.equals(LookupUtil.PERSON) && scale.equals(LookupUtil.DBID)) {
                    LookupUtil.lookupPersonName(jTableTrialConditions, instancia, valor, TRIAL_PROPERTY_COL, TRIAL_SCALE_COL, TRIAL_VALUE_COL);
                }

                if (property.equals(LookupUtil.PERSON) && scale.equals(LookupUtil.DBCV)) {
                    LookupUtil.lookupPersonId(jTableTrialConditions, instancia, valor, TRIAL_PROPERTY_COL, TRIAL_SCALE_COL, TRIAL_VALUE_COL);
                }

                if (property.equals(LookupUtil.LOCATION) && scale.equals(LookupUtil.DBID)) {
                    LookupUtil.lookupLocationName(jTableTrialConditions, instancia, valor, TRIAL_PROPERTY_COL, TRIAL_SCALE_COL, TRIAL_VALUE_COL);
                }
                if (property.equals(LookupUtil.LOCATION) && scale.equals(LookupUtil.DBCV)) {
                    LookupUtil.lookupLocationId(jTableTrialConditions, instancia, valor, TRIAL_PROPERTY_COL, TRIAL_SCALE_COL, TRIAL_VALUE_COL);
                }


                if (condition.equals("PDATE-1 LATE")) {
                    DateUtil.compareDates(instancia, jTableTrialConditions, fila);
                    return;
                }

                if (condition.equals("PDATE-2 LATE")) {
                    DateUtil.compareDates(instancia, jTableTrialConditions, fila);
                    return;
                }
            }

        } catch (NullPointerException error) {
        }
    }//GEN-LAST:event_jTableTrialConditionsPropertyChange

    private void jRadioButtonFilterTrialInfoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonFilterTrialInfoItemStateChanged
        filterTrialInformationByTrial();

}//GEN-LAST:event_jRadioButtonFilterTrialInfoItemStateChanged

    private void jRadioButtonAllTrialsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonAllTrialsItemStateChanged
        if (this.jRadioButtonAllTrials.isSelected()) {
            sorterTrialInformation.setRowFilter(null);
        }
}//GEN-LAST:event_jRadioButtonAllTrialsItemStateChanged

    private void jSpinnerTrialInformationStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerTrialInformationStateChanged
        filterTrialInformationByTrial();
}//GEN-LAST:event_jSpinnerTrialInformationStateChanged

    private void jRadioButtonFilterExpConditionsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonFilterExpConditionsItemStateChanged
        filterExperimentalConditionsByTrial();

}//GEN-LAST:event_jRadioButtonFilterExpConditionsItemStateChanged

    private void jRadioButtonAllExpConditionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonAllExpConditionItemStateChanged
        if (this.jRadioButtonAllExpCondition.isSelected()) {
            sorterConstants.setRowFilter(null);
        }
}//GEN-LAST:event_jRadioButtonAllExpConditionItemStateChanged

    private void jSpinnerExpConditionsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerExpConditionsStateChanged
        filterExperimentalConditionsByTrial();
}//GEN-LAST:event_jSpinnerExpConditionsStateChanged

    private void jRadioButtonFilterTrialStudyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonFilterTrialStudyItemStateChanged
        filterDesignsByTrial();

}//GEN-LAST:event_jRadioButtonFilterTrialStudyItemStateChanged

    private void jSpinnerTrialStudyStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerTrialStudyStateChanged
        filterDesignsByTrial();

}//GEN-LAST:event_jSpinnerTrialStudyStateChanged

    private void jRadioButtonViewAllTrialStudyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonViewAllTrialStudyItemStateChanged
        if (this.jRadioButtonViewAllTrialStudy.isSelected()) {
            sorterDesign.setRowFilter(null);
        }
}//GEN-LAST:event_jRadioButtonViewAllTrialStudyItemStateChanged

    private void asignaClipboard() {
        this.jTableObservations.addKeyListener(new Clipboard((jTableObservations)));
    }
    private void jTableDesignPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableDesignPropertyChange
        if (this.jTableDesign.getCellEditor() != null) {
            jTableDesign.getCellEditor().stopCellEditing();
        }

        int colEditing = this.jTableDesign.getEditingColumn();

        this.fila = this.jTableDesign.getEditingRow();

        if (fila == -1) {
            return;
        }

        try {
            if (evt.getOldValue() == null) {
                return;
            }
            if (evt.getNewValue() == null) {
                designsUtils.checkDesignTable(colEditing, fila, rbtnUseSameDesign.isSelected());
            }
        } catch (NullPointerException error) {
        }

        JTableUtils.ajustaColumnsTable(jTableDesign, 2);

}//GEN-LAST:event_jTableDesignPropertyChange

    private void jRadioButtonFilterTrialItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonFilterTrialItemStateChanged
        filterObservationsByTrial();

}//GEN-LAST:event_jRadioButtonFilterTrialItemStateChanged

    private void jRadioButtonAllTrials2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonAllTrials2ItemStateChanged
        if (this.jRadioButtonAllTrials2.isSelected()) {
            sorterMeasurements.setRowFilter(null);
        }
}//GEN-LAST:event_jRadioButtonAllTrials2ItemStateChanged

    private void jSpinnerTrialStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerTrialStateChanged
        filterObservationsByTrial();
}//GEN-LAST:event_jSpinnerTrialStateChanged

    private void jRadioButtonFilterEntryItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonFilterEntryItemStateChanged
        filterObservationsByEntry();
}//GEN-LAST:event_jRadioButtonFilterEntryItemStateChanged

    private void jSpinnerEntryStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerEntryStateChanged
        filterObservationsByEntry();
}//GEN-LAST:event_jSpinnerEntryStateChanged

    private void filterTrialInformationByTrial() {
        if (this.jRadioButtonFilterTrialInfo.isSelected()) {
            int num = (Integer) this.jSpinnerTrialInformation.getValue();
            try {
                sorterTrialInformation.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, num, 0));
            } catch (PatternSyntaxException pse) {
                System.err.println("Bad regex pattern");
            }
        }
    }

    private void filterExperimentalConditionsByTrial() {
        if (this.jRadioButtonFilterExpConditions.isSelected()) {
            int num = (Integer) this.jSpinnerExpConditions.getValue();
            try {
                sorterConstants.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, num, 0));
            } catch (PatternSyntaxException pse) {
                System.err.println("Bad regex pattern");
            }
        }
    }

    private void filterDesignsByTrial() {
        if (this.jRadioButtonFilterTrialStudy.isSelected()) {
            int num = (Integer) this.jSpinnerTrialStudy.getValue();
            try {
                sorterDesign.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, num, 0));
            } catch (PatternSyntaxException pse) {
                System.err.println("Bad regex pattern");
            }
        }
    }

    private void filterObservationsByTrial() {
        if (this.jRadioButtonFilterTrial.isSelected()) {
            int num = (Integer) this.jSpinnerTrial.getValue();
            try {
                ObservationsTableModel tableModel = (ObservationsTableModel) jTableObservations.getModel();
                int colEntry = tableModel.getHeaderIndex(ObservationsTableModel.TRIAL);
                sorterMeasurements.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, num, colEntry));
            } catch (PatternSyntaxException pse) {
                System.err.println("Bad regex pattern");
            }
        }
    }

    private void filterObservationsByEntry() {
        if (this.jRadioButtonFilterEntry.isSelected()) {
            int num = (Integer) this.jSpinnerEntry.getValue();
            try {
                ObservationsTableModel tableModel = (ObservationsTableModel) jTableObservations.getModel();
                int colEntry = tableModel.getHeaderIndex(ObservationsTableModel.ENTRY);
                sorterMeasurements.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, num, colEntry));
            } catch (PatternSyntaxException pse) {
                System.err.println("Bad regex pattern");
            }
        }
    }

    private void exportToExcel() {
        List<Constant> selectedConstants = WorkbookSavingHelper.getSelectedConstants(this);
        FieldBookExcelExporter excelExporter = new FieldBookExcelExporter(jTableObservations, fileTemplate, trialFile, study, myWorkbook, selectedConstants);

        excelExporter.setStudyConditionsTable(this.jTableStudyConditions);
        excelExporter.setTrialConditionsTable(this.jTableTrialConditions);
        excelExporter.setConstantsTable(this.jTableConstants);
        excelExporter.exportToExcel(triallOption, trialStart, trialEnd, trialSelected);
    }

    private void jButtonCSVTraitsExport1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCSVTraitsExport1ActionPerformed
        if (!iniciaExportWizard2()) {
            return;
        }
}//GEN-LAST:event_jButtonCSVTraitsExport1ActionPerformed

    private void exportToFieldlog() {
        FieldbookCSVExporter.exportToFieldlog(jTableObservations, trialFile, csv, triallOption, trialStart, trialEnd, trialSelected);
    }

    private void jButtonCSVTraitsImport1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCSVTraitsImport1ActionPerformed
        //launchImportWizard();
        if (!launchImportWizard()) {
            return;
        }
}//GEN-LAST:event_jButtonCSVTraitsImport1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        excludeColumns();
        //fillMeasurementsData();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        includeColumns();
        // fillMeasurementsData();

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

    private void jButtonSyncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSyncActionPerformed
        fillObservationsData();

        DialogUtil.displayInfo(StudyEditorTopComponent.class, "StudyEditorTopComponent.datasyncronized");

        this.jTabbedPane1.setSelectedIndex(7);

    }//GEN-LAST:event_jButtonSyncActionPerformed

    private void jButtonSaveToExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveToExcelActionPerformed
        if (jTextTrialName.getText().trim().isEmpty()) {
            DialogUtil.displayError("Please fill Trial Name");
            jTextTrialName.requestFocus();
            return;
        }
        if (!studyAlreadyExists && trialNameAlreadyExists(jTextTrialName.getText().trim())) {
            String message = NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.trialNameAlreadyExists");
            DialogUtil.displayError(message);
            return;
        }

        String studyName = jTextFieldStudy.getText();
        FieldbookCSVUtil fieldbookCSVUtil = new FieldbookCSVUtil(jTableObservations, studyName);
        fieldbookCSVUtil.saveToCsv();
        final ProgressHandle handle = ProgressHandleFactory.createHandle("Saving fieldbook... ");
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
                    DialogUtil.displayInfo("Fieldbook was successfully saved.");
                    RefreshBrowserHelper.refreshStudyBrowser();

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
    }//GEN-LAST:event_jButtonSaveToExcelActionPerformed

    private void doSaveWorkbook() {
        WorkbookSavingHelper.saveFieldbook(this);
        disableTraitsSelection();
        jTextTrialName.setEnabled(false);
    }

    private void jButtonRefreshDesignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshDesignActionPerformed
        int instances = this.jTableDesign.getRowCount();
        for (int i = 0; i < instances; i++) {
            try {
                if (jTableDesign.getValueAt(i, 2).toString().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "You must fill all fields", "IBFIELDBOOK", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (jTableDesign.getValueAt(i, 3).toString().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "You must fill all fields", "IBFIELDBOOK", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (jTableDesign.getValueAt(i, 4).toString().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "You must fill all fields", "IBFIELDBOOK", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "You must fill all fields", "IBFIELDBOOK", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        int opcion = JOptionPane.showConfirmDialog(null, "Do you want to modify the design? All Measurements will be modified", "Caution!", JOptionPane.YES_NO_OPTION);
        if (opcion == 0) {
            final ProgressHandle handle = ProgressHandleFactory.createHandle("Generating design... ");
            handle.start();
            (new SwingWorker<String, Object>() {

                @Override
                protected String doInBackground() throws Exception {
                    //fillMeasurementsData();
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
                        // close the progress handler
                        handle.finish();
                    }
                }
            }).execute();

        }


    }//GEN-LAST:event_jButtonRefreshDesignActionPerformed

    private void btnPrintLabelsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintLabelsActionPerformed
        printLabels();
    }//GEN-LAST:event_btnPrintLabelsActionPerformed

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

                System.out.println("NO CAMBIO VALOR \n");
                return;
            }


            if (evt.getNewValue() == null) {

                Object valor = this.jTableStudyConditions.getValueAt(this.fila, STUDY_VALUE_COL).toString();
                if (valor.equals("")) {
                    return;
                }
                System.out.println("FILA: " + fila);
                System.out.println("Valor: " + valor + "\n");

                //Object condition = this.jTableStudyConditions.getValueAt(this.fila, 0).toString().toUpperCase();
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


            }


        } catch (NullPointerException error) {
        }
    }//GEN-LAST:event_jTableStudyConditionsPropertyChange

    private void importFromCSV() {
        FileFilter[] filtros = new FileFilter[10];
        filtros = selectorArchivo.getChoosableFileFilters();
        for (int i = 0; i < filtros.length; i++) {
            FileFilter filtro = filtros[i];
            selectorArchivo.removeChoosableFileFilter(filtro);
        }
        File archivoNulo = new File("");
        selectorArchivo.setSelectedFile(archivoNulo);
        selectorArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        selectorArchivo.addChoosableFileFilter(new CSVFiltro());
        int resultado = selectorArchivo.showOpenDialog(null);
        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }
        if (!csv.isValid(selectorArchivo.getSelectedFile())) {
            DialogUtil.displayError("The file is invalid");
            return;
        }


        csv.readDATAnew(selectorArchivo.getSelectedFile());
    }

    public void fillDesign() {
        DesignTableModel modeloTabla = new DesignTableModel();
        jTableDesign.setModel(modeloTabla);
        modeloTabla = (DesignTableModel) this.jTableDesign.getModel();
        jTableDesign.setRowSorter(null);

        int instances = Integer.parseInt(jLabelInstances.getText());

        Integer renglon = 0;
        for (int j = 0; j < instances; j++) {
            DesignBean designBean = new DesignBean();
            designBean.setTrialNumber(j + 1);
            modeloTabla.addDesignBean(designBean);
            renglon++;
        }
        //tcr.setHorizontalAlignment(SwingConstants.CENTER);

        if (this.jTableDesign.getRowCount() > 0) {
            this.jTableDesign.setRowSelectionInterval(0, 0);


        }

        int square = (int) Math.sqrt(Integer.parseInt(this.jTextFieldEntries.getText()));
        boolean conLattice = false;
        boolean conAlpha = false;

        boolean hayFactores = !myWorkbook.getOtherFactors().isEmpty();

        if (!hayFactores) {
            conAlpha = true;
        }

        int numEntries = jTableEntries.getRowCount();
        if (org.cimmyt.cril.ibwb.commongui.MathUtils.isPrime(numEntries)) {
            conAlpha = false;
        }

        if (Math.pow(square, 2) == Integer.parseInt(this.jTextFieldEntries.getText())) {
            conLattice = true;
        }

        conAlpha = designsUtils.alphaIsValid(numEntries);

        String inicio = designsUtils.assignMainCellEditor(conAlpha, conLattice);
        for (int j = 0; j < instances; j++) {
            this.jTableDesign.setValueAt(inicio, j, 1);
            this.jTableDesign.setValueAt(null, j, 2);
            this.jTableDesign.setValueAt(null, j, 3);
            this.jTableDesign.setValueAt(null, j, 4);
            this.jTableDesign.setValueAt(null, j, 5);
        }

        SpinnerModel modeloDesign = jSpinnerTrial.getModel();
        jSpinnerTrialStudy.setModel(modeloDesign);

        if (conAlpha) {
            int selected = designsUtils.assignCellEditorAlpha(numEntries);
            designsUtils.generateBlocksSize(selected);
            designsUtils.assignCellEditorBlockSize();
        } else {
            checkTable();
        }

        sorterDesign = new TableRowSorter<TableModel>(modeloTabla);
        this.jTableDesign.setRowSorter(sorterDesign);
    }

    private void checkTable() {
        if (this.jTableDesign.getCellEditor() != null) {
            jTableDesign.getCellEditor().stopCellEditing();
        }

        int colEditing = 1;
        this.fila = 0;

        try {
            designsUtils.checkDesignTable(colEditing, fila, rbtnUseSameDesign.isSelected());
        } catch (NullPointerException error) {
            System.out.println("ERROR EN TRIAL VISUAL PANEL 9: " + error);
        }
    }

    private void exportToR() {
        FieldbookRExport.exportToR(jTableObservations, trialFile, csv, triallOption, trialStart, trialEnd, trialSelected);
    }

    private void jMenuItemUnSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUnSelectActionPerformed
}//GEN-LAST:event_jMenuItemUnSelectActionPerformed

    private void jMenuItemUnselectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUnselectAllActionPerformed
}//GEN-LAST:event_jMenuItemUnselectAllActionPerformed

    private void jMenuItemSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSelectActionPerformed
    }//GEN-LAST:event_jMenuItemSelectActionPerformed

    private void jMenuItemSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSelectAllActionPerformed
}//GEN-LAST:event_jMenuItemSelectAllActionPerformed

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

    private void jTextTrialNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextTrialNameKeyReleased
        studyInfo.setStudy(jTextTrialName.getText());
        //jButtonSaveToExcel.setEnabled(!jTextTrialName.getText().trim().isEmpty());
    }//GEN-LAST:event_jTextTrialNameKeyReleased

    private void jButtonCopyGIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCopyGIDActionPerformed

        GermplasmEntriesTableModel entriesTableModel = (GermplasmEntriesTableModel) this.jTableEntries.getModel();
        int colGID = entriesTableModel.findColumn("GID");
        if (colGID > 0) {


            String str = "GID\n";


            for (int i = 0; i < entriesTableModel.getRowCount(); i++) {
                str = str + entriesTableModel.getValueAt(i, colGID).toString() + "\n";
            }


            StringSelection ss = new StringSelection(str);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

            DialogUtil.display("Data copied");

        } else {
            DialogUtil.displayError("There is not GID column");
        }



    }//GEN-LAST:event_jButtonCopyGIDActionPerformed

    @Override
    public void componentOpened() {
    }

    @Override
    public void componentClosed() {
    }

    void writeProperties(java.util.Properties p) {
        p.setProperty("version", "1.0");
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
    }

    private void assignCellEditorTrialCndition() {
        TrialConditionsRowEditor trialConditionsRowEditor = new TrialConditionsRowEditor(jTableTrialConditions);
        TableColumn valueColumn = this.jTableTrialConditions.getColumnModel().getColumn(5);
        valueColumn.setCellEditor(trialConditionsRowEditor);

        TableColumn columnValue = jTableTrialConditions.getColumnModel().getColumn(5);
        DefaultTableCellRenderer valueCellRenderer = new DefaultTableCellRenderer();
        valueCellRenderer.setToolTipText("Fill this value");
        valueCellRenderer.setBackground(Color.YELLOW);
        columnValue.setCellRenderer(valueCellRenderer);
    }

    private void assignCellEditorStudyConditions() {
        ConditionsRowEditor trialConditionsRowEditor = new ConditionsRowEditor(jTableStudyConditions);
        TableColumn valueColumn = this.jTableStudyConditions.getColumnModel().getColumn(4);
        valueColumn.setCellEditor(trialConditionsRowEditor);

        TableColumn columnValue = jTableStudyConditions.getColumnModel().getColumn(4);
        DefaultTableCellRenderer valueCellRenderer = new DefaultTableCellRenderer();
        valueCellRenderer.setToolTipText("Fill this value");
        valueCellRenderer.setBackground(Color.YELLOW);
        columnValue.setCellRenderer(valueCellRenderer);
    }

    public void assignDesignModel(DesignTableModel designTableModel) {
        DesignTableModel modeloTabla = new DesignTableModel();
        jTableDesign.setModel(modeloTabla);
        modeloTabla = (DesignTableModel) this.jTableDesign.getModel();
        jTableDesign.setRowSorter(null);
        for (DesignBean designBean : designTableModel.getDesignList()) {
            modeloTabla.addDesignBean(designBean);
        }

        int square = (int) Math.sqrt(Integer.parseInt(this.jTextFieldEntries.getText()));
        boolean conLattice = false;
        boolean conAlpha = false;

        boolean hayFactores = !myWorkbook.getOtherFactors().isEmpty();

        if (!hayFactores) {
            conAlpha = true;
        }

        int numEntries = jTableEntries.getRowCount();
        if (org.cimmyt.cril.ibwb.commongui.MathUtils.isPrime(numEntries)) {
            conAlpha = false;
        }

        if (Math.pow(square, 2) == Integer.parseInt(this.jTextFieldEntries.getText())) {
            conLattice = true;
        }


        if (designsUtils.alphaIsValid(numEntries)) {

            conAlpha = true;
        } else {
            conAlpha = false;
        }



        designsUtils.assignMainCellEditor(conAlpha, conLattice);

        SpinnerModel modeloDesign = jSpinnerTrial.getModel();
        jSpinnerTrialStudy.setModel(modeloDesign);

        if (conAlpha) {
            int selected = designsUtils.assignCellEditorAlpha(numEntries);
            designsUtils.generateBlocksSize(selected);
            //designsUtils.assignCellEditorBlockSize(designsUtils.getRep2(),designsUtils.getRep3(),designsUtils.getRep4());

            designsUtils.assignCellEditorBlockSize();
        } else {
            checkTable();
        }

        sorterDesign = new TableRowSorter<TableModel>(modeloTabla);
        this.jTableDesign.setRowSorter(sorterDesign);
        deshabilitaSorters();

    }

    private void MSG_NotImplementedYet() {
        DialogUtil.displayInfo("Not yet implemented");
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

        DesignTableModel designTableModel = (DesignTableModel) jTableDesign.getModel();

        int trials = 0;
        String rep = "";
        String blockSize = "";
        String blocksPerRep = "";
        Integer entries = 0;
        try {
            //trials = this.jTableDesign.getRowCount();
            trials = designTableModel.getRowCount();
        } catch (NullPointerException ex) {
            trials = 0;
        }
        // tmsanchez
        // this because there is not other factors!
        if (this.myWorkbook.getOtherFactors().isEmpty()) {
            combinations = 1;
        }

        for (int i = 0; i < trials; i++) {

            DesignBean bean = designTableModel.getDesignBean(i);
            String disenio = bean.getDesign();
            rep = bean.getReplications().toString();
            blockSize = bean.getBlockSize().toString();
            blocksPerRep = bean.getBlocksPerReplicate().toString();
//            String disenio = jTableDesign.getValueAt(i, 1).toString();            
//            rep = this.jTableDesign.getValueAt(i, 2).toString();
//            blockSize = this.jTableDesign.getValueAt(i, 3).toString();
//            blocksPerRep = this.jTableDesign.getValueAt(i, 4).toString();
            entries = Integer.parseInt(blockSize) * (Integer.parseInt(blocksPerRep));
            if (disenio.startsWith(DesignsClass.USER_DEFINED_DESIGN)) {
                if (bean.getUserDefinedDesign() == null) {
                    DialogUtil.displayError("Please specify a filename for the design.");
                } else {
                    disenios.readUserDefinedDesign(i + 1, bean.getUserDefinedDesign(), tableModel, this.jTableEntries);
                }
            } else if (disenio.equals(DesignsClass.ALFA_DESIGN)) {
                if (OSUtils.isMacOS()) {
                    disenios.runR_alpha(i + 1, entries, Integer.parseInt(rep), Integer.parseInt(blockSize));
                    disenios.readAlphaDesign(i + 1, "alpha", tableModel, jTableEntries);
                    disenios.deleteWDforMac();
                } else {
                    disenios.runR_alphaWindows(entries, Integer.parseInt(rep), Integer.parseInt(blockSize));

                    if (disenios.existeArchivo("alpha")) {
                        disenios.readAlphaDesign(i + 1, "alpha", tableModel, this.jTableEntries);
                        disenios.deleteWD(new File("C:" + File.separator + "R"));
                    } else {
                        disenios.runR_alphaWindowsExtra(entries, Integer.parseInt(rep), Integer.parseInt(blockSize), 155);
                        disenios.readAlphaDesign(i + 1, "alpha", tableModel, this.jTableEntries);
                        disenios.deleteWD(new File("C:" + File.separator + "R"));
                    }

                }
            } else if (disenio.equals(DesignsClass.LATICE_DESIGN)) {
                if (OSUtils.isMacOS()) {
                    disenios.runR_lattice(i + 1, entries, Integer.parseInt(rep), Integer.parseInt(blockSize));
                    disenios.readLatticeDesign(i + 1, "lattice" + (i + 1), tableModel, this.jTableEntries, otherFactors, factorsDesignCad, combinations);
                    disenios.deleteWDforMac();
                } else {
                    disenios.runR_latticeWindows(entries, Integer.parseInt(rep), Integer.parseInt(blockSize));
                    disenios.readLatticeDesign(i + 1, "lattice", tableModel, this.jTableEntries, otherFactors, factorsDesignCad, combinations);
                    disenios.deleteWD(new File("C:" + File.separator + "R"));
                }
            } else if (disenio.equals(DesignsClass.UNREPLICATED_DESIGH_WITH_RANDOMIZATION)) {
                generateUnreplicatedDesignWithRandomization(i + 1, tableModel, otherFactors, factorsDesignCad, combinations);
            } else if (disenio.equals(DesignsClass.UNREPLICATED_DESIGH_WITHOUT_RANDOMIZATION)) {
                generateUnreplicatedDesignWithoutRandomization(i + 1, tableModel, otherFactors, factorsDesignCad, combinations);
            } else {
                //rep = this.jTableDesign.getValueAt(i, 2).toString();
                rep = bean.getReplications().toString();
                generateRandomizeCompleteBlock(Integer.parseInt(rep), i + 1, tableModel, otherFactors, factorsDesignCad, combinations);
            }
        }
        int entriesTot = this.jTableEntries.getRowCount();
        this.jTextFieldEntries.setText(String.valueOf(entriesTot));
        SpinnerNumberModel mod1 = new SpinnerNumberModel(1, 1, trials, 1);
        SpinnerNumberModel mod3 = new SpinnerNumberModel(1, 1, entriesTot, 1);
        this.jSpinnerTrial.setModel(mod1);
        this.jSpinnerEntry.setModel(mod3);
        ajustaColumnsTable(this.jTableObservations, 2);
        for (int i = 0; i < jTableObservations.getColumnCount(); i++) {
            sorterMeasurements.setSortable(i, false);
        }

        this.jTabbedPane1.setEnabledAt(7, true);
        this.jTabbedPane1.setSelectedIndex(7);
        changeCursorWaitStatus(false);
        ObservationTableTooltips.assignTooltips(jTableObservations);
        // asignaClipboard();
    }

    private void generateUnreplicatedDesignWithoutRandomization(int trial, ObservationsTableModel model, ArrayList<String> otherFactors, String[][] factorsDesignCad, int totalRep) {
        TableColumnModel tcm = this.jTableEntries.getColumnModel();
        GermplasmEntriesTableModel entriesTableModel = (GermplasmEntriesTableModel) this.jTableEntries.getModel();
        int total = Integer.parseInt(this.jTextFieldEntries.getText());
        for (int i = 0; i < total; i++) {
            for (int j = 0; j < totalRep; j++) {
                Object[] rowToAdd = new Object[model.getColumnCount()];
                rowToAdd[model.getHeaderIndex(ObservationsTableModel.TRIAL)] = trial;
                rowToAdd[model.getHeaderIndex(ObservationsTableModel.REPLICATION)] = 1;
                rowToAdd[model.getHeaderIndex(ObservationsTableModel.BLOCK)] = 1;
                rowToAdd[model.getHeaderIndex(ObservationsTableModel.PLOT)] = i + 1;
                //rowToAdd[model.getHeaderIndex(ObservationsTableModel.ENTRY)] = i + 1;
                int entriesColIndex = 0;
                for (Factor factor : entriesTableModel.getFactorHeaders()) {
                    String columnHeader = Workbook.getStringWithOutBlanks(factor.getProperty() + factor.getScale());
                    rowToAdd[model.getHeaderIndex(columnHeader)] = entriesTableModel.getValueAt(i, entriesColIndex);
                    entriesColIndex++;
                }
                // tmsanchez
                if (otherFactors != null) {
                    for (int k = 0; k < otherFactors.size(); k++) {
                        //rowToAdd[findColumn(otherFactors.get(k), model)] = factorsDesignCad[k][j];
                    }
                }
                model.addRow(rowToAdd);
            }
        }
    }

    public int findColumn(String name, DefaultTableModel model) {
        int colEntry = 0;

        colEntry = model.findColumn(name);

        return colEntry;

    }

    private void generateUnreplicatedDesignWithRandomization(int trial, ObservationsTableModel model, ArrayList<String> otherFactors, String[][] factorsDesignCad, int totalRep) {
        GermplasmEntriesTableModel entriesTableModel = (GermplasmEntriesTableModel) this.jTableEntries.getModel();
        int total = Integer.parseInt(this.jTextFieldEntries.getText());
        int vector[] = randomize(total);
        for (int i = 0; i < total; i++) {
            for (int j = 0; j < totalRep; j++) {
                Object[] rowToAdd = new Object[model.getColumnCount()];
                rowToAdd[model.getHeaderIndex(ObservationsTableModel.TRIAL)] = trial;
                rowToAdd[model.getHeaderIndex(ObservationsTableModel.REPLICATION)] = 1;
                rowToAdd[model.getHeaderIndex(ObservationsTableModel.BLOCK)] = 1;
                rowToAdd[model.getHeaderIndex(ObservationsTableModel.PLOT)] = i + 1;
                //rowToAdd[model.getHeaderIndex(ObservationsTableModel.ENTRY)] = i + 1;

                int entriesColIndex = 0;
                for (Factor factor : entriesTableModel.getFactorHeaders()) {
                    String columnHeader = Workbook.getStringWithOutBlanks(factor.getProperty() + factor.getScale());
                    rowToAdd[model.getHeaderIndex(columnHeader)] = entriesTableModel.getValueAt(vector[i], entriesColIndex);
                    entriesColIndex++;
                }

                // tmsanchez
                if (otherFactors != null) {
                    for (int k = 0; k < otherFactors.size(); k++) {
                        //rowToAdd[findColumn(otherFactors.get(k), model)] = factorsDesignCad[k][j];
                    }
                }
                model.addRow(rowToAdd);
            }
        }
    }

    private void generateRandomizeCompleteBlock(int rep, int trial, ObservationsTableModel model, ArrayList<String> otherFactors, String[][] factorsDesignCad, int totalRep) {
        GermplasmEntriesTableModel entriesTableModel = (GermplasmEntriesTableModel) this.jTableEntries.getModel();
        int total = Integer.parseInt(this.jTextFieldEntries.getText());
        int plot = 0;
        int repet = 0;
        for (int j = 0; j < rep; j++) {
            repet++;
            int vector[] = randomize(total);

            for (int i = 0; i < total; i++) {

                plot++;

                for (int m = 0; m < totalRep; m++) {
                    Object[] rowToAdd = new Object[model.getColumnCount()];
                    rowToAdd[model.getHeaderIndex(ObservationsTableModel.TRIAL)] = trial;
                    rowToAdd[model.getHeaderIndex(ObservationsTableModel.REPLICATION)] = repet;
                    rowToAdd[model.getHeaderIndex(ObservationsTableModel.BLOCK)] = 1;
                    rowToAdd[model.getHeaderIndex(ObservationsTableModel.PLOT)] = plot;


                    int entriesColIndex = 0;
                    for (Factor factor : entriesTableModel.getFactorHeaders()) {
                        String columnHeader = Workbook.getStringWithOutBlanks(factor.getProperty() + factor.getScale());
                        rowToAdd[model.getHeaderIndex(columnHeader)] = entriesTableModel.getValueAt(vector[i], entriesColIndex);
                        entriesColIndex++;
                    }

                    // tmsanchez
                    if (otherFactors != null) {
                        for (int k = 0; k < otherFactors.size(); k++) {
                            //rowToAdd[findColumn(otherFactors.get(k), model)] = factorsDesignCad[k][j];
                        }
                    }
                    model.addRow(rowToAdd);
                }
            }
        }
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

    public void printLabels() {
        TraitsReportHelper.printTraitsReport(jTableObservations);
    }

    private void quitaCellEditors() {
        JTextField jtf = new JTextField();
        TableColumn valueColumn = this.jTableDesign.getColumnModel().getColumn(2);
        valueColumn.setCellEditor(new DefaultCellEditor(jtf));
        valueColumn = this.jTableDesign.getColumnModel().getColumn(3);
        valueColumn.setCellEditor(new DefaultCellEditor(jtf));
        valueColumn = this.jTableDesign.getColumnModel().getColumn(4);
        valueColumn.setCellEditor(new DefaultCellEditor(jtf));
    }

    private void deshabilitaSorters() {
        for (int i = 0; i < 5; i++) {
            sorterTrialInformation.setSortable(i, false);
            sorterConstants.setSortable(i, false);
            sorterDesign.setSortable(i, false);
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

    private boolean hayGYcolumn() {

        TableColumnModel tableColumns = this.jTableObservations.getColumnModel();

        for (int col = 0; col < tableColumns.getColumnCount(); col++) {
            TableColumn tableColumn = tableColumns.getColumn(col);
            if (tableColumn.getHeaderValue().toString().equals("GY")) {
                return true;
            }
        }
        return false;
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

    private boolean launchImportWizard() {
        ObservationsTableModel observationsTableModel = (ObservationsTableModel) jTableObservations.getModel();
        csv = new CSVOziel(this.jTableObservations, new JList());
        int instanceCounter = observationsTableModel.getTrialCounter();
        ImportData importData = new ImportData(jTableObservations, csv, instanceCounter);
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
                    importData.importFromExcel(trialImportFile);

                    break;
                case 1:
                    importData.importFromFieldroid(trialImportFile);
                    break;

                case 2:
                    importData.importFromCrossInfo(trialImportFile);
                    break;
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean iniciaExportWizard2() {

        WizardDescriptor.Iterator iterator = new exportWizardIterator();
        WizardDescriptor wizardDescriptor = new WizardDescriptor(iterator);
        wizardDescriptor.setTitleFormat(new MessageFormat("{0} ({1})"));
        wizardDescriptor.setTitle("Save Data");
        DialogUtil.createDialog(wizardDescriptor);

        boolean cancelled = wizardDescriptor.getValue() != WizardDescriptor.FINISH_OPTION;
        if (!cancelled) {

            switch (opcionExport) {
                case 0:
                    exportToFieldlog();

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
        SpinnerNumberModel mod = (SpinnerNumberModel) this.jSpinnerTrial.getModel();
        Comparable num = mod.getMaximum();
        num.toString();
        return Integer.parseInt(num.toString());
    }

    public void loadDataFromCsv() {
        String studyName = jTextFieldStudy.getText();
        FieldbookCSVUtil fieldbookCSVUtil = new FieldbookCSVUtil(jTableObservations, studyName);
        fieldbookCSVUtil.populateFiedlbook(jTableObservations, studyName);
        SpinnerNumberModel mod = (SpinnerNumberModel) this.jSpinnerTrial.getModel();
        mod.setMaximum(getMaxTrialsFromDb());
    }

    private int getMaxTrialsFromDb() {
        int maxTrials = 1;

        DefaultTableModel dtm = (DefaultTableModel) jTableObservations.getModel();
        int currentTrial = Integer.parseInt((String) dtm.getValueAt(0, 0));
        for (int row = 0; row < dtm.getRowCount(); row++) {
            String value = (String) dtm.getValueAt(0, 0);
            int trial = Integer.parseInt(value);
            if (trial != currentTrial) {
                maxTrials++;
                currentTrial = trial;
            }
        }
        return maxTrials;
    }

    public void assignStudyConditions(List<Condition> studyConditions) {
        StudyConditionsTableModel studyConditionsTableModel = new StudyConditionsTableModel(studyConditions);
        jTableStudyConditions.setModel(studyConditionsTableModel);
        assignCellEditorStudyConditions();
        deshabilitaSorters();
    }

    public void assignTrialConditions(List<Condition> trialConditions) {
        TrialConditionsTableModel tableModel = new TrialConditionsTableModel(trialConditions);
        this.jTableTrialConditions.setModel(tableModel);
        sorterTrialInformation = new TableRowSorter<TableModel>(tableModel);
        this.jTableTrialConditions.setRowSorter(sorterTrialInformation);
        assignCellEditorTrialCndition();
        deshabilitaSorters();
    }

    public void assignExperimentalConditions(List<Constant> constantList) {
        ExperimentalConditionsTableModel tableModel = new ExperimentalConditionsTableModel(constantList);
        jTableConstants.setModel(tableModel);
        sorterConstants = new TableRowSorter<TableModel>(tableModel);
        jTableConstants.setRowSorter(sorterConstants);

        TableColumn columnValue = jTableConstants.getColumnModel().getColumn(4);
        DefaultTableCellRenderer valueCellRenderer = new DefaultTableCellRenderer();
        valueCellRenderer.setToolTipText("Fill this value");
        valueCellRenderer.setBackground(Color.YELLOW);
        columnValue.setCellRenderer(valueCellRenderer);

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

    /**
     * Assigns
     */
    public void assignOtherTreatmentFactors(List<Factor> otherTreatmentFactors) {

        OtherTreatmentFactorsTableModel model = new OtherTreatmentFactorsTableModel(otherTreatmentFactors, false);
        jTableOtherFactors.setModel(model);
    }

    public void assignOtherTreatmentFactorLabels(List<FactorLabel> factorLabels) {

        TreatmentLabelsTableModel treatmentLabelsTableModel = new TreatmentLabelsTableModel();
        treatmentLabelsTableModel.setFactorLabels(factorLabels);
        jTableOtherFactorLabels.setModel(treatmentLabelsTableModel);
        TableColumn columnValue = jTableOtherFactorLabels.getColumnModel().getColumn(3);
        DefaultTableCellRenderer valueCellRenderer = new DefaultTableCellRenderer();
        valueCellRenderer.setToolTipText("Fill this value");
        valueCellRenderer.setBackground(Color.YELLOW);
        columnValue.setCellRenderer(valueCellRenderer);

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
        jButtonSelectTraits.setEnabled(false);
    }

    public void loadDataFromDB() {
        jTextTrialName.setEnabled(false);
        jButtonSaveToExcel.setEnabled(true);
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
        int trials = getTotalTrialsFromObservations();
        SpinnerNumberModel mod1 = new SpinnerNumberModel(1, 1, trials, 1);
        SpinnerNumberModel mod3 = new SpinnerNumberModel(1, 1, entriesTot, 1);
        this.jSpinnerTrial.setModel(mod1);
        this.jSpinnerEntry.setModel(mod3);
        ajustaColumnsTable(this.jTableObservations, 2);
        for (int i = 0; i < jTableObservations.getColumnCount(); i++) {
            sorterMeasurements.setSortable(i, false);
        }
        this.jTabbedPane1.setEnabledAt(7, true);
        this.jTabbedPane1.setSelectedIndex(7);
        changeCursorWaitStatus(false);

        ObservationTableTooltips.assignTooltips(jTableObservations);
    }

    private int getTotalTrialsFromObservations() {
        int totalTrials = 0;
        ObservationsTableModel tableModel = (ObservationsTableModel) jTableObservations.getModel();
        String currentTrial = "-999";

        for (int row = 0; row < tableModel.getRowCount(); row++) {
            String trialInRow = tableModel.getValueAt(row, 0).toString();

            if (!trialInRow.equals(currentTrial)) {
                totalTrials++;
                currentTrial = trialInRow;
            }
        }
        return totalTrials;
    }

    public void assignTraits(List<Variate> unselectedVariates, List<Variate> selectedVariates) {
        doubleListPanel.setSourceList(unselectedVariates);
        doubleListPanel.setTargetList(selectedVariates);
        doubleListPanel.fillListItems();
    }

    private class TraitsDropTargetCommand implements DropTargetCommand {

        @Override
        public void onDropExecute(String droppedText) {
            System.out.println(droppedText);
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

    public JTextField getjTextTrialName() {
        return jTextTrialName;
    }

    public void setjTextTrialName(JTextField jTextTrialName) {
        this.jTextTrialName = jTextTrialName;
    }

    private void createBallonTips() {
        String msg = NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.jTextTrialName.Tip");
        tipSavingTrial = new BalloonTip(jTextTrialName, msg);
        ToolTipUtils.balloonToToolTip(tipSavingTrial, 500, 3000);
    }

    @Override
    public boolean canClose() {
        boolean result = true;
        String closeTitle = NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.closeFieldbook.title");
        String closeQuestion = NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.closeFieldbook.question");
        String provideTrialName = NbBundle.getMessage(StudyEditorTopComponent.class, "StudyEditorTopComponent.closeFieldbook.provideTrialName");
        boolean saveFieldbook = DialogUtil.displayConfirmation(closeQuestion, closeTitle, NotifyDescriptor.YES_NO_CANCEL_OPTION);
        if (saveFieldbook) {
            if (jTextTrialName.getText().trim().isEmpty()) {
                DialogUtil.displayError(provideTrialName);
                result = false;
            } else {
                jButtonSaveToExcelActionPerformed(null);
            }

        }
        return result;
    }

    public JTable getjTableConstants() {
        return jTableConstants;
    }

    public void setjTableConstants(JTable jTableConstants) {
        this.jTableConstants = jTableConstants;
    }

    private boolean trialNameAlreadyExists(String trialName) {
        boolean result = false;


        if (AppServicesProxy.getDefault().appServices().getStudyByName(trialName) != null) {
            result = true;
        }

        return result;

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel JPanelData;
    private javax.swing.JButton btnPrintLabels;
    private javax.swing.ButtonGroup buttonGroupDesign;
    private javax.swing.ButtonGroup buttonGroupExpConditions;
    private javax.swing.ButtonGroup buttonGroupMeasurements;
    private javax.swing.ButtonGroup buttonGroupTrInformation;
    private javax.swing.JButton jButtonCSVTraitsExport1;
    private javax.swing.JButton jButtonCSVTraitsImport1;
    private javax.swing.JButton jButtonCopyGID;
    public static javax.swing.JButton jButtonRefreshDesign;
    private javax.swing.JButton jButtonSaveToExcel;
    public static javax.swing.JButton jButtonSelectTraits;
    public static javax.swing.JButton jButtonSync;
    public javax.swing.JComboBox jComboBoxStudyType;
    public javax.swing.JTextField jDateChooserEnd;
    public javax.swing.JTextField jDateChooserStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JLabel jLabelEntries;
    public javax.swing.JLabel jLabelInstances;
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
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
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
    private javax.swing.JRadioButton jRadioButtonAllExpCondition;
    private javax.swing.JRadioButton jRadioButtonAllTrials;
    private javax.swing.JRadioButton jRadioButtonAllTrials2;
    private javax.swing.JRadioButton jRadioButtonFilterEntry;
    private javax.swing.JRadioButton jRadioButtonFilterExpConditions;
    private javax.swing.JRadioButton jRadioButtonFilterTrial;
    private javax.swing.JRadioButton jRadioButtonFilterTrialInfo;
    private javax.swing.JRadioButton jRadioButtonFilterTrialStudy;
    private javax.swing.JRadioButton jRadioButtonViewAllTrialStudy;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSpinner jSpinnerEntry;
    private javax.swing.JSpinner jSpinnerExpConditions;
    public javax.swing.JSpinner jSpinnerTrial;
    public javax.swing.JSpinner jSpinnerTrialInformation;
    public javax.swing.JSpinner jSpinnerTrialStudy;
    public javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTable jTableConstants;
    public javax.swing.JTable jTableDesign;
    public javax.swing.JTable jTableEntries;
    public javax.swing.JTable jTableObservations;
    public javax.swing.JTable jTableOtherFactorLabels;
    private javax.swing.JTable jTableOtherFactors;
    public javax.swing.JTable jTableStudyConditions;
    public javax.swing.JTable jTableTrialConditions;
    public javax.swing.JTextField jTextFieldDescription;
    public javax.swing.JTextField jTextFieldDescriptionSelected;
    public javax.swing.JTextField jTextFieldEntries;
    public javax.swing.JTextField jTextFieldObjective;
    public javax.swing.JTextField jTextFieldPMKey;
    public javax.swing.JTextField jTextFieldStudy;
    public javax.swing.JTextField jTextFieldTitle;
    private javax.swing.JTextField jTextTrialName;
    private javax.swing.JPanel pnlSelectList;
    private javax.swing.JRadioButton rbtnIndividualDesign;
    private javax.swing.JRadioButton rbtnUseSameDesign;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}