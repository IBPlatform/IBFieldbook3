package ibfb.nursery.actions;

import ibfb.domain.core.SelectedStudy;
import ibfb.domain.core.Study;
import ibfb.nursery.core.NurseryEditorTopComponent;
import ibfb.nursery.mainwizard.NurseryWizardIterator;
import ibfb.nursery.quickcreation.JDQuickCreation;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

@ActionID(category = "File",
id = "ibfb.nursery.actions.NewNurseryAction")
@ActionRegistration(iconBase = "ibfb/nursery/images/newNursery16.png",
displayName = "#CTL_NewNurseryAction")
@ActionReferences({
    @ActionReference(path = "Menu/Study", position = 1237),
    @ActionReference(path = "Toolbars/File", position = -325)
})
@Messages("CTL_NewNurseryAction=New Nursery")
public final class NewNurseryAction implements ActionListener {
private ResourceBundle bundle = NbBundle.getBundle(NewNurseryAction.class);
    private final Study context;
    private JDQuickCreation quick;

    public NewNurseryAction(Study context) {

        this.context = context;


    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        JPNurseryOptions myPanel = new JPNurseryOptions();
        NotifyDescriptor nd = new NotifyDescriptor(
                myPanel,
                "IBFIELDBOOK - Nursery",
                NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.QUESTION_MESSAGE,
                null,
                NotifyDescriptor.OK_OPTION);


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

    private void runQuickCreation() {
        changeCursorWaitStatus(true);
        quick = new JDQuickCreation(null, true);

        TopComponent background = WindowManager.getDefault().findTopComponent("BackgroundWindowTopComponent");
        if (background.isOpened()) {
            background.close();
        }
        if (existeNursery("Nursery - " + SelectedStudy.selected.getStudy())) {
            int opcion = JOptionPane.showConfirmDialog(null, bundle.getString("NewNurseryAction.alreadyExists"), bundle.getString("NewNurseryAction.caution"), JOptionPane.YES_NO_OPTION);
            if (opcion == 0) {
                NurseryEditorTopComponent nurseryEditor = null;
                ArrayList<TopComponent> opened = new ArrayList<TopComponent>(WindowManager.getDefault().getRegistry().getOpened());
                for (TopComponent t : opened) {
                    if (t.getName().equals(SelectedStudy.selected.getStudy())) {
                        nurseryEditor = (NurseryEditorTopComponent) t;
                        nurseryEditor.close();
                    }
                }
                nurseryEditor.setStudy(SelectedStudy.selected);
                quick.setNurseryWindow(nurseryEditor);
                quick.cleanFields();
                quick.setLocationRelativeTo(null);
                quick.setVisible(true);
            }

        } else {
            NurseryEditorTopComponent nurseryEditor = new NurseryEditorTopComponent();
            nurseryEditor.setStudy(SelectedStudy.selected);
            nurseryEditor.setName("Nursery - " + SelectedStudy.selected.getStudy());
            quick.setNurseryWindow(nurseryEditor);
            quick.cleanFields();
            quick.setLocationRelativeTo(null);
            quick.setVisible(true);
        }

        changeCursorWaitStatus(false);

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

    private void runWizard() {


        changeCursorWaitStatus(true);
        if (existeTopComponent("Nursery - " + SelectedStudy.selected.getStudy())) {
            int opc = JOptionPane.showConfirmDialog(null, "NURSERY ALREADY GENERATED. Do you want to overwrite it?", "Caution!", JOptionPane.YES_NO_OPTION);
            if (opc == 0) {
                NurseryEditorTopComponent nurseryEditor = null;
                ArrayList<TopComponent> opened = new ArrayList<TopComponent>(WindowManager.getDefault().getRegistry().getOpened());
                for (TopComponent t : opened) {
                    if (t.getName().equals("Nursery - " + SelectedStudy.selected.getStudy())) {
                        nurseryEditor = (NurseryEditorTopComponent) t;
                        // nurseryEditor.setStudy(SelectedStudy.selected);
                        nurseryEditor.close();
                    }
                }
                nurseryEditor.setStudy(SelectedStudy.selected);
                runNurseryWizard(nurseryEditor);
            }

        } else {

            NurseryEditorTopComponent nurseryEditor = new NurseryEditorTopComponent();
            nurseryEditor.setStudy(SelectedStudy.selected);
            nurseryEditor.setName("Nursery - " + SelectedStudy.selected.getStudy());

            runNurseryWizard(nurseryEditor);

        }
        changeCursorWaitStatus(false);

    }

    @SuppressWarnings("unchecked")
    public void runNurseryWizard(NurseryEditorTopComponent nurseryEditor) {

        TopComponent background = WindowManager.getDefault().findTopComponent("BackgroundWindowTopComponent");
        if (background.isOpened()) {
            background.close();
        }

        NurseryWizardIterator.nurseryTopComponent = nurseryEditor;
        nurseryEditor.setMainProperties(SelectedStudy.selected);

        WizardDescriptor.Iterator iterator = new NurseryWizardIterator();
        WizardDescriptor wizardDescriptor = new WizardDescriptor(iterator);
        wizardDescriptor.setTitleFormat(new MessageFormat("{0} ({1})"));
        wizardDescriptor.setTitle("Create New Nursery Wizard");
        Dialog dialog = DialogDisplayer.getDefault().createDialog(wizardDescriptor);
        dialog.setVisible(true);
        dialog.toFront();

        changeCursorWaitStatus(false);

        boolean cancelled = wizardDescriptor.getValue() != WizardDescriptor.FINISH_OPTION;
        if (!cancelled) {

            nurseryEditor.addChecks();
            nurseryEditor.fillObservationsData();

            if (NurseryWizardIterator.existenConstantes) {

                nurseryEditor.jTabbedPane1.setEnabledAt(1, true);

            } else {
                nurseryEditor.jTabbedPane1.setEnabledAt(1, false);
            }


            if (NurseryWizardIterator.existenConditions) {
                nurseryEditor.jTabbedPane1.setEnabledAt(2, true);
            } else {
                nurseryEditor.jTabbedPane1.setEnabledAt(2, false);
            }


            nurseryEditor.open();
            nurseryEditor.requestActive();





        }

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

    public boolean existeTopComponent(String topName) {
        boolean existe = false;
        try {
            ArrayList<TopComponent> opened = new ArrayList<TopComponent>(WindowManager.getDefault().getRegistry().getOpened());

            for (TopComponent t : opened) {

                if (t.getName().equals(topName)) {
                    existe = true;
                }
            }
        } catch (NullPointerException ex) {
            existe = false;
        }
        return existe;
    }
}
