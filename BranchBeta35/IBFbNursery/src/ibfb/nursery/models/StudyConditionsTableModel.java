
package ibfb.nursery.models;

import ibfb.domain.core.Condition;
import ibfb.domain.core.Workbook;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.cimmyt.cril.ibwb.commongui.ConvertUtils;
import org.openide.util.NbBundle;

public class StudyConditionsTableModel extends AbstractTableModel {
    private static final int  VALUE_COLUMN = 4;
    
    public static final String PROPERTY_METHOD = "METHOD";
    public static final String SCALE_DBID = "DBID";

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
             studyConditions.get(rowIndex).setValue(aValue);
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    

    /**
     * It return selected breeding method
     * @return 
     */
    public int getBreedingMethod() {
        int breedingMethod = 0;
        for (Condition condition: studyConditions) {
            String propertyAndScale = Workbook.getStringWithOutBlanks(condition.getProperty() + condition.getScale());
            if (propertyAndScale.equals(PROPERTY_METHOD+SCALE_DBID)) {
                if (condition.getValue() != null ) {
                   breedingMethod = ConvertUtils.getValueAsInteger(condition.getValue());
                }
            }
        }
        return breedingMethod;
    }
    
}
