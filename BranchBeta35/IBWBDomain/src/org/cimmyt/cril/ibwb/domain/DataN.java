/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cimmyt.cril.ibwb.domain;

import java.io.Serializable;
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
@Table(name = "data_n")
public class DataN extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DataNPK dataNPK;
    @Column(name = "dvalue")
    private Double dvalue;

    public DataN() {
    	setDefault();
    }
    
    public DataN(boolean atrNulls) {
    	if(!atrNulls)
    		setDefault();
    }

    public void setDefault(){
    	dataNPK = new DataNPK();
    	dataNPK.setOunitid(0);
    	dataNPK.setVariatid(0);
    	dvalue = new Double(0);
    }

    public DataN(DataNPK dataNPK) {
        this.dataNPK = dataNPK;
    }

    public DataN(Integer ounitid, Integer variatid) {
        this.dataNPK = new DataNPK(ounitid, variatid);
    }

    public DataNPK getDataNPK() {
        return dataNPK;
    }

    public void setDataNPK(DataNPK dataNPK) {
        this.dataNPK = dataNPK;
    }

    public Double getDvalue() {
        return dvalue;
    }

    public void setDvalue(Double dvalue) {
        this.dvalue = dvalue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dataNPK != null ? dataNPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataN)) {
            return false;
        }
        DataN other = (DataN) object;
        if ((this.dataNPK == null && other.dataNPK != null) || (this.dataNPK != null && !this.dataNPK.equals(other.dataNPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.DataN[dataNPK=" + dataNPK + "]";
    }

}
