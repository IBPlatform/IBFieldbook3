package ibfb.studyexplorer.explorer.listNames;

import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.api.Services;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.cimmyt.cril.ibwb.commongui.TableBindingUtil;
import org.cimmyt.cril.ibwb.domain.Listnms;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//ibfb.studyexplorer.explorer.listNames//ListNamesExplorer//EN",
autostore = false)
@TopComponent.Description(preferredID = "ListNamesExplorerTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "explorer", openAtStartup = false)
@ActionID(category = "Window", id = "ibfb.studyexplorer.explorer.listNames.ListNamesExplorerTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_ListNamesExplorerAction",
preferredID = "ListNamesExplorerTopComponent")
public final class ListNamesExplorerTopComponent extends TopComponent {
    private ResourceBundle bundle = NbBundle.getBundle(ListNamesExplorerTopComponent.class);
    private List<Listnms> germplamList;
    
    public ListNamesExplorerTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(ListNamesExplorerTopComponent.class, "CTL_ListNamesExplorerTopComponent"));
        setToolTipText(NbBundle.getMessage(ListNamesExplorerTopComponent.class, "HINT_ListNamesExplorerTopComponent"));
        //updateListNameTable();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popMnu = new javax.swing.JPopupMenu();
        pMnuOpenList = new javax.swing.JMenuItem();
        pMnuNew = new javax.swing.JMenuItem();
        pMnuEdit = new javax.swing.JMenuItem();
        pMnuDelete = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGermplasmList = new javax.swing.JTable();
        btnOpenData = new javax.swing.JButton();
        lblListsFound = new javax.swing.JLabel();
        tblBarMenu = new javax.swing.JToolBar();
        btnOpenList = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        lblSearch = new javax.swing.JLabel();

        pMnuOpenList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyexplorer/explorer/listNames/open.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(pMnuOpenList, org.openide.util.NbBundle.getMessage(ListNamesExplorerTopComponent.class, "ListNamesExplorerTopComponent.pMnuOpenList.text")); // NOI18N
        pMnuOpenList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pMnuOpenListActionPerformed(evt);
            }
        });
        popMnu.add(pMnuOpenList);

        pMnuNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyexplorer/explorer/listNames/new.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(pMnuNew, org.openide.util.NbBundle.getMessage(ListNamesExplorerTopComponent.class, "ListNamesExplorerTopComponent.pMnuNew.text")); // NOI18N
        popMnu.add(pMnuNew);

        pMnuEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyexplorer/explorer/listNames/edit.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(pMnuEdit, org.openide.util.NbBundle.getMessage(ListNamesExplorerTopComponent.class, "ListNamesExplorerTopComponent.pMnuEdit.text")); // NOI18N
        popMnu.add(pMnuEdit);

        pMnuDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyexplorer/explorer/listNames/delete.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(pMnuDelete, org.openide.util.NbBundle.getMessage(ListNamesExplorerTopComponent.class, "ListNamesExplorerTopComponent.pMnuDelete.text")); // NOI18N
        pMnuDelete.setToolTipText(org.openide.util.NbBundle.getMessage(ListNamesExplorerTopComponent.class, "ListNamesExplorerTopComponent.pMnuDelete.toolTipText")); // NOI18N
        pMnuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pMnuDeleteActionPerformed(evt);
            }
        });
        popMnu.add(pMnuDelete);

        tblGermplasmList.setComponentPopupMenu(popMnu);
        tblGermplasmList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGermplasmListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGermplasmList);

        org.openide.awt.Mnemonics.setLocalizedText(btnOpenData, org.openide.util.NbBundle.getMessage(ListNamesExplorerTopComponent.class, "ListNamesExplorerTopComponent.btnOpenData.text")); // NOI18N
        btnOpenData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenDataActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lblListsFound, org.openide.util.NbBundle.getMessage(ListNamesExplorerTopComponent.class, "ListNamesExplorerTopComponent.lblListsFound.text")); // NOI18N

        tblBarMenu.setFloatable(false);
        tblBarMenu.setRollover(true);

        btnOpenList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyexplorer/explorer/listNames/open.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnOpenList, org.openide.util.NbBundle.getMessage(ListNamesExplorerTopComponent.class, "ListNamesExplorerTopComponent.btnOpenList.text")); // NOI18N
        btnOpenList.setToolTipText(org.openide.util.NbBundle.getMessage(ListNamesExplorerTopComponent.class, "ListNamesExplorerTopComponent.btnOpenList.toolTipText")); // NOI18N
        btnOpenList.setFocusable(false);
        btnOpenList.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOpenList.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnOpenList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenListActionPerformed(evt);
            }
        });
        tblBarMenu.add(btnOpenList);

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyexplorer/explorer/listNames/new.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnNew, org.openide.util.NbBundle.getMessage(ListNamesExplorerTopComponent.class, "ListNamesExplorerTopComponent.btnNew.text")); // NOI18N
        btnNew.setToolTipText(org.openide.util.NbBundle.getMessage(ListNamesExplorerTopComponent.class, "ListNamesExplorerTopComponent.btnNew.toolTipText")); // NOI18N
        btnNew.setFocusable(false);
        btnNew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNew.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        tblBarMenu.add(btnNew);

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyexplorer/explorer/listNames/edit.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnEdit, org.openide.util.NbBundle.getMessage(ListNamesExplorerTopComponent.class, "ListNamesExplorerTopComponent.btnEdit.text")); // NOI18N
        btnEdit.setToolTipText(org.openide.util.NbBundle.getMessage(ListNamesExplorerTopComponent.class, "ListNamesExplorerTopComponent.btnEdit.toolTipText")); // NOI18N
        btnEdit.setFocusable(false);
        btnEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        tblBarMenu.add(btnEdit);

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyexplorer/explorer/listNames/delete.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnDelete, org.openide.util.NbBundle.getMessage(ListNamesExplorerTopComponent.class, "ListNamesExplorerTopComponent.btnDelete.text")); // NOI18N
        btnDelete.setToolTipText(org.openide.util.NbBundle.getMessage(ListNamesExplorerTopComponent.class, "ListNamesExplorerTopComponent.btnDelete.toolTipText")); // NOI18N
        btnDelete.setFocusable(false);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        tblBarMenu.add(btnDelete);

        txtSearch.setText(org.openide.util.NbBundle.getMessage(ListNamesExplorerTopComponent.class, "ListNamesExplorerTopComponent.txtSearch.text")); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lblSearch, org.openide.util.NbBundle.getMessage(ListNamesExplorerTopComponent.class, "ListNamesExplorerTopComponent.lblSearch.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblSearch)
                        .addGap(49, 49, 49)
                        .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnOpenData)
                        .addGap(18, 18, 18)
                        .addComponent(lblListsFound)
                        .addContainerGap(284, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tblBarMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                        .addGap(74, 74, 74))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(tblBarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblListsFound)
                    .addComponent(btnOpenData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblGermplasmListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGermplasmListMouseClicked
        if (evt.getClickCount() == 2) {
            openListDataWindow();
        }
}//GEN-LAST:event_tblGermplasmListMouseClicked

    private void btnOpenDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenDataActionPerformed
        openListDataWindow();
}//GEN-LAST:event_btnOpenDataActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        createNew();
}//GEN-LAST:event_btnNewActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        editRecord();
}//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteRecord();
}//GEN-LAST:event_btnDeleteActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        updateListNameTable();
}//GEN-LAST:event_txtSearchKeyReleased

    private void pMnuOpenListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pMnuOpenListActionPerformed
        openListDataWindow();
    }//GEN-LAST:event_pMnuOpenListActionPerformed

    private void btnOpenListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenListActionPerformed
        openListDataWindow();
    }//GEN-LAST:event_btnOpenListActionPerformed

    private void pMnuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pMnuDeleteActionPerformed
        deleteRecord();
    }//GEN-LAST:event_pMnuDeleteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnOpenData;
    private javax.swing.JButton btnOpenList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblListsFound;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JMenuItem pMnuDelete;
    private javax.swing.JMenuItem pMnuEdit;
    private javax.swing.JMenuItem pMnuNew;
    private javax.swing.JMenuItem pMnuOpenList;
    private javax.swing.JPopupMenu popMnu;
    private javax.swing.JToolBar tblBarMenu;
    private javax.swing.JTable tblGermplasmList;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");

    }

    public void updateListNameTable() {
        Listnms filter = new Listnms(true);
        filter.setGlobalsearch(txtSearch.getText());
         germplamList = AppServicesProxy.getDefault().appServices().getListListnms(filter, 0, 0, false);
        lblListsFound.setText(germplamList .size() + " " + NbBundle.getMessage(ListNamesExplorerTopComponent.class, "ListNamesExplorerTopComponent.lblListsFound.text"));
        
        StringBuilder headers = new StringBuilder(bundle.getString("ListNamesExplorerTopComponent.headerListid"));
        headers.append(",");
        headers.append(bundle.getString("ListNamesExplorerTopComponent.headerListname"));
        headers.append(",");
        headers.append(bundle.getString("ListNamesExplorerTopComponent.headerListdesc"));
        
        TableBindingUtil.createColumnsFromDB(Listnms.class, germplamList , tblGermplasmList, "listid,listname,listdesc", headers.toString());
        
    }

    private void openListDataWindow() {
        if (tblGermplasmList.getSelectedRowCount() > 0) {
            Listnms listnms = germplamList.get(tblGermplasmList.getSelectedRow());
            ListDataWindowTopComponent ldwtc = ListDataWindowTopComponent.getListDataWindowTopComponent(listnms);
            if (ldwtc == null) {
                ldwtc = new ListDataWindowTopComponent(listnms);
            }
            ldwtc.open();
            ldwtc.requestActive();
        } else {
            
            JOptionPane.showMessageDialog(this, bundle.getString("ListNamesExplorerTopComponent.selectGermplasmList"),bundle.getString("ListNamesExplorerTopComponent.listRequired"), JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void createNew() {
    }

    private void editRecord() {
    }

    private void deleteRecord() {
         String confirmation =  bundle.getString("ListNamesExplorerTopComponent.deleteConfirmation");
         String title = bundle.getString("ListNamesExplorerTopComponent.deleteTitle");
         if (DialogUtil.displayConfirmation(confirmation,title, NotifyDescriptor.OK_CANCEL_OPTION)) {
             Listnms listnms = germplamList.get(tblGermplasmList.getSelectedRow()); 
             AppServicesProxy.getDefault().appServices().deleteListnms(listnms);
             updateListNameTable();
         }
    }
}
