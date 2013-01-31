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
import javax.persistence.Transient;
import org.cimmyt.cril.ibwb.domain.filter.BaseFilter;

/**
 *
 * @author jgcamarena
 */
@Entity
@Table(name = "TMSSCALES")
public class Scales extends BaseFilter implements Serializable {

    public static final String SCALE_TYPE_CONTINOUS = "C";
    public static final String SCALE_TYPE_DISCRETE = "D";
    public static final String DATA_TYPE_NUMERIC = "N";
    public static final String DATA_TYPE_CHARACTER = "C";
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "scaleid")
    private Integer scaleid;
    @Basic(optional = false)
    @Column(name = "scname")
    private String scname;
    @Basic(optional = false)
    @Column(name = "sctype")
    private String sctype;
    @Basic(optional = false)
    @Column(name = "Ontology")
    private String ontology;
    @Basic(optional = false)
    @Column(name = "dtype")
    private String dtype;

    public Scales() {
        setDefault();
    }

    public Scales(boolean atrNulls) {
        if (!atrNulls) {
            setDefault();
        }
    }

    public void setDefault() {
        scaleid = 0;
        scname = "-";
        sctype = "-";
        ontology = "-";
        dtype = "-";
    }

    public Scales(Integer scaleid) {
        this.scaleid = scaleid;
    }

    public Scales(String scname) {
        this.scname = scname;
    }

    public Scales(Integer scaleid, String scname, String sctype, String ontology, String dtype) {
        this.scaleid = scaleid;
        this.scname = scname;
        this.sctype = sctype;
        this.ontology = ontology;
        this.dtype = dtype;
    }

    public Integer getScaleid() {
        return scaleid;
    }

    public void setScaleid(Integer scaleid) {
        this.scaleid = scaleid;
    }

    public String getScname() {
        return scname;
    }

    public void setScname(String scname) {
        this.scname = scname;
    }

    public String getSctype() {
        return sctype;
    }

    public void setSctype(String sctype) {
        this.sctype = sctype;
    }

    public String getOntology() {
        return ontology;
    }

    public void setOntology(String ontology) {
        this.ontology = ontology;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scaleid != null ? scaleid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Scales)) {
            return false;
        }
        Scales other = (Scales) object;
        if ((this.scaleid == null && other.scaleid != null) || (this.scaleid != null && !this.scaleid.equals(other.scaleid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "org.cimmyt.cril.ibworkbench.services.beans.Scale[scaleid=" + scaleid + "]";
        return scname;
    }
}