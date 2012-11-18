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
import javax.persistence.Transient;
import org.cimmyt.cril.ibwb.domain.filter.BaseFilter;

/**
 *
 * @author MasterGama
 */
@Entity
@Table(name = "TmsTransformations")
public class Transformations extends BaseFilter implements Serializable {
    
    @Id
    @Basic(optional = false)
    @Column(name = "transid")
    private Integer transid;
    @Column(name = "fromscaleid")
    private Integer fromscaleid;
    @Column(name = "toscaleid")
    private Integer toscaleid;
    @Column(name = "transtype")
    private String transtype;
    
    @Transient
    private ContinuousConversion continuousConversion;
    
    @Transient
    private ContinuousFunction continuousFunction;
    
    @Transient
    private DiscreteConversion discreteConversion;
    
    public Transformations(){
    	setDefault();
    }
    
    public Transformations(boolean atrNull){
        if(! atrNull){
            setDefault();
        }
    }
    
    public void setDefault(){
        setFromscaleid((Integer) 0);
        setToscaleid((Integer) 0);
        setTranstype(String.valueOf('0'));
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
     * @return the fromscaleid
     */
    public Integer getFromscaleid() {
        return fromscaleid;
    }

    /**
     * @param fromscaleid the fromscaleid to set
     */
    public void setFromscaleid(Integer fromscaleid) {
        this.fromscaleid = fromscaleid;
    }

    /**
     * @return the toscaleid
     */
    public Integer getToscaleid() {
        return toscaleid;
    }

    /**
     * @param toscaleid the toscaleid to set
     */
    public void setToscaleid(Integer toscaleid) {
        this.toscaleid = toscaleid;
    }

    /**
     * @return the transtype
     */
    public String getTranstype() {
        return transtype;
    }

    /**
     * @param transtype the transtype to set
     */
    public void setTranstype(String transtype) {
        this.transtype = transtype;
    }

    /**
     * @return the continuousConversion
     */
    public ContinuousConversion getContinuousConversion() {
        return continuousConversion;
    }

    /**
     * @param continuousConversion the continuousConversion to set
     */
    public void setContinuousConversion(ContinuousConversion continuousConversion) {
        this.continuousConversion = continuousConversion;
    }

    /**
     * @return the continuousFunction
     */
    public ContinuousFunction getContinuousFunction() {
        return continuousFunction;
    }

    /**
     * @param continuousFunction the continuousFunction to set
     */
    public void setContinuousFunction(ContinuousFunction continuousFunction) {
        this.continuousFunction = continuousFunction;
    }

    /**
     * @return the discreteConversion
     */
    public DiscreteConversion getDiscreteConversion() {
        return discreteConversion;
    }

    /**
     * @param discreteConversion the discreteConversion to set
     */
    public void setDiscreteConversion(DiscreteConversion discreteConversion) {
        this.discreteConversion = discreteConversion;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transformations other = (Transformations) obj;
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
        return "org.cimmyt.cril.ibworkbench.services.beans.Transformations[transid=" + transid + "]";
    }
}
