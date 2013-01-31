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
@Table(name = "instln")
public class Instln extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "admin")
    private Integer admin;
    @Column(name = "udate")
    private Integer udate;
    @Basic(optional = false)
    @Column(name = "ugid")
    private Integer ugid;
    @Column(name = "ulocn")
    private Integer ulocn;
    @Basic(optional = false)
    @Column(name = "ucid")
    private Integer ucid;
    @Basic(optional = false)
    @Column(name = "unid")
    private Integer unid;
    @Basic(optional = false)
    @Column(name = "uaid")
    private Integer uaid;
    @Basic(optional = false)
    @Column(name = "uldid")
    private Integer uldid;
    @Column(name = "umethn")
    private Integer umethn;
    @Column(name = "ufldno")
    private Integer ufldno;
    @Column(name = "urefno")
    private Integer urefno;
    @Column(name = "upid")
    private Integer upid;
    @Basic(optional = false)
    @Column(name = "idesc")
    private String idesc;
    @Column(name = "ulistid")
    private Integer ulistid;
    @Column(name = "dms_status")
    private Integer dmsStatus;
    @Column(name = "ulrecid")
    private Integer ulrecid;
    @Basic(optional = false)
    @Column(name = "instalid")
    private Integer instalid;

    public Instln() {
    	setDefault();
    }
    
    public Instln(boolean atrNulls) {
    	if(!atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	instalid = 0;
    	admin = 0;
    	udate = 0;
    	ugid = 0;
    	ulocn = 0;
    	ucid = 0;
    	unid = 0;
    	uaid = 0;
    	uldid = 0;
    	umethn = 0;
    	ufldno = 0;
    	urefno = 0;
    	upid = 0;
    	idesc = "-";
    	ulistid = 0;
    	dmsStatus = 0;
    	ulrecid = 0;
    }

    public Instln(Integer admin) {
        this.admin = admin;
    }

    public Instln(Integer admin, Integer instalid, Integer ugid, Integer ucid, Integer unid, Integer uaid, Integer uldid, String idesc) {
        this.admin = admin;
        this.instalid = instalid;
        this.ugid = ugid;
        this.ucid = ucid;
        this.unid = unid;
        this.uaid = uaid;
        this.uldid = uldid;
        this.idesc = idesc;
    }

    public Integer getInstalid() {
        return instalid;
    }

    public void setInstalid(Integer instalid) {
        this.instalid = instalid;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public Integer getUdate() {
        return udate;
    }

    public void setUdate(Integer udate) {
        this.udate = udate;
    }

    public Integer getUgid() {
        return ugid;
    }

    public void setUgid(Integer ugid) {
        this.ugid = ugid;
    }

    public Integer getUlocn() {
        return ulocn;
    }

    public void setUlocn(Integer ulocn) {
        this.ulocn = ulocn;
    }

    public Integer getUcid() {
        return ucid;
    }

    public void setUcid(Integer ucid) {
        this.ucid = ucid;
    }

    public Integer getUnid() {
        return unid;
    }

    public void setUnid(Integer unid) {
        this.unid = unid;
    }

    public Integer getUaid() {
        return uaid;
    }

    public void setUaid(Integer uaid) {
        this.uaid = uaid;
    }

    public Integer getUldid() {
        return uldid;
    }

    public void setUldid(Integer uldid) {
        this.uldid = uldid;
    }

    public Integer getUmethn() {
        return umethn;
    }

    public void setUmethn(Integer umethn) {
        this.umethn = umethn;
    }

    public Integer getUfldno() {
        return ufldno;
    }

    public void setUfldno(Integer ufldno) {
        this.ufldno = ufldno;
    }

    public Integer getUrefno() {
        return urefno;
    }

    public void setUrefno(Integer urefno) {
        this.urefno = urefno;
    }

    public Integer getUpid() {
        return upid;
    }

    public void setUpid(Integer upid) {
        this.upid = upid;
    }

    public String getIdesc() {
        return idesc;
    }

    public void setIdesc(String idesc) {
        this.idesc = idesc;
    }

    public Integer getUlistid() {
        return ulistid;
    }

    public void setUlistid(Integer ulistid) {
        this.ulistid = ulistid;
    }

    public Integer getDmsStatus() {
        return dmsStatus;
    }

    public void setDmsStatus(Integer dmsStatus) {
        this.dmsStatus = dmsStatus;
    }

    public Integer getUlrecid() {
        return ulrecid;
    }

    public void setUlrecid(Integer ulrecid) {
        this.ulrecid = ulrecid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (admin != null ? admin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Instln)) {
            return false;
        }
        Instln other = (Instln) object;
        if ((this.admin == null && other.admin != null) || (this.admin != null && !this.admin.equals(other.admin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Instln[admin=" + admin + "]";
    }

}
