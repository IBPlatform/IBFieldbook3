/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.datasources;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSource;
import org.openide.modules.InstalledFileLocator;
/**
 *
 * @author TMSANCHEZ
 */
public class IBWBDataSource extends BasicDataSource {

    public IBWBDataSource(String databasePrefix) {
        InstalledFileLocator locator = InstalledFileLocator.getDefault();
        File databaseConfigFile = locator.locate("modules/ext/databaseconfig.properties", "org.cimmyt.cril.ibwb.provider", false);
        Properties propertiesFile = new Properties();
        try {
            InputStream streamProperties = new FileInputStream(databaseConfigFile);
            propertiesFile.load(streamProperties);

            this.driverClassName = propertiesFile.getProperty(databasePrefix +".driverclassname");
            this.url = propertiesFile.getProperty(databasePrefix +".url");
            this.username = propertiesFile.getProperty(databasePrefix +".username");
            this.password = propertiesFile.getProperty(databasePrefix +".password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
