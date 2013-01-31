package ibfb.studyeditor.wizard;

import ibfb.domain.core.Factor;
import ibfb.domain.core.GermplasmList;
import ibfb.domain.core.ListOfEntries;
import ibfb.domain.core.Workbook;
import ibfb.lists.core.SelectListDialog;
import ibfb.query.classes.GermplsmRecord;
import ibfb.query.classes.GpidInfClass;
import ibfb.query.classes.NamesRecord;
import ibfb.query.core.QueryCenter;
import ibfb.settings.core.FieldbookSettings;
import ibfb.studyeditor.core.CROP;
import ibfb.studyeditor.core.StudyEditorTopComponent;
import ibfb.studyeditor.core.model.GermplasmEntriesTableModel;
import ibfb.studyexplorer.filters.ExcelFiltro;
import ibfb.workbook.api.GermplasmAssigmentTool;
import ibfb.workbook.api.GermplasmListReader;
import ibfb.workbook.core.GermplasmAssigmentToolImpl;
import ibfb.workbook.core.GermplasmListReaderImpl;
import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
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

public final class TrialWizardVisualPanel4 extends JPanel {

    private ResourceBundle bundle = NbBundle.getBundle(TrialWizardVisualPanel4.class);
    private JFileChooser selectorArchivo = new JFileChooser();
    private Workbook myWorkbook;
    private Desktop desktop = null;
    private Desktop.Action action = null;
    private File archivo = null;
    private String[] nameColumn = {"Cross Name", "Selection History", "Pedigree", "CID", "SID", "GID", "INTRID", "TID", "ENT", "Folio", "Specific Name", "Name Abbreviation", "Cross Year", "Cross Location", "Cross Country", "Cross Organization", "Cross Program", "FAO In-trust", "Selection Year", "Selection Location", "Selection Country", "Name Country", "Name Year", "FAO designation Date", "24 disp", "25 disp"};
    private ArrayList<String> wheatColumns;
    private ArrayList<String> wheatColumnsforSearch;
    private QueryCenter queryReadCenter;
    private String outSelectionHistory;
    private boolean ready = false;
    private ProgressHandle handle;
    private String porcentaje;
    private StudyEditorTopComponent studyWindow = TrialWizardWizardIterator.studyTopComponent;

    public TrialWizardVisualPanel4() {
        initComponents();
        fillComboListNames();
        checkButtonsStatus();
        
        

        if(studyWindow.getCROP()==CROP.WHEAT){
            loadNamesForWheat();
        }
        
    }

    public void showProgressStatus() {

        handle = ProgressHandleFactory.createHandle(bundle.getString("TrialWizardVisualPanel4.loading"));

        ProgressUtils.showProgressDialogAndRun(new Runnable() {

            @Override
            public void run() {
                porcentaje = "0";
                handle.start(100);
                handle.progress(bundle.getString("TrialWizardVisualPanel4.completado") + porcentaje + " %");
                // completeDataFromDatabase();
                completeFullDataFromDatabase();
            }
        }, handle, true);

    }

    private void loadQueryCenter() {
        queryReadCenter = new QueryCenter();
        queryReadCenter.readAndLoadDatabases();
        queryReadCenter.setFnameKeyEntryNumber("Entry number");
        queryReadCenter.setFnameKeyOcc("occ");
        queryReadCenter.setFnamePedigree("cross name");
        queryReadCenter.readFlexPedConfig();
    }

    private void loadNamesForWheat() {
        wheatColumns = new ArrayList<String>();
        for (int i = 0; i < nameColumn.length; i++) {
            wheatColumns.add(nameColumn[i].toUpperCase());
        }
    }

