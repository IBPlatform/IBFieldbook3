package ibfb.studyexplorer.jdialogs;

import ibfb.domain.core.Study;
import ibfb.studyexplorer.actions.ConfigStudiesAction;
import ibfb.studyexplorer.explorer.StudyExplorerTopComponent;
import ibfb.studyexplorer.wizard.ExplorerWizardWizardIterator;
import ibfb.ui.core.JDExpert;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.ConvertUtils;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.netbeans.validation.api.Problem;
import org.netbeans.validation.api.Problems;
import org.netbeans.validation.api.Validator;
import org.netbeans.validation.api.builtin.Validators;
import org.netbeans.validation.api.ui.ValidationGroup;
import org.netbeans.validation.api.ui.ValidationListener;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;
import org.openide.windows.WindowManager;

public class JDNewSTD extends javax.swing.JDialog {

    private ResourceBundle bundle = NbBundle.getBundle(JDNewSTD.class);
    final static String badchars = "`~!@#$%^&*()_+=\\|\"':;?/>.<, ";
    ValidationGroup group = null;
    ValidationGroup datesGroup = null;
    DocumentListener doc = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent de) {
            checkValidation(group.validateAll());
        }

        @Override
        public void removeUpdate(DocumentEvent de) {
            checkValidation(group.validateAll());
        }

        @Override
        public void changedUpdate(DocumentEvent de) {
            checkValidation(group.validateAll());
        }
    };
    DocumentListener documentDates = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            checkValidation(datesGroup.validateAll());
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            checkValidation(datesGroup.validateAll());
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            checkValidation(datesGroup.validateAll());
        }
    };

    public JDNewSTD(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        assignValidations();
        loadComboValues();
        this.setLocationRelativeTo(null);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextFieldStudy = new javax.swing.JTextField();
        lblStudyTitle = new javax.swing.JLabel();
        lblStudyName = new javax.swing.JLabel();
        jTextFieldTitle = new javax.swing.JTextField();
        jTextFieldObjective = new javax.swing.JTextField();
        lblObjective = new javax.swing.JLabel();
        validationPanel1 = new org.netbeans.validation.api.ui.ValidationPanel();
        jPanel2 = new javax.swing.JPanel();
        lblStudyNumericKey = new javax.swing.JLabel();
        lblEndDate = new javax.swing.JLabel();
        lblStarDate = new javax.swing.JLabel();
        lblStudyType = new javax.swing.JLabel();
        jTextFieldPMKey = new javax.swing.JTextField();
        jComboBoxStudyType = new javax.swing.JComboBox();
        jDateChooserStart = new com.toedter.calendar.JDateChooser();
        jDateChooserEnd = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        validationPanelDates = new org.netbeans.validation.api.ui.ValidationPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButtonCancel = new javax.swing.JButton();
        jButtonOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(JDNewSTD.class, "JDNewSTD.title")); // NOI18N
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setMaximumSize(new java.awt.Dimension(633, 164));

        jTextFieldStudy.setText(org.openide.util.NbBundle.getMessage(JDNewSTD.class, "JDNewSTD.jTextFieldStudy.text")); // NOI18N
        jTextFieldStudy.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldStudyFocusGained(evt);
            }
        });

        lblStudyTitle.setText(org.openide.util.NbBundle.getMessage(JDNewSTD.class, "JDNewSTD.lblStudyTitle.text")); // NOI18N

        lblStudyName.setText(org.openide.util.NbBundle.getMessage(JDNewSTD.class, "JDNewSTD.lblStudyName.text")); // NOI18N

        jTextFieldTitle.setText(org.openide.util.NbBundle.getMessage(JDNewSTD.class, "JDNewSTD.jTextFieldTitle.text")); // NOI18N
        jTextFieldTitle.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldTitleFocusGained(evt);
            }
        });

        jTextFieldObjective.setText(org.openide.util.NbBundle.getMessage(JDNewSTD.class, "JDNewSTD.jTextFieldObjective.text")); // NOI18N
        jTextFieldObjective.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldObjectiveFocusGained(evt);
            }
        });

        lblObjective.setText(org.openide.util.NbBundle.getMessage(JDNewSTD.class, "JDNewSTD.lblObjective.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStudyName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblStudyTitle, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblObjective, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(validationPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                    .addComponent(jTextFieldStudy, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                    .addComponent(jTextFieldTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                    .addComponent(jTextFieldObjective, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldStudy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStudyName))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStudyTitle))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldObjective, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObjective))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(validationPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setMaximumSize(new java.awt.Dimension(627, 215));

        lblStudyNumericKey.setText(org.openide.util.NbBundle.getMessage(JDNewSTD.class, "JDNewSTD.lblStudyNumericKey.text")); // NOI18N
        lblStudyNumericKey.setToolTipText(org.openide.util.NbBundle.getMessage(JDNewSTD.class, "JDNewSTD.lblStudyNumericKey.toolTipText")); // NOI18N

        lblEndDate.setText(org.openide.util.NbBundle.getMessage(JDNewSTD.class, "JDNewSTD.lblEndDate.text")); // NOI18N

        lblStarDate.setText(org.openide.util.NbBundle.getMessage(JDNewSTD.class, "JDNewSTD.lblStarDate.text")); // NOI18N

        lblStudyType.setText(org.openide.util.NbBundle.getMessage(JDNewSTD.class, "JDNewSTD.lblStudyType.text")); // NOI18N

        jTextFieldPMKey.setText(org.openide.util.NbBundle.getMessage(JDNewSTD.class, "JDNewSTD.jTextFieldPMKey.text")); // NOI18N
        jTextFieldPMKey.setToolTipText(org.openide.util.NbBundle.getMessage(JDNewSTD.class, "JDNewSTD.jTextFieldPMKey.toolTipText")); // NOI18N
        jTextFieldPMKey.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldPMKeyKeyTyped(evt);
            }
        });

        jComboBoxStudyType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "International", "National", "Bank", "Foreign" }));

        jDateChooserStart.setDate(new java.util.Date());
        jDateChooserStart.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDateChooserStartFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDateChooserStartFocusLost(evt);
            }
        });

        jDateChooserEnd.setDate(new java.util.Date());
        jDateChooserEnd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jDateChooserEndFocusLost(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyexplorer/images/grass.png"))); // NOI18N
        jLabel2.setText(org.openide.util.NbBundle.getMessage(JDNewSTD.class, "JDNewSTD.jLabel2.text")); // NOI18N
        jLabel2.setToolTipText(org.openide.util.NbBundle.getMessage(JDNewSTD.class, "JDNewSTD.jLabel2.toolTipText")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStarDate, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblEndDate, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblStudyType, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblStudyNumericKey, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldPMKey)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooserEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooserStart, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jComboBoxStudyType, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(91, 91, 91))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(validationPanelDates, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblStarDate)
                    .addComponent(jDateChooserStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooserEnd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEndDate, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxStudyType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStudyType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPMKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStudyNumericKey))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(validationPanelDates, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyexplorer/images/gcp.png"))); // NOI18N
        jLabel1.setText(org.openide.util.NbBundle.getMessage(JDNewSTD.class, "JDNewSTD.jLabel1.text")); // NOI18N
        jLabel1.setToolTipText(org.openide.util.NbBundle.getMessage(JDNewSTD.class, "JDNewSTD.jLabel1.toolTipText")); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setMaximumSize(new java.awt.Dimension(671, 85));

        jButtonCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyexplorer/images/cancel.png"))); // NOI18N
        jButtonCancel.setText(org.openide.util.NbBundle.getMessage(JDNewSTD.class, "JDNewSTD.jButtonCancel.text")); // NOI18N
        jButtonCancel.setToolTipText(org.openide.util.NbBundle.getMessage(JDNewSTD.class, "JDNewSTD.jButtonCancel.toolTipText")); // NOI18N
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jButtonOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyexplorer/images/ok.png"))); // NOI18N
        jButtonOK.setText(org.openide.util.NbBundle.getMessage(JDNewSTD.class, "JDNewSTD.jButtonOK.text")); // NOI18N
        jButtonOK.setToolTipText(org.openide.util.NbBundle.getMessage(JDNewSTD.class, "JDNewSTD.jButtonOK.toolTipText")); // NOI18N
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(jButtonOK, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        closeJDialog();
}//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed
        if (checkValidationForDates()) {
            if (AppServicesProxy.getDefault().appServices().getStudyByName(jTextFieldStudy.getText().trim()) != null) {
                DialogUtil.displayError(JDNewSTD.class, "JDNewStudy.studyAlreadyExists");
            } else {
                this.copyData();
                this.setVisible(false);
                addStudyToExplorer();
            }
        }
    }//GEN-LAST:event_jButtonOKActionPerformed

    private void jTextFieldPMKeyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPMKeyKeyTyped
        char c = evt.getKeyChar();

        if (c == KeyEvent.VK_ENTER) {
            evt.consume();
            return;

        }

        if ((Character.isLetter(c) && !evt.isAltDown())
                || badchars.indexOf(c) > -1) {
            evt.consume();
            return;
        }

        if (jTextFieldPMKey.getText().length() > 6) {

            if (c == 8) {
                super.processKeyEvent(evt);
                return;
            }
            evt.consume();
            return;
        } else {
            super.processKeyEvent(evt);
        }
    }//GEN-LAST:event_jTextFieldPMKeyKeyTyped

    private void jTextFieldTitleFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldTitleFocusGained
        checkValidation(group.validateAll());
    }//GEN-LAST:event_jTextFieldTitleFocusGained

    private void jTextFieldObjectiveFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldObjectiveFocusGained
        checkValidation(group.validateAll());
    }//GEN-LAST:event_jTextFieldObjectiveFocusGained

    private void jTextFieldStudyFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldStudyFocusGained
        checkValidation(group.validateAll());
    }//GEN-LAST:event_jTextFieldStudyFocusGained

    private void jDateChooserStartFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDateChooserStartFocusLost
    }//GEN-LAST:event_jDateChooserStartFocusLost

    private void jDateChooserEndFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDateChooserEndFocusLost
    }//GEN-LAST:event_jDateChooserEndFocusLost

    private void jDateChooserStartFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDateChooserStartFocusGained
    }//GEN-LAST:event_jDateChooserStartFocusGained
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOK;
    public javax.swing.JComboBox jComboBoxStudyType;
    private com.toedter.calendar.JDateChooser jDateChooserEnd;
    private com.toedter.calendar.JDateChooser jDateChooserStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JTextField jTextFieldObjective;
    public javax.swing.JTextField jTextFieldPMKey;
    public javax.swing.JTextField jTextFieldStudy;
    public javax.swing.JTextField jTextFieldTitle;
    private javax.swing.JLabel lblEndDate;
    private javax.swing.JLabel lblObjective;
    private javax.swing.JLabel lblStarDate;
    private javax.swing.JLabel lblStudyName;
    private javax.swing.JLabel lblStudyNumericKey;
    private javax.swing.JLabel lblStudyTitle;
    private javax.swing.JLabel lblStudyType;
    private org.netbeans.validation.api.ui.ValidationPanel validationPanel1;
    private org.netbeans.validation.api.ui.ValidationPanel validationPanelDates;
    // End of variables declaration//GEN-END:variables

    public void copyData() {

        JDNewOptions.studyOBJ.setStudy(this.jTextFieldStudy.getText());
        JDNewOptions.studyOBJ.setTitle(this.jTextFieldTitle.getText());
        JDNewOptions.studyOBJ.setObjective(this.jTextFieldObjective.getText());
        JDNewOptions.studyOBJ.setStarDate(this.jDateChooserStart.getDate());
        JDNewOptions.studyOBJ.setEndDate(this.jDateChooserEnd.getDate());
        JDNewOptions.studyOBJ.setPmkey(this.jTextFieldPMKey.getText());
        JDNewOptions.studyOBJ.setStudyType(this.jComboBoxStudyType.getSelectedItem().toString());

        JDExpert.studyOBJ.setStudy(this.jTextFieldStudy.getText());
        JDExpert.studyOBJ.setTitle(this.jTextFieldTitle.getText());
        JDExpert.studyOBJ.setObjective(this.jTextFieldObjective.getText());
        JDExpert.studyOBJ.setStarDate(this.jDateChooserStart.getDate());
        JDExpert.studyOBJ.setEndDate(this.jDateChooserEnd.getDate());
        JDExpert.studyOBJ.setPmkey(this.jTextFieldPMKey.getText());
        JDExpert.studyOBJ.setStudyType(this.jComboBoxStudyType.getSelectedItem().toString());

    }

    private void addStudyToExplorer() {
        StudyExplorerTopComponent studyExplorer = (StudyExplorerTopComponent) WindowManager.getDefault().findTopComponent("StudyExplorerTopComponent");
        Study study = new Study();
        study.setStudy(jTextFieldStudy.getText());
        study.setTitle(jTextFieldTitle.getText());
        study.setObjective(jTextFieldObjective.getText());
        study.setEndDate(this.jDateChooserEnd.getDate());
        study.setStarDate(this.jDateChooserStart.getDate());
        study.setPmkey(this.jTextFieldPMKey.getText());
        study.setStudyType(this.jComboBoxStudyType.getSelectedItem().toString());
        study.setInstances(0);
        org.cimmyt.cril.ibwb.domain.Study studyDto = new org.cimmyt.cril.ibwb.domain.Study();
        studyDto.setSname(study.getStudy());
        studyDto.setTitle(study.getTitle());
        studyDto.setObjectiv(study.getObjective());
        if (!study.getPmkey().isEmpty()) {
            studyDto.setPmkey(Integer.parseInt(study.getPmkey()));
        }
        studyDto.setSdate(ConvertUtils.getDateAsInteger(study.getStarDate()));
        studyDto.setEdate(ConvertUtils.getDateAsInteger(study.getEndDate()));
        studyDto.setShierarchy(0);
        studyDto.setSstatus(0);
        studyDto.setStype(org.cimmyt.cril.ibwb.domain.Study.STYPE_EXPERIMENT);
        AppServicesProxy.getDefault().appServices().addStudyToLocal(studyDto);

        String selected = NbPreferences.forModule(ConfigStudiesAction.class).get("SELECTED", "");
        selected = selected + "1";
        NbPreferences.forModule(ConfigStudiesAction.class).put("SELECTED", selected);
        
        System.out.println("TAM despues de guardar SELECTED: "+selected.length());

        studyExplorer.refreshStudyBrowser();
    }

    @SuppressWarnings("unchecked")
    private void configPanel() {
        jButtonOK.setEnabled(false);
        group = validationPanel1.getValidationGroup();
        jTextFieldStudy.setName(bundle.getString("JDNewSTD.lblStudyName.text"));
        jTextFieldObjective.setName(bundle.getString("JDNewSTD.lblObjective.text"));
        jTextFieldTitle.setName(bundle.getString("JDNewSTD.lblStudyTitle.text"));
        group.add(jTextFieldStudy, Validators.REQUIRE_NON_EMPTY_STRING, new StudyCheckValidator());
        group.add(jTextFieldObjective, Validators.REQUIRE_NON_EMPTY_STRING);
        group.add(jTextFieldTitle, Validators.REQUIRE_NON_EMPTY_STRING);
        jTextFieldObjective.getDocument().addDocumentListener(doc);
        jTextFieldTitle.getDocument().addDocumentListener(doc);
        jDateChooserStart.setName(bundle.getString("JDNewSTD.lblStarDate.text"));
        jDateChooserEnd.setName(bundle.getString("JDNewSTD.lblEndDate.text"));
        jDateChooserStart.getDateEditor().getUiComponent().setInputVerifier(datesInputVerifier);
        jDateChooserEnd.getDateEditor().getUiComponent().setInputVerifier(datesInputVerifier);
        //datesGroup.add(jDateChooserStart.getDateEditor(),new DateRangeValidator());
        datesGroup = validationPanelDates.getValidationGroup();
        datesGroup.add(new DateValidationListener());
    }

    private void checkValidation(Problem problema) {
        if (problema.isFatal()) {
            jButtonOK.setEnabled(false);
        } else {
            jButtonOK.setEnabled(true);
        }
    }

    public void OpenJDialog() {
        this.jTextFieldStudy.setText("");
        this.jTextFieldObjective.setText("");
        this.jTextFieldTitle.setText("");
        configPanel();
        checkValidation(group.validateAll());
        this.setVisible(true);
    }

    private void closeJDialog() {
        this.jTextFieldStudy.setText("X");
        this.jTextFieldObjective.setText("X");
        this.jTextFieldTitle.setText("X");
        checkValidation(group.validateAll());
        this.setVisible(false);
    }
    private InputVerifier datesInputVerifier = new DatesInputVerifier();

    /**
     * Checks values for star date and end date
     */
    private boolean checkValidationForDates() {
        boolean correctDates = true;
        int compareResult = jDateChooserStart.getDate().compareTo(jDateChooserEnd.getDate());
        if (compareResult > 0) {
            String msg = bundle.getString("JDNewSTD.endDateGreaterStartDate");
//            int msgType = NotifyDescriptor.ERROR_MESSAGE;
//            NotifyDescriptor d = new NotifyDescriptor.Message(msg, msgType);
//            DialogDisplayer.getDefault().notify(d);
            datesGroup.validateAll();
            correctDates = false;
            jDateChooserStart.getDateEditor().getUiComponent().requestFocus();
        }
        return correctDates;
    }

    private void assignValidations() {
    }

    private void loadComboValues() {
        if (bundle.getString("JDNewSTD.language").equals("English")) {
            String[] englishTypes = {"International", "National", "Foreign", "Miscellaneous"};
            jComboBoxStudyType.setModel(new DefaultComboBoxModel(englishTypes));
        } else {
            String[] spanishTypes = {"Internacional", "Nacional", "Foráneo", "Misceláneo"};
            jComboBoxStudyType.setModel(new DefaultComboBoxModel(spanishTypes));
        }
    }

    private class DatesInputVerifier extends InputVerifier {

        public DatesInputVerifier() {
        }

        @Override
        public boolean verify(JComponent input) {

            return checkValidationForDates();
        }
    }

    private class StudyCheckValidator implements Validator<String> {

        public boolean validate(Problems problems, String compName, String model) {
            boolean result = true;

            if (!jTextFieldStudy.getText().isEmpty()) {
                if (AppServicesProxy.getDefault().appServices().getStudyByName(jTextFieldStudy.getText().trim()) != null) {
                    //DialogUtil.displayError(JDNewSTD.class, "JDNewStudy.studyAlreadyExists");
                    String message = NbBundle.getMessage(JDNewSTD.class, "JDNewStudy.studyAlreadyExists");
                    problems.add(message);
                    result = false;
                }
            }
            return result;
        }
    }

    private class DateValidationListener extends ValidationListener {

        @Override
        protected boolean validate(Problems problems) {
            boolean correctDates = true;
            int compareResult = jDateChooserStart.getDate().compareTo(jDateChooserEnd.getDate());
            if (compareResult > 0) {
                String msg = bundle.getString("JDNewSTD.endDateGreaterStartDate");
                correctDates = false;
                //jDateChooserStart.getDateEditor().getUiComponent().requestFocus();
                problems.add(msg);
            } else {
                problems.add(Problem.NO_PROBLEM);
            }
            return correctDates;
        }
    }
}
