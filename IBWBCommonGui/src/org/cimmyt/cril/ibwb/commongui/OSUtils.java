package org.cimmyt.cril.ibwb.commongui;

import java.io.File;
import org.openide.modules.InstalledFileLocator;

/**
 *
 * @author TMSANCHEZ
 */
public class OSUtils {

    private static final String MODULE_NAME = "ibfb.studyeditor.core";
    /**DIFF**/
    private static final String RESOURCE_TO_FIND = "modules/ext/jcalendar-1.3.3.jar";
    /**DIFF**/
    private static final String MAC_OS_X = "mac os x";
    private static final String OS_NAME = "os.name";
    private static final String IBFB_PATH_NAME = "IBFb";
    private static final String TEMPLATES_PATH = File.separator + "Examples" + File.separator + "Templates";
    private static final String DOCUMENTS_PATH = File.separator + "Documents";
    private static final String GERMPLASM_LISTS_PATH = File.separator + "Examples" + File.separator + "GermplasmLists";
    private static final String CROSSES_PATH = File.separator + "Examples" + File.separator + "IBFieldbookImportCrossSelection";
    private static final String SELECTIONS_PATH = File.separator + "Examples" + File.separator + "IBFieldbookImportCrossSelection";    
    public static String TEMPLATES_INI_PATH = "";
    public static String GERMPLASM_LISTS_INI_PATH = "";
    private static final String R_BIN_PATH = File.separator + "R-2.11.1";
    private static final String R_SAMPLES_PATH = File.separator + "Examples" + File.separator + "AnalysisR";
    private static final String MAC_DOCUMENTS_PATH = "Documents";
    private static final String MAC_USER_HOME = System.getProperty("user.home");
    public static final String MAC_RWD = "DataR";
    private static final String R_SCRIPT_PATH = File.separator + "IBFb" + File.separator + "IBFbR" + File.separator + "src" + File.separator + "ibfb" + File.separator + "r" + File.separator + "scripts";

    /**
     * Checks if current Operating System is Mac
     * @return <code>true</code> if is Mac, <code>false</code> if not
     */
    public static boolean isMacOS() {
        String osName = System.getProperty(OS_NAME).toLowerCase();
        boolean isMacOs = osName.startsWith(MAC_OS_X);
        return isMacOs;
    }

    public static String getGERMPLASM_LISTS_INI_PATH() {
        return GERMPLASM_LISTS_INI_PATH;
    }
    
    public static void setGERMPLASM_LISTS_INI_PATH(String GERMPLASM_LISTS_INI_PATH) {
        OSUtils.GERMPLASM_LISTS_INI_PATH = GERMPLASM_LISTS_INI_PATH;
    }

    public static String getTEMPLATES_INI_PATH() {
        return TEMPLATES_INI_PATH;
    }

    public static void setTEMPLATES_INI_PATH(String TEMPLATES_INI_PATH) {
        OSUtils.TEMPLATES_INI_PATH = TEMPLATES_INI_PATH;
    }

    /**
     * Get the Path where IBFieldbook is installed
     * @return a String with the current path or empty String if can not find install path
     */
    public static String getIBFBPath() {
        String ifbPath = "";
        String ifbPathName = File.separator + IBFB_PATH_NAME + File.separator;

        InstalledFileLocator ifl = InstalledFileLocator.getDefault();
        File tempFile = ifl.locate(RESOURCE_TO_FIND, MODULE_NAME, false);

        if (tempFile != null) {
            String temporalPath = tempFile.getParent();
            int ifbPathIndex = temporalPath.indexOf(ifbPathName);
            ifbPath = temporalPath.substring(0, ifbPathIndex);
        }

        return ifbPath;

    }

    /**
     * 
     */
    public static String getTemplatesPath() {
        if (getTEMPLATES_INI_PATH().isEmpty()) {
            return getIBFBPath() + TEMPLATES_PATH;
        } else {
            return getTEMPLATES_INI_PATH();
        }
    }

    public static String getDocumentsPath() {
        if (isMacOS()) {
            return MAC_USER_HOME + File.separator + MAC_DOCUMENTS_PATH;
        } else {
            if (getTEMPLATES_INI_PATH().isEmpty()) {
                return getIBFBPath() + DOCUMENTS_PATH;
            } else {
                return getTEMPLATES_INI_PATH();
            }
        }
    }

    /**
     * 
     * @return 
     */
    public static String getGermplasmListsPath() {
        if (getGERMPLASM_LISTS_INI_PATH().isEmpty()) {
            return getIBFBPath() + GERMPLASM_LISTS_PATH;
        } else {
            return getGERMPLASM_LISTS_INI_PATH();
        }
    }
    
    public static String getCrossesFilesPath() {
        if (getGERMPLASM_LISTS_INI_PATH().isEmpty()) {
            return getIBFBPath() + CROSSES_PATH;
        } else {
            return getIBFBPath() + CROSSES_PATH;
        }
    }    
    
    public static String getSelectionsFilesPath() {
        if (getGERMPLASM_LISTS_INI_PATH().isEmpty()) {
            return getIBFBPath() + SELECTIONS_PATH;
        } else {
            return getIBFBPath() + SELECTIONS_PATH;
        }
    }    
    

    /**
     * copied from studyeditor and R modules OSUtils
     */
    public static String getRPATH() {
        return getIBFBPath() + R_BIN_PATH;
    }

    public static String getRSamplesPath() {
        return getIBFBPath() + R_SAMPLES_PATH;
    }

    public static String getPathRWD() {
        if (isMacOS()) {
            String rWorkingDirectory = OSUtils.getDocumentsPath() + File.separator + MAC_RWD;
            File rWD = new File(rWorkingDirectory);
            if (!rWD.exists()) {
                new File(rWorkingDirectory).mkdir();
            }
            return rWorkingDirectory;
        } else {
            return getRSamplesPath();
        }
    }

    public static String getRScriptPath() {
        return getIBFBPath() + R_SCRIPT_PATH; //"/IBFbR/src/ibfb/r/scripts";\
    }

    public static boolean existDirectory(String directoryToCheck) {
        boolean existDir = false;
        File directory = new File(directoryToCheck);
        if (directory.exists() && directory.isDirectory()) {
            existDir = true;
        }
        return existDir;
    }

    public static boolean existFile(String directory, String fileName) {
        boolean existF = false;
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            existF = true;
        }
        return existF;

    }

    public static boolean createDirectory(String path, String newDirectory) {
        boolean dirCreated = false;
        if (existDirectory(path + File.separator + newDirectory)) {
            dirCreated = true;
        } else if (existDirectory(path)) {
            File dir = new File(path + File.separator + newDirectory);
            dirCreated = dir.mkdir();
        }

        return dirCreated;
    }

    /**
     * Get the Path where IBFieldbook is installed
     * @return a String with the current path or empty String if can not find install path
     */
    public static String extractFilePath(String fullFilePath) {
        String filePath = "";

        int ifbPathIndex = fullFilePath.lastIndexOf(File.separator);
        filePath = fullFilePath.substring(0, ifbPathIndex+1);


        return filePath;

    }
}
