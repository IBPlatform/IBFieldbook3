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
@Table(name = "cntry")
public class Cntry extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cntryid")
    private Integer cntryid;
    @Column(name = "isonum")
    private Integer isonum;
    @Basic(optional = false)
    @Column(name = "isotwo")
    private String isotwo;
    @Basic(optional = false)
    @Column(name = "isothree")
    private String isothree;
    @Basic(optional = false)
    @Column(name = "faothree")
    private String faothree;
    @Basic(optional = false)
    @Column(name = "fips")
    private String fips;
    @Basic(optional = false)
    @Column(name = "wb")
    private String wb;
    @Basic(optional = false)
    @Column(name = "isofull")
    private String isofull;
    @Basic(optional = false)
    @Column(name = "isoabbr")
    private String isoabbr;
    @Basic(optional = false)
    @Column(name = "cont")
    private String cont;
    @Column(name = "scntry")
    private Integer scntry;
    @Column(name = "ecntry")
    private Integer ecntry;
    @Column(name = "cchange")
    private Integer cchange;

    public Cntry() {
    	setDefault();
    }
    
    public Cntry(boolean atrNull) {
    	if(! atrNull)
    		setDefault();
    }

    public void setDefault(){
    	cntryid = 0;
    	isonum = 0;
    	isotwo = "-";
    	isothree = "-";
    	faothree = "-";
    	fips = "-";
    	wb = "-";
    	isofull = "-";
    	isoabbr = "-";
    	cont = "-";
    	scntry = 0;
    	ecntry = 0;
    	cchange = 0;
    }

    public Integer getCntryid() {
        return cntryid;
    }

    public void setCntryid(Integer cntryid) {
        this.cntryid = cntryid;
    }

    public Integer getIsonum() {
        return isonum;
    }

    public void setIsonum(Integer isonum) {
        this.isonum = isonum;
    }

    public String getIsotwo() {
        return isotwo;
    }

    public void setIsotwo(String isotwo) {
        this.isotwo = isotwo;
    }

    public String getIsothree() {
        return isothree;
    }

    public void setIsothree(String isothree) {
        this.isothree = isothree;
    }

    public String getFaothree() {
        return faothree;
    }

    public void setFaothree(String faothree) {
        this.faothree = faothree;
    }

    public String getFips() {
        return fips;
    }

    public void setFips(String fips) {
        this.fips = fips;
    }

    public String getWb() {
        return wb;
    }

    public void setWb(String wb) {
        this.wb = wb;
    }

    public String getIsofull() {
        return isofull;
    }

    public void setIsofull(String isofull) {
        this.isofull = isofull;
    }

    public String getIsoabbr() {
        return isoabbr;
    }

    public void setIsoabbr(String isoabbr) {
        this.isoabbr = isoabbr;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public Integer getScntry() {
        return scntry;
    }

    public void setScntry(Integer scntry) {
        this.scntry = scntry;
    }

    public Integer getEcntry() {
        return ecntry;
    }

    public void setEcntry(Integer ecntry) {
        this.ecntry = ecntry;
    }

    public Integer getCchange() {
        return cchange;
    }

    public void setCchange(Integer cchange) {
        this.cchange = cchange;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cntry other = (Cntry) obj;
        if (this.cntryid != other.cntryid) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.cntryid;
        return hash;
    }

 
    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Cntry[cntryPK=" + cntryid + "]";
    }

}
