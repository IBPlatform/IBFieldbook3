package ibfb.studyeditor.exportwizard;

import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import org.openide.util.NbBundle;

public final class exportVisualPanel1 extends JPanel {

    private ResourceBundle bundle = NbBundle.getBundle(exportVisualPanel1.class);

 
    public exportVisualPanel1() {
        initComponents();
    }

    @Override
    public String getName() {
        return bundle.getString("exportVisualPanel1.title");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupExport = new javax.swing.ButtonGroup();
        jRadioButtonToFieldlog = new javax.swing.JRadioButton();
        jRadioButtonToExcel = new javax.swing.JRadioButton();
        jRadioButtonToR = new javax.swing.JRadioButton();
        lblImgFieldlog = new javax.swing.JLabel();
        lblImgR = new javax.swing.JLabel();
        lblImgExcel = new javax.swing.JLabel();

        buttonGroupExport.add(jRadioButtonToFieldlog);
        jRadioButtonToFieldlog.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonToFieldlog, org.openide.util.NbBundle.getMessage(exportVisualPanel1.class, "exportVisualPanel1.jRadioButtonToFieldlog.text")); // NOI18N
        jRadioButtonToFieldlog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonToFieldlogActionPerformed(evt);
            }
        });

        buttonGroupExport.add(jRadioButtonToExcel);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonToExcel, org.openide.util.NbBundle.getMessage(exportVisualPanel1.class, "exportVisualPanel1.jRadioButtonToExcel.text")); // NOI18N
        jRadioButtonToExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonToExcelActionPerformed(evt);
            }
        });

        buttonGroupExport.add(jRadioButtonToR);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonToR, org.openide.util.NbBundle.getMessage(exportVisualPanel1.class, "exportVisualPanel1.jRadioButtonToR.text")); // NOI18N
        jRadioButtonToR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonToRActionPerformed(evt);
            }
        });

        lblImgFieldlog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/fieldlog.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblImgFieldlog, org.openide.util.NbBundle.getMessage(exportVisualPanel1.class, "exportVisualPanel1.lblImgFieldlog.text")); // NOI18N
        lblImgFieldlog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImgFieldlogMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblImgFieldlogMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblImgFieldlogMouseExited(evt);
            }
        });

        lblImgR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/rBig.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblImgR, org.openide.util.NbBundle.getMessage(exportVisualPanel1.class, "exportVisualPanel1.lblImgR.text")); // NOI18N
        lblImgR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImgRMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblImgRMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblImgRMouseEntered(evt);
            }
        });

        lblImgExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/excelBig1.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblImgExcel, org.openide.util.NbBundle.getMessage(exportVisualPanel1.class, "exportVisualPanel1.lblImgExcel.text")); // NOI18N
        lblImgExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImgExcelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblImgExcelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblImgExcelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButtonToFieldlog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(152, 152, 152)
                        .addComponent(jRadioButtonToR)
                        .addGap(140, 140, 140)
                        .addComponent(jRadioButtonToExcel)
                        .addContainerGap(55, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblImgFieldlog)
                        .addGap(66, 66, 66)
                        .addComponent(lblImgR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addComponent(lblImgExcel)
                        .addGap(57, 57, 57))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jRadioButtonToFieldlog)
                    .addComponent(jRadioButtonToExcel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButtonToR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(lblImgFieldlog))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(lblImgR))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(lblImgExcel)))
                .addGap(59, 59, 59))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblImgFieldlogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgFieldlogMouseClicked
        this.jRadioButtonToFieldlog.setSelected(true);
          setIsForR();
    }//GEN-LAST:event_lblImgFieldlogMouseClicked

    private void lblImgRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgRMouseClicked
        if (this.lblImgR.isEnabled()) {
            this.jRadioButtonToR.setSelected(true);
              setIsForR();
        }
    }//GEN-LAST:event_lblImgRMouseClicked

    private void lblImgExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgExcelMouseClicked
        this.jRadioButtonToExcel.setSelected(true);
          setIsForR();
    }//GEN-LAST:event_lblImgExcelMouseClicked

    private void lblImgFieldlogMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgFieldlogMouseEntered
        this.lblImgFieldlog.setIcon(new ImageIcon(getClass().getResource("/ibfb/studyeditor/images/fieldlog2.png")));
    }//GEN-LAST:event_lblImgFieldlogMouseEntered

    private void lblImgFieldlogMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgFieldlogMouseExited
        this.lblImgFieldlog.setIcon(new ImageIcon(getClass().getResource("/ibfb/studyeditor/images/fieldlog.png")));
    }//GEN-LAST:event_lblImgFieldlogMouseExited

    private void lblImgRMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgRMouseEntered
        if (this.lblImgR.isEnabled()) {
            this.lblImgR.setIcon(new ImageIcon(getClass().getResource("/ibfb/studyeditor/images/rBig2.png")));
        }
    }//GEN-LAST:event_lblImgRMouseEntered

    private void lblImgRMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgRMouseExited
        if (this.lblImgR.isEnabled()) {
            this.lblImgR.setIcon(new ImageIcon(getClass().getResource("/ibfb/studyeditor/images/rBig.png")));
        }
    }//GEN-LAST:event_lblImgRMouseExited

    private void lblImgExcelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgExcelMouseEntered
        this.lblImgExcel.setIcon(new ImageIcon(getClass().getResource("/ibfb/studyeditor/images/excelBig2.png")));
    }//GEN-LAST:event_lblImgExcelMouseEntered

    private void lblImgExcelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgExcelMouseExited
        this.lblImgExcel.setIcon(new ImageIcon(getClass().getResource("/ibfb/studyeditor/images/excelBig1.png")));
       
    }//GEN-LAST:event_lblImgExcelMouseExited

    private void jRadioButtonToRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonToRActionPerformed
      setIsForR();
    }//GEN-LAST:event_jRadioButtonToRActionPerformed

    private void jRadioButtonToFieldlogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonToFieldlogActionPerformed
          setIsForR();
    }//GEN-LAST:event_jRadioButtonToFieldlogActionPerformed

    private void jRadioButtonToExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonToExcelActionPerformed
         setIsForR();
    }//GEN-LAST:event_jRadioButtonToExcelActionPerformed

    
    private void setIsForR(){
        exportWizardIterator.isForR=this.jRadioButtonToR.isSelected();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupExport;
    public javax.swing.JRadioButton jRadioButtonToExcel;
    public javax.swing.JRadioButton jRadioButtonToFieldlog;
    public javax.swing.JRadioButton jRadioButtonToR;
    private javax.swing.JLabel lblImgExcel;
    private javax.swing.JLabel lblImgFieldlog;
    private javax.swing.JLabel lblImgR;
    // End of variables declaration//GEN-END:variables

    void enabledR(boolean b) {
        this.jRadioButtonToR.setEnabled(b);
        this.lblImgR.setEnabled(b);
    }

    void enabledCSV(boolean b) {
       this.jRadioButtonToFieldlog.setVisible(b);
       this.lblImgFieldlog.setVisible(b);
       if(b){
          this.jRadioButtonToFieldlog.setSelected(true);  
       }else{
           this.jRadioButtonToExcel.setSelected(true);
       }
    }
}
