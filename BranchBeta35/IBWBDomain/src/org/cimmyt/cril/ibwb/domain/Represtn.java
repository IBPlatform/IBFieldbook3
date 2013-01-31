/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cimmyt.cril.ibwb.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.cimmyt.cril.ibwb.domain.filter.BaseFilter;

/**
 *
 * @author jgcamarena
 */
@Entity
@Table(name = "represtn")
public class Represtn extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "represno")
    private Integer represno;
    @Basic(optional = false)
    @Column(name = "effectid")
    private Integer effectid;
    @Basic(optional = false)
    @Column(name = "represname")
    private String represname;
    @Transient
    private List<Factor> factors;
    
    public Represtn() {
    	setDefault();
    }
    
    public Represtn(boolean atrNulls) {
    	if(!atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	represno = 0;
    	effectid = 0;
    	represname = "-";
    }

    public Represtn(Integer represno) {
        this.represno = represno;
    }

    public Represtn(Integer represno, Integer effectid, String represname) {
        this.represno = represno;
        this.effectid = effectid;
        this.represname = represname;
    }

    public Integer getRepresno() {
        return represno;
    }

    public void setRepresno(Integer represno) {
        this.represno = represno;
    }

    public Integer getEffectid() {
        return effectid;
    }

    public void setEffectid(Integer effectid) {
        this.effectid = effectid;
    }

    public String getRepresname() {
        return represname;
    }

    public void setRepresname(String represname) {
        this.represname = represname;
    }

    public List<Factor> getFactors() {
        return factors;
    }

    public void setFactors(List<Factor> factors) {
        this.factors = factors;
    }
    
    @Transient
    public int getRepeticiones(String represName){
        if(getRepresname().equals(represName)){
            if(! factors.isEmpty() && factors.size() == 2){
                return factors.get(1).getSizeLevels();
            }else{
                return 0;
            }
        }else{
            return 0;
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (represno != null ? represno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Represtn)) {
            return false;
        }
        Represtn other = (Represtn) object;
        if ((this.represno == null && other.represno != null) || (this.represno != null && !this.represno.equals(other.represno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Represtn[represno=" + represno + "]";
    }

}
