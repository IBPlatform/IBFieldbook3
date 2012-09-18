package ibfb.nursery.advancewizard;

import ibfb.nursery.maize.PolinizationWizardIterator;
import ibfb.nursery.maize.PolinizationWizardPanel1;
import ibfb.nursery.naming.NamingConvention;
import ibfb.nursery.persons.SelectLocationPanel;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JPanel;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.ConvertUtils;
import org.cimmyt.cril.ibwb.domain.Location;
import org.cimmyt.cril.ibwb.domain.Methods;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.WizardDescriptor;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

public final class AdvanceVisualPanel1 extends JPanel {
    
    int maizeMethod=0;  
    int whitParentheses=0; //0= no  1=si
    String maizeMethodName="Autofecundadas individuamente (-)";
    String maizeTooltip="Autofecundadas y mazorcas desgranadas individuamente (-)";

    
    public static final int DEFAULT_METHOD = 205; // SINGLE PLANT
    
    /**
     * List of sorted methods in combo
     */
    private  List<Methods> methodsInCombo;
    
    /**
     * Selected location from Dialog
     */
    private Location selectedLocation;
    
    /**
     * Location abbrevaiature
     */
    private String locationAbbr;
    
    /**
     * Selected breeding method
     */
    private Integer breedingMethod;
    
    public AdvanceVisualPanel1() {
        initComponents();
        loadMethodsIntoCombo();
        setDate();
        loadDefaultMethod();
        fillComboNumberOfSamplesPerPlot();
        setDefaultLocation();
        fillNamingConvention();
        jComboBoxConvention.setSelectedIndex(2);
        this.breedingMethod = AdvanceWizardIterator.breedingMethod;
    }
    
    @Override
    public String getName() {
        return NbBundle.getMessage(AdvanceVisualPanel1.class,"AdvanceVisualPanel1.name");
    }
    
    public void fillComboNumberOfSamplesPerPlot() {
        this.jComboBoxSamplesPerPlot.removeAllItems();
        
        for (int i = 0; i < 1000; i++) {
            this.jComboBoxSamplesPerPlot.addItem(i + 1);
        }
        DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        jComboBoxSamplesPerPlot.setRenderer(dlcr);
        
    }
    
    private void fillNamingConvention() {
        DefaultComboBoxModel dcbm = (DefaultComboBoxModel) jComboBoxConvention.getModel();
        dcbm.removeAllElements();
        dcbm.addElement(NamingConvention.CIMMYT_WHEAT);
        dcbm.addElement(NamingConvention.CIMMYT_MAIZE);
        dcbm.addElement(NamingConvention.OTHER_CROPS);
    }
    
    public int getMethodIndex() {
        return this.jComboBoxConvention.getSelectedIndex();
    }
    
    public int getSamples() {
        return this.jComboBoxSamplesPerPlot.getSelectedIndex() + 1;
    }
    
    public int getsSamplesMethod() {
        
        if (jRadioButtonSameNumberYES.isSelected()) {
            return 0;
        } else {
            return 1;
        }
        
    }
    
    public void loadLocationPreference() {
        
    }
    
