/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cimmyt.cril.ibwb.provider.testsStatic;

import java.util.List;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.domain.Measuredin;
import org.cimmyt.cril.ibwb.domain.Transformations;
import org.cimmyt.cril.ibwb.provider.helpers.HelperTransformations;
import org.cimmyt.cril.ibwb.provider.utils.DecimalUtils;


/**
 *
 * @author MasterGama
 */
public class TestTransformations extends TestService {
    
    private static Logger log = Logger.getLogger(TestTransformations.class);
    
    public void testMeasuredinList() {
        System.out.println("List Measuredin's");
        for (Measuredin measuredin : servicios.getMeasuredinList()) {
            System.out.println(measuredin.getFormula());
        }
        System.out.println("End list Measuredin's");
    }
    
    public void testTransformationLocalYRemoto() {
        System.out.println("-------------List Measuredin's Local and Remote");
        HelperTransformations helperTransformations = new HelperTransformations(servicios, null, null);
        List <Measuredin> measuredins = servicios.getMeasuredinList();
        System.out.println("Tamaño del listado: " + measuredins.size());
        for (Measuredin measuredin : measuredins){
            if(measuredin.getFormula() == null){
                System.out.println("\t" + measuredin.getMeasuredinid() + " Formula NULL ");
            }else if(DecimalUtils.isDecimal(measuredin.getFormula())){
                Transformations transformations = helperTransformations.getByTraididScaleidMethodid(measuredin.getTraitid(), measuredin.getScaleid(), measuredin.getTmethid());
                System.out.println("\t" + measuredin.getMeasuredinid() + " Transformation Type: " + transformations.getTranstype());
            }else{
                System.out.println("\t" + measuredin.getMeasuredinid() + " Formula Incompatible ");
            }
        }
        System.out.println("-------------End list TestTransformation's Local and Remote");
    }
    
    public static void main(String[] args) {
        TestTransformations testTransformations = new TestTransformations();
        //testTransformations.testMeasuredinList();
        testTransformations.testTransformationLocalYRemoto();
        
    }
}