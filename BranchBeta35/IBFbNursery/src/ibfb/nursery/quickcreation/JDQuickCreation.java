package ibfb.nursery.quickcreation;

import ibfb.domain.core.Condition;
import ibfb.domain.core.Constant;
import ibfb.domain.core.GermplasmList;
import ibfb.domain.core.SelectedStudy;
import ibfb.domain.core.Variate;
import ibfb.domain.core.Workbook;
import ibfb.lists.core.SelectListDialog;
import ibfb.nursery.core.NurseryEditorTopComponent;
import ibfb.nursery.filters.ExcelFiltro;
import ibfb.nursery.models.GermplasmEntriesTableModel;
import ibfb.nursery.utils.ExcelReaderClass;
import ibfb.settings.core.FieldbookSettings;
import ibfb.workbook.api.GermplasmAssigmentTool;
import ibfb.workbook.api.GermplasmListReader;
import ibfb.workbook.api.WorkbookExcelReader;
import ibfb.workbook.core.GermplasmAssigmentToolImpl;
import ibfb.workbook.core.GermplasmListReaderImpl;
import ibfb.workbook.core.WorkbookExcelReaderImpl;
import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.*;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.OSUtils;
import org.cimmyt.cril.ibwb.domain.Listdata;
import org.cimmyt.cril.ibwb.domain.Listnms;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.netbeans.api.progress.ProgressUtils;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

public class JDQuickCreation extends javax.swing.JDialog {

    private JFileChooser selectorArchivo = new JFileChooser();
    private int instances = 1;
    private ExcelReaderClass myExcelReader = new ExcelReaderClass();
    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
    private int totalEntries = 0;
    private Workbook myWorkbook;
    private Desktop desktop = null;
    private Desktop.Action action = null;
    private File archivo = null;
    private NurseryEditorTopComponent nurseryWindow = null;
    private int rowsTotales = 0;
    private ArrayList<String> otherFactors = new ArrayList<String>();
    private ArrayList<String> children = new ArrayList<String>();
    private boolean existenConstantes = false;
    private boolean existenConditions = false;
    private boolean isForWheat = false;
    private String[] nameColumn = {"Cross Name", "Selection History", "Pedigree", "CID", "SID", "GID", "INTRID", "TID", "ENT", "Folio", "Specific Name", "Name Abbreviation", "Cross Year", "Cross Location", "Cross Country", "Cross Organization", "Cross Program", "FAO In-trust", "Selection Year", "Selection Location", "Selection Country", "Name Country", "Name Year", "FAO designation Date", "24 disp", "25 disp"};
    private ArrayList<String> wheatColumns;
    private ArrayList<String> wheatColumnsforSearch;
    private ProgressHandle handle;
    private String porcentaje;
    private ResourceBundle bundle = NbBundle.getBundle(JDQuickCreation.class);
    private boolean ready = false;

    public JDQuickCreation(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        deshabilitaGSM();
        fillComboListNames();
        checkButtonsStatus();

        if (isForWheat) {
            loadNamesForWheat();
        }

        ready = true;

    }

    private void loadNamesForWheat() {
        wheatColumns = new ArrayList<String>();
        for (int i = 0; i < nameColumn.length; i++) {
            wheatColumns.add(nameColumn[i].toUpperCase());
        }
    }

