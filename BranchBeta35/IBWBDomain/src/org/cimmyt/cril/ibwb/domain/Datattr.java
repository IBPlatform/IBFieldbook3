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
@Table(name = "datattr")
public class Datattr extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "dattrid")
    private Integer dattrid;
    @Column(name = "datype")
    private Integer datype;
    @Basic(optional = false)
    @Column(name = "datable")
    private String datable;
    @Basic(optional = false)
    @Column(name = "ounitid")
    private Integer ounitid;
    @Basic(optional = false)
    @Column(name = "variatid")
    private Integer variatid;
    @Basic(optional = false)
    @Column(name = "datval")
    private String datval;

    public Datattr() {
    	setDefault();
    }
    
    public Datattr(boolean atrNulls) {
    	if(! atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	dattrid = 0;
    	datype = 0;
    	datable = "-";
    	ounitid = 0;
    	variatid = 0;
    	datval = "-";
    }

    public Datattr(Integer dattrid) {
        this.dattrid = dattrid;
    }

    public Datattr(Integer dattrid, String datable, Integer ounitid, Integer variatid, String datval) {
        this.dattrid = dattrid;
        this.datable = datable;
        this.ounitid = ounitid;
        this.variatid = variatid;
        this.datval = datval;
    }

    public Integer getDattrid() {
        return dattrid;
    }

    public void setDattrid(Integer dattrid) {
        this.dattrid = dattrid;
    }

    public Integer getDatype() {
        return datype;
    }

    public void setDatype(Integer datype) {
        this.datype = datype;
    }

    public String getDatable() {
        return datable;
    }

    public void setDatable(String datable) {
        this.datable = datable;
    }

    public Integer getOunitid() {
        return ounitid;
    }

    public void setOunitid(Integer ounitid) {
        this.ounitid = ounitid;
    }

    public Integer getVariatid() {
        return variatid;
    }

    public void setVariatid(Integer variatid) {
        this.variatid = variatid;
    }

    public String getDatval() {
        return datval;
    }

    public void setDatval(String datval) {
        this.datval = datval;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dattrid != null ? dattrid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datattr)) {
            return false;
        }
        Datattr other = (Datattr) object;
        if ((this.dattrid == null && other.dattrid != null) || (this.dattrid != null && !this.dattrid.equals(other.dattrid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Datattr[dattrid=" + dattrid + "]";
    }

}
