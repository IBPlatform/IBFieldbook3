
package ibfb.inventory.actions;

import ibfb.inventory.core.InventoryViewerTopComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

@ActionID(category = "File",
id = "ibfb.inventory.actions.ViewInventory")
@ActionRegistration(iconBase = "ibfb/inventory/images/inventory_16.png",
displayName = "#CTL_ViewInventory")
@ActionReferences({
    @ActionReference(path = "Menu/Database", position = 3533),
    @ActionReference(path = "Toolbars/File", position =-100)
})

public final class ViewInventory implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
         closeBackground();
        
     TopComponent inventoryTopComponent = WindowManager.getDefault().findTopComponent("InventoryViewerTopComponent");
                if (inventoryTopComponent == null) {
                    inventoryTopComponent = new InventoryViewerTopComponent();
                }
                inventoryTopComponent.open();
                inventoryTopComponent.requestActive();
        
    }
    
     private void closeBackground() {
        TopComponent background = WindowManager.getDefault().findTopComponent("BackgroundWindowTopComponent");
        if (background.isOpened()) {
            background.close();
        }
    }
}
