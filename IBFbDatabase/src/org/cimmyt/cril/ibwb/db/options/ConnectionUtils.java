
package org.cimmyt.cril.ibwb.db.options;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.cimmyt.cril.ibwb.db.util.DatabaseInfo;
import org.openide.util.Exceptions;

/**
 *
 * @author TMSANCHEZ
 */
public class ConnectionUtils {

    private static Map<DatabaseType, DatabaseInfo> dbSettings;

    static {
        dbSettings = new HashMap<DatabaseType, DatabaseInfo>();
        dbSettings.put(DatabaseType.MYSQL, new DatabaseInfo("com.mysql.jdbc.Driver", "", "jdbc:mysql://"));
        dbSettings.put(DatabaseType.ACCESS, new DatabaseInfo("sun.jdbc.odbc.JdbcOdbcDriver", "", "jdbc:odbc:"));
    }

    /**
     * Test a database connection
     * @param databaseType
     * @param url
     * @param userName
     * @param password
     * @return 
     */
    public static boolean connectionSuccess(DatabaseType databaseType, String url, String userName, String password) {
        boolean connected = false;
        Connection connection = null;
        try {
            Class.forName(dbSettings.get(databaseType).getDriverName());
            connection = DriverManager.getConnection(url, userName, password);
            connected = connection != null;
        } catch (Exception e) {
            e.printStackTrace();
            connected = false;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }

        return connected;
    }

    /**
     * Get information from selected database
     * @param databaseType
     * @return 
     */
    public static DatabaseInfo getDatabaseInfo(DatabaseType databaseType) {
        return dbSettings.get(databaseType);
    }
    
    public static DatabaseInfo getDatabaseInfo(String connectionUrl) {
         int indexSrv = connectionUrl.indexOf("//");
                int indexPort = connectionUrl.lastIndexOf(":");
                int indexDb = connectionUrl.lastIndexOf("/");
                String serverName = connectionUrl.substring(indexSrv + 2, indexPort);
                String port = connectionUrl.substring(indexPort + 1, indexDb);
                String dbName = connectionUrl.substring(indexDb + 1);
         DatabaseInfo databaseInfo = new DatabaseInfo();
         databaseInfo.setServerName(serverName);
         databaseInfo.setPort(port);
         databaseInfo.setDatabaseName(dbName);
         return databaseInfo;
    }
}
