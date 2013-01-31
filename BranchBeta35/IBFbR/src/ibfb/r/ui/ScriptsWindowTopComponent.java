package ibfb.r.ui;

import ibfb.r.core.MuestraEspera_Thread;
import ibfb.r.core.RforMac;
import ibfb.r.core.RforWin;
import ibfb.r.filters.CSVFiltro;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.cimmyt.cril.ibwb.commongui.OSUtils;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.validation.api.Problem;
import org.netbeans.validation.api.builtin.Validators;
import org.netbeans.validation.api.ui.ValidationGroup;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Exceptions;

@ConvertAsProperties(dtd = "-//ibfb.r.ui//ScriptsWindow//EN",
autostore = false)
@TopComponent.Description(preferredID = "ScriptsWindowTopComponent",
iconBase = "ibfb/r/images/R16.png",
persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "ibfb.r.ui.ScriptsWindowTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_ScriptsWindowAction",
preferredID = "ScriptsWindowTopComponent")
public final class ScriptsWindowTopComponent extends TopComponent {

    private JFileChooser selectorArchivo = new JFileChooser();
    private File nombreArchivo = null;
    public static RforWin hiloEjecutaR;
//    public static RforMac hiloEjecutaRforMac;
    public static MuestraEspera_Thread hiloEspera;
    String osName = System.getProperty("os.name").toLowerCase();
    boolean isMacOs = osName.startsWith("mac os x");
    ValidationGroup group = null;
     private String pathRHOME = "/Applications/IBFIELDBOOK";
    
 //   Thread rformac;
 //   private String currentDirectory;
    boolean resourcesDirectoryExists = false;
    
    DocumentListener doc = new DocumentListener() {

        @Override
        public void insertUpdate(DocumentEvent de) {
            checkValidation(group.validateAll());
        }

        @Override
        public void removeUpdate(DocumentEvent de) {
            checkValidation(group.validateAll());
        }

        @Override
        public void changedUpdate(DocumentEvent de) {
            checkValidation(group.validateAll());
        }
    };

    public ScriptsWindowTopComponent() {
        initComponents();

        setName(NbBundle.getMessage(ScriptsWindowTopComponent.class, "CTL_ScriptsWindowTopComponent"));
        setToolTipText(NbBundle.getMessage(ScriptsWindowTopComponent.class, "HINT_ScriptsWindowTopComponent"));
        resourcesDirectoryExists = resourcesDirExists();
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButtonBrowseScriptR = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaScriptR = new javax.swing.JTextArea();
        jComboBoxAnalysisR = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButtonOkR = new javax.swing.JButton();
        jButtonCancelR = new javax.swing.JButton();
        validationPanel1 = new org.netbeans.validation.api.ui.ValidationPanel();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/r/images/R.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(ScriptsWindowTopComponent.class, "ScriptsWindowTopComponent.jLabel1.text_1")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(ScriptsWindowTopComponent.class, "ScriptsWindowTopComponent.jLabel2.text")); // NOI18N

