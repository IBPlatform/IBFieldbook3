
package ibfb.domain.core;

import java.io.Serializable;

/**
 *
 * @author TMSANCHEZ
 */
public class Variate implements Serializable {
    
    public static final String ENUMERATED = "ENUMERATED";
    private String variateName;
    private String description;
    private String property;
    private String scale;
    private String method;
    private String dataType;

    private Integer variateId;
    private Integer studyId;    
    
    public Variate() {}

    public Variate(String variateName, String description) {
        this.variateName = variateName;
        this.description = description;
    }


    public Variate(String variateName, String description, String property, String scale, String method, String dataType) {
        this.variateName = variateName;
        this.description = description;
        this.property = property;
        this.scale = scale;
        this.method = method;
        this.dataType = dataType;
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

    public String getVariateName() {
        return variateName;
    }

    public void setVariateName(String variateName) {
        this.variateName = variateName;
    }

    @Override
    public String toString() {
        //return "Variate{" + "variateName=" + variateName + "description=" + description + "property=" + property + "scale=" + scale + "method=" + method + "dataType=" + dataType + '}';
        if (scale == null) {
         return variateName + " (" + " " + ")";   
        } else {
         return variateName + " (" + scale.trim() + ")";
        }
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




}
