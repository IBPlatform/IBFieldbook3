package ibfb.studyexplorer.explorer.listNames;


import java.util.List;
import java.util.Set;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.api.Services;
import org.cimmyt.cril.ibwb.commongui.TableBindingUtil;
import org.cimmyt.cril.ibwb.domain.Listdata;
import org.cimmyt.cril.ibwb.domain.ListdataPK;
import org.cimmyt.cril.ibwb.domain.Listnms;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;

@ConvertAsProperties(dtd = "-//ibfb.studyexplorer.explorer.listNames//ListDataWindow//EN",
autostore = false)
@TopComponent.Description(preferredID = "ListDataWindowTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "ibfb.studyexplorer.explorer.listNames.ListDataWindowTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_ListDataWindowAction",
preferredID = "ListDataWindowTopComponent")
public final class ListDataWindowTopComponent extends TopComponent {

    private Listnms parentListname;

    public ListDataWindowTopComponent(Listnms listnms) {
        this.parentListname = listnms;
        initComponents();
        String theName = NbBundle.getMessage(ListDataWindowTopComponent.class, "CTL_ListDataWindowTopComponent");
        setName(theName + "("+listnms.getListname()+")");
        setToolTipText(NbBundle.getMessage(ListDataWindowTopComponent.class, "HINT_ListDataWindowTopComponent"));
        this.parentListname = listnms;
        updateListData();
       
    }

    public ListDataWindowTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(ListDataWindowTopComponent.class, "CTL_ListDataWindowTopComponent"));
        setToolTipText(NbBundle.getMessage(ListDataWindowTopComponent.class, "HINT_ListDataWindowTopComponent"));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jLabel1 = new javax.swing.JLabel();
        txtListName = new javax.swing.JTextField();
        txtSearchText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListData = new javax.swing.JTable();
        lblEntriesFound = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();
        lblListId = new javax.swing.JLabel();
        txtListId = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(ListDataWindowTopComponent.class, "ListDataWindowTopComponent.jLabel1.text")); // NOI18N

        txtListName.setEditable(false);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${parentListname.listname}"), txtListName, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        txtSearchText.setText(org.openide.util.NbBundle.getMessage(ListDataWindowTopComponent.class, "ListDataWindowTopComponent.txtSearchText.text")); // NOI18N
        txtSearchText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchTextKeyReleased(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(ListDataWindowTopComponent.class, "ListDataWindowTopComponent.jLabel2.text")); // NOI18N

        tblListData.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblListData);

        org.openide.awt.Mnemonics.setLocalizedText(lblEntriesFound, org.openide.util.NbBundle.getMessage(ListDataWindowTopComponent.class, "ListDataWindowTopComponent.lblEntriesFound.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(ListDataWindowTopComponent.class, "ListDataWindowTopComponent.jLabel3.text")); // NOI18N

        txtDescription.setEditable(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${parentListname.listdesc}"), txtDescription, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        org.openide.awt.Mnemonics.setLocalizedText(lblListId, org.openide.util.NbBundle.getMessage(ListDataWindowTopComponent.class, "ListDataWindowTopComponent.lblListId.text")); // NOI18N

        txtListId.setEditable(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${parentListname.listid}"), txtListId, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblListId)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearchText, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEntriesFound)
                        .addGap(40, 40, 40))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtListName, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                            .addComponent(txtDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtListId, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblListId)
                    .addComponent(txtListId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtListName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEntriesFound, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchTextKeyReleased
        updateListData();
}//GEN-LAST:event_txtSearchTextKeyReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEntriesFound;
    private javax.swing.JLabel lblListId;
    private javax.swing.JTable tblListData;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtListId;
    private javax.swing.JTextField txtListName;
    private javax.swing.JTextField txtSearchText;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
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
        // TODO read your settings according to their version
    }

    public Listnms getParentListname() {
        return parentListname;
    }

    public void setParentListname(Listnms parentListname) {
        this.parentListname = parentListname;
    }
  

    private void updateListData() {

        Listdata filter = new Listdata();
        filter.setEntrycd(txtSearchText.getText());
        filter.setListdataPK(new ListdataPK(parentListname.getListid(), 0));
        filter.setGlobalsearch(txtSearchText.getText());
        //int total = AppServicesProxy.getDefault().appServices().getTotalListdata(filter);

        List<Listdata> listdata = AppServicesProxy.getDefault().appServices().getListListdata(filter, 0, 0, false);
        lblEntriesFound.setText(listdata.size() + " Entries found");
        TableBindingUtil.createColumnsFromDB(Listdata.class, listdata, tblListData, "gid,desig,entrycd,source,entryid", "GID,Designation,EntryCD,Source,Entry Id");

    }
    
    /**
     * Return current instance of TraitEditorTopComponent using current Traits object
     * @param scales Scale object to check
     * @return TraitEditorTopComponent instance if found or null if not found
     */
    public static ListDataWindowTopComponent getListDataWindowTopComponent(Listnms listnms) {
        ListDataWindowTopComponent listDataWindowTopComponent = null;
        Set<TopComponent> comps = TopComponent.getRegistry().getOpened();
        for (TopComponent tc : comps) {
            if (tc instanceof ListDataWindowTopComponent) {
                ListDataWindowTopComponent setc = (ListDataWindowTopComponent) tc;
                StringBuilder name = new StringBuilder();

                name.append(NbBundle.getMessage(ListDataWindowTopComponent.class, "CTL_ListDataWindowTopComponent"));
                name.append("(").append(listnms.getListname()).append(")");

                if (setc.getName().equals(name.toString())) {
                    listDataWindowTopComponent = setc;
                    break;
                }
            }
        }
        return listDataWindowTopComponent;
    }    
    
}
