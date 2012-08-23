package org.cimmyt.cril.ibwb.api;

import ibfb.domain.core.Measurement;

import ibfb.domain.core.Workbook;
import java.sql.ResultSet;
import java.util.List;

import org.cimmyt.cril.ibwb.domain.*;
import org.cimmyt.cril.ibwb.domain.inventory.InventoryData;

public interface AppServices {

    //-----------------------------------Atributs---------------------------
    /**
     * Gets a list of Objects Atributs
     *
     * @return List
     */
    public List<Atributs> getAtributsList();

    /**
     * Gets the number of records matching with filter
     *
     * @param atributsFiltro Object to count total items
     */
    public int getTotalAtributs(Atributs atributsFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param atributsFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Atributs> getListAtributs(Atributs atributsFilter, int start, int pageSize, boolean paged);

    //-----------------------------------Bibrefs---------------------------
    /**
     * Gets a list of Objects Bibrefs
     *
     * @return List
     */
    public List<Bibrefs> getBibrefsList();

    /**
     * Gets the number of records matching with filter
     *
     * @param bibrefsFiltro Object to count total items
     */
    public int getTotalBibrefs(Bibrefs bibrefsFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param bibrefsFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Bibrefs> getListBibrefs(Bibrefs bibrefsFilter, int start, int pageSize, boolean paged);

    //-----------------------------------Changes---------------------------
    /**
     * Gets a list of Objects Changes
     *
     * @return List
     */
    public List<Changes> getChangesList();

    /**
     * Gets the number of records matching with filter
     *
     * @param changesFiltro Object to count total items
     */
    public int getTotalChanges(Changes changesFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param changesFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Changes> getListChanges(Changes changesFilter, int start, int pageSize, boolean paged);

    //-----------------------------------Cntry---------------------------
    /**
     * Gets a list of Objects Cntry
     *
     * @return List
     */
    public List<Cntry> getCntryList();

    /**
     * Gets the number of records matching with filter
     *
     * @param cntryFiltro Object to count total items
     */
    public int getTotalCntry(Cntry cntryFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param cntryFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Cntry> getListCntry(Cntry cntryFilter, int start, int pageSize, boolean paged);

    //-----------------------------------DataC---------------------------
    /**
     * Gets a list of Objects DataC
     *
     * @return List
     */
    public List<DataC> getDataCList();

    /**
     * Gets the number of records matching with filter
     *
     * @param dataCFiltro Object to count total items
     */
    public int getTotalDataC(DataC dataCFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param dataCFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<DataC> getListDataC(DataC dataCFilter, int start, int pageSize, boolean paged);

    //-----------------------------------DataN---------------------------
    /**
     * Gets a list of Objects DataN
     *
     * @return List
     */
    public List<DataN> getDataNList();

    /**
     * Gets the number of records matching with filter
     *
     * @param dataNFiltro Object to count total items
     */
    public int getTotalDataN(DataN dataNFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param dataNFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<DataN> getListDataN(DataN dataNFilter, int start, int pageSize, boolean paged);

    //-----------------------------------DataT---------------------------
    /**
     * Gets a list of Objects DataT
     *
     * @return List
     */
    public List<DataT> getDataTList();

    /**
     * Gets the number of records matching with filter
     *
     * @param dataTFiltro Object to count total items
     */
    public int getTotalDataT(DataT dataTFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param dataTFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<DataT> getListDataT(DataT dataTFilter, int start, int pageSize, boolean paged);

    //-----------------------------------Datattr---------------------------
    /**
     * Gets a list of Objects Datattr
     *
     * @return List
     */
    public List<Datattr> getDatattrList();

    /**
     * Gets the number of records matching with filter
     *
     * @param datattrFiltro Object to count total items
     */
    public int getTotalDatattr(Datattr datattrFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param datattrFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Datattr> getListDatattr(Datattr datattrFilter, int start, int pageSize, boolean paged);

    //-----------------------------------Dmsattr---------------------------
    /**
     * Gets a list of Objects Dmsattr
     *
     * @return List
     */
    public List<Dmsattr> getDmsattrList();

    /**
     * Gets the number of records matching with filter
     *
     * @param dmsattrFiltro Object to count total items
     */
    public int getTotalDmsattr(Dmsattr dmsattrFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param dmsattrFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Dmsattr> getListDmsattr(Dmsattr dmsattrFilter, int start, int pageSize, boolean paged);

    public Dmsattr getDmsattrByDmsatrecAndDmsatype(Dmsattr dmsattr);

    //-----------------------------------Dudflds---------------------------
    /**
     * Gets a list of Objects Dudflds
     *
     * @return List
     */
    public List<Dudflds> getDudfldsList();

    /**
     * Gets the number of records matching with filter
     *
     * @param dudfldsFiltro Object to count total items
     */
    public int getTotalDudflds(Dudflds dudfldsFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param dudfldsFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Dudflds> getListDudflds(Dudflds dudfldsFilter, int start, int pageSize, boolean paged);

    //-----------------------------------Effect---------------------------
    /**
     * Gets a list of Objects Effect
     *
     * @return List
     */
    public List<Effect> getEffectList();

    /**
     * Gets the number of records matching with filter
     *
     * @param effectFiltro Object to count total items
     */
    public int getTotalEffect(Effect effectFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param effectFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Effect> getListEffect(Effect effectFilter, int start, int pageSize, boolean paged);

    public List<Effect> getEffectsByEffectsids(final List effectsIds);

    //-----------------------------------Factor---------------------------
    /**
     * Gets a list of Objects Factor
     *
     * @return List
     */
    public List<Factor> getFactorList();

    /**
     * Gets the number of records matching with filter
     *
     * @param factorFiltro Object to count total items
     */
    public int getTotalFactor(Factor factorFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param factorFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Factor> getListFactor(Factor factorFilter, int start, int pageSize, boolean paged);

    public List<Factor> getMainFactorsByStudyid(Integer studyid);

    public List<Factor> getGroupFactorsByStudyidAndFactorid(Integer studyid, Integer factorid);

    public List<Factor> getFactorsByFactorsids(List<Integer> factorIds);

    public Factor getFactorByStudyidAndFname(Integer studyid, String fname);

    //-----------------------------------Georef---------------------------
    /**
     * Gets a list of Objects Georef
     *
     * @return List
     */
    public List<Georef> getGeorefList();

    /**
     * Gets the number of records matching with filter
     *
     * @param georefFiltro Object to count total items
     */
    public int getTotalGeoref(Georef georefFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param georefFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Georef> getListGeoref(Georef georefFilter, int start, int pageSize, boolean paged);

    //-----------------------------------Germplsm---------------------------
    public List<Listdata> addNewsGermplasm(Listnms listnms, List<Listdata> listdatas, Integer userId);

    /**
     * Gets a list of Objects Germplsm
     *
     * @return List
     */
    public List<Germplsm> getGermplsmList();

    /**
     * Gets the number of records matching with filter
     *
     * @param germplsmFiltro Object to count total items
     */
    public int getTotalGermplsm(Germplsm germplsmFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param germplsmFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Germplsm> getListGermplsm(Germplsm germplsmFilter, int start, int pageSize, boolean paged);

    public Germplsm getGermplsm(Integer gid);

    public Germplsm getGermplsmByTidTrialPlot(Integer studyId, Integer trial, Integer plot);

    public List<Germplsm> getGermplsmListByStudyAndTrial(
            Integer studyId,
            Integer trial);

    //-----------------------------------Institut---------------------------
    /**
     * Gets a list of Objects Institut
     *
     * @return List
     */
    public List<Institut> getInstitutList();

    /**
     * Gets the number of records matching with filter
     *
     * @param institutFiltro Object to count total items
     */
    public int getTotalInstitut(Institut institutFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param institutFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Institut> getListInstitut(Institut institutFilter, int start, int pageSize, boolean paged);

    //-----------------------------------Instln---------------------------
    /**
     * Gets a list of Objects Instln
     *
     * @return List
     */
    public List<Instln> getInstlnList();

    /**
     * Gets the number of records matching with filter
     *
     * @param instlnFiltro Object to count total items
     */
    public int getTotalInstln(Instln instlnFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param instlnFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Instln> getListInstln(Instln instlnFilter, int start, int pageSize, boolean paged);

    /**
     * Gets the first record in Instln table from local database
     *
     * @return Instln the first record in Instln from local
     */
    public Instln getLocalInstln();

    //-----------------------------------LevelC---------------------------
    /**
     * Gets a list of Objects LevelC
     *
     * @return List
     */
    public List<LevelC> getLevelCList();

    /**
     * Gets the number of records matching with filter
     *
     * @param levelCFiltro Object to count total items
     */
    public int getTotalLevelC(LevelC levelCFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param levelCFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<LevelC> getListLevelC(LevelC levelCFilter, int start, int pageSize, boolean paged);

    public List<LevelC> getLevelsCByLabelid(Integer labelid);

    //-----------------------------------LevelN---------------------------
    /**
     * Gets a list of Objects LevelN
     *
     * @return List
     */
    public List<LevelN> getLevelNList();

    /**
     * Gets the number of records matching with filter
     *
     * @param levelNFiltro Object to count total items
     */
    public int getTotalLevelN(LevelN levelNFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param levelNFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<LevelN> getListLevelN(LevelN levelNFilter, int start, int pageSize, boolean paged);

    //-----------------------------------LevelT---------------------------
    /**
     * Gets a list of Objects LevelT
     *
     * @return List
     */
    public List<LevelT> getLevelTList();

    /**
     * Gets the number of records matching with filter
     *
     * @param levelTFiltro Object to count total items
     */
    public int getTotalLevelT(LevelT levelTFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param levelTFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<LevelT> getListLevelT(LevelT levelTFilter, int start, int pageSize, boolean paged);

    public List<LevelN> getLevelnByLabelid(Integer labelid);

    //-----------------------------------Levels---------------------------
    /**
     * Gets a list of Objects Levels
     *
     * @return List
     */
    public List<Levels> getLevelsList();

    /**
     * Gets the number of records matching with filter
     *
     * @param levelsFiltro Object to count total items
     */
    public int getTotalLevels(Levels levelsFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param levelsFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Levels> getListLevels(Levels levelsFilter, int start, int pageSize, boolean paged);

    //-----------------------------------Listdata---------------------------
    /**
     * Gets a list of Objects Listdata
     *
     * @return List
     */
    public List<Listdata> getListdataList();

    /**
     * Gets the number of records matching with filter
     *
     * @param listdataFiltro Object to count total items
     */
    public int getTotalListdata(Listdata listdataFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param listdataFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Listdata> getListListdata(Listdata listdataFilter, int start, int pageSize, boolean paged);

    public List<Listdata> getListListdataFiltro(Listdata filter, int start, int pageSize, boolean paged);

    //-----------------------------------Listnms---------------------------
    /**
     * Gets a list of Objects Listnms
     *
     * @return List
     */
    public List<Listnms> getListnmsList();

    /**
     * Gets the number of records matching with filter
     *
     * @param listnmsFiltro Object to count total items
     */
    public int getTotalListnms(Listnms listnmsFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param listnmsFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Listnms> getListListnms(Listnms listnmsFilter, int start, int pageSize, boolean paged);

    public Listnms getListnms(Integer idListnms);
    //-----------------------------------Location---------------------------

    /**
     * Gets a list of Objects Location
     *
     * @return List
     */
    public List<Location> getLocationList();

    /**
     * Gets the number of records matching with filter
     *
     * @param locationFiltro Object to count total items
     */
    public int getTotalLocation(Location locationFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param locationFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Location> getListLocation(Location locationFilter, int start, int pageSize, boolean paged);

    public List<Location> getLocationsByCountryLocidRange(final Integer countryId, final Integer fromLocid, final Integer toLocid);

    //-----------------------------------Locdes---------------------------
    /**
     * Gets a list of Objects Locdes
     *
     * @return List
     */
    public List<Locdes> getLocdesList();

    /**
     * Gets the number of records matching with filter
     *
     * @param locdesFiltro Object to count total items
     */
    public int getTotalLocdes(Locdes locdesFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param locdesFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Locdes> getListLocdes(Locdes locdesFilter, int start, int pageSize, boolean paged);

    //-----------------------------------Measuredin---------------------------
    /**
     * Adds an Object Measuredin to database Local
     *
     * @param measuredin Objeto a agregar
     */
    public void addMeasuredinLocal(Measuredin measuredin);

    /**
     * Adds an Object Measuredin to database Central
     *
     * @param measuredin Objeto a agregar
     */
    public void addMeasuredinCentral(Measuredin measuredin);

    /**
     * Gets a list of Objects Measuredin
     *
     * @return List
     */
    public List<Measuredin> getMeasuredinList();

    /**
     * Gets the number of records matching with filter
     *
     * @param measuredinFiltro Object to count total items
     */
    public int getTotalMeasuredin(Measuredin measuredinFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param measuredinFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Measuredin> getListMeasuredin(Measuredin measuredinFilter, int start, int pageSize, boolean paged);

    /**
     * Get a measuredin
     *
     * @param traitId
     * @param scaleId
     * @param tmethid
     * @return Measuredin
     */
    public Measuredin getMeasuredinByTraitidScaleidTmethid(Integer traitId, Integer scaleId, Integer tmethid);

    /**
     * Gets a list of measured in list by trait id
     *
     * @param traitId Trait ID
     * @return List of measuredin objects filled with it's scale and method
     */
    public List<Measuredin> getMeasuredInListByTrait(final Integer traitId);

    //-----------------------------------Methods---------------------------
    /**
     * Gets a list of Objects Methods
     *
     * @return List
     */
    public List<Methods> getMethodsList();

    /**
     * Gets the number of records matching with filter
     *
     * @param methodsFiltro Object to count total items
     */
    public int getTotalMethods(Methods methodsFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param methodsFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Methods> getListMethods(Methods methodsFilter, int start, int pageSize, boolean paged);

    //-----------------------------------Names---------------------------
    /**
     * Gets a list of Objects Names
     *
     * @return List
     */
    public List<Names> getNamesList();

    /**
     * Gets the number of records matching with filter
     *
     * @param namesFiltro Object to count total items
     */
    public int getTotalNames(Names namesFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param namesFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Names> getListNames(Names namesFilter, int start, int pageSize, boolean paged);

    public String getNextMaxForBCID(Integer studyId, String cadena, Integer ntype);

    public Names getNamesByGid(Germplsm germplasm, Boolean preferido);

    public Integer getMaxForSelection(Integer studyId, String cadena, Integer ntype);

    //-----------------------------------Obsunit---------------------------
    /**
     * Gets a list of Objects Obsunit
     *
     * @return List
     */
    public List<Obsunit> getObsunitList();

    /**
     * Gets the number of records matching with filter
     *
     * @param obsunitFiltro Object to count total items
     */
    public int getTotalObsunit(Obsunit obsunitFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param obsunitFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Obsunit> getListObsunit(Obsunit obsunitFilter, int start, int pageSize, boolean paged);

    //-----------------------------------Oindex---------------------------
    /**
     * Gets a list of Objects Oindex
     *
     * @return List
     */
    public List<Oindex> getOindexList();

    /**
     * Gets the number of records matching with filter
     *
     * @param oindexFiltro Object to count total items
     */
    public int getTotalOindex(Oindex oindexFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param oindexFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Oindex> getListOindex(Oindex oindexFilter, int start, int pageSize, boolean paged);

    //-----------------------------------Persons---------------------------
    /**
     * Gets a list of Objects Persons
     *
     * @return List
     */
    public List<Persons> getPersonsList();

    /**
     * Gets the number of records matching with filter
     *
     * @param personsFiltro Object to count total items
     */
    public int getTotalPersons(Persons personsFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param personsFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Persons> getListPersons(Persons personsFilter, int start, int pageSize, boolean paged);

    //-----------------------------------Progntrs---------------------------
    /**
     * Gets a list of Objects Progntrs
     *
     * @return List
     */
    public List<Progntrs> getProgntrsList();

    /**
     * Gets the number of records matching with filter
     *
     * @param progntrsFiltro Object to count total items
     */
    public int getTotalProgntrs(Progntrs progntrsFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param progntrsFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Progntrs> getListProgntrs(Progntrs progntrsFilter, int start, int pageSize, boolean paged);

    //-----------------------------------Represtn---------------------------
    /**
     * Gets a list of Objects Represtn
     *
     * @return List
     */
    public List<Represtn> getReprestnList();

    /**
     * Gets the number of records matching with filter
     *
     * @param represtnFiltro Object to count total items
     */
    public int getTotalReprestn(Represtn represtnFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param represtnFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Represtn> getListReprestn(Represtn represtnFilter, int start, int pageSize, boolean paged);

    //-----------------------------------Scale---------------------------
    /**
     * Gets a list of Objects Scale
     *
     * @return List
     */
    public List<Scale> getScaleList();

    /**
     * Gets the number of records matching with filter
     *
     * @param scaleFiltro Object to count total items
     */
    public int getTotalScale(Scale scaleFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param scaleFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Scale> getListScale(Scale scaleFilter, int start, int pageSize, boolean paged);

    /**
     * Gets a list of Objects Scale Groups
     *
     * @return List Scale
     */
    public List<Scale> getListScaleGroupsLocal();

    /**
     * Gets a list of Objects Scale Groups
     *
     * @return List Scale
     */
    public List<Scale> getListScaleGroupsCentral();

//-----------------------------------Scales---------------------------
    /**
     * Adds an Object Scales to database Local
     *
     * @param scales Objeto a agregar
     */
    public void addScalesLocal(Scales scales);

    /**
     * Adds an Object Scales to database Cental
     *
     * @param scales Objeto a agregar
     */
    public void addScalesCentral(Scales scales);

    /**
     * Gets a list of Objects Scales
     *
     * @return List
     */
    public List<Scales> getScalesList();

    /**
     * Gets the number of records matching with filter
     *
     * @param scalesFiltro Object to count total items
     */
    public int getTotalScales(Scales scalesFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param scalesFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Scales> getListScales(Scales scalesFilter, int start, int pageSize, boolean paged);

    /**
     * Gets a list of Objects Local for pagination
     *
     * @param scalesFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Scales> getListScalesLocal(Scales filter, int start, int pageSize, boolean paged);

    /**
     * Gets a list of Objects Central for pagination
     *
     * @param scalesFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Scales> getListScalesCentral(Scales filter, int start, int pageSize, boolean paged);

    /**
     * Gets a Scale object by its ID
     *
     * @param scaleid ID to search
     * @return
     */
    public Scales getScalesById(final Integer scaleid);

    /**
     * Updates an scale
     */
    public void updateTmsScale(Scales scale);

    //-----------------------------------Scalecon---------------------------
    /**
     * Gets a list of Objects Scalecon
     *
     * @return List
     */
    public List<Scalecon> getScaleconList();

    /**
     * Gets the number of records matching with filter
     *
     * @param scaleconFiltro Object to count total items
     */
    public int getTotalScalecon(Scalecon scaleconFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param scaleconFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Scalecon> getListScalecon(Scalecon scaleconFilter, int start, int pageSize, boolean paged);

    //-----------------------------------Scaledis---------------------------
    /**
     * Gets a list of Objects Scaledis
     *
     * @return List
     */
    public List<Scaledis> getScaledisList();

    /**
     * Gets the number of records matching with filter
     *
     * @param scaledisFiltro Object to count total items
     */
    public int getTotalScaledis(Scaledis scaledisFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param scaledisFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Scaledis> getListScaledis(Scaledis scaledisFilter, int start, int pageSize, boolean paged);

    //-----------------------------------Scaletab---------------------------
    /**
     * Gets a list of Objects Scaletab
     *
     * @return List
     */
    public List<Scaletab> getScaletabList();

    /**
     * Gets the number of records matching with filter
     *
     * @param scaletabFiltro Object to count total items
     */
    public int getTotalScaletab(Scaletab scaletabFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param scaletabFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Scaletab> getListScaletab(Scaletab scaletabFilter, int start, int pageSize, boolean paged);

    //-----------------------------------Sndivs---------------------------
    /**
     * Gets a list of Objects Sndivs
     *
     * @return List
     */
    public List<Sndivs> getSndivsList();

    /**
     * Gets the number of records matching with filter
     *
     * @param sndivsFiltro Object to count total items
     */
    public int getTotalSndivs(Sndivs sndivsFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param sndivsFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Sndivs> getListSndivs(Sndivs sndivsFilter, int start, int pageSize, boolean paged);

//-----------------------------------Steffect---------------------------
    /**
     * Gets a list of Objects Steffect
     *
     * @return List
     */
    public List<Steffect> getSteffectList();

    /**
     * Gets the number of records matching with filter
     *
     * @param steffectFiltro Object to count total items
     */
    public int getTotalSteffect(Steffect steffectFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param steffectFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Steffect> getListSteffect(Steffect steffectFilter, int start, int pageSize, boolean paged);

    public List<Steffect> getSteffectByStudyid(Integer studyid);

    public List<Integer> getEffectidsByStudyid(Integer studyid);

//-----------------------------------Study---------------------------
    public Study getStudy(Integer idStudy);

    /**
     * Adds an Object Study to database
     *
     * @param study Objeto a agregar
     */
    public void addStudy(Study study);

    /**
     * Gets a list of Objects Study
     *
     * @return List
     */
    public List<Study> getStudyList();

    /**
     * Gets the number of records matching with filter
     *
     * @param studyFiltro Object to count total items
     */
    public int getTotalStudy(Study studyFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param studyFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Study> getListStudy(Study studyFilter, int start, int pageSize, boolean paged);

    /**
     * Adds an Object Study to database
     *
     * @param study Study to add
     */
    public void addStudyToLocal(Study study);

    /**
     * Gets a list of Objects Study from a Parent Study
     *
     * @param studyParent ID for parent
     * @param studyType Study type to be filtered (T=Trial, N=Nursery)
     * @return List List of studies or empty list if not found
     */
    public List<Study> getStudyListByParent(Integer studyParent, String studyType);

    /**
     * Returns an study by study name
     *
     * @param studyName Study name to search
     * @return
     * <code>true</cide> if found,
     * <code>false</code> if not found
     */
    public Study getStudyByName(String studyName);

    /**
     * Return a studys
     *
     * @return List<Study>
     */
    public List<Study> getStudysOnlyTrial();

    public List<Integer> getTrialsByStudyid(Integer studyid);

    //-----------------------------------Tmethod---------------------------
    public Tmethod getTmethod(Integer tmethodId);

    /**
     * Gets a list of Objects Tmethod
     *
     * @return List
     */
    public List<Tmethod> getTmethodList();

    /**
     * Gets the number of records matching with filter
     *
     * @param tmethodFiltro Object to count total items
     */
    public int getTotalTmethod(Tmethod tmethodFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param tmethodFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Tmethod> getListTmethod(Tmethod tmethodFilter, int start, int pageSize, boolean paged);

//-----------------------------------Tmethod---------------------------
    public TmsMethod getTmsMethod(Integer tmethodId);

    /**
     * Gets a list of Objects TmsMethod
     *
     * @return List
     */
    public List<TmsMethod> getTmsMethodList();

    /**
     * Gets the number of records matching with filter
     *
     * @param tmsMethodFiltro Object to count total items
     */
    public int getTotalTmsMethod(TmsMethod tmesMthodFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param tmsMethodFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<TmsMethod> getListTmsMethod(TmsMethod tmsMethodFilter, int start, int pageSize, boolean paged);

    /**
     * Add a tmsmethod to local database
     *
     * @param tmsMethod
     */
    public void addTmsMethod(TmsMethod tmsMethod);

    /**
     * Updates a TMSMETOD in local database
     */
    public void updateTmsMethod(TmsMethod tmsMethod);

    //-----------------------------------TmsScaleCon---------------------------
    /**
     * Adds an Object TmsScaleCon to database
     *
     * @param tmsScaleCon Objeto a agregar
     */
    public void addTmsScaleCon(TmsScaleCon tmsScaleCon);

    /**
     * Updates a record of type TmsScaleCon in database
     *
     * @param tmsScaleCon Objeto a actualizar
     */
    public void updateTmsScaleCon(TmsScaleCon tmsScaleCon);

    /**
     * Deletes an object TmsScaleCon from database
     *
     * @param tmsScaleCon Objeto a eliminar
     */
    public void deleteTmsScaleCon(TmsScaleCon tmsScaleCon);

    /**
     * Gets an Object from database TmsScaleCon of the type TmsScaleCon
     *
     * @param tmsScaleCon
     * @return TmsScaleCon
     */
    public TmsScaleCon getTmsScaleCon(TmsScaleCon tmsScaleCon);

    /**
     * Gets an Object of type TmsScaleCon Finding the record by its ID
     * TmsScaleCon in String format
     *
     * @param idTmethod
     * @return TmsScaleCon
     */
    public TmsScaleCon getTmsScaleCon(Integer idTmethod);

    /**
     * Gets a list of Objects TmsScaleCon
     *
     * @return List
     */
    public List<TmsScaleCon> getTmsScaleConList();

    /**
     * Gets the number of records matching with filter
     *
     * @param tmsScaleConFiltro Object to count total items
     */
    public int getTotalTmsScaleCon(TmsScaleCon tmsScaleConFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param tmethodFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<TmsScaleCon> getListTmsScaleCon(TmsScaleCon tmsScaleConFilter, int start, int pageSize, boolean paged);

    /**
     * Gets a list of TmsScaleCon by it measuredin id
     *
     * @param measuredindid measuredin to search
     * @return list of TmsScaleCon or empty list
     */
    public List<TmsScaleCon> getTmsScaleConByMeasuredinId(final Integer measuredindid);

    //-----------------------------------TmsScaleDis---------------------------
    /**
     * Adds an Object TmsScaleDis to database
     *
     * @param tmsScaleDis Objeto a agregar
     */
    public void addTmsScaleDis(TmsScaleDis tmsScaleDis);

    /**
     * Updates a record of type TmsScaleDis in database
     *
     * @param tmsScaleDis Objeto a actualizar
     */
    public void updateTmsScaleDis(TmsScaleDis tmsScaleDis);

    /**
     * Deletes an object TmsScaleDis from database
     *
     * @param tmsScaleDis Objeto a eliminar
     */
    public void deleteTmsScaleDis(TmsScaleDis tmsScaleDis);

    /**
     * Gets an Object from database TmsScaleDis of the type TmsScaleDis
     *
     * @param tmsScaleDis
     * @return TmsScaleDis
     */
    public TmsScaleDis getTmsScaleDis(TmsScaleDis tmsScaleDis);

    /**
     * Gets an Object of type TmsScaleDis Finding the record by its ID
     * TmsScaleDis in String format
     *
     * @param idTmethod
     * @return TmsScaleDis
     */
    public TmsScaleDis getTmsScaleDis(Integer idTmethod);

    /**
     * Gets a list of Objects TmsScaleDis
     *
     * @return List
     */
    public List<TmsScaleDis> getTmsScaleDisList();

    /**
     * Gets the number of records matching with filter
     *
     * @param tmsScaleDisFiltro Object to count total items
     */
    public int getTotalTmsScaleDis(TmsScaleDis tmsScaleDisFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param tmethodFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<TmsScaleDis> getListTmsScaleDis(TmsScaleDis tmsScaleDisFilter, int start, int pageSize, boolean paged);

    /**
     * Gets a list of TmsScaleDis by it measuredin id
     *
     * @param measuredindid measuredin to search
     * @return list of TmsScaleDis or empty list
     */
    public List<TmsScaleDis> getTmsScaleDisByMeasuredinId(final Integer measuredindid);

//-----------------------------------Trait---------------------------
    /**
     * Gets a list of Objects Trait
     *
     * @return List
     */
    public List<Trait> getTraitList();

    /**
     * Gets the number of records matching with filter
     *
     * @param traitFiltro Object to count total items
     */
    public int getTotalTrait(Trait traitFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param traitFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Trait> getListTrait(Trait traitFilter, int start, int pageSize, boolean paged);

    /**
     * Gets a list of Objects Local for pagination
     *
     * @param traitFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Trait> getListTraitLocal(Trait filter, int start, int pageSize, boolean paged);

    /**
     * Gets a list of Objects Central for pagination
     *
     * @param traitFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Trait> getListTraitCentral(Trait filter, int start, int pageSize, boolean paged);

    /**
     *
     * @return
     */
    public List<String> getTraitGroups();
//-----------------------------------Traits---------------------------

    public Traits getTraits(Integer traitId);

    /**
     * Gets a list of Objects Traits
     *
     * @return List
     */
    public List<Traits> getTraitsList();

    /**
     * Gets a list of Objects Groups an traits
     *
     * @return List
     */
    public List<Traits> getTraitsListGroups();

    /**
     * Gets the number of records matching with filter
     *
     * @param traitsFiltro Object to count total items
     */
    public int getTotalTraits(Traits traitsFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param traitsFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Traits> getListTraits(Traits traitsFilter, int start, int pageSize, boolean paged);

    public List<Traits> getListTraitsOnly(Traits traitsFilter, int start, int pageSize, boolean paged);

    /**
     * Gets a list of Objects Local for pagination
     *
     * @param traitsFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Traits> getListTraitsLocalFull(Traits filter, int start, int pageSize, boolean paged);

    /**
     * Gets a list of Objects Central for pagination
     *
     * @param traitsFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Traits> getListTraitsCentralFull(Traits filter, int start, int pageSize, boolean paged);

    /**
     * Add or update a TmsTrait to Database
     */
    public void addOrUpdateTmsTrait(Traits tmsTrait);

//-----------------------------------Udflds---------------------------
    /**
     * Gets a list of Objects Udflds
     *
     * @return List
     */
    public List<Udflds> getUdfldsList();

    /**
     * Gets the number of records matching with filter
     *
     * @param udfldsFiltro Object to count total items
     */
    public int getTotalUdflds(Udflds udfldsFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param udfldsFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Udflds> getListUdflds(Udflds udfldsFilter, int start, int pageSize, boolean paged);

//-----------------------------------Users---------------------------
    /**
     * Gets a list of Objects Users
     *
     * @return List
     */
    public List<Users> getUsersList();

    /**
     * Gets the number of records matching with filter
     *
     * @param usersFiltro Object to count total items
     */
    public int getTotalUsers(Users usersFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param usersFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Users> getListUsers(Users usersFilter, int start, int pageSize, boolean paged);

    /**
     * get ID for logged user according to following parameters from local
     * database USTATUS = 1 UACC = 100 LOCAL ICIS ADMINISTRATOR UTYPE = 422
     * LOCAL DATABASE ADMINISTRATOR
     *
     * @return
     */
    public Integer getLoggedUserId();

    /**
     * get ID for logged user according to following parameters from local
     * database
     *
     * @param userid
     * @return
     */
    public Integer getLoggedUserId(String userid);

//-----------------------------------Variate---------------------------
    /**
     * Gets a list of Objects Variate
     *
     * @return List
     */
    public List<Variate> getVariateList();

    /**
     * Gets the number of records matching with filter
     *
     * @param variateFiltro Object to count total items
     */
    public int getTotalVariate(Variate variateFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param variateFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Variate> getListVariate(Variate variateFilter, int start, int pageSize, boolean paged);

    /**
     * Return a list of Varietes marked as constants
     *
     * @param studyId Study Identifier
     * @return List of variantes or empty list if not found
     */
    public List<Variate> getListVariateConstants(Integer studyId);

    /**
     * Return a list of Varietes marked as Variate ( traits)
     *
     * @param studyId Study Identifier
     * @return List of variantes or empty list if not found
     */
    public List<Variate> getListVariateTraits(Integer studyId);

    public List<Variate> getListVariateTraitsByEffectid(Integer studyId, Integer effectid);

//-----------------------------------Veffect---------------------------
    /**
     * Gets a list of Objects Veffect
     *
     * @return List
     */
    public List<Veffect> getVeffectList();

    /**
     * Gets the number of records matching with filter
     *
     * @param veffectFiltro Object to count total items
     */
    public int getTotalVeffect(Veffect veffectFilter);

    /**
     * Gets a list of Objects for pagination
     *
     * @param veffectFiltro	The filter object
     * @param inicio initial record
     * @param tamanioPagina page size
     * @return List
     */
    public List<Veffect> getListVeffect(Veffect veffectFilter, int start, int pageSize, boolean paged);

//------------------------------------------------------------------------------------------------------------
//-------------------------------------Estructura de trabajo de la dll----------------------------------------
//------------------------------------------------------------------------------------------------------------
    public Workbook getWorkbookFull(Workbook workbook, boolean datos);

    public Workbook getWorkbookFull(Integer studyid, boolean datos);

    /**
     * Save all information from a Workbook into Database
     *
     * @param workbook
     */
    public void saveWorkbookFull(Workbook workbook);

    public void saveWorkbookNurseryFull(Workbook workbook);

    public Study getStudyFactorsTrialAndEntryFull(Integer studyid);

    /**
     * Checks if Tratis, Scales and Measuredin tables already exists in database
     *
     * @return
     * <code>true</code> if exists,
     * <code>false</code> if does not exist.
     */
    public boolean existsTratisTable();

    /**
     * Create TraTratis, Scales and Measuredin
     */
    public void createTraitsTables();

    /**
     * Adds an Object Listnms to database
     *
     * @param listnms Objeto a agregar
     */
    public void addListnms(Listnms listnms);

    /**
     * Adds an Object Listdata to database
     *
     * @param listdata Objeto a agregar
     */
    public void addListdata(Listdata listdata);

    /**
     * Gets a list of observations data for a study. It contains items of DATAN
     * or DATAC type
     *
     * @param studyId Study ID to search
     * @return Observations data list or empnty list
     */
    public List<Object> getObservationsData(Integer studyId);

    /**
     * Gets a list of observations data for a study. It contains items of DATAN
     * or DATAC type
     *
     * @param studyId Study ID to search
     * @param effectId Effect ID to search
     * @return Observations data list or empnty list
     */
    public List<Object> getObservationsDataMeasurementEffect(Integer studyId, Integer effectId);

    /**
     * Get number of rows for an effect id. For example you can retrieve row
     * number for Measurement Effect
     *
     * @param effectId
     * @return
     */
    public int getObservationsCount(final Integer studyId);

    /**
     * Gets a list of observations unit for a effect id
     *
     * @param studyid Study id
     * @return List of observations units or empty list
     */
    public List<Obsunit> getObsunitListByStudy(final Integer studyId);

    public List<Obsunit> getObsunitListByStudyTrials(final Integer studyId);

    /**
     * Gets a list of observations unit for a effect id
     *
     * @param studyid Study id
     * @param effectid Effect id
     * @return List of observations units or empty list
     */
    public List<Obsunit> getObsunitListByEffectid(final Integer studyId, final Integer effectId);

    /**
     * Return a list of grouping factors by study id
     *
     * @param studyid ID for study
     * @return list of factor or empty list if study id not found
     */
    public List<Factor> getFactorsForStudy(final Integer studyid);

    /**
     * Return a list of Oindex for measurement for a Study
     *
     * @param studyid Study ID to search
     * @return List of Oindex or empty list if not records match
     */
    public List<Oindex> getMeasurementOindexListByStudy(Integer studyid);

    /**
     * Return a list of Oindex for measurement for a Study
     *
     * @param studyid Study ID to search
     * @param effectid Effectid to search
     * @return List of Oindex or empty list if not records match
     */
    public List<Oindex> getMeasurementOindexListByStudy(Integer studyid, Integer effectId);

    /**
     * Update an existing fielbook in database
     *
     * @param workbook
     */
    public void updateWorkbook(Workbook workbook);

    public void migrateWorkbook(Integer studyId);

    public ResultSet getTrialRandomization(
            Integer studyId,
            Integer trialFactorId,
            List<String> factoresPrincipales,
            List<String> factoresSalida);

    public ResultSet getTrialRandomization(
            Integer studyId,
            Integer trialFactorId,
            List<String> factoresPrincipales,
            List<String> factoresSalida,
            String trialNmame);

    public List<Measurement> getTrialRandomizationVeryFast(
            Integer studyId,
            Integer trialFactorId,
            List<String> factoresPrincipales,
            List<String> factoresSalida,
            String trialNmame);

    public ResultSet getTrialRandomizationFast(
            Integer studyId,
            Integer trialFactorId,
            List<String> factoresPrincipales,
            List<String> factoresSalida,
            String trialNmame);

    public StudySearch getListGermplasmAndPlotByStudyidAndTrial(
            StudySearch studySearch);

    public List<Listdata> saveGerplasmCimmytWheat(
            List<Listdata> listGermplsm,
            Listnms listnms,
            Integer userId);

    public Listdata agregarGermPlasmCimmytWheat(
            String nameGermplasmHistory,
            String nameGermplasmBCID,
            Listdata listdata,
            Integer userId);

    public List<GermplasmSearch> getGermplasmByListStudyTrialPlotCross(
            AppServices appServices,
            List<GermplasmSearch> listFmale,
            List<GermplasmSearch> listMale);

    //----------------------------------- Inventory Management ---------------------------   
    /**
     * Save to inventory data from a List
     *
     * @param listnms
     * @param transDate
     * @param inventoryDataList
     * @param userid
     */
    public void saveInventoryFromList(Listnms listnms, Integer transDate, List<InventoryData> inventoryDataList, Integer userid);

    /**
     * Get information data from a List
     *
     * @param listId Id for LIST
     * @return
     */
    public List<InventoryData> getInventoryDataFromList(final Integer listId);

    /**
     * Gets a list of scales used to manage inventory
     *
     * @return
     */
    public List<Scales> getScalesForInventory();

    public List<GermplasmSearch> getGermplasmByListStudyTrialPlotCross(List<GermplasmSearch> listFmale,
            List<GermplasmSearch> listMale);

    /**
     * Returns a Names object by it GID First look for NTYPE = 1028, if not
     * exists then look for NTYPE = 1027
     *
     * @param gid GID to search
     * @return Names Object if GID does not exist then return NULL
     */
    public Names getCimmytWheatName(Integer gid);

    /**
     * Deletes logically a study from database
     *
     * @param study Study to delete
     */
    public void deleteStudy(Study study);
    
     /**
     * Deletes logically a list
     * @param listnms List to delete
     */
    public void deleteListnms(Listnms listnms);
    
    /**
     * Delete logically a germplasm entry
     *
     * @param listdata Entro to delete
     */
    public void deleteListData(Listdata listdata);
    
}