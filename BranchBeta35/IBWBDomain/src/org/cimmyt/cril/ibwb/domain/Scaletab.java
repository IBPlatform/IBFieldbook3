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
@Table(name = "scaletab")
public class Scaletab extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "scaleid")
    private Integer scaleid;
    @Basic(optional = false)
    @Column(name = "ssql")
    private String ssql;
    @Basic(optional = false)
    @Column(name = "smodule")
    private String smodule;

    public Scaletab() {
    	setDefault();
    }

    public Scaletab(boolean atrNulls) {
    	if(!atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	scaleid = 0;
    	ssql = "-";
    	smodule = "-";
    }

    public Integer getScaleid() {
        return scaleid;
    }

    public void setScaleid(Integer scaleid) {
        this.scaleid = scaleid;
    }

    public String getSsql() {
        return ssql;
    }

    public void setSsql(String ssql) {
        this.ssql = ssql;
    }

    public String getSmodule() {
        return smodule;
    }

    public void setSmodule(String smodule) {
        this.smodule = smodule;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Scaletab other = (Scaletab) obj;
        if (this.scaleid != other.scaleid) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.scaleid;
        return hash;
    }

  
    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Scaletab[scaletabPK=" + scaleid + "]";
    }

}
