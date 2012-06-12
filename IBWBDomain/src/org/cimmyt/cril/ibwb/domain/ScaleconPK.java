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
public class ScaleconPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "scaleid")
    private Integer scaleid;
    @Basic(optional = false)
    @Column(name = "slevel")
    private double slevel;
    @Basic(optional = false)
    @Column(name = "elevel")
    private double elevel;

    public ScaleconPK() {
    }

    public ScaleconPK(Integer scaleid, double slevel, double elevel) {
        this.scaleid = scaleid;
        this.slevel = slevel;
        this.elevel = elevel;
    }

    public Integer getScaleid() {
        return scaleid;
    }

    public void setScaleid(Integer scaleid) {
        this.scaleid = scaleid;
    }

    public double getSlevel() {
        return slevel;
    }

    public void setSlevel(double slevel) {
        this.slevel = slevel;
    }

    public double getElevel() {
        return elevel;
    }

    public void setElevel(double elevel) {
        this.elevel = elevel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) scaleid;
        hash += (int) slevel;
        hash += (int) elevel;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScaleconPK)) {
            return false;
        }
        ScaleconPK other = (ScaleconPK) object;
        if (this.scaleid != other.scaleid) {
            return false;
        }
        if (this.slevel != other.slevel) {
            return false;
        }
        if (this.elevel != other.elevel) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.ScaleconPK[scaleid=" + scaleid + ", slevel=" + slevel + ", elevel=" + elevel + "]";
    }

}
