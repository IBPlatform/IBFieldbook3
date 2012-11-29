package ibfb.nursery.inventory;

import ibfb.domain.core.Factor;
import ibfb.nursery.models.GermplasmEntriesTableModel;
import ibfb.nursery.persons.SelectLocationPanel;
import ibfb.settings.core.FieldbookSettings;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.*;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.ConvertUtils;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.cimmyt.cril.ibwb.commongui.NumericKeyAdapter;
import org.cimmyt.cril.ibwb.domain.Listnms;
import org.cimmyt.cril.ibwb.domain.Scale;
import org.cimmyt.cril.ibwb.domain.Scales;
import org.cimmyt.cril.ibwb.domain.Traits;
import org.cimmyt.cril.ibwb.domain.inventory.InventoryData;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.NbPreferences;

@ConvertAsProperties(dtd = "-//ibfb.nursery.inventory//Inventory//EN",
autostore = false)
@TopComponent.Description(preferredID = "InventoryTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "ibfb.nursery.inventory.InventoryTopComponent")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_InventoryAction",
preferredID = "InventoryTopComponent")
@Messages({
    "CTL_InventoryAction=Inventory",
    "CTL_InventoryTopComponent=Inventory",
    "HINT_InventoryTopComponent=Inventory"
})
public final class InventoryTopComponent extends TopComponent {

    private ResourceBundle bundle = NbBundle.getBundle(InventoryTopComponent.class);
    private List<Factor> factores;
    private Listnms listToSave;
    private Integer locationId;
    private Integer amountColumn = -1;
    private Integer locationColumn = -1;
    private Integer commentColumn = -1;
    private Integer scaleColumn = -1;
    private int entryNumberColumn;
    private int entryCodeColumn;
    private int desigColumn;
    private int gidColumn;

