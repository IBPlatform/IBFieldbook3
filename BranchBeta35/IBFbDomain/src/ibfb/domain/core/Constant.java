
package ibfb.domain.core;

import java.io.Serializable;

/**
 *
 * @author TMSANCHEZ
 */
public class Constant implements Serializable {
    private Integer instance;
    private String constantName;
    private String description;
    private String property;
    private String scale;
    private String method;
    private String dataType;
    private Object value;
    
    private Integer variateId;
    private Integer studyId;
    private Integer ounitid;

    public Constant() {}

    public Constant(String constantName, String description) {
        this.constantName = constantName;
        this.description = description;
    }


    public Constant(String constantName, String description, String property, String scale, String method, String dataType, Object value) {
        this.constantName = constantName;
        this.description = description;
        this.property = property;
        this.scale = scale;
        this.method = method;
        this.dataType = dataType;
        this.value = value;
    }

    public String getConstantName() {
        return constantName;
    }

    public void setConstantName(String constantName) {
        this.constantName = constantName;
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
        //return "Constant{" + "constantName=" + constantName + "description=" + description + "property=" + property + "scale=" + scale + "method=" + method + "dataType=" + dataType + "value=" + value + '}';
        return constantName + " (" + scale.trim() + ")";
    }

    public Integer getInstance() {
        return instance;
    }

    public void setInstance(Integer instance) {
        this.instance = instance;
    }

    public Integer getStudyId() {
        return studyId;
    }

    public void setStudyId(Integer studyId) {
        this.studyId = studyId;
    }

    public Integer getVariateId() {
        return variateId;
    }

    public void setVariateId(Integer variateId) {
        this.variateId = variateId;
    }

    /**
     * @return the ounitid
     */
    public Integer getOunitid() {
        return ounitid;
    }

    /**
     * @param ounitid the ounitid to set
     */
    public void setOunitid(Integer ounitid) {
        this.ounitid = ounitid;
    }
}
