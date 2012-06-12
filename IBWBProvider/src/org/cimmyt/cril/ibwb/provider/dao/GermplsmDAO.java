package org.cimmyt.cril.ibwb.provider.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Germplsm;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class GermplsmDAO extends AbstractDAO<Germplsm, Integer> {

    public GermplsmDAO() {
        super(Germplsm.class);
    }

    @Override
    public Germplsm prepareToCreate(Germplsm germplsm) {
        if (isLocal()) {
            germplsm.setGid(getNextMin());
            germplsm.setLgid(germplsm.getGid());
        }
        if (isCentral()) {
            germplsm.setGid(getNextMax());
        }
        return germplsm;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Germplsm filter) {
            // TODO Auto-generated method stub

    }

    @Override
    public String getKeyProperty() {
        return "gid";
    }

    @Override
    public String getConsulta(Germplsm filtro) {
    	String query = "from Germplsm as g";
        
        if(filtro.getGlobalsearch() == null){
            setQuery("g.gid", filtro.getGid());
            setQuery("g.methn", filtro.getMethn());
            setQuery("g.gnpgs", filtro.getGnpgs());
            setQuery("g.gpid1", filtro.getGpid1());
            setQuery("g.gpid2", filtro.getGpid2());
            setQuery("g.germuid", filtro.getGermuid());
            setQuery("g.lgid", filtro.getLgid());
            setQuery("g.glocn", filtro.getGlocn());
            setQuery("g.gdate", filtro.getGdate());
            setQuery("g.gref", filtro.getGref());
            setQuery("g.grplce", filtro.getGrplce());
            setQuery("g.mgid", filtro.getMgid());
            setQuery("g.cid", filtro.getCid());
            setQuery("g.sid", filtro.getSid());
            setQuery("g.gchange", filtro.getGchange());
        }else{
            global = true;
            if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
                setQuery("g.gid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("g.methn", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("g.gnpgs", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("g.gpid1", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("g.gpid2", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("g.germuid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("g.lgid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("g.glocn", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("g.gdate", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("g.gref", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("g.grplce", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("g.mgid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("g.cid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("g.sid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("g.gchange", Integer.valueOf(filtro.getGlobalsearch()));
            }else{

            }
        }
        return query;
    }
}