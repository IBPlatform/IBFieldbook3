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
public class OindexPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ounitid")
    private Integer ounitid;
    @Basic(optional = false)
    @Column(name = "factorid")
    private Integer factorid;
    @Basic(optional = false)
    @Column(name = "levelno")
    private Integer levelno;
    @Basic(optional = false)
    @Column(name = "represno")
    private Integer represno;

    public OindexPK() {
    }

    public OindexPK(Integer ounitid, Integer factorid, Integer levelno, Integer represno) {
        this.ounitid = ounitid;
        this.factorid = factorid;
        this.levelno = levelno;
        this.represno = represno;
    }

    public Integer getOunitid() {
        return ounitid;
    }

    public void setOunitid(Integer ounitid) {
        this.ounitid = ounitid;
    }

    public Integer getFactorid() {
        return factorid;
    }

    public void setFactorid(Integer factorid) {
        this.factorid = factorid;
    }

    public Integer getLevelno() {
        return levelno;
    }

    public void setLevelno(Integer levelno) {
        this.levelno = levelno;
    }

    public Integer getRepresno() {
        return represno;
    }

    public void setRepresno(Integer represno) {
        this.represno = represno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) ounitid;
        hash += (int) factorid;
        hash += (int) levelno;
        hash += (int) represno;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OindexPK)) {
            return false;
        }
        OindexPK other = (OindexPK) object;
        if (this.ounitid != other.ounitid) {
            return false;
        }
        if (this.factorid != other.factorid) {
            return false;
        }
        if (this.levelno != other.levelno) {
            return false;
        }
        if (this.represno != other.represno) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.OindexPK[ounitid=" + ounitid + ", factorid=" + factorid + ", levelno=" + levelno + ", represno=" + represno + "]";
    }

}
