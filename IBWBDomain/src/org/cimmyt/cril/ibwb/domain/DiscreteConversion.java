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
@Table(name = "tmsdiscrete_conversion")
public class DiscreteConversion extends BaseFilter implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "transid")
    private Integer transid;
    @Column(name = "value1")
    private Double value1;
    @Column(name = "value2")
    private Double value2;
    
    public DiscreteConversion(){
    	setDefault();
    }
    
    public DiscreteConversion(boolean atrNull) {
    	if(! atrNull)
            setDefault();
    }
    
    public void setDefault(){
    	setValue1(new Double(0));
    	setValue2(new Double(0));
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
     * @return the value1
     */
    public Double getValue1() {
        return value1;
    }

    /**
     * @param value1 the value1 to set
     */
    public void setValue1(Double value1) {
        this.value1 = value1;
    }

    /**
     * @return the value2
     */
    public Double getValue2() {
        return value2;
    }

    /**
     * @param value2 the value2 to set
     */
    public void setValue2(Double value2) {
        this.value2 = value2;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DiscreteConversion other = (DiscreteConversion) obj;
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
        return "org.cimmyt.cril.ibworkbench.services.beans.DiscreteConversion[transid=" + transid + "]";
    }
}
