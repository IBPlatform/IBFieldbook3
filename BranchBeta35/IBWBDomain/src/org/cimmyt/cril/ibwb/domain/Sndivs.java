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
@Table(name = "sndivs")
public class Sndivs extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "snlid")
    private Integer snlid;
    @Basic(optional = false)
    @Column(name = "snlevel")
    private Integer snlevel;
    @Basic(optional = false)
    @Column(name = "cntryid")
    private Integer cntryid;
    @Basic(optional = false)
    @Column(name = "snliso")
    private String snliso;
    @Basic(optional = false)
    @Column(name = "snlfips")
    private String snlfips;
    @Basic(optional = false)
    @Column(name = "isofull")
    private String isofull;
    @Column(name = "schange")
    private Integer schange;

    public Sndivs() {
    	setDefault();
    }
    
    public Sndivs(boolean atrNulls) {
    	if(!atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	snlid = 0;
    	snlevel = 0;
    	cntryid = 0;
    	snliso = "-";
    	snlfips = "-";
    	isofull = "-";
    	schange = 0;
    }

    public Sndivs(Integer snlid) {
        this.snlid = snlid;
    }

    public Sndivs(Integer snlid, Integer snlevel, Integer cntryid, String snliso, String snlfips, String isofull) {
        this.snlid = snlid;
        this.snlevel = snlevel;
        this.cntryid = cntryid;
        this.snliso = snliso;
        this.snlfips = snlfips;
        this.isofull = isofull;
    }

    public Integer getSnlid() {
        return snlid;
    }

    public void setSnlid(Integer snlid) {
        this.snlid = snlid;
    }

    public Integer getSnlevel() {
        return snlevel;
    }

    public void setSnlevel(Integer snlevel) {
        this.snlevel = snlevel;
    }

    public Integer getCntryid() {
        return cntryid;
    }

    public void setCntryid(Integer cntryid) {
        this.cntryid = cntryid;
    }

    public String getSnliso() {
        return snliso;
    }

    public void setSnliso(String snliso) {
        this.snliso = snliso;
    }

    public String getSnlfips() {
        return snlfips;
    }

    public void setSnlfips(String snlfips) {
        this.snlfips = snlfips;
    }

    public String getIsofull() {
        return isofull;
    }

    public void setIsofull(String isofull) {
        this.isofull = isofull;
    }

    public Integer getSchange() {
        return schange;
    }

    public void setSchange(Integer schange) {
        this.schange = schange;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (snlid != null ? snlid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sndivs)) {
            return false;
        }
        Sndivs other = (Sndivs) object;
        if ((this.snlid == null && other.snlid != null) || (this.snlid != null && !this.snlid.equals(other.snlid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Sndivs[snlid=" + snlid + "]";
    }

}
