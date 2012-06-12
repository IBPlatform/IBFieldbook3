/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cimmyt.cril.ibwb.provider.testsStatic;

import java.util.List;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.domain.Traits;


/**
 *
 * @author MasterGama
 */
public class TestTraits extends TestService {
    
	private static Logger log = Logger.getLogger(TestTraits.class);
    
	public void testTraitsAdd() {
        Traits traits = new Traits();
//        TODO agregar definicion de acceso a informacion
//        servicios.addTraits(traits);
    }
    
    public void testTraitsList() {
        System.out.println("List Traits's");
        for (Traits traits : servicios.getTraitsList()) {
            System.out.println(traits.getTrname());
        }
        System.out.println("End list Traits's");
    }
    
    public void testTraitsLocalYRemoto() {
        System.out.println("-------------List Traits's Local and Remote");
        Traits traitsVacio = new Traits(true);
        List <Traits> traitss = servicios.getListTraits(traitsVacio, 0, 0, false);
        System.out.println("Tamaño del listado: " + traitss.size());
        for (Traits traits : traitss) {
            if(traits.getMeasuredin() != null){
                System.out.println("\t traits: " + traits.getTrname() + " measuredin: " + traits.getMeasuredin().getTraitid() + " scales: " + traits.getMeasuredin().getScales().getScname());
            }else{
                System.out.println("\t traits sin measuredin: " + traits.getTid() + " name: " + traits.getTrname());
            }
            
        }
        System.out.println("-------------End list Traits's Local and Remote");
    }
    
    public static void main(String[] args) {
        TestTraits testTraits = new TestTraits();
//        testTraits.testTraitsAdd();
//        testTraits.testTraitsList();
        testTraits.testTraitsLocalYRemoto();
    }
}