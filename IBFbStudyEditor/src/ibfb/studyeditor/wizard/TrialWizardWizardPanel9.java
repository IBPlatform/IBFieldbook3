package ibfb.studyeditor.wizard;

import ibfb.studyeditor.core.StudyEditorTopComponent;
import ibfb.studyeditor.core.model.DesignTableModel;
import java.awt.Component;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import org.openide.WizardDescriptor;
import org.openide.WizardValidationException;
import org.openide.util.HelpCtx;
import org.openide.util.NbPreferences;

public class TrialWizardWizardPanel9 implements WizardDescriptor.Panel, WizardDescriptor.ValidatingPanel, TableModelListener {

    private TrialWizardVisualPanel9 component;
    private boolean isValid = false;
    private Set<ChangeListener> listeners = new HashSet<ChangeListener>(1);

    @Override
    public Component getComponent() {
        if (component == null) {
            component = new TrialWizardVisualPanel9(this);
            component.getjTableDesign().getModel().addTableModelListener(this);
        }
        return component;
    }

    @Override
    public HelpCtx getHelp() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    public boolean isValid() {
        return isValid;
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

    @Override
    public void readSettings(Object settings) {
       
            SpinnerNumberModel snm = new SpinnerNumberModel(1, 1, TrialWizardWizardIterator.myExcelReader.getNumInstances().intValue(), 1);
            component.getjSpinnerTrial().setModel(snm);
            component.getjTextFieldEntries().setText(NbPreferences.forModule(TrialWizardVisualPanel4.class).get("entries", "0"));
            int factores = Integer.parseInt(NbPreferences.forModule(TrialWizardVisualPanel5.class).get("otherFactors", "0"));
            boolean hayFactores = false;
            if (factores > 0) {
                hayFactores = true;
            }
            component.fillData(TrialWizardWizardIterator.myExcelReader.getNumInstances(), hayFactores);
        
    }

    @Override
    public void storeSettings(Object settings) {
        StudyEditorTopComponent studyWindow = TrialWizardWizardIterator.studyTopComponent;
        SpinnerNumberModel m1 = new SpinnerNumberModel(1, 1, TrialWizardWizardIterator.myExcelReader.getNumInstances().intValue(), 1);
        SpinnerNumberModel m2 = new SpinnerNumberModel(1, 1, TrialWizardWizardIterator.myExcelReader.getNumInstances().intValue(), 1);
        studyWindow.getjSpinnerConstants().setModel(m1);
        studyWindow.getjSpinnerTrInformation().setModel(m2);
        studyWindow.getjTextFieldEntries().setText(NbPreferences.forModule(TrialWizardVisualPanel4.class).get("entries", "0"));
        studyWindow.assignDesignModel((DesignTableModel) component.getjTableDesign().getModel());
        studyWindow.setMyWorkbook(TrialWizardWizardIterator.myExcelReader.getMyWorkbook());
        TrialWizardUtil.assignDesign();
    }

    public void change() {

        setValid(true);

        int instances = component.getjTableDesign().getRowCount();
        DesignTableModel tableModel = (DesignTableModel) component.getjTableDesign().getModel();
        for (int i = 0; i < instances; i++) {
            if (!tableModel.getDesignBean(i).isValidDesign()) {
                setValid(false);
                return;
            }
        }
    }

    private void setValid(boolean val) {
        if (isValid != val) {
            isValid = val;
            fireChangeEvent();
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

    @Override
    public void tableChanged(TableModelEvent tme) {
        change();
    }

    @Override
    public void validate() throws WizardValidationException {
        if (!component.allDesignsFilled()) {
            isValid = false;
            throw new WizardValidationException(null, "Please fill all Designs", null);
        } else {
            isValid = true;
        }
    }
}
