package ibfb.germplasmlist.core;

import ibfb.domain.core.Factor;
import ibfb.domain.core.GermplasmList;
import ibfb.domain.core.ListOfEntries;
import ibfb.domain.core.Workbook;
import ibfb.germplasmlist.filters.ExcelFiltro;
import ibfb.germplasmlist.methods.MethodsClass;
import ibfb.germplasmlist.models.GermplasmEntriesTableModel;
import ibfb.germplasmlist.models.GermplasmTransferHandlerSelection;
import ibfb.germplasmlist.models.RemoveGermplasmTransferHandler;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.ConvertUtils;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.cimmyt.cril.ibwb.domain.*;
import org.cimmyt.cril.ibwb.domain.constants.TypeDB;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.Mutex;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

@ConvertAsProperties(dtd = "-//ibfb.germplasmlist.core//nurseryManager//EN",
autostore = false)
@TopComponent.Description(preferredID = "nurseryManagerTopComponentok",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
//@ActionID(category = "Window", id = "ibfb.germplasmlist.core.nurseryManagerTopComponent")
//@ActionReference(path = "Menu/Window" /*
// * , position = 333
// */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_nurseryManagerActionok",
preferredID = "nurseryManagerTopComponent")
@Messages({
    "CTL_nurseryManagerActionok=CROSSING MANAGER",
    "CTL_nurseryManagerTopComponentok=CROSSING MANAGER",
    "HINT_nurseryManagerTopComponentok=CROSSING MANAGER"
})
public final class SelectionTopComponent extends TopComponent {

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
//    public int FTID = 0;
//    public int FOCC = 1;
//    public int FENT = 2;
//    public int MTID = 3;
//    public int MOCC = 4;
//    public int MENT = 5;
//    public int SEL = 3;
    public int ENT = 0;
    public int CID = 1;
    public int SID = 2;
    public int STID = 3;
    public int SOCC = 4;
    public int SENT = 5;
    public int TYPE = 6;
    public int NSEL = 7;
    private String[] headers = {"ENTRY", "CROSS", "GID", "METHOD", "FDESIG", "FGID", "MDESIG", "MGID"};
    private String[] headersScript = {"ENTRY", "SELECTION", "SOURCE", "DESIG", "GID", "METHOD"};
    MethodsClass metodos = new MethodsClass();
    /**
     * Methods in Combo box, used to retrieve selected method
     */
    private List<Methods> methodsInCombo;

    public SelectionTopComponent() {
        initComponents();
        setName(Bundle.CTL_nurseryManagerTopComponent());
        setToolTipText(Bundle.HINT_nurseryManagerTopComponent());
        toAdd = new ArrayList<List<Object>>();
        fillComboListNames();
        loadFactors();
        addListener();
        fillMethods();
        addDrag();
        this.jButtonSaveCrossList.setEnabled(false);
        this.jTabbedPane1.setEnabledAt(0, false);
        this.jTabbedPane1.setSelectedIndex(1);
             
        // by default select other crops
        jComboBoxConvectionselection.setSelectedIndex(2);
        
        checkConvection();

    }

