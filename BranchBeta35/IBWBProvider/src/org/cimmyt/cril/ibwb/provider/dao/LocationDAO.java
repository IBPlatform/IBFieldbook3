package org.cimmyt.cril.ibwb.provider.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Location;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

public class LocationDAO extends AbstractDAO<Location, Integer> {

    public LocationDAO() {
        super(Location.class);
    }

    @Override
    public Location prepareToCreate(Location location) {
        if (isLocal()) {
            location.setLocid(getNextMin());
        }
        if (isCentral()) {
            location.setLocid(getNextMax());
        }
        return location;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Location filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
        return "locid";
    }

    @Override
    public String getConsulta(Location filtro) {
        String query = "from Location as l";

        if (filtro.getGlobalsearch() == null) {
            setQuery("l.locid", filtro.getLocid());
            setQuery("l.ltype", filtro.getLtype());
            setQuery("l.nllp", filtro.getNllp());
            setQuery("l.lname", filtro.getLname());
            setQuery("l.labbr", filtro.getLabbr());
            setQuery("l.snl3id", filtro.getSnl3id());
            setQuery("l.snl2id", filtro.getSnl2id());
            setQuery("l.snl1id", filtro.getSnl1id());
            setQuery("l.cntryid", filtro.getCntryid());
            setQuery("l.lrplce", filtro.getLrplce());
        } else {
            global = true;
            if (ValidatingDataType.isNumeric(filtro.getGlobalsearch())) {
                setQuery("l.locid", Integer.valueOf(filtro.getLocid()));
                setQuery("l.ltype", Integer.valueOf(filtro.getLtype()));
                setQuery("l.nllp", Integer.valueOf(filtro.getNllp()));
                setQueryInTo("l.lname", filtro.getLname());
                setQueryInTo("l.labbr", filtro.getLabbr());
                setQuery("l.snl3id", Integer.valueOf(filtro.getSnl3id()));
                setQuery("l.snl2id", Integer.valueOf(filtro.getSnl2id()));
                setQuery("l.snl1id", Integer.valueOf(filtro.getSnl1id()));
                setQuery("l.cntryid", Integer.valueOf(filtro.getCntryid()));
                setQuery("l.lrplce", Integer.valueOf(filtro.getLrplce()));
            } else {
                setQueryInTo("l.lname", filtro.getLname());
                setQueryInTo("l.labbr", filtro.getLabbr());
            }
        }
        return query;
    }
    
    /**
     * 
     * @param countryId
     * @param fromLocid
     * @param toLocid
     * @return 
     */
    public List<Location> getLocationsByCountryLocidRange(final Integer countryId, final Integer fromLocid, final Integer toLocid) {
        StringBuilder  queryString = new StringBuilder("from Location as l " );
        queryString.append(" where l.cntryid = ").append(countryId);
        queryString.append(" and l.locid between ").append(fromLocid).append(" and ").append(toLocid);
        queryString.append(" and l.labbr is not null ");
        
        return getHibernateTemplate().find(queryString.toString());
    }
}