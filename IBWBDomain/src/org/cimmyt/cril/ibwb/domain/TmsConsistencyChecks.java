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
@Table(name = "tmsconsistency_checks")
public class TmsConsistencyChecks extends BaseFilter implements Serializable {
    
    @Id
    @Basic(optional = false)
    @Column(name = "implicationid")
    private Integer implicationid;
    @Column(name = "logicaloperator")
    private String logicaloperator;
    @Column(name = "traitid")
    private Integer traitid;
    @Column(name = "scaleid")
    private Integer scaleid;
    @Column(name = "methodid")
    private Integer methodid;
    @Column(name = "value")
    private String value;
    @Column(name = "link")
    private Integer link;
    
    
    public TmsConsistencyChecks(){
    	setDefault();
    }
    
    public TmsConsistencyChecks(boolean atrNull){
        if(! atrNull){
            setDefault();
        }
    }
    
    public void setDefault(){
        setImplicationid((Integer) 0);
        setLogicaloperator(String.valueOf('0'));
        setTraitid((Integer) 0);
        setScaleid((Integer) 0);
        setMethodid((Integer) 0);
        setValue("-");
        setLink((Integer) 0);
    }

    /**
     * @return the implicationid
     */
    public Integer getImplicationid() {
        return implicationid;
    }

    /**
     * @param implicationid the implicationid to set
     */
    public void setImplicationid(Integer implicationid) {
        this.implicationid = implicationid;
    }

    /**
     * @return the logicaloperator
     */
    public String getLogicaloperator() {
        return logicaloperator;
    }

    /**
     * @param logicaloperator the logicaloperator to set
     */
    public void setLogicaloperator(String logicaloperator) {
        this.logicaloperator = logicaloperator;
    }

    /**
     * @return the traitid
     */
    public Integer getTraitid() {
        return traitid;
    }

    /**
     * @param traitid the traitid to set
     */
    public void setTraitid(Integer traitid) {
        this.traitid = traitid;
    }

    /**
     * @return the scaleid
     */
    public Integer getScaleid() {
        return scaleid;
    }

    /**
     * @param scaleid the scaleid to set
     */
    public void setScaleid(Integer scaleid) {
        this.scaleid = scaleid;
    }

    /**
     * @return the methodid
     */
    public Integer getMethodid() {
        return methodid;
    }

    /**
     * @param methodid the methodid to set
     */
    public void setMethodid(Integer methodid) {
        this.methodid = methodid;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the link
     */
    public Integer getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(Integer link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TmsConsistencyChecks other = (TmsConsistencyChecks) obj;
        if (this.implicationid != other.implicationid) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.implicationid;
        return hash;
    }

 
    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.TmsConsistencyChecks[implicationid=" + implicationid + "]";
    }
}
