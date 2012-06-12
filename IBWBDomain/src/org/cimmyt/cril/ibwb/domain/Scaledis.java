/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cimmyt.cril.ibwb.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
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
@Table(name = "scaledis")
public class Scaledis extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ScaledisPK scaledisPK;
    @Basic(optional = false)
    @Column(name = "valdesc")
    private String valdesc;
    
    public Scaledis() {
    	setDefault();
    }
    
    public Scaledis(boolean atrNulls) {
    	if(!atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	scaledisPK = new ScaledisPK();
    	scaledisPK.setScaleid(0);
    	scaledisPK.setValue("-");
    	valdesc = "-";
    }
    
    public Scaledis(ScaledisPK scaledisPK){
        this.scaledisPK = scaledisPK;
    }
    
    public Scaledis(ScaledisPK scaledisPK, String valdesc) {
        this.scaledisPK = scaledisPK;
        this.valdesc = valdesc;
    }

    public Scaledis(Integer scaleid, String value) {
        this.scaledisPK = new ScaledisPK(scaleid, value);
    }

    public ScaledisPK getScaledisPK() {
        return scaledisPK;
    }

    public void setScaledisPK(ScaledisPK scaledisPK) {
        this.scaledisPK = scaledisPK;
    }

    public String getValdesc() {
        return valdesc;
    }

    public void setValdesc(String valdesc) {
        this.valdesc = valdesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scaledisPK != null ? scaledisPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Scaledis)) {
            return false;
        }
        Scaledis other = (Scaledis) object;
        if ((this.scaledisPK == null && other.scaledisPK != null) || (this.scaledisPK != null && !this.scaledisPK.equals(other.scaledisPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Scaledis[scaledisPK=" + scaledisPK + "]";
    }

}
