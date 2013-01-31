package ibfb.inventory.core;

import ibfb.domain.core.Factor;
import ibfb.domain.core.GermplasmList;
import ibfb.inventory.export.ExcelTableExporter;
import ibfb.inventory.export.InventoryExcelExporter;
import ibfb.inventory.models.GermplasmEntriesTableModel;
import ibfb.workbook.api.GermplasmAssigmentTool;
import ibfb.workbook.api.GermplasmListReader;
import ibfb.workbook.core.GermplasmAssigmentToolImpl;
import ibfb.workbook.core.GermplasmListReaderImpl;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.cimmyt.cril.ibwb.domain.Listnms;
import org.cimmyt.cril.ibwb.domain.inventory.InventoryData;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;

@ConvertAsProperties(dtd = "-//ibfb.inventory.core//Inventory//EN",
autostore = false)
@TopComponent.Description(preferredID = "InventoryViewerTopComponent",
iconBase = "ibfb/inventory/images/inventory_16.png",
persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "ibfb.inventory.core.InventoryTopComponent")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_InventoryAction",
preferredID = "InventoryTopComponent")

public final class InventoryViewerTopComponent extends TopComponent {

    private JFileChooser selectorArchivo = new JFileChooser();
    List<List<Object>> rowListDB;
    GermplasmAssigmentTool gat = new GermplasmAssigmentToolImpl();
    private List<Factor> factores;
    private List<InventoryData> inventoryDataList;

