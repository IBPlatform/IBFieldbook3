
package ibfb.studyeditor.exportformaize;

import ibfb.studyeditor.core.StudyEditorTopComponent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.windows.WindowManager;

public class maizeExportWizardPanel2 implements WizardDescriptor.Panel<WizardDescriptor> , ChangeListener{

 
    private maizeExportVisualPanel2 component;
    private boolean isValid = true;
    private Set<ChangeListener> listeners = new HashSet<ChangeListener>(1);
   
    
   
    @Override
    public maizeExportVisualPanel2 getComponent() {
        if (component == null) {
            component = new maizeExportVisualPanel2();
            component.jRadioButtonAll.addChangeListener(this);
            component.jRadioButtonFrom.addChangeListener(this);
            component.jRadioButtonSelected.addChangeListener(this);
            component.jSpinnerTrialBegin.addChangeListener(this);
            component.jSpinnerTrialEnd.addChangeListener(this);
        }
        return component;
    }

    @Override
    public HelpCtx getHelp() {
        return HelpCtx.DEFAULT_HELP;
        
    }

    @Override
    public boolean isValid() {
        return true;
        
    }

    @Override
    public void addChangeListener(ChangeListener l) {
        synchronized (listeners) {
            listeners.add(l);
        }
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
        synchronized (listeners) {
            listeners.remove(l);
        }
    }

    @Override
    public void readSettings(WizardDescriptor wiz) {
         StudyEditorTopComponent studyEditor = (StudyEditorTopComponent) WindowManager.getDefault().getRegistry().getActivated();

        int maximo = studyEditor.getMaxTrial();

        SpinnerNumberModel model1 = new SpinnerNumberModel(1, 1, maximo, 1);
        SpinnerNumberModel model2 = new SpinnerNumberModel(1, 1, maximo, 1);
        SpinnerNumberModel model3 = new SpinnerNumberModel(1, 1, maximo, 1);

        component.jSpinnerTrial.setModel(model1);
        component.jSpinnerTrialBegin.setModel(model2);
        component.jSpinnerTrialEnd.setModel(model3);
        component.jRadioButtonAll.setSelected(true);
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        StudyEditorTopComponent studyEditor = (StudyEditorTopComponent) WindowManager.getDefault().getRegistry().getActivated();


        if (component.jRadioButtonAll.isSelected()) {
            studyEditor.triallOption = 0;
            return;
        }

        if (component.jRadioButtonSelected.isSelected()) {
            studyEditor.triallOption = 1;
            studyEditor.trialSelected = Integer.parseInt(component.jSpinnerTrial.getValue().toString());

            return;
        }

        if (component.jRadioButtonFrom.isSelected()) {
            studyEditor.triallOption = 2;
            studyEditor.trialStart = Integer.parseInt(component.jSpinnerTrialBegin.getValue().toString());
            studyEditor.trialEnd = Integer.parseInt(component.jSpinnerTrialEnd.getValue().toString());
            return;
        }
    }
    
    
    @Override
    public void stateChanged(ChangeEvent e) {
        change();
    }

    private void change() {


        if (component.jRadioButtonFrom.isSelected()) {

            boolean estaSeleccionado = component.jRadioButtonFrom.isSelected();

            if (estaSeleccionado) {
                int menor = Integer.parseInt(component.jSpinnerTrialBegin.getValue().toString());
                int mayor = Integer.parseInt(component.jSpinnerTrialEnd.getValue().toString());

                if (menor <= mayor) {
                    setValid(true);
                } else {
                    setValid(false);
                }


            } else {
                setValid(true);
            }



        } else {
            setValid(true);
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
    
    
}
