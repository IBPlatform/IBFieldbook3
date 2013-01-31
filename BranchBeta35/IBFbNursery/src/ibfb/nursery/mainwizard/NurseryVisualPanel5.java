package ibfb.nursery.mainwizard;

import ibfb.domain.core.Factor;
import ibfb.domain.core.GermplasmList;
import ibfb.domain.core.ListOfEntries;
import ibfb.domain.core.Workbook;
import ibfb.nursery.core.NurseryEditorTopComponent;
import ibfb.nursery.filters.ExcelFiltro;
import ibfb.nursery.models.GermplasmEntriesTableModel;
import ibfb.nursery.models.GermplasmTransferHandler;
import ibfb.nursery.models.RemoveGermplasmTransferHandler;
import ibfb.settings.core.FieldbookSettings;
import ibfb.workbook.api.GermplasmAssigmentTool;
import ibfb.workbook.api.GermplasmListReader;
import ibfb.workbook.core.GermplasmAssigmentToolImpl;
import ibfb.workbook.core.GermplasmListReaderImpl;
import java.awt.Desktop;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.domain.Listnms;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.NbBundle;
import ibfb.nursery.utils.SequenceEntry;


public final class NurseryVisualPanel5 extends JPanel {

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
    private int maximo;
    private int initialPosition = 0;
    private int frequency = 0;
    private GermplasmTransferHandler germplasmTransferHandler;
    private RemoveGermplasmTransferHandler removeGermplasmTransferHandler;
    private SpinnerNumberModel modeloSpinnerFreq;
    java.util.ArrayList<Factor> myFactors;
    private ResourceBundle bundle = NbBundle.getBundle(NurseryVisualPanel5.class);
    private int totalEntries=0;
    private int[] posicionesSec;
    private ArrayList<int[]> posicionesSecuencia;
    private ArrayList<SequenceEntry> sequenceList;

   
    public NurseryVisualPanel5() {
        initComponents();
        selectedEntries = new ArrayList<ListOfEntries>();
        rowSelectedList = new ArrayList<List<Object>>();
        toAdd = new ArrayList<List<Object>>();
        modeloSpinnerFreq = (SpinnerNumberModel) this.jSpinnerFrequency.getModel();
        addListener();
        loadSpinnersValues();
        asignaTransfer();

    }

    public ArrayList<SequenceEntry> getSequenceList() {
        return sequenceList;
    }

    public void setSequenceList(ArrayList<SequenceEntry> sequenceList) {
        this.sequenceList = sequenceList;
    }

    
    
    
    public JComboBox getCboGermplasmList() {
        return cboGermplasmList;
    }

    public void setCboGermplasmList(JComboBox cboGermplasmList) {
        this.cboGermplasmList = cboGermplasmList;
    }

    
    
