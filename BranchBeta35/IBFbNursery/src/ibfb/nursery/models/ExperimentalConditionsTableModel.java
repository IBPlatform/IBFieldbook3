
package ibfb.nursery.models;

import ibfb.domain.core.Constant;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.openide.util.NbBundle;


public class ExperimentalConditionsTableModel extends AbstractTableModel {

    private static final int VALUE_COLUMN = 4;
    private static final String NURSERY = NbBundle.getMessage(StudyConditionsTableModel.class, "header.nursery");
    private static final String CONSTANT = NbBundle.getMessage(StudyConditionsTableModel.class, "header.condition");
    private static final String DESCRIPTION = NbBundle.getMessage(StudyConditionsTableModel.class, "header.description");
    private static final String PROPERTY = NbBundle.getMessage(StudyConditionsTableModel.class, "header.property");
    private static final String SCALE = NbBundle.getMessage(StudyConditionsTableModel.class, "header.scale");
    private static final String VALUE = NbBundle.getMessage(StudyConditionsTableModel.class, "header.value");
    private static final String[] columnNames = {NURSERY, CONSTANT, DESCRIPTION, SCALE, VALUE};   
  
    private List<Constant> constantList = new ArrayList();

    public ExperimentalConditionsTableModel(List<Constant> constantList) {
        this.constantList = constantList;
    }

    @Override
    public int getRowCount() {
        return constantList.size();
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
         Constant constant = constantList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                 return constant.getInstance();
            case 1:
                return constant.getConstantName();
            case 2:
                return constant.getDescription();
            case 3:
                return constant.getScale();
            case 4:
                return constant.getValue();
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
             constantList.get(rowIndex).setValue(aValue);
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public List<Constant> getConstantList() {
        return constantList;
    }

   
   
}
