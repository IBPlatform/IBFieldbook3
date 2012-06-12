
package ibfb.nursery.mainwizard;

import ibfb.domain.core.Factor;
import ibfb.domain.core.Workbook;
import ibfb.nursery.core.NurseryEditorTopComponent;
import ibfb.nursery.utils.ColumnFitAdapter;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.utils.ToolTipUtils;


public final class NurseryVisualPanel8 extends JPanel {

  private int rowsTotales = 0;
    private ArrayList<String> otherFactors = new ArrayList<String>();
    private ArrayList<String> children = new ArrayList<String>();

    
    public NurseryVisualPanel8() {
        initComponents();
        DefaultTableCellRenderer tcrCenter = new DefaultTableCellRenderer();
        tcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
        this.jTableFactors.getColumnModel().getColumn(2).setCellRenderer(tcrCenter);
        this.jTableFactors.getTableHeader().addMouseListener(new ColumnFitAdapter());
        NurseryEditorTopComponent nurseryWindow = NurseryWizardIterator.nurseryTopComponent;
        createBallonTips();
        
    }


    @Override
    public String getName() {
        return "Factor Labels";
    }
    
  
    public int getRowsTotales() {
        return rowsTotales;
    }

    public void setRowsTotales(int rowsTotales) {
        this.rowsTotales = rowsTotales;
    }  
   

    public void StopEditor() {
        if (this.jTableFactors.getCellEditor() != null) {
            this.jTableFactors.getCellEditor().stopCellEditing();
        }
    }

@SuppressWarnings("unchecked")
    public void fillData(final Workbook workbook) {


        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla = (DefaultTableModel) this.jTableFactors.getModel();
        modeloTabla.setRowCount(0);

        int totales = NurseryWizardIterator.levels.length;
        java.util.ArrayList<Factor> factores = new ArrayList();
        factores = (java.util.ArrayList<Factor>) workbook.getOtherFactors();
        int renglon = 0;
        int labelNum = 0;
        int factornum = 0;

        for (String otherFactor : workbook.getChildFactors().keySet()) {

            int nivel = 0;

            for (Factor factor : workbook.getChildFactors().get(otherFactor)) {


                for (int i = 0; i < NurseryWizardIterator.levels[labelNum]; i++) {

                    Object[] fila = new Object[3];
                    modeloTabla.addRow(fila);
                    nivel++;
                    this.jTableFactors.setValueAt(otherFactor, renglon, 0);
                    this.jTableFactors.setValueAt(factor.getFactorName() + " " + nivel, renglon, 1);
                    this.jTableFactors.setValueAt(nivel, renglon, 2);

                    if (!otherFactors.contains(otherFactor)) {
                        otherFactors.add(otherFactor);
                    }

                    if (!children.contains(factor.getFactorName())) {
                        children.add(factor.getFactorName());
                    }

                    renglon++;
                }
            }
            labelNum++;
        }



    }

