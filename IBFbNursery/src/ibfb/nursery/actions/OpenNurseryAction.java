package ibfb.nursery.actions;


import ibfb.domain.core.Factor;
import ibfb.domain.core.SelectedStudy;
import ibfb.domain.core.Variate;
import ibfb.domain.core.Workbook;
import ibfb.nursery.core.NurseryEditorTopComponent;
import ibfb.nursery.editors.ConditionsRowEditor;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import javax.swing.table.TableColumn;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.Exceptions;
import org.openide.util.HelpCtx;
import org.openide.util.Mutex;
import org.openide.util.actions.SystemAction;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

public class OpenNurseryAction extends SystemAction {

    private NurseryEditorTopComponent nurseryWindow = null;

    public OpenNurseryAction() {
        putValue(NAME, "Open nursery");
        setEnabled(Boolean.TRUE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

          if (existeNursery(SelectedStudy.selected.getStudy())) {
                return;
                }
 
        final ProgressHandle handle = ProgressHandleFactory.createHandle("Opening Nursery... ");
        handle.start();
        (new SwingWorker<String, Object>() {

            Workbook workbook = null;

            @Override
            protected String doInBackground() throws Exception {               
                try {
                    changeCursorWaitStatus(true);
                    workbook = AppServicesProxy.getDefault().appServices().getWorkbookFull(new Integer(SelectedStudy.selected.getStudyid()), false);
                    changeCursorWaitStatus(false);
                } catch (Exception e) {
                    System.out.println("ERROR: "+e);
                    changeCursorWaitStatus(false);
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

                        NotifyDescriptor d = new NotifyDescriptor.Message(msgSaving, NotifyDescriptor.ERROR_MESSAGE);
                        DialogDisplayer.getDefault().notify(d);

                    } else {
                         changeCursorWaitStatus(true);
                        nurseryWindow = new NurseryEditorTopComponent();
                        nurseryWindow.setStudy(workbook.getStudy());
                        nurseryWindow.getjTextFieldNurseryName().setText(workbook.getStudy().getStudy());
                        nurseryWindow.setName(workbook.getStudy().getStudy());
                        nurseryWindow.setStudyAlreadyExists(true);
                        fillStudyData(nurseryWindow);
                        nurseryWindow.assignStudyConditions(workbook.getStudyConditions());
                        //fillStudyConditions(studyWindow, workbook);
                      
                        // fillConstants(studyWindow, workbook);

                        fillTraits(nurseryWindow, workbook);

                        nurseryWindow.assignNurseryConditions(workbook.getConditionsData());

                        nurseryWindow.assignGermplasmEntries(workbook.getEntryFactors(), workbook.getGermplasmData());
                        nurseryWindow.assignExperimentalConditions(workbook.getConstants());
                        nurseryWindow.loadDataFromDB();


                        closeBackground();
                        inhabilitaTabs(nurseryWindow);
                        //studyWindow.loadDataFromCsv();
                        nurseryWindow.open();
                        nurseryWindow.requestActive();
                        // changeCursorWaitStatus(false);
                         changeCursorWaitStatus(false);
                        DialogUtil.displayInfo("Nursery was opened succesfully");
                        
                    }
                    
                    
                    
                } catch (InterruptedException ex) {
                    Exceptions.printStackTrace(ex);
                      changeCursorWaitStatus(false);
                } catch (ExecutionException ex) {
                    Exceptions.printStackTrace(ex);
                      changeCursorWaitStatus(false);
                } finally {
                    // close the progress handler
                    handle.finish();
                      changeCursorWaitStatus(false);
                }
            }
        }).execute();
    }

    @Override
    public String getName() {
        return "Open";
    }

    @Override
    public HelpCtx getHelpCtx() {
        return null;
    }

    private void fillStudyData(NurseryEditorTopComponent nurseryWindow) {
        nurseryWindow.setName(SelectedStudy.selected.getStudy());
        nurseryWindow.jTextFieldStudy.setText(SelectedStudy.selected.getStudy());
        nurseryWindow.jTextFieldObjective.setText(SelectedStudy.selected.getStudy());
        nurseryWindow.jTextFieldTitle.setText(SelectedStudy.selected.getStudy());
        Date start = SelectedStudy.selected.getStarDate();
        Date end = SelectedStudy.selected.getEndDate();
        String formato = "dd-MMM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(formato);

        try {
           // nurseryWindow.jDateChooserStart.setText(sdf.format(start));
        } catch (NullPointerException ex) {
        }

        try {
           // nurseryWindow.jDateChooserEnd.setText(sdf.format(end));
        } catch (NullPointerException ex) {
        }
        try {
            nurseryWindow.jTextFieldPMKey.setText(SelectedStudy.selected.getPmkey());
        } catch (NullPointerException ex) {
        }
        try {
            nurseryWindow.jComboBoxStudyType.setSelectedItem(SelectedStudy.selected.getStudyType());
        } catch (NullPointerException ex) {
            nurseryWindow.jComboBoxStudyType.setSelectedItem(0);
        }


    }

    private void assignCellEditor(NurseryEditorTopComponent nurseryWindow) {
        ConditionsRowEditor conditionsRowEditor = new ConditionsRowEditor(nurseryWindow.jTableStudyConditions);
        TableColumn valueColumn = nurseryWindow.jTableStudyConditions.getColumnModel().getColumn(4);
        valueColumn.setCellEditor(conditionsRowEditor);
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

    private void inhabilitaTabs(NurseryEditorTopComponent nurseryWindow) {
        //studyWindow.jTabbedPane1.setEnabledAt(7, false);
        //studyWindow.jTabbedPane1.setEnabledAt(6, false);
        nurseryWindow.disableTraitsSelection();
     //   nurseryWindow.jTabbedPane1.setEnabledAt(5, false);
      //  nurseryWindow.jTabbedPane1.setEnabledAt(4, false);
        //nurseryWindow.jTabbedPane1.setEnabledAt(3, false);
    }

    private void closeBackground() {
        TopComponent background = WindowManager.getDefault().findTopComponent("BackgroundWindowTopComponent");
        if (background.isOpened()) {
            background.close();
        }
    }

    private void fillTraits(NurseryEditorTopComponent nurseryWindow, Workbook workbook) {
        nurseryWindow.assignTraits(new ArrayList<Variate>(), workbook.getVariates());
        nurseryWindow.setMyWorkbook(workbook);
    }
    
    public boolean existeNursery(String nursery) {
        boolean existe = false;
        try {
            ArrayList<TopComponent> opened = new ArrayList<TopComponent>(WindowManager.getDefault().getRegistry().getOpened());
            for (TopComponent t : opened) {
                if (t.getName().equals(nursery)) {
                    existe = true;
                }
            }
        } catch (NullPointerException ex) {
            existe = false;
        }
        return existe;
    }
}
