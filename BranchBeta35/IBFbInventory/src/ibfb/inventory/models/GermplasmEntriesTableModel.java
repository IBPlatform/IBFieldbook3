
package ibfb.inventory.models;

import ibfb.domain.core.Factor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class GermplasmEntriesTableModel extends AbstractTableModel {

    private boolean hasChecks=false;
    private List<Factor> factorHeaders;
    private List<List<Object>> germplasmData;    
    private String[] checkHeaders={"Initial position","Frequency"};
    private boolean isForInventory=false;
    private boolean enabledLocation=false;
    private boolean enabledComments=false;
    private boolean enabledAmount=false;
    private boolean enabledScale=false;
    
    private boolean seActualizaGID=false;

   
     public boolean isEnabledAmount() {
        return enabledAmount;
    }
      public boolean isEnabledScale() {
        return enabledScale;
    }
    
    public boolean isEnabledComments() {
        return enabledComments;
    }

    public boolean SeActualizaGID() {
        return seActualizaGID;
    }

    public void setSeActualizaGID(boolean seActualizaGID) {
        this.seActualizaGID = seActualizaGID;
    }

    
    
    public void setEnabledComments(boolean enabledComments) {
        this.enabledComments = enabledComments;
    }

    public boolean isEnabledLocation() {
        return enabledLocation;
    }

    public void setEnabledLocation(boolean enabledLocation) {
        this.enabledLocation = enabledLocation;
    }
    
    
      public void setEnabledAmount(boolean enabledamount) {
        this.enabledAmount= enabledamount;
    }
        public void setEnabledScale(boolean enabledSclae) {
        this.enabledScale = enabledSclae;
    }
    

    public GermplasmEntriesTableModel() {
        clearTable();
    }
    
    public GermplasmEntriesTableModel(List<Factor> factorHeaders,List<List<Object>> germplasmData) {
        this.factorHeaders = factorHeaders;
        this.germplasmData = germplasmData;
      
    }

    public boolean IsForInventory() {
        return isForInventory;
    }

    public void setIsForInventory(boolean isForInventory) {
        this.isForInventory = isForInventory;
    }

    
    

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

       
           if(SeActualizaGID()){
            if (columnIndex == 2) {
                return true;
            }
            
        }
        
        
        
        if(IsForInventory()){
        
            switch (columnIndex) {
                case 3:
                    if (isEnabledLocation()) {
                        return true;
                    } else {
                        return false;
                    }


                case 4:
                    if (isEnabledComments()) {
                        return true;
                    } else {
                        return false;
                    }
                case 5:
                    if (isEnabledAmount()) {
                        return true;
                    } else {
                        return false;
                    }


                case 6:
                    if (isEnabledScale()) {
                        return true;
                    } else {
                        return false;
                    }


                   
                 
            }
 
        }
        
        
        if(!hasChecks){
            return false;
        }else{
            if(columnIndex>factorHeaders.size()-1){
                return false;  //true if position and frequency will be editables
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
            return factorHeaders.size()+2;
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

        
        if(SeActualizaGID()){
            if (columnIndex == 2) {
                List<Object> columnValues = germplasmData.get(rowIndex);
                columnValues.set(columnIndex, aValue);
                fireTableCellUpdated(rowIndex, columnIndex);  
            }
            
        }
        
        
        
        if (IsForInventory()) {
            if (columnIndex > 2) {
                List<Object> columnValues = germplasmData.get(rowIndex);
                columnValues.set(columnIndex, aValue);
                fireTableCellUpdated(rowIndex, columnIndex);
            }
        }


        if (hasChecks) {

            List<Object> columnValues = germplasmData.get(rowIndex);

            columnValues.set(columnIndex, aValue);


            fireTableCellUpdated(rowIndex, columnIndex);
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
