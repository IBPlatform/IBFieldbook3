package ibfb.nursery.advance;

import ibfb.domain.core.Factor;
import ibfb.domain.core.GermplasmList;
import ibfb.nursery.inventory.InventoryTopComponent;
import ibfb.nursery.maize.PolinizationWizardPanel1;
import ibfb.nursery.models.GermplasmEntriesTableModel;
import ibfb.nursery.models.ObservationsTableModel;
import ibfb.settings.core.FieldbookSettings;
import ibfb.workbook.api.GermplasmListReader;
import ibfb.workbook.core.GermplasmListReaderImpl;
import java.awt.Component;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import org.apache.poi.hmef.attribute.MAPIAttribute;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.ConvertUtils;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.cimmyt.cril.ibwb.domain.*;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Mutex;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;
import org.openide.util.NbPreferences;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

@ConvertAsProperties(dtd = "-//ibfb.nursery.advance//AdvanceLine//EN",
autostore = false)
@TopComponent.Description(preferredID = "AdvanceLineTopComponent",
iconBase = "ibfb/nursery/images/advance16.png",
persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "ibfb.nursery.advance.AdvanceLineTopComponent")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_AdvanceLineAction",
preferredID = "AdvanceLineTopComponent")
@Messages({
    "CTL_AdvanceLineAction=Advance Nursery",
    "CTL_AdvanceLineTopComponent=Advance Nursery",
    "HINT_AdvanceLineTopComponent=Advance Nursery"
})
public final class AdvanceLineTopComponent extends TopComponent {

    private static final int METHOD_UKNOWN_DERIVATE = 31;
    private static final int METHOD_SINGLE_CROSS = 101;
    private static final int METHOD_SINGLE_PLANT = 205;
    private List<String> factoresCadena;
    private List<Factor> factores;
    private int COL_DESIG = 1;
    private int COL_GID = 2;
    private Listnms recentSavedList;
    private List<GermplasmSearch> listToSearchBCID;
    private int convection;
    private String desigArray[] = {"DESIG", "CROSS", "CROSS NAME"};
    private String entryArray[] = {"ENTRY", "ENTRY NUMBER"};
    private String bcidArray[] = {"BCID", "CROSS", "CROSS NAME"};
    ObservationsTableModel modelo;
    List<GermplasmSearch> listFemale;
    List<GermplasmSearch> listMale;
    private Map<Integer, Methods> methodsMap;

    public ObservationsTableModel getModelo() {
        return modelo;
    }

    public void setModelo(ObservationsTableModel modelo) {
        this.modelo = modelo;
    }

    public List<GermplasmSearch> getListToSearchBCID() {
        return listToSearchBCID;
    }

    public void setListToSearchBCID(List<GermplasmSearch> listToSearchBCID) {
        this.listToSearchBCID = listToSearchBCID;
    }

    public int getConvection() {
        return convection;
    }

    public void setConvection(int convection) {
        this.convection = convection;
    }
    /**
     * Method id for advance
     */
    private int methodId;
    /**
     * Selected Location
     */
    private int locationId;
    /**
     * Harvest Date
     */
    private Integer harvestDate;
    /**
     * Nursery Name used as source when saving
     */
    private String nurseryName;
    /**
     * Number of progenitors
     */
    private Integer numberOfParents;
    /**
     * Source with all GID from measurement tab
     */
    private List<Integer> sourceGidList;
    /**
     * Source germplasm to search
     */
    private Germplsm sourceGermplsm;
    /**
     * Store temporary current germplasm
     */
    private Integer currentSourceGid;

