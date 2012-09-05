package org.cimmyt.cril.ibwb.db.options;

import ibfb.settings.core.FieldbookSettings;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import org.cimmyt.cril.ibwb.db.util.DatabaseInfo;
import org.ini4j.Ini;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.modules.InstalledFileLocator;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

final class DatabaseConnectionSettingsPanel extends javax.swing.JPanel {
    
    private ResourceBundle bundle = NbBundle.getBundle(DatabaseConnectionSettingsPanel.class);

    private final DatabaseConnectionSettingsOptionsPanelController controller;

    /**
     * Initializes a combo box with database type constants
     */
    private void initComboBox(JComboBox comboBox) {
        DefaultComboBoxModel dcbm = (DefaultComboBoxModel) comboBox.getModel();
        dcbm.removeAllElements();
        for (DatabaseType databaseType : DatabaseType.values()) {
            dcbm.addElement(databaseType);
        }
    }

    /**
     * Initializes combo boxes with database type constants
     */
    private void initComboBoxes() {
        initComboBox(cboDatabaseTypeCentral);
        initComboBox(cboDatabaseTypeLocal);
        initComboBox(cboGmsCentralDbType);
        initComboBox(cboGmsLocalDbType);
    }

    DatabaseConnectionSettingsPanel(DatabaseConnectionSettingsOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        initComboBoxes();
        // TODO listen to changes in form fields and call controller.changed()
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCentral = new javax.swing.JPanel();
        lblServerNameCentral = new javax.swing.JLabel();
        lblDatabaseTypeCentral = new javax.swing.JLabel();
        cboDatabaseTypeCentral = new javax.swing.JComboBox();
        txtServerNameCentral = new javax.swing.JTextField();
        lblSErverPortCentral = new javax.swing.JLabel();
        spnPortCentral = new javax.swing.JSpinner();
        lblUserNameCentral = new javax.swing.JLabel();
        txtUserNameCentral = new javax.swing.JTextField();
        lblPasswordCentral = new javax.swing.JLabel();
        txtPaswordCentral = new javax.swing.JPasswordField();
        lblDatabaseNameCentral = new javax.swing.JLabel();
        txtDatabaseNameCentral = new javax.swing.JTextField();
        btnTestConnectionCentral = new javax.swing.JButton();
        btnImportFromIniFile = new javax.swing.JButton();
        lblImgCentral = new javax.swing.JLabel();
        lblDMSCentralConnection = new javax.swing.JLabel();
        cboGmsCentralDbType = new javax.swing.JComboBox();
        txtServerGMSCentral = new javax.swing.JTextField();
        spnGmsCentral = new javax.swing.JSpinner();
        txtDbnameGmsCentral = new javax.swing.JTextField();
        txtUserNameGmsCentral = new javax.swing.JTextField();
        btnTestGMSCentralConnection = new javax.swing.JButton();
        lblGMSCentralConnection = new javax.swing.JLabel();
        txtPasswordGMSCentral = new javax.swing.JPasswordField();
        chkSameCentralConnection = new javax.swing.JCheckBox();
        pnlLocal = new javax.swing.JPanel();
        lblImgLocal = new javax.swing.JLabel();
        spnPortLocal = new javax.swing.JSpinner();
        txtServerNameLocal = new javax.swing.JTextField();
        cboDatabaseTypeLocal = new javax.swing.JComboBox();
        lblDatabaseTypeCentral1 = new javax.swing.JLabel();
        lblServerNameCentral1 = new javax.swing.JLabel();
        lblSErverPortCentral1 = new javax.swing.JLabel();
        lblDatabaseNameLocal = new javax.swing.JLabel();
        txtDatabaseNameLocal = new javax.swing.JTextField();
        txtPaswordLocal = new javax.swing.JPasswordField();
        lblPasswordCentral1 = new javax.swing.JLabel();
        lblUserNameCentral1 = new javax.swing.JLabel();
        txtUserNameLocal = new javax.swing.JTextField();
        btnTestConnectionCentral1 = new javax.swing.JButton();
        lblDMSLocallConnection = new javax.swing.JLabel();
        lblGMSLocalConnection = new javax.swing.JLabel();
        cboGmsLocalDbType = new javax.swing.JComboBox();
        txtServerGMSLocal = new javax.swing.JTextField();
        spnGmsLocal = new javax.swing.JSpinner();
        txtDbnameGmsLocal = new javax.swing.JTextField();
        txtUserNameGmsLocal = new javax.swing.JTextField();
        btnTestGMSLocallConnection = new javax.swing.JButton();
        txtPasswordGMSLocal = new javax.swing.JPasswordField();
        chkSameLocalConnection = new javax.swing.JCheckBox();
        chkReadIniFile = new javax.swing.JCheckBox();

        pnlCentral.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.pnlCentral.border.title"))); // NOI18N

        lblServerNameCentral.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(lblServerNameCentral, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.lblServerNameCentral.text")); // NOI18N

        lblDatabaseTypeCentral.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(lblDatabaseTypeCentral, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.lblDatabaseTypeCentral.text")); // NOI18N

        cboDatabaseTypeCentral.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboDatabaseTypeCentral.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboDatabaseTypeCentralItemStateChanged(evt);
            }
        });

