
package ibfb.nursery.mainwizard;

import java.awt.Component;
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

public class NurseryWizardPanel6 implements WizardDescriptor.Panel, DocumentListener {

    private NurseryVisualPanel6 component;
    private boolean isValid = false;
    private boolean reload=false;
    private Set<ChangeListener> listeners = new HashSet<ChangeListener>(1);

    
    @Override
    public Component getComponent() {
        if (component == null) {
            component = new NurseryVisualPanel6();
            component.getjTextFieldTotalEntries().getDocument().addDocumentListener(this);
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

    private void setValid(boolean val) {
        if (isValid != val) {
            isValid = val;
            fireChangeEvent();
            reload=true;
        }else{
             reload=false;
        }
    }

    private void change() {
        String currentText = component.getjTextFieldTotalEntries().getText();
        boolean isValidInput = currentText != null && !currentText.equals("0");
       
        if (isValidInput) {
            setValid(true);        
        } else {
            setValid(false);
           
            
        }
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
    
    @Override
    public void readSettings(Object settings) {
        component.setMyWorkbook(NurseryWizardIterator.myExcelReader.getMyWorkbook());
        component.fillComboListNames();
    }

    @Override
    public void storeSettings(Object settings) {
        ((WizardDescriptor) settings).putProperty("entries", component.getjTextFieldTotalEntries().getText());
        NbPreferences.forModule(NurseryVisualPanel6.class).put("entries", component.getjTextFieldTotalEntries().getText());
        component.copyValues();
    }
}
