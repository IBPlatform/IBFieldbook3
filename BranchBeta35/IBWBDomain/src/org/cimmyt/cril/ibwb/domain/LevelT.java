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
@Table(name = "level_t")
public class LevelT extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LevelTPK levelTPK;
    @Basic(optional = false)
    @Column(name = "factorid")
    private Integer factorid;
    @Basic(optional = false)
    @Column(name = "lvalue")
    private String lvalue;

    public LevelT() {
    	setDefault();
    }
    
    public LevelT(boolean atrNulls) {
    	if(!atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	levelTPK = new LevelTPK();
    	levelTPK.setLabelid(0);
    	levelTPK.setLevelno(0);
    	factorid = 0;
    	lvalue = "-";
    }

    public LevelT(LevelTPK levelTPK) {
        this.levelTPK = levelTPK;
    }

    public LevelT(LevelTPK levelTPK, Integer factorid, String lvalue) {
        this.levelTPK = levelTPK;
        this.factorid = factorid;
        this.lvalue = lvalue;
    }

    public LevelT(Integer labelid, Integer levelno) {
        this.levelTPK = new LevelTPK(labelid, levelno);
    }

    public LevelTPK getLevelTPK() {
        return levelTPK;
    }

    public void setLevelTPK(LevelTPK levelTPK) {
        this.levelTPK = levelTPK;
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
        hash += (levelTPK != null ? levelTPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LevelT)) {
            return false;
        }
        LevelT other = (LevelT) object;
        if ((this.levelTPK == null && other.levelTPK != null) || (this.levelTPK != null && !this.levelTPK.equals(other.levelTPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.LevelT[levelTPK=" + levelTPK + "]";
    }

}
