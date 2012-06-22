/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.helpers;

//import com.sun.tools.internal.xjc.model.nav.NType;
import ibfb.domain.core.Workbook;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.AppServices;
import org.cimmyt.cril.ibwb.api.CommonServices;
import org.cimmyt.cril.ibwb.api.dao.utils.UtilDate;
import org.cimmyt.cril.ibwb.domain.*;

/**
 *
 * @author gamaliel
 */
public class HelperGermplasm {

    private static final String organization = "Organization";
    private static final String program = "Program";
    private static final String harvdate = "Harvdate";
    private static final String lid = "Lid";
    
    private static Logger log = Logger.getLogger(HelperGermplasm.class);
    private AppServices appServices;
    private CommonServices servicioLocal;
    private Listnms listnms;
    private List<Listdata> listdatas;
    private Integer userId;

    public HelperGermplasm(
            Listnms listnms,
            AppServices appServices,
            CommonServices servicioLocal,
            Integer userId) {
        this.listnms = listnms;
        this.appServices = appServices;
        this.servicioLocal = servicioLocal;
        this.userId = userId;
    }

    public List<Listdata> saveGermplasmNews(
            List<Listdata> listGermplsm,
            Listnms listnms) {
        if (listnms.getListid() == null) {
            appServices.addListnms(listnms);
        } else if (listnms.getListid() == 0) {
            appServices.addListnms(listnms);
        }
        List<Listdata> listDatas = new ArrayList<Listdata>(0);
        for (Listdata listdata : listGermplsm) {

            listdata.setListdataPK(new ListdataPK(listnms.getListid(), 0));

            String nameGermplasm = listdata.getDesig();//----------------------->Definir cual es el nombre

            verificaExistencia(listdata.getGid(), nameGermplasm, listdata);

            listdata.setListdataPK(new ListdataPK(listnms.getListid(), 0));
            servicioLocal.addListdata(listdata);
            listDatas.add(listdata);

        }
        return listDatas;
    }

    public void verificaExistencia(Integer gid, String nameGermplasm, Listdata listdata) {
        Germplsm germplsm = verifyByGid(gid);
        if (germplsm == null) {
            Names names = verifyByName(nameGermplasm);
            if (names == null) {
                //Agregar
                agregarGermPlasm(nameGermplasm, listdata);
            } else {
                //recuperar names gid
                listdata.setGid(names.getGid());//Asignando Gid correcto
            }
        } else {

            Names names = verifyByName(nameGermplasm);
            if (names == null) {
                //Agregar
                agregarGermPlasm(nameGermplasm, listdata);
            } else {
                if (names.getGid().equals(germplsm.getGid())) {
                    listdata.setGid(germplsm.getGid());
                } else {
                    //recuperar names gid
                    listdata.setGid(names.getGid());//Asignando Gid correcto
                }
            }
        }
    }

    public Germplsm verifyByGid(Integer gid) {
        if (gid != null) {
            Germplsm filtroGermplsm = new Germplsm(true);
            filtroGermplsm.setGid(gid);
            List<Germplsm> listTempGerm = appServices.getListGermplsm(filtroGermplsm, 0, 0, false);
            if (listTempGerm.isEmpty() || listTempGerm.size() == 0) {
                return null;
            } else {
                return listTempGerm.get(0);
            }
        } else {
            return null;
        }
    }

    public Names verifyByName(String nameGermplasm) {
        //Verificando el nombre por que no se encontro el gid
        Names names = new Names(true);
        names.setNval(nameGermplasm);
        List<Names> listNames = appServices.getListNames(names, 0, 0, false);
        if (listNames.isEmpty() || listNames.size() == 0) {
            return null;
        } else {
            return listNames.get(0);
        }
    }

