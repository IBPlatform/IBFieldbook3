/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.db.options;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.cimmyt.cril.ibwb.db.util.DatabaseInfo;
import org.openide.modules.InstalledFileLocator;

/**
 *
 * @author TMSANCHEZ
 */
public class IBFbConfigFile {

    public static void saveConfigFile(DatabaseInfo dmsCentral, DatabaseInfo gmsCentral, DatabaseInfo dmsLocal, DatabaseInfo gmsLocal) {
        InstalledFileLocator locator = InstalledFileLocator.getDefault();
        File databaseConfigFile = locator.locate("modules/ext/databaseconfig.properties", "org.cimmyt.cril.ibwb.provider", false);


        try {

            FileWriter fileWriter = new FileWriter(databaseConfigFile);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("##############################################");
            printWriter.println("## Database configuration file");
            printWriter.println("## Last Date changed:" + (new java.util.Date()).toString());
            printWriter.println("##############################################");
            printWriter.println("");
            printWriter.println("##############################################");
            printWriter.println("###    SAMPLE CONFIGURATION FOR ACCESS     ###");
            printWriter.println("## local database parameters");
            printWriter.println("#dmslocal.hibernateDialect=");
            printWriter.println("#dmslocal.url=jdbc:odbc:DATASOURCENAME");
            printWriter.println("#dmslocal.driverclassname=sun.jdbc.odbc.JdbcOdbcDriver");
            printWriter.println("#dmslocal.username=USER");
            printWriter.println("#dmslocal.password=PASSOWRD");
            printWriter.println("#dmslocal.defaultSchema=");
            printWriter.println("###");
            printWriter.println("###    SAMPLE CONFIGURATION FOR MYSQL     ###");
            printWriter.println("#dmslocal.url=jdbc:mysql://localhost:3306/DATABASENAME");
            printWriter.println("#dmslocal.driverclassname=com.mysql.jdbc.Driver");
            printWriter.println("#dmslocal.username=USERNAME" );
            printWriter.println("#dmslocal.password=PASSWORD");
            printWriter.println("#dmslocal.defaultSchema=");
            printWriter.println("##############################################");
            printWriter.println("###  END  SAMPLE CONFIGURATION FOR ACCESS     ###");
            printWriter.println("##############################################");
            printWriter.println("");
            printWriter.println("");
            printWriter.println("##############################################");            
            printWriter.println("###   DMS CENTRAL CONFIGURATION     ###");
            printWriter.println("");
            printWriter.println("dmscentral.hibernateDialect=");
            printWriter.println("dmscentral.url=" + dmsCentral.getUrl() );
            printWriter.println("dmscentral.driverclassname=" + dmsCentral.getDriverName());
            printWriter.println("dmscentral.username="+dmsCentral.getUserName());
            printWriter.println("dmscentral.password="+dmsCentral.getPassword());
            printWriter.println("dmscentral.accessType=central");
            printWriter.println("");
            printWriter.println("###   GMS CENTRAL CONFIGURATION     ###");
            printWriter.println("gmscentral.hibernateDialect=");
            printWriter.println("gmscentral.url=" + gmsCentral.getUrl() );
            printWriter.println("gmscentral.driverclassname=" + gmsCentral.getDriverName());
            printWriter.println("gmscentral.username="+gmsCentral.getUserName());
            printWriter.println("gmscentral.password="+gmsCentral.getPassword());
            printWriter.println("gmscentral.accessType=central");
            printWriter.println("");
            printWriter.println("##############################################");
            printWriter.println("");
            printWriter.println("##############################################");            
            printWriter.println("###   DMS LOCAL CONFIGURATION     ###");
            printWriter.println("");
            printWriter.println("dmslocal.hibernateDialect=");
            printWriter.println("dmslocal.url=" + dmsLocal.getUrl() );
            printWriter.println("dmslocal.driverclassname=" + dmsLocal.getDriverName());
            printWriter.println("dmslocal.username="+dmsLocal.getUserName());
            printWriter.println("dmslocal.password="+dmsLocal.getPassword());
            printWriter.println("dmslocal.accessType=local");
            printWriter.println("");
            printWriter.println("###   GMS LOCAL CONFIGURATION     ###");
            printWriter.println("gmslocal.hibernateDialect=");
            printWriter.println("gmslocal.url=" + gmsLocal.getUrl() );
            printWriter.println("gmslocal.driverclassname=" + gmsLocal.getDriverName());
            printWriter.println("gmslocal.username="+gmsLocal.getUserName());
            printWriter.println("gmslocal.password="+gmsLocal.getPassword());
            printWriter.println("gmslocal.accessType=local");
            printWriter.println("");
            printWriter.println("##############################################");
            
            printWriter.close();
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

