package ibfb.germplasmlist.core;

import ibfb.domain.core.Factor;
import ibfb.domain.core.GermplasmList;
import ibfb.domain.core.ListOfEntries;
import ibfb.domain.core.Workbook;
import ibfb.germplasmlist.filters.ExcelFiltro;
import ibfb.germplasmlist.models.GermplasmEntriesTableModel;
import ibfb.germplasmlist.models.GermplasmTransferHandlerSelection;
import ibfb.germplasmlist.models.RemoveGermplasmTransferHandler;
import ibfb.germplasmlist.renders.CrossGridDecorator;
import ibfb.lists.core.SelectListDialog;
import ibfb.settings.core.FieldbookSettings;
import ibfb.studyexplorer.explorer.listNames.ListDataWindowTopComponent;
import ibfb.workbook.api.GermplasmAssigmentTool;
import ibfb.workbook.api.GermplasmListReader;
import ibfb.workbook.core.GermplasmAssigmentToolImpl;
import ibfb.workbook.core.GermplasmListReaderImpl;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.*;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.ConvertUtils;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.cimmyt.cril.ibwb.commongui.FileUtils;
import org.cimmyt.cril.ibwb.domain.*;
import org.cimmyt.cril.ibwb.domain.constants.TypeDB;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.Exceptions;
import org.openide.util.Mutex;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

@ConvertAsProperties(dtd = "-//ibfb.germplasmlist.core//nurseryManager//EN",
autostore = false)
@TopComponent.Description(preferredID = "nurseryManagerTopComponent",
iconBase = "ibfb/germplasmlist/images/fm.png",
persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
//@ActionID(category = "Window", id = "ibfb.germplasmlist.core.nurseryManagerTopComponent")
//@ActionReference(path = "Menu/Window" /*
// * , position = 333
// */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_nurseryManagerAction",
preferredID = "nurseryManagerTopComponent")
@Messages({
    "CTL_nurseryManagerAction=CROSSING MANAGER",
    "CTL_nurseryManagerTopComponent=CROSSING MANAGER",
    "HINT_nurseryManagerTopComponent=CROSSING MANAGER"
})
public final class nurseryManagerTopComponent extends TopComponent {

    private static Logger log = Logger.getLogger(nurseryManagerTopComponent.class);
    private static final int CONVENTION_CIMMYT_WHEAT = 0;
    private static final int CONVENTION_CIMMYT_MAIZE = 1;
    private static final int CONVENTION_OTHER_CROPS = 2;
    public static final int DEFAULT_CROSS = 101; // SINGLE PLANT
    private JFileChooser selectorArchivo = new JFileChooser();
    private Workbook myWorkbook;
    private Desktop desktop = null;
    private Desktop.Action action = null;
    private File archivo = null;
    private List<ListOfEntries> selectedEntries;
    List<List<Object>> rowSelectedList;
    GermplasmAssigmentTool gat = new GermplasmAssigmentToolImpl();
    List<List<Object>> rowList;
    List<List<Object>> rowListDB;
    List<List<Object>> rowListExcel;
    List<List<Object>> rowListExcelSelection;
    List<List<Object>> toAdd;
    List<List<Object>> toRemove;
    private boolean esPrimeraVez = true;
    private List<Factor> factores;
    ImageIcon iconAdd = new ImageIcon("ibfb/germplasmlist/images/add.png");
    ImageIcon iconCancel = new ImageIcon("ibfb/germplasmlist/images/cancel.png");
    private GermplasmTransferHandlerSelection germplasmTransferHandler;
    private RemoveGermplasmTransferHandler removeGermplasmTransferHandler;
    private boolean isCrossScripFile = false;
    private boolean isSelectionScripFile = false;
    public int ENT = 0;
    public int CID = 1;
    public int SID = 2;
    public int FTID = 3;
    public int FOCC = 4;
    public int FENT = 5;
    public int MTID = 6;
    public int MOCC = 7;
    public int MENT = 8;
    public int MEGA = 9;
    public int CROSSTYPE = 10;
    public int TYPE = 11;
    private String[] headers = {"ENTRY", "BCID", "CROSS", "GID", "METHOD", "FDESIG", "FGID", "MDESIG", "MGID"};
    private String[] headersScript = {"ENTRY", "BCID", "FEMALE PEDIGREE", "MALE PEDIGREE", "FEMALE/MALE", "GID", "METHOD", "FTID", "FOCC", "FENTRY", "FDESIG", "FGID", "MTID", "MOCC", "MENTRY", "MDESIG", "MGID"};
    private String[] otherCropsheaders = {"ENTRY", "CROSS", "GID", "METHOD", "FDESIG", "FGID", "MDESIG", "MGID", "SOURCE"};
    private ArrayList<String> tempListCross;
    List<GermplasmSearch> listFemale;
    List<GermplasmSearch> listMale;
    /**
     * Methods in Combo box, used to retrieve selected method
     */
    private List<Methods> methodsInCombo;
    private Listnms recentList;

    public nurseryManagerTopComponent() {
        initComponents();
        setName(Bundle.CTL_nurseryManagerTopComponent());
        setToolTipText(Bundle.HINT_nurseryManagerTopComponent());
        toAdd = new ArrayList<List<Object>>();
        fillComboListNames();
        loadFactors();
        addListener();
        fillMethods();
        this.jButtonSaveCross.setEnabled(false);
        assignModels();

        // by default select other crops
        jComboBoxConvection.setSelectedIndex(CONVENTION_OTHER_CROPS);
        checkConvection();
    }

    private void assignModels() {
        GermplasmEntriesTableModel femaleModel = new GermplasmEntriesTableModel();
        GermplasmEntriesTableModel maleModel = new GermplasmEntriesTableModel();
        this.jTableEntriesExcelFemale.setModel(femaleModel);
        this.jTableEntriesDBFemale.setModel(femaleModel);
        this.jTableEntriesExcelMale.setModel(maleModel);
        this.jTableEntriesDBMale.setModel(maleModel);


    }

