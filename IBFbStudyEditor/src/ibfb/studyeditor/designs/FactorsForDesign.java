
package ibfb.studyeditor.designs;


public class FactorsForDesign {
    String factorNameDefault;
    String factorName;
    String factorLabel;
    String factorNameInCSV;
    boolean founded;

    public FactorsForDesign() {
        factorNameDefault="";
        factorName="";
        factorLabel="";
        factorNameInCSV="";
        founded=false;       
    }

    
    
    
    public String getFactorNameInCSV() {
        return factorNameInCSV;
    }

    public void setFactorNameInCSV(String factorNameInCSV) {
        this.factorNameInCSV = factorNameInCSV;
    }
    
    public String getFactorLabel() {
        return factorLabel;
    }

    public void setFactorLabel(String factorLabel) {
        this.factorLabel = factorLabel;
    }

    
    public String getFactorNameDefault() {
        return factorNameDefault;
    }

    public void setFactorNameDefault(String factorNameDefault) {
        this.factorNameDefault = factorNameDefault;
    }

    public String getFactorName() {
        return factorName;
    }

    public void setFactorName(String factorName) {
        this.factorName = factorName;
    }

    public boolean isFounded() {
        return founded;
    }

    public void setFounded(boolean exist) {
        this.founded = exist;
    }
    
    
    
}
