package ibfb.nursery.mainwizard;

import ibfb.domain.core.Variate;
import ibfb.domain.core.Workbook;
import ibfb.nursery.core.NurseryEditorTopComponent;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JPanel;
import org.cimmyt.cril.ibwb.commongui.select.list.DoubleListPanel;
import org.cimmyt.cril.ibwb.commongui.select.list.SelectCommand;
import org.openide.util.NbBundle;

public final class NurseryVisualPanel10 extends JPanel {

    private ResourceBundle bundle = NbBundle.getBundle(NurseryVisualPanel10.class);
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

    public NurseryVisualPanel10() {
        initComponents();
        createBallonTips();

        doubleListPanel = new DoubleListPanel<Variate>(availableTraits, selectedTraits, unselectedCommand, selectedCommand);
        doubleListPanel.setSourceLabel(bundle.getString("NurseryVisualPanel10.selectedTraits"));
        doubleListPanel.setTargetLabel(bundle.getString("NurseryVisualPanel10.unselectedTraits"));
        doubleListPanel.setVisible(true);
        pnlSelectList.add(doubleListPanel);

    }

    @Override
    public String getName() {
        return bundle.getString("NurseryVisualPanel10.name");
    }

    private void createBallonTips() {
    }

    @SuppressWarnings("unchecked")
    public void copyValues(final Workbook workbook) {

        NurseryEditorTopComponent nurseryWindow = NurseryWizardIterator.nurseryTopComponent;
        nurseryWindow.cleanFields();
        nurseryWindow.configMyList();
        nurseryWindow.assignTraits(doubleListPanel.getSourceList(), doubleListPanel.getTargetList());
        nurseryWindow.setSelectedTraits(selectedTraits);
    }

    private void configMyList() {

        cleanFields();
    }

    public static void cleanFields() {
        jTextFieldDescription.setText("");
        jTextFieldDescriptionSelected.setText("");

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenuUnSelect = new javax.swing.JPopupMenu();
        jMenuItemUnSelect = new javax.swing.JMenuItem();
        jMenuItemUnSelectAll = new javax.swing.JMenuItem();
        jPopupMenuSelect = new javax.swing.JPopupMenu();
        jMenuItemSelect = new javax.swing.JMenuItem();
        jMenuItemSelectAll = new javax.swing.JMenuItem();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldDescription = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldDescriptionSelected = new javax.swing.JTextField();
        pnlSelectList = new javax.swing.JPanel();

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemUnSelect, org.openide.util.NbBundle.getMessage(NurseryVisualPanel10.class, "NurseryVisualPanel10.jMenuItemUnSelect.text")); // NOI18N
        jMenuItemUnSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUnSelectActionPerformed(evt);
            }
        });
        jPopupMenuUnSelect.add(jMenuItemUnSelect);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemUnSelectAll, org.openide.util.NbBundle.getMessage(NurseryVisualPanel10.class, "NurseryVisualPanel10.jMenuItemUnSelectAll.text")); // NOI18N
        jMenuItemUnSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUnSelectAllActionPerformed(evt);
            }
        });
        jPopupMenuUnSelect.add(jMenuItemUnSelectAll);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemSelect, org.openide.util.NbBundle.getMessage(NurseryVisualPanel10.class, "NurseryVisualPanel10.jMenuItemSelect.text")); // NOI18N
        jMenuItemSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSelectActionPerformed(evt);
            }
        });
        jPopupMenuSelect.add(jMenuItemSelect);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemSelectAll, org.openide.util.NbBundle.getMessage(NurseryVisualPanel10.class, "NurseryVisualPanel10.jMenuItemSelectAll.text")); // NOI18N
        jMenuItemSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSelectAllActionPerformed(evt);
            }
        });
        jPopupMenuSelect.add(jMenuItemSelectAll);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(NurseryVisualPanel10.class, "NurseryVisualPanel10.jLabel4.text")); // NOI18N

        jTextFieldDescription.setEditable(false);
        jTextFieldDescription.setText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel10.class, "NurseryVisualPanel10.jTextFieldDescription.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(NurseryVisualPanel10.class, "NurseryVisualPanel10.jLabel3.text")); // NOI18N

        jTextFieldDescriptionSelected.setEditable(false);
        jTextFieldDescriptionSelected.setText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel10.class, "NurseryVisualPanel10.jTextFieldDescriptionSelected.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                    .addComponent(jTextFieldDescriptionSelected, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
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
                .addContainerGap(392, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDescriptionSelected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlSelectList, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(78, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemUnSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUnSelectActionPerformed
    }//GEN-LAST:event_jMenuItemUnSelectActionPerformed

    private void jMenuItemUnSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUnSelectAllActionPerformed
    }//GEN-LAST:event_jMenuItemUnSelectAllActionPerformed

    private void jMenuItemSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSelectActionPerformed
    }//GEN-LAST:event_jMenuItemSelectActionPerformed

    private void jMenuItemSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSelectAllActionPerformed
    }//GEN-LAST:event_jMenuItemSelectAllActionPerformed

    @SuppressWarnings("unchecked")
    public void fillData(final Workbook workbook) {

        if (NurseryWizardIterator.traitsAreFilled) {
            return;
        }

        availableTraits = workbook.getVariates();
        selectedTraits = new ArrayList<Variate>();
        doubleListPanel.setSourceList(availableTraits);
        doubleListPanel.setTargetList(selectedTraits);
        doubleListPanel.fillListItems();
        NurseryWizardIterator.traitsAreFilled = true;
        configMyList();

    }
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
