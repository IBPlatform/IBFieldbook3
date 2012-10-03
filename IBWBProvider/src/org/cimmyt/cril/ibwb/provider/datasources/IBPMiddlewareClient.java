package org.cimmyt.cril.ibwb.provider.datasources;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import org.generationcp.middleware.manager.DatabaseConnectionParameters;
import org.generationcp.middleware.manager.FourConnectionManagerFactory;
import org.generationcp.middleware.manager.api.GermplasmDataManager;
import org.openide.modules.InstalledFileLocator;

/**
 *
 * @author TMSANCHEZ
 */
public class IBPMiddlewareClient {

    private DatabaseConnectionParameters gmsLocalDbParameters;
    private DatabaseConnectionParameters gmsCentralDbParameters;
    private DatabaseConnectionParameters dmsLocalDbParameters;
    private DatabaseConnectionParameters dmsCentralDbParameters;
    private FourConnectionManagerFactory factory;
    private String gmsCentralDriver;
    private String gmsCentralUrl;
    private String gmsCentralUsername;
    private String gmsCentralPassword;
    private String gmsLocalDriver;
    private String gmsLocalUrl;
    private String gmsLocalUsername;
    private String gmsLocalPassword;
    private String dmsCentralDriver;
    private String dmsCentralUrl;
    private String dmsCentralUsername;
    private String dmsCentralPassword;
    private String dmsLocalDriver;
    private String dmsLocalUrl;
    private String dmsLocalUsername;
    private String dmsLocalPassword;
    private boolean staticMode = true;

    public IBPMiddlewareClient() {
    }

    public GermplasmDataManager getGermplasmDataManager() {
        if (factory == null) {
            if (staticMode) {
                createStaticFactory();
            } else {
                createFactory();
            }
        }
        return factory.getGermplasmDataManager();
    }

