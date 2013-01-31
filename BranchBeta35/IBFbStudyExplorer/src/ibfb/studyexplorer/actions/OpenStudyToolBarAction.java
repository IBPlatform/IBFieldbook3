package ibfb.studyexplorer.actions;

import ibfb.domain.core.Factor;
import ibfb.domain.core.Study;
import ibfb.domain.core.Variate;
import ibfb.domain.core.Workbook;
import ibfb.studyeditor.core.StudyEditorTopComponent;
import ibfb.studyexplorer.core.nodes.StudyExperimentNode;
import ibfb.studyexplorer.explorer.StudyExplorerTopComponent;
import ibfb.studyexplorer.jdialogs.JDNewOptions;
import ibfb.ui.core.JDExpert;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.Exceptions;
import org.openide.util.Mutex;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

@ActionID(category = "File",
id = "ibfb.studyexplorer.actions.OpenStudyToolBarAction")
@ActionRegistration(iconBase = "ibfb/studyexplorer/images/open_16.png",
displayName = "#CTL_OpenStudyToolBarAction")
@ActionReferences({
    @ActionReference(path = "Menu/Study", position = 1225),
    @ActionReference(path = "Toolbars/File", position = -350)
})

public final class OpenStudyToolBarAction implements ActionListener {

    public static Study studyOBJ = new Study();
    private final Study context;
    private StudyEditorTopComponent studyWindow = null;

    public OpenStudyToolBarAction(Study context) {
        this.context = context;
    }
    
