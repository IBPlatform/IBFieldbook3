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
import javax.persistence.Table;
import javax.persistence.Transient;
import org.cimmyt.cril.ibwb.domain.filter.BaseFilter;

/**
 *
 * @author jgcamarena
 */
@Entity
@Table(name = "variate")
public class Variate extends BaseFilter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "variatid")
    private Integer variatid;
    @Basic(optional = false)
    @Column(name = "studyid")
    private Integer studyid;
    @Basic(optional = false)
    @Column(name = "vname")
    private String vname;
    @Basic(optional = false)
    @Column(name = "traitid")
    private Integer traitid;
    @Basic(optional = false)
    @Column(name = "scaleid")
    private Integer scaleid;
    @Basic(optional = false)
    @Column(name = "tmethid")
    private Integer tmethid;
    @Basic(optional = false)
    @Column(name = "dtype")
    private String dtype;
    @Basic(optional = false)
    @Column(name = "vtype")
    private String vtype;
    @Basic(optional = false)
    @Column(name = "tid")
    private Integer tid;
    @Transient
    private Measuredin measuredin;
    @Transient
    private Trait trait;
    @Transient
    private LevelC levelC;
    @Transient
    private LevelN levelN;
    @Transient
    private Dmsattr dmsattr;

    public Variate() {
        setDefault();
    }

    public Variate(boolean atrNulls) {
        if (!atrNulls) {
            setDefault();
        }
    }
    @Transient
    private List<DataC> datasC;
    @Transient
    private List<DataN> datasN;

    public List<DataC> getDatasC() {
        return datasC;
    }

    public void setDefault() {
        variatid = 0;
        studyid = 0;
        vname = "-";
        traitid = 0;
        scaleid = 0;
        tmethid = 0;
        dtype = "-";
        vtype = "-";
    }

    public Variate(Integer variatid) {
        this.variatid = variatid;
    }

    public Variate(Integer variatid, Integer studyid, String vname, Integer traitid, Integer scaleid, Integer tmethid, String dtype, String vtype) {
        this.variatid = variatid;
        this.studyid = studyid;
        this.vname = vname;
        this.traitid = traitid;
        this.scaleid = scaleid;
        this.tmethid = tmethid;
        this.dtype = dtype;
        this.vtype = vtype;
    }

    public Integer getVariatid() {
        return variatid;
    }

    public void setVariatid(Integer variatid) {
        this.variatid = variatid;
    }

    public Integer getStudyid() {
        return studyid;
    }

    public void setStudyid(Integer studyid) {
        this.studyid = studyid;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
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

    public Integer getTmethid() {
        return tmethid;
    }

    public void setTmethid(Integer tmethid) {
        this.tmethid = tmethid;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public String getVtype() {
        return vtype;
    }

    public void setVtype(String vtype) {
        this.vtype = vtype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (variatid != null ? variatid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Variate)) {
            return false;
        }
        Variate other = (Variate) object;
        if ((this.variatid == null && other.variatid != null) || (this.variatid != null && !this.variatid.equals(other.variatid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Variate[variatid=" + variatid + "]";
    }

    public Trait getTrait() {
        return trait;
    }

    public void setTrait(Trait trait) {
        this.trait = trait;
    }

    public Measuredin getMeasuredin() {
        return measuredin;
    }

    public void setMeasuredin(Measuredin measuredin) {
        this.measuredin = measuredin;
    }

    public LevelC getLevelC() {
        return levelC;
    }

    public void setLevelC(LevelC levelC) {
        this.levelC = levelC;
    }

    public LevelN getLevelN() {
        return levelN;
    }

    public void setLevelN(LevelN levelN) {
        this.levelN = levelN;
    }

    public Dmsattr getDmsattr() {
        return dmsattr;
    }

    public void setDmsattr(Dmsattr dmsattr) {
        this.dmsattr = dmsattr;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }
    
    public void setDatasC(List<DataC> datasC) {
        this.datasC = datasC;
    }

    public List<DataN> getDatasN() {
        return datasN;
    }

    public void setDatasN(List<DataN> datasN) {
        this.datasN = datasN;
    }
    
    public int getSizeDatas(){
        if(datasC != null){
            return datasC.size();
        }else if(datasN != null){
            return datasN.size();
        }else{
            return 0;
        }
    }
    
    public Object getDataObject(int level){
        if(datasC != null){
            try{
                return datasC.get(level).getDvalue();
            }catch (Exception e){
                return "";
            }
        }else if(datasN != null){
            try{
                return datasN.get(level).getDvalue();
            }catch(Exception e){
                return "";
            }
        }else{
            return "";
        }
    }
    
    public Object getDataByIndex(int level){
        if(dtype.equals("N")){
            if(datasN.size() > 0){
                return datasN.get(level);
            }else{
                return null;
            }
        }else{
            if(datasC.size() > 0){
                return datasC.get(level);
            }else{
                return null;
            }
        }
    }
    
    public Integer getOunitidObject(int level){
        if(datasC != null){
            return datasC.get(level).getDataCPK().getOunitid();
        }else if(datasN != null){
            return datasN.get(level).getDataNPK().getOunitid();
        }else{
            return null;
        }
    }
}
