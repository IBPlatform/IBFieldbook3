package ibfb.nursery.exportwizard;


import ibfb.nursery.core.NurseryEditorTopComponent;
import java.awt.Component;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.windows.WindowManager;

public class exportWizardPanel2 implements WizardDescriptor.Panel, ChangeListener {

    private boolean isValid = true;
    private Set<ChangeListener> listeners = new HashSet<ChangeListener>(1);
    private exportVisualPanel2 component;

    @Override
    public Component getComponent() {
        if (component == null) {
            component = new exportVisualPanel2();
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
        component.setName("Select your data");

       NurseryEditorTopComponent nurseryEditor = (NurseryEditorTopComponent) WindowManager.getDefault().getRegistry().getActivated();

        int maximo = nurseryEditor.getMaxTrial();

        SpinnerNumberModel model1 = new SpinnerNumberModel(1, 1, maximo, 1);
        SpinnerNumberModel model2 = new SpinnerNumberModel(1, 1, maximo, 1);
        SpinnerNumberModel model3 = new SpinnerNumberModel(1, 1, maximo, 1);

        component.jSpinnerTrial.setModel(model1);
        component.jSpinnerTrialBegin.setModel(model2);
        component.jSpinnerTrialEnd.setModel(model3);
        component.jRadioButtonAll.setSelected(true);
    }

    @Override
    public void storeSettings(Object settings) {
        NurseryEditorTopComponent nurseryEditor = (NurseryEditorTopComponent) WindowManager.getDefault().getRegistry().getActivated();


        if (component.jRadioButtonAll.isSelected()) {
            nurseryEditor.triallOption = 0;
            return;
        }

        if (component.jRadioButtonSelected.isSelected()) {
            nurseryEditor.triallOption = 1;
            nurseryEditor.trialSelected = Integer.parseInt(component.jSpinnerTrial.getValue().toString());

            return;
        }

        if (component.jRadioButtonFrom.isSelected()) {
            nurseryEditor.triallOption = 2;
            nurseryEditor.trialStart = Integer.parseInt(component.jSpinnerTrialBegin.getValue().toString());
            nurseryEditor.trialEnd = Integer.parseInt(component.jSpinnerTrialEnd.getValue().toString());
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
