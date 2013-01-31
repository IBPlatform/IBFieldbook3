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
@Table(name = "data_c")
public class DataC extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DataCPK dataCPK;
    @Basic(optional = false)
    @Column(name = "dvalue")
    private String dvalue;

    public DataC() {
    	setDefault();
    }
    
    public DataC(boolean atrNull) {
    	if(! atrNull)
    		setDefault();
    }
    
    public void setDefault(){
    	dataCPK = new DataCPK();
    	dataCPK.setOunitid(0);
    	dataCPK.setVariatid(0);
    	dvalue = "-";
    }

    public DataC(DataCPK dataCPK) {
        this.dataCPK = dataCPK;
    }

    public DataC(DataCPK dataCPK, String dvalue) {
        this.dataCPK = dataCPK;
        this.dvalue = dvalue;
    }

    public DataC(Integer ounitid, Integer variatid) {
        this.dataCPK = new DataCPK(ounitid, variatid);
    }

    public DataCPK getDataCPK() {
        return dataCPK;
    }

    public void setDataCPK(DataCPK dataCPK) {
        this.dataCPK = dataCPK;
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
        hash += (dataCPK != null ? dataCPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataC)) {
            return false;
        }
        DataC other = (DataC) object;
        if ((this.dataCPK == null && other.dataCPK != null) || (this.dataCPK != null && !this.dataCPK.equals(other.dataCPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.DataC[dataCPK=" + dataCPK + "]";
    }

}