    public Listdata agregarGermPlasm(String nameGermplasm, Listdata listdata) {

        Germplsm germplsm = new Germplsm();
        //germplsm.setGid(userId); -> Utogenerado
        if (listdata.getMethodId() != null) {
            germplsm.setMethn(listdata.getMethodId());
        } else {
            germplsm.setMethn(getMethod(nameGermplasm));//methn 31 si tiene - al final si no el   metodo = 1 unknow
        }

        if (listdata.getGnpgs() != null) {
            germplsm.setGnpgs(listdata.getGnpgs());
        } else {
            germplsm.setGnpgs(getGnpgs(nameGermplasm));//gnpgs 2
        }

        //


        // assign parents
        if (listdata.getGpid1() != null) {
            germplsm.setGpid1(listdata.getGpid1());//gpid1 0
        } else {
            germplsm.setGpid1(0);//gpid1 0
        }

        if (listdata.getGpid2() != null) {
            germplsm.setGpid2(listdata.getGpid2());//gpid2 0
        } else {
            germplsm.setGpid2(0);//gpid2 0 
        }


        germplsm.setGermuid(userId);//germuid usuario 


        germplsm.setLgid(0);//lgid 0

        //germplsm.setGlocn(0);//glocn 0
        if (listdata.getLocationId() == null) {
            germplsm.setGlocn(0);//glocn 0
        } else {
            germplsm.setGlocn(listdata.getLocationId());
        }


        if (listdata.getHarvestDate() == null) {
            germplsm.setGdate(UtilDate.getDateAsInteger(new Date()));//gdate la fecha en que se da de alta el registro añomesdia
        } else {
            germplsm.setGdate(listdata.getHarvestDate());
        }


        //los demas 0 (por default tienen 0 asi que ya no se asigna)
        servicioLocal.addGermplsm(germplsm);

        Names names = new Names();
        //names
        //names.setNid(userId);//nid = autoincrement
        names.setGid(germplsm.getGid());//gid = germplasm
        names.setNtype(5);//ntype = 5
        // tmsanchez 20120424
        names.setNstat(1);//nstat = 0

        names.setNuid(userId);//nuid = numero de usuario tienen que pasar o 0
        names.setNval(nameGermplasm);//nval = nombre del germoplasma
        names.setNlocn(0);//nlocn 0
        names.setNdate(UtilDate.getDateAsInteger(new Date()));//ndate añomesdia
        names.setNref(0);//nref 0
        servicioLocal.addNames(names);

        listdata.setGid(germplsm.getGid());
//        servicioLocal.addListdata(listdata);

        return listdata;
    }

    public static Integer getMethod(String name) {
        int defaultMethod = 1;
        log.info("Getting METHOD for NAME = " + name);

        if (name.length() <= 5) {
            log.info("Less or equal than 5 chars" + name);
            if (name.contains("-")) {
                defaultMethod = 31;
            } else {
                defaultMethod = 1;
            }
        } else {
            log.info("Greather than 5 chars" + name);
            String lastChars = name.substring(name.length() - 5, name.length());
            if (lastChars.contains("-")) {
                defaultMethod = 31;
            } else {
                defaultMethod = 1;
            }
        }

        return defaultMethod;
    }

    public static Integer getGnpgs(String name) {
        int defaultGnpgs = 1;
        log.info("Getting Gnpgs for NAME = " + name);

        if (name.length() <= 5) {
            log.info("Less or equal than 5 chars" + name);
            if (name.contains("-")) {
                defaultGnpgs = -1;
            } else {
                defaultGnpgs = 2;
            }
        } else {
            log.info("Greather than 5 chars" + name);
            String lastChars = name.substring(name.length() - 5, name.length());
            if (lastChars.contains("-")) {
                defaultGnpgs = -1;
            } else {
                defaultGnpgs = 2;
            }
        }

        return defaultGnpgs;

    }

    /**
     * @return the listnms
     */
    public Listnms getListnms() {
        return listnms;
    }

    /**
     * @param listnms the listnms to set
     */
    public void setListnms(Listnms listnms) {
        this.listnms = listnms;
    }

    /**
     * @return the listdatas
     */
    public List<Listdata> getListdatas() {
        return listdatas;
    }

    /**
     * @param listdatas the listdatas to set
     */
    public void setListdatas(List<Listdata> listdatas) {
        this.listdatas = listdatas;
    }

