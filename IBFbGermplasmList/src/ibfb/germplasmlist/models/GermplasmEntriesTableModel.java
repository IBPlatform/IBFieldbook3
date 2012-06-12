
package ibfb.germplasmlist.models;

import ibfb.domain.core.Factor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class GermplasmEntriesTableModel extends AbstractTableModel {

    private boolean hasChecks=false;
    private List<Factor> factorHeaders;
    private List<List<Object>> germplasmData;    
    private String[] checkHeaders={"Selection"};
    private boolean estaHabilitadoSeleccion=false;

    public boolean isEstaHabilitadoSeleccion() {
        return estaHabilitadoSeleccion;
    }

    public void setEstaHabilitadoSeleccion(boolean estaHabilitadoSeleccion) {
        this.estaHabilitadoSeleccion = estaHabilitadoSeleccion;
    }

    
    
    
    public GermplasmEntriesTableModel() {
        clearTable();
    }
    
    public GermplasmEntriesTableModel(List<Factor> factorHeaders,List<List<Object>> germplasmData) {
        this.factorHeaders = factorHeaders;
        this.germplasmData = germplasmData;
      
    }


    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
       
        
    
        
        
        if(!hasChecks){
            return false;
        }else{
            
               if(estaHabilitadoSeleccion && columnIndex==3){
               return true;
               }
               
               if(!estaHabilitadoSeleccion && columnIndex==3){
               return false;
               }
            
            
            if(columnIndex>factorHeaders.size()-1){
                return true; 
            }
        }
        
        return super.isCellEditable(rowIndex, columnIndex);
    }

    
    
    
    public void setHasChecks(boolean hasChk){
        this.hasChecks=hasChk;
    }
    
    
    
    @Override
    public int getRowCount() {
        return germplasmData.size();
    }

    @Override
    public int getColumnCount() {
        if(!hasChecks){
        return factorHeaders.size();}
        else{
            return factorHeaders.size()+1;
        }
    }

    @Override
    public String getColumnName(int column) {
        if (!hasChecks) {
            return factorHeaders.get(column).getFactorName();
        } else {

            if (column < factorHeaders.size()) {
                return factorHeaders.get(column).getFactorName();
            } else {
                return checkHeaders[column - factorHeaders.size()];
            }

        }


    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         List<Object> columnValues = germplasmData.get(rowIndex);
         return columnValues.get(columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
       
         if(hasChecks){
             
             
             
             
                          if (columnIndex == 3) {


                 try {
                     if (Integer.parseInt(aValue.toString()) >= 0) {
                         List<Object> columnValues = germplasmData.get(rowIndex);
                         columnValues.set(columnIndex, aValue);

                         fireTableCellUpdated(rowIndex, columnIndex);
                         System.out.println("Valor valido");
                     }
                 } catch (Exception e) {
                     System.out.println("NO VALIDO NUMERO");
                 }



           
       
       
         }else{
             
             
             
                      
               List<Object> columnValues = germplasmData.get(rowIndex);
               
             columnValues.set(columnIndex, aValue);


             fireTableCellUpdated(rowIndex, columnIndex);
                          }
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
    
    public void removeRow(int row) {
        germplasmData.remove(row);
        fireTableRowsDeleted(row, row);
    }
    
}
