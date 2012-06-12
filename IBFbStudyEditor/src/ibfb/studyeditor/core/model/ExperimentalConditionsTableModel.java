/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyeditor.core.model;

import ibfb.domain.core.Constant;
import ibfb.studyeditor.util.ValidityUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.openide.util.NbBundle;

/**
 * Table Model to Manage Experimental Conditions
 * @author TMSANCHEZ
 */
public class ExperimentalConditionsTableModel extends AbstractTableModel {

    private static final int VALUE_COLUMN = 4;
    private static final String TRIAL = NbBundle.getMessage(StudyConditionsTableModel.class, "header.trial");
    private static final String CONSTANT = NbBundle.getMessage(StudyConditionsTableModel.class, "header.condition");
    private static final String DESCRIPTION = NbBundle.getMessage(StudyConditionsTableModel.class, "header.description");
    private static final String PROPERTY = NbBundle.getMessage(StudyConditionsTableModel.class, "header.property");
    private static final String SCALE = NbBundle.getMessage(StudyConditionsTableModel.class, "header.scale");
    private static final String VALUE = NbBundle.getMessage(StudyConditionsTableModel.class, "header.value");
    private static final String[] columnNames = {TRIAL, CONSTANT, DESCRIPTION, SCALE, VALUE};
    
    /**
     * List of Experimental conditions
     */
    private List<Constant> constantList = new ArrayList();

    /**
     * Default constructor
     * @param constantList List of constants (Experimental conditions)
     */
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
        // allow editing only for value COLUMN
        return columnIndex == VALUE_COLUMN;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // only assign value column because other columns are readonly
        if (columnIndex == VALUE_COLUMN) {
           String rowDataType = constantList.get(rowIndex).getDataType();
           String rowScale = constantList.get(rowIndex).getScale();
           if (ValidityUtil.isValidValue(aValue,rowDataType,rowScale)) {
                constantList.get(rowIndex).setValue(aValue);
                fireTableCellUpdated(rowIndex, columnIndex);
           } else {
                DialogUtil.displayError(ExperimentalConditionsTableModel.class, "constantstable.validvaluerequired");
           }
                      
            
        }
        
    }

    public List<Constant> getConstantList() {
        return constantList;
    }

   
   
}

