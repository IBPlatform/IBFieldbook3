package ibfb.nursery.models;

import ibfb.domain.core.Condition;
import ibfb.domain.core.Factor;
import ibfb.domain.core.Variate;
import ibfb.domain.core.Workbook;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import org.cimmyt.cril.ibwb.commongui.DecimalUtils;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;

public class ObservationsTableModel2 extends AbstractTableModel {

    private static final String NURSERY_INSTANCE = "NURSERY INSTANCE";
    private static final String NUMBER = "NUMBER";
    public static final String NURSERY = "NURSERYINSTANCENUMBER";
    public static final String ENTRY = "GERMPLASMENTRYNUMBER";
    public static final String ENTRY_CODE = "GERMPLASMENTRYCODE";
    public static final String DESIG = "GERMPLASMIDDBCV";
    public static final String GID = "GERMPLASMIDDBID";
    public static final String CHECKPOSITION="CHECKPOSITION";
    public static final String CHECKSTART="CHECKSTART";
    
    public static final String CROSS = "CROSSHISTORYPEDIGREESTRING";
    public static final String SOURCE = "SEEDSOURCENAME";
    public static final String PLOT = "FIELDPLOTNESTEDNUMBER";
    public static final String REPLICATION = "REPLICATIONNUMBER";
    public static final String BLOCK = "BLOCKNUMBER";
    public static final String ROW = "";
    public static final String COL = "";
    private static final String FACTOR_PREFIX = "FACTOR_";
    private static final String VARIATE_PREFIX = "VARIATE_";
    private HashMap<String, Integer> headerIndex = new HashMap<String, Integer>();
    private List<Object> headers;
    private List<List<Object>> values;
    private Workbook workbook;
    private List<Variate> variateList;
    private HashMap<String, Integer> numericHeaderIndex = new HashMap<String, Integer>();
    private Map<Integer, Integer> rowsPerTrial = new HashMap<Integer, Integer>();

    public ObservationsTableModel2(Workbook workbook, List<Variate> variateList) {
        this.workbook = workbook;
        this.variateList = variateList;
        values = new ArrayList<List<Object>>();;
        assignHeaders();
        assignLabelHeaders();
    }

    private void assignHeaders() {
        headers = new ArrayList<Object>();
        int columnIndex = 0;
        // add the trial header from conditions
        for (Condition condition : workbook.getConditions()) {
            if (condition.getProperty().toUpperCase().equals(NURSERY_INSTANCE) && condition.getScale().toUpperCase().equals(NUMBER)) {
                headers.add(condition);
                headerIndex.put(Workbook.getStringWithOutBlanks(condition.getProperty() + condition.getScale()), columnIndex);
                // assisn column index to numeric header only if has a labelid
                if (condition.getLabelId() != null) {
                    numericHeaderIndex.put(FACTOR_PREFIX + condition.getLabelId(), columnIndex);
                }
                columnIndex++;
                break;
            }
        }
        // add headers from factor section which are TRIAL
        for (Factor factor : workbook.getEntryFactors()) {
            headers.add(factor);
            headerIndex.put(Workbook.getStringWithOutBlanks(factor.getProperty() + factor.getScale()), columnIndex);
            // assisn column index to numeric header only if has a labelid
            if (factor.getLabelId() != null) {
                numericHeaderIndex.put(FACTOR_PREFIX + factor.getLabelId(), columnIndex);
            }

            columnIndex++;
        }
        // add headers from factor section which are PLOT        
        for (Factor factor : workbook.getPlotFactors()) {
            headers.add(factor);
            headerIndex.put(Workbook.getStringWithOutBlanks(factor.getProperty() + factor.getScale()), columnIndex);
            // assisn column index to numeric header only if has a labelid
            if (factor.getLabelId() != null) {
                numericHeaderIndex.put(FACTOR_PREFIX + factor.getLabelId(), columnIndex);
            }

            columnIndex++;
        }
        // add headers from selected variates
        for (Variate variate : variateList) {
            headers.add(variate);
            // assisn column index to numeric header only if has a labelid
            if (variate.getVariateId() != null) {
                numericHeaderIndex.put(VARIATE_PREFIX + variate.getVariateId(), columnIndex);
            }
            columnIndex++;
        }
    }

