/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.settings.core;

import java.util.HashMap;
import java.util.prefs.Preferences;
import org.openide.util.NbPreferences;

import ibfb.settings.folder.panels.TemplatesandListsFoldersPanel;

/**
 * Proxy for manage all settings in FieldBook prototype
 * @author TMSANCHEZ
 */
public class FieldbookSettings {
    // Germplasm lists and Templates default folder constants

    public static final String GERMPLASM_LIST_DEFAULT_FOLDER = "GERMPLASM_LIST_DEFAULT_FOLDER";
    public static final String TEMPLATES_DEFAULT_FOLDER = "TEMPLATES_DEFAULT_FOLDER";
    
    public static final String CROSSES_DEFAULT_FOLDER = "CROSSES_DEFAULT_FOLDER";
    public static final String SELECTION_DEFAULT_FOLDER = "SELECTION_DEFAULT_FOLDER";
    
    private static final HashMap<String, Class> moduleOptionList = new HashMap<String, Class>();
    // R constants
    public static final String R_HOME_FOLDER = "R_HOME_FOLDER";
    // User id for local and user
    public static final String LOCAL_GMS_USER_ID = "LOCAL_GMS_USER_ID";
    public static final String LOCAL_GMS_USER_ID_UNASSIGNED = "LOCAL_GMS_USER_ID_UNASSIGNED";
    public static final String LOCAL_DMS_USER_ID = "LOCAL_DMS_USER_ID";
    public static final String LOCAL_DMS_USER_ID_UNASSIGNED = "LOCAL_DMS_USER_ID_UNASSIGNED";
    
    public static final String READ_ICIS_INI_FILE = "READ_ICIS_INI_FILE";

    /**
     * Returns stored setting in NBPreferences
     * @param settingClass Java class for option panel
     * @param settingName setting name stored in NBPrefences
     * @param defaultSetting default setting value for property
     * @return
     */
    public static String getSetting(Class settingClass, String settingName, String defaultSetting) {
        Preferences preferences = NbPreferences.forModule(settingClass);
        String result = preferences.get(settingName, defaultSetting);
        return result;
    }

    /**
     * 
     * @param settingName
     * @param defaultSetting
     * @return
     */
    public static String getSetting(String settingName, String defaultSetting) {
        if (moduleOptionList.isEmpty()) {
            fillModuleOptionList();
        }
        Class settingClass = moduleOptionList.get(settingName);
        Preferences preferences = NbPreferences.forModule(settingClass);
        String result = preferences.get(settingName, defaultSetting);
        return result;
    }

    /**
     * 
     * @param settingName
     * @return
     */
    public static String getSetting(String settingName) {
        String defaultSetting = "";
        if (moduleOptionList.isEmpty()) {
            fillModuleOptionList();
        }
        Class settingClass = moduleOptionList.get(settingName);
        Preferences preferences = NbPreferences.forModule(settingClass);
        String result = preferences.get(settingName, defaultSetting);
        return result;
    }

    /**
     * 
     * @param settingName
     * @param settingValue
     */
    public static void setSetting(String settingName, String settingValue) {
        if (moduleOptionList.isEmpty()) {
            fillModuleOptionList();
        }
        Class settingClass = moduleOptionList.get(settingName);
        Preferences preferences = NbPreferences.forModule(settingClass);
        preferences.put(settingName, settingValue);

    }

    private static void fillModuleOptionList() {
        moduleOptionList.put(GERMPLASM_LIST_DEFAULT_FOLDER, TemplatesandListsFoldersPanel.class);
        moduleOptionList.put(TEMPLATES_DEFAULT_FOLDER, TemplatesandListsFoldersPanel.class);
        moduleOptionList.put(LOCAL_DMS_USER_ID, TemplatesandListsFoldersPanel.class);
        moduleOptionList.put(LOCAL_GMS_USER_ID, TemplatesandListsFoldersPanel.class);
        moduleOptionList.put(READ_ICIS_INI_FILE, TemplatesandListsFoldersPanel.class);
        moduleOptionList.put(CROSSES_DEFAULT_FOLDER, TemplatesandListsFoldersPanel.class);
        moduleOptionList.put(SELECTION_DEFAULT_FOLDER, TemplatesandListsFoldersPanel.class);

    }

    /**
     * Assigns User ID for GMS for local database
     * @param userId 
     */
    public static void setLocalGmsUserId(String userId) {
        setSetting(LOCAL_GMS_USER_ID, userId);
    }

    /**
     * Assigns User ID for DMS for local database
     * @param userId 
     */
    public static void setLocalDmsUserId(String userId) {
        setSetting(LOCAL_DMS_USER_ID, userId);
    }

    public static String getLocalGmsUserId() {
        String localGmsuser = getSetting(LOCAL_GMS_USER_ID);
        if (localGmsuser == null) {
            localGmsuser = LOCAL_GMS_USER_ID_UNASSIGNED;
        }
        return localGmsuser;
    }

    public static String getLocalDmsUserId() {
        String localDmsuser = getSetting(LOCAL_DMS_USER_ID);
        if (localDmsuser == null) {
            localDmsuser = LOCAL_DMS_USER_ID_UNASSIGNED;
        }
        return localDmsuser;
    }
    
    /**
     * Returns true if application must read icis ini file at startup
     */
    public static boolean readIcisIniFileAtStartup() {
        boolean readIcisIniFileAtStartup =  false;
        
        String readIcisIniFile = getSetting(READ_ICIS_INI_FILE);
        
        if (readIcisIniFile != null) {
           readIcisIniFileAtStartup = readIcisIniFile.equals("1");
        }
        
        return readIcisIniFileAtStartup;
    }
    
    /**
     * Returns default folder for crosses
     */
    public static String getCrossesDefaultFolder() {
        String crossesDefaultFolder = "";
        
        crossesDefaultFolder = getSetting(CROSSES_DEFAULT_FOLDER);
        
        if (crossesDefaultFolder == null) {
            crossesDefaultFolder= "";
        }
        
        return crossesDefaultFolder;
    }
    
    /**
     * Returns default folder for crosses
     */
    public static String getSelectionsDefaultFolder() {
        String crossesDefaultFolder = "";
        
        crossesDefaultFolder = getSetting(SELECTION_DEFAULT_FOLDER);
        
        if (crossesDefaultFolder == null) {
            crossesDefaultFolder= "";
        }
        
        return crossesDefaultFolder;
    }
    
}