    @Override
    public String getName() {
        return bundle.getString("TrialWizardVisualPanel4.title");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupGMS = new javax.swing.ButtonGroup();
        pnlSelectGermplasm = new javax.swing.JPanel();
        lblImgExcel = new javax.swing.JLabel();
        jButtonSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaPath = new javax.swing.JTextArea();
        radGermplasmFromTemplate = new javax.swing.JRadioButton();
        radGermplasmFromDB1 = new javax.swing.JRadioButton();
        cboGermplasmList = new javax.swing.JComboBox();
        lblImgDb = new javax.swing.JLabel();
        jButtonPreview = new javax.swing.JButton();
        btnSearchList = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableEntries = new javax.swing.JTable();
        lblTotalEntries = new javax.swing.JLabel();
        jTextFieldTotalEntries = new javax.swing.JTextField();

        pnlSelectGermplasm.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel4.class, "TrialWizardVisualPanel4.pnlSelectGermplasm.border.title"))); // NOI18N

        lblImgExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/excelFile.png"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButtonSearch, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel4.class, "TrialWizardVisualPanel4.jButtonSearch.text")); // NOI18N
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
        org.openide.awt.Mnemonics.setLocalizedText(radGermplasmFromTemplate, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel4.class, "TrialWizardVisualPanel4.radGermplasmFromTemplate.text")); // NOI18N
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
        org.openide.awt.Mnemonics.setLocalizedText(radGermplasmFromDB1, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel4.class, "TrialWizardVisualPanel4.radGermplasmFromDB1.text")); // NOI18N
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

        lblImgDb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/database.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblImgDb, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel4.class, "TrialWizardVisualPanel4.lblImgDb.text")); // NOI18N

        jButtonPreview.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/preview.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonPreview, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel4.class, "TrialWizardVisualPanel4.jButtonPreview.text")); // NOI18N
        jButtonPreview.setToolTipText(org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel4.class, "TrialWizardVisualPanel4.jButtonPreview.toolTipText")); // NOI18N
        jButtonPreview.setEnabled(false);
        jButtonPreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPreviewActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnSearchList, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel4.class, "TrialWizardVisualPanel4.btnSearchList.text")); // NOI18N
        btnSearchList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSelectGermplasmLayout = new javax.swing.GroupLayout(pnlSelectGermplasm);
        pnlSelectGermplasm.setLayout(pnlSelectGermplasmLayout);
        pnlSelectGermplasmLayout.setHorizontalGroup(
            pnlSelectGermplasmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSelectGermplasmLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSelectGermplasmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radGermplasmFromDB1)
                    .addComponent(radGermplasmFromTemplate))
                .addGroup(pnlSelectGermplasmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSelectGermplasmLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblImgExcel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSelectGermplasmLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(lblImgDb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboGermplasmList, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearchList)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlSelectGermplasmLayout.setVerticalGroup(
            pnlSelectGermplasmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelectGermplasmLayout.createSequentialGroup()
                .addGroup(pnlSelectGermplasmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSelectGermplasmLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(radGermplasmFromDB1))
                    .addGroup(pnlSelectGermplasmLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblImgDb))
                    .addGroup(pnlSelectGermplasmLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(pnlSelectGermplasmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboGermplasmList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearchList))))
                .addGap(18, 18, 18)
                .addGroup(pnlSelectGermplasmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                    .addComponent(jButtonPreview, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                    .addGroup(pnlSelectGermplasmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblImgExcel)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSelectGermplasmLayout.createSequentialGroup()
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

        org.openide.awt.Mnemonics.setLocalizedText(lblTotalEntries, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel4.class, "TrialWizardVisualPanel4.lblTotalEntries.text")); // NOI18N

        jTextFieldTotalEntries.setEditable(false);
        jTextFieldTotalEntries.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldTotalEntries.setText(org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel4.class, "TrialWizardVisualPanel4.jTextFieldTotalEntries.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(510, Short.MAX_VALUE)
                .addComponent(lblTotalEntries)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldTotalEntries, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlSelectGermplasm, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(399, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalEntries)
                    .addComponent(jTextFieldTotalEntries, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlSelectGermplasm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addGap(66, 66, 66)))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTextFieldTotalEntries, lblTotalEntries});

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
//        if (evt.getStateChange() == 1) {
//            this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
//            readGermplsmEntriesFromDb();
//            if (isForWheat) {
//                completeDataFromDatabase();
//            }
//            this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
//        }
}//GEN-LAST:event_cboGermplasmListItemStateChanged

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
                    //System.out.println("EL CROSSNAME: " + outCrossName);
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

    private void jButtonPreviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPreviewActionPerformed
        previewFile(this.jTextAreaPath.getText());
}//GEN-LAST:event_jButtonPreviewActionPerformed

    private void cboGermplasmListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGermplasmListActionPerformed
        if (!ready) {
            return;
        }

        boolean validSelection = cboGermplasmList.getSelectedIndex() > 0;
        if (validSelection) {

            readGermplsmEntriesFromDb();

            if(studyWindow.getCROP()==CROP.WHEAT){
                showProgressStatus();
            }
        }
    }//GEN-LAST:event_cboGermplasmListActionPerformed

    private void btnSearchListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchListActionPerformed
        searchList();
    }//GEN-LAST:event_btnSearchListActionPerformed

    private void fillComboListNames() {


        cboGermplasmList.setModel(new DefaultComboBoxModel(new String[]{NbBundle.getMessage(TrialWizardVisualPanel4.class, "TrialWizardVisualPanel4.selectOne")}));


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
                ex.printStackTrace();
                System.out.println("ERROR AL LEER EXCEL GERMPLASM ENTRIES: " + ex);
            }
        } else {
            GermplasmEntriesTableModel modeloTablaEntries = new GermplasmEntriesTableModel();
            this.jTableEntries.setModel(modeloTablaEntries);
            this.jTextAreaPath.setText("");
            this.jTextFieldTotalEntries.setText("0");
            if (radGermplasmFromDB1.isSelected()) {
                //   DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message("Please choose a Germplasm List", NotifyDescriptor.ERROR_MESSAGE));
                DialogUtil.displayError(bundle.getString("TrialWizardVisualPanel4.pleaseChooseAGermplasmList"));
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

       
        
        String customPath="";//NbPreferences.forModule(TrialWizardVisualPanel4.class).get("customPathTWVP4", "");        
        File myDesktop=null;
        if(!customPath.isEmpty()){
            myDesktop = new File(customPath);    
        }else{
            myDesktop = new File(FieldbookSettings.getSetting(FieldbookSettings.GERMPLASM_LIST_DEFAULT_FOLDER));  
        }       
        
       
        
        
        if (myDesktop.exists()) {
            selectorArchivo.setCurrentDirectory(myDesktop);
        } else {
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
        
        //NbPreferences.forModule(TrialWizardVisualPanel4.class).put("customPathTWVP4", selectorArchivo.getSelectedFile().toString());

        this.jTextAreaPath.setText(selectorArchivo.getSelectedFile().toString());

    }

    public void copyValues() {
        GermplasmEntriesTableModel tableModel = (GermplasmEntriesTableModel) this.jTableEntries.getModel();
        studyWindow.assignGermplasmEntries(tableModel.getFactorHeaders(), tableModel.getGermplasmData());

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
            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(bundle.getString("TrialWizardVisualPanel4.notValidentriesFile"), NotifyDescriptor.ERROR_MESSAGE));
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearchList;
    private javax.swing.ButtonGroup buttonGroupGMS;
    private javax.swing.JComboBox cboGermplasmList;
    private javax.swing.JButton jButtonPreview;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableEntries;
    private javax.swing.JTextArea jTextAreaPath;
    private javax.swing.JTextField jTextFieldTotalEntries;
    private javax.swing.JLabel lblImgDb;
    private javax.swing.JLabel lblImgExcel;
    private javax.swing.JLabel lblTotalEntries;
    private javax.swing.JPanel pnlSelectGermplasm;
    private javax.swing.JRadioButton radGermplasmFromDB1;
    private javax.swing.JRadioButton radGermplasmFromTemplate;
    // End of variables declaration//GEN-END:variables

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
