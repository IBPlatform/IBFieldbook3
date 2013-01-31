/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cimmyt.cril.ibwb.domain;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.cimmyt.cril.ibwb.domain.filter.BaseFilter;

import java.awt.datatransfer.Transferable;

/**
 *
 * @author jgcamarena
 */
@Entity
@Table(name = "TMSTRAITS")
public class Traits extends BaseFilter implements Serializable, Transferable {
    
   public static final String TRAIT_TYPE_FACTOR = "F";
   public static final String TRAIT_TYPE_CONSTANT = "C";
   public static final String TRAIT_TYPE_VARIATE = "V";
    
    public static final DataFlavor DATA_FLAVOR = new DataFlavor(Traits.class, "traits");    
    
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
//    @Column(name = "tmethid")
//    private Integer tmethid;
    @Column(name = "tnstat")
    private Integer tnstat;
    @Basic(optional = false)
    @Column(name = "traitGroup")
    private String traitGroup;
    @Basic(optional = false)
    @Column(name = "Ontology")
    private String ontology;
    @Basic(optional = true)
    @Column(name = "traittype")
    private String traittype;
//    @ManyToOne(optional=false,fetch= FetchType.LAZY)
//    @JoinColumn(name="tid",nullable=false)
    @Transient
    private Measuredin measuredin;
    @Transient
    private List<Measuredin> measuredins;
//    @Transient
//    private Tmethod tmethod;
    

    public Traits() {
    	setDefault();
    }
    
    public Traits(boolean atrNulls) {
    	if(!atrNulls)
            setDefault();
    }
    
    public Traits(String trname) {
    	trname = trname;
    }
    
    public void setDefault(){
    	tid = 0;
    	traitid = 0;
    	trname = "-";
    	trabbr = "-";
    	trdesc = "-";
//    	tmethid = 0;
    	tnstat = 0;
    	traitGroup = "-";
    	ontology = "-";
//    	traittype = "v";
    }

    public Traits(Integer tid) {
        this.tid = tid;
    }

    public Traits(Integer tid, Integer traitid, String trname, String trabbr, String trdesc, Integer scaleid, String traitGroup, String ontology) {
        this.tid = tid;
        this.traitid = traitid;
        this.trname = trname;
        this.trabbr = trabbr;
        this.trdesc = trdesc;
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

//    public Integer getTmethid() {
//        return tmethid;
//    }
//
//    public void setTmethid(Integer tmethid) {
//        this.tmethid = tmethid;
//    }

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
    
    public String getTraittype() {
            return traittype;
    }

    public void setTraittype(String traittype) {
            this.traittype = traittype;
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
        if (!(object instanceof Traits)) {
            return false;
        }
        Traits other = (Traits) object;
        if ((this.tid == null && other.tid != null) || (this.tid != null && !this.tid.equals(other.tid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Trait[tid=" + tid + "]";
    }

//    @Transient
//    public Tmethod getTmethod() {
//            return tmethod;
//    }
//
//    public void setTmethod(Tmethod tmethod) {
//            this.tmethod = tmethod;
//    }

    /**
     * @return the measuredin
     */
    public Measuredin getMeasuredin() {
        return measuredin;
    }

    /**
     * @param measuredin the measuredin to set
     */
    public void setMeasuredin(Measuredin measuredin) {
        this.measuredin = measuredin;
    }

    /**
     * @return the measuredins
     */
    public List<Measuredin> getMeasuredins() {
        return measuredins;
    }

    /**
     * @param measuredins the measuredins to set
     */
    public void setMeasuredins(List<Measuredin> measuredins) {
        this.measuredins = measuredins;
    }


    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{DATA_FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor == DATA_FLAVOR;
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor == DATA_FLAVOR) {
            return this;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}