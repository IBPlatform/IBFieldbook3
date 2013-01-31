/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cimmyt.cril.ibwb.provider.testsStatic;

import java.util.List;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.domain.Scale;


/**
 *
 * @author MasterGama
 */
public class TestScale extends TestService {
    
	private static Logger log = Logger.getLogger(TestScales.class);
    
	public void testScaleAdd() {
        Scale scale = new Scale();
//        TODO agregar definicion de acceso a informacion
//        servicios.addTraits(traits);
    }
    
    public void testScalesList() {
        System.out.println("List Scales's");
        for (Scale scale : servicios.getScaleList()) {
            System.out.println(scale.getScname());
        }
        System.out.println("End list Scale's");
    }
    
    public void testScaleLocalYRemoto() {
        System.out.println("-------------List Scale's Local and Remote");
        Scale scaleVacio = new Scale(true);
        List <Scale> scales = servicios.getListScale(scaleVacio, 0, 0, false);
        System.out.println("Tamaño del listado: " + scales.size());
        for (Scale scale : scales) {
            System.out.println("\t" + scale.getScname());
        }
        System.out.println("-------------End list Scale's Local and Remote");
    }
    
    public void testScaleGroup (){
        System.out.println("-------------List Scale Groups");
        List <Scale> scales = servicios.getListScaleGroupsCentral();
        System.out.println("Tamaño del listado: " + scales.size());
        for (Scale scale : scales) {
            System.out.println("\t scname: " + scale.getScname() + " sctype: " + scale.getSctype());
        }
        System.out.println("-------------End List Scale Groups");
    }
	
    public static void main(String[] args) {
        TestScale testScale = new TestScale();
//        testTraits.testTraitsAdd();
//        testTraits.testTraitsList();
//        testScale.testScaleLocalYRemoto();
//        testScale.testScaleGroup();
    }
}