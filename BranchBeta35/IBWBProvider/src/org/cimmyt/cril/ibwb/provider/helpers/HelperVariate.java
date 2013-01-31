/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.helpers;

import java.util.List;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.AppServices;
import org.cimmyt.cril.ibwb.api.CommonServices;
import org.cimmyt.cril.ibwb.domain.DataC;
import org.cimmyt.cril.ibwb.domain.DataCPK;
import org.cimmyt.cril.ibwb.domain.DataN;
import org.cimmyt.cril.ibwb.domain.DataNPK;
import org.cimmyt.cril.ibwb.domain.Dmsattr;
import org.cimmyt.cril.ibwb.domain.Variate;
import org.cimmyt.cril.ibwb.domain.LevelC;
import org.cimmyt.cril.ibwb.domain.LevelN;
import org.cimmyt.cril.ibwb.domain.Measuredin;
import org.cimmyt.cril.ibwb.domain.Scales;
import org.cimmyt.cril.ibwb.domain.Trait;
import org.cimmyt.cril.ibwb.domain.Traits;

/**
 *
 * @author gamaliel
 */
public class HelperVariate {

    private static Logger log = Logger.getLogger(HelperVariate.class);

    public static Variate getVariateFillingFull(
            Variate variateDto,
            CommonServices servicesLocal,
            CommonServices servicesCentral,
            AppServices appServices,
            Integer dmsaType
            ) {

        log.info("Filling variate: " + variateDto.getVname() + " " + " VariateId: " + variateDto.getVariatid());

        Dmsattr dmsattrFilter = new Dmsattr(null, dmsaType, null, variateDto.getVariatid(), null);
        if(variateDto.getVariatid() > 0){
            variateDto.setDmsattr(servicesCentral.getDmsattrByDmsatrecAndDmsatype(dmsattrFilter));
        }else{
            variateDto.setDmsattr(servicesLocal.getDmsattrByDmsatrecAndDmsatype(dmsattrFilter));
        }
        
//        List<Dmsattr> dmsattrs = servicio.getListDmsattr(dmsattrFilter, 0, 0, false);
//        if (dmsattrs.size() > 0) {
//            variateDto.setDmsattr(dmsattrs.get(0));
//        }
        
        Measuredin measuredin = appServices.getMeasuredinByTraitidScaleidTmethid(variateDto.getTid(), variateDto.getScaleid(), variateDto.getTmethid());
        variateDto.setMeasuredin(measuredin);

        //------Llenado de traits
//        if (traitB) {
//            Trait traitFilter = new Trait(true);
//            Trait trait;
//            traitFilter.setTid(variateDto.getTid());
//            //traitFilter.setTraitid(variateDto.getTraitid());
//            List<Trait> traitList = servicio.getListTrait(traitFilter, 0, 0, false);
//            if (traitList.size() > 0) {
//                trait = traitList.get(0);
//                System.out.println("Tid" + trait.getTid());
//                if (trait.getTmethid() != null) {
//                    trait.setTmethod(servicio.getTmethod(variateDto.getTmethid()));
//                }
//            } else {
//                trait = null;
//            }
//            variateDto.setTrait(trait);
//        } else {
            //TODO agregar validaciones para traits vacio y para tmethod vacio
            //variateDto.setTraits(servicio.getTraits(variateDto.getTraitid()));
//            variateDto.setTraits(servicio.getTraits(variateDto.getTid()));
//            if (variateDto.getTraits().getTmethid() != null) {
//                variateDto.getTraits().setTmethod(servicio.getTmethod(variateDto.getTraits().getTmethid()));
//            }

            // check if variate scale is null
//            if (variateDto.getTraits().getMeasuredin() == null) {
//                log.info("No measuredin found in variate, then look for scale using scaleid");
//                Measuredin measuredinT = new Measuredin(true);
//                measuredinT.setTraitid(variateDto.getTraitid());
//                measuredinT.setScaleid(variateDto.getScaleid());
//                measuredinT.setTmethid(variateDto.getTmethid());
//
//                Scales scales = servicio.getScalesById(variateDto.getScaleid());
//                measuredinT.setScales(scales);
//                variateDto.getTraits().setMeasuredin(measuredinT);
//            }

//        }
        //Cargando levels
        switch (variateDto.getDtype().charAt(0)) {
            //------Llenado de levelC
            case 'C':
                LevelC levelCFilter = new LevelC(true);
                levelCFilter.setFactorid(variateDto.getVariatid());
                List<LevelC> levelsC = appServices.getListLevelC(levelCFilter, 0, 0, false);
                if (levelsC == null) {
                    variateDto.setLevelC(null);
                } else if (levelsC.size() == 0) {
                    variateDto.setLevelC(null);
                } else {
                    variateDto.setLevelC(levelsC.get(0));
                }
                break;
            //------Llenado de levelN
            case 'N':
                LevelN levelNFilter = new LevelN(true);
                levelNFilter.setFactorid(variateDto.getVariatid());
                List<LevelN> levelsN = appServices.getListLevelN(levelNFilter, 0, 0, false);
                if (levelsN == null) {
                    variateDto.setLevelN(null);
                } else if (levelsN.size() == 0) {
                    variateDto.setLevelN(null);
                } else {
                    variateDto.setLevelN(levelsN.get(0));
                }
                break;
            default:
                System.out.println("Not recognizing the kind of level");
                break;
        }
        return variateDto;
    }

    public static Variate getVariateConstantsFillingFull(
            Variate variateDto,
            AppServices appServices,
            Integer dmsaType) {

        log.info("Filling variate: " + variateDto.getVname() + " " + " VariateId: " + variateDto.getVariatid());
        
        Dmsattr dmsattrFilter = new Dmsattr(null, dmsaType, null, variateDto.getVariatid(), null);
////        if(variateDto.getVariatid() > 0){
////            variateDto.setDmsattr(servicesCentral.getDmsattrByDmsatrecAndDmsatype(dmsattrFilter));
////        }else{
////            variateDto.setDmsattr(servicesLocal.getDmsattrByDmsatrecAndDmsatype(dmsattrFilter));
////        }
        variateDto.setDmsattr(appServices.getDmsattrByDmsatrecAndDmsatype(dmsattrFilter));
        
        Measuredin measuredin = appServices.getMeasuredinByTraitidScaleidTmethid(variateDto.getTid(), variateDto.getScaleid(), variateDto.getTmethid());
        variateDto.setMeasuredin(measuredin);
        
        //------Llenado de traits

        //Cargando datas
        switch (variateDto.getDtype().charAt(0)) {
            //------Llenado de levelC
            case 'C':
                DataC dataCFilter = new DataC(true);
                DataCPK dataCPK = new DataCPK();
//                dataCPK.setOunitid(dmsaType);
                dataCPK.setVariatid(variateDto.getVariatid());
                dataCFilter.setDataCPK(dataCPK);
                List<DataC> datasC = appServices.getListDataC(dataCFilter, 0, 0, false);
                variateDto.setDatasC(datasC);

                break;
            //------Llenado de levelN
            case 'N':
                DataN dataNFilter = new DataN(true);
                DataNPK dataNPK = new DataNPK();
//                dataCPK.setOunitid(dmsaType);
                dataNPK.setVariatid(variateDto.getVariatid());
                dataNFilter.setDataNPK(dataNPK);
                List<DataN> datasN = appServices.getListDataN(dataNFilter, 0, 0, false);
                variateDto.setDatasN(datasN);
                break;
            default:
                System.out.println("Not recognizing the kind of level");
                break;
        }
        return variateDto;
    }
}
