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
public class DataTPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ounitid")
    private Integer ounitid;
    @Basic(optional = false)
    @Column(name = "variatid")
    private Integer variatid;

    public DataTPK() {
    }

    public DataTPK(Integer ounitid, Integer variatid) {
        this.ounitid = ounitid;
        this.variatid = variatid;
    }

    public Integer getOunitid() {
        return ounitid;
    }

    public void setOunitid(Integer ounitid) {
        this.ounitid = ounitid;
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
        hash += (int) ounitid;
        hash += (int) variatid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataTPK)) {
            return false;
        }
        DataTPK other = (DataTPK) object;
        if (this.ounitid != other.ounitid) {
            return false;
        }
        if (this.variatid != other.variatid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.DataTPK[ounitid=" + ounitid + ", variatid=" + variatid + "]";
    }

}
