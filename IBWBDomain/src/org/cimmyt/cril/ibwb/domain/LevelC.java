/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cimmyt.cril.ibwb.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.cimmyt.cril.ibwb.domain.filter.BaseFilter;

/**
 *
 * @author jgcamarena
 */
@Entity
@Table(name = "level_c")
public class LevelC extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LevelCPK levelCPK;
    @Basic(optional = false)
    @Column(name = "factorid")
    private Integer factorid;
    @Basic(optional = false)
    @Column(name = "lvalue")
    private String lvalue;

    public LevelC() {
    	setDefault();
    }
    
    public LevelC(boolean atrNulls) {
    	if(!atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	levelCPK = new LevelCPK();
    	levelCPK.setLabelid(0);
    	levelCPK.setLevelno(0);
    	factorid = 0;
    	lvalue = "-";
    }

    public LevelC(LevelCPK levelCPK) {
        this.levelCPK = levelCPK;
    }

    public LevelC(LevelCPK levelCPK, Integer factorid, String lvalue) {
        this.levelCPK = levelCPK;
        this.factorid = factorid;
        this.lvalue = lvalue;
    }

    public LevelC(Integer labelid, Integer levelno) {
        this.levelCPK = new LevelCPK(labelid, levelno);
    }

    public LevelCPK getLevelCPK() {
        return levelCPK;
    }

    public void setLevelCPK(LevelCPK levelCPK) {
        this.levelCPK = levelCPK;
    }

    public Integer getFactorid() {
        return factorid;
    }

    public void setFactorid(Integer factorid) {
        this.factorid = factorid;
    }

    public String getLvalue() {
        return lvalue;
    }

    public void setLvalue(String lvalue) {
        this.lvalue = lvalue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (levelCPK != null ? levelCPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LevelC)) {
            return false;
        }
        LevelC other = (LevelC) object;
        if ((this.levelCPK == null && other.levelCPK != null) || (this.levelCPK != null && !this.levelCPK.equals(other.levelCPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.LevelC[levelCPK=" + levelCPK + "]";
    }

}
