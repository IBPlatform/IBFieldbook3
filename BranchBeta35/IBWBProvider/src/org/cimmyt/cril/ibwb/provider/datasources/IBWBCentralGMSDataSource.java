/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.datasources;

import java.sql.SQLException;
import org.openide.util.Exceptions;

/**
 *
 * @author TMSANCHEZ
 */
public class IBWBCentralGMSDataSource extends IBWBDataSource {

    public IBWBCentralGMSDataSource() {
        super("gmscentral");

        try {
            DMSReaderProxy.getDefault().setGmsConnectionCentral(this.getConnection());
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
