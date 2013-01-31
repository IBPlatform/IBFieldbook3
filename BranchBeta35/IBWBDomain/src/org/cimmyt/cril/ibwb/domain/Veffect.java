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
@Table(name = "veffect")
public class Veffect extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VeffectPK veffectPK;

    public Veffect() {
    	setDefault();
    }
    
    public Veffect(boolean atrNulls) {
    	if(!atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	veffectPK = new VeffectPK();
    	veffectPK.setRepresno(0);
    	veffectPK.setVariatid(0);
    }

    public Veffect(VeffectPK veffectPK) {
        this.veffectPK = veffectPK;
    }

    public Veffect(Integer represno, Integer variatid) {
        this.veffectPK = new VeffectPK(represno, variatid);
    }

    public VeffectPK getVeffectPK() {
        return veffectPK;
    }

    public void setVeffectPK(VeffectPK veffectPK) {
        this.veffectPK = veffectPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (veffectPK != null ? veffectPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Veffect)) {
            return false;
        }
        Veffect other = (Veffect) object;
        if ((this.veffectPK == null && other.veffectPK != null) || (this.veffectPK != null && !this.veffectPK.equals(other.veffectPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Veffect[veffectPK=" + veffectPK + "]";
    }

}
