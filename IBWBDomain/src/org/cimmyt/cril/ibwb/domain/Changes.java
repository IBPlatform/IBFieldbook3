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
@Table(name = "changes")
public class Changes extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cid")
    private Integer cid;
    @Basic(optional = false)
    @Column(name = "ctable")
    private String ctable;
    @Basic(optional = false)
    @Column(name = "cfield")
    private String cfield;
    @Basic(optional = false)
    @Column(name = "crecord")
    private Integer crecord;
    @Column(name = "cfrom")
    private Integer cfrom;
    @Column(name = "cto")
    private Integer cto;
    @Column(name = "cdate")
    private Integer cdate;
    @Column(name = "ctime")
    private Integer ctime;
    @Basic(optional = false)
    @Column(name = "cgroup")
    private String cgroup;
    @Column(name = "cuid")
    private Integer cuid;
    @Column(name = "cref")
    private Integer cref;
    @Column(name = "cstatus")
    private Integer cstatus;
    @Basic(optional = false)
    @Column(name = "cdesc")
    private String cdesc;

    public Changes() {
    	setDefault();
    }
    
    public Changes(boolean atrNulls) {
    	if(! atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	cid = 0;
    	ctable = "-";
    	cfield = "-";
    	crecord = 0;
    	cfrom = 0;
    	cto = 0;
    	cdate = 0;
    	ctime = 0;
    	cgroup = "-";
    	cuid = 0;
    	cref = 0;
    	cstatus = 0;
    	cdesc = "-";
    }

    public Changes(Integer cid) {
        this.cid = cid;
    }

    public Changes(Integer cid, String ctable, String cfield, Integer crecord, String cgroup, String cdesc) {
        this.cid = cid;
        this.ctable = ctable;
        this.cfield = cfield;
        this.crecord = crecord;
        this.cgroup = cgroup;
        this.cdesc = cdesc;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCtable() {
        return ctable;
    }

    public void setCtable(String ctable) {
        this.ctable = ctable;
    }

    public String getCfield() {
        return cfield;
    }

    public void setCfield(String cfield) {
        this.cfield = cfield;
    }

    public Integer getCrecord() {
        return crecord;
    }

    public void setCrecord(Integer crecord) {
        this.crecord = crecord;
    }

    public Integer getCfrom() {
        return cfrom;
    }

    public void setCfrom(Integer cfrom) {
        this.cfrom = cfrom;
    }

    public Integer getCto() {
        return cto;
    }

    public void setCto(Integer cto) {
        this.cto = cto;
    }

    public Integer getCdate() {
        return cdate;
    }

    public void setCdate(Integer cdate) {
        this.cdate = cdate;
    }

    public Integer getCtime() {
        return ctime;
    }

    public void setCtime(Integer ctime) {
        this.ctime = ctime;
    }

    public String getCgroup() {
        return cgroup;
    }

    public void setCgroup(String cgroup) {
        this.cgroup = cgroup;
    }

    public Integer getCuid() {
        return cuid;
    }

    public void setCuid(Integer cuid) {
        this.cuid = cuid;
    }

    public Integer getCref() {
        return cref;
    }

    public void setCref(Integer cref) {
        this.cref = cref;
    }

    public Integer getCstatus() {
        return cstatus;
    }

    public void setCstatus(Integer cstatus) {
        this.cstatus = cstatus;
    }

    public String getCdesc() {
        return cdesc;
    }

    public void setCdesc(String cdesc) {
        this.cdesc = cdesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cid != null ? cid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Changes)) {
            return false;
        }
        Changes other = (Changes) object;
        if ((this.cid == null && other.cid != null) || (this.cid != null && !this.cid.equals(other.cid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Changes[cid=" + cid + "]";
    }

}
