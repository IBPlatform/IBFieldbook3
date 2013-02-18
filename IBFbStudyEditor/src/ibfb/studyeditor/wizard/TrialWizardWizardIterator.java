package ibfb.studyeditor.wizard;

import ibfb.studyeditor.core.ExcelReaderClass;
import ibfb.studyeditor.core.StudyEditorTopComponent;
import java.awt.Component;
import java.util.NoSuchElementException;
import javax.swing.JComponent;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.ImageUtilities;

public final class TrialWizardWizardIterator implements WizardDescriptor.Iterator {

    public static ExcelReaderClass myExcelReader = new ExcelReaderClass();
    private int index;
    private WizardDescriptor.Panel[] panels;
    public static boolean existenFactores = false;
    public static boolean existenLabels = false;
    public static boolean constantsAreFilled = false;
    public static boolean traitsAreFilled = false;
    public static boolean OtherFactorsAreFilled = false;
    public static StudyEditorTopComponent studyTopComponent;
    public static int[] levels;

    private WizardDescriptor.Panel[] getPanels() {
        if (panels == null) {
            panels = new WizardDescriptor.Panel[]{
                new TrialWizardWizardPanel1(),
                new TrialWizardWizardPanel2(),
                new TrialWizardWizardPanel3(),
                new TrialWizardWizardPanel4(),
                new TrialWizardWizardPanel5(),
                new TrialWizardWizardPanel6(),
                new TrialWizardWizardPanel7(),
                new TrialWizardWizardPanel8(),
                new TrialWizardWizardPanel9()
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
                    jc.putClientProperty("WizardPanel_image", ImageUtilities.loadImage("/ibfb/studyeditor/images/logoWizard.png", true));
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
        
              if (current() instanceof TrialWizardWizardPanel2) {
           TrialWizardUtil.unAssignStudyConditions();
           TrialWizardUtil.unAssignTrialConditions();
        }

        
        return index > 0;
    }

    @Override
    public void nextPanel() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        if (current() instanceof TrialWizardWizardPanel2) {
            if (!TrialWizardWizardIterator.myExcelReader.getMyWorkbook().hasPropertyTrialInstance()) {
                index++;
            }
        }

        if (current() instanceof TrialWizardWizardPanel4) {
            TrialWizardVisualPanel4 panelSelectGsm = (TrialWizardVisualPanel4) current().getComponent();
            if (panelSelectGsm.existenFactores(myExcelReader.getMyWorkbook())) {
                TrialWizardWizardIterator.existenFactores = true;

            } else {

                TrialWizardWizardIterator.existenFactores = false;
                index = index + 2;
            }
        }


        if (current() instanceof TrialWizardWizardPanel5) {
            TrialWizardVisualPanel5 panelOtherFactors = (TrialWizardVisualPanel5) current().getComponent();
            if (!panelOtherFactors.tenemosLabels(myExcelReader.getMyWorkbook())) {
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

        if (current() instanceof TrialWizardWizardPanel4) {
            if (!TrialWizardWizardIterator.myExcelReader.getMyWorkbook().hasPropertyTrialInstance()) {
                index--;
            }

        }

        if (current() instanceof TrialWizardWizardPanel7) {
            if (TrialWizardWizardIterator.existenFactores == false) {
                //index = index - 2;
                index = index - 1;
            }

            if (TrialWizardWizardIterator.existenLabels == false) {
                index--;
            }

        }


        
        
        


        index--;
    }

    // If nothing unusual changes in the middle of the wizard, simply:
    @Override
    public void addChangeListener(ChangeListener l) {
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
    }
 
    /**
     * Resets all setting for wizard
     */
    public static void resetSettings() {
        TrialWizardUtil.resetSettings();
    }
}
