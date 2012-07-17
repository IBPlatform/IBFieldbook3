package ibfb.studyexplorer.actions;

import ibfb.domain.core.Study;
import ibfb.studyeditor.core.StudyEditorTopComponent;
import ibfb.studyeditor.wizard.TrialWizardWizardIterator;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.HelpCtx;
import org.openide.util.Mutex;
import org.openide.util.NbBundle;
import org.openide.util.actions.SystemAction;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

@ActionID(category = "File",
id = "ibfb.studyexplorer.actions.NewTrialAction")
@ActionRegistration(iconBase = "ibfb/studyexplorer/images/newTrial16.png",
displayName = "#CTL_NewTrialAction")
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1250),
    @ActionReference(path = "Toolbars/File", position = -300)
})

public final class NewTrialAction extends SystemAction implements ActionListener {

    public static Study studyOBJ = new Study();
    private final Study context;

    private ResourceBundle bundle = NbBundle.getBundle(NewTrialAction.class);

    public NewTrialAction() {
        putValue(NAME, bundle.getString("NewTrialAction.name"));
        setEnabled(Boolean.TRUE);
        this.context = null;
    }
    
    public NewTrialAction(Study context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        changeCursorWaitStatus(true);
        if (existeTrial(studyOBJ.getStudy())) {
            int opcion = JOptionPane.showConfirmDialog(null, bundle.getString("NewTrialAction.already"), bundle.getString("NewTrialAction.caution"), JOptionPane.YES_NO_OPTION);
            if (opcion == 0) {
                StudyEditorTopComponent studyEditor = null;
                ArrayList<TopComponent> opened = new ArrayList<TopComponent>(WindowManager.getDefault().getRegistry().getOpened());
                for (TopComponent t : opened) {
                    if (t.getName().equals(studyOBJ.getStudy())) {
                        studyEditor = (StudyEditorTopComponent) t;
                        studyEditor.close();
                    }
                }
                launchWizard(studyEditor);
            }

        } else {

            StudyEditorTopComponent studyEditor = new StudyEditorTopComponent();
            studyEditor.setName(studyOBJ.getStudy());
            studyEditor.setStudy(studyOBJ);
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
        wizardDescriptor.setTitle(bundle.getString("NewTrialAction.wizard"));

        Dialog dialog = DialogDisplayer.getDefault().createDialog(wizardDescriptor);
        dialog.setVisible(true);
        dialog.toFront();

        boolean cancelled = wizardDescriptor.getValue() != WizardDescriptor.FINISH_OPTION;

        if (!cancelled) {

            studyEditor.setStudy(studyOBJ);
            studyEditor.jTextFieldStudy.setText(studyOBJ.getStudy());
            studyEditor.jTextFieldObjective.setText(studyOBJ.getObjective());
            studyEditor.jTextFieldTitle.setText(studyOBJ.getTitle());
            Date start = studyOBJ.getStarDate();
            Date end = studyOBJ.getEndDate();
            String formato = "dd-MMM-yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            try {
                studyEditor.jDateChooserStart.setText(sdf.format(start));
            } catch (NullPointerException ex) {
            }
            try {
                studyEditor.jDateChooserEnd.setText(sdf.format(end));
            } catch (NullPointerException ex) {
            }
            try {
                studyEditor.jTextFieldPMKey.setText(studyOBJ.getPmkey());
            } catch (NullPointerException ex) {
            }
            try {
                studyEditor.jComboBoxStudyType.setSelectedItem(studyOBJ.getStudyType());
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

    @Override
    public String getName() {
       /// return NbBundle.getMessage(NewTrialAction.class, "CTL_NewTrialAction");
        return bundle.getString("NewTrialAction.name");
    }

    @Override
    public HelpCtx getHelpCtx() {
        return null;
    }
    
    
}
