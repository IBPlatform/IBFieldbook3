
package ibfb.nursery.importwizard;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;

public class ImportWizardWizardPanel1 implements WizardDescriptor.Panel, PropertyChangeListener {

    private ImportWizardVisualPanel1 component;
    private boolean isValid = false;
    private Set<ChangeListener> listeners = new HashSet<ChangeListener>(1);
    private File myFile;
    
    @Override
    public Component getComponent() {
        if (component == null) {
            component = new ImportWizardVisualPanel1();
            component.getjFileChooser().addPropertyChangeListener(this);
        }
        return component;
    }

    
       public File getMyFile() {
        return myFile;
    }

    public void setMyFile(File myFile) {
        this.myFile = myFile;
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
    }

    private void change() {
        try {
            File archivo = component.getjFileChooser().getSelectedFile();
            if (archivo.exists() && archivo.isFile()) {
                this.setMyFile(archivo);
                setValid(true);
                
            } else {                
                setValid(false);
            }
        } catch (NullPointerException ex) {
            setValid(false);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        change();
    }
}
