package ibfb.branding.core;

import ibfb.settings.core.FieldbookSettings;
import ibfb.studyexplorer.explorer.StudyExplorerTopComponent;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Frame;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.UIManager;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.db.options.DatabaseType;
import org.cimmyt.cril.ibwb.db.options.IBFbConfigFile;
import org.cimmyt.cril.ibwb.db.util.DatabaseInfo;
import org.cimmyt.cril.ibwb.domain.Instln;
import org.ini4j.Ini;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.modules.ModuleInstall;
import org.openide.util.Exceptions;
import org.openide.util.Mutex;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

public class Installer extends ModuleInstall {

    @Override
    public void restored() {


        UIManager.put("NbMainWindow.showCustomBackground", Boolean.TRUE);
        RootFrame.init();
        Runnable run = new Runnable() {

            @Override
            public void run() {
                ProgressHandle p = ProgressHandleFactory.createHandle("Loading data...");
                p.start();
                initialLoading();
                p.finish();
            }
        };
        
        Thread thread = new Thread(run);
        thread.start();

    }

    private void initialLoading() {
        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {

            @Override
            public void run() {
                changeCursorWaitStatus(true);
                
                AppServicesProxy.getDefault().appServices().readTypeDB();

                String osName = System.getProperty("os.name").toLowerCase();
                boolean isMacOs = osName.startsWith("mac os x");
                
                
                
                if (isMacOs) {
                } else {
                    importFromTempINIFile();
                }

                // Checks if traits tables exists, if not then creates
                if (!AppServicesProxy.getDefault().appServices().existsTratisTable()) {
                    AppServicesProxy.getDefault().appServices().createTraitsTables();
                }

                // Change bar title
                changeTitleBar();
                
                // look for logged userid

                TopComponent studyExplorer = WindowManager.getDefault().findTopComponent("StudyExplorerTopComponent");
                if (studyExplorer != null) {
                    ((StudyExplorerTopComponent) studyExplorer).refreshStudyBrowser();
                    studyExplorer.open();
                    studyExplorer.requestActive();
                } 
                else {
                    studyExplorer = new StudyExplorerTopComponent();
                    ((StudyExplorerTopComponent) studyExplorer).refreshStudyBrowser();                    
                    studyExplorer.open();
                    studyExplorer.requestActive();                    
                }

//                TopComponent studyEditor = WindowManager.getDefault().findTopComponent("StudyEditorTopComponent");
//                if (studyEditor != null && studyEditor.isOpened()) {
//                    studyEditor.close();
//                }

                TopComponent listNamesExplorer = WindowManager.getDefault().findTopComponent("ListNamesExplorerTopComponent");
                if (listNamesExplorer != null && listNamesExplorer.isOpened()) {
                    listNamesExplorer.close();
                }

                TopComponent listNamesData = WindowManager.getDefault().findTopComponent("ListDataWindowTopComponent");
                if (listNamesData != null && listNamesData.isOpened()) {
                    listNamesData.close();
                }


                TopComponent traitsExplorer = WindowManager.getDefault().findTopComponent("TraitsExplorerTopComponent");
                if (traitsExplorer != null && traitsExplorer.isOpened()) {
                    traitsExplorer.close();
                }


                TopComponent germplasmExplorer = WindowManager.getDefault().findTopComponent("addChecksTopComponent");
                if (germplasmExplorer != null && germplasmExplorer.isOpened()) {
                    germplasmExplorer.close();
                }
                
                 TopComponent advance = WindowManager.getDefault().findTopComponent("AdvanceLineTopComponent");
                if (advance != null && advance.isOpened()) {
                    advance.close();
                }
                        
                
                 TopComponent nurseryManagerExplorer = WindowManager.getDefault().findTopComponent("nurseryManagerTopComponent");
                if (nurseryManagerExplorer != null && nurseryManagerExplorer.isOpened()) {
                    nurseryManagerExplorer.close();
                }

                 TopComponent selectionTopComponent = WindowManager.getDefault().findTopComponent("nurserySelectionTopComponent");
                if (selectionTopComponent != null && selectionTopComponent.isOpened()) {
                    selectionTopComponent.close();
                }
                
                
                TopComponent background = WindowManager.getDefault().findTopComponent("BackgroundWindowTopComponent");
                if (!background.isOpened()) {
                    background.open();
                }
                Frame mainFrame = WindowManager.getDefault().getMainWindow();
                mainFrame.setExtendedState(mainFrame.getExtendedState()
                        | JFrame.MAXIMIZED_BOTH);

                changeCursorWaitStatus(false);
            }

        });

    }

    private void importFromTempINIFile() {

        if (! FieldbookSettings.readIcisIniFileAtStartup()) {
            return;
        }
        
        String path = System.getProperty("java.io.tmpdir");
        path = path + "ICIS.ini";
        File INIFile = new File(path);
        if (!INIFile.exists()) {
            return;
        }

        try {
            Ini iniFile = new Ini();
            iniFile.load(INIFile);

            Ini.Section sectionDMSCentral = iniFile.get("Central DMS");
            String dmsCentral = sectionDMSCentral.get("DSN");

            Ini.Section sectionDMSLocal = iniFile.get("Local DMS");
            String dmsLocal = sectionDMSLocal.get("DSN");
            String dmsLocalUserId = "UID";

            Ini.Section sectionGMSCentral = iniFile.get("Central GMS");
            String gmsCentral = sectionGMSCentral.get("DSN");

            Ini.Section sectionGMSLocal = iniFile.get("Local GMS");
            String gmsLocal = sectionGMSLocal.get("DSN");
            String gmsLocalUserId = "UID";

            Ini.Section sectionIBFIELDBOOK = iniFile.get("IBFIELDBOOK");
            if (sectionIBFIELDBOOK != null) {
                String templates = sectionIBFIELDBOOK.get("TEMPLATE");
                String germplasm = sectionIBFIELDBOOK.get("GERMPLASM");
                FieldbookSettings.setSetting(FieldbookSettings.TEMPLATES_DEFAULT_FOLDER, templates);
                FieldbookSettings.setSetting(FieldbookSettings.GERMPLASM_LIST_DEFAULT_FOLDER, germplasm);
            }
            updateConfigFile(dmsCentral, gmsCentral, dmsLocal, gmsLocal);

            // Asign userid for local database
            FieldbookSettings.setLocalGmsUserId(gmsLocalUserId);
            FieldbookSettings.setLocalDmsUserId(dmsLocalUserId);
            
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

    }

    private void updateConfigFile(String dmsCentral, String gmsCentral, String dmsLocal, String gmsLocal) {
        DatabaseInfo dmsInfoCentral = new DatabaseInfo(DatabaseType.ACCESS);
        dmsInfoCentral.setDatabaseName(dmsCentral);

        DatabaseInfo gmsInfoCentral = new DatabaseInfo(DatabaseType.ACCESS);
        gmsInfoCentral.setDatabaseName(gmsCentral);

        DatabaseInfo dmsInfoLocal = new DatabaseInfo(DatabaseType.ACCESS);
        dmsInfoLocal.setDatabaseName(dmsLocal);

        DatabaseInfo gmsInfoLocal = new DatabaseInfo(DatabaseType.ACCESS);
        gmsInfoLocal.setDatabaseName(gmsLocal);

        IBFbConfigFile.saveConfigFile(dmsInfoCentral, gmsInfoCentral, dmsInfoLocal, gmsInfoLocal);
    }

    private void changeTitleBar() {
        final Instln instln = AppServicesProxy.getDefault().appServices().getLocalInstln();
        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {

            @Override
            public void run() {
                JFrame frame = (JFrame) WindowManager.getDefault().getMainWindow();
                String title = frame.getTitle() + " " + instln.getIdesc();
                frame.setTitle(title);
            }
        });
    }

    private static void changeCursorWaitStatus(final boolean isWaiting) {
        Mutex.EVENT.writeAccess(new Runnable() {

            @Override
            public void run() {
                try {
                    JFrame mainFrame =
                            (JFrame) WindowManager.getDefault().getMainWindow();
                    Component glassPane = mainFrame.getGlassPane();
                    if (isWaiting) {
                        glassPane.setVisible(true);

                        glassPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    } else {
                        glassPane.setVisible(false);

                        glassPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    }
                } catch (Exception e) {
                }
            }
        });
    }
}