    public AdvanceLineTopComponent() {
        initComponents();
        setName(Bundle.CTL_AdvanceLineTopComponent());
        setToolTipText(Bundle.HINT_AdvanceLineTopComponent());
        this.jButtonSave.setEnabled(true);
        this.jButtonInventory.setEnabled(false);
        assignMethods();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane7 = new javax.swing.JScrollPane();
        jTableEntries = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextFieldDescription = new javax.swing.JTextField();
        jTextFieldNurseryAdvanceName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonInventory = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabelEntries = new javax.swing.JLabel();

        jScrollPane7.setAutoscrolls(true);
        jScrollPane7.setMinimumSize(new java.awt.Dimension(0, 0));
        jScrollPane7.setPreferredSize(new java.awt.Dimension(450, 400));

        jTableEntries.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableEntries.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTableEntries.getTableHeader().setReorderingAllowed(false);
        jTableEntries.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableEntriesPropertyChange(evt);
            }
        });
        jScrollPane7.setViewportView(jTableEntries);

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldDescription.setText(org.openide.util.NbBundle.getMessage(AdvanceLineTopComponent.class, "AdvanceLineTopComponent.jTextFieldDescription.text")); // NOI18N

        jTextFieldNurseryAdvanceName.setText(org.openide.util.NbBundle.getMessage(AdvanceLineTopComponent.class, "AdvanceLineTopComponent.jTextFieldNurseryAdvanceName.text")); // NOI18N
        jTextFieldNurseryAdvanceName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldNurseryAdvanceNameKeyReleased(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(AdvanceLineTopComponent.class, "AdvanceLineTopComponent.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(AdvanceLineTopComponent.class, "AdvanceLineTopComponent.jLabel4.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldDescription)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextFieldNurseryAdvanceName, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 482, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldNurseryAdvanceName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/save.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave, org.openide.util.NbBundle.getMessage(AdvanceLineTopComponent.class, "AdvanceLineTopComponent.jButtonSave.text")); // NOI18N
        jButtonSave.setToolTipText(org.openide.util.NbBundle.getMessage(AdvanceLineTopComponent.class, "AdvanceLineTopComponent.jButtonSave.toolTipText")); // NOI18N
        jButtonSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonInventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/inventory.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonInventory, org.openide.util.NbBundle.getMessage(AdvanceLineTopComponent.class, "AdvanceLineTopComponent.jButtonInventory.text")); // NOI18N
        jButtonInventory.setToolTipText(org.openide.util.NbBundle.getMessage(AdvanceLineTopComponent.class, "AdvanceLineTopComponent.jButtonInventory.toolTipText")); // NOI18N
        jButtonInventory.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonInventory.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInventoryActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(AdvanceLineTopComponent.class, "AdvanceLineTopComponent.jLabel2.text")); // NOI18N

        jLabelEntries.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(jLabelEntries, org.openide.util.NbBundle.getMessage(AdvanceLineTopComponent.class, "AdvanceLineTopComponent.jLabelEntries.text")); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonInventory, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelEntries, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelEntries)
                .addGap(39, 39, 39)
                .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jButtonInventory)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNurseryAdvanceNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNurseryAdvanceNameKeyReleased
    }//GEN-LAST:event_jTextFieldNurseryAdvanceNameKeyReleased

    private void jTableEntriesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableEntriesPropertyChange
        this.jLabelEntries.setText(String.valueOf(this.jTableEntries.getRowCount()));
    }//GEN-LAST:event_jTableEntriesPropertyChange

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        saveAdvanceLine();

    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInventoryActionPerformed

        if (existeTopComponent(this.getName() + " INVENTORY")) {
            JOptionPane.showMessageDialog(null, "INVENTORY IS ALREADY GENERATED", "Caution!", JOptionPane.OK_OPTION);

        } else {


            if (jTextFieldNurseryAdvanceName.getText().trim().isEmpty()) {
                DialogUtil.displayError("Please fill Nursery advance name");
                jTextFieldNurseryAdvanceName.requestFocusInWindow();
                return;
            }

            if (jTextFieldDescription.getText().trim().isEmpty()) {
                DialogUtil.displayError("Please fill Nursery advance description name");
                jTextFieldDescription.requestFocusInWindow();
                return;
            }



//        WizardDescriptor wiz = new WizardDescriptor(new InventoryWizardIterator());
//        wiz.setTitleFormat(new MessageFormat("{0} ({1})"));
//        wiz.setTitle("Inventory wizard");
//
//        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
//
//            JOptionPane.showMessageDialog(null, "The inventory is saved", "INVENTORY", JOptionPane.OK_OPTION);
//
//        }

            GermplasmEntriesTableModel tableModel = (GermplasmEntriesTableModel) this.jTableEntries.getModel();
            List<List<Object>> germplasmForInventory = tableModel.getGermplasmData();
            List<Factor> factores = tableModel.getFactorHeaders();

            InventoryTopComponent inventario = new InventoryTopComponent();

            inventario.assignGermplasmEntries(factores, germplasmForInventory);
            inventario.setListName(this.jTextFieldNurseryAdvanceName.getText());
            inventario.setDescription(this.jTextFieldDescription.getText());
            inventario.setListToSave(recentSavedList);

            if (!inventario.validateSeedStockTrait()) {
                return;
            }
            inventario.open();
            inventario.setName(this.getName() + " INVENTORY");
            inventario.requestActive();

        }

    }//GEN-LAST:event_jButtonInventoryActionPerformed

    private static void changeCursorWaitStatus(final boolean isWaiting) {
        Mutex.EVENT.writeAccess(new Runnable() {
            @Override
            public void run() {
                try {
                    JFrame mainFrame =
                            (JFrame) WindowManager.getDefault().getMainWindow();
                    Component glassPane = mainFrame.getGlassPane();
                    if (isWaiting) {
                        glassPane.setVisible(true);

                        glassPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    } else {
                        glassPane.setVisible(false);

                        glassPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    }
                } catch (Exception e) {
                }
            }
        });
    }

    public void assignGermplasmEntries(List<Factor> factorHeaders, List<List<Object>> germplasmData) {

        List<Factor> losFactores = factorHeaders;

        factores = losFactores;
        GermplasmEntriesTableModel tableModel = new GermplasmEntriesTableModel(losFactores, germplasmData);
        // tableModel.setIsForInventory(true);
        this.jTableEntries.setModel(tableModel);
        ajustaColumnsTable(this.jTableEntries);


    }

    private List<Factor> addColumnsForInventory(List<Factor> factores) {
        List<Factor> losFactores = factores;

        Factor factor = new Factor();
        factor.setFactorName("LOCATION");
        factor.setProperty("GERMPLASM ENTRY");
        factor.setScale("TEXT");
        factor.setDataType("C");
        losFactores.add(factor);

        factor = new Factor();
        factor.setFactorName("COMMENT");
        factor.setProperty("GERMPLASM ENTRY");
        factor.setScale("TEXT");
        factor.setDataType("C");
        losFactores.add(factor);

        return losFactores;
    }

    public boolean existeTopComponent(String topName) {
        boolean existe = false;
        try {
            ArrayList<TopComponent> opened = new ArrayList<TopComponent>(WindowManager.getDefault().getRegistry().getOpened());

            for (TopComponent t : opened) {

                if (t.getName().equals(topName)) {
                    existe = true;
                }
            }
        } catch (NullPointerException ex) {
            existe = false;
        }
        return existe;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonInventory;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelEntries;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane7;
    public javax.swing.JTable jTableEntries;
    private javax.swing.JTextField jTextFieldDescription;
    private javax.swing.JTextField jTextFieldNurseryAdvanceName;
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

    private void saveAdvanceLine() {

        if (jTextFieldNurseryAdvanceName.getText().trim().isEmpty()) {
            DialogUtil.displayError(NbBundle.getMessage(AdvanceLineTopComponent.class, "AdvanceLineTopComponent.fill"));
            jTextFieldNurseryAdvanceName.requestFocusInWindow();
            return;
        }

        if (this.jTextFieldDescription.getText().isEmpty()) {
            NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(AdvanceLineTopComponent.class, "AdvanceLineTopComponent.need"), NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            jTextFieldDescription.requestFocusInWindow();
            return;
        }

        if (this.jTableEntries.getRowCount() == 0) {
            NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(AdvanceLineTopComponent.class, "AdvanceLineTopComponent.empty"), NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            return;
        }

        if (AppServicesProxy.getDefault().appServices().existGermplasmListName(jTextFieldNurseryAdvanceName.getText())) {
            NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(AdvanceLineTopComponent.class, "AdvanceLineTopComponent.listNameAlreadyExists"), NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            return;
        }

        if (getConvection() == 0)//IF IS FOR WHEAT
        {
            saveListForWheat();
        } else {
            saveList();
        }
        NotifyDescriptor d2 = new NotifyDescriptor.Message(NbBundle.getMessage(AdvanceLineTopComponent.class, "AdvanceLineTopComponent.saved"), NotifyDescriptor.INFORMATION_MESSAGE);
        DialogDisplayer.getDefault().notify(d2);

        // }

        this.jButtonSave.setEnabled(false);
        this.jButtonInventory.setEnabled(true);
        this.jTextFieldNurseryAdvanceName.setEnabled(false);
        this.jTextFieldDescription.setEnabled(false);

    }

    //MODIFICAR PARA ABRIR LISTA
//    private void openRecentList(Listnms listnms) {
//        ListDataWindowTopComponent ldwtc = ListDataWindowTopComponent.getListDataWindowTopComponent(listnms);
//        if (ldwtc == null) {
//            ldwtc = new ListDataWindowTopComponent(listnms);
//        }
//        ldwtc.open();
//        ldwtc.requestActive();
//
//    }
    private void saveList() {

        changeCursorWaitStatus(true);
        Integer loggedUserId = AppServicesProxy.getDefault().appServices().getLoggedUserId(FieldbookSettings.getLocalGmsUserId());

        Listnms listnms = new Listnms();
        String nombreLista = this.jTextFieldNurseryAdvanceName.getText();
        listnms.setListname(nombreLista);

        listnms.setListdate(this.harvestDate);

        listnms.setListtype(Listnms.LIST_TYPE_HARVEST);
        listnms.setListuid(loggedUserId);



        listnms.setListdesc(this.jTextFieldDescription.getText());
        listnms.setLhierarchy(0);
        listnms.setListstatus(1);
        AppServicesProxy.getDefault().appServices().addListnms(listnms);
        List<Listdata> dataList = new ArrayList<Listdata>();

        GermplasmEntriesTableModel tableModel = (GermplasmEntriesTableModel) jTableEntries.getModel();

        // int bcid= tableModel.getHeaderIndex(GermplasmEntriesTableModel.BCID); //findColumn("BCID");
        int gid = tableModel.getHeaderIndex(GermplasmEntriesTableModel.GID); //findColumn("GID");
        int desig = tableModel.getHeaderIndex(GermplasmEntriesTableModel.DESIG);//findColumn("DESIG");
        int entryCD = tableModel.getHeaderIndex(GermplasmEntriesTableModel.ENTRY);// findColumn("ENTRY");
        int crossColumn = tableModel.getHeaderIndex(GermplasmEntriesTableModel.CROSS);

        currentSourceGid = 0;
        int counter = 1;

        for (int index = 0; index < jTableEntries.getRowCount(); index++) {
            Listdata listdata = new Listdata(true);
            ListdataPK pk1 = new ListdataPK(listnms.getListid(), 0);

            listdata.setListdataPK(pk1);
            listdata.setEntryid(index + 1);
            if (desig > 0) {
                listdata.setDesig(this.jTableEntries.getValueAt(index, desig).toString());
            } else {
                listdata.setDesig("");
            }
            if (entryCD > 0) {
                listdata.setEntrycd(this.jTableEntries.getValueAt(index, entryCD).toString());
            } else {
                listdata.setEntrycd("");
            }

            listdata.setSource(this.nurseryName + ":" + counter);
            listdata.setEntrycd("E" + ConvertUtils.getZeroLeading(counter, 4));
            if (crossColumn > -1) {
                listdata.setGrpname(this.jTableEntries.getValueAt(index, crossColumn).toString());
            } else {
                listdata.setGrpname("");
            }

            listdata.setLrstatus(0);
            listdata.setGid(0);

            // assign number of parents
            listdata.setGnpgs(numberOfParents);

            // this values are used when a germplasm is going to be added
            listdata.setHarvestDate(harvestDate);


            if (getConvection() == 1) {//Maize           
                int maizeMethodId = NbPreferences.forModule(PolinizationWizardPanel1.class).getInt("maizeMethodID", 0);
                listdata.setMethodId(maizeMethodId);
                //System.out.println("METODO MAIZ A GUARDDAR: "+maizeMethodId);
  
            } else {//other crops
                listdata.setMethodId(methodId);
            }


            listdata.setLocationId(locationId);

            // asigns GPID1 and GPDI2
            assignGpid1AndGpid2(listdata, index);
            dataList.add(listdata);
            counter++;

        }
        //Integer loggedUserId = AppServicesProxy.getDefault().appServices().getLoggedUserId();

        AppServicesProxy.getDefault().appServices().addNewsGermplasm(listnms, dataList, loggedUserId);

        loadListIntoWindow(listnms);

        changeCursorWaitStatus(false);

        setRecentSavedList(listnms);

    }

    private void saveListForWheat() {

        if (!loadFemaleMale()) {
            listFemale = new ArrayList<GermplasmSearch>();
            listMale = new ArrayList<GermplasmSearch>();
        }


        changeCursorWaitStatus(true);

        Integer loggedUserid = AppServicesProxy.getDefault().appServices().getLoggedUserId(FieldbookSettings.getLocalGmsUserId());

        Listnms listnms = new Listnms();
        listnms.setListname(this.jTextFieldNurseryAdvanceName.getText());

        listnms.setListdate(this.harvestDate);

        listnms.setListtype(Listnms.LIST_TYPE_HARVEST);
        listnms.setListuid(loggedUserid);
        listnms.setListdesc(this.jTextFieldDescription.getText());
        listnms.setLhierarchy(0);
        listnms.setListstatus(1);

        AppServicesProxy.getDefault().appServices().addListnms(listnms);
        List<Listdata> dataList = new ArrayList<Listdata>();

        int desigColumn = findColumn(desigArray);
        int entryCDColumn = findColumn(entryArray);
        int bcidColumn = findColumn(bcidArray);

        int gid = 0;
        currentSourceGid = 0;


        int numOfParents = getNumberOfParents();

        int counter = 1;
        GermplasmEntriesTableModel tableModel = (GermplasmEntriesTableModel) jTableEntries.getModel();
        int crossColumn = tableModel.getHeaderIndex(GermplasmEntriesTableModel.CROSSCIMMYTWHEAT);

        for (int indexRow = 0; indexRow < jTableEntries.getRowCount(); indexRow++) {


            int selectedMethodId = giveMethodSelection(indexRow);


            Listdata listdata = new Listdata(true);
            Listdata listdataBCID = new Listdata(true);

            ListdataPK pk1 = new ListdataPK(listnms.getListid(), 0);
            listdata.setListdataPK(pk1);
            listdata.setEntryid(indexRow + 1);


            listdata.setDesig(this.jTableEntries.getValueAt(indexRow, desigColumn).toString());


            if (bcidColumn > 0) {
                listdata.setNameBCID(this.jTableEntries.getValueAt(indexRow, desigColumn).toString());
            } else {
                listdata.setNameBCID("");

                break;
            }


            if (entryCDColumn > 0) {
                listdata.setEntrycd(this.jTableEntries.getValueAt(indexRow, entryCDColumn).toString());
            } else {
                listdata.setEntrycd("");
            }


            listdata.setSource(this.nurseryName + ":" + counter);
            listdata.setEntrycd("E" + ConvertUtils.getZeroLeading(counter, 4));

            if (crossColumn > -1) {
                listdata.setGrpname(this.jTableEntries.getValueAt(indexRow, crossColumn).toString());
            } else {
                listdata.setGrpname("");
            }


            listdata.setLrstatus(0);
            listdata.setGid(0);

            // assign number of parents
            listdata.setGnpgs(numberOfParents);

            // this values are used when a germplasm is going to be added
            listdata.setHarvestDate(harvestDate);
            listdata.setMethodId(selectedMethodId);
            listdata.setLocationId(locationId);

            // asigns GPID1 and GPDI2
            assignGpid1AndGpid2(listdata, indexRow);
            dataList.add(listdata);

            counter++;

        }


        AppServicesProxy.getDefault().appServices().saveGerplasmCimmytWheat(dataList, listnms, loggedUserid, listFemale, listMale);

        loadListIntoWindow(listnms);
        changeCursorWaitStatus(false);
        setRecentSavedList(listnms);
    }

    private int findColumn(String[] opciones) {
        int myCol = -1;

        for (int i = 0; i < opciones.length; i++) {
            String opcion = opciones[i];
            try {
                int founded = jTableEntries.getTableHeader().getColumnModel().getColumnIndex(opcion);
                if (founded >= 0) {
                    myCol = founded;
                    break;
                }
            } catch (Exception ex) {
                continue;
            }

        }

        return myCol;
    }

    private boolean loadFemaleMale() {

        listFemale = new ArrayList<GermplasmSearch>();
        listMale = new ArrayList<GermplasmSearch>();

        int source = findColumn("FDESIG");

        int fgidcol = findColumn("FGID");
        if (fgidcol < 0) {
            return false;
        }

        int mgidcol = findColumn("MGID");
        if (mgidcol < 0) {
            return false;
        }

        int ftidCol = findColumn("FTID");
        if (ftidCol < 0) {
            return false;
        }

        int foccCol = findColumn("FOCC");
        if (foccCol < 0) {
            return false;
        }
        int fentCol = findColumn("FENT");
        if (fentCol < 0) {
            return false;
        }
        int mtidCol = findColumn("MTID");
        if (mtidCol < 0) {
            return false;
        }
        int moccCol = findColumn("MOCC");
        if (moccCol < 0) {
            return false;
        }
        int mentCol = findColumn("MENT");
        if (mentCol < 0) {
            return false;
        }


        for (int i = 0; i < jTableEntries.getRowCount(); i++) {

            int ftid;
            int focc;
            int fent;
            int mtid;
            int mocc;
            int ment;

            try {
                ftid = (int) (Double.parseDouble(this.jTableEntries.getValueAt(i, ftidCol).toString()));
            } catch (Exception ex) {
                ftid = 0;
            }
            try {
                focc = (int) (Double.parseDouble(this.jTableEntries.getValueAt(i, foccCol).toString()));
            } catch (Exception ex) {
                focc = 0;
            }
            try {
                fent = (int) (Double.parseDouble(this.jTableEntries.getValueAt(i, fentCol).toString()));
            } catch (Exception ex) {
                fent = 0;
            }
            try {
                mtid = (int) (Double.parseDouble(this.jTableEntries.getValueAt(i, mtidCol).toString()));
            } catch (Exception ex) {
                mtid = 0;
            }
            try {
                mocc = (int) (Double.parseDouble(this.jTableEntries.getValueAt(i, moccCol).toString()));
            } catch (Exception ex) {
                mocc = 0;
            }
            try {
                ment = (int) (Double.parseDouble(this.jTableEntries.getValueAt(i, mentCol).toString()));
            } catch (Exception ex) {
                ment = 0;
            }


            GermplasmSearch gsF = new GermplasmSearch();
            gsF.setStudyId(ftid);
            gsF.setTrial(focc);
            gsF.setPlot(fent);
            listFemale.add(gsF);

            GermplasmSearch gsM = new GermplasmSearch();
            gsM.setStudyId(mtid);
            gsM.setTrial(mocc);
            gsM.setPlot(ment);
            listMale.add(gsM);
        }

        List<GermplasmSearch> germplasmSearchs = AppServicesProxy.getDefault().appServices().getGermplasmByListStudyTrialPlotCross(listFemale, listMale);
        return true;

    }

    private int findColumn(String col) {
        int myCol;
        try {
            myCol = jTableEntries.getTableHeader().getColumnModel().getColumnIndex(col);
        } catch (Exception ex) {
            myCol = -1;
        }
        return myCol;
    }

    public void ajustaColumnsTable(JTable table) {
        for (int c = 0; c < table.getColumnCount(); c++) {
            ajustaColumn(table, c, 2);
        }
    }

    public void ajustaColumn(JTable table, int vColIndex, int margin) {
        TableModel modelPack = table.getModel();
        DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
        TableColumn col = colModel.getColumn(vColIndex);
        int width = 0;
        TableCellRenderer renderer = col.getHeaderRenderer();
        if (renderer == null) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }
        Component comp = renderer.getTableCellRendererComponent(
                table, col.getHeaderValue(), false, false, 0, 0);
        width = comp.getPreferredSize().width;
        for (int r = 0; r < table.getRowCount(); r++) {
            renderer = table.getCellRenderer(r, vColIndex);
            comp = renderer.getTableCellRendererComponent(
                    table, table.getValueAt(r, vColIndex), false, false, r,
                    vColIndex);
            width = Math.max(width, comp.getPreferredSize().width);
        }
        width += 2 * margin;
        col.setPreferredWidth(width);
    }

    private void loadListIntoWindow(Listnms lista) {

        System.out.println("LOAD LISTA: " + lista.getListname() + "    LISTAID: " + lista.getListid());
        int gidColumn = findColumn("GID");
        GermplasmListReader germplasmListReader = new GermplasmListReaderImpl();
        GermplasmList germplasmList = germplasmListReader.getGermPlasmListFromDB(lista.getListid());
        GermplasmEntriesTableModel modeloConGID = (GermplasmEntriesTableModel) this.jTableEntries.getModel();
        modeloConGID.setSeActualizaGID(true);


        for (int i = 0; i < germplasmList.getListEntries().size(); i++) {
            int GID = germplasmList.getListEntries().get(i).getGid();
            String desig = germplasmList.getListEntries().get(i).getDesignation();
            int row = findRow(desig, modeloConGID);
            //System.out.println(germplasmList.getListEntries().get(i).getDesignation() +" /// " +germplasmList.getListEntries().get(i).getGid());
            modeloConGID.setValueAt(GID, row, gidColumn);
        }


    }

    private int findRow(String desig, GermplasmEntriesTableModel modeloConGID) {

        int desigColumn = findColumn(desigArray);
        for (int i = 0; i < modeloConGID.getRowCount(); i++) {
            if (modeloConGID.getValueAt(i, desigColumn).toString().equals(desig)) {
                System.out.println("ENCONTRADO");
                return i;
            }

        }

        return 0;

    }

    public Listnms getRecentSavedList() {
        return recentSavedList;
    }

    public void setRecentSavedList(Listnms recentSavedList) {
        this.recentSavedList = recentSavedList;
    }

    public int getMethodId() {
        return methodId;
    }

    public void setMethodId(int methodId) {
        this.methodId = methodId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public Integer getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(Integer harvestDate) {
        this.harvestDate = harvestDate;
    }

    public String getNurseryName() {
        return nurseryName;
    }

    public void setNurseryName(String nurseryName) {
        this.nurseryName = nurseryName;
    }

    public Integer getNumberOfParents() {
        return numberOfParents;
    }

    public void setNumberOfParents(Integer numberOfParents) {
        this.numberOfParents = numberOfParents;
    }

    public void clearEntries() {
        if (this.jTableEntries.getModel() instanceof GermplasmEntriesTableModel) {
            GermplasmEntriesTableModel tableModel = (GermplasmEntriesTableModel) this.jTableEntries.getModel();
            tableModel.clearTable();
        }
    }

    /**
     * Assign parents to list data.
     *
     * @param listdata
     * @param gidIndex
     */
    private void assignGpid1AndGpid2(Listdata listdata, int gidIndex) {
        Integer sourceGid = sourceGidList.get(gidIndex);
        boolean searchGermplasm = false;

        if (sourceGid.intValue() != currentSourceGid) {
            searchGermplasm = true;
        }

        if (searchGermplasm) {
            sourceGermplsm = AppServicesProxy.getDefault().appServices().getGermplsm(sourceGid);
            currentSourceGid = sourceGid;
        }
        // if germplasm exists then check it method to assign GPID1 and GPAID2
        if (sourceGermplsm != null) {
            switch (sourceGermplsm.getMethn()) {
                case METHOD_SINGLE_CROSS:
                    listdata.setGpid1(sourceGid);
                    listdata.setGpid2(sourceGid);
                    break;
                case METHOD_SINGLE_PLANT:
                    listdata.setGpid1(sourceGermplsm.getGpid1());
                    listdata.setGpid2(sourceGid);
                    break;
                case METHOD_UKNOWN_DERIVATE:
                    listdata.setGpid1(sourceGid);
                    listdata.setGpid2(0);
                    break;
                default:
                    // first look for source method germplsm
                    Methods methods = methodsMap.get(sourceGermplsm.getMethn());
                    // if method for source germplasm was found
                    if (methods != null) {
                        // checks if is a GENERATIVE METHOD
                        String methodType = methods.getMtype().toUpperCase();
                        if (methodType.equals(Methods.MTYPE_GENERATIVE)) {
                            listdata.setGpid1(sourceGermplsm.getGpid1());
                            listdata.setGpid2(sourceGermplsm.getGpid1());
                        }
                    } else {
                        listdata.setGpid1(sourceGermplsm.getGpid1());
                        listdata.setGpid2(sourceGid);
                    }
                    break;
            }

        }
    }

    public List<Integer> getSourceGidList() {
        return sourceGidList;
    }

    public void setSourceGidList(List<Integer> sourceGidList) {
        this.sourceGidList = sourceGidList;
    }

    private int giveMethodSelection(int renglon) {
        int method = 0;

        int colSelection = modelo.getHeaderIndex(GermplasmEntriesTableModel.PLANTS_SELECTED);

//        if (renglon < modelo.getRowCount()) {
//
//            if (colSelection > 0 && modelo.getValueAt(renglon, colSelection) != null) {
//                //int elMetodo = Integer.parseInt(modelo.getValueAt(renglon, colSelection).toString());
//                int elMetodo = ConvertUtils.getValueAsInteger(modelo.getValueAt(renglon, colSelection));
//
//                if (elMetodo > 0) {
//                    return 205;
//                }
//
//                if (elMetodo == 0) {
//                    return 206;
//                }
//
//                if (elMetodo < 0) {
//                    return 207;
//                }
//            }
//        } else {
        GermplasmEntriesTableModel tableModel = (GermplasmEntriesTableModel) this.jTableEntries.getModel();
        int entryCodeColumn = tableModel.getHeaderIndex(GermplasmEntriesTableModel.ENTRY_CODE);
        String entryCodeToFind = jTableEntries.getValueAt(renglon, entryCodeColumn).toString();
        int entryRow = findEntry(entryCodeToFind);
        if (colSelection > 0 && modelo.getValueAt(entryRow, colSelection) != null) {
            //int elMetodo = Integer.parseInt(modelo.getValueAt(renglon, colSelection).toString());
            int elMetodo = ConvertUtils.getValueAsInteger(modelo.getValueAt(entryRow, colSelection));

            if (elMetodo > 0) {
                return 205;
            }

            if (elMetodo == 0) {
                return 206;
            }

            if (elMetodo < 0) {
                return 207;
            }
//            }
        }


        return method;
    }

    /**
     *
     * @param entryCodeToFind
     * @return
     */
    private int findEntry(String entryCodeToFind) {
        int entryRow = -1;

        int entryCodeColumnModel = modelo.getHeaderIndex(GermplasmEntriesTableModel.ENTRY_CODE);

        for (int row = 0; row < modelo.getRowCount(); row++) {
            String modelEntryCode = modelo.getValueAt(row, entryCodeColumnModel).toString();
            if (modelEntryCode.equals(entryCodeToFind)) {
                entryRow = row;
                break;
            }

        }
        return entryRow;
    }

    /**
     * Assign a list of method to methods maps
     *
     * @param methodsList
     */
    private void assignMethods() {
        List<Methods> methodsList = AppServicesProxy.getDefault().appServices().getMethodsList();
        methodsMap = new HashMap<Integer, Methods>();
        for (Methods method : methodsList) {
            methodsMap.put(method.getMid(), method);
        }
    }
}
