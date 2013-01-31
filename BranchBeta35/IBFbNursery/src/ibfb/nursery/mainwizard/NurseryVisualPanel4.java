
package ibfb.nursery.mainwizard;

import javax.swing.JPanel;

public final class NurseryVisualPanel4 extends JPanel {

    private boolean quiereNuevo=false;

   
    public boolean quiereNuevo() {
        return quiereNuevo;
    }

    public void setQuiereNuevo(boolean quiereNuevo) {
        this.quiereNuevo = quiereNuevo;
    }
   
    public NurseryVisualPanel4() {
        initComponents();
    }

    @Override
    public String getName() {
        return "Germplasm list source";
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioButtonExisting = new javax.swing.JRadioButton();
        jRadioButtonNew = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        buttonGroup1.add(jRadioButtonExisting);
        jRadioButtonExisting.setFont(new java.awt.Font("Lucida Grande", 0, 18));
        jRadioButtonExisting.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonExisting, org.openide.util.NbBundle.getMessage(NurseryVisualPanel4.class, "NurseryVisualPanel4.jRadioButtonExisting.text")); // NOI18N
        jRadioButtonExisting.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonExistingItemStateChanged(evt);
            }
        });
        jRadioButtonExisting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonExistingActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonNew);
        jRadioButtonNew.setFont(new java.awt.Font("Lucida Grande", 0, 18));
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonNew, org.openide.util.NbBundle.getMessage(NurseryVisualPanel4.class, "NurseryVisualPanel4.jRadioButtonNew.text")); // NOI18N
        jRadioButtonNew.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButtonNewStateChanged(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/list.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(NurseryVisualPanel4.class, "NurseryVisualPanel4.jLabel1.text")); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/newList.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(NurseryVisualPanel4.class, "NurseryVisualPanel4.jLabel2.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonExisting)
                    .addComponent(jRadioButtonNew))
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel1)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jRadioButtonExisting)
                        .addGap(100, 100, 100)
                        .addComponent(jRadioButtonNew)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButtonExistingItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonExistingItemStateChanged
        if(this.jRadioButtonExisting.isSelected()){
            setQuiereNuevo(false);
        }
    }//GEN-LAST:event_jRadioButtonExistingItemStateChanged

    private void jRadioButtonNewStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButtonNewStateChanged
        if(this.jRadioButtonNew.isSelected()){
            setQuiereNuevo(true);
        }
    }//GEN-LAST:event_jRadioButtonNewStateChanged

    private void jRadioButtonExistingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonExistingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonExistingActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton jRadioButtonExisting;
    private javax.swing.JRadioButton jRadioButtonNew;
    // End of variables declaration//GEN-END:variables
}
