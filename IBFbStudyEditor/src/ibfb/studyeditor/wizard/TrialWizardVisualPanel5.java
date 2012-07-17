package ibfb.studyeditor.wizard;

import ibfb.domain.core.Factor;
import ibfb.domain.core.Workbook;
import ibfb.studyeditor.core.StudyEditorTopComponent;
import ibfb.studyeditor.core.model.OtherTreatmentFactorsTableModel;
import ibfb.studyeditor.roweditors.ColumnFitAdapter;
import ibfb.studyeditor.roweditors.SpinnerEditor;
import java.awt.Color;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.utils.ToolTipUtils;
import org.openide.util.NbBundle;

public final class TrialWizardVisualPanel5 extends JPanel {
    private ResourceBundle bundle = NbBundle.getBundle(TrialWizardVisualPanel5.class);

    public TrialWizardVisualPanel5() {
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
        StudyEditorTopComponent studyWindow = TrialWizardWizardIterator.studyTopComponent;
        TableColumn levColumn = this.jTableFactors.getColumnModel().getColumn(3);
        TableColumn levColumnStudy = studyWindow.jTableDesign.getColumnModel().getColumn(3);
        levColumn.setCellEditor(new DefaultCellEditor(comboBoxLevels));
        levColumnStudy.setCellEditor(new DefaultCellEditor(comboBoxLevels));
        createBallonTips();
    }

    @Override
    public String getName() {
       return  bundle.getString("TrialWizardVisualPanel5.title");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFactors = new javax.swing.JTable();

        jTableFactors.setModel(new ibfb.studyeditor.core.model.OtherTreatmentFactorsTableModel());
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

    private void assignCellEditor() {
        SpinnerEditor spinnerEditor = new SpinnerEditor();
        TableColumn valueColumn = this.jTableFactors.getColumnModel().getColumn(3);
        valueColumn.setCellEditor(spinnerEditor);
        
        TableColumn columnValue = jTableFactors.getColumnModel().getColumn(3);
        DefaultTableCellRenderer valueCellRenderer =  new DefaultTableCellRenderer();
        valueCellRenderer.setToolTipText(bundle.getString("TrialWizardVisualPanel5.fillThisValue"));
        valueCellRenderer.setBackground(Color.YELLOW);
        columnValue.setCellRenderer(valueCellRenderer);          
    }

    @SuppressWarnings("unchecked")
    public void fillData(final Workbook workbook) {
        if (TrialWizardWizardIterator.OtherFactorsAreFilled) {
            return;
        }

        //DefaultTableModel modeloTabla = new DefaultTableModel();

        OtherTreatmentFactorsTableModel tableModel = null;


        if (TrialWizardWizardIterator.existenFactores == false) {
            tableModel = new OtherTreatmentFactorsTableModel();
        } else {
            tableModel = new OtherTreatmentFactorsTableModel(workbook.getOtherFactors(),true);
        }
        jTableFactors.setModel(tableModel);
        
        assignCellEditor();

        TrialWizardWizardIterator.OtherFactorsAreFilled = true;
    }

    public void copyValues(final Workbook workbook) {
        StudyEditorTopComponent studyWindow = TrialWizardWizardIterator.studyTopComponent;
        OtherTreatmentFactorsTableModel tableModel =  (OtherTreatmentFactorsTableModel)jTableFactors.getModel();
        studyWindow.assignOtherTreatmentFactors(tableModel.getOtherFactors());
    }

    private void createBallonTips() {
        BalloonTip tip = new BalloonTip(jTableFactors,bundle.getString("TrialWizardVisualPanel5.tipNumberOfLevels"));
        ToolTipUtils.balloonToToolTip(tip, 500, 3000);
    }

    public void copyLevels() {

        TrialWizardWizardIterator.levels = new int[jTableFactors.getRowCount()];

        int cont = this.jTableFactors.getRowCount() - 1;

        for (int i = 0; i < this.jTableFactors.getRowCount(); i++) {
            int levels = Integer.parseInt(this.jTableFactors.getValueAt(i, 3).toString());
            TrialWizardWizardIterator.levels[cont] = levels;
            cont--;
        }



    }

    public boolean tenemosLabels(final Workbook workbook) {
        boolean tenemos = false;
        int total = 0;

        java.util.ArrayList<Factor> factores = new ArrayList<Factor>();
        factores = (java.util.ArrayList<Factor>) workbook.getOtherFactors();

        for (String otherFactor : workbook.getChildFactors().keySet()) {

            for (Factor factor : workbook.getChildFactors().get(otherFactor)) {
                total++;
            }
        }

        if (total > 0) {
            tenemos = true;
            TrialWizardWizardIterator.existenLabels = true;
        } else {
            tenemos = false;
            TrialWizardWizardIterator.existenLabels = false;
        }
        return tenemos;

    }
}