    private void fillComboListNames() {
        List<Listnms> germplasmList = AppServicesProxy.getDefault().appServices().getListnmsList();
        for (Listnms list : germplasmList) {
            cboGermplasmListFemale.addItem(list);
            cboGermplasmListMale.addItem(list);
            cboGermplasmListSelect.addItem(list);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldListEntriesFemale = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldSelectedEntriesFemale = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldListEntriesMale = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldSelectedFinalMale = new javax.swing.JTextField();
        jTabbedPaneMale = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboGermplasmListMale = new javax.swing.JComboBox();
        jScrollEntriesDbMale = new javax.swing.JScrollPane();
        jTableEntriesDBMale = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaPathMale = new javax.swing.JTextArea();
        jButtonBrowseExcelMale = new javax.swing.JButton();
        jScrollEntiresExcelMale = new javax.swing.JScrollPane();
        jTableEntriesExcelMale = new javax.swing.JTable();
        jTabbedPaneFemale = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cboGermplasmListFemale = new javax.swing.JComboBox();
        jScrollEntriesDbFemale = new javax.swing.JScrollPane();
        jTableEntriesDBFemale = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaPathFemale = new javax.swing.JTextArea();
        jButtonBrowseExcelFemale = new javax.swing.JButton();
        jScrollEntiresExcelFemale = new javax.swing.JScrollPane();
        jTableEntriesExcelFemale = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButtonCross = new javax.swing.JButton();
        jButtonSaveCrossList = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxConvection = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jComboBoxMethods = new javax.swing.JComboBox();
        jButtonLoadExcelScript = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableFinalList = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPaneSelect = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        cboGermplasmListSelect = new javax.swing.JComboBox();
        jScrollEntriesDbSelect = new javax.swing.JScrollPane();
        jTableEntriesDBSelect = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaPathSelect = new javax.swing.JTextArea();
        jButtonBrowseExcelSelect = new javax.swing.JButton();
        jScrollEntiresExcelSelect = new javax.swing.JScrollPane();
        jTableEntriesExcelSelect = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableFinalListSelection = new javax.swing.JTable();
        jPanelSelection = new javax.swing.JPanel();
        jScrollFinalListSelect = new javax.swing.JScrollPane();
        jTableFinalListSelect = new javax.swing.JTable();
        jCheckBoxSameValue = new javax.swing.JCheckBox();
        jSpinnerEntries = new javax.swing.JSpinner();
        jToolBar4 = new javax.swing.JToolBar();
        jTextFieldTotalEntries = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JToolBar.Separator();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldSelectedFinal = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jButtonDoSelection = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jComboBoxConvectionselection = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        jComboBoxMethodsSelection = new javax.swing.JComboBox();
        jButtonSelExcelScript = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jTextFieldListName = new javax.swing.JTextField();
        jButtonSaveSelection = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jTextFieldDescription = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabelEntriesSelection = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldListEntriesSelect = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldSelectedEntriesSelect = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jLabel8.text")); // NOI18N
        jToolBar2.add(jLabel8);

        jTextFieldListEntriesFemale.setEditable(false);
        jTextFieldListEntriesFemale.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldListEntriesFemale.setText(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jTextFieldListEntriesFemale.text")); // NOI18N
        jToolBar2.add(jTextFieldListEntriesFemale);
        jToolBar2.add(jSeparator5);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jLabel9.text")); // NOI18N
        jToolBar2.add(jLabel9);

        jTextFieldSelectedEntriesFemale.setEditable(false);
        jTextFieldSelectedEntriesFemale.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldSelectedEntriesFemale.setText(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jTextFieldSelectedEntriesFemale.text")); // NOI18N
        jToolBar2.add(jTextFieldSelectedEntriesFemale);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jLabel7.text")); // NOI18N
        jToolBar1.add(jLabel7);

        jTextFieldListEntriesMale.setEditable(false);
        jTextFieldListEntriesMale.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldListEntriesMale.setText(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jTextFieldListEntriesMale.text")); // NOI18N
        jToolBar1.add(jTextFieldListEntriesMale);
        jToolBar1.add(jSeparator6);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jLabel11.text")); // NOI18N
        jToolBar1.add(jLabel11);

        jTextFieldSelectedFinalMale.setEditable(false);
        jTextFieldSelectedFinalMale.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldSelectedFinalMale.setText(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jTextFieldSelectedFinalMale.text")); // NOI18N
        jToolBar1.add(jTextFieldSelectedFinalMale);

        jTabbedPaneMale.setBackground(new java.awt.Color(0, 102, 255));
        jTabbedPaneMale.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jTabbedPaneMale.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 204))); // NOI18N
        jTabbedPaneMale.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneMaleStateChanged(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/database.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jLabel1.text")); // NOI18N

        cboGermplasmListMale.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT ONE..." }));
        cboGermplasmListMale.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboGermplasmListMaleItemStateChanged(evt);
            }
        });

        jScrollEntriesDbMale.setToolTipText(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jScrollEntriesDbMale.toolTipText")); // NOI18N

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
                        .addComponent(cboGermplasmListMale, 0, 325, Short.MAX_VALUE))
                    .addComponent(jScrollEntriesDbMale, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(cboGermplasmListMale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollEntriesDbMale, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneMale.addTab(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/excelFile.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jLabel2.text")); // NOI18N

        jTextAreaPathMale.setColumns(20);
        jTextAreaPathMale.setLineWrap(true);
        jTextAreaPathMale.setRows(5);
        jTextAreaPathMale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextAreaPathMaleMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextAreaPathMale);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonBrowseExcelMale, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jButtonBrowseExcelMale.text")); // NOI18N
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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollEntiresExcelMale, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBrowseExcelMale, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonBrowseExcelMale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollEntiresExcelMale, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneMale.addTab(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jPanel7.TabConstraints.tabTitle"), jPanel7); // NOI18N

        jTabbedPaneFemale.setBackground(new java.awt.Color(255, 153, 255));
        jTabbedPaneFemale.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jTabbedPaneFemale.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 0, 102))); // NOI18N
        jTabbedPaneFemale.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneFemaleStateChanged(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/database.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jLabel5.text")); // NOI18N

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

        jScrollEntriesDbFemale.setToolTipText(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jScrollEntriesDbFemale.toolTipText")); // NOI18N

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollEntriesDbFemale, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboGermplasmListFemale, 0, 333, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(cboGermplasmListFemale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollEntriesDbFemale, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneFemale.addTab(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jPanel5.TabConstraints.tabTitle"), jPanel5); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/excelFile.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jLabel6.text")); // NOI18N

        jTextAreaPathFemale.setColumns(20);
        jTextAreaPathFemale.setLineWrap(true);
        jTextAreaPathFemale.setRows(5);
        jTextAreaPathFemale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextAreaPathFemaleMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTextAreaPathFemale);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonBrowseExcelFemale, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jButtonBrowseExcelFemale.text")); // NOI18N
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

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollEntiresExcelFemale, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBrowseExcelFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(jScrollEntiresExcelFemale, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneFemale.addTab(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jPanel8.TabConstraints.tabTitle"), jPanel8); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        org.openide.awt.Mnemonics.setLocalizedText(jButtonCross, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jButtonCross.text")); // NOI18N
        jButtonCross.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrossActionPerformed(evt);
            }
        });

        jButtonSaveCrossList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/save.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSaveCrossList, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jButtonSaveCrossList.text")); // NOI18N
        jButtonSaveCrossList.setToolTipText(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jButtonSaveCrossList.toolTipText")); // NOI18N
        jButtonSaveCrossList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveCrossListActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jLabel4.text")); // NOI18N

        jComboBoxConvection.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CIMMYT WHEAT", "CIMMYT MAIZE", "OTHER CROPS" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel19, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jLabel19.text")); // NOI18N

        jComboBoxMethods.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        org.openide.awt.Mnemonics.setLocalizedText(jButtonLoadExcelScript, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jButtonLoadExcelScript.text")); // NOI18N
        jButtonLoadExcelScript.setToolTipText(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jButtonLoadExcelScript.toolTipText")); // NOI18N
        jButtonLoadExcelScript.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadExcelScriptActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSaveCrossList, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCross, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxConvection, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxMethods, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLoadExcelScript, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxConvection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxMethods, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonCross, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonLoadExcelScript, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jButtonSaveCrossList)
                .addGap(17, 17, 17))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonCross, jButtonSaveCrossList});

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jScrollPane3.border.title"))); // NOI18N

        jTableFinalList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableFinalList.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane3.setViewportView(jTableFinalList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jTabbedPaneFemale))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTabbedPaneMale)))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTabbedPaneFemale, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jTabbedPaneMale, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTabbedPaneFemale, jTabbedPaneMale});

        jTabbedPaneMale.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "nurseryManagerTopComponent.jTabbedPaneMale.AccessibleContext.accessibleName")); // NOI18N

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jTabbedPaneSelect.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jTabbedPaneSelect.border.title"))); // NOI18N
        jTabbedPaneSelect.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneSelectStateChanged(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/database.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jLabel12.text")); // NOI18N

        cboGermplasmListSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT ONE..." }));
        cboGermplasmListSelect.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboGermplasmListSelectItemStateChanged(evt);
            }
        });

        jScrollEntriesDbSelect.setToolTipText(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jScrollEntriesDbSelect.toolTipText")); // NOI18N

        jTableEntriesDBSelect.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableEntriesDBSelect.setDragEnabled(true);
        jTableEntriesDBSelect.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableEntriesDBSelect.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableEntriesDBSelectPropertyChange(evt);
            }
        });
        jScrollEntriesDbSelect.setViewportView(jTableEntriesDBSelect);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jScrollEntriesDbSelect, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboGermplasmListSelect, 0, 370, Short.MAX_VALUE))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(cboGermplasmListSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollEntriesDbSelect, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneSelect.addTab(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jPanel9.TabConstraints.tabTitle"), jPanel9); // NOI18N

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/excelFile.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jLabel13.text")); // NOI18N

        jTextAreaPathSelect.setColumns(20);
        jTextAreaPathSelect.setLineWrap(true);
        jTextAreaPathSelect.setRows(5);
        jTextAreaPathSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextAreaPathSelectMousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(jTextAreaPathSelect);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonBrowseExcelSelect, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jButtonBrowseExcelSelect.text")); // NOI18N
        jButtonBrowseExcelSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBrowseExcelSelectActionPerformed(evt);
            }
        });

        jTableEntriesExcelSelect.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableEntriesExcelSelect.setDragEnabled(true);
        jTableEntriesExcelSelect.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableEntriesExcelSelect.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableEntriesExcelSelectPropertyChange(evt);
            }
        });
        jScrollEntiresExcelSelect.setViewportView(jTableEntriesExcelSelect);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollEntiresExcelSelect, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBrowseExcelSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, 0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonBrowseExcelSelect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollEntiresExcelSelect, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneSelect.addTab(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jPanel10.TabConstraints.tabTitle"), jPanel10); // NOI18N

        jScrollPane7.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jScrollPane7.border.title"))); // NOI18N
        jScrollPane7.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jScrollPane7PropertyChange(evt);
            }
        });

        jTableFinalListSelection.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableFinalListSelection.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTableFinalListSelection.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableFinalListSelectionPropertyChange(evt);
            }
        });
        jScrollPane7.setViewportView(jTableFinalListSelection);

        jPanelSelection.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jPanelSelection.border.title"))); // NOI18N

        jScrollFinalListSelect.setToolTipText(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jScrollFinalListSelect.toolTipText")); // NOI18N

        jTableFinalListSelect.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableFinalListSelect.setDragEnabled(true);
        jTableFinalListSelect.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableFinalListSelect.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableFinalListSelectPropertyChange(evt);
            }
        });
        jScrollFinalListSelect.setViewportView(jTableFinalListSelect);

        jCheckBoxSameValue.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxSameValue, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jCheckBoxSameValue.text")); // NOI18N
        jCheckBoxSameValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSameValueActionPerformed(evt);
            }
        });

        jSpinnerEntries.setModel(new javax.swing.SpinnerNumberModel(1, 1, 1000, 1));
        jSpinnerEntries.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerEntriesStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelSelectionLayout = new javax.swing.GroupLayout(jPanelSelection);
        jPanelSelection.setLayout(jPanelSelectionLayout);
        jPanelSelectionLayout.setHorizontalGroup(
            jPanelSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSelectionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollFinalListSelect, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                    .addGroup(jPanelSelectionLayout.createSequentialGroup()
                        .addComponent(jCheckBoxSameValue, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerEntries, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelSelectionLayout.setVerticalGroup(
            jPanelSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSelectionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCheckBoxSameValue)
                    .addComponent(jSpinnerEntries, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollFinalListSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jToolBar4.setFloatable(false);
        jToolBar4.setRollover(true);

        jTextFieldTotalEntries.setEditable(false);
        jTextFieldTotalEntries.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldTotalEntries.setText(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jTextFieldTotalEntries.text")); // NOI18N
        jToolBar4.add(jTextFieldTotalEntries);
        jToolBar4.add(jSeparator9);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel18, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jLabel18.text")); // NOI18N
        jToolBar4.add(jLabel18);

        jTextFieldSelectedFinal.setEditable(false);
        jTextFieldSelectedFinal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldSelectedFinal.setText(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jTextFieldSelectedFinal.text")); // NOI18N
        jToolBar4.add(jTextFieldSelectedFinal);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonDoSelection.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jButtonDoSelection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/sel.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonDoSelection, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jButtonDoSelection.text")); // NOI18N
        jButtonDoSelection.setToolTipText(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jButtonDoSelection.toolTipText")); // NOI18N
        jButtonDoSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDoSelectionActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jLabel10.text")); // NOI18N

        jComboBoxConvectionselection.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CIMMYT WHEAT", "CIMMYT MAIZE", "OTHER CROPS" }));
        jComboBoxConvectionselection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxConvectionselectionActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel20, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jLabel20.text")); // NOI18N

        jComboBoxMethodsSelection.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonSelExcelScript.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/excelScript.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSelExcelScript, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jButtonSelExcelScript.text")); // NOI18N
        jButtonSelExcelScript.setToolTipText(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jButtonSelExcelScript.toolTipText")); // NOI18N
        jButtonSelExcelScript.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelExcelScriptActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/draganddrop.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jLabel3.text")); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel10))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxMethodsSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxConvectionselection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonSelExcelScript, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonDoSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(37, 37, 37))))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBoxConvectionselection, jComboBoxMethodsSelection});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxConvectionselection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxMethodsSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDoSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSelExcelScript)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel21, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jLabel21.text")); // NOI18N
        jLabel21.setAutoscrolls(true);

        jTextFieldListName.setText(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jTextFieldListName.text")); // NOI18N
        jTextFieldListName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldListNameKeyReleased(evt);
            }
        });

        jButtonSaveSelection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/save.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSaveSelection, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jButtonSaveSelection.text")); // NOI18N
        jButtonSaveSelection.setToolTipText(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jButtonSaveSelection.toolTipText")); // NOI18N
        jButtonSaveSelection.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSaveSelection.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSaveSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveSelectionActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel22, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jLabel22.text")); // NOI18N

        jTextFieldDescription.setText(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jTextFieldDescription.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel23, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jLabel23.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelEntriesSelection, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jLabelEntriesSelection.text")); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldListName, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelEntriesSelection)
                .addGap(51, 51, 51)
                .addComponent(jButtonSaveSelection)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23)
                        .addComponent(jLabelEntriesSelection))
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22))
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(jTextFieldListName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4))
            .addComponent(jButtonSaveSelection)
        );

        org.openide.awt.Mnemonics.setLocalizedText(jLabel15, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jLabel15.text")); // NOI18N

        jTextFieldListEntriesSelect.setEditable(false);
        jTextFieldListEntriesSelect.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldListEntriesSelect.setText(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jTextFieldListEntriesSelect.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel16, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jLabel16.text")); // NOI18N

        jTextFieldSelectedEntriesSelect.setEditable(false);
        jTextFieldSelectedEntriesSelect.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldSelectedEntriesSelect.setText(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jTextFieldSelectedEntriesSelect.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel17, org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jLabel17.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTabbedPaneSelect)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(0, 0, 0)
                                .addComponent(jTextFieldListEntriesSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel16)
                                .addGap(0, 0, 0)
                                .addComponent(jTextFieldSelectedEntriesSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanelSelection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(6, 6, 6))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToolBar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane7)
                        .addContainerGap())
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTabbedPaneSelect)
                            .addComponent(jPanelSelection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldListEntriesSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldSelectedEntriesSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16)))
                            .addComponent(jToolBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(SelectionTopComponent.class, "SelectionTopComponent.jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveSelectionList() {
        changeCursorWaitStatus(true);
        Listnms listnms = new Listnms();
        // listnms.setListname(this.jTextFieldListName.getText());
        listnms.setListname(this.jTextFieldListName.getText());
        listnms.setListdesc(jTextFieldDescription.getText());

        int fecha = ConvertUtils.getDateAsInteger(new java.util.Date());
        listnms.setListdate(fecha);
        listnms.setListtype(Listnms.LIST_TYPE_LIST);
        listnms.setListuid(0);
        //   listnms.setListdesc(this.jTextFieldDescription.getText());
        listnms.setLhierarchy(0);
        listnms.setListstatus(1);
        AppServicesProxy.getDefault().appServices().addListnms(listnms);
        List<Listdata> dataList = new ArrayList<Listdata>();

        //int gid = findColumn("GID");
        int gid = 0;
        int desig = findColumn("DESIG");
        int entryCD = findColumn("ENTRY");

        int numberOfParents = getSelectedMethod().getMprgn();
        int selectedMethod = getSelectedMethod().getMid();

        for (int i = 0; i < jTableFinalListSelection.getRowCount(); i++) {
            Listdata listdata = new Listdata(true);
            ListdataPK pk1 = new ListdataPK(listnms.getListid(), 0);

            listdata.setListdataPK(pk1);   //*
            listdata.setEntryid(i + 1);        //*
            if (desig > 0) {
                listdata.setDesig(this.jTableFinalListSelection.getValueAt(i, desig).toString());  //*
            } else {
                listdata.setDesig("");
            }
            if (entryCD > 0) {
                listdata.setEntrycd(this.jTableFinalListSelection.getValueAt(i, entryCD).toString());
            } else {
                listdata.setEntrycd("");
            }

            listdata.setSource("SOURC1");
            listdata.setGrpname("grp");
            listdata.setLrstatus(0);      //*
            //d1.setLlrecid(0);

            listdata.setMethodId(selectedMethod);


            listdata.setGnpgs(numberOfParents);

            if (gid > 0) {
                listdata.setGid(Integer.parseInt(jTableFinalListSelection.getValueAt(i, gid).toString()));
            } else {
                listdata.setGid(0);
            }
            dataList.add(listdata);

        }

        AppServicesProxy.getDefault().appServices().addNewsGermplasm(listnms, dataList, 1);

        Integer loggedUserid = AppServicesProxy.getDefault().appServices().getLoggedUserId();
        AppServicesProxy.getDefault().appServices().addNewsGermplasm(listnms, dataList, loggedUserid);
        fillComboListNames();
        openRecentList(listnms);
        changeCursorWaitStatus(false);

    }

    private void openRecentList(Listnms listnms) {
        ListDataWindowTopComponent ldwtc = ListDataWindowTopComponent.getListDataWindowTopComponent(listnms);
        if (ldwtc == null) {
            ldwtc = new ListDataWindowTopComponent(listnms);
        }
        ldwtc.open();
        ldwtc.requestActive();

    }

    private int findColumn(String col) {
        int myCol = 0;

        try {
            myCol = jTableFinalListSelection.getTableHeader().getColumnModel().getColumnIndex(col);
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

            case 2:
                validSelection = cboGermplasmListSelect.getSelectedIndex() > 0;

                if (validSelection) {
                    try {
                        Listnms selectedList = (Listnms) cboGermplasmListSelect.getSelectedItem();
                        GermplasmList germplasmList = germplasmListReader.getGermPlasmListFromDB(selectedList.getListid());
                        setGermplasmListIntoTable(germplasmList, tabla, 0, 2);
                    } catch (Exception ex) {
                        System.out.println("ERROR AL LEER DB GERMPLASM ENTRIES: " + ex);
                    }
                } else {
                    GermplasmEntriesTableModel modeloTablaEntries = new GermplasmEntriesTableModel();
                    this.jTableEntriesDBSelect.setModel(modeloTablaEntries);
                    this.jTextAreaPathSelect.setText("");
                    this.jTextFieldListEntriesSelect.setText("0");

                }


                germplasmTransferHandler.setSourceList(rowListDB);
                germplasmTransferHandler.setSourceTable(jTableEntriesDBSelect);
                germplasmTransferHandler.setFactores(factores);

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
            case 2: //selection form
                this.jTextFieldListEntriesSelect.setText(String.valueOf(germplasmList.getListEntries().size()));

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

        ListSelectionModel cellSelectionSelectModel = jTableEntriesDBSelect.getSelectionModel();
        cellSelectionSelectModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        cellSelectionSelectModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                jTextFieldSelectedEntriesSelect.setText(String.valueOf(jTableEntriesDBSelect.getSelectedRowCount()));

            }
        });


        this.jTableEntriesDBSelect.setSelectionModel(cellSelectionSelectModel);

        //--------------------------------------------------------------------------------------------

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
//--------------------------------------------------------------------------------------------

        ListSelectionModel cellFinalSelectModel = jTableFinalListSelect.getSelectionModel();
        cellFinalSelectModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        cellFinalSelectModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                jTextFieldSelectedFinal.setText(String.valueOf(jTableFinalListSelect.getSelectedRowCount()));
            }
        });
        this.jTableFinalListSelect.setSelectionModel(cellFinalSelectModel);
        //--------------------------------------------------------------------------------------------

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
        //--------------------------------------------------------------------------------------------     
        ListSelectionModel cellSelectionExcelSelectModel = jTableEntriesExcelSelect.getSelectionModel();
        cellSelectionExcelSelectModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        cellSelectionExcelSelectModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                jTextFieldSelectedEntriesSelect.setText(String.valueOf(jTableEntriesExcelSelect.getSelectedRowCount()));
            }
        });
        this.jTableEntriesExcelSelect.setSelectionModel(cellSelectionExcelSelectModel);

    }

    private void enabledMale(boolean state) {
        this.jTabbedPaneMale.setEnabledAt(0, state);
        this.jTabbedPaneMale.setEnabledAt(1, state);
        this.cboGermplasmListMale.setEnabled(state);
        jTextAreaPathMale.setEnabled(state);
        jButtonBrowseExcelMale.setEnabled(state);
    }

    private void jButtonCrossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrossActionPerformed


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
            NotifyDescriptor d = new NotifyDescriptor.Message("Female and male list must be have the same number of entries", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            return;
        }



        if (female == 0) {
            NotifyDescriptor d = new NotifyDescriptor.Message("Female list must be have entries", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            return;
        }


        if (male == 0) {
            NotifyDescriptor d = new NotifyDescriptor.Message("Male list must be have entries", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            return;
        }




        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };
        modelo.setColumnIdentifiers(headers);
        modelo.setRowCount(female);


        for (int i = 0; i < female; i++) {

            Object fdesig = femaleModel.getValueAt(i, 1);
            Object mdesig = maleModel.getValueAt(i, 1);
            Object fgid = femaleModel.getValueAt(i, 2);
            Object mgid = maleModel.getValueAt(i, 2);

            modelo.setValueAt(i + 1, i, 0);//ENTRY
            modelo.setValueAt(fdesig + "/" + mdesig, i, 1);//CROSS
            modelo.setValueAt("Not assigned yet", i, 2);//GIG
            modelo.setValueAt("SINGLE CROSS", i, 3);//METHOD
            modelo.setValueAt(fdesig, i, 4);//FDESIG
            modelo.setValueAt(fgid, i, 5);//FGIG
            modelo.setValueAt(mdesig, i, 6);//MDESIG
            modelo.setValueAt(mgid, i, 7);//MGID        
        }




        jTableFinalList.setModel(modelo);
        ajustaColumnsTable(this.jTableFinalList);

        if (this.jTableFinalList.getRowCount() > 0) {
            this.jButtonSaveCrossList.setEnabled(true);
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

    private void cboGermplasmListSelectItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboGermplasmListSelectItemStateChanged
        readGermplsmEntriesFromDb(this.jTableEntriesDBSelect, 2);
    }//GEN-LAST:event_cboGermplasmListSelectItemStateChanged

    private void jTableEntriesDBSelectPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableEntriesDBSelectPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableEntriesDBSelectPropertyChange

    private void jTextAreaPathSelectMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaPathSelectMousePressed
        if (!this.jTextAreaPathSelect.isEnabled()) {
            return;
        }

        openFile(2);
        if (this.jTextAreaPathSelect.getText().isEmpty() == false) {
            readExcelGermplsmEntries(this.jTextAreaPathSelect.getText(), this.jTableEntriesExcelSelect, 2);
        }
    }//GEN-LAST:event_jTextAreaPathSelectMousePressed

    private void jButtonBrowseExcelSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBrowseExcelSelectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonBrowseExcelSelectActionPerformed

    private void jTableEntriesExcelSelectPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableEntriesExcelSelectPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableEntriesExcelSelectPropertyChange

    private void jTabbedPaneSelectStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPaneSelectStateChanged
        switch (jTabbedPaneSelect.getSelectedIndex()) {
            case 0:
                this.jTextFieldListEntriesSelect.setText(String.valueOf(this.jTableEntriesDBSelect.getRowCount()));
                this.jTextFieldSelectedEntriesSelect.setText(String.valueOf(this.jTableEntriesDBSelect.getSelectedRowCount()));
                isSelectionScripFile = false;

                break;
            case 1:
                this.jTextFieldListEntriesSelect.setText(String.valueOf(this.jTableEntriesExcelSelect.getRowCount()));
                this.jTextFieldSelectedEntriesSelect.setText(String.valueOf(this.jTableEntriesExcelSelect.getSelectedRowCount()));
                isSelectionScripFile = false;

                break;


    }//GEN-LAST:event_jTabbedPaneSelectStateChanged

    }

    private void jTableFinalListSelectPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableFinalListSelectPropertyChange
        this.jTextFieldTotalEntries.setText(String.valueOf(this.jTableFinalListSelect.getRowCount()));
        this.jTextFieldSelectedFinal.setText(String.valueOf(this.jTableFinalListSelect.getSelectedRowCount()));
    }//GEN-LAST:event_jTableFinalListSelectPropertyChange

    private void jButtonSaveCrossListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveCrossListActionPerformed
    }//GEN-LAST:event_jButtonSaveCrossListActionPerformed

    private void jButtonLoadExcelScriptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadExcelScriptActionPerformed

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




        processExcelSelectionFile(selectorArchivo.getSelectedFile());




    }//GEN-LAST:event_jButtonLoadExcelScriptActionPerformed

    private void processExcelSelectionFile(File archivo) {

        metodos.setConvention(jComboBoxConvectionselection.getSelectedIndex());
        metodos.setMethod(jComboBoxMethodsSelection.getSelectedIndex());
        changeCursorWaitStatus(true);

        boolean moreRowsToRead = true;
        int rowIndex = 0;
        int gms = 0;
        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
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

                cellData = rowData.getCell(TYPE);
                String type = getStringValueFromCell(cellData);


                if (type.equals("SEL")) {

                    cellData = rowData.getCell(STID);
                    int tid = getIntValueFromCell(cellData);

                    cellData = rowData.getCell(SOCC);
                    int occ = getIntValueFromCell(cellData);

                    cellData = rowData.getCell(SENT);
                    int ent = getIntValueFromCell(cellData);

                    cellData = rowData.getCell(NSEL);
                    int sel = getIntValueFromCell(cellData);

                    Germplsm selGermplsm = AppServicesProxy.getDefault().appServices().getGermplsmByTidTrialPlot(tid, occ, ent);

                    if (selGermplsm != null) {


                        Names selfiltro = new Names(true);
                        selfiltro.setGid(selGermplsm.getGid());
                        List<Names> selLista = AppServicesProxy.getDefault().appServices().getListNames(selfiltro, 0, 0, false);

                        ArrayList<String> data = metodos.giveMeDataDerivative(selLista.get(0).getNval(), sel);

                        for (int j = 0; j < data.size(); j++) {
                            gms++;
                            modelo.setRowCount(gms);
                            modelo.setValueAt(gms, gms - 1, 0);//ENTRY
                            modelo.setValueAt(j + 1, gms - 1, 1); //SELECTION
                            modelo.setValueAt(selLista.get(0).getNval(), gms - 1, 2); //SOURCE  
                            modelo.setValueAt(data.get(j), gms - 1, 3); //DESIG                         
                            modelo.setValueAt("Not assigned yet", gms - 1, 4);//GID
                            modelo.setValueAt("Single Plants Selection", gms - 1, 5);//METHOD
                        }

                    } else {
                        System.out.println("GID no encontrado");
                    }
                }

            }//end while


            this.jTableFinalListSelection.setModel(modelo);
            ajustaColumnsTable(this.jTableFinalListSelection);


            if (this.jTableFinalListSelection.getRowCount() > 0) {
                this.jButtonSaveSelection.setEnabled(true);
            }

            changeCursorWaitStatus(false);


        } catch (Exception e) {

            System.out.println("ERROR PROCESS FILE: " + e);
            changeCursorWaitStatus(false);
        }



    }

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

    private void jTableEntriesDBFemalePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableEntriesDBFemalePropertyChange
   }//GEN-LAST:event_jTableEntriesDBFemalePropertyChange

    private void cboGermplasmListFemaleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboGermplasmListFemaleItemStateChanged
        readGermplsmEntriesFromDb(this.jTableEntriesDBFemale, 0);
    }//GEN-LAST:event_cboGermplasmListFemaleItemStateChanged

    private void cboGermplasmListFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGermplasmListFemaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboGermplasmListFemaleActionPerformed

    private void jButtonDoSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDoSelectionActionPerformed

        if (jTableFinalListSelect.getRowCount() == 0) {
            return;
        }


        this.jButtonSaveSelection.setEnabled(false);

        GermplasmEntriesTableModel selectedModel = (GermplasmEntriesTableModel) this.jTableFinalListSelect.getModel();
        DefaultTableModel modeloEntries = new DefaultTableModel();

        modeloEntries.addColumn("ENTRY");
        modeloEntries.addColumn("SELECTION");
        modeloEntries.addColumn("SOURCE");
        modeloEntries.addColumn("DESIG");
        modeloEntries.addColumn("GID");
        modeloEntries.addColumn("METHOD");




        if (selectedModel.getRowCount() == 0) {
        }



        int conv = this.jComboBoxConvectionselection.getSelectedIndex();


        MethodsClass metodos = new MethodsClass();
        metodos.setConvention(conv);

        int total = 0;


        for (int i = 0; i < selectedModel.getRowCount(); i++) {

            String pedigree = selectedModel.getValueAt(i, 1).toString();
            int rep = Integer.parseInt(selectedModel.getValueAt(i, 3).toString());

            if (rep > 0) {

                ArrayList<String> data = metodos.giveMeDataDerivative(pedigree, rep);

                for (int j = 0; j < data.size(); j++) {
                    total++;
                    modeloEntries.setRowCount(total);

                    modeloEntries.setValueAt(total, total - 1, 0);//ENTRY
                    modeloEntries.setValueAt(j + 1, total - 1, 1);//SEL 
                    modeloEntries.setValueAt(pedigree, total - 1, 2);//SOURCE 
                    modeloEntries.setValueAt(data.get(j), total - 1, 3);//DESIG GENERATED
                    modeloEntries.setValueAt("Not asigned yet", total - 1, 4);//GID
                    modeloEntries.setValueAt("Single Plants Selection", total - 1, 5);//METHOD

                }
            }


        }


        this.jTableFinalListSelection.setModel(modeloEntries);
        ajustaColumnsTable(this.jTableFinalListSelection);

        if (this.jTableFinalListSelection.getRowCount() > 0) {
            this.jButtonSaveSelection.setEnabled(true);
        }



    }//GEN-LAST:event_jButtonDoSelectionActionPerformed

    private void ajustaColumnas() {
        for (int i = 0; i < 2; i++) {
            ajustaColumnsTable(this.jTableFinalListSelection, 2);
        }

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        for (int col = 0; col < this.jTableFinalListSelection.getColumnCount(); col++) {
            this.jTableFinalListSelection.getColumnModel().getColumn(col).setCellRenderer(tcr);
        }

        //this.jTableFinalListSelection.getColumnModel().getColumn(4).setCellRenderer(tcr2);
        // this.jTableFinalListSelection.getColumnModel().getColumn(6).setCellRenderer(tcr2);

    }

    private void jButtonSelExcelScriptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelExcelScriptActionPerformed

        FileFilter[] filtros = new FileFilter[10];
        filtros = selectorArchivo.getChoosableFileFilters();

        for (int i = 0; i < filtros.length; i++) {
            FileFilter filtro = filtros[i];
            selectorArchivo.removeChoosableFileFilter(filtro);
        }

        File myDesktop = new File(FieldbookSettings.getSelectionsDefaultFolder());

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

        if (isValidExcelWheatFile(selectorArchivo.getSelectedFile())) {
            processExcelSelectionFile(selectorArchivo.getSelectedFile());
        }

    }//GEN-LAST:event_jButtonSelExcelScriptActionPerformed

    private void jTextFieldListNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldListNameKeyReleased
   }//GEN-LAST:event_jTextFieldListNameKeyReleased

    private void jButtonSaveSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveSelectionActionPerformed
        if (jTextFieldListName.getText().trim().isEmpty()) {
            DialogUtil.displayError("Please type your list name");
            jTextFieldListName.requestFocusInWindow();
            return;
        }

        if (this.jTextFieldDescription.getText().isEmpty()) {
            NotifyDescriptor d = new NotifyDescriptor.Message("You need to set a description", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            jTextFieldDescription.requestFocusInWindow();
            return;
        }

        if (this.jTableFinalListSelection.getRowCount() == 0) {
            NotifyDescriptor d = new NotifyDescriptor.Message("Your list is empty", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            return;
        }
        NotifyDescriptor d = new NotifyDescriptor.Confirmation("Do you want to save the germplasm list?", "Save final list",
                NotifyDescriptor.OK_CANCEL_OPTION);
        if (DialogDisplayer.getDefault().notify(d) == NotifyDescriptor.OK_OPTION) {
            saveSelectionList();
            NotifyDescriptor d2 = new NotifyDescriptor.Message("Your list was saved!", NotifyDescriptor.INFORMATION_MESSAGE);
            DialogDisplayer.getDefault().notify(d2);
            this.jTextFieldDescription.setText("");
            this.jTextFieldListName.setText("");

            DefaultTableModel modelo = (DefaultTableModel) this.jTableFinalListSelection.getModel();
            modelo.setRowCount(0);
        }

        this.jButtonSaveSelection.setEnabled(false);
    }//GEN-LAST:event_jButtonSaveSelectionActionPerformed

    private void jTableFinalListSelectionPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableFinalListSelectionPropertyChange
        this.jLabelEntriesSelection.setText(String.valueOf(this.jTableFinalListSelection.getRowCount()));
    }//GEN-LAST:event_jTableFinalListSelectionPropertyChange

    private void jScrollPane7PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jScrollPane7PropertyChange
    }//GEN-LAST:event_jScrollPane7PropertyChange

    private void jCheckBoxSameValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSameValueActionPerformed

        GermplasmEntriesTableModel model = (GermplasmEntriesTableModel) this.jTableFinalListSelect.getModel();
        if (this.jCheckBoxSameValue.isSelected()) {


            model.setEstaHabilitadoSeleccion(false);

            int value = Integer.parseInt(this.jSpinnerEntries.getValue().toString());
            for (int i = 0; i < jTableFinalListSelect.getRowCount(); i++) {
                jTableFinalListSelect.setValueAt(value, i, 3);

            }
        } else {
            model.setEstaHabilitadoSeleccion(true);
        }



    }//GEN-LAST:event_jCheckBoxSameValueActionPerformed

    private void jSpinnerEntriesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerEntriesStateChanged
        GermplasmEntriesTableModel model = (GermplasmEntriesTableModel) this.jTableFinalListSelect.getModel();
        if (this.jCheckBoxSameValue.isSelected()) {


            model.setEstaHabilitadoSeleccion(false);

            int value = Integer.parseInt(this.jSpinnerEntries.getValue().toString());
            for (int i = 0; i < jTableFinalListSelect.getRowCount(); i++) {
                jTableFinalListSelect.setValueAt(value, i, 3);

            }
        } else {
            model.setEstaHabilitadoSeleccion(true);
        }
    }//GEN-LAST:event_jSpinnerEntriesStateChanged

    private void jComboBoxConvectionselectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxConvectionselectionActionPerformed
        switch (jComboBoxConvectionselection.getSelectedIndex()) {
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
    }//GEN-LAST:event_jComboBoxConvectionselectionActionPerformed

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
            case 2: //selection form
                this.jTextAreaPathSelect.setText(selectorArchivo.getSelectedFile().toString());

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
            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message("THIS EXCEL FILE IS NOT A VALID ENTRIES FILE", NotifyDescriptor.ERROR_MESSAGE));
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
                    DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message("THIS EXCEL FILE IS NOT A VALID ENTRIES FILE", NotifyDescriptor.ERROR_MESSAGE));
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
                    DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message("THIS EXCEL FILE IS NOT A VALID ENTRIES FILE", NotifyDescriptor.ERROR_MESSAGE));
                }

                break;



            case 2://selection form
                if (validFile) {
                    try {
                        GermplasmList germplasmList = germplasmListReader.getGermPlasmList(myFile);
                        setGermplasmListIntoTable(germplasmList, tabla, 2);
                    } catch (Exception ex) {
                        System.out.println("ERROR AL LEER EXCEL GERMPLASM ENTRIES: " + ex);
                    }
                } else {
                    GermplasmEntriesTableModel modeloTablaEntries = new GermplasmEntriesTableModel();
                    this.jTableEntriesExcelSelect.setModel(modeloTablaEntries);
                    this.jTextAreaPathSelect.setText("");
                    this.jTextFieldListEntriesSelect.setText("0");
                    DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message("THIS EXCEL FILE IS NOT A VALID ENTRIES FILE", NotifyDescriptor.ERROR_MESSAGE));
                }

                germplasmTransferHandler.setSourceList(rowListExcel);
                germplasmTransferHandler.setSourceTable(jTableEntriesExcelSelect);
                germplasmTransferHandler.setFactores(factores);


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
                break;
            case 1://male
                this.jTextFieldListEntriesMale.setText(String.valueOf(germplasmList.getListEntries().size()));
                break;
            case 2://selection form
                this.jTextFieldListEntriesSelect.setText(String.valueOf(germplasmList.getListEntries().size()));
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
    private javax.swing.JComboBox cboGermplasmListFemale;
    private javax.swing.JComboBox cboGermplasmListMale;
    private javax.swing.JComboBox cboGermplasmListSelect;
    private javax.swing.JButton jButtonBrowseExcelFemale;
    private javax.swing.JButton jButtonBrowseExcelMale;
    private javax.swing.JButton jButtonBrowseExcelSelect;
    private javax.swing.JButton jButtonCross;
    private javax.swing.JButton jButtonDoSelection;
    private javax.swing.JButton jButtonLoadExcelScript;
    private javax.swing.JButton jButtonSaveCrossList;
    private javax.swing.JButton jButtonSaveSelection;
    private javax.swing.JButton jButtonSelExcelScript;
    private javax.swing.JCheckBox jCheckBoxSameValue;
    private javax.swing.JComboBox jComboBoxConvection;
    private javax.swing.JComboBox jComboBoxConvectionselection;
    private javax.swing.JComboBox jComboBoxMethods;
    private javax.swing.JComboBox jComboBoxMethodsSelection;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelEntriesSelection;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelSelection;
    private javax.swing.JScrollPane jScrollEntiresExcelFemale;
    private javax.swing.JScrollPane jScrollEntiresExcelMale;
    private javax.swing.JScrollPane jScrollEntiresExcelSelect;
    private javax.swing.JScrollPane jScrollEntriesDbFemale;
    private javax.swing.JScrollPane jScrollEntriesDbMale;
    private javax.swing.JScrollPane jScrollEntriesDbSelect;
    private javax.swing.JScrollPane jScrollFinalListSelect;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator9;
    private javax.swing.JSpinner jSpinnerEntries;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPaneFemale;
    private javax.swing.JTabbedPane jTabbedPaneMale;
    private javax.swing.JTabbedPane jTabbedPaneSelect;
    private javax.swing.JTable jTableEntriesDBFemale;
    private javax.swing.JTable jTableEntriesDBMale;
    private javax.swing.JTable jTableEntriesDBSelect;
    private javax.swing.JTable jTableEntriesExcelFemale;
    private javax.swing.JTable jTableEntriesExcelMale;
    private javax.swing.JTable jTableEntriesExcelSelect;
    private javax.swing.JTable jTableFinalList;
    private javax.swing.JTable jTableFinalListSelect;
    private javax.swing.JTable jTableFinalListSelection;
    private javax.swing.JTextArea jTextAreaPathFemale;
    private javax.swing.JTextArea jTextAreaPathMale;
    private javax.swing.JTextArea jTextAreaPathSelect;
    private javax.swing.JTextField jTextFieldDescription;
    private javax.swing.JTextField jTextFieldListEntriesFemale;
    private javax.swing.JTextField jTextFieldListEntriesMale;
    private javax.swing.JTextField jTextFieldListEntriesSelect;
    private javax.swing.JTextField jTextFieldListName;
    private javax.swing.JTextField jTextFieldSelectedEntriesFemale;
    private javax.swing.JTextField jTextFieldSelectedEntriesSelect;
    private javax.swing.JTextField jTextFieldSelectedFinal;
    private javax.swing.JTextField jTextFieldSelectedFinalMale;
    private javax.swing.JTextField jTextFieldTotalEntries;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar4;
    // End of variables declaration//GEN-END:variables

    public void enabledComponentsForWheat(boolean habilitado) {
        jTabbedPaneSelect.setEnabled(habilitado);
        jPanelSelection.setEnabled(habilitado);
        jButtonDoSelection.setEnabled(habilitado);
        cboGermplasmListSelect.setEnabled(habilitado);
        jScrollEntriesDbSelect.setEnabled(habilitado);
        jCheckBoxSameValue.setEnabled(habilitado);
        jSpinnerEntries.setEnabled(habilitado);
        jScrollFinalListSelect.setEnabled(habilitado);
        jTableEntriesDBSelect.setEnabled(habilitado);
        jTableFinalListSelect.setEnabled(habilitado);
        jTextAreaPathSelect.setEnabled(habilitado);
        jButtonBrowseExcelSelect.setEnabled(habilitado);
        jScrollEntiresExcelSelect.setEnabled(habilitado);
        jTableEntriesExcelSelect.setEnabled(habilitado);
        this.jButtonSelExcelScript.setVisible(!habilitado);

    }

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
        jTableEntriesDBSelect.setDragEnabled(true);
        jTableEntriesDBSelect.setDropMode(DropMode.INSERT_ROWS);

        germplasmTransferHandler = new GermplasmTransferHandlerSelection(jTableEntriesDBSelect, jTableFinalListSelect, rowListDB, toAdd);
        jTableFinalListSelect.setTransferHandler(germplasmTransferHandler);
        jScrollFinalListSelect.setTransferHandler(germplasmTransferHandler);


        jTableFinalListSelect.setDragEnabled(true);
        jTableFinalListSelect.setDropMode(DropMode.INSERT_ROWS);

        removeGermplasmTransferHandler = new RemoveGermplasmTransferHandler(jTableFinalListSelect, toAdd);

        jTableEntriesDBSelect.setTransferHandler(removeGermplasmTransferHandler);
        jScrollEntriesDbSelect.setTransferHandler(removeGermplasmTransferHandler);

        jTableEntriesExcelSelect.setTransferHandler(removeGermplasmTransferHandler);
        jScrollEntiresExcelSelect.setTransferHandler(removeGermplasmTransferHandler);
    }

    private void fillMethods() {



        List<Methods> methodsList = AppServicesProxy.getDefault().appServices().getMethodsList();

        methodsInCombo = new ArrayList<Methods>();
        List<String> sortedMethods = new ArrayList<String>();

        for (Methods methods : methodsList) {

            if (methods.getMtype().equals("DER") || methods.getMtype().equals("MAN")) {
                sortedMethods.add(methods.getMname().toUpperCase());
                methodsInCombo.add(methods);
            }
        }

        Collections.sort(sortedMethods);

        for (String methodName : sortedMethods) {
            jComboBoxMethodsSelection.addItem(methodName);
        }

        this.jComboBoxMethodsSelection.setSelectedItem("SINGLE PLANT SELECTION SF");

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

    public void ajustaColumnsTable(JTable table, int margin) {
        for (int c = 0; c < table.getColumnCount(); c++) {
            ajustaColumn(table, c, 2);
        }
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
                NotifyDescriptor d = new NotifyDescriptor.Message("Excel File NOT VALID!, Check ENT column", NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
            }
            cellData = rowData.getCell(CID);
            if (getStringValueFromCell(cellData).equals("CID")) {
                check++;
            } else {
                NotifyDescriptor d = new NotifyDescriptor.Message("Excel File NOT VALID!, Check CID column", NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
            }

            cellData = rowData.getCell(SID);
            if (getStringValueFromCell(cellData).equals("SID")) {
                check++;
            } else {
                NotifyDescriptor d = new NotifyDescriptor.Message("Excel File NOT VALID!, Check SID column", NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
            }
            cellData = rowData.getCell(STID);
            if (getStringValueFromCell(cellData).equals("STID")) {
                check++;
            } else {
                NotifyDescriptor d = new NotifyDescriptor.Message("Excel File NOT VALID!, Check STID column", NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
            }
            cellData = rowData.getCell(SOCC);
            if (getStringValueFromCell(cellData).equals("SOCC")) {
                check++;
            } else {
                NotifyDescriptor d = new NotifyDescriptor.Message("Excel File NOT VALID!, Check SOCC column", NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
            }

            cellData = rowData.getCell(SENT);
            if (getStringValueFromCell(cellData).equals("SENT")) {
                check++;
            } else {
                NotifyDescriptor d = new NotifyDescriptor.Message("Excel File NOT VALID!, Check SENT column", NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
            }

            cellData = rowData.getCell(TYPE);
            if (getStringValueFromCell(cellData).equals("TYPE")) {
                check++;
            } else {
                NotifyDescriptor d = new NotifyDescriptor.Message("Excel File NOT VALID!, Check TYPE column", NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
            }

            cellData = rowData.getCell(NSEL);
            if (getStringValueFromCell(cellData).equals("NSEL")) {
                check++;
            } else {
                NotifyDescriptor d = new NotifyDescriptor.Message("Excel File NOT VALID!, Check NSEL column", NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
            }

            if (check == 8) {
                valid = true;
            }

            changeCursorWaitStatus(false);

        } catch (Exception e) {

            System.out.println("ERROR AL VALIDAR EXCEL: " + e);
            NotifyDescriptor d = new NotifyDescriptor.Message("Excel File NOT VALID!, Check yours columns", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            changeCursorWaitStatus(false);
            valid = false;
        }

        return valid;
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

    private void checkConvection() {
        switch (jComboBoxConvectionselection.getSelectedIndex()) {
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
    }

    private Methods getSelectedMethod() {

        Methods selectedMethod = null;
        String methodName = (String) jComboBoxMethodsSelection.getSelectedItem();
        for (Methods methods : methodsInCombo) {

            if (methods.getMname().equals(methodName)) {
                selectedMethod = methods;
            }
        }
        return selectedMethod;
    }
    
        public void selectOptionDB() {
         TypeDB tipo= AppServicesProxy.getDefault().appServices().getTypeDB();         
         if(tipo==TypeDB.IWIS){
             this.jComboBoxConvectionselection.setSelectedIndex(0);             
         }else if(tipo==TypeDB.IMIS){
             this.jComboBoxConvectionselection.setSelectedIndex(1);             
         }else{
              this.jComboBoxConvectionselection.setSelectedIndex(2);
         }                  
    }
    
}
