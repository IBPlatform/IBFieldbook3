package org.cimmyt.cril.ibwb.provider.testsStatic;

import java.util.List;
import org.cimmyt.cril.ibwb.domain.Factor;
import org.cimmyt.cril.ibwb.domain.Study;

public class TestStudy extends TestService {

    public void testStudyAdd() {
        Study study = new Study();
//        study.setStudyid(-1);
        study.setSname("nombre del study");
//        study.setPmkey(4654);
        study.setTitle("titulo del estudio");
        study.setObjectiv("objetivo");
//        study.setInvestid(565);
//        study.setStype("t");
//        study.setSdate(6456);
//        study.setEdate(4555);
//        study.setUserid(56456);
//        study.setSstatus(45254);
//        study.setShierarchy(6345);
//        serviciosLocal.addStudy(study);
    }

    public void testStudyList() {
        System.out.println("List Study's");
        List<Study> studies = servicios.getStudyList();
        System.out.println("Total del registros: " + studies.size());
        for (Study study : studies) {
            printStudy(study);
        }
        System.out.println("End list Study's");
    }

    public void testStudyLocalYRemoto() {
        System.out.println("-------------List Study's Local and Remote");
        Study studyVacio = new Study(true);
        List<Study> studys = servicios.getListStudy(studyVacio, 0, 0, false);
        System.out.println("Tamaño del listado: " + studys.size());
        for (Study study : studys) {
            printStudy(study);
        }
        System.out.println("-------------End list Study's Local and Remote");
    }
    
    public void getStudyFactorsTrialsAndEntrys(Integer idStudy){
        Study study = servicios.getStudyFactorsTrialAndEntryFull(idStudy);
        printStudy(study);
    }
    
    public void getStudysOnlyTrial(){
        List<Study> studys = servicios.getStudysOnlyTrial();
        for(Study study : studys){
            printStudy(study);
        }
    }
    
    public void getTrialsByStudyid(Integer studyid){
        for(Integer trial : servicios.getTrialsByStudyid(studyid)){
            System.out.println("Instancia: " + trial);
        }
    }
    
    public void printStudy(Study study){
        if(study != null){
            System.out.println("\t-> " + study.getStudyid() + " " + study.getTitle());
            for(Factor factor : study.getFactors()){
                System.out.println("\t\t" + factor.getFname());
                System.out.println("\t\t" + factor.getStudyid());
                System.out.println("\t\t\t" + factor.getSizeLevels());
            }
        }else{
            System.out.println("\t\t\t ====== El estudio es nulo. ======");
        }
    }

    public static void main(String[] args) {
        TestStudy testServices = new TestStudy();
        //testServices.testStudyAdd();
        //testServices.testStudyList();
        //testServices.testStudyLocalYRemoto();
        //testServices.getStudyFactorsTrialsAndEntrys(Integer.SIZE);
        //testServices.getStudysOnlyTrial();
        testServices.getTrialsByStudyid(-2);
    }
}