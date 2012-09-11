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
@Table(name = "transformations")
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
    private Integer transtype;
    
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
        setTranstype((Integer) 0);
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
    public Integer getTranstype() {
        return transtype;
    }

    /**
     * @param transtype the transtype to set
     */
    public void setTranstype(Integer transtype) {
        this.transtype = transtype;
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
