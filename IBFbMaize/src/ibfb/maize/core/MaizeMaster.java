
package ibfb.maize.core;


public class MaizeMaster {
    
  
    String trait;
    String p1;
    String p2;
    String p3;
    String p4;
    ObservationsTableModel original;
    ObservationsTableModel master;

    public ObservationsTableModel getMaster() {
        return master;
    }

    public void setMaster(ObservationsTableModel master) {
        this.master = master;
    }

    public ObservationsTableModel getOriginal() {
        return original;
    }

    public void setOriginal(ObservationsTableModel original) {
        this.original = original;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public String getP3() {
        return p3;
    }

    public void setP3(String p3) {
        this.p3 = p3;
    }

    public String getP4() {
        return p4;
    }

    public void setP4(String p4) {
        this.p4 = p4;
    }

    public String getTrait() {
        return trait;
    }

    public void setTrait(String trait) {
        this.trait = trait;
    }
    
    
    
    
    
}
