package ibfb.nursery.mainwizard;

import ibfb.domain.core.Factor;
import ibfb.domain.core.GermplasmList;
import ibfb.domain.core.ListOfEntries;
import ibfb.domain.core.Workbook;
import ibfb.lists.core.SelectListDialog;
import ibfb.nursery.core.NurseryEditorTopComponent;
import ibfb.nursery.filters.ExcelFiltro;
import ibfb.nursery.models.GermplasmEntriesTableModel;
import ibfb.query.classes.GermplsmRecord;
import ibfb.query.classes.GpidInfClass;
import ibfb.query.classes.NamesRecord;
import ibfb.query.core.QueryCenter;
import ibfb.settings.core.FieldbookSettings;
import ibfb.workbook.api.GermplasmAssigmentTool;
import ibfb.workbook.api.GermplasmListReader;
import ibfb.workbook.core.GermplasmAssigmentToolImpl;
import ibfb.workbook.core.GermplasmListReaderImpl;
import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.cimmyt.cril.ibwb.commongui.OSUtils;
import org.cimmyt.cril.ibwb.domain.Listdata;
import org.cimmyt.cril.ibwb.domain.Listnms;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.netbeans.api.progress.ProgressUtils;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

public final class NurseryVisualPanel41 extends JPanel {

    private JFileChooser selectorArchivo = new JFileChooser();
    private Workbook myWorkbook;
    private Desktop desktop = null;
    private Desktop.Action action = null;
    private File archivo = null;
    private ResourceBundle bundle = NbBundle.getBundle(NurseryVisualPanel41.class);
    private boolean isForWheat = true;
    private String[] nameColumn = {"Cross Name", "Selection History", "Pedigree", "CID", "SID", "GID", "INTRID", "TID", "ENT", "Folio", "Specific Name", "Name Abbreviation", "Cross Year", "Cross Location", "Cross Country", "Cross Organization", "Cross Program", "FAO In-trust", "Selection Year", "Selection Location", "Selection Country", "Name Country", "Name Year", "FAO designation Date", "24 disp", "25 disp"};
    private ArrayList<String> wheatColumns;
    private ArrayList<String> wheatColumnsforSearch;
    private QueryCenter queryReadCenter;
    private Connection dmsLocal, dmsCentral, gmsLocal, gmsCentral;
    private String outSelectionHistory;
    private boolean ready = false;
    private ProgressHandle handle;
    private String porcentaje;

    public NurseryVisualPanel41() {
        initComponents();
        checkButtonsStatus();
        if (isForWheat) {
            loadNamesForWheat();
            // loadQueryCenter();
        }
    }

    private void loadNamesForWheat() {
        wheatColumns = new ArrayList<String>();
        for (int i = 0; i < nameColumn.length; i++) {
            wheatColumns.add(nameColumn[i].toUpperCase());
        }
    }

