/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import org.cimmyt.cril.ibwb.domain.filter.BaseFilter;

/**
 *
 * @author jgcamarena
 */
@Entity
@Table(name = "factor")
public class Factor extends BaseFilter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "labelid")
    private Integer labelid;
    @Basic(optional = false)
    @Column(name = "factorid")
    private Integer factorid;
    @Basic(optional = false)
    @Column(name = "studyid")
    private Integer studyid;
    @Basic(optional = false)
    @Column(name = "fname")
    private String fname;
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
    @Column(name = "ltype")
    private String ltype;
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
    private List<LevelC> levelsC;
    @Transient
    private List<LevelN> levelsN;
    @Transient
    private Dmsattr dmsattr;
    @Transient
    private Map<Integer, LevelN> mapLevels;

    public Factor() {
        setDefault();
    }

    public Factor(boolean atrNulls) {
        if (!atrNulls) {
            setDefault();
        }
    }

    public void setDefault() {
        labelid = 0;
        factorid = 0;
        studyid = 0;
        fname = "-";
        traitid = 0;
        scaleid = 0;
        tmethid = 0;
        ltype = "-";
        levelsC = new ArrayList<LevelC>(0);
        levelsN = new ArrayList<LevelN>(0);
    }

    public Factor(Integer labelid) {
        this.labelid = labelid;
    }

    public Factor(Integer labelid, Integer factorid, Integer studyid, String fname, Integer traitid, Integer scaleid, Integer tmethid, String ltype) {
        this.labelid = labelid;
        this.factorid = factorid;
        this.studyid = studyid;
        this.fname = fname;
        this.traitid = traitid;
        this.scaleid = scaleid;
        this.tmethid = tmethid;
        this.ltype = ltype;
    }

    public Integer getLabelid() {
        return labelid;
    }

    public void setLabelid(Integer labelid) {
        this.labelid = labelid;
    }

    public Integer getFactorid() {
        return factorid;
    }

    public void setFactorid(Integer factorid) {
        this.factorid = factorid;
    }

    public Integer getStudyid() {
        return studyid;
    }

    public void setStudyid(Integer studyid) {
        this.studyid = studyid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
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

    public String getLtype() {
        return ltype;
    }

    public void setLtype(String ltype) {
        this.ltype = ltype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (labelid != null ? labelid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factor)) {
            return false;
        }
        Factor other = (Factor) object;
        if ((this.labelid == null && other.labelid != null) || (this.labelid != null && !this.labelid.equals(other.labelid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Factor[labelid=" + labelid + "]";
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

    public List<LevelC> getLevelsC() {
        return levelsC;
    }

    public void setLevelsC(List<LevelC> levelsC) {
        this.levelsC = levelsC;
    }

    public List<LevelN> getLevelsN() {
        return levelsN;
    }

    public void setLevelsN(List<LevelN> levelsN) {
        this.levelsN = levelsN;
    }

    public Integer getLevelNo() {
        if (getLtype().equals("N")) {
            if (this.getLevelsN().isEmpty()) {
                return 0;
            }
            return this.getLevelsN().get(0).getLevelNPK().getLevelno();
        } else {
            if (this.getLevelsC().isEmpty()) {
                return 0;
            }

            return this.getLevelsC().get(0).getLevelCPK().getLevelno();
        }
    }

    @Transient
    public Integer getSizeLevels() {
        if (getLtype().equals("N")) {
            return getLevelsN().size();
        } else {
            return getLevelsC().size();
        }
    }

    @Transient
    public Object getLevel(int indexLevel) {
        if (getLtype().equals("N")) {
            return getLevelsN().get(indexLevel).getLvalue();
        } else {
            return getLevelsC().get(indexLevel).getLvalue();
        }
    }
    
    @Transient
    public Object getLevelByIndex(int indexLevel) {
        if (getLtype().equals("N")) {
            if(getLevelsN().size()>0){
                return getLevelsN().get(indexLevel);
            }else{
                return null;
            }
        } else {
            if(getLevelsC().size()>0){
                return getLevelsC().get(indexLevel);
            }else{
                return null;
            }
        }
    }

    @Transient
    public Integer getLevelNo(int indexLevel) {
        if (getLtype().equals("N")) {
            return getLevelsN().get(indexLevel).getLevelNPK().getLevelno();
        } else {
            return getLevelsC().get(indexLevel).getLevelCPK().getLevelno();
        }
    }

    private void initMapLevel() {
        mapLevels = new HashMap<Integer, LevelN>();
        for (LevelN levelN : levelsN) {
            mapLevels.put(levelN.getLvalue().intValue(), levelN);
        }
    }

    public LevelN getLevelNByValue(Integer value) {

        if (mapLevels == null) {
            initMapLevel();
        }
        return mapLevels.get(value);
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }
    
    public List getLevels() {
        if (getLtype().equals("N")) {
            return levelsN;
        }else{
            return levelsC;
        }
    }
}
