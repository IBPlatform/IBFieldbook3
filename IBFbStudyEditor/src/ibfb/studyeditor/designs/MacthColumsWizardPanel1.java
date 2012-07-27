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
        String v2 = component.getjTextFieldBlock().getText();
        String v3 = component.getjTextFieldCol().getText();
        String v4 = component.getjTextFieldEntry().getText();
        String v5 = component.getjTextFieldPlot().getText();
        String v6 = component.getjTextFieldRep().getText();
        String v7 = component.getjTextFieldRow().getText();

        if (!v1.isEmpty() && !v2.isEmpty() && !v3.isEmpty() && !v4.isEmpty() && !v5.isEmpty() && !v6.isEmpty() && !v7.isEmpty()) {
            setValid(true);
        } else {
            setValid(false);
        }
    }

    @Override
    public void readSettings(WizardDescriptor wiz) {
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("TRIAL", "TRIAL");
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("ENTRY", "ENTRY");
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("PLOT", "PLOT");
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("REP", "REP");
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("BLOCK", "BLOCK");
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("ROW", "ROW");
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("COL", "COLUMN");
        component.loadColumnsIntoList();
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("TRIAL", component.getjTextFieldTrial().getText());
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("ENTRY", component.getjTextFieldEntry().getText());
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("PLOT", component.getjTextFieldPlot().getText());
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("REP", component.getjTextFieldRep().getText());
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("BLOCK", component.getjTextFieldBlock().getText());
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("ROW", component.getjTextFieldRow().getText());
        NbPreferences.forModule(MacthColumsWizardPanel1.class).put("COL", component.getjTextFieldCol().getText());
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
