/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.settings.folder.panels;

import ibfb.settings.core.FieldbookSettings;

import java.io.File;
import org.cimmyt.cril.ibwb.commongui.FileUtils;
import org.cimmyt.cril.ibwb.commongui.OSUtils;

public final class TemplatesandListsFoldersPanel extends javax.swing.JPanel {

    private final TemplatesandListsFoldersOptionsPanelController controller;

    TemplatesandListsFoldersPanel(TemplatesandListsFoldersOptionsPanelController controller) {
        this.controller = controller;
        initComponents();

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnSearchTempExpert = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPathTempExpert = new javax.swing.JTextArea();
        btnUseDefault = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnSearchGSMExpert = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPathGSMExpert = new javax.swing.JTextArea();
        btnUseDefaultGersmplasmFolder = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.jPanel2.border.title"))); // NOI18N
        jPanel2.setToolTipText(org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.jPanel2.toolTipText")); // NOI18N
        jPanel2.setMaximumSize(null);
        jPanel2.setPreferredSize(new java.awt.Dimension(550, 175));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/settings/core/images/templates.png"))); // NOI18N
        jLabel1.setToolTipText(org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.jLabel1.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnSearchTempExpert, org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.btnSearchTempExpert.text")); // NOI18N
        btnSearchTempExpert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchTempExpertActionPerformed(evt);
            }
        });

        txtPathTempExpert.setColumns(20);
        txtPathTempExpert.setEditable(false);
        txtPathTempExpert.setLineWrap(true);
        txtPathTempExpert.setRows(5);
        txtPathTempExpert.setMaximumSize(new java.awt.Dimension(240, 80));
        txtPathTempExpert.setMinimumSize(new java.awt.Dimension(240, 80));
        txtPathTempExpert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtPathTempExpertMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtPathTempExpert);

        org.openide.awt.Mnemonics.setLocalizedText(btnUseDefault, org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.btnUseDefault.text")); // NOI18N
        btnUseDefault.setToolTipText(org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.btnUseDefault.toolTipText")); // NOI18N
        btnUseDefault.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUseDefaultActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSearchTempExpert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUseDefault, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(btnSearchTempExpert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUseDefault))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.jPanel3.border.title"))); // NOI18N
        jPanel3.setToolTipText(org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.jPanel3.toolTipText")); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/settings/core/images/germplasm.png"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnSearchGSMExpert, org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.btnSearchGSMExpert.text")); // NOI18N
        btnSearchGSMExpert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchGSMExpertActionPerformed(evt);
            }
        });

        txtPathGSMExpert.setColumns(20);
        txtPathGSMExpert.setEditable(false);
        txtPathGSMExpert.setLineWrap(true);
        txtPathGSMExpert.setRows(5);
        txtPathGSMExpert.setMaximumSize(null);
        txtPathGSMExpert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtPathGSMExpertMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(txtPathGSMExpert);

        org.openide.awt.Mnemonics.setLocalizedText(btnUseDefaultGersmplasmFolder, org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.btnUseDefaultGersmplasmFolder.text")); // NOI18N
        btnUseDefaultGersmplasmFolder.setToolTipText(org.openide.util.NbBundle.getMessage(TemplatesandListsFoldersPanel.class, "TemplatesandListsFoldersPanel.btnUseDefaultGersmplasmFolder.toolTipText")); // NOI18N
        btnUseDefaultGersmplasmFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUseDefaultGersmplasmFolderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSearchGSMExpert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUseDefaultGersmplasmFolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearchGSMExpert)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnUseDefaultGersmplasmFolder)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchTempExpertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchTempExpertActionPerformed
        selectFolderForTemplates();
}//GEN-LAST:event_btnSearchTempExpertActionPerformed

    private void txtPathTempExpertMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPathTempExpertMousePressed
        selectFolderForTemplates();

}//GEN-LAST:event_txtPathTempExpertMousePressed

    private void btnUseDefaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUseDefaultActionPerformed
        txtPathTempExpert.setText(OSUtils.getTemplatesPath());
}//GEN-LAST:event_btnUseDefaultActionPerformed

    private void btnSearchGSMExpertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchGSMExpertActionPerformed
        selectFolderForGermplasmLists();
    }//GEN-LAST:event_btnSearchGSMExpertActionPerformed

    private void txtPathGSMExpertMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPathGSMExpertMousePressed
        selectFolderForGermplasmLists();
}//GEN-LAST:event_txtPathGSMExpertMousePressed

    private void btnUseDefaultGersmplasmFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUseDefaultGersmplasmFolderActionPerformed
        this.txtPathGSMExpert.setText(OSUtils.getGermplasmListsPath());
    }//GEN-LAST:event_btnUseDefaultGersmplasmFolderActionPerformed

    void load() {
        // TODO read settings and initialize GUI
        // Example:        
        // someCheckBox.setSelected(Preferences.userNodeForPackage(TemplatesandListsFoldersPanel.class).getBoolean("someFlag", false));
        // or for org.openide.util with API spec. version >= 7.4:
        // someCheckBox.setSelected(NbPreferences.forModule(TemplatesandListsFoldersPanel.class).getBoolean("someFlag", false));
        // or:
        // someTextField.setText(SomeSystemOption.getDefault().getSomeStringProperty());
        txtPathGSMExpert.setText(FieldbookSettings.getSetting(FieldbookSettings.GERMPLASM_LIST_DEFAULT_FOLDER, ""));
        txtPathTempExpert.setText(FieldbookSettings.getSetting(FieldbookSettings.TEMPLATES_DEFAULT_FOLDER, ""));
        
    }

    void store() {
        // TODO store modified settings
        // Example:
        // Preferences.userNodeForPackage(TemplatesandListsFoldersPanel.class).putBoolean("someFlag", someCheckBox.isSelected());
        // or for org.openide.util with API spec. version >= 7.4:
        // NbPreferences.forModule(TemplatesandListsFoldersPanel.class).putBoolean("someFlag", someCheckBox.isSelected());
        // or:
        // SomeSystemOption.getDefault().setSomeStringProperty(someTextField.getText());
        FieldbookSettings.setSetting(FieldbookSettings.TEMPLATES_DEFAULT_FOLDER, txtPathTempExpert.getText());
        FieldbookSettings.setSetting(FieldbookSettings.GERMPLASM_LIST_DEFAULT_FOLDER, txtPathGSMExpert.getText());
    }

    boolean valid() {
        // TODO check whether form is consistent and complete
        return true;
    }

    private void selectFolderForTemplates() {
        txtPathTempExpert.setText(FileUtils.getSelectedFolder(txtPathTempExpert.getText()));
    }

    private void selectFolderForGermplasmLists() {
        txtPathGSMExpert.setText(FileUtils.getSelectedFolder(txtPathGSMExpert.getText()));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearchGSMExpert;
    private javax.swing.JButton btnSearchTempExpert;
    private javax.swing.JButton btnUseDefault;
    private javax.swing.JButton btnUseDefaultGersmplasmFolder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtPathGSMExpert;
    private javax.swing.JTextArea txtPathTempExpert;
    // End of variables declaration//GEN-END:variables
}
