/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.helpers;

import org.cimmyt.cril.ibwb.api.AppServices;
import org.cimmyt.cril.ibwb.api.CommonServices;
import org.cimmyt.cril.ibwb.domain.Germplsm;
import org.cimmyt.cril.ibwb.domain.Listnms;
import org.cimmyt.cril.ibwb.domain.Measuredin;
import org.cimmyt.cril.ibwb.domain.Transformations;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.domain.*;

/**
 *
 * @author MasterGama
 */
public class HelperTransformations {

    private static Logger log = Logger.getLogger(HelperTransformations.class);
    private AppServices appServices;
    private CommonServices servicioLocal;
    private CommonServices servicioCentral;
    
    public HelperTransformations(
            AppServices appServices,
            CommonServices servicioLocal,
            CommonServices servicioCentral
            ) {
        
        this.setAppServices(appServices);
        this.setServicioLocal(servicioLocal);
        this.setServicioCentral(servicioCentral);
    }
    
    public Transformations getByTraididScaleidMethodid(Integer traitid, Integer scaleid, Integer methodid) {
        Measuredin measuredin = appServices.getMeasuredinByTraitidScaleidTmethid(traitid, scaleid, methodid);
        if(measuredin != null){
            Integer transformationId = (Integer.valueOf(measuredin.getFormula()));
            Transformations transformations = servicioCentral.getTransformations(transformationId);
            if(transformations.getTranstype() != null){
                if(transformations.getTranstype().length() != 0){
                    switch (transformations.getTranstype().charAt(0)){
                        case 'C':
                            ContinuousConversion continuousConversion = servicioCentral.getContinuousConversion(transformationId);
                            transformations.setContinuousConversion(continuousConversion);
                            return transformations;
                        case 'D':
                            break;
                        case 'F':
                            break;
                        default:
                            log.error("No se reconoce el tipo de transformacion.");
                            break;
                    }
                }else{
                    log.error("El tipo de transformacion esta vacio.");
                }
            }else{
                log.error("El tipo de transformacion es null.");
            }
        }else{
            log.error("No se encontro ningun measuredin.");
        }
        return null;
    }

    /**
     * @return the appServices
     */
    public AppServices getAppServices() {
        return appServices;
    }

    /**
     * @param appServices the appServices to set
     */
    public void setAppServices(AppServices appServices) {
        this.appServices = appServices;
    }

    /**
     * @return the servicioLocal
     */
    public CommonServices getServicioLocal() {
        return servicioLocal;
    }

    /**
     * @param servicioLocal the servicioLocal to set
     */
    public void setServicioLocal(CommonServices servicioLocal) {
        this.servicioLocal = servicioLocal;
    }

    /**
     * @return the servicioCentral
     */
    public CommonServices getServicioCentral() {
        return servicioCentral;
    }

    /**
     * @param servicioCentral the servicioCentral to set
     */
    public void setServicioCentral(CommonServices servicioCentral) {
        this.servicioCentral = servicioCentral;
    }
    
}
