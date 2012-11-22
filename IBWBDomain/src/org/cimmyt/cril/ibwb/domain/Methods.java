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
@Table(name = "methods")
public class Methods extends BaseFilter implements Serializable {
    public static final String MTYPE_GENERATIVE = "GEN";
    public static final String MTYPE_DERIVATIVE = "DER";
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "mid")
    private Integer mid;
    @Basic(optional = false)
    @Column(name = "mtype")
    private String mtype;
    @Basic(optional = false)
    @Column(name = "mgrp")
    private String mgrp;
    @Basic(optional = false)
    @Column(name = "mcode")
    private String mcode;
    @Basic(optional = false)
    @Column(name = "mname")
    private String mname;
    @Basic(optional = false)
    @Column(name = "mdesc")
    private String mdesc;
    @Basic(optional = false)
    @Column(name = "mref")
    private Integer mref;
    @Basic(optional = false)
    @Column(name = "mprgn")
    private Integer mprgn;
    @Basic(optional = false)
    @Column(name = "mfprg")
    private Integer mfprg;
    @Basic(optional = false)
    @Column(name = "mattr")
    private Integer mattr;
    @Basic(optional = false)
    @Column(name = "geneq")
    private Integer geneq;
    @Basic(optional = false)
    @Column(name = "muid")
    private Integer muid;
    @Basic(optional = false)
    @Column(name = "lmid")
    private Integer lmid;
    @Basic(optional = false)
    @Column(name = "mdate")
    private Integer mdate;

    public Methods() {
    	setDefault();
    }
    
    public Methods(boolean atrNulls) {
    	if(!atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	mid = 0;
    	mtype = "-";
    	mgrp = "-";
    	mcode = "-";
    	mname = "-";
    	mdesc = "-";
    	mref = 0;
    	mprgn = 0;
    	mfprg = 0;
    	mattr = 0;
    	geneq = 0;
    	muid = 0;
    	lmid = 0;
    	mdate = 0;
    }

    public Methods(Integer mid) {
        this.mid = mid;
    }

    public Methods(Integer mid, String mtype, String mgrp, String mcode, String mname, String mdesc, Integer mref, Integer mprgn, Integer mfprg, Integer mattr, Integer geneq, Integer muid, Integer lmid, Integer mdate) {
        this.mid = mid;
        this.mtype = mtype;
        this.mgrp = mgrp;
        this.mcode = mcode;
        this.mname = mname;
        this.mdesc = mdesc;
        this.mref = mref;
        this.mprgn = mprgn;
        this.mfprg = mfprg;
        this.mattr = mattr;
        this.geneq = geneq;
        this.muid = muid;
        this.lmid = lmid;
        this.mdate = mdate;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getMtype() {
        return mtype;
    }

    public void setMtype(String mtype) {
        this.mtype = mtype;
    }

    public String getMgrp() {
        return mgrp;
    }

    public void setMgrp(String mgrp) {
        this.mgrp = mgrp;
    }

    public String getMcode() {
        return mcode;
    }

    public void setMcode(String mcode) {
        this.mcode = mcode;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMdesc() {
        return mdesc;
    }

    public void setMdesc(String mdesc) {
        this.mdesc = mdesc;
    }

    public Integer getMref() {
        return mref;
    }

    public void setMref(Integer mref) {
        this.mref = mref;
    }

    public Integer getMprgn() {
        return mprgn;
    }

    public void setMprgn(Integer mprgn) {
        this.mprgn = mprgn;
    }

    public Integer getMfprg() {
        return mfprg;
    }

    public void setMfprg(Integer mfprg) {
        this.mfprg = mfprg;
    }

    public Integer getMattr() {
        return mattr;
    }

    public void setMattr(Integer mattr) {
        this.mattr = mattr;
    }

    public Integer getGeneq() {
        return geneq;
    }

    public void setGeneq(Integer geneq) {
        this.geneq = geneq;
    }

    public Integer getMuid() {
        return muid;
    }

    public void setMuid(Integer muid) {
        this.muid = muid;
    }

    public Integer getLmid() {
        return lmid;
    }

    public void setLmid(Integer lmid) {
        this.lmid = lmid;
    }

    public Integer getMdate() {
        return mdate;
    }

    public void setMdate(Integer mdate) {
        this.mdate = mdate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mid != null ? mid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Methods)) {
            return false;
        }
        Methods other = (Methods) object;
        if ((this.mid == null && other.mid != null) || (this.mid != null && !this.mid.equals(other.mid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Methods[mid=" + mid + "]";
    }

}
