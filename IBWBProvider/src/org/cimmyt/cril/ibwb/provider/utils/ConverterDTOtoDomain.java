package org.cimmyt.cril.ibwb.provider.utils;

import java.util.Map;

import org.cimmyt.cril.ibwb.domain.Study;
import org.cimmyt.cril.ibwb.domain.Factor;
import org.cimmyt.cril.ibwb.domain.Scales;
import org.cimmyt.cril.ibwb.domain.Variate;

public class ConverterDTOtoDomain {

    private static String notFound = "NOT FOUND";
    private static String unknown = "UNKNOWN";

    public static ibfb.domain.core.Study getStudy(Study studyDto) {
        ibfb.domain.core.Study study = new ibfb.domain.core.Study();

        study.setStudyid(studyDto.getStudyid());
        study.setStudy(studyDto.getSname());
        study.setTitle(studyDto.getTitle());
        study.setPmkey(studyDto.getPmkey().toString());
        study.setObjective(studyDto.getObjectiv());
        study.setInvestid(studyDto.getInvestid());
        //study.setStarDate(ConverterDate.getDate(studyDto.getSdate()));
        //study.setEndDate(ConverterDate.getDate(studyDto.getEdate()));
        study.setStarDate(ConverterDate.getIntegerAsDate(studyDto.getSdate()));
        study.setEndDate(ConverterDate.getIntegerAsDate(studyDto.getEdate()));        
        study.setStudyType(studyDto.getStype());
        study.setUserid(studyDto.getUserid());
        study.setSstatus(studyDto.getSstatus());
        study.setShierarchy(studyDto.getShierarchy());
        return study;
    }

    public static ibfb.domain.core.Condition getCondition(Factor factorDto, Map mapLabels, int indexLevel, int instance) {
        ibfb.domain.core.Condition condition = new ibfb.domain.core.Condition();

        // assign ids from database
        condition.setLabelId(factorDto.getLabelid());
        condition.setFactorId(factorDto.getFactorid());

        condition.setConditionName(factorDto.getFname());

        if (factorDto.getMeasuredin() != null) {
            if(factorDto.getMeasuredin().getTraits() != null){
                condition.setProperty(factorDto.getMeasuredin().getTraits().getTrname());
            }else{
                condition.setProperty(null);
            }
            if (factorDto.getDmsattr() != null) {
                condition.setDescription(factorDto.getDmsattr().getDmsatval());
            }
            if (factorDto.getMeasuredin().getTmsMethod() != null) {
                condition.setMethod(factorDto.getMeasuredin().getTmsMethod().getTmname());
            } else {
                condition.setMethod(null);
            }
            if (factorDto.getMeasuredin().getScales() != null) {
                condition.setScale(factorDto.getMeasuredin().getScales().getScname());
            } else {
                condition.setScale("--@--");
            }
        } else {
            condition.setProperty(null);
            condition.setDescription(null);
        }
        condition.setDataType(factorDto.getLtype());

//      Asignando los value
        if (factorDto.getLtype().equals("C")) {
            if (factorDto.getLevelsC().get(indexLevel) != null) {
                condition.setValue(factorDto.getLevelC().getLvalue());
                condition.setLevelNo(factorDto.getLevelC().getLevelCPK().getLevelno());
            } else {
                condition.setValue(null);
            }
        } else if (factorDto.getLtype().equals("N")) {
            if (factorDto.getLevelsN().get(indexLevel) != null) {
                condition.setValue(DecimalUtils.getValueAsString(factorDto.getLevelN().getLvalue()));
                condition.setLevelNo(factorDto.getLevelN().getLevelNPK().getLevelno());
            } else {
                condition.setValue(null);
            }
        } else {
            condition.setValue(unknown);
        }

//      Asignando los label
        Factor tempLabel = (Factor) mapLabels.get(factorDto.getFactorid());
        if (tempLabel != null) {
            condition.setLabel(tempLabel.getFname());
        } else {
            condition.setLabel(notFound);
        }
        condition.setInstance(instance);
        return condition;


    }

    public static ibfb.domain.core.Condition getCondition(
            Factor factorDto,
            Map mapLabels) {
        ibfb.domain.core.Condition condition = new ibfb.domain.core.Condition();

        // assign ids from database
        condition.setLabelId(factorDto.getLabelid());
        condition.setFactorId(factorDto.getFactorid());

        condition.setConditionName(factorDto.getFname());
        
        if (factorDto.getMeasuredin() != null) {
            if(factorDto.getMeasuredin().getTraits() != null){
                condition.setProperty(factorDto.getMeasuredin().getTraits().getTrname());
            }else{
                condition.setProperty(null);
            }
            if (factorDto.getDmsattr() != null) {
                condition.setDescription(factorDto.getDmsattr().getDmsatval());
            }
            if (factorDto.getMeasuredin().getTmsMethod() != null) {
                condition.setMethod(factorDto.getMeasuredin().getTmsMethod().getTmname());
            } else {
                condition.setMethod(null);
            }
            if (factorDto.getMeasuredin().getScales() != null) {
                condition.setScale(factorDto.getMeasuredin().getScales().getScname());
            } else {
                condition.setScale("--@--");
            }
        } else {
            condition.setProperty(null);
            condition.setDescription(null);
        }
        condition.setDataType(factorDto.getLtype());

//      Asignando los value
        if (factorDto.getLtype().equals("C")) {
            if (factorDto.getLevelC() != null) {
                condition.setValue(factorDto.getLevelC().getLvalue());
                condition.setLevelNo(factorDto.getLevelC().getLevelCPK().getLevelno());
            } else {
                condition.setValue("");
            }
        } else if (factorDto.getLtype().equals("N")) {
            if (factorDto.getLevelN() != null) {
                condition.setValue(DecimalUtils.getValueAsString(factorDto.getLevelN().getLvalue()));
                condition.setLevelNo(factorDto.getLevelN().getLevelNPK().getLevelno());
            } else {
                condition.setValue("");
            }
        } else {
            condition.setValue(unknown);
        }

//      Asignando los label
        Factor tempLabel = (Factor) mapLabels.get(factorDto.getFactorid());
        if (tempLabel != null) {
            condition.setLabel(tempLabel.getFname());
        } else {
            condition.setLabel(notFound);
        }
        return condition;
    }

