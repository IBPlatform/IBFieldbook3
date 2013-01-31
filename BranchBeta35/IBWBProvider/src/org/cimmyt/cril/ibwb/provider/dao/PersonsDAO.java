package org.cimmyt.cril.ibwb.provider.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Persons;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;

public class PersonsDAO extends AbstractDAO<Persons, Integer> {

	public PersonsDAO() {
		super(Persons.class);
	}
	
	@Override
	public Persons prepareToCreate(Persons persons) {
        if (isLocal()) {
        	persons.setPersonid(getNextMin());
		}
        if (isCentral()) {
        	persons.setPersonid(getNextMax());
		}
        return persons;
	}

	@Override
	protected void buildCriteria(DetachedCriteria criteria, Persons filter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getKeyProperty() {
		return "personid";
	}

    @Override
    public String getConsulta(Persons filtro) {
    	String query = "from Persons as p";
        
        if(filtro.getGlobalsearch() == null){
        	setQuery("p.personid", filtro.getPersonid());
        	setQuery("p.fname", filtro.getFname());
        	setQuery("p.lname", filtro.getLname());
        	setQuery("p.ioname", filtro.getIoname());
        	setQuery("p.institid", filtro.getInstitid());
        	setQuery("p.ptitle", filtro.getPtitle());
        	setQuery("p.poname", filtro.getPoname());
        	setQuery("p.plangu", filtro.getPlangu());
        	setQuery("p.pphone", filtro.getPphone());
        	setQuery("p.pextent", filtro.getPextent());
        	setQuery("p.pfax", filtro.getPfax());
        	setQuery("p.pemail", filtro.getPemail());
        	setQuery("p.prole", filtro.getProle());
        	setQuery("p.sperson", filtro.getSperson());
        	setQuery("p.eperson", filtro.getEperson());
        	setQuery("p.pstatus", filtro.getPstatus());
        	setQuery("p.pnotes", filtro.getPnotes());
        	setQuery("p.contact", filtro.getContact());
//        	setQuery("p.idno", filtro.getIdno());
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("p.personid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQueryInTo("p.fname", filtro.getGlobalsearch());
	        	setQueryInTo("p.lname", filtro.getGlobalsearch());
	        	setQueryInTo("p.ioname", filtro.getGlobalsearch());
	        	setQuery("p.institid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQueryInTo("p.ptitle", filtro.getGlobalsearch());
	        	setQueryInTo("p.poname", filtro.getGlobalsearch());
	        	setQuery("p.plangu", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQueryInTo("p.pphone", filtro.getGlobalsearch());
	        	setQueryInTo("p.pextent", filtro.getGlobalsearch());
	        	setQueryInTo("p.pfax", filtro.getGlobalsearch());
	        	setQueryInTo("p.pemail", filtro.getGlobalsearch());
	        	setQuery("p.prole", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("p.sperson", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("p.eperson", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("p.pstatus", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQueryInTo("p.pnotes", filtro.getGlobalsearch());
	        	setQueryInTo("p.contact", filtro.getGlobalsearch());
//	        	setQueryInTo("p.idno", filtro.getGlobalsearch());
	        }else{
	        	setQueryInTo("p.fname", filtro.getGlobalsearch());
	        	setQueryInTo("p.lname", filtro.getGlobalsearch());
	        	setQueryInTo("p.ioname", filtro.getGlobalsearch());
	        	setQueryInTo("p.ptitle", filtro.getGlobalsearch());
	        	setQueryInTo("p.poname", filtro.getGlobalsearch());
	        	setQueryInTo("p.pphone", filtro.getGlobalsearch());
	        	setQueryInTo("p.pextent", filtro.getGlobalsearch());
	        	setQueryInTo("p.pfax", filtro.getGlobalsearch());
	        	setQueryInTo("p.pemail", filtro.getGlobalsearch());
	        	setQueryInTo("p.pnotes", filtro.getGlobalsearch());
	        	setQueryInTo("p.contact", filtro.getGlobalsearch());
//	        	setQueryInTo("p.idno", filtro.getGlobalsearch());
	        }
        }
        return query;
    }
}