    private void createStaticFactory() {
        try {


            gmsLocalDbParameters =
                    new DatabaseConnectionParameters(gmsLocalDriver, gmsLocalUrl, gmsLocalUsername, gmsLocalPassword);

            gmsCentralDbParameters =
                    new DatabaseConnectionParameters(gmsCentralDriver, gmsCentralUrl, gmsCentralUsername, gmsCentralPassword);

            dmsLocalDbParameters =
                    new DatabaseConnectionParameters(dmsLocalDriver, dmsLocalUrl, dmsLocalUsername, dmsLocalPassword);

            dmsCentralDbParameters =
                    new DatabaseConnectionParameters(dmsCentralDriver, dmsCentralUrl, dmsCentralUsername, dmsCentralPassword);
            //create the ManagerFactory            
            factory = new FourConnectionManagerFactory(gmsLocalDbParameters, gmsCentralDbParameters, dmsLocalDbParameters, dmsCentralDbParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createFactory() {
        InstalledFileLocator locator = InstalledFileLocator.getDefault();
        File databaseConfigFile = locator.locate("modules/ext/databaseconfig.properties", "org.cimmyt.cril.ibwb.provider", false);
        Properties propertiesFile = new Properties();
        String databasePrefix = "";
        try {
            InputStream streamProperties = new FileInputStream(databaseConfigFile);
            propertiesFile.load(streamProperties);

            gmsCentralDriver = propertiesFile.getProperty("gmscentral.driverclassname");
            gmsCentralUrl = propertiesFile.getProperty("gmscentral.url");
            gmsCentralUsername = propertiesFile.getProperty("gmscentral.username");
            gmsCentralPassword = propertiesFile.getProperty("gmscentral.password");

            gmsLocalDriver = propertiesFile.getProperty("gmslocal.driverclassname");
            gmsLocalUrl = propertiesFile.getProperty("gmslocal.url");
            gmsLocalUsername = propertiesFile.getProperty("gmslocal.username");
            gmsLocalPassword = propertiesFile.getProperty("gmslocal.password");

            dmsCentralDriver = propertiesFile.getProperty("dmscentral.driverclassname");
            dmsCentralUrl = propertiesFile.getProperty("dmscentral.url");
            dmsCentralUsername = propertiesFile.getProperty("dmscentral.username");
            dmsCentralPassword = propertiesFile.getProperty("dmscentral.password");

            dmsLocalDriver = propertiesFile.getProperty("dmslocal.driverclassname");
            dmsLocalUrl = propertiesFile.getProperty("dmslocal.url");
            dmsLocalUsername = propertiesFile.getProperty("dmslocal.username");
            dmsLocalPassword = propertiesFile.getProperty("dmslocal.password");

            gmsLocalDbParameters =
                    new DatabaseConnectionParameters(gmsLocalDriver, gmsLocalUrl, gmsLocalUsername, gmsLocalPassword);

            gmsCentralDbParameters =
                    new DatabaseConnectionParameters(gmsCentralDriver, gmsCentralUrl, gmsCentralUsername, gmsCentralPassword);

            dmsLocalDbParameters =
                    new DatabaseConnectionParameters(dmsLocalDriver, dmsLocalUrl, dmsLocalUsername, dmsLocalPassword);

            dmsCentralDbParameters =
                    new DatabaseConnectionParameters(dmsCentralDriver, dmsCentralUrl, dmsCentralUsername, dmsCentralPassword);
            //create the ManagerFactory            
            factory = new FourConnectionManagerFactory(gmsLocalDbParameters, gmsCentralDbParameters, dmsLocalDbParameters, dmsCentralDbParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDmsCentralDriver() {
        return dmsCentralDriver;
    }

    public void setDmsCentralDriver(String dmsCentralDriver) {
        this.dmsCentralDriver = dmsCentralDriver;
    }

    public String getDmsCentralPassword() {
        return dmsCentralPassword;
    }

    public void setDmsCentralPassword(String dmsCentralPassword) {
        this.dmsCentralPassword = dmsCentralPassword;
    }

    public String getDmsCentralUrl() {
        return dmsCentralUrl;
    }

    public void setDmsCentralUrl(String dmsCentralUrl) {
        this.dmsCentralUrl = dmsCentralUrl;
    }

    public String getDmsCentralUsername() {
        return dmsCentralUsername;
    }

    public void setDmsCentralUsername(String dmsCentralUsername) {
        this.dmsCentralUsername = dmsCentralUsername;
    }

    public String getDmsLocalDriver() {
        return dmsLocalDriver;
    }

    public void setDmsLocalDriver(String dmsLocalDriver) {
        this.dmsLocalDriver = dmsLocalDriver;
    }

    public String getDmsLocalPassword() {
        return dmsLocalPassword;
    }

    public void setDmsLocalPassword(String dmsLocalPassword) {
        this.dmsLocalPassword = dmsLocalPassword;
    }

    public String getDmsLocalUrl() {
        return dmsLocalUrl;
    }

    public void setDmsLocalUrl(String dmsLocalUrl) {
        this.dmsLocalUrl = dmsLocalUrl;
    }

    public String getDmsLocalUsername() {
        return dmsLocalUsername;
    }

    public void setDmsLocalUsername(String dmsLocalUsername) {
        this.dmsLocalUsername = dmsLocalUsername;
    }

    public DatabaseConnectionParameters getGmsCentralDbParameters() {
        return gmsCentralDbParameters;
    }

    public void setGmsCentralDbParameters(DatabaseConnectionParameters gmsCentralDbParameters) {
        this.gmsCentralDbParameters = gmsCentralDbParameters;
    }

    public String getGmsCentralDriver() {
        return gmsCentralDriver;
    }

    public void setGmsCentralDriver(String gmsCentralDriver) {
        this.gmsCentralDriver = gmsCentralDriver;
    }

    public String getGmsCentralPassword() {
        return gmsCentralPassword;
    }

    public void setGmsCentralPassword(String gmsCentralPassword) {
        this.gmsCentralPassword = gmsCentralPassword;
    }

    public String getGmsCentralUrl() {
        return gmsCentralUrl;
    }

    public void setGmsCentralUrl(String gmsCentralUrl) {
        this.gmsCentralUrl = gmsCentralUrl;
    }

    public String getGmsCentralUsername() {
        return gmsCentralUsername;
    }

    public void setGmsCentralUsername(String gmsCentralUsername) {
        this.gmsCentralUsername = gmsCentralUsername;
    }

    public String getGmsLocalDriver() {
        return gmsLocalDriver;
    }

    public void setGmsLocalDriver(String gmsLocalDriver) {
        this.gmsLocalDriver = gmsLocalDriver;
    }

    public String getGmsLocalPassword() {
        return gmsLocalPassword;
    }

    public void setGmsLocalPassword(String gmsLocalPassword) {
        this.gmsLocalPassword = gmsLocalPassword;
    }

    public String getGmsLocalUrl() {
        return gmsLocalUrl;
    }

    public void setGmsLocalUrl(String gmsLocalUrl) {
        this.gmsLocalUrl = gmsLocalUrl;
    }

    public String getGmsLocalUsername() {
        return gmsLocalUsername;
    }

    public void setGmsLocalUsername(String gmsLocalUsername) {
        this.gmsLocalUsername = gmsLocalUsername;
    }

    public boolean isStaticMode() {
        return staticMode;
    }

    public void setStaticMode(boolean staticMode) {
        this.staticMode = staticMode;
    }
}
