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
@Table(name = "progntrs")
public class Progntrs extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProgntrsPK progntrsPK;
    @Basic(optional = false)
    @Column(name = "pid")
    private Integer pid;

    public Progntrs() {
    	setDefault();
    }
    
    public Progntrs(boolean atrNulls) {
    	if(!atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	progntrsPK = new ProgntrsPK();
    	progntrsPK.setGid(0);
    	progntrsPK.setPno(0);
    	pid = 0;
    }

    public Progntrs(ProgntrsPK progntrsPK) {
        this.progntrsPK = progntrsPK;
    }

    public Progntrs(ProgntrsPK progntrsPK, Integer pid) {
        this.progntrsPK = progntrsPK;
        this.pid = pid;
    }

    public Progntrs(Integer gid, Integer pno) {
        this.progntrsPK = new ProgntrsPK(gid, pno);
    }

    public ProgntrsPK getProgntrsPK() {
        return progntrsPK;
    }

    public void setProgntrsPK(ProgntrsPK progntrsPK) {
        this.progntrsPK = progntrsPK;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (progntrsPK != null ? progntrsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Progntrs)) {
            return false;
        }
        Progntrs other = (Progntrs) object;
        if ((this.progntrsPK == null && other.progntrsPK != null) || (this.progntrsPK != null && !this.progntrsPK.equals(other.progntrsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Progntrs[progntrsPK=" + progntrsPK + "]";
    }

}
