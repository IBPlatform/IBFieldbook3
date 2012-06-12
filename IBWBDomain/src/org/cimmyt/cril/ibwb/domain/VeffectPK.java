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
public class VeffectPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "represno")
    private Integer represno;
    @Basic(optional = false)
    @Column(name = "variatid")
    private Integer variatid;

    public VeffectPK() {
    }

    public VeffectPK(Integer represno, Integer variatid) {
        this.represno = represno;
        this.variatid = variatid;
    }

    public Integer getRepresno() {
        return represno;
    }

    public void setRepresno(Integer represno) {
        this.represno = represno;
    }

    public Integer getVariatid() {
        return variatid;
    }

    public void setVariatid(Integer variatid) {
        this.variatid = variatid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) represno;
        hash += (int) variatid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VeffectPK)) {
            return false;
        }
        VeffectPK other = (VeffectPK) object;
        if (this.represno != other.represno) {
            return false;
        }
        if (this.variatid != other.variatid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.VeffectPK[represno=" + represno + ", variatid=" + variatid + "]";
    }

}
