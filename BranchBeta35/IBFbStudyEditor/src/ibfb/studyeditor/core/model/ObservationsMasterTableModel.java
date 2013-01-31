
package ibfb.studyeditor.core.model;

import ibfb.domain.core.Condition;
import ibfb.domain.core.Factor;
import ibfb.domain.core.Variate;
import ibfb.domain.core.Workbook;
import java.util.*;
import javax.swing.table.AbstractTableModel;
import org.cimmyt.cril.ibwb.commongui.DecimalUtils;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;


public class ObservationsMasterTableModel extends AbstractTableModel {

    private static final String TRIAL_INSTANCE = "TRIAL INSTANCE";
    private static final String NUMBER = "NUMBER";
    public static final String TRIAL = "TRIALINSTANCENUMBER";
    public static final String ENTRY = "GERMPLASMENTRYNUMBER";
    public static final String ENTRY_CODE = "GERMPLASMENTRYCODE";
    public static final String DESIG = "GERMPLASMIDDBCV";
    public static final String GID = "GERMPLASMIDDBID";
    public static final String CROSS = "CROSSHISTORYPEDIGREESTRING";
    public static final String SOURCE = "SEEDSOURCENAME";
    public static final String PLOT = "FIELDPLOTNESTEDNUMBER";
    public static final String REPLICATION = "REPLICATIONNUMBER";
    public static final String BLOCK = "BLOCKNUMBER";
    public static final String ROW = "ROWINLAYOUTNUMBER";
    public static final String COL = "COLUMNINLAYOUTNUMBER";
    public static boolean isFromCrossInfo = false;
    /**
     * Prefix to store header index in map for factors
     */
    private static final String FACTOR_PREFIX = "FACTOR_";
    /**
     * Prefix to store header index in map for variates
     */
    private static final String VARIATE_PREFIX = "VARIATE_";
    /**
     * To easy retrieving of column indexes
     */
    private HashMap<String, Integer> headerIndex = new HashMap<String, Integer>();
    /**
     * List of items containing all headers. Items in list can be Factor o
     * Variates
     */
    private List<Object> headers;
    /**
     *
     */
    private List<List<Object>> values;
    private Workbook workbook;
    private List<Variate> variateList;
    /**
     * Map to store LabelId or VariateId and know which column index is used by
     * this factor or variate
     */
    private HashMap<String, Integer> numericHeaderIndex = new HashMap<String, Integer>();
    /**
     * Rows per trial, specially when Fieldbook has different designs
     */
    private Map<Integer, Integer> rowsPerTrial = new HashMap<Integer, Integer>();

    /**
     *
     * @param headers
     */
    public ObservationsMasterTableModel(Workbook workbook, List<Variate> variateList) {
        this.workbook = workbook;
        this.variateList = variateList;
        values = new ArrayList<List<Object>>();;
        assignHeaders();
        assignLabelHeaders();
    }

    public static boolean IsFromCrossInfo() {
        return isFromCrossInfo;
    }

    public static void setIsFromCrossInfo(boolean isFromCrossInfo) {
        ObservationsMasterTableModel.isFromCrossInfo = isFromCrossInfo;
    }

