/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyeditor.exportwizard;

import ibfb.studyeditor.core.StudyEditorTopComponent;
import java.awt.Component;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.windows.WindowManager;

public class exportWizardPanel1 implements WizardDescriptor.Panel {


    private exportVisualPanel1 component;

  
    public Component getComponent() {
        if (component == null) {
            component = new exportVisualPanel1();
        }
        return component;
    }

    public HelpCtx getHelp() {
        // Show no Help button for this panel:
        return HelpCtx.DEFAULT_HELP;
        // If you have context help:
        // return new HelpCtx(SampleWizardPanel1.class);
    }

    public boolean isValid() {
        // If it is always OK to press Next or Finish, then:
        return true;

    }

    public final void addChangeListener(ChangeListener l) {
    }

    public final void removeChangeListener(ChangeListener l) {
    }
    /*
    private final Set<ChangeListener> listeners = new HashSet<ChangeListener>(1); // or can use ChangeSupport in NB 6.0
    public final void addChangeListener(ChangeListener l) {
    synchronized (listeners) {
    listeners.add(l);
    }
    }
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
     */

    // You can use a settings object to keep track of state. Normally the
    // settings object will be the WizardDescriptor, so you can use
    // WizardDescriptor.getProperty & putProperty to store information entered
    // by the user.
    @Override
    public void readSettings(Object settings) {
    }

    @Override
    public void storeSettings(Object settings) {
        
               StudyEditorTopComponent studyEditor = (StudyEditorTopComponent) WindowManager.getDefault().getRegistry().getActivated();


        if (component.jRadioButtonToFieldlog.isSelected()) {
            studyEditor.opcionFiltro = 1;
           studyEditor.opcionExport = 0;
            return;
        }

        if (component.jRadioButtonToR.isSelected()) {
            studyEditor.opcionFiltro = 1;
            studyEditor.opcionExport = 1;
          

            return;
        }

        if (component.jRadioButtonToExcel.isSelected()) {
            studyEditor.opcionFiltro = 0;
            studyEditor.opcionExport = 2;
            
            return;
        }
 
    }
}
