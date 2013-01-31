package org.cimmyt.cril.ibwb.provider.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Dmsattr;
import org.cimmyt.cril.ibwb.domain.Listdata;
import org.cimmyt.cril.ibwb.domain.ListdataPK;
import org.cimmyt.cril.ibwb.domain.Names;
import org.cimmyt.cril.ibwb.domain.util.WheatData;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import org.springframework.orm.hibernate3.HibernateCallback;

public class ListdataDAO extends AbstractDAO<Listdata, ListdataPK> {

    public ListdataDAO() {
        super(Listdata.class);
    }

    @Override
    public Listdata prepareToCreate(Listdata listdata) {
        if (isLocal()) {

            listdata.getListdataPK().setLrecid(this.getNextMinLrcid());
//            listdata.getListdataPK().setListid(this.getNextMinListid());
//            listdata.setGid(this.getNextMinGid());
//            listdata.getListdataPK().setLrecid(getNextllrecid(listdata.getListdataPK().getListid()));
        }
        if (isCentral()) {
            listdata.getListdataPK().setLrecid(this.getNextMaxLrcid());
//            listdata.getListdataPK().setListid(this.getNextMaxListid());
//            listdata.setGid(this.getNextMaxGid());
//            listdata.getListdataPK().setLrecid(getNextllrecid(listdata.getListdataPK().getListid()));
        }
        return listdata;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Listdata filter) {
        if (filter.getListdataPK() != null && filter.getListdataPK().getListid() != null) {
            Criterion critListId = Restrictions.eq("listdataPK.listid", filter.getListdataPK().getListid());

            // filter only active entries
            Criterion activeEntries = Restrictions.eq("lrstatus", Listdata.LRSTATUS_ACTIVE);

            //criteria.add(Restrictions.eq("listdataPK.listid", filter.getListdataPK().getListid()));
            //} 
            if (filter.getGlobalsearch() != null && !filter.getGlobalsearch().isEmpty()) {
                Criterion critDesig = Restrictions.like("desig", filter.getGlobalsearch(), MatchMode.ANYWHERE);
                Criterion critEntrycd = Restrictions.like("entrycd", filter.getGlobalsearch(), MatchMode.ANYWHERE);
                Criterion critSource = Restrictions.like("source", filter.getGlobalsearch(), MatchMode.ANYWHERE);


                Disjunction disjunction = Restrictions.disjunction();
                disjunction.add(critDesig);
                disjunction.add(critEntrycd);
                disjunction.add(critSource);

                boolean canParseToInt = true;
                Integer number = 0;

                try {
                    number = Integer.parseInt(filter.getGlobalsearch());
                } catch (Exception e) {
                    canParseToInt = false;
                }

                if (canParseToInt) {
                    disjunction.add(Restrictions.eq("listdataPK.gid", number));
                    disjunction.add(Restrictions.eq("entryid", number));
                }
                criteria.add(activeEntries);
                criteria.add(Restrictions.and(critListId, disjunction));

            } else {
                criteria.add(activeEntries);
                criteria.add(critListId);
            }



        }
        // always sort in ascendant way
        criteria.addOrder(Order.asc("entryid"));
    }

    @Override
    public String getKeyProperty() {
        return "listdataPK.listid";
    }

