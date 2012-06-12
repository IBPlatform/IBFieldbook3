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
@Table(name = "trait")
public class Trait extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "tid")
    private Integer tid;
    @Basic(optional = false)
    @Column(name = "traitid")
    private Integer traitid;
    @Basic(optional = false)
    @Column(name = "trname")
    private String trname;
    @Basic(optional = false)
    @Column(name = "trabbr")
    private String trabbr;
    @Basic(optional = false)
    @Column(name = "trdesc")
    private String trdesc;
    @Basic(optional = false)
    @Column(name = "scaleid")
    private Integer scaleid;
    @Column(name = "tmethid")
    private Integer tmethid;
    @Column(name = "tnstat")
    private Integer tnstat;
    @Basic(optional = false)
    @Column(name = "TraitGroup")
    private String traitGroup;
    @Basic(optional = false)
    @Column(name = "Ontology")
    private String ontology;
    @Transient
    private Scale scale;
    @Transient
    private Tmethod tmethod;
    
    public Trait() {
    	setDefault();
    }
    
    public Trait(boolean atrNulls) {
    	if(!atrNulls)
            setDefault();
    }
    
    public void setDefault(){
    	tid = 0;
    	traitid = 0;
    	trname = "-";
    	trabbr = "-";
    	trdesc = "-";
    	scaleid = 0;
    	tmethid = 0;
    	tnstat = 0;
    	traitGroup = "-";
    	ontology = "-";
    }

    public Trait(Integer tid) {
        this.tid = tid;
    }

    public Trait(
            Integer tid, 
            Integer traitid, 
            String trname, 
            String trabbr, 
            String trdesc, 
            Integer scaleid, 
            String traitGroup, 
            String ontology
            ) {
        this.tid = tid;
        this.traitid = traitid;
        this.trname = trname;
        this.trabbr = trabbr;
        this.trdesc = trdesc;
        this.scaleid = scaleid;
        this.traitGroup = traitGroup;
        this.ontology = ontology;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getTraitid() {
        return traitid;
    }

    public void setTraitid(Integer traitid) {
        this.traitid = traitid;
    }

    public String getTrname() {
        return trname;
    }

    public void setTrname(String trname) {
        this.trname = trname;
    }

    public String getTrabbr() {
        return trabbr;
    }

    public void setTrabbr(String trabbr) {
        this.trabbr = trabbr;
    }

    public String getTrdesc() {
        return trdesc;
    }

    public void setTrdesc(String trdesc) {
        this.trdesc = trdesc;
    }

    public Integer getScaleid() {
        return scaleid;
    }

    public void setScaleid(Integer scaleid) {
        this.scaleid = scaleid;
    }

    public Integer getTmethid() {
        return tmethid;
    }

    public void setTmethid(Integer tmethid) {
        this.tmethid = tmethid;
    }

    public Integer getTnstat() {
        return tnstat;
    }

    public void setTnstat(Integer tnstat) {
        this.tnstat = tnstat;
    }

    public String getTraitGroup() {
        return traitGroup;
    }

    public void setTraitGroup(String traitGroup) {
        this.traitGroup = traitGroup;
    }

    public String getOntology() {
        return ontology;
    }

    public void setOntology(String ontology) {
        this.ontology = ontology;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tid != null ? tid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trait)) {
            return false;
        }
        Trait other = (Trait) object;
        if ((this.tid == null && other.tid != null) || (this.tid != null && !this.tid.equals(other.tid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Trait[tid=" + tid + "]";
    }
    
    @Transient
    public Scale getScale() {
        return scale;
    }

    public void setScale(Scale scale) {
        this.scale = scale;
    }
    

    @Transient
    public Tmethod getTmethod() {
            return tmethod;
    }

    public void setTmethod(Tmethod tmethod) {
            this.tmethod = tmethod;
    }
}