    public static Germplsm getQueryCenterTemp(
            AppServices appServices,
            CommonServices servicioLocal,
            CommonServices servicioCentral,
            Integer studyId,
            Integer trial,
            Integer plot) {
        String nameStudy = null;
        String nameTrial = null;
        String nameEntry = null;
        String nameDesig = null;
        String nameGid = null;
        String namePlot = null;

        List<Factor> factors = appServices.getMainFactorsByStudyid(studyId);
        Factor factorEntry = null;
        for (Factor factor : factors) {
            factor = HelperFactor.getFactorFillingFullWhitoutLevels(factor, appServices, 801);
            String traitScale = factor.getMeasuredin().getTraits().getTrname() + factor.getMeasuredin().getScales().getScname();
            if (Workbook.STUDY_NAME.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                nameStudy = factor.getFname();
            } else if (Workbook.TRIAL_INSTANCE_NUMBER.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                nameTrial = factor.getFname();
            } else if (Workbook.GERMPLASM_ENTRY_NUMBER.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                factorEntry = factor;
                nameEntry = factor.getFname();
            } else if (Workbook.FIELD_PLOT_NUMBER.equals(Workbook.getStringWithOutBlanks(traitScale))
                    || Workbook.FIELD_PLOT_NESTEDNUMBER.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                namePlot = factor.getFname();
            }
        }
        List<Factor> factorsEntry = new ArrayList<Factor>();
        if (factorEntry != null) {
            factorsEntry = appServices.getGroupFactorsByStudyidAndFactorid(studyId, factorEntry.getFactorid());
        }
        for (Factor factor : factorsEntry) {
            factor = HelperFactor.getFactorFillingFullWhitoutLevels(factor, appServices, 801);

            String traitScale = factor.getMeasuredin().getTraits().getTrname() + factor.getMeasuredin().getScales().getScname();

            if (Workbook.GERMPLASM_DESIG_DBCV.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                nameDesig = factor.getFname();
            } else if (Workbook.GERMPLASM_GID_DBID.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                nameGid = factor.getFname();
            }
        }

        log.info("Asignando nombres de fatorres principales");
        List<String> factorsKey = new ArrayList<String>();
        factorsKey.add(nameStudy);
        factorsKey.add(nameTrial);
        factorsKey.add(nameEntry);
        factorsKey.add(namePlot);

        log.info("Asignando nombres de factores de salida");
        List<String> factorsReturn = new ArrayList<String>();
        factorsReturn.add(nameTrial);
        factorsReturn.add(nameEntry);
        factorsReturn.add(namePlot);
        factorsReturn.add(nameDesig);
        factorsReturn.add(nameGid);

        ResultSet rst = appServices.getTrialRandomization(studyId, trial, factorsKey, factorsReturn, nameTrial);
        Integer gidBuscar = null;
        try {
            if (rst != null) {
                while (rst.next()) {
                    Integer numberPlot = rst.getInt(namePlot);
                    if (numberPlot.equals(plot)) {
                        gidBuscar = rst.getInt(nameGid);
                        //log.info("Gid : " + gidBuscar + " Design: " + rst.getString(nameDesig));
                        log.info("Gid : " + gidBuscar);
                        break;
                    }
                }
            }
            log.info("Germplsm gid : " + gidBuscar);
            return appServices.getGermplsm(gidBuscar);
        } catch (SQLException ex) {
            log.error("Error al trabajar con el resulset. ", ex);
            return null;
        }
    }
    