    @Override
    public String getConsulta(Listdata filtro) {
        String query = "from Listdata as l";

        if (filtro.getGlobalsearch() == null) {
            if (filtro.getListdataPK() != null) {
                setQuery("l.listdataPK.listid", filtro.getListdataPK().getListid());
                setQuery("l.listdataPK.lrecid", filtro.getListdataPK().getLrecid());
            }
            setQuery("l.entryid", filtro.getEntryid());
            setQuery("l.entrycd", filtro.getEntrycd());
            setQuery("l.source", filtro.getSource());
            setQuery("l.desig", filtro.getDesig());
            setQuery("l.grpname", filtro.getGrpname());
            setQuery("l.gid", filtro.getGid());
            setQuery("l.lrstatus", filtro.getLrstatus());
        } else {
            global = true;
            if (ValidatingDataType.isNumeric(filtro.getGlobalsearch())) {
                setQuery("l.listdataPK.listid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("l.listdataPK.lrecid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("l.entryid", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryInTo("l.entrycd", filtro.getGlobalsearch());
                setQueryInTo("l.source", filtro.getGlobalsearch());
                setQuery("l.desig", filtro.getGlobalsearch());
                setQuery("l.grpname", filtro.getGlobalsearch());
//                setQuery("l.lrecid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("l.lrstatus", Integer.valueOf(filtro.getGlobalsearch()));
            } else {
                setQueryInTo("l.entrycd", filtro.getGlobalsearch());
                setQueryInTo("l.source", filtro.getGlobalsearch());
            }
        }
        return query;
    }

    public Integer getNextllrecid(final Integer listid) {
        Integer result = (Integer) getHibernateTemplate().execute(
                new HibernateCallback() {

                    public Object doInHibernate(
                            final org.hibernate.Session session)
                            throws HibernateException, SQLException {
                        final String queryString = "select max(listdata.listdataPK.lrecid) from Listdata as listdata "
                                + " where listdata.listdataPK.listid = :LISTID ";

                        Query query = session.createQuery(queryString);

                        query.setParameter("LISTID", listid);

                        Integer result = (Integer) query.uniqueResult();

                        Integer nextLlrecid = 0;
                        if (result != null) {

                            nextLlrecid = result.intValue();
                        }
                        return nextLlrecid + 1;
                    }
                });

        System.out.println("getNextllrecid : " + result);

        return result;
    }

    public Integer getNextMaxLrcid() {
        String hql = "select max(listdataPK.lrecid) from Listdata as l ";
        Integer result = executeQueryCustomUniqueResult(hql);
        if (result > 1) {
            return result++;
        }
        return result;
    }
//    
//    public Integer getNextMaxListid(){
//        String hql = "select max(listdataPK.listid) from Listdata as l ";
//        Integer result = executeQueryCustomUniqueResult(hql);
//        if(result>1)
//            return result++;
//        return result;
//    }
//    
//    public Integer getNextMaxGid(){
//        String hql = "select max(gid) from Listdata as l ";
//        Integer result = executeQueryCustomUniqueResult(hql);
//        if(result>1)
//            return result++;
//        return result;
//    }
//    

    public Integer getNextMinLrcid() {
        String hql = "select min(listdataPK.lrecid) from Listdata as l ";
        Integer result = executeQueryCustomUniqueResult(hql);
        return validMin(result);
    }
//    
//    public Integer getNextMinListid(){
//        String hql = "select min(listdataPK.listid) from Listdata as l ";
//        Integer result = executeQueryCustomUniqueResult(hql);
//        return validMin(result);
//    }
//    
//    public Integer getNextMinGid(){
//        String hql = "select min(gid) from Listdata as l ";
//        Integer result = executeQueryCustomUniqueResult(hql);
//        return validMin(result);
//    }

    public Integer validMin(Integer result) {
        if (result == null) {
            result = -1;
        } else if (result > 0) {
            result = -1;
        } else {
            result--;
        }
        return result--;
    }

    /**
     * Delete logically a germplasm entry
     *
     * @param listdata Entro to delete
     */
    public void logicalDelete(Listdata listdata) {
        listdata = read(listdata.getListdataPK());
        listdata.setLrstatus(Listdata.LSSTATUS_DELETED);
        getHibernateTemplate().update(listdata);
    }

    /**
     * Deletes logically a list of germplasm entries
     *
     * @param listdataEntries List of germplasm list entries to delete
     */
    public void logicalDelete(List<Listdata> listdataEntries) {
        for (Listdata listdata : listdataEntries) {
            logicalDelete(listdata);
        }
    }

    /**
     * Deletes logically all entries of a list
     *
     * @param listId listid to delete
     */
    public void logicalDeleteAllEntries(final Integer listId) {
        final String updateListdataStatus = " update Listdata listdata set listdata.lrstatus = "
                + Listdata.LSSTATUS_DELETED + " where listdata.listdataPK.listid = " + listId;
        try {
            getHibernateTemplate().bulkUpdate(updateListdataStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Return a list of grouping Listdata by idListnms
     *
     * @param idListnms ID for Listnms
     * @return list of Listdata or empty list if Listnms id not found
     */
    public List<Listdata> getListdataByIdlistnms(final Integer idListnms,List<Dmsattr> dmsattrList) {
        List<Listdata> listdataList = new ArrayList<Listdata>();

        String order = "";
//        if (isLocal()) {
//            order = " order by l.entryid desc";
//        } else {
        order = " order by l.entryid";
//        }

        final String queryString = " from Listdata as l where "
                + " l.listdataPK.listid = ? " + order;

        listdataList = getHibernateTemplate().find(queryString, idListnms);

        fillListDataAtributtes(listdataList, dmsattrList);

        return listdataList;
    }

    /**
     * Fill all attributes for a Listdata
     * @param listDataList
     * @param idListnms 
     */
    private void fillListDataAtributtes(List<Listdata> listDataList,List<Dmsattr> dmsattrList) {
        // first retrieve all atributes for desired listid
        Map<Integer, WheatData> wheatDataList = new HashMap<Integer, WheatData>();

        // then populate wheatData List for fast retrieving
        for (Dmsattr dmsattr : dmsattrList) {
            if (wheatDataList.containsKey(dmsattr.getDmsatrec())) {
                updateWheatData(dmsattr, wheatDataList);
            } else {
                addNewWheatData(dmsattr, wheatDataList);
            }
        }

        // then iterate over lista data records to assign it corresponing atributes
        for (Listdata listdata: listDataList) {
            if (wheatDataList.containsKey(listdata.getListdataPK().getLrecid()))  {
                listdata.setWheatData(wheatDataList.get(listdata.getListdataPK().getLrecid()));
            }
        }
    }

    /**
     * Update attributes for a Wheatdata bean
     * @param dmsattr
     * @param wheatDataList 
     */
    private void updateWheatData(Dmsattr dmsattr, Map<Integer, WheatData> wheatDataList) {
        WheatData wheatData = wheatDataList.get(dmsattr.getDmsatrec());
        populateWheatData(wheatData, dmsattr);
    }

    /**
     * Adds a new Wheatdata bean to collection
     * @param dmsattr
     * @param wheatDataList 
     */
    private void addNewWheatData(Dmsattr dmsattr, Map<Integer, WheatData> wheatDataList) {
        WheatData wheatData = new WheatData();
        wheatDataList.put(dmsattr.getDmsatrec(), wheatData);
        populateWheatData(wheatData, dmsattr);
    }

    /**
     * Populates a Wheatdata bean with data coming from DmsAttr
     * @param wheatData bean to populate
     * @param dmsattr dmsattr used to populate bean
     */
    private void populateWheatData(WheatData wheatData, Dmsattr dmsattr) {
        Integer integerValue = null;

        if (dmsattr != null && dmsattr.getDmsatval() != null && !dmsattr.getDmsatval().isEmpty()) {
            try {
                integerValue = Integer.parseInt(dmsattr.getDmsatval());
            } catch (Exception e) {
            }

        }

        switch (dmsattr.getDmsatype()) {
            case Dmsattr.DMSATYPE_FTID:
                wheatData.setFtid(integerValue);
                break;
            case Dmsattr.DMSATYPE_FOCC:
                wheatData.setFocc(integerValue);
                break;
            case Dmsattr.DMSATYPE_FENT:
                wheatData.setFent(integerValue);
                break;
            case Dmsattr.DMSATYPE_MTID:
                wheatData.setMtid(integerValue);
                break;
            case Dmsattr.DMSATYPE_MOCC:
                wheatData.setMocc(integerValue);
                break;
            case Dmsattr.DMSATYPE_MENT:
                wheatData.setMent(integerValue);
                break;
        }
    }

  
    /**
     * Gets a list for Wheat Data (cimmyt) related to BCID, Selection history
     * 1. It looks for all elements in names where gid are used by a list
     * @param listId
     * @return Gets a list for Wheat Data (cimmyt)
     */
    public List<WheatData> getDataForCimmytWheat(final Integer listId) {
        List<WheatData> wheatDataList = new ArrayList<WheatData>();
        Integer currentGid = -99999999;
        
        final String queryString = " from Names as n where n.gid in "
                + " (select distinct ld.gid from Listdata as ld where ld.listdataPK.listid = " + listId + " )" +
                " order by n.gid, n.ntype ";
        
        List<Names> namesList = getHibernateTemplate().find(queryString);
        WheatData wheatDataToAdd = new WheatData();
        for (Names name : namesList) {
            
            if (currentGid.intValue() != name.getGid().intValue()) {
                wheatDataList.add( wheatDataToAdd);
                currentGid = name.getGid();
                wheatDataToAdd = new WheatData();
                wheatDataToAdd.setGid(name.getGid());
                fillWheatData(wheatDataToAdd, name);
            } else {
                fillWheatData(wheatDataToAdd, name);
            }
        }
        
        // remove first element because is null;
        wheatDataList.remove(0);
        return wheatDataList;
    }
    
    /**
     * Fills a wheat data comparing ntype values
     * @param wheatData Wheat Data to fill
     * @param names Names used to check values
     */
    private void fillWheatData(WheatData wheatData, Names names) {
        if (names.getNtype() != null) {
            switch (names.getNtype()) {
                case Names.CIMMYT_WHEAT_BCID:
                    wheatData.setBcid(names.getNval());
                    break;
                case Names.CIMMYT_WHEAT_PEDIGREE:
                    wheatData.setCrossName(names.getNval());
                    break;
                case Names.CIMMYT_WHEAT_SELECTION_HISTORY:
                    wheatData.setSelectionHistory(names.getNval());
                    break;
                   
            }
        }
    }    
    
    /**
     * Gets all Lrectidbylist according to given list
     * @param List id
     */
    public List<Integer> getLRecidListByListId(final Integer listId) {
        List<Integer> lrecidList = new ArrayList<Integer>();
        
        String queryString = "select listdataPK.lrecid from Listdata where  listdataPK.listid = ? ";
        lrecidList = getHibernateTemplate().find(queryString,listId);
//        lrecidList = getHibernateTemplate().executeFind(new HibernateCallback() {
//
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException, SQLException {
//                
//            }
//        });
        return lrecidList;
        
    }
            
}