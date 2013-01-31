package ibfb.ui.core;

import ibfb.ui.actions.ShowTutorialAction;
import org.cimmyt.cril.ibwb.commongui.OSUtils;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ResourceBundle;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.actions.SystemAction;

@ConvertAsProperties(dtd = "-//ibfb.ui.core//BackgroundWindow//EN",
autostore = false)
@TopComponent.Description(preferredID = "BackgroundWindowTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = true)
@ActionID(category = "Window", id = "ibfb.ui.core.BackgroundWindowTopComponent")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_BackgroundWindowAction",
preferredID = "BackgroundWindowTopComponent")
public final class BackgroundWindowTopComponent extends TopComponent {

    private static final String TUTORIAL_FILE_NAME = "Tutorial_for_using_the_Integrated_Breeding_Fieldbook";
    private static final String PDF_EXT = ".pdf";
    private ResourceBundle bundle = NbBundle.getBundle(BackgroundWindowTopComponent.class);

    public BackgroundWindowTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(BackgroundWindowTopComponent.class, "CTL_BackgroundWindowTopComponent"));
        setToolTipText(NbBundle.getMessage(BackgroundWindowTopComponent.class, "HINT_BackgroundWindowTopComponent"));
        jLabel1.setText(bundle.getString("BackgroundWindowTopComponent.desc"));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(BackgroundWindowTopComponent.class, "BackgroundWindowTopComponent.jButton1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(BackgroundWindowTopComponent.class, "BackgroundWindowTopComponent.jButton2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton3, org.openide.util.NbBundle.getMessage(BackgroundWindowTopComponent.class, "BackgroundWindowTopComponent.jButton3.text")); // NOI18N

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/ui/images/splash.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(BackgroundWindowTopComponent.class, "BackgroundWindowTopComponent.jLabel6.text")); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(BackgroundWindowTopComponent.class, "BackgroundWindowTopComponent.jLabel1.text")); // NOI18N

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/ui/images/tutorialsmall.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButton4, org.openide.util.NbBundle.getMessage(BackgroundWindowTopComponent.class, "BackgroundWindowTopComponent.jButton4.text")); // NOI18N
        jButton4.setToolTipText(org.openide.util.NbBundle.getMessage(BackgroundWindowTopComponent.class, "BackgroundWindowTopComponent.jButton4.toolTipText")); // NOI18N
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(208, 208, 208))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 819, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(jButton4)))
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(236, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        openTutorial();
    }//GEN-LAST:event_jButton4ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
    }

    @Override
    public void componentClosed() {
    }

    void writeProperties(java.util.Properties p) {

        p.setProperty("version", "1.0");

    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
    }

    private void openTutorial() {
        
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
