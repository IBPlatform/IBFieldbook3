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
import org.cimmyt.cril.ibwb.provider.utils.DecimalUtils;

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
            if(measuredin.getFormula() != null){
                if( DecimalUtils.isDecimal(measuredin.getFormula())){
                    Integer transformationId = (Integer.valueOf(measuredin.getFormula()));
                    Transformations transformations = appServices.getTransformations(transformationId);
                    if(transformations.getTranstype() != null){
                        if(transformations.getTranstype().length() != 0){
                            switch (transformations.getTranstype().charAt(0)){
                                case 'C':
                                    ContinuousConversion continuousConversion = appServices.getContinuousConversion(transformationId);
                                    transformations.setContinuousConversion(continuousConversion);
                                    return transformations;
                                case 'D':
                                    DiscreteConversion discreteConversion = appServices.getDiscreteConversion(transformationId);
                                    transformations.setDiscreteConversion(discreteConversion);
                                    return transformations;
                                case 'F':
                                    ContinuousFunction continuousFunction = appServices.getContinuousFunction(transformationId);
                                    TmsConsistencyChecks tmsConsistencyChecks = appServices.getTmsConsistencyChecks(transformationId);
                                    continuousFunction.getTmsConsistencyChecksList().add(tmsConsistencyChecks);
                                    continuousFunction.setFormulaOriginal(tmsConsistencyChecks.getValue());
                                    while(tmsConsistencyChecks.getLink() != null){
                                        tmsConsistencyChecks = appServices.getTmsConsistencyChecks(tmsConsistencyChecks.getLink());
                                        continuousFunction.getTmsConsistencyChecksList().add(tmsConsistencyChecks);
                                        continuousFunction.getTmsConsistencyChecksDependencys().add(tmsConsistencyChecks);
                                    }
                                    String temp = continuousFunction.getFormulaOriginal();
                                    for(int i = continuousFunction.getTmsConsistencyChecksDependencys().size() - 1 ; i >= 0 ; i-- ){
                                        TmsConsistencyChecks tmsConsistencyChecksT = continuousFunction.getTmsConsistencyChecksDependencys().get(i);
                                        temp = temp.replace(tmsConsistencyChecksT.getValue(), "@" + tmsConsistencyChecksT.getTraitid());
                                    }
                                    continuousFunction.setFormulaTraducida(temp);
                                    transformations.setContinuousFunction(continuousFunction);
                                    return transformations;
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
                    log.error("La formula encontrada dentro del TmsMeasuredin es incompatible.");
                }
            }else{
                log.error("No se encontro ninguna formula.");
            }
        }else{
            log.error("No se encontro ningun measuredin.");
        }
        return null;
    }
    
    public Transformations getByVariateid(Integer variateid) {
        Variate variate = appServices.getVariate(variateid);
        return getByTraididScaleidMethodid(variate.getTid(), variate.getScaleid(), variate.getTmethid());
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
