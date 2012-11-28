
package ibfb.studyexplorer.actions;

import ibfb.domain.core.SelectedStudy;
import ibfb.domain.core.Study;
import ibfb.studyeditor.core.StudyEditorTopComponent;
import ibfb.studyeditor.wizard.TrialWizardWizardIterator;
import ibfb.studyexplorer.jdialogs.JDNewOptions;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.WizardDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.Mutex;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

@ActionID(category = "File",
id = "ibfb.studyexplorer.actions.NewTrialPanelAction")
@ActionRegistration(iconBase = "ibfb/studyexplorer/images/newTrial16.png",
displayName = "#CTL_NewTrialPanelAction")
@ActionReferences({
    @ActionReference(path = "Menu/Study", position = 1231),
    @ActionReference(path = "Toolbars/File", position = -318)
})
public final class NewTrialPanelAction implements ActionListener {

    private final Study context;

    public NewTrialPanelAction(Study context) {
        this.context = context;
    }


    @Override
    public void actionPerformed(ActionEvent ev) {
      
        
        
        
        JPTrialOptions myPanel = new JPTrialOptions();
        
       
        NotifyDescriptor nd = new NotifyDescriptor(
                myPanel, 
                "IBFIELDBOOK - Trial", 
                NotifyDescriptor.OK_CANCEL_OPTION, 
                NotifyDescriptor.QUESTION_MESSAGE, 
                null,                     
                     
                NotifyDescriptor.OK_OPTION // default option is "Yes"
        );

        
        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
          
            
            switch (myPanel.getOption()) {
                   case 0:
                    runWizard();
                    break;
                    
                    case 1:
                    runQuickCreation();
                     break;  
                
            }
            
            
        } 
        
    }
    
    
    private void runQuickCreation(){
        changeCursorWaitStatus(true);
        TopComponent background = WindowManager.getDefault().findTopComponent("BackgroundWindowTopComponent");
        if (background.isOpened()) {
            background.close();
        }
        if (existeTrial(JDNewOptions.studyOBJ.getStudy())) {
            int opcion = JOptionPane.showConfirmDialog(null, "TRIAL ALREADY GENERATED. Do you want to overwrite it?", "Caution!", JOptionPane.YES_NO_OPTION);
            if (opcion == 0) {
                StudyEditorTopComponent studyEditor = null;
                ArrayList<TopComponent> opened = new ArrayList<TopComponent>(WindowManager.getDefault().getRegistry().getOpened());
                for (TopComponent t : opened) {
                    if (t.getName().equals(JDNewOptions.studyOBJ.getStudy())) {
                        studyEditor = (StudyEditorTopComponent) t;
                        studyEditor.close();
                    }
                }
                studyEditor.setStudy(JDNewOptions.studyOBJ);
                JDNewOptions.expertForm.setStudyWindow(studyEditor);
                JDNewOptions.expertForm.cleanFields();
                JDNewOptions.expertForm.setLocationRelativeTo(null);
                JDNewOptions.expertForm.setVisible(true);
            }

        } else {
            StudyEditorTopComponent studyEditor = new StudyEditorTopComponent();
            studyEditor.setStudy(JDNewOptions.studyOBJ);
            studyEditor.setName(JDNewOptions.studyOBJ.getStudy());
            JDNewOptions.expertForm.setStudyWindow(studyEditor);
            JDNewOptions.expertForm.cleanFields();
            JDNewOptions.expertForm.setLocationRelativeTo(null);
            JDNewOptions.expertForm.setVisible(true);
        }
        changeCursorWaitStatus(false);
    }
    
    private void runWizard(){
         changeCursorWaitStatus(true);
        if (existeTrial(SelectedStudy.selected.getStudy())) {
            int opcion = JOptionPane.showConfirmDialog(null, "TRIAL ALREADY GENERATED. Do you want to overwrite it?", "Caution!", JOptionPane.YES_NO_OPTION);
            if (opcion == 0) {
                StudyEditorTopComponent studyEditor = null;
                ArrayList<TopComponent> opened = new ArrayList<TopComponent>(WindowManager.getDefault().getRegistry().getOpened());
                for (TopComponent t : opened) {
                    if (t.getName().equals(SelectedStudy.selected.getStudy())) {
                        studyEditor = (StudyEditorTopComponent) t;
                        studyEditor.close();
                    }
                }
                launchWizard(studyEditor);
            }

        } else {

            StudyEditorTopComponent studyEditor = new StudyEditorTopComponent();
            studyEditor.setName(SelectedStudy.selected.getStudy());
            studyEditor.setStudy(SelectedStudy.selected);
            launchWizard(studyEditor);

        }
        changeCursorWaitStatus(false);
    }
    
    @SuppressWarnings("unchecked")
    private void launchWizard(StudyEditorTopComponent studyEditor) {


        TopComponent background = WindowManager.getDefault().findTopComponent("BackgroundWindowTopComponent");
        if (background.isOpened()) {
            background.close();
        }

        TrialWizardWizardIterator.studyTopComponent = studyEditor;
        TrialWizardWizardIterator.resetSettings();

        WizardDescriptor.Iterator iterator = new TrialWizardWizardIterator();
        WizardDescriptor wizardDescriptor = new WizardDescriptor(iterator);
        wizardDescriptor.setTitleFormat(new MessageFormat("{0} ({1})"));
        wizardDescriptor.setTitle("Create Trial Wizard");

        Dialog dialog = DialogDisplayer.getDefault().createDialog(wizardDescriptor);
        dialog.setVisible(true);
        dialog.toFront();

        boolean cancelled = wizardDescriptor.getValue() != WizardDescriptor.FINISH_OPTION;

        if (!cancelled) {

            studyEditor.setStudy(SelectedStudy.selected);
            studyEditor.jTextFieldStudy.setText(SelectedStudy.selected.getStudy());
            studyEditor.jTextFieldObjective.setText(SelectedStudy.selected.getObjective());
            studyEditor.jTextFieldTitle.setText(SelectedStudy.selected.getTitle());
            Date start = SelectedStudy.selected.getStarDate();
            Date end = SelectedStudy.selected.getEndDate();
            String formato = "dd-MMM-yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            try {
                studyEditor.jDateChooserStart.setDate(start);
            } catch (NullPointerException ex) {
            }
            try {
                studyEditor.jDateChooserEnd.setDate(end);
            } catch (NullPointerException ex) {
            }
            try {
                studyEditor.jTextFieldPMKey.setText(SelectedStudy.selected.getPmkey());
            } catch (NullPointerException ex) {
            }
            try {
                studyEditor.jComboBoxStudyType.setSelectedItem(SelectedStudy.selected.getStudyType());
            } catch (NullPointerException ex) {
            }


            boolean estaCorrecto = true;
            for (int i = 0; i < studyEditor.jTableDesign.getRowCount(); i++) {
                try {
                    if (studyEditor.jTableDesign.getValueAt(i, 2).toString().isEmpty()) {
                        estaCorrecto = false;
                    }
                    if (studyEditor.jTableDesign.getValueAt(i, 3).toString().isEmpty()) {
                        estaCorrecto = false;
                    }
                    if (studyEditor.jTableDesign.getValueAt(i, 4).toString().isEmpty()) {
                        estaCorrecto = false;
                    }

                } catch (NullPointerException ex) {

                    return;
                }
            }


            if (estaCorrecto) {
                studyEditor.fillObservationsData();
            }


            if (!TrialWizardWizardIterator.myExcelReader.getMyWorkbook().hasPropertyTrialInstance()) {
                studyEditor.jTabbedPaneEditor.setEnabledAt(1, false);
            } else {
                studyEditor.jTabbedPaneEditor.setEnabledAt(1, true);
            }

            if (!TrialWizardWizardIterator.existenFactores) {
                studyEditor.jTabbedPaneEditor.setEnabledAt(4, false);
            } else {
                studyEditor.jTabbedPaneEditor.setEnabledAt(4, true);
            }
            
            studyEditor.defineTabs();
            studyEditor.open();
            studyEditor.requestActive();

        }

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

    private Study getStudyFromDto(org.cimmyt.cril.ibwb.domain.Study studyDto) {
        Study study = new Study();
        //studyId=studyDto.get
        return study;
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
