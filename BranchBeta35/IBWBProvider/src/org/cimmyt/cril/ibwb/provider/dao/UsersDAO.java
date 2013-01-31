package org.cimmyt.cril.ibwb.provider.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Users;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class UsersDAO extends AbstractDAO<Users, Integer> {

    public UsersDAO() {
        super(Users.class);
    }

    @Override
    public Users prepareToCreate(Users users) {
        if (isLocal()) {
            users.setUserid(getNextMin());
        }
        if (isCentral()) {
            users.setUserid(getNextMax());
        }
        return users;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Users filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
        return "userid";
    }

    @Override
    public String getConsulta(Users filtro) {
        String query = "from Users as u";

        if (filtro.getGlobalsearch() == null) {
            setQuery("u.userid", filtro.getUserid());
            setQuery("u.instalid", filtro.getInstalid());
            setQuery("u.ustatus", filtro.getUstatus());
            setQuery("u.uaccess", filtro.getUaccess());
            setQuery("u.utype", filtro.getUtype());
            setQuery("u.uname", filtro.getUname());
            setQuery("u.personid", filtro.getPersonid());
            setQuery("u.adate", filtro.getAdate());
            setQuery("u.cdate", filtro.getCdate());
        } else {
            global = true;
            if (ValidatingDataType.isNumeric(filtro.getGlobalsearch())) {
                setQuery("u.userid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("u.instalid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("u.ustatus", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("u.uaccess", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("u.utype", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryInTo("u.uname", filtro.getGlobalsearch());
                setQuery("u.personid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("u.adate", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("u.cdate", Integer.valueOf(filtro.getGlobalsearch()));
            } else {
                setQueryInTo("u.uname", filtro.getGlobalsearch());
            }
        }
        return query;
    }

    /**
     * get ID for logged user accordin to following parameters
     * USTATUS = 1
     * UACC = 100 	LOCAL ICIS ADMINISTRATOR 
     * UTYPE = 422  LOCAL DATABASE ADMINISTRATOR
     * @return 
     */
    public Integer getLoggedUserId() {
        String queryString = "from Users as u where u.ustatus = 1 and u.uaccess = 100 and u.utype = 422 ";
        Integer loggedUserId = null;
        
        List<Users> users = getHibernateTemplate().find(queryString);
        
        // if no users then return -1
        if (users.isEmpty()) {
            loggedUserId = -1;
        } else {
            // return first user in list
            loggedUserId = users.get(0).getUserid();
        }
        
        return loggedUserId;
    }
}