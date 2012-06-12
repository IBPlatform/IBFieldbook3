/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.helpers;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.domain.Scale;
import org.cimmyt.cril.ibwb.api.AppServices;
import org.cimmyt.cril.ibwb.api.CommonServices;
import org.cimmyt.cril.ibwb.domain.*;

/**
 *
 * @author MasterGama
 */
public class MigrateData {

    private static Logger log = Logger.getLogger(MigrateData.class);

    public static void insertScaleGroupToScales(CommonServices commonServices) {

//        if(commonServices.isLocal()){
//            log.info("-------------List Scale Groups Local");
//        }else{
//            log.info("-------------List Scale Groups Central");
//        }
        List<Scale> listScaleCatalogLocal = commonServices.getListScaleGroups();

//        log.info("Tamaño del listado: " + listScaleCatalogLocal.size());
//        for (Scale scale : listScaleCatalogLocal) {
//            log.info("\t scname: " + scale.getScname() + " sctype: " + scale.getSctype());
//        }
//        if(commonServices.isLocal()){
//            log.info("-------------End List Scale Groups Local");
//        }else{
//            log.info("-------------End List Scale Groups Central");
//        }
        if (listScaleCatalogLocal.size() != commonServices.getListScaleAll().size()) {
            for (Scale scale : listScaleCatalogLocal) {
                Scales tempScales = new Scales();

                //            tempScales.setScaleid(scale.getScaleid());
                tempScales.setScname(scale.getScname());
                tempScales.setSctype(scale.getSctype());

                commonServices.addScales(tempScales);
            }
        } else {
            commonServices.migrateScaleToTmsscalesDirect();
        }

//        List<Scales> listScalesCatalog = commonServices.getScalesList();
//        log.info("-------------List Scales Catalog");
//        for (Scales scales : listScalesCatalog) {
//            log.info("\t scname: " + scales.getScname() + " sctype: " + scales.getSctype());
//        }
//        log.info("-------------End List Scales Catalog");
    }

    public static void completeDependencyRatioMeasuredinLocal(CommonServices commonServices, CommonServices commonServicesCentral) {
        List<Factor> factors = commonServices.getFactorConvinacionesTraitScaleMethod();
        List<Variate> variates = commonServices.getVariateConvinacionesTraitScaleMethod();

        log.info("number different factors found " + factors.size());
        log.info("number differents variates found " + variates.size());
        if (factors != null) {
            for (Factor factorTemp : factors) {
                Scale scale = null;
                Scales tmsScales = null;
                if (factorTemp.getScaleid() < 0) {
                    scale = commonServices.getScale(factorTemp.getScaleid());
                    tmsScales = new Scales(null, scale.getScname(), scale.getSctype(), null, "N");
                    tmsScales = commonServices.getScalesByScnameAndSctype(tmsScales);
                } else {
                    scale = commonServicesCentral.getScale(factorTemp.getScaleid());
                    if (scale != null) {
                        tmsScales = new Scales(null, scale.getScname(), scale.getSctype(), null, "N");
                        tmsScales = commonServicesCentral.getScalesByScnameAndSctype(tmsScales);
                    }
                }
                if (tmsScales != null) {
                    Measuredin measuredin = new Measuredin();
                    measuredin.setTraitid(factorTemp.getTid());
                    measuredin.setScaleid(tmsScales.getScaleid());
                    measuredin.setStandardscale(tmsScales.getScaleid().toString());
                    measuredin.setTmethid(factorTemp.getTmethid());
                    commonServices.addMeasuredin(measuredin);
                } else {
                    log.error("An error occurred while trying to create the registry measuredin for Factor: " + factorTemp.getFactorid());
                    log.error("traitid: " + factorTemp.getTraitid() + " scaleid: " + factorTemp.getScaleid() + "tmethodid: " + factorTemp.getTmethid());
                    //log.error("Scale not found in the table referred to scales -> scaleid: " + scale.getScaleid());
                }
            }
        }

        if (variates != null) {
            for (Variate variateTemp : variates) {
                Scale scale = null;
                Scales tmsScales = null;
                if (variateTemp.getScaleid() < 0) {
                    scale = commonServices.getScale(variateTemp.getScaleid());
                    tmsScales = new Scales(null, scale.getScname(), scale.getSctype(), null, "N");
                    tmsScales = commonServices.getScalesByScnameAndSctype(tmsScales);
                } else {
                    scale = commonServicesCentral.getScale(variateTemp.getScaleid());
                    if (scale != null) {
                        tmsScales = new Scales(null, scale.getScname(), scale.getSctype(), null, "N");
                        tmsScales = commonServicesCentral.getScalesByScnameAndSctype(tmsScales);
                    }
                }
                if (tmsScales != null) {
                    Measuredin measuredin = new Measuredin();
                    measuredin.setTraitid(variateTemp.getTid());
                    measuredin.setScaleid(tmsScales.getScaleid());
                    measuredin.setStandardscale(tmsScales.getScaleid().toString());
                    measuredin.setTmethid(variateTemp.getTmethid());
                    commonServices.addMeasuredin(measuredin);
                } else {
                    log.error("An error occurred while trying to create the registry measuredin for Variate: " + variateTemp.getVariatid());
                    log.error("traitid: " + variateTemp.getTraitid() + " scaleid: " + variateTemp.getScaleid() + "tmethodid: " + variateTemp.getTmethid());
                    //log.error("Scale not found in the table referred to scales -> scaleid: " + scale.getScaleid());
                }
            }
        }
    }

