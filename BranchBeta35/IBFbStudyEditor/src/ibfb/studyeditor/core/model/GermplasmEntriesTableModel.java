package ibfb.studyeditor.core.model;

import ibfb.domain.core.Factor;
import ibfb.domain.core.Variate;
import ibfb.domain.core.Workbook;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;

/**
 * Table Model to manage Germplasm Entries
 *
 * @author TMSANCHEZ
 */
public class GermplasmEntriesTableModel extends AbstractTableModel {

    /**
     * List of factors to be used as headers
     */
    private List<Factor> factorHeaders;
    /**
     * Each germplasm value (GID, ENTRY CODE, etc.)
     */
    private List<List<Object>> germplasmData;
    /**
     * To easy retrieving of column indexes
     */
    private HashMap<String, Integer> headerIndex = new HashMap<String, Integer>();
    /**
     * List of items containing all headers. Items in list can be Factor o
     * Variates
     */
    private List<Object> headers;
    public static boolean isFromCrossInfo = false;

    /**
     * Default constructor
     */
    public GermplasmEntriesTableModel() {
        clearTable();
        assignHeaders();
    }
    
    public static boolean IsFromCrossInfo() {
        return isFromCrossInfo;
    }
    
    public static void setIsFromCrossInfo(boolean isFromCrossInfo) {
        GermplasmEntriesTableModel.isFromCrossInfo = isFromCrossInfo;
    }

    /**
     * Constructor using headers and data
     *
     * @param factorHeaders List of Factors (with LABEL == ENTRY ) to be used as
     * Headers
     * @param germplasmData Each germplasm value (GID, ENTRY CODE, etc.)
     */
    public GermplasmEntriesTableModel(List<Factor> factorHeaders, List<List<Object>> germplasmData) {
        this.factorHeaders = factorHeaders;
        this.germplasmData = germplasmData;
        assignHeaders();
    }
    
    @Override
    public int getRowCount() {
        return germplasmData.size();
    }
    
    @Override
    public int getColumnCount() {
        return factorHeaders.size();
    }
    
    @Override
    public String getColumnName(int column) {
        return factorHeaders.get(column).getFactorName();
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        List<Object> columnValues = germplasmData.get(rowIndex);
        if (columnIndex < columnValues.size()) {
            return columnValues.get(columnIndex);
        } else {
            return null;
        }
        
    }
    
    public List<Factor> getFactorHeaders() {
        return factorHeaders;
    }
    
    public List<List<Object>> getGermplasmData() {
        return germplasmData;
    }
    
    public void clearTable() {
        factorHeaders = new ArrayList<Factor>();
        germplasmData = new ArrayList<List<Object>>();
        fireTableDataChanged();
    }

    /**
     * Assign headers from template
     */
    private void assignHeaders() {
        headers = new ArrayList<Object>();
        int columnIndex = 0;

        // add headers from factor section which are TRIAL
        for (Factor factor : factorHeaders) {
            headers.add(factor);
            headerIndex.put(Workbook.getStringWithOutBlanks(factor.getProperty() + factor.getScale()), columnIndex);
            columnIndex++;
        }
        
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
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        List<Object> columnValues = germplasmData.get(rowIndex);
        if (columnIndex < columnValues.size()) {
            columnValues.set(columnIndex, aValue);
            
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
}
