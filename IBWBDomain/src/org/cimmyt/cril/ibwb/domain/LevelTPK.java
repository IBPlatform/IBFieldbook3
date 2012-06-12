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
public class LevelTPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "labelid")
    private Integer labelid;
    @Basic(optional = false)
    @Column(name = "levelno")
    private Integer levelno;

    public LevelTPK() {
    }

    public LevelTPK(Integer labelid, Integer levelno) {
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

    public void setLevelno(Integer levelno) {
        this.levelno = levelno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) labelid;
        hash += (int) levelno;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LevelTPK)) {
            return false;
        }
        LevelTPK other = (LevelTPK) object;
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
        return "org.cimmyt.cril.ibworkbench.services.beans.LevelTPK[labelid=" + labelid + ", levelno=" + levelno + "]";
    }

}