    public static List<GermplasmSearch> getGermplasmByListStudyTrialPlot(
            AppServices appServices,
            List<GermplasmSearch> list
            ) {
        Map<String, StudySearch> mapStudySearchs = new HashMap<String, StudySearch>();
        List<StudySearch> listStudySearchs = new ArrayList <StudySearch>();
        for(GermplasmSearch germplasmSearch : list){
            StudySearch studySearch = mapStudySearchs.get(germplasmSearch.getStudyId() + "|" + germplasmSearch.getTrial());
            if(studySearch == null){
                studySearch = new StudySearch();
                studySearch.setStudyId(germplasmSearch.getStudyId());
                studySearch.setTrial(germplasmSearch.getTrial());
                listStudySearchs.add(studySearch);
                mapStudySearchs.put(germplasmSearch.getStudyId() + "|" + germplasmSearch.getTrial(), studySearch);
            }
        }
        List<StudySearch> listStudySearchFound = new ArrayList <StudySearch>();
        for(StudySearch studySearchTemp : listStudySearchs){
            
            List<Factor> factors = appServices.getMainFactorsByStudyid(studySearchTemp.getStudyId());
            Factor factorEntry = null;
            for (Factor factor : factors) {
                factor = HelperFactor.getFactorFillingFullWhitoutLevels(factor, appServices, 801);
                String traitScale = factor.getMeasuredin().getTraits().getTrname() + factor.getMeasuredin().getScales().getScname();
                if (Workbook.STUDY_NAME.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                    studySearchTemp.setNameStudy(factor.getFname());
                } else if (Workbook.TRIAL_INSTANCE_NUMBER.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                    studySearchTemp.setNameTrial(factor.getFname());
                } else if (Workbook.GERMPLASM_ENTRY_NUMBER.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                    factorEntry = factor;
                    studySearchTemp.setNameEntry(factor.getFname());
                } else if (Workbook.FIELD_PLOT_NUMBER.equals(Workbook.getStringWithOutBlanks(traitScale))
                        || Workbook.FIELD_PLOT_NESTEDNUMBER.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                    studySearchTemp.setNamePlot(factor.getFname());
                }
            }
            List<Factor> factorsEntry = new ArrayList<Factor>();
            if (factorEntry != null) {
                factorsEntry = appServices.getGroupFactorsByStudyidAndFactorid(studySearchTemp.getStudyId(), factorEntry.getFactorid());
            }
            for (Factor factor : factorsEntry) {
                factor = HelperFactor.getFactorFillingFullWhitoutLevels(factor, appServices, 801);

                String traitScale = factor.getMeasuredin().getTraits().getTrname() + factor.getMeasuredin().getScales().getScname();

                if (Workbook.GERMPLASM_DESIG_DBCV.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                    studySearchTemp.setNameDesig( factor.getFname());
                } else if (Workbook.GERMPLASM_GID_DBID.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                    studySearchTemp.setNameGid(factor.getFname());
                }
            }

            log.info("Asignando nombres de fatorres principales");
            List<String> factorsKey = new ArrayList<String>();
            factorsKey.add(studySearchTemp.getNameStudy());
            factorsKey.add(studySearchTemp.getNameTrial());
            factorsKey.add(studySearchTemp.getNameEntry());
            factorsKey.add(studySearchTemp.getNamePlot());

            log.info("Asignando nombres de factores de salida");
            List<String> factorsReturn = new ArrayList<String>();
            factorsReturn.add(studySearchTemp.getNameTrial());
            factorsReturn.add(studySearchTemp.getNameEntry());
            factorsReturn.add(studySearchTemp.getNamePlot());
            factorsReturn.add(studySearchTemp.getNameDesig());
            factorsReturn.add(studySearchTemp.getNameGid());

            ResultSet rst = appServices.getTrialRandomization(studySearchTemp.getStudyId(), studySearchTemp.getTrial(), factorsKey, factorsReturn, studySearchTemp.getNameTrial());
            
            studySearchTemp.setRst(rst);
            listStudySearchFound.add(studySearchTemp);
            
        }
        
        for(StudySearch studySearchTemp : listStudySearchFound){
            Integer gidBuscar = null;
            try {
                if (studySearchTemp.getRst() != null) {
                    ResultSet rst = studySearchTemp.getRst();
                    while (rst.next()) {
                        Integer numberPlot = rst.getInt(studySearchTemp.getNamePlot());
                        for(GermplasmSearch germplasmSearchT : list){
                            if (numberPlot.equals(germplasmSearchT.getPlot()) 
                                    && germplasmSearchT.getStudyId().equals(studySearchTemp.getStudyId())
                                    && germplasmSearchT.getTrial().equals(studySearchTemp.getTrial())){
                                gidBuscar = rst.getInt(studySearchTemp.getNameGid());
                                //log.info("Gid : " + gidBuscar + " Design: " + rst.getString(nameDesig));
                                log.info("Gid : " + gidBuscar);
                                log.info("Germplsm gid : " + gidBuscar);
                                germplasmSearchT.setGermplsm(appServices.getGermplsm(gidBuscar));
                                germplasmSearchT.setNames(appServices.getNamesByGid(germplasmSearchT.getGermplsm(), false));
                                germplasmSearchT.setBcid(studySearchTemp.getSb().toString());
                            }
                        }
                    }
                }
            } catch (SQLException ex) {
                log.error("Error al trabajar con el resulset. ", ex);
            }
        }
        
        for(GermplasmSearch gs : list){
            Factor factorLid = appServices.getFactorByStudyidAndFname(gs.getStudyId(), lid);
            if(factorLid != null){
                factorLid = HelperFactor.getFactorFillingFull(factorLid, appServices, 801);
                String levelValue = (String) factorLid.getLevel(gs.getTrial()-1);
                if(levelValue != null){
                    gs.setLid(levelValue);
                }
            }
            if(gs.getGermplsm().getGnpgs() == -1){
                if(gs.getNames().getNtype() == 1028){
                    Integer max = appServices.getMaxForSelection(gs.getStudyId(), gs.getNames().getNval(), 1028);
                    gs.setMax(max);
                }else{
                    gs.setMax(0);
                }
            }else if (gs.getGermplsm().getGnpgs() == 2){
                if(gs.getNames().getNtype() == 1027){
                    Integer max = appServices.getMaxForSelection(gs.getStudyId(), gs.getNames().getNval(), 1027);
                    gs.setMax(max);
                }else{
                    gs.setMax(0);
                }
            }
        }
        return list;
    }
    
