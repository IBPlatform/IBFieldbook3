/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.commongui;

import java.awt.Desktop;
import java.net.URL;

/**
 *
 * @author TMSANCHEZ
 */
public class OntologyTool {
    public static final String ONTOLOGY_URL ="http://www.cropontology-curationtool.org/terms/";

    /**
     * Open Ontoloty browser
     * @param ontologyTerm 
     */
    public static void openOntology(String ontologyTerm) {
        try {
            URL url = new URL( ONTOLOGY_URL+ ontologyTerm + "/");
            Desktop.getDesktop().browse(url.toURI());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}