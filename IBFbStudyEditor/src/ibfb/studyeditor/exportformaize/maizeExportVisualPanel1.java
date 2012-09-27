
package ibfb.studyeditor.exportformaize;

import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import org.openide.util.NbBundle;

public final class maizeExportVisualPanel1 extends JPanel {
   
    private ResourceBundle bundle = NbBundle.getBundle(maizeExportVisualPanel1.class);

 
    public maizeExportVisualPanel1() {
        initComponents();
    }
    

    @Override
    public String getName() {
         return bundle.getString("maizeExportVisualPanel1.name");
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lblImgFieldlog = new javax.swing.JLabel();
        jRadioButtonToFieldlog = new javax.swing.JRadioButton();
        lblImgExcel = new javax.swing.JLabel();
        jRadioButtonToExcel = new javax.swing.JRadioButton();

        lblImgFieldlog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/fieldlog.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblImgFieldlog, org.openide.util.NbBundle.getMessage(maizeExportVisualPanel1.class, "maizeExportVisualPanel1.lblImgFieldlog.text")); // NOI18N
        lblImgFieldlog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImgFieldlogMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblImgFieldlogMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblImgFieldlogMouseEntered(evt);
            }
        });

        buttonGroup1.add(jRadioButtonToFieldlog);
        jRadioButtonToFieldlog.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonToFieldlog, org.openide.util.NbBundle.getMessage(maizeExportVisualPanel1.class, "maizeExportVisualPanel1.jRadioButtonToFieldlog.text")); // NOI18N
        jRadioButtonToFieldlog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonToFieldlogActionPerformed(evt);
            }
        });

        lblImgExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/excelBig1.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblImgExcel, org.openide.util.NbBundle.getMessage(maizeExportVisualPanel1.class, "maizeExportVisualPanel1.lblImgExcel.text")); // NOI18N
        lblImgExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImgExcelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblImgExcelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblImgExcelMouseEntered(evt);
            }
        });

        buttonGroup1.add(jRadioButtonToExcel);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonToExcel, org.openide.util.NbBundle.getMessage(maizeExportVisualPanel1.class, "maizeExportVisualPanel1.jRadioButtonToExcel.text")); // NOI18N
        jRadioButtonToExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonToExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButtonToFieldlog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButtonToExcel)
                        .addGap(127, 127, 127))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblImgFieldlog)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                        .addComponent(lblImgExcel)
                        .addGap(115, 115, 115))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButtonToExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblImgExcel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButtonToFieldlog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(lblImgFieldlog)))
                .addGap(42, 42, 42))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblImgFieldlogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgFieldlogMouseClicked
        this.jRadioButtonToFieldlog.setSelected(true);
    }//GEN-LAST:event_lblImgFieldlogMouseClicked

    private void lblImgFieldlogMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgFieldlogMouseExited
        this.lblImgFieldlog.setIcon(new ImageIcon(getClass().getResource("/ibfb/studyeditor/images/fieldlog.png")));
    }//GEN-LAST:event_lblImgFieldlogMouseExited

    private void lblImgFieldlogMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgFieldlogMouseEntered
        this.lblImgFieldlog.setIcon(new ImageIcon(getClass().getResource("/ibfb/studyeditor/images/fieldlog2.png")));
    }//GEN-LAST:event_lblImgFieldlogMouseEntered

    private void jRadioButtonToFieldlogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonToFieldlogActionPerformed
        
    }//GEN-LAST:event_jRadioButtonToFieldlogActionPerformed

    private void lblImgExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgExcelMouseClicked
        this.jRadioButtonToExcel.setSelected(true);
       
    }//GEN-LAST:event_lblImgExcelMouseClicked

    private void lblImgExcelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgExcelMouseExited
        this.lblImgExcel.setIcon(new ImageIcon(getClass().getResource("/ibfb/studyeditor/images/excelBig1.png")));

    }//GEN-LAST:event_lblImgExcelMouseExited

    private void lblImgExcelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgExcelMouseEntered
        this.lblImgExcel.setIcon(new ImageIcon(getClass().getResource("/ibfb/studyeditor/images/excelBig2.png")));
    }//GEN-LAST:event_lblImgExcelMouseEntered

    private void jRadioButtonToExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonToExcelActionPerformed
       
    }//GEN-LAST:event_jRadioButtonToExcelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JRadioButton jRadioButtonToExcel;
    public javax.swing.JRadioButton jRadioButtonToFieldlog;
    private javax.swing.JLabel lblImgExcel;
    private javax.swing.JLabel lblImgFieldlog;
    // End of variables declaration//GEN-END:variables
}
