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
public class ScaledisPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "scaleid")
    private Integer scaleid;
    @Basic(optional = false)
    @Column(name = "value")
    private String value;

    public ScaledisPK() {
    }

    public ScaledisPK(Integer scaleid, String value) {
        this.scaleid = scaleid;
        this.value = value;
    }

    public Integer getScaleid() {
        return scaleid;
    }

    public void setScaleid(Integer scaleid) {
        this.scaleid = scaleid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) scaleid;
        hash += (value != null ? value.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScaledisPK)) {
            return false;
        }
        ScaledisPK other = (ScaledisPK) object;
        if (this.scaleid != other.scaleid) {
            return false;
        }
        if ((this.value == null && other.value != null) || (this.value != null && !this.value.equals(other.value))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.ScaledisPK[scaleid=" + scaleid + ", value=" + value + "]";
    }

}
