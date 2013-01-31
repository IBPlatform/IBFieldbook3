/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.domain.core;

import java.io.Serializable;

/**
 *
 * @author TMSANCHEZ
 */
public class FactorLabel implements Serializable {

    private String factorName;
    private Integer level;
    private Object label;
    private String value;

    public FactorLabel() {
    }

    public FactorLabel(String factorName, Integer level, Object label) {
        this.factorName = factorName;
        this.level = level;
        this.label = label;
    }

    public String getFactorName() {
        return factorName;
    }

    public void setFactorName(String factorName) {
        this.factorName = factorName;
    }

    public Object getLabel() {
        return label;
    }

    public void setLabel(Object label) {
        this.label = label;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
}