    @Override
    public String getName() {
        return bundle.getString("NurseryVisualPanel41.name");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupGMS = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButtonSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaPath = new javax.swing.JTextArea();
        radGermplasmFromTemplate = new javax.swing.JRadioButton();
        radGermplasmFromDB1 = new javax.swing.JRadioButton();
        cboGermplasmList = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jButtonPreview = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableEntries = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldTotalEntries = new javax.swing.JTextField();

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(NurseryVisualPanel41.class, "NurseryVisualPanel41.jPanel2.border.title"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/excelFile.png"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButtonSearch, org.openide.util.NbBundle.getMessage(NurseryVisualPanel41.class, "NurseryVisualPanel41.jButtonSearch.text")); // NOI18N
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

        buttonGroupGMS.add(radGermplasmFromTemplate);
        org.openide.awt.Mnemonics.setLocalizedText(radGermplasmFromTemplate, org.openide.util.NbBundle.getMessage(NurseryVisualPanel41.class, "NurseryVisualPanel41.radGermplasmFromTemplate.text")); // NOI18N
        radGermplasmFromTemplate.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radGermplasmFromTemplateStateChanged(evt);
            }
        });
        radGermplasmFromTemplate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radGermplasmFromTemplateActionPerformed(evt);
            }
        });

        buttonGroupGMS.add(radGermplasmFromDB1);
        radGermplasmFromDB1.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(radGermplasmFromDB1, org.openide.util.NbBundle.getMessage(NurseryVisualPanel41.class, "NurseryVisualPanel41.radGermplasmFromDB1.text")); // NOI18N
        radGermplasmFromDB1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radGermplasmFromDB1StateChanged(evt);
            }
        });
        radGermplasmFromDB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radGermplasmFromDB1ActionPerformed(evt);
            }
        });

        cboGermplasmList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT ONE..." }));
        cboGermplasmList.setEnabled(false);
        cboGermplasmList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboGermplasmListItemStateChanged(evt);
            }
        });
        cboGermplasmList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGermplasmListActionPerformed(evt);
            }
        });
        cboGermplasmList.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cboGermplasmListPropertyChange(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/database.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(NurseryVisualPanel41.class, "NurseryVisualPanel41.jLabel4.text")); // NOI18N

        jButtonPreview.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/preview.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonPreview, org.openide.util.NbBundle.getMessage(NurseryVisualPanel41.class, "NurseryVisualPanel41.jButtonPreview.text")); // NOI18N
        jButtonPreview.setToolTipText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel41.class, "NurseryVisualPanel41.jButtonPreview.toolTipText")); // NOI18N
        jButtonPreview.setEnabled(false);
        jButtonPreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPreviewActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnSearch, org.openide.util.NbBundle.getMessage(NurseryVisualPanel41.class, "NurseryVisualPanel41.btnSearch.text")); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radGermplasmFromDB1)
                    .addComponent(radGermplasmFromTemplate))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboGermplasmList, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSearch)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(radGermplasmFromDB1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboGermplasmList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                    .addComponent(jButtonPreview, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(radGermplasmFromTemplate)
                            .addGap(20, 20, 20)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTableEntries.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableEntries.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(jTableEntries);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(NurseryVisualPanel41.class, "NurseryVisualPanel41.jLabel3.text")); // NOI18N

        jTextFieldTotalEntries.setEditable(false);
        jTextFieldTotalEntries.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldTotalEntries.setText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel41.class, "NurseryVisualPanel41.jTextFieldTotalEntries.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(578, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldTotalEntries, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(437, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldTotalEntries, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addGap(66, 66, 66)))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel3, jTextFieldTotalEntries});

    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed

        openFile();
        if (this.jTextAreaPath.getText().isEmpty() == false) {
            readExcelGermplsmEntries(this.jTextAreaPath.getText());
            this.jButtonPreview.setEnabled(true);
        }
}//GEN-LAST:event_jButtonSearchActionPerformed

    private void jTextAreaPathMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaPathMousePressed
        if (radGermplasmFromTemplate.isSelected()) {
            openFile();
            if (this.jTextAreaPath.getText().isEmpty() == false) {
                readExcelGermplsmEntries(this.jTextAreaPath.getText());
                this.jButtonPreview.setEnabled(true);
            }
        }
}//GEN-LAST:event_jTextAreaPathMousePressed

    private void radGermplasmFromTemplateStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radGermplasmFromTemplateStateChanged
}//GEN-LAST:event_radGermplasmFromTemplateStateChanged

    private void radGermplasmFromTemplateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radGermplasmFromTemplateActionPerformed
        checkButtonsStatus();
}//GEN-LAST:event_radGermplasmFromTemplateActionPerformed

    private void radGermplasmFromDB1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radGermplasmFromDB1StateChanged
}//GEN-LAST:event_radGermplasmFromDB1StateChanged

    private void radGermplasmFromDB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radGermplasmFromDB1ActionPerformed
        checkButtonsStatus();
}//GEN-LAST:event_radGermplasmFromDB1ActionPerformed

    private void cboGermplasmListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboGermplasmListItemStateChanged
}//GEN-LAST:event_cboGermplasmListItemStateChanged

    private void jButtonPreviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPreviewActionPerformed
        previewFile(this.jTextAreaPath.getText());
}//GEN-LAST:event_jButtonPreviewActionPerformed

    private void cboGermplasmListPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cboGermplasmListPropertyChange
    }//GEN-LAST:event_cboGermplasmListPropertyChange

    public void showProgressStatus() {

        handle = ProgressHandleFactory.createHandle(bundle.getString("NurseryVisualPanel41.loading"));


        ProgressUtils.showProgressDialogAndRun(new Runnable() {

            @Override
            public void run() {
                porcentaje = "0";
                handle.start(100);
                handle.progress(bundle.getString("NurseryVisualPanel41.completado") + porcentaje + " %");
                //completeFullDataFromDatabase();
            }
        }, handle, true);

    }

    private void cboGermplasmListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGermplasmListActionPerformed
        if (!ready) {
            return;
        }

        boolean validSelection = cboGermplasmList.getSelectedIndex() > 0;
        if (validSelection) {

            readGermplsmEntriesFromDb();

            if (isForWheat) {
                showProgressStatus();
            }
        }


    }//GEN-LAST:event_cboGermplasmListActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        searchList();
    }//GEN-LAST:event_btnSearchActionPerformed

    public void fillComboListNames() {
        List<Listnms> germplasmList = AppServicesProxy.getDefault().appServices().getListnmsList();
        for (Listnms list : germplasmList) {
            cboGermplasmList.addItem(list);
        }

        ready = true;
    }

    public JTextField getjTextFieldTotalEntries() {
        return jTextFieldTotalEntries;
    }

    public void setjTextFieldTotalEntries(JTextField jTextFieldTotalEntries) {
        this.jTextFieldTotalEntries = jTextFieldTotalEntries;
    }

    public Workbook getMyWorkbook() {
        return myWorkbook;
    }

    public void setMyWorkbook(Workbook myWorkbook) {
        this.myWorkbook = myWorkbook;
    }

    public JTable getjTableEntries() {
        return jTableEntries;
    }

    public void setjTableEntries(JTable jTableEntries) {
        this.jTableEntries = jTableEntries;
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
            System.out.println("ERROR e PROCSA_ARCHIVO");
        }
    }

    private void checkButtonsStatus() {
        boolean enabled = radGermplasmFromTemplate.isSelected();
        jTextAreaPath.setEnabled(enabled);
        jButtonSearch.setEnabled(enabled);
        cboGermplasmList.setEnabled(!enabled);

        GermplasmEntriesTableModel modeloTablaEntries = new GermplasmEntriesTableModel();
        this.jTableEntries.setModel(modeloTablaEntries);


        if (enabled) {

            cboGermplasmList.setSelectedIndex(0);
            this.jButtonPreview.setEnabled(false);
        } else {
            jTextAreaPath.setText("");
            this.jTextFieldTotalEntries.setText("0");
            this.jButtonPreview.setEnabled(false);
            this.jTableEntries.removeAll();
        }

    }

    private void readGermplsmEntriesFromDb() {


        GermplasmListReader germplasmListReader = new GermplasmListReaderImpl();
        boolean validSelection = cboGermplasmList.getSelectedIndex() > 0;

        if (validSelection) {
            try {
                Listnms selectedList = (Listnms) cboGermplasmList.getSelectedItem();
                GermplasmList germplasmList = germplasmListReader.getGermPlasmListFromDB(selectedList.getListid());
                setGermplasmListIntoTable(germplasmList);
            } catch (Exception ex) {
                System.out.println("ERROR AL LEER EXCEL GERMPLASM ENTRIES: " + ex);
            }
        } else {
            GermplasmEntriesTableModel modeloTablaEntries = new GermplasmEntriesTableModel();
            this.jTableEntries.setModel(modeloTablaEntries);
            this.jTextAreaPath.setText("");
            this.jTextFieldTotalEntries.setText("0");
            if (radGermplasmFromDB1.isSelected()) {
                DialogUtil.displayError(bundle.getString("NurseryVisualPanel41.choose"));
            }
        }


    }

    public JTextArea getjTextAreaPath() {
        return jTextAreaPath;
    }

    public void setjTextAreaPath(JTextArea jTextAreaPath) {
        this.jTextAreaPath = jTextAreaPath;
    }

    private void openFile() {
        FileFilter[] filtros = new FileFilter[10];
        filtros = selectorArchivo.getChoosableFileFilters();

        for (int i = 0; i < filtros.length; i++) {
            FileFilter filtro = filtros[i];
            selectorArchivo.removeChoosableFileFilter(filtro);
        }

       
       String customPath="";//NbPreferences.forModule(NurseryVisualPanel41.class).get("customPathNWVP41", "");        
        File myDesktop=null;
        if(!customPath.isEmpty()){
            myDesktop = new File(customPath);    
        }else{
            myDesktop = new File(FieldbookSettings.getSetting(FieldbookSettings.GERMPLASM_LIST_DEFAULT_FOLDER));  
        } 
        
        
        if (myDesktop.exists()) {
            selectorArchivo.setCurrentDirectory(myDesktop);
        } else {
            //File archivoNulo = new File("");
            //selectorArchivo.setSelectedFile(archivoNulo);
            File archivoNulo = new File(OSUtils.getGermplasmListsPath());
            if (archivoNulo.exists()) {
                selectorArchivo.setSelectedFile(archivoNulo);
            } else {
                archivoNulo = new File("");
            }

            selectorArchivo.setSelectedFile(archivoNulo);
        }


        selectorArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        selectorArchivo.addChoosableFileFilter(new ExcelFiltro());
        int resultado = selectorArchivo.showOpenDialog(null);

        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }
        //NbPreferences.forModule(NurseryVisualPanel41.class).put("customPathNWVP41", selectorArchivo.getSelectedFile().toString());

        this.jTextAreaPath.setText(selectorArchivo.getSelectedFile().toString());

    }

    public void copyValues() {
        NurseryEditorTopComponent nurseryWindow = NurseryWizardIterator.nurseryTopComponent;
        GermplasmEntriesTableModel tableModel = (GermplasmEntriesTableModel) this.jTableEntries.getModel();
        nurseryWindow.assignGermplasmEntries(tableModel.getFactorHeaders(), tableModel.getGermplasmData());
    }

    private void readExcelGermplsmEntries(String myFile) {
        GermplasmListReader germplasmListReader = new GermplasmListReaderImpl();
        boolean validFile = false;
        try {
            validFile = germplasmListReader.isValidTemplate(myFile);
        } catch (Exception ex) {
            System.out.println("ERROR AL VALIDAR ARCHIVO EXCEL");
        }

        if (validFile) {
            try {
                GermplasmList germplasmList = germplasmListReader.getGermPlasmList(myFile);
                setGermplasmListIntoTable(germplasmList);
            } catch (Exception ex) {
                System.out.println("ERROR AL LEER EXCEL GERMPLASM ENTRIES: " + ex);
            }
        } else {
            GermplasmEntriesTableModel modeloTablaEntries = new GermplasmEntriesTableModel();
            this.jTableEntries.setModel(modeloTablaEntries);
            this.jTextAreaPath.setText("");
            this.jTextFieldTotalEntries.setText("0");
            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(bundle.getString("NurseryVisualPanel41.invalid"), NotifyDescriptor.ERROR_MESSAGE));
        }
    }

    @SuppressWarnings("unchecked")
    private void setGermplasmListIntoTable(GermplasmList germplasmList) {

        GermplasmAssigmentTool gat = new GermplasmAssigmentToolImpl();
        List<String> columnList = gat.getColumnList(this.myWorkbook.getFactors());
        for (String string : columnList) {
            System.out.println(string);
        }
        java.util.ArrayList<Factor> factors = new ArrayList();
        factors = (java.util.ArrayList<Factor>) this.myWorkbook.getFactors();
        boolean hayCoincidencias = false;
        this.jTextFieldTotalEntries.setText(String.valueOf(germplasmList.getListEntries().size()));
        List<ListOfEntries> myList = germplasmList.getListEntries();
        List<List<Object>> rowList = gat.getMappedColumns(columnList, germplasmList);
        GermplasmEntriesTableModel tableModel = new GermplasmEntriesTableModel(this.myWorkbook.getEntryFactors(), rowList);
        this.jTableEntries.setModel(tableModel);
        ajustaColumnsTable(this.jTableEntries, 2);
    }

    @SuppressWarnings("unchecked")
    public boolean existenFactores(final Workbook workbook) {
        boolean existenFactores = false;

        java.util.ArrayList<Factor> factores = new ArrayList();
        try {
            factores = (java.util.ArrayList<Factor>) workbook.getOtherFactors();
        } catch (NullPointerException ex) {
            return false;
        }
        if (factores == null) {
            return false;
        }
        if (factores.isEmpty()) {
            return false;
        }

        if (factores.size() > 0) {
            existenFactores = true;
        }

        return existenFactores;
    }

    public void ajustaColumnsTable(JTable table, int margin) {
        for (int c = 0; c < table.getColumnCount(); c++) {
            ajustaColumn(table, c, 2);
        }
    }

    public void ajustaColumn(JTable table, int vColIndex, int margin) {
        TableModel modelPack = table.getModel();
        DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
        TableColumn col = colModel.getColumn(vColIndex);
        int width = 0;
        TableCellRenderer renderer = col.getHeaderRenderer();
        if (renderer == null) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }
        Component comp = renderer.getTableCellRendererComponent(
                table, col.getHeaderValue(), false, false, 0, 0);
        width = comp.getPreferredSize().width;
        for (int r = 0; r < table.getRowCount(); r++) {
            renderer = table.getCellRenderer(r, vColIndex);
            comp = renderer.getTableCellRendererComponent(
                    table, table.getValueAt(r, vColIndex), false, false, r,
                    vColIndex);
            width = Math.max(width, comp.getPreferredSize().width);
        }
        width += 2 * margin;
        col.setPreferredWidth(width);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.ButtonGroup buttonGroupGMS;
    private javax.swing.JComboBox cboGermplasmList;
    private javax.swing.JButton jButtonPreview;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableEntries;
    private javax.swing.JTextArea jTextAreaPath;
    private javax.swing.JTextField jTextFieldTotalEntries;
    private javax.swing.JRadioButton radGermplasmFromDB1;
    private javax.swing.JRadioButton radGermplasmFromTemplate;
    // End of variables declaration//GEN-END:variables

    private void completeDataFromDatabase() {


        wheatColumnsforSearch = new ArrayList<String>();
        GermplasmEntriesTableModel tableModel = (GermplasmEntriesTableModel) this.jTableEntries.getModel();
        List<NamesRecord> listNL = null;

        if (tableModel.getRowCount() <= 0) {
            return;
        }

        int gidColumn = tableModel.findColumn("GID");
        System.out.println("gidColumn found in: " + gidColumn);

        if (gidColumn < 0) {
            return;
        }

        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            String col = tableModel.getColumnName(i).toUpperCase();

            if (col.equals("CROSS NAME") || col.equals("PEDIGREE")) {
                wheatColumnsforSearch.add("PEDIGREE");
            } else {

                if (wheatColumns.contains(col) && (!col.equals("GID"))) {
                    wheatColumnsforSearch.add(tableModel.getColumnName(i));
                }
            }

        }

        for (int i = 0; i < wheatColumnsforSearch.size(); i++) {
            System.out.println("COLUMN TO FIND -> " + wheatColumnsforSearch.get(i));

        }




        int crossColumn = tableModel.findColumn("CROSS NAME");
        if (crossColumn < 0) {
            crossColumn = tableModel.findColumn("PEDIGREE");
        }


        int selHistColumn = tableModel.findColumn("SELECTION HISTORY");

        GermplasmEntriesTableModel.setIsFromCrossInfo(true);

        for (int i = 0; i < tableModel.getRowCount(); i++) {

            int perc = (int) ((i + 1) * 100 / tableModel.getRowCount());

            porcentaje = String.valueOf(perc);
            handle.progress("Porcentaje completado: " + porcentaje + " %");



            GermplsmRecord germplsmRecord = new GermplsmRecord();
            GpidInfClass gpidinfClass = new GpidInfClass();

            int gid = Integer.parseInt(tableModel.getValueAt(i, gidColumn).toString());
            germplsmRecord = queryReadCenter.getGermplsmRecord(gid);


            try {

                outSelectionHistory = "";
                String outCrossName = "";

                if (crossColumn >= 0) {
                    outCrossName = queryReadCenter.arma_pedigree(germplsmRecord.getGid(), 0, gpidinfClass, 0, 0, 0, 0);
                    if (outCrossName.isEmpty()) {
                        outCrossName = "";
                    }
                    tableModel.setValueAt(outCrossName, i, crossColumn);
                    //  System.out.println("EL CROSSNAME: " + outCrossName);
                }

                if (selHistColumn >= 0) {
                    listNL = queryReadCenter.getNamesList(germplsmRecord.getGid());
                    getFieldsFromArrayNames(listNL);
                    if (outSelectionHistory.isEmpty()) {
                        outSelectionHistory = "";
                    }
                    tableModel.setValueAt(outSelectionHistory, i, selHistColumn);
                    // System.out.println("HIstoria de seleccion: " + outSelectionHistory);
                }


            } catch (Exception e) {
                System.out.println("ERROR" + e);
            }
        }
        GermplasmEntriesTableModel.setIsFromCrossInfo(false);



    }

    private void getFieldsFromArrayNames(List<NamesRecord> p_ALN) {
        if (p_ALN.isEmpty()) {
            return;
        }
        try {
            for (NamesRecord namesrecord : p_ALN) {
                if (namesrecord.getNtype() == 1027 || namesrecord.getNtype() == 1028) {
                    if (namesrecord.getNstat() == 1) {
                        if (!namesrecord.getNval().startsWith("###")) {
                            outSelectionHistory = namesrecord.getNval();
                        }
                    }
                    if (outSelectionHistory.equals("")) {
                        if (!namesrecord.getNval().startsWith("###")) {
                            outSelectionHistory = namesrecord.getNval();
                        }
                    }
                }

            }
        } catch (Exception e) {
        }
    }

    private void loadQueryCenter() {
        queryReadCenter = new QueryCenter();
        queryReadCenter.readAndLoadDatabases();
        queryReadCenter.setFnameKeyEntryNumber("Entry number");
        queryReadCenter.setFnameKeyOcc("occ");
        queryReadCenter.setFnamePedigree("cross name");
        queryReadCenter.readFlexPedConfig();
    }

    private void searchList() {
        SelectListDialog selectListDialog = new SelectListDialog();
        selectListDialog.showSearchDialog();
        selectListDialog.populateComboListNames(cboGermplasmList);
        if (selectListDialog.isListSelected()) {
            try {
                GermplasmListReader germplasmListReader = new GermplasmListReaderImpl();
                GermplasmList germplasmList = germplasmListReader.getGermPlasmListFromDB(selectListDialog.getSeledtedListnms().getListid());
                setGermplasmListIntoTable(germplasmList);

            } catch (Exception ex) {
                System.out.println("ERROR AL LEER EXCEL GERMPLASM ENTRIES DB: " + ex);
            }
        }
    }
