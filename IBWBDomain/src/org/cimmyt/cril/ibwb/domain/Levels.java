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
@Table(name = "levels")
public class Levels extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "levelno")
    private Integer levelno;
    @Basic(optional = false)
    @Column(name = "factorid")
    private Integer factorid;

    public Levels() {
    	setDefault();
    }
    
    public Levels(boolean atrNulls) {
    	if(!atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	levelno = 0;
    	factorid = 0;
    }

    public Levels(Integer levelno) {
        this.levelno = levelno;
    }

    public Levels(Integer levelno, Integer factorid) {
        this.levelno = levelno;
        this.factorid = factorid;
    }

    public Integer getLevelno() {
        return levelno;
    }

    public void setLevelno(Integer levelno) {
        this.levelno = levelno;
    }

    public Integer getFactorid() {
        return factorid;
    }

    public void setFactorid(Integer factorid) {
        this.factorid = factorid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (levelno != null ? levelno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Levels)) {
            return false;
        }
        Levels other = (Levels) object;
        if ((this.levelno == null && other.levelno != null) || (this.levelno != null && !this.levelno.equals(other.levelno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Levels[levelno=" + levelno + "]";
    }

}
