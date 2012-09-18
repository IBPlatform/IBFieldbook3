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
import javax.persistence.Table;
import org.cimmyt.cril.ibwb.domain.filter.BaseFilter;

/**
 *
 * @author jgcamarena
 */
@Entity
@Table(name = "names")
public class Names extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // constants used to manage WHEAT data
    public static final int CIMMYT_WHEAT_BCID = 1027;
    public static final int CIMMYT_WHEAT_SELECTION_HISTORY = 1028;
    /**
     * pedigree or cross name as well
     */
    public static final int CIMMYT_WHEAT_PEDIGREE = 1029;

    @Id
    @Basic(optional = false)
    @Column(name = "nid")
    private Integer nid;
    @Basic(optional = false)
    @Column(name = "gid")
    private Integer gid;
    @Basic(optional = false)
    @Column(name = "ntype")
    private Integer ntype;
    @Basic(optional = false)
    @Column(name = "nstat")
    private Integer nstat;
    @Basic(optional = false)
    @Column(name = "nuid")
    private Integer nuid;
    @Basic(optional = false)
    @Column(name = "nval")
    private String nval;
    @Basic(optional = false)
    @Column(name = "nlocn")
    private Integer nlocn;
    @Basic(optional = false)
    @Column(name = "ndate")
    private Integer ndate;
    @Basic(optional = false)
    @Column(name = "nref")
    private Integer nref;

    public Names() {
    	setDefault();
    }
    
    public Names(boolean atrNulls) {
    	if(!atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	nid = 0;
    	gid = 0;
    	ntype = 0;
    	nstat = 0;
    	nuid = 0;
    	nval = "-";
    	nlocn = 0;
    	ndate = 0;
    	nref = 0;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getNtype() {
        return ntype;
    }

    public void setNtype(Integer ntype) {
        this.ntype = ntype;
    }

    public Integer getNstat() {
        return nstat;
    }

    public void setNstat(Integer nstat) {
        this.nstat = nstat;
    }

    public Integer getNuid() {
        return nuid;
    }

    public void setNuid(Integer nuid) {
        this.nuid = nuid;
    }

    public String getNval() {
        return nval;
    }

    public void setNval(String nval) {
        this.nval = nval;
    }

    public Integer getNlocn() {
        return nlocn;
    }

    public void setNlocn(Integer nlocn) {
        this.nlocn = nlocn;
    }

    public Integer getNdate() {
        return ndate;
    }

    public void setNdate(Integer ndate) {
        this.ndate = ndate;
    }

    public Integer getNref() {
        return nref;
    }

    public void setNref(Integer nref) {
        this.nref = nref;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Names other = (Names) obj;
        if (this.nid != other.nid) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.nid;
        return hash;
    }

  

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Names[namesPK=" + nid + "]";
    }

}
