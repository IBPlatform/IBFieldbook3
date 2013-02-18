package ibfb.nursery.advancewizard;

import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbPreferences;

public class AdvanceWizardPanel1 implements WizardDescriptor.Panel {

    /**
     * Select from each plot will be enabled?
     */
    private boolean selectFromEachPlot;

    public AdvanceWizardPanel1(boolean selectFromEachPlot) {
        this.selectFromEachPlot = selectFromEachPlot;
    }
    private AdvanceVisualPanel1 component;

    @Override
    public AdvanceVisualPanel1 getComponent() {
        if (component == null) {
            component = new AdvanceVisualPanel1(selectFromEachPlot);
        }
       
        return component;
    }

    @Override
    public HelpCtx getHelp() {
        return HelpCtx.DEFAULT_HELP;

    }

    @Override
    public boolean isValid() {

        return true;
    }

    @Override
    public void addChangeListener(ChangeListener l) {
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
    }

    @Override
    public void readSettings(Object settings) {
        component.setDefaultLocation();

    }

    @Override
    public void storeSettings(Object settings) {
        NbPreferences.forModule(AdvanceWizardPanel1.class).put("methodIndex", String.valueOf(component.getMethodIndex()));
        NbPreferences.forModule(AdvanceWizardPanel1.class).put("samples", String.valueOf(component.getSamples()));
        NbPreferences.forModule(AdvanceWizardPanel1.class).put("samplesMethod", String.valueOf(component.getsSamplesMethod()));
        NbPreferences.forModule(AdvanceWizardPanel1.class).putInt("MethodId", component.getSelectedMethod().getMid());
        NbPreferences.forModule(AdvanceWizardPanel1.class).putInt("LocationId", component.getSelectedLocation().getLocid());
        NbPreferences.forModule(AdvanceWizardPanel1.class).putInt("HarvestDate", component.getHarvestDate());
        NbPreferences.forModule(AdvanceWizardPanel1.class).putInt("NumberOfParents", component.getSelectedMethod().getMprgn());
        // location abbreviature
        NbPreferences.forModule(AdvanceWizardPanel1.class).put("LAbbr", component.getLAbbr());


    }
}