    public void setDate() {
        String DATE_FORMAT = "yyyyMMdd";
        jDateChooser1.setDateFormatString(DATE_FORMAT);
        Date fecha = new Date();
        jDateChooser1.setDate(fecha);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jRadioButtonSameMethodNO = new javax.swing.JRadioButton();
        jRadioButtonSameMethodYES = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jRadioButtonSameNumberNO = new javax.swing.JRadioButton();
        jRadioButtonSameNumberYES = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblHarvestDate = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        lblHarvestLocation = new javax.swing.JLabel();
        jTextFieldLocation = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lblNamingConvention = new javax.swing.JLabel();
        jComboBoxConvention = new javax.swing.JComboBox();
        lblSuffix = new javax.swing.JLabel();
        jTextFieldSuffix = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jComboBoxSamplesPerPlot = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButtonChange = new javax.swing.JButton();
        jLabelMaizeMethod = new javax.swing.JLabel();
        jComboBoxMethods = new javax.swing.JComboBox();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(AdvanceVisualPanel1.class, "AdvanceVisualPanel1.jPanel1.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(AdvanceVisualPanel1.class, "AdvanceVisualPanel1.jLabel1.text")); // NOI18N

        buttonGroup1.add(jRadioButtonSameMethodNO);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonSameMethodNO, org.openide.util.NbBundle.getMessage(AdvanceVisualPanel1.class, "AdvanceVisualPanel1.jRadioButtonSameMethodNO.text")); // NOI18N
        jRadioButtonSameMethodNO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSameMethodNOActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonSameMethodYES);
        jRadioButtonSameMethodYES.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonSameMethodYES, org.openide.util.NbBundle.getMessage(AdvanceVisualPanel1.class, "AdvanceVisualPanel1.jRadioButtonSameMethodYES.text")); // NOI18N
        jRadioButtonSameMethodYES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSameMethodYESActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(AdvanceVisualPanel1.class, "AdvanceVisualPanel1.jLabel5.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonSameMethodNO)
                    .addComponent(jRadioButtonSameMethodYES))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jRadioButtonSameMethodYES))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonSameMethodNO)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(AdvanceVisualPanel1.class, "AdvanceVisualPanel1.jPanel2.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(AdvanceVisualPanel1.class, "AdvanceVisualPanel1.jLabel2.text")); // NOI18N

        buttonGroup2.add(jRadioButtonSameNumberNO);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonSameNumberNO, org.openide.util.NbBundle.getMessage(AdvanceVisualPanel1.class, "AdvanceVisualPanel1.jRadioButtonSameNumberNO.text")); // NOI18N
        jRadioButtonSameNumberNO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSameNumberNOActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButtonSameNumberYES);
        jRadioButtonSameNumberYES.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonSameNumberYES, org.openide.util.NbBundle.getMessage(AdvanceVisualPanel1.class, "AdvanceVisualPanel1.jRadioButtonSameNumberYES.text")); // NOI18N
        jRadioButtonSameNumberYES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSameNumberYESActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(AdvanceVisualPanel1.class, "AdvanceVisualPanel1.jLabel7.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonSameNumberYES)
                    .addComponent(jRadioButtonSameNumberNO))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jRadioButtonSameNumberYES))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonSameNumberNO)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblHarvestDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(lblHarvestDate, org.openide.util.NbBundle.getMessage(AdvanceVisualPanel1.class, "AdvanceVisualPanel1.lblHarvestDate.text")); // NOI18N
        lblHarvestDate.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jDateChooser1.setToolTipText(org.openide.util.NbBundle.getMessage(AdvanceVisualPanel1.class, "AdvanceVisualPanel1.jDateChooser1.toolTipText")); // NOI18N

        lblHarvestLocation.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(lblHarvestLocation, org.openide.util.NbBundle.getMessage(AdvanceVisualPanel1.class, "AdvanceVisualPanel1.lblHarvestLocation.text")); // NOI18N

        jTextFieldLocation.setText(org.openide.util.NbBundle.getMessage(AdvanceVisualPanel1.class, "AdvanceVisualPanel1.jTextFieldLocation.text")); // NOI18N

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/findLocation.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(AdvanceVisualPanel1.class, "AdvanceVisualPanel1.jButton1.text")); // NOI18N
        jButton1.setToolTipText(org.openide.util.NbBundle.getMessage(AdvanceVisualPanel1.class, "AdvanceVisualPanel1.jButton1.toolTipText")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(176, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblHarvestDate, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblHarvestLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(66, 66, 66))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHarvestDate)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblHarvestLocation)
                    .addComponent(jTextFieldLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jDateChooser1, jTextFieldLocation, lblHarvestDate, lblHarvestLocation});

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblNamingConvention.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(lblNamingConvention, org.openide.util.NbBundle.getMessage(AdvanceVisualPanel1.class, "AdvanceVisualPanel1.lblNamingConvention.text")); // NOI18N

        jComboBoxConvention.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CIMMYT - WHEAT", "CIMMYT - MAIZE", "OTHER CROPS" }));
        jComboBoxConvention.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxConventionItemStateChanged(evt);
            }
        });

        lblSuffix.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(lblSuffix, org.openide.util.NbBundle.getMessage(AdvanceVisualPanel1.class, "AdvanceVisualPanel1.lblSuffix.text")); // NOI18N

        jTextFieldSuffix.setEditable(false);
        jTextFieldSuffix.setText(org.openide.util.NbBundle.getMessage(AdvanceVisualPanel1.class, "AdvanceVisualPanel1.jTextFieldSuffix.text")); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblSuffix, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldSuffix, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblNamingConvention, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxConvention, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxConvention, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNamingConvention))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSuffix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSuffix, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBoxConvention, lblNamingConvention});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTextFieldSuffix, lblSuffix});

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(AdvanceVisualPanel1.class, "AdvanceVisualPanel1.jPanel5.border.title"))); // NOI18N

        jComboBoxSamplesPerPlot.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(AdvanceVisualPanel1.class, "AdvanceVisualPanel1.jLabel4.text")); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jComboBoxSamplesPerPlot, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxSamplesPerPlot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(AdvanceVisualPanel1.class, "AdvanceVisualPanel1.jPanel6.border.title"))); // NOI18N

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(AdvanceVisualPanel1.class, "AdvanceVisualPanel1.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButtonChange, org.openide.util.NbBundle.getMessage(AdvanceVisualPanel1.class, "AdvanceVisualPanel1.jButtonChange.text")); // NOI18N
        jButtonChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChangeActionPerformed(evt);
            }
        });

        jLabelMaizeMethod.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(jLabelMaizeMethod, org.openide.util.NbBundle.getMessage(AdvanceVisualPanel1.class, "AdvanceVisualPanel1.jLabelMaizeMethod.text")); // NOI18N

        jComboBoxMethods.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Single plants selection" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabelMaizeMethod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jButtonChange, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jComboBoxMethods, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxMethods, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabelMaizeMethod)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonChange))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel1, jPanel2});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setDefaultLocation() {
        
        String loc = NbPreferences.forModule(AdvanceVisualPanel1.class).get("location", "");
        this.jTextFieldLocation.setText(loc);        
        
        locationAbbr = NbPreferences.forModule(AdvanceWizardPanel1.class).get("LAbbr", "");
        
        selectedLocation = new Location(true);
        selectedLocation.setLocid(NbPreferences.forModule(AdvanceVisualPanel1.class).getInt("LocationId", 0));
        selectedLocation.setLabbr(locationAbbr);
        
        
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        SelectLocationPanel selectLocationPanel = new SelectLocationPanel();
        selectLocationPanel.assignNamingConvention((String) jComboBoxConvention.getSelectedItem());
        
        NotifyDescriptor notifyDescriptor = new NotifyDescriptor(selectLocationPanel, NbBundle.getMessage(AdvanceVisualPanel1.class,"AdvanceVisualPanel1.location"), NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);
        
        if (DialogDisplayer.getDefault().notify(notifyDescriptor) == NotifyDescriptor.OK_OPTION) {
            String value = selectLocationPanel.getLocationName();
            this.selectedLocation = selectLocationPanel.getSelectedLocation();
            NbPreferences.forModule(AdvanceVisualPanel1.class).put("location", value);            
            
            this.jTextFieldLocation.setText(value);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void jRadioButtonSameMethodNOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonSameMethodNOActionPerformed
        if (jRadioButtonSameMethodNO.isSelected()) {
            this.jComboBoxMethods.setEnabled(false);
        }
    }//GEN-LAST:event_jRadioButtonSameMethodNOActionPerformed
    
    private void jRadioButtonSameMethodYESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonSameMethodYESActionPerformed
        if (jRadioButtonSameMethodYES.isSelected()) {
            this.jComboBoxMethods.setEnabled(true);
        }
    }//GEN-LAST:event_jRadioButtonSameMethodYESActionPerformed
    
    private void jRadioButtonSameNumberYESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonSameNumberYESActionPerformed
        if (jRadioButtonSameNumberYES.isSelected()) {
            this.jComboBoxSamplesPerPlot.setEnabled(true);
        }
    }//GEN-LAST:event_jRadioButtonSameNumberYESActionPerformed
    
    private void jRadioButtonSameNumberNOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonSameNumberNOActionPerformed
        if (jRadioButtonSameNumberNO.isSelected()) {
            this.jComboBoxSamplesPerPlot.setEnabled(false);
        }
    }//GEN-LAST:event_jRadioButtonSameNumberNOActionPerformed

    
    
    private void showMaizeWizard() {
        
         WizardDescriptor wiz = new WizardDescriptor(new PolinizationWizardIterator());
                 // {0} will be replaced by WizardDescriptor.Panel.getComponent().getName()
                 // {1} will be replaced by WizardDescriptor.Iterator.name()
                 wiz.setTitleFormat(new MessageFormat("{0} ({1})"));
                 wiz.setTitle("...dialog title...");
                 if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
                   
                 maizeMethod = NbPreferences.forModule(PolinizationWizardPanel1.class).getInt("maizeMethod", 0);
                 whitParentheses = NbPreferences.forModule(PolinizationWizardPanel1.class).getInt("whitParentheses", 0);
                 maizeMethodName = NbPreferences.forModule(PolinizationWizardPanel1.class).get("maizeMethodName", "NO METHOD");
                 maizeTooltip= NbPreferences.forModule(PolinizationWizardPanel1.class).get("maizeTooltip", "NO TOOLTIP");
                 
                 jLabelMaizeMethod.setText(maizeMethodName);
                 jLabelMaizeMethod.setToolTipText(maizeTooltip);
                 
                     System.out.println("METODO A REALIZAR: "+maizeMethod);
                     System.out.println("CON PARENTESIS: "+whitParentheses);
                 }
    }


    private void jComboBoxConventionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxConventionItemStateChanged
                        

        if (evt.getStateChange() == 1) {
            switch (jComboBoxConvention.getSelectedIndex()) {

                case 0://wheat
                    lblSuffix.setVisible(false);
                    jTextFieldSuffix.setText("");
                    jTextFieldSuffix.setEditable(false);
                    jTextFieldSuffix.setVisible(false);
                    jComboBoxMethods.setVisible(true);
                    jLabelMaizeMethod.setVisible(false);
                    jButtonChange.setVisible(false);
                    break;
                
                case 1://maize

                    lblSuffix.setVisible(false);
                    jTextFieldSuffix.setText("");
                    jTextFieldSuffix.setEditable(false);
                    jTextFieldSuffix.setVisible(false);
                    jComboBoxMethods.setVisible(false);
                    jLabelMaizeMethod.setVisible(true);
                    jButtonChange.setVisible(true);
                    showMaizeWizard();
                    break;

                case 2:// other crops
                    lblSuffix.setVisible(true);
                    jTextFieldSuffix.setEditable(true);
                    jTextFieldSuffix.setVisible(true);
                    jComboBoxMethods.setVisible(true);
                    jLabelMaizeMethod.setVisible(false);
                    jButtonChange.setVisible(false);

                    break;
            }

        }

       
    }//GEN-LAST:event_jComboBoxConventionItemStateChanged

    private void jButtonChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChangeActionPerformed
       showMaizeWizard();
    }//GEN-LAST:event_jButtonChangeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonChange;
    private javax.swing.JComboBox jComboBoxConvention;
    private javax.swing.JComboBox jComboBoxMethods;
    private javax.swing.JComboBox jComboBoxSamplesPerPlot;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelMaizeMethod;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButtonSameMethodNO;
    private javax.swing.JRadioButton jRadioButtonSameMethodYES;
    private javax.swing.JRadioButton jRadioButtonSameNumberNO;
    private javax.swing.JRadioButton jRadioButtonSameNumberYES;
    private javax.swing.JTextField jTextFieldLocation;
    private javax.swing.JTextField jTextFieldSuffix;
    private javax.swing.JLabel lblHarvestDate;
    private javax.swing.JLabel lblHarvestLocation;
    private javax.swing.JLabel lblNamingConvention;
    private javax.swing.JLabel lblSuffix;
    // End of variables declaration//GEN-END:variables

    private void loadMethodsIntoCombo() {
        List<Methods> methodsList = AppServicesProxy.getDefault().appServices().getMethodsList();
        
        List<String> sortedMethods = new ArrayList<String>();
        methodsInCombo = new ArrayList<Methods>();
        
        int defaultSelectedIndex = 0;
        int counter = -1;
        String selectedMethodName = "";
        
        if (breedingMethod == null) {
            breedingMethod = DEFAULT_METHOD;
        }
        
        
        
        for (Methods methods : methodsList) {
            if (methods.getMtype().equals("DER") || methods.getMtype().equals("MAN")) {
                sortedMethods.add(methods.getMname().toUpperCase());
                methodsInCombo.add(methods);
                //if (methods.getMid().intValue() == DEFAULT_METHOD) {
                if (methods.getMid().intValue() == breedingMethod.intValue()) {
                    selectedMethodName = methods.getMname();
                }
            }
        }
        
        
        Collections.sort(sortedMethods);
        
        jComboBoxMethods.removeAllItems();
        
        for (String methodName : sortedMethods) {
            counter++;
            jComboBoxMethods.addItem(methodName);
            if (methodName.toUpperCase().equals(selectedMethodName.toUpperCase())) {
                defaultSelectedIndex = counter;
            }
            
        }
        jComboBoxMethods.setEditable(false);
        jComboBoxMethods.setSelectedIndex(defaultSelectedIndex);
        
        
        
    }
    
    private void loadDefaultMethod() {
        if (!AdvanceWizardIterator.metodo.isEmpty()) {
            this.jComboBoxMethods.setSelectedItem(AdvanceWizardIterator.metodo);
        }
    }
    
    public Methods getSelectedMethod() {
        
        Methods selectedMethod  = null;
        String methodName = (String)jComboBoxMethods.getSelectedItem();
        for (Methods methods: methodsInCombo) {
            
            if (methods.getMname().toUpperCase().equals(methodName.toUpperCase()) ) {
                selectedMethod = methods;   
            }
        }
        return  selectedMethod;
    }

    public Location getSelectedLocation() {
        return selectedLocation;
    }

    public void setSelectedLocation(Location selectedLocation) {
        this.selectedLocation = selectedLocation;
    }

    public Integer getHarvestDate() {
        Integer harvestDate = ConvertUtils.getDateAsInteger(jDateChooser1.getDate());
        return harvestDate;
    }
    
    public String getLAbbr() {
        if (jComboBoxConvention.getSelectedIndex()==2) {
            return jTextFieldSuffix.getText();
        } else {
            return getSelectedLocation().getLabbr();
        }
    }

    public Integer getBreedingMethod() {
        return breedingMethod;
    }

    public void setBreedingMethod(Integer breedingMethod) {
        this.breedingMethod = breedingMethod;
    }
    
    
    
}
