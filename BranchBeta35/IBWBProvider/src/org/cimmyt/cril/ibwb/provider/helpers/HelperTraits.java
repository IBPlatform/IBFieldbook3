/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.helpers;

import java.util.List;
import org.cimmyt.cril.ibwb.api.AppServices;
import org.cimmyt.cril.ibwb.api.CommonServices;
import org.cimmyt.cril.ibwb.domain.Measuredin;
import org.cimmyt.cril.ibwb.domain.Traits;

/**
 *
 * @author MasterGama
 */
public class HelperTraits {
    
    public static void getTraitsFull(List<Traits> traitsList, CommonServices servicios){
        Measuredin measuredinT = new Measuredin(true);
        for (Traits traits : traitsList) {
            measuredinT.setTraitid(traits.getTid());
            List<Measuredin> listMeasuredinResult = servicios.getListMeasuredin(measuredinT, 0, 0, false);
            if(! listMeasuredinResult.isEmpty() && listMeasuredinResult.size() == 1){
                traits.setMeasuredin(HelperMeasuredin.getMeasuredinFull(listMeasuredinResult.get(0), servicios) );
            }else if(! listMeasuredinResult.isEmpty() && listMeasuredinResult.size() > 1){
                for(Measuredin measuredin : listMeasuredinResult){
                    if(measuredin.getScales().getScaleid() == Integer.valueOf(measuredin.getStandardscale())){
                        traits.setMeasuredin(HelperMeasuredin.getMeasuredinFull(measuredin, servicios));
                        break;
                    }
                }
            }
            HelperMeasuredin.getMeasuredinFull(listMeasuredinResult, servicios);
            traits.setMeasuredins(listMeasuredinResult);
        }
    }
    
    public static void getTraitsFull(List<Traits> traitsList, AppServices servicios){
        Measuredin measuredinT = new Measuredin(true);
        for (Traits traits : traitsList) {
            measuredinT.setTraitid(traits.getTid());
            List<Measuredin> listMeasuredinResult = servicios.getListMeasuredin(measuredinT, 0, 0, false);
            if(! listMeasuredinResult.isEmpty() && listMeasuredinResult.size() == 1){
                traits.setMeasuredin(HelperMeasuredin.getMeasuredinFull(listMeasuredinResult.get(0), servicios) );
            }else if(! listMeasuredinResult.isEmpty() && listMeasuredinResult.size() > 1){
                for(Measuredin measuredin : listMeasuredinResult){
                    if(measuredin.getScales() != null && measuredin.getScales().getScaleid() == Integer.valueOf(measuredin.getStandardscale())){
                        traits.setMeasuredin(HelperMeasuredin.getMeasuredinFull(measuredin, servicios));
                        break;
                    }
                }
            }
            HelperMeasuredin.getMeasuredinFull(listMeasuredinResult, servicios);
            traits.setMeasuredins( listMeasuredinResult);
        }
    }
    
    public static Traits getTraitsFull(Traits traits, AppServices servicios){
        Measuredin measuredinT = new Measuredin(true);
        measuredinT.setTraitid(traits.getTid());
        //measuredinT.setTraitid(traits.getTraitid());
        List<Measuredin> listMeasuredinResult = servicios.getListMeasuredin(measuredinT, 0, 0, false);
        if(! listMeasuredinResult.isEmpty() && listMeasuredinResult.size() == 1){
            traits.setMeasuredin(HelperMeasuredin.getMeasuredinFull(listMeasuredinResult.get(0), servicios));
        }else if(! listMeasuredinResult.isEmpty() && listMeasuredinResult.size() > 1){
            for(Measuredin measuredin : listMeasuredinResult){
                measuredin = HelperMeasuredin.getMeasuredinFull(measuredin, servicios);
                if(measuredin.getScales().getScaleid().equals(Integer.valueOf(measuredin.getStandardscale()))){
                    traits.setMeasuredin(measuredin);
                    break;
                }
            }
        }
        traits.setMeasuredins(listMeasuredinResult);
        return traits;
    }
    
    public static Traits getTraitsFull(Traits traits, CommonServices servicios){
        Measuredin measuredinT = new Measuredin(true);
        measuredinT.setTraitid(traits.getTid());
        List<Measuredin> listMeasuredinResult = servicios.getListMeasuredin(measuredinT, 0, 0, false);
        if(! listMeasuredinResult.isEmpty() && listMeasuredinResult.size() == 1){
            traits.setMeasuredin(HelperMeasuredin.getMeasuredinFull(listMeasuredinResult.get(0), servicios));
        }else if(! listMeasuredinResult.isEmpty() && listMeasuredinResult.size() > 1){
            for(Measuredin measuredin : listMeasuredinResult){
                measuredin = HelperMeasuredin.getMeasuredinFull(measuredin, servicios);
                if(measuredin.getScales().getScaleid().equals(Integer.valueOf(measuredin.getStandardscale()))){
                    traits.setMeasuredin(measuredin);
                    break;
                }
            }
        }
        traits.setMeasuredins(listMeasuredinResult);
        return traits;
    }
}