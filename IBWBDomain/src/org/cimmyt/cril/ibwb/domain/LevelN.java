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
@Table(name = "level_n")
public class LevelN extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LevelNPK levelNPK;
    @Basic(optional = false)
    @Column(name = "factorid")
    private Integer factorid;
    @Basic(optional = false)
    @Column(name = "lvalue")
    private Double lvalue;

    public LevelN() {
    	setDefault();
    }
    
    public LevelN(boolean atrNulls) {
    	if(!atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	levelNPK = new LevelNPK();
    	levelNPK.setLabelid(0);
    	levelNPK.setLevelno(0);
    	factorid = 0;
    	lvalue = new Double(0);
    }

    public LevelN(LevelNPK levelNPK) {
        this.levelNPK = levelNPK;
    }

    public LevelN(LevelNPK levelNPK, Integer factorid, Double lvalue) {
        this.levelNPK = levelNPK;
        this.factorid = factorid;
        this.lvalue = lvalue;
    }

    public LevelN(Integer labelid, Integer levelno) {
        this.levelNPK = new LevelNPK(labelid, levelno);
    }

    public LevelNPK getLevelNPK() {
        return levelNPK;
    }

    public void setLevelNPK(LevelNPK levelNPK) {
        this.levelNPK = levelNPK;
    }

    public Integer getFactorid() {
        return factorid;
    }

    public void setFactorid(Integer factorid) {
        this.factorid = factorid;
    }

    public Double getLvalue() {
        return lvalue;
    }

    public void setLvalue(Double lvalue) {
        this.lvalue = lvalue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (levelNPK != null ? levelNPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LevelN)) {
            return false;
        }
        LevelN other = (LevelN) object;
        if ((this.levelNPK == null && other.levelNPK != null) || (this.levelNPK != null && !this.levelNPK.equals(other.levelNPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.LevelN[levelNPK=" + levelNPK + "]";
    }

}