    public final void fillComboListNames() {
        List<Listnms> germplasmList = AppServicesProxy.getDefault().appServices().getListnmsList();
        cboGermplasmList.removeAllItems();
        cboGermplasmList.addItem(bundle.getString("JDQuickCreation.selectOne"));
        for (Listnms list : germplasmList) {
            cboGermplasmList.addItem(list);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jradExcelFile = new javax.swing.JRadioButton();
        jradTemplateDB = new javax.swing.JRadioButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaPathTemplate = new javax.swing.JTextArea();
        jButtonSearchTemplate = new javax.swing.JButton();
        jButtonPreviewTemplate = new javax.swing.JButton();
        cboTemplateList = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        radGermplasmFromDB1 = new javax.swing.JRadioButton();
        cboGermplasmList = new javax.swing.JComboBox();
        radGermplasmFromTemplate = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaPathGSM = new javax.swing.JTextArea();
        jButtonSearchGSM = new javax.swing.JButton();
        jButtonPreviewGSM = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableEntries = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldTotalEntries = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jRadioButtonNormal = new javax.swing.JRadioButton();
        jRadioButtonRandom = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButtonCancel = new javax.swing.JButton();
        jButtonFinish = new javax.swing.JButton();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(JDQuickCreation.class, "JDQuickCreation.title")); // NOI18N
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(JDQuickCreation.class, "JDQuickCreation.jPanel1.border.title"))); // NOI18N

        jradExcelFile.setSelected(true);
        jradExcelFile.setText(org.openide.util.NbBundle.getMessage(JDQuickCreation.class, "JDQuickCreation.jradExcelFile.text")); // NOI18N
        jradExcelFile.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jradExcelFileStateChanged(evt);
            }
        });
        jradExcelFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradExcelFileActionPerformed(evt);
            }
        });

        buttonGroup1.add(jradTemplateDB);
        jradTemplateDB.setText(org.openide.util.NbBundle.getMessage(JDQuickCreation.class, "JDQuickCreation.jradTemplateDB.text")); // NOI18N
        jradTemplateDB.setEnabled(false);
        jradTemplateDB.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jradTemplateDBStateChanged(evt);
            }
        });
        jradTemplateDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradTemplateDBActionPerformed(evt);
            }
        });

        jTextAreaPathTemplate.setColumns(20);
        jTextAreaPathTemplate.setEditable(false);
        jTextAreaPathTemplate.setLineWrap(true);
        jTextAreaPathTemplate.setRows(5);
        jTextAreaPathTemplate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextAreaPathTemplateMousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(jTextAreaPathTemplate);

        jButtonSearchTemplate.setText(org.openide.util.NbBundle.getMessage(JDQuickCreation.class, "JDQuickCreation.jButtonSearchTemplate.text")); // NOI18N
        jButtonSearchTemplate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchTemplateActionPerformed(evt);
            }
        });

        jButtonPreviewTemplate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/preview.png"))); // NOI18N
        jButtonPreviewTemplate.setText(org.openide.util.NbBundle.getMessage(JDQuickCreation.class, "JDQuickCreation.jButtonPreviewTemplate.text")); // NOI18N
        jButtonPreviewTemplate.setToolTipText(org.openide.util.NbBundle.getMessage(JDQuickCreation.class, "JDQuickCreation.jButtonPreviewTemplate.toolTipText")); // NOI18N
        jButtonPreviewTemplate.setEnabled(false);
        jButtonPreviewTemplate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPreviewTemplateActionPerformed(evt);
            }
        });

        cboTemplateList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT ONE..." }));
        cboTemplateList.setEnabled(false);
        cboTemplateList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTemplateListItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jradExcelFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jradTemplateDB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonSearchTemplate, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPreviewTemplate, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cboTemplateList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jradExcelFile)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSearchTemplate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonPreviewTemplate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTemplateList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jradTemplateDB))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(JDQuickCreation.class, "JDQuickCreation.jPanel2.border.title"))); // NOI18N

        buttonGroup1.add(radGermplasmFromDB1);
        radGermplasmFromDB1.setSelected(true);
        radGermplasmFromDB1.setText(org.openide.util.NbBundle.getMessage(JDQuickCreation.class, "JDQuickCreation.radGermplasmFromDB1.text")); // NOI18N
        radGermplasmFromDB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radGermplasmFromDB1ActionPerformed(evt);
            }
        });

        cboGermplasmList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT ONE..." }));
        cboGermplasmList.setEnabled(false);
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

        buttonGroup1.add(radGermplasmFromTemplate);
        radGermplasmFromTemplate.setText(org.openide.util.NbBundle.getMessage(JDQuickCreation.class, "JDQuickCreation.radGermplasmFromTemplate.text")); // NOI18N
        radGermplasmFromTemplate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radGermplasmFromTemplateActionPerformed(evt);
            }
        });

        jTextAreaPathGSM.setColumns(20);
        jTextAreaPathGSM.setEditable(false);
        jTextAreaPathGSM.setLineWrap(true);
        jTextAreaPathGSM.setRows(5);
        jTextAreaPathGSM.setEnabled(false);
        jTextAreaPathGSM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextAreaPathGSMMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTextAreaPathGSM);

        jButtonSearchGSM.setText(org.openide.util.NbBundle.getMessage(JDQuickCreation.class, "JDQuickCreation.jButtonSearchGSM.text")); // NOI18N
        jButtonSearchGSM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchGSMActionPerformed(evt);
            }
        });

        jButtonPreviewGSM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/preview.png"))); // NOI18N
        jButtonPreviewGSM.setText(org.openide.util.NbBundle.getMessage(JDQuickCreation.class, "JDQuickCreation.jButtonPreviewGSM.text")); // NOI18N
        jButtonPreviewGSM.setToolTipText(org.openide.util.NbBundle.getMessage(JDQuickCreation.class, "JDQuickCreation.jButtonPreviewGSM.toolTipText")); // NOI18N
        jButtonPreviewGSM.setEnabled(false);
        jButtonPreviewGSM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPreviewGSMActionPerformed(evt);
            }
        });

        jTableEntries.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableEntries.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane4.setViewportView(jTableEntries);

        jToolBar1.setRollover(true);

        jLabel1.setText(org.openide.util.NbBundle.getMessage(JDQuickCreation.class, "JDQuickCreation.jLabel1.text")); // NOI18N
        jToolBar1.add(jLabel1);

        jTextFieldTotalEntries.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldTotalEntries.setText(org.openide.util.NbBundle.getMessage(JDQuickCreation.class, "JDQuickCreation.jTextFieldTotalEntries.text")); // NOI18N
        jToolBar1.add(jTextFieldTotalEntries);

        btnSearch.setText(org.openide.util.NbBundle.getMessage(JDQuickCreation.class, "JDQuickCreation.btnSearch.text")); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(radGermplasmFromTemplate, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSearch)
                        .addGap(21, 21, 21))))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(radGermplasmFromDB1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonSearchGSM, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonPreviewGSM, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(cboGermplasmList, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 154, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnSearch)
                .addGap(41, 41, 41)
                .addComponent(radGermplasmFromTemplate)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(cboGermplasmList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addComponent(radGermplasmFromDB1)))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButtonPreviewGSM, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSearchGSM, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE))
                    .addContainerGap(206, Short.MAX_VALUE)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(JDQuickCreation.class, "JDQuickCreation.jPanel3.border.title"))); // NOI18N

        buttonGroup2.add(jRadioButtonNormal);
        jRadioButtonNormal.setSelected(true);
        jRadioButtonNormal.setText(org.openide.util.NbBundle.getMessage(JDQuickCreation.class, "JDQuickCreation.jRadioButtonNormal.text")); // NOI18N

        buttonGroup2.add(jRadioButtonRandom);
        jRadioButtonRandom.setText(org.openide.util.NbBundle.getMessage(JDQuickCreation.class, "JDQuickCreation.jRadioButtonRandom.text")); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/random50.png"))); // NOI18N
        jLabel2.setText(org.openide.util.NbBundle.getMessage(JDQuickCreation.class, "JDQuickCreation.jLabel2.text")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonNormal)
                    .addComponent(jRadioButtonRandom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(31, 31, 31))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jRadioButtonNormal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonRandom)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(JDQuickCreation.class, "JDQuickCreation.jPanel4.border.title"))); // NOI18N

        jButtonCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/cancel.png"))); // NOI18N
        jButtonCancel.setText(org.openide.util.NbBundle.getMessage(JDQuickCreation.class, "JDQuickCreation.jButtonCancel.text")); // NOI18N
        jButtonCancel.setToolTipText(org.openide.util.NbBundle.getMessage(JDQuickCreation.class, "JDQuickCreation.jButtonCancel.toolTipText")); // NOI18N
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jButtonFinish.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/ok.png"))); // NOI18N
        jButtonFinish.setText(org.openide.util.NbBundle.getMessage(JDQuickCreation.class, "JDQuickCreation.jButtonFinish.text")); // NOI18N
        jButtonFinish.setToolTipText(org.openide.util.NbBundle.getMessage(JDQuickCreation.class, "JDQuickCreation.jButtonFinish.toolTipText")); // NOI18N
        jButtonFinish.setEnabled(false);
        jButtonFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinishActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jButtonCancel)
                .addGap(18, 18, 18)
                .addComponent(jButtonFinish)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonFinish))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jradExcelFileStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jradExcelFileStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jradExcelFileStateChanged

    private void jradExcelFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradExcelFileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jradExcelFileActionPerformed

    private void jradTemplateDBStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jradTemplateDBStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jradTemplateDBStateChanged

    private void jradTemplateDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradTemplateDBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jradTemplateDBActionPerformed

    private void jTextAreaPathTemplateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaPathTemplateMousePressed
        openTemplate();
    }//GEN-LAST:event_jTextAreaPathTemplateMousePressed

    private void jButtonSearchTemplateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchTemplateActionPerformed
        openTemplate();
    }//GEN-LAST:event_jButtonSearchTemplateActionPerformed

    private void jButtonPreviewTemplateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPreviewTemplateActionPerformed
        previewFile(this.jTextAreaPathTemplate.getText());
    }//GEN-LAST:event_jButtonPreviewTemplateActionPerformed

    private void previewFile(String path) {
        try {

            if (Desktop.isDesktopSupported() == true) {
                desktop = Desktop.getDesktop();
                archivo = new File(path);
                desktop.open(archivo);
            }
        } catch (IOException e) {
            System.out.println("ERROR IO PROCESA_ARCHIVO");
        } catch (Exception e) {
            System.out.println("ERROR e PROCSA_ARCHIVO");
        }
    }

    private void cboTemplateListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTemplateListItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTemplateListItemStateChanged

    private void radGermplasmFromDB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radGermplasmFromDB1ActionPerformed
        checkButtonsStatus();
    }//GEN-LAST:event_radGermplasmFromDB1ActionPerformed

    private void cboGermplasmListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboGermplasmListItemStateChanged
    }//GEN-LAST:event_cboGermplasmListItemStateChanged

    private void showProgressStatus(final int listID) {

        handle = ProgressHandleFactory.createHandle(bundle.getString("JDQuickCreation.loading"));

        ProgressUtils.showProgressDialogAndRun(new Runnable() {

            @Override
            public void run() {
                porcentaje = "0";
                handle.start(100);
                handle.progress(bundle.getString("JDQuickCreation.completado") + porcentaje + " %");
                completeFullDataFromDatabase(listID);
            }
        }, handle, true);

    }

    private void radGermplasmFromTemplateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radGermplasmFromTemplateActionPerformed
        checkButtonsStatus();
    }//GEN-LAST:event_radGermplasmFromTemplateActionPerformed

    private void jTextAreaPathGSMMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaPathGSMMousePressed
        if (this.jTextAreaPathGSM.isEnabled()) {

            if (radGermplasmFromTemplate.isSelected()) {
                openFileGSM();
                if (this.jTextAreaPathGSM.getText().isEmpty() == false) {
                    readExcelGermplsmEntries(this.jTextAreaPathGSM.getText());
                    this.jButtonPreviewGSM.setEnabled(true);
                }
            }
        }
    }//GEN-LAST:event_jTextAreaPathGSMMousePressed

    private void jButtonSearchGSMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchGSMActionPerformed
        openFileGSM();
        if (this.jTextAreaPathGSM.getText().isEmpty() == false) {
            readExcelGermplsmEntries(this.jTextAreaPathGSM.getText());
            this.jButtonPreviewGSM.setEnabled(true);
        }
    }//GEN-LAST:event_jButtonSearchGSMActionPerformed

    private void jButtonPreviewGSMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPreviewGSMActionPerformed
        previewFile(this.jTextAreaPathGSM.getText());
    }//GEN-LAST:event_jButtonPreviewGSMActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinishActionPerformed

        if (this.jTextAreaPathTemplate.getText().isEmpty() && this.jTextAreaPathGSM.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "YOU NEED TO SELECT VALID FILES", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {

            this.setVisible(false);
            myExcelReader.setFile(this.jTextAreaPathTemplate.getText());

            fillStudyData(nurseryWindow);
            fillStudyConditions(nurseryWindow);
            fillNurseryConditions(nurseryWindow);
            fillGermplsmEntries(nurseryWindow);
            fillConstants(nurseryWindow);
            fillTraits(nurseryWindow);
            fillDesign(nurseryWindow);

            nurseryWindow.setFileTemplate(this.jTextAreaPathTemplate.getText());
            nurseryWindow.jTabbedPane1.setSelectedIndex(6);
            nurseryWindow.fillObservationsData();

            if (existenConstantes) {

                nurseryWindow.jTabbedPane1.setEnabledAt(1, true);

            } else {
                nurseryWindow.jTabbedPane1.setEnabledAt(1, false);
            }


            if (existenConditions) {
                nurseryWindow.jTabbedPane1.setEnabledAt(2, true);
            } else {
                nurseryWindow.jTabbedPane1.setEnabledAt(2, false);
            }

            nurseryWindow.open();
            nurseryWindow.requestActive();
        }
    }//GEN-LAST:event_jButtonFinishActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        searchList();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void cboGermplasmListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGermplasmListActionPerformed
        if (!ready) {
            return;
        }

        boolean validSelection = cboGermplasmList.getSelectedIndex() > 0;
        if (validSelection) {

            Listnms selectedList = (Listnms) cboGermplasmList.getSelectedItem();

            readGermplsmEntriesFromDb();

            if (isForWheat) {
                showProgressStatus(selectedList.getListid());
            }
        }
    }//GEN-LAST:event_cboGermplasmListActionPerformed

    private void fillStudyData(NurseryEditorTopComponent nurseryWindow) {
        nurseryWindow.setName("Nursery - " + SelectedStudy.selected.getStudy());
        nurseryWindow.jTextFieldStudy.setText(SelectedStudy.selected.getStudy());
        nurseryWindow.jTextFieldObjective.setText(SelectedStudy.selected.getObjective());
        nurseryWindow.jTextFieldTitle.setText(SelectedStudy.selected.getTitle());
        Date start = SelectedStudy.selected.getStarDate();
        Date end = SelectedStudy.selected.getEndDate();
        String formato = "dd-MMM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(formato);

        try {
            nurseryWindow.jDateChooserEnd.setDateFormatString("yyyyMMdd");
            nurseryWindow.jDateChooserEnd.setDate(start);
        } catch (NullPointerException ex) {
        }
        try {

            nurseryWindow.jDateChooserEnd.setDateFormatString("yyyyMMdd");
            nurseryWindow.jDateChooserEnd.setDate(end);

        } catch (NullPointerException ex) {
        }
        try {
            nurseryWindow.jTextFieldPMKey.setText(SelectedStudy.selected.getPmkey());
        } catch (NullPointerException ex) {
        }
        try {
            nurseryWindow.jComboBoxStudyType.setSelectedItem(SelectedStudy.selected.getStudyType());
        } catch (NullPointerException ex) {
            nurseryWindow.jComboBoxStudyType.setSelectedItem(0);
        }
    }

    @SuppressWarnings("unchecked")
    private void fillStudyConditions(NurseryEditorTopComponent nurseryWindow) {
        myExcelReader.readExcelWorkbookTemplateStudyConditions();
        Workbook workbook = myExcelReader.getMyWorkbook();
        nurseryWindow.assignStudyConditions(workbook.getStudyConditions());

    }

    @SuppressWarnings("unchecked")
    private void fillNurseryConditions(NurseryEditorTopComponent nurseryWindow) {
        myExcelReader.readExcelWorkbookTemplate();
        Workbook workbook = myExcelReader.getMyWorkbook();

        List<Condition> nurseryConditions = new ArrayList();
        int instance = 1;
        for (int j = 0; j < instances; j++) {
            for (Condition condition : workbook.getConditions()) {

                if (!condition.getConditionName().equals(condition.getLabel())) {
                    Condition condToAdd = new Condition();
                    condToAdd.setConditionName(condition.getConditionName());
                    condToAdd.setDescription(condition.getDescription());
                    condToAdd.setProperty(condition.getProperty());
                    condToAdd.setScale(condition.getScale());
                    condToAdd.setMethod(condition.getMethod());
                    condToAdd.setDataType(condition.getDataType());
                    condToAdd.setLabel(condition.getLabel());
                    condToAdd.setInstance(instance);
                    nurseryConditions.add(condToAdd);
                }

            }
            instance++;
        }
        nurseryWindow.assignNurseryConditions(nurseryConditions);

        if (nurseryConditions.size() > 0) {
            existenConditions = true;
        } else {
            existenConditions = false;
        }
    }

    private void fillGermplsmEntries(NurseryEditorTopComponent nurseryWindow) {
        nurseryWindow.jTextFieldEntries.setText(this.jTextFieldTotalEntries.getText());
        GermplasmEntriesTableModel tableModel = (GermplasmEntriesTableModel) this.jTableEntries.getModel();
        nurseryWindow.assignGermplasmEntries(tableModel.getFactorHeaders(), tableModel.getGermplasmData());
    }

    @SuppressWarnings("unchecked")
    private void fillConstants(NurseryEditorTopComponent nurseryWindow) {
        Workbook workbook = myExcelReader.getMyWorkbook();

        List<Constant> constants = new ArrayList();
        int instance = 1;
        for (int j = 0; j < instances; j++) {
            for (Constant constant : workbook.getConstants()) {
                Constant condToAdd = new Constant();
                condToAdd.setConstantName(constant.getConstantName());
                condToAdd.setDescription(constant.getDescription());
                condToAdd.setProperty(constant.getProperty());
                condToAdd.setScale(constant.getScale());
                condToAdd.setMethod(constant.getMethod());
                condToAdd.setDataType(constant.getDataType());
                condToAdd.setInstance(instance);
                constants.add(condToAdd);
            }
            instance++;
        }
        nurseryWindow.assignExperimentalConditions(constants);

        if (constants.size() > 0) {
            existenConstantes = true;
        } else {
            existenConstantes = false;
        }


    }

    @SuppressWarnings("unchecked")
    private void fillTraits(NurseryEditorTopComponent nurseryWindow) {
        Workbook workbook = myExcelReader.getMyWorkbook();
        nurseryWindow.setMyWorkbook(workbook);
        nurseryWindow.configMyList();
        nurseryWindow.assignTraits(new ArrayList<Variate>(), workbook.getVariates());
        nurseryWindow.setSelectedTraits(workbook.getVariates());
    }

    private void fillDesign(NurseryEditorTopComponent nurseryWindow) {
        if (this.jRadioButtonNormal.isSelected()) {
            nurseryWindow.selectNormalDesign();
        } else {
            nurseryWindow.selectRandomDesign();
        }
    }

    private void openTemplate() {
        this.jButtonPreviewTemplate.setEnabled(false);
        FileFilter[] filtros = new FileFilter[10];
        filtros = selectorArchivo.getChoosableFileFilters();
        String path = "";
        path = FieldbookSettings.getSetting(FieldbookSettings.TEMPLATES_DEFAULT_FOLDER);
     
        
        String customPath="";//NbPreferences.forModule(JDQuickCreation.class).get("customPathNQC", "");               
        File myDesktop=null;
        if(!customPath.isEmpty()){
            myDesktop = new File(customPath);    
        }else{
            myDesktop = new File(FieldbookSettings.getSetting(FieldbookSettings.TEMPLATES_DEFAULT_FOLDER));  
        }     
                        
        
        if (myDesktop.exists()) {
            selectorArchivo.setCurrentDirectory(myDesktop);
        }

        for (int i = 0; i < filtros.length; i++) {
            FileFilter filtro = filtros[i];
            selectorArchivo.removeChoosableFileFilter(filtro);
        }

        selectorArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        selectorArchivo.addChoosableFileFilter(new ExcelFiltro());
        int resultado = selectorArchivo.showOpenDialog(null);

        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }
       
        //NbPreferences.forModule(JDQuickCreation.class).put("customPathNQC", selectorArchivo.getSelectedFile().toString());

        this.jTextAreaPathTemplate.setText(selectorArchivo.getSelectedFile().toString());
        WorkbookExcelReader validateExcelReader = new WorkbookExcelReaderImpl();
        boolean isValidFile = false;

        try {
            isValidFile = validateExcelReader.isValidNurseryTemplate(this.jTextAreaPathTemplate.getText());
        } catch (Exception ex) {
            System.out.println("ERROR AL VALIDAD TEMPLATE DE EXCEL");
        }

        if (isValidFile) {

            this.jTextAreaPathTemplate.setText(selectorArchivo.getSelectedFile().toString());
            this.jButtonPreviewTemplate.setEnabled(true);
            myExcelReader.setFile(this.jTextAreaPathTemplate.getText());
            habilitaGSM();


            checkButtonsStatus();

        } else {
            this.jTextAreaPathTemplate.setText("");
            JOptionPane.showMessageDialog(this, "THIS EXCEL FILE IS NOT A VALID TEMPLATE", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void openFileGSM() {
        this.jButtonFinish.setEnabled(false);
        FileFilter[] filtros = new FileFilter[10];
        filtros = selectorArchivo.getChoosableFileFilters();

        String path = "";
        String osName = System.getProperty("os.name").toLowerCase();
        boolean isMacOs = osName.startsWith("mac os x");
        if (isMacOs) {
            path = File.separator + "Applications" + File.separator + "IBFIELDBOOK" + File.separator + "Examples" + File.separator + "GermplasmLists";
        } else {
            path = "C:" + File.separator + "Program Files" + File.separator + "ibfieldbook" + File.separator + "Examples" + File.separator + "GermplasmLists";
        }

        for (int i = 0; i < filtros.length; i++) {
            FileFilter filtro = filtros[i];
            selectorArchivo.removeChoosableFileFilter(filtro);
        }

        
        
        String customPath="";//NbPreferences.forModule(JDQuickCreation.class).get("customPathNQCGsm", "");               
        File myDesktop=null;
        if(!customPath.isEmpty()){
            myDesktop = new File(customPath);    
        }else{
            myDesktop = new File(FieldbookSettings.getSetting(FieldbookSettings.GERMPLASM_LIST_DEFAULT_FOLDER));  
        }     
        
        
        
        
        
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
            if (this.jTextAreaPathGSM.getText().isEmpty()) {
                this.jButtonFinish.setEnabled(false);
            } else {
                this.jButtonFinish.setEnabled(true);
            }
            return;
        }
        
        //NbPreferences.forModule(JDQuickCreation.class).put("customPathNQCGsm", selectorArchivo.getSelectedFile().toString());

        this.jTextAreaPathGSM.setText(selectorArchivo.getSelectedFile().toString());
        this.jButtonFinish.setEnabled(true);

    }

    private void habilitaGSM() {
        this.radGermplasmFromTemplate.setEnabled(true);
        this.radGermplasmFromDB1.setEnabled(true);
        this.jTextAreaPathGSM.setEnabled(true);
        this.jButtonSearchGSM.setEnabled(true);
    }

    private void checkButtonsStatus() {
        boolean enabled = radGermplasmFromTemplate.isSelected();
        jTextAreaPathGSM.setEnabled(enabled);
        jButtonSearchGSM.setEnabled(enabled);
        cboGermplasmList.setEnabled(!enabled);
        GermplasmEntriesTableModel modeloTablaEntries = new GermplasmEntriesTableModel();
        this.jTableEntries.setModel(modeloTablaEntries);

        if (enabled) {
            cboGermplasmList.setSelectedIndex(0);
            this.jButtonPreviewGSM.setEnabled(false);
        } else {
            cboGermplasmList.setSelectedIndex(0);
            jTextAreaPathGSM.setText("");
            this.jTextFieldTotalEntries.setText("0");
            this.jButtonPreviewGSM.setEnabled(false);
            this.jTableEntries.removeAll();
        }
    }

    private void readExcelGermplsmEntries(String myFile) {

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
                setGermplasmListIntoTable(germplasmList);
            } catch (Exception ex) {
                System.out.println("ERROR AL LEER EXCEL GERMPLASM ENTRIES: " + ex);
            }
        } else {
            DefaultTableModel modeloTabla = new DefaultTableModel();
            modeloTabla = (DefaultTableModel) this.jTableEntries.getModel();
            modeloTabla.setNumRows(0);
            this.jTextAreaPathGSM.setText("");
            this.jTextFieldTotalEntries.setText("0");
            JOptionPane.showMessageDialog(this, "THIS EXCEL FILE IS NOT A VALID ENTRIES FILE", "ERROR", JOptionPane.ERROR_MESSAGE);
            this.jButtonFinish.setEnabled(false);
        }

    }

    @SuppressWarnings("unchecked")
    private void setGermplasmListIntoTable(GermplasmList germplasmList) {

        myExcelReader.readExcelWorkbookTemplate();
        Workbook workbook = myExcelReader.getMyWorkbook();


        GermplasmAssigmentTool gat = new GermplasmAssigmentToolImpl();

        List<String> columnList = gat.getColumnList(workbook.getFactors());
        for (String string : columnList) {
            System.out.println(string);
        }
        List<List<Object>> rowList = gat.getMappedColumns(columnList, germplasmList);
        GermplasmEntriesTableModel tableModel = new GermplasmEntriesTableModel(myExcelReader.getMyWorkbook().getEntryFactors(), rowList);
        this.jTableEntries.setModel(tableModel);
        this.jTextFieldTotalEntries.setText(String.valueOf(germplasmList.getListEntries().size()));
        ajustaColumnsTable(this.jTableEntries, 2);


    }

    public void setNurseryWindow(NurseryEditorTopComponent nurseryWindow) {
        this.nurseryWindow = nurseryWindow;
    }

    public void cleanFields() {
        deshabilitaGSM();
        this.jTextAreaPathGSM.setText("");
        this.jTextAreaPathTemplate.setText("");
        this.jTableEntries.setModel(new DefaultTableModel());
        this.jTextFieldTotalEntries.setText("0");
    }

    private void deshabilitaGSM() {
        this.radGermplasmFromTemplate.setEnabled(false);
        this.radGermplasmFromDB1.setEnabled(false);
        this.jTextAreaPathGSM.setEnabled(false);
        this.jButtonSearchGSM.setEnabled(false);
        this.cboGermplasmList.setEnabled(false);
    }

    private void readGermplsmEntriesFromDb() {
        GermplasmListReader germplasmListReader = new GermplasmListReaderImpl();
        boolean validSelection = cboGermplasmList.getSelectedIndex() > 0;
        if (validSelection) {
            try {
                Listnms selectedList = (Listnms) cboGermplasmList.getSelectedItem();
                GermplasmList germplasmList = germplasmListReader.getGermPlasmListFromDB(selectedList.getListid());
                setGermplasmListIntoTable(germplasmList);

                if (this.jTableEntries.getRowCount() > 0) {
                    this.jButtonFinish.setEnabled(true);
                } else {
                    this.jButtonFinish.setEnabled(false);
                }


            } catch (Exception ex) {
                System.out.println("ERROR AL LEER EXCEL GERMPLASM ENTRIES DB: " + ex);
            }
        } else {
            GermplasmEntriesTableModel modeloTabla = new GermplasmEntriesTableModel();
            this.jTableEntries.setModel(modeloTabla);
            this.jTextAreaPathGSM.setText("");
            this.jTextFieldTotalEntries.setText("0");
            this.jButtonFinish.setEnabled(false);
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

    private void searchList() {
        SelectListDialog selectListDialog = new SelectListDialog();
        selectListDialog.showSearchDialog();
        selectListDialog.populateComboListNames(cboGermplasmList);
        if (selectListDialog.isListSelected()) {
            try {
                GermplasmListReader germplasmListReader = new GermplasmListReaderImpl();
                GermplasmList germplasmList = germplasmListReader.getGermPlasmListFromDB(selectListDialog.getSeledtedListnms().getListid());
                setGermplasmListIntoTable(germplasmList);
                this.jButtonFinish.setEnabled(true);

                if (isForWheat) {
                    showProgressStatus(selectListDialog.getSeledtedListnms().getListid());
                }
            } catch (Exception ex) {
                System.out.println("ERROR AL LEER EXCEL GERMPLASM ENTRIES DB: " + ex);
            }
        }
    }

    private void completeFullDataFromDatabase(int listID) {
        wheatColumnsforSearch = new ArrayList<String>();
        GermplasmEntriesTableModel tableModel = (GermplasmEntriesTableModel) this.jTableEntries.getModel();


        Listnms listnms = null;
        List<Listdata> listas = null;

        try {
            listnms = AppServicesProxy.getDefault().appServices().getFullListnms(listID);
            listas = (List<Listdata>) listnms.getLisdatas();
        } catch (Exception ex) {
            return;

        }

        if (tableModel.getRowCount() <= 0) {
            return;
        }

        int gidColumn = tableModel.findColumn("GID");
        System.out.println("gidColumn found in: " + gidColumn);

        if (gidColumn < 0) {
            return;
        }

        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            String col = tableModel.getColumnName(i).toUpperCase();

            if (col.equals("CROSS NAME") || col.equals("PEDIGREE")) {
                wheatColumnsforSearch.add("PEDIGREE");
            } else {

                if (wheatColumns.contains(col) && (!col.equals("GID"))) {
                    wheatColumnsforSearch.add(tableModel.getColumnName(i));
                }
            }

        }


        int crossColumn = tableModel.findColumn("CROSS NAME");
        if (crossColumn < 0) {
            crossColumn = tableModel.findColumn("PEDIGREE");
        }

        int selHistColumn = tableModel.findColumn("SELECTION HISTORY");

        GermplasmEntriesTableModel.setIsFromCrossInfo(true);

        for (int i = 0; i < tableModel.getRowCount(); i++) {

            int perc = (int) ((i + 1) * 100 / tableModel.getRowCount());

            porcentaje = String.valueOf(perc);
            handle.progress("Porcentaje completado: " + porcentaje + " %");

            String bdid = "";
            String selHist = "";
            String pedigree = "";


            try {
                bdid = listas.get(i).getName1027().getNval();//BCID
            } catch (NullPointerException ex) {
                bdid = "";
            }
            try {
                selHist = listas.get(i).getName1028().getNval();//SELECTION HISTORY
            } catch (NullPointerException ex) {
                selHist = "";
            }

            try {
                pedigree = listas.get(i).getName1029().getNval(); //PEDIGREE
            } catch (NullPointerException ex) {
                pedigree = "";
            }

            try {


                if (crossColumn >= 0) {

                    if (pedigree.isEmpty()) {
                        pedigree = "";
                    }
                    tableModel.setValueAt(pedigree, i, crossColumn);

                }

                if (selHistColumn >= 0) {
                    if (selHist.isEmpty()) {
                        selHist = "";
                    }
                    tableModel.setValueAt(selHist, i, selHistColumn);

                }


            } catch (Exception e) {
                System.out.println("ERROR" + e);
            }
        }
        GermplasmEntriesTableModel.setIsFromCrossInfo(false);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox cboGermplasmList;
    private javax.swing.JComboBox cboTemplateList;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonFinish;
    private javax.swing.JButton jButtonPreviewGSM;
    private javax.swing.JButton jButtonPreviewTemplate;
    private javax.swing.JButton jButtonSearchGSM;
    private javax.swing.JButton jButtonSearchTemplate;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButtonNormal;
    private javax.swing.JRadioButton jRadioButtonRandom;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTableEntries;
    private javax.swing.JTextArea jTextAreaPathGSM;
    private javax.swing.JTextArea jTextAreaPathTemplate;
    private javax.swing.JTextField jTextFieldTotalEntries;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JRadioButton jradExcelFile;
    private javax.swing.JRadioButton jradTemplateDB;
    private javax.swing.JRadioButton radGermplasmFromDB1;
    private javax.swing.JRadioButton radGermplasmFromTemplate;
    // End of variables declaration//GEN-END:variables
}
