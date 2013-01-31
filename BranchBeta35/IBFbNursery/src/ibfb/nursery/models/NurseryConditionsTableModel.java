
package ibfb.nursery.models;

import ibfb.domain.core.Condition;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.openide.util.NbBundle;


public class NurseryConditionsTableModel extends AbstractTableModel {
    private static final int  VALUE_COLUMN = 5;
    private static final String NURSERY = NbBundle.getMessage(StudyConditionsTableModel.class, "header.nursery");
    private static final String CONDITION = NbBundle.getMessage(StudyConditionsTableModel.class, "header.condition");
    private static final String DESCRIPTION = NbBundle.getMessage(StudyConditionsTableModel.class, "header.description");
    private static final String PROPERTY = NbBundle.getMessage(StudyConditionsTableModel.class, "header.property");
    private static final String SCALE = NbBundle.getMessage(StudyConditionsTableModel.class, "header.scale");
    private static final String VALUE = NbBundle.getMessage(StudyConditionsTableModel.class, "header.value");
    
    private static final String[] columnNames = {NURSERY,CONDITION, DESCRIPTION, PROPERTY, SCALE, VALUE};    

    private List<Condition> nurseryConditions  = new ArrayList();    
    
    public NurseryConditionsTableModel(List<Condition> nurseryConditions) {
       this.nurseryConditions = nurseryConditions; 
    }
    
    @Override
    public int getRowCount() {
       return nurseryConditions.size();
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
         Condition condition = nurseryConditions.get(rowIndex);
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
             nurseryConditions.get(rowIndex).setValue(aValue);
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public List<Condition> getNurseryConditions() {
        return nurseryConditions;
    }

        public List<Condition> getTrialConditions() {
        return nurseryConditions;
    }
    
}
