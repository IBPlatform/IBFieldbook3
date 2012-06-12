/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.domain.filter;

import javax.persistence.Transient;

/**
 *
 * @author MasterGama
 */
public class BaseFilter {
    
    @Transient
    private boolean pivot = false;
    
    @Transient
    private String globalsearch;
	
    public String getGlobalsearch() {
        return globalsearch;
    }

    public void setGlobalsearch(String globalsearch) {
        this.globalsearch = globalsearch;
    }

    @Transient
    public boolean isPivot() {
        return pivot;
    }

    @Transient
    public void setPivot(boolean pivot) {
        this.pivot = pivot;
    }
    
}
