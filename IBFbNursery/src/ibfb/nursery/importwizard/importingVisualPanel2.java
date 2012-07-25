
package ibfb.nursery.importwizard;

import ibfb.nursery.filters.CSVFiltro;
import ibfb.nursery.filters.ExcelFiltro;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import org.openide.WizardDescriptor;
import org.openide.util.NbBundle;

public final class importingVisualPanel2 extends JPanel {

    private JFileChooser selectorArchivo = new JFileChooser();
    private File nombreArchivo;
    private int opcionFiltro = 0; //0=xls, 1=csv
    static WizardDescriptor wizardDescriptor;
    
    
    
    public importingVisualPanel2() {
        initComponents();
    }

    @Override
    public String getName() {     
        return NbBundle.getMessage(importingVisualPanel2.class,"importingVisualPanel2.name");
    }

         public File getNombreArchivo() {
         return nombreArchivo;
    }
 
          public static void setWizardDescriptor(WizardDescriptor wd) {
        wizardDescriptor = wd;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButtonSearch = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(importingVisualPanel2.class, "importingVisualPanel2.jLabel2.text")); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setRows(5);
        jTextArea1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextArea1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonSearch, org.openide.util.NbBundle.getMessage(importingVisualPanel2.class, "importingVisualPanel2.jButtonSearch.text")); // NOI18N
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/file.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(importingVisualPanel2.class, "importingVisualPanel2.jLabel1.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43))
        );
    }// </editor-fold>//GEN-END:initComponents

      public void setOpcionFiltro(int opcionFiltro) {
        this.opcionFiltro = opcionFiltro;
    }
    
    private void jTextArea1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea1MousePressed

        openFile();     }//GEN-LAST:event_jTextArea1MousePressed

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed

        openFile();     }//GEN-LAST:event_jButtonSearchActionPerformed

    private void openFile() {
        FileFilter[] filtros = new FileFilter[10];
        filtros = selectorArchivo.getChoosableFileFilters();
        for (int i = 0; i < filtros.length; i++) {
            FileFilter filtro = filtros[i];
            selectorArchivo.removeChoosableFileFilter(filtro);
        }
        File archivoNulo = new File("");
        selectorArchivo.setSelectedFile(archivoNulo);
        selectorArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if (this.opcionFiltro == 0) {
            selectorArchivo.addChoosableFileFilter(new ExcelFiltro());
        }
        if (this.opcionFiltro == 1) {
            selectorArchivo.addChoosableFileFilter(new CSVFiltro());
        }


        int resultado = selectorArchivo.showOpenDialog(null);
        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }


        if (this.opcionFiltro == 0) {
            nombreArchivo = selectorArchivo.getSelectedFile();
            this.jTextArea1.setText(nombreArchivo + ".xls");
        }


        if (this.opcionFiltro == 1) {
            nombreArchivo = selectorArchivo.getSelectedFile();
            this.jTextArea1.setText(nombreArchivo + ".csv");
        }

        wizardDescriptor.doFinishClick();

    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