    public static void completeDependencyRatioMeasuredinCentral(CommonServices commonServices) {
        List<Factor> factors = commonServices.getFactorConvinacionesTraitScaleMethod();
        List<Variate> variates = commonServices.getVariateConvinacionesTraitScaleMethod();
        log.info("number different factors found " + factors.size());
        log.info("number differents variates found " + variates.size());
        if (factors != null) {
            for (Factor factorTemp : factors) {
                log.info("looking scale " + factorTemp.getScaleid());
                Scale scale = commonServices.getScale(factorTemp.getScaleid());
                if (scale != null) {
                    Scales tmsScales = new Scales(null, scale.getScname(), scale.getSctype(), null, "N");
                    tmsScales = commonServices.getScalesByScnameAndSctype(tmsScales);
                    if (tmsScales != null) {
                        Measuredin measuredin = new Measuredin();
                        measuredin.setTraitid(factorTemp.getTid());
                        measuredin.setScaleid(tmsScales.getScaleid());
                        measuredin.setStandardscale(tmsScales.getScaleid().toString());
                        measuredin.setTmethid(factorTemp.getTmethid());
                        commonServices.addMeasuredin(measuredin);
                    } else {
                        log.error("An error occurred while trying to create the registry measuredin for Factor: " + factorTemp.getFactorid());
                        log.error("traitid: " + factorTemp.getTraitid() + " scaleid: " + factorTemp.getScaleid() + "tmethodid: " + factorTemp.getTmethid());
                        log.error("Scale not found in the table referred to scales -> scaleid: " + scale.getScaleid());
                    }
                } else {
                    log.error("Scale not found in the table referred to scales -> scaleid: " + factorTemp.getScaleid());
                }
            }
        }
        if (variates != null) {
            for (Variate variateTemp : variates) {
                log.info("looking scale " + variateTemp.getScaleid());
                Scale scale = commonServices.getScale(variateTemp.getScaleid());
                if (scale != null) {
                    Scales tmsScales = new Scales(null, scale.getScname(), scale.getSctype(), null, "N");
                    tmsScales = commonServices.getScalesByScnameAndSctype(tmsScales);
                    if (tmsScales != null) {
                        Measuredin measuredin = new Measuredin();
                        measuredin.setTraitid(variateTemp.getTid());
                        measuredin.setScaleid(tmsScales.getScaleid());
                        measuredin.setStandardscale(tmsScales.getScaleid().toString());
                        measuredin.setTmethid(variateTemp.getTmethid());
                        commonServices.addMeasuredin(measuredin);
                    } else {
                        log.error("An error occurred while trying to create the registry measuredin for Variate: " + variateTemp.getVariatid());
                        log.error("traitid: " + variateTemp.getTraitid() + " scaleid: " + variateTemp.getScaleid() + "tmethodid: " + variateTemp.getTmethid());
                        log.error("Scale not found in the table referred to scales -> scaleid: " + scale.getScaleid());
                    }
                } else {
                    log.error("Scale not found in the table referred to scales -> scaleid: " + variateTemp.getScaleid());
                }
            }
        }
        addTraitsToMeasuredinCentral(commonServices);
    }