    private boolean isTrialOrNursery(StudyExplorerTopComponent explorerTopComponent) {
        Object selectedNode = explorerTopComponent.getSelectedNode();
        return (selectedNode instanceof StudyExperimentNode);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {

        TopComponent topComponent = WindowManager.getDefault().findTopComponent("StudyExplorerTopComponent");

        StudyExplorerTopComponent explorerTopComponent = null;

        // if found, then cast it as StudyExplorerListener, then refresh it
        if (topComponent != null) {
            explorerTopComponent = (StudyExplorerTopComponent) topComponent;

        }

        if (explorerTopComponent != null && isTrialOrNursery(explorerTopComponent)) {

            final ProgressHandle handle = ProgressHandleFactory.createHandle("Opening Fieldbook... ");
            handle.start();
            (new SwingWorker<String, Object>() {

                Workbook workbook = null;

                @Override
                protected String doInBackground() throws Exception {

                    //changeCursorWaitStatus(true);
                    try {
                        workbook = AppServicesProxy.getDefault().appServices().getWorkbookFull(new Integer(JDNewOptions.studyOBJ.getStudyid()), false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    return "";
                }

                @Override
                protected void done() {
                    super.done();
                    try {
                        String valor = get();
                        if (workbook == null) {
                            changeCursorWaitStatus(false);
                            String msgSaving = "Cannot open selected study";
                            DialogUtil.displayError(msgSaving);

                        } else {
                            studyWindow = new StudyEditorTopComponent();
                            studyWindow.setStudy(workbook.getStudy());
                            studyWindow.getjTextTrialName().setText(workbook.getStudy().getStudy());
                            studyWindow.setStudyAlreadyExists(true);
                            fillStudyData(studyWindow);
                            studyWindow.assignStudyConditions(workbook.getStudyConditions());
                            //fillStudyConditions(studyWindow, workbook);
                            fillFactores(studyWindow, workbook);
                            // fillConstants(studyWindow, workbook);

                            fillTraits(studyWindow, workbook);

                            studyWindow.assignTrialConditions(workbook.getConditionsData());

                            studyWindow.assignGermplasmEntries(workbook.getEntryFactors(), workbook.getGermplasmData());
                            studyWindow.assignExperimentalConditions(workbook.getConstants());
                            studyWindow.loadDataFromDB();


                            closeBackground();
                            inhabilitaTabs(studyWindow);
                            //studyWindow.loadDataFromCsv();
                            studyWindow.open();
                            studyWindow.requestActive();
                            // changeCursorWaitStatus(false);
                            DialogUtil.displayInfo("Fieldbook was opened succesfully");
                        }
                    } catch (InterruptedException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (ExecutionException ex) {
                        Exceptions.printStackTrace(ex);
                    } finally {
                        // close the progress handler
                        handle.finish();
                    }
                }
            }).execute();


        }


    }

    private void fillStudyData(StudyEditorTopComponent studyWindow) {
        studyWindow.setName(JDExpert.studyOBJ.getStudy());
        studyWindow.jTextFieldStudy.setText(JDExpert.studyOBJ.getStudy());
        studyWindow.jTextFieldObjective.setText(JDExpert.studyOBJ.getObjective());
        studyWindow.jTextFieldTitle.setText(JDExpert.studyOBJ.getTitle());
        Date start = JDExpert.studyOBJ.getStarDate();
        Date end = JDExpert.studyOBJ.getEndDate();
        String formato = "dd-MMM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(formato);

        try {
            studyWindow.jDateChooserStart.setDate(start);
        } catch (NullPointerException ex) {
        }

        try {
            studyWindow.jDateChooserEnd.setDate(end);
        } catch (NullPointerException ex) {
        }
        try {
            studyWindow.jTextFieldPMKey.setText(JDExpert.studyOBJ.getPmkey());
        } catch (NullPointerException ex) {
        }
        try {
            studyWindow.jComboBoxStudyType.setSelectedItem(JDExpert.studyOBJ.getStudyType());
        } catch (NullPointerException ex) {
            studyWindow.jComboBoxStudyType.setSelectedItem(0);
        }


    }

    @SuppressWarnings("unchecked")
    private void fillFactores(StudyEditorTopComponent studyWindow, Workbook workbook) {
//        DefaultTableModel modeloTabla = new DefaultTableModel();
//        modeloTabla = (DefaultTableModel) studyWindow.jTableOtherFactorLabels.getModel();
//        java.util.ArrayList<Factor> factores = new ArrayList();
//        factores = (java.util.ArrayList<Factor>) workbook.getOtherFactors();
//
//        if (this.existenFactores(workbook)) {
//            modeloTabla.setNumRows(workbook.getOtherFactors().size());
//        } else {
//            modeloTabla.setNumRows(0);
//            return;
//        }
//
//        int renglon = 0;
//        for (int i = 0; i < factores.size(); i++) {
//
//            studyWindow.jTableOtherFactorLabels.setValueAt(factores.get(i).getFactorName(), renglon, 0);
//            studyWindow.jTableOtherFactorLabels.setValueAt(factores.get(i).getDescription(), renglon, 1);
//            studyWindow.jTableOtherFactorLabels.setValueAt(factores.get(i).getScale(), renglon, 2);
//            studyWindow.jTableOtherFactorLabels.setValueAt(1, renglon, 3);
//            renglon++;
//        }
    }

    @SuppressWarnings("unchecked")
    public boolean existenFactores(Workbook workbook) {

        boolean existenFactores = false;

        java.util.ArrayList<Factor> factores = new ArrayList();
        try {
            factores = (java.util.ArrayList<Factor>) workbook.getOtherFactors();
        } catch (NullPointerException ex) {
            return false;
        }
        if (factores == null) {
            return false;
        }
        if (factores.isEmpty()) {
            return false;
        }
        if (factores.size() > 0) {
            existenFactores = true;
        }

        return existenFactores;
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

    private void inhabilitaTabs(StudyEditorTopComponent studyWindow) {
        //studyWindow.jTabbedPane1.setEnabledAt(7, false);
        //studyWindow.jTabbedPane1.setEnabledAt(6, false);
        studyWindow.disableTraitsSelection();
        studyWindow.jTabbedPaneEditor.setEnabledAt(5, false);
        studyWindow.jTabbedPaneEditor.setEnabledAt(4, false);
        //studyWindow.jTabbedPane1.setEnabledAt(3, false);
    }

    private void closeBackground() {
        TopComponent background = WindowManager.getDefault().findTopComponent("BackgroundWindowTopComponent");
        if (background.isOpened()) {
            background.close();
        }
    }

    private void fillTraits(StudyEditorTopComponent studyWindow, Workbook workbook) {
        studyWindow.assignTraits(new ArrayList<Variate>(), workbook.getVariates());
        studyWindow.setMyWorkbook(workbook);
        //studyWindow.configMyList();
    }
}
