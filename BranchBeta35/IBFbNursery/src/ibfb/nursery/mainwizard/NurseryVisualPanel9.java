package ibfb.nursery.mainwizard;

import ibfb.domain.core.Constant;
import ibfb.domain.core.Workbook;
import ibfb.nursery.core.NurseryEditorTopComponent;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JPanel;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.utils.ToolTipUtils;
import org.cimmyt.cril.ibwb.commongui.select.list.DoubleListPanel;
import org.cimmyt.cril.ibwb.commongui.select.list.SelectCommand;
import org.openide.util.NbBundle;

public final class NurseryVisualPanel9 extends JPanel {

    private List<Constant> availableConstants = new ArrayList<Constant>();
    private List<Constant> selectedConstants = new ArrayList<Constant>();
    private DoubleListPanel<Constant> doubleListPanel;
    private ResourceBundle bundle = NbBundle.getBundle(NurseryVisualPanel9.class);
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

    public NurseryVisualPanel9() {
        initComponents();
        createBallonTips();
        doubleListPanel = new DoubleListPanel<Constant>(availableConstants, selectedConstants, unselectedCommand, selectedCommand);
        doubleListPanel.setSourceLabel(bundle.getString("NurseryVisualPanel9.selectedCons"));
        doubleListPanel.setTargetLabel(bundle.getString("NurseryVisualPanel9.unselectedCons"));
        doubleListPanel.setVisible(true);
        pnlSelectList.add(doubleListPanel);




    }

    @Override
    public String getName() {
        return bundle.getString("NurseryVisualPanel9.name");
    }

    private void createBallonTips() {
        BalloonTip tip = new BalloonTip(this.pnlSelectList, bundle.getString("NurseryVisualPanel9.select"));
        ToolTipUtils.balloonToToolTip(tip, 500, 3000);
    }

    public int giveMeTotalConstants(final Workbook workbook) {
        java.util.ArrayList<Constant> constants = new java.util.ArrayList();
        try {
            constants = (java.util.ArrayList<Constant>) workbook.getConstants();
            return workbook.getConstants().size();
        } catch (NullPointerException ex) {
            return 0;
        }



    }

    private void configMyList() {

        cleanFields();
    }

    public static void cleanFields() {
        jTextFieldDescription.setText("");
        jTextFieldDescriptionSelected.setText("");

    }

    @SuppressWarnings("unchecked")
    public void copyValues(final Workbook workbook) {

        NurseryEditorTopComponent studyWindow = NurseryWizardIterator.nurseryTopComponent;

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
        for (int k = 0; k < 1; k++) {

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

        NurseryWizardIterator.nurseryTopComponent.assignExperimentalConditions(constantList);
    }

    @SuppressWarnings("unchecked")
    public void fillData(final Workbook workbook) {

        if (NurseryWizardIterator.constantsAreFilled) {
            return;
        }
        availableConstants = workbook.getConstants();
        selectedConstants = new ArrayList<Constant>();
        doubleListPanel.setSourceList(availableConstants);
        doubleListPanel.setTargetList(selectedConstants);
        doubleListPanel.fillListItems();
        NurseryWizardIterator.constantsAreFilled = true;
        configMyList();

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenuSelect = new javax.swing.JPopupMenu();
        jMenuItemSelect = new javax.swing.JMenuItem();
        jMenuItemSelectAll = new javax.swing.JMenuItem();
        jPopupMenuUnSelect = new javax.swing.JPopupMenu();
        jMenuItemUnSelect = new javax.swing.JMenuItem();
        jMenuItemUnSelectAll = new javax.swing.JMenuItem();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldDescription = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldDescriptionSelected = new javax.swing.JTextField();
        pnlSelectList = new javax.swing.JPanel();

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemSelect, org.openide.util.NbBundle.getMessage(NurseryVisualPanel9.class, "NurseryVisualPanel9.jMenuItemSelect.text")); // NOI18N
        jMenuItemSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSelectActionPerformed(evt);
            }
        });
        jPopupMenuSelect.add(jMenuItemSelect);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemSelectAll, org.openide.util.NbBundle.getMessage(NurseryVisualPanel9.class, "NurseryVisualPanel9.jMenuItemSelectAll.text")); // NOI18N
        jMenuItemSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSelectAllActionPerformed(evt);
            }
        });
        jPopupMenuSelect.add(jMenuItemSelectAll);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemUnSelect, org.openide.util.NbBundle.getMessage(NurseryVisualPanel9.class, "NurseryVisualPanel9.jMenuItemUnSelect.text")); // NOI18N
        jMenuItemUnSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUnSelectActionPerformed(evt);
            }
        });
        jPopupMenuUnSelect.add(jMenuItemUnSelect);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemUnSelectAll, org.openide.util.NbBundle.getMessage(NurseryVisualPanel9.class, "NurseryVisualPanel9.jMenuItemUnSelectAll.text")); // NOI18N
        jMenuItemUnSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUnSelectAllActionPerformed(evt);
            }
        });
        jPopupMenuUnSelect.add(jMenuItemUnSelectAll);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(NurseryVisualPanel9.class, "NurseryVisualPanel9.jLabel4.text")); // NOI18N

        jTextFieldDescription.setEditable(false);
        jTextFieldDescription.setText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel9.class, "NurseryVisualPanel9.jTextFieldDescription.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(NurseryVisualPanel9.class, "NurseryVisualPanel9.jLabel3.text")); // NOI18N

        jTextFieldDescriptionSelected.setEditable(false);
        jTextFieldDescriptionSelected.setText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel9.class, "NurseryVisualPanel9.jTextFieldDescriptionSelected.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE))
                .addGap(81, 81, 81)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDescriptionSelected, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlSelectList, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(387, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldDescriptionSelected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlSelectList, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(90, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSelectActionPerformed
    }//GEN-LAST:event_jMenuItemSelectActionPerformed

    private void jMenuItemSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSelectAllActionPerformed
    }//GEN-LAST:event_jMenuItemSelectAllActionPerformed

    private void jMenuItemUnSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUnSelectActionPerformed
    }//GEN-LAST:event_jMenuItemUnSelectActionPerformed

    private void jMenuItemUnSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUnSelectAllActionPerformed
    }//GEN-LAST:event_jMenuItemUnSelectAllActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuItem jMenuItemSelect;
    private javax.swing.JMenuItem jMenuItemSelectAll;
    private javax.swing.JMenuItem jMenuItemUnSelect;
    private javax.swing.JMenuItem jMenuItemUnSelectAll;
    private javax.swing.JPopupMenu jPopupMenuSelect;
    private javax.swing.JPopupMenu jPopupMenuUnSelect;
    public static javax.swing.JTextField jTextFieldDescription;
    public static javax.swing.JTextField jTextFieldDescriptionSelected;
    private javax.swing.JPanel pnlSelectList;
    // End of variables declaration//GEN-END:variables
}