    private void asignaTransfer() {
        jTableEntriesDB.setDragEnabled(true);
        jTableEntriesDB.setDropMode(DropMode.INSERT_ROWS);
        jTableEntriesExcel.setDragEnabled(true);
        jTableEntriesExcel.setDropMode(DropMode.INSERT_ROWS);
        germplasmTransferHandler = new GermplasmTransferHandler(jTableEntriesDB, jTableFinalList, rowListDB, toAdd);
        jTableFinalList.setTransferHandler(germplasmTransferHandler);
        jScrollFinalList.setTransferHandler(germplasmTransferHandler);

        jTableFinalList.setDragEnabled(true);
        jTableFinalList.setDropMode(DropMode.INSERT_ROWS);

        removeGermplasmTransferHandler = new RemoveGermplasmTransferHandler(jTableFinalList, toAdd);

        jTableEntriesDB.setTransferHandler(removeGermplasmTransferHandler);
        jScrollPane3.setTransferHandler(removeGermplasmTransferHandler);
        jTableEntriesExcel.setTransferHandler(removeGermplasmTransferHandler);
        jScrollPane4.setTransferHandler(removeGermplasmTransferHandler);
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
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
    
     
    private void actualizaSpinnerFreq() {
               
        SpinnerNumberModel modeloSpinner = (SpinnerNumberModel) this.jSpinnerFrequency.getModel();
        modeloSpinner.setValue(this.jTableFinalList.getRowCount()+1);
     
    }

    public JTextField getjTextFieldListEntries() {
        return jTextFieldListEntries;
    }

    public void setjTextFieldListEntries(JTextField jTextFieldListEntries) {
        this.jTextFieldListEntries = jTextFieldListEntries;
    }

    @Override
    public String getName() {
        return bundle.getString("NurseryVisualPanel5.name");
    }

    public void fillComboListNames() {
     cboGermplasmList.setModel(new DefaultComboBoxModel(new String[] {NbBundle.getMessage(NurseryVisualPanel5.class,"NurseryVisualPanel5.selectOne") }));

       List<Listnms> germplasmList = AppServicesProxy.getDefault().appServices().getListnmsList();
        for (Listnms list : germplasmList) {
            cboGermplasmList.addItem(list);
        }
    }

    public JTextField getjTextFieldTotalEntries() {
        return jTextFieldTotalEntries;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        jPopupMenuSelection = new javax.swing.JPopupMenu();
        jMenuItemAdd = new javax.swing.JMenuItem();
        jMenuItemClear = new javax.swing.JMenuItem();
        jPopupMenuFinalList = new javax.swing.JPopupMenu();
        jMenuItemClearFinal = new javax.swing.JMenuItem();
        jMenuItemRemove = new javax.swing.JMenuItem();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboGermplasmList = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableEntriesDB = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaPath = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableEntriesExcel = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollFinalList = new javax.swing.JScrollPane();
        jTableFinalList = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jRadioButtonPosition = new javax.swing.JRadioButton();
        jRadioButtonSequence = new javax.swing.JRadioButton();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        jSpinnerPosition = new javax.swing.JSpinner();
        jSpinnerFrequency = new javax.swing.JSpinner();
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

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox1, org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jCheckBox1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jLabel10.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemAdd, org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jMenuItemAdd.text")); // NOI18N
        jMenuItemAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAddActionPerformed(evt);
            }
        });
        jPopupMenuSelection.add(jMenuItemAdd);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemClear, org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jMenuItemClear.text")); // NOI18N
        jMenuItemClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClearActionPerformed(evt);
            }
        });
        jPopupMenuSelection.add(jMenuItemClear);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemClearFinal, org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jMenuItemClearFinal.text")); // NOI18N
        jMenuItemClearFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClearFinalActionPerformed(evt);
            }
        });
        jPopupMenuFinalList.add(jMenuItemClearFinal);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemRemove, org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jMenuItemRemove.text")); // NOI18N
        jMenuItemRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRemoveActionPerformed(evt);
            }
        });
        jPopupMenuFinalList.add(jMenuItemRemove);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jTabbedPane1.border.title"))); // NOI18N
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/database.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jLabel1.text")); // NOI18N

        cboGermplasmList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT ONE..." }));
        cboGermplasmList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboGermplasmListItemStateChanged(evt);
            }
        });

        jScrollPane3.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jScrollPane3.toolTipText")); // NOI18N

        jTableEntriesDB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableEntriesDB.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTableEntriesDB.setComponentPopupMenu(jPopupMenuSelection);
        jTableEntriesDB.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableEntriesDBPropertyChange(evt);
            }
        });
        jScrollPane3.setViewportView(jTableEntriesDB);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboGermplasmList, 0, 241, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cboGermplasmList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/excelFile.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jLabel2.text")); // NOI18N

        jTextAreaPath.setColumns(20);
        jTextAreaPath.setLineWrap(true);
        jTextAreaPath.setRows(5);
        jTextAreaPath.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextAreaPathMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextAreaPath);

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jButton1.text")); // NOI18N
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
        jTableEntriesExcel.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableEntriesExcelPropertyChange(evt);
            }
        });
        jScrollPane4.setViewportView(jTableEntriesExcel);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
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
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jPanel4.border.title"))); // NOI18N

        jScrollFinalList.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jScrollFinalList.toolTipText")); // NOI18N

        jTableFinalList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableFinalList.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTableFinalList.setComponentPopupMenu(jPopupMenuFinalList);
        jTableFinalList.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableFinalListPropertyChange(evt);
            }
        });
        jScrollFinalList.setViewportView(jTableFinalList);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup3.add(jRadioButtonPosition);
        jRadioButtonPosition.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonPosition, org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jRadioButtonPosition.text")); // NOI18N
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

        buttonGroup3.add(jRadioButtonSequence);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonSequence, org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jRadioButtonSequence.text")); // NOI18N
        jRadioButtonSequence.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButtonSequenceStateChanged(evt);
            }
        });
        jRadioButtonSequence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSequenceActionPerformed(evt);
            }
        });

        label1.setText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.label1.text")); // NOI18N

        label2.setText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.label2.text")); // NOI18N

        jSpinnerPosition.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        jSpinnerPosition.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jSpinnerPosition.toolTipText")); // NOI18N
        jSpinnerPosition.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerPositionStateChanged(evt);
            }
        });

        jSpinnerFrequency.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        jSpinnerFrequency.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jSpinnerFrequency.toolTipText")); // NOI18N
        jSpinnerFrequency.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerFrequencyStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jSpinnerPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jSpinnerFrequency, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jRadioButtonPosition)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonSequence)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSpinnerPosition, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSpinnerFrequency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonPosition)
                    .addComponent(jRadioButtonSequence))
                .addGap(35, 35, 35))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jSpinnerPosition, label1});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jSpinnerFrequency, label2});

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollFinalList, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollFinalList, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonClear, org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jButtonClear.text")); // NOI18N
        jButtonClear.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jButtonClear.toolTipText")); // NOI18N
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });
        jToolBar2.add(jButtonClear);
        jToolBar2.add(jSeparator1);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonAdd, org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jButtonAdd.text")); // NOI18N
        jButtonAdd.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jButtonAdd.toolTipText")); // NOI18N
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        jToolBar2.add(jButtonAdd);
        jToolBar2.add(jSeparator4);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jLabel8.text")); // NOI18N
        jToolBar2.add(jLabel8);

        jTextFieldListEntries.setEditable(false);
        jTextFieldListEntries.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldListEntries.setText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jTextFieldListEntries.text")); // NOI18N
        jToolBar2.add(jTextFieldListEntries);
        jToolBar2.add(jSeparator5);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jLabel9.text")); // NOI18N
        jToolBar2.add(jLabel9);

        jTextFieldSelectedEntries.setEditable(false);
        jTextFieldSelectedEntries.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldSelectedEntries.setText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jTextFieldSelectedEntries.text")); // NOI18N
        jToolBar2.add(jTextFieldSelectedEntries);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonUp, org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jButtonUp.text")); // NOI18N
        jButtonUp.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jButtonUp.toolTipText")); // NOI18N
        jButtonUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonUp);
        jToolBar1.add(jSeparator2);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonErase, org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jButtonErase.text")); // NOI18N
        jButtonErase.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jButtonErase.toolTipText")); // NOI18N
        jButtonErase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEraseActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonErase);
        jToolBar1.add(jSeparator7);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jLabel7.text")); // NOI18N
        jToolBar1.add(jLabel7);

        jTextFieldTotalEntries.setEditable(false);
        jTextFieldTotalEntries.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldTotalEntries.setText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jTextFieldTotalEntries.text")); // NOI18N
        jToolBar1.add(jTextFieldTotalEntries);
        jToolBar1.add(jSeparator6);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jLabel11.text")); // NOI18N
        jToolBar1.add(jLabel11);

        jTextFieldSelectedFinal.setEditable(false);
        jTextFieldSelectedFinal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldSelectedFinal.setText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel5.class, "NurseryVisualPanel5.jTextFieldSelectedFinal.text")); // NOI18N
        jToolBar1.add(jTextFieldSelectedFinal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextAreaPathMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaPathMousePressed
        openFile();
        if (this.jTextAreaPath.getText().isEmpty() == false) {
            readExcelGermplsmEntries(this.jTextAreaPath.getText(), this.jTableEntriesExcel);
        }
    }//GEN-LAST:event_jTextAreaPathMousePressed

    private void cboGermplasmListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboGermplasmListItemStateChanged
        readGermplsmEntriesFromDb(this.jTableEntriesDB);
    }//GEN-LAST:event_cboGermplasmListItemStateChanged

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

    }//GEN-LAST:event_jButtonEraseActionPerformed

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
                    rowListDB.get(seleccionados[i]).add(1);
                    rowListDB.get(seleccionados[i]).add(1);
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
                    rowListExcel.get(seleccionados[i]).add(1);
                    rowListExcel.get(seleccionados[i]).add(1);
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



        List<Factor> factorHeaders = this.myWorkbook.getEntryFactors();
        GermplasmEntriesTableModel tableModel = new GermplasmEntriesTableModel(factorHeaders, toAdd);
        tableModel.setHasChecks(true);
        this.jTableFinalList.setModel(tableModel);
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
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
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jTableFinalListPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableFinalListPropertyChange


        try {

            if (evt.getNewValue() != null) {
                this.jTextFieldTotalEntries.setText(String.valueOf(this.jTableFinalList.getRowCount()));
                this.jTextFieldSelectedFinal.setText(String.valueOf(this.jTableFinalList.getSelectedRowCount()));
                //actualizaSpinnerFreq();
                actualizaSpinner();
                actualizaIndicesPosicion();
                actualizaIndicesFrecuencia();
                
            }


        } catch (Exception error) {
            error.printStackTrace();
        }




    }//GEN-LAST:event_jTableFinalListPropertyChange

    private void actualizaSpinner() {

        SpinnerNumberModel modeloSpinner = (SpinnerNumberModel) this.jSpinnerPosition.getModel();
        modeloSpinner.setMaximum(maximo + 1);
    }

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

    private void jMenuItemAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAddActionPerformed
        if (jTextFieldListEntries.getText().equals("0")) {
            return;
        }

        toRemove = new ArrayList<List<Object>>();
        this.jSpinnerPosition.getValue();


        switch (this.jTabbedPane1.getSelectedIndex()) {
            case 0:
                int[] seleccionados = this.jTableEntriesDB.getSelectedRows();
                for (int i = 0; i < seleccionados.length; i++) {
                    rowListDB.get(seleccionados[i]).add(1);
                    rowListDB.get(seleccionados[i]).add(1);
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
                    rowListExcel.get(seleccionados[i]).add(1);
                    rowListExcel.get(seleccionados[i]).add(1);
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



        GermplasmEntriesTableModel tableModel = new GermplasmEntriesTableModel(this.myWorkbook.getEntryFactors(), toAdd);

        tableModel.setHasChecks(true);
        this.jTableFinalList.setModel(tableModel);
    }//GEN-LAST:event_jMenuItemAddActionPerformed

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
    }//GEN-LAST:event_jMenuItemRemoveActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        openFile();
        if (this.jTextAreaPath.getText().isEmpty() == false) {
            readExcelGermplsmEntries(this.jTextAreaPath.getText(), this.jTableEntriesExcel);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void actualizaIndicesPosicion() {
        if (this.jTableFinalList.getRowCount() == 0) {
            return;
        }

        GermplasmEntriesTableModel tableModel = (GermplasmEntriesTableModel) this.jTableFinalList.getModel();
        List<List<Object>> germplasmData = tableModel.getGermplasmData();

        int colPos = tableModel.findColumn("Initial position");
        if (colPos < 0) {
            return;
        }

        loadPositionIntoTable(germplasmData, colPos);
    }

    private void actualizaIndicesFrecuencia() {
        if (this.jTableFinalList.getRowCount() == 0) {
            return;
        }

        GermplasmEntriesTableModel tableModel = (GermplasmEntriesTableModel) this.jTableFinalList.getModel();
        List<List<Object>> germplasmData = tableModel.getGermplasmData();

        int colFreq = tableModel.findColumn("Frequency");
        if (colFreq < 0) {
            return;
        }

        loadFrequencyIntoTable(germplasmData, colFreq);
    }

    private void jSpinnerPositionStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerPositionStateChanged

        this.initialPosition = (Integer) this.jSpinnerPosition.getValue();
        actualizaIndicesPosicion();
      //  actualizaSpinnerFreq();
        this.jTableFinalList.updateUI();

    }//GEN-LAST:event_jSpinnerPositionStateChanged

    private void jSpinnerFrequencyStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerFrequencyStateChanged

        this.frequency = (Integer) this.jSpinnerFrequency.getValue();
        actualizaIndicesFrecuencia();
        this.jTableFinalList.updateUI();


    }//GEN-LAST:event_jSpinnerFrequencyStateChanged

    private void jRadioButtonPositionStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButtonPositionStateChanged
        if (jRadioButtonPosition.isSelected()) {
            actualizaIndicesPosicion();
            this.jTableFinalList.updateUI();
        }

    }//GEN-LAST:event_jRadioButtonPositionStateChanged

    private void jRadioButtonSequenceStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButtonSequenceStateChanged
        if (jRadioButtonSequence.isSelected()) {
            actualizaIndicesPosicion();
            this.jTableFinalList.updateUI();
        }
    }//GEN-LAST:event_jRadioButtonSequenceStateChanged

    private void jRadioButtonPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonPositionActionPerformed
    }//GEN-LAST:event_jRadioButtonPositionActionPerformed

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

    private void jTableEntriesExcelPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableEntriesExcelPropertyChange
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
    }//GEN-LAST:event_jTableEntriesExcelPropertyChange

    private void jRadioButtonSequenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonSequenceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonSequenceActionPerformed

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
        germplasmTransferHandler.setFactores(this.myWorkbook.getEntryFactors());
    }

    
    public boolean checksInSequence(){
        if(this.jRadioButtonPosition.isSelected()){
            return false;
        }else{
            return true;
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
            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(bundle.getString("NurseryVisualPanel5.invalid"), NotifyDescriptor.ERROR_MESSAGE));
        }

        germplasmTransferHandler.setSourceList(rowListExcel);
        germplasmTransferHandler.setSourceTable(jTableEntriesExcel);
        germplasmTransferHandler.setFactores(this.myWorkbook.getEntryFactors());


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
        List<String> columnList = gat.getColumnList(this.myWorkbook.getFactors());


        GermplasmEntriesTableModel tableModel = null;

        for (String string : columnList) {
            System.out.println(string);
        }



        this.jTextFieldListEntries.setText(String.valueOf(germplasmList.getListEntries().size()));

        List<Factor> factorHeaders = this.myWorkbook.getEntryFactors();

        switch (opcion) {
            case 0:
                rowListDB = gat.getMappedColumns(columnList, germplasmList);
                tableModel = new GermplasmEntriesTableModel(factorHeaders, rowListDB);



                break;
            case 1:
                rowListExcel = gat.getMappedColumns(columnList, germplasmList);
                tableModel = new GermplasmEntriesTableModel(factorHeaders, rowListExcel);
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

    public Workbook getMyWorkbook() {
        return myWorkbook;
    }

    public void setMyWorkbook(Workbook myWorkbook) {
        this.myWorkbook = myWorkbook;
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

    public JTable getjTableFinalList() {
        return jTableFinalList;
    }

    public void setjTableFinalList(JTable jTableFinalList) {
        this.jTableFinalList = jTableFinalList;
    }

    public void addCheckIntoList() {
        if (jTableFinalList.isEditing()) {
            jTableFinalList.getCellEditor().stopCellEditing();
        }
        NurseryEditorTopComponent nurseryWindow = NurseryWizardIterator.nurseryTopComponent;
        nurseryWindow.setTableChechs(this.jTableFinalList);

        if (jRadioButtonSequence.isSelected()) {

            nurseryWindow.setByPosition(true);
        } else {
            nurseryWindow.setByPosition(false);
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

        public ArrayList<SequenceEntry> calculaPosicionesSecuencia() {
        posicionesSecuencia = new ArrayList<int[]>();
        this.totalEntries = maximo;        
        int total=maximo;
        GermplasmEntriesTableModel tableModelChecks = (GermplasmEntriesTableModel) this.jTableFinalList.getModel();
        int colPosition = tableModelChecks.findColumn("Initial position");
        int colFreq = tableModelChecks.findColumn("Frequency");    
        int posicion = Integer.parseInt(tableModelChecks.getValueAt(0, colPosition).toString());
        int freq = Integer.parseInt(tableModelChecks.getValueAt(0, colFreq).toString());;
        sequenceList = new ArrayList<SequenceEntry>();
        int add=0;
        boolean sePuedeAgregar=true;
       
            while (sePuedeAgregar) {

                if (posicion <= total + 1) {
                    total++;
                    SequenceEntry sequenceEntry = new SequenceEntry();
                    sequenceEntry.setPosicion(posicion);
                    sequenceEntry.setEntrada(add);
                    sequenceList.add(sequenceEntry);                    
                    add++;

                    if (add >= tableModelChecks.getRowCount()) {
                        add = 0;
                    }
                }

                if ((total - posicion) >= freq) {
                    posicion = posicion + freq;
                    sePuedeAgregar = true;
                }else{
                     sePuedeAgregar = false;
                }

            }
        
        
        
        return sequenceList;
    }
    
    public ArrayList<SequenceEntry> calculaPosicionesSecuenciaForWheat() {

        posicionesSecuencia = new ArrayList<int[]>();
        int restantes = 0;

        this.totalEntries = maximo;

        GermplasmEntriesTableModel tableModelChecks = (GermplasmEntriesTableModel) this.jTableFinalList.getModel();

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

    
    public ArrayList<SequenceEntry> calculaPosicionesSecuencia2(){
     
        posicionesSecuencia=new ArrayList<int[]>();
        int restantes = 0;

        this.totalEntries = this.getMaximo();


        GermplasmEntriesTableModel tableModelChecks = (GermplasmEntriesTableModel) this.jTableFinalList.getModel();
        int colPosition = tableModelChecks.findColumn("Initial position");

        int colFreq = tableModelChecks.findColumn("Frequency");

        int entradasDepositar=(Integer.parseInt(tableModelChecks.getValueAt(0, colFreq).toString()))-1;
        int agregados=0;
        
        
        for (int i = 0; i < tableModelChecks.getRowCount(); i++) {
           
            int posInitial = Integer.parseInt(tableModelChecks.getValueAt(i, colPosition).toString());
            int freqInitial = Integer.parseInt(tableModelChecks.getValueAt(i, colFreq).toString());
    
            restantes = totalEntries - (posInitial - 1);
            int testigosAgregar = (restantes / freqInitial)+1;
           
            posicionesSec = new int[testigosAgregar];
            
                    for (int j = 0; j < testigosAgregar; j++) {

                        if (j == 0) {
                            posicionesSec[0] = posInitial;
                        } else {
                            posicionesSec[j] = posInitial + entradasDepositar + 1;
                            posInitial = posicionesSec[j];
                        }
                        
                        agregados++;
                    }
                      
            posicionesSecuencia.add(posicionesSec);            
        }
                
        posicionesSecuencia=new ArrayList<int[]>();                
        totalEntries=totalEntries+agregados;
        
            for (int i = 0; i < tableModelChecks.getRowCount(); i++) {
           
            int posInitial = Integer.parseInt(tableModelChecks.getValueAt(i, colPosition).toString());
            int freqInitial = Integer.parseInt(tableModelChecks.getValueAt(i, colFreq).toString());

            
            
            restantes = totalEntries - (posInitial - 1);            
            
            int testigosAgregar = (restantes / freqInitial)+1;
           
            posicionesSec = new int[testigosAgregar];
            
                    for (int j = 0; j < testigosAgregar; j++) {

                        if (j == 0) {
                            posicionesSec[0] = posInitial;
                        } else {
                            posicionesSec[j] = posInitial + entradasDepositar + 1;
                            posInitial = posicionesSec[j];
                        }
                    }
                      
            posicionesSecuencia.add(posicionesSec);
            
        }
        

        sequenceList=new ArrayList<SequenceEntry>();
          
  
        for (int j = 0; j < posicionesSecuencia.size(); j++) {
           
            int[] pos = posicionesSecuencia.get(j);
                                      
            for (int k = 0; k < pos.length; k++) {
                
                SequenceEntry sequenceEntry =new SequenceEntry();
                sequenceEntry.setPosicion( pos[k]);
                sequenceEntry.setEntrada(j);                
                sequenceList.add(sequenceEntry);                
            }
        }

        
//        for (int i = 0; i < sequenceList.size(); i++) {
//             System.out.println("Entrada: "+sequenceList.get(i).getEntrada());  
//             System.out.println("Posicion: "+sequenceList.get(i).getPosicion());
//             System.out.println("");
//            
//        }
        
        return sequenceList;        
    }
    
    
    
    
     public ArrayList<Integer> calculaPosicionesSecuenciaInteger() {
          ArrayList<Integer> posiciones = new ArrayList();
          for (int i = 0; i < sequenceList.size(); i++) {
             posiciones.add(sequenceList.get(i).getPosicion());             
         }
          
          return posiciones;
     }
    
    
    public int getSequenceListSize(){
        return sequenceList.size();
    }
    

    
    public ArrayList<Integer> calculaPosiciones() {
        
      //  calculaPosicionesSecuencia();

        ArrayList<Integer> positionsTable = new ArrayList();

        if (this.jTableFinalList.getRowCount() == 0) {
            return null;
        }

        GermplasmEntriesTableModel tableModelChecks = (GermplasmEntriesTableModel) this.jTableFinalList.getModel();
        int colPosition = tableModelChecks.findColumn("Initial position");

        if (colPosition < 0) {
            return null;
        }

        for (int i = 0; i < tableModelChecks.getRowCount(); i++) {
            int pos = Integer.parseInt(tableModelChecks.getValueAt(i, colPosition).toString());
            positionsTable.add(pos);
        }


        ArrayList<Integer> posiciones = new ArrayList();

        int total = this.jTableFinalList.getRowCount();
        int valor = this.initialPosition;
        int contador = 0;
        int max = maximo;


        while (valor <= max+1) {

            for (int i = 0; i < positionsTable.size(); i++) {
                valor = positionsTable.get(i);
                valor = valor + (frequency * contador);

                if (valor <= max+1) {
                    posiciones.add(valor);
                    max++;

                }

            }
            contador++;
        }

        /*
         * for (int i = 0; i < posiciones.size(); i++) { System.out.println(" "
         * + posiciones.get(i));
         *
         * }
         *
         */

        return posiciones;

    }

    public int getTamPos() {
        return this.jTableFinalList.getRowCount();
    }

    public ArrayList<Integer> calculaPosicionesOLD() {



        if (this.jTableFinalList.getRowCount() == 0) {
            return new ArrayList();
        }

        ArrayList<Integer> posiciones = new ArrayList();

        int total = this.jTableFinalList.getRowCount();
        int valor = this.initialPosition;
        int contador = 0;
        int restantes = maximo;

        while (restantes >= frequency) {
            posiciones.add(valor);

            if (this.jRadioButtonPosition.isSelected()) {
                valor++;


                contador++;

                if (contador == total) {
                    contador = 0;


                    valor = valor + frequency;
                    restantes = restantes - frequency;

                }

            } else {

                valor = (valor + this.frequency) + 1;
                restantes = restantes - frequency;

            }


        }

        for (int i = 0; i < posiciones.size(); i++) {
            System.out.println("  " + posiciones.get(i));

        }

        return posiciones;

    }

    private void recorreIndices(int pos, List<List<Object>> germplasmData, int colEntry) {
        for (int j = pos - 1; j < germplasmData.size(); j++) {
            List<Object> gsm = germplasmData.get(j);
            gsm.set(colEntry, j + 1);

        }
    }

    public boolean validateTable() {

        if (this.jTableFinalList.getRowCount() == 0) {
             
            return true;
        }
             
          if(freqIsInvalid()){ 
      
            return false;
          }


        if (this.jRadioButtonSequence.isSelected()) {
//            GermplasmEntriesTableModel tableModelChecks = (GermplasmEntriesTableModel) this.jTableFinalList.getModel();
//            int colPosition = tableModelChecks.findColumn("Initial position");
//
//            ArrayList<Object> posiciones = new ArrayList();
//
//            for (int i = 0; i < jTableFinalList.getRowCount(); i++) {
//                posiciones.add(jTableFinalList.getValueAt(i, colPosition));
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
            int val=0;
            
            try{
             val = Integer.parseInt(posiciones.get(i).toString());
            }catch(Exception e){
                System.out.println("ERROR posiciones "+e);
              return true;  
            }
            
            
            
            if (val == 0 || val > (maximo + posiciones.size())) {
                System.out.println("HAY FUERA DE RANGO: " + val);
                return true;
            }
        }
        return fueraDeRango;
    }

    private void loadSpinnersValues() {
        this.initialPosition = Integer.parseInt(this.jSpinnerPosition.getValue().toString());
        this.frequency = Integer.parseInt(this.jSpinnerFrequency.getValue().toString());
    }

        private void loadPositionIntoTableForWheat(List<List<Object>> germplasmData, int colPos) {

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
                temp = temp + this.frequency;
            }



        }

    }

    private void loadFrequencyIntoTable(List<List<Object>> germplasmData, int colFreq) {
        for (int j = 0; j < germplasmData.size(); j++) {
            List<Object> gsm = germplasmData.get(j);
            gsm.set(colFreq, this.frequency);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox cboGermplasmList;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonErase;
    private javax.swing.JButton jButtonUp;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItemAdd;
    private javax.swing.JMenuItem jMenuItemClear;
    private javax.swing.JMenuItem jMenuItemClearFinal;
    private javax.swing.JMenuItem jMenuItemRemove;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenuFinalList;
    private javax.swing.JPopupMenu jPopupMenuSelection;
    private javax.swing.JRadioButton jRadioButtonPosition;
    private javax.swing.JRadioButton jRadioButtonSequence;
    private javax.swing.JScrollPane jScrollFinalList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JSpinner jSpinnerFrequency;
    private javax.swing.JSpinner jSpinnerPosition;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableEntriesDB;
    private javax.swing.JTable jTableEntriesExcel;
    private javax.swing.JTable jTableFinalList;
    private javax.swing.JTextArea jTextAreaPath;
    private javax.swing.JTextField jTextFieldListEntries;
    private javax.swing.JTextField jTextFieldSelectedEntries;
    private javax.swing.JTextField jTextFieldSelectedFinal;
    private javax.swing.JTextField jTextFieldTotalEntries;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private java.awt.Label label1;
    private java.awt.Label label2;
    // End of variables declaration//GEN-END:variables

    boolean freqIsInvalid() {
      if(this.frequency<=this.jTableFinalList.getRowCount()){
          return true;
      }else{
         
          return false;
      }
    }

  
 
    
}
