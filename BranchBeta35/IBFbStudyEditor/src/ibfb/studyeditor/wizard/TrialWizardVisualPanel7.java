package ibfb.studyeditor.wizard;

import ibfb.domain.core.Constant;
import ibfb.domain.core.Workbook;
import ibfb.studyeditor.core.StudyEditorTopComponent;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JPanel;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.utils.ToolTipUtils;
import org.cimmyt.cril.ibwb.commongui.select.list.DoubleListPanel;
import org.cimmyt.cril.ibwb.commongui.select.list.SelectCommand;
import org.openide.util.NbBundle;

public class TrialWizardVisualPanel7 extends JPanel {
    private ResourceBundle bundle = NbBundle.getBundle(TrialWizardVisualPanel7.class);

    private List<Constant> availableConstants = new ArrayList<Constant>();
    private List<Constant> selectedConstants = new ArrayList<Constant>();
    private DoubleListPanel<Constant> doubleListPanel;
    private SelectCommand unselectedCommand = new SelectCommand() {

        @Override
        public void execute() {
            Constant constant = doubleListPanel.getSelectedSourceItem();
            jTextFieldDescription.setText(constant.getDescription());
        }
    };
    private SelectCommand selectedCommand = new SelectCommand() {

        @Override
        public void execute() {
            Constant constant = doubleListPanel.getSelectedTargetItem();
            jTextFieldDescriptionSelected.setText(constant.getDescription());
        }
    };

    public TrialWizardVisualPanel7() {
        initComponents();
        createBallonTips();



        doubleListPanel = new DoubleListPanel<Constant>(availableConstants, selectedConstants, unselectedCommand, selectedCommand);
        doubleListPanel.setSourceLabel(bundle.getString("TrialWizardVisualPanel7.unselectedConstants"));
        doubleListPanel.setTargetLabel(bundle.getString("TrialWizardVisualPanel7.selectedConstants"));
        doubleListPanel.setVisible(true);
        pnlSelectList.add(doubleListPanel);

    }

    @Override
    public String getName() {
        return bundle.getString("TrialWizardVisualPanel7.title");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldDescription = new javax.swing.JTextField();
        lblDescriptionLeft = new javax.swing.JLabel();
        lblDescriptionRight = new javax.swing.JLabel();
        jTextFieldDescriptionSelected = new javax.swing.JTextField();
        pnlSelectList = new javax.swing.JPanel();

        jTextFieldDescription.setEditable(false);
        jTextFieldDescription.setText(org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel7.class, "TrialWizardVisualPanel6Tree.jTextFieldDescription.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblDescriptionLeft, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel7.class, "TrialWizardVisualPanel7.lblDescriptionLeft.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblDescriptionRight, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel7.class, "TrialWizardVisualPanel7.lblDescriptionRight.text")); // NOI18N

        jTextFieldDescriptionSelected.setEditable(false);
        jTextFieldDescriptionSelected.setText(org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel7.class, "TrialWizardVisualPanel6Tree.jTextFieldDescriptionSelected.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlSelectList, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDescriptionLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                            .addComponent(jTextFieldDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
                        .addGap(141, 141, 141)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDescriptionRight, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(jTextFieldDescriptionSelected, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlSelectList, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblDescriptionLeft)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblDescriptionRight)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDescriptionSelected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings("unchecked")
    public void fillData(final Workbook workbook) {


        if (TrialWizardWizardIterator.constantsAreFilled) {
            return;
        }

        availableConstants = workbook.getConstants();
        selectedConstants = new ArrayList<Constant>();
        doubleListPanel.setSourceList(availableConstants);
        doubleListPanel.setTargetList(selectedConstants);
        doubleListPanel.fillListItems();

        TrialWizardWizardIterator.constantsAreFilled = true;
        configMyList();

    }

    @SuppressWarnings("unchecked")
    public void copyValues(final Workbook workbook, int numInstances) {
        StudyEditorTopComponent studyWindow = TrialWizardWizardIterator.studyTopComponent;

        java.util.List<Constant> constants = new java.util.ArrayList();
        try {
            //constants = (java.util.ArrayList<Constant>) workbook.getConstants();
            constants = doubleListPanel.getTargetList();
        } catch (NullPointerException ex) {
            return;
        }
        if (constants == null) {
            return;
        }

        int instance = 1;

        List<Constant> constantList = new ArrayList();
        for (int k = 0; k < numInstances; k++) {

            for (int j = 0; j < selectedConstants.size(); j++) {
                Constant constant = selectedConstants.get(j);

                Constant condToAdd = new Constant();
                condToAdd.setConstantName(constant.getConstantName());
                condToAdd.setDescription(constant.getDescription());
                condToAdd.setProperty(constant.getProperty());
                condToAdd.setScale(constant.getScale());
                condToAdd.setMethod(constant.getMethod());
                condToAdd.setDataType(constant.getDataType());
                condToAdd.setInstance(instance);
                constantList.add(condToAdd);

            }


            instance++;
        }

        studyWindow.assignExperimentalConditions(constantList);
    }

    private void createBallonTips() {
        BalloonTip tip = new BalloonTip(this.pnlSelectList, bundle.getString("TrialWizardVisualPanel7.tipSelectList"));
        ToolTipUtils.balloonToToolTip(tip, 500, 3000);
    }

    private void configMyList() {

        cleanFields();
    }

    public static void cleanFields() {
        jTextFieldDescription.setText("");
        jTextFieldDescriptionSelected.setText("");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField jTextFieldDescription;
    public static javax.swing.JTextField jTextFieldDescriptionSelected;
    private javax.swing.JLabel lblDescriptionLeft;
    private javax.swing.JLabel lblDescriptionRight;
    private javax.swing.JPanel pnlSelectList;
    // End of variables declaration//GEN-END:variables
}