    @Override
    public int getRowCount() {
        return values.size();
    }

    @Override
    public int getColumnCount() {
        return headers.size();
    }

    /**
     * It return the column text label.  It check
     * column index in header List and take the name
     * according to each different object (Condition, Factor or Variate)
     * @param column column index
     * @return String text for label or empty String if cannot retrieve text
     */
    @Override
    public String getColumnName(int column) {
        Object columnObject = headers.get(column);

        String columnName = "";
        if (columnObject instanceof Condition) {
            columnName = ((Condition) columnObject).getConditionName();
        } else if (columnObject instanceof Factor) {
            columnName = ((Factor) columnObject).getFactorName();
        } else if (columnObject instanceof Variate) {
            columnName = ((Variate) columnObject).getVariateName();
        }

        return columnName;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //return values[rowIndex][columnIndex];
        List<Object> columnList = values.get(rowIndex);
 Object value = columnList.get(columnIndex);

        if (value != null) {
            if (value instanceof Double) {
               Double doubleValue = (Double)value; 
               if (DecimalUtils.isIntegerValue(doubleValue)) {
                   value = DecimalUtils.getValueAsInteger(value);
               } 
            }
        }

        return value;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        List<Object> columnList = values.get(rowIndex);
        Object columnObject = headers.get(columnIndex);
        String columnDataType = ((Variate) columnObject).getDataType();
        if (isValidValue(aValue, columnDataType)) {
            columnList.set(columnIndex, aValue);
        } else {
          //  DialogUtil.display(ObservationsTableModel2.class, "observationstable.numericvaluerequired");
        }
        fireTableCellUpdated(rowIndex, columnIndex);        
    }

    private boolean isValidValue(Object aValue, String columnDataType) {
        boolean isValid = true;

        // if is a null value then accept it
        if (aValue == null) {
            return true;
        }

        if (aValue instanceof String) {
            String value = (String) aValue;
            if (value.trim().isEmpty()) {
                return true;
            }
        }

        if (columnDataType.equals("N")) {
            if (!isNumeric(aValue)) {
                isValid = false;
            }
        }

        return isValid;
    }

    private boolean isNumeric(Object aValue) {
        try {
            Double.parseDouble((String) aValue);
        } catch (NumberFormatException nme) {
            System.out.println(aValue + " is not numeric : " + nme);
            return false;
        }
        return true;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        Object columnObject = headers.get(columnIndex);
        boolean cellEditable = columnObject instanceof Variate;
        return cellEditable;
    }

    public int getHeaderIndex(String columnName) {
        int columnIndex = -1;
        if (headerIndex.get(columnName) != null) {
            columnIndex = headerIndex.get(columnName);
        }
        return columnIndex;
    }

    public void addRow(List<Object> colValues) {
        values.add(colValues);
        fireTableDataChanged();
    }

    public void addRow(Object[] colValues) {
        values.add(Arrays.asList(colValues));
        fireTableDataChanged();
    }

    public List<Object> getRow(int rowIndex) {
        return values.get(rowIndex);
    }

    public List<Object> getHeaders() {
        return headers;
    }

