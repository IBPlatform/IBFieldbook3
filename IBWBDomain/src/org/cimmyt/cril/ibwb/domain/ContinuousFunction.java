/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.cimmyt.cril.ibwb.domain.filter.BaseFilter;

/**
 *
 * @author MasterGama
 */
@Entity
@Table(name = "TmsContinuous-function")
public class ContinuousFunction extends BaseFilter implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "transid")
    private Integer transid;
    @Column(name = "function")
    private String function;
    @Column(name = "funabbr")
    private String funabbr;
    
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
}
