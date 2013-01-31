package ibfb.ui.core;

import ibfb.domain.core.*;
import ibfb.filters.core.ExcelFiltro;
import ibfb.lists.core.SelectListDialog;
import ibfb.settings.core.FieldbookSettings;
import ibfb.studyeditor.core.ExcelReaderClass;
import ibfb.studyeditor.core.StudyEditorTopComponent;
import ibfb.studyeditor.core.model.GermplasmEntriesTableModel;
import ibfb.studyeditor.roweditors.SpinnerEditor;
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
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.*;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.OSUtils;
import org.cimmyt.cril.ibwb.domain.Listdata;
import org.cimmyt.cril.ibwb.domain.Listnms;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.netbeans.api.progress.ProgressUtils;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

public class JDExpert extends javax.swing.JDialog {

    private ResourceBundle bundle = NbBundle.getBundle(JDExpert.class);
    private JFileChooser selectorArchivo = new JFileChooser();
    private SpinnerNumberModel modeloinstances = new SpinnerNumberModel(1, 1, 1000, 1);
    private int instances = 1;
    private ExcelReaderClass myExcelReader = new ExcelReaderClass();
    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
    private int totalEntries = 0;
    public static Study studyOBJ = new Study();
    private Workbook myWorkbook;
    private Desktop desktop = null;
    private Desktop.Action action = null;
    private File archivo = null;
    private SpinnerEditor editor;
    private StudyEditorTopComponent studyWindow = null;
    private int rowsTotales = 0;
    private ArrayList<String> otherFactors = new ArrayList<String>();
    private ArrayList<String> children = new ArrayList<String>();
    private String[] nameColumn = {"Cross Name", "Selection History", "Pedigree", "CID", "SID", "GID", "INTRID", "TID", "ENT", "Folio", "Specific Name", "Name Abbreviation", "Cross Year", "Cross Location", "Cross Country", "Cross Organization", "Cross Program", "FAO In-trust", "Selection Year", "Selection Location", "Selection Country", "Name Country", "Name Year", "FAO designation Date", "24 disp", "25 disp"};
    private ArrayList<String> wheatColumns;
    private ArrayList<String> wheatColumnsforSearch;
    private ProgressHandle handle;
    private String porcentaje;
    private boolean ready = false;
    private int theCrop=WHEAT;
    public static final int WHEAT=0;
    public static final int MAIZE=1;
    public static final int OTHERCROPS=2;
    WorkbookExcelReader validateExcelReader = new WorkbookExcelReaderImpl();


    public JDExpert(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.jSpinnerInstances.setModel(modeloinstances);
        JFormattedTextField ftf = getTextField(jSpinnerInstances);
        if (ftf != null) {
            ftf.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
        }
        deshabilitaGSM();
        fillComboListNames();
        checkButtonsStatus();
        cboGermplasmList.setModel(new javax.swing.DefaultComboBoxModel(new String[]{bundle.getString("JDExpert.selectOne")}));
        try {
            theCrop = validateExcelReader.giveMeCrop();
        } catch (Exception ex) {
           theCrop=WHEAT;
        }
                
        if (theCrop==WHEAT) {
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

    public int getInstances() {
        return instances;
    }

    public void setInstances(int instances) {
        this.instances = instances;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupTemplate = new javax.swing.ButtonGroup();
        buttonGroupGSM = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jradExcelFile = new javax.swing.JRadioButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaPathTemplate = new javax.swing.JTextArea();
        jButtonSearchTemplate = new javax.swing.JButton();
        jButtonPreviewTemplate = new javax.swing.JButton();
        cboTemplateList = new javax.swing.JComboBox();
        jradTemplateDB = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        radGermplasmFromTemplate = new javax.swing.JRadioButton();
        radGermplasmFromDB1 = new javax.swing.JRadioButton();
        cboGermplasmList = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaPathGSM = new javax.swing.JTextArea();
        jButtonSearchGSM = new javax.swing.JButton();
        jButtonPreviewGSM = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableEntries = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldTotalEntries = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButtonCancelExpert = new javax.swing.JButton();
        jButtonFinishExpert = new javax.swing.JButton();
        jPanelInstances = new javax.swing.JPanel();
        jSpinnerInstances = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(JDExpert.class, "JDExpert.title_1")); // NOI18N
        setIconImage(null);
        setLocationByPlatform(true);
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(JDExpert.class, "JDExpert.jPanel2.border.title"))); // NOI18N
        jPanel2.setToolTipText(org.openide.util.NbBundle.getMessage(JDExpert.class, "JDExpert.jPanel2.toolTipText")); // NOI18N
        jPanel2.setMaximumSize(null);
        jPanel2.setPreferredSize(new java.awt.Dimension(550, 175));

