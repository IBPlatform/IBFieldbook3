package org.cimmyt.cril.ibwb.provider;

import ibfb.domain.core.Measurement;
import ibfb.domain.core.Workbook;
import ibfb.query.core.QueryCenter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.AppServices;
import org.cimmyt.cril.ibwb.api.CommonServices;
import org.cimmyt.cril.ibwb.domain.*;
import org.cimmyt.cril.ibwb.domain.inventory.InventoryData;
import org.cimmyt.cril.ibwb.domain.util.WheatData;
import org.cimmyt.cril.ibwb.provider.helpers.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service(value = "IBWorkBenchServiceApiImpl")
public class IBWBAppServicesImpl implements AppServices {

    private static Logger log = Logger.getLogger(IBWBAppServicesImpl.class);
    private CommonServices serviciosLocal;
    private CommonServices serviciosCentral;

    public static AppServices getIBWBAppServices() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ibwApiApplicationContext.xml");
        AppServices servicios = (AppServices) context.getBean("ibWorkBenchServiceApiImpl");

        return servicios;
    }

//-----------------------------------Atributs---------------------------
    @Override
    public List<Atributs> getAtributsList() {
        List<Atributs> atributs = serviciosCentral.getAtributsList();
        atributs.addAll(serviciosLocal.getAtributsList());
        return atributs;
    }

    @Override
    public int getTotalAtributs(Atributs atribut) {
        int total = serviciosCentral.getTotalAtributs(atribut);
        total += serviciosLocal.getTotalAtributs(atribut);
        return total;
    }

    @Override
    public List<Atributs> getListAtributs(Atributs filter, int start, int pageSize, boolean paged) {
        List<Atributs> atributs = serviciosCentral.getListAtributs(filter, start, pageSize, paged);
        atributs.addAll(serviciosLocal.getListAtributs(filter, start, pageSize, paged));
        return atributs;
    }

//-----------------------------------Bibrefs---------------------------
    @Override
    public List<Bibrefs> getBibrefsList() {
        List<Bibrefs> bibrefs = serviciosCentral.getBibrefsList();
        bibrefs.addAll(serviciosLocal.getBibrefsList());
        return bibrefs;
    }

    @Override
    public int getTotalBibrefs(Bibrefs bibrefs) {
        int total = serviciosCentral.getTotalBibrefs(bibrefs);
        total += serviciosLocal.getTotalBibrefs(bibrefs);
        return total;
    }

    @Override
    public List<Bibrefs> getListBibrefs(Bibrefs filter, int start, int pageSize, boolean paged) {
        List<Bibrefs> bibrefs = serviciosCentral.getListBibrefs(filter, start, pageSize, paged);
        bibrefs.addAll(serviciosLocal.getListBibrefs(filter, start, pageSize, paged));
        return bibrefs;
    }

//-----------------------------------Changes---------------------------
    @Override
    public List<Changes> getChangesList() {
        List<Changes> changes = serviciosCentral.getChangesList();
        changes.addAll(serviciosLocal.getChangesList());
        return changes;
    }

    @Override
    public int getTotalChanges(Changes changes) {
        int total = serviciosCentral.getTotalChanges(changes);
        total += serviciosLocal.getTotalChanges(changes);
        return total;
    }

    @Override
    public List<Changes> getListChanges(Changes filter, int start, int pageSize, boolean paged) {
        List<Changes> changes = serviciosCentral.getListChanges(filter, start, pageSize, paged);
        changes.addAll(serviciosLocal.getListChanges(filter, start, pageSize, paged));
        return changes;
    }