    public static void addTraitsToMeasuredinLocal(
            CommonServices commonServices,
            CommonServices commonServicesCentral) {
        List<Trait> traitList = commonServices.getTraitList();

        for (Trait trait : traitList) {
            Scale scale = commonServices.getScale(trait.getScaleid());
            Scales tmsScales = commonServices.getScalesByScnameAndSctype(new Scales(null, scale.getScname(), scale.getSctype(), null, null));

            Traits tmsTraits = commonServices.getTraits(trait.getTid());

            TmsMethod tmsMethod = commonServices.getTmsMethod(trait.getTmethid());

            if (tmsScales != null && tmsMethod != null && tmsTraits != null) {
                Measuredin measuredin = new Measuredin();
                measuredin.setTraitid(tmsTraits.getTid());
                measuredin.setScaleid(tmsScales.getScaleid());
                measuredin.setStandardscale(tmsScales.getScaleid().toString());
                measuredin.setTmethid(tmsMethod.getTmethid());
                if (commonServices.getMeasuredinByTraitidScaleidTmethid(measuredin) == null) {
                    commonServices.addMeasuredin(measuredin);
                }
            } else {
                log.error("An error occurred while trying to create the registry measuredin for traitid: " + trait.getTraitid());
                log.error("tid: " + trait.getTid() + " scaleid: " + trait.getScaleid() + "tmethodid: " + trait.getTmethid());
            }
        }
    }

    public static void addTraitsToMeasuredinCentral(CommonServices commonServices) {
        List<Trait> traitList = commonServices.getTraitList();

        for (Trait trait : traitList) {
            log.info("looking scale " + trait.getScaleid());
            Scale scale = commonServices.getScale(trait.getScaleid());
            if (scale != null) {
                Scales tmsScales = commonServices.getScalesByScnameAndSctype(new Scales(null, scale.getScname(), scale.getSctype(), null, null));

                Traits tmsTraits = commonServices.getTraits(trait.getTid());

                TmsMethod tmsMethod = commonServices.getTmsMethod(trait.getTmethid());

                if (tmsScales != null && tmsMethod != null && tmsTraits != null) {
                    Measuredin measuredin = new Measuredin();
                    measuredin.setTraitid(tmsTraits.getTid());
                    measuredin.setScaleid(tmsScales.getScaleid());
                    measuredin.setStandardscale(tmsScales.getScaleid().toString());
                    measuredin.setTmethid(tmsMethod.getTmethid());
                    if (commonServices.getMeasuredinByTraitidScaleidTmethid(measuredin) == null) {
                        commonServices.addMeasuredin(measuredin);
                    }
                } else {
                    log.error("An error occurred while trying to create the registry measuredin for traitid: " + trait.getTraitid());
                    log.error("tid: " + trait.getTid() + " scaleid: " + trait.getScaleid() + "tmethodid: " + trait.getTmethid());
                }
            } else {
                log.info("scalenot found " + trait.getScaleid());
            }

        }
    }
}
