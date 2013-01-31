/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyeditor.core.model;

import ibfb.domain.core.Factor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.openide.util.NbBundle;

/**
 *
 * @author TMSANCHEZ
 */
public class OtherTreatmentFactorsTableModel extends AbstractTableModel {

    private static final int VALUE_COLUMN = 3;
    private static final String FACTOR = NbBundle.getMessage(OtherTreatmentFactorsTableModel.class, "othertreatment.factor");
    private static final String DESCRIPTION = NbBundle.getMessage(OtherTreatmentFactorsTableModel.class, "othertreatment.description");
    private static final String SCALE = NbBundle.getMessage(OtherTreatmentFactorsTableModel.class, "othertreatment.scale");
    private static final String LEVEL_NUMBER = NbBundle.getMessage(OtherTreatmentFactorsTableModel.class, "othertreatment.levelnumber");
    private static final String[] columnNames = {FACTOR, DESCRIPTION, SCALE, LEVEL_NUMBER};
    private List<Factor> otherFactors = new ArrayList<Factor>();

    public OtherTreatmentFactorsTableModel() {
    }

    public OtherTreatmentFactorsTableModel(List<Factor> otherFactors, boolean initialize) {
        this.otherFactors = otherFactors;
        if (initialize) {
            initLevels();
        }
    }

    @Override
    public int getRowCount() {
        return otherFactors.size();
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
        Factor factor = otherFactors.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return factor.getFactorName();
            case 1:
                return factor.getDescription();
            case 2:
                return factor.getScale();
            case 3:
                return factor.getValue();
            default:
                return "";
        }
    }

    public List<Factor> getOtherFactors() {
        return otherFactors;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == VALUE_COLUMN;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == VALUE_COLUMN) {
            otherFactors.get(rowIndex).setValue(aValue);
            fireTableCellUpdated(rowIndex, columnIndex);
        }

    }

    /**
     * Initializes level values with 1 as default 
     */
    private void initLevels() {
        for (Factor factor : otherFactors) {
            factor.setValue(1);
        }
    }
}
