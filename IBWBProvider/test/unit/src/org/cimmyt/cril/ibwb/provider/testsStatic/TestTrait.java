/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.testsStatic;

import java.util.List;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.domain.Trait;

/**
 *
 * @author TMSANCHEZ
 */
public class TestTrait extends TestService {

    private static Logger log = Logger.getLogger(TestTrait.class);

    public void testListTraits() {
        Trait traitFilter = new Trait(true);
//        traitFilter.setTraitGroup("Agronomic");
        List<Trait> traitList = servicios.getListTrait(traitFilter, 0, 0, false);
        for (Trait trait : traitList) {
            if(trait.getScale() == null){
                log.info("====================================================\n\n");
                log.info(trait.getTid() + " - " + trait.getTrname() + "\t" + "NULL" + "\n\n");
                log.info("====================================================");
            }else{
                log.info(trait.getTid() + trait.getTrname() + "\t" + trait.getScale().getScname());
            }
        }
    }
    
    public void testTraitGroups() {
        for (String group: servicios.getTraitGroups()) {
            log.info(group);
        }
    }

    public static void main(String[] args) {
        TestTrait tt = new TestTrait();
        tt.testListTraits();
        //tt.testTraitGroups();
    }
}
