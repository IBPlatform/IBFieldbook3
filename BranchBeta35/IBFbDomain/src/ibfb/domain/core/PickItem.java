package ibfb.domain.core;

/**
 *
 * @author TMSANCHEZ
 */
public class PickItem {
    
    private String value;
    private String description;

    public PickItem() {
    }

    public PickItem(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    

}
