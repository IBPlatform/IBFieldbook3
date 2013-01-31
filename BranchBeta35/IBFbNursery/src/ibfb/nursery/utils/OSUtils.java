package ibfb.nursery.utils;

import java.io.File;
import org.openide.modules.InstalledFileLocator;

public class OSUtils {

    private static final String MODULE_NAME = "ibfb.nursery.core";
    private static final String RESOURCE_TO_FIND = "modules/ext/jcalendar-1.3.3.jar";
    private static final String MAC_OS_X = "mac os x";
    private static final String OS_NAME = "os.name";
    private static final String IBFB_PATH_NAME = "IBFb";
    private static final String TEMPLATES_PATH = File.separator + "Examples" + File.separator + "Templates";
    private static final String GERMPLASM_LISTS_PATH = File.separator + "Examples" + File.separator + "GermplasmLists";
    private static final String R_BIN_PATH = File.separator + "R-2.11.1";
    private static final String R_SAMPLES_PATH = File.separator + "Examples" + File.separator + "AnalysisR";

    public static boolean isMacOS() {
        String osName = System.getProperty(OS_NAME).toLowerCase();
        boolean isMacOs = osName.startsWith(MAC_OS_X);
        return isMacOs;
    }

   
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

    public static String getTemplatesPath() {
        return getIBFBPath() + TEMPLATES_PATH;
    }

    public static String getGermplasmListsPath() {
        return getIBFBPath() + GERMPLASM_LISTS_PATH;
    }

    public static String getRPATH() {
        return getIBFBPath() + R_BIN_PATH;
    }

    public static String getRSamplesPath() {
        return getIBFBPath() + R_SAMPLES_PATH;
    }
}
