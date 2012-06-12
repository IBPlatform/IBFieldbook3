/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyeditor.core.model;

import ibfb.domain.core.Factor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Table Model to manage Germplasm Entries
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
     * Default constructor
     */
    public GermplasmEntriesTableModel() {
        clearTable();
    }
    
    /**
     * Constructor using headers and data
     * @param factorHeaders List of Factors (with LABEL == ENTRY ) to be used as Headers
     * @param germplasmData Each germplasm value (GID, ENTRY CODE, etc.)
     */
    public GermplasmEntriesTableModel(List<Factor> factorHeaders,List<List<Object>> germplasmData) {
        this.factorHeaders = factorHeaders;
        this.germplasmData = germplasmData;
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
        factorHeaders = new ArrayList<Factor> ();
        germplasmData = new ArrayList<List<Object>>();
        fireTableDataChanged();
    }
    
    
}
