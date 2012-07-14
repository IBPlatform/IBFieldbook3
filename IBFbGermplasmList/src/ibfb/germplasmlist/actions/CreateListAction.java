
package ibfb.germplasmlist.actions;

import ibfb.germplasmlist.core.germplasmListTopComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

@ActionID(category = "File",
id = "ibfb.germplasmlist.actions.CreateListAction")
@ActionRegistration(iconBase = "ibfb/germplasmlist/images/germplasmIcon16.png",
displayName = "#CTL_CreateListAction")
@ActionReferences({
    @ActionReference(path = "Menu/Study", position = 1262),
    @ActionReference(path = "Toolbars/File", position = -288)
})

public final class CreateListAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        closeBackground();
        
     TopComponent newListTopComponent = WindowManager.getDefault().findTopComponent("addChecksTopComponent");
                if (newListTopComponent == null) {
                    newListTopComponent = new germplasmListTopComponent();
                }
                newListTopComponent.open();
                newListTopComponent.requestActive();
    }
    
     private void closeBackground() {
        TopComponent background = WindowManager.getDefault().findTopComponent("BackgroundWindowTopComponent");
        if (background.isOpened()) {
            background.close();
        }
    }
}
