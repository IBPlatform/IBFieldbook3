package ibfb.traits.traits;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ResourceBundle;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.OntologyTool;
import org.cimmyt.cril.ibwb.commongui.TableBindingUtil;
import org.cimmyt.cril.ibwb.domain.Trait;
import org.cimmyt.cril.ibwb.domain.Traits;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//ibfb.traits.traits//TraitsExplorer//EN",
autostore = false)
@TopComponent.Description(preferredID = "TraitsExplorerTopComponent",
iconBase = "ibfb/traits/traits/traits16.png",
persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "explorer", openAtStartup = false)
@ActionID(category = "Window", id = "ibfb.traits.traits.TraitsExplorerTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_TraitsExplorerAction",
preferredID = "TraitsExplorerTopComponent")
public final class TraitsExplorerTopComponent extends TopComponent {
    
    private ResourceBundle bundle = NbBundle.getBundle(TraitsExplorerTopComponent.class);

    private List<Traits> traitsList;

    public TraitsExplorerTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(TraitsExplorerTopComponent.class, "CTL_TraitsExplorerTopComponent"));
        setToolTipText(NbBundle.getMessage(TraitsExplorerTopComponent.class, "HINT_TraitsExplorerTopComponent"));
        putClientProperty(TopComponent.PROP_UNDOCKING_DISABLED, Boolean.TRUE);
        //updateTraitsTable();
        traitsTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    editRecord();
                }
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popMenu = new javax.swing.JPopupMenu();
        mnuNew = new javax.swing.JMenuItem();
        mnuEdit = new javax.swing.JMenuItem();
        mnuDelete = new javax.swing.JMenuItem();
        mnuBrowse = new javax.swing.JMenuItem();
        txtSearchTrait = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        traitsTable = new javax.swing.JTable();
        lblTraitsFound = new javax.swing.JLabel();
        tblBarMenu = new javax.swing.JToolBar();
        btnNew = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnBrowse = new javax.swing.JButton();

        mnuNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/traits/traits/new.png"))); // NOI18N
        mnuNew.setLabel(org.openide.util.NbBundle.getMessage(TraitsExplorerTopComponent.class, "TraitsExplorerTopComponent.mnuNew.label")); // NOI18N
        mnuNew.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/traits/traits/new.png"))); // NOI18N
        mnuNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuNewActionPerformed(evt);
            }
        });
        popMenu.add(mnuNew);

        mnuEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/traits/traits/edit.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(mnuEdit, org.openide.util.NbBundle.getMessage(TraitsExplorerTopComponent.class, "TraitsExplorerTopComponent.mnuEdit.text")); // NOI18N
        mnuEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEditActionPerformed(evt);
            }
        });
        popMenu.add(mnuEdit);

        mnuDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/traits/traits/delete.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(mnuDelete, org.openide.util.NbBundle.getMessage(TraitsExplorerTopComponent.class, "TraitsExplorerTopComponent.mnuDelete.text")); // NOI18N
        mnuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuDeleteActionPerformed(evt);
            }
        });
        popMenu.add(mnuDelete);

        mnuBrowse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/traits/traits/browse16.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(mnuBrowse, org.openide.util.NbBundle.getMessage(TraitsExplorerTopComponent.class, "TraitsExplorerTopComponent.mnuBrowse.text")); // NOI18N
        mnuBrowse.setToolTipText(org.openide.util.NbBundle.getMessage(TraitsExplorerTopComponent.class, "TraitsExplorerTopComponent.mnuBrowse.toolTipText")); // NOI18N
        mnuBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuBrowseActionPerformed(evt);
            }
        });
        popMenu.add(mnuBrowse);

        txtSearchTrait.setText(org.openide.util.NbBundle.getMessage(TraitsExplorerTopComponent.class, "TraitsExplorerTopComponent.txtSearchTrait.text")); // NOI18N
        txtSearchTrait.setToolTipText(org.openide.util.NbBundle.getMessage(TraitsExplorerTopComponent.class, "TraitsExplorerTopComponent.txtSearchTrait.toolTipText")); // NOI18N
        txtSearchTrait.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchTraitKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchTraitKeyReleased(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/traits/traits/delete.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(TraitsExplorerTopComponent.class, "TraitsExplorerTopComponent.jLabel1.text")); // NOI18N

        traitsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        traitsTable.setComponentPopupMenu(popMenu);
        traitsTable.setDragEnabled(true);
        jScrollPane1.setViewportView(traitsTable);

        lblTraitsFound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/traits/traits/browse16.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblTraitsFound, org.openide.util.NbBundle.getMessage(TraitsExplorerTopComponent.class, "TraitsExplorerTopComponent.lblTraitsFound.text")); // NOI18N

        tblBarMenu.setRollover(true);

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/traits/traits/new.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnNew, org.openide.util.NbBundle.getMessage(TraitsExplorerTopComponent.class, "TraitsExplorerTopComponent.btnNew.text")); // NOI18N
        btnNew.setToolTipText(org.openide.util.NbBundle.getMessage(TraitsExplorerTopComponent.class, "TraitsExplorerTopComponent.btnNew.toolTipText")); // NOI18N
        btnNew.setFocusable(false);
        btnNew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNew.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        tblBarMenu.add(btnNew);

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/traits/traits/edit.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnEdit, org.openide.util.NbBundle.getMessage(TraitsExplorerTopComponent.class, "TraitsExplorerTopComponent.btnEdit.text")); // NOI18N
        btnEdit.setToolTipText(org.openide.util.NbBundle.getMessage(TraitsExplorerTopComponent.class, "TraitsExplorerTopComponent.btnEdit.toolTipText")); // NOI18N
        btnEdit.setFocusable(false);
        btnEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        tblBarMenu.add(btnEdit);

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/traits/traits/delete.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnDelete, org.openide.util.NbBundle.getMessage(TraitsExplorerTopComponent.class, "TraitsExplorerTopComponent.btnDelete.text")); // NOI18N
        btnDelete.setToolTipText(org.openide.util.NbBundle.getMessage(TraitsExplorerTopComponent.class, "TraitsExplorerTopComponent.btnDelete.toolTipText")); // NOI18N
        btnDelete.setFocusable(false);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        tblBarMenu.add(btnDelete);

        btnBrowse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/traits/traits/browse16.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnBrowse, org.openide.util.NbBundle.getMessage(TraitsExplorerTopComponent.class, "TraitsExplorerTopComponent.btnBrowse.text")); // NOI18N
        btnBrowse.setToolTipText(org.openide.util.NbBundle.getMessage(TraitsExplorerTopComponent.class, "TraitsExplorerTopComponent.btnBrowse.toolTipText")); // NOI18N
        btnBrowse.setFocusable(false);
        btnBrowse.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBrowse.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });
        tblBarMenu.add(btnBrowse);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tblBarMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                .addGap(256, 256, 256))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTraitsFound)
                    .addComponent(txtSearchTrait, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(378, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tblBarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearchTrait, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTraitsFound)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchTraitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchTraitKeyPressed
}//GEN-LAST:event_txtSearchTraitKeyPressed

    private void txtSearchTraitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchTraitKeyReleased
        updateTraitsTable();
}//GEN-LAST:event_txtSearchTraitKeyReleased

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        createNew();
}//GEN-LAST:event_btnNewActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        editRecord();
}//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteRecord();
}//GEN-LAST:event_btnDeleteActionPerformed

    private void mnuNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuNewActionPerformed
        createNew();
}//GEN-LAST:event_mnuNewActionPerformed

    private void mnuEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEditActionPerformed
        editRecord();
}//GEN-LAST:event_mnuEditActionPerformed

    private void mnuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuDeleteActionPerformed
        deleteRecord();
}//GEN-LAST:event_mnuDeleteActionPerformed

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        openOntology();
    }//GEN-LAST:event_btnBrowseActionPerformed

    private void mnuBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuBrowseActionPerformed
        openOntology();
    }//GEN-LAST:event_mnuBrowseActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNew;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTraitsFound;
    private javax.swing.JMenuItem mnuBrowse;
    private javax.swing.JMenuItem mnuDelete;
    private javax.swing.JMenuItem mnuEdit;
    private javax.swing.JMenuItem mnuNew;
    private javax.swing.JPopupMenu popMenu;
    private javax.swing.JToolBar tblBarMenu;
    private javax.swing.JTable traitsTable;
    private javax.swing.JTextField txtSearchTrait;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        // updateTraitsTable();
    }

    @Override
    public void componentClosed() {
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    public void updateTraitsTable() {

        Traits filter = new Traits(true);
        //filter.setTrname(txtSearchTrait.getText());
        filter.setGlobalsearch(txtSearchTrait.getText());

        //int total = AppServicesProxy.getDefault().appServices().getTotalTrait(filter);
        traitsList = AppServicesProxy.getDefault().appServices().getListTraits(filter, 0, 0, false);
        lblTraitsFound.setText(traitsList.size() + " Trait(s) found ");
        
        String tid = bundle.getString("TraitsExplorerTopComponent.tid");
        String trname = bundle.getString("TraitsExplorerTopComponent.trname");
        String trabbr = bundle.getString("TraitsExplorerTopComponent.trabbr");
        String ontology = bundle.getString("TraitsExplorerTopComponent.ontology");
        
        String headers = tid + "," + trname + "," + trabbr + "," + ontology;
        
        TableBindingUtil.createColumnsFromDB(Trait.class, traitsList, traitsTable, "tid,trname,trabbr,ontology", headers);
    }

    private void createNew() {
        Traits trait = new Traits();
        TraitEditorTopComponent traitEditorTopComponent = new TraitEditorTopComponent(trait);
        traitEditorTopComponent.open();
        traitEditorTopComponent.requestActive();
    }

    private void editRecord() {
        Integer row = traitsTable.getSelectedRow();
        if (row != -1) {
            Traits trait = traitsList.get(row);
            TraitEditorTopComponent traitEditorTopComponent = TraitEditorTopComponent.getTraitEditorTopComponent(trait);
            if (traitEditorTopComponent == null) {
                traitEditorTopComponent = new TraitEditorTopComponent(trait);
            }
            traitEditorTopComponent.open();
            traitEditorTopComponent.requestActive();
        }
    }

    private void deleteRecord() {
        Integer row = traitsTable.getSelectedRow();

        if (row != -1) {
        }
    }

    private void openOntology() {
        Integer row = traitsTable.getSelectedRow();
        if (row != -1) {

            Traits trait = traitsList.get(row);
            OntologyTool.openOntology(trait.getOntology());
        }
    }
}
