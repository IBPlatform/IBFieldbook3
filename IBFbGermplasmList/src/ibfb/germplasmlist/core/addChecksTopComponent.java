package ibfb.germplasmlist.core;

import ibfb.domain.core.Factor;
import ibfb.domain.core.GermplasmList;
import ibfb.domain.core.ListOfEntries;
import ibfb.domain.core.Workbook;
import ibfb.germplasmlist.filters.ExcelFiltro;
import ibfb.germplasmlist.models.GermplasmEntriesTableModelChecks;
import ibfb.germplasmlist.models.GermplasmSourceTransferHandler;
import ibfb.germplasmlist.models.GermplasmTransferHandler;
import ibfb.germplasmlist.models.RemoveGermplasmTransferHandler;
import ibfb.germplasmlist.renders.MyRenderer;
import ibfb.lists.core.SelectListDialog;
import ibfb.settings.core.FieldbookSettings;
import ibfb.studyexplorer.explorer.listNames.ListDataWindowTopComponent;
import ibfb.workbook.api.GermplasmAssigmentTool;
import ibfb.workbook.api.GermplasmListReader;
import ibfb.workbook.core.GermplasmAssigmentToolImpl;
import ibfb.workbook.core.GermplasmListReaderImpl;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.ConvertUtils;
import org.cimmyt.cril.ibwb.commongui.FileUtils;
import org.cimmyt.cril.ibwb.commongui.OSUtils;
import org.cimmyt.cril.ibwb.domain.Listdata;
import org.cimmyt.cril.ibwb.domain.ListdataPK;
import org.cimmyt.cril.ibwb.domain.Listnms;
import org.generationcp.middleware.pojos.GermplasmPedigreeTreeNode;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import ibfb.germplasmlist.utils.SequenceEntry;
import java.util.*;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//ibfb.germplasmlist.core//addChecks//EN",
autostore = false)
@TopComponent.Description(preferredID = "addChecksTopComponent",
iconBase = "ibfb/germplasmlist/images/germplasmIcon16.png",
persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "ibfb.germplasmlist.core.addChecksTopComponent")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_addChecksAction",
preferredID = "addChecksTopComponent")
public final class addChecksTopComponent extends TopComponent {

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
    List<List<Object>> rowListDBChecks;
    List<List<Object>> rowListExcelChecks;
    List<List<Object>> toAdd;
    List<List<Object>> toAddChecks;
    List<List<Object>> toRemove;
    List<List<Object>> toRemoveChecks;
    private boolean esPrimeraVez = true;
    private List<Factor> factores;
    ImageIcon iconAdd = new ImageIcon("ibfb/germplasmlist/images/add.png");
    ImageIcon iconCancel = new ImageIcon("ibfb/germplasmlist/images/cancel.png");
    private GermplasmTransferHandler germplasmTransferHandler;
    private GermplasmSourceTransferHandler germplasmSourceTransferHandler;
    private RemoveGermplasmTransferHandler removeGermplasmTransferHandler;
    private int initialPosition = 0;
    private int frequency = 0;
    private int maximo;
    ArrayList<Integer> posiciones;
    private List<List<Object>> sourceList;
    private List<List<Object>> destList;
    private int totalEntries = 0;
    private int[] posicionesSec;
    private ArrayList<int[]> posicionesSecuencia;
    private ArrayList<SequenceEntry> sequenceList;

