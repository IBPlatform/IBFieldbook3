package org.cimmyt.cril.ibwb.provider.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Study;

import org.hibernate.criterion.DetachedCriteria;

/**
 * Data Acccess Object to manage study table
 * @author tmsanchez
 */
public class StudyDAO extends AbstractDAO<Study, Integer> {

    private static Logger log = Logger.getLogger(StudyDAO.class);

    public StudyDAO() {
        super(Study.class);
    }

    @Override
    public Study prepareToCreate(Study study) {
        if (isLocal()) {
            study.setStudyid(getNextMin());
        }
        if (isCentral()) {
            study.setStudyid(getNextMax());
        }
        return study;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Study filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
        return "studyid";
    }

    @Override
    public String getConsulta(Study filtro) {
        String query = "from Study as s";

        if (filtro.getGlobalsearch() == null) {
            setQuery("s.studyid", filtro.getStudyid());
            setQuery("s.sname", filtro.getSname());
            setQuery("s.pmkey", filtro.getPmkey());
            setQuery("s.title", filtro.getTitle());
            setQuery("s.objectiv", filtro.getObjectiv());
            setQuery("s.investid", filtro.getInvestid());
            setQuery("s.stype", filtro.getStype());
            setQuery("s.sdate", filtro.getSdate());
            setQuery("s.edate", filtro.getEdate());
            setQuery("s.userid", filtro.getUserid());

            // only active studies
            //setQuery("s.sstatus", filtro.getSstatus());            
            setQueryNotEqual("s.sstatus", Study.SSTATUS_DELETED);

            setQuery("s.shierarchy", filtro.getShierarchy());
        } else {
            global = true;
            if (ValidatingDataType.isNumeric(filtro.getGlobalsearch())) {
                setQuery("s.studyid", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryInTo("s.sname", filtro.getGlobalsearch());
                setQuery("s.pmkey", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryInTo("s.title", filtro.getGlobalsearch());
                setQueryInTo("s.objectiv", filtro.getGlobalsearch());
                setQuery("s.investid", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryInTo("s.stype", filtro.getGlobalsearch());
                setQuery("s.sdate", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("s.edate", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("s.userid", Integer.valueOf(filtro.getGlobalsearch()));
                // only active studies                
                //setQuery("s.sstatus", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryNotEqual("s.sstatus", Study.SSTATUS_DELETED);
                setQuery("s.shierarchy", Integer.valueOf(filtro.getGlobalsearch()));
            } else {
                setQueryInTo("s.sname", filtro.getGlobalsearch());
                setQueryInTo("s.title", filtro.getGlobalsearch());
                setQueryInTo("s.objectiv", filtro.getGlobalsearch());
                setQueryInTo("s.stype", filtro.getGlobalsearch());
                // only active studies                
                //setQuery("s.sstatus", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryNotEqual("s.sstatus", Study.SSTATUS_DELETED);
                
            }
        }
        return query;
    }

    /**
     * Return a studys
     *
     * @return List<Study>
     */
    public List<Study> getStudysOnlyTrial() {
        StringBuilder sbHql = new StringBuilder();
        Object[] parametros = {"T"};
        sbHql.append("FROM Study as s WHERE s.stype = ? ORDER BY s.studyid ");
        if (isLocal()) {
            sbHql.append("DESC");
        } else {
            sbHql.append("ASC");
        }
    //    log.info(sbHql.toString());
        List<Study> liststuList = getHibernateTemplate().find(sbHql.toString(), parametros);
        return liststuList;
    }

    /**
     * Deletes logically a study from database
     *
     * @param study Study to delete
     */
    public void logicalDelete(Study study) {
        study = read(study.getStudyid());
        study.setSstatus(Study.SSTATUS_DELETED);
        update(study);
        deleteStudyChildren(study.getStudyid());
    }
    
    /**
     * Deletes all children for a Study by checking which shrierarchy belongs to StudyId
     * @param studyId Study Id
     */
    private void deleteStudyChildren(final Integer studyId) {
        final String updateListdataStatus = " update Study study set study.sstatus = " 
                + Study.SSTATUS_DELETED + " where study.shierarchy= " + studyId;
        try {
            getHibernateTemplate().bulkUpdate(updateListdataStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}