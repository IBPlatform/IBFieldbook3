
package ibfb.germplasmlist.crossWizard;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;

public class CrossWizardWizardPanel1 implements WizardDescriptor.Panel<WizardDescriptor>,PropertyChangeListener {

    
    private CrossWizardVisualPanel1 component;
    private boolean isValid = false;
    private Set<ChangeListener> listeners = new HashSet<ChangeListener>(1);

    @Override
    public CrossWizardVisualPanel1 getComponent() {
        if (component == null) {
            component = new CrossWizardVisualPanel1();
             
            component.getjLabelRows().addPropertyChangeListener(this);
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
      
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        CrossWizardWizardIterator.crossTopComponent.setModeloEntries(component.getjTableEntries().getModel());
    }

    private void change() {
        String currentEntries = component.getjLabelRows().getText();
        boolean isValidInput = currentEntries.equals("0");
        if (isValidInput) {
            setValid(false);
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        change();
    }
}
