package ibfb.studyexplorer.actions;

import ibfb.domain.core.Factor;
import ibfb.domain.core.SelectedStudy;
import ibfb.domain.core.Variate;
import ibfb.domain.core.Workbook;
import ibfb.studyeditor.core.StudyEditorTopComponent;
import ibfb.studyeditor.roweditors.ConditionsRowEditor;
import ibfb.studyexplorer.jdialogs.JDNewOptions;
import ibfb.ui.core.JDExpert;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import javax.swing.table.TableColumn;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.ConvertUtils;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.Exceptions;
import org.openide.util.HelpCtx;
import org.openide.util.Mutex;
import org.openide.util.NbBundle;
import org.openide.util.actions.SystemAction;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

public class OpenStudyAction extends SystemAction {

    private StudyEditorTopComponent studyWindow = null;
    private ResourceBundle bundle = NbBundle.getBundle(OpenStudyAction.class);

    public OpenStudyAction() {
        putValue(NAME,bundle.getString("OpenStudyAction.name"));
        setEnabled(Boolean.TRUE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
          if (existeTrial(SelectedStudy.selected.getStudy())) {
                return;
                }

        final ProgressHandle handle = ProgressHandleFactory.createHandle(bundle.getString("OpenStudyAction.open"));
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
                        String msgSaving = bundle.getString("OpenStudyAction.cannot");

                        NotifyDescriptor d = new NotifyDescriptor.Message(msgSaving, NotifyDescriptor.ERROR_MESSAGE);
                        DialogDisplayer.getDefault().notify(d);

                    } else {
                        studyWindow = new StudyEditorTopComponent();
                        studyWindow.setStudy(workbook.getStudy());
                        studyWindow.getjTextTrialName().setText(workbook.getStudy().getStudy());
                        studyWindow.setStudyAlreadyExists(true);
                        fillStudyData(studyWindow, workbook.getStudy());
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
                        DialogUtil.displayInfo(bundle.getString("OpenStudyAction.opened"));
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

    @Override
    public String getName() {
        return bundle.getString("OpenStudyAction.trial");
    }

    @Override
    public HelpCtx getHelpCtx() {
        return null;
    }

    private void fillStudyData(StudyEditorTopComponent studyWindow, ibfb.domain.core.Study study) {
//        studyWindow.setName(JDExpert.studyOBJ.getStudy());
//        studyWindow.jTextFieldStudy.setText(JDExpert.studyOBJ.getStudy());
//        studyWindow.jTextFieldObjective.setText(JDExpert.studyOBJ.getObjective());
//        studyWindow.jTextFieldTitle.setText(JDExpert.studyOBJ.getTitle());
//        Date start = JDExpert.studyOBJ.getStarDate();
//        Date end = JDExpert.studyOBJ.getEndDate();
        studyWindow.setName(study.getStudy());
        studyWindow.jTextFieldStudy.setText(study.getStudy());
        studyWindow.jTextFieldObjective.setText(study.getObjective());
        studyWindow.jTextFieldTitle.setText(study.getTitle());
        Date start = study.getStarDate();
        Date end = study.getEndDate();
        
        String formato = ConvertUtils.DATE_PATTERN; //"dd-MMM-yyyy";
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

    private void assignCellEditor(StudyEditorTopComponent studyWindow) {
        ConditionsRowEditor conditionsRowEditor = new ConditionsRowEditor(studyWindow.jTableStudyConditions);
        TableColumn valueColumn = studyWindow.jTableStudyConditions.getColumnModel().getColumn(4);
        valueColumn.setCellEditor(conditionsRowEditor);
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
//
//    @SuppressWarnings("unchecked")
//    private void fillConstants(StudyEditorTopComponent studyWindow, Workbook workbook) {
//
//        DefaultTableModel modeloTablaConstants = (DefaultTableModel) studyWindow.jTableConstants.getModel();
//        java.util.ArrayList<Constant> trialConstants = new ArrayList();
//
//        try {
//            // constants = (java.util.ArrayList<Constant>) workbook.getConstants();
//            trialConstants = (java.util.ArrayList<Constant>) workbook.getConstants();
//        } catch (NullPointerException ex) {
//            return;
//        }
//        if (trialConstants == null) {
//            return;
//        }
//
//        modeloTablaConstants.setRowCount(0);
//        modeloTablaConstants.setRowCount(trialConstants.size() * JDNewOptions.studyOBJ.getInstances());
//
//        if (trialConstants.size() >= 0) {
//
//            int instance = 0;
//            int renglon = 0;
//
//            for (int j = 0; j < JDNewOptions.studyOBJ.getInstances(); j++) {
//
//
//                for (int i = 0; i < trialConstants.size(); i++) {
//
//                    studyWindow.jTableConstants.setValueAt(instance + 1, renglon, 0);
//                    studyWindow.jTableConstants.setValueAt(trialConstants.get(i).getConstantName(), renglon, 1);
//                    studyWindow.jTableConstants.setValueAt(trialConstants.get(i).getDescription(), renglon, 2);
//                    studyWindow.jTableConstants.setValueAt(trialConstants.get(i).getScale(), renglon, 3);
//                    renglon++;
//                }
//
//                instance++;
//            }
//        }
//    }

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
        studyWindow.setSelectedTraits(workbook.getVariates());
    }
    
      public boolean existeTrial(String trial) {
        boolean existe = false;
        try {
            ArrayList<TopComponent> opened = new ArrayList<TopComponent>(WindowManager.getDefault().getRegistry().getOpened());

            for (TopComponent t : opened) {

                if (t.getName().equals(trial)) {
                    existe = true;
                }
            }
        } catch (NullPointerException ex) {
            existe = false;
        }

        return existe;
    }
}
