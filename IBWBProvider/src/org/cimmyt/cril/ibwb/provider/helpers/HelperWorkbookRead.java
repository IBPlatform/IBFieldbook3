/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.helpers;

import ibfb.domain.core.*;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.log4j.Logger;
import org.cimmyt.cril.dmsreader.DMSReader;
import org.cimmyt.cril.ibwb.api.AppServices;
import org.cimmyt.cril.ibwb.api.CommonServices;
import org.cimmyt.cril.ibwb.domain.*;
import org.cimmyt.cril.ibwb.domain.Factor;
import org.cimmyt.cril.ibwb.domain.Study;
import org.cimmyt.cril.ibwb.domain.Variate;
import org.cimmyt.cril.ibwb.provider.datasources.DMSReaderProxy;
import org.cimmyt.cril.ibwb.provider.utils.ConverterDTOtoDomain;
import org.cimmyt.cril.ibwb.provider.utils.DecimalUtils;
import org.openide.util.Exceptions;

/**
 * Helper class to fill Workbook object from database
 * @author gamaliel
 * @author tmsanchez
 */
public class HelperWorkbookRead {

    private static Logger log = Logger.getLogger(HelperWorkbookRead.class);
    private Integer effectid;
    private static final String DATA_TYPE_CHAR = "C";
    private static final String DATA_TYPE_NUMERIC = "N";
    private Workbook workbookStudy = new Workbook();
    private Study study;
    public List<Factor> factors;
    /**
     * Reference to local services
     */
    private CommonServices localServices;
    /**
     * Reference to central services
     */
    private CommonServices centralServices;
    /**
     * Proxy to application services to perform operation in local and central
     */
    private AppServices servicioApp;
//    boolean llamadaTrait;
    //Definidiendo mapa de labels con la igualacion del factorid y labelid
    Map<Integer, Factor> mapaLabes = new HashMap<Integer, Factor>();
    private List<Factor> factorsDto;
    private List<Factor> factorsDtoStudy = new ArrayList<Factor>();
    private Map<String, Factor> mapFactorsDtoStudy = new HashMap<String, Factor>();
    private Integer factorIdStudy;
    private List<Factor> factorsDtoTrial = new ArrayList<Factor>();
    private Map<String, Factor> mapFactorsDtoTrial = new HashMap<String, Factor>();
    private Integer factorIdTrial;
    private List<Factor> factorsDtoEntrys = new ArrayList<Factor>();
    private Map<String, Factor> mapFactorsDtoEntrys = new HashMap<String, Factor>();
    private Integer factorIdEntry;
    private List<Factor> factorsDtoPlot = new ArrayList<Factor>();
    private Map<String, Factor> mapFactorsDtoPlot = new HashMap<String, Factor>();
    private Integer factorIdPlot;
    private List<Factor> factorsDtoOthers = new ArrayList<Factor>();
    private Map<String, Factor> mapFactorsDtoOthers = new HashMap<String, Factor>();
    private List<Factor> factorsDtoAllFactorsView = new ArrayList<Factor>();
    private Map<String, Factor> mapFactorsDtoAllFactorsView = new HashMap<String, Factor>();
    private List<Variate> variatesDTO = new ArrayList<Variate>();
    private List<Variate> variatesDtoConstants = new ArrayList<Variate>();
    private List<Variate> variatesDtoVariatesView = new ArrayList<Variate>();
    private Map<String, Variate> mapVariatesDtoVariatesView = new HashMap<String, Variate>();
    private ibfb.domain.core.Study studyView;
    private List<ibfb.domain.core.Condition> conditions = new ArrayList<Condition>();
    private List<ibfb.domain.core.Condition> studyConditions = new ArrayList<Condition>();
    private List<ibfb.domain.core.Factor> factorsView = new ArrayList<ibfb.domain.core.Factor>();
    private List<ibfb.domain.core.Constant> constants = new ArrayList<ibfb.domain.core.Constant>();
    private List<ibfb.domain.core.Variate> variates = new ArrayList<ibfb.domain.core.Variate>();
    private List<List<Object>> germplasmData = new ArrayList<List<Object>>();
    public Factor factorStudy = new Factor();
    public Factor factorTrial = new Factor();
    public Factor factorEntry = new Factor();
    public Factor factorPlot = new Factor();