    public static List<GermplasmSearch> getGermplasmByListStudyTrialPlotCross(
            AppServices appServices,
            List<GermplasmSearch> listFmale,
            List<GermplasmSearch> listMale
            ) {
        Map<String, StudySearch> mapStudySearchs = new HashMap<String, StudySearch>();
        List<StudySearch> listStudySearchs = new ArrayList <StudySearch>();
        log.info("Extrayendo las diferentes convinaciones de studio y trial para fmale");
        log.info("Recuperando la organization, program, harvdate y lid");
        for(GermplasmSearch germplasmSearch : listFmale) {
            StudySearch studySearch = mapStudySearchs.get(germplasmSearch.getStudyId() + "|" + germplasmSearch.getTrial());
            if(studySearch == null){
                studySearch = new StudySearch();
                studySearch.setStudyId(germplasmSearch.getStudyId());
                studySearch.setTrial(germplasmSearch.getTrial());
                listStudySearchs.add(studySearch);
                mapStudySearchs.put(germplasmSearch.getStudyId() + "|" + germplasmSearch.getTrial(), studySearch);
                
                Factor factorOrganization = appServices.getFactorByStudyidAndFname(studySearch.getStudyId(), organization);
                if(factorOrganization != null){
                    factorOrganization = HelperFactor.getFactorFillingFull(factorOrganization, appServices, 801);
                    String levelValue = (String)factorOrganization.getLevel(0);
                    if(levelValue != null){
                        studySearch.getSb().append(levelValue);
                    }
                }

                Factor factorProgram = appServices.getFactorByStudyidAndFname(studySearch.getStudyId(), program);
                if(factorProgram != null){
                    factorProgram = HelperFactor.getFactorFillingFull(factorProgram, appServices, 801);
                    String levelValue = (String)factorProgram.getLevel(0);
                    if(levelValue != null){
                        studySearch.getSb().append(levelValue);
                    }
                }
                
                Factor factorHaveDate = appServices.getFactorByStudyidAndFname(studySearch.getStudyId(), harvdate);
                if(factorHaveDate != null){
                    factorHaveDate = HelperFactor.getFactorFillingFull(factorHaveDate, appServices, 801);
                    Double levelValue = (Double) factorHaveDate.getLevel(studySearch.getTrial()-1);
                    if(levelValue != null){
                        studySearch.getSb().append(levelValue.toString().substring(2, 4));
                    }
                }

                Factor factorLid = appServices.getFactorByStudyidAndFname(studySearch.getStudyId(), lid);
                if(factorLid != null){
                    factorLid = HelperFactor.getFactorFillingFull(factorLid, appServices, 801);
                    String levelValue = (String) factorLid.getLevel(studySearch.getTrial()-1);
                    if(levelValue != null){
                        studySearch.getSb().append(levelValue);
                        studySearch.setLid(levelValue);
                    }
                }
            }
        }
        
        log.info("Extrayendo las diferentes convinaciones de studio y trial para Male");
        for(GermplasmSearch germplasmSearch : listMale) {
            StudySearch studySearch = mapStudySearchs.get(germplasmSearch.getStudyId() + "|" + germplasmSearch.getTrial());
            if(studySearch == null){
                studySearch = new StudySearch();
                studySearch.setStudyId(germplasmSearch.getStudyId());
                studySearch.setTrial(germplasmSearch.getTrial());
                listStudySearchs.add(studySearch);
                mapStudySearchs.put(germplasmSearch.getStudyId() + "|" + germplasmSearch.getTrial(), studySearch);
                
            }
        }
        
        log.info("Recuperando lista de factores por estudio y trial detectado para ejecutar"
                + "query center ");
        List<StudySearch> listStudySearchFound = new ArrayList <StudySearch>();
        for(StudySearch studySearchTemp : listStudySearchs) {
            
            List<Factor> factors = appServices.getMainFactorsByStudyid(studySearchTemp.getStudyId());
            Factor factorEntry = null;
            for (Factor factor : factors) {
                factor = HelperFactor.getFactorFillingFullWhitoutLevels(factor, appServices, 801);
                String traitScale = factor.getMeasuredin().getTraits().getTrname() + factor.getMeasuredin().getScales().getScname();
                if (Workbook.STUDY_NAME.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                    studySearchTemp.setNameStudy(factor.getFname());
                } else if (Workbook.TRIAL_INSTANCE_NUMBER.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                    studySearchTemp.setNameTrial(factor.getFname());
                } else if (Workbook.GERMPLASM_ENTRY_NUMBER.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                    factorEntry = factor;
                    studySearchTemp.setNameEntry(factor.getFname());
                } else if (Workbook.FIELD_PLOT_NUMBER.equals(Workbook.getStringWithOutBlanks(traitScale))
                        || Workbook.FIELD_PLOT_NESTEDNUMBER.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                    studySearchTemp.setNamePlot(factor.getFname());
                }
            }
            List<Factor> factorsEntry = new ArrayList<Factor>();
            if (factorEntry != null) {
                factorsEntry = appServices.getGroupFactorsByStudyidAndFactorid(studySearchTemp.getStudyId(), factorEntry.getFactorid());
            }
            for (Factor factor : factorsEntry) {
                factor = HelperFactor.getFactorFillingFullWhitoutLevels(factor, appServices, 801);

                String traitScale = factor.getMeasuredin().getTraits().getTrname() + factor.getMeasuredin().getScales().getScname();

                if (Workbook.GERMPLASM_GID_DBID.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                    studySearchTemp.setNameGid(factor.getFname());
                }
            }

            log.info("Asignando nombres de fatorres principales");
            List<String> factorsKey = new ArrayList<String>();
            factorsKey.add(studySearchTemp.getNameStudy());
            factorsKey.add(studySearchTemp.getNameTrial());
            factorsKey.add(studySearchTemp.getNameEntry());
            factorsKey.add(studySearchTemp.getNamePlot());

            log.info("Asignando nombres de factores de salida");
            List<String> factorsReturn = new ArrayList<String>();
            factorsReturn.add(studySearchTemp.getNameTrial());
            factorsReturn.add(studySearchTemp.getNameEntry());
            factorsReturn.add(studySearchTemp.getNamePlot());
            factorsReturn.add(studySearchTemp.getNameGid());

            ResultSet rst = appServices.getTrialRandomization(studySearchTemp.getStudyId(), studySearchTemp.getTrial(), factorsKey, factorsReturn, studySearchTemp.getNameTrial());
            
            studySearchTemp.setRst(rst);
            listStudySearchFound.add(studySearchTemp);
            
        }
        
        log.info("Iterando en los resulset para optener el gid del germoplasma");
        log.info("Y recuperar los germplasm y los names");
        for(StudySearch studySearchTemp : listStudySearchFound) {
            Integer gidBuscar = null;
            try {
                if (studySearchTemp.getRst() != null) {
                    ResultSet rst = studySearchTemp.getRst();
                    while (rst.next()) {
                        Integer numberPlot = rst.getInt(studySearchTemp.getNamePlot());
                        for(GermplasmSearch germplasmSearchT : listFmale){
                            if (numberPlot.equals(germplasmSearchT.getPlot()) 
                                    && germplasmSearchT.getStudyId().equals(studySearchTemp.getStudyId())
                                    && germplasmSearchT.getTrial().equals(studySearchTemp.getTrial())){
                                gidBuscar = rst.getInt(studySearchTemp.getNameGid());
                                log.info("Gid : " + gidBuscar);
                                log.info("Germplsm gid : " + gidBuscar);
                                germplasmSearchT.setGermplsm(appServices.getGermplsm(gidBuscar));
                                germplasmSearchT.setNames(appServices.getNamesByGid(germplasmSearchT.getGermplsm(), false));
                                germplasmSearchT.setBcid(studySearchTemp.getSb().toString());
                            }
                        }
                        for(GermplasmSearch germplasmSearchT : listMale){
                            if (numberPlot.equals(germplasmSearchT.getPlot()) 
                                    && germplasmSearchT.getStudyId().equals(studySearchTemp.getStudyId())
                                    && germplasmSearchT.getTrial().equals(studySearchTemp.getTrial())){
                                gidBuscar = rst.getInt(studySearchTemp.getNameGid());
                                log.info("Gid : " + gidBuscar);
                                log.info("Germplsm gid : " + gidBuscar);
                                germplasmSearchT.setGermplsm(appServices.getGermplsm(gidBuscar));
                                germplasmSearchT.setNames(appServices.getNamesByGid(germplasmSearchT.getGermplsm(), false));
                            }
                        }
                    }
                }
            } catch (SQLException ex) {
                log.error("Error al trabajar con el resulset. ", ex);
            }
        }
        log.info("Iniciando el proceso de recuperacion de maximos para los fmale");
        for(GermplasmSearch gs : listFmale){
            if(gs.getGermplsm().getGnpgs() == -1 && gs.getNames().getNtype() == 1028){
                gs.setMax(appServices.getMaxForSelection(gs.getStudyId(), gs.getNames().getNval(), 1028));
            }else if (gs.getGermplsm().getGnpgs() == 2 && gs.getNames().getNtype() == 1027){
                Integer max = appServices.getMaxForSelection(gs.getStudyId(), gs.getNames().getNval(), 1027);
                gs.setMax(max);
            }else{
                gs.setMax(0);
            }
        }
        log.info("seteando los datos del male a los objetos GermplasmSearchFmale");
        for(GermplasmSearch gs : listMale){
            listFmale.get(listMale.indexOf(gs)).setGermplsmMale(gs.getGermplsm());
            listFmale.get(listMale.indexOf(gs)).setNamesMale(gs.getNames());
        }
        return listFmale;
    }

