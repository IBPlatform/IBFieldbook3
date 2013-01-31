/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.traits.methods;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

@ActionID(category = "Database",
id = "ibfb.traits.methods.MethodsAction")
@ActionRegistration(iconBase = "ibfb/traits/methods/methods16.png",displayName = "#CTL_MethodsAction"
        )
@ActionReferences({
    @ActionReference(path = "Menu/Database", position = 3133),
    @ActionReference(path = "Toolbars/File", position = -180)
})
//@Messages("CTL_MethodsAction=Methods")
public final class MethodsAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        TopComponent explorer = WindowManager.getDefault().findTopComponent("MethodBrowserTopComponent");
        explorer.open();
        explorer.requestActive();
    }
}