    public HelperWorkbookRead(
            CommonServices commonServicesLocal,
            CommonServices commonServicesCentral,
            AppServices appServices //            ,
            //            boolean llamadaTrait
            ) {
        this.localServices = commonServicesLocal;
        this.centralServices = commonServicesCentral;
        this.servicioApp = appServices;
//        this.llamadaTrait = llamadaTrait;
    }

    //Obtencion de la base de datos en objetos study dto
    public Workbook readStudy(Integer studyId) {
        log.info("Read study full: " + studyId);
        Study studyDto = this.servicioApp.getStudy(studyId);
        this.study = studyDto;
        //Validando si existe el study
        if (studyDto == null) {
            return null;
        } else if (studyDto.getStudyid() == null) {
            return null;
        } else {
            log.info("Read list all factors.");
            readFactors();
            readVariates();
            transformandoObjectsToView();
            transformVariatesToView();
            workbookStudy.getValuesForGroupingLabels();

            setToStructure();
            //tmsanchez            
            arrangeFactors();

            log.info("Filling Study Conditions Data....");
            fillStudyConditionsData();
            log.info("Filling Study Conditions DONE!!!!");

            log.info("Filling Trial Conditions Data....");
            fillTrialConditionsData();
            log.info("Filling Trial Conditions DONE!!!!....");
//            fillTrialConditionsData2();
            log.info("Filling Trial Germplasm Data....");
            fillGermplasmData();
            log.info("Filling Trial Germplasm DONE!!!");
            log.info("Filling Observations Data....");
//            fillObservationsData();
            fillObservationsDataFast();
            log.info("Filling Observations Data DONE!!!");
        }
        workbookStudy.getValuesForGroupingLabels();
        return workbookStudy;
    }

    //Obtencion de la base de datos en objetos study dto
    public Study readStudyFactorsTrialAndEntry(Integer studyId) {
        Study studyDto = this.servicioApp.getStudy(studyId);
        this.study = studyDto;
        //Validando si existe el study
        if (studyDto == null) {
            return null;
        } else if (studyDto.getStudyid() == null) {
            return null;
        } else {
            readFactors();
        }
        study.setFactors(factorsDto);
        return study;
    }

