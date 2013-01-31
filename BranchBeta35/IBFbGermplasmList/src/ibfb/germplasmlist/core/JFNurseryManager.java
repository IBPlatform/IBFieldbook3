package ibfb.germplasmlist.core;

import ibfb.germplasmlist.crossWizard.CrossWizardWizardIterator;
import ibfb.germplasmlist.selectionWizard.SelectionWizardWizardIterator;
import java.text.MessageFormat;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;

public class JFNurseryManager extends javax.swing.JDialog {

    public JFNurseryManager(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jRadioButtonSelection = new javax.swing.JRadioButton();
        jRadioButtonCross = new javax.swing.JRadioButton();
        jButtonOK = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButtonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(JFNurseryManager.class, "JFNurseryManager.title")); // NOI18N
        setResizable(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/selection.png"))); // NOI18N
        jLabel1.setText(org.openide.util.NbBundle.getMessage(JFNurseryManager.class, "JFNurseryManager.jLabel1.text")); // NOI18N
        jLabel1.setToolTipText(org.openide.util.NbBundle.getMessage(JFNurseryManager.class, "JFNurseryManager.jLabel1.toolTipText")); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        buttonGroup1.add(jRadioButtonSelection);
        jRadioButtonSelection.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jRadioButtonSelection.setSelected(true);
        jRadioButtonSelection.setText(org.openide.util.NbBundle.getMessage(JFNurseryManager.class, "JFNurseryManager.jRadioButtonSelection.text")); // NOI18N
        jRadioButtonSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSelectionActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonCross);
        jRadioButtonCross.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jRadioButtonCross.setText(org.openide.util.NbBundle.getMessage(JFNurseryManager.class, "JFNurseryManager.jRadioButtonCross.text")); // NOI18N

        jButtonOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/ok.png"))); // NOI18N
        jButtonOK.setText(org.openide.util.NbBundle.getMessage(JFNurseryManager.class, "JFNurseryManager.jButtonOK.text")); // NOI18N
        jButtonOK.setToolTipText(org.openide.util.NbBundle.getMessage(JFNurseryManager.class, "JFNurseryManager.jButtonOK.toolTipText")); // NOI18N
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/cross.png"))); // NOI18N
        jLabel2.setText(org.openide.util.NbBundle.getMessage(JFNurseryManager.class, "JFNurseryManager.jLabel2.text")); // NOI18N
        jLabel2.setToolTipText(org.openide.util.NbBundle.getMessage(JFNurseryManager.class, "JFNurseryManager.jLabel2.toolTipText")); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jButtonCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/cancel.png"))); // NOI18N
        jButtonCancel.setText(org.openide.util.NbBundle.getMessage(JFNurseryManager.class, "JFNurseryManager.jButtonCancel.text")); // NOI18N
        jButtonCancel.setToolTipText(org.openide.util.NbBundle.getMessage(JFNurseryManager.class, "JFNurseryManager.jButtonCancel.toolTipText")); // NOI18N
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jRadioButtonSelection)
                .addGap(213, 213, 213)
                .addComponent(jRadioButtonCross)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jButtonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonSelection)
                    .addComponent(jRadioButtonCross))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(107, 107, 107)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed
        if (jRadioButtonSelection.isSelected()) {
            this.setVisible(false);
          //  showSelectionWizard();           
            showSelectionTopComponent();

        } else {
            this.setVisible(false);
            //showCrossWizard();
            showNurseryManagerTopComponent();

        }
    }//GEN-LAST:event_jButtonOKActionPerformed

    private void jRadioButtonSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonSelectionActionPerformed
    }//GEN-LAST:event_jRadioButtonSelectionActionPerformed

    
     private void showNurseryManagerTopComponent() {
       nurseryManagerTopComponent manager=new nurseryManagerTopComponent();
       manager.selectOptionDB();
       manager.open();
       manager.requestActive();
    }
    
    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.jRadioButtonSelection.setSelected(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.jRadioButtonCross.setSelected(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton jRadioButtonCross;
    private javax.swing.JRadioButton jRadioButtonSelection;
    // End of variables declaration//GEN-END:variables

    private void showSelectionWizard() {

        nurserySelectionTopComponent selectionTopComponent = new nurserySelectionTopComponent();
        WizardDescriptor wiz = new WizardDescriptor(new SelectionWizardWizardIterator());
        wiz.setTitleFormat(new MessageFormat("{0} ({1})"));
        wiz.setTitle("Nursery Manager - Selection");

        SelectionWizardWizardIterator.selectionTopComponent = selectionTopComponent;

        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
            selectionTopComponent.createSelectionList();
            selectionTopComponent.open();
            selectionTopComponent.requestActive();


        }

    }
    
    
    private void showSelectionTopComponent(){
       SelectionTopComponent manager=new SelectionTopComponent();
       manager.selectOptionDB();
       manager.open();
       manager.requestActive();
    }

    private void showCrossWizard() {
        nurseryCrossesTopComponent crossTopComponent = new nurseryCrossesTopComponent();
        WizardDescriptor wiz = new WizardDescriptor(new CrossWizardWizardIterator());
        wiz.setTitleFormat(new MessageFormat("{0} ({1})"));
        wiz.setTitle("Nursery Manager - Cross");
        CrossWizardWizardIterator.crossTopComponent = crossTopComponent;
        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
            crossTopComponent.createSelectionList();
            crossTopComponent.open();
            crossTopComponent.requestActive();


        }

    }
}
