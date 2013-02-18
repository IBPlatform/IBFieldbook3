package ibfb.nursery.mainwizard;

import ibfb.nursery.core.NurseryEditorTopComponent;
import ibfb.nursery.utils.ExcelReaderClass;
import java.awt.Component;
import java.util.NoSuchElementException;
import javax.swing.JComponent;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.ImageUtilities;

public final class NurseryWizardIterator implements WizardDescriptor.Iterator {



    private int index;
    private WizardDescriptor.Panel[] panels;
    public static int state = 0;
    public static boolean isNew = false;
    public static boolean existenFactores = false;
    public static boolean existenConstantes = false;
    public static boolean existenConditions = false;
    public static boolean existenLabels = false;
    public static boolean constantsAreFilled = false;
    public static boolean traitsAreFilled = false;
    public static boolean OtherFactorsAreFilled = false;
    public static String myList = "";
    public static boolean isNewList = false;
    public static NurseryEditorTopComponent nurseryTopComponent;
    public static int[] levels;
    public static ExcelReaderClass myExcelReader = new ExcelReaderClass();

    private WizardDescriptor.Panel[] getPanels() {
        if (panels == null) {
            panels = new WizardDescriptor.Panel[]{
                new NurseryWizardPanel1(),
                new NurseryWizardPanel2(),
                new NurseryWizardPanel3(),
                new NurseryWizardPanel41(),
                new NurseryWizardPanel5(),
                new NurseryWizardPanel9(),
                new NurseryWizardPanel10(),
                new NurseryWizardPanel11()
            };
            String[] steps = new String[panels.length];
            for (int i = 0; i < panels.length; i++) {
                Component c = panels[i].getComponent();
                steps[i] = c.getName();
                if (c instanceof JComponent) {
                    JComponent jc = (JComponent) c;
                    jc.putClientProperty("WizardPanel_contentSelectedIndex", new Integer(i));
                    jc.putClientProperty("WizardPanel_contentData", steps);
                    jc.putClientProperty("WizardPanel_autoWizardStyle", Boolean.TRUE);
                    jc.putClientProperty("WizardPanel_contentDisplayed", Boolean.TRUE);
                    jc.putClientProperty("WizardPanel_contentNumbered", Boolean.TRUE);
                    jc.putClientProperty("WizardPanel_image", ImageUtilities.loadImage("/ibfb/nursery/images/logoWizard.png", true));
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
        return index + 1 + " of " + getPanels().length;
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


        if (current() instanceof NurseryWizardPanel2) {

            if (!existenConditions) {
                index++;
            }
        }


        if (current() instanceof NurseryWizardPanel5) {

            if (!existenConstantes) {
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

        if (current() instanceof NurseryWizardPanel41) {

            if (!existenConditions) {
                index--;
            }
        }

        if (current() instanceof NurseryWizardPanel10) {

            if (!existenConstantes) {
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
