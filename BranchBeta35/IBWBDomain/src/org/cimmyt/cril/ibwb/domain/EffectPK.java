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
public class EffectPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "represno")
    private Integer represno;
    @Basic(optional = false)
    @Column(name = "factorid")
    private Integer factorid;
    @Basic(optional = false)
    @Column(name = "effectid")
    private Integer effectid;

    public EffectPK() {
    }

    public EffectPK(Integer represno, Integer factorid, Integer effectid) {
        this.represno = represno;
        this.factorid = factorid;
        this.effectid = effectid;
    }

    public Integer getRepresno() {
        return represno;
    }

    public void setRepresno(Integer represno) {
        this.represno = represno;
    }

    public Integer getFactorid() {
        return factorid;
    }

    public void setFactorid(Integer factorid) {
        this.factorid = factorid;
    }

    public Integer getEffectid() {
        return effectid;
    }

    public void setEffectid(Integer effectid) {
        this.effectid = effectid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) represno;
        hash += (int) factorid;
        hash += (int) effectid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EffectPK)) {
            return false;
        }
        EffectPK other = (EffectPK) object;
        if (this.represno != other.represno) {
            return false;
        }
        if (this.factorid != other.factorid) {
            return false;
        }
        if (this.effectid != other.effectid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.EffectPK[represno=" + represno + ", factorid=" + factorid + ", effectid=" + effectid + "]";
    }

}
