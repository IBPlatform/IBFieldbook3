package ibfb.studyeditor.designs;

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

public class MacthColumsWizardPanel1 implements WizardDescriptor.Panel<WizardDescriptor>, DocumentListener {

    private MacthColumsVisualPanel1 component;
    private boolean isValid = false;
    private Set<ChangeListener> listeners = new HashSet<ChangeListener>(1);

    @Override
    public MacthColumsVisualPanel1 getComponent() {
        if (component == null) {
            component = new MacthColumsVisualPanel1();
            component.getjTextFieldTrial().getDocument().addDocumentListener(this);
            component.getjTextFieldBlock().getDocument().addDocumentListener(this);
            component.getjTextFieldCol().getDocument().addDocumentListener(this);
            component.getjTextFieldEntry().getDocument().addDocumentListener(this);
            component.getjTextFieldPlot().getDocument().addDocumentListener(this);
            component.getjTextFieldRep().getDocument().addDocumentListener(this);
            component.getjTextFieldRow().getDocument().addDocumentListener(this);
        }
        return component;
    }

    @Override
    public HelpCtx getHelp() {
        // Show no Help button for this panel:
        return HelpCtx.DEFAULT_HELP;
        // If you have context help:
        // return new HelpCtx("help.key.here");
    }

    @Override
    public boolean isValid() {
        return isValid;

    }

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

    private void change() {
        String v1 = component.getjTextFieldTrial().getText();
        String v2 = component.getjTextFieldEntry().getText();
        String v3 = component.getjTextFieldPlot().getText();
        

        if (!v1.isEmpty() && !v2.isEmpty() && !v3.isEmpty()) {
            setValid(true);
        } else {
            setValid(false);
        }
    }

    @Override
    public void readSettings(WizardDescriptor wiz) {
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("TRIAL", "");
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("ENTRY", "");
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("PLOT", "");
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("REP", "");
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("BLOCK", "");
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("ROW", "");
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("COL", "");
        component.loadColumnsIntoList();
        component.loadFieldLabels();
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("TRIAL", component.getTrialString());
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("ENTRY", component.getEntryString());
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("PLOT", component.getPlotString());
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("REP", component.getRepString());   
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("BLOCK", component.getBlockString());   
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("ROW", component.getRowString());   
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("COL", component.getColString());   

//        System.out.println("--------------------------------------------------------");
//        System.out.println("TRIAL: " + component.getTrialString());
//        System.out.println("ENTRY: " + component.getEntryString());             
//        System.out.println("PLOT: " + component.getPlotString());
//        System.out.println("REP: " + component.getRepString());
//        System.out.println("BLOCK: " + component.getBlockString());
//        System.out.println("ROW: " + component.getRowString());
//        System.out.println("COL: " + component.getColString());
//        System.out.println("--------------------------------------------------------");

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
}
