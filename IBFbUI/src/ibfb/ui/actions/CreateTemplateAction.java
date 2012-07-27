
package ibfb.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

@ActionID(category = "File",
id = "ibfb.ui.actions.CreateTemplateAction")
@ActionRegistration(iconBase = "ibfb/ui/actions/wTemplate_16.png",
displayName = "#CTL_CreateTemplateAction")
@ActionReferences({
    @ActionReference(path = "Menu/Tools", position = 50)
})
@Messages("CTL_CreateTemplateAction=Create Workbook Template ")
public final class CreateTemplateAction implements ActionListener {

   
    
    @Override
    public void actionPerformed(ActionEvent e) {
        TopComponent templateEditor = WindowManager.getDefault().findTopComponent("CreateTemplateTopComponent");
               
                    templateEditor.open();
                   
                    
                     if (!templateEditor.isVisible()) {
            templateEditor.setVisible(true);
        }


        templateEditor.requestVisible();
                

    }
    
}
