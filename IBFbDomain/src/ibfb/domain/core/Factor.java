

package ibfb.domain.core;

/**
 *
 * @author TMSANCHEZ
 */
public class Factor {
    private String factorName;
    private String description;
    private String property;
    private String scale;
    private String method;
    private String dataType;
    private String label;
    private Object value;
    private Integer instance;
    
    private Integer labelId;
    private Integer factorId;
    

    public Factor() {

    }

    public Factor(String factorName, String description) {
        this.factorName = factorName;
        this.description = description;
    }


    public Factor(String factorName, String description, String property, String scale, String method, String dataType, String label) {
        this.factorName = factorName;
        this.description = description;
        this.property = property;
        this.scale = scale;
        this.method = method;
        this.dataType = dataType;
        this.label = label;
    }



    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFactorName() {
        return factorName;
    }

    public void setFactorName(String factorName) {
        this.factorName = factorName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    @Override
    public String toString() {
        return "Factor{" + "factorName = " + factorName + "\tdescription = " + description + "\t property=" + property + "\tscale=" + scale + "\tmethod=" + method + "\tdataType=" + dataType + "\tlabel=" + label + '}';
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }

    public Integer getInstance() {
        return instance;
    }

    public void setInstance(Integer instance) {
        this.instance = instance;
    }

    public Integer getFactorId() {
        return factorId;
    }

    public void setFactorId(Integer factorId) {
        this.factorId = factorId;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }
    
    
}
