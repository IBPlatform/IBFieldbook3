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
@Table(name = "atributs")
public class Atributs extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "aid")
    private Integer aid;
    @Basic(optional = false)
    @Column(name = "gid")
    private Integer gid;
    @Basic(optional = false)
    @Column(name = "atype")
    private Integer atype;
    @Basic(optional = false)
    @Column(name = "auid")
    private Integer auid;
    @Basic(optional = false)
    @Column(name = "aval")
    private String aval;
    @Column(name = "alocn")
    private Integer alocn;
    @Column(name = "aref")
    private Integer aref;
    @Column(name = "adate")
    private Integer adate;

    public Atributs() {
    	setDefault();
    }

    public Atributs(boolean atrNulls) {
    	if(! atrNulls){
    		setDefault();
    	}
    }
    
    public void setDefault(){
    	aid = 0;
    	gid = 0;
    	atype = 0;
    	auid = 0;
    	aval = "-";
    	alocn = 0;
    	aref = 0;
    	adate = 0;
    }
    
    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getAtype() {
        return atype;
    }

    public void setAtype(Integer atype) {
        this.atype = atype;
    }

    public Integer getAuid() {
        return auid;
    }

    public void setAuid(Integer auid) {
        this.auid = auid;
    }

    public String getAval() {
        return aval;
    }

    public void setAval(String aval) {
        this.aval = aval;
    }

    public Integer getAlocn() {
        return alocn;
    }

    public void setAlocn(Integer alocn) {
        this.alocn = alocn;
    }

    public Integer getAref() {
        return aref;
    }

    public void setAref(Integer aref) {
        this.aref = aref;
    }

    public Integer getAdate() {
        return adate;
    }

    public void setAdate(Integer adate) {
        this.adate = adate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Atributs other = (Atributs) obj;
        if (this.aid != other.aid) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.aid;
        return hash;
    }

  

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Atributs[atributsPK=" + aid + "]";
    }

}
