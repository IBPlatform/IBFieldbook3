/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.testsStatic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.domain.GermplasmSearch;
import org.cimmyt.cril.ibwb.domain.Germplsm;
import org.cimmyt.cril.ibwb.domain.Listdata;
import org.cimmyt.cril.ibwb.domain.ListdataPK;

import org.cimmyt.cril.ibwb.provider.helpers.HelperGermplasm;

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
    
    private void getGermplasmByStudyIdTrialPlotOneByeOne(){
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
    
    private List<GermplasmSearch> getListGermplasmSearch(){
        List<GermplasmSearch> result = new ArrayList<GermplasmSearch>();
        for(int i = 1 ; i<11; i++){
            GermplasmSearch gs = new GermplasmSearch();
            //gs.setStudyId(19973);
            gs.setStudyId(40170);
            gs.setTrial(1);
            gs.setPlot(i);
            result.add(gs);
        }
        return result;
    }
    
    private List<GermplasmSearch> getListGermplasmSearchFmale(){
        List<GermplasmSearch> result = new ArrayList<GermplasmSearch>();
        for(int i = 1 ; i<=200; i++){
            GermplasmSearch gs = new GermplasmSearch();
            gs.setStudyId(40165);
            gs.setTrial(1);
            gs.setCrosstype("SS");
            //gs.setPlot(i-300);
            gs.setPlot(i);
            result.add(gs);
        }
        return result;
    }
    
    private List<GermplasmSearch> getListGermplasmSearchMale(){
        List<GermplasmSearch> result = new ArrayList<GermplasmSearch>();
        for(int i = 1 ; i<201; i++){
            GermplasmSearch gs = new GermplasmSearch();
            gs.setCrosstype("SS");
            gs.setStudyId(40170);
            gs.setTrial(1);
            switch(i){
                case 	1	: gs.setPlot(	25	); break;
                case 	2	: gs.setPlot(	39	); break;
                case 	3	: gs.setPlot(	25	); break;
                case 	4	: gs.setPlot(	39	); break;
                case 	5	: gs.setPlot(	26	); break;
                case 	6	: gs.setPlot(	25	); break;
                case 	7	: gs.setPlot(	39	); break;
                case 	8	: gs.setPlot(	26	); break;
                case 	9	: gs.setPlot(	25	); break;
                case 	10	: gs.setPlot(	25	); break;
                case 	11	: gs.setPlot(	26	); break;
                case 	12	: gs.setPlot(	27	); break;
                case 	13	: gs.setPlot(	39	); break;
                case 	14	: gs.setPlot(	27	); break;
                case 	15	: gs.setPlot(	28	); break;
                case 	16	: gs.setPlot(	27	); break;
                case 	17	: gs.setPlot(	28	); break;
                case 	18	: gs.setPlot(	28	); break;
                case 	19	: gs.setPlot(	15	); break;
                case 	20	: gs.setPlot(	17	); break;
                case 	21	: gs.setPlot(	15	); break;
                case 	22	: gs.setPlot(	16	); break;
                case 	23	: gs.setPlot(	17	); break;
                case 	24	: gs.setPlot(	18	); break;
                case 	25	: gs.setPlot(	19	); break;
                case 	26	: gs.setPlot(	20	); break;
                case 	27	: gs.setPlot(	15	); break;
                case 	28	: gs.setPlot(	17	); break;
                case 	29	: gs.setPlot(	21	); break;
                case 	30	: gs.setPlot(	22	); break;
                case 	31	: gs.setPlot(	15	); break;
                case 	32	: gs.setPlot(	16	); break;
                case 	33	: gs.setPlot(	18	); break;
                case 	34	: gs.setPlot(	19	); break;
                case 	35	: gs.setPlot(	20	); break;
                case 	36	: gs.setPlot(	21	); break;
                case 	37	: gs.setPlot(	22	); break;
                case 	38	: gs.setPlot(	15	); break;
                case 	39	: gs.setPlot(	16	); break;
                case 	40	: gs.setPlot(	17	); break;
                case 	41	: gs.setPlot(	18	); break;
                case 	42	: gs.setPlot(	19	); break;
                case 	43	: gs.setPlot(	20	); break;
                case 	44	: gs.setPlot(	21	); break;
                case 	45	: gs.setPlot(	22	); break;
                case 	46	: gs.setPlot(	15	); break;
                case 	47	: gs.setPlot(	18	); break;
                case 	48	: gs.setPlot(	16	); break;
                case 	49	: gs.setPlot(	17	); break;
                case 	50	: gs.setPlot(	19	); break;
                case 	51	: gs.setPlot(	20	); break;
                case 	52	: gs.setPlot(	21	); break;
                case 	53	: gs.setPlot(	44	); break;
                case 	54	: gs.setPlot(	53	); break;
                case 	55	: gs.setPlot(	56	); break;
                case 	56	: gs.setPlot(	57	); break;
                case 	57	: gs.setPlot(	45	); break;
                case 	58	: gs.setPlot(	53	); break;
                case 	59	: gs.setPlot(	56	); break;
                case 	60	: gs.setPlot(	57	); break;
                case 	61	: gs.setPlot(	44	); break;
                case 	62	: gs.setPlot(	51	); break;
                case 	63	: gs.setPlot(	43	); break;
                case 	64	: gs.setPlot(	51	); break;
                case 	65	: gs.setPlot(	38	); break;
                case 	66	: gs.setPlot(	32	); break;
                case 	67	: gs.setPlot(	37	); break;
                case 	68	: gs.setPlot(	80	); break;
                case 	69	: gs.setPlot(	80	); break;
                case 	70	: gs.setPlot(	72	); break;
                case 	71	: gs.setPlot(	80	); break;
                case 	72	: gs.setPlot(	73	); break;
                case 	73	: gs.setPlot(	74	); break;
                case 	74	: gs.setPlot(	75	); break;
                case 	75	: gs.setPlot(	80	); break;
                case 	76	: gs.setPlot(	84	); break;
                case 	77	: gs.setPlot(	85	); break;
                case 	78	: gs.setPlot(	80	); break;
                case 	79	: gs.setPlot(	77	); break;
                case 	80	: gs.setPlot(	77	); break;
                case 	81	: gs.setPlot(	76	); break;
                case 	82	: gs.setPlot(	77	); break;
                case 	83	: gs.setPlot(	77	); break;
                case 	84	: gs.setPlot(	76	); break;
                case 	85	: gs.setPlot(	77	); break;
                case 	86	: gs.setPlot(	76	); break;
                case 	87	: gs.setPlot(	77	); break;
                case 	88	: gs.setPlot(	76	); break;
                case 	89	: gs.setPlot(	73	); break;
                case 	90	: gs.setPlot(	4	); break;
                case 	91	: gs.setPlot(	74	); break;
                case 	92	: gs.setPlot(	76	); break;
                case 	93	: gs.setPlot(	92	); break;
                case 	94	: gs.setPlot(	106	); break;
                case 	95	: gs.setPlot(	98	); break;
                case 	96	: gs.setPlot(	106	); break;
                case 	97	: gs.setPlot(	101	); break;
                case 	98	: gs.setPlot(	106	); break;
                case 	99	: gs.setPlot(	106	); break;
                case 	100	: gs.setPlot(	93	); break;
                case 	101	: gs.setPlot(	99	); break;
                case 	102	: gs.setPlot(	102	); break;
                case 	103	: gs.setPlot(	106	); break;
                case 	104	: gs.setPlot(	94	); break;
                case 	105	: gs.setPlot(	98	); break;
                case 	106	: gs.setPlot(	103	); break;
                case 	107	: gs.setPlot(	3	); break;
                case 	108	: gs.setPlot(	3	); break;
                case 	109	: gs.setPlot(	4	); break;
                case 	110	: gs.setPlot(	3	); break;
                case 	111	: gs.setPlot(	4	); break;
                case 	112	: gs.setPlot(	3	); break;
                case 	113	: gs.setPlot(	4	); break;
                case 	114	: gs.setPlot(	3	); break;
                case 	115	: gs.setPlot(	4	); break;
                case 	116	: gs.setPlot(	4	); break;
                case 	117	: gs.setPlot(	4	); break;
                case 	118	: gs.setPlot(	3	); break;
                case 	119	: gs.setPlot(	3	); break;
                case 	120	: gs.setPlot(	4	); break;
                case 	121	: gs.setPlot(	3	); break;
                case 	122	: gs.setPlot(	357	); break;
                case 	123	: gs.setPlot(	368	); break;
                case 	124	: gs.setPlot(	361	); break;
                case 	125	: gs.setPlot(	361	); break;
                case 	126	: gs.setPlot(	361	); break;
                case 	127	: gs.setPlot(	357	); break;
                case 	128	: gs.setPlot(	361	); break;
                case 	129	: gs.setPlot(	361	); break;
                case 	130	: gs.setPlot(	361	); break;
                case 	131	: gs.setPlot(	361	); break;
                case 	132	: gs.setPlot(	368	); break;
                case 	133	: gs.setPlot(	361	); break;
                case 	134	: gs.setPlot(	361	); break;
                case 	135	: gs.setPlot(	357	); break;
                case 	136	: gs.setPlot(	368	); break;
                case 	137	: gs.setPlot(	357	); break;
                case 	138	: gs.setPlot(	371	); break;
                case 	139	: gs.setPlot(	371	); break;
                case 	140	: gs.setPlot(	373	); break;
                case 	141	: gs.setPlot(	374	); break;
                case 	142	: gs.setPlot(	371	); break;
                case 	143	: gs.setPlot(	373	); break;
                case 	144	: gs.setPlot(	373	); break;
                case 	145	: gs.setPlot(	373	); break;
                case 	146	: gs.setPlot(	374	); break;
                case 	147	: gs.setPlot(	374	); break;
                case 	148	: gs.setPlot(	372	); break;
                case 	149	: gs.setPlot(	372	); break;
                case 	150	: gs.setPlot(	374	); break;
                case 	151	: gs.setPlot(	371	); break;
                case 	152	: gs.setPlot(	372	); break;
                case 	153	: gs.setPlot(	372	); break;
                case 	154	: gs.setPlot(	371	); break;
                case 	155	: gs.setPlot(	372	); break;
                case 	156	: gs.setPlot(	372	); break;
                case 	157	: gs.setPlot(	371	); break;
                case 	158	: gs.setPlot(	375	); break;
                case 	159	: gs.setPlot(	372	); break;
                case 	160	: gs.setPlot(	372	); break;
                case 	161	: gs.setPlot(	371	); break;
                case 	162	: gs.setPlot(	376	); break;
                case 	163	: gs.setPlot(	362	); break;
                case 	164	: gs.setPlot(	363	); break;
                case 	165	: gs.setPlot(	365	); break;
                case 	166	: gs.setPlot(	364	); break;
                case 	167	: gs.setPlot(	180	); break;
                case 	168	: gs.setPlot(	372	); break;
                case 	169	: gs.setPlot(	365	); break;
                case 	170	: gs.setPlot(	365	); break;
                case 	171	: gs.setPlot(	180	); break;
                case 	172	: gs.setPlot(	364	); break;
                case 	173	: gs.setPlot(	364	); break;
                case 	174	: gs.setPlot(	365	); break;
                case 	175	: gs.setPlot(	363	); break;
                case 	176	: gs.setPlot(	363	); break;
                case 	177	: gs.setPlot(	5	); break;
                case 	178	: gs.setPlot(	5	); break;
                case 	179	: gs.setPlot(	2	); break;
                case 	180	: gs.setPlot(	2	); break;
                case 	181	: gs.setPlot(	2	); break;
                case 	182	: gs.setPlot(	2	); break;
                case 	183	: gs.setPlot(	2	); break;
                case 	184	: gs.setPlot(	2	); break;
                case 	185	: gs.setPlot(	2	); break;
                case 	186	: gs.setPlot(	4	); break;
                case 	187	: gs.setPlot(	2	); break;
                case 	188	: gs.setPlot(	2	); break;
                case 	189	: gs.setPlot(	5	); break;
                case 	190	: gs.setPlot(	4	); break;
                case 	191	: gs.setPlot(	18	); break;
                case 	192	: gs.setPlot(	21	); break;
                case 	193	: gs.setPlot(	51	); break;
                case 	194	: gs.setPlot(	56	); break;
                case 	195	: gs.setPlot(	58	); break;
                case 	196	: gs.setPlot(	77	); break;
                case 	197	: gs.setPlot(	372	); break;
                case 	198	: gs.setPlot(	373	); break;
                case 	199	: gs.setPlot(	374	); break;
                case 	200	: gs.setPlot(	365	); break;
            }
            result.add(gs);
        }
        return result;
    }
    
    private void getGermplasmByListStudyTrialPlot(){
        List<GermplasmSearch> germplasmSearchs = HelperGermplasm.getGermplasmByListStudyTrialPlot(servicios, getListGermplasmSearch());
        for(GermplasmSearch gs : germplasmSearchs){
            System.out.println("BSCID: " + gs.getBcid() + " LOCID: " + gs.getLid());
        }
    }
    
    private void getGermplasmByListStudyTrialPlotCross(){
        
        List<GermplasmSearch> germplasmSearchs = HelperGermplasm.getGermplasmByListStudyTrialPlotCross(servicios, getListGermplasmSearchFmale(), getListGermplasmSearchMale());
        for(GermplasmSearch gs : germplasmSearchs){
            System.out.println("BSCID: " + gs.getBcid() + " LOCID: " + gs.getLid()+ "PEDIGREE FEMALE   " + gs.getNames().getCimmytPedigree() + " PEDIGREE EMALE    " + gs.getNamesMale().getCimmytPedigree() );
        }
    }
    
    private void getGermplasmByListStudyTrialPlotSelection(){
        List<GermplasmSearch> germplasmSearchs = HelperGermplasm.getGermplasmByListStudyTrialPlotCross(servicios, getListGermplasmSearchFmale(),  new ArrayList<GermplasmSearch>());
        for(GermplasmSearch gs : germplasmSearchs){
            System.out.println("BSCID: " + gs.getBcid() + " LOCID: " + gs.getLid() + "   " + gs.getNames().getCimmytPedigree());
        }
    }
    
    public static void main(String[] args) {
        TestGermplasm testGermplasm = new TestGermplasm();
//        testGermplasm.testSaveWorkbook();
//        testGermplasm.getGermplasmByStudyIdTrialPlotOneByeOne();
//        testGermplasm.getGermplasmListByStudyAndTrial();
//        testGermplasm.getGermplasmByListStudyTrialPlot();
        
        testGermplasm.getGermplasmByListStudyTrialPlotCross();
        
//        testGermplasm.getGermplasmByListStudyTrialPlotSelection();
    }
}