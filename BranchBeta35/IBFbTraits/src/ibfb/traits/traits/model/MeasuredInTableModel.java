/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.traits.traits.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import org.cimmyt.cril.ibwb.domain.Measuredin;
import org.cimmyt.cril.ibwb.domain.Scales;
import org.openide.util.NbBundle;

/**
 *
 * @author TMSANCHEZ
 */
public class MeasuredInTableModel extends AbstractTableModel {
    public static final int SCALE_ID_SIZE = 54;
    public static final int SCALE_NAME_SIZE = 117;
    public static final int SCALE_TYPE_SIZE = 99;
    public static final int SCALE_DATATYPE_SIZE = 80;
    public static final int SCALE_STANDARD_SIZE = 97;
    public static final int METHOD_ID_SIZE = 64;
    public static final int METHOD_NAME_SIZE = 101;
    public static final int SCALE_DEFINITION_SIZE = 561;
    
    
    private static final String SCALE_ID = NbBundle.getMessage(MeasuredInTableModel.class, "measuredin.scaleid");
    private static final String SCALE_NAME = NbBundle.getMessage(MeasuredInTableModel.class, "measuredin.scalename");
    private static final String SCALE_TYPE = NbBundle.getMessage(MeasuredInTableModel.class, "measuredin.scaletype");
    private static final String SCALE_DTYPE = NbBundle.getMessage(MeasuredInTableModel.class, "measuredin.scaledatatype");
    private static final String METHOD_ID = NbBundle.getMessage(MeasuredInTableModel.class, "measuredin.methodid");
    private static final String METHOD_NAME = NbBundle.getMessage(MeasuredInTableModel.class, "measuredin.methodname");
    private static final String STANDARD_SCALE = NbBundle.getMessage(MeasuredInTableModel.class, "measuredin.standardscale");
    private static final String SCALE_DEFINITION = NbBundle.getMessage(MeasuredInTableModel.class, "measuredin.scaledefinition");
    private static final String[] columnNames = {SCALE_ID, SCALE_NAME, SCALE_TYPE, SCALE_DTYPE, STANDARD_SCALE, METHOD_ID, METHOD_NAME,SCALE_DEFINITION};
    /**
     * List of Measuredin Objects to show
     */
    private List<Measuredin> measuredInList;
    /**
     * Map to check when attempts to add an exiting combination of method and scale
     */
    private Map<String, String> currentIds;

    public MeasuredInTableModel(List<Measuredin> measuredInList) {
        this.measuredInList = measuredInList;
        fillCurrentIds();
    }

    public List<Measuredin> getMeasuredInList() {
        return measuredInList;
    }

    public void setMeasuredInList(List<Measuredin> measuredInList) {
        this.measuredInList = measuredInList;
    }

    @Override
    public int getRowCount() {
        return measuredInList.size();

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
        Measuredin measuredin = measuredInList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return measuredin.getScaleid();
            case 1:
                return measuredin.getScales().getScname();
            case 2:
                return getScaleTypeDesc(measuredin.getScales().getSctype());
            case 3:
                return getScaleDataTypeDesc(measuredin.getScales().getDtype());
            case 4:
                return measuredin.getStandardscale();
            case 5:
                return measuredin.getTmethid();
            case 6:
                return measuredin.getTmsMethod().getTmname();
            case 7:
                return measuredin.getTmsScaleDef();
        }
        return "";
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex ==7;
    }

    public void addMeasuredin(Measuredin measuredin) {
        currentIds.put(measuredin.getScaleid() + "|" + measuredin.getTmethid(), "");
        measuredInList.add(measuredin);
        fireTableDataChanged();
    }

    private void fillCurrentIds() {
        currentIds = new HashMap<String, String>();
        for (Measuredin measuredin : measuredInList) {
            currentIds.put(measuredin.getScaleid() + "|" + measuredin.getTmethid(), "");
        }
    }

    /**
     * Checks if scale and method already exists in MeasuredIn
     * @param measuredin
     * @return 
     */
    public boolean scaleMethodAlreadyExists(Measuredin measuredin) {
        boolean alreadyExists = false;
        String scaleMethodId = measuredin.getScaleid() + "|" + measuredin.getTmethid();
        alreadyExists = currentIds.containsKey(scaleMethodId);
        return alreadyExists;
    }

    /**
     * Checks if MeasuredIN is empty
     * @return <code>true</code> if empty, <code>false</code> if not
     */
    public boolean isEmpty() {
        return measuredInList.isEmpty();
    }

    /**
     * Mask desired row as Standard scale
     * @param rowIndex row index to mark as default
     */
    public void setAsStandarScale(int rowIndex) {
        for (int row = 0; row < measuredInList.size(); row++) {
            Measuredin measuredin = measuredInList.get(row);
            if (row == rowIndex) {
                measuredin.setStandardscale(Measuredin.STANTARD_SCALE_YES);
            } else {
                measuredin.setStandardscale(Measuredin.STANTARD_SCALE_NO);
            }
        }
        fireTableDataChanged();
    }

    /**
     * Gets text string for data type from bundle resource file
     * @param dtype
     * @return Text with description for data type
     */
    private String getScaleDataTypeDesc(String dtype) {
        String desc = "";
        if (dtype.equals(Scales.DATA_TYPE_CHARACTER)) {
            desc = NbBundle.getMessage(MeasuredInTableModel.class, "measuredin.scaledtypechar");
        } else if (dtype.equals(Scales.DATA_TYPE_NUMERIC)) {
            desc = NbBundle.getMessage(MeasuredInTableModel.class, "measuredin.scaledtypenumeric");
        }
        return desc;
    }

    /**
     * Gets text string for scale type from bundle resource file
     * @param sctype
     * @return 
     */
    private String getScaleTypeDesc(String sctype) {
        String desc = "";
        if (sctype.equals(Scales.SCALE_TYPE_CONTINOUS)) {
            desc = NbBundle.getMessage(MeasuredInTableModel.class, "measuredin.scaletypecontinuous");
        } else if (sctype.equals(Scales.SCALE_TYPE_DISCRETE)) {
            desc = NbBundle.getMessage(MeasuredInTableModel.class, "measuredin.scaletypediscrete");
        }
        return desc;
    }
}
