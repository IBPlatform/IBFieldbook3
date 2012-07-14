package ibfb.studyexplorer.actions;

import ibfb.domain.core.Study;
import ibfb.studyeditor.core.StudyEditorTopComponent;
import ibfb.studyexplorer.jdialogs.JDNewOptions;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
id = "ibfb.studyexplorer.actions.QuickCreationAction")
@ActionRegistration(iconBase = "ibfb/studyexplorer/images/quickTrial16.png",
displayName = "#CTL_QuickCreationAction")
@ActionReferences({
    @ActionReference(path = "Menu/Study", position = 1275),
    @ActionReference(path = "Toolbars/File", position = -250)
})

public final class QuickCreationAction extends SystemAction implements ActionListener {

    private final Study context;
        private ResourceBundle bundle = NbBundle.getBundle(QuickCreationAction.class);


    public QuickCreationAction() {
        putValue(NAME, bundle.getString("QuickCreationAction.name"));
        setEnabled(Boolean.TRUE);
        this.context = null;
    }

    public QuickCreationAction(Study context) {
        this.context = context;

    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        changeCursorWaitStatus(true);
        TopComponent background = WindowManager.getDefault().findTopComponent("BackgroundWindowTopComponent");
        if (background.isOpened()) {
            background.close();
        }
        if (existeTrial(JDNewOptions.studyOBJ.getStudy())) {
            int opcion = JOptionPane.showConfirmDialog(null, bundle.getString("QuickCreationAction.already"), bundle.getString("QuickCreationAction.caution"), JOptionPane.YES_NO_OPTION);
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
        return bundle.getString("QuickCreationAction.name");
    }

    @Override
    public HelpCtx getHelpCtx() {
        return null;
    }
}
