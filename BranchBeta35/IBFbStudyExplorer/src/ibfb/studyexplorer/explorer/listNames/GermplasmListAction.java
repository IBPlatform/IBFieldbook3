
package ibfb.studyexplorer.explorer.listNames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

@ActionID(category = "Edit",
id = "ibfb.studyexplorer.explorer.listNames.GermplasmListAction")
@ActionRegistration(iconBase = "ibfb/studyexplorer/images/listnms16.png",
displayName = "#CTL_GermplasmListAction")
@ActionReferences({
    @ActionReference(path = "Menu/Database", position = 3433),
    @ActionReference(path = "Toolbars/File", position = -180)
})
public final class GermplasmListAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      TopComponent ListExplorer = WindowManager.getDefault().findTopComponent("ListNamesExplorerTopComponent");
       ListNamesExplorerTopComponent lnetc = (ListNamesExplorerTopComponent)ListExplorer;
       lnetc.updateListNameTable();
       ListExplorer.open();
       ListExplorer.requestActive();
    }
}