    private void fillComboListNames() {
        List<Listnms> germplasmList = AppServicesProxy.getDefault().appServices().getListnmsList();
        for (Listnms list : germplasmList) {
            cboGermplasmListFemale.addItem(list);
            cboGermplasmListMale.addItem(list);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPaneMale = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboGermplasmListMale = new javax.swing.JComboBox();
        jScrollEntriesDbMale = new javax.swing.JScrollPane();
        jTableEntriesDBMale = new javax.swing.JTable();
        btnRemoveMaleDb = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldSelectedFinalMale = new javax.swing.JTextField();
        jTextFieldListEntriesMale = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JToolBar.Separator();
        btnSearchMale = new javax.swing.JButton();
        btnRemoveMaleExcel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaPathMale = new javax.swing.JTextArea();
        jButtonBrowseExcelMale = new javax.swing.JButton();
        jScrollEntiresExcelMale = new javax.swing.JScrollPane();
        jTableEntriesExcelMale = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTabbedPaneFemale = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cboGermplasmListFemale = new javax.swing.JComboBox();
        jScrollEntriesDbFemale = new javax.swing.JScrollPane();
        jTableEntriesDBFemale = new javax.swing.JTable();
        btnRemoveFemaleDb = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jTextFieldSelectedEntriesFemale = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldListEntriesFemale = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        btnSearchFemaleList = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaPathFemale = new javax.swing.JTextArea();
        jButtonBrowseExcelFemale = new javax.swing.JButton();
        jScrollEntiresExcelFemale = new javax.swing.JScrollPane();
        jTableEntriesExcelFemale = new javax.swing.JTable();
        btnRemoveFemaleExcel = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButtonCross = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxConvection = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jComboBoxMethods = new javax.swing.JComboBox();
        jButtonLoadExcelScript = new javax.swing.JButton();
        lblCrossPrefix = new javax.swing.JLabel();
        jTextPrefix = new javax.swing.JTextField();
        lblStartNo = new javax.swing.JLabel();
        spnStartNo = new javax.swing.JSpinner();
        lblSuffix = new javax.swing.JLabel();
        jTextSuffix = new javax.swing.JTextField();
        jChkLeading = new javax.swing.JCheckBox();
        lblNumericFieldWidth = new javax.swing.JLabel();
        spnNumFieldWidth = new javax.swing.JSpinner();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableFinalList = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jlblListName = new javax.swing.JLabel();
        jTextFieldListName = new javax.swing.JTextField();
        jlblDescription = new javax.swing.JLabel();
        jTextFieldDescription = new javax.swing.JTextField();
        jlblEntries = new javax.swing.JLabel();
        jLabelEntries = new javax.swing.JLabel();
        jButtonSaveCross = new javax.swing.JButton();
        jDateChooserStart = new com.toedter.calendar.JDateChooser();
        jlblDate = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JToolBar.Separator();

        jTabbedPaneMale.setBackground(new java.awt.Color(0, 102, 255));
        jTabbedPaneMale.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jTabbedPaneMale.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 204))); // NOI18N
        jTabbedPaneMale.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneMaleStateChanged(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/database.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jLabel1.text")); // NOI18N

        cboGermplasmListMale.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT ONE..." }));
        cboGermplasmListMale.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboGermplasmListMaleItemStateChanged(evt);
            }
        });

        jScrollEntriesDbMale.setToolTipText(org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jScrollEntriesDbMale.toolTipText")); // NOI18N

        jTableEntriesDBMale.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableEntriesDBMale.setDragEnabled(true);
        jTableEntriesDBMale.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableEntriesDBMale.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableEntriesDBMalePropertyChange(evt);
            }
        });
        jScrollEntriesDbMale.setViewportView(jTableEntriesDBMale);

        btnRemoveMaleDb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/remove.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnRemoveMaleDb, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.btnRemoveMaleDb.text")); // NOI18N
        btnRemoveMaleDb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveMaleDbActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jLabel11.text")); // NOI18N

        jTextFieldSelectedFinalMale.setEditable(false);
        jTextFieldSelectedFinalMale.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldSelectedFinalMale.setText(org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jTextFieldSelectedFinalMale.text")); // NOI18N

        jTextFieldListEntriesMale.setEditable(false);
        jTextFieldListEntriesMale.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldListEntriesMale.setText(org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jTextFieldListEntriesMale.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jLabel7.text")); // NOI18N

        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldListEntriesMale, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldSelectedFinalMale)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(jLabel7)
                .addComponent(jTextFieldListEntriesMale, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel11)
                .addComponent(jTextFieldSelectedFinalMale, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(btnSearchMale, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.btnSearchMale.text")); // NOI18N
        btnSearchMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchMaleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboGermplasmListMale, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearchMale)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollEntriesDbMale, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnRemoveMaleDb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboGermplasmListMale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearchMale)))
                .addGap(18, 18, 18)
                .addComponent(jScrollEntriesDbMale, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRemoveMaleDb)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPaneMale.addTab(org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/excelFile.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jLabel2.text")); // NOI18N

        jTextAreaPathMale.setColumns(20);
        jTextAreaPathMale.setLineWrap(true);
        jTextAreaPathMale.setRows(5);
        jTextAreaPathMale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextAreaPathMaleMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextAreaPathMale);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonBrowseExcelMale, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jButtonBrowseExcelMale.text")); // NOI18N
        jButtonBrowseExcelMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBrowseExcelMaleActionPerformed(evt);
            }
        });

        jTableEntriesExcelMale.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableEntriesExcelMale.setDragEnabled(true);
        jTableEntriesExcelMale.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableEntriesExcelMale.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableEntriesExcelMalePropertyChange(evt);
            }
        });
        jScrollEntiresExcelMale.setViewportView(jTableEntriesExcelMale);

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout btnRemoveMaleExcelLayout = new javax.swing.GroupLayout(btnRemoveMaleExcel);
        btnRemoveMaleExcel.setLayout(btnRemoveMaleExcelLayout);
        btnRemoveMaleExcelLayout.setHorizontalGroup(
            btnRemoveMaleExcelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnRemoveMaleExcelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnRemoveMaleExcelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollEntiresExcelMale, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnRemoveMaleExcelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBrowseExcelMale, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1))
                .addContainerGap())
        );
        btnRemoveMaleExcelLayout.setVerticalGroup(
            btnRemoveMaleExcelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnRemoveMaleExcelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnRemoveMaleExcelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonBrowseExcelMale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollEntiresExcelMale, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1))
        );

        jTabbedPaneMale.addTab(org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.btnRemoveMaleExcel.TabConstraints.tabTitle"), btnRemoveMaleExcel); // NOI18N

        jTabbedPaneFemale.setBackground(new java.awt.Color(255, 102, 255));
        jTabbedPaneFemale.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jTabbedPaneFemale.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(102, 0, 102))); // NOI18N
        jTabbedPaneFemale.setMaximumSize(new java.awt.Dimension(32767, 300));
        jTabbedPaneFemale.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneFemaleStateChanged(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/database.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jLabel5.text")); // NOI18N

        cboGermplasmListFemale.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT ONE..." }));
        cboGermplasmListFemale.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboGermplasmListFemaleItemStateChanged(evt);
            }
        });
        cboGermplasmListFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGermplasmListFemaleActionPerformed(evt);
            }
        });

        jScrollEntriesDbFemale.setToolTipText(org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jScrollEntriesDbFemale.toolTipText")); // NOI18N

        jTableEntriesDBFemale.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableEntriesDBFemale.setDragEnabled(true);
        jTableEntriesDBFemale.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableEntriesDBFemale.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableEntriesDBFemalePropertyChange(evt);
            }
        });
        jScrollEntriesDbFemale.setViewportView(jTableEntriesDBFemale);

        btnRemoveFemaleDb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/remove.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnRemoveFemaleDb, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.btnRemoveFemaleDb.text")); // NOI18N
        btnRemoveFemaleDb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveFemaleDbActionPerformed(evt);
            }
        });

        jTextFieldSelectedEntriesFemale.setEditable(false);
        jTextFieldSelectedEntriesFemale.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldSelectedEntriesFemale.setText(org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jTextFieldSelectedEntriesFemale.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jLabel9.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jLabel8.text")); // NOI18N
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jTextFieldListEntriesFemale.setEditable(false);
        jTextFieldListEntriesFemale.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldListEntriesFemale.setText(org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jTextFieldListEntriesFemale.text")); // NOI18N

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldListEntriesFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldSelectedEntriesFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jTextFieldListEntriesFemale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSelectedEntriesFemale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(btnSearchFemaleList, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.btnSearchFemaleList.text")); // NOI18N
        btnSearchFemaleList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchFemaleListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollEntriesDbFemale, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboGermplasmListFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearchFemaleList)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnRemoveFemaleDb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboGermplasmListFemale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearchFemaleList)))
                .addGap(18, 18, 18)
                .addComponent(jScrollEntriesDbFemale, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(btnRemoveFemaleDb))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jTabbedPaneFemale.addTab(org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jPanel5.TabConstraints.tabTitle"), jPanel5); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/excelFile.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jLabel6.text")); // NOI18N

        jTextAreaPathFemale.setColumns(20);
        jTextAreaPathFemale.setLineWrap(true);
        jTextAreaPathFemale.setRows(5);
        jTextAreaPathFemale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextAreaPathFemaleMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTextAreaPathFemale);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonBrowseExcelFemale, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jButtonBrowseExcelFemale.text")); // NOI18N
        jButtonBrowseExcelFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBrowseExcelFemaleActionPerformed(evt);
            }
        });

        jTableEntriesExcelFemale.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableEntriesExcelFemale.setDragEnabled(true);
        jTableEntriesExcelFemale.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableEntriesExcelFemale.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableEntriesExcelFemalePropertyChange(evt);
            }
        });
        jScrollEntiresExcelFemale.setViewportView(jTableEntriesExcelFemale);

        org.openide.awt.Mnemonics.setLocalizedText(btnRemoveFemaleExcel, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.btnRemoveFemaleExcel.text")); // NOI18N
        btnRemoveFemaleExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveFemaleExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollEntiresExcelFemale, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBrowseExcelFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnRemoveFemaleExcel))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, 0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonBrowseExcelFemale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollEntiresExcelFemale, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemoveFemaleExcel))
        );

        jTabbedPaneFemale.addTab(org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jPanel8.TabConstraints.tabTitle"), jPanel8); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonCross.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/fm.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonCross, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jButtonCross.text")); // NOI18N
        jButtonCross.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrossActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jLabel4.text")); // NOI18N

        jComboBoxConvection.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CIMMYT WHEAT", "CIMMYT MAIZE", "OTHER CROPS" }));
        jComboBoxConvection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxConvectionActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel19, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jLabel19.text")); // NOI18N

        jComboBoxMethods.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonLoadExcelScript.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/excelScript.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonLoadExcelScript, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jButtonLoadExcelScript.text")); // NOI18N
        jButtonLoadExcelScript.setToolTipText(org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jButtonLoadExcelScript.toolTipText")); // NOI18N
        jButtonLoadExcelScript.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadExcelScriptActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lblCrossPrefix, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.lblCrossPrefix.text")); // NOI18N

        jTextPrefix.setText(org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jTextPrefix.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblStartNo, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.lblStartNo.text")); // NOI18N

        spnStartNo.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), null, null, Integer.valueOf(1)));
        spnStartNo.setValue(1);

        org.openide.awt.Mnemonics.setLocalizedText(lblSuffix, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.lblSuffix.text")); // NOI18N

        jTextSuffix.setText(org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jTextSuffix.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jChkLeading, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jChkLeading.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblNumericFieldWidth, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.lblNumericFieldWidth.text")); // NOI18N

        spnNumFieldWidth.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(3), null, null, Integer.valueOf(1)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCross, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblNumericFieldWidth)
                        .addGap(18, 18, 18)
                        .addComponent(spnNumFieldWidth))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxConvection, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxMethods, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jChkLeading)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(lblSuffix)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextSuffix))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(lblStartNo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(spnStartNo))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(lblCrossPrefix)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextPrefix, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButtonLoadExcelScript, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxConvection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxMethods, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCrossPrefix)
                    .addComponent(jTextPrefix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStartNo)
                    .addComponent(spnStartNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSuffix)
                    .addComponent(jTextSuffix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jChkLeading)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumericFieldWidth)
                    .addComponent(spnNumFieldWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jButtonCross, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonLoadExcelScript))
        );

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jScrollPane3.border.title"))); // NOI18N

        jTableFinalList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableFinalList.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTableFinalList.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableFinalListPropertyChange(evt);
            }
        });
        jScrollPane3.setViewportView(jTableFinalList);

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jlblListName, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jlblListName.text")); // NOI18N

        jTextFieldListName.setText(org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jTextFieldListName.text")); // NOI18N
        jTextFieldListName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldListNameKeyReleased(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jlblDescription, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jlblDescription.text")); // NOI18N

        jTextFieldDescription.setText(org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jTextFieldDescription.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jlblEntries, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jlblEntries.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelEntries, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jLabelEntries.text")); // NOI18N

        jButtonSaveCross.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/save.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSaveCross, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jButtonSaveCross.text")); // NOI18N
        jButtonSaveCross.setToolTipText(org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jButtonSaveCross.toolTipText")); // NOI18N
        jButtonSaveCross.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSaveCross.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSaveCross.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveCrossActionPerformed(evt);
            }
        });

        jDateChooserStart.setDate(new java.util.Date());

        jlblDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jlblDate, org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jlblDate.text")); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblListName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldListName, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooserStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlblEntries)
                .addGap(18, 18, 18)
                .addComponent(jLabelEntries)
                .addGap(18, 18, 18)
                .addComponent(jButtonSaveCross)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(jlblListName)
                .addComponent(jTextFieldListName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jlblDescription)
                .addComponent(jTextFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jlblEntries)
                .addComponent(jLabelEntries)
                .addComponent(jButtonSaveCross)
                .addComponent(jDateChooserStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jlblDate))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTabbedPaneFemale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPaneMale))
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPaneFemale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPaneMale)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPaneMale.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.jTabbedPaneMale.AccessibleContext.accessibleName")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveList() {

        changeCursorWaitStatus(true);

        Integer loggedUserid = AppServicesProxy.getDefault().appServices().getLoggedUserId(FieldbookSettings.getLocalGmsUserId());

        Listnms listnms = new Listnms();
        listnms.setListname(this.jTextFieldListName.getText());

        int fecha;
        try {
            fecha = ConvertUtils.getDateAsInteger(this.jDateChooserStart.getDate());
        } catch (Exception ex) {
            fecha = ConvertUtils.getDateAsInteger(new java.util.Date());
        }

        listnms.setListdate(fecha);
        listnms.setListtype(Listnms.LIST_TYPE_HARVEST);
        listnms.setListuid(loggedUserid);
        listnms.setListdesc(this.jTextFieldDescription.getText());
        listnms.setLhierarchy(0);
        listnms.setListstatus(1);

        AppServicesProxy.getDefault().appServices().addListnms(listnms);
        recentList = listnms;

        List<Listdata> dataList = new ArrayList<Listdata>();

        int desig = -1;
        int source = -1;
        int bcidColumn = -1;

        if (jComboBoxConvection.getSelectedIndex() == CONVENTION_CIMMYT_WHEAT) {
            source = findColumn("FEMALE/MALE");
            bcidColumn = findColumn("BCID");
        } else {
            desig = findColumn("CROSS");
            source = findColumn("SOURCE");
        }

        listnms.setListtype(Listnms.LIST_TYPE_HARVEST);
        int entryCD = findColumn("ENTRY");
        int fgidcol = findColumn("FGID");
        int mgidcol = findColumn("MGID");

        int fdesig = findColumn("FDESIG");
        int mdesig = findColumn("MDESIG");

        int gid = 0;

        // get selected method from combo
        int selectedMethodId = getSelectedMethod().getMid();
        int numberOfParents = getSelectedMethod().getMprgn();

        for (int i = 0; i < jTableFinalList.getRowCount(); i++) {
            Listdata listdata = new Listdata(true);

            ListdataPK pk1 = new ListdataPK(listnms.getListid(), 0);
            listdata.setListdataPK(pk1);
            listdata.setEntryid(i + 1);

            if (jComboBoxConvection.getSelectedIndex() == CONVENTION_CIMMYT_WHEAT) {
                listdata.setDesig(this.jTableFinalList.getValueAt(i, bcidColumn).toString());
                if (bcidColumn > 0) {
                    listdata.setNameBCID(this.jTableFinalList.getValueAt(i, bcidColumn).toString());
                } else {
                    listdata.setNameBCID("");
                    break;
                }
            } else {
                if (desig > 0) {
                    listdata.setDesig(this.jTableFinalList.getValueAt(i, desig).toString());
                } else {
                    listdata.setDesig("");
                }
            }


            if (entryCD >= 0) {
                int entryNumber = ConvertUtils.getValueAsInteger(this.jTableFinalList.getValueAt(i, entryCD));
                listdata.setEntrycd("E" + ConvertUtils.getZeroLeading(entryNumber, 4));
            } else {
                listdata.setEntrycd("");
            }

            if (source > 0) {
                listdata.setSource(this.jTableFinalList.getValueAt(i, source).toString());
            } else {
                listdata.setSource("");
            }


            if (fdesig > 0 && mdesig > 0) {
                listdata.setGrpname(jTableFinalList.getValueAt(i, fdesig).toString() + "/" + jTableFinalList.getValueAt(i, mdesig));
            } else {
                listdata.setGrpname("");
            }
            listdata.setLrstatus(0);

            if (gid > 0) {
                listdata.setGid(Integer.parseInt(jTableFinalList.getValueAt(i, gid).toString()));
            } else {
                listdata.setGid(0);
            }

            // if is cimmyt wheat method will be assigned once germplasm has been saved
            if (jComboBoxConvection.getSelectedIndex() == CONVENTION_CIMMYT_WHEAT) {
                String methodName = (String) jTableFinalList.getValueAt(i, WheatColumns.METHOD);
                listdata.setMethodId(GermplasmSearch.getMethodNumber(methodName));
            } else {
                listdata.setMethodId(selectedMethodId);
            }

            listdata.setGnpgs(numberOfParents);

            // assign parents
            Integer gpdi1 = ConvertUtils.getValueAsInteger(jTableFinalList.getValueAt(i, fgidcol));
            Integer gpdi2 = ConvertUtils.getValueAsInteger(jTableFinalList.getValueAt(i, mgidcol));

            listdata.setGpid1(gpdi1);
            listdata.setGpid2(gpdi2);

            // clean all html codes
            String cleanedSource = listdata.getSource();
            cleanedSource = cleanedSource.replaceAll("<html> <font color='purple'>", "");
            cleanedSource = cleanedSource.replaceAll("</font>", "");
            cleanedSource = cleanedSource.replaceAll("<font color='blue'>", "");
            cleanedSource = cleanedSource.replaceAll("</html>", "");
            listdata.setSource(cleanedSource);

            dataList.add(listdata);

        }


        if (jComboBoxConvection.getSelectedIndex() == CONVENTION_CIMMYT_WHEAT) {

            AppServicesProxy.getDefault().appServices().saveGerplasmCimmytWheat(dataList, listnms, loggedUserid, listFemale, listMale);

        } else {
            AppServicesProxy.getDefault().appServices().addNewsGermplasm(listnms, dataList, loggedUserid);
        }

        fillComboListNames();
        changeCursorWaitStatus(false);
    }

    private void openRecentList(Listnms listnms) {
        ListDataWindowTopComponent ldwtc = ListDataWindowTopComponent.getListDataWindowTopComponent(listnms);

        if (ldwtc == null) {
            ldwtc = new ListDataWindowTopComponent(listnms);
        }

        if (jComboBoxConvection.getSelectedIndex() == CONVENTION_CIMMYT_WHEAT) {
            ldwtc.setIsForWheat(true);
        } else {
            ldwtc.setIsForWheat(false);
        }
        ldwtc.open();
        ldwtc.requestActive();

    }

    private int findColumn(String col) {
        int myCol = 0;

        try {
            myCol = jTableFinalList.getTableHeader().getColumnModel().getColumnIndex(col);
        } catch (NullPointerException ex) {
            myCol = 0;
        }
        return myCol;
    }

    private void cboGermplasmListMaleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboGermplasmListMaleItemStateChanged
        readGermplsmEntriesFromDb(this.jTableEntriesDBMale, 1);
   }//GEN-LAST:event_cboGermplasmListMaleItemStateChanged

    private void readGermplsmEntriesFromDb(JTable tabla, int opcion) {

        rowListDB = new ArrayList<List<Object>>();
        boolean validSelection = false;

        GermplasmListReader germplasmListReader = new GermplasmListReaderImpl();

        switch (opcion) {
            case 0:

                validSelection = cboGermplasmListFemale.getSelectedIndex() > 0;

                if (validSelection) {
                    try {
                        Listnms selectedList = (Listnms) cboGermplasmListFemale.getSelectedItem();
                        GermplasmList germplasmList = germplasmListReader.getGermPlasmListFromDB(selectedList.getListid());
                        setGermplasmListIntoTable(germplasmList, tabla, 0, 0);
                    } catch (Exception ex) {
                        System.out.println("ERROR AL LEER DB GERMPLASM ENTRIES: " + ex);
                    }
                } else {
                    GermplasmEntriesTableModel modeloTablaEntries = new GermplasmEntriesTableModel();
                    this.jTableEntriesDBFemale.setModel(modeloTablaEntries);
                    this.jTextAreaPathFemale.setText("");
                    this.jTextFieldListEntriesFemale.setText("0");

                }

                break;
            case 1:
                validSelection = cboGermplasmListMale.getSelectedIndex() > 0;

                if (validSelection) {
                    try {
                        Listnms selectedList = (Listnms) cboGermplasmListMale.getSelectedItem();
                        GermplasmList germplasmList = germplasmListReader.getGermPlasmListFromDB(selectedList.getListid());
                        setGermplasmListIntoTable(germplasmList, tabla, 1, 1);
                    } catch (Exception ex) {
                        System.out.println("ERROR AL LEER DB GERMPLASM ENTRIES: " + ex);
                    }
                } else {
                    GermplasmEntriesTableModel modeloTablaEntries = new GermplasmEntriesTableModel();
                    this.jTableEntriesDBMale.setModel(modeloTablaEntries);
                    this.jTextAreaPathMale.setText("");
                    this.jTextFieldListEntriesMale.setText("0");

                }

                break;

        }






    }

    @SuppressWarnings("unchecked")
    private void setGermplasmListIntoTable(GermplasmList germplasmList, JTable tabla, int opcion, int male) {
        List<String> columnList = gat.getColumnList(factores);
        GermplasmEntriesTableModel tableModel = null;



        switch (male) {
            case 0://female
                this.jTextFieldListEntriesFemale.setText(String.valueOf(germplasmList.getListEntries().size()));

                break;
            case 1://male
                this.jTextFieldListEntriesMale.setText(String.valueOf(germplasmList.getListEntries().size()));
                break;

        }


        switch (opcion) {
            case 0:
                rowListDB = gat.getMappedColumns(columnList, germplasmList);
                tableModel = new GermplasmEntriesTableModel(factores, rowListDB);
                break;
            case 1:
                rowListExcel = gat.getMappedColumns(columnList, germplasmList);
                tableModel = new GermplasmEntriesTableModel(factores, rowListExcel);
                break;

        }

        tabla.setModel(tableModel);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int col = 0; col < tabla.getColumnCount(); col++) {
            tabla.getColumnModel().getColumn(col).setCellRenderer(tcr);
        }
    }

    private void jTableEntriesDBMalePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableEntriesDBMalePropertyChange
        switch (jTabbedPaneMale.getSelectedIndex()) {
            case 0:
                this.jTextFieldListEntriesMale.setText(String.valueOf(this.jTableEntriesDBMale.getRowCount()));
                this.jTextFieldSelectedFinalMale.setText(String.valueOf(this.jTableEntriesDBMale.getSelectedRowCount()));
                break;
            case 1:
                this.jTextFieldListEntriesMale.setText(String.valueOf(this.jTableEntriesExcelMale.getRowCount()));
                this.jTextFieldSelectedFinalMale.setText(String.valueOf(this.jTableEntriesExcelMale.getSelectedRowCount()));
                break;
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_jTableEntriesDBMalePropertyChange

    private void jTextAreaPathMaleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaPathMaleMousePressed

        if (!this.jTextAreaPathMale.isEnabled()) {
            return;
        }

        openFile(1);
        if (this.jTextAreaPathMale.getText().isEmpty() == false) {
            readExcelGermplsmEntries(this.jTextAreaPathMale.getText(), this.jTableEntriesExcelMale, 1);
        }
    }//GEN-LAST:event_jTextAreaPathMaleMousePressed

    private void jButtonBrowseExcelMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBrowseExcelMaleActionPerformed

        openFile(1);
        if (this.jTextAreaPathMale.getText().isEmpty() == false) {
            readExcelGermplsmEntries(this.jTextAreaPathMale.getText(), this.jTableEntriesExcelMale, 1);
        }
    }//GEN-LAST:event_jButtonBrowseExcelMaleActionPerformed

    private void jTabbedPaneMaleStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPaneMaleStateChanged
        switch (jTabbedPaneMale.getSelectedIndex()) {
            case 0:
                this.jTextFieldListEntriesMale.setText(String.valueOf(this.jTableEntriesDBMale.getRowCount()));
                this.jTextFieldSelectedFinalMale.setText(String.valueOf(this.jTableEntriesDBMale.getSelectedRowCount()));
                break;
            case 1:
                this.jTextFieldListEntriesMale.setText(String.valueOf(this.jTableEntriesExcelMale.getRowCount()));
                this.jTextFieldSelectedFinalMale.setText(String.valueOf(this.jTableEntriesExcelMale.getSelectedRowCount()));
                break;

            default:
                throw new AssertionError();
        }
   }//GEN-LAST:event_jTabbedPaneMaleStateChanged

    public void addListener() {



        ListSelectionModel cellSelectionFemaleModel = jTableEntriesDBFemale.getSelectionModel();
        cellSelectionFemaleModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        cellSelectionFemaleModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                jTextFieldSelectedEntriesFemale.setText(String.valueOf(jTableEntriesDBFemale.getSelectedRowCount()));
            }
        });
        this.jTableEntriesDBFemale.setSelectionModel(cellSelectionFemaleModel);
        //--------------------------------------------------------------------------------------------

        ListSelectionModel cellSelectionMaleModel = jTableEntriesDBMale.getSelectionModel();
        cellSelectionMaleModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        cellSelectionMaleModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                jTextFieldSelectedFinalMale.setText(String.valueOf(jTableEntriesDBMale.getSelectedRowCount()));
            }
        });
        this.jTableEntriesDBMale.setSelectionModel(cellSelectionMaleModel);


        ListSelectionModel cellSelectionExcelFemaleModel = jTableEntriesExcelFemale.getSelectionModel();
        cellSelectionExcelFemaleModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        cellSelectionExcelFemaleModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                jTextFieldSelectedEntriesFemale.setText(String.valueOf(jTableEntriesExcelFemale.getSelectedRowCount()));
            }
        });
        this.jTableEntriesExcelFemale.setSelectionModel(cellSelectionExcelFemaleModel);


        //--------------------------------------------------------------------------------------------     
        ListSelectionModel cellSelectionExcelMaleModel = jTableEntriesExcelMale.getSelectionModel();
        cellSelectionExcelMaleModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        cellSelectionExcelMaleModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                jTextFieldSelectedFinalMale.setText(String.valueOf(jTableEntriesExcelMale.getSelectedRowCount()));
            }
        });
        this.jTableEntriesExcelMale.setSelectionModel(cellSelectionExcelMaleModel);


    }

    private void enabledMale(boolean state) {
        this.jTabbedPaneMale.setEnabledAt(0, state);
        this.jTabbedPaneMale.setEnabledAt(1, state);
        this.cboGermplasmListMale.setEnabled(state);
        jTextAreaPathMale.setEnabled(state);
        jButtonBrowseExcelMale.setEnabled(state);
    }

    private void jButtonCrossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrossActionPerformed

        this.jButtonSaveCross.setEnabled(false);

        GermplasmEntriesTableModel femaleModel = null;
        GermplasmEntriesTableModel maleModel = null;

        int female = 0;
        int male = 0;

        switch (jTabbedPaneFemale.getSelectedIndex()) {
            case 0://DATABASE
                female = this.jTableEntriesDBFemale.getRowCount();
                femaleModel = (GermplasmEntriesTableModel) this.jTableEntriesDBFemale.getModel();
                break;
            case 1://EXCEL FILE
                female = jTableEntriesExcelFemale.getRowCount();
                femaleModel = (GermplasmEntriesTableModel) this.jTableEntriesExcelFemale.getModel();
                break;

        }

        switch (jTabbedPaneMale.getSelectedIndex()) {
            case 0://DATABASE
                male = this.jTableEntriesDBMale.getRowCount();
                maleModel = (GermplasmEntriesTableModel) this.jTableEntriesDBMale.getModel();
                break;

            case 1://EXCEL FILE
                male = this.jTableEntriesExcelMale.getRowCount();
                maleModel = (GermplasmEntriesTableModel) this.jTableEntriesExcelMale.getModel();
                break;



        }

        if (female != male) {
            NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.sameNumber"), NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);

            return;
        }

        if (female == 0) {
            NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.femaleEmpty"), NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);

            return;
        }

        if (male == 0) {
            NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.maleEmpty"), NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);

            return;
        }
        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };

        jTableFinalList.getTableHeader().setDefaultRenderer(new CrossGridDecorator(OtherCropColumns.FEMALE_COLUMNS,OtherCropColumns.MALE_COLUMNS));
                
        modelo.setColumnIdentifiers(otherCropsheaders);
        modelo.setRowCount(female);

        int startNumber = (Integer) spnStartNo.getValue();
        int numericFieldWidth = (Integer) spnNumFieldWidth.getValue();
        boolean leadingZeros = jChkLeading.isSelected();

        // get female list name
        String femaleListName = "";
        String maleListName = "";

        if (jTabbedPaneFemale.getSelectedIndex() == 0) {
            if (cboGermplasmListFemale.getSelectedItem() instanceof Listnms) {
                Listnms femaleList = (Listnms) cboGermplasmListFemale.getSelectedItem();
                femaleListName = femaleList.getListname();
            } else {
                String femaleList = (String) cboGermplasmListFemale.getSelectedItem();
                femaleListName = femaleList;
            }
        } else {
            femaleListName = FileUtils.extractFileName(jTextAreaPathFemale.getText());
        }
        if (jTabbedPaneMale.getSelectedIndex() == 0) {
            if (cboGermplasmListMale.getSelectedItem() instanceof Listnms) {
                Listnms maleList = (Listnms) cboGermplasmListMale.getSelectedItem();
                maleListName = maleList.getListname();
            } else {
                String maleList = (String) cboGermplasmListMale.getSelectedItem();
                maleListName = maleList;
            }
        } else {
            maleListName = FileUtils.extractFileName(jTextAreaPathMale.getText());
        }

        for (int i = 0; i < female; i++) {

            Object fentry = femaleModel.getValueAt(i, 0);
            Object mentry = maleModel.getValueAt(i, 0);
            Object fdesig = femaleModel.getValueAt(i, 1);
            Object mdesig = maleModel.getValueAt(i, 1);
            Object fgid = femaleModel.getValueAt(i, 2);
            Object mgid = maleModel.getValueAt(i, 2);

            modelo.setValueAt(i + 1, i, 0);//ENTRY

            if (jComboBoxConvection.getSelectedIndex() == CONVENTION_OTHER_CROPS) {
                StringBuilder designation = new StringBuilder();
                if (jTextPrefix.getText().isEmpty() && jTextSuffix.getText().isEmpty()) {
                    designation.append(fdesig).append("/").append(mdesig);
                } else {
                    designation.append(jTextPrefix.getText());
                    if (leadingZeros) {
                        designation.append(ConvertUtils.getZeroLeading(startNumber, numericFieldWidth));
                    } else {
                        designation.append(startNumber);
                    }
                    designation.append(jTextSuffix.getText());
                }
                modelo.setValueAt(designation, i, 1);
            } else {
                modelo.setValueAt(fdesig + "/" + mdesig, i, 1);//CROSS
            }

            startNumber++;

            modelo.setValueAt(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.notAssigned"), i, 2);//GIG
            modelo.setValueAt(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.singleCross"), i, 3);//METHOD
            modelo.setValueAt(fdesig, i, 4);//FDESIG
            modelo.setValueAt(fgid, i, 5);//FGIG
            modelo.setValueAt(mdesig, i, 6);//MDESIG
            modelo.setValueAt(mgid, i, 7);//MGID  
            if (jComboBoxConvection.getSelectedIndex() != CONVENTION_CIMMYT_WHEAT) {
                StringBuilder source = new StringBuilder();
                source.append(femaleListName).append(":").append(fentry);
                source.append("/");
                source.append(maleListName).append(":").append(mentry);
                modelo.setValueAt(source.toString(), i, 8); // SOURCE
            }
        }




        jTableFinalList.setModel(modelo);

        ajustaColumnsTable(this.jTableFinalList);

        if (this.jTableFinalList.getRowCount() > 0) {
            this.jButtonSaveCross.setEnabled(true);
        }
    }//GEN-LAST:event_jButtonCrossActionPerformed
    private void jTableEntriesExcelMalePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableEntriesExcelMalePropertyChange
        switch (jTabbedPaneMale.getSelectedIndex()) {
            case 0:
                this.jTextFieldListEntriesMale.setText(String.valueOf(this.jTableEntriesDBMale.getRowCount()));
                this.jTextFieldSelectedFinalMale.setText(String.valueOf(this.jTableEntriesDBMale.getSelectedRowCount()));

                break;
            case 1:
                this.jTextFieldListEntriesMale.setText(String.valueOf(this.jTableEntriesExcelMale.getRowCount()));
                this.jTextFieldSelectedFinalMale.setText(String.valueOf(this.jTableEntriesExcelMale.getSelectedRowCount()));

                break;

            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_jTableEntriesExcelMalePropertyChange

    private void jButtonLoadExcelScriptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadExcelScriptActionPerformed

        FileFilter[] filtros = new FileFilter[10];
        filtros = selectorArchivo.getChoosableFileFilters();

        for (int i = 0; i < filtros.length; i++) {
            FileFilter filtro = filtros[i];
            selectorArchivo.removeChoosableFileFilter(filtro);
        }


        String customPath = "";//NbPreferences.forModule(nurseryManagerTopComponent.class).get("customPathNM", "");               
        File myDesktop = null;
        if (!customPath.isEmpty()) {
            myDesktop = new File(customPath);
        } else {
            myDesktop = new File(FieldbookSettings.getCrossesDefaultFolder());
        }


        if (myDesktop.exists()) {
            selectorArchivo.setCurrentDirectory(myDesktop);
        } else {
            File archivoNulo = new File("");
            selectorArchivo.setSelectedFile(archivoNulo);
        }

        //NbPreferences.forModule(nurseryManagerTopComponent.class).put("customPathNM", selectorArchivo.getSelectedFile().toString());

        selectorArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        selectorArchivo.addChoosableFileFilter(new ExcelFiltro());
        int resultado = selectorArchivo.showOpenDialog(null);

        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }
        if (isValidExcelWheatFile(selectorArchivo.getSelectedFile())) {
            final ProgressHandle handle = ProgressHandleFactory.createHandle(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.readingwheatfile"));
            handle.start();
            (new SwingWorker<String, Object>() {

                @Override
                protected String doInBackground() throws Exception {
                    processExcelCrossFile(selectorArchivo.getSelectedFile());
                    return "";
                }

                @Override
                protected void done() {
                    super.done();
                    try {
                        String valor = get();
                        DialogUtil.displayInfo(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.readingwheatfiledone"));
                    } catch (Exception ex) {
                        Exceptions.printStackTrace(ex);
                    } finally {
                        // close the progress handler
                        handle.finish();
                    }
                }
            }).execute();

        }

    }//GEN-LAST:event_jButtonLoadExcelScriptActionPerformed

    private void processExcelCrossFile(File archivo) {

        listFemale = new ArrayList<GermplasmSearch>();
        listMale = new ArrayList<GermplasmSearch>();

        tempListCross = new ArrayList<String>();

        changeCursorWaitStatus(true);
        this.jButtonSaveCross.setEnabled(false);




        boolean moreRowsToRead = true;
        int rowIndex = 0;
        int gms = 0;
        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        jTableFinalList.getTableHeader().setDefaultRenderer(new CrossGridDecorator(WheatColumns.FEMALE_COLUMNS, WheatColumns.MALE_COLUMNS));

        modelo.setRowCount(0);
        modelo.setColumnIdentifiers(headersScript);

        try {

            InputStream inputStream = new FileInputStream(archivo);
            HSSFWorkbook excelBook = new HSSFWorkbook(inputStream);

            HSSFSheet sheet = excelBook.getSheetAt(0);
            HSSFCell cellData;
            HSSFRow rowData;

            while (moreRowsToRead) {
                rowIndex++;

                rowData = sheet.getRow(rowIndex);

                moreRowsToRead = isMoreRows(rowData);


                if (!moreRowsToRead) {
                    break;
                }

                cellData = rowData.getCell(FTID);
                int ftid = getIntValueFromCell(cellData);

                cellData = rowData.getCell(FOCC);
                int focc = getIntValueFromCell(cellData);

                cellData = rowData.getCell(FENT);
                int fent = getIntValueFromCell(cellData);

                cellData = rowData.getCell(MTID);
                int mtid = getIntValueFromCell(cellData);

                cellData = rowData.getCell(MOCC);
                int mocc = getIntValueFromCell(cellData);

                cellData = rowData.getCell(MENT);
                int ment = getIntValueFromCell(cellData);


                GermplasmSearch gsF = new GermplasmSearch();
                gsF.setCrosstype("SS");
                gsF.setStudyId(ftid);
                gsF.setTrial(focc);
                gsF.setPlot(fent);
                listFemale.add(gsF);


                GermplasmSearch gsM = new GermplasmSearch();
                gsM.setCrosstype("SS");
                gsM.setStudyId(mtid);
                gsM.setTrial(mocc);
                gsM.setPlot(ment);
                listMale.add(gsM);
            }


            log.info("Size for listFemale " + listFemale.size());
            log.info("Size for listMale " + listMale.size());

            log.info("Getting getGermplasmByListStudyTrialPlotCross ");

            List<GermplasmSearch> germplasmSearchs = AppServicesProxy.getDefault().appServices().getGermplasmByListStudyTrialPlotCross(listFemale, listMale);

            log.info("DONE !!!! Getting getGermplasmByListStudyTrialPlotCross ");

            for (GermplasmSearch gs : germplasmSearchs) {

                log.info("Value for GMS  var " + gms);
                gms++;

                int maximo = gs.getMax() + gms;

                int met = gs.getMethodGermplasm() + (gms - 1);
                String fin = gs.getCharBCID();


                //String maxString = giveMaxString(maximo) + "S";

                String maxString = giveMaxString(met) + fin;
                modelo.setRowCount(gms);
                String crossString = gs.getNames().getNval() + "/" + gs.getNamesMale().getNval();



                tempListCross.add(crossString);
                String cross = "<html> <font color='purple'>" + gs.getNames().getNval() + "</font>/</font><font color='blue'>" + gs.getNamesMale().getNval() + " </font> </html>";

                // String cross=giveMeCross(gs.getNames().getGid(),gs.getNamesMale().getGid());

                modelo.setValueAt(gms, gms - 1, WheatColumns.ENTRY);//ENTRY
                modelo.setValueAt(gs.getBcid() + maxString, gms - 1, WheatColumns.BCID);//BCID

                modelo.setValueAt(getHtmlForText(gs.getNames().getCimmytPedigree(), true), gms - 1, WheatColumns.FEMALE_PEDIGREE);//FPEDIGREE                
                modelo.setValueAt(getHtmlForText(gs.getNamesMale().getCimmytPedigree(), false), gms - 1, WheatColumns.MALE_PEDIGREE);//MALE PEDIGREE                

                modelo.setValueAt(cross, gms - 1, WheatColumns.FEMALE_MALE); //CROSS
                modelo.setValueAt(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.notAssigned"), gms - 1, WheatColumns.GID); //GID                        
                //modelo.setValueAt(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.singleCross"), gms - 1, 4);//METHOD
                modelo.setValueAt(gs.getMethodName(), gms - 1, WheatColumns.METHOD);//METHOD

                modelo.setValueAt(listFemale.get(gms - 1).getStudyId(), gms - 1, WheatColumns.FTID);//FTID
                modelo.setValueAt(listFemale.get(gms - 1).getTrial(), gms - 1, WheatColumns.FOCC);//FOCC
                modelo.setValueAt(listFemale.get(gms - 1).getPlot(), gms - 1, WheatColumns.FENTRY);//FENTRY
                modelo.setValueAt(gs.getNames().getNval(), gms - 1, WheatColumns.FDESIG);//FDESIG
                modelo.setValueAt(gs.getNames().getGid(), gms - 1, WheatColumns.FGID);//FGID

                modelo.setValueAt(listMale.get(gms - 1).getStudyId(), gms - 1, WheatColumns.MTID);//MTID
                modelo.setValueAt(listMale.get(gms - 1).getTrial(), gms - 1, WheatColumns.MOCC);//MOCC
                modelo.setValueAt(listMale.get(gms - 1).getPlot(), gms - 1, WheatColumns.MENTRY);//MENTRY
                modelo.setValueAt(gs.getNamesMale().getNval(), gms - 1, WheatColumns.MDESIG);//MDESIG
                modelo.setValueAt(gs.getNamesMale().getGid(), gms - 1, WheatColumns.MGID);//MGID

            }

            log.info("Setting model..... ");
            this.jTableFinalList.setModel(modelo);
            log.info("Setting model DONE..... ");

            log.info("Adjusting Columns..... ");
            //ajustaColumnsTable(this.jTableFinalList);
            log.info("Adjusting Columns DONE..... ");

            changeCursorWaitStatus(false);

            log.info("Enabling button..... ");
            if (this.jTableFinalList.getRowCount() > 0) {
                this.jButtonSaveCross.setEnabled(true);
            }
            log.info("Enabling button DONE..... ");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e);
            changeCursorWaitStatus(false);


        }



    }

    private void jTextFieldListNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldListNameKeyReleased
   }//GEN-LAST:event_jTextFieldListNameKeyReleased

    private void jButtonSaveCrossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveCrossActionPerformed
        if (jTextFieldListName.getText().trim().isEmpty()) {
            DialogUtil.displayError(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.typeName"));
            jTextFieldListName.requestFocusInWindow();
            return;
        }

        if (this.jTextFieldDescription.getText().isEmpty()) {
            NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.description"), NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            jTextFieldDescription.requestFocusInWindow();
            return;
        }

        if (this.jTableFinalList.getRowCount() == 0) {
            NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.empty"), NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            return;
        }

        if (AppServicesProxy.getDefault().appServices().existGermplasmListName(jTextFieldListName.getText())) {
            NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.listNameAlreadyExists"), NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            return;
        }

        NotifyDescriptor d = new NotifyDescriptor.Confirmation(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.save"), NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.final"),
                NotifyDescriptor.OK_CANCEL_OPTION);
        if (DialogDisplayer.getDefault().notify(d) == NotifyDescriptor.OK_OPTION) {

            boolean validFile = true;
            if (jComboBoxConvection.getSelectedIndex() == CONVENTION_CIMMYT_WHEAT) {
                validFile = isValidExcelWheatFile(selectorArchivo.getSelectedFile());
            }

            if (validFile) {
                final ProgressHandle handle = ProgressHandleFactory.createHandle(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.savingList"));
                handle.start();
                (new SwingWorker<String, Object>() {

                    @Override
                    protected String doInBackground() throws Exception {
                        saveList();
                        return "";
                    }

                    @Override
                    protected void done() {
                        super.done();
                        try {
                            String valor = get();
                            showListSavedDone();

                        } catch (Exception ex) {
                            Exceptions.printStackTrace(ex);
                        } finally {
                            // close the progress handler
                            handle.finish();
                        }
                    }
                }).execute();

            }




        }
        this.jButtonSaveCross.setEnabled(false);
    }//GEN-LAST:event_jButtonSaveCrossActionPerformed

    private void jTableFinalListPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableFinalListPropertyChange
        this.jLabelEntries.setText(String.valueOf(this.jTableFinalList.getRowCount()));
    }//GEN-LAST:event_jTableFinalListPropertyChange

    private void btnRemoveMaleDbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveMaleDbActionPerformed
        removeMaleFromDb();
    }//GEN-LAST:event_btnRemoveMaleDbActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        removeMaleFromExcel();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTabbedPaneFemaleStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPaneFemaleStateChanged
        switch (jTabbedPaneFemale.getSelectedIndex()) {
            case 0:
                this.jTextFieldListEntriesFemale.setText(String.valueOf(this.jTableEntriesDBFemale.getRowCount()));
                this.jTextFieldSelectedEntriesFemale.setText(String.valueOf(this.jTableEntriesDBFemale.getSelectedRowCount()));

                isCrossScripFile = false;
                enabledMale(true);
                break;
            case 1:
                this.jTextFieldListEntriesFemale.setText(String.valueOf(this.jTableEntriesExcelFemale.getRowCount()));
                this.jTextFieldSelectedEntriesFemale.setText(String.valueOf(this.jTableEntriesExcelFemale.getSelectedRowCount()));
                isCrossScripFile = false;
                enabledMale(true);
                break;


        }
    }//GEN-LAST:event_jTabbedPaneFemaleStateChanged

    private void btnRemoveFemaleExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveFemaleExcelActionPerformed
        removeFemaleFromExcel();
    }//GEN-LAST:event_btnRemoveFemaleExcelActionPerformed

    private void jTableEntriesExcelFemalePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableEntriesExcelFemalePropertyChange
        switch (jTabbedPaneMale.getSelectedIndex()) {
            case 0:
                this.jTextFieldListEntriesMale.setText(String.valueOf(this.jTableEntriesDBMale.getRowCount()));
                this.jTextFieldSelectedFinalMale.setText(String.valueOf(this.jTableEntriesDBMale.getSelectedRowCount()));
                break;
            case 1:
                this.jTextFieldListEntriesMale.setText(String.valueOf(this.jTableEntriesExcelMale.getRowCount()));
                this.jTextFieldSelectedFinalMale.setText(String.valueOf(this.jTableEntriesExcelMale.getSelectedRowCount()));
                break;
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_jTableEntriesExcelFemalePropertyChange

    private void jButtonBrowseExcelFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBrowseExcelFemaleActionPerformed
        openFile(0);
        if (this.jTextAreaPathFemale.getText().isEmpty() == false) {
            readExcelGermplsmEntries(this.jTextAreaPathFemale.getText(), this.jTableEntriesExcelFemale, 0);
        }
    }//GEN-LAST:event_jButtonBrowseExcelFemaleActionPerformed

    private void jTextAreaPathFemaleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaPathFemaleMousePressed
        if (!this.jTextAreaPathFemale.isEnabled()) {
            return;
        }

        openFile(0);
        if (this.jTextAreaPathFemale.getText().isEmpty() == false) {
            readExcelGermplsmEntries(this.jTextAreaPathFemale.getText(), this.jTableEntriesExcelFemale, 0);
        }
    }//GEN-LAST:event_jTextAreaPathFemaleMousePressed

    private void btnRemoveFemaleDbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveFemaleDbActionPerformed
        removeFemaleFromDb();
    }//GEN-LAST:event_btnRemoveFemaleDbActionPerformed

    private void jTableEntriesDBFemalePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableEntriesDBFemalePropertyChange
        switch (jTabbedPaneFemale.getSelectedIndex()) {
            case 0:
                this.jTextFieldListEntriesFemale.setText(String.valueOf(this.jTableEntriesDBFemale.getRowCount()));
                this.jTextFieldSelectedEntriesFemale.setText(String.valueOf(this.jTableEntriesDBFemale.getSelectedRowCount()));

                break;
            case 1:
                this.jTextFieldListEntriesFemale.setText(String.valueOf(this.jTableEntriesExcelFemale.getRowCount()));
                this.jTextFieldSelectedEntriesFemale.setText(String.valueOf(this.jTableEntriesExcelFemale.getSelectedRowCount()));

                break;

            default:
                throw new AssertionError();
        }

   }//GEN-LAST:event_jTableEntriesDBFemalePropertyChange

    private void cboGermplasmListFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGermplasmListFemaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboGermplasmListFemaleActionPerformed

    private void cboGermplasmListFemaleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboGermplasmListFemaleItemStateChanged
        readGermplsmEntriesFromDb(this.jTableEntriesDBFemale, 0);
    }//GEN-LAST:event_cboGermplasmListFemaleItemStateChanged

    private void jComboBoxConvectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxConvectionActionPerformed
        checkConvection();
    }//GEN-LAST:event_jComboBoxConvectionActionPerformed

    private void btnSearchMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchMaleActionPerformed
        searchMaleList();
    }//GEN-LAST:event_btnSearchMaleActionPerformed

    private void btnSearchFemaleListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchFemaleListActionPerformed
        searchFemaleList();
    }//GEN-LAST:event_btnSearchFemaleListActionPerformed

    private void showListSavedDone() {
        NotifyDescriptor d2 = new NotifyDescriptor.Message(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.saved"), NotifyDescriptor.INFORMATION_MESSAGE);
        DialogDisplayer.getDefault().notify(d2);
        this.jTextFieldDescription.setText("");
        this.jTextFieldListName.setText("");

        DefaultTableModel modelo = (DefaultTableModel) this.jTableFinalList.getModel();
        modelo.setRowCount(0);
        openRecentList(recentList);
    }

    private void openFile(int opcion) {
        FileFilter[] filtros = new FileFilter[10];
        filtros = selectorArchivo.getChoosableFileFilters();

        for (int i = 0; i < filtros.length; i++) {
            FileFilter filtro = filtros[i];
            selectorArchivo.removeChoosableFileFilter(filtro);
        }

        File myDesktop = new File(FieldbookSettings.getSetting(FieldbookSettings.GERMPLASM_LIST_DEFAULT_FOLDER));

        if (myDesktop.exists()) {
            selectorArchivo.setCurrentDirectory(myDesktop);
        } else {
            File archivoNulo = new File("");
            selectorArchivo.setSelectedFile(archivoNulo);
        }


        selectorArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        selectorArchivo.addChoosableFileFilter(new ExcelFiltro());
        int resultado = selectorArchivo.showOpenDialog(null);

        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }


        switch (opcion) {
            case 0://female
                this.jTextAreaPathFemale.setText(selectorArchivo.getSelectedFile().toString());
                break;
            case 1://male
                this.jTextAreaPathMale.setText(selectorArchivo.getSelectedFile().toString());

                break;

        }



    }

    private void readExcelGermplsmEntriesScript(String myFile, JTable tabla) {

        rowListExcel = new ArrayList<List<Object>>();
        GermplasmListReader germplasmListReader = new GermplasmListReaderImpl();
        boolean validFile = false;
        try {
            validFile = germplasmListReader.isValidCrossScript(myFile);
        } catch (Exception ex) {
            System.out.println("ERROR AL VALIDAR ARCHIVO EXCEL");
        }

        if (validFile) {
            try {
                GermplasmList germplasmList = germplasmListReader.getGermPlasmList(myFile);
                setGermplasmListIntoTable(germplasmList, tabla, 2);
            } catch (Exception ex) {
                System.out.println("ERROR AL LEER EXCEL GERMPLASM ENTRIES: " + ex);
            }
        } else {
            GermplasmEntriesTableModel modeloTablaEntries = new GermplasmEntriesTableModel();
            this.jTableEntriesExcelFemale.setModel(modeloTablaEntries);
            this.jTextAreaPathFemale.setText("");
            this.jTextFieldListEntriesFemale.setText("0");


            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.notValid"), NotifyDescriptor.ERROR_MESSAGE));
        }

    }

    private void readExcelGermplsmEntries(String myFile, JTable tabla, int opcion) {

        rowListExcel = new ArrayList<List<Object>>();
        GermplasmListReader germplasmListReader = new GermplasmListReaderImpl();
        boolean validFile = false;
        try {
            validFile = germplasmListReader.isValidTemplate(myFile);
        } catch (Exception ex) {
            System.out.println("ERROR AL VALIDAR ARCHIVO EXCEL");
        }


        switch (opcion) {
            case 0://female
                if (validFile) {
                    try {
                        GermplasmList germplasmList = germplasmListReader.getGermPlasmList(myFile);
                        setGermplasmListIntoTable(germplasmList, tabla, 0);
                    } catch (Exception ex) {
                        System.out.println("ERROR AL LEER EXCEL GERMPLASM ENTRIES: " + ex);
                    }
                } else {
                    GermplasmEntriesTableModel modeloTablaEntries = new GermplasmEntriesTableModel();
                    this.jTableEntriesExcelFemale.setModel(modeloTablaEntries);
                    this.jTextAreaPathFemale.setText("");
                    this.jTextFieldListEntriesFemale.setText("0");
                    DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.notValid"), NotifyDescriptor.ERROR_MESSAGE));
                }


                break;
            case 1://male
                if (validFile) {
                    try {
                        GermplasmList germplasmList = germplasmListReader.getGermPlasmList(myFile);
                        setGermplasmListIntoTable(germplasmList, tabla, 1);
                    } catch (Exception ex) {
                        System.out.println("ERROR AL LEER EXCEL GERMPLASM ENTRIES: " + ex);
                    }
                } else {
                    GermplasmEntriesTableModel modeloTablaEntries = new GermplasmEntriesTableModel();
                    this.jTableEntriesExcelMale.setModel(modeloTablaEntries);
                    this.jTextAreaPathMale.setText("");
                    this.jTextFieldListEntriesMale.setText("0");
                    DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.notValid"), NotifyDescriptor.ERROR_MESSAGE));
                }

                break;





        }


    }

    @SuppressWarnings("unchecked")
    private void setGermplasmListIntoTable(GermplasmList germplasmList, JTable tabla, int option) {
        List<String> columnList = gat.getColumnList(factores);
        GermplasmEntriesTableModel tableModel = null;

        switch (option) {
            case 0://female
                this.jTextFieldListEntriesFemale.setText(String.valueOf(germplasmList.getListEntries().size()));
                this.jTextFieldSelectedEntriesFemale.setText(String.valueOf(this.jTableEntriesExcelFemale.getSelectedRowCount()));
                break;
            case 1://male
                this.jTextFieldListEntriesMale.setText(String.valueOf(germplasmList.getListEntries().size()));
                this.jTextFieldSelectedFinalMale.setText(String.valueOf(this.jTableEntriesExcelMale.getSelectedRowCount()));
                break;

        }


        rowListExcel = gat.getMappedColumns(columnList, germplasmList);
        tableModel = new GermplasmEntriesTableModel(factores, rowListExcel);

        tabla.setModel(tableModel);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int col = 0; col < tabla.getColumnCount(); col++) {
            tabla.getColumnModel().getColumn(col).setCellRenderer(tcr);
        }
    }

    private void loadFactors() {

        factores = new ArrayList<Factor>();
        Factor factor = new Factor();
        factor.setFactorName("ENTRY");
        factor.setProperty("GERMPLASM ENTRY");
        factor.setScale("NUMBER");
        factor.setDataType("N");
        factores.add(factor);

        factor = new Factor();
        factor.setFactorName("DESIG");
        factor.setProperty("GERMPLASM ID");
        factor.setScale("DBCV");
        factor.setDataType("C");
        factores.add(factor);

        factor = new Factor();
        factor.setFactorName("GID");
        factor.setProperty("GERMPLASM ID");
        factor.setScale("DBID");
        factor.setDataType("N");
        factores.add(factor);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRemoveFemaleDb;
    private javax.swing.JButton btnRemoveFemaleExcel;
    private javax.swing.JButton btnRemoveMaleDb;
    private javax.swing.JPanel btnRemoveMaleExcel;
    private javax.swing.JButton btnSearchFemaleList;
    private javax.swing.JButton btnSearchMale;
    private javax.swing.JComboBox cboGermplasmListFemale;
    private javax.swing.JComboBox cboGermplasmListMale;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonBrowseExcelFemale;
    private javax.swing.JButton jButtonBrowseExcelMale;
    private javax.swing.JButton jButtonCross;
    private javax.swing.JButton jButtonLoadExcelScript;
    private javax.swing.JButton jButtonSaveCross;
    private javax.swing.JCheckBox jChkLeading;
    private javax.swing.JComboBox jComboBoxConvection;
    private javax.swing.JComboBox jComboBoxMethods;
    private com.toedter.calendar.JDateChooser jDateChooserStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelEntries;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollEntiresExcelFemale;
    private javax.swing.JScrollPane jScrollEntiresExcelMale;
    private javax.swing.JScrollPane jScrollEntriesDbFemale;
    private javax.swing.JScrollPane jScrollEntriesDbMale;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToolBar.Separator jSeparator10;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JTabbedPane jTabbedPaneFemale;
    private javax.swing.JTabbedPane jTabbedPaneMale;
    private javax.swing.JTable jTableEntriesDBFemale;
    private javax.swing.JTable jTableEntriesDBMale;
    private javax.swing.JTable jTableEntriesExcelFemale;
    private javax.swing.JTable jTableEntriesExcelMale;
    private javax.swing.JTable jTableFinalList;
    private javax.swing.JTextArea jTextAreaPathFemale;
    private javax.swing.JTextArea jTextAreaPathMale;
    private javax.swing.JTextField jTextFieldDescription;
    private javax.swing.JTextField jTextFieldListEntriesFemale;
    private javax.swing.JTextField jTextFieldListEntriesMale;
    private javax.swing.JTextField jTextFieldListName;
    private javax.swing.JTextField jTextFieldSelectedEntriesFemale;
    private javax.swing.JTextField jTextFieldSelectedFinalMale;
    private javax.swing.JTextField jTextPrefix;
    private javax.swing.JTextField jTextSuffix;
    private javax.swing.JLabel jlblDate;
    private javax.swing.JLabel jlblDescription;
    private javax.swing.JLabel jlblEntries;
    private javax.swing.JLabel jlblListName;
    private javax.swing.JLabel lblCrossPrefix;
    private javax.swing.JLabel lblNumericFieldWidth;
    private javax.swing.JLabel lblStartNo;
    private javax.swing.JLabel lblSuffix;
    private javax.swing.JSpinner spnNumFieldWidth;
    private javax.swing.JSpinner spnStartNo;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
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

    private void addDrag() {
    }

    private void fillMethods() {

        jComboBoxMethods.removeAllItems();

        List<Methods> methodsList = AppServicesProxy.getDefault().appServices().getMethodsList();

        List<String> sortedMethods = new ArrayList<String>();
        String selectedMethodName = "";

        methodsInCombo = new ArrayList<Methods>();

        for (Methods methods : methodsList) {
            if (methods.getMtype().equals("GEN")) {
                sortedMethods.add(methods.getMname().toUpperCase());
                methodsInCombo.add(methods);
                if (methods.getMid().intValue() == DEFAULT_CROSS) {
                    selectedMethodName = methods.getMname().toUpperCase();
                }
            }
        }

        Collections.sort(sortedMethods);

        int defaultSelectedIndex = 0;
        int counter = -1;


        for (String methodName : sortedMethods) {
            counter++;
            jComboBoxMethods.addItem(methodName);
            if (methodName.equals(selectedMethodName)) {
                defaultSelectedIndex = counter;
            }
        }
        jComboBoxMethods.setEditable(false);

        //this.jComboBoxMethods.setSelectedItem("SINGLE CROSS");
        this.jComboBoxMethods.setSelectedIndex(defaultSelectedIndex);

    }

    public void ajustaColumnsTable(JTable table) {
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

    private boolean isMoreRows(HSSFRow rowData) {
        boolean result = true;

        if (rowData == null) {
            return false;
        }

        if (rowData.getCell(ENT) == null) {
            return false;
        }

        String value = String.valueOf(rowData.getCell(ENT).getNumericCellValue());

        if (value == null) {
            result = false;
        } else if (value.trim().isEmpty()) {
            result = false;
        }



        return result;
    }

    private Integer getIntValueFromCell(HSSFCell cellData) {
        Integer result = 0;

        String cellValue = null;

        switch (cellData.getCellType()) {

            case HSSFCell.CELL_TYPE_STRING:
                cellValue = cellData.getStringCellValue();
                break;

            case HSSFCell.CELL_TYPE_NUMERIC:
                cellValue = String.valueOf((int) cellData.getNumericCellValue());
                break;
        }



        if (cellValue == null) {
            result = null;
        } else {
            try {
                result = Integer.parseInt(cellValue);
            } catch (Exception e) {
                result = null;
            }
        }

        return result;
    }

    public void ajustaColumnsTable(JTable table, int margin) {
        for (int c = 0; c < table.getColumnCount(); c++) {
            ajustaColumn(table, c, 2);
        }
    }

    public void enabledComponentsForWheat(boolean habilitado) {


        jTabbedPaneFemale.setEnabled(habilitado);
        jTabbedPaneMale.setEnabled(habilitado);

        jButtonCross.setEnabled(habilitado);

        cboGermplasmListFemale.setEnabled(habilitado);

        jScrollEntriesDbFemale.setEnabled(habilitado);
        jTableEntriesDBFemale.setEnabled(habilitado);
        jTextAreaPathFemale.setEnabled(habilitado);
        jButtonBrowseExcelFemale.setEnabled(habilitado);
        jScrollEntiresExcelFemale.setEnabled(habilitado);

        cboGermplasmListMale.setEnabled(habilitado);

        jScrollEntriesDbMale.setEnabled(habilitado);
        jTableEntriesDBMale.setEnabled(habilitado);
        jTextAreaPathMale.setEnabled(habilitado);
        jButtonBrowseExcelMale.setEnabled(habilitado);
        jScrollEntiresExcelMale.setEnabled(habilitado);

        btnRemoveFemaleDb.setEnabled(habilitado);
        btnRemoveMaleDb.setEnabled(habilitado);


        jButtonLoadExcelScript.setVisible(!habilitado);

        jTextPrefix.setEnabled(habilitado);

        jTextPrefix.setVisible(habilitado);
        lblCrossPrefix.setVisible(habilitado);
        spnStartNo.setVisible(habilitado);
        jTextSuffix.setVisible(habilitado);
        lblSuffix.setVisible(habilitado);
        lblStartNo.setVisible(habilitado);
        jButtonCross.setVisible(habilitado);
        lblNumericFieldWidth.setVisible(habilitado);
        spnNumFieldWidth.setVisible(habilitado);
        jChkLeading.setVisible(habilitado);
        jLabel19.setVisible(habilitado);
        jComboBoxMethods.setVisible(habilitado);

        spnStartNo.setEnabled(habilitado);
        jTextSuffix.setEnabled(habilitado);
        jChkLeading.setEnabled(habilitado);
        spnNumFieldWidth.setEnabled(habilitado);

    }

    private void checkConvection() {
        assignModels();
        switch (jComboBoxConvection.getSelectedIndex()) {
            case 0:
                enabledComponentsForWheat(false);
                break;
            case 1:
                enabledComponentsForWheat(true);
                break;
            case 2:
                enabledComponentsForWheat(true);
                break;
        }
        jTabbedPaneFemale.setSelectedIndex(0);
        jTabbedPaneMale.setSelectedIndex(0);
        cboGermplasmListFemale.setSelectedIndex(0);
        cboGermplasmListMale.setSelectedIndex(0);
        jTextAreaPathFemale.setText("");
        jTextAreaPathMale.setText("");
        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTableFinalList.setModel(modelo);
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

    private void removeFemaleFromDb() {
        if (jTableEntriesDBFemale.getSelectedRows().length == 0) {
            return;
        }

        int[] seleccionados = this.jTableEntriesDBFemale.getSelectedRows();
        GermplasmEntriesTableModel tableModel = (GermplasmEntriesTableModel) jTableEntriesDBFemale.getModel();

        for (int i = 0; i < seleccionados.length; i++) {
            tableModel.removeRow(seleccionados[i] - i);
        }

        this.jTextFieldListEntriesFemale.setText(String.valueOf(tableModel.getRowCount()));

    }

    private void removeMaleFromDb() {
        if (jTableEntriesDBMale.getSelectedRows().length == 0) {
            return;
        }

        int[] seleccionados = this.jTableEntriesDBMale.getSelectedRows();
        GermplasmEntriesTableModel tableModel = (GermplasmEntriesTableModel) jTableEntriesDBMale.getModel();

        for (int i = 0; i < seleccionados.length; i++) {
            tableModel.removeRow(seleccionados[i] - i);
        }

        this.jTextFieldListEntriesMale.setText(String.valueOf(tableModel.getRowCount()));
    }

    private void removeFemaleFromExcel() {
        if (jTableEntriesExcelFemale.getSelectedRows().length == 0) {
            return;
        }

        int[] seleccionados = this.jTableEntriesExcelFemale.getSelectedRows();
        GermplasmEntriesTableModel tableModel = (GermplasmEntriesTableModel) jTableEntriesExcelFemale.getModel();

        for (int i = 0; i < seleccionados.length; i++) {
            tableModel.removeRow(seleccionados[i] - i);
        }

        this.jTextFieldListEntriesFemale.setText(String.valueOf(tableModel.getRowCount()));

    }

    private void removeMaleFromExcel() {
        if (jTableEntriesExcelMale.getSelectedRows().length == 0) {
            return;
        }

        int[] seleccionados = this.jTableEntriesExcelMale.getSelectedRows();
        GermplasmEntriesTableModel tableModel = (GermplasmEntriesTableModel) jTableEntriesExcelMale.getModel();

        for (int i = 0; i < seleccionados.length; i++) {
            tableModel.removeRow(seleccionados[i] - i);
        }

        this.jTextFieldListEntriesMale.setText(String.valueOf(tableModel.getRowCount()));
    }

    private Methods getSelectedMethod() {

        Methods selectedMethod = null;
        String methodName = (String) jComboBoxMethods.getSelectedItem();
        for (Methods methods : methodsInCombo) {

            if (methods.getMname().toUpperCase().equals(methodName.toUpperCase())) {
                selectedMethod = methods;
            }
        }
        return selectedMethod;
    }

    private String getStringValueFromCell(HSSFCell cellData) {

        String cellValue = null;

        switch (cellData.getCellType()) {

            case HSSFCell.CELL_TYPE_STRING:
                cellValue = cellData.getStringCellValue();
                break;

            case HSSFCell.CELL_TYPE_NUMERIC:
                cellValue = String.valueOf((int) cellData.getNumericCellValue());
                break;
        }

        if (cellValue == null) {
            cellValue = "";
        }

        return cellValue;
    }

    private boolean isValidExcelWheatFile(File selectedFile) {

        boolean valid = false;
        int check = 0;

        changeCursorWaitStatus(true);

        try {

            InputStream inputStream = new FileInputStream(selectedFile);
            HSSFWorkbook excelBook = new HSSFWorkbook(inputStream);

            HSSFSheet sheet = excelBook.getSheetAt(0);
            HSSFCell cellData;
            HSSFRow rowData;

            rowData = sheet.getRow(0); //HEADERS

            cellData = rowData.getCell(ENT);
            if (getStringValueFromCell(cellData).equals("ENT")) {
                check++;
            } else {

                NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.notValidENT"), NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
                return false;
            }
            cellData = rowData.getCell(CID);
            if (getStringValueFromCell(cellData).equals("CID")) {
                check++;
            } else {
                NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.notValidCID"), NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
                return false;
            }

            cellData = rowData.getCell(SID);
            if (getStringValueFromCell(cellData).equals("SID")) {
                check++;
            } else {
                NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.notValidSID"), NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
                return false;
            }
            cellData = rowData.getCell(FTID);
            if (getStringValueFromCell(cellData).equals("FTID")) {
                check++;
            } else {
                NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.notValidFTID"), NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
                return false;
            }
            cellData = rowData.getCell(FOCC);
            if (getStringValueFromCell(cellData).equals("FOCC")) {
                check++;
            } else {
                NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.notValidFOCC"), NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
                return false;
            }

            cellData = rowData.getCell(FENT);
            if (getStringValueFromCell(cellData).equals("FENT")) {
                check++;
            } else {
                NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.notValidFENT"), NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
                return false;
            }

            cellData = rowData.getCell(MTID);
            if (getStringValueFromCell(cellData).equals("MTID")) {
                check++;
            } else {
                NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.notValidMTID"), NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
                return false;
            }
            cellData = rowData.getCell(MOCC);
            if (getStringValueFromCell(cellData).equals("MOCC")) {
                check++;
            } else {
                NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.notValidMOCC"), NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
                return false;
            }

            cellData = rowData.getCell(MENT);
            if (getStringValueFromCell(cellData).equals("MENT")) {
                check++;
            } else {
                NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.notValidMENT"), NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
                return false;
            }


            cellData = rowData.getCell(MEGA);
            if (getStringValueFromCell(cellData).equals("MEGA")) {
                check++;
            } else {
                NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.notValidMEGA"), NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
                return false;
            }

            cellData = rowData.getCell(CROSSTYPE);
            if (getStringValueFromCell(cellData).equals("CROSSTYPE")) {
                check++;
            } else {
                NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.notValidCROSS"), NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
                return false;
            }

            cellData = rowData.getCell(TYPE);
            if (getStringValueFromCell(cellData).equals("TYPE")) {
                check++;
            } else {
                NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.notValidTYPE"), NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
                return false;
            }




            if (check == 12) {
                valid = true;
            }

            changeCursorWaitStatus(false);

        } catch (Exception e) {

            System.out.println("ERROR AL VALIDAR EXCEL: " + e);
            NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(nurseryManagerTopComponent.class, "nurseryManagerTopComponent.notValidColumns"), NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            changeCursorWaitStatus(false);
            valid = false;
        }

        return valid;
    }

    private String giveMaxString(int maximo) {
        String cadena;
        DecimalFormat format = new DecimalFormat("000000");
        cadena = format.format(maximo);
        return cadena;
    }

    private void searchFemaleList() {
        SelectListDialog selectListDialog = new SelectListDialog();
        selectListDialog.showSearchDialog();
        selectListDialog.populateComboListNames(cboGermplasmListFemale);
        if (selectListDialog.isListSelected()) {
            try {
                GermplasmListReader germplasmListReader = new GermplasmListReaderImpl();
                GermplasmList germplasmList = germplasmListReader.getGermPlasmListFromDB(selectListDialog.getSeledtedListnms().getListid());
                setGermplasmListIntoTable(germplasmList, this.jTableEntriesDBFemale, 0, 0);

            } catch (Exception ex) {
                System.out.println("ERROR AL LEER EXCEL GERMPLASM ENTRIES DB: " + ex);
            }
        } else {
            GermplasmEntriesTableModel modeloTablaEntries = new GermplasmEntriesTableModel();
            this.jTableEntriesDBFemale.setModel(modeloTablaEntries);
            this.jTextAreaPathFemale.setText("");
            this.jTextFieldListEntriesFemale.setText("0");
        }

    }

    private void searchMaleList() {
        SelectListDialog selectListDialog = new SelectListDialog();
        selectListDialog.showSearchDialog();
        selectListDialog.populateComboListNames(cboGermplasmListMale);
        if (selectListDialog.isListSelected()) {
            try {
                GermplasmListReader germplasmListReader = new GermplasmListReaderImpl();
                GermplasmList germplasmList = germplasmListReader.getGermPlasmListFromDB(selectListDialog.getSeledtedListnms().getListid());
                setGermplasmListIntoTable(germplasmList, this.jTableEntriesDBMale, 1, 1);

            } catch (Exception ex) {
                System.out.println("ERROR AL LEER EXCEL GERMPLASM ENTRIES DB: " + ex);
            }
        } else {
            GermplasmEntriesTableModel modeloTablaEntries = new GermplasmEntriesTableModel();
            this.jTableEntriesDBMale.setModel(modeloTablaEntries);
            this.jTextAreaPathMale.setText("");
            this.jTextFieldListEntriesMale.setText("0");
        }
    }

    public void selectOptionDB() {
        TypeDB tipo = AppServicesProxy.getDefault().appServices().getTypeDB();

        if (tipo == TypeDB.IWIS) {
            this.jComboBoxConvection.setSelectedIndex(0);

        } else if (tipo == TypeDB.IMIS) {
            this.jComboBoxConvection.setSelectedIndex(1);

        } else {
            this.jComboBoxConvection.setSelectedIndex(2);
        }
    }

    /**
     * Gets an string between html code
     *
     * @param value String value to enclose
     * @param female if is female then use purple color, else use blue
     * @return
     */
    private String getHtmlForText(String value, boolean female) {
        StringBuilder formatedText = new StringBuilder();
        if (value != null) {
            formatedText.append("<html> <font color='");
            if (female) {
                formatedText.append("purple");
            } else {
                formatedText.append("blue");
            }
            formatedText.append("'>").append(value);
            formatedText.append("</font></html>");
        }
        return formatedText.toString();
    }
}