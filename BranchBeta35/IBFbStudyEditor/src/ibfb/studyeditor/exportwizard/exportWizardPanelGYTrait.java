package ibfb.studyeditor.exportwizard;

import ibfb.studyeditor.core.StudyEditorTopComponent;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbPreferences;
import org.openide.windows.WindowManager;

public class exportWizardPanelGYTrait implements WizardDescriptor.Panel, PropertyChangeListener {

    private exportVisualPanelGYTrait component;
    private boolean isValid = false;
    private Set<ChangeListener> listeners = new HashSet<ChangeListener>(1);

    @Override
    public Component getComponent() {
        if (component == null) {
            component = new exportVisualPanelGYTrait();
            component.getjLabelSelected().addPropertyChangeListener(this);
        }
        return component;
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
    public void readSettings(Object settings) {
        StudyEditorTopComponent studyEditor = (StudyEditorTopComponent) WindowManager.getDefault().getRegistry().getActivated();
        component.fillTraitList(studyEditor.getSelectedTraits());      

        int index = Integer.parseInt(NbPreferences.forModule(exportWizardPanelGYTrait.class).get("traitIndex", "-1"));
        component.setTraitInList(index) ;  
        
        
    }

    @Override
    public void storeSettings(Object settings) {
        StudyEditorTopComponent studyEditor = (StudyEditorTopComponent) WindowManager.getDefault().getRegistry().getActivated();
        studyEditor.setTraitToEvaluate(component.getElTrait());
        if (component.getElTrait() == null) {
            return;
        }
        studyEditor.setStringTraitToEvaluate(component.getElTrait().getVariateName());
        
        //((WizardDescriptor) settings).putProperty("traitIndex", String.valueOf(component.getSelectedTrait()));
         NbPreferences.forModule(exportWizardPanelGYTrait.class).put("traitIndex", String.valueOf(component.getSelectedTrait()));
        
    }

    private void setValid(boolean val) {
        if (isValid != val) {
            isValid = val;
            fireChangeEvent();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        change();
    }

    private void change() {
        String currentText = component.getjLabelSelected().getText();
        boolean isValidInput = currentText != null && currentText.length() > 0;
        if (isValidInput) {
            setValid(true);
        } else {
            setValid(false);
        }
    }

    @Override
    public HelpCtx getHelp() {
        return HelpCtx.DEFAULT_HELP;
    }
}
