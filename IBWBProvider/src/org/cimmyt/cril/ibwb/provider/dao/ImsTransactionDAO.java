package org.cimmyt.cril.ibwb.provider.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.ImsTransaction;
import org.cimmyt.cril.ibwb.domain.Users;
import org.cimmyt.cril.ibwb.domain.inventory.InventoryData;

import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 *
 * @author TMSANCHEZ
 */
public class ImsTransactionDAO extends AbstractDAO<ImsTransaction, Integer> {

    public ImsTransactionDAO() {
        super(ImsTransaction.class);
    }

    @Override
    public ImsTransaction prepareToCreate(ImsTransaction imsTransaction) {
        if (isLocal()) {
            imsTransaction.setTrnid(getNextMin());
        } else {
            imsTransaction.setTrnid(getNextMax());
        }
        return imsTransaction;
    }

    @Override
    public String getKeyProperty() {
        return "trnid";
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, ImsTransaction filter) {
    }

    @Override
    public String getConsulta(ImsTransaction filtro) {
        return "";
    }

    /**
     * Get information data from a List
     * @param listId Id for LIST
     * @return 
     */
    public List<InventoryData> getInventoryDataFromList(final Integer listId) {
        List<InventoryData> resultList = new ArrayList<InventoryData>();

        final StringBuilder querySQL = new StringBuilder();
        querySQL.append(" SELECT LISTDATA.ENTRYID, LISTDATA.DESIG, LISTDATA.GID , IMS_LOT.LOCID, IMS_LOT.COMMENTS, IMS_TRANSACTION.TRNQTY, IMS_LOT.SCALEID ");
        querySQL.append(" FROM LISTDATA, IMS_LOT, IMS_TRANSACTION  ");
        querySQL.append(" WHERE (LISTDATA.LISTID = ").append(listId).append(") ");
        querySQL.append("   AND  (IMS_LOT.LOTID = IMS_TRANSACTION.LOTID) AND ( IMS_LOT.EID = LISTDATA.GID) ");
        querySQL.append(" ORDER BY LISTDATA.ENTRYID ");


        resultList = getHibernateTemplate().executeFind(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                
                List<InventoryData> resultList = new ArrayList<InventoryData>();
                
                SQLQuery query = session.createSQLQuery(querySQL.toString());
                query.addScalar("ENTRYID", Hibernate.INTEGER);
                query.addScalar("DESIG", Hibernate.STRING);
                query.addScalar("GID", Hibernate.INTEGER);
                query.addScalar("LOCID", Hibernate.INTEGER);
                query.addScalar("COMMENTS", Hibernate.STRING);
                query.addScalar("TRNQTY", Hibernate.DOUBLE);
                query.addScalar("SCALEID", Hibernate.INTEGER);
                
                List<Object> records =  query.list();
                
                for (Object record :records) {
                    Object[] recordArray = (Object[])record;
                    InventoryData inventoryData = new InventoryData();
                    
                    inventoryData.setEntry((Integer)recordArray[0]);
                    inventoryData.setDesig((String)recordArray[1]);
                    inventoryData.setGid((Integer)recordArray[2]);
                    inventoryData.setLocationid((Integer)recordArray[3]);
                    inventoryData.setComment((String)recordArray[4]);
                    inventoryData.setAmmount((Double)recordArray[5]);
                    inventoryData.setScale((Integer)recordArray[6]);
                    
                    resultList.add(inventoryData);
                }

                return resultList;
            }
        });
        return resultList;
    }
}
