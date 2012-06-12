
package ibfb.ui.actions;

import ibfb.ui.core.JDAbout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;


@ActionID(category = "File",
id = "ibfb.ui.actions.ShowAboutAction")
@ActionRegistration(iconBase = "ibfb/ui/images/about16.png",
displayName = "#CTL_ShowAboutAction")
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1300),
    @ActionReference(path = "Toolbars/File", position = -100)
})
@Messages("CTL_ShowAboutAction=About...")
public final class ShowAboutAction implements ActionListener {
    
    JDAbout about=new JDAbout(null,true);

    @Override
    public void actionPerformed(ActionEvent e) {
      about.setLocationByPlatform(true);
      about.setVisible(true);

    }

    private void llamaBT() {
        /*
        WizardDescriptor.Iterator iterator = new BluetoothWizardIterator();
    WizardDescriptor wizardDescriptor = new WizardDescriptor(iterator);

    wizardDescriptor.setTitleFormat(new MessageFormat("{0} ({1})"));
    wizardDescriptor.setTitle("Your wizard dialog title here");
    Dialog dialog = DialogDisplayer.getDefault().createDialog(wizardDescriptor);
    dialog.setVisible(true);
    dialog.toFront();
    boolean cancelled = wizardDescriptor.getValue() != WizardDescriptor.FINISH_OPTION;
    if (!cancelled) {

    }
    }

  
         * 
         */
        
    }
}
