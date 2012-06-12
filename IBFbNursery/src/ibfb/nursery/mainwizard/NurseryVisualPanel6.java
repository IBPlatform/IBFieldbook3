package ibfb.nursery.mainwizard;

import ibfb.domain.core.Factor;
import ibfb.domain.core.GermplasmList;
import ibfb.domain.core.ListOfEntries;
import ibfb.domain.core.Workbook;
import ibfb.nursery.models.GermplasmEntriesTableModel;
import ibfb.workbook.api.GermplasmAssigmentTool;
import ibfb.workbook.api.GermplasmListReader;
import ibfb.workbook.core.GermplasmAssigmentToolImpl;
import ibfb.workbook.core.GermplasmListReaderImpl;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.domain.Listnms;

public final class NurseryVisualPanel6 extends JPanel {

    private Workbook myWorkbook;
    private int index = 0;

    public NurseryVisualPanel6() {
        initComponents();

    }

    @Override
    public String getName() {
        return "ADD CHECKS";
    }

    public Workbook getMyWorkbook() {
        return myWorkbook;
    }

    public void setMyWorkbook(Workbook myWorkbook) {
        this.myWorkbook = myWorkbook;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cboGermplasmList = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableEntriesDB = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldTotalEntries = new javax.swing.JTextField();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/database.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(NurseryVisualPanel6.class, "NurseryVisualPanel6.jLabel1.text")); // NOI18N

        cboGermplasmList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT ONE..." }));
        cboGermplasmList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboGermplasmListItemStateChanged(evt);
            }
        });

        jTableEntriesDB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTableEntriesDB);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(NurseryVisualPanel6.class, "NurseryVisualPanel6.jLabel2.text")); // NOI18N

        jTextFieldTotalEntries.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldTotalEntries.setText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel6.class, "NurseryVisualPanel6.jTextFieldTotalEntries.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboGermplasmList, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTotalEntries, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(cboGermplasmList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTotalEntries, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboGermplasmListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboGermplasmListItemStateChanged

        readGermplsmEntriesFromDb(this.jTableEntriesDB);     }//GEN-LAST:event_cboGermplasmListItemStateChanged

    public void fillComboListNames() {


        int count = 0;

        List<Listnms> germplasmList = AppServicesProxy.getDefault().appServices().getListnmsList();

        if (NurseryWizardIterator.isNewList) {

            for (Listnms list : germplasmList) {
                cboGermplasmList.addItem(list);
                if (list.getListname().equals(NurseryWizardIterator.myList)) {
                    index = count;
                }
                count++;
            }
            cboGermplasmList.setSelectedIndex(index + 1);
            
        } else {
            for (Listnms list : germplasmList) {
                cboGermplasmList.addItem(list);
            }
        }

        
    }

    public JTextField getjTextFieldTotalEntries() {
        return jTextFieldTotalEntries;
    }

    public void setjTextFieldTotalEntries(JTextField jTextFieldTotalEntries) {
        this.jTextFieldTotalEntries = jTextFieldTotalEntries;
    }

    public void copyValues() {

        if (this.cboGermplasmList.getSelectedIndex() > 0) {
            GermplasmEntriesTableModel tableModel = (GermplasmEntriesTableModel) this.jTableEntriesDB.getModel();
            NurseryWizardIterator.nurseryTopComponent.assignGermplasmEntries(tableModel.getFactorHeaders(), tableModel.getGermplasmData());
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboGermplasmList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableEntriesDB;
    private javax.swing.JTextField jTextFieldTotalEntries;
    // End of variables declaration//GEN-END:variables

    private void readGermplsmEntriesFromDb(JTable tabla) {
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
            tabla.setModel(modeloTablaEntries);

            this.jTextFieldTotalEntries.setText("0");

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
        this.jTableEntriesDB.setModel(tableModel);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int col = 0; col < this.jTableEntriesDB.getColumnCount(); col++) {

            this.jTableEntriesDB.getColumnModel().getColumn(col).setCellRenderer(tcr);
        }
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
}