//    private boolean loadConnections() {
//        boolean hayConexion = false;
//        try {
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
//        } catch (Exception e) {
//            System.out.println("No se ha podido cargar el Driver JDBC-ODBC");
//        }
//
//        try {
//            dmsLocal = DriverManager.getConnection("jdbc:mysql://localhost:3306/iwis_local_dms", "root", "");
//            dmsCentral = DriverManager.getConnection("jdbc:mysql://localhost:3306/iwis_central_short", "root", "");
//            gmsLocal = DriverManager.getConnection("jdbc:mysql://localhost:3306/iwis_local_gms", "root", "");
//            gmsCentral = DriverManager.getConnection("jdbc:mysql://localhost:3306/iwis_central_gms", "root", "");
//            hayConexion = true;
//        } catch (SQLException ex) {
//            System.out.println("ERROR AL CONECTAR CON LA BASE DE DATOS, MYSQL " + ex);
//        }
//        return hayConexion;
//    }

    private void completeFullDataFromDatabase() {
        wheatColumnsforSearch = new ArrayList<String>();
        GermplasmEntriesTableModel tableModel = (GermplasmEntriesTableModel) this.jTableEntries.getModel();

        Listnms selectedList = (Listnms) cboGermplasmList.getSelectedItem();
        Listnms listnms = null;
        List<Listdata> listas = null;

        try {
            listnms = AppServicesProxy.getDefault().appServices().getFullListnms(selectedList.getListid());
            listas = (List<Listdata>) listnms.getLisdatas();
        } catch (Exception ex) {
            ex.printStackTrace();
            return;

        }

        if (tableModel.getRowCount() <= 0) {
            return;
        }

        int gidColumn = tableModel.findColumn("GID");
        System.out.println("gidColumn found in: " + gidColumn);

        if (gidColumn < 0) {
            return;
        }

        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            String col = tableModel.getColumnName(i).toUpperCase();

            if (col.equals("CROSS NAME") || col.equals("PEDIGREE")) {
                wheatColumnsforSearch.add("PEDIGREE");
            } else {

                if (wheatColumns.contains(col) && (!col.equals("GID"))) {
                    wheatColumnsforSearch.add(tableModel.getColumnName(i));
                }
            }

        }


        int crossColumn = tableModel.findColumn("CROSS NAME");
        if (crossColumn < 0) {
            crossColumn = tableModel.findColumn("PEDIGREE");
        }

        int selHistColumn = tableModel.findColumn("SELECTION HISTORY");

        GermplasmEntriesTableModel.setIsFromCrossInfo(true);

        for (int i = 0; i < tableModel.getRowCount(); i++) {

            int perc = (int) ((i + 1) * 100 / tableModel.getRowCount());

            porcentaje = String.valueOf(perc);
            handle.progress("Porcentaje completado: " + porcentaje + " %");

            String bdid = "";
            String selHist = "";
            String pedigree = "";


            try {
                bdid = listas.get(i).getName1027().getNval();//BCID
            } catch (NullPointerException ex) {
                bdid = "";
            }
            try {
                selHist = listas.get(i).getName1028().getNval();//SELECTION HISTORY
            } catch (NullPointerException ex) {
                selHist = "";
            }

            try {
                pedigree = listas.get(i).getName1029().getNval(); //PEDIGREE
            } catch (NullPointerException ex) {
                pedigree = "";
            }

            try {


                if (crossColumn >= 0) {

                    if (pedigree.isEmpty()) {
                        pedigree = "";
                    }
                    tableModel.setValueAt(pedigree, i, crossColumn);

                }

                if (selHistColumn >= 0) {
                    if (selHist.isEmpty()) {
                        selHist = "";
                    }
                    tableModel.setValueAt(selHist, i, selHistColumn);

                }


            } catch (Exception e) {
                System.out.println("ERROR" + e);
            }
        }
        GermplasmEntriesTableModel.setIsFromCrossInfo(false);
    }
}
