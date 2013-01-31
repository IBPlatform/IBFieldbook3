package ibfb.studyeditor.wizard;

import ibfb.settings.core.FieldbookSettings;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import ibfb.studyexplorer.filters.ExcelFiltro;
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
import javax.swing.table.DefaultTableModel;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

public final class TrialWizardVisualPanel1 extends JPanel {

    private ResourceBundle bundle = NbBundle.getBundle(TrialWizardVisualPanel1.class);
    
    private Desktop desktop = null;
    private Desktop.Action action = null;
    private File archivo = null;
    private JFileChooser selectorArchivo = new JFileChooser();
    private DefaultTableModel modeloTablaConditions = new DefaultTableModel();
    private DefaultTableModel modeloTablaTraitsConditions = new DefaultTableModel();
    private DefaultTableModel modeloTablaConstants = new DefaultTableModel();
    private DefaultTableModel modeloTablaFactors = new DefaultTableModel();
    private DefaultTableModel modeloTablaTraits = new DefaultTableModel();
    private DefaultTableModel modeloTablaMain = new DefaultTableModel();
    private int theCrop=0;

    public TrialWizardVisualPanel1() {
        initComponents();


    }

    @Override
    public String getName() {
        return bundle.getString("TrialWizardVisualPanel1.title");
    }

    public JTextArea getjTextAreaPath() {
        return jTextAreaPath;

    }

    public void setjTextAreaPath(JTextArea jTextAreaPath) {
        this.jTextAreaPath = jTextAreaPath;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSelecteTemplateFile = new javax.swing.JPanel();
        jRadioButtonExcel = new javax.swing.JRadioButton();
        lblImgExcel = new javax.swing.JLabel();
        jButtonSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaPath = new javax.swing.JTextArea();
        jButtonPreview = new javax.swing.JButton();
        pnlSelecteTemplateDb = new javax.swing.JPanel();
        jRadioButtonDataBase = new javax.swing.JRadioButton();
        jComboBoxTemplates = new javax.swing.JComboBox();
        lblImgDb = new javax.swing.JLabel();

        pnlSelecteTemplateFile.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlSelecteTemplateFile.setToolTipText(org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel1.class, "TrialWizardVisualPanel1.pnlSelecteTemplateFile.toolTipText")); // NOI18N
        pnlSelecteTemplateFile.setPreferredSize(new java.awt.Dimension(550, 175));

