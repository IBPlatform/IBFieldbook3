package ibfb.studyeditor.wizard;

import ibfb.domain.core.Variate;
import ibfb.domain.core.Workbook;
import ibfb.studyeditor.core.StudyEditorTopComponent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import org.cimmyt.cril.ibwb.commongui.select.list.DoubleListPanel;
import org.cimmyt.cril.ibwb.commongui.select.list.SelectCommand;

public final class TrialWizardVisualPanel8 extends JPanel {

    private List<Variate> availableTraits = new ArrayList<Variate>();
    private List<Variate> selectedTraits = new ArrayList<Variate>();
    private DoubleListPanel<Variate> doubleListPanel;
    private SelectCommand unselectedCommand = new SelectCommand() {

        @Override
        public void execute() {
            Variate variate = doubleListPanel.getSelectedSourceItem();
            jTextFieldDescription.setText(variate.getDescription());
        }
    };
    private SelectCommand selectedCommand = new SelectCommand() {

        @Override
        public void execute() {
            Variate variate = doubleListPanel.getSelectedTargetItem();
            jTextFieldDescriptionSelected.setText(variate.getDescription());
        }
    };

    public TrialWizardVisualPanel8() {
        initComponents();
        createBallonTips();
        doubleListPanel = new DoubleListPanel<Variate>(availableTraits, selectedTraits, unselectedCommand, selectedCommand);
        doubleListPanel.setSourceLabel("Unselected Traits (from template)");
        doubleListPanel.setTargetLabel("Selected Traits");
        doubleListPanel.setVisible(true);
        pnlSelectList.add(doubleListPanel);

    }

    @Override
    public String getName() {
        return "Traits to be measured";
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenuUnSelect = new javax.swing.JPopupMenu();
        jMenuItemUnSelect = new javax.swing.JMenuItem();
        jMenuItemUnselectAll = new javax.swing.JMenuItem();
        jPopupMenuSelect = new javax.swing.JPopupMenu();
        jMenuItemSelect = new javax.swing.JMenuItem();
        jMenuItemSelectAll = new javax.swing.JMenuItem();
        jTextFieldDescriptionSelected = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldDescription = new javax.swing.JTextField();
        pnlSelectList = new javax.swing.JPanel();

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemUnSelect, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel8.class, "TrialWizardVisualPanel8.jMenuItemUnSelect.text")); // NOI18N
        jMenuItemUnSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUnSelectActionPerformed(evt);
            }
        });
        jPopupMenuUnSelect.add(jMenuItemUnSelect);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemUnselectAll, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel8.class, "TrialWizardVisualPanel8.jMenuItemUnselectAll.text")); // NOI18N
        jMenuItemUnselectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUnselectAllActionPerformed(evt);
            }
        });
        jPopupMenuUnSelect.add(jMenuItemUnselectAll);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemSelect, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel8.class, "TrialWizardVisualPanel8.jMenuItemSelect.text")); // NOI18N
        jMenuItemSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSelectActionPerformed(evt);
            }
        });
        jPopupMenuSelect.add(jMenuItemSelect);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemSelectAll, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel8.class, "TrialWizardVisualPanel8.jMenuItemSelectAll.text")); // NOI18N
        jMenuItemSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSelectAllActionPerformed(evt);
            }
        });
        jPopupMenuSelect.add(jMenuItemSelectAll);

        setPreferredSize(new java.awt.Dimension(678, 475));

        jTextFieldDescriptionSelected.setEditable(false);
        jTextFieldDescriptionSelected.setText(org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel8.class, "TrialWizardVisualPanel8.jTextFieldDescriptionSelected.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel8.class, "TrialWizardVisualPanel8.jLabel4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel8.class, "TrialWizardVisualPanel8.jLabel3.text")); // NOI18N

        jTextFieldDescription.setEditable(false);
        jTextFieldDescription.setText(org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel8.class, "TrialWizardVisualPanel8.jTextFieldDescription.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlSelectList, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                            .addComponent(jTextFieldDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                        .addGap(139, 139, 139)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                            .addComponent(jTextFieldDescriptionSelected, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlSelectList, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDescriptionSelected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemUnSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUnSelectActionPerformed
}//GEN-LAST:event_jMenuItemUnSelectActionPerformed

    private void jMenuItemUnselectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUnselectAllActionPerformed
}//GEN-LAST:event_jMenuItemUnselectAllActionPerformed

    private void jMenuItemSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSelectActionPerformed
    }//GEN-LAST:event_jMenuItemSelectActionPerformed

    private void jMenuItemSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSelectAllActionPerformed
    }//GEN-LAST:event_jMenuItemSelectAllActionPerformed

    @SuppressWarnings("unchecked")
    public void copyValues(final Workbook workbook) {
        StudyEditorTopComponent studyWindow = TrialWizardWizardIterator.studyTopComponent;
        studyWindow.cleanFields();
        studyWindow.assignTraits(doubleListPanel.getSourceList(), doubleListPanel.getTargetList());
    }

    @SuppressWarnings("unchecked")
    public void fillData(final Workbook workbook) {

        if (TrialWizardWizardIterator.traitsAreFilled) {
            return;
        }

        availableTraits = workbook.getVariates();
        selectedTraits = new ArrayList<Variate>();
        doubleListPanel.setSourceList(availableTraits);
        doubleListPanel.setTargetList(selectedTraits);
        doubleListPanel.fillListItems();

        TrialWizardWizardIterator.traitsAreFilled = true;
        configMyList();
    }

    private void createBallonTips() {
        //BalloonTip tip = new BalloonTip(this.jListUnSelected, "Select variables from the following list wich will be measured on each plot for each trial instance ");
        // ToolTipUtils.balloonToToolTip(tip, 500, 3000);
    }

    private void configMyList() {

        cleanFields();
    }

    public static void cleanFields() {
        jTextFieldDescription.setText("");
        jTextFieldDescriptionSelected.setText("");

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuItem jMenuItemSelect;
    private javax.swing.JMenuItem jMenuItemSelectAll;
    private javax.swing.JMenuItem jMenuItemUnSelect;
    private javax.swing.JMenuItem jMenuItemUnselectAll;
    private javax.swing.JPopupMenu jPopupMenuSelect;
    private javax.swing.JPopupMenu jPopupMenuUnSelect;
    public static javax.swing.JTextField jTextFieldDescription;
    public static javax.swing.JTextField jTextFieldDescriptionSelected;
    private javax.swing.JPanel pnlSelectList;
    // End of variables declaration//GEN-END:variables
}
