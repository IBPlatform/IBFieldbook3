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
@Table(name = "dmsattr")
public class Dmsattr extends BaseFilter implements Serializable {
    public static final String DMSATYPE_LIST = "LIST";
    
    public static final int DMSATYPE_FTID = 804;
    public static final int DMSATYPE_FOCC = 805;
    public static final int DMSATYPE_FENT = 806;
    public static final int DMSATYPE_MTID = 807;
    public static final int DMSATYPE_MOCC = 808;
    public static final int DMSATYPE_MENT = 809;
    public static final int DMSATYPE_STID = 810;
    public static final int DMSATYPE_SOCC = 811;
    public static final int DMSATYPE_SENT = 812;
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "dmsatid")
    private Integer dmsatid;
    @Column(name = "dmsatype")
    private Integer dmsatype;
    @Basic(optional = false)
    @Column(name = "dmsatab")
    private String dmsatab;
    @Column(name = "dmsatrec")
    private Integer dmsatrec;
    @Basic(optional = false)
    @Column(name = "dmsatval")
    private String dmsatval;

    public Dmsattr() {
    	setDefault();
    }
    
    public Dmsattr(boolean atrNulls) {
    	if(! atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	dmsatid = 0;
    	dmsatype = 0;
    	dmsatab = "-";
    	dmsatrec = 0;
    	dmsatval = "-";
    }

    public Dmsattr(Integer dmsatid) {
        this.dmsatid = dmsatid;
    }

    public Dmsattr(Integer dmsatid, String dmsatab, String dmsatval) {
        this.dmsatid = dmsatid;
        this.dmsatab = dmsatab;
        this.dmsatval = dmsatval;
    }

    public Dmsattr(Integer dmsatid, Integer dmsatype, String dmsatab, Integer dmsatrec, String dmsatval) {
        this.dmsatid = dmsatid;
        this.dmsatype = dmsatype;
        this.dmsatab = dmsatab;
        this.dmsatrec = dmsatrec;
        this.dmsatval = dmsatval;
    }

    public Integer getDmsatid() {
        return dmsatid;
    }

    public void setDmsatid(Integer dmsatid) {
        this.dmsatid = dmsatid;
    }

    public Integer getDmsatype() {
        return dmsatype;
    }

    public void setDmsatype(Integer dmsatype) {
        this.dmsatype = dmsatype;
    }

    public String getDmsatab() {
        return dmsatab;
    }

    public void setDmsatab(String dmsatab) {
        this.dmsatab = dmsatab;
    }

    public Integer getDmsatrec() {
        return dmsatrec;
    }

    public void setDmsatrec(Integer dmsatrec) {
        this.dmsatrec = dmsatrec;
    }

    public String getDmsatval() {
        return dmsatval;
    }

    public void setDmsatval(String dmsatval) {
        this.dmsatval = dmsatval;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmsatid != null ? dmsatid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dmsattr)) {
            return false;
        }
        Dmsattr other = (Dmsattr) object;
        if ((this.dmsatid == null && other.dmsatid != null) || (this.dmsatid != null && !this.dmsatid.equals(other.dmsatid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Dmsattr[dmsatid=" + dmsatid + "]";
    }

}
