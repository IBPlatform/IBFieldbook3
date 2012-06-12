package ibfb.germplasmlist.core;

import ibfb.domain.core.Factor;
import ibfb.domain.core.GermplasmList;
import ibfb.domain.core.ListOfEntries;
import ibfb.domain.core.Workbook;
import ibfb.germplasmlist.filters.ExcelFiltro;
import ibfb.germplasmlist.models.GermplasmEntriesTableModel;
import ibfb.germplasmlist.models.GermplasmTransferHandler;
import ibfb.germplasmlist.models.RemoveGermplasmTransferHandler;
import ibfb.settings.core.FieldbookSettings;
import ibfb.workbook.api.GermplasmAssigmentTool;
import ibfb.workbook.api.GermplasmListReader;
import ibfb.workbook.core.GermplasmAssigmentToolImpl;
import ibfb.workbook.core.GermplasmListReaderImpl;
import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.domain.Listdata;
import org.cimmyt.cril.ibwb.domain.ListdataPK;
import org.cimmyt.cril.ibwb.domain.Listnms;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

@ConvertAsProperties(dtd = "-//ibfb.germplasmlist.core//germplasmCreateList//EN",
autostore = false)
@TopComponent.Description(preferredID = "germplasmCreateListTopComponent",
iconBase = "ibfb/germplasmlist/images/germplasmIcon16.png",
persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
//@ActionID(category = "Window", id = "ibfb.germplasmlist.core.germplasmCreateListTopComponent")
//@ActionReference(path = "Menu/Window" /*
// * , position = 333
// */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_germplasmCreateListAction",
preferredID = "germplasmCreateListTopComponent")
public final class germplasmCreateListTopComponent extends TopComponent {

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
    List<List<Object>> toAdd;
    List<List<Object>> toRemove;
    private boolean esPrimeraVez = true;
    private List<Factor> factores;
    ImageIcon iconAdd = new ImageIcon("ibfb/germplasmlist/images/add.png");
    ImageIcon iconCancel = new ImageIcon("ibfb/germplasmlist/images/cancel.png");
    private GermplasmTransferHandler germplasmTransferHandler;
    private RemoveGermplasmTransferHandler removeGermplasmTransferHandler;

