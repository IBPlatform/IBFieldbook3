/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
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
@Table(name = "TMSMEASUREDIN")
public class Measuredin extends BaseFilter implements Serializable {

    public static final String STANTARD_SCALE_YES = "1";
    public static final String STANTARD_SCALE_NO = "0";
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "measuredinid")
    private Integer measuredinid;
    @Basic(optional = false)
    @Column(name = "traitid")
    private Integer traitid;
    @Basic(optional = false)
    @Column(name = "scaleid")
    private Integer scaleid;
    @Basic(optional = false)
    @Column(name = "standardscale")
    private String standardscale;
    @Basic(optional = false)
    @Column(name = "report")
    private String report;
    @Basic(optional = false)
    @Column(name = "formula")
    private String formula;
    @Basic(optional = false)
    @Column(name = "tmethid")
    private Integer tmethid;
    @Transient
    private Traits traits;
    @Transient
    private Scales scales;
    @Transient
    private TmsMethod tmsMethod;
//    @Basic(optional = true)
//    @Column(name = "tmethodid", nullable = true)
//    private Integer tmethodid;

    /**
     * Scale definition, can be ScaleCon or ScaleDis
     */
    @Transient
    private Object tmsScaleDef;
    
    public Measuredin() {
        setDefault();
    }

    public Measuredin(boolean atrNulls) {
        if (!atrNulls) {
            setDefault();
        }
    }

    public void setDefault() {
        traitid = 0;
        scaleid = 0;
        standardscale = "-";
        report = "-";
        formula = "-";
        tmethid = 0;
    }

    public Measuredin(Integer scaleid) {
        this.scaleid = scaleid;
    }

    public Measuredin(
            Integer traitid,
            Integer scaleid,
            String standardscale,
            String report,
            String formula,
            Integer tmethodid) {
        this.traitid = traitid;
        this.scaleid = scaleid;
        this.standardscale = standardscale;
        this.report = report;
        this.formula = formula;
        this.tmethid = tmethodid;
    }

    public Measuredin(
            Integer traitid,
            Integer scaleid,
            Integer tmethodid) {
        this.traitid = traitid;
        this.scaleid = scaleid;
        this.tmethid = tmethodid;
    }

    public Measuredin(
    		Integer traitid,
    		Integer scaleid
    		){
        this.traitid = traitid;
    	this.scaleid = scaleid;
    }
    
    public Integer getTraitid() {
        return traitid;
    }

    public void setTraitid(Integer traitid) {
        this.traitid = traitid;
    }

    public Integer getScaleid() {
        return scaleid;
    }

    public void setScaleid(Integer scaleid) {
        this.scaleid = scaleid;
    }

    public String getStandardscale() {
        return standardscale;
    }

    public void setStandardscale(String standardscale) {
        this.standardscale = standardscale;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

//	public Integer getTmethodid() {
//		return this.tmethodid;
//	}
//	
//	public void setTmethodid(Integer tmethodid) {
//		this.tmethodid = tmethodid;
//	}
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (traitid != null ? traitid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trait)) {
            return false;
        }
        Measuredin other = (Measuredin) object;
        if ((this.traitid == null && other.traitid != null) || (this.traitid != null && !this.traitid.equals(other.traitid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Measuredin[traitid=" + traitid + "]";
    }

    @Transient
    public Traits getTraits() {
        return traits;
    }

    public void setTraits(Traits traits) {
        this.traits = traits;
    }

    @Transient
    public Scales getScales() {
        return scales;
    }

    public void setScales(Scales scales) {
        this.scales = scales;
    }

    /**
     * @return the measuredinid
     */
    public Integer getMeasuredinid() {
        return measuredinid;
    }

    /**
     * @param measuredinid the measuredinid to set
     */
    public void setMeasuredinid(Integer measuredinid) {
        this.measuredinid = measuredinid;
    }

    /**
     * @return the tmsMethodTmethid
     */
    public Integer getTmethid() {
        return tmethid;
    }

    /**
     * @param tmsMethodTmethid the tmsMethodTmethid to set
     */
    public void setTmethid(Integer tmethid) {
        this.tmethid = tmethid;
    }

    /**
     * @return the tmsMethod
     */
    public TmsMethod getTmsMethod() {
        return tmsMethod;
    }

    /**
     * @param tmsMethod the tmsMethod to set
     */
    public void setTmsMethod(TmsMethod tmsMethod) {
        this.tmsMethod = tmsMethod;
    }
    
    @Transient
    public Boolean isStandardScale() {
        return standardscale.equals(STANTARD_SCALE_YES);
    }

    public Object getTmsScaleDef() {
        return tmsScaleDef;
    }

    public void setTmsScaleDef(Object tmsScaleDef) {
        this.tmsScaleDef = tmsScaleDef;
    }
    
    
}