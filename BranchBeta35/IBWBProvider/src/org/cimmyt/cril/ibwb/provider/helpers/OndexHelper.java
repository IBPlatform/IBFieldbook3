/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.helpers;

import java.util.HashMap;
import java.util.Map;
import org.cimmyt.cril.ibwb.domain.LevelN;

/**
 *
 * @author TMSANCHEZ
 */
public class OndexHelper {
    
    /**
     * To store FACTORID and LEVELNO for study
     */
    private Map<Integer,Integer> studyMapLevel = new HashMap<Integer, Integer>();

    /**
     * To store TRIALNUMBER and LEVELNO for study
     */
    private Map<Integer,LevelN> trialMapLevel = new HashMap<Integer, LevelN>();
    
    /**
     * To store FACTORID and LEVELNO for study
     */    
    private Map<Integer,LevelN> entryMapLevel = new HashMap<Integer, LevelN>();
    
}