        txtServerNameCentral.setText(org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.txtServerNameCentral.text")); // NOI18N
        txtServerNameCentral.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtServerNameCentralKeyReleased(evt);
            }
        });

        lblSErverPortCentral.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(lblSErverPortCentral, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.lblSErverPortCentral.text")); // NOI18N

        spnPortCentral.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(3306), null, null, Integer.valueOf(1)));
        spnPortCentral.setEditor(new javax.swing.JSpinner.NumberEditor(spnPortCentral, "####"));
        spnPortCentral.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnPortCentralStateChanged(evt);
            }
        });

        lblUserNameCentral.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(lblUserNameCentral, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.lblUserNameCentral.text")); // NOI18N

        txtUserNameCentral.setText(org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.txtUserNameCentral.text")); // NOI18N
        txtUserNameCentral.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUserNameCentralKeyReleased(evt);
            }
        });

        lblPasswordCentral.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(lblPasswordCentral, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.lblPasswordCentral.text")); // NOI18N

        txtPaswordCentral.setText(org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.txtPaswordCentral.text")); // NOI18N
        txtPaswordCentral.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPaswordCentralKeyReleased(evt);
            }
        });

        lblDatabaseNameCentral.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(lblDatabaseNameCentral, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.lblDatabaseNameCentral.text")); // NOI18N

        txtDatabaseNameCentral.setText(org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.txtDatabaseNameCentral.text")); // NOI18N
        txtDatabaseNameCentral.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDatabaseNameCentralKeyReleased(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnTestConnectionCentral, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.btnTestConnectionCentral.text")); // NOI18N
        btnTestConnectionCentral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestConnectionCentralActionPerformed(evt);
            }
        });

        btnImportFromIniFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/cimmyt/cril/ibwb/db/options/ini.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnImportFromIniFile, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.btnImportFromIniFile.text")); // NOI18N
        btnImportFromIniFile.setToolTipText(org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.btnImportFromIniFile.toolTipText")); // NOI18N
        btnImportFromIniFile.setEnabled(false);
        btnImportFromIniFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportFromIniFileActionPerformed(evt);
            }
        });

        lblImgCentral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/cimmyt/cril/ibwb/db/options/barra.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblImgCentral, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.lblImgCentral.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblDMSCentralConnection, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.lblDMSCentralConnection.text")); // NOI18N

        cboGmsCentralDbType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboGmsCentralDbType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboGmsCentralDbTypeItemStateChanged(evt);
            }
        });

        txtServerGMSCentral.setText(org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.txtServerGMSCentral.text")); // NOI18N

        spnGmsCentral.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(3306), null, null, Integer.valueOf(1)));
        spnGmsCentral.setEditor(new javax.swing.JSpinner.NumberEditor(spnGmsCentral, "####"));

        txtDbnameGmsCentral.setText(org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.txtDbnameGmsCentral.text")); // NOI18N

        txtUserNameGmsCentral.setText(org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.txtUserNameGmsCentral.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnTestGMSCentralConnection, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.btnTestGMSCentralConnection.text")); // NOI18N
        btnTestGMSCentralConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestGMSCentralConnectionActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lblGMSCentralConnection, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.lblGMSCentralConnection.text")); // NOI18N

        txtPasswordGMSCentral.setText(org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.txtPasswordGMSCentral.text")); // NOI18N

        chkSameCentralConnection.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(chkSameCentralConnection, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.chkSameCentralConnection.text")); // NOI18N

        javax.swing.GroupLayout pnlCentralLayout = new javax.swing.GroupLayout(pnlCentral);
        pnlCentral.setLayout(pnlCentralLayout);
        pnlCentralLayout.setHorizontalGroup(
            pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCentralLayout.createSequentialGroup()
                .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCentralLayout.createSequentialGroup()
                        .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pnlCentralLayout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(lblUserNameCentral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlCentralLayout.createSequentialGroup()
                                .addComponent(lblImgCentral)
                                .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnlCentralLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(lblDatabaseNameCentral, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblDatabaseTypeCentral, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblSErverPortCentral, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblServerNameCentral, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)))
                                    .addGroup(pnlCentralLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblPasswordCentral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(spnPortCentral, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUserNameCentral)
                            .addComponent(txtPaswordCentral, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                            .addComponent(txtDatabaseNameCentral)
                            .addComponent(txtServerNameCentral, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(cboDatabaseTypeCentral, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTestConnectionCentral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlCentralLayout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(lblDMSCentralConnection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46)
                .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spnGmsCentral, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCentralLayout.createSequentialGroup()
                        .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtPasswordGMSCentral, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUserNameGmsCentral, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDbnameGmsCentral, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtServerGMSCentral, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTestGMSCentralConnection, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboGmsCentralDbType, javax.swing.GroupLayout.Alignment.LEADING, 0, 144, Short.MAX_VALUE)
                            .addComponent(lblGMSCentralConnection, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkSameCentralConnection)
                            .addComponent(btnImportFromIniFile, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        pnlCentralLayout.setVerticalGroup(
            pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCentralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCentralLayout.createSequentialGroup()
                        .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDMSCentralConnection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGMSCentralConnection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblImgCentral)
                            .addGroup(pnlCentralLayout.createSequentialGroup()
                                .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlCentralLayout.createSequentialGroup()
                                        .addComponent(lblDatabaseTypeCentral)
                                        .addGap(77, 77, 77)
                                        .addComponent(lblDatabaseNameCentral)
                                        .addGap(11, 11, 11)
                                        .addComponent(lblUserNameCentral, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlCentralLayout.createSequentialGroup()
                                        .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cboDatabaseTypeCentral, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboGmsCentralDbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlCentralLayout.createSequentialGroup()
                                                .addGap(11, 11, 11)
                                                .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(txtServerNameCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblServerNameCentral))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(spnPortCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblSErverPortCentral, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(pnlCentralLayout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(txtServerGMSCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(chkSameCentralConnection))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(spnGmsCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(9, 9, 9)
                                        .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtDatabaseNameCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDbnameGmsCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtUserNameCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtUserNameGmsCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPaswordCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPasswordCentral)
                                    .addComponent(txtPasswordGMSCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCentralLayout.createSequentialGroup()
                                .addComponent(btnTestConnectionCentral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(12, 12, 12))
                            .addGroup(pnlCentralLayout.createSequentialGroup()
                                .addComponent(btnTestGMSCentralConnection)
                                .addContainerGap())))
                    .addGroup(pnlCentralLayout.createSequentialGroup()
                        .addComponent(btnImportFromIniFile, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pnlLocal.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.pnlLocal.border.title"))); // NOI18N

        lblImgLocal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/cimmyt/cril/ibwb/db/options/barra2.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblImgLocal, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.lblImgLocal.text")); // NOI18N

        spnPortLocal.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(3306), null, null, Integer.valueOf(1)));
        spnPortLocal.setEditor(new javax.swing.JSpinner.NumberEditor(spnPortLocal, "####"));
        spnPortLocal.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnPortLocalStateChanged(evt);
            }
        });

        txtServerNameLocal.setText(org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.txtServerNameLocal.text")); // NOI18N
        txtServerNameLocal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtServerNameLocalKeyReleased(evt);
            }
        });

        cboDatabaseTypeLocal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboDatabaseTypeLocal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboDatabaseTypeLocalItemStateChanged(evt);
            }
        });
        cboDatabaseTypeLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDatabaseTypeLocalActionPerformed(evt);
            }
        });

        lblDatabaseTypeCentral1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(lblDatabaseTypeCentral1, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.lblDatabaseTypeCentral1.text")); // NOI18N

        lblServerNameCentral1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(lblServerNameCentral1, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.lblServerNameCentral1.text")); // NOI18N

        lblSErverPortCentral1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(lblSErverPortCentral1, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.lblSErverPortCentral1.text")); // NOI18N

        lblDatabaseNameLocal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(lblDatabaseNameLocal, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.lblDatabaseNameLocal.text")); // NOI18N

        txtDatabaseNameLocal.setText(org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.txtDatabaseNameLocal.text")); // NOI18N
        txtDatabaseNameLocal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDatabaseNameLocalKeyReleased(evt);
            }
        });

        txtPaswordLocal.setText(org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.txtPaswordLocal.text")); // NOI18N
        txtPaswordLocal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPaswordLocalKeyReleased(evt);
            }
        });

        lblPasswordCentral1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(lblPasswordCentral1, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.lblPasswordCentral1.text")); // NOI18N

        lblUserNameCentral1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(lblUserNameCentral1, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.lblUserNameCentral1.text")); // NOI18N

        txtUserNameLocal.setText(org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.txtUserNameLocal.text")); // NOI18N
        txtUserNameLocal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUserNameLocalKeyReleased(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnTestConnectionCentral1, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.btnTestConnectionCentral1.text")); // NOI18N
        btnTestConnectionCentral1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestConnectionCentral1ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lblDMSLocallConnection, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.lblDMSLocallConnection.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblGMSLocalConnection, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.lblGMSLocalConnection.text")); // NOI18N

        cboGmsLocalDbType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboGmsLocalDbType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboGmsLocalDbTypeItemStateChanged(evt);
            }
        });

        txtServerGMSLocal.setText(org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.txtServerGMSLocal.text")); // NOI18N

        spnGmsLocal.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(3306), null, null, Integer.valueOf(1)));
        spnGmsLocal.setEditor(new javax.swing.JSpinner.NumberEditor(spnGmsLocal, "####"));

        txtDbnameGmsLocal.setText(org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.txtDbnameGmsLocal.text")); // NOI18N

        txtUserNameGmsLocal.setText(org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.txtUserNameGmsLocal.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnTestGMSLocallConnection, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.btnTestGMSLocallConnection.text")); // NOI18N
        btnTestGMSLocallConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestGMSLocallConnectionActionPerformed(evt);
            }
        });

        txtPasswordGMSLocal.setText(org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.txtPasswordGMSLocal.text")); // NOI18N

        chkSameLocalConnection.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(chkSameLocalConnection, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.chkSameLocalConnection.text")); // NOI18N

        javax.swing.GroupLayout pnlLocalLayout = new javax.swing.GroupLayout(pnlLocal);
        pnlLocal.setLayout(pnlLocalLayout);
        pnlLocalLayout.setHorizontalGroup(
            pnlLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLocalLayout.createSequentialGroup()
                .addComponent(lblImgLocal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblPasswordCentral1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblUserNameCentral1)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlLocalLayout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(lblSErverPortCentral1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(lblDatabaseNameLocal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlLocalLayout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addComponent(lblServerNameCentral1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(lblDatabaseTypeCentral1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(pnlLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLocalLayout.createSequentialGroup()
                        .addComponent(lblDMSLocallConnection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105))
                    .addGroup(pnlLocalLayout.createSequentialGroup()
                        .addGroup(pnlLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDatabaseNameLocal)
                            .addComponent(spnPortLocal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUserNameLocal, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtServerNameLocal, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPaswordLocal, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboDatabaseTypeLocal, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTestConnectionCentral1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(47, 47, 47)))
                .addGroup(pnlLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPasswordGMSLocal)
                    .addComponent(lblGMSLocalConnection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboGmsLocalDbType, 0, 143, Short.MAX_VALUE)
                    .addComponent(btnTestGMSLocallConnection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtServerGMSLocal)
                    .addComponent(txtDbnameGmsLocal)
                    .addComponent(txtUserNameGmsLocal)
                    .addComponent(spnGmsLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkSameLocalConnection)
                .addGap(25, 25, 25))
        );
        pnlLocalLayout.setVerticalGroup(
            pnlLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLocalLayout.createSequentialGroup()
                .addComponent(lblImgLocal)
                .addGap(58, 58, 58))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLocalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDMSLocallConnection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGMSLocalConnection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDatabaseTypeCentral1)
                    .addComponent(cboDatabaseTypeLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboGmsLocalDbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblServerNameCentral1)
                    .addComponent(txtServerNameLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtServerGMSLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkSameLocalConnection))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(spnPortLocal)
                        .addComponent(spnGmsLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblSErverPortCentral1))
                .addGap(4, 4, 4)
                .addGroup(pnlLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDatabaseNameLocal)
                    .addComponent(txtDatabaseNameLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDbnameGmsLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUserNameLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUserNameCentral1)
                    .addComponent(txtUserNameGmsLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPaswordLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPasswordCentral1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPasswordGMSLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTestConnectionCentral1)
                    .addComponent(btnTestGMSLocallConnection))
                .addContainerGap())
        );

        pnlLocalLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboDatabaseTypeLocal, txtServerNameLocal});

        pnlLocalLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblSErverPortCentral1, spnPortLocal});

        org.openide.awt.Mnemonics.setLocalizedText(chkReadIniFile, org.openide.util.NbBundle.getMessage(DatabaseConnectionSettingsPanel.class, "DatabaseConnectionSettingsPanel.chkReadIniFile.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkReadIniFile)
                    .addComponent(pnlLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(chkReadIniFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(pnlCentral, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboDatabaseTypeCentralItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboDatabaseTypeCentralItemStateChanged
        checkCentralDatabaseType();
    }//GEN-LAST:event_cboDatabaseTypeCentralItemStateChanged

    private void cboDatabaseTypeLocalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboDatabaseTypeLocalItemStateChanged
        checkLocalDatabaseType();
    }//GEN-LAST:event_cboDatabaseTypeLocalItemStateChanged

    private void btnTestConnectionCentralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestConnectionCentralActionPerformed
        checkCentralDatabaseConnection(true);
    }//GEN-LAST:event_btnTestConnectionCentralActionPerformed

    private void btnTestConnectionCentral1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestConnectionCentral1ActionPerformed
        checkLocalDatabaseConnection(true);
    }//GEN-LAST:event_btnTestConnectionCentral1ActionPerformed

    private void btnImportFromIniFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportFromIniFileActionPerformed
        importFromINIFile();
    }//GEN-LAST:event_btnImportFromIniFileActionPerformed

    private void cboDatabaseTypeLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDatabaseTypeLocalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboDatabaseTypeLocalActionPerformed

    private void cboGmsCentralDbTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboGmsCentralDbTypeItemStateChanged
        checkGMSCentralDatabaseType();
    }//GEN-LAST:event_cboGmsCentralDbTypeItemStateChanged

    private void cboGmsLocalDbTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboGmsLocalDbTypeItemStateChanged
        checkGMSLocalDatabaseType();
    }//GEN-LAST:event_cboGmsLocalDbTypeItemStateChanged

    private void btnTestGMSCentralConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestGMSCentralConnectionActionPerformed
        checkGMSCentralConnection(true);
    }//GEN-LAST:event_btnTestGMSCentralConnectionActionPerformed

    private void btnTestGMSLocallConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestGMSLocallConnectionActionPerformed
        checkGMSLocalConnection(true);
    }//GEN-LAST:event_btnTestGMSLocallConnectionActionPerformed

    private void txtServerNameCentralKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtServerNameCentralKeyReleased
        if (chkSameCentralConnection.isSelected()) {
            txtServerGMSCentral.setText(txtServerNameCentral.getText());
        }
    }//GEN-LAST:event_txtServerNameCentralKeyReleased

    private void spnPortCentralStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnPortCentralStateChanged
        if (chkSameCentralConnection.isSelected()) {
            spnGmsCentral.setValue(spnPortCentral.getValue());
        }
    }//GEN-LAST:event_spnPortCentralStateChanged

    private void txtDatabaseNameCentralKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDatabaseNameCentralKeyReleased
        if (chkSameCentralConnection.isSelected()) {
            txtDbnameGmsCentral.setText(txtDatabaseNameCentral.getText());
        }
    }//GEN-LAST:event_txtDatabaseNameCentralKeyReleased

    private void txtUserNameCentralKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserNameCentralKeyReleased
        if (chkSameCentralConnection.isSelected()) {
            txtUserNameGmsCentral.setText(txtUserNameCentral.getText());
        }
    }//GEN-LAST:event_txtUserNameCentralKeyReleased

    private void txtPaswordCentralKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaswordCentralKeyReleased
        if (chkSameCentralConnection.isSelected()) {
            String password = new String(txtPaswordCentral.getPassword());
            txtPasswordGMSCentral.setText(password);
        }
    }//GEN-LAST:event_txtPaswordCentralKeyReleased

    private void txtServerNameLocalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtServerNameLocalKeyReleased
        if (chkSameLocalConnection.isSelected()) {
            txtServerGMSLocal.setText(txtServerNameLocal.getText());
        }
    }//GEN-LAST:event_txtServerNameLocalKeyReleased

    private void spnPortLocalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnPortLocalStateChanged
        if (chkSameLocalConnection.isSelected()) {
            spnGmsLocal.setValue(spnPortLocal.getValue());
        }
    }//GEN-LAST:event_spnPortLocalStateChanged

    private void txtDatabaseNameLocalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDatabaseNameLocalKeyReleased
        if (chkSameLocalConnection.isSelected()) {
            txtDbnameGmsLocal.setText(txtDatabaseNameLocal.getText());
        }
    }//GEN-LAST:event_txtDatabaseNameLocalKeyReleased

    private void txtUserNameLocalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserNameLocalKeyReleased
        if (chkSameLocalConnection.isSelected()) {
            txtUserNameGmsLocal.setText(txtUserNameLocal.getText());
        }
    }//GEN-LAST:event_txtUserNameLocalKeyReleased

    private void txtPaswordLocalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaswordLocalKeyReleased
        if (chkSameLocalConnection.isSelected()) {
            String password = new String(txtPaswordLocal.getPassword());
            txtPasswordGMSLocal.setText(password);
        }
    }//GEN-LAST:event_txtPaswordLocalKeyReleased

    /**
     * Checks selected database type to enable/disable fields
     */
    private void checkCentralDatabaseType() {
        DatabaseType selectedDB = (DatabaseType) cboDatabaseTypeCentral.getSelectedItem();
        if (selectedDB != null) {
            if (selectedDB.equals(DatabaseType.MYSQL)) {
                btnImportFromIniFile.setEnabled(false);
            } else {

                btnImportFromIniFile.setEnabled(true);
            }
        }

        checkDataBaseType(cboDatabaseTypeCentral, txtServerNameCentral, txtDatabaseNameCentral,
                txtUserNameCentral, txtPaswordCentral, spnPortCentral, lblDatabaseNameCentral);

        cboDatabaseTypeLocal.setSelectedItem(selectedDB);
        cboGmsCentralDbType.setSelectedItem(selectedDB);
        cboGmsLocalDbType.setSelectedItem(selectedDB);
    }

    /**
     * Checks selected database type to enable/disable fields
     */
    private void checkLocalDatabaseType() {
        checkDataBaseType(cboDatabaseTypeLocal, txtServerNameLocal, txtDatabaseNameLocal,
                txtUserNameLocal, txtPaswordLocal, spnPortLocal, lblDatabaseNameLocal);
    }

    /**
     * Checks selected database type to enable/disable fields
     */
    private void checkGMSCentralDatabaseType() {
        checkDataBaseType(cboGmsCentralDbType, txtServerGMSCentral, txtDbnameGmsCentral,
                txtUserNameGmsCentral, txtPasswordGMSCentral, spnGmsCentral, lblDatabaseNameLocal);
    }

    /**
     * Checks selected database type to enable/disable fields
     */
    private void checkGMSLocalDatabaseType() {
        checkDataBaseType(cboGmsLocalDbType, txtServerGMSLocal, txtDbnameGmsLocal,
                txtUserNameGmsLocal, txtPasswordGMSLocal, spnGmsLocal, lblDatabaseNameLocal);
    }

    /**
     * Validates if can connect to central database using filled data in fields
     * @param showDialog if Dialog message should appear
     * @return <code>true</code> if connection is success, <code>false</code> if fails
     */
    private boolean checkCentralDatabaseConnection(boolean showDialog) {
        DatabaseType selectedDB = (DatabaseType) cboDatabaseTypeCentral.getSelectedItem();
        String url = getUrl(cboDatabaseTypeCentral, txtServerNameCentral, spnPortCentral, txtDatabaseNameCentral);

        String userName = txtUserNameCentral.getText();
        String password = new String(txtPaswordCentral.getPassword());

        return checkDatabaseConnection(selectedDB, url, userName, password, "DMS Central", showDialog);
    }

    /**
     * Validates if can connect to local database using filled data in fields
     * @param showDialog if Dialog message should appear
     * @return <code>true</code> if connection is success, <code>false</code> if fails
     */
    private boolean checkLocalDatabaseConnection(boolean showDialog) {
        DatabaseType selectedDB = (DatabaseType) cboDatabaseTypeLocal.getSelectedItem();
        String url = getUrl(cboDatabaseTypeLocal, txtServerNameLocal, spnPortLocal, txtDatabaseNameLocal);

        String userName = txtUserNameLocal.getText();
        String password = new String(txtPaswordLocal.getPassword());

        return checkDatabaseConnection(selectedDB, url, userName, password, "DMS Local", showDialog);

    }

    /**
     * Validates if can connect to local database using filled data in fields
     * @param showDialog if Dialog message should appear
     * @return <code>true</code> if connection is success, <code>false</code> if fails
     */
    private boolean checkGMSLocalConnection(boolean showDialog) {
        DatabaseType selectedDB = (DatabaseType) cboGmsLocalDbType.getSelectedItem();
        String url = getUrl(cboGmsLocalDbType, txtServerGMSLocal, spnGmsLocal, txtDbnameGmsLocal);

        String userName = txtUserNameLocal.getText();
        String password = new String(txtPaswordLocal.getPassword());

        return checkDatabaseConnection(selectedDB, url, userName, password, "GMS Local", showDialog);

    }

    /**
     * Validates if can connect to local database using filled data in fields
     * @param showDialog if Dialog message should appear
     * @return <code>true</code> if connection is success, <code>false</code> if fails
     */
    private boolean checkGMSCentralConnection(boolean showDialog) {
        DatabaseType selectedDB = (DatabaseType) cboGmsCentralDbType.getSelectedItem();
        String url = getUrl(cboGmsCentralDbType, txtServerGMSCentral, spnGmsCentral, txtDbnameGmsCentral);

        String userName = txtUserNameGmsCentral.getText();
        String password = new String(txtPasswordGMSCentral.getPassword());

        return checkDatabaseConnection(selectedDB, url, userName, password, "GMS Central", showDialog);

    }

    /**
     * Enable / Disable fields depending on Database Type
     * @param cboDbType
     * @param serverName
     * @param databaseName
     * @param userName
     * @param password
     * @param port
     * @param lblDatabase 
     */
    private void checkDataBaseType(JComboBox cboDbType, JTextField serverName,
            JTextField databaseName,
            JTextField userName, JTextField password, JSpinner port,
            JLabel lblDatabase) {
        DatabaseType selectedDB = (DatabaseType) cboDbType.getSelectedItem();
        if (selectedDB != null) {
            Boolean enableEditing = selectedDB.equals(DatabaseType.MYSQL);
            serverName.setEditable(enableEditing);
            userName.setEditable(enableEditing);
            password.setEditable(enableEditing);
            port.setEnabled(enableEditing);
            if (selectedDB.equals(DatabaseType.MYSQL)) {
                lblDatabase.setText(bundle.getString("DatabaseConnectionSettingsPanel.lblDatabaseNameText"));
            } else {
                lblDatabase.setText(bundle.getString("DatabaseConnectionSettingsPanel.lblDataSourceNameText"));

            }
        }
        serverName.setText("");
        userName.setText("");
        databaseName.setText("");
        password.setText("");
    }

    /**
     * Gets the URL connection using fields provided
     * @param cboDbType
     * @param serverName
     * @param port
     * @param databaseName
     * @return 
     */
    public String getUrl(JComboBox cboDbType, JTextField serverName, JSpinner port,
            JTextField databaseName) {
        DatabaseType selectedDB = (DatabaseType) cboDbType.getSelectedItem();
        DatabaseInfo dbInfo = ConnectionUtils.getDatabaseInfo(selectedDB);
        StringBuilder url = new StringBuilder();
        url.append(dbInfo.getJdbcPrefix());

        if (selectedDB.equals(DatabaseType.MYSQL)) {
            url.append(serverName.getText());
            url.append(":").append(port.getValue());
            url.append("/").append(databaseName.getText());
        } else {
            url.append(databaseName.getText());
        }
        return url.toString();

    }

    /**
     * Checks if can connect to a database
     * @param databaseType Database Type to connect
     * @param url URL database
     * @param userName user name for database
     * @param password password
     * @param database database name 
     * @param showDialog if true show a Dialog box with result
     * @return  <code>true</code> if connection is success, <code>false</code> if fails
     */
    private boolean checkDatabaseConnection(DatabaseType databaseType, String url, String userName, String password, String database, boolean showDialog) {
        boolean connectionSuccess = false;
        NotifyDescriptor nd = null;
        if (ConnectionUtils.connectionSuccess(databaseType, url, userName, password)) {
            
            nd = new NotifyDescriptor.Message(database +bundle.getString("DatabaseConnectionSettingsPanel.connectionSuccess"));
            connectionSuccess = true;
        } else {
            
            nd = new NotifyDescriptor.Message(bundle.getString("DatabaseConnectionSettingsPanel.cannotConnectTo") + database + bundle.getString("DatabaseConnectionSettingsPanel.database"), NotifyDescriptor.ERROR_MESSAGE);
        }
        if (showDialog) {
            DialogDisplayer.getDefault().notify(nd);
        }
        return connectionSuccess;
    }

    /**
     * Import parameters from INI FILE
     */
    private void importFromINIFile() {
        JFileChooser fileChooser = new JFileChooser();


        fileChooser.setFileFilter(new FileFilterImpl());
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if (fileChooser.showOpenDialog(null) == JFileChooser.CANCEL_OPTION) {
            return;
        }

        try {
            Ini iniFile = new Ini();
            iniFile.load(fileChooser.getSelectedFile());

            Ini.Section sectionDMSCentral = iniFile.get("Central DMS");
            String dmsCentral = sectionDMSCentral.get("DSN");

            Ini.Section sectionDMSLocal = iniFile.get("Local DMS");
            String dmsLocal = sectionDMSLocal.get("DSN");

            Ini.Section sectionGMSCentral = iniFile.get("Central GMS");
            String gmsCentral = sectionGMSCentral.get("DSN");

            Ini.Section sectionGMSLocal = iniFile.get("Local GMS");
            String gmsLocal = sectionGMSLocal.get("DSN");

            txtDatabaseNameCentral.setText(dmsCentral);
            txtDatabaseNameLocal.setText(dmsLocal);
            txtDbnameGmsCentral.setText(gmsCentral);
            txtDbnameGmsLocal.setText(gmsLocal);

        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

    }

    /**
     * Filter class to read only .ini files
     */
    private static class FileFilterImpl extends FileFilter {

        public FileFilterImpl() {
        }

        @Override
        public boolean accept(File f) {
            return f.getName().toLowerCase().endsWith(".ini") || f.isDirectory();
        }

        @Override
        public String getDescription() {
            return "INI FILE (.ini)";
        }
    }

    void load() {


        InstalledFileLocator locator = InstalledFileLocator.getDefault();
        File databaseConfigFile = locator.locate("modules/ext/databaseconfig.properties", "org.cimmyt.cril.ibwb.provider", false);
        Properties config = new Properties();
        try {

            InputStream streamProperties = new FileInputStream(databaseConfigFile);
            config.load(streamProperties);


            if (config.getProperty("dmscentral.driverclassname").equals("sun.jdbc.odbc.JdbcOdbcDriver")) {
                cboDatabaseTypeCentral.setSelectedItem(DatabaseType.ACCESS);
                String databaseName = config.getProperty("dmscentral.url");
                int index = databaseName.lastIndexOf(":");
                databaseName = databaseName.substring(index + 1);
                txtDatabaseNameCentral.setText(databaseName);
            } else {
                cboDatabaseTypeCentral.setSelectedItem(DatabaseType.MYSQL);
                String centralUrl = config.getProperty("dmscentral.url");
                DatabaseInfo databaseInfo = ConnectionUtils.getDatabaseInfo(centralUrl);
                txtServerNameCentral.setText(databaseInfo.getServerName());
                spnPortCentral.getModel().setValue(Integer.valueOf(databaseInfo.getPort()));
                txtDatabaseNameCentral.setText(databaseInfo.getDatabaseName());
            }

            if (config.getProperty("gmscentral.driverclassname").equals("sun.jdbc.odbc.JdbcOdbcDriver")) {
                cboGmsCentralDbType.setSelectedItem(DatabaseType.ACCESS);
                String databaseName = config.getProperty("gmscentral.url");
                int index = databaseName.lastIndexOf(":");
                databaseName = databaseName.substring(index + 1);
                txtDbnameGmsCentral.setText(databaseName);
            } else {
                cboGmsCentralDbType.setSelectedItem(DatabaseType.MYSQL);
                String centralUrl = config.getProperty("gmscentral.url");
                DatabaseInfo databaseInfo = ConnectionUtils.getDatabaseInfo(centralUrl);
                txtServerGMSCentral.setText(databaseInfo.getServerName());
                spnGmsCentral.getModel().setValue(Integer.valueOf(databaseInfo.getPort()));
                txtDbnameGmsCentral.setText(databaseInfo.getDatabaseName());
            }

            if (config.getProperty("dmslocal.driverclassname").equals("sun.jdbc.odbc.JdbcOdbcDriver")) {
                cboDatabaseTypeLocal.setSelectedItem(DatabaseType.ACCESS);
                String databaseName = config.getProperty("dmslocal.url");
                int index = databaseName.lastIndexOf(":");
                databaseName = databaseName.substring(index + 1);
                txtDatabaseNameLocal.setText(databaseName);
            } else {
                cboDatabaseTypeLocal.setSelectedItem(DatabaseType.MYSQL);
                String localUrl = config.getProperty("dmslocal.url");
                DatabaseInfo databaseInfo = ConnectionUtils.getDatabaseInfo(localUrl);
                txtServerNameLocal.setText(databaseInfo.getServerName());
                spnPortLocal.getModel().setValue(Integer.valueOf(databaseInfo.getPort()));
                txtDatabaseNameLocal.setText(databaseInfo.getDatabaseName());
            }

            if (config.getProperty("gmslocal.driverclassname").equals("sun.jdbc.odbc.JdbcOdbcDriver")) {
                cboGmsLocalDbType.setSelectedItem(DatabaseType.ACCESS);
                String databaseName = config.getProperty("gmslocal.url");
                int index = databaseName.lastIndexOf(":");
                databaseName = databaseName.substring(index + 1);
                txtDbnameGmsLocal.setText(databaseName);
            } else {
                cboDatabaseTypeLocal.setSelectedItem(DatabaseType.MYSQL);
                String localUrl = config.getProperty("gmslocal.url");
                DatabaseInfo databaseInfo = ConnectionUtils.getDatabaseInfo(localUrl);
                txtServerGMSLocal.setText(databaseInfo.getServerName());
                spnGmsLocal.getModel().setValue(Integer.valueOf(databaseInfo.getPort()));
                txtDbnameGmsLocal.setText(databaseInfo.getDatabaseName());
            }


            txtUserNameCentral.setText(config.getProperty("dmscentral.username"));
            txtPaswordCentral.setText(config.getProperty("dmscentral.password"));

            txtUserNameGmsCentral.setText(config.getProperty("gmscentral.username"));
            txtPasswordGMSCentral.setText(config.getProperty("gmscentral.password"));


            txtUserNameLocal.setText(config.getProperty("dmslocal.username"));
            txtPaswordLocal.setText(config.getProperty("dmslocal.password"));

            txtUserNameGmsLocal.setText(config.getProperty("gmslocal.username"));
            txtPasswordGMSLocal.setText(config.getProperty("gmslocal.password"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void store() {
        if (canStoreConfigFile()) {

            String passwordCentral = new String(txtPaswordCentral.getPassword());
            String passwordLocal = new String(txtPaswordLocal.getPassword());
            String passwordGmsCentral = new String(txtPasswordGMSCentral.getPassword());
            String passwordGmsLocal = new String(txtPasswordGMSLocal.getPassword());

            DatabaseInfo dmsCentral = new DatabaseInfo();
            dmsCentral.setDatabaseType((DatabaseType) cboDatabaseTypeCentral.getSelectedItem());
            dmsCentral.setServerName(txtServerNameCentral.getText());
            dmsCentral.setDatabaseName(txtDatabaseNameCentral.getText());
            dmsCentral.setUserName(txtUserNameCentral.getText());
            dmsCentral.setPort(spnPortCentral.getValue().toString());
            dmsCentral.setPassword(passwordCentral);

            DatabaseInfo gmsCentral = new DatabaseInfo();
            gmsCentral.setDatabaseType((DatabaseType) cboGmsCentralDbType.getSelectedItem());
            gmsCentral.setServerName(txtServerGMSCentral.getText());
            gmsCentral.setDatabaseName(txtDbnameGmsCentral.getText());
            gmsCentral.setUserName(txtUserNameGmsCentral.getText());
            gmsCentral.setPort(spnGmsCentral.getValue().toString());
            gmsCentral.setPassword(passwordGmsCentral);


            DatabaseInfo dmsLocal = new DatabaseInfo();
            dmsLocal.setDatabaseType((DatabaseType) cboDatabaseTypeLocal.getSelectedItem());
            dmsLocal.setServerName(txtServerNameLocal.getText());
            dmsLocal.setDatabaseName(txtDatabaseNameLocal.getText());
            dmsLocal.setUserName(txtUserNameLocal.getText());
            dmsLocal.setPort(spnPortLocal.getValue().toString());
            dmsLocal.setPassword(passwordLocal);

            DatabaseInfo gmsLocal = new DatabaseInfo();
            gmsLocal.setDatabaseType((DatabaseType) cboGmsLocalDbType.getSelectedItem());
            gmsLocal.setServerName(txtServerGMSLocal.getText());
            gmsLocal.setDatabaseName(txtDbnameGmsLocal.getText());
            gmsLocal.setUserName(txtUserNameGmsLocal.getText());
            gmsLocal.setPort(spnGmsLocal.getValue().toString());
            gmsLocal.setPassword(passwordGmsLocal);

            try {

                IBFbConfigFile.saveConfigFile(dmsCentral, gmsCentral, dmsLocal, gmsLocal);

                //LifecycleManager.getDefault().markForRestart();
                NotifyDescriptor nd = null;
                nd = new NotifyDescriptor.Message(bundle.getString("DatabaseConnectionSettingsPanel.needRestartApp"));
                DialogDisplayer.getDefault().notify(nd);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    boolean valid() {
        return checkCentralDatabaseConnection(false) && checkLocalDatabaseConnection(false);
    }

    private boolean canStoreConfigFile() {
        boolean canStoreFile = checkCentralDatabaseConnection(false)
                && checkLocalDatabaseConnection(false)
                && checkGMSCentralConnection(false)
                && checkGMSLocalConnection(false);
        if (!canStoreFile) {
            NotifyDescriptor nd = null;
            nd = new NotifyDescriptor.Message(bundle.getString("DatabaseConnectionSettingsPanel.cannotConnectToDbTryAgain"));
            DialogDisplayer.getDefault().notify(nd);

        }
        return canStoreFile;
    }
    
    public void storeIniFileConfig() {
        if (chkReadIniFile.isSelected()) {
            FieldbookSettings.setSetting(FieldbookSettings.READ_ICIS_INI_FILE, "1");
        } else {
            FieldbookSettings.setSetting(FieldbookSettings.READ_ICIS_INI_FILE, "0");
        }
    }
    
    public void loadIniFileConfig() {
        String readIniFile = FieldbookSettings.getSetting(FieldbookSettings.READ_ICIS_INI_FILE);
        if (readIniFile.equals("1")) {
            chkReadIniFile.setSelected(true);
        } else {
            chkReadIniFile.setSelected(false);
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImportFromIniFile;
    private javax.swing.JButton btnTestConnectionCentral;
    private javax.swing.JButton btnTestConnectionCentral1;
    private javax.swing.JButton btnTestGMSCentralConnection;
    private javax.swing.JButton btnTestGMSLocallConnection;
    private javax.swing.JComboBox cboDatabaseTypeCentral;
    private javax.swing.JComboBox cboDatabaseTypeLocal;
    private javax.swing.JComboBox cboGmsCentralDbType;
    private javax.swing.JComboBox cboGmsLocalDbType;
    private javax.swing.JCheckBox chkReadIniFile;
    private javax.swing.JCheckBox chkSameCentralConnection;
    private javax.swing.JCheckBox chkSameLocalConnection;
    private javax.swing.JLabel lblDMSCentralConnection;
    private javax.swing.JLabel lblDMSLocallConnection;
    private javax.swing.JLabel lblDatabaseNameCentral;
    private javax.swing.JLabel lblDatabaseNameLocal;
    private javax.swing.JLabel lblDatabaseTypeCentral;
    private javax.swing.JLabel lblDatabaseTypeCentral1;
    private javax.swing.JLabel lblGMSCentralConnection;
    private javax.swing.JLabel lblGMSLocalConnection;
    private javax.swing.JLabel lblImgCentral;
    private javax.swing.JLabel lblImgLocal;
    private javax.swing.JLabel lblPasswordCentral;
    private javax.swing.JLabel lblPasswordCentral1;
    private javax.swing.JLabel lblSErverPortCentral;
    private javax.swing.JLabel lblSErverPortCentral1;
    private javax.swing.JLabel lblServerNameCentral;
    private javax.swing.JLabel lblServerNameCentral1;
    private javax.swing.JLabel lblUserNameCentral;
    private javax.swing.JLabel lblUserNameCentral1;
    private javax.swing.JPanel pnlCentral;
    private javax.swing.JPanel pnlLocal;
    private javax.swing.JSpinner spnGmsCentral;
    private javax.swing.JSpinner spnGmsLocal;
    private javax.swing.JSpinner spnPortCentral;
    private javax.swing.JSpinner spnPortLocal;
    private javax.swing.JTextField txtDatabaseNameCentral;
    private javax.swing.JTextField txtDatabaseNameLocal;
    private javax.swing.JTextField txtDbnameGmsCentral;
    private javax.swing.JTextField txtDbnameGmsLocal;
    private javax.swing.JPasswordField txtPasswordGMSCentral;
    private javax.swing.JPasswordField txtPasswordGMSLocal;
    private javax.swing.JPasswordField txtPaswordCentral;
    private javax.swing.JPasswordField txtPaswordLocal;
    private javax.swing.JTextField txtServerGMSCentral;
    private javax.swing.JTextField txtServerGMSLocal;
    private javax.swing.JTextField txtServerNameCentral;
    private javax.swing.JTextField txtServerNameLocal;
    private javax.swing.JTextField txtUserNameCentral;
    private javax.swing.JTextField txtUserNameGmsCentral;
    private javax.swing.JTextField txtUserNameGmsLocal;
    private javax.swing.JTextField txtUserNameLocal;
    // End of variables declaration//GEN-END:variables
}
