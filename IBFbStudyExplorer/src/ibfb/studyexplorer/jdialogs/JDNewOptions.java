package ibfb.studyexplorer.jdialogs;

import ibfb.domain.core.SelectedStudy;
import ibfb.domain.core.Study;
import ibfb.nursery.actions.QuickNurseryCreationAction;
import ibfb.nursery.core.NurseryEditorTopComponent;
import ibfb.nursery.mainwizard.NurseryWizardIterator;
import ibfb.studyeditor.core.StudyEditorTopComponent;
import ibfb.studyeditor.wizard.TrialWizardWizardIterator;
import ibfb.ui.core.JDExpert;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dialog;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.util.Exceptions;
import org.openide.util.Mutex;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

public class JDNewOptions extends javax.swing.JDialog {

    public static JDNewSTD newTrial = new JDNewSTD(null, true);
    public static Study studyOBJ = new Study();
    public static JDExpert expertForm = new JDExpert(null, true);
    public static boolean seQuitoFactores = false;
    private int opcion = 0;

    /** Creates new form JDNewStudy */
    public JDNewOptions(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.getRootPane().setDefaultButton(this.jButtonOK);
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupOption = new javax.swing.ButtonGroup();
        buttonGroupWizardMode = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jButtonOK = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jRadioTrial = new javax.swing.JRadioButton();
        jRadioNursery = new javax.swing.JRadioButton();
        jRadioClimate = new javax.swing.JRadioButton();
        jRadioSite = new javax.swing.JRadioButton();
        jRadioLabel = new javax.swing.JRadioButton();
        jRadioSurvey = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        lblCreateNew = new javax.swing.JLabel();
        pnlMode = new javax.swing.JPanel();
        jRadioWizard = new javax.swing.JRadioButton();
        jRadioButtonExpert = new javax.swing.JRadioButton();
        lblWizardIcon = new javax.swing.JLabel();
        lblQuickIcon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(JDNewOptions.class, "JDNewOptions.title")); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setMaximumSize(new java.awt.Dimension(671, 85));

        jButtonOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyexplorer/images/ok.png"))); // NOI18N
        jButtonOK.setText(org.openide.util.NbBundle.getMessage(JDNewOptions.class, "JDNewOptions.jButtonOK.text_1")); // NOI18N
        jButtonOK.setToolTipText(org.openide.util.NbBundle.getMessage(JDNewOptions.class, "JDNewOptions.jButtonOK.toolTipText_1")); // NOI18N
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });

        jButtonCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyexplorer/images/cancel.png"))); // NOI18N
        jButtonCancel.setText(org.openide.util.NbBundle.getMessage(JDNewOptions.class, "JDNewOptions.jButtonCancel.text_1")); // NOI18N
        jButtonCancel.setToolTipText(org.openide.util.NbBundle.getMessage(JDNewOptions.class, "JDNewOptions.jButtonCancel.toolTipText_1")); // NOI18N
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130)
                .addComponent(jButtonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonCancel, jButtonOK});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setMaximumSize(new java.awt.Dimension(627, 215));

        buttonGroupOption.add(jRadioTrial);
        jRadioTrial.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jRadioTrial.setSelected(true);
        jRadioTrial.setText(org.openide.util.NbBundle.getMessage(JDNewOptions.class, "JDNewOptions.jRadioTrial.text_1")); // NOI18N
        jRadioTrial.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioTrialItemStateChanged(evt);
            }
        });

        buttonGroupOption.add(jRadioNursery);
        jRadioNursery.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jRadioNursery.setText(org.openide.util.NbBundle.getMessage(JDNewOptions.class, "JDNewOptions.jRadioNursery.text_1")); // NOI18N
        jRadioNursery.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioNurseryItemStateChanged(evt);
            }
        });

        buttonGroupOption.add(jRadioClimate);
        jRadioClimate.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jRadioClimate.setText(org.openide.util.NbBundle.getMessage(JDNewOptions.class, "JDNewOptions.jRadioClimate.text_1")); // NOI18N
        jRadioClimate.setEnabled(false);

        buttonGroupOption.add(jRadioSite);
        jRadioSite.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jRadioSite.setText(org.openide.util.NbBundle.getMessage(JDNewOptions.class, "JDNewOptions.jRadioSite.text_1")); // NOI18N
        jRadioSite.setEnabled(false);

        buttonGroupOption.add(jRadioLabel);
        jRadioLabel.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jRadioLabel.setText(org.openide.util.NbBundle.getMessage(JDNewOptions.class, "JDNewOptions.jRadioLabel.text_1")); // NOI18N
        jRadioLabel.setEnabled(false);

        buttonGroupOption.add(jRadioSurvey);
        jRadioSurvey.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jRadioSurvey.setText(org.openide.util.NbBundle.getMessage(JDNewOptions.class, "JDNewOptions.jRadioSurvey.text_1")); // NOI18N
        jRadioSurvey.setEnabled(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyexplorer/images/agricultura.png"))); // NOI18N
        jLabel1.setText(org.openide.util.NbBundle.getMessage(JDNewOptions.class, "JDNewOptions.jLabel1.text_1")); // NOI18N

        lblCreateNew.setText(org.openide.util.NbBundle.getMessage(JDNewOptions.class, "JDNewOptions.lblCreateNew.text_1")); // NOI18N

        pnlMode.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(JDNewOptions.class, "JDNewOptions.pnlMode.border.title_1"))); // NOI18N

        buttonGroupWizardMode.add(jRadioWizard);
        jRadioWizard.setSelected(true);
        jRadioWizard.setText(org.openide.util.NbBundle.getMessage(JDNewOptions.class, "JDNewOptions.jRadioWizard.text_1")); // NOI18N

        buttonGroupWizardMode.add(jRadioButtonExpert);
        jRadioButtonExpert.setText(org.openide.util.NbBundle.getMessage(JDNewOptions.class, "JDNewOptions.jRadioButtonExpert.text_1")); // NOI18N
        jRadioButtonExpert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonExpertActionPerformed(evt);
            }
        });

        lblWizardIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyexplorer/images/newTrial24.png"))); // NOI18N
        lblWizardIcon.setText(org.openide.util.NbBundle.getMessage(JDNewOptions.class, "JDNewOptions.lblWizardIcon.text")); // NOI18N

        lblQuickIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyexplorer/images/clock24.png"))); // NOI18N
        lblQuickIcon.setText(org.openide.util.NbBundle.getMessage(JDNewOptions.class, "JDNewOptions.lblQuickIcon.text")); // NOI18N

        javax.swing.GroupLayout pnlModeLayout = new javax.swing.GroupLayout(pnlMode);
        pnlMode.setLayout(pnlModeLayout);
        pnlModeLayout.setHorizontalGroup(
            pnlModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlModeLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(pnlModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblQuickIcon)
                    .addComponent(lblWizardIcon))
                .addGap(18, 18, 18)
                .addGroup(pnlModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonExpert)
                    .addComponent(jRadioWizard))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlModeLayout.setVerticalGroup(
            pnlModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlModeLayout.createSequentialGroup()
                .addGroup(pnlModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioWizard)
                    .addComponent(lblWizardIcon))
                .addGap(18, 18, 18)
                .addGroup(pnlModeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblQuickIcon)
                    .addComponent(jRadioButtonExpert))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioSite)
                            .addComponent(jRadioLabel)
                            .addComponent(jRadioClimate)
                            .addComponent(jRadioNursery)
                            .addComponent(jRadioTrial)
                            .addComponent(jRadioSurvey)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblCreateNew)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlMode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(pnlMode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblCreateNew)
                        .addGap(20, 20, 20)
                        .addComponent(jRadioTrial)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioNursery)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioClimate)
                        .addGap(19, 19, 19)
                        .addComponent(jRadioSite)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioLabel)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioSurvey)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed

    public void setOption(int option){
        switch (option) {
            case 0://TRIAL
               this.jRadioTrial.setSelected(true);
               this.jButtonOK.requestFocusInWindow();
                break;
                
            case 1: //NURSERY
                
                 this.jRadioNursery.setSelected(true);
                 this.jButtonOK.requestFocusInWindow();
                break;
                
            default:
                throw new AssertionError();
        }
 
    }
    
    
    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed


        this.setVisible(false);
        TopComponent background = WindowManager.getDefault().findTopComponent("BackgroundWindowTopComponent");

        if (background.isOpened()) {
            background.close();
        }

        switch (this.opcion) {
            case 0:  //TRIAL

                changeCursorWaitStatus(true);
                if (existeTopComponent(studyOBJ.getStudy())) {
                    int opc = JOptionPane.showConfirmDialog(null, "TRIAL ALREADY GENERATED. Do you want to overwrite it?", "Caution!", JOptionPane.YES_NO_OPTION);
                    if (opc == 0) {
                        StudyEditorTopComponent studyEditor = null;
                        ArrayList<TopComponent> opened = new ArrayList<TopComponent>(WindowManager.getDefault().getRegistry().getOpened());
                        for (TopComponent t : opened) {
                            if (t.getName().equals(studyOBJ.getStudy())) {
                                studyEditor = (StudyEditorTopComponent) t;
                                studyEditor.setStudy(studyOBJ);
                                studyEditor.close();
                            }
                        }
                        if (this.jRadioWizard.isSelected()) {
                            launchWizard(studyEditor);
                        } else {
                            expertForm.setStudyWindow(studyEditor);
                            expertForm.setLocationRelativeTo(null);
                            expertForm.cleanFields();
                            expertForm.setVisible(true);
                        }
                    }

                } else {

                    StudyEditorTopComponent studyEditor = new StudyEditorTopComponent();
                    studyEditor.setStudy(studyOBJ);
                    studyEditor.setName(studyOBJ.getStudy());

                    if (this.jRadioWizard.isSelected()) {
                        launchWizard(studyEditor);
                    } else {

                        expertForm.setStudyWindow(studyEditor);
                        expertForm.setLocationRelativeTo(null);
                        expertForm.cleanFields();
                        expertForm.setVisible(true);

                    }

                }



                changeCursorWaitStatus(false);
                break;



            case 1:  //NURSERY
                changeCursorWaitStatus(true);
                if (existeTopComponent("Nursery - " + SelectedStudy.selected.getStudy())) {
                    NurseryEditorTopComponent nurseryEditor = null;
                    int opc = JOptionPane.showConfirmDialog(null, "NURSERY ALREADY GENERATED. Do you want to overwrite it?", "Caution!", JOptionPane.YES_NO_OPTION);

                    if (opc == 0) {

                        ArrayList<TopComponent> opened = new ArrayList<TopComponent>(WindowManager.getDefault().getRegistry().getOpened());
                        for (TopComponent t : opened) {
                            if (t.getName().equals("Nursery - " + SelectedStudy.selected.getStudy())) {
                                nurseryEditor = (NurseryEditorTopComponent) t;
                                nurseryEditor.setStudy(SelectedStudy.selected);
                                nurseryEditor.close();
                            }
                        }

                    }




                    if (this.jRadioWizard.isSelected()) {
                        runNurseryWizard(nurseryEditor);
                    } else {


                        QuickNurseryCreationAction quickNursery = new QuickNurseryCreationAction(SelectedStudy.selected);
                        quickNursery.actionPerformed(evt);

                    }



                } else {

                    NurseryEditorTopComponent nurseryEditor = new NurseryEditorTopComponent();
                    nurseryEditor.setStudy(SelectedStudy.selected);
                    nurseryEditor.setName("Nursery - " + SelectedStudy.selected.getStudy());




                    if (this.jRadioWizard.isSelected()) {
                        runNurseryWizard(nurseryEditor);
                    } else {


                        QuickNurseryCreationAction quickNursery = new QuickNurseryCreationAction(SelectedStudy.selected);
                        quickNursery.actionPerformed(evt);

                    }




                }
                changeCursorWaitStatus(false);
                break;
        }
    }//GEN-LAST:event_jButtonOKActionPerformed

    private void jRadioButtonExpertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonExpertActionPerformed
    }//GEN-LAST:event_jRadioButtonExpertActionPerformed

    private void jRadioNurseryItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioNurseryItemStateChanged
        if (this.jRadioNursery.isSelected()) {
            this.opcion = 1;
        }
    }//GEN-LAST:event_jRadioNurseryItemStateChanged

    private void jRadioTrialItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioTrialItemStateChanged
        if (this.jRadioTrial.isSelected()) {
            this.opcion = 0;
        }
    }//GEN-LAST:event_jRadioTrialItemStateChanged

    private void defineControles(boolean state) {
        jRadioWizard.setEnabled(state);
        jRadioButtonExpert.setEnabled(state);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupOption;
    private javax.swing.ButtonGroup buttonGroupWizardMode;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButtonExpert;
    private javax.swing.JRadioButton jRadioClimate;
    private javax.swing.JRadioButton jRadioLabel;
    private javax.swing.JRadioButton jRadioNursery;
    private javax.swing.JRadioButton jRadioSite;
    private javax.swing.JRadioButton jRadioSurvey;
    private javax.swing.JRadioButton jRadioTrial;
    private javax.swing.JRadioButton jRadioWizard;
    private javax.swing.JLabel lblCreateNew;
    private javax.swing.JLabel lblQuickIcon;
    private javax.swing.JLabel lblWizardIcon;
    private javax.swing.JPanel pnlMode;
    // End of variables declaration//GEN-END:variables

    @SuppressWarnings("unchecked")
    private void launchWizard(StudyEditorTopComponent studyEditor) {

        TrialWizardWizardIterator.studyTopComponent = studyEditor;
        TrialWizardWizardIterator.resetSettings();
        
        WizardDescriptor.Iterator iterator = new TrialWizardWizardIterator();
        

        WizardDescriptor wizardDescriptor = new WizardDescriptor(iterator);
        wizardDescriptor.setTitleFormat(new MessageFormat("{0} ({1})"));
        wizardDescriptor.setTitle("Create Trial Wizard");
        Dialog dialog = DialogDisplayer.getDefault().createDialog(wizardDescriptor);
        dialog.setVisible(true);
        dialog.toFront();

        boolean cancelled = wizardDescriptor.getValue() != WizardDescriptor.FINISH_OPTION;

        if (!cancelled) {
            studyEditor.setStudy(studyOBJ);
            studyEditor.jTextFieldStudy.setText(studyOBJ.getStudy());
            studyEditor.jTextFieldObjective.setText(studyOBJ.getObjective());
            studyEditor.jTextFieldTitle.setText(studyOBJ.getTitle());
            Date start = studyOBJ.getStarDate();
            Date end = studyOBJ.getEndDate();
            String formato = "dd-MMM-yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(formato);

            try {
                studyEditor.jDateChooserStart.setDate(start);
            } catch (NullPointerException ex) {
            }
            try {
                studyEditor.jDateChooserEnd.setDate(end);
            } catch (NullPointerException ex) {
            }
            try {
                studyEditor.jTextFieldPMKey.setText(studyOBJ.getPmkey());
            } catch (NullPointerException ex) {
            }
            try {
                studyEditor.jComboBoxStudyType.setSelectedItem(studyOBJ.getStudyType());
            } catch (NullPointerException ex) {
            }


            boolean estaCorrecto = true;
            for (int i = 0; i < studyEditor.jTableDesign.getRowCount(); i++) {
                try {
                    if (studyEditor.jTableDesign.getValueAt(i, 2).toString().isEmpty()) {
                        estaCorrecto = false;
                    }
                    if (studyEditor.jTableDesign.getValueAt(i, 3).toString().isEmpty()) {
                        estaCorrecto = false;
                    }
                    if (studyEditor.jTableDesign.getValueAt(i, 4).toString().isEmpty()) {
                        estaCorrecto = false;
                    }

                } catch (NullPointerException ex) {

                    return;
                }
            }


            if (estaCorrecto) {
                //studyEditor.fillMeasurementsData();
                studyEditor.fillObservationsData();
            }


            if (!TrialWizardWizardIterator.myExcelReader.getMyWorkbook().hasPropertyTrialInstance()) {
                studyEditor.jTabbedPaneEditor.setEnabledAt(1, false);
            } else {
                studyEditor.jTabbedPaneEditor.setEnabledAt(1, true);
            }

            if (!TrialWizardWizardIterator.existenFactores) {
                studyEditor.jTabbedPaneEditor.setEnabledAt(4, false);
            } else {
                studyEditor.jTabbedPaneEditor.setEnabledAt(4, true);
            }
            
            studyEditor.defineTabs();
            studyEditor.open();
            studyEditor.requestActive();
        }


    }

    private void muestraProgress() {
        final ProgressHandle handle = ProgressHandleFactory.createHandle("Generating design... ");

        handle.start();

        (new SwingWorker<String, Object>() {

            @Override
            protected String doInBackground() throws Exception {

                //  studyEditor.fillMeasurementsAlpha();


                return "";

            }

            @Override
            protected void done() {

                super.done();

                try {

                    String valor = get();

                } catch (InterruptedException ex) {

                    Exceptions.printStackTrace(ex);

                } catch (ExecutionException ex) {

                    Exceptions.printStackTrace(ex);

                } finally {
                    handle.finish();
                }
            }
        }).execute();

    }

    public boolean existeTopComponent(String topName) {
        boolean existe = false;
        try {
            ArrayList<TopComponent> opened = new ArrayList<TopComponent>(WindowManager.getDefault().getRegistry().getOpened());

            for (TopComponent t : opened) {

                if (t.getName().equals(topName)) {
                    existe = true;
                }
            }
        } catch (NullPointerException ex) {
            existe = false;
        }
        return existe;
    }

    private void runNurseryWizard(NurseryEditorTopComponent nurseryEditor) {


        TopComponent background = WindowManager.getDefault().findTopComponent("BackgroundWindowTopComponent");
        if (background.isOpened()) {
            background.close();
        }

        NurseryWizardIterator.nurseryTopComponent = nurseryEditor;
        
        if(SelectedStudy.selected!=null){
        nurseryEditor.setMainProperties(SelectedStudy.selected);
        }
        
        WizardDescriptor.Iterator iterator = new NurseryWizardIterator();
        WizardDescriptor wizardDescriptor = new WizardDescriptor(iterator);
        wizardDescriptor.setTitleFormat(new MessageFormat("{0} ({1})"));
        wizardDescriptor.setTitle("Create New Nursery Wizard");
        Dialog dialog = DialogDisplayer.getDefault().createDialog(wizardDescriptor);
        dialog.setVisible(true);
        dialog.toFront();

        boolean cancelled = wizardDescriptor.getValue() != WizardDescriptor.FINISH_OPTION;
        if (!cancelled) {
            
            nurseryEditor.addChecks();
            nurseryEditor.fillObservationsData();
            nurseryEditor.open();
            nurseryEditor.requestActive();

        }
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
}