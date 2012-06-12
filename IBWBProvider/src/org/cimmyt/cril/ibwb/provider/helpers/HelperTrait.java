/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.helpers;

import java.util.List;
import org.cimmyt.cril.ibwb.api.AppServices;
import org.cimmyt.cril.ibwb.api.CommonServices;
import org.cimmyt.cril.ibwb.domain.Scale;
import org.cimmyt.cril.ibwb.domain.Trait;

/**
 *
 * @author MasterGama
 */
public class HelperTrait {
    
    public static void getTraitFull(List<Trait> traitList, CommonServices servicios){
        Scale scaleFilter = new Scale(true);
        for (Trait trait : traitList) {
            getTraitFull(trait, servicios);
            
            scaleFilter.setTraitid(trait.getTraitid());
            List<Scale> listScaleResult = servicios.getListScale(scaleFilter, 0, 0, false);
            if(! listScaleResult.isEmpty()){
                trait.setScale(listScaleResult.get(0));
            }
            
        }
    }
    
    public static void getTraitFull(List<Trait> traitList, AppServices servicios){
        Scale scaleFilter = new Scale(true);
        for (Trait trait : traitList) {
            scaleFilter.setTraitid(trait.getTraitid());
            List<Scale> listScaleResult = servicios.getListScale(scaleFilter, 0, 0, false);
            if(! listScaleResult.isEmpty()){
                trait.setScale(listScaleResult.get(0));
            }
        }
    }
    
    public static Trait getTraitFull(Trait trait, AppServices servicios){
        Scale scaleFilter = new Scale(true);
        scaleFilter.setTraitid(trait.getTraitid());
        List<Scale> listScaleResult = servicios.getListScale(scaleFilter, 0, 0, false);
        if(listScaleResult.size() == 1){
            trait.setScale(listScaleResult.get(0));
        }
        return trait;
    }
    
    public static Trait getTraitFull(Trait trait, CommonServices servicios){
        Scale scaleFilter = new Scale(true);
        scaleFilter.setTraitid(trait.getTraitid());
        List<Scale> listScaleResult = servicios.getListScale(scaleFilter, 0, 0, false);
        if(! listScaleResult.isEmpty()){
            trait.setScale(listScaleResult.get(0));
        }
        return trait;
    }
}
