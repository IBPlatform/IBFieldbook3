package org.cimmyt.cril.ibwb.provider.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Atributs;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

public class AtributsDAO extends AbstractDAO<Atributs, Integer> {

    public AtributsDAO() {
        super(Atributs.class);
    }

    @Override
    public Atributs prepareToCreate(Atributs atributs) {
        if (isLocal()) {
            atributs.setAid(getNextMin());
        }
        if (isCentral()) {
            atributs.setAid(getNextMax());
        }
        return atributs;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Atributs filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
        return "aid";
    }

    @Override
    public String getConsulta(Atributs filtro) {
        String query = "from Atributs as a";

        if (filtro.getGlobalsearch() == null) {
            setQuery("a.aid", filtro.getAid());
            setQuery("a.gid", filtro.getGid());
            setQuery("a.atype", filtro.getAtype());
            setQuery("a.auid", filtro.getAuid());
            setQuery("a.aval", filtro.getAval());
            setQuery("a.alocn", filtro.getAlocn());
            setQuery("a.aref", filtro.getAref());
            setQuery("a.adate", filtro.getAdate());
        } else {
            global = true;
            if (ValidatingDataType.isNumeric(filtro.getGlobalsearch())) {
                setQuery("a.aid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("a.gid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("a.atype", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("a.auid", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryInTo("a.aval", filtro.getGlobalsearch());
                setQuery("a.alocn", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("a.aref", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("a.adate", Integer.valueOf(filtro.getGlobalsearch()));
            } else {
                setQueryInTo("a.aval", filtro.getGlobalsearch());
            }
        }
        return query;
    }
}