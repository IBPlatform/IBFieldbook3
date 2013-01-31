/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.domain;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.cimmyt.cril.ibwb.domain.filter.BaseFilter;

/**
 *
 * @author jgcamarena
 */
@Entity
@Table(name = "oindex")
public class Oindex extends BaseFilter implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OindexPK oindexPK;

    public Oindex() {
        setDefault();
    }

    public Oindex(boolean atrNulls) {
        if (!atrNulls) {
            setDefault();
        }
    }

    public void setDefault() {
        oindexPK = new OindexPK();
        oindexPK.setFactorid(0);
        oindexPK.setLevelno(0);
        oindexPK.setOunitid(0);
        oindexPK.setRepresno(0);
    }

    public Oindex(OindexPK oindexPK) {
        this.oindexPK = oindexPK;
    }

    public Oindex(Integer ounitid, Integer factorid, Integer levelno, Integer represno) {
        this.oindexPK = new OindexPK(ounitid, factorid, levelno, represno);
    }

    public OindexPK getOindexPK() {
        return oindexPK;
    }

    public void setOindexPK(OindexPK oindexPK) {
        this.oindexPK = oindexPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oindexPK != null ? oindexPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Oindex)) {
            return false;
        }
        Oindex other = (Oindex) object;
        if ((this.oindexPK == null && other.oindexPK != null) || (this.oindexPK != null && !this.oindexPK.equals(other.oindexPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Oindex[oindexPK=" + oindexPK + "]";
    }
}
