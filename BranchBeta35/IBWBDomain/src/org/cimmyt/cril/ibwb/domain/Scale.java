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
@Table(name = "scale")
public class Scale extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "scaleid")
    private Integer scaleid;
    @Basic(optional = false)
    @Column(name = "scname")
    private String scname;
    @Basic(optional = false)
    @Column(name = "traitid")
    private Integer traitid;
    @Basic(optional = false)
    @Column(name = "sctype")
    private String sctype;

    public Scale() {
    	setDefault();
    }
    
    public Scale(boolean atrNulls) {
    	if(!atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	scaleid = 0;
    	scname = "-";
    	traitid = 0;
    	sctype = "-";
    }

    public Scale(Integer scaleid) {
        this.scaleid = scaleid;
    }

    public Scale(Integer scaleid, String scname, Integer traitid, String sctype) {
        this.scaleid = scaleid;
        this.scname = scname;
        this.traitid = traitid;
        this.sctype = sctype;
    }

    public Integer getScaleid() {
        return scaleid;
    }

    public void setScaleid(Integer scaleid) {
        this.scaleid = scaleid;
    }

    public String getScname() {
        return scname;
    }

    public void setScname(String scname) {
        this.scname = scname;
    }

    public Integer getTraitid() {
        return traitid;
    }

    public void setTraitid(Integer traitid) {
        this.traitid = traitid;
    }

    public String getSctype() {
        return sctype;
    }

    public void setSctype(String sctype) {
        this.sctype = sctype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scaleid != null ? scaleid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Scale)) {
            return false;
        }
        Scale other = (Scale) object;
        if ((this.scaleid == null && other.scaleid != null) || (this.scaleid != null && !this.scaleid.equals(other.scaleid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Scale[scaleid=" + scaleid + "]";
    }

}