    public addChecksTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(addChecksTopComponent.class, "CTL_addChecksTopComponent"));
        setToolTipText(NbBundle.getMessage(addChecksTopComponent.class, "HINT_addChecksTopComponent"));
        addListener();
        fillComboListNames();
        loadFactors();
        toAdd = new ArrayList<List<Object>>();
        toAddChecks = new ArrayList<List<Object>>();
        rowListDB = new ArrayList<List<Object>>();
        rowListExcel = new ArrayList<List<Object>>();
        rowListDBChecks = new ArrayList<List<Object>>();
        rowListExcelChecks = new ArrayList<List<Object>>();
        asignaTransferSource();
        asignaTransferChecks();
        asignaTableModelsDefault();
        
        
        // disable panel for checks
        //jTabbedPaneChecks.setEnabledAt(1, false);
        jTabbedPaneChecks.remove(jPanel2);
        this.jButtonSaveList.setEnabled(true);   
        jRadioButtonWith.setVisible(false);
        jRadioButtonWithout.setVisible(false);
    }

    public void initLists() {
        toAdd = new ArrayList<List<Object>>();
        toAddChecks = new ArrayList<List<Object>>();
        rowList = new ArrayList<List<Object>>();
        rowListDB = new ArrayList<List<Object>>();
        rowListExcel = new ArrayList<List<Object>>();
        rowListDBChecks = new ArrayList<List<Object>>();
        rowListExcelChecks = new ArrayList<List<Object>>();
        refreshData();
        asignaTransferSource();
        asignaTransferChecks();
        fillComboListNames();
    }

    public void fillComboListNames() {
        cboGermplasmList.setModel(new DefaultComboBoxModel(new String[]{NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.selectOne")}));
        cboGermplasmListChecks.setModel(new DefaultComboBoxModel(new String[]{NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.selectOne")}));
        List<Listnms> germplasmList = AppServicesProxy.getDefault().appServices().getListnmsList();
        for (Listnms list : germplasmList) {
            cboGermplasmList.addItem(list);
            cboGermplasmListChecks.addItem(list);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTabbedPaneChecks = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jButtonUp = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldSourceEntries = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldSelectedSource = new javax.swing.JTextField();
        jToolBar2 = new javax.swing.JToolBar();
        jButtonClear = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldEntries = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldSelectedEntries = new javax.swing.JTextField();
        jTabbedPaneSource = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboGermplasmList = new javax.swing.JComboBox();
        jScrollEntriesDb = new javax.swing.JScrollPane();
        jTableEntriesDB = new javax.swing.JTable();
        btnSearchList = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaPath = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollEntiresExcel = new javax.swing.JScrollPane();
        jTableEntriesExcel = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollFinalSource = new javax.swing.JScrollPane();
        jTableFinalSource = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jButtonRemove = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jToolBar3 = new javax.swing.JToolBar();
        jButtonUpChecks = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jLabel10 = new javax.swing.JLabel();
        jTextFielTotalChecks = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JToolBar.Separator();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldSelectedTotalChecks = new javax.swing.JTextField();
        jToolBar4 = new javax.swing.JToolBar();
        jButtonClearChecks = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JToolBar.Separator();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldEntriesChecks = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JToolBar.Separator();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldSelectedChecks = new javax.swing.JTextField();
        jTabbedPaneSelectChecks = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cboGermplasmListChecks = new javax.swing.JComboBox();
        jScrollEntriesDbChecks = new javax.swing.JScrollPane();
        jTableEntriesDBChecks = new javax.swing.JTable();
        btnSearchCheckList = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaPathChecks = new javax.swing.JTextArea();
        jButtonBrowseExcelChecks = new javax.swing.JButton();
        jScrollEntiresExcelChecks = new javax.swing.JScrollPane();
        jTableEntriesExcelChecks = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jScrollFinalListChecks = new javax.swing.JScrollPane();
        jTableFinalChecks = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jRadioButtonPosition = new javax.swing.JRadioButton();
        jRadioButtonSequence = new javax.swing.JRadioButton();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        jSpinnerPosition = new javax.swing.JSpinner();
        jSpinnerFrequency = new javax.swing.JSpinner();
        jPanel13 = new javax.swing.JPanel();
        jButtonRemoveChecks = new javax.swing.JButton();
        jButtonAddChecks = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollFinal = new javax.swing.JScrollPane();
        jTableFinal = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldListName = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldDescription = new javax.swing.JTextField();
        jButtonSaveList = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabelFinalEntries = new javax.swing.JLabel();
        jRadioButtonWith = new javax.swing.JRadioButton();
        jRadioButtonWithout = new javax.swing.JRadioButton();

        jTabbedPaneChecks.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneChecksStateChanged(evt);
            }
        });

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonUp, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jButtonUp.text")); // NOI18N
        jButtonUp.setToolTipText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jButtonUp.toolTipText")); // NOI18N
        jButtonUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonUp);
        jToolBar1.add(jSeparator2);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jLabel7.text")); // NOI18N
        jToolBar1.add(jLabel7);

        jTextFieldSourceEntries.setEditable(false);
        jTextFieldSourceEntries.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldSourceEntries.setText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jTextFieldSourceEntries.text")); // NOI18N
        jToolBar1.add(jTextFieldSourceEntries);
        jToolBar1.add(jSeparator6);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jLabel11.text")); // NOI18N
        jToolBar1.add(jLabel11);

        jTextFieldSelectedSource.setEditable(false);
        jTextFieldSelectedSource.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldSelectedSource.setText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jTextFieldSelectedSource.text")); // NOI18N
        jToolBar1.add(jTextFieldSelectedSource);

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonClear, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jButtonClear.text")); // NOI18N
        jButtonClear.setToolTipText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jButtonClear.toolTipText")); // NOI18N
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });
        jToolBar2.add(jButtonClear);
        jToolBar2.add(jSeparator1);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jLabel8.text")); // NOI18N
        jToolBar2.add(jLabel8);

        jTextFieldEntries.setEditable(false);
        jTextFieldEntries.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldEntries.setText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jTextFieldEntries.text")); // NOI18N
        jToolBar2.add(jTextFieldEntries);
        jToolBar2.add(jSeparator5);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jLabel9.text")); // NOI18N
        jToolBar2.add(jLabel9);

        jTextFieldSelectedEntries.setEditable(false);
        jTextFieldSelectedEntries.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldSelectedEntries.setText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jTextFieldSelectedEntries.text")); // NOI18N
        jToolBar2.add(jTextFieldSelectedEntries);

        jTabbedPaneSource.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jTabbedPaneSource.border.title"))); // NOI18N
        jTabbedPaneSource.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneSourceStateChanged(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/database.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jLabel1.text")); // NOI18N

        cboGermplasmList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT ONE..." }));
        cboGermplasmList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboGermplasmListItemStateChanged(evt);
            }
        });
        cboGermplasmList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGermplasmListActionPerformed(evt);
            }
        });

        jScrollEntriesDb.setToolTipText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jScrollEntriesDb.toolTipText")); // NOI18N

        jTableEntriesDB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableEntriesDB.setDragEnabled(true);
        jTableEntriesDB.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableEntriesDB.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableEntriesDBPropertyChange(evt);
            }
        });
        jScrollEntriesDb.setViewportView(jTableEntriesDB);

        org.openide.awt.Mnemonics.setLocalizedText(btnSearchList, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.btnSearchList.text")); // NOI18N
        btnSearchList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollEntriesDb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboGermplasmList, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearchList)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboGermplasmList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearchList)))
                .addGap(18, 18, 18)
                .addComponent(jScrollEntriesDb, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneSource.addTab(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/excelFile.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jLabel2.text")); // NOI18N

        jTextAreaPath.setColumns(20);
        jTextAreaPath.setLineWrap(true);
        jTextAreaPath.setRows(5);
        jTextAreaPath.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextAreaPathMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextAreaPath);

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTableEntriesExcel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableEntriesExcel.setDragEnabled(true);
        jTableEntriesExcel.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableEntriesExcel.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableEntriesExcelPropertyChange(evt);
            }
        });
        jScrollEntiresExcel.setViewportView(jTableEntriesExcel);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollEntiresExcel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollEntiresExcel, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneSource.addTab(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jPanel5.TabConstraints.tabTitle"), jPanel5); // NOI18N

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jPanel6.border.title"))); // NOI18N

        jScrollFinalSource.setToolTipText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jScrollFinalSource.toolTipText")); // NOI18N

        jTableFinalSource.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableFinalSource.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableFinalSourcePropertyChange(evt);
            }
        });
        jScrollFinalSource.setViewportView(jTableFinalSource);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollFinalSource, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollFinalSource)
                .addContainerGap())
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jPanel12.border.title"))); // NOI18N

        jButtonRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/removeCircle.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonRemove, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jButtonRemove.text")); // NOI18N
        jButtonRemove.setToolTipText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jButtonRemove.toolTipText")); // NOI18N
        jButtonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveActionPerformed(evt);
            }
        });

        jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/addCircle.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonAdd, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jButtonAdd.text")); // NOI18N
        jButtonAdd.setToolTipText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jButtonAdd.toolTipText")); // NOI18N
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/drag.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jLabel4.text")); // NOI18N
        jLabel4.setToolTipText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jLabel4.toolTipText")); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButtonRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPaneSource)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15, 15, 15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jTabbedPaneSource, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );

        jTabbedPaneChecks.addTab(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jToolBar3.setFloatable(false);
        jToolBar3.setRollover(true);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonUpChecks, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jButtonUpChecks.text")); // NOI18N
        jButtonUpChecks.setToolTipText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jButtonUpChecks.toolTipText")); // NOI18N
        jButtonUpChecks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpChecksActionPerformed(evt);
            }
        });
        jToolBar3.add(jButtonUpChecks);
        jToolBar3.add(jSeparator3);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jLabel10.text")); // NOI18N
        jToolBar3.add(jLabel10);

        jTextFielTotalChecks.setEditable(false);
        jTextFielTotalChecks.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFielTotalChecks.setText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jTextFielTotalChecks.text")); // NOI18N
        jToolBar3.add(jTextFielTotalChecks);
        jToolBar3.add(jSeparator9);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jLabel12.text")); // NOI18N
        jToolBar3.add(jLabel12);

        jTextFieldSelectedTotalChecks.setEditable(false);
        jTextFieldSelectedTotalChecks.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldSelectedTotalChecks.setText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jTextFieldSelectedTotalChecks.text")); // NOI18N
        jToolBar3.add(jTextFieldSelectedTotalChecks);

        jToolBar4.setFloatable(false);
        jToolBar4.setRollover(true);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonClearChecks, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jButtonClearChecks.text")); // NOI18N
        jButtonClearChecks.setToolTipText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jButtonClearChecks.toolTipText")); // NOI18N
        jButtonClearChecks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearChecksActionPerformed(evt);
            }
        });
        jToolBar4.add(jButtonClearChecks);
        jToolBar4.add(jSeparator11);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jLabel13.text")); // NOI18N
        jToolBar4.add(jLabel13);

        jTextFieldEntriesChecks.setEditable(false);
        jTextFieldEntriesChecks.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldEntriesChecks.setText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jTextFieldEntriesChecks.text")); // NOI18N
        jToolBar4.add(jTextFieldEntriesChecks);
        jToolBar4.add(jSeparator12);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel14, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jLabel14.text")); // NOI18N
        jToolBar4.add(jLabel14);

        jTextFieldSelectedChecks.setEditable(false);
        jTextFieldSelectedChecks.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldSelectedChecks.setText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jTextFieldSelectedChecks.text")); // NOI18N
        jToolBar4.add(jTextFieldSelectedChecks);

        jTabbedPaneSelectChecks.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jTabbedPaneSelectChecks.border.title"))); // NOI18N
        jTabbedPaneSelectChecks.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneSelectChecksStateChanged(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/database.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jLabel5.text")); // NOI18N

        cboGermplasmListChecks.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT ONE..." }));
        cboGermplasmListChecks.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboGermplasmListChecksItemStateChanged(evt);
            }
        });

        jScrollEntriesDbChecks.setToolTipText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jScrollEntriesDbChecks.toolTipText")); // NOI18N

        jTableEntriesDBChecks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableEntriesDBChecks.setDragEnabled(true);
        jTableEntriesDBChecks.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableEntriesDBChecks.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableEntriesDBChecksPropertyChange(evt);
            }
        });
        jScrollEntriesDbChecks.setViewportView(jTableEntriesDBChecks);

        org.openide.awt.Mnemonics.setLocalizedText(btnSearchCheckList, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.btnSearchCheckList.text")); // NOI18N
        btnSearchCheckList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchCheckListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollEntriesDbChecks, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboGermplasmListChecks, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearchCheckList)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboGermplasmListChecks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearchCheckList)))
                .addGap(18, 18, 18)
                .addComponent(jScrollEntriesDbChecks, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneSelectChecks.addTab(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jPanel7.TabConstraints.tabTitle"), jPanel7); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/excelFile.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jLabel6.text")); // NOI18N

        jTextAreaPathChecks.setColumns(20);
        jTextAreaPathChecks.setLineWrap(true);
        jTextAreaPathChecks.setRows(5);
        jTextAreaPathChecks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextAreaPathChecksMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTextAreaPathChecks);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonBrowseExcelChecks, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jButtonBrowseExcelChecks.text")); // NOI18N
        jButtonBrowseExcelChecks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBrowseExcelChecksActionPerformed(evt);
            }
        });

        jTableEntriesExcelChecks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableEntriesExcelChecks.setDragEnabled(true);
        jTableEntriesExcelChecks.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableEntriesExcelChecks.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableEntriesExcelChecksPropertyChange(evt);
            }
        });
        jScrollEntiresExcelChecks.setViewportView(jTableEntriesExcelChecks);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollEntiresExcelChecks, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBrowseExcelChecks, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, 0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonBrowseExcelChecks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollEntiresExcelChecks, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneSelectChecks.addTab(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jPanel8.TabConstraints.tabTitle"), jPanel8); // NOI18N

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jPanel9.border.title"))); // NOI18N

        jScrollFinalListChecks.setToolTipText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jScrollFinalListChecks.toolTipText")); // NOI18N

        jTableFinalChecks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableFinalChecks.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableFinalChecksPropertyChange(evt);
            }
        });
        jScrollFinalListChecks.setViewportView(jTableFinalChecks);

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup1.add(jRadioButtonPosition);
        jRadioButtonPosition.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonPosition, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jRadioButtonPosition.text")); // NOI18N
        jRadioButtonPosition.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButtonPositionStateChanged(evt);
            }
        });
        jRadioButtonPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonPositionActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonSequence);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonSequence, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jRadioButtonSequence.text")); // NOI18N
        jRadioButtonSequence.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButtonSequenceStateChanged(evt);
            }
        });

        label1.setText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.label1.text")); // NOI18N

        label2.setText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.label2.text")); // NOI18N

        jSpinnerPosition.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        jSpinnerPosition.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerPositionStateChanged(evt);
            }
        });
        jSpinnerPosition.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSpinnerPositionPropertyChange(evt);
            }
        });

        jSpinnerFrequency.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        jSpinnerFrequency.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerFrequencyStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonPosition)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jSpinnerPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSpinnerFrequency, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addComponent(jRadioButtonSequence))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinnerPosition, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerFrequency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonPosition)
                    .addComponent(jRadioButtonSequence))
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollFinalListChecks, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollFinalListChecks, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jPanel13.border.title"))); // NOI18N

        jButtonRemoveChecks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/removeCircle.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonRemoveChecks, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jButtonRemoveChecks.text")); // NOI18N
        jButtonRemoveChecks.setToolTipText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jButtonRemoveChecks.toolTipText")); // NOI18N
        jButtonRemoveChecks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveChecksActionPerformed(evt);
            }
        });

        jButtonAddChecks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/addCircle.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonAddChecks, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jButtonAddChecks.text")); // NOI18N
        jButtonAddChecks.setToolTipText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jButtonAddChecks.toolTipText")); // NOI18N
        jButtonAddChecks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddChecksActionPerformed(evt);
            }
        });

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/drag.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel15, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jLabel15.text")); // NOI18N
        jLabel15.setToolTipText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jLabel15.toolTipText")); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel15)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAddChecks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRemoveChecks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jButtonAddChecks, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButtonRemoveChecks, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPaneSelectChecks)
                    .addComponent(jToolBar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPaneSelectChecks))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPaneChecks.addTab(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jScrollFinal.setToolTipText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jScrollFinal.toolTipText")); // NOI18N

        jTableFinal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableFinal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableFinalPropertyChange(evt);
            }
        });
        jScrollFinal.setViewportView(jTableFinal);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel17, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jLabel17.text")); // NOI18N

        jTextFieldListName.setText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jTextFieldListName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel18, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jLabel18.text")); // NOI18N

        jTextFieldDescription.setText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jTextFieldDescription.text")); // NOI18N

        jButtonSaveList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/save.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSaveList, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jButtonSaveList.text")); // NOI18N
        jButtonSaveList.setToolTipText(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jButtonSaveList.toolTipText")); // NOI18N
        jButtonSaveList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveListActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelFinalEntries, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jLabelFinalEntries.text")); // NOI18N

        buttonGroup2.add(jRadioButtonWith);
        jRadioButtonWith.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonWith, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jRadioButtonWith.text")); // NOI18N
        jRadioButtonWith.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButtonWithStateChanged(evt);
            }
        });
        jRadioButtonWith.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonWithActionPerformed(evt);
            }
        });
        jRadioButtonWith.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jRadioButtonWithPropertyChange(evt);
            }
        });

        buttonGroup2.add(jRadioButtonWithout);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonWithout, org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jRadioButtonWithout.text")); // NOI18N
        jRadioButtonWithout.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButtonWithoutStateChanged(evt);
            }
        });
        jRadioButtonWithout.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jRadioButtonWithoutPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldListName)
                    .addComponent(jTextFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonSaveList, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonWithout)
                    .addComponent(jRadioButtonWith))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 367, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(95, 95, 95))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabelFinalEntries)
                        .addGap(133, 133, 133))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldListName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(jTextFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButtonSaveList, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jRadioButtonWith))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelFinalEntries)
                            .addComponent(jRadioButtonWithout))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollFinal, javax.swing.GroupLayout.DEFAULT_SIZE, 1109, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollFinal, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneChecks.addTab(org.openide.util.NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneChecks)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneChecks)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpActionPerformed
        cleanGermplasmFinalList();
   }//GEN-LAST:event_jButtonUpActionPerformed

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed

        switch (this.jTabbedPaneSource.getSelectedIndex()) {
            case 0:
                jTableEntriesDB.getSelectionModel().clearSelection();
                break;

            case 1:
                jTableEntriesExcel.getSelectionModel().clearSelection();
                break;

        }
    }//GEN-LAST:event_jButtonClearActionPerformed

    private void cboGermplasmListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboGermplasmListItemStateChanged
        readGermplsmEntriesFromDb(this.jTableEntriesDB, 0);
   }//GEN-LAST:event_cboGermplasmListItemStateChanged

    private void readGermplsmEntriesFromDb(JTable tabla, int opcion) {

        rowListDB = new ArrayList<List<Object>>();
        rowListDBChecks = new ArrayList<List<Object>>();

        GermplasmListReader germplasmListReader = new GermplasmListReaderImpl();
        boolean validSelection = false;

        switch (opcion) {
            case 0:

                validSelection = cboGermplasmList.getSelectedIndex() > 0;

                if (validSelection) {
                    try {
                        Listnms selectedList = (Listnms) cboGermplasmList.getSelectedItem();
                        GermplasmList germplasmList = germplasmListReader.getGermPlasmListFromDB(selectedList.getListid());
                        setGermplasmListIntoTable(germplasmList, tabla, 0, 0);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        System.out.println("ERROR AL LEER EXCEL GERMPLASM ENTRIES: " + ex);
                    }
                } else {
                    GermplasmEntriesTableModelChecks modeloTablaEntries = new GermplasmEntriesTableModelChecks();
                    this.jTableEntriesDB.setModel(modeloTablaEntries);
                    this.jTextAreaPath.setText("");
                    this.jTextFieldEntries.setText("0");

                }

                germplasmSourceTransferHandler.setSourceList(rowListDB);
                germplasmSourceTransferHandler.setSourceTable(jTableEntriesDB);
                germplasmSourceTransferHandler.setFactores(factores);

                break;

            case 1:
                validSelection = cboGermplasmListChecks.getSelectedIndex() > 0;

                if (validSelection) {
                    try {
                        Listnms selectedList = (Listnms) cboGermplasmListChecks.getSelectedItem();
                        GermplasmList germplasmList = germplasmListReader.getGermPlasmListFromDB(selectedList.getListid());
                        setGermplasmListIntoTable(germplasmList, tabla, 0, 1);
                    } catch (Exception ex) {
                        System.out.println("ERROR AL LEER EXCEL GERMPLASM ENTRIES: " + ex);
                    }
                } else {
                    GermplasmEntriesTableModelChecks modeloTablaEntries = new GermplasmEntriesTableModelChecks();
                    this.jTableEntriesDBChecks.setModel(modeloTablaEntries);
                    this.jTextAreaPathChecks.setText("");
                    this.jTextFieldEntriesChecks.setText("0");

                }
                germplasmTransferHandler.setSourceList(rowListDBChecks);
                germplasmTransferHandler.setSourceTable(jTableEntriesDBChecks);
                germplasmTransferHandler.setFactores(factores);
                break;
        }
        jTableEntriesDB.requestFocus();
    }

    private void jTextAreaPathMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaPathMousePressed

        if (!this.jTextAreaPath.isEnabled()) {
            return;
        }

        openFile(0);
        if (this.jTextAreaPath.getText().isEmpty() == false) {
            readExcelGermplsmEntries(this.jTextAreaPath.getText(), this.jTableEntriesExcel, 0);
        }
    }//GEN-LAST:event_jTextAreaPathMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        openFile(0);
        if (this.jTextAreaPath.getText().isEmpty() == false) {
            readExcelGermplsmEntries(this.jTextAreaPath.getText(), this.jTableEntriesExcel, 0);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTabbedPaneSourceStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPaneSourceStateChanged


        switch (jTabbedPaneSource.getSelectedIndex()) {
            case 0:
                this.jTextFieldEntries.setText(String.valueOf(this.jTableEntriesDB.getRowCount()));
                this.jTextFieldSelectedEntries.setText(String.valueOf(this.jTableEntriesDB.getSelectedRowCount()));


                break;

            case 1:
                this.jTextFieldEntries.setText(String.valueOf(this.jTableEntriesExcel.getRowCount()));
                this.jTextFieldSelectedEntries.setText(String.valueOf(this.jTableEntriesExcel.getSelectedRowCount()));

                break;


        }
   }//GEN-LAST:event_jTabbedPaneSourceStateChanged

    private void jButtonUpChecksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpChecksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonUpChecksActionPerformed

    private void jButtonClearChecksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearChecksActionPerformed
        switch (this.jTabbedPaneSelectChecks.getSelectedIndex()) {
            case 0:
                jTableEntriesDBChecks.getSelectionModel().clearSelection();
                break;

            case 1:
                jTableEntriesExcelChecks.getSelectionModel().clearSelection();
                break;


            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_jButtonClearChecksActionPerformed

    private void cboGermplasmListChecksItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboGermplasmListChecksItemStateChanged
        readGermplsmEntriesFromDb(this.jTableEntriesDBChecks, 1);
    }//GEN-LAST:event_cboGermplasmListChecksItemStateChanged

    private void jTextAreaPathChecksMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaPathChecksMousePressed
        if (!this.jTextAreaPathChecks.isEnabled()) {
            return;
        }

        openFile(1);
        if (this.jTextAreaPathChecks.getText().isEmpty() == false) {
            readExcelGermplsmEntries(this.jTextAreaPathChecks.getText(), this.jTableEntriesExcelChecks, 1);
        }
    }//GEN-LAST:event_jTextAreaPathChecksMousePressed

    private void jButtonBrowseExcelChecksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBrowseExcelChecksActionPerformed
        openFile(1);
        if (this.jTextAreaPathChecks.getText().isEmpty() == false) {
            readExcelGermplsmEntries(this.jTextAreaPathChecks.getText(), this.jTableEntriesExcelChecks, 1);
        }
    }//GEN-LAST:event_jButtonBrowseExcelChecksActionPerformed

    private void jTabbedPaneSelectChecksStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPaneSelectChecksStateChanged

        switch (jTabbedPaneSelectChecks.getSelectedIndex()) {

            case 0:
                this.jTextFieldEntriesChecks.setText(String.valueOf(this.jTableEntriesDBChecks.getRowCount()));
                this.jTextFieldSelectedChecks.setText(String.valueOf(this.jTableEntriesDBChecks.getSelectedRowCount()));
                break;
            case 1:
                this.jTextFieldEntriesChecks.setText(String.valueOf(this.jTableEntriesExcelChecks.getRowCount()));
                this.jTextFieldSelectedChecks.setText(String.valueOf(this.jTableEntriesExcelChecks.getSelectedRowCount()));
                break;

            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_jTabbedPaneSelectChecksStateChanged

    private void jButtonSaveListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveListActionPerformed
        if (this.jTextFieldListName.getText().isEmpty()) {
            NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.needListName"), NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            jTextFieldListName.requestFocusInWindow();
            return;
        }

        if (this.jTextFieldDescription.getText().isEmpty()) {
            NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.needDescription"), NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            jTextFieldDescription.requestFocusInWindow();
            return;
        }


        if (this.jTableFinal.getRowCount() == 0) {
            NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.empty"), NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            return;
        }

        if (AppServicesProxy.getDefault().appServices().existGermplasmListName(jTextFieldListName.getText())) {
            NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.listNameAlreadyExists"), NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            return;
        }

        System.out.println("ANTES DE GUARDAR LA LISTA");

        saveList();

        System.out.println("YA GUARDO LA LISTA");
        NotifyDescriptor d2 = new NotifyDescriptor.Message(NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.saved"), NotifyDescriptor.INFORMATION_MESSAGE);
        DialogDisplayer.getDefault().notify(d2);
        this.jTextFieldListName.setText("");
        this.jTextFieldDescription.setText("");

        // refreshData();

    }//GEN-LAST:event_jButtonSaveListActionPerformed

    private void saveList() {

        Integer loggedUserid = AppServicesProxy.getDefault().appServices().getLoggedUserId(FieldbookSettings.getLocalGmsUserId());
        Listnms listnms = new Listnms();
        listnms.setListname(this.jTextFieldListName.getText());

        listnms.setListdate(ConvertUtils.getDateAsInteger(new java.util.Date()));
        listnms.setListtype(Listnms.LIST_TYPE_LIST);
        listnms.setListuid(loggedUserid);
        listnms.setListdesc(this.jTextFieldDescription.getText());
        listnms.setLhierarchy(0);
        listnms.setListstatus(1);

        // add list to database
        AppServicesProxy.getDefault().appServices().addListnms(listnms);


        List<Listdata> dataList = new ArrayList<Listdata>();

        int gid = findColumn("GID");
        int desig = findColumn("DESIG");
        int entryCD = findColumn("ENTRY");
        int source = findColumn("SOURCE");

        for (int i = 0; i < jTableFinal.getRowCount(); i++) {
            Listdata listdata = new Listdata(true);
            ListdataPK pk1 = new ListdataPK(listnms.getListid(), 0);

            listdata.setListdataPK(pk1);
            listdata.setEntryid(i + 1);
            if (desig > 0) {
                listdata.setDesig(this.jTableFinal.getValueAt(i, desig).toString());  //*
            } else {
                listdata.setDesig("");
            }
            if (entryCD >= 0) {
                int entryNumber = ConvertUtils.getValueAsInteger(this.jTableFinal.getValueAt(i, entryCD));
                listdata.setEntrycd("E" + ConvertUtils.getZeroLeading(entryNumber, 4));
            } else {
                listdata.setEntrycd("");
            }

            if (source >= 0) {
                String sourceStr = this.jTableFinal.getValueAt(i, source).toString();
                if (sourceStr == null) {

                    listdata.setSource("");
                } else {
                    if (sourceStr.isEmpty()) {
                    } else {
                        listdata.setSource(sourceStr);
                    }
                }
            }
            listdata.setGrpname("");


            listdata.setLrstatus(0);      //*
            //d1.setLlrecid(0);

            if (gid > 0) {
                listdata.setGid(Integer.parseInt(jTableFinal.getValueAt(i, gid).toString()));
            } else {
                listdata.setGid(0);
            }
            dataList.add(listdata);

        }



        AppServicesProxy.getDefault().appServices().addNewsGermplasm(listnms, dataList, loggedUserid);

        //fillComboListNames();

        addRecentListToCombos(listnms);

        openRecentList(listnms);

        //clear list
        toAdd.clear();
        toAddChecks.clear();
    }

    /**
     * Add recently list name created to combobox to avoid full reaload of items
     */
    private void addRecentListToCombos(Listnms listnms) {
        cboGermplasmList.addItem(listnms);
        cboGermplasmListChecks.addItem(listnms);
    }

    private int findColumn(String col) {
        int myCol = 0;

        try {
            myCol = jTableFinal.getTableHeader().getColumnModel().getColumnIndex(col);
        } catch (NullPointerException ex) {
            myCol = 0;
        }

        return myCol;
    }

    private void jRadioButtonPositionStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButtonPositionStateChanged
        if (jRadioButtonPosition.isSelected()) {
            actualizaIndicesPosicion();
            this.jTableFinalChecks.updateUI();
        }
    }//GEN-LAST:event_jRadioButtonPositionStateChanged

    private void jRadioButtonSequenceStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButtonSequenceStateChanged
        if (jRadioButtonSequence.isSelected()) {
            actualizaIndicesPosicion();
            this.jTableFinalChecks.updateUI();
        }
    }//GEN-LAST:event_jRadioButtonSequenceStateChanged

    private void jSpinnerPositionStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerPositionStateChanged

        this.initialPosition = (Integer) this.jSpinnerPosition.getValue();
        actualizaIndicesPosicion();
        actualizaSpinnerFreq();
        this.jTableFinalChecks.updateUI();
    }//GEN-LAST:event_jSpinnerPositionStateChanged

    private void jSpinnerPositionPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSpinnerPositionPropertyChange
   }//GEN-LAST:event_jSpinnerPositionPropertyChange

    private void jSpinnerFrequencyStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerFrequencyStateChanged
        this.frequency = (Integer) this.jSpinnerFrequency.getValue();
        actualizaIndicesFrecuencia();
        this.jTableFinalChecks.updateUI();

    }//GEN-LAST:event_jSpinnerFrequencyStateChanged

    private void jTableEntriesDBChecksPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableEntriesDBChecksPropertyChange
        switch (jTabbedPaneSelectChecks.getSelectedIndex()) {
            case 0:
                this.jTextFieldEntriesChecks.setText(String.valueOf(this.jTableEntriesDBChecks.getRowCount()));
                this.jTextFieldSelectedChecks.setText(String.valueOf(this.jTableEntriesDBChecks.getSelectedRowCount()));
                break;
            case 1:
                this.jTextFieldEntriesChecks.setText(String.valueOf(this.jTableEntriesExcelChecks.getRowCount()));
                this.jTextFieldSelectedChecks.setText(String.valueOf(this.jTableEntriesExcelChecks.getSelectedRowCount()));
                break;
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_jTableEntriesDBChecksPropertyChange

    private void jTableEntriesDBPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableEntriesDBPropertyChange
        switch (jTabbedPaneSource.getSelectedIndex()) {
            case 0:
                this.jTextFieldEntries.setText(String.valueOf(this.jTableEntriesDB.getRowCount()));
                this.jTextFieldSelectedEntries.setText(String.valueOf(this.jTableEntriesDB.getSelectedRowCount()));
                break;
            case 1:
                this.jTextFieldEntries.setText(String.valueOf(this.jTableEntriesExcel.getRowCount()));
                this.jTextFieldSelectedEntries.setText(String.valueOf(this.jTableEntriesExcel.getSelectedRowCount()));
                break;
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_jTableEntriesDBPropertyChange

    private void jTableEntriesExcelPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableEntriesExcelPropertyChange
        switch (jTabbedPaneSource.getSelectedIndex()) {
            case 0:
                this.jTextFieldEntries.setText(String.valueOf(this.jTableEntriesDB.getRowCount()));
                this.jTextFieldSelectedEntries.setText(String.valueOf(this.jTableEntriesDB.getSelectedRowCount()));
                break;
            case 1:
                this.jTextFieldEntries.setText(String.valueOf(this.jTableEntriesExcel.getRowCount()));
                this.jTextFieldSelectedEntries.setText(String.valueOf(this.jTableEntriesExcel.getSelectedRowCount()));
                break;
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_jTableEntriesExcelPropertyChange

    private void jTableEntriesExcelChecksPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableEntriesExcelChecksPropertyChange
        switch (jTabbedPaneSelectChecks.getSelectedIndex()) {
            case 0:
                this.jTextFieldEntriesChecks.setText(String.valueOf(this.jTableEntriesDBChecks.getRowCount()));
                this.jTextFieldSelectedChecks.setText(String.valueOf(this.jTableEntriesDBChecks.getSelectedRowCount()));
                break;
            case 1:
                this.jTextFieldEntriesChecks.setText(String.valueOf(this.jTableEntriesExcelChecks.getRowCount()));
                this.jTextFieldSelectedChecks.setText(String.valueOf(this.jTableEntriesExcelChecks.getSelectedRowCount()));
                break;
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_jTableEntriesExcelChecksPropertyChange

    private void jTableFinalSourcePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableFinalSourcePropertyChange
        this.jTextFieldSourceEntries.setText(String.valueOf(this.jTableFinalSource.getRowCount()));
        this.jTextFieldSelectedSource.setText(String.valueOf(this.jTableFinalSource.getSelectedRowCount()));
        this.maximo = this.jTableFinalSource.getRowCount();
    }//GEN-LAST:event_jTableFinalSourcePropertyChange

    private void jTableFinalChecksPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableFinalChecksPropertyChange

        try {

            if (evt.getNewValue() != null) {
                this.jTextFielTotalChecks.setText(String.valueOf(this.jTableFinalChecks.getRowCount()));
                this.jTextFieldSelectedTotalChecks.setText(String.valueOf(this.jTableFinalChecks.getSelectedRowCount()));
                actualizaSpinner();


                actualizaIndicesPosicion();
                actualizaIndicesFrecuencia();

            }


        } catch (Exception error) {
            error.printStackTrace();
        }

    }//GEN-LAST:event_jTableFinalChecksPropertyChange

    private void actualizaSpinner() {


        SpinnerNumberModel modeloSpinner = (SpinnerNumberModel) this.jSpinnerPosition.getModel();
        modeloSpinner.setMaximum(maximo + 1);



    }

    private void actualizaSpinnerFreq() {
        SpinnerNumberModel modeloSpinner = (SpinnerNumberModel) this.jSpinnerFrequency.getModel();
        modeloSpinner.setValue(this.jTableFinalChecks.getRowCount() + 1);
    }
    private void jTabbedPaneChecksStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPaneChecksStateChanged

        switch (jTabbedPaneChecks.getSelectedIndex()) {
           // case 2://final list
            case 1 : //final list

                //
                //if (this.jRadioButtonWith.isSelected()) {
                //    fillChecks();
                //} else {
                //    fillWithoutChecks();
                //}
                // allways fill final list without checks
                fillWithoutChecks();
                break;

        }


    }//GEN-LAST:event_jTabbedPaneChecksStateChanged

    public ArrayList<SequenceEntry> calculaPosicionesSecuencia() {

        posicionesSecuencia = new ArrayList<int[]>();
        int restantes = 0;

        this.totalEntries = maximo;

        GermplasmEntriesTableModelChecks tableModelChecks = (GermplasmEntriesTableModelChecks) this.jTableFinalChecks.getModel();

        int colPosition = tableModelChecks.findColumn("Initial position");
        int colFreq = tableModelChecks.findColumn("Frequency");

        ArrayList<Integer> posInt = new ArrayList<Integer>();

        int entradasDepositar = (Integer.parseInt(tableModelChecks.getValueAt(0, colFreq).toString())) - 1;
        int posInitial = Integer.parseInt(tableModelChecks.getValueAt(0, colPosition).toString());
        int freqInitial = Integer.parseInt(tableModelChecks.getValueAt(0, colFreq).toString());
        int agregados = 0;
        int checksToAdd = 0;
        int posicionActual = posInitial;

        restantes = totalEntries - (posInitial - 1);
        sequenceList = new ArrayList<SequenceEntry>();

        while (restantes > 0) {
            if (checksToAdd < tableModelChecks.getRowCount()) {
                checksToAdd++;
            }
            for (int i = 0; i < checksToAdd; i++) {

                if (i > 0) {
                    posicionActual++;
                }
                // System.out.println("Actual: " + posicionActual);
                posInt.add(posicionActual);
                SequenceEntry sequenceEntry = new SequenceEntry();
                sequenceEntry.setPosicion(posicionActual);
                sequenceEntry.setEntrada(i);
                sequenceList.add(sequenceEntry);
            }
            int temp = (entradasDepositar + 1) - checksToAdd;
            restantes = restantes - temp;
            // System.out.println("Quedan: " + restantes);
            posicionActual = posicionActual + ((entradasDepositar + 1) - (checksToAdd - 1));
            //  System.out.println("POS ACTUAL: " + posicionActual);
        }

//         for (int i = 0; i < posInt.size(); i++) {
//             System.out.println("POSSSS: "+posInt.get(i));
//             
//         }
        return sequenceList;
    }

    private void fillChecks() {


        GermplasmEntriesTableModelChecks tableModel = (GermplasmEntriesTableModelChecks) this.jTableFinalSource.getModel();
        List<List<Object>> germplasmData = tableModel.getGermplasmData();
        List<List<Object>> germplasmDataTemp = new ArrayList<List<Object>>();


        tableModel = new GermplasmEntriesTableModelChecks(factores, germplasmDataTemp);
        this.jTableFinal.setModel(tableModel);

        if (this.jTableFinalSource.getRowCount() == 0) {
            tableModel = new GermplasmEntriesTableModelChecks(factores, germplasmDataTemp);
            this.jTableFinal.setModel(tableModel);
            return;
        }

        for (int i = 0; i < germplasmData.size(); i++) {
            List<Object> data = new ArrayList<Object>();
            Object[] fila = germplasmData.get(i).toArray();
            Object[] temp = fila.clone();

            for (int j = 0; j < temp.length; j++) {
                data.add(j, temp[j]);

            }
            germplasmDataTemp.add(i, data);

        }
        int colEntry = tableModel.findColumn("ENTRY");

        if (this.jTableFinalChecks.getRowCount() > 0) {

            if (!validateTable()) {

                NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.frequency"), NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(d);

                this.jRadioButtonWithout.setSelected(true);

                return;
            }


            GermplasmEntriesTableModelChecks tableModelChecks = (GermplasmEntriesTableModelChecks) this.jTableFinalChecks.getModel();
            List<List<Object>> germplasmDataChecks = tableModelChecks.getGermplasmData();



            if (colEntry < 0) {
                return;
            }


            if (checksInSequence()) {

                sequenceList = calculaPosicionesSecuencia();
                posiciones = calculaPosicionesSecuenciaInteger();

                Collections.sort(sequenceList, new MyIntComparable());

                for (int j = 0; j < sequenceList.size(); j++) {

                    List<Object> newData = new ArrayList<Object>();

                    Object[] check = germplasmDataChecks.get(sequenceList.get(j).getEntrada()).toArray();
                    Object[] temp = check.clone();

                    for (int k = 0; k < temp.length; k++) {
                        newData.add(k, temp[k]);
                    }


                    //System.out.println("Insertando entrada "+sequenceList.get(j).getEntrada() +"   en pos "+(sequenceList.get(j).getPosicion() - 1));

                    int posToAdd = sequenceList.get(j).getPosicion() - 1;
                    // System.out.println("POS TO ADD "+posToAdd);

                    germplasmDataTemp.add(posToAdd, newData);


                }


            } else {
                int contador = 0;
                posiciones = calculaPosiciones();

                for (int i = 0; i < posiciones.size(); i++) {

                    List<Object> newData = new ArrayList<Object>();
                    Object[] nuevo = germplasmDataChecks.get(contador).toArray();
                    Object[] temp = nuevo.clone();

                    for (int j = 0; j < temp.length; j++) {
                        newData.add(j, temp[j]);

                    }

                    int pos = posiciones.get(i);
                    germplasmDataTemp.add(pos - 1, newData);
                    contador++;
                    if (contador > tableModelChecks.getRowCount() - 1) {
                        contador = 0;
                    }
                }

            }

        }






        recorreIndices(germplasmDataTemp, colEntry);

        assignGermplasmEntries(factores, germplasmDataTemp, colEntry);

    }

    public boolean checksInSequence() {
        if (this.jRadioButtonPosition.isSelected()) {
            return false;
        } else {
            return true;
        }
    }

    public class MyIntComparable implements Comparator<SequenceEntry> {

        @Override
        public int compare(SequenceEntry o1, SequenceEntry o2) {
            return (o1.getPosicion() < o2.getPosicion() ? -1 : (o1.getPosicion() == o2.getPosicion() ? 0 : 1));
        }
    }

    private void fillChecks2() {
        GermplasmEntriesTableModelChecks tableModel = (GermplasmEntriesTableModelChecks) this.jTableFinalSource.getModel();
        List<List<Object>> germplasmData = tableModel.getGermplasmData();
        List<List<Object>> germplasmDataTemp = new ArrayList<List<Object>>();

        if (this.jTableFinalSource.getRowCount() == 0) {
            tableModel = new GermplasmEntriesTableModelChecks(factores, germplasmDataTemp);
            this.jTableFinal.setModel(tableModel);
            return;
        }

        for (int i = 0; i < germplasmData.size(); i++) {
            List<Object> data = new ArrayList<Object>();
            Object[] fila = germplasmData.get(i).toArray();
            Object[] temp = fila.clone();
            for (int j = 0; j < temp.length; j++) {
                data.add(j, temp[j]);
            }
            germplasmDataTemp.add(i, data);
        }

        int colEntry = tableModel.findColumn("ENTRY");

        if (this.jTableFinalChecks.getRowCount() > 0) {

            if (!validateTable()) {

                NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.frequency"), NotifyDescriptor.ERROR_MESSAGE);
                DialogDisplayer.getDefault().notify(d);

                this.jRadioButtonWithout.setSelected(true);

                return;
            }


            GermplasmEntriesTableModelChecks tableModelChecks = (GermplasmEntriesTableModelChecks) this.jTableFinalChecks.getModel();
            List<List<Object>> germplasmDataChecks = tableModelChecks.getGermplasmData();



            if (colEntry < 0) {
                return;
            }

            int contador = 0;


            posiciones = calculaPosiciones();

            posiciones = quitaRepetidos(posiciones);


            Collections.sort(posiciones);

//            for (int i = 0; i < posiciones.size(); i++) {
//                System.out.println("POSICION:--> " + posiciones.get(i).toString());
//
//            }

            for (int i = 0; i < posiciones.size(); i++) {

                List<Object> newData = new ArrayList<Object>();
                Object[] nuevo = germplasmDataChecks.get(contador).toArray();
                Object[] temp = nuevo.clone();

                for (int j = 0; j < temp.length; j++) {
                    newData.add(j, temp[j]);
                }

                int pos = posiciones.get(i);
                germplasmDataTemp.add(pos - 1, newData);

                contador++;
                if (contador > tableModelChecks.getRowCount() - 1) {
                    contador = 0;
                }
            }


        }
        recorreIndices(germplasmDataTemp, colEntry);

        ArrayList<Integer> posic = new ArrayList<Integer>();

        for (int i = 0; i < posiciones.size(); i++) {
            posic.add(posiciones.get(i) - 1);

        }

        assignGermplasmEntries(factores, germplasmDataTemp, colEntry, posic);

    }

    private void fillWithoutChecksOLD() {


        GermplasmEntriesTableModelChecks tableModel = (GermplasmEntriesTableModelChecks) this.jTableFinalSource.getModel();
        List<List<Object>> germplasmData = tableModel.getGermplasmData();

        List<List<Object>> germplasmDataTemp = new ArrayList<List<Object>>();


        if (this.jTableFinalSource.getRowCount() == 0) {
            tableModel = new GermplasmEntriesTableModelChecks(factores, germplasmDataTemp);
            this.jTableFinal.setModel(tableModel);
            return;
        }


        for (int i = 0; i < germplasmData.size(); i++) {
            List<Object> data = new ArrayList<Object>();
            Object[] fila = germplasmData.get(i).toArray();
            Object[] temp = fila.clone();

            for (int j = 0; j < temp.length; j++) {
                data.add(j, temp[j]);

            }
            germplasmDataTemp.add(i, data);

        }

        int colEntry = tableModel.findColumn("ENTRY");
        recorreIndices(germplasmDataTemp, colEntry);

        assignGermplasmEntries(factores, germplasmDataTemp, colEntry);
    }

    private void fillWithoutChecks() {


        GermplasmEntriesTableModelChecks tableModel = (GermplasmEntriesTableModelChecks) this.jTableFinalSource.getModel();
        List<List<Object>> germplasmData = tableModel.getGermplasmData();

        List<List<Object>> germplasmDataTemp = new ArrayList<List<Object>>();


        if (this.jTableFinalSource.getRowCount() == 0) {
            tableModel = new GermplasmEntriesTableModelChecks(factores, germplasmDataTemp);
            this.jTableFinal.setModel(tableModel);
            return;
        }


        for (int i = 0; i < germplasmData.size(); i++) {
            List<Object> data = new ArrayList<Object>();
            Object[] fila = germplasmData.get(i).toArray();
            Object[] temp = fila.clone();

            for (int j = 0; j < temp.length; j++) {
                data.add(j, temp[j]);

            }
            germplasmDataTemp.add(i, data);

        }

        int colEntry = tableModel.findColumn("ENTRY");
        recorreIndices(germplasmDataTemp, colEntry);
        ArrayList<Integer> pos = new ArrayList<Integer>();

        assignGermplasmEntries(factores, germplasmDataTemp, colEntry, pos);
    }

    private void jTableFinalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableFinalPropertyChange
        this.jLabelFinalEntries.setText(String.valueOf(this.jTableFinal.getRowCount()));
        if (this.jTableFinal.getRowCount() > 0) {
            this.jButtonSaveList.setEnabled(true);
        } else {
            this.jButtonSaveList.setEnabled(false);
        }

    }//GEN-LAST:event_jTableFinalPropertyChange

    private void jRadioButtonPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonPositionActionPerformed
   }//GEN-LAST:event_jRadioButtonPositionActionPerformed

    private void jRadioButtonWithPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jRadioButtonWithPropertyChange
    }//GEN-LAST:event_jRadioButtonWithPropertyChange

    private void jRadioButtonWithoutPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jRadioButtonWithoutPropertyChange
    }//GEN-LAST:event_jRadioButtonWithoutPropertyChange

    private void jRadioButtonWithActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonWithActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonWithActionPerformed

    private void jRadioButtonWithStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButtonWithStateChanged
        if (jRadioButtonWith.isSelected()) {
            fillChecks();
        }
    }//GEN-LAST:event_jRadioButtonWithStateChanged

    private void jRadioButtonWithoutStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButtonWithoutStateChanged

        if (jRadioButtonWithout.isSelected()) {
            fillWithoutChecks();
        }
    }//GEN-LAST:event_jRadioButtonWithoutStateChanged

    private void cboGermplasmListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGermplasmListActionPerformed
    }//GEN-LAST:event_cboGermplasmListActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed

        List<List<Object>> sourceEntries = new ArrayList<List<Object>>();
        JTable sourceTable = null;

        switch (jTabbedPaneSource.getSelectedIndex()) {

            case 0:
                sourceEntries = rowListDB;
                sourceTable = jTableEntriesDB;
                break;
            case 1:
                sourceEntries = rowListExcel;
                sourceTable = jTableEntriesExcel;
                break;
        }


        List<List<Object>> toRemoveLocal = new ArrayList<List<Object>>();
        int[] selectedRows = sourceTable.getSelectedRows();
        for (int i = 0; i < selectedRows.length; i++) {

            toAdd.add(sourceEntries.get(selectedRows[i]));
            toRemoveLocal.add(sourceEntries.get(selectedRows[i]));
        }
        sourceEntries.removeAll(toRemoveLocal);

        GermplasmEntriesTableModelChecks tableModel = new GermplasmEntriesTableModelChecks(factores, toAdd);
        jTableFinalSource.setModel(tableModel);
        sourceTable.getSelectionModel().clearSelection();
        jTableFinalSource.updateUI();
        sourceTable.updateUI();
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveActionPerformed

        List<List<Object>> sourceEntries = new ArrayList<List<Object>>();
        JTable destTable = null;

        destTable = jTableFinalSource;

        List<List<Object>> toRemoveLocal = new ArrayList<List<Object>>();
        int[] selectedRows = destTable.getSelectedRows();

        for (int i = 0; i < selectedRows.length; i++) {
            toRemoveLocal.add(toAdd.get(selectedRows[i]));

        }
        toAdd.removeAll(toRemoveLocal);
        destTable.getSelectionModel().clearSelection();
        destTable.updateUI();
    }//GEN-LAST:event_jButtonRemoveActionPerformed

    private void jButtonRemoveChecksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveChecksActionPerformed
        List<List<Object>> sourceEntries = new ArrayList<List<Object>>();
        JTable destTable = null;

        destTable = jTableFinalChecks;

        List<List<Object>> toRemoveLocal = new ArrayList<List<Object>>();
        int[] selectedRows = destTable.getSelectedRows();

        for (int i = 0; i < selectedRows.length; i++) {
            toRemoveLocal.add(toAddChecks.get(selectedRows[i]));

        }
        toAddChecks.removeAll(toRemoveLocal);
        destTable.getSelectionModel().clearSelection();
        destTable.updateUI();
    }//GEN-LAST:event_jButtonRemoveChecksActionPerformed

    private void jButtonAddChecksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddChecksActionPerformed
        List<List<Object>> sourceEntries = new ArrayList<List<Object>>();
        JTable sourceTable = null;
        boolean hasChecks = false;

        switch (jTabbedPaneSelectChecks.getSelectedIndex()) {

            case 0:
                sourceEntries = rowListDBChecks;
                sourceTable = jTableEntriesDBChecks;
                break;
            case 1:
                sourceEntries = rowListExcelChecks;
                sourceTable = jTableEntriesExcelChecks;
                hasChecks = true;
                break;
        }


        List<List<Object>> toRemoveLocal = new ArrayList<List<Object>>();
        int[] selectedRows = sourceTable.getSelectedRows();



        switch (jTabbedPaneSelectChecks.getSelectedIndex()) {

            case 0:
                for (int i = 0; i < selectedRows.length; i++) {

                    toAddChecks.add(sourceEntries.get(selectedRows[i]));
                    toRemoveLocal.add(sourceEntries.get(selectedRows[i]));
                }
                break;
            case 1:
                for (int i = 0; i < selectedRows.length; i++) {

                    sourceEntries.get(selectedRows[i]).add(1);
                    sourceEntries.get(selectedRows[i]).add(1);

                    toAddChecks.add(sourceEntries.get(selectedRows[i]));


                    toRemoveLocal.add(sourceEntries.get(selectedRows[i]));
                }
                break;
        }








        sourceEntries.removeAll(toRemoveLocal);

        GermplasmEntriesTableModelChecks tableModel = new GermplasmEntriesTableModelChecks(factores, toAddChecks);
        tableModel.setHasChecks(hasChecks);
        jTableFinalChecks.setModel(tableModel);
        sourceTable.getSelectionModel().clearSelection();
        //  jTableFinalChecks.updateUI();
        //  sourceTable.updateUI();
    }//GEN-LAST:event_jButtonAddChecksActionPerformed

    private void btnSearchListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchListActionPerformed
        searchList();
    }//GEN-LAST:event_btnSearchListActionPerformed

    private void btnSearchCheckListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchCheckListActionPerformed
        searchChecksList();
    }//GEN-LAST:event_btnSearchCheckListActionPerformed

    private void recorreIndices(List<List<Object>> germplasmData, int colEntry) {

        for (int j = 0; j < germplasmData.size(); j++) {
            List<Object> gsm = germplasmData.get(j);
            gsm.set(colEntry, j + 1);

        }
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
            //File archivoNulo = new File("");
            //selectorArchivo.setSelectedFile(archivoNulo);
            File archivoNulo = new File(OSUtils.getGermplasmListsPath());
            if (archivoNulo.exists()) {
                selectorArchivo.setSelectedFile(archivoNulo);
            } else {
                archivoNulo = new File("");
            }

            selectorArchivo.setSelectedFile(archivoNulo);
        }


        selectorArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        selectorArchivo.addChoosableFileFilter(new ExcelFiltro());
        int resultado = selectorArchivo.showOpenDialog(null);

        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }

        switch (opcion) {
            case 0:
                this.jTextAreaPath.setText(selectorArchivo.getSelectedFile().toString());
                break;
            case 1:
                this.jTextAreaPathChecks.setText(selectorArchivo.getSelectedFile().toString());
                break;
        }


    }

    @SuppressWarnings("unchecked")
    private void setGermplasmListIntoTable33(GermplasmList germplasmList, JTable tabla, int option) {
        List<String> columnList = gat.getColumnList(factores);
        GermplasmEntriesTableModelChecks tableModel = null;

        switch (option) {
            case 0://source
                this.jTextFieldEntries.setText(String.valueOf(germplasmList.getListEntries().size()));
                break;
            case 1://checks
                this.jTextFieldEntriesChecks.setText(String.valueOf(germplasmList.getListEntries().size()));
                break;

        }

        rowListExcel = gat.getMappedColumns(columnList, germplasmList);
        tableModel = new GermplasmEntriesTableModelChecks(factores, rowListExcel);

        tabla.setModel(tableModel);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int col = 0; col < tabla.getColumnCount(); col++) {
            tabla.getColumnModel().getColumn(col).setCellRenderer(tcr);
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
            case 0://source
                if (validFile) {
                    try {
                        GermplasmList germplasmList = germplasmListReader.getGermPlasmList(myFile);
                        setGermplasmListIntoTable(germplasmList, tabla, 1, 0);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        System.out.println("ERROR AL LEER EXCEL GERMPLASM ENTRIES: " + ex);
                    }
                } else {
                    GermplasmEntriesTableModelChecks modeloTablaEntries = new GermplasmEntriesTableModelChecks();
                    this.jTableEntriesExcel.setModel(modeloTablaEntries);
                    this.jTextAreaPath.setText("");
                    this.jTextFieldEntries.setText("0");
                    DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.notValid"), NotifyDescriptor.ERROR_MESSAGE));
                }


                germplasmSourceTransferHandler.setSourceList(rowListExcel);
                germplasmSourceTransferHandler.setSourceTable(jTableEntriesExcel);
                germplasmSourceTransferHandler.setFactores(factores);


                break;
            case 1://checks
                if (validFile) {
                    try {
                        GermplasmList germplasmList = germplasmListReader.getGermPlasmList(myFile);
                        setGermplasmListIntoTable(germplasmList, tabla, 1, 1);
                    } catch (Exception ex) {
                        System.out.println("ERROR AL LEER EXCEL GERMPLASM ENTRIES: " + ex);
                    }
                } else {
                    GermplasmEntriesTableModelChecks modeloTablaEntries = new GermplasmEntriesTableModelChecks();
                    this.jTableEntriesExcelChecks.setModel(modeloTablaEntries);
                    this.jTextAreaPathChecks.setText("");
                    this.jTextFieldEntriesChecks.setText("0");
                    DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(NbBundle.getMessage(addChecksTopComponent.class, "addChecksTopComponent.notValid"), NotifyDescriptor.ERROR_MESSAGE));
                }

                germplasmTransferHandler.setSourceList(rowListExcelChecks);
                germplasmTransferHandler.setSourceTable(jTableEntriesExcelChecks);
                germplasmTransferHandler.setFactores(factores);
                break;
        }

        jTableEntriesExcel.requestFocus();

    }

    @SuppressWarnings("unchecked")
    private void setGermplasmListIntoTable(GermplasmList germplasmList, JTable tabla, int opcion, int form) {
        List<String> columnList = gat.getColumnList(factores);


        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();

        switch (form) {
            case 0://sources

                GermplasmEntriesTableModelChecks tableModel = null;

                switch (opcion) {
                    case 0:
                        rowListDB = gat.getMappedColumns(columnList, germplasmList);
                        validateValuesForSource(rowListDB);
                        tableModel = new GermplasmEntriesTableModelChecks(factores, rowListDB);
                        break;
                    case 1:
                        rowListExcel = gat.getMappedColumns(columnList, germplasmList);
                        validateValuesForSource(rowListExcel);
                        tableModel = new GermplasmEntriesTableModelChecks(factores, rowListExcel);
                        break;
                }



                this.jTextFieldEntries.setText(String.valueOf(germplasmList.getListEntries().size()));
                tabla.setModel(tableModel);
                tcr = new DefaultTableCellRenderer();
                tcr.setHorizontalAlignment(SwingConstants.CENTER);
                for (int col = 0; col < tabla.getColumnCount(); col++) {
                    tabla.getColumnModel().getColumn(col).setCellRenderer(tcr);
                }

                break;
            case 1://checks

                GermplasmEntriesTableModelChecks tableModelChecks = null;
                switch (opcion) {
                    case 0:
                        rowListDBChecks = gat.getMappedColumns(columnList, germplasmList);
                        validateValuesForSource(rowListDBChecks);
                        tableModelChecks = new GermplasmEntriesTableModelChecks(factores, rowListDBChecks);
                        break;
                    case 1:
                        rowListExcelChecks = gat.getMappedColumns(columnList, germplasmList);
                        validateValuesForSource(rowListExcelChecks);
                        tableModelChecks = new GermplasmEntriesTableModelChecks(factores, rowListExcelChecks);
                        break;
                }

                this.jTextFieldEntriesChecks.setText(String.valueOf(germplasmList.getListEntries().size()));

                tabla.setModel(tableModelChecks);
                tcr = new DefaultTableCellRenderer();
                tcr.setHorizontalAlignment(SwingConstants.CENTER);
                for (int col = 0; col < tabla.getColumnCount(); col++) {
                    tabla.getColumnModel().getColumn(col).setCellRenderer(tcr);
                }
                break;

        }



    }

    /**
     * Validates if source column is empty if source column is empty then uses
     * either list name from combo box or file name
     *
     * @param jtable
     */
    private void validateValuesForSource(List<List<Object>> dataList) {
        int SOURCE_COLUMN = 3;
        if (dataList.get(0).size() != 4) {
            return;
        }

        // get female list name
        String listName = "";

        switch (jTabbedPaneChecks.getSelectedIndex()) {
            case 0:
                if (jTabbedPaneSource.getSelectedIndex() == 0) {
                    if (cboGermplasmList.getSelectedItem() instanceof Listnms) {
                        Listnms femaleList = (Listnms) cboGermplasmList.getSelectedItem();
                        listName = femaleList.getListname();
                    } else if (cboGermplasmList.getSelectedItem() instanceof String) {
                        String strListName = (String) cboGermplasmList.getSelectedItem();
                        listName = strListName;
                    }
                } else {
                    listName = FileUtils.extractFileName(jTextAreaPath.getText());
                }
                break;
            case 1:
                if (jTabbedPaneSelectChecks.getSelectedIndex() == 0) {
                    if (cboGermplasmListChecks.getSelectedItem() instanceof Listnms) {
                        Listnms femaleList = (Listnms) cboGermplasmListChecks.getSelectedItem();
                        listName = femaleList.getListname();
                    } else if (cboGermplasmListChecks.getSelectedItem() instanceof String) {
                        String strListName = (String) cboGermplasmListChecks.getSelectedItem();
                        listName = strListName;
                    }
                } else {
                    listName = FileUtils.extractFileName(jTextAreaPathChecks.getText());
                }

        }

        int rowNumber = 1;
        for (int rowIndex = 0; rowIndex < dataList.size(); rowIndex++) {
            List<Object> columnList = dataList.get(rowIndex);
            String sourceText = (String) columnList.get(SOURCE_COLUMN);

            if (sourceText == null) {
                columnList.set(SOURCE_COLUMN, listName + ":" + rowNumber);
            } else if (sourceText.isEmpty()) {
                columnList.set(SOURCE_COLUMN, listName + ":" + rowNumber);
            }
            rowNumber++;
        }

    }

    private void actualizaIndicesPosicion() {
        if (this.jTableFinalChecks.getRowCount() == 0) {
            return;
        }

        GermplasmEntriesTableModelChecks tableModel = (GermplasmEntriesTableModelChecks) this.jTableFinalChecks.getModel();
        List<List<Object>> germplasmData = tableModel.getGermplasmData();

        int colPos = tableModel.findColumn("Initial position");
        if (colPos < 0) {
            return;
        }

        loadPositionIntoTable(germplasmData, colPos);
    }

    private void loadPositionIntoTable2(List<List<Object>> germplasmData, int colPos) {

        int temp = this.initialPosition;

        if (this.jRadioButtonPosition.isSelected()) {
            for (int j = 0; j < germplasmData.size(); j++) {
                List<Object> gsm = germplasmData.get(j);
                gsm.set(colPos, temp);
                temp++;
            }
        } else {

//            for (int j = 0; j < germplasmData.size(); j++) {
//                List<Object> gsm = germplasmData.get(j);
//                gsm.set(colPos, temp);
//                temp = temp + this.frequency + 1;
//               
//            }


            for (int j = 0; j < germplasmData.size(); j++) {
                List<Object> gsm = germplasmData.get(j);
                gsm.set(colPos, temp);
                temp = temp + this.frequency;

            }



        }

    }

    private void loadPositionIntoTable(List<List<Object>> germplasmData, int colPos) {

        int temp = this.initialPosition;

        if (this.jRadioButtonPosition.isSelected()) {
            for (int j = 0; j < germplasmData.size(); j++) {
                List<Object> gsm = germplasmData.get(j);
                gsm.set(colPos, temp);
                temp++;
            }
        } else {


            for (int j = 0; j < germplasmData.size(); j++) {
                List<Object> gsm = germplasmData.get(j);
                gsm.set(colPos, temp);
                temp = temp + this.frequency + 1;
            }



        }

    }

    private void actualizaIndicesFrecuencia() {
        if (this.jTableFinalChecks.getRowCount() == 0) {
            return;
        }

        GermplasmEntriesTableModelChecks tableModel = (GermplasmEntriesTableModelChecks) this.jTableFinalChecks.getModel();
        List<List<Object>> germplasmData = tableModel.getGermplasmData();

        int colFreq = tableModel.findColumn("Frequency");
        if (colFreq < 0) {
            return;
        }
        loadFrequencyIntoTable(germplasmData, colFreq);
    }

    private void loadFrequencyIntoTable(List<List<Object>> germplasmData, int colFreq) {
        for (int j = 0; j < germplasmData.size(); j++) {
            List<Object> gsm = germplasmData.get(j);
            gsm.set(colFreq, this.frequency);
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

        factor = new Factor();
        factor.setFactorName("SOURCE");
        factor.setProperty("SEED SOURCE");
        factor.setScale("NAME");
        factor.setDataType("C");
        factores.add(factor);
    }

    private void clearSelectionValues() {
        GermplasmEntriesTableModelChecks modelo = (GermplasmEntriesTableModelChecks) this.jTableEntriesDB.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.setValueAt(0, i, 3);

        }
    }

    public boolean validateTable() {


        if (this.jTableFinalChecks.getRowCount() == 0) {

            return true;
        }

        if (freqIsInvalid()) {

            return false;
        }

        if (this.jRadioButtonSequence.isSelected()) {
//            GermplasmEntriesTableModelChecks tableModelChecks = (GermplasmEntriesTableModelChecks) this.jTableFinalChecks.getModel();
//            int colPosition = tableModelChecks.findColumn("Initial position");
//
//            ArrayList<Object> posiciones = new ArrayList();
//
//            for (int i = 0; i < jTableFinalChecks.getRowCount(); i++) {
//                posiciones.add(jTableFinalChecks.getValueAt(i, colPosition));
//
//            }
//
//            if (hayposicionesRepetidas(posiciones)) {
//                return false;
//            }
//            if (hayPosicionesFueraRango(posiciones)) {
//                return false;
//            }
            return true;


        } else {
            return true;
        }
    }

    private boolean hayPosicionesFueraRango(ArrayList<Object> posiciones) {
        boolean fueraDeRango = false;
        int tam = posiciones.size();
        for (int i = 0; i < tam; i++) {
            int val = Integer.parseInt(posiciones.get(i).toString());
            if (val == 0 || val > (maximo + posiciones.size())) {
                System.out.println("HAY FUERA DE RANGO: " + val);
                return true;
            }
        }
        return fueraDeRango;
    }

    boolean freqIsInvalid() {
        if (this.frequency <= this.jTableFinalChecks.getRowCount()) {
            return true;
        } else {

            return false;
        }
    }

    private boolean hayposicionesRepetidas(ArrayList<Object> posiciones) {
        boolean hayRepetidos = false;
        int tam = posiciones.size();

        HashSet hs = new HashSet();
        hs.addAll(posiciones);
        posiciones.clear();
        posiciones.addAll(hs);

        if (tam != posiciones.size()) {
            hayRepetidos = true;
            System.out.println("HAY REPETIDOS");
        } else {
            hayRepetidos = false;;
        }

        return hayRepetidos;
    }

    private ArrayList<Integer> quitaRepetidos(ArrayList<Integer> posiciones) {

        ArrayList<Integer> sinRep = new ArrayList<Integer>();
        int tam = posiciones.size();
        HashSet hs = new HashSet();
        hs.addAll(posiciones);
        sinRep.clear();
        sinRep.addAll(hs);
        return sinRep;
    }

    public ArrayList<Integer> calculaPosiciones2() {

        ArrayList<Integer> positionsTable = new ArrayList();

        if (this.jTableFinalChecks.getRowCount() == 0) {
            return null;
        }

        GermplasmEntriesTableModelChecks tableModelChecks = (GermplasmEntriesTableModelChecks) this.jTableFinalChecks.getModel();
        int colPosition = tableModelChecks.findColumn("Initial position");

        if (colPosition < 0) {
            return null;
        }

        for (int i = 0; i < tableModelChecks.getRowCount(); i++) {
            int pos = Integer.parseInt(tableModelChecks.getValueAt(i, colPosition).toString());
            positionsTable.add(pos);
        }


        ArrayList<Integer> pos = new ArrayList();

        int total = this.jTableFinalChecks.getRowCount();
        int valor = this.initialPosition;
        int contador = 0;
        int max = maximo;


        while (valor <= max) {

            for (int i = 0; i < positionsTable.size(); i++) {
                valor = positionsTable.get(i);
                valor = valor + (frequency * contador);

                if (valor < max) {
                    pos.add(valor);
                    max++;

                }

            }
            contador++;
        }



        return pos;

    }

    public ArrayList<Integer> calculaPosicionesSecuenciaInteger() {
        ArrayList<Integer> posiciones = new ArrayList();
        for (int i = 0; i < sequenceList.size(); i++) {
            posiciones.add(sequenceList.get(i).getPosicion());

        }

        return posiciones;
    }

    public int getSequenceListSize() {
        return sequenceList.size();
    }

    public ArrayList<Integer> calculaPosiciones() {

        ArrayList<Integer> positionsTable = new ArrayList();

        if (this.jTableFinalChecks.getRowCount() == 0) {
            return null;
        }

        GermplasmEntriesTableModelChecks tableModelChecks = (GermplasmEntriesTableModelChecks) this.jTableFinalChecks.getModel();
        int colPosition = tableModelChecks.findColumn("Initial position");

        if (colPosition < 0) {
            return null;
        }

        for (int i = 0; i < tableModelChecks.getRowCount(); i++) {
            int pos = Integer.parseInt(tableModelChecks.getValueAt(i, colPosition).toString());
            positionsTable.add(pos);
        }


        ArrayList<Integer> pos = new ArrayList();

        int total = this.jTableFinalChecks.getRowCount();
        int valor = this.initialPosition;
        int contador = 0;
        int max = maximo;


        while (valor <= (max + 1)) {

            for (int i = 0; i < positionsTable.size(); i++) {
                valor = positionsTable.get(i);
                valor = valor + (frequency * contador);

                if (valor <= max + 1) {
                    pos.add(valor);
                    max++;

                }

            }
            contador++;
        }


        return pos;

    }

    public int getTamPos() {
        return this.jTableFinalChecks.getRowCount();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearchCheckList;
    private javax.swing.JButton btnSearchList;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox cboGermplasmList;
    private javax.swing.JComboBox cboGermplasmListChecks;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonAddChecks;
    private javax.swing.JButton jButtonBrowseExcelChecks;
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonClearChecks;
    private javax.swing.JButton jButtonRemove;
    private javax.swing.JButton jButtonRemoveChecks;
    private javax.swing.JButton jButtonSaveList;
    private javax.swing.JButton jButtonUp;
    private javax.swing.JButton jButtonUpChecks;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelFinalEntries;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButtonPosition;
    private javax.swing.JRadioButton jRadioButtonSequence;
    private javax.swing.JRadioButton jRadioButtonWith;
    private javax.swing.JRadioButton jRadioButtonWithout;
    private javax.swing.JScrollPane jScrollEntiresExcel;
    private javax.swing.JScrollPane jScrollEntiresExcelChecks;
    private javax.swing.JScrollPane jScrollEntriesDb;
    private javax.swing.JScrollPane jScrollEntriesDbChecks;
    private javax.swing.JScrollPane jScrollFinal;
    private javax.swing.JScrollPane jScrollFinalListChecks;
    private javax.swing.JScrollPane jScrollFinalSource;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator11;
    private javax.swing.JToolBar.Separator jSeparator12;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator9;
    private javax.swing.JSpinner jSpinnerFrequency;
    private javax.swing.JSpinner jSpinnerPosition;
    private javax.swing.JTabbedPane jTabbedPaneChecks;
    private javax.swing.JTabbedPane jTabbedPaneSelectChecks;
    private javax.swing.JTabbedPane jTabbedPaneSource;
    private javax.swing.JTable jTableEntriesDB;
    private javax.swing.JTable jTableEntriesDBChecks;
    private javax.swing.JTable jTableEntriesExcel;
    private javax.swing.JTable jTableEntriesExcelChecks;
    private javax.swing.JTable jTableFinal;
    private javax.swing.JTable jTableFinalChecks;
    private javax.swing.JTable jTableFinalSource;
    private javax.swing.JTextArea jTextAreaPath;
    private javax.swing.JTextArea jTextAreaPathChecks;
    private javax.swing.JTextField jTextFielTotalChecks;
    private javax.swing.JTextField jTextFieldDescription;
    private javax.swing.JTextField jTextFieldEntries;
    private javax.swing.JTextField jTextFieldEntriesChecks;
    private javax.swing.JTextField jTextFieldListName;
    private javax.swing.JTextField jTextFieldSelectedChecks;
    private javax.swing.JTextField jTextFieldSelectedEntries;
    private javax.swing.JTextField jTextFieldSelectedSource;
    private javax.swing.JTextField jTextFieldSelectedTotalChecks;
    private javax.swing.JTextField jTextFieldSourceEntries;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JToolBar jToolBar4;
    private java.awt.Label label1;
    private java.awt.Label label2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        refreshData();
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

    private void addListener() {
        ListSelectionModel cellSelectionSelectModel = jTableEntriesDB.getSelectionModel();
        cellSelectionSelectModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        cellSelectionSelectModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                jTextFieldSelectedEntries.setText(String.valueOf(jTableEntriesDB.getSelectedRowCount()));

            }
        });


        this.jTableEntriesDB.setSelectionModel(cellSelectionSelectModel);

        //--------------------------------------------------------------------------------------------

        ListSelectionModel cellSelectionChecksModel = jTableEntriesDBChecks.getSelectionModel();
        cellSelectionChecksModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        cellSelectionChecksModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                jTextFieldSelectedChecks.setText(String.valueOf(jTableEntriesDBChecks.getSelectedRowCount()));
            }
        });
        this.jTableEntriesDBChecks.setSelectionModel(cellSelectionChecksModel);
        //--------------------------------------------------------------------------------------------


        ListSelectionModel cellSelectionExcelSource = jTableEntriesExcel.getSelectionModel();
        cellSelectionExcelSource.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        cellSelectionExcelSource.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                jTextFieldSelectedEntries.setText(String.valueOf(jTableEntriesExcel.getSelectedRowCount()));
            }
        });
        this.jTableEntriesExcel.setSelectionModel(cellSelectionExcelSource);
