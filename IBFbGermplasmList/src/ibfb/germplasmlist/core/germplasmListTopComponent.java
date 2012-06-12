package ibfb.germplasmlist.core;

import ibfb.domain.core.Factor;
import ibfb.domain.core.GermplasmList;
import ibfb.domain.core.ListOfEntries;
import ibfb.domain.core.Workbook;
import ibfb.germplasmlist.filters.ExcelFiltro;
import ibfb.germplasmlist.models.GermplasmEntriesTableModel;
import ibfb.germplasmlist.models.TableRowTransferHandler;
import ibfb.settings.core.FieldbookSettings;
import ibfb.workbook.api.GermplasmAssigmentTool;
import ibfb.workbook.api.GermplasmListReader;
import ibfb.workbook.core.GermplasmAssigmentToolImpl;
import ibfb.workbook.core.GermplasmListReaderImpl;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DropMode;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.domain.Listdata;
import org.cimmyt.cril.ibwb.domain.ListdataPK;
import org.cimmyt.cril.ibwb.domain.Listnms;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.windows.WindowManager;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//ibfb.germplasmlist.core//germplasmList//EN",
autostore = false)
@TopComponent.Description(preferredID = "germplasmListTopComponent",
iconBase = "ibfb/germplasmlist/images/germplasmIcon16.png",
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
//@ActionID(category = "Window", id = "ibfb.germplasmlist.core.germplasmListTopComponent")
//@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_germplasmListAction",
preferredID = "germplasmListTopComponent")
public final class germplasmListTopComponent extends TopComponent {

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
    TransferHandler handler = new TableRowTransferHandler();
    TransferHandler handler2 = new TableRowTransferHandler();

    

    public germplasmListTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(germplasmListTopComponent.class, "CTL_germplasmListTopComponent"));
        setToolTipText(NbBundle.getMessage(germplasmListTopComponent.class, "HINT_germplasmListTopComponent"));
