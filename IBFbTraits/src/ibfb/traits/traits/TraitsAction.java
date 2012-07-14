package ibfb.traits.traits;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

@ActionID(category = "File",
id = "ibfb.traits.traits.TraitsActions")
@ActionRegistration(iconBase = "ibfb/traits/traits/traits16.png",
displayName = "#CTL_TraitsActions")
@ActionReferences({
    @ActionReference(path = "Menu/Database", position = 3333),
    @ActionReference(path = "Toolbars/File", position = -200)
})
public final class TraitsAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        TraitsExplorerTopComponent tetc = null;
        TopComponent traitsExplorer = WindowManager.getDefault().findTopComponent("TraitsExplorerTopComponent");
        if (traitsExplorer == null) {
            traitsExplorer = new TraitsExplorerTopComponent();
            //tetc = (TraitsExplorerTopComponent) traitsExplorer;
            //tetc.updateTraitsTable();
        }
        tetc = (TraitsExplorerTopComponent) traitsExplorer;
        tetc.updateTraitsTable();

        traitsExplorer.open();
        traitsExplorer.requestActive();
    }
}
