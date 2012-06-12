package org.cimmyt.cril.ibwb.provider.tests;

import java.util.List;
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
        //serviciosLocal.addStudy(study);
    }

    public void testStudyList() {
        System.out.println("List Study's");
        List<Study> studies = servicios.getStudyList();
        System.out.println("Total del registros: " + studies.size());
        for (Study study : studies) {
            System.out.println(study.getTitle());
        }
        System.out.println("End list Study's");
    }

    public void testStudyLocalYRemoto() {
        System.out.println("-------------List Study's Local and Remote");
        Study studyVacio = new Study(true);
        List<Study> studys = servicios.getListStudy(studyVacio, 0, 0, false);
        System.out.println("Tama�o del listado: " + studys.size());
        for (Study study : studys) {
            System.out.println("\t" + study.getTitle());
        }
        System.out.println("-------------End list Study's Local and Remote");
    }

    public static void main(String[] args) {
        TestStudy testServices = new TestStudy();
        //testServices.testStudyAdd();
        //testServices.testStudyList();
        testServices.testStudyLocalYRemoto();
    }
}