/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.traits.scales;

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
id = "ibfb.traits.scales.ScalesBrowseAction")
@ActionRegistration(iconBase = "ibfb/traits/scales/scales16.png",
displayName = "#CTL_ScalesBrowseAction")
@ActionReferences({
    @ActionReference(path = "Menu/Database", position = 3233),
    @ActionReference(path = "Toolbars/File", position = -180)
})
//@Messages("CTL_ScalesBrowseAction=Scales")
public final class ScalesBrowseAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        TopComponent traitsExplorer = WindowManager.getDefault().findTopComponent("ScalesBrowserTopComponent");
     traitsExplorer.open();
     traitsExplorer.requestActive();
    }
}
