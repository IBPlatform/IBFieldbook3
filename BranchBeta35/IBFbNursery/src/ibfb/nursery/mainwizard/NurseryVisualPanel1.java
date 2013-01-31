
package ibfb.nursery.mainwizard;

import ibfb.nursery.actions.JPNurseryOptions;
import ibfb.nursery.filters.ExcelFiltro;
import ibfb.nursery.utils.OSUtils;
import ibfb.settings.core.FieldbookSettings;
import ibfb.workbook.api.WorkbookExcelReader;
import ibfb.workbook.core.WorkbookExcelReaderImpl;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

public final class NurseryVisualPanel1 extends JPanel {

    private Desktop desktop = null;
    private Desktop.Action action = null;
    private File archivo = null;
    private JFileChooser selectorArchivo = new JFileChooser();
    private ResourceBundle bundle = NbBundle.getBundle(NurseryVisualPanel1.class);
    
    public NurseryVisualPanel1() {
        initComponents();
    }

    @Override
    public String getName() {
        return bundle.getString("NurseryVisualPanel1.name");
    }

     public JTextArea getjTextAreaPath() {
        return jTextAreaPath;

    }

    public void setjTextAreaPath(JTextArea jTextAreaPath) {
        this.jTextAreaPath = jTextAreaPath;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jRadioButtonDataBase = new javax.swing.JRadioButton();
        jComboBoxTemplates = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jRadioButtonExcel = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jButtonSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaPath = new javax.swing.JTextArea();
        jButtonPreview = new javax.swing.JButton();

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel1.class, "NurseryVisualPanel1.jPanel3.toolTipText")); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(550, 174));

        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonDataBase, org.openide.util.NbBundle.getMessage(NurseryVisualPanel1.class, "NurseryVisualPanel1.jRadioButtonDataBase.text")); // NOI18N
        jRadioButtonDataBase.setEnabled(false);
        jRadioButtonDataBase.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonDataBaseItemStateChanged(evt);
            }
        });

        jComboBoxTemplates.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Drought Breeding RYT", "Multi-factorial treatment structure", "SST" }));
        jComboBoxTemplates.setEnabled(false);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/db.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(NurseryVisualPanel1.class, "NurseryVisualPanel1.jLabel3.text")); // NOI18N
        jLabel3.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel1.class, "NurseryVisualPanel1.jLabel3.toolTipText")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(35, 35, 35)
                .addComponent(jComboBoxTemplates, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(214, 214, 214))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jRadioButtonDataBase)
                .addContainerGap(652, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonDataBase)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addComponent(jComboBoxTemplates, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel1.class, "NurseryVisualPanel1.jPanel2.toolTipText")); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(550, 175));

        jRadioButtonExcel.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonExcel, org.openide.util.NbBundle.getMessage(NurseryVisualPanel1.class, "NurseryVisualPanel1.jRadioButtonExcel.text")); // NOI18N
        jRadioButtonExcel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButtonExcelStateChanged(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/excelPNG.png"))); // NOI18N
        jLabel1.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel1.class, "NurseryVisualPanel1.jLabel1.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButtonSearch, org.openide.util.NbBundle.getMessage(NurseryVisualPanel1.class, "NurseryVisualPanel1.jButtonSearch.text")); // NOI18N
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        jTextAreaPath.setColumns(20);
        jTextAreaPath.setEditable(false);
        jTextAreaPath.setLineWrap(true);
        jTextAreaPath.setRows(5);
        jTextAreaPath.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextAreaPathMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextAreaPath);

        jButtonPreview.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/preview.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonPreview, org.openide.util.NbBundle.getMessage(NurseryVisualPanel1.class, "NurseryVisualPanel1.jButtonPreview.text")); // NOI18N
        jButtonPreview.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel1.class, "NurseryVisualPanel1.jButtonPreview.toolTipText")); // NOI18N
        jButtonPreview.setEnabled(false);
        jButtonPreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPreviewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jRadioButtonExcel))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonSearch)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonExcel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonPreview, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, 0, 670, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 403, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButtonDataBaseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonDataBaseItemStateChanged

        if (this.jRadioButtonDataBase.isSelected()) {             this.jButtonSearch.setEnabled(false);             this.jTextAreaPath.setEnabled(false);             this.jComboBoxTemplates.setEnabled(true);         }     }//GEN-LAST:event_jRadioButtonDataBaseItemStateChanged

    private void jRadioButtonExcelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButtonExcelStateChanged

        if (this.jRadioButtonExcel.isSelected()) {             this.jButtonSearch.setEnabled(true);             this.jTextAreaPath.setEnabled(true);             this.jComboBoxTemplates.setEnabled(false);         }     }//GEN-LAST:event_jRadioButtonExcelStateChanged

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed

        
         openFile();     }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jTextAreaPathMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaPathMousePressed


        openFile();

    }//GEN-LAST:event_jTextAreaPathMousePressed

    private void jButtonPreviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPreviewActionPerformed

        previewFile(this.jTextAreaPath.getText());     }//GEN-LAST:event_jButtonPreviewActionPerformed

    
    private void openFile() {
        FileFilter[] filtros = new FileFilter[10];
        filtros = selectorArchivo.getChoosableFileFilters();
        System.out.println("root: " + System.getenv("systemroot"));

     
        for (int i = 0; i < filtros.length; i++) {
            FileFilter filtro = filtros[i];
            selectorArchivo.removeChoosableFileFilter(filtro);
        }


        
         String customPath="";//NbPreferences.forModule(NurseryVisualPanel1.class).get("customPathNWVP1", "");        
        File myDesktop=null;
        if(!customPath.isEmpty()){
            myDesktop = new File(customPath);    
        }else{
            myDesktop = new File(FieldbookSettings.getSetting(FieldbookSettings.TEMPLATES_DEFAULT_FOLDER));  
        } 

        System.out.println("IBFbpath ---->" + OSUtils.getTemplatesPath());

        if (myDesktop.exists()) {
            selectorArchivo.setCurrentDirectory(myDesktop);
        } else {
            File archivoNulo = new File("");
            selectorArchivo.setSelectedFile(archivoNulo);
        }


        selectorArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        selectorArchivo.addChoosableFileFilter(new ExcelFiltro());
        int resultado = selectorArchivo.showOpenDialog(null);

        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }
        
        //NbPreferences.forModule(NurseryVisualPanel1.class).put("customPathNWVP1", selectorArchivo.getSelectedFile().toString());
        this.jTextAreaPath.setText(selectorArchivo.getSelectedFile().toString());

        WorkbookExcelReader validateExcelReader =  new WorkbookExcelReaderImpl();

        boolean isValidFile = false;

        try {
            isValidFile = validateExcelReader.isValidNurseryTemplate(this.jTextAreaPath.getText());
        } catch (Exception ex) {
            System.out.println("ERROR AL VALIDAD TEMPLATE DE EXCEL");
        }

        if (isValidFile) {

            this.jTextAreaPath.setText(selectorArchivo.getSelectedFile().toString());
            this.jButtonPreview.setEnabled(true);

        } else {
            this.jTextAreaPath.setText("");

            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message("THIS EXCEL FILE IS NOT A VALID NURSERY TEMPLATE", NotifyDescriptor.ERROR_MESSAGE));

            this.jButtonPreview.setEnabled(false);
        }


    }

    private void previewFile(String path) {
        try {

            if (Desktop.isDesktopSupported() == true) {
                desktop = Desktop.getDesktop();
                archivo = new File(path);
                desktop.open(archivo);
            }
        } catch (IOException e) {
            System.out.println("ERROR IO PROCESA_ARCHIVO");
        } catch (Exception e) {
            System.out.println("ERROR e PROCESA_ARCHIVO");
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPreview;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JComboBox jComboBoxTemplates;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButtonDataBase;
    private javax.swing.JRadioButton jRadioButtonExcel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaPath;
    // End of variables declaration//GEN-END:variables
}
