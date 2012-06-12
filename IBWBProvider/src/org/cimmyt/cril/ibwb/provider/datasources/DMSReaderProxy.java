/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.datasources;

import cimmyt.cril.queryReadCenter.QueryCenterTemp;
import java.sql.Connection;
import org.cimmyt.cril.dmsreader.DMSReader;

/**
 *
 * @author TMSANCHEZ
 */
public class DMSReaderProxy {

    private static DMSReaderProxy dMSReaderProxy;
    /**
     * Local Database Connection for DMS
     */
    private Connection dmsConnectionLocal;
    /**
     * Central Database Connection for DMS
     */
    private Connection dmsConnectionCentral;
    /**
     * Local Database Connection for GMS
     */
    private Connection gmsConnectionLocal;
    /**
     * Central Database Connection for GMS
     */
    private Connection gmsConnectionCentral;
    /**
     * Are connections already assigned?
     */
    private boolean connectionsAssiged = false;
    /**
     * DMSReader Object from Juan Gonzalez Espinoza
     */
    private DMSReader dmsReader;
    private QueryCenterTemp queryCenterTemp;

    public static DMSReaderProxy getDefault() {
        if (dMSReaderProxy == null) {
            dMSReaderProxy = new DMSReaderProxy();
        }
        return dMSReaderProxy;
    }

    private DMSReaderProxy() {
        dmsReader = new DMSReader();
        queryCenterTemp = new QueryCenterTemp();
    }

    public DMSReader getDmsReader() {
        if (!connectionsAssiged) {
            assignConnections();
            connectionsAssiged = true;
        }
        return dmsReader;
    }
    
    public QueryCenterTemp getQueryCenterTemp() {
        if (!connectionsAssiged) {
            assignConnections();
            connectionsAssiged = true;
        }
        return queryCenterTemp;
    }

    public Connection getDmsConnectionCentral() {
        return dmsConnectionCentral;
    }

    public void setDmsConnectionCentral(Connection dmsConnectionCentral) {
        this.dmsConnectionCentral = dmsConnectionCentral;
    }

    public Connection getDmsConnectionLocal() {
        return dmsConnectionLocal;
    }

    public void setDmsConnectionLocal(Connection dmsConnectionLocal) {
        this.dmsConnectionLocal = dmsConnectionLocal;
    }

    public Connection getGmsConnectionCentral() {
        return gmsConnectionCentral;
    }

    public void setGmsConnectionCentral(Connection gmsConnectionCentral) {
        this.gmsConnectionCentral = gmsConnectionCentral;
    }

    public Connection getGmsConnectionLocal() {
        return gmsConnectionLocal;
    }

    public void setGmsConnectionLocal(Connection gmsConnectionLocal) {
        this.gmsConnectionLocal = gmsConnectionLocal;
    }

    private void assignConnections() {
        this.dmsReader.sendConnections(gmsConnectionCentral, dmsConnectionCentral, gmsConnectionLocal, dmsConnectionLocal);
        this.queryCenterTemp.sendConnections(dmsConnectionLocal, dmsConnectionLocal, dmsConnectionLocal, dmsConnectionLocal);
    }

    /**
     * @param queryCenterTemp the queryCenterTemp to set
     */
    public void setQueryCenterTemp(QueryCenterTemp queryCenterTemp) {
        this.queryCenterTemp = queryCenterTemp;
    }
}
