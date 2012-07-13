/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.testsStatic;

import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.domain.Names;

/**
 *
 * @author TMSANCHEZ
 */
public class TestNames extends TestService {
    private static Logger log = Logger.getLogger(TestNames.class);
    
    public void getCimmytWheatName() {
        Integer gidToSearch  = 315904;
        Names cimmytName = servicios.getCimmytWheatName(gidToSearch);
        log.info("Nval for " + gidToSearch + " = " + cimmytName.getNval());
    }
    
    public static void main(String[] args) {
        TestNames testNames =  new TestNames();
        testNames.getCimmytWheatName();
    }
}
