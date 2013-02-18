package org.cimmyt.cril.ibwb.provider.helpers;

//import com.sun.tools.internal.xjc.model.nav.NType;
import ibfb.query.classes.GpidInfClass;
import ibfb.domain.core.Workbook;
import ibfb.query.core.QueryCenter;
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

    public List<Listdata> saveGerplasmCimmytWheat(
            List<Listdata> listGermplsm,
            Listnms listnms,
            List<GermplasmSearch> lgsf,
            List<GermplasmSearch> lgsm) {
        if (listnms.getListid() == null) {
            appServices.addListnms(listnms);
        } else if (listnms.getListid() == 0) {
            appServices.addListnms(listnms);
        }
        QueryCenter queryCenter = loadQueryCenter();
        List<Listdata> listDatas = new ArrayList<Listdata>(0);
        for (Listdata listdataT : listGermplsm) {

            listdataT.setListdataPK(new ListdataPK(listnms.getListid(), 0));

            String nameGermplasm = listdataT.getDesig();//----------------------->Definir cual es el nombre History
            String nameGermplasmBCID = listdataT.getNameBCID();//----------------------->Definir cual es el nombre BCID

            Integer gidWheat = null;
            boolean existWheatName = verifyExistingWheatName(gidWheat, nameGermplasm, listdataT);

            GermplasmSearch gsf = null;
            GermplasmSearch gsm = null;
            int i = listGermplsm.lastIndexOf(listdataT);
            if (i != -1 && i < lgsf.size()) {
                gsf = lgsf.get(i);
            }
            if (i != -1 && i < lgsm.size()) {
                gsm = lgsm.get(i);
            }
            if (existWheatName) {
                servicioLocal.addListdata(listdataT);
                saveCimmytDataAtributes(listdataT,gsf,gsm);
            } else {

                agregarGermPlasmCimmytWheat(nameGermplasm, nameGermplasmBCID, listdataT, listnms, gsf, gsm, queryCenter);
                //agregarGermPlasmCimmytWheat(nameGermplasm, nameGermplasmBCID, listdataT);


                listdataT.setListdataPK(new ListdataPK(listnms.getListid(), 0));
                //servicioLocal.addListdata(listdataT);

            }
            listDatas.add(listdataT);

        }
        return listDatas;
    }

    private boolean verifyExistingWheatName(Integer gid, String nameGermplasm, Listdata listdata) {
        boolean existWheatName = false;
        Germplsm germplsm = verifyByGid(gid);
        if (germplsm == null) {
            Names names = verifyByName(nameGermplasm);
            if (names == null) {
            } else {
                //recuperar names gid
                listdata.setGid(names.getGid());//Asignando Gid correcto
                existWheatName = Boolean.TRUE;
            }
        } else {
            existWheatName = Boolean.TRUE;
        }
        return existWheatName;
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
        // check if names as is provided already exists        
        names.setNval(nameGermplasm);
        List<Names> listNames = appServices.getListNames(names, 0, 0, false);
        if (listNames.isEmpty() || listNames.size() == 0) {
            // now try with standarized name
            names = new Names(true);
            names.setNval(org.generationcp.middleware.manager.GermplasmDataManagerImpl.standardaizeName(nameGermplasm));
            listNames = appServices.getListNames(names, 0, 0, false);
            if (listNames.isEmpty() || listNames.size() == 0) {
                return null;
            } else {
                return listNames.get(0);
            }

        } else {
            return listNames.get(0);
        }
    }

    public Listdata agregarGermPlasm(String nameGermplasm, Listdata listdata) {

        Germplsm germplsm = new Germplsm();

        // standardize germplasm name


        //germplsm.setGid(userId); -> Utogenerado
        if (listdata.getMethodId() != null) {
            germplsm.setMethn(listdata.getMethodId());//Asigmar metodo de la lista 107 para cruzas no 101
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

        germplsm.setSid(0);
        germplsm.setCid(0);


        //los demas 0 (por default tienen 0 asi que ya no se asigna)
        servicioLocal.addGermplsm(germplsm);

        Names names = new Names();
        //names
        //names.setNid(userId);//nid = autoincrement
        names.setGid(germplsm.getGid());//gid = germplasm

        if (germplsm.getMethn() == Methods.UNKNOWN_DERIVATIVE_METHOD_SF) {
            names.setNtype(Names.DERIVATIVE_NAME);
        } else {
            if (germplsm.getMethn() == Methods.UNKNOWN_GENERATIVE_METHOD_SF && names.getNval().contains(Names.SLASH_SEPARATOR)) {
                names.setNtype(Names.CROSS_NAME);
            } else if (germplsm.getMethn() == Methods.UNKNOWN_GENERATIVE_METHOD_SF && !names.getNval().contains(Names.SLASH_SEPARATOR)) {
                names.setNtype(Names.UNNAMED_CROSS);
            }
        }

        // tmsanchez 20120424
        names.setNstat(1);//nstat = 0

        names.setNuid(userId);//nuid = numero de usuario tienen que pasar o 0
        //names.setNval(nameGermplasm);//nval = nombre del germoplasma
        names.setNval(org.generationcp.middleware.manager.GermplasmDataManagerImpl.standardaizeName(nameGermplasm));//nval = nombre del germoplasma
        names.setNlocn(0);//nlocn 0
        names.setNdate(UtilDate.getDateAsInteger(new Date()));//ndate añomesdia
        names.setNref(0);//nref 0
        servicioLocal.addNames(names);
        listdata.setGid(germplsm.getGid());
//      servicioLocal.addListdata(listdata);
        return listdata;
    }

    public Listdata agregarGermPlasmCimmytWheat(
            String nameGermplasmHistory,
            String nameGermplasmBCID,
            Listdata listdata,
            Listnms listnms,
            GermplasmSearch gsf,
            GermplasmSearch gsm,
            QueryCenter queryCenter) {

        Germplsm germplsm = new Germplsm();
        //germplsm.setGid(userId); -> Utogenerado
        if (listdata.getMethodId() != null) {
            germplsm.setMethn(listdata.getMethodId());
        } else {
            germplsm.setMethn(getMethod(nameGermplasmHistory));//methn 31 si tiene - al final si no el   metodo = 1 unknow
        }

        if (listdata.getGnpgs() != null) {
            germplsm.setGnpgs(listdata.getGnpgs());
        } else {
            germplsm.setGnpgs(getGnpgs(nameGermplasmHistory));//gnpgs 2
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
            //germplsm.setGdate(UtilDate.getDateAsInteger(new Date()));//gdate la fecha en que se da de alta el registro añomesdia
            germplsm.setGdate(listnms.getListdate());
        } else {
            germplsm.setGdate(listdata.getHarvestDate());
        }


        //los demas 0 (por default tienen 0 asi que ya no se asigna)
        servicioLocal.addGermplsm(germplsm);



        Names names = new Names();
        //names
        //names.setNid(userId);//nid = autoincrement
        names.setGid(germplsm.getGid());//gid = germplasm
        names.setNtype(1028);//historia de seleccion = 1028
        // tmsanchez 20120424
        names.setNstat(0);//nstat = 0

        names.setNuid(userId);//nuid = numero de usuario tienen que pasar o 0
        names.setNval(nameGermplasmHistory);//nval = nombre del germoplasma
        names.setNlocn(0);//nlocn 0
        names.setNdate(UtilDate.getDateAsInteger(new Date()));//ndate añomesdia
        names.setNref(0);//nref 0
        servicioLocal.addNames(names);



        names = new Names();
        //names
        //names.setNid(userId);//nid = autoincrement
        names.setGid(germplsm.getGid());//gid = germplasm
        names.setNtype(1027);//nombre bcid = 1027
        // tmsanchez 20120424
        names.setNstat(1);//nstat = 0

        names.setNuid(userId);//nuid = numero de usuario tienen que pasar o 0
        names.setNval(nameGermplasmBCID);//nval = nombre del germoplasma
        names.setNlocn(0);//nlocn 0
        names.setNdate(UtilDate.getDateAsInteger(new Date()));//ndate añomesdia
        names.setNref(0);//nref 0
        servicioLocal.addNames(names);

        names = new Names();
        //names
        //names.setNid(userId);//nid = autoincrement
        names.setGid(germplsm.getGid());//gid = germplasm
        names.setNtype(Names.CIMMYT_WHEAT_PEDIGREE);//pedigri = 1029 
        // tmsanchez 20120424
        names.setNstat(0);//nstat = 0

        names.setNuid(userId);//nuid = numero de usuario tienen que pasar o 0
        String arma_pedigree;
        try {
            arma_pedigree = queryCenter.arma_pedigree(germplsm.getGid(), 0, new GpidInfClass(), 0, 0, 0, 0);
            log.info("Pedigree armado para GID: " + germplsm.getGid() + ":" + arma_pedigree);
        } catch (Exception e) {
            log.info(e);
            arma_pedigree = "";
        }
        names.setNval(arma_pedigree);//nval = nombre del germoplasma
        names.setNlocn(0);//nlocn 0
        names.setNdate(UtilDate.getDateAsInteger(new Date()));//ndate añomesdia
        names.setNref(0);//nref 0
        servicioLocal.addNames(names);

        listdata.setGid(germplsm.getGid());

        listdata.setGrpname(arma_pedigree);

        servicioLocal.addListdata(listdata);

        Dmsattr dmsattr = new Dmsattr();
        //Para seleccion
        if (gsf != null && gsm == null) {
            dmsattr.setDmsatype(Dmsattr.DMSATYPE_STID);
            dmsattr.setDmsatab(Dmsattr.DMSATYPE_LIST);
            dmsattr.setDmsatrec(listdata.getListdataPK().getLrecid());
            dmsattr.setDmsatval(gsf.getStudyId().toString());//Studyid
            servicioLocal.addDmsattr(dmsattr);

            dmsattr.setDmsatype(Dmsattr.DMSATYPE_SOCC);
            dmsattr.setDmsatab(Dmsattr.DMSATYPE_LIST);
            dmsattr.setDmsatrec(listdata.getListdataPK().getLrecid());
            dmsattr.setDmsatval(gsf.getTrial().toString());//Ocurrence
            servicioLocal.addDmsattr(dmsattr);

            dmsattr.setDmsatype(Dmsattr.DMSATYPE_SENT);
            dmsattr.setDmsatab(Dmsattr.DMSATYPE_LIST);
            dmsattr.setDmsatrec(listdata.getListdataPK().getLrecid());
            dmsattr.setDmsatval(gsf.getPlot().toString());//Plot
            servicioLocal.addDmsattr(dmsattr);
        }

        //Para cruzas
        if (gsf != null && gsm != null) {
            dmsattr.setDmsatype(Dmsattr.DMSATYPE_FTID);
            dmsattr.setDmsatab(Dmsattr.DMSATYPE_LIST);
            dmsattr.setDmsatrec(listdata.getListdataPK().getLrecid());
            dmsattr.setDmsatval(gsf.getStudyId().toString());//Studyid
            servicioLocal.addDmsattr(dmsattr);

            dmsattr.setDmsatype(Dmsattr.DMSATYPE_FOCC);
            dmsattr.setDmsatab(Dmsattr.DMSATYPE_LIST);
            dmsattr.setDmsatrec(listdata.getListdataPK().getLrecid());
            dmsattr.setDmsatval(gsf.getTrial().toString());//Ocurrence
            servicioLocal.addDmsattr(dmsattr);

            dmsattr.setDmsatype(Dmsattr.DMSATYPE_FENT);
            dmsattr.setDmsatab(Dmsattr.DMSATYPE_LIST);
            dmsattr.setDmsatrec(listdata.getListdataPK().getLrecid());
            dmsattr.setDmsatval(gsf.getPlot().toString());//Plot
            servicioLocal.addDmsattr(dmsattr);

            dmsattr.setDmsatype(Dmsattr.DMSATYPE_MTID);
            dmsattr.setDmsatab(Dmsattr.DMSATYPE_LIST);
            dmsattr.setDmsatrec(listdata.getListdataPK().getLrecid());
            dmsattr.setDmsatval(gsm.getStudyId().toString());//Studyid
            servicioLocal.addDmsattr(dmsattr);

            dmsattr.setDmsatype(Dmsattr.DMSATYPE_MOCC);
            dmsattr.setDmsatab(Dmsattr.DMSATYPE_LIST);
            dmsattr.setDmsatrec(listdata.getListdataPK().getLrecid());
            dmsattr.setDmsatval(gsm.getTrial().toString());//Ocurrence
            servicioLocal.addDmsattr(dmsattr);

            dmsattr.setDmsatype(Dmsattr.DMSATYPE_MENT);
            dmsattr.setDmsatab(Dmsattr.DMSATYPE_LIST);
            dmsattr.setDmsatrec(listdata.getListdataPK().getLrecid());
            dmsattr.setDmsatval(gsm.getPlot().toString());//Plot
            servicioLocal.addDmsattr(dmsattr);
        }
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
            List<GermplasmSearch> list) {
        Map<String, StudySearch> mapStudySearchs = new HashMap<String, StudySearch>();
        List<StudySearch> listStudySearchs = new ArrayList<StudySearch>();
        for (GermplasmSearch germplasmSearch : list) {
            StudySearch studySearch = mapStudySearchs.get(germplasmSearch.getStudyId() + "|" + germplasmSearch.getTrial());
            if (studySearch == null) {
                studySearch = new StudySearch();
                studySearch.setStudyId(germplasmSearch.getStudyId());
                studySearch.setTrial(germplasmSearch.getTrial());
                listStudySearchs.add(studySearch);
                mapStudySearchs.put(germplasmSearch.getStudyId() + "|" + germplasmSearch.getTrial(), studySearch);
            }
        }
        List<StudySearch> listStudySearchFound = new ArrayList<StudySearch>();
        for (StudySearch studySearchTemp : listStudySearchs) {

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
                    studySearchTemp.setNameDesig(factor.getFname());
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

        for (StudySearch studySearchTemp : listStudySearchFound) {
            Integer gidBuscar = null;
            try {
                if (studySearchTemp.getRst() != null) {
                    ResultSet rst = studySearchTemp.getRst();
                    while (rst.next()) {
                        Integer numberPlot = rst.getInt(studySearchTemp.getNamePlot());
                        for (GermplasmSearch germplasmSearchT : list) {
                            if (numberPlot.equals(germplasmSearchT.getPlot())
                                    && germplasmSearchT.getStudyId().equals(studySearchTemp.getStudyId())
                                    && germplasmSearchT.getTrial().equals(studySearchTemp.getTrial())) {
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

        for (GermplasmSearch gs : list) {
            Factor factorLid = appServices.getFactorByStudyidAndFname(gs.getStudyId(), lid);
            if (factorLid != null) {
                factorLid = HelperFactor.getFactorFillingFull(factorLid, appServices, 801);
                String levelValue = (String) factorLid.getLevel(gs.getTrial() - 1);
                if (levelValue != null) {
                    gs.setLid(levelValue);
                }
            }
            if (gs.getGermplsm().getGnpgs() == -1) {
                if (gs.getNames().getNtype() == 1028) {
                    Integer max = appServices.getMaxForSelection(gs.getStudyId(), gs.getNames().getNval(), 1028);
                    gs.setMax(max);
                } else {
                    gs.setMax(0);
                }
            } else if (gs.getGermplsm().getGnpgs() == 2) {
                if (gs.getNames().getNtype() == 1027) {
                    Integer max = appServices.getMaxForSelection(gs.getStudyId(), gs.getNames().getNval(), 1027);
                    gs.setMax(max);
                } else {
                    gs.setMax(0);
                }
            }
        }
        return list;
    }

    /**
     * This method function by generate BCID
     *
     * @param appServices
     * @param listFmale
     * @param listMale
     * @return a List of GermplamsSearch contain Germplasm(Fmale) Names(Fmale)
     * Germplasm(Male) Names(Male) BCID Max for BCID Locid (Fmale)
     */
    public static List<GermplasmSearch> getGermplasmByListStudyTrialPlotCross(
            AppServices appServices,
            List<GermplasmSearch> listFmale,
            List<GermplasmSearch> listMale) {
        Map<String, StudySearch> mapStudySearchs = new HashMap<String, StudySearch>();
        List<StudySearch> listStudySearchs = new ArrayList<StudySearch>();
        log.info("Extrayendo las diferentes convinaciones de studio y trial para fmale");
        log.info("Recuperando la organization, program, harvdate y lid");
        for (GermplasmSearch germplasmSearch : listFmale) {
            StudySearch studySearch = mapStudySearchs.get(germplasmSearch.getStudyId() + "|" + germplasmSearch.getTrial());
            if (studySearch == null) {
                studySearch = new StudySearch();
                studySearch.setStudyId(germplasmSearch.getStudyId());
                studySearch.setTrial(germplasmSearch.getTrial());
                listStudySearchs.add(studySearch);
                mapStudySearchs.put(germplasmSearch.getStudyId() + "|" + germplasmSearch.getTrial(), studySearch);

                //Recuperando la organizacion para el estudio solicitado
                Factor factorOrganization = appServices.getFactorByStudyidAndFname(studySearch.getStudyId(), organization);
                if (factorOrganization != null) {
                    factorOrganization = HelperFactor.getFactorFillingFull(factorOrganization, appServices, 801);
                    String levelValue = (String) factorOrganization.getLevel(0);
                    if (levelValue != null) {
                        //Construyendo BCID
                        studySearch.getSb().append(levelValue);
                    }
                }

                //Recuperando el programa para el estudio solicitado
                Factor factorProgram = appServices.getFactorByStudyidAndFname(studySearch.getStudyId(), program);
                if (germplasmSearch.getCrosstype() != null) {
                    studySearch.getSb().append(germplasmSearch.getCrosstype());
                } else {
                    if (factorProgram != null) {
                        factorProgram = HelperFactor.getFactorFillingFull(factorProgram, appServices, 801);
                        String levelValue = (String) factorProgram.getLevel(0);
                        if (levelValue != null) {
                            //Construyendo BCID
                            studySearch.getSb().append(levelValue);
                        }
                    }
                }

                //Recuperando el HavDate para el estudio solicitado
                Factor factorHaveDate = appServices.getFactorByStudyidAndFname(studySearch.getStudyId(), harvdate);
                if (factorHaveDate != null) {
                    factorHaveDate = HelperFactor.getFactorFillingFull(factorHaveDate, appServices, 801);
                    Double levelValue = (Double) factorHaveDate.getLevel(studySearch.getTrial() - 1);
                    if (levelValue != null) {
                        //Construyendo BCID
                        studySearch.getSb().append(levelValue.toString().substring(3, 5));
                    }
                }

                //Recuperando localidad
                Factor factorLid = appServices.getFactorByStudyidAndFname(studySearch.getStudyId(), lid);
                if (factorLid != null) {
                    factorLid = HelperFactor.getFactorFillingFull(factorLid, appServices, 801);
                    String levelValue = (String) factorLid.getLevel(studySearch.getTrial() - 1);
                    if (levelValue != null) {
                        //Construyendo BCID
                        studySearch.getSb().append(levelValue);
                        studySearch.setLid(levelValue);
                    }
                }
            }
        }

        log.info("Extrayendo las diferentes convinaciones de studio y trial para Male");
        for (GermplasmSearch germplasmSearch : listMale) {
            StudySearch studySearch = mapStudySearchs.get(germplasmSearch.getStudyId() + "|" + germplasmSearch.getTrial());
            if (studySearch == null) {
                studySearch = new StudySearch();
                studySearch.setStudyId(germplasmSearch.getStudyId());
                studySearch.setTrial(germplasmSearch.getTrial());
                listStudySearchs.add(studySearch);
                mapStudySearchs.put(germplasmSearch.getStudyId() + "|" + germplasmSearch.getTrial(), studySearch);

            }
        }

        log.info("Recuperando lista de factores por estudio y trial detectado para ejecutar"
                + "query center ");
        List<StudySearch> listStudySearchFound = new ArrayList<StudySearch>();
        for (StudySearch studySearchTemp : listStudySearchs) {
            studySearchTemp = appServices.getListGermplasmAndPlotByStudyidAndTrial(studySearchTemp);
            listStudySearchFound.add(studySearchTemp);

        }

        log.info("Iterando en los resulset para optener el gid del germoplasma");
        log.info("Y recuperar los germplasm y los names");
        for (StudySearch studySearchTemp : listStudySearchFound) {
            Integer gidBuscar = null;
            try {
                if (studySearchTemp.getRst() != null) {
                    ResultSet rst = studySearchTemp.getRst();
                    while (rst.next()) {
                        Integer numberPlot = rst.getInt(studySearchTemp.getNamePlot());
                        for (GermplasmSearch germplasmSearchT : listFmale) {
                            if (numberPlot.equals(germplasmSearchT.getPlot())
                                    && germplasmSearchT.getStudyId().equals(studySearchTemp.getStudyId())
                                    && germplasmSearchT.getTrial().equals(studySearchTemp.getTrial())) {
                                gidBuscar = rst.getInt(studySearchTemp.getNameGid());
                                log.info("Gid : " + gidBuscar);
                                log.info("Germplsm gid : " + gidBuscar);
                                germplasmSearchT.setGermplsm(appServices.getGermplsm(gidBuscar));
                                germplasmSearchT.setNames(appServices.getNamesByGid(germplasmSearchT.getGermplsm(), false));
                                germplasmSearchT.setBcid(studySearchTemp.getSb().toString());
                                germplasmSearchT.setLid(studySearchTemp.getLid());
                                germplasmSearchT.setSnameFmale(studySearchTemp.getsName());
                            }
                        }
                        for (GermplasmSearch germplasmSearchT : listMale) {
                            if (numberPlot.equals(germplasmSearchT.getPlot())
                                    && germplasmSearchT.getStudyId().equals(studySearchTemp.getStudyId())
                                    && germplasmSearchT.getTrial().equals(studySearchTemp.getTrial())) {
                                gidBuscar = rst.getInt(studySearchTemp.getNameGid());
                                log.info("Gid : " + gidBuscar);
                                log.info("Germplsm gid : " + gidBuscar);
                                germplasmSearchT.setGermplsm(appServices.getGermplsm(gidBuscar));
                                germplasmSearchT.setNames(appServices.getNamesByGid(germplasmSearchT.getGermplsm(), false));
                                germplasmSearchT.setSnameMale(studySearchTemp.getsName());
                            }
                        }
                    }
                }
            } catch (SQLException ex) {
                log.error("Error al trabajar con el resulset. ", ex);
            }
        }
        //Recuperando los maximos para el fmale
        log.info("Iniciando el proceso de recuperacion de maximos para los fmale");
        for (GermplasmSearch gs : listFmale) {

//            if(gs.getGermplsm() != null && gs.getNames() != null){
//                log.info("Gnpgs: " + gs.getGermplsm().getGnpgs() + " Ntype: " + gs.getNames().getNtype());
//            }else{
//                log.info("Germplasm: " + gs.getGermplsm() + " Names: " + gs.getNames());
//            }

            if (gs.getGermplsm().getGnpgs() == -1 && gs.getNames().getNtype() == Names.CIMMYT_WHEAT_SELECTION_HISTORY) {
                gs.setMax(appServices.getMaxForSelection(gs.getStudyId(), gs.getBcid(), Names.CIMMYT_WHEAT_SELECTION_HISTORY));
            } else if (gs.getGermplsm().getGnpgs() == 2 && gs.getNames().getNtype() == Names.CIMMYT_WHEAT_BCID) {
                Integer max = appServices.getMaxForSelection(gs.getStudyId(), gs.getBcid(), Names.CIMMYT_WHEAT_BCID);
                gs.setMax(max);
            } else {
                gs.setMax(0);
            }
//            log.info("Max: " + gs.getMax());
        }
        log.info("FIN!!!! Iniciando el proceso de recuperacion de maximos para los fmale");        
        log.info("seteando los datos del male a los objetos GermplasmSearchFmale");
        for (GermplasmSearch gs : listMale) {
            listFmale.get(listMale.indexOf(gs)).setGermplsmMale(gs.getGermplsm());
            listFmale.get(listMale.indexOf(gs)).setNamesMale(gs.getNames());
            listFmale.get(listMale.indexOf(gs)).setSnameMale(gs.getSnameMale());
        }
        log.info("FIN!!!! seteando los datos del male a los objetos GermplasmSearchFmale");

        log.info("seteando los datos del FEMALE a los objetos GermplasmSearchFmale");
        for (GermplasmSearch gs : listFmale) {
            boolean fmaleFound = false;
            boolean maleFound = false;
            if (gs.getGermplsmMale() != null) {
                if (gs.getGermplsmMale().getGpid1() != null) {
                    if (gs.getGermplsm() != null) {
                        if (gs.getGermplsmMale().getGpid1().equals(gs.getGermplsm().getGid())) {
                            fmaleFound = true;
                        }
                    }
                }
            }
            if (gs.getGermplsm() != null) {
                if (gs.getGermplsm().getGpid2() != null) {
                    if (gs.getGermplsmMale() != null) {
                        if (gs.getGermplsm().getGpid2().equals(gs.getGermplsmMale().getGid())) {
                            maleFound = true;
                        }
                    }
                }
            }

            if (gs.getSnameMale().substring(0, 2).equals("F1")
                    && gs.getSnameFmale().substring(0, 2).equals("F1")) {//Comparar con f1 el inicio de los nombres de ambos
                gs.setCharBCID("D");
//                gs.setMethodGermplasm(103);
            } else if (gs.getSnameMale().substring(0, 2).equals("F1")
                    || gs.getSnameFmale().substring(0, 2).equals("F1")) {//Comparar con f1 el inicio de los nombres de alguno
                gs.setCharBCID("T");
//                gs.setMethodGermplasm(102);
            } else if (maleFound) {//male found
                gs.setCharBCID("M");
//                gs.setMethodGermplasm(107);
            } else if (fmaleFound) {//fmalefound
                gs.setCharBCID("F");
//                gs.setMethodGermplasm(107);
            } else {//simple
                gs.setCharBCID("S");
//                gs.setMethodGermplasm(2);
            }

//----------Asignando metodos y letras finales del BCID
            if (gs.getGermplsm().getGnpgs() < 0) {//Inbred female parent
                if (gs.getGermplsmMale().getGnpgs() < 0) {//Inbred male parent
                    gs.setMethodGermplasm(101);//Single cross
//                    gs.setCharBCID("S");
                } else {
                    if (gs.getGermplsmMale().getGnpgs() == 1) {
                        gs.setMethodGermplasm(101);//Male is a mutant - single cross
//                        gs.setCharBCID("S");
                    } else if (gs.getGermplsmMale().getGnpgs() == 2) {
                        //Get GMF and GMM ! Male is a cross - get paternal grandparents
                        Germplsm gmf = appServices.getGermplsm(gs.getGermplsmMale().getGpid1());
                        Germplsm gmm = appServices.getGermplsm(gs.getGermplsmMale().getGpid2());
                        if (gmf.getGid().equals(gs.getGermplsm().getGid()) || gmm.getGid().equals(gs.getGermplsm().getGid())) {
                            gs.setMethodGermplasm(107);//If one paternal grandparent is the same as the mother - backcross
//                            gs.setCharBCID("F");
                        } else {
                            gs.setMethodGermplasm(102);//Paternal grandparents are different to mother - top cross
//                            gs.setCharBCID("T");
                        }
                    } else {
                        gs.setMethodGermplasm(106);//Male has more than two parents - complex cross
//                        gs.setCharBCID("S");
                    }
                }
            } else {//Heterozygous female parent
                if (gs.getGermplsmMale().getGnpgs() < 0) {//Inbred male parent 
                    if (gs.getGermplsm().getGnpgs() == 1) {
                        gs.setMethodGermplasm(101);//Female parent is a mutant - single cross
//                        gs.setCharBCID("S");
                    } else if (gs.getGermplsm().getGnpgs() == 2) {
                        //TODO : Female is a cross - get maternal grand parents
                        Germplsm gff = appServices.getGermplsm(gs.getGermplsm().getGpid1());
                        Germplsm gfm = appServices.getGermplsm(gs.getGermplsm().getGpid2());

                        if (gff.getGid().equals(gs.getGermplsmMale().getGid()) || gfm.getGid().equals(gs.getGermplsmMale().getGid())) {
                            gs.setMethodGermplasm(107);//If one maternal grandparent is the same as the father - backcross
//                            gs.setCharBCID("M");
                        } else {
                            gs.setMethodGermplasm(102);//Maternal grandparents are different to father - top cross
//                            gs.setCharBCID("T");
                        }
                    } else {
                        gs.setMethodGermplasm(106);//Female has more than two parents - complex cross
//                        gs.setCharBCID("S");
                    }
                } else {
                    if (gs.getGermplsm().getMethn() == 101 && gs.getGermplsmMale().getMethn() == 101) {
                        gs.setMethodGermplasm(103);//Both parents are single crosses - double cross
//                        gs.setCharBCID("D");
                    } else {
                        gs.setMethodGermplasm(106);//At least one parent is more complex than a single cross - complex cross
//                        gs.setCharBCID("S");
                    }
                }
            }
        }
        log.info("FIN!!!!! seteando los datos del FEMALE a los objetos GermplasmSearchFmale");
        return listFmale;
    }

    public static List<Germplsm> getGermplsmListByStudyAndTrial(
            AppServices appServices,
            CommonServices servicioLocal,
            CommonServices servicioCentral,
            Integer studyId,
            Integer trial) {
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

        log.info("Asignando nombres de factores principales");
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
            Integer entry) {

        StringBuilder sb = new StringBuilder();

        Factor factorOrganization = appServices.getFactorByStudyidAndFname(studyid, organization);
        if (factorOrganization != null) {
            factorOrganization = HelperFactor.getFactorFillingFull(factorOrganization, appServices, 801);
            String levelValue = (String) factorOrganization.getLevel(0);
            if (levelValue != null) {
                sb.append(levelValue);
            }
        }

        Factor factorProgram = appServices.getFactorByStudyidAndFname(studyid, program);
        if (factorProgram != null) {
            factorProgram = HelperFactor.getFactorFillingFull(factorProgram, appServices, 801);
            String levelValue = (String) factorProgram.getLevel(0);
            if (levelValue != null) {
                sb.append(levelValue);
            }
        }

        Factor factorHaveDate = appServices.getFactorByStudyidAndFname(studyid, harvdate);
        if (factorHaveDate != null) {
            factorHaveDate = HelperFactor.getFactorFillingFull(factorHaveDate, appServices, 801);
            Double levelValue = (Double) factorHaveDate.getLevel(trial - 1);
            if (levelValue != null) {
                sb.append(levelValue.toString().substring(2, 4));
            }
        }

        Factor factorLid = appServices.getFactorByStudyidAndFname(studyid, lid);
        if (factorLid != null) {
            factorLid = HelperFactor.getFactorFillingFull(factorLid, appServices, 801);
            String levelValue = (String) factorLid.getLevel(trial - 1);
            if (levelValue != null) {
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
            Integer trial) {
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
                    if (entry != plot) {
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

    private QueryCenter loadQueryCenter() {
        QueryCenter queryReadCenter = new QueryCenter();
        queryReadCenter.readAndLoadDatabases();
        queryReadCenter.setFnameKeyEntryNumber("Entry number");
        queryReadCenter.setFnameKeyOcc("occ");
        queryReadCenter.setFnamePedigree("cross name");
        queryReadCenter.readFlexPedConfig();
        return queryReadCenter;
    }

    /**
     * Save data atributes for germplasm in the linst
     *
     * @param listdata
     * @param gsf
     * @param gsm
     */
    private void saveCimmytDataAtributes(Listdata listdata, GermplasmSearch gsf,
            GermplasmSearch gsm) {
        Dmsattr dmsattr = new Dmsattr();
        //Para seleccion
        if (gsf != null && gsm == null) {
            dmsattr.setDmsatype(Dmsattr.DMSATYPE_STID);
            dmsattr.setDmsatab(Dmsattr.DMSATYPE_LIST);
            dmsattr.setDmsatrec(listdata.getListdataPK().getLrecid());
            dmsattr.setDmsatval(gsf.getStudyId().toString());//Studyid
            servicioLocal.addDmsattr(dmsattr);

            dmsattr.setDmsatype(Dmsattr.DMSATYPE_SOCC);
            dmsattr.setDmsatab(Dmsattr.DMSATYPE_LIST);
            dmsattr.setDmsatrec(listdata.getListdataPK().getLrecid());
            dmsattr.setDmsatval(gsf.getTrial().toString());//Ocurrence
            servicioLocal.addDmsattr(dmsattr);

            dmsattr.setDmsatype(Dmsattr.DMSATYPE_SENT);
            dmsattr.setDmsatab(Dmsattr.DMSATYPE_LIST);
            dmsattr.setDmsatrec(listdata.getListdataPK().getLrecid());
            dmsattr.setDmsatval(gsf.getPlot().toString());//Plot
            servicioLocal.addDmsattr(dmsattr);
        }

        //Para cruzas
        if (gsf != null && gsm != null) {
            dmsattr.setDmsatype(Dmsattr.DMSATYPE_FTID);
            dmsattr.setDmsatab(Dmsattr.DMSATYPE_LIST);
            dmsattr.setDmsatrec(listdata.getListdataPK().getLrecid());
            dmsattr.setDmsatval(gsf.getStudyId().toString());//Studyid
            servicioLocal.addDmsattr(dmsattr);

            dmsattr.setDmsatype(Dmsattr.DMSATYPE_FOCC);
            dmsattr.setDmsatab(Dmsattr.DMSATYPE_LIST);
            dmsattr.setDmsatrec(listdata.getListdataPK().getLrecid());
            dmsattr.setDmsatval(gsf.getTrial().toString());//Ocurrence
            servicioLocal.addDmsattr(dmsattr);

            dmsattr.setDmsatype(Dmsattr.DMSATYPE_FENT);
            dmsattr.setDmsatab(Dmsattr.DMSATYPE_LIST);
            dmsattr.setDmsatrec(listdata.getListdataPK().getLrecid());
            dmsattr.setDmsatval(gsf.getPlot().toString());//Plot
            servicioLocal.addDmsattr(dmsattr);

            dmsattr.setDmsatype(Dmsattr.DMSATYPE_MTID);
            dmsattr.setDmsatab(Dmsattr.DMSATYPE_LIST);
            dmsattr.setDmsatrec(listdata.getListdataPK().getLrecid());
            dmsattr.setDmsatval(gsm.getStudyId().toString());//Studyid
            servicioLocal.addDmsattr(dmsattr);

            dmsattr.setDmsatype(Dmsattr.DMSATYPE_MOCC);
            dmsattr.setDmsatab(Dmsattr.DMSATYPE_LIST);
            dmsattr.setDmsatrec(listdata.getListdataPK().getLrecid());
            dmsattr.setDmsatval(gsm.getTrial().toString());//Ocurrence
            servicioLocal.addDmsattr(dmsattr);

            dmsattr.setDmsatype(Dmsattr.DMSATYPE_MENT);
            dmsattr.setDmsatab(Dmsattr.DMSATYPE_LIST);
            dmsattr.setDmsatrec(listdata.getListdataPK().getLrecid());
            dmsattr.setDmsatval(gsm.getPlot().toString());//Plot
            servicioLocal.addDmsattr(dmsattr);
        }
    }
}