package ibfb.studyeditor.exportwizard;

import ibfb.studyeditor.core.StudyEditorTopComponent;
import java.awt.Component;
import java.util.NoSuchElementException;
import javax.swing.JComponent;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.ImageUtilities;
import org.openide.windows.WindowManager;

public final class exportWizardIterator implements WizardDescriptor.Iterator {

    private int index;
    private WizardDescriptor.Panel[] panels;
    public static boolean isForR = false;
    public static boolean hasGYTrait = false;
    public static int indexTrait = -1;
    StudyEditorTopComponent studyEditor = (StudyEditorTopComponent) WindowManager.getDefault().getRegistry().getActivated();

    private WizardDescriptor.Panel[] getPanels() {
        if (panels == null) {
            panels = new WizardDescriptor.Panel[]{
                new exportWizardPanel1(),
                new exportWizardPanelGYTrait(),
                new exportWizardPanel2(),
                new exportWizardPanel3()
            };

            String[] steps = new String[panels.length];
            for (int i = 0; i < panels.length; i++) {
                Component c = panels[i].getComponent();
                // Default step name to component name of panel.
                steps[i] = c.getName();
                if (c instanceof JComponent) { // assume Swing components
                    JComponent jc = (JComponent) c;

                    jc.putClientProperty("WizardPanel_contentSelectedIndex", new Integer(i));
                    jc.putClientProperty("WizardPanel_contentData", steps);
                    jc.putClientProperty("WizardPanel_autoWizardStyle", Boolean.TRUE);
                    jc.putClientProperty("WizardPanel_contentDisplayed", Boolean.TRUE);
                    jc.putClientProperty("WizardPanel_contentNumbered", Boolean.TRUE);
                    jc.putClientProperty("WizardPanel_image", ImageUtilities.loadImage("ibfb/studyeditor/images/logoExport.png", true));

                }
            }
        }
        return panels;
    }

    @Override
    public WizardDescriptor.Panel current() {
        return getPanels()[index];
    }

    @Override
    public String name() {
        return index + 1 + ". from " + getPanels().length;
    }

    @Override
    public boolean hasNext() {
        return index < getPanels().length - 1;
    }

    @Override
    public boolean hasPrevious() {
        return index > 0;
    }

    @Override
    public void nextPanel() {

        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        if (current() instanceof exportWizardPanel1) {

            if (isForR) {

                if (studyEditor.hasGYbyDefault()) {
                    index++;
                }

            } else {
                index++;
            }
        }


        index++;
    }

    @Override
    public void previousPanel() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }

        if (current() instanceof exportWizardPanel2) {

            if (!isForR) {

                index--;
            }

            if (studyEditor.getSelectedTraits().size() <= 0) {
                index--;
            }



            if (studyEditor.hasGYbyDefault()) {
                index--;
            }
        }


        index--;
    }

    @Override
    public void addChangeListener(ChangeListener l) {
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
    }
}
