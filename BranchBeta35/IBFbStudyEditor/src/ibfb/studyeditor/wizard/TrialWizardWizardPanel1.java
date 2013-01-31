package ibfb.studyeditor.wizard;

import ibfb.studyeditor.core.StudyEditorTopComponent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbPreferences;

public class TrialWizardWizardPanel1 implements WizardDescriptor.Panel, DocumentListener {

    private TrialWizardVisualPanel1 component;
    private boolean isValid = false;
    private Set<ChangeListener> listeners = new HashSet<ChangeListener>(1);

    @Override
    public TrialWizardVisualPanel1 getComponent() {
        if (component == null) {
            component = new TrialWizardVisualPanel1();
            component.getjTextAreaPath().getDocument().addDocumentListener(this);
        }
        return component;
    }

    @Override
    public HelpCtx getHelp() {
        // Show no Help button for this panel:
        return HelpCtx.DEFAULT_HELP;
        // If you have context help:
        // return new HelpCtx(SampleWizardPanel1.class);
    }

    @Override
    public boolean isValid() {

        return isValid;
    }

    private void change() {
        String currentText = component.getjTextAreaPath().getText();
        boolean isValidInput = currentText != null && currentText.length() > 0;
        if (isValidInput) {
            setValid(true);
        } else {
            setValid(false);
        }
    }

    @Override
    public final void addChangeListener(ChangeListener l) {
        synchronized (listeners) {
            listeners.add(l);
        }
    }

    @Override
    public final void removeChangeListener(ChangeListener l) {
        synchronized (listeners) {
            listeners.remove(l);
        }
    }

    protected final void fireChangeEvent() {
        Iterator<ChangeListener> it;
        synchronized (listeners) {
            it = new HashSet<ChangeListener>(listeners).iterator();
        }

        ChangeEvent ev = new ChangeEvent(this);
        while (it.hasNext()) {
            it.next().stateChanged(ev);
        }
    }

    private void setValid(boolean val) {
        if (isValid != val) {
            isValid = val;
            fireChangeEvent();
        }
    }

    @Override
    public void readSettings(Object settings) {
    }

    @Override
    public void storeSettings(Object settings) {
        doActions();
    }

    @Override
    public void insertUpdate(DocumentEvent de) {
        change();
    }

    @Override
    public void removeUpdate(DocumentEvent de) {
        change();
    }

    @Override
    public void changedUpdate(DocumentEvent de) {
        change();

    }

    private void doActions() {
        TrialWizardWizardIterator.myExcelReader.setFile(component.getjTextAreaPath().getText());
        TrialWizardWizardIterator.constantsAreFilled = false;
        TrialWizardWizardIterator.traitsAreFilled = false;
        TrialWizardWizardIterator.OtherFactorsAreFilled = false;
        StudyEditorTopComponent studyWindow = TrialWizardWizardIterator.studyTopComponent;
        studyWindow.setMyWorkbook(TrialWizardWizardIterator.myExcelReader.getMyWorkbook());
        studyWindow.setFileTemplate(component.getjTextAreaPath().getText());
        studyWindow.setCROP(component.giveMeCrop());
        String templateName = component.getjTextAreaPath().getText();

        // check if is same template, if not same assign it 
        if (!TrialWizardUtil.isSameTemplate(templateName)) {
            TrialWizardUtil.assignTemplateName(templateName);
            // study conditions are not filled
            TrialWizardUtil.unAssignStudyConditions();
            TrialWizardUtil.unAssignTrialConditions();


        }
    }
}
