/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cimmyt.cril.ibwb.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.cimmyt.cril.ibwb.domain.filter.BaseFilter;

/**
 *
 * @author jgcamarena
 */
@Entity
@Table(name = "georef")
public class Georef extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "locid")
    private Integer locid;
    @Column(name = "llpn")
    private Integer llpn;
    @Column(name = "lat")
    private Double lat;
    @Column(name = "lon")
    private Double lon;
    @Column(name = "alt")
    private Double alt;

    public Georef() {
    	setDefault();
    }
    
    public Georef(boolean atrNulls) {
    	if(!atrNulls)
    		setDefault();
    }

    public void setDefault(){
    	locid = 0;
    	llpn = 0;
    	lat = new Double(0);
    	lon = new Double(0);
    	alt = new Double(0);
    }
    
    public Georef(Integer locid) {
        this.locid = locid;
    }

    public Integer getLocid() {
        return locid;
    }

    public void setLocid(Integer locid) {
        this.locid = locid;
    }

    public Integer getLlpn() {
        return llpn;
    }

    public void setLlpn(Integer llpn) {
        this.llpn = llpn;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getAlt() {
        return alt;
    }

    public void setAlt(Double alt) {
        this.alt = alt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locid != null ? locid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Georef)) {
            return false;
        }
        Georef other = (Georef) object;
        if ((this.locid == null && other.locid != null) || (this.locid != null && !this.locid.equals(other.locid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Georef[locid=" + locid + "]";
    }

}