        jRadioButtonExcel.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonExcel, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel1.class, "TrialWizardVisualPanel1.jRadioButtonExcel.text")); // NOI18N
        jRadioButtonExcel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButtonExcelStateChanged(evt);
            }
        });

        lblImgExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/excelPNG.png"))); // NOI18N
        lblImgExcel.setToolTipText(org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel1.class, "TrialWizardVisualPanel1.lblImgExcel.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButtonSearch, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel1.class, "TrialWizardVisualPanel1.jButtonSearch.text")); // NOI18N
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

        jButtonPreview.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/preview.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonPreview, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel1.class, "TrialWizardVisualPanel1.jButtonPreview.text")); // NOI18N
        jButtonPreview.setToolTipText(org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel1.class, "TrialWizardVisualPanel1.jButtonPreview.toolTipText")); // NOI18N
        jButtonPreview.setEnabled(false);
        jButtonPreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPreviewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSelecteTemplateFileLayout = new javax.swing.GroupLayout(pnlSelecteTemplateFile);
        pnlSelecteTemplateFile.setLayout(pnlSelecteTemplateFileLayout);
        pnlSelecteTemplateFileLayout.setHorizontalGroup(
            pnlSelecteTemplateFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelecteTemplateFileLayout.createSequentialGroup()
                .addGroup(pnlSelecteTemplateFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSelecteTemplateFileLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jRadioButtonExcel))
                    .addGroup(pnlSelecteTemplateFileLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblImgExcel)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonSearch)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );
        pnlSelecteTemplateFileLayout.setVerticalGroup(
            pnlSelecteTemplateFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSelecteTemplateFileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonExcel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSelecteTemplateFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblImgExcel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlSelecteTemplateFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonPreview, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pnlSelecteTemplateDb.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlSelecteTemplateDb.setToolTipText(org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel1.class, "TrialWizardVisualPanel1.pnlSelecteTemplateDb.toolTipText")); // NOI18N
        pnlSelecteTemplateDb.setPreferredSize(new java.awt.Dimension(550, 174));

        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonDataBase, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel1.class, "TrialWizardVisualPanel1.jRadioButtonDataBase.text")); // NOI18N
        jRadioButtonDataBase.setEnabled(false);
        jRadioButtonDataBase.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonDataBaseItemStateChanged(evt);
            }
        });

        jComboBoxTemplates.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Drought Breeding RYT", "Multi-factorial treatment structure", "SST" }));
        jComboBoxTemplates.setEnabled(false);

        lblImgDb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/db.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblImgDb, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel1.class, "TrialWizardVisualPanel1.lblImgDb.text")); // NOI18N
        lblImgDb.setToolTipText(org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel1.class, "TrialWizardVisualPanel1.lblImgDb.toolTipText")); // NOI18N

        javax.swing.GroupLayout pnlSelecteTemplateDbLayout = new javax.swing.GroupLayout(pnlSelecteTemplateDb);
        pnlSelecteTemplateDb.setLayout(pnlSelecteTemplateDbLayout);
        pnlSelecteTemplateDbLayout.setHorizontalGroup(
            pnlSelecteTemplateDbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSelecteTemplateDbLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImgDb)
                .addGap(35, 35, 35)
                .addComponent(jComboBoxTemplates, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(214, 214, 214))
            .addGroup(pnlSelecteTemplateDbLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jRadioButtonDataBase)
                .addContainerGap())
        );
        pnlSelecteTemplateDbLayout.setVerticalGroup(
            pnlSelecteTemplateDbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelecteTemplateDbLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSelecteTemplateDbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSelecteTemplateDbLayout.createSequentialGroup()
                        .addComponent(jRadioButtonDataBase)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblImgDb)
                        .addContainerGap(40, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSelecteTemplateDbLayout.createSequentialGroup()
                        .addGap(0, 73, Short.MAX_VALUE)
                        .addComponent(jComboBoxTemplates, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(pnlSelecteTemplateDb, javax.swing.GroupLayout.Alignment.LEADING, 0, 710, Short.MAX_VALUE)
                        .addComponent(pnlSelecteTemplateFile, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 424, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlSelecteTemplateFile, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(pnlSelecteTemplateDb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButtonExcelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButtonExcelStateChanged
        if (this.jRadioButtonExcel.isSelected()) {
            this.jButtonSearch.setEnabled(true);
            this.jTextAreaPath.setEnabled(true);
            this.jComboBoxTemplates.setEnabled(false);
        }
}//GEN-LAST:event_jRadioButtonExcelStateChanged

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed

        openFile();
}//GEN-LAST:event_jButtonSearchActionPerformed

    private void jTextAreaPathMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaPathMousePressed


        openFile();


}//GEN-LAST:event_jTextAreaPathMousePressed

    private void jButtonPreviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPreviewActionPerformed
        previewFile(this.jTextAreaPath.getText());
}//GEN-LAST:event_jButtonPreviewActionPerformed

    private void jRadioButtonDataBaseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonDataBaseItemStateChanged
        if (this.jRadioButtonDataBase.isSelected()) {
            this.jButtonSearch.setEnabled(false);
            this.jTextAreaPath.setEnabled(false);
            this.jComboBoxTemplates.setEnabled(true);
        }
}//GEN-LAST:event_jRadioButtonDataBaseItemStateChanged

    private void openFile() {
        FileFilter[] filtros = new FileFilter[10];
        filtros = selectorArchivo.getChoosableFileFilters();
        System.out.println("root: " + System.getenv("systemroot"));

     
        for (int i = 0; i < filtros.length; i++) {
            FileFilter filtro = filtros[i];
            selectorArchivo.removeChoosableFileFilter(filtro);
        }       
        
        
        String customPath="";//NbPreferences.forModule(TrialWizardVisualPanel1.class).get("customPathTWVP1", "");        
        File myDesktop=null;
        if(!customPath.isEmpty()){
            myDesktop = new File(customPath);    
        }else{
            myDesktop = new File(FieldbookSettings.getSetting(FieldbookSettings.TEMPLATES_DEFAULT_FOLDER));  
        }       
        
       

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
        
        //NbPreferences.forModule(TrialWizardVisualPanel1.class).put("customPathTWVP1", selectorArchivo.getSelectedFile().toString());
        this.jTextAreaPath.setText(selectorArchivo.getSelectedFile().toString());

        WorkbookExcelReader validateExcelReader = new WorkbookExcelReaderImpl();

        boolean isValidFile = false;

        try {
            isValidFile = validateExcelReader.isValidTemplate(this.jTextAreaPath.getText());
            
        
        } catch (Exception ex) {
            System.out.println("ERROR AL VALIDAD TEMPLATE DE EXCEL");
        }

        if (isValidFile) {

         
            try {
                theCrop = validateExcelReader.giveMeCrop();
            } catch (Exception ex) {
                System.out.println("ERROR AL OBTENER CROP" +ex);
            }
            
            
            this.jTextAreaPath.setText(selectorArchivo.getSelectedFile().toString());
            this.jButtonPreview.setEnabled(true);

        } else {
            this.jTextAreaPath.setText("");

            DialogUtil.displayError(bundle.getString("TrialWizardVisualPanel1.invalidTemplate") + validateExcelReader.getValidationMessage());
            
            
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
    private javax.swing.JRadioButton jRadioButtonDataBase;
    private javax.swing.JRadioButton jRadioButtonExcel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaPath;
    private javax.swing.JLabel lblImgDb;
    private javax.swing.JLabel lblImgExcel;
    private javax.swing.JPanel pnlSelecteTemplateDb;
    private javax.swing.JPanel pnlSelecteTemplateFile;
    // End of variables declaration//GEN-END:variables

    public int giveMeCrop() {
        return this.theCrop;
    }
}
