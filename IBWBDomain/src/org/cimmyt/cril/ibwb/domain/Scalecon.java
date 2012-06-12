/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cimmyt.cril.ibwb.domain;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.cimmyt.cril.ibwb.domain.filter.BaseFilter;

/**
 *
 * @author jgcamarena
 */
@Entity
@Table(name = "scalecon")
public class Scalecon extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ScaleconPK scaleconPK;

    public Scalecon() {
    	setDefault();
    }
    
    public Scalecon(boolean atrNulls) {
    	if(!atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	scaleconPK = new ScaleconPK();
    	scaleconPK.setElevel(new Double(0));
    	scaleconPK.setScaleid(0);
    	scaleconPK.setElevel(new Double(0));
    }

    public Scalecon(ScaleconPK scaleconPK) {
        this.scaleconPK = scaleconPK;
    }

    public Scalecon(Integer scaleid, double slevel, double elevel) {
        this.scaleconPK = new ScaleconPK(scaleid, slevel, elevel);
    }

    public ScaleconPK getScaleconPK() {
        return scaleconPK;
    }

    public void setScaleconPK(ScaleconPK scaleconPK) {
        this.scaleconPK = scaleconPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scaleconPK != null ? scaleconPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Scalecon)) {
            return false;
        }
        Scalecon other = (Scalecon) object;
        if ((this.scaleconPK == null && other.scaleconPK != null) || (this.scaleconPK != null && !this.scaleconPK.equals(other.scaleconPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Scalecon[scaleconPK=" + scaleconPK + "]";
    }

}
