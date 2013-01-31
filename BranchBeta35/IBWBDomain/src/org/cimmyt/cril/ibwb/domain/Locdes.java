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
@Table(name = "locdes")
public class Locdes extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ldid")
    private Integer ldid;
    @Basic(optional = false)
    @Column(name = "locid")
    private Integer locid;
    @Basic(optional = false)
    @Column(name = "dtype")
    private Integer dtype;
    @Basic(optional = false)
    @Column(name = "duid")
    private Integer duid;
    @Basic(optional = false)
    @Column(name = "dval")
    private String dval;
    @Basic(optional = false)
    @Column(name = "ddate")
    private Integer ddate;
    @Basic(optional = false)
    @Column(name = "dref")
    private Integer dref;

    public Locdes() {
    	setDefault();
    }
    
    public Locdes(boolean atrNulls) {
    	if(! atrNulls)
    		setDefault();
    }

    public void setDefault(){
    	ldid = 0;
    	locid = 0;
    	dtype = 0;
    	duid = 0;
    	dval = "-";
    	ddate = 0;
    	dref = 0;
    }

    public Integer getLdid() {
        return ldid;
    }

    public void setLdid(Integer ldid) {
        this.ldid = ldid;
    }

    public Integer getLocid() {
        return locid;
    }

    public void setLocid(Integer locid) {
        this.locid = locid;
    }

    public Integer getDtype() {
        return dtype;
    }

    public void setDtype(Integer dtype) {
        this.dtype = dtype;
    }

    public Integer getDuid() {
        return duid;
    }

    public void setDuid(Integer duid) {
        this.duid = duid;
    }

    public String getDval() {
        return dval;
    }

    public void setDval(String dval) {
        this.dval = dval;
    }

    public Integer getDdate() {
        return ddate;
    }

    public void setDdate(Integer ddate) {
        this.ddate = ddate;
    }

    public Integer getDref() {
        return dref;
    }

    public void setDref(Integer dref) {
        this.dref = dref;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Locdes other = (Locdes) obj;
        if (this.ldid != other.ldid) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.ldid;
        return hash;
    }

   
    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Locdes[locdesPK=" + ldid + "]";
    }

}
