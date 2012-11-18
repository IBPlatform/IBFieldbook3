/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cimmyt.cril.ibwb.provider.testsStatic;

import java.util.List;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.domain.Measuredin;


/**
 *
 * @author MasterGama
 */
public class TestMeasuredin extends TestService {
    
    private static Logger log = Logger.getLogger(TestMeasuredin.class);
    
    public void testMeasuredinAdd() {
        Measuredin measuredin = new Measuredin();
        //TODO agregar definicion de acceso a informacion
        //servicios.addTraits(traits);
    }
    
    public void testMeasuredinList() {
        System.out.println("List Measuredin's");
        for (Measuredin measuredin : servicios.getMeasuredinList()) {
            System.out.println(measuredin.getFormula());
        }
        System.out.println("End list Measuredin's");
    }
    
    public void testMeasuredinLocalYRemoto() {
        System.out.println("-------------List Measuredin's Local and Remote");
        Measuredin measuredinVacio = new Measuredin(true);
        List <Measuredin> measuredins = servicios.getListMeasuredin(measuredinVacio, 0, 0, false);
        measuredins.addAll(servicios.getListMeasuredin(measuredinVacio, 0, 0, false));
        System.out.println("Tamaño del listado: " + measuredins.size());
        for (Measuredin measuredin : measuredins){
            if(measuredin.getScales() == null){
                System.out.println("\t" + "simple traitid: " + measuredin.getTraitid());
            }else{
                System.out.println("\t" + "complete traitid: " + measuredin.getTraitid() + " scale: " + measuredin.getScales().getScaleid());
            }
        }
        System.out.println("-------------End list Measuredin's Local and Remote");
    }
    
    public void eliminarDuplicados() {
//        Measuredin measuredinVacio = new Measuredin(true);
//        List <Measuredin> measuredins = servicios.getListMeasuredin(measuredinVacio, 0, 0, false);
//        for (Measuredin measuredin : measuredins) {
//            Scales scales = new Scales(true);
//            scales.setScaleid(measuredin.getScales().getScaleid());
//            List<Scales > scalestemp = servicios.getListScales(scales, 0, 0, false);
//            if(scalestemp.size()>1){
//                for(int i=1; i<scalestemp.size(); i++){
//                    servicios.de
//                }
//            }
//        }
    }
    
    public static void main(String[] args) {
        TestMeasuredin testMeasuredin = new TestMeasuredin();
//        testMeasuredin.testMeasuredinLocalYRemoto();
        testMeasuredin.testMeasuredinList();
        
    }
}