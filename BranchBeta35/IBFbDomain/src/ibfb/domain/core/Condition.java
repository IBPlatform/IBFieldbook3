
package ibfb.domain.core;

/**
 *
 * @author TMSANCHEZ
 */
public class Condition {
    
    private String conditionName;
    private String description;
    private String property;
    private String scale;
    private String method;
    private String dataType;
    private Object value;
    private String label;
    private Integer instance;
    
    private Integer labelId;
    private Integer factorId;
    private Integer levelNo;
    
    public Condition() {
        
    }
    
    public Condition(String conditionName, String description, String property, String scale, String method, String dataType, Object value, String label) {
        this.conditionName = conditionName;
        this.description = description;
        this.property = property;
        this.scale = scale;
        this.method = method;
        this.dataType = dataType;
        this.value = value;
        this.label = label;
    }
    
    public String getConditionName() {
        return conditionName;
    }
    
    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
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

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Condition{" + "conditionName=" + conditionName + "description=" + description + "property=" + property + "scale=" + scale + "method=" + method + "dataType=" + dataType + "value=" + value + "label=" + label + '}';
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

    /**
     * @return the levelNo
     */
    public Integer getLevelNo() {
        return levelNo;
    }

    /**
     * @param levelNo the levelNo to set
     */
    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }
    
    
}
