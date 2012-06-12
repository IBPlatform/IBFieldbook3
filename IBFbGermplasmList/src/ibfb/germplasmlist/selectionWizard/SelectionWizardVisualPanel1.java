package ibfb.germplasmlist.selectionWizard;

import ibfb.germplasmlist.filters.ExcelFiltro;
import ibfb.settings.core.FieldbookSettings;
import java.awt.Component;
import java.awt.Cursor;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.domain.Germplsm;
import org.cimmyt.cril.ibwb.domain.Names;

public final class SelectionWizardVisualPanel1 extends JPanel {

    private JFileChooser selectorArchivo = new JFileChooser();
    public int COL_TID = 0;
    public int COL_OCC = 1;
    public int COL_ENT = 2;
    public int COL_SEL = 3;
    public int COL_DESIG = 3;
    public int COL_GID = 4;
    public int COL_SELECTION = 5;

    public SelectionWizardVisualPanel1() {
        initComponents();
    }

    public JLabel getjLabelRows() {
        return jLabelRows;
    }

    public void setjLabelRows(JLabel jLabelRows) {
        this.jLabelRows = jLabelRows;
    }

    @Override
    public String getName() {
        return "Selection Excel File";
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaPath = new javax.swing.JTextArea();
        jButtonBrowse = new javax.swing.JButton();
        jScrollEntiresExcelFemaleScript = new javax.swing.JScrollPane();
        jTableEntries = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabelRows = new javax.swing.JLabel();

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/excelFile.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(SelectionWizardVisualPanel1.class, "SelectionWizardVisualPanel1.jLabel10.text")); // NOI18N

