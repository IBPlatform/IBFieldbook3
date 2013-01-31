
package ibfb.domain.core;


public class Experiment {
    public static final String TRIAL = "T";
    public static final String NURSERY = "N";
    private String type;
    private String description;

    
    
    
    public Experiment(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
