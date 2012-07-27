/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.testsStatic;

import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.domain.Study;

/**
 *
 * @author gamaliel
 */
public class ExtraerStudys extends TestService{
    
    public static void main(String[] args) {
        ExtraerStudys extraerStudys = new ExtraerStudys();
        extraerStudys.extraerStudys();
    }
    
    public void extraerStudys(){
        List<Integer> studyIds = new ArrayList<Integer>();
        
        studyIds.add(40165);
        studyIds.add(40170);
        
        for(Integer studyId : studyIds){
            this.servicios.migrateWorkbook(studyId);
        }
    }
}
