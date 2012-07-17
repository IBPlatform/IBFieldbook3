package ibfb.studyeditor.wizard;

import ibfb.domain.core.Factor;
import ibfb.domain.core.FactorLabel;
import ibfb.domain.core.Workbook;
import ibfb.studyeditor.core.StudyEditorTopComponent;
import ibfb.studyeditor.roweditors.ColumnFitAdapter;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.utils.ToolTipUtils;
import ibfb.studyeditor.core.model.TreatmentLabelsTableModel;
import java.awt.Color;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.table.TableColumn;
import org.openide.util.NbBundle;

public final class TrialWizardVisualPanel6 extends JPanel {

    private ResourceBundle bundle = NbBundle.getBundle(TrialWizardVisualPanel6.class);
    private int rowsTotales = 0;
    private ArrayList<String> otherFactors = new ArrayList<String>();
    private ArrayList<String> children = new ArrayList<String>();

    public TrialWizardVisualPanel6() {
        initComponents();
        DefaultTableCellRenderer tcrCenter = new DefaultTableCellRenderer();
        tcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
        this.jTableFactors.getColumnModel().getColumn(2).setCellRenderer(tcrCenter);
        this.jTableFactors.getTableHeader().addMouseListener(new ColumnFitAdapter());
        StudyEditorTopComponent studyWindow = TrialWizardWizardIterator.studyTopComponent;
        createBallonTips();
    }

    public int getRowsTotales() {
        return rowsTotales;
    }

    public void setRowsTotales(int rowsTotales) {
        this.rowsTotales = rowsTotales;
    }

    @Override
    public String getName() {
        return bundle.getString("TrialWizardVisualPanel6.title");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFactors = new javax.swing.JTable();

        jTableFactors.setModel(new ibfb.studyeditor.core.model.TreatmentLabelsTableModel());
        jScrollPane1.setViewportView(jTableFactors);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableFactors;
    // End of variables declaration//GEN-END:variables

    public void StopEditor() {
        if (this.jTableFactors.getCellEditor() != null) {
            this.jTableFactors.getCellEditor().stopCellEditing();
        }
    }

    @SuppressWarnings("unchecked")
    public void fillData(final Workbook workbook) {

        List<FactorLabel> factorLabels = new ArrayList<FactorLabel>();
        TreatmentLabelsTableModel treatmentLabelsTableModel = new TreatmentLabelsTableModel(factorLabels, workbook);
        jTableFactors.setModel(treatmentLabelsTableModel);
        TableColumn columnValue = jTableFactors.getColumnModel().getColumn(3);
        DefaultTableCellRenderer valueCellRenderer = new DefaultTableCellRenderer();
        valueCellRenderer.setToolTipText(bundle.getString("TrialWizardVisualPanel6.fillThisValue"));
        valueCellRenderer.setBackground(Color.YELLOW);
        columnValue.setCellRenderer(valueCellRenderer);

    }

    public void generateCombinations() {
        int totalFactores = TrialWizardWizardIterator.levels.length;
        int totalRowsToAdd = 1;

        int cont = 0;

        int repet[] = new int[totalFactores];


        for (int i = 0; i < totalFactores; i++) {
            totalRowsToAdd = totalRowsToAdd * TrialWizardWizardIterator.levels[i];
        }

        //System.out.println("TOTAL ROWS: "+totalRowsToAdd);  

        repet[totalFactores - 1] = 1;
        cont = totalFactores - 1;
        for (int i = 0; i < totalFactores - 1; i++) {
            repet[cont - 1] = TrialWizardWizardIterator.levels[cont] * repet[cont];
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
                    if (index > TrialWizardWizardIterator.levels[i]) {
                        index = 1;
                    }

                    continue;
                }

                if (contador < repet[i]) {
                    contador++;
                } else {
                    contador = 1;

                    if (index < TrialWizardWizardIterator.levels[i]) {
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


        StudyEditorTopComponent studyWindow = TrialWizardWizardIterator.studyTopComponent;
        studyWindow.setCombinations(totalRowsToAdd);
        studyWindow.setFactorsDesignCad(factorsDesignCad);
        studyWindow.setOtherFactors(otherFactors);
        studyWindow.setChildren(children);
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

    public void copyValues(final Workbook workbook) {

        StudyEditorTopComponent studyWindow = TrialWizardWizardIterator.studyTopComponent;
        TreatmentLabelsTableModel tableModel = (TreatmentLabelsTableModel) jTableFactors.getModel();
        studyWindow.assignOtherTreatmentFactorLabels(tableModel.getFactorLabels());
    }

    private void createBallonTips() {
        BalloonTip tip = new BalloonTip(jTableFactors, bundle.getString("TrialWizardVisualPanel6.tipSpecifyLabels"));
        ToolTipUtils.balloonToToolTip(tip, 500, 3000);
    }
}
