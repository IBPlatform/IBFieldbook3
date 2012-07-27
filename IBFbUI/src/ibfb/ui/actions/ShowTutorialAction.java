package ibfb.ui.actions;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ResourceBundle;
import org.cimmyt.cril.ibwb.commongui.OSUtils;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle;

@ActionID(category = "Help",
id = "ibfb.ui.actions.ShowTutorialAction")
@ActionRegistration(displayName = "#CTL_ShowTutorialAction")
@ActionReferences({
    @ActionReference(path = "Menu/Help", position = 3333)
})
public final class ShowTutorialAction implements ActionListener {

    private static final String TUTORIAL_FILE_NAME = "Tutorial_for_using_the_Integrated_Breeding_Fieldbook";
    private static final String PDF_EXT = ".pdf";
    private ResourceBundle bundle = NbBundle.getBundle(ShowTutorialAction.class);

    @Override
    public void actionPerformed(ActionEvent e) {
        String language = System.getProperty("user.language");
        String pdfFileName = OSUtils.getDocumentsPath() + File.separator + TUTORIAL_FILE_NAME + PDF_EXT;
        if (language.equals("es")) {
            pdfFileName = OSUtils.getDocumentsPath() + File.separator + TUTORIAL_FILE_NAME + "_es" + PDF_EXT;
        }

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
