/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.helpers;

import ibfb.domain.core.Workbook;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.AppServices;
import org.cimmyt.cril.ibwb.domain.Effect;
import org.cimmyt.cril.ibwb.domain.Factor;

/**
 *
 * @author gamaliel
 */
public class HelperEffect {

    private static Logger log = Logger.getLogger(HelperEffect.class);
    
    public static Integer getEffectidForMeasurementEffect(
            AppServices servicios,
            Integer studyId,
            Factor factorStudy,
            Factor factorTrial,
            Factor factorEntry,
            Factor factorPlot
            ){
        
        List<Integer> effectsIds = servicios.getEffectidsByStudyid(studyId);
        List<Effect> effects = servicios.getEffectsByEffectsids(effectsIds);
        imprimeEffects(effects);
        
        Map<Integer, Factor> mapMeasurementEffect = new HashMap<Integer, Factor>();
        Map<Integer, Factor> mapAllMainFactors = new HashMap<Integer, Factor>();
        
        if(validarFactores(factorStudy, factorTrial, factorEntry, factorPlot)){

            List<Factor> factors = servicios.getMainFactorsByStudyid(studyId);
            for(Factor factor : factors){
                factor = HelperFactor.getFactorFillingFullWhitoutLevels(factor, servicios, 801);
                mapAllMainFactors.put(factor.getFactorid(), factor);
                String traitScale = factor.getMeasuredin().getTraits().getTrname() + factor.getMeasuredin().getScales().getScname();
                if( Workbook.STUDY_NAME.equals( Workbook.getStringWithOutBlanks( traitScale ) ) ){
                    factorStudy = factor;
                }else if( Workbook.TRIAL_INSTANCE_NUMBER.equals( Workbook.getStringWithOutBlanks( traitScale ) ) ){
                    factorTrial = factor;
                }else if( Workbook.GERMPLASM_ENTRY_NUMBER.equals( Workbook.getStringWithOutBlanks( traitScale ) ) ){
                    factorEntry = factor;
                }else if( Workbook.FIELD_PLOT_NUMBER.equals( Workbook.getStringWithOutBlanks( traitScale ) ) ||
                        Workbook.FIELD_PLOT_NESTEDNUMBER.equals( Workbook.getStringWithOutBlanks( traitScale ) )){
                    factorPlot = factor;
                }
            }
        }
        
        
        if(factorStudy != null){
            mapMeasurementEffect.put(factorStudy.getLabelid(), factorStudy);
        }else{
            log.error("Factor study not found.");
        }
        if(factorTrial != null){
            mapMeasurementEffect.put(factorTrial.getLabelid(), factorTrial);
        }else{
            log.error("Factor trial not found.");
        }
        if(factorEntry != null){
            mapMeasurementEffect.put(factorEntry.getLabelid(), factorEntry);
        }else{
            log.error("Factor entry not found.");
        }
        if(factorPlot != null){
            mapMeasurementEffect.put(factorPlot.getLabelid(), factorPlot);
        }else{
            log.error("Factor plot not found.");
        }
        
        
        Boolean bStudy = Boolean.FALSE;
        Boolean bTrial = Boolean.FALSE;
        Boolean bEntry = Boolean.FALSE;
        Boolean bPlot = Boolean.FALSE;
        
        Integer effectId = 0;
        
        for(Effect effect : effects){
            if(! effectId.equals(effect.getEffectPK().getEffectid())){
                effectId = effect.getEffectPK().getEffectid();
                limpiarBooleans(bStudy, bTrial, bEntry, bPlot);
            }
            if(mapMeasurementEffect.get(effect.getEffectPK().getFactorid()) != null){
                if(factorStudy.getLabelid().equals(effect.getEffectPK().getFactorid())){
                    bStudy = Boolean.TRUE;
                }else if (factorTrial.getLabelid().equals(effect.getEffectPK().getFactorid())){
                    bTrial = Boolean.TRUE;
                }else if (factorEntry.getLabelid().equals(effect.getEffectPK().getFactorid())){
                    bEntry = Boolean.TRUE;
                }else if (factorPlot.getLabelid().equals(effect.getEffectPK().getFactorid())){
                    bPlot = Boolean.TRUE;
                }
            }
            if(encontrados(bStudy, bTrial, bEntry, bPlot)){
                break;
            }
        }
        if (effectId.equals(0)){
            return null;
        }else{
            return effectId;
        }
    }
    
