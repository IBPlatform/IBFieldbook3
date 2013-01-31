package ibfb.nursery.mainwizard;

import ibfb.nursery.core.NurseryEditorTopComponent;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
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

public class NurseryWizardPanel5 implements WizardDescriptor.Panel, DocumentListener, PropertyChangeListener {

    private NurseryVisualPanel5 component;
    private boolean isValid = false;
    private Set<ChangeListener> listeners = new HashSet<ChangeListener>(1);
    private boolean seEjecuto = false;

    @Override
    public Component getComponent() {
        if (component == null) {
            component = new NurseryVisualPanel5();
            component.getjTextFieldTotalEntries().getDocument().addDocumentListener(this);
            component.getjTableFinalList().addPropertyChangeListener(this);
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
        int entries = Integer.parseInt(NbPreferences.forModule(NurseryVisualPanel41.class).get("entries", "0"));
        component.setMaximo(entries);
        component.setMyWorkbook(NurseryWizardIterator.myExcelReader.getMyWorkbook());
        component.fillComboListNames();
        seEjecuto = false;
        change();
    }

    @Override
    public void storeSettings(Object settings) {
       
        NurseryEditorTopComponent nurseryWindow = NurseryWizardIterator.nurseryTopComponent;
        if (seEjecuto) {
            return;
        }
        
        component.addCheckIntoList();        
        int tamPos=component.getTamPos();
 
        if(tamPos>0 ){
            
            int tot=0;
            
        
            if(!component.freqIsInvalid()){
           
                if (component.checksInSequence()) {
                    
                    nurseryWindow.setSequenceList(component.calculaPosicionesSecuencia());
                    tot=component.getSequenceListSize();
                    nurseryWindow.setPosiciones(component.calculaPosicionesSecuenciaInteger());
                    
                } else {
                    ArrayList<Integer> posiciones = component.calculaPosiciones();
                    nurseryWindow.setPosiciones(posiciones);
                    tot = posiciones.size();
                }
            
            
           nurseryWindow.setChecksInSequence(component.checksInSequence());
           
           
           
           
        }
 
            
        
        ((WizardDescriptor) settings).putProperty("checks", String.valueOf(tot));
         NbPreferences.forModule(NurseryVisualPanel5.class).put("checks", String.valueOf(tot));
        
        }else{
          nurseryWindow.setPosiciones(new ArrayList<Integer>()); 
         ((WizardDescriptor) settings).putProperty("checks", "0");
          NbPreferences.forModule(NurseryVisualPanel5.class).put("checks", "0");          
        }
 
        seEjecuto = true;
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

    private void change() {
        if (component.getjTextFieldTotalEntries().getText().equals("0")) {
            
        } else {
            setValid(component.validateTable());
        }

    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        change();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        change();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        change();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (component.getjTextFieldTotalEntries().getText().equals("0")) {
            setValid(true);
        } else {
            setValid(component.validateTable());
        }
    }
}