    public List<Variate> getVariateList() {
        return variateList;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public List<List<Object>> getValues() {
        return values;
    }

    public void setValues(List<List<Object>> values) {
        this.values = values;
    }

    public String getDescriptionForColumn(int columnIndex) {
        StringBuilder description = new StringBuilder();

        Object columnObject = headers.get(columnIndex);

        if (columnObject instanceof Condition) {
            Condition condition = (Condition) columnObject;
            description.append(condition.getDescription() == null ? "" : condition.getDescription());
            if (condition.getScale() != null && !condition.getScale().isEmpty()) {
                description.append("  ").append(condition.getScale());
            }
        } else if (columnObject instanceof Factor) {
            Factor factor = (Factor) columnObject;
            description.append(factor.getDescription() == null ? "" : factor.getDescription());
            if (factor.getScale() != null && !factor.getScale().isEmpty()) {
                description.append("  ").append(factor.getScale());
            }

        } else if (columnObject instanceof Variate) {
            Variate variate = (Variate) columnObject;
            description.append(variate.getDescription() == null ? "" : variate.getDescription());
            if (variate.getScale() != null && !variate.getScale().isEmpty()) {
                description.append("  ").append(variate.getScale());
            }
        }

        return description.toString();
    }

    private void assignLabelHeaders() {
    }

    public int getHeaderIndexForVariate(Integer variateId) {
        int headerIndexForVariate = -1;

        if (numericHeaderIndex.containsKey(VARIATE_PREFIX + variateId)) {
            headerIndexForVariate = numericHeaderIndex.get(VARIATE_PREFIX + variateId);
        }

        return headerIndexForVariate;
    }

    public int getNurseryCounter() {
        int nurseryCounter = 0;

        int currentNursery = -999;

        for (int row = 0; row < values.size(); row++) {
            Object value = getValueAt(row, 0);
            Integer nurseryNumber = null;
            if (value instanceof String) {
                nurseryNumber = Integer.parseInt((String) value);
            } else if (value instanceof Integer) {
                nurseryNumber = (Integer) value;
            }
            if (nurseryNumber != null) {
                if (nurseryNumber.intValue() != currentNursery) {
                    nurseryCounter++;
                    currentNursery = nurseryNumber;
                }
            }
        }

        return nurseryCounter;
    }

    /**
     * Gets a map with number of plot for each row
     * @return 
     */
    public Map<Integer, Integer> getRowsPerNursery() {
        int plotColumn = getHeaderIndex(PLOT);
        int nurseryColumn = 0;//getHeaderIndex(NURSERY);

        rowsPerTrial = new HashMap<Integer, Integer>();

        int currentNursery = -999;

        for (int row = values.size() - 1; row > 0; row--) {
            Object value = getValueAt(row, nurseryColumn);
            Integer nurseryNumber = null;
            if (value instanceof String) {
                nurseryNumber = Integer.parseInt((String) value);
            } else if (value instanceof Integer) {
                nurseryNumber = (Integer) value;
            }
            if (nurseryNumber != null) {
                if (nurseryNumber.intValue() != currentNursery) {
                    currentNursery = nurseryNumber;
                    Object maxPlot = getValueAt(row, plotColumn);
                    Integer plotValue = 0;
                    if (maxPlot instanceof Integer) {
                        plotValue = (Integer) maxPlot;
                    } else if (maxPlot instanceof String) {
                        plotValue = Integer.parseInt((String) maxPlot);
                    }
                    rowsPerTrial.put(currentNursery, plotValue);
                }
            }
        }

        return rowsPerTrial;
    }

    /**
     * Get datatype for specified Variate.  It check in current list of variates
     * using variate name, if found then returns datatype
     * @param variateName Variate name to search
     * @return Data type (C, N, T) if found or (-) if not found
     */
    public String getDataTypeForVariate(String variateName) {
        String dataType = "-";

        for (Variate variate : variateList) {
            if (variate.getVariateName().equals(variateName)) {
                dataType = variate.getDataType();
                break;
            }
        }


        return dataType;
    }
    
    /**
     * Return the variate for column index
     * @param columnIndex column index in header
     * @return The variate for that column or null
     */
    public Variate getVariate(int columnIndex) {
        Variate variate = (Variate)headers.get(columnIndex);
        return variate;
    }
}