    public static HelperContentEffectidAndFactors getEffectidForMeasurementEffectAndFactors(
            AppServices servicios,
            Integer studyId,
            Factor factorStudy,
            Factor factorTrial,
            Factor factorEntry,
            Factor factorPlot
            ){
        
        List<Integer> effectsIds = servicios.getEffectidsByStudyid(studyId);
        List<Effect> effects = servicios.getEffectsByEffectsids(effectsIds);
        imprimeEffects(effects);
        
        Map<Integer, Factor> mapMeasurementEffect = new HashMap<Integer, Factor>();
        Map<Integer, Factor> mapAllMainFactors = new HashMap<Integer, Factor>();
        
        if(validarFactores(factorStudy, factorTrial, factorEntry, factorPlot)){

            List<Factor> factors = servicios.getMainFactorsByStudyid(studyId);
            for(Factor factor : factors){
                factor = HelperFactor.getFactorFillingFullWhitoutLevels(factor, servicios, 801);
                mapAllMainFactors.put(factor.getFactorid(), factor);
                String traitScale = factor.getMeasuredin().getTraits().getTrname() + factor.getMeasuredin().getScales().getScname();
                if( Workbook.STUDY_NAME.equals( Workbook.getStringWithOutBlanks( traitScale ) ) ){
                    factorStudy = factor;
                }else if( Workbook.TRIAL_INSTANCE_NUMBER.equals( Workbook.getStringWithOutBlanks( traitScale ) ) ){
                    factorTrial = factor;
                }else if( Workbook.GERMPLASM_ENTRY_NUMBER.equals( Workbook.getStringWithOutBlanks( traitScale ) ) ){
                    factorEntry = factor;
                }else if( Workbook.FIELD_PLOT_NUMBER.equals( Workbook.getStringWithOutBlanks( traitScale ) ) ||
                        Workbook.FIELD_PLOT_NESTEDNUMBER.equals( Workbook.getStringWithOutBlanks( traitScale ) )){
                    factorPlot = factor;
                }
            }
        }
        
        
        if(factorStudy != null){
            mapMeasurementEffect.put(factorStudy.getLabelid(), factorStudy);
        }else{
            log.error("Factor study not found.");
        }
        if(factorTrial != null){
            mapMeasurementEffect.put(factorTrial.getLabelid(), factorTrial);
        }else{
            log.error("Factor trial not found.");
        }
        if(factorEntry != null){
            mapMeasurementEffect.put(factorEntry.getLabelid(), factorEntry);
        }else{
            log.error("Factor entry not found.");
        }
        if(factorPlot != null){
            mapMeasurementEffect.put(factorPlot.getLabelid(), factorPlot);
        }else{
            log.error("Factor plot not found.");
        }
        
        
        Boolean bStudy = Boolean.FALSE;
        Boolean bTrial = Boolean.FALSE;
        Boolean bEntry = Boolean.FALSE;
        Boolean bPlot = Boolean.FALSE;
        
        Integer effectId = 0;
        
        for(Effect effect : effects){
            if(! effectId.equals(effect.getEffectPK().getEffectid())){
                effectId = effect.getEffectPK().getEffectid();
                limpiarBooleans(bStudy, bTrial, bEntry, bPlot);
            }
            if(mapMeasurementEffect.get(effect.getEffectPK().getFactorid()) != null){
                if(factorStudy.getLabelid().equals(effect.getEffectPK().getFactorid())){
                    bStudy = Boolean.TRUE;
                }else if (factorTrial.getLabelid().equals(effect.getEffectPK().getFactorid())){
                    bTrial = Boolean.TRUE;
                }else if (factorEntry.getLabelid().equals(effect.getEffectPK().getFactorid())){
                    bEntry = Boolean.TRUE;
                }else if (factorPlot.getLabelid().equals(effect.getEffectPK().getFactorid())){
                    bPlot = Boolean.TRUE;
                }
            }
            if(encontrados(bStudy, bTrial, bEntry, bPlot)){
                break;
            }
        }
        HelperContentEffectidAndFactors helperContentEffectidAndFactors = new HelperContentEffectidAndFactors();
        helperContentEffectidAndFactors.setFactorStudy(factorStudy);
        helperContentEffectidAndFactors.setFactorTrial(factorTrial);
        helperContentEffectidAndFactors.setFactorEntry(factorEntry);
        helperContentEffectidAndFactors.setFactorPlot(factorPlot);
        if (effectId.equals(0)){
            helperContentEffectidAndFactors.setEffectid(null);
        }else{
            helperContentEffectidAndFactors.setEffectid(effectId);
        }
        return helperContentEffectidAndFactors;
    }
    
