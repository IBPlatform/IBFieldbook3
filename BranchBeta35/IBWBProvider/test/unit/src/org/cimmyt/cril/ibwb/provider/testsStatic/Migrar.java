/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.testsStatic;

import java.util.List;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.domain.Measuredin;
import org.cimmyt.cril.ibwb.domain.Scale;
import org.cimmyt.cril.ibwb.domain.Scales;
import org.cimmyt.cril.ibwb.domain.Trait;
import org.cimmyt.cril.ibwb.domain.Traits;

/**
 *
 * @author MasterGama
 */
public class Migrar extends TestService {
    
    private static Logger log = Logger.getLogger(Migrar.class);
    
    public void insertScaleGroupToScalesLocal (){
        
        System.out.println("-------------List Scale Groups Local");
        List <Scale> listScaleCatalogLocal = this.servicios.getListScaleGroupsLocal();
        System.out.println("Tamaño del listado: " + listScaleCatalogLocal.size());
        for (Scale scale : listScaleCatalogLocal) {
            System.out.println("\t scname: " + scale.getScname() + " sctype: " + scale.getSctype());
        }
        System.out.println("-------------End List Scale Groups Local");
        
        for (Scale scale : listScaleCatalogLocal) {
            Scales tempScales = new Scales();
            
            tempScales.setScaleid(scale.getScaleid());
            tempScales.setScname(scale.getScname());
            tempScales.setSctype(scale.getSctype());
            
            this.servicios.addScalesLocal(tempScales);
        }
        
        List <Scales> listScalesCatalog = this.servicios.getScalesList();
        System.out.println("-------------List Scales Catalog");
        for (Scales scales : listScalesCatalog) {
            System.out.println("\t scname: " + scales.getScname() + " sctype: " + scales.getSctype());
        }
        System.out.println("-------------End List Scales Catalog");
    }
    
    public void insertScaleGroupToScalesCentral (){
        
        System.out.println("-------------List Scale Groups Local");
        List <Scale> listScaleCatalogLocal = this.servicios.getListScaleGroupsLocal();
        System.out.println("Tamaño del listado: " + listScaleCatalogLocal.size());
        for (Scale scale : listScaleCatalogLocal) {
            System.out.println("\t scname: " + scale.getScname() + " sctype: " + scale.getSctype());
        }
        System.out.println("-------------End List Scale Groups Local");
        
        for (Scale scale : listScaleCatalogLocal) {
            Scales tempScales = new Scales();
            
            tempScales.setScaleid(scale.getScaleid());
            tempScales.setScname(scale.getScname());
            tempScales.setSctype(scale.getSctype());
            
            this.servicios.addScalesLocal(tempScales);
        }
        
        System.out.println("-------------List Scale Groups Central");
        List <Scale> listScaleCatalogCentral = this.servicios.getListScaleGroupsCentral();
        System.out.println("Tamaño del listado: " + listScaleCatalogCentral.size());
        for (Scale scale : listScaleCatalogCentral) {
            System.out.println("\t scname: " + scale.getScname() + " sctype: " + scale.getSctype());
        }
        System.out.println("-------------End List Scale Groups Central");
        
        for (Scale scale : listScaleCatalogCentral) {
            Scales tempScales = new Scales();
            
            tempScales.setScaleid(scale.getScaleid());
            tempScales.setScname(scale.getScname());
            tempScales.setSctype(scale.getSctype());
            
            this.servicios.addScalesCentral(tempScales);
        }
        
        List <Scales> listScalesCatalog = this.servicios.getScalesList();
        System.out.println("-------------List Scales Catalog");
        for (Scales scales : listScalesCatalog) {
            System.out.println("\t scname: " + scales.getScname() + " sctype: " + scales.getSctype());
        }
        System.out.println("-------------End List Scales Catalog");
    }
    
    public void llenarRelacionDeDependenciasMeasuredin(){
        List<Trait> listTraitLocal = this.servicios.getListTraitLocal(new Trait(true), 0, 0, false);
        for(Trait trait: listTraitLocal){
            
            //Definicion de objetos necesarios
            Traits traits = new Traits();
            Measuredin measuredin = new Measuredin();
            Scales scales = new Scales ();
            
            Traits traitsFilter = new Traits(true);
            traitsFilter.setTraitid(trait.getTraitid());
            List<Traits> listTraitsTemp = this.servicios.getListTraitsLocalFull(traitsFilter, 0, 0, false);
            if(listTraitsTemp.size()==1){
                traits = listTraitsTemp.get(0);
            }
            
            //Casting entre objetos
            measuredin.setTraitid(traits.getTraitid());
            Scales scalesFilter = new Scales(true);
            scalesFilter.setScname(trait.getScale().getScname());
            scalesFilter.setSctype(trait.getScale().getSctype());
            List <Scales> listScalesTemp = this.servicios.getListScalesLocal(scalesFilter, 0, 0, false);
            if(listScalesTemp.size()==1){
                scales = listScalesTemp.get(0);
            }else if(listScalesTemp.size()>1){
                scales = listScalesTemp.get(0);
            }
            measuredin.setScales(scales);
            measuredin.setTraitid(traits.getTid());
            measuredin.setStandardscale(scales.getScaleid().toString());
            
            this.servicios.addMeasuredinLocal(measuredin);
        }
        
        List<Trait> listTraitCentral = this.servicios.getListTraitCentral(new Trait(true), 0, 0, false);
        for(Trait trait: listTraitCentral){
            
            //Definicion de objetos necesarios
            Traits traits = new Traits();
            Measuredin measuredin = new Measuredin();
            Scales scales = new Scales ();
            
            Traits traitsFilter = new Traits(true);
            traitsFilter.setTraitid(trait.getTraitid());
            List<Traits> listTraitsTemp = this.servicios.getListTraitsCentralFull(traitsFilter, 0, 0, false);
            if(listTraitsTemp.size()==1){
                traits = listTraitsTemp.get(0);
            }
            
            //Casting entre objetos
            measuredin.setTraitid(traits.getTraitid());
            Scales scalesFilter = new Scales(true);
            scalesFilter.setScname(trait.getScale().getScname());
            scalesFilter.setSctype(trait.getScale().getSctype());
            List <Scales> listScalesTemp = this.servicios.getListScalesCentral(scalesFilter, 0, 0, false);
            if(listScalesTemp.size()==1){
                scales = listScalesTemp.get(0);
            }else if(listScalesTemp.size()>1){
                scales = listScalesTemp.get(0);
            }
            measuredin.setScales(scales);
            measuredin.setTraitid(traits.getTid());
            measuredin.setStandardscale(scales.getScaleid().toString());
            
            this.servicios.addMeasuredinCentral(measuredin);
        }
        
        for(Measuredin measuredin: this.servicios.getListMeasuredin(new Measuredin(true), 0, 0, false)){
            log.info("\t traitid: " + measuredin.getTraitid() + "scaleid: " + measuredin);
        }
    }
    
    public static void main(String[] args) {
        Migrar migrar = new Migrar();
//        migrar.insertScaleGroupToScales();
        migrar.llenarRelacionDeDependenciasMeasuredin();
    }
}