    public static List<Germplsm> getGermplsmListByStudyAndTrial(
            AppServices appServices,
            CommonServices servicioLocal,
            CommonServices servicioCentral,
            Integer studyId,
            Integer trial ) {
        String nameStudy = null;
        String nameTrial = null;
        String nameEntry = null;
        String nameDesig = null;
        String nameGid = null;
        String namePlot = null;

        List<Factor> factors = appServices.getMainFactorsByStudyid(studyId);
        Factor factorEntry = null;
        for (Factor factor : factors) {
            factor = HelperFactor.getFactorFillingFullWhitoutLevels(factor, appServices, 801);
            String traitScale = factor.getMeasuredin().getTraits().getTrname() + factor.getMeasuredin().getScales().getScname();
            if (Workbook.STUDY_NAME.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                nameStudy = factor.getFname();
            } else if (Workbook.TRIAL_INSTANCE_NUMBER.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                nameTrial = factor.getFname();
            } else if (Workbook.GERMPLASM_ENTRY_NUMBER.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                factorEntry = factor;
                nameEntry = factor.getFname();
            } else if (Workbook.FIELD_PLOT_NUMBER.equals(Workbook.getStringWithOutBlanks(traitScale))
                    || Workbook.FIELD_PLOT_NESTEDNUMBER.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                namePlot = factor.getFname();
            }
        }
        List<Factor> factorsEntry = new ArrayList<Factor>();
        if (factorEntry != null) {
            factorsEntry = appServices.getGroupFactorsByStudyidAndFactorid(studyId, factorEntry.getFactorid());
        }
        for (Factor factor : factorsEntry) {
            factor = HelperFactor.getFactorFillingFullWhitoutLevels(factor, appServices, 801);

            String traitScale = factor.getMeasuredin().getTraits().getTrname() + factor.getMeasuredin().getScales().getScname();

            if (Workbook.GERMPLASM_DESIG_DBCV.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                nameDesig = factor.getFname();
            } else if (Workbook.GERMPLASM_GID_DBID.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                nameGid = factor.getFname();
            }
        }

        log.info("Asignando nombres de fatorres principales");
        List<String> factorsKey = new ArrayList<String>();
        factorsKey.add(nameStudy);
        factorsKey.add(nameTrial);
        factorsKey.add(nameEntry);
        factorsKey.add(namePlot);

        log.info("Asignando nombres de factores de salida");
        List<String> factorsReturn = new ArrayList<String>();
        factorsReturn.add(nameTrial);
        factorsReturn.add(nameEntry);
        factorsReturn.add(namePlot);
        factorsReturn.add(nameDesig);
        factorsReturn.add(nameGid);

        ResultSet rst = appServices.getTrialRandomization(studyId, trial, factorsKey, factorsReturn, nameTrial);
        Integer gidBuscar = null;
        List<Germplsm> germplsms = new ArrayList<Germplsm>();
        try {
            if (rst != null) {
                while (rst.next()) {
                    gidBuscar = rst.getInt(nameGid);
                    log.info("Gid : " + gidBuscar + " Design: " + rst.getString(nameDesig));
                    germplsms.add(appServices.getGermplsm(gidBuscar));
                }
            }
            log.info("Germplsm gid : " + gidBuscar);
            return germplsms;
        } catch (SQLException ex) {
            log.error("Error al trabajar con el resulset. ", ex);
            return new ArrayList<Germplsm>();
        }
    }
    
