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
 * @author jgcamarena
 */
@Entity
@Table(name = "obsunit")
public class Obsunit extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ounitid")
    private Integer ounitid;
    @Basic(optional = false)
    @Column(name = "effectid")
    private Integer effectid;

    public Obsunit() {
    	setDefault();
    }
    
    public Obsunit(boolean atrNulls) {
    	if(!atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	ounitid = 0;
    	effectid = 0;
    }

    public Obsunit(Integer ounitid) {
        this.ounitid = ounitid;
    }

    public Obsunit(Integer ounitid, Integer effectid) {
        this.ounitid = ounitid;
        this.effectid = effectid;
    }

    public Integer getOunitid() {
        return ounitid;
    }

    public void setOunitid(Integer ounitid) {
        this.ounitid = ounitid;
    }

    public Integer getEffectid() {
        return effectid;
    }

    public void setEffectid(Integer effectid) {
        this.effectid = effectid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ounitid != null ? ounitid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Obsunit)) {
            return false;
        }
        Obsunit other = (Obsunit) object;
        if ((this.ounitid == null && other.ounitid != null) || (this.ounitid != null && !this.ounitid.equals(other.ounitid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Obsunit[ounitid=" + ounitid + "]";
    }

}