    public germplasmCreateListTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(germplasmCreateListTopComponent.class, "CTL_germplasmCreateListTopComponent"));
        setToolTipText(NbBundle.getMessage(germplasmCreateListTopComponent.class, "HINT_germplasmCreateListTopComponent"));
        fillComboListNames();
        toAdd = new ArrayList<List<Object>>();
        loadFactors();
        addListener();

        jTableEntriesDB.setDragEnabled(true);
        jTableEntriesDB.setDropMode(DropMode.INSERT_ROWS);

        germplasmTransferHandler = new GermplasmTransferHandler(jTableEntriesDB, jTableFinalList, rowListDB, toAdd);
        jTableFinalList.setTransferHandler(germplasmTransferHandler);
        jScrollFinalList.setTransferHandler(germplasmTransferHandler);

        jTableFinalList.setDragEnabled(true);
        jTableFinalList.setDropMode(DropMode.INSERT_ROWS);

        removeGermplasmTransferHandler = new RemoveGermplasmTransferHandler(jTableFinalList, toAdd);

        jTableEntriesDB.setTransferHandler(removeGermplasmTransferHandler);
        jScrollEntriesDb.setTransferHandler(removeGermplasmTransferHandler);
        jTableEntriesExcel.setTransferHandler(removeGermplasmTransferHandler);
        jScrollEntiresExcel.setTransferHandler(removeGermplasmTransferHandler);

        this.jButtonAddChecks.setVisible(false);
    }

    private void fillComboListNames() {
        List<Listnms> germplasmList = AppServicesProxy.getDefault().appServices().getListnmsList();
        for (Listnms list : germplasmList) {
            cboGermplasmList.addItem(list);
        }
    }

    public void addListener() {
        ListSelectionModel cellSelectionModel = jTableEntriesDB.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                jTextFieldSelectedEntries.setText(String.valueOf(jTableEntriesDB.getSelectedRowCount()));
            }
        });


        this.jTableEntriesDB.setSelectionModel(cellSelectionModel);


        ListSelectionModel cellSelectionFinalModel = jTableFinalList.getSelectionModel();
        cellSelectionFinalModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        cellSelectionFinalModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                jTextFieldSelectedFinal.setText(String.valueOf(jTableFinalList.getSelectedRowCount()));
            }
        });


        this.jTableFinalList.setSelectionModel(cellSelectionFinalModel);


        ListSelectionModel cellSelectionExcelModel = jTableEntriesExcel.getSelectionModel();
        cellSelectionExcelModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        cellSelectionExcelModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                jTextFieldSelectedEntries.setText(String.valueOf(jTableEntriesExcel.getSelectedRowCount()));
            }
        });


        this.jTableEntriesExcel.setSelectionModel(cellSelectionExcelModel);

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenuSelection = new javax.swing.JPopupMenu();
        jMenuItemAdd = new javax.swing.JMenuItem();
        jMenuItemClear = new javax.swing.JMenuItem();
        jPopupMenuFinalList = new javax.swing.JPopupMenu();
        jMenuItemRemove = new javax.swing.JMenuItem();
        jMenuItemClearFinal = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboGermplasmList = new javax.swing.JComboBox();
        jScrollEntriesDb = new javax.swing.JScrollPane();
        jTableEntriesDB = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaPath = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollEntiresExcel = new javax.swing.JScrollPane();
        jTableEntriesExcel = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollFinalList = new javax.swing.JScrollPane();
        jTableFinalList = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldListName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldDescription = new javax.swing.JTextField();
        jButtonSaveList = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        jButtonClear = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButtonAdd = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldListEntries = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldSelectedEntries = new javax.swing.JTextField();
        jButtonNewList = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jButtonUp = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButtonErase = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldTotalEntries = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldSelectedFinal = new javax.swing.JTextField();
        jButtonAddChecks = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemAdd, org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jMenuItemAdd.text")); // NOI18N
        jMenuItemAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAddActionPerformed(evt);
            }
        });
        jPopupMenuSelection.add(jMenuItemAdd);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemClear, org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jMenuItemClear.text")); // NOI18N
        jMenuItemClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClearActionPerformed(evt);
            }
        });
        jPopupMenuSelection.add(jMenuItemClear);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemRemove, org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jMenuItemRemove.text")); // NOI18N
        jMenuItemRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRemoveActionPerformed(evt);
            }
        });
        jPopupMenuFinalList.add(jMenuItemRemove);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemClearFinal, org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jMenuItemClearFinal.text")); // NOI18N
        jMenuItemClearFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClearFinalActionPerformed(evt);
            }
        });
        jPopupMenuFinalList.add(jMenuItemClearFinal);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jTabbedPane1.border.title"))); // NOI18N
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/database.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jLabel1.text")); // NOI18N

        cboGermplasmList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT ONE..." }));
        cboGermplasmList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboGermplasmListItemStateChanged(evt);
            }
        });

        jScrollEntriesDb.setToolTipText(org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jScrollEntriesDb.toolTipText")); // NOI18N

        jTableEntriesDB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableEntriesDB.setComponentPopupMenu(jPopupMenuSelection);
        jTableEntriesDB.setDragEnabled(true);
        jTableEntriesDB.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableEntriesDB.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableEntriesDBPropertyChange(evt);
            }
        });
        jScrollEntriesDb.setViewportView(jTableEntriesDB);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollEntriesDb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboGermplasmList, 0, 314, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(cboGermplasmList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollEntriesDb, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/excelFile.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jLabel2.text")); // NOI18N

        jTextAreaPath.setColumns(20);
        jTextAreaPath.setLineWrap(true);
        jTextAreaPath.setRows(5);
        jTextAreaPath.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextAreaPathMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextAreaPath);

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jButton1.text")); // NOI18N
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
        jTableEntriesExcel.setComponentPopupMenu(jPopupMenuSelection);
        jTableEntriesExcel.setDragEnabled(true);
        jTableEntriesExcel.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollEntiresExcel.setViewportView(jTableEntriesExcel);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollEntiresExcel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollEntiresExcel, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jPanel4.border.title"))); // NOI18N

        jScrollFinalList.setToolTipText(org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jScrollFinalList.toolTipText")); // NOI18N

        jTableFinalList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableFinalList.setComponentPopupMenu(jPopupMenuFinalList);
        jTableFinalList.setDragEnabled(true);
        jTableFinalList.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableFinalList.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableFinalListPropertyChange(evt);
            }
        });
        jScrollFinalList.setViewportView(jTableFinalList);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jLabel3.text")); // NOI18N

        jTextFieldListName.setText(org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jTextFieldListName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jLabel4.text")); // NOI18N

        jTextFieldDescription.setText(org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jTextFieldDescription.text")); // NOI18N

        jButtonSaveList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/save.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSaveList, org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jButtonSaveList.text")); // NOI18N
        jButtonSaveList.setToolTipText(org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jButtonSaveList.toolTipText")); // NOI18N
        jButtonSaveList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollFinalList, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldListName, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(jTextFieldDescription))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonSaveList, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldListName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButtonSaveList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollFinalList, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonClear, org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jButtonClear.text")); // NOI18N
        jButtonClear.setToolTipText(org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jButtonClear.toolTipText")); // NOI18N
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });
        jToolBar2.add(jButtonClear);
        jToolBar2.add(jSeparator1);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonAdd, org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jButtonAdd.text")); // NOI18N
        jButtonAdd.setToolTipText(org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jButtonAdd.toolTipText")); // NOI18N
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        jToolBar2.add(jButtonAdd);
        jToolBar2.add(jSeparator4);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jLabel8.text")); // NOI18N
        jToolBar2.add(jLabel8);

        jTextFieldListEntries.setEditable(false);
        jTextFieldListEntries.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldListEntries.setText(org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jTextFieldListEntries.text")); // NOI18N
        jToolBar2.add(jTextFieldListEntries);
        jToolBar2.add(jSeparator5);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jLabel9.text")); // NOI18N
        jToolBar2.add(jLabel9);

        jTextFieldSelectedEntries.setEditable(false);
        jTextFieldSelectedEntries.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldSelectedEntries.setText(org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jTextFieldSelectedEntries.text")); // NOI18N
        jToolBar2.add(jTextFieldSelectedEntries);

        jButtonNewList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/add.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonNewList, org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jButtonNewList.text")); // NOI18N
        jButtonNewList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewListActionPerformed(evt);
            }
        });

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonUp, org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jButtonUp.text")); // NOI18N
        jButtonUp.setToolTipText(org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jButtonUp.toolTipText")); // NOI18N
        jButtonUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonUp);
        jToolBar1.add(jSeparator2);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonErase, org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jButtonErase.text")); // NOI18N
        jButtonErase.setToolTipText(org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jButtonErase.toolTipText")); // NOI18N
        jButtonErase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEraseActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonErase);
        jToolBar1.add(jSeparator7);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jLabel7.text")); // NOI18N
        jToolBar1.add(jLabel7);

        jTextFieldTotalEntries.setEditable(false);
        jTextFieldTotalEntries.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldTotalEntries.setText(org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jTextFieldTotalEntries.text")); // NOI18N
        jToolBar1.add(jTextFieldTotalEntries);
        jToolBar1.add(jSeparator6);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jLabel11.text")); // NOI18N
        jToolBar1.add(jLabel11);

        jTextFieldSelectedFinal.setEditable(false);
        jTextFieldSelectedFinal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldSelectedFinal.setText(org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jTextFieldSelectedFinal.text")); // NOI18N
        jToolBar1.add(jTextFieldSelectedFinal);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonAddChecks, org.openide.util.NbBundle.getMessage(germplasmCreateListTopComponent.class, "germplasmCreateListTopComponent.jButtonAddChecks.text")); // NOI18N
        jButtonAddChecks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddChecksActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonNewList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonAddChecks, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNewList, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAddChecks))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboGermplasmListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboGermplasmListItemStateChanged

        readGermplsmEntriesFromDb(this.jTableEntriesDB);     }//GEN-LAST:event_cboGermplasmListItemStateChanged

    private void jTextAreaPathMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaPathMousePressed

        if (!this.jTextAreaPath.isEnabled()) {
            return;
        }

        openFile();
        if (this.jTextAreaPath.getText().isEmpty() == false) {
            readExcelGermplsmEntries(this.jTextAreaPath.getText(), this.jTableEntriesExcel);
        }
    }//GEN-LAST:event_jTextAreaPathMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        openFile();
        if (this.jTextAreaPath.getText().isEmpty() == false) {
            readExcelGermplsmEntries(this.jTextAreaPath.getText(), this.jTableEntriesExcel);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged

        switch (jTabbedPane1.getSelectedIndex()) {             case 0:                 this.jTextFieldListEntries.setText(String.valueOf(this.jTableEntriesDB.getRowCount()));                 this.jTextFieldSelectedEntries.setText(String.valueOf(this.jTableEntriesDB.getSelectedRowCount()));                 break;             case 1:                 this.jTextFieldListEntries.setText(String.valueOf(this.jTableEntriesExcel.getRowCount()));                 this.jTextFieldSelectedEntries.setText(String.valueOf(this.jTableEntriesExcel.getSelectedRowCount()));                 break;                default:                 throw new AssertionError();         }     }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jTableFinalListPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableFinalListPropertyChange
        this.jTextFieldTotalEntries.setText(String.valueOf(this.jTableFinalList.getRowCount()));
        this.jTextFieldSelectedFinal.setText(String.valueOf(this.jTableFinalList.getSelectedRowCount()));
    }//GEN-LAST:event_jTableFinalListPropertyChange

    private void jButtonUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpActionPerformed

        jTableFinalList.getSelectionModel().clearSelection();     }//GEN-LAST:event_jButtonUpActionPerformed

    private void jButtonEraseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEraseActionPerformed
        if (jTextFieldTotalEntries.getText().equals("0")) {
            return;
        }

        toRemove = new ArrayList<List<Object>>();
        int[] seleccionados = this.jTableFinalList.getSelectedRows();
        for (int i = 0; i < seleccionados.length; i++) {

            toRemove.add(toAdd.get(seleccionados[i]));

        }

        toAdd.removeAll(toRemove);
        jTableFinalList.clearSelection();
        jTableFinalList.updateUI();
        this.jButtonSaveList.setEnabled(true);
    }//GEN-LAST:event_jButtonEraseActionPerformed

    private void jButtonSaveListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveListActionPerformed
        if (this.jTextFieldListName.getText().isEmpty()) {
            NotifyDescriptor d = new NotifyDescriptor.Message("You need to set a List name", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            jTextFieldListName.requestFocusInWindow();
            return;
        }

        if (this.jTextFieldDescription.getText().isEmpty()) {
            NotifyDescriptor d = new NotifyDescriptor.Message("You need to set a description", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            jTextFieldDescription.requestFocusInWindow();
            return;
        }


        if (this.jTableFinalList.getRowCount() == 0) {
            NotifyDescriptor d = new NotifyDescriptor.Message("Your list is empty", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            return;
        }
       // NotifyDescriptor d = new NotifyDescriptor.Confirmation("Do you want to save the germplasm list?", "Save final list",
         //       NotifyDescriptor.OK_CANCEL_OPTION);
       // if (DialogDisplayer.getDefault().notify(d) == NotifyDescriptor.OK_OPTION) {
             
            saveList();
            NotifyDescriptor d2 = new NotifyDescriptor.Message("Your list was saved!", NotifyDescriptor.INFORMATION_MESSAGE);
            DialogDisplayer.getDefault().notify(d2);
            this.jButtonNewList.setText("Create new list");
            this.jButtonNewList.setIcon(new ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/add.png")));
            this.cboGermplasmList.setEnabled(false);
            this.jTextAreaPath.setEnabled(false);
            this.jButton1.setEnabled(false);
            this.jButtonClear.setEnabled(false);
            this.jButtonAdd.setEnabled(false);
            this.jButtonUp.setEnabled(false);
            this.jButtonErase.setEnabled(false);
            this.jButtonSaveList.setEnabled(false);
            this.jTableEntriesDB.setModel(new DefaultTableModel());
            this.jTableEntriesExcel.setModel(new DefaultTableModel());
            this.jTableFinalList.setModel(new DefaultTableModel());
            this.jTextFieldListName.setText("");
            this.jTextFieldDescription.setText("");
       
      //  }

    }//GEN-LAST:event_jButtonSaveListActionPerformed

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        switch (this.jTabbedPane1.getSelectedIndex()) {
            case 0:
                jTableEntriesDB.getSelectionModel().clearSelection();
                break;
            case 1:
                jTableEntriesExcel.getSelectionModel().clearSelection();
                break;
            default:
                throw new AssertionError();
        }

    }//GEN-LAST:event_jButtonClearActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        if (jTextFieldListEntries.getText().equals("0")) {
            return;
        }
        toRemove = new ArrayList<List<Object>>();
        switch (this.jTabbedPane1.getSelectedIndex()) {
            case 0:
                int[] seleccionados = this.jTableEntriesDB.getSelectedRows();
                for (int i = 0; i < seleccionados.length; i++) {
                    toAdd.add(rowListDB.get(seleccionados[i]));
                    toRemove.add(rowListDB.get(seleccionados[i]));

                }
                rowListDB.removeAll(toRemove);
                jTableEntriesDB.getSelectionModel().clearSelection();
                jTableEntriesDB.updateUI();
                break;

            case 1:
                seleccionados = this.jTableEntriesExcel.getSelectedRows();
                for (int i = 0; i < seleccionados.length; i++) {
                    toAdd.add(rowListExcel.get(seleccionados[i]));
                    toRemove.add(rowListExcel.get(seleccionados[i]));

                }
                rowListExcel.removeAll(toRemove);
                jTableEntriesExcel.getSelectionModel().clearSelection();
                jTableEntriesExcel.updateUI();
                break;
            default:
                throw new AssertionError();
        }

        GermplasmEntriesTableModel tableModel = new GermplasmEntriesTableModel(factores, toAdd);
        this.jTableFinalList.setModel(tableModel);
        this.jButtonSaveList.setEnabled(true);


    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jMenuItemAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAddActionPerformed

        if (jTextFieldListEntries.getText().equals("0")) {
            return;
        }
        toRemove = new ArrayList<List<Object>>();
        switch (this.jTabbedPane1.getSelectedIndex()) {
            case 0:
                int[] seleccionados = this.jTableEntriesDB.getSelectedRows();
                for (int i = 0; i < seleccionados.length; i++) {
                    toAdd.add(rowListDB.get(seleccionados[i]));
                    toRemove.add(rowListDB.get(seleccionados[i]));
                }
                rowListDB.removeAll(toRemove);
                jTableEntriesDB.getSelectionModel().clearSelection();
                jTableEntriesDB.updateUI();
                break;
            case 1:
                seleccionados = this.jTableEntriesExcel.getSelectedRows();
                for (int i = 0; i < seleccionados.length; i++) {
                    toAdd.add(rowListExcel.get(seleccionados[i]));
                    toRemove.add(rowListExcel.get(seleccionados[i]));
                }
                rowListExcel.removeAll(toRemove);
                jTableEntriesExcel.getSelectionModel().clearSelection();
                jTableEntriesExcel.updateUI();
                break;
            default:
                throw new AssertionError();
        }
        GermplasmEntriesTableModel tableModel = new GermplasmEntriesTableModel(factores, toAdd);
        this.jTableFinalList.setModel(tableModel);
        this.jButtonSaveList.setEnabled(true);
    }//GEN-LAST:event_jMenuItemAddActionPerformed

    private void jMenuItemClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClearActionPerformed

        switch (this.jTabbedPane1.getSelectedIndex()) {
            case 0:
                jTableEntriesDB.getSelectionModel().clearSelection();
                break;
            case 1:
                jTableEntriesExcel.getSelectionModel().clearSelection();
                break;
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_jMenuItemClearActionPerformed

    private void jMenuItemClearFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClearFinalActionPerformed

        jTableFinalList.getSelectionModel().clearSelection();
    }//GEN-LAST:event_jMenuItemClearFinalActionPerformed

    private void jMenuItemRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRemoveActionPerformed
        if (jTextFieldTotalEntries.getText().equals("0")) {
            return;
        }

        toRemove = new ArrayList<List<Object>>();
        int[] seleccionados = this.jTableFinalList.getSelectedRows();
        for (int i = 0; i < seleccionados.length; i++) {

            toRemove.add(toAdd.get(seleccionados[i]));

        }

        toAdd.removeAll(toRemove);
        jTableFinalList.clearSelection();
        jTableFinalList.updateUI();
        this.jButtonSaveList.setEnabled(true);
    }//GEN-LAST:event_jMenuItemRemoveActionPerformed

    private void jButtonNewListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewListActionPerformed

        if (this.jButtonNewList.getText().equals("Create new list")) {
            this.jButtonNewList.setText("CANCEL");
            this.jButtonNewList.setIcon(new ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/cancel.png")));
            this.cboGermplasmList.setEnabled(true);
            this.jTextAreaPath.setEnabled(true);
            this.jButton1.setEnabled(true);
            this.jButtonClear.setEnabled(true);
            this.jButtonAdd.setEnabled(true);
            this.jButtonUp.setEnabled(true);
            this.jButtonErase.setEnabled(true);
            this.jButtonSaveList.setEnabled(true);
        } else {
            this.jButtonNewList.setText("Create new list");
            this.jButtonNewList.setIcon(new ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/add.png")));
            this.cboGermplasmList.setEnabled(false);
            this.jTextAreaPath.setEnabled(false);
            this.jButton1.setEnabled(false);
            this.jButtonClear.setEnabled(false);
            this.jButtonAdd.setEnabled(false);
            this.jButtonUp.setEnabled(false);
            this.jButtonErase.setEnabled(false);
            this.jButtonSaveList.setEnabled(false);
            this.jTableEntriesDB.setModel(new DefaultTableModel());
            this.jTableEntriesExcel.setModel(new DefaultTableModel());
            this.jTableFinalList.setModel(new DefaultTableModel());
            this.jTextFieldListName.setText("");
            this.jTextFieldDescription.setText("");
        }
    }//GEN-LAST:event_jButtonNewListActionPerformed

    private void jTableEntriesDBPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableEntriesDBPropertyChange
        switch (jTabbedPane1.getSelectedIndex()) {
            case 0:
                this.jTextFieldListEntries.setText(String.valueOf(this.jTableEntriesDB.getRowCount()));
                this.jTextFieldSelectedEntries.setText(String.valueOf(this.jTableEntriesDB.getSelectedRowCount()));
                break;
            case 1:
                this.jTextFieldListEntries.setText(String.valueOf(this.jTableEntriesExcel.getRowCount()));
                this.jTextFieldSelectedEntries.setText(String.valueOf(this.jTableEntriesExcel.getSelectedRowCount()));
                break;
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_jTableEntriesDBPropertyChange

    private void jButtonAddChecksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddChecksActionPerformed
        hideNewListWindow();
        OpenChecksWindow();
    }//GEN-LAST:event_jButtonAddChecksActionPerformed

    private void hideNewListWindow() {
        this.close();
    }

    private void OpenChecksWindow() {
        TopComponent checks = WindowManager.getDefault().findTopComponent("addChecksTopComponent");
        checks.open();
        checks.requestFocusInWindow();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboGermplasmList;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonAddChecks;
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonErase;
    private javax.swing.JButton jButtonNewList;
    private javax.swing.JButton jButtonSaveList;
    private javax.swing.JButton jButtonUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItemAdd;
    private javax.swing.JMenuItem jMenuItemClear;
    private javax.swing.JMenuItem jMenuItemClearFinal;
    private javax.swing.JMenuItem jMenuItemRemove;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenuFinalList;
    private javax.swing.JPopupMenu jPopupMenuSelection;
    private javax.swing.JScrollPane jScrollEntiresExcel;
    private javax.swing.JScrollPane jScrollEntriesDb;
    private javax.swing.JScrollPane jScrollFinalList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableEntriesDB;
    private javax.swing.JTable jTableEntriesExcel;
    private javax.swing.JTable jTableFinalList;
    private javax.swing.JTextArea jTextAreaPath;
    private javax.swing.JTextField jTextFieldDescription;
    private javax.swing.JTextField jTextFieldListEntries;
    private javax.swing.JTextField jTextFieldListName;
    private javax.swing.JTextField jTextFieldSelectedEntries;
    private javax.swing.JTextField jTextFieldSelectedFinal;
    private javax.swing.JTextField jTextFieldTotalEntries;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        this.jButtonNewList.setText("Create new list");
        this.jButtonNewList.setIcon(new ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/add.png")));


        this.cboGermplasmList.setEnabled(false);
        this.jTextAreaPath.setEnabled(false);
        this.jButton1.setEnabled(false);
        this.jButtonClear.setEnabled(false);
        this.jButtonAdd.setEnabled(false);
        this.jButtonUp.setEnabled(false);
        this.jButtonErase.setEnabled(false);
        this.jButtonSaveList.setEnabled(false);
        this.jTextFieldListName.setText("");
        this.jTextFieldDescription.setText("");


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

    private void readGermplsmEntriesFromDb(JTable tabla) {

        rowListDB = new ArrayList<List<Object>>();


        GermplasmListReader germplasmListReader = new GermplasmListReaderImpl();
        boolean validSelection = cboGermplasmList.getSelectedIndex() > 0;

        if (validSelection) {
            try {
                Listnms selectedList = (Listnms) cboGermplasmList.getSelectedItem();
                GermplasmList germplasmList = germplasmListReader.getGermPlasmListFromDB(selectedList.getListid());
                setGermplasmListIntoTable(germplasmList, tabla, 0);
            } catch (Exception ex) {
                System.out.println("ERROR AL LEER EXCEL GERMPLASM ENTRIES: " + ex);
            }
        } else {
            GermplasmEntriesTableModel modeloTablaEntries = new GermplasmEntriesTableModel();
            this.jTableEntriesDB.setModel(modeloTablaEntries);
            this.jTextAreaPath.setText("");
            this.jTextFieldListEntries.setText("0");

        }

        germplasmTransferHandler.setSourceList(rowListDB);
        germplasmTransferHandler.setSourceTable(jTableEntriesDB);
        germplasmTransferHandler.setFactores(factores);
    }

    private void openFile() {
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

        this.jTextAreaPath.setText(selectorArchivo.getSelectedFile().toString());

    }

    @SuppressWarnings("unchecked")
    private void setGermplasmListIntoTable(GermplasmList germplasmList, JTable tabla, int opcion) {
        List<String> columnList = gat.getColumnList(factores);
        GermplasmEntriesTableModel tableModel = null;

        this.jTextFieldListEntries.setText(String.valueOf(germplasmList.getListEntries().size()));


        switch (opcion) {
            case 0:
                rowListDB = gat.getMappedColumns(columnList, germplasmList);
                tableModel = new GermplasmEntriesTableModel(factores, rowListDB);
                break;
            case 1:
                rowListExcel = gat.getMappedColumns(columnList, germplasmList);
                tableModel = new GermplasmEntriesTableModel(factores, rowListExcel);
                break;

            case 2:
                break;

        }


        tabla.setModel(tableModel);



        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int col = 0; col < tabla.getColumnCount(); col++) {
            tabla.getColumnModel().getColumn(col).setCellRenderer(tcr);
        }
    }

    @SuppressWarnings("unchecked")
    public boolean existenFactores(final Workbook workbook) {
        boolean existenFactores = false;

        java.util.ArrayList<Factor> factores = new ArrayList();
        try {
            factores = (java.util.ArrayList<Factor>) workbook.getOtherFactors();
        } catch (NullPointerException ex) {
            return false;
        }
        if (factores == null) {
            return false;
        }
        if (factores.isEmpty()) {
            return false;
        }

        if (factores.size() > 0) {
            existenFactores = true;
        }

        return existenFactores;
    }

     private int dameFecha() {
              
        int val;
  
        try{
         SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
         Date fechaDate = new Date();
         String fecha = formato.format(fechaDate);
         
         val=((Integer.parseInt(fecha.substring(0, 4)))*10000)+((Integer.parseInt(fecha.substring(4, 6)))*100)+
                 ((Integer.parseInt(fecha.substring(6, 8))));       
    
    
     
        }catch(Exception e){
           val=0; 
        }
        return val;
    }
    
    private void saveList() {
        Listnms listnms = new Listnms();
        listnms.setListname(this.jTextFieldListName.getText());

        //NurseryWizardIterator.myList = this.jTextFieldListName.getText();
        //NurseryWizardIterator.isNewList = true;
        int fecha=dameFecha();
        listnms.setListdate(fecha);
       
        listnms.setListtype(Listnms.LIST_TYPE_LIST);
        listnms.setListuid(0);
        listnms.setListdesc(this.jTextFieldDescription.getText());
        listnms.setLhierarchy(0);
        listnms.setListstatus(1);        
        AppServicesProxy.getDefault().appServices().addListnms(listnms);
        List<Listdata> dataList = new ArrayList<Listdata>();

        int gid = findColumn("GID");
        int desig = findColumn("DESIG");
        int entryCD = findColumn("ENTRY");

        for (int i = 0; i < jTableFinalList.getRowCount(); i++) {
            Listdata d1 = new Listdata(true);
            ListdataPK pk1 = new ListdataPK(listnms.getListid(), 0);

            d1.setListdataPK(pk1);   //*
            d1.setEntryid(i + 1);        //*
            if (desig > 0) {
                d1.setDesig(this.jTableFinalList.getValueAt(i, desig).toString());  //*
            } else {
                d1.setDesig("");
            }
            if (entryCD > 0) {
                d1.setEntrycd(this.jTableFinalList.getValueAt(i, entryCD).toString());
            } else {
                d1.setEntrycd("");
            }

      //      d1.setSource("SOURC1");
    //        d1.setGrpname("grp");
           
            d1.setLrstatus(0);      //*
            //d1.setLlrecid(0);

            if (gid > 0) {
                d1.setGid(Integer.parseInt(jTableFinalList.getValueAt(i, gid).toString()));
            } else {
                d1.setGid(0);
            }
            dataList.add(d1);

        }

        /*
         * for (Listdata data : dataList) {
         * AppServicesProxy.getDefault().appServices().addListdata(data); }
         */



        AppServicesProxy.getDefault().appServices().addNewsGermplasm(listnms, dataList, 1);


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

    private void readExcelGermplsmEntries(String myFile, JTable tabla) {

        rowListExcel = new ArrayList<List<Object>>();
        GermplasmListReader germplasmListReader = new GermplasmListReaderImpl();
        boolean validFile = false;
        try {
            validFile = germplasmListReader.isValidTemplate(myFile);
        } catch (Exception ex) {
            System.out.println("ERROR AL VALIDAR ARCHIVO EXCEL");
        }

        if (validFile) {
            try {
                GermplasmList germplasmList = germplasmListReader.getGermPlasmList(myFile);
                setGermplasmListIntoTable(germplasmList, tabla, 1);
            } catch (Exception ex) {
                System.out.println("ERROR AL LEER EXCEL GERMPLASM ENTRIES: " + ex);
            }
        } else {
            GermplasmEntriesTableModel modeloTablaEntries = new GermplasmEntriesTableModel();
            this.jTableEntriesExcel.setModel(modeloTablaEntries);
            this.jTextAreaPath.setText("");
            this.jTextFieldListEntries.setText("0");
            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message("THIS EXCEL FILE IS NOT A VALID ENTRIES FILE", NotifyDescriptor.ERROR_MESSAGE));
        }
        germplasmTransferHandler.setSourceList(rowListExcel);
        germplasmTransferHandler.setSourceTable(jTableEntriesExcel);
        germplasmTransferHandler.setFactores(factores);

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

    private void clearSelectionValues() {
        GermplasmEntriesTableModel modelo = (GermplasmEntriesTableModel) this.jTableEntriesDB.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.setValueAt(0, i, 3);

        }
    }
}