        buttonGroupTemplate.add(jradExcelFile);
        jradExcelFile.setSelected(true);
        jradExcelFile.setText(org.openide.util.NbBundle.getMessage(JDExpert.class, "JDExpert.jradExcelFile.text")); // NOI18N
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

        jButtonSearchTemplate.setText(org.openide.util.NbBundle.getMessage(JDExpert.class, "JDExpert.jButtonSearchTemplate.text")); // NOI18N
        jButtonSearchTemplate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchTemplateActionPerformed(evt);
            }
        });

        jButtonPreviewTemplate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/ui/images/preview.png"))); // NOI18N
        jButtonPreviewTemplate.setText(org.openide.util.NbBundle.getMessage(JDExpert.class, "JDExpert.jButtonPreviewTemplate.text")); // NOI18N
        jButtonPreviewTemplate.setToolTipText(org.openide.util.NbBundle.getMessage(JDExpert.class, "JDExpert.jButtonPreviewTemplate.toolTipText")); // NOI18N
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

        buttonGroupTemplate.add(jradTemplateDB);
        jradTemplateDB.setText(org.openide.util.NbBundle.getMessage(JDExpert.class, "JDExpert.jradTemplateDB.text")); // NOI18N
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jradExcelFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jradTemplateDB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonSearchTemplate, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPreviewTemplate, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cboTemplateList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonPreviewTemplate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonSearchTemplate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jradExcelFile)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTemplateList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jradTemplateDB))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboTemplateList, jradTemplateDB});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonPreviewTemplate, jButtonSearchTemplate, jScrollPane5});

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(JDExpert.class, "JDExpert.jPanel3.border.title"))); // NOI18N

        buttonGroupGSM.add(radGermplasmFromTemplate);
        radGermplasmFromTemplate.setText(org.openide.util.NbBundle.getMessage(JDExpert.class, "JDExpert.radGermplasmFromTemplate.text")); // NOI18N
        radGermplasmFromTemplate.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radGermplasmFromTemplateStateChanged(evt);
            }
        });
        radGermplasmFromTemplate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radGermplasmFromTemplateActionPerformed(evt);
            }
        });

        buttonGroupGSM.add(radGermplasmFromDB1);
        radGermplasmFromDB1.setSelected(true);
        radGermplasmFromDB1.setText(org.openide.util.NbBundle.getMessage(JDExpert.class, "JDExpert.radGermplasmFromDB1.text")); // NOI18N
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

        jTextAreaPathGSM.setEditable(false);
        jTextAreaPathGSM.setColumns(20);
        jTextAreaPathGSM.setLineWrap(true);
        jTextAreaPathGSM.setRows(5);
        jTextAreaPathGSM.setEnabled(false);
        jTextAreaPathGSM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextAreaPathGSMMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTextAreaPathGSM);

        jButtonSearchGSM.setText(org.openide.util.NbBundle.getMessage(JDExpert.class, "JDExpert.jButtonSearchGSM.text")); // NOI18N
        jButtonSearchGSM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchGSMActionPerformed(evt);
            }
        });

        jButtonPreviewGSM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/ui/images/preview.png"))); // NOI18N
        jButtonPreviewGSM.setText(org.openide.util.NbBundle.getMessage(JDExpert.class, "JDExpert.jButtonPreviewGSM.text")); // NOI18N
        jButtonPreviewGSM.setToolTipText(org.openide.util.NbBundle.getMessage(JDExpert.class, "JDExpert.jButtonPreviewGSM.toolTipText")); // NOI18N
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

        jLabel5.setText(org.openide.util.NbBundle.getMessage(JDExpert.class, "JDExpert.jLabel5.text")); // NOI18N

        jTextFieldTotalEntries.setEditable(false);
        jTextFieldTotalEntries.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldTotalEntries.setText(org.openide.util.NbBundle.getMessage(JDExpert.class, "JDExpert.jTextFieldTotalEntries.text")); // NOI18N

        btnSearch.setText(org.openide.util.NbBundle.getMessage(JDExpert.class, "JDExpert.btnSearch.text")); // NOI18N
        btnSearch.setEnabled(false);
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldTotalEntries, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane4)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(radGermplasmFromDB1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                                .addComponent(radGermplasmFromTemplate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                                .addComponent(cboGermplasmList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnSearch)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jButtonSearchGSM, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonPreviewGSM, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(67, 67, 67))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboGermplasmList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch))
                    .addComponent(radGermplasmFromDB1))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, 0, 0, Short.MAX_VALUE)
                            .addComponent(jButtonSearchGSM, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonPreviewGSM, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(radGermplasmFromTemplate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTotalEntries, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel5, jTextFieldTotalEntries});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboGermplasmList, radGermplasmFromDB1});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonPreviewGSM, jButtonSearchGSM, jScrollPane3});

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(JDExpert.class, "JDExpert.jPanel4.border.title"))); // NOI18N

        jButtonCancelExpert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/ui/images/cancel.png"))); // NOI18N
        jButtonCancelExpert.setText(org.openide.util.NbBundle.getMessage(JDExpert.class, "JDExpert.jButtonCancelExpert.text")); // NOI18N
        jButtonCancelExpert.setToolTipText(org.openide.util.NbBundle.getMessage(JDExpert.class, "JDExpert.jButtonCancelExpert.toolTipText")); // NOI18N
        jButtonCancelExpert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelExpertActionPerformed(evt);
            }
        });

        jButtonFinishExpert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/ui/images/ok.png"))); // NOI18N
        jButtonFinishExpert.setText(org.openide.util.NbBundle.getMessage(JDExpert.class, "JDExpert.jButtonFinishExpert.text")); // NOI18N
        jButtonFinishExpert.setToolTipText(org.openide.util.NbBundle.getMessage(JDExpert.class, "JDExpert.jButtonFinishExpert.toolTipText")); // NOI18N
        jButtonFinishExpert.setEnabled(false);
        jButtonFinishExpert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinishExpertActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonCancelExpert)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(jButtonFinishExpert)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonFinishExpert)
                    .addComponent(jButtonCancelExpert, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelInstances.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(JDExpert.class, "JDExpert.jPanelInstances.border.title"))); // NOI18N

        jSpinnerInstances.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        jSpinnerInstances.setToolTipText(org.openide.util.NbBundle.getMessage(JDExpert.class, "JDExpert.jSpinnerInstances.toolTipText")); // NOI18N
        jSpinnerInstances.setRequestFocusEnabled(false);
        jSpinnerInstances.setValue(1);
        jSpinnerInstances.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerInstancesStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelInstancesLayout = new javax.swing.GroupLayout(jPanelInstances);
        jPanelInstances.setLayout(jPanelInstancesLayout);
        jPanelInstancesLayout.setHorizontalGroup(
            jPanelInstancesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInstancesLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jSpinnerInstances, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
        jPanelInstancesLayout.setVerticalGroup(
            jPanelInstancesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInstancesLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jSpinnerInstances, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/ui/images/123.png"))); // NOI18N
        jLabel1.setText(org.openide.util.NbBundle.getMessage(JDExpert.class, "JDExpert.jLabel1.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelInstances, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelInstances, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

            if (theCrop==WHEAT) {
                showProgressStatus(selectedList.getListid());
            }
        }
    }//GEN-LAST:event_cboGermplasmListActionPerformed

    private void showProgressStatus(final int listID) {

        handle = ProgressHandleFactory.createHandle(bundle.getString("JDExpert.loading"));

        ProgressUtils.showProgressDialogAndRun(new Runnable() {

            @Override
            public void run() {
                porcentaje = "0";
                handle.start(100);
                handle.progress(bundle.getString("JDExpert.completado") + porcentaje + " %");
                //completeFullDataFromDatabase(listID);
            }
        }, handle, true);

    }

    private void jButtonCancelExpertActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
    }

    public void setStudyWindow(StudyEditorTopComponent studyWindow) {
        this.studyWindow = studyWindow;
    }

    private void jButtonFinishExpertActionPerformed(java.awt.event.ActionEvent evt) {


        if (this.jTextAreaPathTemplate.getText().isEmpty() && this.jTextAreaPathGSM.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, bundle.getString("JDExpert.youNeedSelectValidFiles"), bundle.getString("JDExpert.error"), JOptionPane.ERROR_MESSAGE);
        } else {

            this.setVisible(false);
            myExcelReader.setFile(this.jTextAreaPathTemplate.getText());
            myExcelReader.setNumInstances((Integer) jSpinnerInstances.getValue());
            fillStudyData(studyWindow);
            studyWindow.jLabelInstances.setText(jSpinnerInstances.getValue().toString());
            studyWindow.setCROP(theCrop);
            fillStudyConditions(studyWindow);
            fillTrialConditions(studyWindow);
            fillGermplsmEntries(studyWindow);
            fillFactores(studyWindow);
            fillConstants(studyWindow);
            fillTraits(studyWindow);



            if (!myExcelReader.getMyWorkbook().hasPropertyTrialInstance()) {
                studyWindow.jTabbedPaneEditor.setEnabledAt(1, false);
            } else {
                studyWindow.jTabbedPaneEditor.setEnabledAt(1, true);
            }

            if (!existenFactores()) {
                studyWindow.jTabbedPaneEditor.setEnabledAt(4, false);
            } else {
                studyWindow.jTabbedPaneEditor.setEnabledAt(4, true);
            }

            studyWindow.setMyWorkbook(myExcelReader.getMyWorkbook());
            studyWindow.setFileTemplate(this.jTextAreaPathTemplate.getText());
            studyWindow.jTabbedPaneEditor.setEnabledAt(7, false);
            studyWindow.jTabbedPaneEditor.setSelectedIndex(5);
            studyWindow.fillDesignWorkbook();
            studyWindow.fillDesign();
            
            studyWindow.defineTabs();
            studyWindow.open();
            studyWindow.requestActive();
        }
    }

    private void jSpinnerInstancesStateChanged(javax.swing.event.ChangeEvent evt) {
        this.setInstances(Integer.valueOf(this.jSpinnerInstances.getValue().toString()));
    }

    private void radGermplasmFromTemplateStateChanged(javax.swing.event.ChangeEvent evt) {
    }

    private void radGermplasmFromTemplateActionPerformed(java.awt.event.ActionEvent evt) {
        checkButtonsStatus();
    }

    private void radGermplasmFromDB1StateChanged(javax.swing.event.ChangeEvent evt) {
    }

    private void radGermplasmFromDB1ActionPerformed(java.awt.event.ActionEvent evt) {
        checkButtonsStatus();
    }

    private void cboGermplasmListItemStateChanged(java.awt.event.ItemEvent evt) {
        readGermplsmEntriesFromDb();
    }

    private void jTextAreaPathGSMMousePressed(java.awt.event.MouseEvent evt) {

        if (this.jTextAreaPathGSM.isEnabled()) {

            if (radGermplasmFromTemplate.isSelected()) {
                openFileGSM();
                if (this.jTextAreaPathGSM.getText().isEmpty() == false) {
                    readExcelGermplsmEntries(this.jTextAreaPathGSM.getText());
                    this.jButtonPreviewGSM.setEnabled(true);
                }
            }
        }
    }

    private void jButtonSearchGSMActionPerformed(java.awt.event.ActionEvent evt) {

        openFileGSM();
        if (this.jTextAreaPathGSM.getText().isEmpty() == false) {
            readExcelGermplsmEntries(this.jTextAreaPathGSM.getText());
            this.jButtonPreviewGSM.setEnabled(true);
        }
    }

    private void jButtonPreviewGSMActionPerformed(java.awt.event.ActionEvent evt) {
        previewFile(this.jTextAreaPathGSM.getText());
    }

    private void jradExcelFileStateChanged(javax.swing.event.ChangeEvent evt) {
        // TODO add your handling code here:
    }

    private void jradExcelFileActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jTextAreaPathTemplateMousePressed(java.awt.event.MouseEvent evt) {
        openTemplateExpert();
    }

    private void jButtonSearchTemplateActionPerformed(java.awt.event.ActionEvent evt) {
        openTemplateExpert();

    }

    private void jButtonPreviewTemplateActionPerformed(java.awt.event.ActionEvent evt) {
        previewFile(this.jTextAreaPathTemplate.getText());
    }

    private void cboTemplateListItemStateChanged(java.awt.event.ItemEvent evt) {
        // TODO add your handling code here:
    }

    private void jradTemplateDBStateChanged(javax.swing.event.ChangeEvent evt) {
        // TODO add your handling code here:
    }

    private void jradTemplateDBActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void openTemplateExpert() {
        this.jButtonPreviewTemplate.setEnabled(false);
        FileFilter[] filtros = new FileFilter[10];
        filtros = selectorArchivo.getChoosableFileFilters();


        String path = "";


        path = FieldbookSettings.getSetting(FieldbookSettings.TEMPLATES_DEFAULT_FOLDER);
        System.out.println("PART TEMPLATES: " + path);

     
        
       String customPath=NbPreferences.forModule(JDExpert.class).get("customPathTQC", "");               
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

        //selectorArchivo.setSelectedFile(new File(path));


        selectorArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        selectorArchivo.addChoosableFileFilter(new ExcelFiltro());
        int resultado = selectorArchivo.showOpenDialog(null);

        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }
      
        NbPreferences.forModule(JDExpert.class).put("customPathTQC", selectorArchivo.getSelectedFile().toString());

        this.jTextAreaPathTemplate.setText(selectorArchivo.getSelectedFile().toString());
        WorkbookExcelReader validateExcelReader = new WorkbookExcelReaderImpl();
        boolean isValidFile = false;

        try {
            isValidFile = validateExcelReader.isValidTemplate(this.jTextAreaPathTemplate.getText());
        } catch (Exception ex) {
            System.out.println("ERROR AL VALIDAD TEMPLATE DE EXCEL");
        }

        if (isValidFile) {
            
             try {
                theCrop = validateExcelReader.giveMeCrop();
            } catch (Exception ex) {
                System.out.println("ERROR AL OBTENER CROP" +ex);
            }

            this.jTextAreaPathTemplate.setText(selectorArchivo.getSelectedFile().toString());
            this.jButtonPreviewTemplate.setEnabled(true);
            myExcelReader.setFile(this.jTextAreaPathTemplate.getText());
            myExcelReader.setNumInstances((Integer) jSpinnerInstances.getValue());
            habilitaGSM();
            setLabel();

            this.checkButtonsStatus();

        } else {
            this.jTextAreaPathTemplate.setText("");
            setLabelDefault();
            JOptionPane.showMessageDialog(this, bundle.getString("JDExpert.noValidTemplate") + validateExcelReader.getValidationMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void cleanFields() {
        deshabilitaGSM();
        this.jTextAreaPathGSM.setText("");
        this.jTextAreaPathTemplate.setText("");
        this.jTableEntries.setModel(new DefaultTableModel());
        this.jTextFieldTotalEntries.setText("0");
        fillComboListNames();
    }

    @SuppressWarnings("unchecked")
    private void fillStudyConditions(StudyEditorTopComponent studyWindow) {

        myExcelReader.readExcelWorkbookTemplateStudyConditions();
        Workbook workbook = myExcelReader.getMyWorkbook();

        studyWindow.assignStudyConditions(workbook.getStudyConditions());
        jSpinnerInstances.setEnabled(workbook.hasPropertyTrialInstance());
    }

    @SuppressWarnings("unchecked")
    private void fillTrialConditions(StudyEditorTopComponent studyWindow) {
        myExcelReader.readExcelWorkbookTemplate();
        Workbook workbook = myExcelReader.getMyWorkbook();

        SpinnerNumberModel modelo = new SpinnerNumberModel(1, 1, myExcelReader.getNumInstances().intValue(), 1);
        studyWindow.jSpinnerTrialInformation.setModel(modelo);

        List<Condition> trialConditions = new ArrayList();
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
                    trialConditions.add(condToAdd);
                }

            }
            instance++;
        }
        studyWindow.assignTrialConditions(trialConditions);

    }

    @SuppressWarnings("unchecked")
    private void setGermplasmListIntoTable(Integer listid, GermplasmList germplasmList) {

        myExcelReader.readExcelWorkbookTemplate();
        Workbook workbook = myExcelReader.getMyWorkbook();


        GermplasmAssigmentTool gat = new GermplasmAssigmentToolImpl();

        List<String> columnList = gat.getColumnList(workbook.getFactors());
        for (String string : columnList) {
            System.out.println(string);
        }
        List<List<Object>> rowList = gat.getMappedColumns(columnList, germplasmList);
        
        //gat.mappCimmytWheatColumns(listid, rowList, workbook.getFactors());
        
        GermplasmEntriesTableModel tableModel = new GermplasmEntriesTableModel(myExcelReader.getMyWorkbook().getEntryFactors(), rowList);
        this.jTableEntries.setModel(tableModel);
        this.jTextFieldTotalEntries.setText(String.valueOf(germplasmList.getListEntries().size()));

        ajustaColumnsTable(this.jTableEntries, 2);

    }

    @SuppressWarnings("unchecked")
    private void fillFactores(StudyEditorTopComponent studyWindow) {
        Workbook workbook = myExcelReader.getMyWorkbook();
        studyWindow.assignOtherTreatmentFactors(workbook.getOtherFactors());
        List<FactorLabel> factorLabels = new ArrayList<FactorLabel>();

        studyWindow.assignOtherTreatmentFactorLabels(factorLabels);


        //generateCombinations();

    }

    private void generateCombinations() {
        int totalFactores = otherFactors.size();
        int totalRowsToAdd = 1;

        int cont = 0;

        int repet[] = new int[totalFactores];

        totalRowsToAdd = 1;


        repet[totalFactores - 1] = 1;
        cont = totalFactores - 1;
        for (int i = 0; i < totalFactores - 1; i++) {
            repet[cont - 1] = 1;
            cont--;
        }



        for (int i = 0; i < totalFactores; i++) {
            System.out.println("REP: " + repet[i]);

        }



        int[][] factorsDesign = new int[totalFactores][totalRowsToAdd];
        String[][] factorsDesignCad = new String[totalFactores][totalRowsToAdd];


        for (int i = 0; i < totalFactores; i++) {
            int contador = 0;
            int index = 1;

            for (int j = 0; j < totalRowsToAdd; j++) {


                if (i == totalFactores - 1) {
                    factorsDesign[i][j] = index;
                    factorsDesignCad[i][j] = children.get(i);
                    index++;
                    if (index > 1) {
                        index = 1;
                    }

                    continue;
                }

                if (contador < repet[i]) {
                    contador++;
                } else {
                    contador = 1;

                    if (index < 1) {
                        index++;
                    } else {
                        index = 1;
                    }
                }

                factorsDesign[i][j] = index;
                factorsDesignCad[i][j] = children.get(i);

            }

        }







        for (int j = 0; j < totalRowsToAdd; j++) {
            for (int i = 0; i < totalFactores; i++) {
                System.out.print(factorsDesign[i][j] + " ");

            }
            System.out.println("");

        }



        for (int j = 0; j < totalRowsToAdd; j++) {
            for (int i = 0; i < totalFactores; i++) {
                System.out.print(factorsDesignCad[i][j] + " ");

            }
            System.out.println("");
        }

        studyWindow.setCombinations(totalRowsToAdd);
        studyWindow.setFactorsDesignCad(factorsDesignCad);
        studyWindow.setOtherFactors(otherFactors);
        studyWindow.setChildren(children);
    }

    @SuppressWarnings("unchecked")
    private void fillConstants(StudyEditorTopComponent studyWindow) {
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
        studyWindow.assignExperimentalConditions(constants);
    }

    @SuppressWarnings("unchecked")
    private void fillTraits(StudyEditorTopComponent studyWindow) {
        Workbook workbook = myExcelReader.getMyWorkbook();
        studyWindow.setMyWorkbook(workbook);
        studyWindow.configMyList();
        studyWindow.assignTraits(new ArrayList<Variate>(), workbook.getVariates());
        studyWindow.setSelectedTraits(workbook.getVariates());
    }

    private void quitaCellEditors(StudyEditorTopComponent studyWindow) {
        JTextField jtf = new JTextField();
        TableColumn valueColumnStudy = studyWindow.jTableDesign.getColumnModel().getColumn(2);
        valueColumnStudy.setCellEditor(new DefaultCellEditor(jtf));
        valueColumnStudy = studyWindow.jTableDesign.getColumnModel().getColumn(3);
        valueColumnStudy.setCellEditor(new DefaultCellEditor(jtf));
        valueColumnStudy = studyWindow.jTableDesign.getColumnModel().getColumn(4);
        valueColumnStudy.setCellEditor(new DefaultCellEditor(jtf));
    }

    private void fillStudyData(StudyEditorTopComponent studyWindow) {
        studyWindow.setName(studyOBJ.getStudy());
        studyWindow.jTextFieldStudy.setText(studyOBJ.getStudy());
        studyWindow.jTextFieldObjective.setText(studyOBJ.getObjective());
        studyWindow.jTextFieldTitle.setText(studyOBJ.getTitle());
        Date start = studyOBJ.getStarDate();
        Date end = studyOBJ.getEndDate();
        String formato = "dd-MMM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(formato);

        try {
            studyWindow.jDateChooserStart.setDate(start);
        } catch (NullPointerException ex) {
        }

        try {
            studyWindow.jDateChooserEnd.setDate(end);
        } catch (NullPointerException ex) {
        }
        try {
            studyWindow.jTextFieldPMKey.setText(studyOBJ.getPmkey());
        } catch (NullPointerException ex) {
        }
        try {
            studyWindow.jComboBoxStudyType.setSelectedItem(studyOBJ.getStudyType());
        } catch (NullPointerException ex) {
            studyWindow.jComboBoxStudyType.setSelectedItem(0);
        }
    }

    private void checkButtonsStatus() {
        boolean enabled = radGermplasmFromTemplate.isSelected();
        jTextAreaPathGSM.setEnabled(enabled);
        jButtonSearchGSM.setEnabled(enabled);
        cboGermplasmList.setEnabled(!enabled);
        btnSearch.setEnabled(!enabled);
        GermplasmEntriesTableModel modeloTablaEntries = new GermplasmEntriesTableModel();
        this.jTableEntries.setModel(modeloTablaEntries);
        //modeloTablaEntries.clearTable();

        //modeloTablaEntries.setColumnCount(0);
        //modeloTablaEntries.setNumRows(0);
        if (enabled) {
            if (cboGermplasmList.getItemCount() > 0) {
                cboGermplasmList.setSelectedIndex(0);
            }
            this.jButtonPreviewGSM.setEnabled(false);
        } else {
            if (cboGermplasmList.getItemCount() > 0) {
                cboGermplasmList.setSelectedIndex(0);
            }
            jTextAreaPathGSM.setText("");
            this.jTextFieldTotalEntries.setText("0");
            this.jButtonPreviewGSM.setEnabled(false);
            this.jTableEntries.removeAll();
        }
    }

    private void readGermplsmEntriesFromDb() {
        GermplasmListReader germplasmListReader = new GermplasmListReaderImpl();
        boolean validSelection = cboGermplasmList.getSelectedIndex() > 0;
        if (validSelection) {
            try {
                Listnms selectedList = (Listnms) cboGermplasmList.getSelectedItem();
                GermplasmList germplasmList = germplasmListReader.getGermPlasmListFromDB(selectedList.getListid());
                setGermplasmListIntoTable(selectedList.getListid(),germplasmList);
                this.jButtonFinishExpert.setEnabled(true);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("ERROR AL LEER EXCEL GERMPLASM ENTRIES DB: " + ex);
            }
        } else {
            GermplasmEntriesTableModel modeloTabla = new GermplasmEntriesTableModel();
            this.jTableEntries.setModel(modeloTabla);
            this.jTextAreaPathGSM.setText("");
            this.jTextFieldTotalEntries.setText("0");
            this.jButtonFinishExpert.setEnabled(false);
        }
    }

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

    private void readExcelGermplsmEntries(String myFile) {

        GermplasmListReader germplasmListReader = new GermplasmListReaderImpl();
        boolean validFile = false;
        try {
            validFile = germplasmListReader.isValidTemplate(myFile);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR AL VALIDAR ARCHIVO EXCEL");
        }

        if (validFile) {
            try {
                GermplasmList germplasmList = germplasmListReader.getGermPlasmList(myFile);
                setGermplasmListIntoTable(0,germplasmList);
            } catch (Exception ex) {
                System.out.println("ERROR AL LEER EXCEL GERMPLASM ENTRIES: " + ex);
            }
        } else {
            DefaultTableModel modeloTabla = new DefaultTableModel();
            modeloTabla = (DefaultTableModel) this.jTableEntries.getModel();
            modeloTabla.setNumRows(0);
            this.jTextAreaPathGSM.setText("");
            this.jTextFieldTotalEntries.setText("0");
            JOptionPane.showMessageDialog(this, bundle.getString("JDExpert.noValidEntriesFile"), bundle.getString("JDExpert.error"), JOptionPane.ERROR_MESSAGE);
            this.jButtonFinishExpert.setEnabled(false);
        }

    }

    public final void fillComboListNames() {
        List<Listnms> germplasmList = AppServicesProxy.getDefault().appServices().getListnmsList();
        cboGermplasmList.removeAllItems();
        cboGermplasmList.addItem(bundle.getString("JDExpert.selectOne"));
        for (Listnms list : germplasmList) {
            cboGermplasmList.addItem(list);
        }

    }

    private void openFileGSM() {
        this.jButtonFinishExpert.setEnabled(false);
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
        
        String customPath=NbPreferences.forModule(JDExpert.class).get("customPathTQCGsm", "");               
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
                this.jButtonFinishExpert.setEnabled(false);
            } else {
                this.jButtonFinishExpert.setEnabled(true);
            }
            return;
        }
     
         NbPreferences.forModule(JDExpert.class).put("customPathTQCGsm", selectorArchivo.getSelectedFile().toString());

        this.jTextAreaPathGSM.setText(selectorArchivo.getSelectedFile().toString());
        this.jButtonFinishExpert.setEnabled(true);

    }

    private void habilitaGSM() {
        this.radGermplasmFromTemplate.setEnabled(true);
        this.radGermplasmFromDB1.setEnabled(true);
        this.jTextAreaPathGSM.setEnabled(true);
        this.jButtonSearchGSM.setEnabled(true);
    }

    private void deshabilitaGSM() {
        this.radGermplasmFromTemplate.setEnabled(false);
        this.radGermplasmFromDB1.setEnabled(false);
        this.jTextAreaPathGSM.setEnabled(false);
        this.jButtonSearchGSM.setEnabled(false);
        this.cboGermplasmList.setEnabled(false);
        btnSearch.setEnabled(false);
    }

    private void fillGermplsmEntries(StudyEditorTopComponent studyWindow) {
//        DefaultTableModel modeloTabla = (DefaultTableModel) this.jTableEntries.getModel();
//        studyWindow.jTableEntries.setModel(modeloTabla);
        studyWindow.jTextFieldEntries.setText(this.jTextFieldTotalEntries.getText());

        GermplasmEntriesTableModel tableModel = (GermplasmEntriesTableModel) this.jTableEntries.getModel();
        studyWindow.assignGermplasmEntries(tableModel.getFactorHeaders(), tableModel.getGermplasmData());

    }

    public void setLabel() {
        myExcelReader.readExcelWorkbookTemplateStudyConditions();
        Workbook workbook = myExcelReader.getMyWorkbook();
        String label = workbook.getInstanceLabel();
        String cad = bundle.getString("JDExpert.specifyNumberOfLevelsOf") + " " + label;
        TitledBorder title;
        title = BorderFactory.createTitledBorder(cad);
        this.jPanelInstances.setBorder(title);
    }

    private void setLabelDefault() {
        String cad = bundle.getString("JDExpert.specifyNumberOfLevelsOfTrials");
        TitledBorder title;
        title = BorderFactory.createTitledBorder(cad);
        this.jPanelInstances.setBorder(title);
    }

    @SuppressWarnings("unchecked")
    public boolean existenFactores() {
        myExcelReader.readExcelWorkbookTemplateStudyConditions();
        Workbook workbook = myExcelReader.getMyWorkbook();
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

    public final JFormattedTextField getTextField(JSpinner spinner) {
        JComponent editorJSpinner = spinner.getEditor();
        if (editorJSpinner instanceof JSpinner.DefaultEditor) {
            return ((JSpinner.DefaultEditor) editorJSpinner).getTextField();
        } else {
            System.err.println("Unexpected editor type: " + spinner.getEditor().getClass());
            return null;
        }
    }

    private void searchList() {
        SelectListDialog selectListDialog = new SelectListDialog();
        selectListDialog.showSearchDialog();
        selectListDialog.populateComboListNames(cboGermplasmList);
        if (selectListDialog.isListSelected()) {
            try {
                GermplasmListReader germplasmListReader = new GermplasmListReaderImpl();
                GermplasmList germplasmList = germplasmListReader.getGermPlasmListFromDB(selectListDialog.getSeledtedListnms().getListid());
                setGermplasmListIntoTable(selectListDialog.getSeledtedListnms().getListid(),germplasmList);
                this.jButtonFinishExpert.setEnabled(true);

                if (theCrop==0) {
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.ButtonGroup buttonGroupGSM;
    private javax.swing.ButtonGroup buttonGroupTemplate;
    private javax.swing.JComboBox cboGermplasmList;
    private javax.swing.JComboBox cboTemplateList;
    private javax.swing.JButton jButtonCancelExpert;
    private javax.swing.JButton jButtonFinishExpert;
    private javax.swing.JButton jButtonPreviewGSM;
    private javax.swing.JButton jButtonPreviewTemplate;
    private javax.swing.JButton jButtonSearchGSM;
    private javax.swing.JButton jButtonSearchTemplate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelInstances;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSpinner jSpinnerInstances;
    private javax.swing.JTable jTableEntries;
    private javax.swing.JTextArea jTextAreaPathGSM;
    private javax.swing.JTextArea jTextAreaPathTemplate;
    private javax.swing.JTextField jTextFieldTotalEntries;
    private javax.swing.JRadioButton jradExcelFile;
    private javax.swing.JRadioButton jradTemplateDB;
    private javax.swing.JRadioButton radGermplasmFromDB1;
    private javax.swing.JRadioButton radGermplasmFromTemplate;
    // End of variables declaration//GEN-END:variables
}
