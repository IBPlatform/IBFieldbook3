
package ibfb.studyeditor.importwizard;

import ibfb.studyeditor.core.StudyEditorTopComponent;
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
import org.openide.windows.WindowManager;

public class importingWizardPanel2 implements WizardDescriptor.Panel,DocumentListener {

    private importingVisualPanel2 component;

    private boolean isValid = false;
    private Set<ChangeListener> listeners = new HashSet<ChangeListener>(1);
   
    
    
    @Override
    public Component getComponent() {
        if (component == null) {
            component = new importingVisualPanel2();
             component.jTextArea1.getDocument().addDocumentListener(this);
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
       StudyEditorTopComponent studyEditor = (StudyEditorTopComponent) WindowManager.getDefault().getRegistry().getActivated();
       component.setOpcionFiltro(studyEditor.opcionImport);
      
    }

    @Override
    public void storeSettings(Object settings) {

        StudyEditorTopComponent studyEditor = (StudyEditorTopComponent) WindowManager.getDefault().getRegistry().getActivated();
        studyEditor.trialImportFile = component.getArchivo();

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

    private void change() {
        String file = component.jTextArea1.getText();
        boolean isValidInput = file.isEmpty();

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
