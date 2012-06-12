/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.testsStatic;

import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.domain.Germplsm;
import org.cimmyt.cril.ibwb.domain.Listdata;
import org.cimmyt.cril.ibwb.domain.ListdataPK;

/**
 *
 * @author gamaliel
 */
public class TestGermplasm extends TestService {
    
    public void testSaveWorkbook() {
        List<Listdata> listDatas = getListDataGermplsm();
//        servicios.addNewsListdata(listDatas);
    }
    
    private static List<Listdata> getListDataGermplsm(){
        List<Listdata> listGermplasm = new ArrayList<Listdata>(0);
        for(int i = 1; i < 11; i++){
            Listdata tempGermplasmListData = new Listdata();
            ListdataPK pk = new ListdataPK();
            pk.setListid(i);
            pk.setLrecid(i);
            tempGermplasmListData.setListdataPK(pk);
            tempGermplasmListData.setEntryid(i);
            tempGermplasmListData.setEntrycd("A"+i);
            tempGermplasmListData.setSource("-");
            tempGermplasmListData.setDesig("-");
            tempGermplasmListData.setGrpname("ApO/r 64" + i);
            tempGermplasmListData.setLrstatus(i);
            tempGermplasmListData.setGid(i);
            listGermplasm.add(tempGermplasmListData);
        }
        return listGermplasm;
    }
    
    private void getGermplasmByStudyIdTrialPlot(){
        Germplsm germplsm = this.servicios.getGermplsmByTidTrialPlot(-2, 2, 17);
        if(germplsm != null){
            System.out.println("Germplasm vale : " + germplsm.getGid() + " cid: " + germplsm.getCid());
        }else{
            System.out.println("Germplasm vacio.");
        }
    }
    
    private void getGermplasmListByStudyAndTrial(){
        List<Germplsm> germplsms = this.servicios.getGermplsmListByStudyAndTrial(-2, 2);
        for(Germplsm germplsm : germplsms){
            if(germplsm != null){
                System.out.println("Germplasm vale : " + germplsm.getGid() + " cid: " + germplsm.getCid());
            }else{
                System.out.println("Germplasm vacio.");
            }
        }
    }
    
    public static void main(String[] args) {
        TestGermplasm testGermplasm = new TestGermplasm();
//        testGermplasm.testSaveWorkbook();
        testGermplasm.getGermplasmByStudyIdTrialPlot();
//        testGermplasm.getGermplasmListByStudyAndTrial();
    }
}