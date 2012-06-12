
package ibfb.nursery.mainwizard;


import ibfb.domain.core.Factor;
import ibfb.domain.core.Workbook;
import ibfb.nursery.utils.ColumnFitAdapter;
import ibfb.nursery.editors.SpinnerEditor;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.utils.ToolTipUtils;


public final class NurseryVisualPanel7 extends JPanel {
 
    public NurseryVisualPanel7() {
        initComponents();
        DefaultTableCellRenderer tcrCenter = new DefaultTableCellRenderer();
        tcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
        this.jTableFactors.getColumnModel().getColumn(3).setCellRenderer(tcrCenter);
        this.jTableFactors.getTableHeader().addMouseListener(new ColumnFitAdapter());
        JComboBox comboBoxLevels = new JComboBox();
        for (int i = 0; i < 20; i++) {
            comboBoxLevels.addItem(i + 1);
        }
        comboBoxLevels.setSelectedItem(comboBoxLevels.getItemAt(0));
        TableColumn levColumn = this.jTableFactors.getColumnModel().getColumn(3);
                levColumn.setCellEditor(new DefaultCellEditor(comboBoxLevels));       
        createBallonTips();
        
    }

     
    @Override
    public String getName() {
        return "Other Treatment Factors";
    }
     public void StopEditor() {
        if (this.jTableFactors.getCellEditor() != null) {
            this.jTableFactors.getCellEditor().stopCellEditing();
        }
    }

    private void assignCellEditor() {
        SpinnerEditor spinnerEditor = new SpinnerEditor();
        TableColumn valueColumn = this.jTableFactors.getColumnModel().getColumn(3);
        valueColumn.setCellEditor(spinnerEditor);
    }

    @SuppressWarnings("unchecked")
    public void fillData(final Workbook workbook) {

        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla = (DefaultTableModel) this.jTableFactors.getModel();
        java.util.ArrayList<Factor> factores = new ArrayList();
        factores = (java.util.ArrayList<Factor>) workbook.getOtherFactors();
        if (NurseryWizardIterator.existenFactores == false) {
            modeloTabla.setNumRows(0);
            return;
        }
        modeloTabla.setNumRows(workbook.getOtherFactors().size());
        int renglon = 0;
        for (int i = 0; i < factores.size(); i++) {
            this.jTableFactors.setValueAt(factores.get(i).getFactorName(), renglon, 0);
            this.jTableFactors.setValueAt(factores.get(i).getDescription(), renglon, 1);
            this.jTableFactors.setValueAt(factores.get(i).getScale(), renglon, 2);
            this.jTableFactors.setValueAt(1, renglon, 3);
            renglon++;
        }

    
    }

    private void createBallonTips() {
        BalloonTip tip = new BalloonTip(jTableFactors, "Specify the number of levels for the following treatment factors");
        ToolTipUtils.balloonToToolTip(tip, 500, 3000);
    }

    public void copyLevels() {

        NurseryWizardIterator.levels = new int[jTableFactors.getRowCount()];

        int cont = this.jTableFactors.getRowCount() - 1;

        for (int i = 0; i < this.jTableFactors.getRowCount(); i++) {
            int levels = Integer.parseInt(this.jTableFactors.getValueAt(i, 3).toString());
            NurseryWizardIterator.levels[cont] = levels;
            cont--;
        }



    }

     @SuppressWarnings("unchecked")
    public boolean tenemosLabels(final Workbook workbook) {
        boolean tenemos = false;
        int total = 0;

        java.util.ArrayList<Factor> factores = new ArrayList();
        factores = (java.util.ArrayList<Factor>) workbook.getOtherFactors();

        for (String otherFactor : workbook.getChildFactors().keySet()) {

            for (Factor factor : workbook.getChildFactors().get(otherFactor)) {
                total++;
            }
        }

        if (total > 0) {
            tenemos = true;
            NurseryWizardIterator.existenLabels = true;
        } else {
            tenemos = false;
           NurseryWizardIterator.existenLabels = false;
        }
        return tenemos;

    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFactors = new javax.swing.JTable();

        jTableFactors.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Factor", "Description", "Scale", "Number of levels"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableFactors);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableFactors;
    // End of variables declaration//GEN-END:variables
}
