
package ibfb.germplasmlist.methods;

import ibfb.germplasmlist.models.GermplasmEntriesTableModel;
import java.util.ArrayList;


public class SelectionProcess {
    
    private int convention; //0=wheat   1= maize   2=rice
    private boolean sameForAll=false;
    private ArrayList<Integer> samples;
    private int samplesForAll;

    public int getConvention() {
        return convention;
    }

    public void setConvention(int convention) {
        this.convention = convention;
    }

    public boolean isSameForAll() {
        return sameForAll;
    }

    public void setSameForAll(boolean sameForAll) {
        this.sameForAll = sameForAll;
    }
    
    
    
    public void processData(GermplasmEntriesTableModel modelo){
       
        
        MethodsClass metodos = new MethodsClass();
        
        if(sameForAll){
            
            
          GermplasmEntriesTableModel tableModelA = new GermplasmEntriesTableModel();;   
            
            
            
            
        }else{
          
            
            
        }
        
   
        
    }
    
    
    
    
    
}
