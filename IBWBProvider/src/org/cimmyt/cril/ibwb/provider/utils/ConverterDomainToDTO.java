package org.cimmyt.cril.ibwb.provider.utils;

import org.cimmyt.cril.ibwb.domain.Variate;
import org.cimmyt.cril.ibwb.domain.Dmsattr;
import org.cimmyt.cril.ibwb.domain.Factor;
import org.cimmyt.cril.ibwb.domain.Measuredin;
import org.cimmyt.cril.ibwb.domain.Scales;
import org.cimmyt.cril.ibwb.domain.Study;
import org.cimmyt.cril.ibwb.domain.Tmethod;
import org.cimmyt.cril.ibwb.domain.TmsMethod;
import org.cimmyt.cril.ibwb.domain.Traits;

/**
 * Utility class to convert from Domain object to Hibernate object
 * @author GCAMARENA
 */
public class ConverterDomainToDTO {

    /**
     * Convert a Study from domain to a hibernate Study object
     * @param study Study to convert
     * @return Study object
     */
    public static Study getStudy(ibfb.domain.core.Study study) {
        Study studyDto = new Study();

        studyDto.setStudyid(study.getStudyid());
        if (study.getStudy().length() > 50) {
            studyDto.setSname(study.getStudy().substring(0, 50));
        } else {
            studyDto.setSname(study.getStudy());
        }
        studyDto.setTitle(study.getTitle());
        //Conflicto tipos de datos
        //studyDto.setPmkey(Integer.valueOf(study.getPmkey()));
        studyDto.setObjectiv(study.getObjective());
        // studyDto.setInvestid(study.getInvestid());
        studyDto.setSdate(ConverterDate.getInteger(study.getStarDate()));
        studyDto.setEdate(ConverterDate.getInteger(study.getEndDate()));
        studyDto.setStype(study.getStudyType());
        // studyDto.setUserid(study.getUserid());
        // studyDto.setSstatus(study.getSstatus());
         studyDto.setShierarchy(study.getShierarchy());
        return studyDto;
    }

    /**
     * Converts a Factor from template to a hibernate Factor object
     * @param name The factor name
     * @param dataType Data type N=Numeric or C=Character
     * @param study the study to which corresponds the factor 
     * @param traits Trait used in this factor
     * @param tmethod Method used by this factor
     * @return Factor hibernate object
     */
    public static Factor getFactor(
            String name,
            String dataType,
            Study study,
            Traits traits,
            TmsMethod tmsMethod) {
        Factor factorDTO = new Factor();

        //Conseguir dmsattr
//        factorDTO.setLabelid(null);
        //Generadp al guardar
//        factorDTO.setFactorid(null);
        //Conseguir study
        factorDTO.setStudyid(study.getStudyid());
        //Directo de condition ConditionName
        factorDTO.setFname(name);
        //Conseguir traits con measuredin y scaleid
        
        //use traitid instead of tid
        factorDTO.setTid(traits.getTid());
        factorDTO.setTraitid(traits.getTraitid());
        
        //Conseguir traits con measuredin y scaleid
        factorDTO.setScaleid(traits.getMeasuredin().getScaleid());
        //Conseguir tmethodid
        factorDTO.setTmethid(tmsMethod.getTmethid());
        //Directo de condition DataType
        factorDTO.setLtype(dataType);

        return factorDTO;
    }

    public static Variate getVariate(
            String name,
            String dataType,
            Study study,
            Traits traits,
            TmsMethod tmsMethod) {
        Variate variateDTO = new Variate();

        //Conseguir dmsattr
//        variateDTO.setLabelid(null);
        //Generadp al guardar
//        variateDTO.setFactorid(null);
        //Conseguir study
        variateDTO.setStudyid(study.getStudyid());
        //Directo de condition ConditionName
        variateDTO.setVname(name);
        //Conseguir traits con measuredin y scaleid
        
        // use traitid instead of tid
        variateDTO.setTid(traits.getTid());
        variateDTO.setTraitid(traits.getTraitid());
        
        //Conseguir traits con measuredin y scaleid
        variateDTO.setScaleid(traits.getMeasuredin().getScaleid());
        //Conseguir tmethodid
        variateDTO.setTmethid(tmsMethod.getTmethid());
        //Directo de condition DataType
        //variateDTO.setVtype(dataType);
        variateDTO.setDtype(dataType);

        return variateDTO;
    }

    public static Traits getTraits(
            String traitName
            ) {
        Traits traits = new Traits();
        //TODO quitar del mapeo de traits
        //Error de mapeo no deveria estar
        //traits.setTid();
        //Autogenerado al aguardar
//        traits.setTraitid();
        //
            traits.setTrname(traitName);
        //Por default
//        traits.setTrabbr();
        //Por default
//        traits.setTrdesc();
        //
//        traits.setTmethid(tmethod.getTmethid());
        //Por default
//        traits.setTnstat();
        //Por default
//        traits.setTraitGroup();
        //Por default
//        traits.setOntology();
        //Tipo de trait dependiendo de donde provenga
        //Condition, Study conditions, factor, variate, constant
        //Pasado como parametro
//        traits.setTraittype(String.valueOf(traitType));

        return traits;
    }

    public static Scales getScales(
            String scaleName,
            char scaleType) {
        Scales scales = new Scales();

        //Autogenerado al guardar
//        scales.setScaleid();
        //Recibido como parametro
        scales.setScname(scaleName);
        //Por default
        scales.setSctype(String.valueOf(scaleType));
        //Por default
//        scales.setOntology();

        return scales;
    }

    public static Measuredin getMeasuredin(
            Traits traits,
            Scales scales,
            Integer standardScale,
            TmsMethod tmsMethod
            ) {
        Measuredin measuredin = new Measuredin();
        //Asignado de la relacion de donde proviene traits
        measuredin.setTraitid(traits.getTid());
        //Asignado de la relacion de donde proviene scalse
        measuredin.setScaleid(scales.getScaleid());
        //Pasada como parametro al measuredin para el standard
        measuredin.setStandardscale(standardScale.toString());
        measuredin.setTmethid(tmsMethod.getTmethid());
        //Por default
//        measuredin.setReport();
        //Por default
//        measuredin.setFormula();

        //Seteando Transient
        measuredin.setScales(scales);

        return measuredin;
    }

    public static TmsMethod getTmsMethod(
            String tmethodName) {
        TmsMethod tmsMethod = new TmsMethod();

        //Autogenerado al guardar
//        tmethod.setTmethid();
        //Asignado del parametro pasado
        tmsMethod.setTmname(tmethodName);
        //Por default
        tmsMethod.setTmabbr("-");
        //Por default
        tmsMethod.setTmdesc("-");

        return tmsMethod;
    }

    public static Dmsattr getDmsattr(
            Integer dmsatype,
            String dmsatab,
            Integer idExternal,
            String description) {
        Dmsattr dmsattr = new Dmsattr();
        //Autogenerado al guardar
        dmsattr.setDmsatid(null);
        //Asignado del parametro de tipo 801 si es 
        //Factor u 802 si es Variate
        dmsattr.setDmsatype(dmsatype);
        //Asignado del parametro FACTOR
        //o VARIATE segun corresponda
        dmsattr.setDmsatab(dmsatab);
        //Asignado del id de factor (labelid )
        //o del id de variate (variateid)
        dmsattr.setDmsatrec(idExternal);
        if (description == null) {
            dmsattr.setDmsatval("-");
        } else if (description.isEmpty()) {
            dmsattr.setDmsatval("-");
        } else {
            dmsattr.setDmsatval(description);
        }

        return dmsattr;
    }
}