        jTextAreaPath.setColumns(20);
        jTextAreaPath.setEditable(false);
        jTextAreaPath.setLineWrap(true);
        jTextAreaPath.setRows(5);
        jTextAreaPath.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextAreaPathMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(jTextAreaPath);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonBrowse, org.openide.util.NbBundle.getMessage(SelectionWizardVisualPanel1.class, "SelectionWizardVisualPanel1.jButtonBrowse.text")); // NOI18N
        jButtonBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBrowseActionPerformed(evt);
            }
        });

        jTableEntries.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TID", "OCC", "ENTRY", "DESIG", "GID", "SELECTION"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableEntries.setDragEnabled(true);
        jTableEntries.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jTableEntries.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableEntriesPropertyChange(evt);
            }
        });
        jScrollEntiresExcelFemaleScript.setViewportView(jTableEntries);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(SelectionWizardVisualPanel1.class, "SelectionWizardVisualPanel1.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelRows, org.openide.util.NbBundle.getMessage(SelectionWizardVisualPanel1.class, "SelectionWizardVisualPanel1.jLabelRows.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollEntiresExcelFemaleScript, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(414, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelRows)
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, 0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollEntiresExcelFemaleScript, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelRows))
                .addGap(15, 15, 15))
        );
    }// </editor-fold>//GEN-END:initComponents

    public JTable getjTableEntries() {
        return jTableEntries;
    }

    public void setjTableEntries(JTable jTableEntries) {
        this.jTableEntries = jTableEntries;
    }

    private void jTextAreaPathMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaPathMousePressed
        openFile();
        if (this.jTextAreaPath.getText().isEmpty() == false) {
            readExcelScript(this.jTextAreaPath.getText());
        }
    }//GEN-LAST:event_jTextAreaPathMousePressed

    private void jButtonBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBrowseActionPerformed
        openFile();
        if (this.jTextAreaPath.getText().isEmpty() == false) {
            readExcelScript(this.jTextAreaPath.getText());
        }
    }//GEN-LAST:event_jButtonBrowseActionPerformed

    private void jTableEntriesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableEntriesPropertyChange
        this.jLabelRows.setText(String.valueOf(this.jTableEntries.getRowCount()));
        
    }//GEN-LAST:event_jTableEntriesPropertyChange
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBrowse;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabelRows;
    private javax.swing.JScrollPane jScrollEntiresExcelFemaleScript;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableEntries;
    private javax.swing.JTextArea jTextAreaPath;
    // End of variables declaration//GEN-END:variables

    private void openFile() {
        FileFilter[] filtros = new FileFilter[10];
        filtros = selectorArchivo.getChoosableFileFilters();

        for (int i = 0; i < filtros.length; i++) {
            FileFilter filtro = filtros[i];
            selectorArchivo.removeChoosableFileFilter(filtro);
        }

        File myDesktop = new File(FieldbookSettings.getSetting(FieldbookSettings.GERMPLASM_LIST_DEFAULT_FOLDER));

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

        DefaultTableModel mod = (DefaultTableModel) this.jTableEntries.getModel();
        mod.setRowCount(0);



        if (isValidFile(selectorArchivo.getSelectedFile())) {
            this.jTextAreaPath.setText(selectorArchivo.getSelectedFile().toString());
        } else {
            this.jTextAreaPath.setText("");
        }



    }

    private void readExcelScript(String fileName) {

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {

            boolean moreRowsToRead = true;
            int rowIndex = 0;
            int gms = 0;


            try {


                InputStream inputStream = new FileInputStream(fileName);
                HSSFWorkbook excelBook = new HSSFWorkbook(inputStream);

                HSSFSheet sheet = excelBook.getSheetAt(0);
                HSSFCell cellData;
                HSSFRow rowData;

                while (moreRowsToRead) {
                    rowIndex++;
                    rowData = sheet.getRow(rowIndex);

                    moreRowsToRead = isMoreRows(rowData);

                    if (rowData == null ) {
                        break;
                    }
                    
                    cellData = rowData.getCell(COL_TID);
                    int tid = getIntValueFromCell(cellData);

                    cellData = rowData.getCell(COL_OCC);
                    int occ = getIntValueFromCell(cellData);

                    cellData = rowData.getCell(COL_ENT);
                    int ent = getIntValueFromCell(cellData);

                    cellData = rowData.getCell(COL_SEL);
                    int sel = getIntValueFromCell(cellData);

                    DefaultTableModel modelo = (DefaultTableModel) this.jTableEntries.getModel();

                    Germplsm germplsm = AppServicesProxy.getDefault().appServices().getGermplsmByTidTrialPlot(tid, occ, ent);




                    if (germplsm != null) {

                        Names filtro = new Names(true);
                        filtro.setGid(germplsm.getGid());
                        List<Names> lista = AppServicesProxy.getDefault().appServices().getListNames(filtro, 0, 0, false);



                        gms++;
                        modelo.setRowCount(gms);

                        System.out.println("Germplasm vale : " + germplsm.getGid() + " cid: " + germplsm.getCid());
                        this.jTableEntries.setValueAt(tid, gms - 1, COL_TID);
                        this.jTableEntries.setValueAt(occ, gms - 1, COL_OCC);
                        this.jTableEntries.setValueAt(ent, gms - 1, COL_ENT);
                        this.jTableEntries.setValueAt(lista.get(0).getNval(), gms - 1, COL_DESIG);
                        this.jTableEntries.setValueAt(germplsm.getGid(), gms - 1, COL_GID);

                        this.jTableEntries.setValueAt(sel, gms - 1, COL_SELECTION);

                        System.out.println(tid + "  " + occ + "  " + ent + "  " + sel);

                    } else {
                        System.out.println("GID no encontrado");
                    }


                    if (!moreRowsToRead) {
                        break;
                    }


                    ajustaColumnas();
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ERROR: " + e);
            }

        } finally {
            this.setCursor(null);
        }
    }

    private boolean isMoreRows(HSSFRow rowData) {
        boolean result = true;

        if (rowData == null) {
            return false;
        }

        if (rowData.getCell(COL_TID) == null) {
            return false;
        }

        String value = String.valueOf(rowData.getCell(COL_TID).getNumericCellValue());

        if (value == null) {
            result = false;
        } else if (value.trim().isEmpty()) {
            result = false;
        }

        return result;
    }

    private Integer getIntValueFromCell(HSSFCell cellData) {
        Integer result = 0;

        String cellValue = null;

        switch (cellData.getCellType()) {

            case HSSFCell.CELL_TYPE_STRING:
                cellValue = cellData.getStringCellValue();
                break;

            case HSSFCell.CELL_TYPE_NUMERIC:
                cellValue = String.valueOf((int) cellData.getNumericCellValue());
                break;
        }



        if (cellValue == null) {
            result = null;
        } else {
            try {
                result = Integer.parseInt(cellValue);
            } catch (Exception e) {
                result = null;
            }
        }

        return result;
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

    private void ajustaColumnas() {
        for (int i = 0; i < 3; i++) {
            ajustaColumnsTable(this.jTableEntries, 2);
        }

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        for (int col = 0; col < this.jTableEntries.getColumnCount(); col++) {
            this.jTableEntries.getColumnModel().getColumn(col).setCellRenderer(tcr);
        }

        this.jTableEntries.getColumnModel().getColumn(3).setCellRenderer(tcr2);

    }

    

    private boolean isValidFile(File selectedFile) {
        return true;
    }
}
