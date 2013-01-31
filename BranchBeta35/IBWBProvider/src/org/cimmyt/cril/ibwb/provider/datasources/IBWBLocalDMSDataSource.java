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
public class IBWBLocalDMSDataSource extends IBWBDataSource {

    public IBWBLocalDMSDataSource() {
        super("dmslocal");

        try {
            DMSReaderProxy.getDefault().setDmsConnectionLocal(this.getConnection());
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