//--------------------------------------------------------------------------------------------


        ListSelectionModel cellSelectionExcelChecksSource = jTableEntriesExcelChecks.getSelectionModel();
        cellSelectionExcelChecksSource.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        cellSelectionExcelChecksSource.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                jTextFieldSelectedChecks.setText(String.valueOf(jTableEntriesExcelChecks.getSelectedRowCount()));
            }
        });
        this.jTableEntriesExcelChecks.setSelectionModel(cellSelectionExcelChecksSource);
        //--------------------------------------------------------------------------------------------        


        ListSelectionModel cellSelectionFinalSource = jTableFinalSource.getSelectionModel();
        cellSelectionFinalSource.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        cellSelectionFinalSource.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                jTextFieldSelectedSource.setText(String.valueOf(jTableFinalSource.getSelectedRowCount()));
            }
        });
        this.jTableFinalSource.setSelectionModel(cellSelectionFinalSource);

        //--------------------------------------------------------------------------------------------        


        ListSelectionModel cellSelectionFinalChecks = jTableFinalChecks.getSelectionModel();
        cellSelectionFinalChecks.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        cellSelectionFinalChecks.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                jTextFieldSelectedTotalChecks.setText(String.valueOf(jTableFinalChecks.getSelectedRowCount()));
            }
        });
        this.jTableFinalChecks.setSelectionModel(cellSelectionFinalChecks);


    }

    private void asignaTransferSource() {
        jTableEntriesDB.setDragEnabled(true);
        jTableEntriesDB.setDropMode(DropMode.INSERT_ROWS);

        germplasmSourceTransferHandler = new GermplasmSourceTransferHandler(jTableEntriesDB, jTableFinalSource, rowListDB, toAdd);
        germplasmSourceTransferHandler.setDeleteFromSourceList(false);
        jTableFinalSource.setTransferHandler(germplasmSourceTransferHandler);
        jScrollFinalSource.setTransferHandler(germplasmSourceTransferHandler);

        jTableFinalSource.setDragEnabled(true);
        jTableFinalSource.setDropMode(DropMode.INSERT_ROWS);

        removeGermplasmTransferHandler = new RemoveGermplasmTransferHandler(jTableFinalSource, toAdd);
        jTableEntriesDB.setTransferHandler(removeGermplasmTransferHandler);
        jScrollEntriesDb.setTransferHandler(removeGermplasmTransferHandler);
        jTableEntriesExcel.setTransferHandler(removeGermplasmTransferHandler);
        jScrollEntiresExcel.setTransferHandler(removeGermplasmTransferHandler);
    }

    private void asignaTransferChecks() {
        jTableEntriesDBChecks.setDragEnabled(true);
        jTableEntriesDBChecks.setDropMode(DropMode.INSERT_ROWS);
        germplasmTransferHandler = new GermplasmTransferHandler(jTableEntriesDBChecks, jTableFinalChecks, rowListDBChecks, toAddChecks);
        germplasmTransferHandler.setDeleteFromSourceList(false);
        jTableFinalChecks.setTransferHandler(germplasmTransferHandler);
        jScrollFinalListChecks.setTransferHandler(germplasmTransferHandler);

        jTableFinalChecks.setDragEnabled(true);
        jTableFinalChecks.setDropMode(DropMode.INSERT_ROWS);

        removeGermplasmTransferHandler = new RemoveGermplasmTransferHandler(jTableFinalChecks, toAddChecks);
        jTableEntriesDBChecks.setTransferHandler(removeGermplasmTransferHandler);
        jScrollEntriesDbChecks.setTransferHandler(removeGermplasmTransferHandler);
        jTableEntriesExcelChecks.setTransferHandler(removeGermplasmTransferHandler);
        jScrollEntiresExcelChecks.setTransferHandler(removeGermplasmTransferHandler);
    }

    public void assignGermplasmEntries(List<Factor> factorHeaders, List<List<Object>> germplasmData, int colEntry) {
        GermplasmEntriesTableModelChecks tableModel = new GermplasmEntriesTableModelChecks(factorHeaders, germplasmData);
        this.jTableFinal.setModel(tableModel);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        this.jTableFinal.getColumnModel().getColumn(colEntry).setCellRenderer(tcr);
        // setRenders();
    }

    public void assignGermplasmEntries(List<Factor> factorHeaders, List<List<Object>> germplasmData, int colEntry, ArrayList<Integer> posic) {
        GermplasmEntriesTableModelChecks tableModel = new GermplasmEntriesTableModelChecks(factorHeaders, germplasmData);
        this.jTableFinal.setModel(tableModel);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        this.jTableFinal.getColumnModel().getColumn(colEntry).setCellRenderer(tcr);
        //jTableFinal.setDefaultRenderer(Object.class, new ColorRenderer(posic)); 
        // setRenders();
    }

    public ArrayList<Integer> getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(ArrayList<Integer> posiciones) {
        this.posiciones = posiciones;
    }

    private void asignaTableModelsDefault() {
        List<List<Object>> germplasmDataTemp = new ArrayList<List<Object>>();
        GermplasmEntriesTableModelChecks tableModel = new GermplasmEntriesTableModelChecks(factores, germplasmDataTemp);

        this.jTableFinalSource.setModel(tableModel);

    }

    private void setRenders() {
        for (int i = 0; i < this.jTableFinal.getColumnModel().getColumnCount(); i++) {
            jTableFinal.getColumnModel().getColumn(i).setHeaderRenderer(new MyRenderer(Color.cyan, Color.black));
        }
    }

    private void refreshData() {
        List<List<Object>> germplasmData = new ArrayList<List<Object>>();
        this.jTableEntriesDB.setModel(new GermplasmEntriesTableModelChecks());
        this.jTableEntriesExcel.setModel(new GermplasmEntriesTableModelChecks());
        this.jTableFinalSource.setModel(new GermplasmEntriesTableModelChecks());
        this.jTableEntriesDBChecks.setModel(new GermplasmEntriesTableModelChecks());
        this.jTableEntriesExcelChecks.setModel(new GermplasmEntriesTableModelChecks());
        this.jTableFinalChecks.setModel(new GermplasmEntriesTableModelChecks());
        this.jTableFinal.setModel(new GermplasmEntriesTableModelChecks());
        this.jTextAreaPath.setText("");
        this.jTextAreaPathChecks.setText("");
        this.jTabbedPaneChecks.setSelectedIndex(0);
    }

    private void openRecentList(Listnms listnms) {
        ListDataWindowTopComponent ldwtc = ListDataWindowTopComponent.getListDataWindowTopComponent(listnms);
        if (ldwtc == null) {
            ldwtc = new ListDataWindowTopComponent(listnms);
        }
        ldwtc.open();
        ldwtc.requestActive();

    }

    private void searchList() {
        rowListDB = new ArrayList<List<Object>>();
        SelectListDialog selectListDialog = new SelectListDialog();
        selectListDialog.showSearchDialog();
        selectListDialog.populateComboListNames(cboGermplasmList);
        if (selectListDialog.isListSelected()) {
            try {
                GermplasmListReader germplasmListReader = new GermplasmListReaderImpl();
                GermplasmList germplasmList = germplasmListReader.getGermPlasmListFromDB(selectListDialog.getSeledtedListnms().getListid());
                setGermplasmListIntoTable(germplasmList, this.jTableEntriesDB, 0, 0);

            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("ERROR AL LEER EXCEL GERMPLASM ENTRIES DB: " + ex);
            }
        } else {
            GermplasmEntriesTableModelChecks modeloTablaEntries = new GermplasmEntriesTableModelChecks();
            this.jTableEntriesDB.setModel(modeloTablaEntries);
            this.jTextAreaPath.setText("");
            this.jTextFieldEntries.setText("0");
        }
        germplasmSourceTransferHandler.setSourceList(rowListDB);
        germplasmSourceTransferHandler.setSourceTable(jTableEntriesDB);
        germplasmSourceTransferHandler.setFactores(factores);
    }

    private void searchChecksList() {

        rowListDBChecks = new ArrayList<List<Object>>();
        SelectListDialog selectListDialog = new SelectListDialog();
        selectListDialog.showSearchDialog();
        selectListDialog.populateComboListNames(cboGermplasmListChecks);
        if (selectListDialog.isListSelected()) {
            try {
                GermplasmListReader germplasmListReader = new GermplasmListReaderImpl();
                GermplasmList germplasmList = germplasmListReader.getGermPlasmListFromDB(selectListDialog.getSeledtedListnms().getListid());
                setGermplasmListIntoTable(germplasmList, this.jTableEntriesDBChecks, 0, 1);

            } catch (Exception ex) {
                System.out.println("ERROR AL LEER EXCEL GERMPLASM ENTRIES DB: " + ex);
            }
        } else {
            GermplasmEntriesTableModelChecks modeloTablaEntries = new GermplasmEntriesTableModelChecks();
            this.jTableEntriesDBChecks.setModel(modeloTablaEntries);
            this.jTextAreaPathChecks.setText("");
            this.jTextFieldEntriesChecks.setText("0");
        }
        germplasmTransferHandler.setSourceList(rowListDBChecks);
        germplasmTransferHandler.setSourceTable(jTableEntriesDBChecks);
        germplasmTransferHandler.setFactores(factores);
    }

    private void cleanGermplasmFinalList() {
        toAdd = new ArrayList<List<Object>>();
        GermplasmEntriesTableModelChecks tableModel = new GermplasmEntriesTableModelChecks(factores, toAdd);
        jTableFinalSource.setModel(tableModel);
        jTableFinalSource.getSelectionModel().clearSelection();
        jTableFinalSource.updateUI();
        jTableFinalSource.updateUI();

    }

    private static void printNode(GermplasmPedigreeTreeNode node, int level) {
        StringBuffer tabs = new StringBuffer();

        for (int ctr = 1; ctr < level; ctr++) {
            tabs.append("\t");
        }

        String name = node.getGermplasm().getPreferredName() != null ? node.getGermplasm().getPreferredName().getNval() : null;
        System.out.println(tabs.toString() + node.getGermplasm().getGid() + " : " + name);

        for (GermplasmPedigreeTreeNode parent : node.getLinkedNodes()) {
            printNode(parent, level + 1);
        }
    }
}