        jLabel4.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 24)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/r/images/IBtools.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(ScriptsWindowTopComponent.class, "ScriptsWindowTopComponent.jLabel4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButtonBrowseScriptR, org.openide.util.NbBundle.getMessage(ScriptsWindowTopComponent.class, "ScriptsWindowTopComponent.jButtonBrowseScriptR.text")); // NOI18N
        jButtonBrowseScriptR.setToolTipText(org.openide.util.NbBundle.getMessage(ScriptsWindowTopComponent.class, "ScriptsWindowTopComponent.jButtonBrowseScriptR.toolTipText")); // NOI18N
        jButtonBrowseScriptR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBrowseScriptRActionPerformed(evt);
            }
        });

        jTextAreaScriptR.setColumns(20);
        jTextAreaScriptR.setEditable(false);
        jTextAreaScriptR.setLineWrap(true);
        jTextAreaScriptR.setRows(5);
        jTextAreaScriptR.setToolTipText(org.openide.util.NbBundle.getMessage(ScriptsWindowTopComponent.class, "ScriptsWindowTopComponent.jTextAreaScriptR.toolTipText")); // NOI18N
        jTextAreaScriptR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextAreaScriptRMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextAreaScriptR);

        jComboBoxAnalysisR.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jComboBoxAnalysisR.setMaximumRowCount(10);
        jComboBoxAnalysisR.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "One Site", "Multi Location", "Greg Ammi Multi Location", "Line by tester" }));
        jComboBoxAnalysisR.setToolTipText(org.openide.util.NbBundle.getMessage(ScriptsWindowTopComponent.class, "ScriptsWindowTopComponent.jComboBoxAnalysisR.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(ScriptsWindowTopComponent.class, "ScriptsWindowTopComponent.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(ScriptsWindowTopComponent.class, "ScriptsWindowTopComponent.jLabel5.text")); // NOI18N

        jButtonOkR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/r/images/ok.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonOkR, org.openide.util.NbBundle.getMessage(ScriptsWindowTopComponent.class, "ScriptsWindowTopComponent.jButtonOkR.text")); // NOI18N
        jButtonOkR.setToolTipText(org.openide.util.NbBundle.getMessage(ScriptsWindowTopComponent.class, "ScriptsWindowTopComponent.jButtonOkR.toolTipText")); // NOI18N
        jButtonOkR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkRActionPerformed(evt);
            }
        });

        jButtonCancelR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/r/images/cancel.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonCancelR, org.openide.util.NbBundle.getMessage(ScriptsWindowTopComponent.class, "ScriptsWindowTopComponent.jButtonCancelR.text")); // NOI18N
        jButtonCancelR.setToolTipText(org.openide.util.NbBundle.getMessage(ScriptsWindowTopComponent.class, "ScriptsWindowTopComponent.jButtonCancelR.toolTipText")); // NOI18N
        jButtonCancelR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jButtonCancelR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
                .addComponent(jButtonOkR)
                .addGap(54, 54, 54))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonOkR)
                    .addComponent(jButtonCancelR))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxAnalysisR, 0, 436, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                            .addComponent(validationPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jButtonBrowseScriptR, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)))
                .addGap(63, 63, 63))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1))
                    .addComponent(jLabel4))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBoxAnalysisR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jButtonBrowseScriptR, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(validationPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBrowseScriptRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBrowseScriptRActionPerformed
        openCSVFile();
}//GEN-LAST:event_jButtonBrowseScriptRActionPerformed

    private void jTextAreaScriptRMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaScriptRMousePressed
        openCSVFile();
}//GEN-LAST:event_jTextAreaScriptRMousePressed

    private void jButtonOkRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkRActionPerformed
        Thread rformac;
        RforMac hiloEjecutaRforMac;
        System.out.println("jButtonOkRActionPerformed: OK button pressed");
        if (this.jTextAreaScriptR.getText().isEmpty()) {
            DialogUtil.displayError("Select your CSV file");
            return;
        } else {
            if (isMacOs) {
                
                hiloEjecutaRforMac = new RforMac();
                hiloEjecutaRforMac.setScript(this.jComboBoxAnalysisR.getSelectedIndex() + 1);
                hiloEjecutaRforMac.setFile(nombreArchivo.toString());

                if (resourcesDirectoryExists) {
                    hiloEjecutaRforMac.start();
                    hiloEspera = new MuestraEspera_Thread();
                    hiloEspera.start();
                    return;
                } else {
                    rformac = new Thread(hiloEjecutaRforMac);
                    rformac.start();
                }

            } else {
                hiloEjecutaR = new RforWin();
                hiloEjecutaR.setScript(this.jComboBoxAnalysisR.getSelectedIndex() + 1);
                hiloEspera = new MuestraEspera_Thread();
                hiloEjecutaR.setFile(nombreArchivo.toString());
                hiloEjecutaR.start();
                hiloEspera.start();

            }
        }
}//GEN-LAST:event_jButtonOkRActionPerformed

    private void jButtonCancelRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelRActionPerformed
        if (this.isOpened()) {
            this.close();

        }
}//GEN-LAST:event_jButtonCancelRActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBrowseScriptR;
    private javax.swing.JButton jButtonCancelR;
    private javax.swing.JButton jButtonOkR;
    public javax.swing.JComboBox jComboBoxAnalysisR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea jTextAreaScriptR;
    private org.netbeans.validation.api.ui.ValidationPanel validationPanel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        this.jTextAreaScriptR.setText("");
        configPanel();
        checkValidation(group.validateAll());


    }

    @Override
    public void componentClosed() {

        this.jTextAreaScriptR.setText("X");
        checkValidation(group.validateAll());

    }

    void writeProperties(java.util.Properties p) {

        p.setProperty("version", "1.0");

    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");

    }

    private void openCSVFile() {
        FileFilter[] filtros = new FileFilter[10];
        filtros = selectorArchivo.getChoosableFileFilters();
        System.out.println("root: " + System.getenv("systemroot"));

        String path = "";

        if (isMacOs) {
            path = File.separator + "Applications" + File.separator + "IBFIELDBOOK" + File.separator + "Examples" + File.separator + "AnalysisR";


        } else {
            path = "C:" + File.separator + "Program Files" + File.separator + "IBFIELDBOOK" + File.separator + "Examples" + File.separator + "AnalysisR";
        }

        for (int i = 0; i < filtros.length; i++) {
            FileFilter filtro = filtros[i];
            selectorArchivo.removeChoosableFileFilter(filtro);
        }


        //File myDesktop = new File(path);
        File myDesktop = new File(OSUtils.getRSamplesPath());

        if (myDesktop.exists()) {
            selectorArchivo.setCurrentDirectory(myDesktop);
        } else {
            File archivoNulo = new File("");
            selectorArchivo.setSelectedFile(archivoNulo);
        }


        selectorArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        selectorArchivo.addChoosableFileFilter(new CSVFiltro());
        int resultado = selectorArchivo.showOpenDialog(null);

        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }
        this.nombreArchivo = selectorArchivo.getSelectedFile();
        this.jTextAreaScriptR.setText(selectorArchivo.getSelectedFile().toString());

    }

   
  @SuppressWarnings("unchecked")   
    private void configPanel() {

        jButtonOkR.setEnabled(false);

        group = validationPanel1.getValidationGroup();

        jTextAreaScriptR.setName("CVS file");

//        group.add(jTextAreaScriptR, Validators.REQUIRE_NON_EMPTY_STRING,
//                Validators.NO_WHITESPACE);

        group.add(jTextAreaScriptR, Validators.REQUIRE_NON_EMPTY_STRING);
        
        jTextAreaScriptR.getDocument().addDocumentListener(doc);

    }

    private void checkValidation(Problem problema) {

        if (problema.isFatal()) {
            jButtonOkR.setEnabled(false);
        } else {
            jButtonOkR.setEnabled(true);
        }


    }
    
    private boolean resourcesDirExists() {
        boolean ozielDirectoryExists = false;
        File ozielDirectory = new File("/Users/ozieluz/Desktop/RforMac/Resources");
        if (ozielDirectory.exists() && ozielDirectory.isDirectory()) {
            ozielDirectoryExists = true;
        }
        return ozielDirectoryExists;
    }
    private void setRHome2() {
        
        String RHome2 = "/Applications/IBFIELDBOOK/RforMac/setRHome.sh";
        String RHome = "/Applications/IBFIELDBOOK/RforMac/Resources";
       Map<String,String> mp=new HashMap<String, String>();
        mp.put("R_HOME", RHome);
       
        try {
            set(mp);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }
    
    
    
    private void setRHome(){
    try {
            System.out.println("Se esta definiendo RHOME...");


            String[] data = new String[3];
            data[0] = "/bin/sh";
            data[1] = "-c";            
             data[2] = "#!/bin/sh \n cd " + pathRHOME +" \n sh setRHome";
            Process p = Runtime.getRuntime().exec(data);
            p.waitFor();

            InputStream output = p.getInputStream();
            System.out.println(output);

            p.waitFor();
            System.out.println("Finalizo set rhome...");


        } catch (Exception er) {
            System.out.println("Error en RHOME mac" + er);
        }
    
}
    
    
    public static void set(Map<String, String> newenv) throws Exception {      
    Class[] classes = Collections.class.getDeclaredClasses();
    Map<String, String> env = System.getenv();
    for(Class cl : classes) {
        if("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
            Field field = cl.getDeclaredField("m");
            field.setAccessible(true);
            Object obj = field.get(env);
            Map<String, String> map = (Map<String, String>) obj;
            map.clear();
            map.putAll(newenv);
        }
    }
}
}
