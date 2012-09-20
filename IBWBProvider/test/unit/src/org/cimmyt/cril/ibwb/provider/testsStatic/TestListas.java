/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.testsStatic;

import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.domain.Listdata;
import org.cimmyt.cril.ibwb.domain.ListdataPK;
import org.cimmyt.cril.ibwb.domain.Listnms;

/**
 *
 * @author TMSANCHEZ
 */
public class TestListas extends TestService {

    public void testAddListnms() {
        Listnms listnms = new Listnms();
        listnms.setListname("LIST01");
        listnms.setListdate(20111026);
        listnms.setListtype(Listnms.LIST_TYPE_LIST);
        listnms.setListuid(0);
        listnms.setListdesc("LIST01 DESCRIPTION");
        listnms.setLhierarchy(0);
        listnms.setListstatus(0);

        System.out.println("Adding list");
        //AppServicesProxy.getDefault().appServices().addListnms(listnms);
        servicios.addListnms(listnms);
        
        System.out.println("Id for listname: " + listnms.getListid());

        System.out.println("List added");

        List<Listdata> dataList = new ArrayList<Listdata>();

        Listdata d1 = new Listdata(true);
        ListdataPK pk1 = new ListdataPK(listnms.getListid(),0);
        d1.setListdataPK(pk1);   //*
        d1.setEntryid(1);        //*
        d1.setDesig("CM44444");  //*
        d1.setEntrycd("ddddd");
        d1.setSource("SOURC1");
        d1.setGrpname("grp");
        d1.setLrstatus(0);      //*
        d1.setGid( -202558);
        

        dataList.add(d1);

        Listdata d2 = new Listdata(true);
        ListdataPK pk2 = new ListdataPK(listnms.getListid(), 0);
        d2.setListdataPK(pk2);   //*
        d2.setEntryid(1);        //*
        d2.setDesig("CM44444");  //*
        d2.setEntrycd("ddddd");
        d2.setSource("SOURC1");
        d2.setGrpname("grp");
        d2.setLrstatus(0);      //*
         d2.setGid(45889);

        dataList.add(d2);

        for (Listdata data : dataList) {
            servicios.addListdata(data);
        }
    }
    
    public void testAddNewsGermplasm() {
        Listnms listnms = new Listnms();
        listnms.setListname("NIC2");
        listnms.setListdate(20111026);
        listnms.setListtype(Listnms.LIST_TYPE_LIST);
        listnms.setListuid(0);
        listnms.setListdesc("LIST01 DESCRIPTION");
        listnms.setLhierarchy(0);
        listnms.setListstatus(0);

        System.out.println("Adding list");
        //AppServicesProxy.getDefault().appServices().addListnms(listnms);
//        servicios.addListnms(listnms);
        
        System.out.println("Id for listname: " + listnms.getListid());

        System.out.println("List added");

        List<Listdata> dataList = new ArrayList<Listdata>();

        Listdata d1 = new Listdata(true);
        ListdataPK pk1 = new ListdataPK(listnms.getListid(),0);
        d1.setListdataPK(pk1);   //*
        d1.setEntryid(1);        //*
        d1.setDesig("OZCM444344");  //*
        d1.setEntrycd("ddddd");
        d1.setSource("SOURC1");
        d1.setGrpname("grp");
        d1.setLrstatus(0);      //*
        d1.setGid(0);
        

        dataList.add(d1);

        Listdata d2 = new Listdata(true);
        ListdataPK pk2 = new ListdataPK(listnms.getListid(), 0);
        d2.setListdataPK(pk2);   //*
        d2.setEntryid(1);        //*
        d2.setDesig("OZCM44499");  //*
        d2.setEntrycd("ddddddd");
        d2.setSource("SOURC13");
        d2.setGrpname("grp");
        d2.setLrstatus(0);      //*
        d2.setGid(0);

        dataList.add(d2);

//        for (Listdata data : dataList) {
//            servicios.addListdata(data);
//        }
        servicios.addNewsGermplasm(listnms, dataList, 1);
    }
    
    public void testGetListnmsFull() {
        Listnms listnms = servicios.getFullListnms(-14);
        printListnms(listnms);
    }
    
    private void printListnms(Listnms listnms){
        System.out.println("Name: " + listnms.getListname());
        System.out.println("Desc: " + listnms.getListdesc());
        System.out.println("Type: " + listnms.getListtype());
        System.out.println("Date: " + listnms.getListdate());
        System.out.println("EntryCD \t Design \t\t\t Grpname \t\t 1027" );
        for(Listdata listdata : listnms.getLisdatas()){
            System.out.print(listdata.getEntrycd() + "\t");
            System.out.print(listdata.getDesig() + "\t\t");
            System.out.print(listdata.getGrpname() + "\t\t");
            if(listdata.getName1027() != null){
                System.out.print(listdata.getName1027().getNval());
            }
            System.out.print("\t\t");
            if(listdata.getName1028() != null){
                System.out.print(listdata.getName1028().getNval());
            }
            System.out.print("\t\t");
            if(listdata.getName1029() != null){
                System.out.print(listdata.getName1029().getNval());
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TestListas tl = new TestListas();
//        tl.testAddListnms();
//        tl.testAddNewsGermplasm();
        tl.testGetListnmsFull();
    }
}
