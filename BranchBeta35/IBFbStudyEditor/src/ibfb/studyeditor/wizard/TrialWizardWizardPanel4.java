
package ibfb.studyeditor.wizard;

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

public class TrialWizardWizardPanel4 implements WizardDescriptor.Panel,DocumentListener {

    
    private TrialWizardVisualPanel4 component;
     private boolean isValid = false;    
    private Set<ChangeListener> listeners = new HashSet<ChangeListener>(1);

  
    @Override
    public TrialWizardVisualPanel4 getComponent() {
        if (component == null) {
            component = new TrialWizardVisualPanel4();
           
            component.getjTextFieldTotalEntries().getDocument().addDocumentListener(this);
                    
        }
        return component;
    }

    @Override
    public HelpCtx getHelp() {
       
        return HelpCtx.DEFAULT_HELP;
        // If you have context help:
        // return new HelpCtx(SampleWizardPanel1.class);
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
        component.setMyWorkbook(TrialWizardWizardIterator.myExcelReader.getMyWorkbook());
    }

    @Override
    public void storeSettings(Object settings) {
        ((WizardDescriptor) settings).putProperty("entries", component.getjTextFieldTotalEntries().getText());
        NbPreferences.forModule(TrialWizardVisualPanel4.class).put("entries", component.getjTextFieldTotalEntries().getText());
        component.copyValues();
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
    
      private void change() {
        String currentEntries = component.getjTextFieldTotalEntries().getText();
        boolean isValidInput =  currentEntries.equals("0"); 
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
    
}
