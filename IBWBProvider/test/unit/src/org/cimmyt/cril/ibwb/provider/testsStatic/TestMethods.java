/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.testsStatic;

import java.util.List;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.domain.Methods;

/**
 *
 * @author TMSANCHEZ
 */
public class TestMethods extends TestService {

    private static Logger log = Logger.getLogger(TestMethods.class);

    public void listAllMethods() {
        List<Methods> methodsList = servicios.getMethodsList();
        for (Methods methods : methodsList) {
            log.info(methods.toString());
        }
    }

    public static void main(String[] args) {
        TestMethods testMethods =  new TestMethods();
        testMethods.listAllMethods();
    }
}
