/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.helpers;

import java.util.List;
import org.cimmyt.cril.ibwb.api.AppServices;
import org.cimmyt.cril.ibwb.api.CommonServices;
import org.cimmyt.cril.ibwb.domain.Measuredin;
import org.cimmyt.cril.ibwb.domain.Scales;
import org.cimmyt.cril.ibwb.domain.TmsMethod;

/**
 *
 * @author MasterGama
 */
public class HelperMeasuredin {
    
    public static void getMeasuredinFull(List<Measuredin> measuredinList, CommonServices servicios){
        Scales scalesFilter = new Scales(true);
        TmsMethod tmsMethod = new TmsMethod(true);
        for (Measuredin measuredin : measuredinList) {
            scalesFilter.setScaleid(measuredin.getScaleid());
            List<Scales> listScalesResult = servicios.getListScales(scalesFilter, 0, 0, false);
            if(! listScalesResult.isEmpty()){
                measuredin.setScales(listScalesResult.get(0));
            }
            tmsMethod.setTmethid(measuredin.getTmethid());
            List<TmsMethod> tmsMethods = servicios.getListTmsMethod(tmsMethod, 0, 0, false);
            if(! tmsMethods.isEmpty()){
                measuredin.setTmsMethod(tmsMethods.get(0));
            }
        }
    }
    
    public static void getMeasuredinFull(List<Measuredin> measuredinList, AppServices servicios){
        Scales scalesFilter = new Scales(true);
        TmsMethod tmsMethod = new TmsMethod(true);
        for (Measuredin measuredin : measuredinList) {
            scalesFilter.setScaleid(measuredin.getScaleid());
            List<Scales> listScalesResult = servicios.getListScales(scalesFilter, 0, 0, false);
            if(! listScalesResult.isEmpty()){
                measuredin.setScales(listScalesResult.get(0));
            }
            tmsMethod.setTmethid(measuredin.getTmethid());
            List<TmsMethod> tmsMethods = servicios.getListTmsMethod(tmsMethod, 0, 0, false);
            if(! tmsMethods.isEmpty()){
                measuredin.setTmsMethod(tmsMethods.get(0));
            }
        }
    }
    
    public static Measuredin getMeasuredinFull(Measuredin measuredin, AppServices servicios){
        Scales scalesFilter = new Scales(true);
        scalesFilter.setScaleid(measuredin.getScaleid());
        List<Scales> listScalesResult = servicios.getListScales(scalesFilter, 0, 0, false);
        if(! listScalesResult.isEmpty()){
            measuredin.setScales(listScalesResult.get(0));
        }
        TmsMethod tmsMethod = new TmsMethod(true);
        tmsMethod.setTmethid(measuredin.getTmethid());
        List<TmsMethod> tmsMethods = servicios.getListTmsMethod(tmsMethod, 0, 0, false);
        if(! tmsMethods.isEmpty()){
            measuredin.setTmsMethod(tmsMethods.get(0));
        }
        return measuredin;
    }
    
    public static Measuredin getMeasuredinFull(Measuredin measuredin, CommonServices servicios){
        Scales scalesFilter = new Scales(true);
        scalesFilter.setScaleid(measuredin.getScaleid());
        List<Scales> listScalesResult = servicios.getListScales(scalesFilter, 0, 0, false);
        if(! listScalesResult.isEmpty()){
            measuredin.setScales(listScalesResult.get(0));
        }
        TmsMethod tmsMethod = new TmsMethod(true);
        tmsMethod.setTmethid(measuredin.getTmethid());
        List<TmsMethod> tmsMethods = servicios.getListTmsMethod(tmsMethod, 0, 0, false);
        if(! tmsMethods.isEmpty()){
            measuredin.setTmsMethod(tmsMethods.get(0));
        }
        return measuredin;
    }
}