    public static ibfb.domain.core.Factor getFactor(
            Factor factorDto,
//            boolean traitB,
            Map mapLabels) {
        ibfb.domain.core.Factor factor = new ibfb.domain.core.Factor();
        factor.setFactorName(factorDto.getFname());

        // assign from database
        factor.setLabelId(factorDto.getLabelid());
        factor.setFactorId(factorDto.getFactorid());
        
        if (factorDto.getMeasuredin() != null) {
            if(factorDto.getMeasuredin().getTraits() != null){
                factor.setProperty(factorDto.getMeasuredin().getTraits().getTrname());
            }else{
                factor.setProperty(null);
            }
            if (factorDto.getDmsattr() != null) {
                factor.setDescription(factorDto.getDmsattr().getDmsatval());
            }
            if (factorDto.getMeasuredin().getTmsMethod() != null) {
                factor.setMethod(factorDto.getMeasuredin().getTmsMethod().getTmname());
            } else {
                factor.setMethod(null);
            }
            if (factorDto.getMeasuredin().getScales() != null) {
                factor.setScale(factorDto.getMeasuredin().getScales().getScname());
            } else {
                factor.setScale("--@--");
            }
        } else {
            factor.setProperty(null);
            factor.setDescription(null);
        }
        
        factor.setDataType(factorDto.getLtype());

//      Asignando los value
//        if(factorDto.getLtype().equals("C") ){
//            factor.setValue(factorDto.getLevelC().getLvalue());
//        }else if(factorDto.getLtype().equals("N") ){
//            factor.setValue(factorDto.getLevelN().getLvalue());
//        }

//      Asignando los label
        Factor tempLabel = (Factor) mapLabels.get(factorDto.getFactorid());
        factor.setLabel(tempLabel.getFname());
        return factor;
    }

    public static ibfb.domain.core.Constant getConstant(
            Variate variateDto
//            , boolean traitB
            ) {
        ibfb.domain.core.Constant constant = new ibfb.domain.core.Constant();
        // assign ids from database

        constant.setConstantName(variateDto.getVname());

        if (variateDto.getMeasuredin() != null) {
            if(variateDto.getMeasuredin().getTraits() != null){
                constant.setProperty(variateDto.getMeasuredin().getTraits().getTrname());
            }else{
                constant.setProperty("");
            }
            if (variateDto.getDmsattr() != null) {
                constant.setDescription(variateDto.getDmsattr().getDmsatval());
            }else{
                constant.setDescription("");
            }
            if (variateDto.getMeasuredin().getTmsMethod() != null) {
                constant.setMethod(variateDto.getMeasuredin().getTmsMethod().getTmname());
            } else {
                constant.setMethod(null);
            }
            Scales scales = variateDto.getMeasuredin().getScales();
            if (scales == null) {
                constant.setScale("--@--");
            } else {
                constant.setScale(scales.getScname());
            }
        } else {
            constant.setProperty(null);
            constant.setDescription(null);
            constant.setMethod(null);
            constant.setScale(null);
        }
        constant.setDataType(variateDto.getDtype());
//      constant.setValue(value);
//      constant.setLabel(label); lvalue de levelc o n
        //con lavels para clasificar los trials
        return constant;
    }

    public static ibfb.domain.core.Variate getVariate(
            Variate variateDto
//            , boolean traitB
            ) {
        ibfb.domain.core.Variate variate = new ibfb.domain.core.Variate();
        variate.setVariateName(variateDto.getVname());
        variate.setVariateId(variateDto.getVariatid());

            if (variateDto.getMeasuredin() != null) {
                if(variateDto.getMeasuredin().getTraits() != null){
                    variate.setProperty(variateDto.getMeasuredin().getTraits().getTrname());
                }else{
                    variate.setProperty("");
                }
                if (variateDto.getDmsattr() != null) {
                    variate.setDescription(variateDto.getDmsattr().getDmsatval());
                }else{
                    variate.setDescription("");
                }
                if (variateDto.getMeasuredin().getTmsMethod() != null) {
                    variate.setMethod(variateDto.getMeasuredin().getTmsMethod().getTmname());
                } else {
                    variate.setMethod("");
                }
                Scales scales = variateDto.getMeasuredin().getScales();
                if (scales == null) {
                    variate.setScale("--@--");
                } else {
                    variate.setScale(scales.getScname());
                }
            } else {
                variate.setProperty(null);
                variate.setDescription(null);
                variate.setMethod(null);
                variate.setScale(null);
            }
        variate.setDataType(variateDto.getDtype());
        

        return variate;
    }
}