    public void generateCombinations() {
        int totalFactores = NurseryWizardIterator.levels.length;
        int totalRowsToAdd = 1;

        int cont = 0;

        int repet[] = new int[totalFactores];


        for (int i = 0; i < totalFactores; i++) {
            totalRowsToAdd = totalRowsToAdd * NurseryWizardIterator.levels[i];
        }

        //System.out.println("TOTAL ROWS: "+totalRowsToAdd);  

        repet[totalFactores - 1] = 1;
        cont = totalFactores - 1;
        for (int i = 0; i < totalFactores - 1; i++) {
            repet[cont - 1] = NurseryWizardIterator.levels[cont] * repet[cont];
            cont--;
        }



        for (int i = 0; i < totalFactores; i++) {
            System.out.println("REP: " + repet[i]);

        }



        int[][] factorsDesign = new int[totalFactores][totalRowsToAdd];
        String[][] factorsDesignCad = new String[totalFactores][totalRowsToAdd];


        for (int i = 0; i < totalFactores; i++) {
            int contador = 0;
            int index = 1;

            for (int j = 0; j < totalRowsToAdd; j++) {


                if (i == totalFactores - 1) {
                    factorsDesign[i][j] = index;
                    factorsDesignCad[i][j] = giveMeLabel(i, index);
                    index++;
                    if (index > NurseryWizardIterator.levels[i]) {
                        index = 1;
                    }

                    continue;
                }

                if (contador < repet[i]) {
                    contador++;
                } else {
                    contador = 1;

                    if (index < NurseryWizardIterator.levels[i]) {
                        index++;
                    } else {
                        index = 1;
                    }
                }

                factorsDesign[i][j] = index;
                factorsDesignCad[i][j] = giveMeLabel(i, index);

            }

        }




        for (int j = 0; j < totalRowsToAdd; j++) {
            for (int i = 0; i < totalFactores; i++) {
                System.out.print(factorsDesign[i][j] + " ");

            }
            System.out.println("");

        }



        for (int j = 0; j < totalRowsToAdd; j++) {
            for (int i = 0; i < totalFactores; i++) {
                System.out.print(factorsDesignCad[i][j] + " ");

            }
            System.out.println("");
        }


        NurseryEditorTopComponent nurseryWindow = NurseryWizardIterator.nurseryTopComponent;
        nurseryWindow.setCombinations(totalRowsToAdd);
       //nurseryWindow.setFactorsDesignCad(factorsDesignCad);
        nurseryWindow.setOtherFactors(otherFactors);
        nurseryWindow.setChildren(children);
    }

    public String giveMeLabel(int i, int index) {

        String labelFactor = "";


        for (int j = 0; j < this.jTableFactors.getRowCount(); j++) {

            if (jTableFactors.getValueAt(j, 0).equals(otherFactors.get(i))
                    && (jTableFactors.getValueAt(j, 2).equals(index))) {

                labelFactor = jTableFactors.getValueAt(j, 1).toString();
                break;
            }

        }

        return labelFactor;


    }

    @SuppressWarnings("unchecked")
    public void copyValues(final Workbook workbook) {

        NurseryEditorTopComponent nurseryWindow = NurseryWizardIterator.nurseryTopComponent;
        DefaultTableModel modeloTabla = new DefaultTableModel();
       // modeloTabla = (DefaultTableModel) nurseryWindow.jTableFactors.getModel();
        modeloTabla.setRowCount(0);

        DefaultTableModel modeloTablaFactores = new DefaultTableModel();
        modeloTablaFactores = (DefaultTableModel) this.jTableFactors.getModel();


        if (rowsTotales < 1) {
            return;
        }

        int totales = NurseryWizardIterator.levels.length;
        java.util.ArrayList<Factor> factores = new ArrayList();
        factores = (java.util.ArrayList<Factor>) workbook.getOtherFactors();
        int renglon = 0;



        for (String otherFactor : workbook.getChildFactors().keySet()) {
            int nivel = 0;
            int labelNum = 0;
            for (Factor factor : workbook.getChildFactors().get(otherFactor)) {

                for (int i = 0; i < NurseryWizardIterator.levels[labelNum]; i++) {

                    Object[] fila = new Object[3];
                    modeloTabla.addRow(fila);
                    nivel++;
                    this.jTableFactors.setValueAt(otherFactor, renglon, 0);
                    this.jTableFactors.setValueAt(factor.getFactorName(), renglon, 1);
                    this.jTableFactors.setValueAt(nivel, renglon, 2);
                    renglon++;
                }
            }
            labelNum++;
        }


        for (int i = 0; i < rowsTotales; i++) {
        }


    }

    private void createBallonTips() {
        BalloonTip tip = new BalloonTip(jTableFactors, "Specify the labels for each treatment factors");
        ToolTipUtils.balloonToToolTip(tip, 500, 3000);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenuSelect = new javax.swing.JPopupMenu();
        jPopupMenuUnSelect = new javax.swing.JPopupMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFactors = new javax.swing.JTable();

        jTableFactors.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Factor", "Labell", "Level"
            }
        ));
        jScrollPane1.setViewportView(jTableFactors);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu jPopupMenuSelect;
    private javax.swing.JPopupMenu jPopupMenuUnSelect;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableFactors;
    // End of variables declaration//GEN-END:variables
}
