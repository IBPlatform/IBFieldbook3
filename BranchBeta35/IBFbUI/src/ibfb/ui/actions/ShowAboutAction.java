
package ibfb.ui.actions;

import ibfb.ui.core.JDAbout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle;


@ActionID(category = "File",
id = "ibfb.ui.actions.ShowAboutAction")
@ActionRegistration(iconBase = "ibfb/ui/images/about16.png",
displayName = "#CTL_ShowAboutAction")
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1300),
    @ActionReference(path = "Toolbars/File", position = -100)
})

public final class ShowAboutAction implements ActionListener {
    
    private ResourceBundle bundle = NbBundle.getBundle(ShowAboutAction.class);
    JDAbout about=new JDAbout(null,true);

    @Override
    public void actionPerformed(ActionEvent e) {
      about.setLocationByPlatform(true);
      about.setVisible(true);

    }


}
