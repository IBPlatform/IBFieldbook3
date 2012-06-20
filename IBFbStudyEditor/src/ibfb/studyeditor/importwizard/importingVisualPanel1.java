
package ibfb.studyeditor.importwizard;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public final class importingVisualPanel1 extends JPanel {

    /** Creates new form importingVisualPanel1 */
    public importingVisualPanel1() {
        initComponents();
    }

    @Override
    public String getName() {
        return "Import type";
    }

   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioButtonCrossInfo = new javax.swing.JRadioButton();
        jRadioButtonToFieldlog = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jRadioButtonToExcel = new javax.swing.JRadioButton();
        jLabelCrossInfo = new javax.swing.JLabel();

        buttonGroup1.add(jRadioButtonCrossInfo);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonCrossInfo, org.openide.util.NbBundle.getMessage(importingVisualPanel1.class, "importingVisualPanel1.jRadioButtonCrossInfo.text")); // NOI18N
        jRadioButtonCrossInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCrossInfoActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonToFieldlog);
        jRadioButtonToFieldlog.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonToFieldlog, org.openide.util.NbBundle.getMessage(importingVisualPanel1.class, "importingVisualPanel1.jRadioButtonToFieldlog.text")); // NOI18N
        jRadioButtonToFieldlog.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonToFieldlogItemStateChanged(evt);
            }
        });
        jRadioButtonToFieldlog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonToFieldlogActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/fieldlog.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(importingVisualPanel1.class, "importingVisualPanel1.jLabel1.text")); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/excelBig1.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(importingVisualPanel1.class, "importingVisualPanel1.jLabel3.text")); // NOI18N
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

        buttonGroup1.add(jRadioButtonToExcel);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonToExcel, org.openide.util.NbBundle.getMessage(importingVisualPanel1.class, "importingVisualPanel1.jRadioButtonToExcel.text")); // NOI18N
        jRadioButtonToExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonToExcelActionPerformed(evt);
            }
        });

        jLabelCrossInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/crossInfo.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabelCrossInfo, org.openide.util.NbBundle.getMessage(importingVisualPanel1.class, "importingVisualPanel1.jLabelCrossInfo.text")); // NOI18N
        jLabelCrossInfo.setToolTipText(org.openide.util.NbBundle.getMessage(importingVisualPanel1.class, "importingVisualPanel1.jLabelCrossInfo.toolTipText")); // NOI18N
        jLabelCrossInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCrossInfoMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelCrossInfoMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelCrossInfoMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButtonToFieldlog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jRadioButtonToExcel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                        .addComponent(jRadioButtonCrossInfo)
                        .addGap(45, 45, 45))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(58, 58, 58)
                        .addComponent(jLabelCrossInfo)
                        .addGap(35, 35, 35))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonToFieldlog)
                    .addComponent(jRadioButtonToExcel)
                    .addComponent(jRadioButtonCrossInfo))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel1)
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCrossInfo)
                            .addComponent(jLabel3))
                        .addGap(61, 61, 61))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

        this.jRadioButtonToFieldlog.setSelected(true);     }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited

        this.jLabel1.setIcon(new ImageIcon(getClass().getResource("/ibfb/studyeditor/images/fieldlog.png")));     }//GEN-LAST:event_jLabel1MouseExited

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered

        this.jLabel1.setIcon(new ImageIcon(getClass().getResource("/ibfb/studyeditor/images/fieldlog2.png")));     }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked

        this.jRadioButtonToExcel.setSelected(true);     }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited

        this.jLabel3.setIcon(new ImageIcon(getClass().getResource("/ibfb/studyeditor/images/excelBig1.png")));     }//GEN-LAST:event_jLabel3MouseExited

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered

        this.jLabel3.setIcon(new ImageIcon(getClass().getResource("/ibfb/studyeditor/images/excelBig2.png")));     }//GEN-LAST:event_jLabel3MouseEntered

    private void jRadioButtonToFieldlogItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonToFieldlogItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonToFieldlogItemStateChanged

    private void jRadioButtonCrossInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCrossInfoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonCrossInfoActionPerformed

    private void jRadioButtonToExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonToExcelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonToExcelActionPerformed

    private void jLabelCrossInfoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCrossInfoMouseClicked
        this.jRadioButtonCrossInfo.setSelected(true); 
    }//GEN-LAST:event_jLabelCrossInfoMouseClicked

    private void jLabelCrossInfoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCrossInfoMouseEntered
       this.jLabelCrossInfo.setIcon(new ImageIcon(getClass().getResource("/ibfb/studyeditor/images/crossInfo2.png")));  
    }//GEN-LAST:event_jLabelCrossInfoMouseEntered

    private void jLabelCrossInfoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCrossInfoMouseExited
             this.jLabelCrossInfo.setIcon(new ImageIcon(getClass().getResource("/ibfb/studyeditor/images/crossInfo.png")));
    }//GEN-LAST:event_jLabelCrossInfoMouseExited

    private void jRadioButtonToFieldlogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonToFieldlogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonToFieldlogActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelCrossInfo;
    public javax.swing.JRadioButton jRadioButtonCrossInfo;
    public javax.swing.JRadioButton jRadioButtonToExcel;
    public javax.swing.JRadioButton jRadioButtonToFieldlog;
    // End of variables declaration//GEN-END:variables
}