    /**
     * Read information from Factors
     */
    public void readFactors() {
        //Obtencion de la base todos los factores dto para este study
//        Factor factorFilter = new Factor(true);
//        factorFilter.setStudyid(this.study.getStudyid());
//        this.factorsDto = this.servicioApp.getListFactor(factorFilter, 0, 0, false);



        HelperContentEffectidAndFactors hceaf = HelperEffect.getEffectidForMeasurementEffectAndFactors(
                this.servicioApp,
                this.study.getStudyid(),
                this.factorStudy,
                this.factorTrial,
                this.factorEntry,
                this.factorPlot);
        this.effectid = hceaf.getEffectid();
        this.factorsDto = HelperFactor.getFactorsByEffectid(this.effectid, this.servicioApp);

        this.factorStudy = hceaf.getFactorStudy();
        this.factorTrial = hceaf.getFactorTrial();
        this.factorEntry = hceaf.getFactorEntry();
        this.factorPlot = hceaf.getFactorPlot();

        this.factorIdStudy = this.factorStudy.getLabelid();
        this.factorIdTrial = this.factorTrial.getLabelid();
        this.factorIdEntry = this.factorEntry.getLabelid();
        this.factorIdPlot = this.factorPlot.getLabelid();

        this.workbookStudy.setTrialLabel(this.factorTrial.getFname());
        this.workbookStudy.setEntryLabel(this.factorEntry.getFname());
        this.workbookStudy.setPlotLabel(this.factorPlot.getFname());
        //Obteniendo de la base todos los dto relacionados con el factor
//        List<Factor> factorsRemove = new ArrayList<Factor>();
        for (Factor factorDto : this.factorsDto) {
            if (factorDto.getLabelid().equals(factorDto.getFactorid())) {
                this.mapaLabes.put(factorDto.getLabelid(), factorDto);
            }
            Factor temp = HelperFactor.getFactorFillingFull(
                    factorDto,
                    this.servicioApp,
                    801);

            if (factorDto.getFactorid().equals(this.factorIdStudy)) {
                this.mapFactorsDtoStudy.put(factorDto.getFname(), factorDto);
                this.factorsDtoStudy.add(factorDto);
            } else if (factorDto.getFactorid().equals(this.factorIdTrial)) {
                this.mapFactorsDtoTrial.put(factorDto.getFname(), factorDto);
                this.factorsDtoTrial.add(factorDto);
            } else if (factorDto.getFactorid().equals(this.factorIdEntry)) {
                this.mapFactorsDtoEntrys.put(factorDto.getFname(), factorDto);
                this.factorsDtoEntrys.add(factorDto);
                this.mapFactorsDtoAllFactorsView.put(factorDto.getFname(), factorDto);
                this.factorsDtoAllFactorsView.add(factorDto);
            } else if (factorDto.getFactorid().equals(this.factorIdPlot)) {
                this.mapFactorsDtoPlot.put(factorDto.getFname(), factorDto);
                this.factorsDtoPlot.add(factorDto);
                this.mapFactorsDtoAllFactorsView.put(factorDto.getFname(), factorDto);
                this.factorsDtoAllFactorsView.add(factorDto);
            } else {
                this.mapFactorsDtoOthers.put(factorDto.getFname(), factorDto);
                this.factorsDtoOthers.add(factorDto);
                this.mapFactorsDtoAllFactorsView.put(factorDto.getFname(), factorDto);
                this.factorsDtoAllFactorsView.add(factorDto);
            }
        }
    }

    /**
     * Read information from variates
     */
    public void readVariates() {
        // get all variate from database marked as Traits
        this.variatesDTO = this.servicioApp.getListVariateTraits(study.getStudyid());
//        this.variatesDTO = this.servicioApp.getListVariateTraitsByEffectid(study.getStudyid(), effectid);
        log.info("fill all variate as traits");
        for (Variate variateDTO : this.variatesDTO) {
            variateDTO = HelperVariate.getVariateFillingFull(
                    variateDTO,
                    this.localServices,
                    this.centralServices,
                    this.servicioApp,
                    802);
        }

        // get all variate from database marked as Traits
        this.variatesDtoConstants = this.servicioApp.getListVariateConstants(this.study.getStudyid());

        // fill all variate as traits
        for (Variate variateDTO : this.variatesDtoConstants) {
            variateDTO = HelperVariate.getVariateConstantsFillingFull(
                    variateDTO,
                    //                    this.localServices,
                    //                    this.centralServices,
                    this.servicioApp,
                    802);
        }
    }

    /**
     * Transform objects from database to objects used by UI
     */
    public void transformandoObjectsToView() {
        //Transformacion a objetos de interfaz
        this.studyView = ConverterDTOtoDomain.getStudy(this.study);

        for (Factor factorStudy : this.factorsDtoStudy) {
            this.studyConditions.add(
                    ConverterDTOtoDomain.getCondition(
                    factorStudy,
                    this.mapaLabes));
        }
        for (Factor factorTrial : this.factorsDtoTrial) {
            this.conditions.add(
                    ConverterDTOtoDomain.getCondition(
                    factorTrial,
                    this.mapaLabes));
        }
        for (Factor factorDtoView : this.factorsDtoAllFactorsView) {
            this.factorsView.add(
                    ConverterDTOtoDomain.getFactor(
                    factorDtoView,
                    this.mapaLabes));
        }
    }

