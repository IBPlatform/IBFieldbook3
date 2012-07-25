
package ibfb.nursery.exportwizard;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import org.openide.util.NbBundle;

public final class exportVisualPanel1 extends JPanel {

    /** Creates new form exportVisualPanel1 */
    public exportVisualPanel1() {
        initComponents();
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(exportVisualPanel1.class,"exportVisualPanel1.name");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupExport = new javax.swing.ButtonGroup();
        jRadioButtonToFieldlog = new javax.swing.JRadioButton();
        jRadioButtonToExcel = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jRadioButtonToR = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();

        buttonGroupExport.add(jRadioButtonToFieldlog);
        jRadioButtonToFieldlog.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonToFieldlog, org.openide.util.NbBundle.getMessage(exportVisualPanel1.class, "exportVisualPanel1.jRadioButtonToFieldlog.text")); // NOI18N

        buttonGroupExport.add(jRadioButtonToExcel);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonToExcel, org.openide.util.NbBundle.getMessage(exportVisualPanel1.class, "exportVisualPanel1.jRadioButtonToExcel.text")); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/fieldlog.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(exportVisualPanel1.class, "exportVisualPanel1.jLabel1.text")); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/excelBig1.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(exportVisualPanel1.class, "exportVisualPanel1.jLabel3.text")); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel3MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
        });

        buttonGroupExport.add(jRadioButtonToR);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonToR, org.openide.util.NbBundle.getMessage(exportVisualPanel1.class, "exportVisualPanel1.jRadioButtonToR.text")); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/rBig.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(exportVisualPanel1.class, "exportVisualPanel1.jLabel2.text")); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jRadioButtonToFieldlog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jRadioButtonToR)
                        .addGap(67, 67, 67)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jRadioButtonToExcel)))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButtonToFieldlog, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(jRadioButtonToR, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jRadioButtonToExcel)
                        .addGap(63, 63, 63)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(108, 108, 108))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
      this.jRadioButtonToFieldlog.setSelected(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
       this.jRadioButtonToExcel.setSelected(true);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
     this.jLabel1.setIcon(new ImageIcon(getClass().getResource("/ibfb/nursery/images/fieldlog2.png")));  
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
       this.jLabel1.setIcon(new ImageIcon(getClass().getResource("/ibfb/nursery/images/fieldlog.png"))); 
    }//GEN-LAST:event_jLabel1MouseExited

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
       this.jLabel3.setIcon(new ImageIcon(getClass().getResource("/ibfb/nursery/images/excelBig2.png")));  
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
       this.jLabel3.setIcon(new ImageIcon(getClass().getResource("/ibfb/nursery/images/excelBig1.png")));  
    }//GEN-LAST:event_jLabel3MouseExited

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked

        this.jRadioButtonToR.setSelected(true);     }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered

        this.jLabel2.setIcon(new ImageIcon(getClass().getResource("/ibfb/nursery/images/rBig2.png")));     }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited

        this.jLabel2.setIcon(new ImageIcon(getClass().getResource("/ibfb/nursery/images/rBig.png")));     }//GEN-LAST:event_jLabel2MouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupExport;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JRadioButton jRadioButtonToExcel;
    public javax.swing.JRadioButton jRadioButtonToFieldlog;
    public javax.swing.JRadioButton jRadioButtonToR;
    // End of variables declaration//GEN-END:variables
}