    /**
     * Assign headers from template
     */
    private void assignHeaders() {
        headers = new ArrayList<Object>();
        int columnIndex = 0;
        // add the trial header from conditions
        for (Condition condition : workbook.getConditions()) {
            if (condition.getProperty().toUpperCase().equals(TRIAL_INSTANCE) && condition.getScale().toUpperCase().equals(NUMBER)) {
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

        // add headers from OtherTreatment factors

        // add headers from factor section which are PLOT        
        for (Factor otherFactor : workbook.getOtherFactors()) {
            headers.add(otherFactor);
            headerIndex.put(Workbook.getStringWithOutBlanks(otherFactor.getProperty() + otherFactor.getScale()), columnIndex);
            // assisn column index to numeric header only if has a labelid
            if (otherFactor.getLabelId() != null) {
                numericHeaderIndex.put(FACTOR_PREFIX + otherFactor.getLabelId(), columnIndex);
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
     * It return the column text label. It check column index in header List and
     * take the name according to each different object (Condition, Factor or
     * Variate)
     *
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


        if (IsFromCrossInfo()) {
            columnList.set(columnIndex, aValue);

        } else {
            String columnDataType = ((Variate) columnObject).getDataType();
            if (isValidValue(aValue, columnDataType)) {
                columnList.set(columnIndex, aValue);
            } else {
                DialogUtil.display(ObservationsMasterTableModel.class, "observationstable.numericvaluerequired");
            }
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
        // only cells with a variate column are editable/
        Object columnObject = headers.get(columnIndex);
        boolean cellEditable = columnObject instanceof Variate;
        return cellEditable;
    }

    /**
     * Get the column index for header
     *
     * @param columnName Column name to search
     * @return column index number greater than 0 if found or -1 if not found
     */
    public int getHeaderIndex(String columnName) {
        int columnIndex = -1;
        if (headerIndex.get(columnName) != null) {
            columnIndex = headerIndex.get(columnName);
        }
        return columnIndex;
    }

    /**
     * Add a row to model
     *
     * @param colValues
     */
    public void addRow(List<Object> colValues) {
        values.add(colValues);
        fireTableDataChanged();
    }

    /**
     * Add a row to model
     *
     * @param colValues
     */
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

    /**
     * Get additional description for headers
     *
     * @param columnIndex
     * @return
     */
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

    /**
     * Return column index for a variate by its variateid It checks which
     * variate id is stored in map and if check then retrieves it column index
     * in table
     *
     * @param variateId
     * @return
     */
    public int getHeaderIndexForVariate(Integer variateId) {
        int headerIndexForVariate = -1;

        if (numericHeaderIndex.containsKey(VARIATE_PREFIX + variateId)) {
            headerIndexForVariate = numericHeaderIndex.get(VARIATE_PREFIX + variateId);
        }

        return headerIndexForVariate;
    }

    /**
     * Return the variate for column index
     *
     * @param columnIndex column index in header
     * @return The variate for that column or null
     */
    public Variate getVariate(int columnIndex) {
        Variate variate = (Variate) headers.get(columnIndex);
        return variate;
    }

    /**
     * It count how many different trials are in observations table;
     *
     * @return
     */
    public int getTrialCounter() {
        int trialCounter = 0;

        int currentTrial = -999;

        for (int row = 0; row < values.size(); row++) {
            Object value = getValueAt(row, 0);
            Integer trialNumber = null;
            if (value instanceof String) {
                trialNumber = Integer.parseInt((String) value);
            } else if (value instanceof Integer) {
                trialNumber = (Integer) value;
            }
            if (trialNumber != null) {
                if (trialNumber.intValue() != currentTrial) {
                    trialCounter++;
                    currentTrial = trialNumber;
                }
            }
        }

        return trialCounter;
    }

    /**
     * Gets a map with number of plot for each row
     *
     * @return
     */
    public Map<Integer, Integer> getRowsPerTrial() {
        int plotColumn = getHeaderIndex(PLOT);
        int trialColumn = 0;//getHeaderIndex(TRIAL);

        rowsPerTrial = new HashMap<Integer, Integer>();

        int currentTrial = -999;

        for (int row = values.size() - 1; row > 0; row--) {
            Object value = getValueAt(row, trialColumn);
            Integer trialNumber = null;
            if (value instanceof String) {
                trialNumber = Integer.parseInt((String) value);
            } else if (value instanceof Integer) {
                trialNumber = (Integer) value;
            }
            if (trialNumber != null) {
                if (trialNumber.intValue() != currentTrial) {
                    currentTrial = trialNumber;
                    Object maxPlot = getValueAt(row, plotColumn);
                    Integer plotValue = 0;
                    if (maxPlot instanceof Integer) {
                        plotValue = (Integer) maxPlot;
                    } else if (maxPlot instanceof String) {
                        plotValue = Integer.parseInt((String) maxPlot);
                    }
                    rowsPerTrial.put(currentTrial, plotValue);
                }
            }
        }

        return rowsPerTrial;
    }
}
