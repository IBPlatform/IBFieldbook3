/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.domain;

/**
 *
 * @author TMSANCHEZ
 */
public class GermplasmSearch {
     
    private Integer studyId;
    private Integer trial;
    private Integer plot;
    
    private Germplsm germplsm;
    private Names names;
    
    private Germplsm germplsmMale;
    private Names namesMale;
    
    private String lid;
    private Integer max;//exacto el maximo
    private String bcid;

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
     * @return the plot
     */
    public Integer getPlot() {
        return plot;
    }

    /**
     * @param plot the plot to set
     */
    public void setPlot(Integer plot) {
        this.plot = plot;
    }

    /**
     * @return the germplsm
     */
    public Germplsm getGermplsm() {
        return germplsm;
    }

    /**
     * @param germplsm the germplsm to set
     */
    public void setGermplsm(Germplsm germplsm) {
        this.germplsm = germplsm;
    }

    /**
     * @return the names
     */
    public Names getNames() {
        return names;
    }

    /**
     * @param names the names to set
     */
    public void setNames(Names names) {
        this.names = names;
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
     * @return the max
     */
    public Integer getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(Integer max) {
        this.max = max;
    }

    /**
     * @return the bcid
     */
    public String getBcid() {
        return bcid;
    }

    /**
     * @param bcid the bcid to set
     */
    public void setBcid(String bcid) {
        this.bcid = bcid;
    }

    /**
     * @return the germplsmMale
     */
    public Germplsm getGermplsmMale() {
        return germplsmMale;
    }

    /**
     * @param germplsmMale the germplsmMale to set
     */
    public void setGermplsmMale(Germplsm germplsmMale) {
        this.germplsmMale = germplsmMale;
    }

    /**
     * @return the namesMale
     */
    public Names getNamesMale() {
        return namesMale;
    }

    /**
     * @param namesMale the namesMale to set
     */
    public void setNamesMale(Names namesMale) {
        this.namesMale = namesMale;
    }
    
    
}