/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cimmyt.cril.ibwb.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author jgcamarena
 */
@Embeddable
public class LevelNPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "labelid")
    private Integer labelid;
    @Basic(optional = false)
    @Column(name = "levelno")
    private Integer levelno;

    public LevelNPK() {
    }

    public LevelNPK(Integer labelid, Integer levelno) {
        this.labelid = labelid;
        this.levelno = levelno;
    }

    public Integer getLabelid() {
        return labelid;
    }

    public void setLabelid(Integer labelid) {
        this.labelid = labelid;
    }

    public Integer getLevelno() {
        return levelno;
    }
    
    public Integer getLevelnoAbs() {
        if(levelno > 1){
            return levelno;
        }else{
            return levelno * -1;
        }
    }

    public void setLevelno(Integer levelno) {
        this.levelno = levelno;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash +=  labelid;
        hash +=  levelno;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LevelNPK)) {
            return false;
        }
        LevelNPK other = (LevelNPK) object;
        if (this.labelid != other.labelid) {
            return false;
        }
        if (this.levelno != other.levelno) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.LevelNPK[labelid=" + labelid + ", levelno=" + levelno + "]";
    }

}
