/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.helpers;

import org.cimmyt.cril.ibwb.domain.Factor;

/**
 *
 * @author gamaliel
 */
public class HelperContentEffectidAndFactors {
    
    private Factor factorStudy;
    private Factor factorTrial;
    private Factor factorEntry;
    private Factor factorPlot;
    private Integer effectid;

    /**
     * @return the factorStudy
     */
    public Factor getFactorStudy() {
        return factorStudy;
    }

    /**
     * @param factorStudy the factorStudy to set
     */
    public void setFactorStudy(Factor factorStudy) {
        this.factorStudy = factorStudy;
    }

    /**
     * @return the factorTrial
     */
    public Factor getFactorTrial() {
        return factorTrial;
    }

    /**
     * @param factorTrial the factorTrial to set
     */
    public void setFactorTrial(Factor factorTrial) {
        this.factorTrial = factorTrial;
    }

    /**
     * @return the factorEntry
     */
    public Factor getFactorEntry() {
        return factorEntry;
    }

    /**
     * @param factorEntry the factorEntry to set
     */
    public void setFactorEntry(Factor factorEntry) {
        this.factorEntry = factorEntry;
    }

    /**
     * @return the factorPlot
     */
    public Factor getFactorPlot() {
        return factorPlot;
    }

    /**
     * @param factorPlot the factorPlot to set
     */
    public void setFactorPlot(Factor factorPlot) {
        this.factorPlot = factorPlot;
    }

    /**
     * @return the effectid
     */
    public Integer getEffectid() {
        return effectid;
    }

    /**
     * @param effectid the effectid to set
     */
    public void setEffectid(Integer effectid) {
        this.effectid = effectid;
    }
    
}
