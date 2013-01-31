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
public class ProgntrsPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "gid")
    private Integer gid;
    @Basic(optional = false)
    @Column(name = "pno")
    private Integer pno;

    public ProgntrsPK() {
    }

    public ProgntrsPK(Integer gid, Integer pno) {
        this.gid = gid;
        this.pno = pno;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getPno() {
        return pno;
    }

    public void setPno(Integer pno) {
        this.pno = pno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) gid;
        hash += (int) pno;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgntrsPK)) {
            return false;
        }
        ProgntrsPK other = (ProgntrsPK) object;
        if (this.gid != other.gid) {
            return false;
        }
        if (this.pno != other.pno) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.ProgntrsPK[gid=" + gid + ", pno=" + pno + "]";
    }

}
