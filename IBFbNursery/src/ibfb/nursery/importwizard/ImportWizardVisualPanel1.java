
package ibfb.nursery.importwizard;


import ibfb.nursery.filters.CSVFiltro;
import ibfb.nursery.filters.ExcelFiltro;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileFilter;


public final class ImportWizardVisualPanel1 extends JPanel {

    private JFileChooser selectorArchivo = new JFileChooser();

   
    public ImportWizardVisualPanel1() {
        initComponents();
         customDialog();
    }

    @Override
    public String getName() {
        return "Import data from file";
    }

  public JFileChooser getjFileChooser() {
        return jFileChooser1;
    }

    public JFileChooser getSelectorArchivo() {
        return selectorArchivo;
    }

  
    public JRadioButton getjRadioButtonToExcel() {
        return jRadioButtonToExcel;
    }

    public JRadioButton getjRadioButtonToFieldlog() {
        return jRadioButtonToFieldlog;
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioButtonToExcel = new javax.swing.JRadioButton();
        jRadioButtonToFieldlog = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jFileChooser1 = new javax.swing.JFileChooser();
        lblFileName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        buttonGroup1.add(jRadioButtonToExcel);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonToExcel, org.openide.util.NbBundle.getMessage(ImportWizardVisualPanel1.class, "ImportWizardVisualPanel1.jRadioButtonToExcel.text")); // NOI18N
        jRadioButtonToExcel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonToExcelItemStateChanged(evt);
            }
        });

        buttonGroup1.add(jRadioButtonToFieldlog);
        jRadioButtonToFieldlog.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonToFieldlog, org.openide.util.NbBundle.getMessage(ImportWizardVisualPanel1.class, "ImportWizardVisualPanel1.jRadioButtonToFieldlog.text")); // NOI18N
        jRadioButtonToFieldlog.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonToFieldlogItemStateChanged(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/fieldlog.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(ImportWizardVisualPanel1.class, "ImportWizardVisualPanel1.jLabel1.text")); // NOI18N
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

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/excelBig1.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(ImportWizardVisualPanel1.class, "ImportWizardVisualPanel1.jLabel3.text")); // NOI18N
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

        jFileChooser1.setControlButtonsAreShown(false);
        jFileChooser1.setToolTipText(org.openide.util.NbBundle.getMessage(ImportWizardVisualPanel1.class, "ImportWizardVisualPanel1.jFileChooser1.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblFileName, org.openide.util.NbBundle.getMessage(ImportWizardVisualPanel1.class, "ImportWizardVisualPanel1.lblFileName.text")); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(ImportWizardVisualPanel1.class, "ImportWizardVisualPanel1.jButton1.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jRadioButtonToFieldlog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel1)
                        .addGap(39, 39, 39)
                        .addComponent(jRadioButtonToExcel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFileName)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jRadioButtonToFieldlog, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addComponent(lblFileName))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jRadioButtonToExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(205, 205, 205))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
 this.jRadioButtonToFieldlog.setSelected(true);       
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        this.jLabel1.setIcon(new ImageIcon(getClass().getResource("/ibfb/nursery/images/fieldlog2.png")));  
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
       this.jLabel1.setIcon(new ImageIcon(getClass().getResource("/ibfb/nursery/images/fieldlog.png"))); 
    }//GEN-LAST:event_jLabel1MouseExited

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
         this.jRadioButtonToExcel.setSelected(true);  
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
         this.jLabel3.setIcon(new ImageIcon(getClass().getResource("/ibfb/nursery/images/excelBig2.png"))); 
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
       this.jLabel3.setIcon(new ImageIcon(getClass().getResource("/ibfb/nursery/images/excelBig1.png"))); 
    }//GEN-LAST:event_jLabel3MouseExited

    private void jRadioButtonToFieldlogItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonToFieldlogItemStateChanged
             if (jRadioButtonToFieldlog.isSelected()) {
            FileFilter[] filtros = new FileFilter[10];
            filtros = jFileChooser1.getChoosableFileFilters();
            for (int i = 0; i < filtros.length; i++) {
                FileFilter filtro = filtros[i];
                jFileChooser1.removeChoosableFileFilter(filtro);
            }
            this.jFileChooser1.addChoosableFileFilter(new CSVFiltro());
        }
    }//GEN-LAST:event_jRadioButtonToFieldlogItemStateChanged

    private void jRadioButtonToExcelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonToExcelItemStateChanged
        if (jRadioButtonToExcel.isSelected()) {
            FileFilter[] filtros = new FileFilter[10];
            filtros = jFileChooser1.getChoosableFileFilters();
            for (int i = 0; i < filtros.length; i++) {
                FileFilter filtro = filtros[i];
                jFileChooser1.removeChoosableFileFilter(filtro);
            }
            this.jFileChooser1.addChoosableFileFilter(new ExcelFiltro());
        }
    }//GEN-LAST:event_jRadioButtonToExcelItemStateChanged

     private void customDialog() {
        this.jFileChooser1.addChoosableFileFilter(new CSVFiltro());
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JRadioButton jRadioButtonToExcel;
    public javax.swing.JRadioButton jRadioButtonToFieldlog;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblFileName;
    // End of variables declaration//GEN-END:variables
}