    public InventoryTopComponent() {
        initComponents();
        setName(Bundle.CTL_InventoryTopComponent());
        setToolTipText(Bundle.HINT_InventoryTopComponent());

        jTextFieldAmount.addKeyListener(new NumericKeyAdapter(jTextFieldAmount));
        fillComboBoxScales();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel11 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelListName = new javax.swing.JLabel();
        jLabelDescription = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelEntries = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jCheckBoxSameLocation = new javax.swing.JCheckBox();
        jTextFieldLocation = new javax.swing.JTextField();
        jCheckBoxSameComments = new javax.swing.JCheckBox();
        jTextFieldComment = new javax.swing.JTextField();
        jCheckBoxSameAmount = new javax.swing.JCheckBox();
        jCheckBoxSameScale = new javax.swing.JCheckBox();
        jComboBoxScale = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jTextFieldAmount = new javax.swing.JFormattedTextField();
        jPanel12 = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableEntries = new javax.swing.JTable();

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.jLabel4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelListName, org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.jLabelListName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelDescription, org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.jLabelDescription.text")); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/invnetoryLogo.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.jLabel3.text")); // NOI18N

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.jLabel2.text")); // NOI18N

        jLabelEntries.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabelEntries, org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.jLabelEntries.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelEntries, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                    .addComponent(jLabelListName, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabelListName))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabelDescription))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabelEntries))))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.jPanel3.border.title"))); // NOI18N

        jCheckBoxSameLocation.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxSameLocation, org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.jCheckBoxSameLocation.text")); // NOI18N
        jCheckBoxSameLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSameLocationActionPerformed(evt);
            }
        });

        jTextFieldLocation.setText(org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.jTextFieldLocation.text")); // NOI18N
        jTextFieldLocation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldLocationKeyReleased(evt);
            }
        });

        jCheckBoxSameComments.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxSameComments, org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.jCheckBoxSameComments.text")); // NOI18N
        jCheckBoxSameComments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSameCommentsActionPerformed(evt);
            }
        });

        jTextFieldComment.setText(org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.jTextFieldComment.text")); // NOI18N
        jTextFieldComment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldCommentKeyReleased(evt);
            }
        });

        jCheckBoxSameAmount.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxSameAmount, org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.jCheckBoxSameAmount.text")); // NOI18N
        jCheckBoxSameAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSameAmountActionPerformed(evt);
            }
        });

        jCheckBoxSameScale.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxSameScale, org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.jCheckBoxSameScale.text")); // NOI18N
        jCheckBoxSameScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSameScaleActionPerformed(evt);
            }
        });

        jComboBoxScale.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "g", "lbs", "seeds" }));
        jComboBoxScale.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxScaleItemStateChanged(evt);
            }
        });
        jComboBoxScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxScaleActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/findLocation.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.jButton1.text")); // NOI18N
        jButton1.setToolTipText(org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.jButton1.toolTipText")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextFieldAmount.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jTextFieldAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldAmountKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxSameLocation)
                            .addComponent(jCheckBoxSameComments))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldLocation)
                            .addComponent(jTextFieldComment, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxSameAmount)
                            .addComponent(jCheckBoxSameScale))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxScale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 130, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBoxSameLocation)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxSameComments)
                    .addComponent(jTextFieldComment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxSameAmount)
                    .addComponent(jTextFieldAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxSameScale)
                    .addComponent(jComboBoxScale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/save.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave, org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.jButtonSave.text")); // NOI18N
        jButtonSave.setToolTipText(org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.jButtonSave.toolTipText")); // NOI18N
        jButtonSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(355, Short.MAX_VALUE))
        );

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
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1045, Short.MAX_VALUE))
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setListName(String name) {
        this.jLabelListName.setText(name);

    }

    private void findInventoryColumns() {

        GermplasmEntriesTableModel tableModel = (GermplasmEntriesTableModel) this.jTableEntries.getModel();
        amountColumn = tableModel.findColumn("AMOUNT");
        locationColumn = tableModel.findColumn("LOCATION");
        commentColumn = tableModel.findColumn("COMMENT");
        scaleColumn = tableModel.findColumn("SCALE");
        entryNumberColumn = tableModel.findColumn("ENTRY NUMBER");
        entryCodeColumn = tableModel.findColumn("ENTRY CD");
        desigColumn = tableModel.findColumn("DESIG");
        gidColumn = tableModel.findColumn("GID");
    }

    public void setDescription(String description) {
        this.jLabelDescription.setText(description);

    }

    private void jCheckBoxSameLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSameLocationActionPerformed

        GermplasmEntriesTableModel modelo = (GermplasmEntriesTableModel) this.jTableEntries.getModel();

        if (jTableEntries.getCellEditor() != null) {
            jTableEntries.getCellEditor().stopCellEditing();
        }

        if (this.jCheckBoxSameLocation.isSelected()) {

            modelo.setEnabledLocation(false);

            String location = this.jTextFieldLocation.getText();

            for (int i = 0; i < this.jTableEntries.getRowCount(); i++) {
                this.jTableEntries.setValueAt(location, i, 3);
            }
            ajustaColumn(jTableEntries, 3, 2);
        } else {

            modelo.setEnabledLocation(true);
        }
    }//GEN-LAST:event_jCheckBoxSameLocationActionPerformed

    public void ajustaColumn(JTable table, int vColIndex, int margin) {
        if (table.getColumnCount() == 0) {
            return;
        }
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

    private void jTextFieldLocationKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldLocationKeyReleased
        populateLocations();
    }

    private void populateAmount() {
        if (!isNumericAmount()) {
            DialogUtil.displayError(InventoryTopComponent.class, "InventoryTopComponent.numericAmountRequired");
            return;
        }

        if (amountColumn < 0) {
            return;
        }

        if (this.jCheckBoxSameAmount.isSelected()) {
            String amount = this.jTextFieldAmount.getText();
            for (int i = 0; i < this.jTableEntries.getRowCount(); i++) {
                this.jTableEntries.setValueAt(amount, i, amountColumn);
            }
            ajustaColumn(jTableEntries, amountColumn, 2);
        }
    }

    private void populateLocations() {
        if (this.jCheckBoxSameLocation.isSelected()) {
            String location = this.jTextFieldLocation.getText();

            if (locationColumn < 0) {
                return;
            }



            for (int i = 0; i < this.jTableEntries.getRowCount(); i++) {
                this.jTableEntries.setValueAt(location, i, locationColumn);
            }

            ajustaColumn(jTableEntries, locationColumn, 2);

        }
    }//GEN-LAST:event_jTextFieldLocationKeyReleased

    private void jCheckBoxSameCommentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSameCommentsActionPerformed

        GermplasmEntriesTableModel modelo = (GermplasmEntriesTableModel) this.jTableEntries.getModel();

        if (jTableEntries.getCellEditor() != null) {
            jTableEntries.getCellEditor().stopCellEditing();
        }

        if (this.jCheckBoxSameComments.isSelected()) {
            modelo.setEnabledComments(false);
            String location = this.jTextFieldComment.getText();

            for (int i = 0; i < this.jTableEntries.getRowCount(); i++) {
                this.jTableEntries.setValueAt(location, i, 4);
            }
            ajustaColumn(jTableEntries, 4, 2);
        } else {
            modelo.setEnabledComments(true);
        }
    }//GEN-LAST:event_jCheckBoxSameCommentsActionPerformed

    private void jTextFieldCommentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCommentKeyReleased
        if (this.jCheckBoxSameComments.isSelected()) {
            String location = this.jTextFieldComment.getText();

            if (commentColumn < 0) {
                return;
            }


            for (int i = 0; i < this.jTableEntries.getRowCount(); i++) {
                this.jTableEntries.setValueAt(location, i, commentColumn);
            }
            ajustaColumn(jTableEntries, commentColumn, 2);
        }
    }//GEN-LAST:event_jTextFieldCommentKeyReleased

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        saveInventory();

    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jTableEntriesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableEntriesPropertyChange
        this.jLabelEntries.setText(String.valueOf(this.jTableEntries.getRowCount()));
    }//GEN-LAST:event_jTableEntriesPropertyChange

    private void jCheckBoxSameAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSameAmountActionPerformed

        GermplasmEntriesTableModel modelo = (GermplasmEntriesTableModel) this.jTableEntries.getModel();

        if (jTableEntries.getCellEditor() != null) {
            jTableEntries.getCellEditor().stopCellEditing();
        }

        if (this.jCheckBoxSameAmount.isSelected()) {
            modelo.setEnabledAmount(false);
            populateAmount();
            ajustaColumn(jTableEntries, 5, 2);
        } else {
            modelo.setEnabledAmount(true);
        }
    }//GEN-LAST:event_jCheckBoxSameAmountActionPerformed

    private void jCheckBoxSameScaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSameScaleActionPerformed
        GermplasmEntriesTableModel modelo = (GermplasmEntriesTableModel) this.jTableEntries.getModel();

        if (jTableEntries.getCellEditor() != null) {
            jTableEntries.getCellEditor().stopCellEditing();
        }

        if (this.jCheckBoxSameScale.isSelected()) {
            modelo.setEnabledScale(false);

            String location = this.jComboBoxScale.getSelectedItem().toString();

            for (int i = 0; i < this.jTableEntries.getRowCount(); i++) {
                this.jTableEntries.setValueAt(location, i, 6);
            }
            ajustaColumn(jTableEntries, 6, 2);
        } else {
            modelo.setEnabledScale(true);
        }
    }//GEN-LAST:event_jCheckBoxSameScaleActionPerformed

    private void jComboBoxScaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxScaleActionPerformed
    }//GEN-LAST:event_jComboBoxScaleActionPerformed

    private void jComboBoxScaleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxScaleItemStateChanged
        if (this.jCheckBoxSameScale.isSelected()) {
            //String scale = this.jComboBoxScale.getSelectedItem().toString();
            Scales scale = (Scales) jComboBoxScale.getSelectedItem();


            if (scaleColumn < 0) {
                return;
            }

            for (int i = 0; i < this.jTableEntries.getRowCount(); i++) {
                this.jTableEntries.setValueAt(scale, i, scaleColumn);
            }
            ajustaColumn(jTableEntries, scaleColumn, 2);
        }
    }//GEN-LAST:event_jComboBoxScaleItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        SelectLocationPanel selectLocationPanel = new SelectLocationPanel(true);
        String chooseLocation = bundle.getString("InventoryTopComponent.chooseLocation");
        NotifyDescriptor notifyDescriptor = new NotifyDescriptor(selectLocationPanel, chooseLocation, NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(notifyDescriptor) == NotifyDescriptor.OK_OPTION) {
            String value = selectLocationPanel.getLocationName();
            this.jTextFieldLocation.setText(value);
            this.locationId = selectLocationPanel.getLocationId();
            populateLocations();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAmountKeyReleased
        populateAmount();
    }//GEN-LAST:event_jTextFieldAmountKeyReleased

    public void assignGermplasmEntries(List<Factor> factorHeaders, List<List<Object>> germplasmData) {

        List<Factor> losFactores = factorHeaders;

        if (factorHeaders.size() <= 4 || inventoryFactorsAreMissing()) {
            losFactores = addColumnsForInventory(factorHeaders);

        }

        factores = losFactores;
        GermplasmEntriesTableModel tableModel = new GermplasmEntriesTableModel(losFactores, germplasmData);
        tableModel.setIsForInventory(true);
        this.jTableEntries.setModel(tableModel);

        ajustaColumnsTable(this.jTableEntries);
        //addComboScales();
        fillComboBoxScales();
        this.findInventoryColumns();

    }

    public void ajustaColumnsTable(JTable table) {
        for (int c = 0; c < table.getColumnCount(); c++) {
            ajustaColumn(table, c, 2);
        }
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

        factor = new Factor();
        factor.setFactorName("AMOUNT");
        factor.setProperty("GERMPLASM ENTRY");
        factor.setScale("NUMBER");
        factor.setDataType("N");
        losFactores.add(factor);


        factor = new Factor();
        factor.setFactorName("SCALE");
        factor.setProperty("GERMPLASM ENTRY");
        factor.setScale("TEXT");
        factor.setDataType("C");
        losFactores.add(factor);

        return losFactores;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JCheckBox jCheckBoxSameAmount;
    private javax.swing.JCheckBox jCheckBoxSameComments;
    private javax.swing.JCheckBox jCheckBoxSameLocation;
    private javax.swing.JCheckBox jCheckBoxSameScale;
    private javax.swing.JComboBox jComboBoxScale;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelDescription;
    private javax.swing.JLabel jLabelEntries;
    private javax.swing.JLabel jLabelListName;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane7;
    public javax.swing.JTable jTableEntries;
    private javax.swing.JFormattedTextField jTextFieldAmount;
    private javax.swing.JTextField jTextFieldComment;
    private javax.swing.JTextField jTextFieldLocation;
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

    private void saveInventory() {
        findInventoryColumns();
        List<InventoryData> inventoryDataList = new ArrayList<InventoryData>();

        TableModel model = (TableModel) jTableEntries.getModel();

        for (int row = 0; row < model.getRowCount(); row++) {
            InventoryData data = new InventoryData();

            data.setEntry((Integer) model.getValueAt(row, entryNumberColumn));
            data.setDesig((String) model.getValueAt(row, desigColumn));

            if (model.getValueAt(row, gidColumn) instanceof String) {
                String gid = (String) model.getValueAt(row, gidColumn);
                try {
                    int intGid = Integer.parseInt(gid);
                    data.setGid(intGid);
                } catch (Exception e) {
                    data.setGid(0);
                }
            } else if (model.getValueAt(row, gidColumn) instanceof Integer) {
                data.setGid((Integer) model.getValueAt(row, gidColumn));
            }


            data.setLocationid(locationId);


            data.setComment((String) model.getValueAt(row, commentColumn));


            if (model.getValueAt(row, amountColumn) instanceof String) {
                String ammount = (String) model.getValueAt(row, amountColumn);
                data.setAmmount(new Double(ammount));
            } else {
                data.setAmmount((Double) model.getValueAt(row, amountColumn));
            }

            if (model.getValueAt(row, scaleColumn) instanceof Scales) {
                Scales scale = (Scales)model.getValueAt(row, scaleColumn);
                data.setScale(scale.getScaleid());
            } else {
                data.setScale(0);
            }


            inventoryDataList.add(data);
        }


        Integer transactionDate = ConvertUtils.getDateAsInteger(new java.util.Date());
        //Integer loggedUserId = AppServicesProxy.getDefault().appServices().getLoggedUserId();
        Integer loggedUserId = AppServicesProxy.getDefault().appServices().getLoggedUserId(FieldbookSettings.getLocalGmsUserId());
        AppServicesProxy.getDefault().appServices().saveInventoryFromList(listToSave, transactionDate, inventoryDataList, loggedUserId);

        String saveMsg = bundle.getString("InventoryTopComponent.inventorySaved");
        NotifyDescriptor inventorySaved = new NotifyDescriptor.Message(saveMsg  , NotifyDescriptor.INFORMATION_MESSAGE);
        DialogDisplayer.getDefault().notify(inventorySaved);

    }

    private void addComboScales() {
        TableColumn scaleColumn = this.jTableEntries.getColumnModel().getColumn(6);
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("g");
        comboBox.addItem("lbs");
        comboBox.addItem("seeds");
        scaleColumn.setCellEditor(new DefaultCellEditor(comboBox));
    }

    public Listnms getListToSave() {
        return listToSave;
    }

    public void setListToSave(Listnms listToSave) {
        this.listToSave = listToSave;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    private boolean isNumericAmount() {
        boolean result = true;
        try {
            Double.parseDouble(jTextFieldAmount.getText());
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    private void fillComboBoxScales() {
        DefaultComboBoxModel dcbm = (DefaultComboBoxModel) jComboBoxScale.getModel();
        dcbm.removeAllElements();
        List<Scales> seedStockScales = AppServicesProxy.getDefault().appServices().getScalesForInventory();
        for (Scales inventoryScale : seedStockScales) {
            dcbm.addElement(inventoryScale);
        }
    }

    public boolean validateSeedStockTrait() {
        boolean seedStockExists = false;
        Traits seedStockTrait = new Traits(true);

        seedStockTrait.setTrname(InventoryData.TRAIT_SEED_STOCK_TRNAME);
        seedStockTrait.setTrabbr(InventoryData.TRAIT_SEED_STOCK_TRABBR);
        seedStockTrait.setTrdesc(InventoryData.TRAIT_SEED_STOCK_TRDESC);
        seedStockTrait.setTnstat(InventoryData.TRAIT_SEED_STOCK_TNSTAT);
        seedStockTrait.setTraitGroup(InventoryData.TRAIT_SEED_STOCK_TRAITGROUP);
        seedStockTrait.setOntology(InventoryData.TRAIT_SEED_STOCK_ONTOLOGY);
        seedStockTrait.setTraittype(InventoryData.TRAIT_SEED_STOCK_TRAITTYPE);

        List<Traits> traitList = AppServicesProxy.getDefault().appServices().getListTraits(seedStockTrait, 0, 0, false);

        seedStockExists = !traitList.isEmpty();

        if (!seedStockExists) {
            DialogUtil.displayError(NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.traitSeedStockDontExist"));
        }

        return seedStockExists;
    }

    private boolean inventoryFactorsAreMissing() {
        boolean inventoryFactorsAreMissing = false;
        DefaultTableModel tableModel = (DefaultTableModel) this.jTableEntries.getModel();
        amountColumn = tableModel.findColumn("AMOUNT");
        locationColumn = tableModel.findColumn("LOCATION");
        commentColumn = tableModel.findColumn("COMMENT");
        scaleColumn = tableModel.findColumn("SCALE");

        inventoryFactorsAreMissing = amountColumn == -1 || locationColumn == -1 || commentColumn == -1 || scaleColumn == -1;

        return inventoryFactorsAreMissing;
    }
}