    public static Integer getEffectidForTrialEffect(AppServices servicios, Integer studyId){
        List<Integer> effectsIds = servicios.getEffectidsByStudyid(studyId);
        
        
        Factor factorStudy = null;
        Factor factorTrial = null;
        
        Map<Integer, Factor> mapMeasurementEffect = new HashMap<Integer, Factor>();
        Map<Integer, Factor> mapAllMainFactors = new HashMap<Integer, Factor>();
        
        List<Factor> factors = servicios.getMainFactorsByStudyid(studyId);
        for(Factor factor : factors){
            factor = HelperFactor.getFactorFillingFullWhitoutLevels(factor, servicios, 801);
            mapAllMainFactors.put(factor.getFactorid(), factor);
            String traitScale = factor.getMeasuredin().getTraits().getTrname() + factor.getMeasuredin().getScales().getScname();
            if( Workbook.STUDY_NAME.equals( Workbook.getStringWithOutBlanks( traitScale ) ) ){
                factorStudy = factor;
            }else if( Workbook.TRIAL_INSTANCE_NUMBER.equals( Workbook.getStringWithOutBlanks( traitScale ) ) ){
                factorTrial = factor;
            }
        }
        
        
        if(factorStudy != null){
            mapMeasurementEffect.put(factorStudy.getLabelid(), factorStudy);
        }else{
            log.error("Factor study not found.");
        }
        if(factorTrial != null){
            mapMeasurementEffect.put(factorTrial.getLabelid(), factorTrial);
        }else{
            log.error("Factor trial not found.");
        }
        
        
        Boolean bStudy = Boolean.FALSE;
        Boolean bTrial = Boolean.FALSE;
        
        Integer effectId = 0;
        
        for(Integer effectIdTemp : effectsIds){
            List<Integer> effectIdsTemp = new ArrayList<Integer>();
            effectIdsTemp.add(effectIdTemp);
            List<Effect> effects = servicios.getEffectsByEffectsids(effectIdsTemp);
            imprimeEffects(effects);
            if(effects.size() == 2){
                for(Effect effect : effects){
                    if(! effectId.equals(effect.getEffectPK().getEffectid())){
                        effectId = effect.getEffectPK().getEffectid();
                        limpiarBooleans(bStudy, bTrial);
                    }
                    if(mapMeasurementEffect.get(effect.getEffectPK().getFactorid()) != null){
                        if(factorStudy.getLabelid().equals(effect.getEffectPK().getFactorid())){
                            bStudy = Boolean.TRUE;
                        }else if (factorTrial.getLabelid().equals(effect.getEffectPK().getFactorid())){
                            bTrial = Boolean.TRUE;
                        }
                    }
                }
            }
        }
        if (effectId.equals(0)){
            return null;
        }else{
            return effectId;
        }
    }
    
    private static void limpiarBooleans(Boolean bStudy, Boolean bTrial, Boolean bEntry, Boolean bPlot){
        bStudy = Boolean.FALSE;
        bTrial = Boolean.FALSE;
        bEntry = Boolean.FALSE;
        bPlot = Boolean.FALSE;
    }
    
    private static void limpiarBooleans(Boolean bStudy, Boolean bTrial){
        bStudy = Boolean.FALSE;
        bTrial = Boolean.FALSE;
    }
    
    private static boolean encontrados(Boolean bStudy, Boolean bTrial, Boolean bEntry, Boolean bPlot){
        if(bStudy.booleanValue()
                && bTrial.booleanValue()
                && bEntry.booleanValue()
                && bPlot.booleanValue()){
            return true;
        }else{
            return false;
        }
    }
    
    private static void imprimeEffects(List<Effect> effects){
        if(effects != null){
            for(Effect effect : effects){
                log.info("Effect vale : " + effect.getEffectPK().getEffectid() + " factorid: " + effect.getEffectPK().getFactorid());
            }
        }else{
            log.error("Effects vacio.");
        }
    }
    
    private static boolean validarFactores(
            Factor factorStudy,
            Factor factorTrial,
            Factor factorEntry,
            Factor factorPlot
            ){
        
        if(factorStudy == null || factorTrial == null || factorEntry == null ||
            factorPlot == null){
            return true;
        }else if(
                factorStudy.getLabelid().equals(0) || factorStudy.getLabelid().equals(null) ||
                factorTrial.getLabelid().equals(0) || factorTrial.getLabelid().equals(null) || 
                factorEntry.getLabelid().equals(0) || factorEntry.getLabelid().equals(null) || 
                factorPlot.getLabelid().equals(0) || factorPlot.getLabelid().equals(null)
                ){
            return true;
        }else{
            return false;
        }
    }
}
