package ibfb.nursery.mainwizard;

import ibfb.nursery.core.NurseryEditorTopComponent;
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

public class NurseryWizardPanel1 implements WizardDescriptor.Panel, DocumentListener {

    private NurseryVisualPanel1 component;
    private boolean isValid = false;
    private boolean reload=false;
    private Set<ChangeListener> listeners = new HashSet<ChangeListener>(1);
  
    
    @Override
    public Component getComponent() {
        if (component == null) {
            component = new NurseryVisualPanel1();
            component.getjTextAreaPath().getDocument().addDocumentListener(this);
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
        String currentText = component.getjTextAreaPath().getText();
        boolean isValidInput = currentText != null && currentText.length() > 0;
        if (isValidInput) {
            setValid(true);         
        } else {
            setValid(false);
        }
    }

    @Override
    public void readSettings(Object settings) {
        reload=false;
    }

    @Override
    public void storeSettings(Object settings) {
        doActions();
        if(reload){
        restoreProperty(settings);
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

    private void doActions() {
        NurseryWizardIterator.myExcelReader.setFile(component.getjTextAreaPath().getText());
        NurseryEditorTopComponent nurseryWindow = NurseryWizardIterator.nurseryTopComponent;
        nurseryWindow.setFileTemplate(component.getjTextAreaPath().getText()); 
        NurseryWizardIterator.myExcelReader.readExcelWorkbookTemplate();
//        NurseryWizardIterator.existenConditions = ! NurseryWizardIterator.myExcelReader.getMyWorkbook().getConditions().isEmpty();
        NurseryWizardIterator.existenConstantes = ! NurseryWizardIterator.myExcelReader.getMyWorkbook().getConstants().isEmpty();
        nurseryWindow.setMyWorkbook(NurseryWizardIterator.myExcelReader.getMyWorkbook());
    }
    
    
    private void restoreProperty(Object settings){
         ((WizardDescriptor) settings).putProperty("loaded2", "no");
         NbPreferences.forModule(NurseryVisualPanel1.class).put("loaded2", "no");
         ((WizardDescriptor) settings).putProperty("loaded3", "no");
         NbPreferences.forModule(NurseryVisualPanel1.class).put("loaded3", "no");
         ((WizardDescriptor) settings).putProperty("loaded4", "no");

         NbPreferences.forModule(NurseryVisualPanel1.class).put("loaded5", "no");
         ((WizardDescriptor) settings).putProperty("loaded6", "no");
         NbPreferences.forModule(NurseryVisualPanel1.class).put("loaded6", "no");
         ((WizardDescriptor) settings).putProperty("loaded7", "no");
         NbPreferences.forModule(NurseryVisualPanel1.class).put("loaded7", "no");  
         NurseryWizardIterator.traitsAreFilled=false;
         NurseryWizardIterator.constantsAreFilled=false;
         NurseryWizardIterator.isNewList=false;
    }
            
    
}