    public static String generateBCID(
            AppServices appServices,
            Integer studyid,
            Integer trial,
            Integer entry
            ){
        
        StringBuilder sb = new StringBuilder();
        
        Factor factorOrganization = appServices.getFactorByStudyidAndFname(studyid, organization);
        if(factorOrganization != null){
            factorOrganization = HelperFactor.getFactorFillingFull(factorOrganization, appServices, 801);
            String levelValue = (String)factorOrganization.getLevel(0);
            if(levelValue != null){
                sb.append(levelValue);
            }
        }
        
        Factor factorProgram = appServices.getFactorByStudyidAndFname(studyid, program);
        if(factorProgram != null){
            factorProgram = HelperFactor.getFactorFillingFull(factorProgram, appServices, 801);
            String levelValue = (String)factorProgram.getLevel(0);
            if(levelValue != null){
                sb.append(levelValue);
            }
        }
        
        Factor factorHaveDate = appServices.getFactorByStudyidAndFname(studyid, harvdate);
        if(factorHaveDate != null){
            factorHaveDate = HelperFactor.getFactorFillingFull(factorHaveDate, appServices, 801);
            Double levelValue = (Double) factorHaveDate.getLevel(trial-1);
            if(levelValue != null){
                sb.append(levelValue.toString().substring(2, 4));
            }
        }
        
        Factor factorLid = appServices.getFactorByStudyidAndFname(studyid, lid);
        if(factorLid != null){
            factorLid = HelperFactor.getFactorFillingFull(factorLid, appServices, 801);
            String levelValue = (String) factorLid.getLevel(trial-1);
            if(levelValue != null){
                sb.append(levelValue);
            }
        }
        //ntype para (dos padres) bcid = 1027
        //ntype para (un padre) = 1028 para seleccion con gnpgs del germplasm -1
        String siguienteConsecutivo = appServices.getNextMaxForBCID(studyid, sb.toString(), 1027);
        sb.append(siguienteConsecutivo);
        sb.append("S");
        return sb.toString();
    }
    
