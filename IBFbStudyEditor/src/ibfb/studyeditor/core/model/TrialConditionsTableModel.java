/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyeditor.core.model;

import ibfb.domain.core.Condition;
import ibfb.studyeditor.util.ValidityUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.openide.util.NbBundle;

/**
 * Table Model to manage Trial Conditions
 * @author TMSANCHEZ
 */
public class TrialConditionsTableModel extends AbstractTableModel {

    private static final int VALUE_COLUMN = 5;
    private static final String TRIAL = NbBundle.getMessage(StudyConditionsTableModel.class, "header.trial");
    private static final String CONDITION = NbBundle.getMessage(StudyConditionsTableModel.class, "header.condition");
    private static final String DESCRIPTION = NbBundle.getMessage(StudyConditionsTableModel.class, "header.description");
    private static final String PROPERTY = NbBundle.getMessage(StudyConditionsTableModel.class, "header.property");
    private static final String SCALE = NbBundle.getMessage(StudyConditionsTableModel.class, "header.scale");
    private static final String VALUE = NbBundle.getMessage(StudyConditionsTableModel.class, "header.value");
    private static final String[] columnNames = {TRIAL, CONDITION, DESCRIPTION, PROPERTY, SCALE, VALUE};
    private List<Condition> trialConditions = new ArrayList();

    public TrialConditionsTableModel(List<Condition> trialConditions) {
        this.trialConditions = trialConditions;
    }

    @Override
    public int getRowCount() {
        return trialConditions.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        // if is trial column then
//        if (column == 0) {
//            if (trialConditions != null && trialConditions.get(0)!= null) {
//            return trialConditions.get(0).getConditionName();
//            } else {
//                return columnNames[column];
//            }
//        } else {
            return columnNames[column];
//        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Condition condition = trialConditions.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return condition.getInstance();
            case 1:
                return condition.getConditionName();
            case 2:
                return condition.getDescription();
            case 3:
                return condition.getProperty();
            case 4:
                return condition.getScale();
            case 5:
                return condition.getValue();
            default:
                return "";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == VALUE_COLUMN;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == VALUE_COLUMN) {
            String rowDataType = trialConditions.get(rowIndex).getDataType();
            String rowScale = trialConditions.get(rowIndex).getScale();

            if (ValidityUtil.isValidValue(aValue, rowDataType, rowScale)) {
                trialConditions.get(rowIndex).setValue(aValue);
                fireTableCellUpdated(rowIndex, columnIndex);
            } else {
                DialogUtil.displayError(TrialConditionsTableModel.class, "trialconditionstable.validvaluerequired");
            }

        }

    }

    public List<Condition> getTrialConditions() {
        return trialConditions;
    }
}
