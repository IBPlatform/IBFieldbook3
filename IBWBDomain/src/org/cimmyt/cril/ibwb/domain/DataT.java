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
@Table(name = "data_t")
public class DataT extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DataTPK dataTPK;
    @Basic(optional = false)
    @Column(name = "dvalue")
    private String dvalue;

    public DataT() {
    	setDefault();
    }
    
    public DataT(boolean atrNulls) {
    	if(! atrNulls)
    		setDefault();
    }     
    
    public void setDefault(){
    	dataTPK = new DataTPK();
    	dataTPK.setOunitid(0);
    	dataTPK.setVariatid(0);
    	dvalue = "-";
    }

    public DataT(DataTPK dataTPK) {
        this.dataTPK = dataTPK;
    }

    public DataT(DataTPK dataTPK, String dvalue) {
        this.dataTPK = dataTPK;
        this.dvalue = dvalue;
    }

    public DataT(Integer ounitid, Integer variatid) {
        this.dataTPK = new DataTPK(ounitid, variatid);
    }

    public DataTPK getDataTPK() {
        return dataTPK;
    }

    public void setDataTPK(DataTPK dataTPK) {
        this.dataTPK = dataTPK;
    }

    public String getDvalue() {
        return dvalue;
    }

    public void setDvalue(String dvalue) {
        this.dvalue = dvalue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dataTPK != null ? dataTPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataT)) {
            return false;
        }
        DataT other = (DataT) object;
        if ((this.dataTPK == null && other.dataTPK != null) || (this.dataTPK != null && !this.dataTPK.equals(other.dataTPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.DataT[dataTPK=" + dataTPK + "]";
    }

}
