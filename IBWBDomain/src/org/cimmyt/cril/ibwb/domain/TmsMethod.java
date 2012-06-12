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
import javax.persistence.Table;
import org.cimmyt.cril.ibwb.domain.filter.BaseFilter;

/**
 *
 * @author jgcamarena
 */
@Entity
@Table(name = "TMSMETHOD")
public class TmsMethod extends BaseFilter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "tmethid")
    private Integer tmethid;
    @Basic(optional = false)
    @Column(name = "tmname")
    private String tmname;
    @Basic(optional = false)
    @Column(name = "tmabbr")
    private String tmabbr;
    @Basic(optional = false)
    @Column(name = "tmdesc")
    private String tmdesc;

    public TmsMethod() {
        setDefault();
    }

    public TmsMethod(boolean atrNulls) {
        if (!atrNulls) {
            setDefault();
        }
    }

    public void setDefault() {
        tmethid = 0;
        tmname = "-";
        tmabbr = "-";
        tmdesc = "-";
    }

    public TmsMethod(Integer tmethid) {
        this.tmethid = tmethid;
    }

    public TmsMethod(Integer tmethid, String tmname, String tmabbr, String tmdesc) {
        this.tmethid = tmethid;
        this.tmname = tmname;
        this.tmabbr = tmabbr;
        this.tmdesc = tmdesc;
    }

    public Integer getTmethid() {
        return tmethid;
    }

    public void setTmethid(Integer tmethid) {
        this.tmethid = tmethid;
    }

    public String getTmname() {
        return tmname;
    }

    public void setTmname(String tmname) {
        this.tmname = tmname;
    }

    public String getTmabbr() {
        return tmabbr;
    }

    public void setTmabbr(String tmabbr) {
        this.tmabbr = tmabbr;
    }

    public String getTmdesc() {
        return tmdesc;
    }

    public void setTmdesc(String tmdesc) {
        this.tmdesc = tmdesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tmethid != null ? tmethid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsMethod)) {
            return false;
        }
        TmsMethod other = (TmsMethod) object;
        if ((this.tmethid == null && other.tmethid != null) || (this.tmethid != null && !this.tmethid.equals(other.tmethid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "org.cimmyt.cril.ibworkbench.services.beans.TmsMethod[tmethid=" + tmethid + "]";
        return tmname;
    }
}
