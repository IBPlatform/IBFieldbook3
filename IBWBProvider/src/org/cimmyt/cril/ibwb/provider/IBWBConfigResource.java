/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Properties;
import org.openide.modules.InstalledFileLocator;

import org.springframework.core.io.Resource;

/**
 *
 * @author TMSANCHEZ
 */
public class IBWBConfigResource implements Resource {

    private static final String FILE_NAME = "databaseconfig.properties";
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";

    @Override
    public Resource createRelative(String arg0) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean exists() {
        return true;
    }

    @Override
    public String getDescription() {

        return "Configuration file for IBWBProvider";
    }

    @Override
    public File getFile() throws IOException {
        String configFileName = getConfigPath() + FILE_NAME;
        File file = new File(configFileName);
        return file;
    }

    @Override
    public String getFilename() {
        return FILE_NAME;
    }

    @Override
    public URI getURI() throws IOException {
        return null;
    }

    @Override
    public URL getURL() throws IOException {
        return null;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public boolean isReadable() {
        return true;
    }

    @Override
    public long lastModified() throws IOException {
        return 0;
    }

    /* (non-Javadoc)
     * @see org.springframework.core.io.InputStreamSource#getInputStream()
     */
    @Override
    public InputStream getInputStream() throws IOException {
        String configFileName = getConfigPath()  + FILE_NAME;
        InputStream resultStream = new FileInputStream(configFileName);
        return resultStream;
    }

    /**
     * 
     * @param url
     * @param user
     * @param password
     */
    public static void storeConnectionParameters(String url, String user, String password) {
        String configFileName = getConfigPath() + "/dnastconfig/" + FILE_NAME;
        Properties configProperties = new Properties();

        configProperties.put(DB_URL, url);
        configProperties.put(DB_USER, user);
        configProperties.put(DB_PASSWORD, password);

        FileOutputStream configFile;
        try {
            configFile = new FileOutputStream(configFileName);
            configProperties.store(configFile, "# file changed" + new java.util.Date().toString());
            configFile.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }

    private static String getConfigPath() {
        InstalledFileLocator locator = InstalledFileLocator.getDefault();
        File databaseConfigFile = locator.locate("modules/config/databaseconfig.properties", "org.cimmyt.cril.ibwb.provider", false);


        return databaseConfigFile.getParent();
    }
}
