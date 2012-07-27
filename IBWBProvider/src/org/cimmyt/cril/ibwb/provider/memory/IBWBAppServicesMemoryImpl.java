/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.memory;

import ibfb.domain.core.IBFStudy;
import ibfb.domain.core.Workbook;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.AppServices;
import org.cimmyt.cril.ibwb.domain.*;
import org.cimmyt.cril.ibwb.domain.inventory.InventoryData;

/**
 *
 * @author TMSANCHEZ
 */
public class IBWBAppServicesMemoryImpl implements AppServices {
    
    private static List<Study> studyList = new ArrayList<Study>();
    
    @Override
    public List<Atributs> getAtributsList() {
        return new ArrayList<Atributs>();
    }
    
    @Override
    public int getTotalAtributs(Atributs atributsFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Atributs> getListAtributs(Atributs atributsFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Bibrefs> getBibrefsList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalBibrefs(Bibrefs bibrefsFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Bibrefs> getListBibrefs(Bibrefs bibrefsFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Changes> getChangesList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalChanges(Changes changesFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Changes> getListChanges(Changes changesFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Cntry> getCntryList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalCntry(Cntry cntryFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Cntry> getListCntry(Cntry cntryFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<DataC> getDataCList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalDataC(DataC dataCFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<DataC> getListDataC(DataC dataCFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<DataN> getDataNList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalDataN(DataN dataNFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<DataN> getListDataN(DataN dataNFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<DataT> getDataTList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalDataT(DataT dataTFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<DataT> getListDataT(DataT dataTFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Datattr> getDatattrList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalDatattr(Datattr datattrFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Datattr> getListDatattr(Datattr datattrFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Dmsattr> getDmsattrList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalDmsattr(Dmsattr dmsattrFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Dmsattr> getListDmsattr(Dmsattr dmsattrFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Dudflds> getDudfldsList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalDudflds(Dudflds dudfldsFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Dudflds> getListDudflds(Dudflds dudfldsFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Effect> getEffectList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalEffect(Effect effectFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Effect> getListEffect(Effect effectFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Factor> getFactorList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalFactor(Factor factorFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Factor> getListFactor(Factor factorFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Georef> getGeorefList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalGeoref(Georef georefFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Georef> getListGeoref(Georef georefFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Germplsm> getGermplsmList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalGermplsm(Germplsm germplsmFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Germplsm> getListGermplsm(Germplsm germplsmFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Institut> getInstitutList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalInstitut(Institut institutFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Institut> getListInstitut(Institut institutFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Instln> getInstlnList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalInstln(Instln instlnFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Instln> getListInstln(Instln instlnFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public Instln getLocalInstln() {
        Instln instln = null;
        
        instln = new Instln();
        instln.setIdesc("Unknown");
        
        
        return instln;
    }
    
    @Override
    public List<LevelC> getLevelCList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalLevelC(LevelC levelCFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<LevelC> getListLevelC(LevelC levelCFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<LevelN> getLevelNList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalLevelN(LevelN levelNFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<LevelN> getListLevelN(LevelN levelNFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<LevelT> getLevelTList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalLevelT(LevelT levelTFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<LevelT> getListLevelT(LevelT levelTFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Levels> getLevelsList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalLevels(Levels levelsFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Levels> getListLevels(Levels levelsFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Listdata> getListdataList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalListdata(Listdata listdataFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Listdata> getListListdata(Listdata listdataFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Listnms> getListnmsList() {
        return new ArrayList<Listnms>();
    }
    
    @Override
    public int getTotalListnms(Listnms listnmsFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Listnms> getListListnms(Listnms listnmsFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public Listnms getListnms(Integer idListnms) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Location> getLocationList() {
        List<Location> locationList = new ArrayList<Location>();
        
        for (int i = 0; i < 10; i++) {
            Location location = new Location();
            location.setLocid(i);
            location.setLname("Location" + i);
            locationList.add(location);
            
        }
        
        return locationList;
    }
    
    @Override
    public int getTotalLocation(Location locationFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Location> getListLocation(Location locationFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Locdes> getLocdesList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalLocdes(Locdes locdesFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Locdes> getListLocdes(Locdes locdesFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Methods> getMethodsList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalMethods(Methods methodsFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Methods> getListMethods(Methods methodsFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Names> getNamesList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalNames(Names namesFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Names> getListNames(Names namesFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Obsunit> getObsunitList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalObsunit(Obsunit obsunitFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Obsunit> getListObsunit(Obsunit obsunitFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Oindex> getOindexList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalOindex(Oindex oindexFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Oindex> getListOindex(Oindex oindexFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Persons> getPersonsList() {
        List<Persons> personsList = new ArrayList<Persons>();
        
        for (int i = 0; i < 10; i++) {
            Persons persons = new Persons();
            persons.setPersonid(i);
            persons.setFname("FirstName" + i);
            persons.setLname("LastName" + i);
            personsList.add(persons);
        }
        
        
        return personsList;
    }
    
    @Override
    public int getTotalPersons(Persons personsFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Persons> getListPersons(Persons personsFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Progntrs> getProgntrsList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalProgntrs(Progntrs progntrsFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Progntrs> getListProgntrs(Progntrs progntrsFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Represtn> getReprestnList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalReprestn(Represtn represtnFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Represtn> getListReprestn(Represtn represtnFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Scale> getScaleList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalScale(Scale scaleFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Scale> getListScale(Scale scaleFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Scalecon> getScaleconList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalScalecon(Scalecon scaleconFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Scalecon> getListScalecon(Scalecon scaleconFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Scaledis> getScaledisList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalScaledis(Scaledis scaledisFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Scaledis> getListScaledis(Scaledis scaledisFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Scaletab> getScaletabList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalScaletab(Scaletab scaletabFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Scaletab> getListScaletab(Scaletab scaletabFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Sndivs> getSndivsList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalSndivs(Sndivs sndivsFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Sndivs> getListSndivs(Sndivs sndivsFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Steffect> getSteffectList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalSteffect(Steffect steffectFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Steffect> getListSteffect(Steffect steffectFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Study> getStudyList() {
        return studyList;
    }
    
    @Override
    public int getTotalStudy(Study studyFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Study> getListStudy(Study studyFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void addStudyToLocal(Study study) {
        studyList.add(study);
    }
    
    @Override
    public List<Study> getStudyListByParent(Integer studyParent, String studyType) {
        return studyList;
    }
    
    @Override
    public List<Tmethod> getTmethodList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalTmethod(Tmethod tmethodFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Tmethod> getListTmethod(Tmethod tmethodFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Trait> getTraitList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalTrait(Trait traitFilter) {
        return 0;
    }
    
    @Override
    public List<Trait> getListTrait(Trait traitFilter, int start, int pageSize, boolean paged) {
        return new ArrayList<Trait>();
    }
    
    @Override
    public List<Udflds> getUdfldsList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalUdflds(Udflds udfldsFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Udflds> getListUdflds(Udflds udfldsFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Users> getUsersList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalUsers(Users usersFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Users> getListUsers(Users usersFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Variate> getVariateList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalVariate(Variate variateFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Variate> getListVariate(Variate variateFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Veffect> getVeffectList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int getTotalVeffect(Veffect veffectFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Veffect> getListVeffect(Veffect veffectFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public List<String> getTraitGroups() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public List<Measuredin> getMeasuredinList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getTotalMeasuredin(Measuredin measuredinFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Measuredin> getListMeasuredin(Measuredin measuredinFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Scales> getScalesList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getTotalScales(Scales scalesFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Scales> getListScales(Scales scalesFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addStudy(Study study) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Traits> getTraitsList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Traits> getTraitsListGroups() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getTotalTraits(Traits traitsFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Traits> getListTraits(Traits traitsFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveWorkbookFull(Workbook workbook) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean existsTratisTable() {
       return true;
    }

    @Override
    public void createTraitsTables() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    @Override
    public List<Scale> getListScaleGroupsLocal() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Scale> getListScaleGroupsCentral() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addScalesLocal(Scales scales) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addScalesCentral(Scales scales) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addMeasuredinLocal(Measuredin measuredin) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addMeasuredinCentral(Measuredin measuredin) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Trait> getListTraitLocal(Trait filter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Trait> getListTraitCentral(Trait filter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Scales> getListScalesLocal(Scales filter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Scales> getListScalesCentral(Scales filter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Traits> getListTraitsLocalFull(Traits filter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Traits> getListTraitsCentralFull(Traits filter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Tmethod getTmethod(Integer tmethodId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Traits getTraits(Integer traitId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Workbook getWorkbookFull(Workbook workbook, boolean datos) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Workbook getWorkbookFull(Integer studyid, boolean datos) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Study getStudy(Integer idStudy) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addListnms(Listnms listnms) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addListdata(Listdata listdata) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Variate> getListVariateConstants(Integer studyId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Variate> getListVariateTraits(Integer studyId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Object> getObservationsData(Integer studyId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getObservationsCount(Integer effectId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Obsunit> getObsunitListByStudy(Integer studyid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Factor> getFactorsForStudy(Integer studyid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Oindex> getMeasurementOindexListByStudy(Integer studyid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateWorkbook(Workbook workbook) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Scales getScalesById(Integer scaleid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TmsMethod getTmsMethod(Integer tmethodId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TmsMethod> getTmsMethodList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getTotalTmsMethod(TmsMethod tmesMthodFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TmsMethod> getListTmsMethod(TmsMethod tmsMethodFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Traits> getListTraitsOnly(Traits traitsFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Listdata> getListListdataFiltro(Listdata filter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Listdata> addNewsGermplasm(Listnms listnms, List<Listdata> listdatas, Integer userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Study getStudyFactorsTrialAndEntryFull(Integer studyid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Study getStudyByName(String studyName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveWorkbookNurseryFull(Workbook workbook) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Measuredin getMeasuredinByTraitidScaleidTmethid(Integer traitId, Integer scaleId, Integer tmethid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Factor> getMainFactorsByStudyid(Integer studyid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Factor> getGroupFactorsByStudyidAndFactorid(Integer studyid, Integer factorid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Germplsm getGermplsm(Integer gid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Germplsm getGermplsmByTidTrialPlot(Integer studyId, Integer trial, Integer plot) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResultSet getTrialRandomization(Integer studyId, Integer trialFactorId, List<String> factoresPrincipales, List<String> factoresSalida) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addTmsMethod(TmsMethod tmsMethod) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateTmsMethod(TmsMethod tmsMethod) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateTmsScale(Scales scale) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public List<Measuredin> getMeasuredInListByTrait(Integer traitId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addOrUpdateTmsTrait(Traits tmsTrait) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Dmsattr getDmsattrByDmsatrecAndDmsatype(Dmsattr dmsattr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Effect> getEffectsByEffectsids(List effectsIds) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Factor> getFactorsByFactorsids(List<Integer> factorIds) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Germplsm> getGermplsmListByStudyAndTrial(Integer studyId, Integer trial) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<LevelC> getLevelsCByLabelid(Integer labelid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<LevelN> getLevelnByLabelid(Integer labelid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Steffect> getSteffectByStudyid(Integer studyid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Integer> getEffectidsByStudyid(Integer studyid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Study> getStudysOnlyTrial() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Integer> getTrialsByStudyid(Integer studyid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Variate> getListVariateTraitsByEffectid(Integer studyId, Integer effectid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResultSet getTrialRandomization(Integer studyId, Integer trialFactorId, List<String> factoresPrincipales, List<String> factoresSalida, String trialNmame) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Object> getObservationsDataMeasurementEffect(Integer studyId, Integer effectId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Obsunit> getObsunitListByEffectid(Integer studyId, Integer effectId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //@Override
    public List<Oindex> getMeasurementOindexListByStudy(Integer studyid, Integer effectId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addTmsScaleCon(TmsScaleCon tmsScaleCon) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateTmsScaleCon(TmsScaleCon tmsScaleCon) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteTmsScaleCon(TmsScaleCon tmsScaleCon) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TmsScaleCon getTmsScaleCon(TmsScaleCon tmsScaleCon) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TmsScaleCon getTmsScaleCon(Integer idTmethod) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TmsScaleCon> getTmsScaleConList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getTotalTmsScaleCon(TmsScaleCon tmsScaleConFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TmsScaleCon> getListTmsScaleCon(TmsScaleCon tmsScaleConFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TmsScaleCon> getTmsScaleConByMeasuredinId(Integer measuredindid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addTmsScaleDis(TmsScaleDis tmsScaleDis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateTmsScaleDis(TmsScaleDis tmsScaleDis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteTmsScaleDis(TmsScaleDis tmsScaleDis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TmsScaleDis getTmsScaleDis(TmsScaleDis tmsScaleDis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TmsScaleDis getTmsScaleDis(Integer idTmethod) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TmsScaleDis> getTmsScaleDisList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getTotalTmsScaleDis(TmsScaleDis tmsScaleDisFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TmsScaleDis> getListTmsScaleDis(TmsScaleDis tmsScaleDisFilter, int start, int pageSize, boolean paged) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TmsScaleDis> getTmsScaleDisByMeasuredinId(Integer measuredindid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Obsunit> getObsunitListByStudyTrials(Integer studyId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer getLoggedUserId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveInventoryFromList(Listnms listnms, Integer transDate, List<InventoryData> inventoryDataList, Integer userid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer getLoggedUserId(String userid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Location> getLocationsByCountryLocidRange(Integer countryId, Integer fromLocid, Integer toLocid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<InventoryData> getInventoryDataFromList(Integer listId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public List<Scales> getScalesForInventory() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Factor getFactorByStudyidAndFname(Integer studyid, String fname) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public String getNextMaxForBCID(Integer studyId, String cadena, Integer ntype) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Names getNamesByGid(Germplsm germplasm, Boolean preferido) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer getMaxForSelection(Integer studyId, String cadena, Integer ntype) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<GermplasmSearch> getGermplasmByListStudyTrialPlotCross(List<GermplasmSearch> listFmale, List<GermplasmSearch> listMale) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResultSet getTrialRandomizationFast(Integer studyId, Integer trialFactorId, List<String> factoresPrincipales, List<String> factoresSalida, String trialNmame) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public StudySearch getListGermplasmAndPlotByStudyidAndTrial(StudySearch studySearch) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Listdata> saveGerplasmCimmytWheat(List<Listdata> listGermplsm, Listnms listnms, Integer userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Listdata agregarGermPlasmCimmytWheat(String nameGermplasmHistory, String nameGermplasmBCID, Listdata listdata, Integer userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<GermplasmSearch> getGermplasmByListStudyTrialPlotCross(AppServices appServices, List<GermplasmSearch> listFmale, List<GermplasmSearch> listMale) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Names getCimmytWheatName(Integer gid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void migrateWorkbook(Integer studyId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
