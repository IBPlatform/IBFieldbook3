/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.domain;

import java.sql.ResultSet;

/**
 *
 * @author MasterGama
 */
public class StudySearch {
    
    private Integer studyId;
    private Integer trial;
    private String nameStudy;
    private String nameTrial;
    private String nameEntry;
    private String nameDesig;
    private String nameGid;
    private String namePlot;
    private ResultSet rst;
    private StringBuilder sb = new StringBuilder();
    private String lid;
    private String sName;
    
    /**
     * @return the studyId
     */
    public Integer getStudyId() {
        return studyId;
    }

    /**
     * @param studyId the studyId to set
     */
    public void setStudyId(Integer studyId) {
        this.studyId = studyId;
    }

    /**
     * @return the nameStudy
     */
    public String getNameStudy() {
        return nameStudy;
    }

    /**
     * @param nameStudy the nameStudy to set
     */
    public void setNameStudy(String nameStudy) {
        this.nameStudy = nameStudy;
    }

    /**
     * @return the nameTrial
     */
    public String getNameTrial() {
        return nameTrial;
    }

    /**
     * @param nameTrial the nameTrial to set
     */
    public void setNameTrial(String nameTrial) {
        this.nameTrial = nameTrial;
    }

    /**
     * @return the nameEntry
     */
    public String getNameEntry() {
        return nameEntry;
    }

    /**
     * @param nameEntry the nameEntry to set
     */
    public void setNameEntry(String nameEntry) {
        this.nameEntry = nameEntry;
    }

    /**
     * @return the nameDesig
     */
    public String getNameDesig() {
        return nameDesig;
    }

    /**
     * @param nameDesig the nameDesig to set
     */
    public void setNameDesig(String nameDesig) {
        this.nameDesig = nameDesig;
    }

    /**
     * @return the nameGid
     */
    public String getNameGid() {
        return nameGid;
    }

    /**
     * @param nameGid the nameGid to set
     */
    public void setNameGid(String nameGid) {
        this.nameGid = nameGid;
    }

    /**
     * @return the namePlot
     */
    public String getNamePlot() {
        return namePlot;
    }

    /**
     * @param namePlot the namePlot to set
     */
    public void setNamePlot(String namePlot) {
        this.namePlot = namePlot;
    }

    /**
     * @return the trial
     */
    public Integer getTrial() {
        return trial;
    }

    /**
     * @param trial the trial to set
     */
    public void setTrial(Integer trial) {
        this.trial = trial;
    }

    /**
     * @return the rst
     */
    public ResultSet getRst() {
        return rst;
    }

    /**
     * @param rst the rst to set
     */
    public void setRst(ResultSet rst) {
        this.rst = rst;
    }

    /**
     * @return the sb
     */
    public StringBuilder getSb() {
        return sb;
    }

    /**
     * @param sb the sb to set
     */
    public void setSb(StringBuilder sb) {
        this.sb = sb;
    }

    /**
     * @return the lid
     */
    public String getLid() {
        return lid;
    }

    /**
     * @param lid the lid to set
     */
    public void setLid(String lid) {
        this.lid = lid;
    }

    /**
     * @return the sName
     */
    public String getsName() {
        return sName;
    }

    /**
     * @param sName the sName to set
     */
    public void setsName(String sName) {
        this.sName = sName;
    }
    
    
}