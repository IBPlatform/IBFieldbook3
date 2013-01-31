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
@Table(name = "effect")
public class Effect extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EffectPK effectPK;

    public Effect() {
    	setDefault();
    }
    
    public Effect(boolean atrNulls) {
    	if(!atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	effectPK = new EffectPK();
    	effectPK.setEffectid(0);
    	effectPK.setFactorid(0);
    	effectPK.setRepresno(0);
    }

    public Effect(EffectPK effectPK) {
        this.effectPK = effectPK;
    }

    public Effect(Integer represno, Integer factorid, Integer effectid) {
        this.effectPK = new EffectPK(represno, factorid, effectid);
    }

    public EffectPK getEffectPK() {
        return effectPK;
    }

    public void setEffectPK(EffectPK effectPK) {
        this.effectPK = effectPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (effectPK != null ? effectPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Effect)) {
            return false;
        }
        Effect other = (Effect) object;
        if ((this.effectPK == null && other.effectPK != null) || (this.effectPK != null && !this.effectPK.equals(other.effectPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Effect[effectPK=" + effectPK + "]";
    }

}
