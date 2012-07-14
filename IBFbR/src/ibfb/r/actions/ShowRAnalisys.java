package ibfb.r.actions;

import ibfb.r.ui.ScriptsWindowTopComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.windows.WindowManager;

@ActionID(category = "File",
id = "ibfb.r.actions.ShowRAnalisys")

@ActionRegistration(iconBase = "ibfb/r/actions/R16.png",
displayName = "#CTL_ShowRAnalisys")

@ActionReferences({
    @ActionReference(path = "Menu/Tools", position = 0),
    @ActionReference(path = "Toolbars/File", position = -150)
})

public final class ShowRAnalisys implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
         ScriptsWindowTopComponent analisysR =  (ScriptsWindowTopComponent) WindowManager.getDefault().findTopComponent("ScriptsWindowTopComponent");
       
        if (!analisysR.isOpened()) {
            analisysR.open();
        }
        analisysR.requestVisible();
    }

    
}