//-----------------------------------Cntry---------------------------
    @Override
    public List<Cntry> getCntryList() {

        List<Cntry> cntries = new ArrayList<Cntry>();
        try {
            cntries.addAll(serviciosCentral.getCntryList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            cntries.addAll(serviciosLocal.getCntryList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cntries;
    }

    @Override
    public int getTotalCntry(Cntry cntry) {
        int total = serviciosCentral.getTotalCntry(cntry);
        total += serviciosLocal.getTotalCntry(cntry);
        return total;
    }

    @Override
    public List<Cntry> getListCntry(Cntry filter, int start, int pageSize, boolean paged) {
        List<Cntry> cntries = serviciosCentral.getListCntry(filter, start, pageSize, paged);
        cntries.addAll(serviciosLocal.getListCntry(filter, start, pageSize, paged));
        return cntries;
    }

//-----------------------------------DataC---------------------------
    @Override
    public List<DataC> getDataCList() {
        List<DataC> dataCs = serviciosCentral.getDataCList();
        dataCs.addAll(serviciosLocal.getDataCList());
        return dataCs;
    }

    @Override
    public int getTotalDataC(DataC dataC) {
        int total = serviciosCentral.getTotalDataC(dataC);
        total += serviciosLocal.getTotalDataC(dataC);
        return total;
    }

    @Override
    public List<DataC> getListDataC(DataC filter, int start, int pageSize, boolean paged) {
        List<DataC> dataCs = serviciosCentral.getListDataC(filter, start, pageSize, paged);
        dataCs.addAll(serviciosLocal.getListDataC(filter, start, pageSize, paged));
        return dataCs;
    }

//-----------------------------------DataN---------------------------
    @Override
    public List<DataN> getDataNList() {
        List<DataN> dataNs = serviciosCentral.getDataNList();
        dataNs.addAll(serviciosLocal.getDataNList());
        return dataNs;
    }

    @Override
    public int getTotalDataN(DataN dataN) {
        int total = serviciosCentral.getTotalDataN(dataN);
        total += serviciosLocal.getTotalDataN(dataN);
        return total;
    }

    @Override
    public List<DataN> getListDataN(DataN filter, int start, int pageSize, boolean paged) {
        List<DataN> dataNs = serviciosCentral.getListDataN(filter, start, pageSize, paged);
        dataNs.addAll(serviciosLocal.getListDataN(filter, start, pageSize, paged));
        return dataNs;
    }

//-----------------------------------DataT--------------------------
    @Override
    public List<DataT> getDataTList() {
        List<DataT> dataTs = serviciosCentral.getDataTList();
        dataTs.addAll(serviciosLocal.getDataTList());
        return dataTs;
    }

    @Override
    public int getTotalDataT(DataT dataT) {
        int total = serviciosCentral.getTotalDataT(dataT);
        total += serviciosLocal.getTotalDataT(dataT);
        return total;
    }

    @Override
    public List<DataT> getListDataT(DataT filter, int start, int pageSize, boolean paged) {
        List<DataT> dataTs = serviciosCentral.getListDataT(filter, start, pageSize, paged);
        dataTs.addAll(serviciosLocal.getListDataT(filter, start, pageSize, paged));
        return dataTs;
    }

//-----------------------------------Datattr---------------------------
    @Override
    public List<Datattr> getDatattrList() {
        List<Datattr> datattrs = serviciosCentral.getDatattrList();
        datattrs.addAll(serviciosLocal.getDatattrList());
        return datattrs;
    }

    @Override
    public int getTotalDatattr(Datattr datattr) {
        int total = serviciosCentral.getTotalDatattr(datattr);
        total += serviciosLocal.getTotalDatattr(datattr);
        return total;
    }

    @Override
    public List<Datattr> getListDatattr(Datattr filter, int start, int pageSize, boolean paged) {
        List<Datattr> datattrs = serviciosCentral.getListDatattr(filter, start, pageSize, paged);
        datattrs.addAll(serviciosLocal.getListDatattr(filter, start, pageSize, paged));
        return datattrs;
    }

//-----------------------------------Dmsattr---------------------------
    @Override
    public List<Dmsattr> getDmsattrList() {
        List<Dmsattr> dmsattrs = serviciosCentral.getDmsattrList();
        dmsattrs.addAll(serviciosLocal.getDmsattrList());
        return dmsattrs;
    }

    @Override
    public int getTotalDmsattr(Dmsattr dmsattr) {
        int total = serviciosCentral.getTotalDmsattr(dmsattr);
        total += serviciosLocal.getTotalDmsattr(dmsattr);
        return total;
    }

    @Override
    public List<Dmsattr> getListDmsattr(Dmsattr filter, int start, int pageSize, boolean paged) {
        List<Dmsattr> dmsattrs = serviciosCentral.getListDmsattr(filter, start, pageSize, paged);
        dmsattrs.addAll(serviciosLocal.getListDmsattr(filter, start, pageSize, paged));
        return dmsattrs;
    }

    @Override
    public Dmsattr getDmsattrByDmsatrecAndDmsatype(Dmsattr dmsattr) {
        if (dmsattr == null) {
            return null;
        }
        if (dmsattr.getDmsatrec() > 0) {
            return serviciosCentral.getDmsattrByDmsatrecAndDmsatype(dmsattr);
        } else {
            return serviciosLocal.getDmsattrByDmsatrecAndDmsatype(dmsattr);
        }
    }

//-----------------------------------Dudflds---------------------------
    @Override
    public List<Dudflds> getDudfldsList() {
        List<Dudflds> dudflds = serviciosCentral.getDudfldsList();
        dudflds.addAll(serviciosLocal.getDudfldsList());
        return dudflds;
    }

    @Override
    public int getTotalDudflds(Dudflds dudflds) {
        int total = serviciosCentral.getTotalDudflds(dudflds);
        total += serviciosLocal.getTotalDudflds(dudflds);
        return total;
    }

    @Override
    public List<Dudflds> getListDudflds(Dudflds filter, int start, int pageSize, boolean paged) {
        List<Dudflds> dudflds = serviciosCentral.getListDudflds(filter, start, pageSize, paged);
        dudflds.addAll(serviciosLocal.getListDudflds(filter, start, pageSize, paged));
        return dudflds;
    }

//-----------------------------------Effect---------------------------
    @Override
    public List<Effect> getEffectList() {
        List<Effect> effects = serviciosCentral.getEffectList();
        effects.addAll(serviciosLocal.getEffectList());
        return effects;
    }

    @Override
    public int getTotalEffect(Effect effect) {
        int total = serviciosCentral.getTotalEffect(effect);
        total += serviciosLocal.getTotalEffect(effect);
        return total;
    }

    @Override
    public List<Effect> getListEffect(Effect filter, int start, int pageSize, boolean paged) {
        List<Effect> effects = serviciosCentral.getListEffect(filter, start, pageSize, paged);
        effects.addAll(serviciosLocal.getListEffect(filter, start, pageSize, paged));
        return effects;
    }

    public List<Effect> getEffectsByEffectsids(final List effectsIds) {
        List<Effect> effects = serviciosCentral.getEffectsByEffectsids(effectsIds);
        if (effects != null) {
            if (effects.size() > 0) {
                return effects;
            } else {
                return serviciosLocal.getEffectsByEffectsids(effectsIds);
            }
        } else {
            return serviciosLocal.getEffectsByEffectsids(effectsIds);
        }
    }

//-----------------------------------Factor---------------------------
    @Override
    public List<Factor> getFactorList() {
        List<Factor> factors = serviciosCentral.getFactorList();
        factors.addAll(serviciosLocal.getFactorList());
        return factors;
    }

    @Override
    public int getTotalFactor(Factor factor) {
        int total = serviciosCentral.getTotalFactor(factor);
        total += serviciosLocal.getTotalFactor(factor);
        return total;
    }

    @Override
    public List<Factor> getListFactor(Factor filter, int start, int pageSize, boolean paged) {
        List<Factor> factors = serviciosCentral.getListFactor(filter, start, pageSize, paged);
        factors.addAll(serviciosLocal.getListFactor(filter, start, pageSize, paged));
        return factors;
    }

    public List<Factor> getMainFactorsByStudyid(Integer studyid) {
        if (studyid > 0) {
            return this.serviciosCentral.getMainFactorsByStudyid(studyid);
        } else {
            return this.serviciosLocal.getMainFactorsByStudyid(studyid);
        }
    }

    public List<Factor> getGroupFactorsByStudyidAndFactorid(Integer studyid, Integer factorid) {
        if (studyid > 0) {
            return this.serviciosCentral.getGroupFactorsByStudyidAndFactorid(studyid, factorid);
        } else {
            return this.serviciosLocal.getGroupFactorsByStudyidAndFactorid(studyid, factorid);
        }
    }

    public Factor getTrialFactor(Integer studyid) {
        List<Factor> factoresPincipales;
        if (studyid > 0) {
            factoresPincipales = this.serviciosCentral.getMainFactorsByStudyid(studyid);
        } else {
            factoresPincipales = this.serviciosLocal.getMainFactorsByStudyid(studyid);
        }
        if (factoresPincipales != null) {
            for (Factor factor : factoresPincipales) {
                factor = HelperFactor.getFactorFillingFullWhitoutLevels(factor, this, studyid);
                String nombreTrialScale = factor.getMeasuredin().getTraits().getTrname() + factor.getMeasuredin().getScales().getScname();
                if (Workbook.getStringWithOutBlanks(nombreTrialScale).equals(Workbook.TRIAL_INSTANCE_NUMBER)) {
                    return factor;
                }
            }
        }
        return null;
    }

    public List<Factor> getFactorsByFactorsids(List<Integer> factorIds) {
        if (factorIds != null) {
            if (factorIds.size() > 0) {
                if (factorIds.get(0) > 0) {
                    return this.serviciosCentral.getFactorsByFactorsids(factorIds);
                } else {
                    return this.serviciosLocal.getFactorsByFactorsids(factorIds);
                }
            } else {
                return new ArrayList<Factor>();
            }
        } else {
            return new ArrayList<Factor>();
        }
    }

    public Factor getFactorByStudyidAndFname(Integer studyid, String fname) {
        if (studyid > 0) {
            return this.serviciosCentral.getFactorByStudyidAndFname(studyid, fname);
        } else {
            return this.serviciosLocal.getFactorByStudyidAndFname(studyid, fname);
        }
    }

//-----------------------------------Georef---------------------------
    @Override
    public List<Georef> getGeorefList() {
        List<Georef> georefs = serviciosCentral.getGeorefList();
        georefs.addAll(serviciosLocal.getGeorefList());
        return georefs;
    }

    @Override
    public int getTotalGeoref(Georef georef) {
        int total = serviciosCentral.getTotalGeoref(georef);
        total += serviciosLocal.getTotalGeoref(georef);
        return total;
    }

    @Override
    public List<Georef> getListGeoref(Georef filter, int start, int pageSize, boolean paged) {
        List<Georef> georefs = serviciosCentral.getListGeoref(filter, start, pageSize, paged);
        georefs.addAll(serviciosLocal.getListGeoref(filter, start, pageSize, paged));
        return georefs;
    }

//-----------------------------------Germplsm---------------------------
    public List<Listdata> addNewsGermplasm(Listnms listnms, List<Listdata> listdatas, Integer userId) {
        HelperGermplasm helperGermplasm = new HelperGermplasm(listnms, this, serviciosLocal, userId);
        helperGermplasm.saveGermplasmNews(listdatas, listnms);
        return helperGermplasm.getListdatas();
    }

    @Override
    public List<Germplsm> getGermplsmList() {
        List<Germplsm> germplsms = serviciosCentral.getGermplsmList();
        germplsms.addAll(serviciosLocal.getGermplsmList());
        return germplsms;
    }

    @Override
    public int getTotalGermplsm(Germplsm germplsm) {
        int total = serviciosCentral.getTotalGermplsm(germplsm);
        total += serviciosLocal.getTotalGermplsm(germplsm);
        return total;
    }

    @Override
    public List<Germplsm> getListGermplsm(Germplsm filter, int start, int pageSize, boolean paged) {
        List<Germplsm> germplsms = serviciosCentral.getListGermplsm(filter, start, pageSize, paged);
        germplsms.addAll(serviciosLocal.getListGermplsm(filter, start, pageSize, paged));
        return germplsms;
    }

    public Germplsm getGermplsm(Integer gid) {
        if (gid == null) {
            log.error("No se puede rucuperar ningun gid por que biene nulo.");
            return null;
        }
        if (gid > 0) {
            return this.serviciosCentral.getGermplsm(gid);
        } else {
            return this.serviciosLocal.getGermplsm(gid);
        }
    }

    public Germplsm getGermplsmByTidTrialPlot(
            Integer studyId,
            Integer trial,
            Integer plot) {
        return HelperGermplasm.getQueryCenterTemp(
                this,
                serviciosLocal,
                serviciosCentral,
                studyId,
                trial,
                plot);
    }

    public List<Germplsm> getGermplsmListByStudyAndTrial(
            Integer studyId,
            Integer trial) {
        return HelperGermplasm.getGermplsmListByStudyAndTrial(
                this,
                serviciosLocal,
                serviciosCentral,
                studyId,
                trial);
    }

//-----------------------------------Institut---------------------------
    @Override
    public List<Institut> getInstitutList() {
        List<Institut> instituts = serviciosCentral.getInstitutList();
        instituts.addAll(serviciosLocal.getInstitutList());
        return instituts;
    }

    @Override
    public int getTotalInstitut(Institut institut) {
        int total = serviciosCentral.getTotalInstitut(institut);
        total += serviciosLocal.getTotalInstitut(institut);
        return total;
    }

    @Override
    public List<Institut> getListInstitut(Institut filter, int start, int pageSize, boolean paged) {
        List<Institut> instituts = serviciosCentral.getListInstitut(filter, start, pageSize, paged);
        instituts.addAll(serviciosLocal.getListInstitut(filter, start, pageSize, paged));
        return instituts;
    }

//-----------------------------------LevelC---------------------------
    @Override
    public List<Instln> getInstlnList() {
        List<Instln> instlns = serviciosCentral.getInstlnList();
        instlns.addAll(serviciosLocal.getInstlnList());
        return instlns;
    }

    @Override
    public int getTotalInstln(Instln instln) {
        int total = serviciosCentral.getTotalInstln(instln);
        total += serviciosLocal.getTotalInstln(instln);
        return total;
    }

    @Override
    public List<Instln> getListInstln(Instln filter, int start, int pageSize, boolean paged) {
        List<Instln> instlns = serviciosCentral.getListInstln(filter, start, pageSize, paged);
        instlns.addAll(serviciosLocal.getListInstln(filter, start, pageSize, paged));
        return instlns;
    }

    @Override
    public Instln getLocalInstln() {
        Instln instln = null;

        List<Instln> instlnList = serviciosLocal.getInstlnList();

        // if there are records in Intsln then take first record
        if (instlnList.size() > 0) {
            instln = instlnList.get(0);
        } else {
            // if there aren't record, then return an empty Instln object
            // with 'Unknown' as desc
            instln = new Instln();
            instln.setIdesc("Unknown");
        }

        return instln;
    }
//-----------------------------------LevelC---------------------------

    @Override
    public List<LevelC> getLevelCList() {
        List<LevelC> levelCs = serviciosCentral.getLevelCList();
        levelCs.addAll(serviciosLocal.getLevelCList());
        return levelCs;
    }

    @Override
    public int getTotalLevelC(LevelC levelC) {
        int total = serviciosCentral.getTotalLevelC(levelC);
        total += serviciosLocal.getTotalLevelC(levelC);
        return total;
    }

    @Override
    public List<LevelC> getListLevelC(LevelC filter, int start, int pageSize, boolean paged) {
        List<LevelC> levelCs = serviciosCentral.getListLevelC(filter, start, pageSize, paged);
        levelCs.addAll(serviciosLocal.getListLevelC(filter, start, pageSize, paged));
        return levelCs;
    }

    public List<LevelC> getLevelsCByLabelid(Integer labelid) {
        if (labelid > 0) {
            return this.serviciosCentral.getLevelsCByLabelid(labelid);
        } else {
            return this.serviciosLocal.getLevelsCByLabelid(labelid);
        }
    }

//-----------------------------------LevelN---------------------------
    @Override
    public List<LevelN> getLevelNList() {
        List<LevelN> levelNs = serviciosCentral.getLevelNList();
        levelNs.addAll(serviciosLocal.getLevelNList());
        return levelNs;
    }

    @Override
    public int getTotalLevelN(LevelN levelN) {
        int total = serviciosCentral.getTotalLevelN(levelN);
        total += serviciosLocal.getTotalLevelN(levelN);
        return total;
    }

    @Override
    public List<LevelN> getListLevelN(LevelN filter, int start, int pageSize, boolean paged) {
        List<LevelN> levelNs = serviciosCentral.getListLevelN(filter, start, pageSize, paged);
        levelNs.addAll(serviciosLocal.getListLevelN(filter, start, pageSize, paged));
        return levelNs;
    }

    public List<LevelN> getLevelnByLabelid(Integer labelid) {
        if (labelid > 0) {
            return this.serviciosCentral.getLevelnByLabelid(labelid);
        } else {
            return this.serviciosLocal.getLevelnByLabelid(labelid);
        }
    }

//-----------------------------------LevelT---------------------------
    @Override
    public List<LevelT> getLevelTList() {
        List<LevelT> levelTs = serviciosCentral.getLevelTList();
        levelTs.addAll(serviciosLocal.getLevelTList());
        return levelTs;
    }

    @Override
    public int getTotalLevelT(LevelT levelT) {
        int total = serviciosCentral.getTotalLevelT(levelT);
        total += serviciosLocal.getTotalLevelT(levelT);
        return total;
    }

    @Override
    public List<LevelT> getListLevelT(LevelT filter, int start, int pageSize, boolean paged) {
        List<LevelT> levelTs = serviciosCentral.getListLevelT(filter, start, pageSize, paged);
        levelTs.addAll(serviciosLocal.getListLevelT(filter, start, pageSize, paged));
        return levelTs;
    }

//-----------------------------------Levels---------------------------
    @Override
    public List<Levels> getLevelsList() {
        List<Levels> levels = serviciosCentral.getLevelsList();
        levels.addAll(serviciosLocal.getLevelsList());
        return levels;
    }

    @Override
    public int getTotalLevels(Levels levels) {
        int total = serviciosCentral.getTotalLevels(levels);
        total += serviciosLocal.getTotalLevels(levels);
        return total;
    }

    @Override
    public List<Levels> getListLevels(Levels filter, int start, int pageSize, boolean paged) {
        List<Levels> levels = serviciosCentral.getListLevels(filter, start, pageSize, paged);
        levels.addAll(serviciosLocal.getListLevels(filter, start, pageSize, paged));
        return levels;
    }

//-----------------------------------Listdata---------------------------
    @Override
    public List<Listdata> getListdataList() {
        List<Listdata> listdatas = serviciosCentral.getListdataList();
        listdatas.addAll(serviciosLocal.getListdataList());
        return listdatas;
    }

    @Override
    public int getTotalListdata(Listdata listdata) {
        int total = serviciosCentral.getTotalListdata(listdata);
        total += serviciosLocal.getTotalListdata(listdata);
        return total;
    }

    @Override
    public List<Listdata> getListListdata(Listdata filter, int start, int pageSize, boolean paged) {
        List<Listdata> listdatas = new ArrayList<Listdata>();
        if (filter.getListdataPK().getListid().intValue() > 0) {
            listdatas.addAll(serviciosCentral.getListListdata(filter, start, pageSize, paged));
        } else {
            listdatas.addAll(serviciosLocal.getListListdata(filter, start, pageSize, paged));
        }
        return listdatas;
    }

    @Override
    public List<Listdata> getListListdataFiltro(Listdata filter, int start, int pageSize, boolean paged) {
        List<Listdata> listdatas = serviciosCentral.getListListdataFiltro(filter, start, pageSize, paged);
        listdatas.addAll(serviciosLocal.getListListdataFiltro(filter, start, pageSize, paged));
        return listdatas;
    }

//-----------------------------------Listnms---------------------------
    @Override
    public List<Listnms> getListnmsList() {
        List<Listnms> listnms = serviciosCentral.getListnmsList();
        listnms.addAll(serviciosLocal.getListnmsList());
        return listnms;
    }

    @Override
    public int getTotalListnms(Listnms listnms) {
        int total = serviciosCentral.getTotalListnms(listnms);
        total += serviciosLocal.getTotalListnms(listnms);
        return total;
    }

    @Override
    public List<Listnms> getListListnms(Listnms filter, int start, int pageSize, boolean paged) {
        List<Listnms> listnms = serviciosCentral.getListListnms(filter, start, pageSize, paged);
        listnms.addAll(serviciosLocal.getListListnms(filter, start, pageSize, paged));
        return listnms;
    }

    @Override
    public Listnms getListnms(Integer idListnms) {
        if (idListnms.intValue() < 0) {
            return this.serviciosLocal.getListnms(idListnms);
        } else {
            return this.serviciosCentral.getListnms(idListnms);
        }
    }
    
    @Override
    public Listnms getFullListnms(Integer idListnms){
        Listnms listnms;
        if(idListnms.intValue() < 0){
            listnms = this.serviciosLocal.getListnms(idListnms);
            listnms.setLisdatas(this.serviciosLocal.getListdataByIdlistnms(idListnms));
            listnms = this.serviciosLocal.getNamesCentral(listnms);
            listnms = this.serviciosCentral.getNamesCentral(listnms);
            //Recuperar la informacion local pripero y despues la central
        }else{
            listnms = this.serviciosCentral.getListnms(idListnms);
            listnms.setLisdatas(this.serviciosCentral.getListdataByIdlistnms(idListnms));
            //Recuperar la informacion central
            listnms = this.serviciosCentral.getNamesCentral(listnms);
        }
        return listnms;
    }

//-----------------------------------Location---------------------------
    @Override
    public List<Location> getLocationList() {
        List<Location> locations = serviciosCentral.getLocationList();
        locations.addAll(serviciosLocal.getLocationList());
        return locations;
    }

    @Override
    public int getTotalLocation(Location location) {
        int total = serviciosCentral.getTotalLocation(location);
        total += serviciosLocal.getTotalLocation(location);
        return total;
    }

    @Override
    public List<Location> getListLocation(Location filter, int start, int pageSize, boolean paged) {
        List<Location> locations = serviciosCentral.getListLocation(filter, start, pageSize, paged);
        locations.addAll(serviciosLocal.getListLocation(filter, start, pageSize, paged));
        return locations;
    }

    @Override
    public List<Location> getLocationsByCountryLocidRange(final Integer countryId, final Integer fromLocid, final Integer toLocid) {
        List<Location> locations = serviciosCentral.getLocationsByCountryLocidRange(countryId, fromLocid, toLocid);
        locations.addAll(serviciosLocal.getLocationsByCountryLocidRange(countryId, fromLocid, toLocid));
        return locations;
    }
//-----------------------------------Locdes---------------------------

    @Override
    public List<Locdes> getLocdesList() {
        List<Locdes> locdes = serviciosCentral.getLocdesList();
        locdes.addAll(serviciosLocal.getLocdesList());
        return locdes;
    }

    @Override
    public int getTotalLocdes(Locdes locdes) {
        int total = serviciosCentral.getTotalLocdes(locdes);
        total += serviciosLocal.getTotalLocdes(locdes);
        return total;
    }

    @Override
    public List<Locdes> getListLocdes(Locdes filter, int start, int pageSize, boolean paged) {
        List<Locdes> locdes = serviciosCentral.getListLocdes(filter, start, pageSize, paged);
        locdes.addAll(serviciosLocal.getListLocdes(filter, start, pageSize, paged));
        return locdes;
    }

//-----------------------------------Measuredin---------------------------
    @Override
    public void addMeasuredinCentral(Measuredin measuredin) {
        this.serviciosCentral.addMeasuredin(measuredin);
    }

    @Override
    public void addMeasuredinLocal(Measuredin measuredin) {
        this.serviciosLocal.addMeasuredin(measuredin);
    }

    @Override
    public List<Measuredin> getMeasuredinList() {
        List<Measuredin> measuredins = serviciosCentral.getMeasuredinList();
        measuredins.addAll(serviciosLocal.getMeasuredinList());
        return measuredins;
    }

    @Override
    public Measuredin getMeasuredinByTraitidScaleidTmethid(Integer tid, Integer scaleId, Integer tmethid) {

        Traits traitsTemp = null;
        if (tid > 0) {
            traitsTemp = this.serviciosCentral.getTraits(tid);
        } else {
            traitsTemp = this.serviciosLocal.getTraits(tid);
        }
        if (traitsTemp == null) {
            log.error("Catnt read tid: " + tid);
        }
        TmsMethod tmsMethodTemp = null;
        if (tmethid > 0) {
            tmsMethodTemp = this.serviciosCentral.getTmsMethod(tmethid);
        } else {
            tmsMethodTemp = this.serviciosLocal.getTmsMethod(tmethid);
        }
        if (tmsMethodTemp == null) {
            log.error("Catnt read tmethid: " + tmethid);
        }
        Scales scalesTemp = null;
        if (scaleId > 0) {
            scalesTemp = this.serviciosCentral.getScales(scaleId);
            if (scalesTemp == null) {
                Scale scale = this.serviciosCentral.getScale(scaleId);
                scalesTemp = new Scales(null, scale.getScname(), scale.getSctype(), null, null);
                scalesTemp = this.serviciosCentral.getScalesByScnameAndSctype(scalesTemp);
            }
        } else {
            scalesTemp = this.serviciosLocal.getScales(scaleId);
            if (scalesTemp == null) {
                Scale scale = this.serviciosLocal.getScale(scaleId);
                scalesTemp = new Scales(null, scale.getScname(), scale.getSctype(), null, null);
                scalesTemp = this.serviciosLocal.getScalesByScnameAndSctype(scalesTemp);
            }
        }
        if (scalesTemp == null) {
            log.error("Catnt read scaleId: " + scaleId);
        }

        Measuredin measuredin = new Measuredin(traitsTemp.getTid(), scaleId, tmethid);

        measuredin = this.serviciosCentral.getMeasuredinByTraitidScaleidTmethid(measuredin);
        if (measuredin != null) {
            measuredin.setTraits(traitsTemp);
            measuredin.setScales(scalesTemp);
            measuredin.setTmsMethod(tmsMethodTemp);
        } else {
            measuredin = new Measuredin(tid, scaleId, tmethid);
            measuredin = this.serviciosLocal.getMeasuredinByTraitidScaleidTmethid(measuredin);
            if (measuredin != null) {
                measuredin.setTraits(traitsTemp);
                measuredin.setScales(scalesTemp);
                measuredin.setTmsMethod(tmsMethodTemp);
            }
        }
        if (measuredin == null) {
            log.error("Catnt read measuredin tid: " + tid + " scaleid: " + scaleId + " tmethid: " + tmethid);
        }
        return measuredin;
    }

    public Measuredin getMeasuredinByTraitidAndScaleid(String trname, String scname) {

        Traits traitsTemp = null;
        if (trname != null) {
            traitsTemp = this.serviciosCentral.getTraitsByTrname(new Traits(trname));
        } else {
            traitsTemp = this.serviciosLocal.getTraitsByTrname(new Traits(trname));
        }
        if (traitsTemp == null) {
            log.error("Catnt read trname: " + trname);
        }
        Scales scalesTemp = null;
        if (scname != null) {
            scalesTemp = this.serviciosCentral.getScalesByScname(new Scales(scname));
            if (scalesTemp == null) {
                log.error("No se encontro el scname: " + scname + " en central");
            }
        } else {
            scalesTemp = this.serviciosLocal.getScalesByScname(new Scales(scname));
            if (scalesTemp == null) {
                log.error("No se encontro el scname: " + scname + " en local");
            }
        }
        if (scalesTemp == null) {
            log.error("Catnt read scname: " + scname);
        }
        Measuredin measuredin = null;
        if (traitsTemp != null && scalesTemp != null) {
            measuredin = new Measuredin(traitsTemp.getTid(), scalesTemp.getScaleid());
            measuredin = this.serviciosCentral.getMeasuredinByTraitidAndScaleid(measuredin);
            if (measuredin != null) {
                measuredin.setTraits(traitsTemp);
                measuredin.setScales(scalesTemp);
            } else {
                measuredin = new Measuredin(traitsTemp.getTid(), scalesTemp.getScaleid());
                measuredin = this.serviciosLocal.getMeasuredinByTraitidAndScaleid(measuredin);
                if (measuredin != null) {
                    measuredin.setTraits(traitsTemp);
                    measuredin.setScales(scalesTemp);
                }
            }
            if (measuredin == null) {
                log.error("Catnt read measuredin trname: " + trname + " scname: " + scname);
            }
        }
        return measuredin;
    }

    @Override
    public int getTotalMeasuredin(Measuredin measuredin) {
        int total = serviciosCentral.getTotalMeasuredin(measuredin);
        total += serviciosLocal.getTotalMeasuredin(measuredin);
        return total;
    }

    @Override
    public List<Measuredin> getListMeasuredin(Measuredin filter, int start, int pageSize, boolean paged) {
        List<Measuredin> measuredins = serviciosCentral.getListMeasuredin(filter, start, pageSize, paged);
        measuredins.addAll(serviciosLocal.getListMeasuredin(filter, start, pageSize, paged));
        return measuredins;
    }

    /**
     * Gets a list of measured in list by trait id
     *
     * @param traitId Trait ID
     * @return List of measuredin objects filled with it's scale and method
     */
    @Override
    public List<Measuredin> getMeasuredInListByTrait(final Integer traitId) {
        List<Measuredin> measuredinList = new ArrayList<Measuredin>();

        CommonServices services = null;

        if (traitId.intValue() > 0) {
            measuredinList = serviciosCentral.getMeasuredInListByTrait(traitId);
        } else {
            measuredinList = serviciosLocal.getMeasuredInListByTrait(traitId);
        }

        for (Measuredin measuredin : measuredinList) {
            Scales scales = null;
            TmsMethod tmsMethod = null;

            if (measuredin.getScaleid().intValue() > 0) {
                scales = serviciosCentral.getScales(measuredin.getScaleid());
                services = serviciosCentral;
            } else {
                scales = serviciosLocal.getScales(measuredin.getScaleid());
                services = serviciosLocal;
            }

            if (measuredin.getTmethid().intValue() > 0) {
                tmsMethod = serviciosCentral.getTmsMethod(measuredin.getTmethid());
            } else {
                tmsMethod = serviciosLocal.getTmsMethod(measuredin.getTmethid());
            }


            measuredin.setScales(scales);
            measuredin.setTmsMethod(tmsMethod);

            // now fill each Scale definition according to Its scale.
            if (scales.getSctype().equals(Scales.SCALE_TYPE_CONTINOUS)) {
                TmsScaleCon scaleCon = services.getScaleConByMeasuredinId(measuredin.getMeasuredinid());
                // if not found it's because needs to be added
                if (scaleCon == null) {
                    scaleCon = new TmsScaleCon(true);
                }
                measuredin.setTmsScaleDef(scaleCon);
            } else {
                TmsScaleDis scaleDis = services.getScaleDisByMeasuredinId(measuredin.getMeasuredinid());
                if (scaleDis == null) {
                    scaleDis = new TmsScaleDis(true);
                }
                measuredin.setTmsScaleDef(scaleDis);
            }
        }
        return measuredinList;
    }
//-----------------------------------Methods---------------------------

    @Override
    public List<Methods> getMethodsList() {
        List<Methods> methods = serviciosCentral.getMethodsList();
        methods.addAll(serviciosLocal.getMethodsList());
        return methods;
    }

    @Override
    public int getTotalMethods(Methods methods) {
        int total = serviciosCentral.getTotalMethods(methods);
        total += serviciosLocal.getTotalMethods(methods);
        return total;
    }

    @Override
    public List<Methods> getListMethods(Methods filter, int start, int pageSize, boolean paged) {
        List<Methods> methods = serviciosCentral.getListMethods(filter, start, pageSize, paged);
        methods.addAll(serviciosLocal.getListMethods(filter, start, pageSize, paged));
        return methods;
    }

    /**
     * Add a tmsmethod to local database
     *
     * @param tmsMethod
     */
    @Override
    public void addTmsMethod(TmsMethod tmsMethod) {
        serviciosLocal.addTmsMethod(tmsMethod);
    }

    /**
     * Updates a TMSMETOD in local database
     */
    @Override
    public void updateTmsMethod(TmsMethod tmsMethod) {
        serviciosLocal.updateTmsMethod(tmsMethod);
    }

//-----------------------------------Names---------------------------
    @Override
    public List<Names> getNamesList() {
        List<Names> names = serviciosCentral.getNamesList();
        names.addAll(serviciosLocal.getNamesList());
        return names;
    }

    @Override
    public int getTotalNames(Names names) {
        int total = serviciosCentral.getTotalNames(names);
        total += serviciosLocal.getTotalNames(names);
        return total;
    }

    @Override
    public List<Names> getListNames(Names filter, int start, int pageSize, boolean paged) {
        List<Names> names = serviciosCentral.getListNames(filter, start, pageSize, paged);
        names.addAll(serviciosLocal.getListNames(filter, start, pageSize, paged));
        return names;
    }

    public String getNextMaxForBCID(Integer studyId, String cadena, Integer ntype) {
        if (studyId > 0) {
            return this.serviciosCentral.getNextMaxForBCID(cadena, ntype);
        } else {
            return this.serviciosLocal.getNextMaxForBCID(cadena, ntype);
        }
    }

    public Names getNamesByGid(Germplsm germplasm, Boolean preferido) {
        if (germplasm == null) {
            return null;
        } else if (germplasm.getGid() > 0) {
            return this.serviciosCentral.getNamesByGid(germplasm, preferido);
        } else {
            return this.serviciosLocal.getNamesByGid(germplasm, preferido);
        }
    }

    public Integer getMaxForSelection(Integer studyId, String cadena, Integer ntype) {
        Integer tempC = this.serviciosCentral.getMaxForSelection(cadena, ntype);
        Integer tempL = this.serviciosLocal.getMaxForSelection(cadena, ntype);
        if (tempC > tempL) {
            return tempC;
        }else{
            return tempL;
        }
    }

//-----------------------------------Obsunit---------------------------
    @Override
    public List<Obsunit> getObsunitList() {
        List<Obsunit> obsunits = serviciosCentral.getObsunitList();
        obsunits.addAll(serviciosLocal.getObsunitList());
        return obsunits;
    }

    @Override
    public int getTotalObsunit(Obsunit obsunit) {
        int total = serviciosCentral.getTotalObsunit(obsunit);
        total += serviciosLocal.getTotalObsunit(obsunit);
        return total;
    }

    @Override
    public List<Obsunit> getListObsunit(Obsunit filter, int start, int pageSize, boolean paged) {
        List<Obsunit> obsunits = serviciosCentral.getListObsunit(filter, start, pageSize, paged);
        obsunits.addAll(serviciosLocal.getListObsunit(filter, start, pageSize, paged));
        return obsunits;
    }

//-----------------------------------Oindex---------------------------
    @Override
    public List<Oindex> getOindexList() {
        List<Oindex> oindexs = serviciosCentral.getOindexList();
        oindexs.addAll(serviciosLocal.getOindexList());
        return oindexs;
    }

    @Override
    public int getTotalOindex(Oindex oindex) {
        int total = serviciosCentral.getTotalOindex(oindex);
        total += serviciosLocal.getTotalOindex(oindex);
        return total;
    }

    @Override
    public List<Oindex> getListOindex(Oindex filter, int start, int pageSize, boolean paged) {
        List<Oindex> oindexs = serviciosCentral.getListOindex(filter, start, pageSize, paged);
        oindexs.addAll(serviciosLocal.getListOindex(filter, start, pageSize, paged));
        return oindexs;
    }

    //-----------------------------------Persons---------------------------
    @Override
    public List<Persons> getPersonsList() {
        List<Persons> persons = serviciosCentral.getPersonsList();
        persons.addAll(serviciosLocal.getPersonsList());
        return persons;
    }

    @Override
    public int getTotalPersons(Persons persons) {
        int total = serviciosCentral.getTotalPersons(persons);
        total += serviciosLocal.getTotalPersons(persons);
        return total;
    }

    @Override
    public List<Persons> getListPersons(Persons personsFilter, int start,
            int pageSize, boolean paged) {
        List<Persons> persons = serviciosCentral.getListPersons(personsFilter, start, pageSize, paged);
        persons.addAll(serviciosLocal.getListPersons(personsFilter, start, pageSize, paged));
        return persons;
    }

//-----------------------------------Progntrs---------------------------
    @Override
    public List<Progntrs> getProgntrsList() {
        List<Progntrs> progntrs = serviciosCentral.getProgntrsList();
        progntrs.addAll(serviciosLocal.getProgntrsList());
        return progntrs;
    }

    @Override
    public int getTotalProgntrs(Progntrs progntrs) {
        int total = serviciosCentral.getTotalProgntrs(progntrs);
        total += serviciosLocal.getTotalProgntrs(progntrs);
        return total;
    }

    @Override
    public List<Progntrs> getListProgntrs(Progntrs filter, int start, int pageSize, boolean paged) {
        List<Progntrs> progntrs = serviciosCentral.getListProgntrs(filter, start, pageSize, paged);
        progntrs.addAll(serviciosLocal.getListProgntrs(filter, start, pageSize, paged));
        return progntrs;
    }

//-----------------------------------Represtn---------------------------
    @Override
    public List<Represtn> getReprestnList() {
        List<Represtn> represtns = serviciosCentral.getReprestnList();
        represtns.addAll(serviciosLocal.getReprestnList());
        return represtns;
    }

    @Override
    public int getTotalReprestn(Represtn represtn) {
        int total = serviciosCentral.getTotalReprestn(represtn);
        total += serviciosLocal.getTotalReprestn(represtn);
        return total;
    }

    @Override
    public List<Represtn> getListReprestn(Represtn filter, int start, int pageSize, boolean paged) {
        List<Represtn> represtns = serviciosCentral.getListReprestn(filter, start, pageSize, paged);
        represtns.addAll(serviciosLocal.getListReprestn(filter, start, pageSize, paged));
        return represtns;
    }

//-----------------------------------Scale---------------------------
    @Override
    public List<Scale> getScaleList() {
        List<Scale> scales = serviciosCentral.getScaleList();
        scales.addAll(serviciosLocal.getScaleList());
        return scales;
    }

    @Override
    public int getTotalScale(Scale scale) {
        int total = serviciosCentral.getTotalScale(scale);
        total += serviciosLocal.getTotalScale(scale);
        return total;
    }

    @Override
    public List<Scale> getListScale(Scale filter, int start, int pageSize, boolean paged) {
        List<Scale> scales = serviciosCentral.getListScale(filter, start, pageSize, paged);
        scales.addAll(serviciosLocal.getListScale(filter, start, pageSize, paged));
        return scales;
    }

    @Override
    public List<Scale> getListScaleGroupsCentral() {
        return serviciosCentral.getListScaleGroups();
    }

    @Override
    public List<Scale> getListScaleGroupsLocal() {
        return serviciosLocal.getListScaleGroups();
    }

//-----------------------------------Scales---------------------------
    @Override
    public void addScalesCentral(Scales scales) {
        this.serviciosCentral.addScales(scales);
    }

    @Override
    public void addScalesLocal(Scales scales) {
        this.serviciosLocal.addScales(scales);
    }

//    @Override
//    public void deleteAtributsLocal(Scales scales){
//        this.serviciosLocal.deleteScales(scales);
//    }
    @Override
    public List<Scales> getScalesList() {
        List<Scales> scaless = serviciosCentral.getScalesList();
        scaless.addAll(serviciosLocal.getScalesList());
        return scaless;
    }

    @Override
    public int getTotalScales(Scales scales) {
        int total = serviciosCentral.getTotalScales(scales);
        total += serviciosLocal.getTotalScales(scales);
        return total;
    }

    @Override
    public List<Scales> getListScales(Scales filter, int start, int pageSize, boolean paged) {
        List<Scales> scaless = serviciosCentral.getListScales(filter, start, pageSize, paged);
        scaless.addAll(serviciosLocal.getListScales(filter, start, pageSize, paged));
        return scaless;
    }

    @Override
    public List<Scales> getListScalesCentral(Scales filter, int start, int pageSize, boolean paged) {
        return serviciosCentral.getListScales(filter, start, pageSize, paged);
    }

    @Override
    public List<Scales> getListScalesLocal(Scales filter, int start, int pageSize, boolean paged) {
        return serviciosLocal.getListScales(filter, start, pageSize, paged);
    }

    @Override
    public Scales getScalesById(final Integer scaleid) {
        Scales scales = null;
        if (scaleid.intValue() < 0) {
            scales = serviciosLocal.getScales(scaleid);
        } else {
            scales = serviciosCentral.getScales(scaleid);
        }

        return scales;
    }

    @Override
    public void updateTmsScale(Scales scale) {
        serviciosLocal.updateScales(scale);
    }
//-----------------------------------Scalecon---------------------------

    @Override
    public List<Scalecon> getScaleconList() {
        List<Scalecon> scalecons = serviciosCentral.getScaleconList();
        scalecons.addAll(serviciosLocal.getScaleconList());
        return scalecons;
    }

    @Override
    public int getTotalScalecon(Scalecon scalecon) {
        int total = serviciosCentral.getTotalScalecon(scalecon);
        total += serviciosLocal.getTotalScalecon(scalecon);
        return total;
    }

    @Override
    public List<Scalecon> getListScalecon(Scalecon filter, int start, int pageSize, boolean paged) {
        List<Scalecon> scalecons = serviciosCentral.getListScalecon(filter, start, pageSize, paged);
        scalecons.addAll(serviciosLocal.getListScalecon(filter, start, pageSize, paged));
        return scalecons;
    }

//-----------------------------------Scaledis---------------------------
    @Override
    public List<Scaledis> getScaledisList() {
        List<Scaledis> scaledis = serviciosCentral.getScaledisList();
        scaledis.addAll(serviciosLocal.getScaledisList());
        return scaledis;
    }

    @Override
    public int getTotalScaledis(Scaledis scaledis) {
        int total = serviciosCentral.getTotalScaledis(scaledis);
        total += serviciosLocal.getTotalScaledis(scaledis);
        return total;
    }

    @Override
    public List<Scaledis> getListScaledis(Scaledis filter, int start, int pageSize, boolean paged) {
        List<Scaledis> scaledis = serviciosCentral.getListScaledis(filter, start, pageSize, paged);
        scaledis.addAll(serviciosLocal.getListScaledis(filter, start, pageSize, paged));
        return scaledis;
    }

//-----------------------------------Scaletab---------------------------
    @Override
    public List<Scaletab> getScaletabList() {
        List<Scaletab> scaletabs = serviciosCentral.getScaletabList();
        scaletabs.addAll(serviciosLocal.getScaletabList());
        return scaletabs;
    }

    @Override
    public int getTotalScaletab(Scaletab scaletab) {
        int total = serviciosCentral.getTotalScaletab(scaletab);
        total += serviciosLocal.getTotalScaletab(scaletab);
        return total;
    }

    @Override
    public List<Scaletab> getListScaletab(Scaletab filter, int start, int pageSize, boolean paged) {
        List<Scaletab> scaletabs = serviciosCentral.getListScaletab(filter, start, pageSize, paged);
        scaletabs.addAll(serviciosLocal.getListScaletab(filter, start, pageSize, paged));
        return scaletabs;
    }

//-----------------------------------Sndivs---------------------------
    @Override
    public List<Sndivs> getSndivsList() {
        List<Sndivs> sndivs = serviciosCentral.getSndivsList();
        sndivs.addAll(serviciosLocal.getSndivsList());
        return sndivs;
    }

    @Override
    public int getTotalSndivs(Sndivs sndivs) {
        int total = serviciosCentral.getTotalSndivs(sndivs);
        total += serviciosLocal.getTotalSndivs(sndivs);
        return total;
    }

    @Override
    public List<Sndivs> getListSndivs(Sndivs filter, int start, int pageSize, boolean paged) {
        List<Sndivs> sndivs = serviciosCentral.getListSndivs(filter, start, pageSize, paged);
        sndivs.addAll(serviciosLocal.getListSndivs(filter, start, pageSize, paged));
        return sndivs;
    }

//-----------------------------------Steffect---------------------------
    @Override
    public List<Steffect> getSteffectList() {
        List<Steffect> steffects = serviciosCentral.getSteffectList();
        steffects.addAll(serviciosLocal.getSteffectList());
        return steffects;
    }

    @Override
    public int getTotalSteffect(Steffect steffect) {
        int total = serviciosCentral.getTotalSteffect(steffect);
        total += serviciosLocal.getTotalSteffect(steffect);
        return total;
    }

    @Override
    public List<Steffect> getListSteffect(Steffect filter, int start, int pageSize, boolean paged) {
        List<Steffect> steffects = serviciosCentral.getListSteffect(filter, start, pageSize, paged);
        steffects.addAll(serviciosLocal.getListSteffect(filter, start, pageSize, paged));
        return steffects;
    }

    public List<Steffect> getSteffectByStudyid(Integer studyid) {
        if (studyid > 0) {
            return serviciosCentral.getSteffectByStudyid(studyid);
        } else {
            return serviciosLocal.getSteffectByStudyid(studyid);
        }
    }

    public List<Integer> getEffectidsByStudyid(Integer studyid) {
        if (studyid > 0) {
            return serviciosCentral.getEffectidsByStudyid(studyid);
        } else {
            return serviciosLocal.getEffectidsByStudyid(studyid);
        }
    }

//-----------------------------------Study---------------------------
    public Study getStudy(Integer idStudy) {
        if (idStudy > 0) {
            return this.serviciosCentral.getStudy(idStudy);
        } else {
            return this.serviciosLocal.getStudy(idStudy);
        }
//        Study temp = this.serviciosCentral.getStudy(idStudy);
//        if (temp != null) {
//            return temp;
//        } else {
//            return this.serviciosLocal.getStudy(idStudy);
//        }
    }

    @Override
    public void addStudy(Study study) {
        this.serviciosLocal.addStudy(study);
    }

    @Override
    public List<Study> getStudyList() {
        List<Study> studys = serviciosCentral.getStudyList();
        studys.addAll(serviciosLocal.getStudyList());
        return studys;
    }

    @Override
    public int getTotalStudy(Study study) {
        int total = serviciosCentral.getTotalStudy(study);
        total += serviciosLocal.getTotalStudy(study);
        return total;
    }

    @Override
    public List<Study> getListStudy(Study studyFilter, int start, int pageSize,
            boolean paged) {
        List<Study> studys = serviciosCentral.getListStudy(studyFilter, start, pageSize, paged);
        studys.addAll(serviciosLocal.getListStudy(studyFilter, start, pageSize, paged));
        return studys;
    }

    @Override
    public void addStudyToLocal(Study study) {
        serviciosLocal.addStudy(study);
    }

    @Override
    public List<Study> getStudyListByParent(Integer studyParent, String studyType) {
        List<Study> studiesByParent = new ArrayList<Study>();
        Study parentFilter = new Study(true);
        parentFilter.setShierarchy(studyParent);
        parentFilter.setStype(studyType);
        //parentFilter.setStype(org.cimmyt.cril.ibwb.domain.Study.STYPE_EXPERIMENT);

        // add all studies from local

        // count total of studies from this parent in local
        //int totalRecords = serviciosLocal.getTotalStudy(parentFilter);

        studiesByParent.addAll(serviciosCentral.getListStudy(parentFilter, 0, 0, false));

        // count total of studies from this parent in central
        //totalRecords = serviciosCentral.getTotalStudy(parentFilter);
        // add all studies from central

        studiesByParent.addAll(serviciosLocal.getListStudy(parentFilter, 0, 0, false));

        return studiesByParent;
    }

    /**
     * Returns an study by study name
     *
     * @param studyName Study name to search
     * @return
     * <code>true</cide> if found,
     * <code>false</code> if not found
     */
    @Override
    public Study getStudyByName(String studyName) {
        Study study = null;
        Study studyFilter = new Study(true);
        studyFilter.setSname(studyName);

        List<Study> studyList = serviciosCentral.getListStudy(studyFilter, 0, 0, false);
        if (!studyList.isEmpty()) {
            study = studyList.get(0);
        } else {
            studyList = serviciosLocal.getListStudy(studyFilter, 0, 0, false);
            if (!studyList.isEmpty()) {
                study = studyList.get(0);
            }
        }



        return study;
    }

    public List<Study> getStudysOnlyTrial() {
        List<Study> studys = serviciosCentral.getStudysOnlyTrial();
        studys.addAll(serviciosLocal.getStudysOnlyTrial());
        return studys;
    }

    public List<Integer> getTrialsByStudyid(Integer studyid) {
        List<Integer> instancias = HelperFactor.getTrialsForStudyid(this, studyid);
        return instancias;
    }

//-----------------------------------Tmethod---------------------------
    public Tmethod getTmethod(Integer tmethodId) {
        if (tmethodId > 0) {
            return serviciosCentral.getTmethod(tmethodId);
        } else {
            return serviciosLocal.getTmethod(tmethodId);
        }
//        Tmethod tmethod = serviciosCentral.getTmethod(tmethodId);
//        if (tmethod != null) {
//            return tmethod;
//        } else {
//            return serviciosLocal.getTmethod(tmethodId);
//        }
    }

    @Override
    public List<Tmethod> getTmethodList() {
        List<Tmethod> tmethods = serviciosCentral.getTmethodList();
        tmethods.addAll(serviciosLocal.getTmethodList());
        return tmethods;
    }

    @Override
    public int getTotalTmethod(Tmethod tmethod) {
        int total = serviciosCentral.getTotalTmethod(tmethod);
        total += serviciosLocal.getTotalTmethod(tmethod);
        return total;
    }

    @Override
    public List<Tmethod> getListTmethod(Tmethod filter, int start, int pageSize, boolean paged) {
        List<Tmethod> tmethods = serviciosCentral.getListTmethod(filter, start, pageSize, paged);
        tmethods.addAll(serviciosLocal.getListTmethod(filter, start, pageSize, paged));
        return tmethods;
    }

//-----------------------------------TmsMethod---------------------------
    public TmsMethod getTmsMethod(Integer tmethodId) {
        if (tmethodId > 0) {
            return serviciosCentral.getTmsMethod(tmethodId);
        } else {
            return serviciosLocal.getTmsMethod(tmethodId);
        }
    }

    @Override
    public List<TmsMethod> getTmsMethodList() {
        List<TmsMethod> tmsMethods = serviciosCentral.getTmsMethodList();
        tmsMethods.addAll(serviciosLocal.getTmsMethodList());
        return tmsMethods;
    }

    @Override
    public int getTotalTmsMethod(TmsMethod tmsMethod) {
        int total = serviciosCentral.getTotalTmsMethod(tmsMethod);
        total += serviciosLocal.getTotalTmsMethod(tmsMethod);
        return total;
    }

    @Override
    public List<TmsMethod> getListTmsMethod(TmsMethod filter, int start, int pageSize, boolean paged) {
        List<TmsMethod> tmsMethods = serviciosCentral.getListTmsMethod(filter, start, pageSize, paged);
        tmsMethods.addAll(serviciosLocal.getListTmsMethod(filter, start, pageSize, paged));
        return tmsMethods;
    }

    //-----------------------------------TmsScaleCon---------------------------
    /**
     * Adds an Object TmsScaleCon to database
     *
     * @param tmsScaleCon Objeto a agregar
     */
    @Override
    public void addTmsScaleCon(TmsScaleCon tmsScaleCon) {
        if (tmsScaleCon.getTmsscaleconid() == null) {
            serviciosLocal.addTmsScaleCon(tmsScaleCon);
        } else if (tmsScaleCon.getTmsscaleconid() != null && tmsScaleCon.getTmsscaleconid().intValue() == 0) {
            serviciosLocal.addTmsScaleCon(tmsScaleCon);
        }
    }

    /**
     * Updates a record of type TmsScaleCon in database
     *
     * @param tmsScaleCon Objeto a actualizar
     */
    @Override
    public void updateTmsScaleCon(TmsScaleCon tmsScaleCon) {
        if (tmsScaleCon.getTmsscaleconid().intValue() < 0) {
            serviciosLocal.updateTmsScaleCon(tmsScaleCon);
        } else {
            serviciosCentral.updateTmsScaleCon(tmsScaleCon);
        }
    }

    /**
     * Deletes an object TmsScaleCon from database
     *
     * @param tmsScaleCon Objeto a eliminar
     */
    @Override
    public void deleteTmsScaleCon(TmsScaleCon tmsScaleCon) {
        if (tmsScaleCon.getTmsscaleconid().intValue() < 0) {
            serviciosLocal.deleteTmsScaleCon(tmsScaleCon);
        } else {
            serviciosCentral.deleteTmsScaleCon(tmsScaleCon);
        }
    }

    /**
     * Gets an Object from database TmsScaleCon of the type TmsScaleCon
     *
     * @param tmsScaleCon
     * @return TmsScaleCon
     */
    @Override
    public TmsScaleCon getTmsScaleCon(TmsScaleCon tmsScaleCon) {
        if (tmsScaleCon.getTmsscaleconid().intValue() < 0) {
            return serviciosLocal.getTmsScaleCon(tmsScaleCon);
        } else {
            return serviciosCentral.getTmsScaleCon(tmsScaleCon);
        }
    }

    /**
     * Gets an Object of type TmsScaleCon Finding the record by its ID
     * TmsScaleCon in String format
     *
     * @param idTmethod
     * @return TmsScaleCon
     */
    @Override
    public TmsScaleCon getTmsScaleCon(Integer tmsScaleConId) {
        if (tmsScaleConId.intValue() < 0) {
            return serviciosLocal.getTmsScaleCon(tmsScaleConId);
        } else {
            return serviciosCentral.getTmsScaleCon(tmsScaleConId);
        }
    }

    /**
     * Gets a list of Objects TmsScaleCon
     *
     * @return List
     */
    @Override
    public List<TmsScaleCon> getTmsScaleConList() {
        return new ArrayList<TmsScaleCon>();
    }

    /**
     * Gets the number of records matching with filter
     *
     * @param tmsScaleConFiltro Object to count total items
     */
    @Override
    public int getTotalTmsScaleCon(TmsScaleCon tmsScaleConFilter) {
        return 0;
    }

    /**
     * Gets a list of Objects for pagination
     *
     * @param tmethodFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    @Override
    public List<TmsScaleCon> getListTmsScaleCon(TmsScaleCon tmsScaleConFilter, int start, int pageSize, boolean paged) {
        return new ArrayList<TmsScaleCon>();
    }

    /**
     * Gets a list of TmsScaleCon by it measuredin id
     *
     * @param measuredindid measuredin to search
     * @return list of TmsScaleCon or empty list
     */
    @Override
    public List<TmsScaleCon> getTmsScaleConByMeasuredinId(final Integer measuredindid) {
        if (measuredindid.intValue() < 0) {
            return serviciosLocal.getTmsScaleConByMeasuredinId(measuredindid);
        } else {
            return serviciosCentral.getTmsScaleConByMeasuredinId(measuredindid);
        }
    }

    /**
     * Adds an Object TmsScaleDis to database
     *
     * @param tmsScaleDis Objeto a agregar
     */
    @Override
    public void addTmsScaleDis(TmsScaleDis tmsScaleDis) {
        if (tmsScaleDis.getTmsscaledisid() == null) {
            serviciosLocal.addTmsScaleDis(tmsScaleDis);
        } else if (tmsScaleDis.getTmsscaledisid() != null && tmsScaleDis.getTmsscaledisid().intValue() == 0) {
            serviciosLocal.addTmsScaleDis(tmsScaleDis);
        }
    }

    /**
     * Updates a record of type TmsScaleDis in database
     *
     * @param tmsScaleDis Objeto a actualizar
     */
    @Override
    public void updateTmsScaleDis(TmsScaleDis tmsScaleDis) {
        if (tmsScaleDis.getTmsscaledisid().intValue() < 0) {
            serviciosLocal.updateTmsScaleDis(tmsScaleDis);
        } else {
            serviciosCentral.updateTmsScaleDis(tmsScaleDis);
        }
    }

    /**
     * Deletes an object TmsScaleDis from database
     *
     * @param tmsScaleDis Objeto a eliminar
     */
    @Override
    public void deleteTmsScaleDis(TmsScaleDis tmsScaleDis) {
        if (tmsScaleDis.getTmsscaledisid().intValue() < 0) {
            serviciosLocal.deleteTmsScaleDis(tmsScaleDis);
        } else {
            serviciosCentral.deleteTmsScaleDis(tmsScaleDis);
        }
    }

    /**
     * Gets an Object from database TmsScaleDis of the type TmsScaleDis
     *
     * @param tmsScaleDis
     * @return TmsScaleDis
     */
    @Override
    public TmsScaleDis getTmsScaleDis(TmsScaleDis tmsScaleDis) {
        if (tmsScaleDis.getTmsscaledisid().intValue() < 0) {
            return serviciosLocal.getTmsScaleDis(tmsScaleDis);
        } else {
            return serviciosCentral.getTmsScaleDis(tmsScaleDis);
        }
    }

    /**
     * Gets an Object of type TmsScaleDis Finding the record by its ID
     * TmsScaleDis in String format
     *
     * @param idTmethod
     * @return TmsScaleDis
     */
    @Override
    public TmsScaleDis getTmsScaleDis(Integer tmsScaleDisId) {
        if (tmsScaleDisId.intValue() < 0) {
            return serviciosLocal.getTmsScaleDis(tmsScaleDisId);
        } else {
            return serviciosCentral.getTmsScaleDis(tmsScaleDisId);
        }
    }

    /**
     * Gets a list of Objects TmsScaleDis
     *
     * @return List
     */
    @Override
    public List<TmsScaleDis> getTmsScaleDisList() {
        return new ArrayList<TmsScaleDis>();
    }

    /**
     * Gets the number of records matching with filter
     *
     * @param tmsScaleDisFiltro Object to count total items
     */
    @Override
    public int getTotalTmsScaleDis(TmsScaleDis tmsScaleDisFilter) {
        return 0;
    }

    /**
     * Gets a list of Objects for pagination
     *
     * @param tmethodFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    @Override
    public List<TmsScaleDis> getListTmsScaleDis(TmsScaleDis tmsScaleDisFilter, int start, int pageSize, boolean paged) {
        return new ArrayList<TmsScaleDis>();
    }

    /**
     * Gets a list of TmsScaleDis by it measuredin id
     *
     * @param measuredindid measuredin to search
     * @return list of TmsScaleDis or empty list
     */
    @Override
    public List<TmsScaleDis> getTmsScaleDisByMeasuredinId(final Integer measuredindid) {
        if (measuredindid.intValue() < 0) {
            return serviciosLocal.getTmsScaleDisByMeasuredinId(measuredindid);
        } else {
            return serviciosCentral.getTmsScaleDisByMeasuredinId(measuredindid);
        }
    }

//-----------------------------------Trait---------------------------
    @Override
    public List<Trait> getTraitList() {
        List<Trait> traits = serviciosCentral.getTraitList();
        traits.addAll(serviciosLocal.getTraitList());
        return traits;
    }

    @Override
    public int getTotalTrait(Trait trait) {
        int total = serviciosCentral.getTotalTrait(trait);
        total += serviciosLocal.getTotalTrait(trait);
        return total;
    }

    @Override
    public List<Trait> getListTrait(Trait filter, int start, int pageSize, boolean paged) {
        List<Trait> traits = serviciosCentral.getListTrait(filter, start, pageSize, paged);
        traits.addAll(serviciosLocal.getListTrait(filter, start, pageSize, paged));
        HelperTrait.getTraitFull(traits, this);
        return traits;
    }

    @Override
    public List<Trait> getListTraitLocal(Trait filter, int start, int pageSize, boolean paged) {
        List<Trait> listTrait = this.serviciosLocal.getListTrait(filter, start, pageSize, paged);
        HelperTrait.getTraitFull(listTrait, this.serviciosLocal);
        return listTrait;
    }

    @Override
    public List<Trait> getListTraitCentral(Trait filter, int start, int pageSize, boolean paged) {
        List<Trait> listTrait = this.serviciosCentral.getListTrait(filter, start, pageSize, paged);
        HelperTrait.getTraitFull(listTrait, this.serviciosCentral);
        return listTrait;
    }

    @Override
    public List<String> getTraitGroups() {
        List<String> traitGroups = serviciosCentral.getTraitGroups();
        traitGroups.addAll(serviciosLocal.getTraitGroups());
        return traitGroups;
    }

//-----------------------------------Traits---------------------------
    public Traits getTraits(Integer traitId) {
        if (traitId > 0) {
            return HelperTraits.getTraitsFull(serviciosCentral.getTraits(traitId), this);
        } else {
            return HelperTraits.getTraitsFull(serviciosLocal.getTraits(traitId), this);
        }
//        Traits traits = serviciosCentral.getTraits(traitId);
//        if (traits != null) {
//            return HelperTraits.getTraitsFull(traits, this);
//        } else {
//            return HelperTraits.getTraitsFull(serviciosLocal.getTraits(traitId), this);
//        }
    }

    @Override
    public List<Traits> getTraitsList() {
        List<Traits> traitss = serviciosCentral.getTraitsList();
        traitss.addAll(serviciosLocal.getTraitsList());
        return traitss;
    }

    @Override
    public List<Traits> getTraitsListGroups() {
        List<Traits> traitss = serviciosCentral.getTraitsList();
        traitss.addAll(serviciosLocal.getTraitsList());
        return traitss;
    }

    @Override
    public int getTotalTraits(Traits traits) {
        int total = serviciosCentral.getTotalTraits(traits);
        total += serviciosLocal.getTotalTraits(traits);
        return total;
    }

    @Override
    public List<Traits> getListTraits(Traits filter, int start, int pageSize, boolean paged) {
        List<Traits> traitss = serviciosCentral.getListTraits(filter, start, pageSize, paged);
        traitss.addAll(serviciosLocal.getListTraits(filter, start, pageSize, paged));
        HelperTraits.getTraitsFull(traitss, this);
        return traitss;
    }

    @Override
    public List<Traits> getListTraitsOnly(Traits filter, int start, int pageSize, boolean paged) {
        List<Traits> traitss = serviciosCentral.getListTraits(filter, start, pageSize, paged);
        traitss.addAll(serviciosLocal.getListTraits(filter, start, pageSize, paged));
        return traitss;
    }

    @Override
    public List<Traits> getListTraitsLocalFull(Traits filter, int start, int pageSize, boolean paged) {
        List<Traits> traitss = serviciosLocal.getListTraits(filter, start, pageSize, paged);
        HelperTraits.getTraitsFull(traitss, serviciosLocal);
        return traitss;
    }

    @Override
    public List<Traits> getListTraitsCentralFull(Traits filter, int start, int pageSize, boolean paged) {
        List<Traits> traitss = serviciosCentral.getListTraits(filter, start, pageSize, paged);
        HelperTraits.getTraitsFull(traitss, serviciosCentral);
        return traitss;
    }

    /**
     * Add or update a TmsTrait to Database
     */
    @Override
    public void addOrUpdateTmsTrait(Traits tmsTrait) {

        // is is a new trait then add it
        if (tmsTrait.getTid() == 0) {
            serviciosLocal.addTraits(tmsTrait);
        } else {
            // if not update it
            serviciosLocal.updateTraits(tmsTrait);
        }

        // now add or update all measuredin
        for (Measuredin measuredin : tmsTrait.getMeasuredins()) {
            measuredin.setTraitid(tmsTrait.getTid());
            serviciosLocal.addOrUpdateMeasuredIn(measuredin);
            // now add or update scale definition accordint to it type
            if (measuredin.getScales().getSctype().equals(Scales.SCALE_TYPE_CONTINOUS)) {

                TmsScaleCon scaleCon = (TmsScaleCon) measuredin.getTmsScaleDef();
                scaleCon.setMeasuredinid(measuredin.getMeasuredinid());
                serviciosLocal.addOrUpdateTmsScaleCon(scaleCon);

            } else {
                TmsScaleDis scaleDis = (TmsScaleDis) measuredin.getTmsScaleDef();
                scaleDis.setMeasuredinid(measuredin.getMeasuredinid());
                serviciosLocal.addOrUpdateTmsScaleDis(scaleDis);
            }
        }
    }
    //-----------------------------------Udflds---------------------------

    @Override
    public List<Udflds> getUdfldsList() {
        List<Udflds> udflds = serviciosCentral.getUdfldsList();
        udflds.addAll(serviciosLocal.getUdfldsList());
        return udflds;
    }

    @Override
    public int getTotalUdflds(Udflds udflds) {
        int total = serviciosCentral.getTotalUdflds(udflds);
        total += serviciosLocal.getTotalUdflds(udflds);
        return total;
    }

    @Override
    public List<Udflds> getListUdflds(Udflds filter, int start, int pageSize, boolean paged) {
        List<Udflds> udflds = serviciosCentral.getListUdflds(filter, start, pageSize, paged);
        udflds.addAll(serviciosLocal.getListUdflds(filter, start, pageSize, paged));
        return udflds;
    }

//-----------------------------------Users---------------------------
    @Override
    public List<Users> getUsersList() {
        List<Users> users = serviciosCentral.getUsersList();
        users.addAll(serviciosLocal.getUsersList());
        return users;
    }

    @Override
    public int getTotalUsers(Users users) {
        int total = serviciosCentral.getTotalUsers(users);
        total += serviciosLocal.getTotalUsers(users);
        return total;
    }

    @Override
    public List<Users> getListUsers(Users filter, int start, int pageSize, boolean paged) {
        List<Users> users = serviciosCentral.getListUsers(filter, start, pageSize, paged);
        users.addAll(serviciosLocal.getListUsers(filter, start, pageSize, paged));
        return users;
    }

    /**
     * get ID for logged user according to following parameters USTATUS = 1 UACC
     * = 100 LOCAL ICIS ADMINISTRATOR UTYPE = 422 LOCAL DATABASE ADMINISTRATOR
     *
     * @return
     */
    @Override
    public Integer getLoggedUserId() {
        return serviciosLocal.getLoggedUserId();
    }

    @Override
    public Integer getLoggedUserId(String userid) {
        Integer userId = null;
        Users filter = new Users(true);
        filter.setUname(userid);

        List<Users> userList = serviciosLocal.getListUsers(filter, 0, 0, false);

        if (userList.isEmpty()) {
            userId = -1;
        } else {
            userId = userList.get(0).getUserid();
        }


        return userId;
    }
//-----------------------------------Variate---------------------------

    @Override
    public List<Variate> getVariateList() {
        List<Variate> variates = serviciosCentral.getVariateList();
        variates.addAll(serviciosLocal.getVariateList());
        return variates;
    }

    @Override
    public int getTotalVariate(Variate variate) {
        int total = serviciosCentral.getTotalVariate(variate);
        total += serviciosLocal.getTotalVariate(variate);
        return total;
    }

    @Override
    public List<Variate> getListVariate(Variate filter, int start, int pageSize, boolean paged) {
        List<Variate> variates = serviciosCentral.getListVariate(filter, start, pageSize, paged);
        variates.addAll(serviciosLocal.getListVariate(filter, start, pageSize, paged));
        return variates;
    }

//-----------------------------------Variate---------------------------
    @Override
    public List<Veffect> getVeffectList() {
        List<Veffect> veffects = serviciosCentral.getVeffectList();
        veffects.addAll(serviciosLocal.getVeffectList());
        return veffects;
    }

    @Override
    public int getTotalVeffect(Veffect veffect) {
        int total = serviciosCentral.getTotalVeffect(veffect);
        total += serviciosLocal.getTotalVeffect(veffect);
        return total;
    }

    @Override
    public List<Veffect> getListVeffect(Veffect filter, int start, int pageSize, boolean paged) {
        List<Veffect> veffects = serviciosCentral.getListVeffect(filter, start, pageSize, paged);
        veffects.addAll(serviciosLocal.getListVeffect(filter, start, pageSize, paged));
        return veffects;
    }

//---------------------------------------------Seters and Getter of services
    public CommonServices getServiciosLocal() {
        return serviciosLocal;
    }

    public void setServiciosLocal(CommonServices serviciosLocal) {
        this.serviciosLocal = serviciosLocal;
    }

    public CommonServices getServiciosCentral() {
        return serviciosCentral;
    }

    public void setServiciosCentral(CommonServices serviciosCentral) {
        this.serviciosCentral = serviciosCentral;
    }

//------------------------------------------------------------------------------------------------------------
//-------------------------------------Estructura de trabajo de la dll----------------------------------------
//------------------------------------------------------------------------------------------------------------
    @Override
    public Workbook getWorkbookFull(Workbook workbook, boolean datos) {
        return getWorkbookFull(Integer.valueOf(workbook.getStudy().getPmkey()), datos);
    }

    @Override
    public Workbook getWorkbookFull(Integer studyid, boolean datos) {
        HelperWorkbookRead helperWorkbookRead = new HelperWorkbookRead(
                serviciosLocal,
                serviciosCentral,
                this);
        return helperWorkbookRead.readStudy(studyid);
    }

    @Override
    public Study getStudyFactorsTrialAndEntryFull(Integer studyid) {
        HelperWorkbookRead helperWorkbookRead = new HelperWorkbookRead(
                serviciosLocal,
                serviciosCentral,
                this);
        return helperWorkbookRead.readStudyFactorsTrialAndEntry(studyid);
    }

    @Override
    public void saveWorkbookNurseryFull(Workbook workbook) {
        //Llenando measurementsRep
        workbook.setGroupRepsMeasurementsForNursery();

        HelperWorkbook helperWorkbook = new HelperWorkbook(workbook, serviciosLocal, this);

        //Guardar Workbook
        log.info("Savin workbook....");
        helperWorkbook.saveStudy();
        log.info("Savin workbook DONE!");
    }

    @Override
    public void saveWorkbookFull(Workbook workbook) {
        //Llenando measurementsRep
        workbook.setGroupRepsMeasurements();

        HelperWorkbook helperWorkbook = new HelperWorkbook(workbook, serviciosLocal, this);

        //Guardar Workbook
        log.info("Savin workbook....");
        helperWorkbook.saveStudy();
        log.info("Savin workbook DONE!");
    }

    public ResultSet getTrialRandomization(
            Integer studyId,
            Integer trialFactorId,
            List<String> factoresPrincipales,
            List<String> factoresSalida) {
        String nombreTrial = null;
        if (trialFactorId != null) {
            Factor temp = getTrialFactor(studyId);
            if (temp != null) {
                nombreTrial = temp.getFname();
            }
        }
        if (studyId > 0) {
            return this.serviciosCentral.getTrialRandomization(studyId, trialFactorId, factoresPrincipales, factoresSalida, nombreTrial);
        } else {
            return this.serviciosLocal.getTrialRandomization(studyId, trialFactorId, factoresPrincipales, factoresSalida, nombreTrial);
        }
    }

    public ResultSet getTrialRandomizationFast(
            Integer studyId,
            Integer trialFactorId,
            List<String> factoresPrincipales,
            List<String> factoresSalida,
            String nombreTrial) {
        if (studyId > 0) {
            return this.serviciosCentral.getTrialRandomizationFast(studyId, trialFactorId, factoresPrincipales, factoresSalida, nombreTrial);
        } else {
            return this.serviciosLocal.getTrialRandomizationFast(studyId, trialFactorId, factoresPrincipales, factoresSalida, nombreTrial);
        }
    }

    public StudySearch getListGermplasmAndPlotByStudyidAndTrial(
            StudySearch studySearch) {
        if (studySearch.getStudyId() > 0) {
            return this.serviciosCentral.getListGermplasmAndPlotByStudyidAndTrial(studySearch);
        } else {
            return this.serviciosLocal.getListGermplasmAndPlotByStudyidAndTrial(studySearch);
        }
    }

    public List<Listdata> saveGerplasmCimmytWheat(
            List<Listdata> listGermplsm,
            Listnms listnms,
            Integer userId) {
        HelperGermplasm helperGermplasm = new HelperGermplasm(listnms, this, serviciosLocal, userId);
        return helperGermplasm.saveGerplasmCimmytWheat(listGermplsm, listnms);
    }

    public Listdata agregarGermPlasmCimmytWheat(
            String nameGermplasmHistory,
            String nameGermplasmBCID,
            Listdata listdata,
            Listnms listnms,
            Integer userId,
            QueryCenter queryCenter
            ) {
        HelperGermplasm helperGermplasm = new HelperGermplasm(new Listnms(), this, serviciosLocal, userId);
        return helperGermplasm.agregarGermPlasmCimmytWheat(
                nameGermplasmHistory,
                nameGermplasmBCID,
                listdata,
                listnms,
                queryCenter);
    }

    public List<GermplasmSearch> getGermplasmByListStudyTrialPlotCross(
            AppServices appServices,
            List<GermplasmSearch> listFmale,
            List<GermplasmSearch> listMale) {
        return HelperGermplasm.getGermplasmByListStudyTrialPlotCross(appServices, listFmale, listMale);
    }

    public ResultSet getTrialRandomization(
            Integer studyId,
            Integer trialFactorId,
            List<String> factoresPrincipales,
            List<String> factoresSalida,
            String trialNmame) {
        if (studyId > 0) {
            return this.serviciosCentral.getTrialRandomization(studyId, trialFactorId, factoresPrincipales, factoresSalida, trialNmame);
        } else {
            return this.serviciosLocal.getTrialRandomization(studyId, trialFactorId, factoresPrincipales, factoresSalida, trialNmame);
        }
    }

    public List<Measurement> getTrialRandomizationVeryFast(
            Integer studyId,
            Integer trialFactorId,
            List<String> factoresPrincipales,
            List<String> factoresSalida,
            String trialNmame) {
        if (studyId > 0) {
            return this.serviciosCentral.getTrialRandomizationVeryFast(studyId, trialFactorId, factoresPrincipales, factoresSalida, trialNmame);
        } else {
            return this.serviciosLocal.getTrialRandomizationVeryFast(studyId, trialFactorId, factoresPrincipales, factoresSalida, trialNmame);
        }
    }

    /**
     * Checks if Tratis, Scales and Measuredin tables already exists in database
     *
     * @return
     * <code>true</code> if exists,
     * <code>false</code> if does not exist.
     */
    @Override
    public boolean existsTratisTable() {
        boolean moreTables = true;
        moreTables = this.serviciosCentral.existsTratisTable() && this.serviciosLocal.existsTratisTable();
        if(!moreTables){
            return moreTables;
        }
        moreTables = serviciosCentral.existsTableTransformations() && serviciosLocal.existsTableTransformations();
        if(!moreTables){
            return moreTables;
        }
        moreTables = serviciosCentral.existsTableContinuousConversion() && serviciosLocal.existsTableContinuousConversion();
        if(!moreTables){
            return moreTables;
        }
        moreTables = serviciosCentral.existsTableContinuousFunction() && serviciosLocal.existsTableContinuousFunction();
        if(!moreTables){
            return moreTables;
        }
        moreTables = serviciosCentral.existsTableDiscreteConversion() && serviciosLocal.existsTableDiscreteConversion();
        return moreTables;
    }

    /**
     * Create TraTratis, Scales and Measuredin
     */
    @Override
    public void createTraitsTables() {

        if (!serviciosCentral.existsTratisTable()) {
            serviciosCentral.createTraitsTables();
            MigrateData.insertScaleGroupToScales(serviciosCentral);
            MigrateData.completeDependencyRatioMeasuredinCentral(serviciosCentral);
        }
        if (!serviciosLocal.existsTratisTable()) {
            serviciosLocal.createTraitsTables();
            MigrateData.insertScaleGroupToScales(serviciosLocal);
            MigrateData.completeDependencyRatioMeasuredinLocal(serviciosLocal, serviciosCentral);
        }
        
        if(!serviciosCentral.existsTableTransformations()){
            serviciosCentral.createTableTransformations();
        }
        
        if(!serviciosLocal.existsTableTransformations()){
            serviciosLocal.createTableTransformations();
        }
        
        if(!serviciosCentral.existsTableContinuousConversion()){
            serviciosCentral.createTableContinuousConversion();
        }
        
        if(!serviciosLocal.existsTableContinuousConversion()){
            serviciosLocal.createTableContinuousConversion();
        }
        
        if(!serviciosCentral.existsTableContinuousFunction()){
            serviciosCentral.createTableContinuousFunction();
        }
        
        if(!serviciosLocal.existsTableContinuousFunction()){
            serviciosLocal.createTableContinuousFunction();
        }
        
        if(!serviciosCentral.existsTableDiscreteConversion()){
            serviciosCentral.createTableDiscreteConversion();
        }
        
        if(!serviciosLocal.existsTableDiscreteConversion()){
            serviciosLocal.createTableDiscreteConversion();
        }
    }

    /**
     * Adds an Object Listnms to database
     *
     * @param listnms Objeto a agregar
     */
    public void addListnms(Listnms listnms) {
        this.serviciosLocal.addListnms(listnms);
    }

    /**
     * Adds an Object Listdata to database
     *
     * @param listdata Objeto a agregar
     */
    public void addListdata(Listdata listdata) {
        this.serviciosLocal.addListdata(listdata);
    }

    @Override
    public List<Variate> getListVariateConstants(Integer studyId) {
        Integer effectId = HelperEffect.getEffectidForTrialEffect(this, studyId);
        return getListVariateConstantOrTraits(studyId, effectId);
    }

    @Override
    public List<Variate> getListVariateTraits(Integer studyId) {
        Integer effectId = HelperEffect.getEffectidForMeasurementEffect(this, studyId, null, null, null, null);
        return getListVariateConstantOrTraits(studyId, effectId);

    }

    public List<Variate> getListVariateTraitsByEffectid(Integer studyId, Integer effectid) {
        return getListVariateConstantOrTraits(studyId, effectid);
    }

    /**
     *
     */
    private List<Variate> getListVariateConstantOrTraits(Integer studyId, String effectName) {
        // decide where to search in local or central
        CommonServices services = null;
        if (studyId.intValue() < 0) {
            services = serviciosLocal;
        } else {
            services = serviciosCentral;
        }


        // first get EfectId by study
        Steffect steffectFilter = new Steffect(true);
        steffectFilter.setStudyid(studyId);
        steffectFilter.setEffectname(effectName);
        List<Steffect> steffects = new ArrayList<Steffect>();


        steffects = services.getListSteffect(steffectFilter, 0, 0, false);

        Integer effectId = steffects.get(0).getEffectid();

        // then find represetn ID for EFECTID

        Represtn represtnFilter = new Represtn(true);
        represtnFilter.setEffectid(effectId);
        List<Represtn> represtns = new ArrayList<Represtn>();


        represtns = services.getListReprestn(represtnFilter, 0, 0, false);

        Integer resprestnId = represtns.get(0).getRepresno();

        return services.getVarieteFromVeffects(resprestnId);
    }

    private List<Variate> getListVariateConstantOrTraits(Integer studyId, Integer effectId) {
        // decide where to search in local or central
        CommonServices services = null;
        if (studyId.intValue() < 0) {
            services = serviciosLocal;
        } else {
            services = serviciosCentral;
        }


        // first get EfectId by study
//        Steffect steffectFilter = new Steffect(true);
//        steffectFilter.setStudyid(studyId);
//        steffectFilter.setEffectid(effectid);
//        List<Steffect> steffects = new ArrayList<Steffect>();
//
//
//        steffects = services.getListSteffect(steffectFilter, 0, 0, false);
//
//        Integer effectId = steffects.get(0).getEffectid();

        // then find represetn ID for EFECTID

        Represtn represtnFilter = new Represtn(true);
        represtnFilter.setEffectid(effectId);
        List<Represtn> represtns = new ArrayList<Represtn>();


        represtns = services.getListReprestn(represtnFilter, 0, 0, false);

        Integer resprestnId = represtns.get(0).getRepresno();

        return services.getVarieteFromVeffects(resprestnId);
    }

    @Override
    public List<Object> getObservationsData(Integer studyId) {
        CommonServices services = null;
        if (studyId.intValue() < 0) {
            services = serviciosLocal;
        } else {
            services = serviciosCentral;
        }


        // first get EfectId by study
//        Steffect steffectFilter = new Steffect(true);
//        steffectFilter.setStudyid(studyId);
//        steffectFilter.setEffectname("MEASUREMENT EFECT");
//        List<Steffect> steffects = new ArrayList<Steffect>();
//
//
//        steffects = services.getListSteffect(steffectFilter, 0, 0, false);
//
//        Integer effectId = steffects.get(0).getEffectid();

        Integer effectId = HelperEffect.getEffectidForMeasurementEffect(this, studyId, null, null, null, null);

        List<Object> observationsDataList = new ArrayList<Object>();

        for (DataN dataN : services.getDataNByEffectId(effectId)) {
            observationsDataList.add(dataN);
        }

        for (DataC dataC : services.getDataCByEffectId(effectId)) {
            observationsDataList.add(dataC);
        }


        return observationsDataList;

    }

    @Override
    public List<Object> getObservationsDataMeasurementEffect(Integer studyId, Integer effectId) {
        CommonServices services = null;
        if (studyId.intValue() < 0) {
            services = serviciosLocal;
        } else {
            services = serviciosCentral;
        }

        List<Object> observationsDataList = new ArrayList<Object>();
//
//        for (DataN dataN : services.getDataNByEffectId(effectId)) {
//            observationsDataList.add(dataN);
//        }
//
//        for (DataC dataC : services.getDataCByEffectId(effectId)) {
//            observationsDataList.add(dataC);
//        }
        observationsDataList.addAll(services.getDataNByEffectId(effectId));
        observationsDataList.addAll(services.getDataCByEffectId(effectId));

        return observationsDataList;

    }

    /**
     * Get number of rows for an effect id. For example you can retrieve row
     * number for Measurement Effect
     *
     * @param effectId
     * @return
     */
    @Override
    public int getObservationsCount(final Integer studyId) {
        CommonServices services = null;
        if (studyId.intValue() < 0) {
            services = serviciosLocal;
        } else {
            services = serviciosCentral;
        }


        // first get EfectId by study
//        Steffect steffectFilter = new Steffect(true);
//        steffectFilter.setStudyid(studyId);
//        steffectFilter.setEffectname("MEASUREMENT EFECT");
//        List<Steffect> steffects = new ArrayList<Steffect>();
//
//
//        steffects = services.getListSteffect(steffectFilter, 0, 0, false);
//
//        Integer effectId = steffects.get(0).getEffectid();

        Integer effectId = HelperEffect.getEffectidForMeasurementEffect(this, studyId, null, null, null, null);

        if (effectId.intValue() < 0) {
            return serviciosLocal.getObservationsCount(effectId);
        } else {
            return serviciosCentral.getObservationsCount(effectId);
        }
    }

    /**
     * Gets a list of observations unit for a effect id
     *
     * @param effectId Effect Id to search
     * @return List of observations units or empty list
     */
    @Override
    public List<Obsunit> getObsunitListByStudy(final Integer studyId) {
        CommonServices services = null;
        if (studyId.intValue() < 0) {
            services = serviciosLocal;
        } else {
            services = serviciosCentral;
        }


        // first get EfectId by study
//        Steffect steffectFilter = new Steffect(true);
//        steffectFilter.setStudyid(studyId);
//        steffectFilter.setEffectname("MEASUREMENT EFECT");
//        List<Steffect> steffects = new ArrayList<Steffect>();
//
//
//        steffects = services.getListSteffect(steffectFilter, 0, 0, false);
//
//        Integer effectId = steffects.get(0).getEffectid();

        Integer effectId = HelperEffect.getEffectidForMeasurementEffect(this, studyId, null, null, null, null);

        return services.getObsunitListByEffectid(effectId);
    }

    /**
     * Gets a list of observations unit for a effect id
     *
     * @param effectId Effect Id to search
     * @return List of observations units or empty list
     */
    @Override
    public List<Obsunit> getObsunitListByStudyTrials(final Integer studyId) {
        CommonServices services = null;
        if (studyId.intValue() < 0) {
            services = serviciosLocal;
        } else {
            services = serviciosCentral;
        }
        Integer effectId = HelperEffect.getEffectidForTrialEffect(this, studyId);

        return services.getObsunitListByEffectid(effectId);
    }

    /**
     * Gets a list of observations unit for a effect id
     *
     * @param studyId Study Id to search
     * @param effectId Effect Id to search
     * @return List of observations units or empty list
     */
    @Override
    public List<Obsunit> getObsunitListByEffectid(final Integer studyId, final Integer effectId) {
        CommonServices services = null;
        if (studyId.intValue() < 0) {
            services = serviciosLocal;
        } else {
            services = serviciosCentral;
        }
        return services.getObsunitListByEffectid(effectId);
    }

    /**
     * Return a list of grouping factors by study id
     *
     * @param studyid ID for study
     * @return list of factor or empty list if study id not found
     */
    @Override
    public List<Factor> getFactorsForStudy(final Integer studyid) {
        CommonServices services = null;
        if (studyid.intValue() < 0) {
            services = serviciosLocal;
        } else {
            services = serviciosCentral;
        }

        List<Factor> factorList = services.getFactorsForStudy(studyid);

        return factorList;
    }

    @Override
    public List<Oindex> getMeasurementOindexListByStudy(Integer studyid) {
        CommonServices services = null;
        if (studyid.intValue() < 0) {
            services = serviciosLocal;
        } else {
            services = serviciosCentral;
        }


        // first get EfectId by study
//        Steffect steffectFilter = new Steffect(true);
//        steffectFilter.setStudyid(studyid);
//        steffectFilter.setEffectname("MEASUREMENT EFECT");
//        List<Steffect> steffects = new ArrayList<Steffect>();
//
//
//        steffects = services.getListSteffect(steffectFilter, 0, 0, false);

//        Integer effectId;
//        effectId = steffects.get(0).getEffectid();
//        effectId = HelperEffect.getEffectidForMeasurementEffect(this);

        // then find represetn ID for EFECTID

        Integer effectId = HelperEffect.getEffectidForMeasurementEffect(this, studyid, null, null, null, null);

        Represtn represtnFilter = new Represtn(true);
        represtnFilter.setEffectid(effectId);
        List<Represtn> represtns = new ArrayList<Represtn>();


        represtns = services.getListReprestn(represtnFilter, 0, 0, false);

        Integer resprestnId = represtns.get(0).getRepresno();

        return services.getOindexListByRepresno(resprestnId);
    }

    public List<Oindex> getMeasurementOindexListByStudy(Integer studyid, Integer effectId) {
        CommonServices services = null;
        if (studyid.intValue() < 0) {
            services = serviciosLocal;
        } else {
            services = serviciosCentral;
        }
        Represtn represtnFilter = new Represtn(true);
        represtnFilter.setEffectid(effectId);
        List<Represtn> represtns = new ArrayList<Represtn>();

        represtns = services.getListReprestn(represtnFilter, 0, 0, false);

        Integer resprestnId = represtns.get(0).getRepresno();

        return services.getOindexListByRepresno(resprestnId);
    }

    @Override
    public void updateWorkbook(Workbook workbook) {
        if (workbook.getStudy().getStudyid() < 0) {
            HelperWorkbookUpdate workbookUpdate = new HelperWorkbookUpdate(workbook, serviciosLocal, this);
            workbookUpdate.update();
        } else {
            boolean seGuardo = validarStudy(workbook);
        }
    }
    
    public void migrateWorkbook(Integer studyId) {
        Study studyTemp = this.serviciosCentral.getStudy(studyId);
        this.serviciosLocal.addStudy(studyTemp);
        
        Study studyTempPadre = this.serviciosLocal.getStudy(studyTemp.getShierarchy());
        if(studyTempPadre == null){
            studyTempPadre = this.serviciosCentral.getStudy(studyTemp.getShierarchy());
            this.serviciosLocal.addStudy(studyTempPadre);
        }
        
        Factor factorTemp = new Factor(true);
        factorTemp.setStudyid(studyId);
        List<Factor> factors = this.serviciosCentral.getListFactor(factorTemp, 0, 0, false);
        for(Factor factor : factors){
            this.serviciosLocal.addFactor(factor);
            Object level;
            if(factor.getLtype().equals("N")){
                LevelN levelN = new LevelN(true);
                levelN.setLevelNPK(new LevelNPK());
                levelN.getLevelNPK().setLabelid(factor.getLabelid());
                level = levelN;
            }else{
                LevelC levelC = new LevelC(true);
                levelC.setLevelCPK(new LevelCPK());
                levelC.getLevelCPK().setLabelid(factor.getLabelid());
                level = levelC;
            }
            
            List levelsG;
            if(level instanceof LevelN){
                levelsG = this.serviciosCentral.getListLevelN((LevelN)level, 0, 0, false);
            }else{
                levelsG = this.serviciosCentral.getListLevelC((LevelC)level, 0, 0, false);
            }
            for(Object levelTemp : levelsG){
                if(levelTemp instanceof LevelN){
                    this.serviciosLocal.addLevelN((LevelN)levelTemp);
                }else{
                    this.serviciosLocal.addLevelC((LevelC)levelTemp);
                }
            }
            
            Levels levels = new Levels(true);
            levels.setFactorid(factor.getLabelid());
            for(Levels levelsTemp : this.serviciosCentral.getListLevels(levels, 0, 0, false)){
                this.serviciosLocal.addLevels(levelsTemp);
            }
        }
        
        Steffect steffect = new Steffect(true);
        steffect.setStudyid(studyId);
        for(Steffect steffectTemp : this.serviciosCentral.getListSteffect(steffect, 0, 0, false)){
            this.serviciosLocal.addSteffect(steffectTemp);
            Represtn represtn = new Represtn(true);
            represtn.setEffectid(steffectTemp.getEffectid());
            for(Represtn represtn1 : this.serviciosCentral.getListReprestn(represtn, 0, 0, false)){
                this.serviciosLocal.addReprestn(represtn1);
                Oindex oindex = new Oindex();
                oindex.setOindexPK(new OindexPK());
                oindex.getOindexPK().setRepresno(represtn1.getRepresno());
                for(Oindex oindex1 : this.serviciosCentral.getListOindex(oindex, 0, 0, false)){
                    this.serviciosLocal.addOindex(oindex1);
                }
            }
            Effect effect = new Effect(true);
            effect.setEffectPK(new EffectPK());
            effect.getEffectPK().setEffectid(steffectTemp.getEffectid());
            for(Effect effect1 : this.serviciosCentral.getListEffect(effect, 0, 0, false)){
                this.serviciosLocal.addEffect(effect1);
            }
            Obsunit obsunit = new Obsunit(true);
            obsunit.setEffectid(represtn.getEffectid());
            for(Obsunit obsunit1 : this.serviciosCentral.getListObsunit(obsunit, 0, 0, false)){
                this.serviciosLocal.addObsunit(obsunit1);
            }
            
        }
    }

    public boolean validarStudy(Workbook workbook) {
        Study studyFull = this.serviciosCentral.getStudy(workbook.getStudy().getStudyid());
        Study studyPadreFull = this.serviciosCentral.getStudy(studyFull.getShierarchy());

        studyPadreFull.setStudyid(null);
        studyPadreFull.setShierarchy(null);

        Study studyLocalPadre = leeStudyLocal(studyPadreFull);

        if (studyLocalPadre == null) {
            studyPadreFull.setShierarchy(0);
            this.serviciosLocal.addStudy(studyPadreFull);
            studyLocalPadre.setShierarchy(studyPadreFull.getStudyid());
        }

        studyFull.setStudyid(null);
        studyFull.setShierarchy(null);

        Study studyLocalAGuardar = leeStudyLocal(studyFull);
        if (studyLocalAGuardar == null) {
            ibfb.domain.core.Study studyVista = workbook.getStudy();
            studyVista.setStudyid(null);
            studyVista.setShierarchy(studyLocalPadre.getStudyid());
            workbook.setStudy(studyVista);

            if (workbook.getStudy().getStudyType().equals(Study.STYPE_TRIAL)) {
                this.saveWorkbookFull(workbook);
            } else if (workbook.getStudy().getStudyType().equals(Study.STYPE_NURSERY)) {
                this.saveWorkbookNurseryFull(workbook);
            }
            return true;
        }
        return false;
    }

    public Study leeStudyLocal(Study studyPadreFull) {
        List<Study> listStudys = this.serviciosLocal.getListStudy(studyPadreFull, 0, 0, false);
        if (listStudys.size() > 0) {
            return listStudys.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void saveInventoryFromList(Listnms listnms, Integer transDate, List<InventoryData> inventoryDataList, Integer userid) {
        HelperInventory helperInventory = new HelperInventory();
        helperInventory.setListnms(listnms);
        helperInventory.setTransDate(transDate);
        helperInventory.setInventoryDataList(inventoryDataList);
        helperInventory.setUserid(userid);
        helperInventory.setLocalServices(serviciosLocal);
        helperInventory.saveInventoryFromList();
    }

    @Override
    public List<InventoryData> getInventoryDataFromList(final Integer listId) {
        if (listId.intValue() > 0) {
            return serviciosCentral.getInventoryDataFromList(listId);
        } else {
            return serviciosLocal.getInventoryDataFromList(listId);
        }
    }

    /**
     * Gets a list of scales used to manage inventory
     *
     * @return
     */
    @Override
    public List<Scales> getScalesForInventory() {
        HelperInventory helperInventory = new HelperInventory();
        helperInventory.setAppServices(this);
        return helperInventory.getScalesForInventory();

    }

    @Override
    public List<GermplasmSearch> getGermplasmByListStudyTrialPlotCross(List<GermplasmSearch> listFemale,
            List<GermplasmSearch> listMale) {
        return HelperGermplasm.getGermplasmByListStudyTrialPlotCross(this, listFemale, listMale);
    }

    /**
     * Returns a Names object by it GID First look for NTYPE = 1028, if not
     * exists then look for NTYPE = 1027
     *
     * @param gid GID to search
     * @return Names Object if GID does not exist then return NULL
     */
    @Override
    public Names getCimmytWheatName(Integer gid) {
        Names cimmytWheatName = null;
        CommonServices service = null;

        if (gid != null) {
            if (gid.intValue() > 0) {
                service = serviciosCentral;
            } else {
                service = serviciosLocal;
            }
            Names nameToSearch = new Names(true);
            nameToSearch.setGid(gid);
            nameToSearch.setNtype(1028);
            List<Names> namesList = service.getListNames(nameToSearch, 0, 0, false);
            if (namesList.isEmpty()) {
                // if not found then search using other ntype
                nameToSearch.setNtype(1027);
                namesList = service.getListNames(nameToSearch, 0, 0, false);
                if (!namesList.isEmpty()) {
                    // if found then get it
                    cimmytWheatName = namesList.get(0);
                }
            } else {
                cimmytWheatName = namesList.get(0);
            }
        }
        return cimmytWheatName;
    }
    
    /**
     * Deletes logically a study from database
     *
     * @param study Study to delete
     */
    @Override
    public void deleteStudy(Study study) {
        // only delete from local studys
        if (study != null && study.getStudyid().intValue() < 0) {
           serviciosLocal.deleteStudy(study);
        }
    }
    
     /**
     * Deletes logically a list
     * @param listnms List to delete
     */
    @Override
    public void deleteListnms(Listnms listnms) {
        // only delete list from local database
        if (listnms != null && listnms.getListid().intValue() < 0) {
            serviciosLocal.deleteListnms(listnms);
        }
    }
    
    /**
     * Delete logically a germplasm entry
     *
     * @param listdata Entro to delete
     */
    @Override
    public void deleteListData(Listdata listdata) {
        // only delete from local databaase 
        if (listdata != null && listdata.getListdataPK().getListid().intValue() < 0 ) {
            serviciosLocal.deleteListdata(listdata);
        }
    }

        /**
     * Gets a list for Wheat Data (cimmyt) related to BCID, Selection history
     * 1. It looks for all elements in names where gid are used by a list
     * @param listId
     * @return Gets a list for Wheat Data (cimmyt)
     */
    public List<WheatData> getDataForCimmytWheat(final Integer listId) {
        if (listId.intValue() > 0) {
            return serviciosCentral.getDataForCimmytWheat(listId);
        } else {
            return serviciosLocal.getDataForCimmytWheat(listId);
        }
    }
}