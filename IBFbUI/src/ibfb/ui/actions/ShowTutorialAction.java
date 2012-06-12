/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.ui.actions;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import org.cimmyt.cril.ibwb.commongui.OSUtils;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "Help",
id = "ibfb.ui.actions.ShowTutorialAction")
@ActionRegistration(displayName = "#CTL_ShowTutorialAction")
@ActionReferences({
    @ActionReference(path = "Menu/Help", position = 3333)
})
@Messages("CTL_ShowTutorialAction=Show Tutorial")
public final class ShowTutorialAction implements ActionListener {

    public void actionPerformed(ActionEvent e) {
         String pdfFileName = OSUtils.getDocumentsPath()+File.separator + "Tutorial_for_using_the_Integrated_Breeding_Fieldbook.pdf";
        try {

            File pdfFile = new File(pdfFileName);
            if (pdfFile.exists()) {

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("Awt Desktop is not supported!");
                }

            } else {
                System.out.println("File is not exists!");
            }

            System.out.println("Done");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
