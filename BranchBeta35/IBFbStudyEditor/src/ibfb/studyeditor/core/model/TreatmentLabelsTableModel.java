/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyeditor.core.model;

import ibfb.domain.core.Factor;
import ibfb.domain.core.FactorLabel;
import ibfb.domain.core.Workbook;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.openide.util.NbBundle;

/**
 *
 * @author TMSANCHEZ
 */
public class TreatmentLabelsTableModel extends AbstractTableModel {

    private static final int VALUE_COLUMN = 3;
    private static final String FACTOR = NbBundle.getMessage(TreatmentLabelsTableModel.class, "factorlabels.factor");
    private static final String LEVEL = NbBundle.getMessage(TreatmentLabelsTableModel.class, "factorlabels.level");
    private static final String LABEL = NbBundle.getMessage(TreatmentLabelsTableModel.class, "factorlabels.label");
    private static final String VALUE = NbBundle.getMessage(TreatmentLabelsTableModel.class, "factorlabels.value");
    private static final String[] columnNames = {FACTOR, LEVEL, LABEL, VALUE};
    private List<FactorLabel> factorLabels = new ArrayList<FactorLabel>();
    private List<Factor> otherFactors = new ArrayList<Factor>();
    private Workbook workbook;

    public TreatmentLabelsTableModel() {
    }

    public TreatmentLabelsTableModel(List<FactorLabel> factorLabels, Workbook workbook) {
        this.factorLabels = factorLabels;
        this.otherFactors = workbook.getOtherFactors();
        this.workbook = workbook;
        initFactorLabels();
    }

    @Override
    public int getRowCount() {
        return factorLabels.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FactorLabel factor = factorLabels.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return factor.getFactorName();
            case 1:
                return factor.getLevel();
            case 2:
                return factor.getLabel();
            case 3:
                return factor.getValue();
            default:
                return "";
        }
    }

    public List<FactorLabel> getFactorLabels() {
        return factorLabels;
    }

    public void setFactorLabels(List<FactorLabel> factorLabels) {
        this.factorLabels = factorLabels;
    }

    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == VALUE_COLUMN;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == VALUE_COLUMN) {
            factorLabels.get(rowIndex).setValue((String)aValue);
            fireTableCellUpdated(rowIndex, columnIndex);
        }

    }

    private void initFactorLabels() {
        for (Factor factor : otherFactors) {
            int totalLevel = (Integer) factor.getValue();

            for (Factor childFactor : workbook.getChildFactors(factor.getLabel())) {
                for (int level = 1; level < (totalLevel + 1); level++) {
                    FactorLabel factorLabel = new FactorLabel();
                    factorLabel.setFactorName(factor.getFactorName());
                    factorLabel.setLabel(childFactor.getFactorName() + " " + level);
                    factorLabel.setLevel(level);
                    factorLabel.setValue("");
                    factorLabels.add(factorLabel);
                }
            }
        }
    }
    
    
}
