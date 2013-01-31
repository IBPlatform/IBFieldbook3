/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.cimmyt.cril.ibwb.domain.filter.BaseFilter;

/**
 *
 * @author MasterGama
 */
@Entity
@Table(name = "tmscontinuous_function")
public class ContinuousFunction extends BaseFilter implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "transid")
    private Integer transid;
    @Column(name = "function")
    private String function;
    @Column(name = "funabbr")
    private String funabbr;
    
    @Transient
    private List<TmsConsistencyChecks> tmsConsistencyChecksList = new ArrayList<TmsConsistencyChecks>();
    @Transient
    private List<TmsConsistencyChecks> tmsConsistencyChecksDependencys = new ArrayList<TmsConsistencyChecks>();
    @Transient
    private String formulaOriginal;
    @Transient
    private String formulaTraducida;
            
    public ContinuousFunction(){
    	setDefault();
    }
    
    public ContinuousFunction(boolean atrNull) {
    	if(! atrNull)
            setDefault();
    }
    
    public void setDefault(){
    	setFunction("-");
    	setFunabbr("-");
    }

    /**
     * @return the transid
     */
    public Integer getTransid() {
        return transid;
    }

    /**
     * @param transid the transid to set
     */
    public void setTransid(Integer transid) {
        this.transid = transid;
    }

    /**
     * @return the function
     */
    public String getFunction() {
        return function;
    }

    /**
     * @param function the function to set
     */
    public void setFunction(String function) {
        this.function = function;
    }

    /**
     * @return the funabbr
     */
    public String getFunabbr() {
        return funabbr;
    }

    /**
     * @param funabbr the funabbr to set
     */
    public void setFunabbr(String funabbr) {
        this.funabbr = funabbr;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ContinuousFunction other = (ContinuousFunction) obj;
        if (this.transid != other.transid) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.transid;
        return hash;
    }

 
    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.ContinuousFunction[transid=" + transid + "]";
    }

    /**
     * @return the tmsConsistencyChecksList
     */
    public List<TmsConsistencyChecks> getTmsConsistencyChecksList() {
        return tmsConsistencyChecksList;
    }

    /**
     * @param tmsConsistencyChecksList the tmsConsistencyChecksList to set
     */
    public void setTmsConsistencyChecksList(List<TmsConsistencyChecks> tmsConsistencyChecksList) {
        this.tmsConsistencyChecksList = tmsConsistencyChecksList;
    }
    
    /**
     * @return the formulaOriginal
     */
    public String getFormulaOriginal() {
        return formulaOriginal;
    }

    /**
     * @param formulaOriginal the formulaOriginal to set
     */
    public void setFormulaOriginal(String formulaOriginal) {
        this.formulaOriginal = formulaOriginal;
    }

    /**
     * @return the formulaTraducida
     */
    public String getFormulaTraducida() {
        return formulaTraducida;
    }

    /**
     * @param formulaTraducida the formulaTraducida to set
     */
    public void setFormulaTraducida(String formulaTraducida) {
        this.formulaTraducida = formulaTraducida;
    }

    /**
     * @return the tmsConsistencyChecksDependencys
     */
    public List<TmsConsistencyChecks> getTmsConsistencyChecksDependencys() {
        return tmsConsistencyChecksDependencys;
    }
}
