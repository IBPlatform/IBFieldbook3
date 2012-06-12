/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyeditor.core.model;

import ibfb.domain.core.Condition;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.openide.util.NbBundle;
import ibfb.studyeditor.util.ValidityUtil;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;

/**
 *
 * @author TMSANCHEZ
 */
public class StudyConditionsTableModel extends AbstractTableModel {
    private static final int  VALUE_COLUMN = 4;

    private static final String CONDITION = NbBundle.getMessage(StudyConditionsTableModel.class, "header.condition");
    private static final String DESCRIPTION = NbBundle.getMessage(StudyConditionsTableModel.class, "header.description");
    private static final String PROPERTY = NbBundle.getMessage(StudyConditionsTableModel.class, "header.property");
    private static final String SCALE = NbBundle.getMessage(StudyConditionsTableModel.class, "header.scale");
    private static final String VALUE = NbBundle.getMessage(StudyConditionsTableModel.class, "header.value");
    private static final String[] columnNames = {CONDITION, DESCRIPTION, PROPERTY, SCALE, VALUE};
    
    private List<Condition> studyConditions = new ArrayList();

    public StudyConditionsTableModel(List<Condition> studyConditions) {
        this.studyConditions = studyConditions;
    }

    @Override
    public int getRowCount() {
        return studyConditions.size();
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
        Condition condition = studyConditions.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return condition.getConditionName();
            case 1:
                return condition.getDescription();
            case 2:
                return condition.getProperty();
            case 3:
                return condition.getScale();
            case 4:
                return condition.getValue();
            default:
                return "";
        }
    }

    public List<Condition> getStudyConditions() {
        return studyConditions;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == VALUE_COLUMN;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == VALUE_COLUMN) {
           String rowDataType = studyConditions.get(rowIndex).getDataType();
           String rowScale = studyConditions.get(rowIndex).getScale();
           if (ValidityUtil.isValidValue(aValue, rowDataType, rowScale)) {  
          
          //  if (ValidityUtil.isValidValueforDataType(aValue,rowDataType)) {
                studyConditions.get(rowIndex).setValue(aValue);
                fireTableCellUpdated(rowIndex, columnIndex);
             } else {
                DialogUtil.displayError(StudyConditionsTableModel.class, "studyconditionstable.validvaluerequired");
           }
            
                        
            
            
        }
        
    }
    
    
    
}