fillComboListNames();
editTables();
    }

    private void fillComboListNames() {
        List<Listnms> germplasmList = AppServicesProxy.getDefault().appServices().getListnmsList();
        for (Listnms list : germplasmList) {
            cboGermplasmList.addItem(list);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cboGermplasmList = new javax.swing.JComboBox();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableEntriesDB = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaPath = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableEntriesExcel = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableInventory = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTableFinalList = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jButtonUp = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButtonErase = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldTotalEntries = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldSelectedFinal = new javax.swing.JTextField();
        jToolBar2 = new javax.swing.JToolBar();
        jButtonClear = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButtonAdd = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldListEntries = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldSelectedEntries = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldListName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldDescription = new javax.swing.JTextField();
        jButtonSaveList = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jTabbedPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jTabbedPane3.border.title"))); // NOI18N
        jTabbedPane3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane3StateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jLabel6.text")); // NOI18N

        cboGermplasmList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT ONE..." }));
        cboGermplasmList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboGermplasmListItemStateChanged(evt);
            }
        });

        jScrollPane6.setToolTipText(org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jScrollPane6.toolTipText")); // NOI18N

        jTableEntriesDB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableEntriesDB.setDragEnabled(true);
        jTableEntriesDB.setDropMode(javax.swing.DropMode.INSERT_ROWS);
        jScrollPane6.setViewportView(jTableEntriesDB);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboGermplasmList, 0, 369, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cboGermplasmList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab(org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jPanel8.TabConstraints.tabTitle"), jPanel8); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jLabel7.text")); // NOI18N

        jTextAreaPath.setColumns(20);
        jTextAreaPath.setLineWrap(true);
        jTextAreaPath.setRows(5);
        jTextAreaPath.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextAreaPathMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTextAreaPath);

        org.openide.awt.Mnemonics.setLocalizedText(jButton4, org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jButton4.text")); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTableEntriesExcel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane7.setViewportView(jTableEntriesExcel);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab(org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jPanel9.TabConstraints.tabTitle"), jPanel9); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jLabel8.text")); // NOI18N

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Coming soon" }));

        jTableInventory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane8.setViewportView(jTableInventory);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, 0, 369, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab(org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jPanel10.TabConstraints.tabTitle"), jPanel10); // NOI18N

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jPanel11.border.title"))); // NOI18N

        jScrollPane9.setToolTipText(org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jScrollPane9.toolTipText")); // NOI18N

        jTableFinalList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableFinalList.setDragEnabled(true);
        jTableFinalList.setDropMode(javax.swing.DropMode.INSERT_ROWS);
        jTableFinalList.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableFinalListPropertyChange(evt);
            }
        });
        jScrollPane9.setViewportView(jTableFinalList);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonUp, org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jButtonUp.text")); // NOI18N
        jButtonUp.setToolTipText(org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jButtonUp.toolTipText")); // NOI18N
        jButtonUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonUp);
        jToolBar1.add(jSeparator2);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonErase, org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jButtonErase.text")); // NOI18N
        jButtonErase.setToolTipText(org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jButtonErase.toolTipText")); // NOI18N
        jButtonErase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEraseActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonErase);
        jToolBar1.add(jSeparator7);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jLabel9.text")); // NOI18N
        jToolBar1.add(jLabel9);

        jTextFieldTotalEntries.setEditable(false);
        jTextFieldTotalEntries.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldTotalEntries.setText(org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jTextFieldTotalEntries.text")); // NOI18N
        jToolBar1.add(jTextFieldTotalEntries);
        jToolBar1.add(jSeparator6);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jLabel11.text")); // NOI18N
        jToolBar1.add(jLabel11);

        jTextFieldSelectedFinal.setEditable(false);
        jTextFieldSelectedFinal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldSelectedFinal.setText(org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jTextFieldSelectedFinal.text")); // NOI18N
        jToolBar1.add(jTextFieldSelectedFinal);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonClear, org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jButtonClear.text")); // NOI18N
        jButtonClear.setToolTipText(org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jButtonClear.toolTipText")); // NOI18N
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });
        jToolBar2.add(jButtonClear);
        jToolBar2.add(jSeparator1);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonAdd, org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jButtonAdd.text")); // NOI18N
        jButtonAdd.setToolTipText(org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jButtonAdd.toolTipText")); // NOI18N
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        jToolBar2.add(jButtonAdd);
        jToolBar2.add(jSeparator4);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jLabel13.text")); // NOI18N
        jToolBar2.add(jLabel13);

        jTextFieldListEntries.setEditable(false);
        jTextFieldListEntries.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldListEntries.setText(org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jTextFieldListEntries.text")); // NOI18N
        jToolBar2.add(jTextFieldListEntries);
        jToolBar2.add(jSeparator5);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel14, org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jLabel14.text")); // NOI18N
        jToolBar2.add(jLabel14);

        jTextFieldSelectedEntries.setEditable(false);
        jTextFieldSelectedEntries.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldSelectedEntries.setText(org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jTextFieldSelectedEntries.text")); // NOI18N
        jToolBar2.add(jTextFieldSelectedEntries);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTabbedPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 905, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jPanel6.TabConstraints.tabTitle"), jPanel6); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 905, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jPanel7.TabConstraints.tabTitle"), jPanel7); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/newList.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jButton2.text")); // NOI18N
        jButton2.setToolTipText(org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jButton2.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jLabel3.text")); // NOI18N

        jTextFieldListName.setText(org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jTextFieldListName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jLabel4.text")); // NOI18N

        jTextFieldDescription.setText(org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jTextFieldDescription.text")); // NOI18N

        jButtonSaveList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/save.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSaveList, org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jButtonSaveList.text")); // NOI18N
        jButtonSaveList.setToolTipText(org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jButtonSaveList.toolTipText")); // NOI18N
        jButtonSaveList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveListActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/viewList.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButton3, org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jButton3.text")); // NOI18N
        jButton3.setToolTipText(org.openide.util.NbBundle.getMessage(germplasmListTopComponent.class, "germplasmListTopComponent.jButton3.toolTipText")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldListName, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)))
                .addGap(57, 57, 57)
                .addComponent(jButtonSaveList, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(185, 185, 185))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextFieldDescription, jTextFieldListName});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonSaveList, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldListName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jTextFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

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





        NotifyDescriptor d = new NotifyDescriptor.Confirmation("Do you want to save the germplasm list", "Save final list",
                NotifyDescriptor.OK_CANCEL_OPTION);
        if (DialogDisplayer.getDefault().notify(d) == NotifyDescriptor.OK_OPTION) {
            saveList();


            NotifyDescriptor d2 = new NotifyDescriptor.Message("Your list was saved! Click NEXT to continue", NotifyDescriptor.INFORMATION_MESSAGE);
            DialogDisplayer.getDefault().notify(d2);

            this.jButtonSaveList.setEnabled(false);


        }

    }//GEN-LAST:event_jButtonSaveListActionPerformed

    private void cboGermplasmListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboGermplasmListItemStateChanged

        readGermplsmEntriesFromDb(this.jTableEntriesDB);

    }//GEN-LAST:event_cboGermplasmListItemStateChanged

    private void jTextAreaPathMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaPathMousePressed

        openFile();
        if (this.jTextAreaPath.getText().isEmpty() == false) {
            readExcelGermplsmEntries(this.jTextAreaPath.getText(), this.jTableEntriesExcel);
        }
    }//GEN-LAST:event_jTextAreaPathMousePressed

    private void jTabbedPane3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane3StateChanged

        switch (jTabbedPane3.getSelectedIndex()) {
            case 0:
                this.jTextFieldListEntries.setText(String.valueOf(this.jTableEntriesDB.getRowCount()));
                this.jTextFieldSelectedEntries.setText(String.valueOf(this.jTableEntriesDB.getSelectedRowCount()));
                break;
            case 1:
                this.jTextFieldListEntries.setText(String.valueOf(this.jTableEntriesExcel.getRowCount()));
                this.jTextFieldSelectedEntries.setText(String.valueOf(this.jTableEntriesExcel.getSelectedRowCount()));
                break;
            case 2:
                this.jTextFieldListEntries.setText(String.valueOf(this.jTableInventory.getRowCount()));
                this.jTextFieldSelectedEntries.setText(String.valueOf(this.jTableInventory.getSelectedRowCount()));
                break;
            
        }

    }//GEN-LAST:event_jTabbedPane3StateChanged

    private void jTableFinalListPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableFinalListPropertyChange
        this.jTextFieldTotalEntries.setText(String.valueOf(this.jTableFinalList.getRowCount()));
        this.jTextFieldSelectedFinal.setText(String.valueOf(this.jTableFinalList.getSelectedRowCount()));
    }//GEN-LAST:event_jTableFinalListPropertyChange

    private void jButtonUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpActionPerformed

        jTableFinalList.getSelectionModel().clearSelection();

    }//GEN-LAST:event_jButtonUpActionPerformed

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

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed

        switch (this.jTabbedPane2.getSelectedIndex()) {
            case 0:
                jTableEntriesDB.getSelectionModel().clearSelection();
                break;

            case 1:
                jTableEntriesExcel.getSelectionModel().clearSelection();
                break;

            case 3:
                jTableInventory.getSelectionModel().clearSelection();
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



        switch (this.jTabbedPane2.getSelectedIndex()) {
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

            case 3:
                seleccionados = this.jTableInventory.getSelectedRows();
                break;
            default:
                throw new AssertionError();
        }

        Factor factor=new Factor();
        factor.setFactorName("GID");
        
        
ArrayList<Factor> factorHeaders=new ArrayList();
factorHeaders.add(factor);

        GermplasmEntriesTableModel tableModel = new GermplasmEntriesTableModel(this.myWorkbook.getEntryFactors(), toAdd);
        this.jTableFinalList.setModel(tableModel);
        this.jButtonSaveList.setEnabled(true);


    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        TopComponent ListExplorer = WindowManager.getDefault().findTopComponent("ListNamesExplorerTopComponent");
        ListExplorer.open();
        ListExplorer.requestActive();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboGermplasmList;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonErase;
    private javax.swing.JButton jButtonSaveList;
    private javax.swing.JButton jButtonUp;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTableEntriesDB;
    private javax.swing.JTable jTableEntriesExcel;
    private javax.swing.JTable jTableFinalList;
    private javax.swing.JTable jTableInventory;
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

        jButtonSaveList.setEnabled(false);
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    @Override
    public void componentActivated() {
      
    }

    void writeProperties(java.util.Properties p) {

        p.setProperty("version", "1.0");
   
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
     
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
    }

    @SuppressWarnings("unchecked")
    private void setGermplasmListIntoTable(GermplasmList germplasmList, JTable tabla, int opcion) {
        List<String> columnList = gat.getColumnList(this.myWorkbook.getFactors());
        GermplasmEntriesTableModel tableModel = null;

        for (String string : columnList) {
            System.out.println(string);
        }
        java.util.ArrayList<Factor> factors = new ArrayList();
        factors = (java.util.ArrayList<Factor>) this.myWorkbook.getFactors();
        this.jTextFieldListEntries.setText(String.valueOf(germplasmList.getListEntries().size()));
        List<ListOfEntries> myList = germplasmList.getListEntries();



        switch (opcion) {
            case 0:
                rowListDB = gat.getMappedColumns(columnList, germplasmList);
                tableModel = new GermplasmEntriesTableModel(this.myWorkbook.getEntryFactors(), rowListDB);
                break;
            case 1:
                rowListExcel = gat.getMappedColumns(columnList, germplasmList);
                tableModel = new GermplasmEntriesTableModel(this.myWorkbook.getEntryFactors(), rowListExcel);
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

    private void saveList() {
        Listnms listnms = new Listnms();
        listnms.setListname(this.jTextFieldListName.getText());

        listnms.setListdate(20111026);
        listnms.setListtype(Listnms.LIST_TYPE_LIST);
        listnms.setListuid(0);
        listnms.setListdesc(this.jTextFieldDescription.getText());
        listnms.setLhierarchy(0);
        listnms.setListstatus(0);
        AppServicesProxy.getDefault().appServices().addListnms(listnms);
        List<Listdata> dataList = new ArrayList<Listdata>();

        int gid = findColumn("GID");
        int desig = findColumn("DESIG");
        int entryCD = findColumn("ENTRY CD");

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

            d1.setSource("SOURC1");
            d1.setGrpname("grp");
            d1.setLrstatus(0);      //*
            //d1.setLlrecid(0);

            if (gid > 0) {
                d1.setGid(Integer.parseInt(jTableFinalList.getValueAt(i, gid).toString()));
            } else {
                d1.setGid(0);
            }
            dataList.add(d1);

        }

        for (Listdata data : dataList) {
            AppServicesProxy.getDefault().appServices().addListdata(data);
        }

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

    private void editTables() {
       this.jTableFinalList.setTransferHandler(handler); 
       
        this.jTableFinalList.getSelectionModel().setSelectionMode(
            ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.jTableFinalList.setTransferHandler(handler);
        this.jTableFinalList.setDropMode(DropMode.INSERT_ROWS);
        this.jTableFinalList.setDragEnabled(true);
        this.jTableFinalList.setFillsViewportHeight(true);
       
       
        
        
        
        
        
        this.jTableEntriesDB.setTransferHandler(handler2); 
       
        this.jTableEntriesDB.getSelectionModel().setSelectionMode(
            ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.jTableEntriesDB.setTransferHandler(handler);
        this.jTableEntriesDB.setDropMode(DropMode.INSERT_ROWS);
        this.jTableEntriesDB.setDragEnabled(true);
        this.jTableEntriesDB.setFillsViewportHeight(true);
        
        
        	this.jTableEntriesDB.addMouseListener(new MouseAdapter(){

            @Override
            public void mousePressed(MouseEvent e){
                JTable tabla = (JTable)e.getSource();
                TransferHandler handle = tabla.getTransferHandler();

                handle.exportAsDrag(tabla, e, TransferHandler.MOVE);

            }

        });

       
    
    }
}