    public void setToStructure() {
        //Rellenando Workbook con todos los parametros optenidos
        workbookStudy.setStudy(studyView);
        workbookStudy.setConditions(conditions);
        workbookStudy.setStudyConditions(studyConditions);
        workbookStudy.setFactors(factorsView);
        workbookStudy.setConstants(constants);
        workbookStudy.setVariates(variates);
        workbookStudy.setGermplasmData(germplasmData);
    }

    private void fillStudyConditionsData() {
        // for each factor retrieve vales form data
        for (Condition condition : workbookStudy.getStudyConditions()) {
            if (condition.getDataType().equals(DATA_TYPE_CHAR)) {
                LevelC levelCFilter = new LevelC(false);
                levelCFilter.setFactorid(Integer.SIZE);
            }
        }
    }

    private void fillTrialConditionsData() {
        // for each factor retrieve vales form data
        List<Condition> trialConditions = new ArrayList<Condition>();

        int instanceCounter = 1;
        for (Condition condition : this.workbookStudy.getConditions()) {
            if (condition.getDataType().equals(DATA_TYPE_CHAR)) {
                LevelC levelCFilter = new LevelC(true);
                LevelCPK levelCPK = new LevelCPK();
                levelCPK.setLabelid(condition.getLabelId());
                levelCFilter.setFactorid(condition.getFactorId());
                levelCFilter.setLevelCPK(levelCPK);
                List<LevelC> levelCList = this.servicioApp.getListLevelC(levelCFilter, 0, 0, false);
                instanceCounter = 1;
                for (LevelC levelC : levelCList) {
                    try {
                        Condition conditionToAdd = new Condition();
                        BeanUtilsBean.getInstance().copyProperties(conditionToAdd, condition);
                        conditionToAdd.setValue(levelC.getLvalue());
                        conditionToAdd.setInstance(instanceCounter);
                        conditionToAdd.setLevelNo(levelC.getLevelCPK().getLevelno());
                        trialConditions.add(conditionToAdd);
                        instanceCounter++;
                    } catch (IllegalAccessException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (InvocationTargetException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            } else if (condition.getDataType().equals(DATA_TYPE_NUMERIC)) {
                LevelN levelNFilter = new LevelN(true);
                LevelNPK levelnPK = new LevelNPK();
                levelnPK.setLabelid(condition.getLabelId());
                levelNFilter.setFactorid(condition.getFactorId());
                levelNFilter.setLevelNPK(levelnPK);
                List<LevelN> levelNList = this.servicioApp.getListLevelN(levelNFilter, 0, 0, false);
                instanceCounter = 1;
                for (LevelN levelN : levelNList) {
                    try {
                        Condition conditionToAdd = new Condition();
                        BeanUtilsBean.getInstance().copyProperties(conditionToAdd, condition);
                        conditionToAdd.setValue(DecimalUtils.getValueAsString(levelN.getLvalue()));
                        conditionToAdd.setInstance(instanceCounter);
                        conditionToAdd.setLevelNo(levelN.getLevelNPK().getLevelno());
                        trialConditions.add(conditionToAdd);
                        instanceCounter++;
                    } catch (IllegalAccessException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (InvocationTargetException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            }
        }

        Comparator<Condition> conditionComparator = new Comparator<Condition>() {

            @Override
            public int compare(Condition o1, Condition o2) {
                return o1.getInstance().compareTo(o2.getInstance());
            }
        };

        Collections.sort(trialConditions, conditionComparator);

        this.workbookStudy.setConditionsData(trialConditions);
    }

    private void fillTrialConditionsData2() {
        // for each factor retrieve vales form data
//        List<Condition> trialConditions = new ArrayList<Condition>();
//
//        for (int i = 0; i < factorsDtoTrial.get(0).getSizeLevels(); i++) {
//            for (Factor factors : factorsDtoTrial) {
//                trialConditions.add(ConverterDTOtoDomain.getCondition(factors, mapaLabes, i, i + 1));
//            }
//        }
//        workbookStudy.setConditionsData(trialConditions);

        List<Condition> trialConditions = new ArrayList<Condition>();

        Comparator<Condition> conditionComparator = new Comparator<Condition>() {

            @Override
            public int compare(Condition o1, Condition o2) {
                return o1.getInstance().compareTo(o2.getInstance());
            }
        };

        Collections.sort(this.conditions, conditionComparator);

        this.workbookStudy.setConditionsData(this.conditions);

    }

    private void fillGermplasmData() {
        int totalFilas = factorsDtoEntrys.get(0).getSizeLevels();
        int factorFila = factorsDtoEntrys.get(0).getLevelNo(0);
        if(factorFila < 0){
            factorFila = factorFila * -1;
        }
        
        for (int i = 0; i < totalFilas; i++) {
            List<Object> row = new ArrayList<Object>();
            for(int j=0 ; j < factorsDtoEntrys.size() ; j++){
                row.add("");
            }
            germplasmData.add(row);
        }
        for(Factor factor : factorsDtoEntrys){
            int columna = factorsDtoEntrys.indexOf(factor);
            if(factor.getLtype().equals("N")){
                for(LevelN levelN : factor.getLevelsN()){
                    Double value = (Double) levelN.getLvalue();
                    if (DecimalUtils.isIntegerValue(value)) {
                        germplasmData.get(levelN.getLevelNPK().getLevelnoAbs() - factorFila).set(columna, DecimalUtils.getValueAsInteger(value));
                    }else{
                        germplasmData.get(levelN.getLevelNPK().getLevelnoAbs() - factorFila).set(columna, DecimalUtils.getValueAsString(value));
                    }
                }
            }else{
                for(LevelC levelC : factor.getLevelsC()){
                    germplasmData.get(levelC.getLevelCPK().getLevelnoAbs() - factorFila).set(columna, levelC.getLvalue());
                }
            }
        }
        
        System.out.print("");
    }

    /**
     * Arrange factors according to template
     */
    private void arrangeFactors() {
        List<Condition> tempTrialConditions = new ArrayList<Condition>();
        List<Condition> tempStudyConditions = new ArrayList<Condition>();
        List<ibfb.domain.core.Factor> tempFactors = new ArrayList<ibfb.domain.core.Factor>();

        // conditions
        for (Condition cond : workbookStudy.getConditions()) {
            if (hasLabel(cond.getLabel(), Workbook.STUDY)) {
                cond.setLabel(Workbook.STUDY);
                tempStudyConditions.add(cond);
            }
        }
        for (Condition cond : workbookStudy.getStudyConditions()) {
            if (hasLabel(cond.getLabel(), Workbook.STUDY)) {
                cond.setLabel(Workbook.STUDY);
                tempStudyConditions.add(cond);
            }
        }

        // study conditions
        for (Condition cond : workbookStudy.getConditions()) {
            //if (hasLabel(cond.getLabel(), Workbook.TRIAL_LABEL)) {
            if (hasLabel(cond.getLabel(), workbookStudy.getTrialLabel())) {

                cond.setLabel(workbookStudy.getTrialLabel());
                tempTrialConditions.add(cond);
            }
        }
        for (Condition cond : workbookStudy.getStudyConditions()) {
            // if (hasLabel(cond.getLabel(), Workbook.TRIAL_LABEL)) {
            if (hasLabel(cond.getLabel(), workbookStudy.getTrialLabel())) {
                cond.setLabel(workbookStudy.getTrialLabel());
                tempTrialConditions.add(cond);
            }
        }

        // factors (ENTRY)
        for (Condition cond : workbookStudy.getStudyConditions()) {
            //if (hasLabel(cond.getLabel(), Workbook.TRIAL_LABEL)) {
            if (hasLabel(cond.getLabel(), workbookStudy.getTrialLabel())) {
                //tempFactors.add(getFactor(cond, Workbook.ENTRY_LABEL));
                tempFactors.add(getFactor(cond, workbookStudy.getTrialLabel()));
            }
        }

        for (Condition cond : workbookStudy.getConditions()) {
            //if (hasLabel(cond.getLabel(), Workbook.ENTRY_LABEL)) {
            if (hasLabel(cond.getLabel(), workbookStudy.getEntryLabel())) {
                //tempFactors.add(getFactor(cond, Workbook.ENTRY_LABEL));
                tempFactors.add(getFactor(cond, workbookStudy.getEntryLabel()));
            }
        }
        for (ibfb.domain.core.Factor factor : workbookStudy.getFactors()) {
            //if (hasLabel(factor.getLabel(), Workbook.ENTRY_LABEL)) {
            if (hasLabel(factor.getLabel(), workbookStudy.getEntryLabel())) {
                tempFactors.add(factor);
            }
        }

        // Factors (plot)
        for (Condition cond : workbookStudy.getStudyConditions()) {
            //if (hasLabel(cond.getLabel(), Workbook.PLOT_LABEL)) {
            if (hasLabel(cond.getLabel(), workbookStudy.getPlotLabel())) {
                //tempFactors.add(getFactor(cond, Workbook.PLOT_LABEL));
                tempFactors.add(getFactor(cond, workbookStudy.getPlotLabel()));
            }
        }
        for (Condition cond : workbookStudy.getConditions()) {
            //if (hasLabel(cond.getLabel(), Workbook.PLOT_LABEL)) {
            if (hasLabel(cond.getLabel(), workbookStudy.getPlotLabel())) {
                //tempFactors.add(getFactor(cond, Workbook.PLOT_LABEL));
                tempFactors.add(getFactor(cond, workbookStudy.getPlotLabel()));
            }
        }

        for (ibfb.domain.core.Factor factor : workbookStudy.getFactors()) {
            //if (hasLabel(factor.getLabel(), Workbook.PLOT_LABEL)) {
            if (hasLabel(factor.getLabel(), workbookStudy.getPlotLabel())) {
                tempFactors.add(factor);
            }
        }


        workbookStudy.setStudyConditions(tempStudyConditions);
        workbookStudy.setConditions(tempTrialConditions);
        workbookStudy.setFactors(tempFactors);
    }

    /**
     * Checks if label text matches with desired label
     * @param labelText label text from factor
     * @param label label text fro template
     * @return true if matches, false if not
     */
    private boolean hasLabel(String labelText, String label) {
        boolean result = false;
        if (labelText == null) {
            result = false;
        } else {
            if (labelText.trim().toUpperCase().equals(label)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Converts a Condition object to a ibfb.domain.core.Factor
     * @param condition
     * @param textLabel
     * @return 
     */
    private ibfb.domain.core.Factor getFactor(Condition condition, String textLabel) {
        ibfb.domain.core.Factor factor = new ibfb.domain.core.Factor();
        factor.setFactorId(condition.getFactorId());
        factor.setLabelId(condition.getLabelId());
        factor.setFactorName(condition.getConditionName());
        factor.setProperty(condition.getProperty());
        factor.setScale(condition.getScale());
        factor.setDescription(condition.getDescription());
        factor.setDataType(condition.getDataType());
        factor.setLabel(textLabel);
        return factor;
    }

    /**
     * Transform Variates from database to Constants or Variates from View
     */
    private void transformVariatesToView() {

        // transform Variate list to Constants
        List<Constant> constantsData = new ArrayList<Constant>();
        Integer numeroDeInstancias = factorsDtoTrial.get(0).getSizeLevels();
        for (Variate variate : variatesDtoConstants) {
            for (int i = 0; i < numeroDeInstancias; i++) {
                ibfb.domain.core.Constant constant = ConverterDTOtoDomain.getConstant(variate);
                // TODO ajustar estructura para que se recupere los datos tipo numero sin el .0
//                if(variate.getDtype().equals("N")){
//                    constant.setValue(DecimalUtils.getValueAsString((Double)variate.getDataObject(i)));
//                }else{
//                    constant.setValue(variate.getDataObject(i));
                    constant.setValue(DecimalUtils.getValueAsString(variate.getDataObject(i)));
//                }
                constant.setInstance(i + 1);
//                constant.setOunitid(variate.getOunitidObject(i));
                constant.setVariateId(variate.getVariatid());
                constant.setStudyId(variate.getStudyid());
                constants.add(constant);
            }
        }

        Comparator<Constant> constantComparator = new Comparator<Constant>() {

            @Override
            public int compare(Constant o1, Constant o2) {
                return o1.getInstance().compareTo(o2.getInstance());
            }
        };

        Collections.sort(constants, constantComparator);

        // transform Variate list to Variate(from view)
        for (Variate variate : variatesDTO) {
            ibfb.domain.core.Variate variateToAdd = ConverterDTOtoDomain.getVariate(variate);
            variates.add(variateToAdd);
        }


        workbookStudy.setConstants(constants);
        workbookStudy.setConstantsData(constants);

        workbookStudy.setVariates(variates);
        workbookStudy.setVariatesData(variates);
    }

    /**
     * Fill all information for observation data
     */
    private void fillObservationsData() {

        // retrieve all oindex records
//        List<Oindex> oindexList = this.servicioApp.getMeasurementOindexListByStudy(workbookStudy.getStudy().getStudyid(), effectid);

        Integer studyId = this.workbookStudy.getStudy().getStudyid();

        int variateCount = this.workbookStudy.getVariates().size();

        List<Measurement> measurementList = new ArrayList<Measurement>();
        //todo quitar no se usa
//        int observationsCount = this.servicioApp.getObservationsCount(studyId);
        log.info("Getting Data Trial ...");
        List<Object> dataList = this.servicioApp.getObservationsDataMeasurementEffect(studyId, effectid);
        log.info("Getting Data Trial Done...");
        
        log.info("Getting List of Obsunit ...");
        List<Obsunit> obsunits = this.servicioApp.getObsunitListByEffectid(studyId, effectid);
        log.info("Getting List of Obsunit...");
        for (Obsunit obsUnit : obsunits){
            Measurement measurement = new Measurement();

            measurement.initMeasurementData(variateCount);

            assignMeasurementData(measurement, obsUnit, dataList);

            measurementList.add(measurement);
        }


        workbookStudy.setMeasurements(measurementList);

        List<String> factorsReturn = getFactoresReturnList();
        log.info("Getting Trial Randomization ...");
        ResultSet rst = servicioApp.getTrialRandomization(studyId, 0, getFactoresKeyList(), factorsReturn, factorTrial.getFname());
        log.info("Getting Trial Randomization done");
        fillFactorLabelData(rst, factorsReturn);
    }

    /**
     * Fill all information for observation data
     */
    private void fillObservationsDataFast() {

        Integer studyId = this.workbookStudy.getStudy().getStudyid();
//        int variateCount = this.workbookStudy.getVariates().size();
        
        List<String> factorsReturn = getFactoresReturnList();
        
        fillFactorLabelDataOptimized(studyId, 0, getFactoresKeyList(), factorsReturn, factorTrial.getFname());
        
//        List<Measurement> measurementList = workbookStudy.getMeasurements();
        
//        log.info("Getting Data Trial ...");
//        List<Object> dataList;
//        if(! workbookStudy.getVariates().isEmpty()){
//            dataList = this.servicioApp.getObservationsDataMeasurementEffect(studyId, effectid);
//        }else{
//            dataList = new ArrayList<Object>();
//        }
//        log.info("Getting Data Trial Done...");
//        
//        
//        log.info("Getting List of Obsunit ...");
//        List<Obsunit> obsunits = this.servicioApp.getObsunitListByEffectid(studyId, effectid);
//        log.info("Getting List of Obsunit...");
//        int rowIndex = 0;
//        for (Obsunit obsUnit : obsunits) {
//            Measurement measurement = measurementList.get(rowIndex);
//            measurement.initMeasurementData(variateCount);
//
//            assignMeasurementData(measurement, obsUnit, dataList);
//            rowIndex++;
//        }
    }

    /**
     * Assign values for each COLUMN (TRAIT) in observations table.
     * It check if observation unit id is in dataList and if found then
     * assigns that value to trait column index according to index on traits
     * @param measurement measurement row to fill
     * @param obsUnit observation unit to identify each row in observations
     * @param dataList datalist with all records of DATA_N and DATA_C
     */
    private void assignMeasurementData(Measurement measurement, Obsunit obsUnit, List<Object> dataList) {
        for (Object data : dataList) {
            if (data instanceof DataN) {
                DataN dataN = (DataN) data;
                if (obsUnit.getOunitid().equals(dataN.getDataNPK().getOunitid())) {
                    int columnIndex = workbookStudy.getVariateColumnIndex(dataN.getDataNPK().getVariatid());
                    if (columnIndex != -1) {
                        MeasurementData measurementData = new MeasurementData();
                        measurementData.setData(dataN);
                        measurement.setMeasurementData(columnIndex, measurementData);
                    }
                }
            } else if (data instanceof DataC) {
                DataC dataC = (DataC) data;
                if (obsUnit.getOunitid().equals(dataC.getDataCPK().getOunitid())) {
                    int columnIndex = workbookStudy.getVariateColumnIndex(dataC.getDataCPK().getVariatid());
                    if (columnIndex != -1) {
                        MeasurementData measurementData = new MeasurementData();
                        measurementData.setData(dataC);
                        measurement.setMeasurementData(columnIndex, measurementData);
                    }
                }
            }
        }
    }

    private List<String> getFactoresKeyList() {
        List<String> factorsKey = new ArrayList<String>();
        factorsKey.add(factorStudy.getFname());
        factorsKey.add(factorTrial.getFname());
        factorsKey.add(factorEntry.getFname());
        factorsKey.add(factorPlot.getFname());
        return factorsKey;
    }

    private List<String> getFactoresReturnList() {
        List<String> factorsReturn = new ArrayList<String>();

        if (!study.getStype().equals(Study.STYPE_NURSERY)) {
            factorsReturn.add(workbookStudy.getTrialLabel());
        }

        for (ibfb.domain.core.Factor factor : workbookStudy.getEntryFactors()) {
            factorsReturn.add(factor.getFactorName());
        }

        for (ibfb.domain.core.Factor factor : workbookStudy.getPlotFactors()) {
            factorsReturn.add(factor.getFactorName());
        }
        return factorsReturn;
    }

    private void fillFactorLabelData(ResultSet rst, List<String> factorsReturn) {
        log.info("filling FactorLabelData... ");
        System.gc();
//        Runtime.getRuntime().gc();
        int measurementRow = 0;
        try {
            if (rst != null) {
//                ResultSetMetaData rsmd = rst.getMetaData();
//                for (int col = 1; col <= rsmd.getColumnCount(); col++) {
//                    System.out.print(rsmd.getColumnName(col) + "\t");
//                }
//                System.out.println();

                while (rst.next()) {
                    List<Object> factorLabelList = new ArrayList<Object>();
//                    for (int col = 1; col <= rsmd.getColumnCount(); col++) {
                    for (String nameColumn : factorsReturn) {
//                        factorLabelList.add(rst.getString(col));
                        factorLabelList.add(rst.getString(nameColumn));
                        //System.out.print(rst.getString(col) + "\t");
                    }
                    workbookStudy.getMeasurements().get(measurementRow).setFactorLabelData(factorLabelList);
                    measurementRow++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("filling FactorLabelData DONE... ");
    }
    
    private void fillFactorLabelDataOptimized(Integer studyId, Integer trialId, List<String> factoresPrincipales, List<String> factoresSalida, String trialName) {
        log.info("filling FactorLabelData... ");
        workbookStudy.setMeasurements(this.servicioApp.getTrialRandomizationVeryFast(studyId, trialId, factoresPrincipales, factoresSalida, trialName));
        log.info("filling FactorLabelData DONE... ");
    }
}