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
import javax.persistence.Transient;
import org.cimmyt.cril.ibwb.domain.filter.BaseFilter;

/**
 *
 * @author jgcamarena
 */
@Entity
@Table(name = "germplsm")
public class Germplsm extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "gid")
    private Integer gid;
    @Basic(optional = false)
    @Column(name = "methn")
    private Integer methn;
    @Basic(optional = false)
    @Column(name = "gnpgs")
    private Integer gnpgs;
    @Basic(optional = false)
    @Column(name = "gpid1")
    private Integer gpid1;
    @Basic(optional = false)
    @Column(name = "gpid2")
    private Integer gpid2;
    @Basic(optional = false)
    @Column(name = "germuid")
    private Integer germuid;
    @Basic(optional = false)
    @Column(name = "lgid")
    private Integer lgid;
    @Basic(optional = false)
    @Column(name = "glocn")
    private Integer glocn;
    @Basic(optional = false)
    @Column(name = "gdate")
    private Integer gdate;
    @Basic(optional = false)
    @Column(name = "gref")
    private Integer gref;
    @Basic(optional = false)
    @Column(name = "grplce")
    private Integer grplce;
    @Column(name = "mgid")
    private Integer mgid;
    
    // following columns are only for WHEAT
    // temporary disable this columns and marke them as Transient
    // 20120702
    //@Column(name = "cid")
    @Transient
    private Integer cid;
    //@Column(name = "sid")
    @Transient
    private Integer sid;
    //*@Column(name = "gchange")
    @Transient
    private Integer gchange;

    public Germplsm() {
    	setDefault();
    }
    
    public Germplsm(boolean atrNull) {
    	if(!atrNull)
    		setDefault();
    }
    
    public void setDefault(){
    	gid = 0;
    	methn = 0;
    	gnpgs = 0;
    	gpid1 = 0;
    	gpid2 = 0;
    	germuid = 0;
    	lgid = 0;
    	glocn = 0;
    	gdate = 0;
    	gref = 0;
    	grplce = 0;
    	mgid = 0;
    	cid = 0;
    	sid = 0;
    	gchange = 0;
    }

    public Germplsm(Integer gid) {
        this.gid = gid;
    }

    public Germplsm(Integer gid, Integer methn, Integer gnpgs, Integer gpid1, Integer gpid2, Integer germuid, Integer lgid, Integer glocn, Integer gdate, Integer gref, Integer grplce) {
        this.gid = gid;
        this.methn = methn;
        this.gnpgs = gnpgs;
        this.gpid1 = gpid1;
        this.gpid2 = gpid2;
        this.germuid = germuid;
        this.lgid = lgid;
        this.glocn = glocn;
        this.gdate = gdate;
        this.gref = gref;
        this.grplce = grplce;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getMethn() {
        return methn;
    }

    public void setMethn(Integer methn) {
        this.methn = methn;
    }

    public Integer getGnpgs() {
        return gnpgs;
    }

    public void setGnpgs(Integer gnpgs) {
        this.gnpgs = gnpgs;
    }

    public Integer getGpid1() {
        return gpid1;
    }

    public void setGpid1(Integer gpid1) {
        this.gpid1 = gpid1;
    }

    public Integer getGpid2() {
        return gpid2;
    }

    public void setGpid2(Integer gpid2) {
        this.gpid2 = gpid2;
    }

    public Integer getGermuid() {
        return germuid;
    }

    public void setGermuid(Integer germuid) {
        this.germuid = germuid;
    }

    public Integer getLgid() {
        return lgid;
    }

    public void setLgid(Integer lgid) {
        this.lgid = lgid;
    }

    public Integer getGlocn() {
        return glocn;
    }

    public void setGlocn(Integer glocn) {
        this.glocn = glocn;
    }

    public Integer getGdate() {
        return gdate;
    }

    public void setGdate(Integer gdate) {
        this.gdate = gdate;
    }

    public Integer getGref() {
        return gref;
    }

    public void setGref(Integer gref) {
        this.gref = gref;
    }

    public Integer getGrplce() {
        return grplce;
    }

    public void setGrplce(Integer grplce) {
        this.grplce = grplce;
    }

    public Integer getMgid() {
        return mgid;
    }

    public void setMgid(Integer mgid) {
        this.mgid = mgid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getGchange() {
        return gchange;
    }

    public void setGchange(Integer gchange) {
        this.gchange = gchange;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gid != null ? gid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Germplsm)) {
            return false;
        }
        Germplsm other = (Germplsm) object;
        if ((this.gid == null && other.gid != null) || (this.gid != null && !this.gid.equals(other.gid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Germplsm[gid=" + gid + "]";
    }

}
