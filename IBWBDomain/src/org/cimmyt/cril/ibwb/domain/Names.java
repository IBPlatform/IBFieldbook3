/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cimmyt.cril.ibwb.domain;

import java.io.Serializable;
import javax.persistence.*;
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
    
    public static final int CIMMYT_COMMON_NAME = 7;
    
    public static final String SLASH_SEPARATOR = "/";
    
    public static final int DERIVATIVE_NAME = 5;
    public static final int CROSS_NAME = 2;
    public static final int UNNAMED_CROSS = 3;
    
    public static final int NSTAT_PREFERED_NAME = 1 ;
    public static final int NSTAT_PREFERED_ID = 1 ;
    public static final int NSTAT_PREFERED_ABBREVIATIONS = 2 ;
    public static final int NSTAT_CHINESSE_GBK = 3 ;
    public static final int NSTAT_DBCS_NAMES = 4 ;
    public static final int NSTAT_MARKED_DELETED = 9 ;


    

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

    /**
     * Cimmyt pedigree in names where S
     */
    @Transient
    private String cimmytPedigree;    
    
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

    public String getCimmytPedigree() {
        return cimmytPedigree;
    }

    public void setCimmytPedigree(String cimmytPedigree) {
        this.cimmytPedigree = cimmytPedigree;
    }

    

}
