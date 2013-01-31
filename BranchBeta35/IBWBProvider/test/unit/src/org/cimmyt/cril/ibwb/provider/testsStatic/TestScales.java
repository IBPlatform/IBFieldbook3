/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.testsStatic;

import java.util.List;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.domain.Scale;
import org.cimmyt.cril.ibwb.domain.Scales;

/**
 *
 * @author MasterGama
 */
public class TestScales extends TestService {

    private static Logger log = Logger.getLogger(TestScales.class);

    public void testScalesAdd() {
        Scales scales = new Scales();
//        TODO agregar definicion de acceso a informacion
//        servicios.addTraits(traits);
    }

    public void testScalesList() {
        System.out.println("List Scales's");
        for (Scales scales : servicios.getScalesList()) {
            System.out.println(scales.getScname());
        }
        System.out.println("End list Scales's");
    }

    public void testScalesLocalYRemoto() {
        System.out.println("-------------List Scales's Local and Remote");
        Scales scalesVacio = new Scales(true);
        List<Scales> scaless = servicios.getListScales(scalesVacio, 0, 0, false);
        System.out.println("Tamaño del listado: " + scaless.size());
        for (Scales scales : scaless) {
            System.out.println("\t" + scales.getScname());
        }
        System.out.println("-------------End list Scales's Local and Remote");
    }
    
    public void testScaleGroups() {
        List<Scale> scaless = servicios.getListScaleGroupsCentral();
        for (Scale scale: scaless) {
            System.out.println("\t" + scale.getScname());
        }
    }

    public static void main(String[] args) {
        TestScales testScales = new TestScales();
//        testTraits.testTraitsAdd();
//        testTraits.testTraitsList();
       // testScales.testScalesLocalYRemoto();
        testScales.testScaleGroups();
    }
}