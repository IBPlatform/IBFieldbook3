package ibfb.studyeditor.gywizard;

import ibfb.studyeditor.core.StudyEditorTopComponent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.windows.WindowManager;

public class GYwizardWizardPanel1 implements WizardDescriptor.Panel<WizardDescriptor> ,PropertyChangeListener{

    
    private GYwizardVisualPanel1 component;
      private boolean isValid = false;
    private Set<ChangeListener> listeners = new HashSet<ChangeListener>(1);

   
    
    @Override
    public GYwizardVisualPanel1 getComponent() {
        if (component == null) {
            component = new GYwizardVisualPanel1();
             component.getjLabelSelected().addPropertyChangeListener(this) ;
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
    public void readSettings(WizardDescriptor wiz) {
      StudyEditorTopComponent studyEditor = (StudyEditorTopComponent) WindowManager.getDefault().getRegistry().getActivated();
      component.fillTraitList(studyEditor.getSelectedTraits());
   
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
       StudyEditorTopComponent studyEditor = (StudyEditorTopComponent) WindowManager.getDefault().getRegistry().getActivated();
       studyEditor.setTraitToEvaluate(component.getElTrait());
       if(component.getElTrait()==null){
          return; 
       }
       studyEditor.setStringTraitToEvaluate(component.getElTrait().getVariateName());
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

}