    public static boolean itsRandom(
            AppServices appServices,
            Integer studyId,
            Integer trial
            ){
        String nameStudy = null;
        String nameTrial = null;
        String nameEntry = null;
        String namePlot = null;

        List<Factor> factors = appServices.getMainFactorsByStudyid(studyId);
        
        for (Factor factor : factors) {
            factor = HelperFactor.getFactorFillingFullWhitoutLevels(factor, appServices, 801);
            String traitScale = factor.getMeasuredin().getTraits().getTrname() + factor.getMeasuredin().getScales().getScname();
            if (Workbook.STUDY_NAME.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                nameStudy = factor.getFname();
            } else if (Workbook.TRIAL_INSTANCE_NUMBER.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                nameTrial = factor.getFname();
            } else if (Workbook.GERMPLASM_ENTRY_NUMBER.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                nameEntry = factor.getFname();
            } else if (Workbook.FIELD_PLOT_NUMBER.equals(Workbook.getStringWithOutBlanks(traitScale))
                    || Workbook.FIELD_PLOT_NESTEDNUMBER.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                namePlot = factor.getFname();
            }
        }

        log.info("Asignando nombres de fatorres principales");
        List<String> factorsKey = new ArrayList<String>();
        factorsKey.add(nameStudy);
        factorsKey.add(nameTrial);
        factorsKey.add(nameEntry);
        factorsKey.add(namePlot);

        log.info("Asignando nombres de factores de salida");
        List<String> factorsReturn = new ArrayList<String>();
        factorsReturn.add(nameTrial);
        factorsReturn.add(nameEntry);
        factorsReturn.add(namePlot);

        ResultSet rst = appServices.getTrialRandomization(studyId, trial, factorsKey, factorsReturn, nameTrial);
        Integer entry = null;
        Integer plot = null;
        try {
            if (rst != null) {
                while (rst.next()) {
                    entry = rst.getInt(nameEntry);
                    plot = rst.getInt(namePlot);
                    log.info("entry: " + entry + " plot: " + rst.getString(namePlot));
                    if(entry != plot){
                      return true;  
                    }
                }
            }
            return false;
        } catch (SQLException ex) {
            log.error("Error al trabajar con el resulset. ", ex);
            return false;
        }
    }
}