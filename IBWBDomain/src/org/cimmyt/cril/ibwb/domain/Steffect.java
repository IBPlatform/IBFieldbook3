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
@Table(name = "steffect")
public class Steffect extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "effectid")
    private Integer effectid;
    @Basic(optional = false)
    @Column(name = "studyid")
    private Integer studyid;
    @Basic(optional = false)
    @Column(name = "effectname")
    private String effectname;

    public Steffect() {
    	setDefault();
    }

    public Steffect(boolean atrNulls) {
    	if(!atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	effectid = 0;
    	studyid = 0;
    	effectname = "-";
    }

    public Integer getEffectid() {
        return effectid;
    }

    public void setEffectid(Integer effectid) {
        this.effectid = effectid;
    }

    public Integer getStudyid() {
        return studyid;
    }

    public void setStudyid(Integer studyid) {
        this.studyid = studyid;
    }

    public String getEffectname() {
        return effectname;
    }

    public void setEffectname(String effectname) {
        this.effectname = effectname;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Steffect other = (Steffect) obj;
        if (this.effectid != other.effectid) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.effectid;
        return hash;
    }

    

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Steffect[steffectPK=" + effectid + "]";
    }

}