    public InventoryViewerTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(InventoryViewerTopComponent.class, "CTL_InventoryTopComponent"));
        setToolTipText(NbBundle.getMessage(InventoryViewerTopComponent.class, "CTL_InventoryTopComponent"));
        fillComboListNames();
        this.jButtonExport.setEnabled(false);
        this.jLabelEntries.setText("0");
    }

    private void fillComboListNames() {
       cboGermplasmList.setModel(new javax.swing.DefaultComboBoxModel(new String[] {NbBundle.getMessage(InventoryViewerTopComponent.class, "InventoryViewerTopComponent.selectOne")}));        
        List<Listnms> germplasmList = AppServicesProxy.getDefault().appServices().getListnmsList();
        for (Listnms list : germplasmList) {
            cboGermplasmList.addItem(list);

        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        cboGermplasmList = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEntries = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabelEntries = new javax.swing.JLabel();
        jButtonExport = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        cboGermplasmList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT ONE..." }));
        cboGermplasmList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboGermplasmListItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(InventoryViewerTopComponent.class, "InventoryViewerTopComponent.jLabel1.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboGermplasmList, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cboGermplasmList, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        jTableEntries.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ENTRY", "DESIG", "GID", "LOCATION", "COMMENT", "AMOUNT", "SCALE"
            }
        ));
        jTableEntries.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableEntriesPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(jTableEntries);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(InventoryViewerTopComponent.class, "InventoryViewerTopComponent.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelEntries, org.openide.util.NbBundle.getMessage(InventoryViewerTopComponent.class, "InventoryViewerTopComponent.jLabelEntries.text")); // NOI18N

        jButtonExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/inventory/images/excelScript.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonExport, org.openide.util.NbBundle.getMessage(InventoryViewerTopComponent.class, "InventoryViewerTopComponent.jButtonExport.text")); // NOI18N
        jButtonExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelEntries)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonExport)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabelEntries))
                    .addComponent(jButtonExport))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboGermplasmListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboGermplasmListItemStateChanged
        readGermplsmEntriesFromDb();
    }//GEN-LAST:event_cboGermplasmListItemStateChanged

    private void readGermplsmEntriesFromDb() {

        rowListDB = new ArrayList<List<Object>>();
        boolean validSelection = false;
        GermplasmListReader germplasmListReader = new GermplasmListReaderImpl();
        validSelection = cboGermplasmList.getSelectedIndex() > 0;

        if (validSelection) {
            try {
                Listnms selectedList = (Listnms) cboGermplasmList.getSelectedItem();
                
                this.inventoryDataList = AppServicesProxy.getDefault().appServices().getInventoryDataFromList(selectedList.getListid());
                setGermplasmListIntoTable(inventoryDataList);
//                GermplasmList germplasmList = germplasmListReader.getGermPlasmListFromDB();
//                setGermplasmListIntoTable(germplasmList);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("ERROR AL LEER DB GERMPLASM ENTRIES: " + ex);
            }
        } else {
            GermplasmEntriesTableModel modeloTablaEntries = new GermplasmEntriesTableModel();
            this.jTableEntries.setModel(modeloTablaEntries);

        }


    }

    private void setGermplasmListIntoTable(GermplasmList germplasmList) {
        List<Factor> fact = new ArrayList<Factor>();
        fact = addColumnsForInventory(factores);

        List<String> columnList = gat.getColumnList(fact);
        GermplasmEntriesTableModel tableModel = null;

        rowListDB = gat.getMappedColumns(columnList, germplasmList);
        tableModel = new GermplasmEntriesTableModel(fact, rowListDB);

        this.jTableEntries.setModel(tableModel);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int col = 0; col < jTableEntries.getColumnCount(); col++) {
            jTableEntries.getColumnModel().getColumn(col).setCellRenderer(tcr);
        }
    }
    
    private void setGermplasmListIntoTable(List<InventoryData> inventoryDataList) {
        List<Factor> fact = new ArrayList<Factor>();
        fact = addColumnsForInventory(factores);

        List<String> columnList = gat.getColumnList(fact);
        GermplasmEntriesTableModel tableModel = null;

        List<List<Object>> rowListInventory = new ArrayList<List<Object>>();
           
        for (InventoryData data: inventoryDataList) {
            List<Object> columns = new ArrayList<Object>();
            
            columns.add(data.getEntry());
            columns.add(data.getDesig());
            columns.add(data.getGid());
            columns.add(data.getLocationid()+"-"+data.getLocationName());
            columns.add(data.getComment());
            columns.add(data.getAmmount());
            columns.add(data.getScale()+"-"+data.getScaleName());
            
            rowListInventory.add(columns);
        }   
        
        tableModel = new GermplasmEntriesTableModel(fact, rowListInventory);

        this.jTableEntries.setModel(tableModel);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int col = 0; col < jTableEntries.getColumnCount(); col++) {
            jTableEntries.getColumnModel().getColumn(col).setCellRenderer(tcr);
        }
    }
    
    

    private List<Factor> addColumnsForInventory(List<Factor> factores) {


        factores = new ArrayList<Factor>();

        Factor factor = new Factor();
        factor.setFactorName("ENTRY");
        factor.setProperty("GERMPLASM ENTRY");
        factor.setScale("NUMBER");
        factor.setDataType("N");
        factores.add(factor);

        factor = new Factor();
        factor.setFactorName("DESIG");
        factor.setProperty("GERMPLASM ID");
        factor.setScale("DBCV");
        factor.setDataType("C");
        factores.add(factor);

        factor = new Factor();
        factor.setFactorName("GID");
        factor.setProperty("GERMPLASM ID");
        factor.setScale("DBID");
        factor.setDataType("N");
        factores.add(factor);

        factor = new Factor();
        factor.setFactorName("LOCATION");
        factor.setProperty("GERMPLASM ENTRY");
        factor.setScale("TEXT");
        factor.setDataType("C");
        factores.add(factor);
        
        factor = new Factor();
        factor.setFactorName("COMMENT");
        factor.setProperty("GERMPLASM ENTRY");
        factor.setScale("TEXT");
        factor.setDataType("C");
        factores.add(factor);
        
        factor = new Factor();
        factor.setFactorName("AMOUNT");
        factor.setProperty("GERMPLASM ENTRY");
        factor.setScale("NUMBER");
        factor.setDataType("N");
        factores.add(factor);
        
        
        factor = new Factor();
        factor.setFactorName("SCALE");
        factor.setProperty("GERMPLASM ENTRY");
        factor.setScale("TEXT");
        factor.setDataType("C");
        factores.add(factor);

        return factores;
    }

    private void jTableEntriesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableEntriesPropertyChange
        int entradas = this.jTableEntries.getRowCount();

        this.jLabelEntries.setText(String.valueOf(entradas));
        if (entradas > 0) {
            this.jButtonExport.setEnabled(true);
        } else {
            this.jButtonExport.setEnabled(false);
        }

    }//GEN-LAST:event_jTableEntriesPropertyChange

    private void jButtonExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportActionPerformed

        openFile();

    }//GEN-LAST:event_jButtonExportActionPerformed

    private void exportToExcel(File file) {

        File archivo = new File(file.getAbsolutePath() );
        List<String> nombreTabs = new ArrayList<String>();
        nombreTabs.add("Inventory");

        List<JTable> tables = new ArrayList<JTable>();
        tables.add(this.jTableEntries);

        ExcelTableExporter exporter;
        InventoryExcelExporter inventoryExcelExporter = new InventoryExcelExporter(jTableEntries,archivo.getAbsolutePath(),inventoryDataList);
        try {
            //exporter = new ExcelTableExporter(tables, archivo, nombreTabs);
            inventoryExcelExporter.exportToExcel();
            //if (exporter.export()) {
                DialogUtil.display("Your list was exported");
            //}


        } catch (Exception ex) {
            System.out.println("ERROR AL EXPORTAR TABLA");
        }



    }

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
        selectorArchivo.addChoosableFileFilter(new ExcelFiltro());
        int resultado = selectorArchivo.showSaveDialog(null);

        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }

        exportToExcel(selectorArchivo.getSelectedFile());


    }

    public class ExcelFiltro extends FileFilter {

        @Override
        public boolean accept(File f) {
            return f.getName().toLowerCase().endsWith(".xls") || f.isDirectory();
        }

        @Override
        public String getDescription() {
            return "EXCEL FILE (.xls)";
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboGermplasmList;
    private javax.swing.JButton jButtonExport;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelEntries;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEntries;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
    }

    @Override
    public void componentClosed() {
    }

    void writeProperties(java.util.Properties p) {

        p.setProperty("version", "1.0");
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");

    }
}
