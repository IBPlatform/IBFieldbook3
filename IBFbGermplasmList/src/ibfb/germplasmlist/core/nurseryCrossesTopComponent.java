
package ibfb.germplasmlist.core;

import ibfb.germplasmlist.methods.MethodsClass;
import ibfb.germplasmlist.selectionWizard.SelectionWizardWizardPanel2;
import java.awt.Component;
import java.awt.Cursor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.*;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.cimmyt.cril.ibwb.domain.Listdata;
import org.cimmyt.cril.ibwb.domain.ListdataPK;
import org.cimmyt.cril.ibwb.domain.Listnms;
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


@ConvertAsProperties(dtd = "-//ibfb.germplasmlist.core//nurseryCrosses//EN",
autostore = false)
@TopComponent.Description(preferredID = "nurseryCrossesTopComponent",
iconBase="ibfb/germplasmlist/images/fm.png", 
persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "ibfb.germplasmlist.core.nurseryCrossesTopComponent")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_nurseryCrossesAction",
preferredID = "nurseryCrossesTopComponent")

public final class nurseryCrossesTopComponent extends TopComponent {

     private DefaultTableModel modeloTemporal;
    MethodsClass metodos = new MethodsClass(); 
    
    
    public nurseryCrossesTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(nurseryCrossesTopComponent.class, "CTL_nurseryCrossesTopComponent"));
        setToolTipText(NbBundle.getMessage(nurseryCrossesTopComponent.class, "HINT_nurseryCrossesTopComponent"));

    }

    
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel11 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldListName = new javax.swing.JTextField();
        jButtonSave = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldDescription = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabelEntries = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableEntries = new javax.swing.JTable();

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(nurseryCrossesTopComponent.class, "nurseryCrossesTopComponent.jLabel1.text")); // NOI18N
        jLabel1.setAutoscrolls(true);

        jTextFieldListName.setText(org.openide.util.NbBundle.getMessage(nurseryCrossesTopComponent.class, "nurseryCrossesTopComponent.jTextFieldListName.text")); // NOI18N
        jTextFieldListName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldListNameKeyReleased(evt);
            }
        });

        jButtonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/germplasmlist/images/save.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave, org.openide.util.NbBundle.getMessage(nurseryCrossesTopComponent.class, "nurseryCrossesTopComponent.jButtonSave.text")); // NOI18N
        jButtonSave.setToolTipText(org.openide.util.NbBundle.getMessage(nurseryCrossesTopComponent.class, "nurseryCrossesTopComponent.jButtonSave.toolTipText")); // NOI18N
        jButtonSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(nurseryCrossesTopComponent.class, "nurseryCrossesTopComponent.jLabel4.text")); // NOI18N

        jTextFieldDescription.setText(org.openide.util.NbBundle.getMessage(nurseryCrossesTopComponent.class, "nurseryCrossesTopComponent.jTextFieldDescription.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(nurseryCrossesTopComponent.class, "nurseryCrossesTopComponent.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelEntries, org.openide.util.NbBundle.getMessage(nurseryCrossesTopComponent.class, "nurseryCrossesTopComponent.jLabelEntries.text")); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(jTextFieldListName))
                .addGap(69, 69, 69)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelEntries)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextFieldListName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabelEntries))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jScrollPane7.setAutoscrolls(true);
        jScrollPane7.setMinimumSize(new java.awt.Dimension(0, 0));
        jScrollPane7.setPreferredSize(new java.awt.Dimension(450, 400));

        jTableEntries.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ENTRY", "FTID", "FOCC", "FENT", "FDESIG", "FGID", "MTID", "MOCC", "MENT", "MDESIG", "MGID", "CROSS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldListNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldListNameKeyReleased

   }//GEN-LAST:event_jTextFieldListNameKeyReleased

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        saveList();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jTableEntriesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableEntriesPropertyChange
        this.jLabelEntries.setText(String.valueOf(this.jTableEntries.getRowCount()));
    }//GEN-LAST:event_jTableEntriesPropertyChange

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelEntries;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JScrollPane jScrollPane7;
    public javax.swing.JTable jTableEntries;
    private javax.swing.JTextField jTextFieldDescription;
    private javax.swing.JTextField jTextFieldListName;
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

private void saveList() {
        if (jTextFieldListName.getText().trim().isEmpty()) {
            DialogUtil.displayError("Please fill Nursery advance name");
            jTextFieldListName.requestFocusInWindow();
            return;
        }

        if (this.jTextFieldDescription.getText().isEmpty()) {
            NotifyDescriptor d = new NotifyDescriptor.Message("You need to set a description", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            jTextFieldDescription.requestFocusInWindow();
            return;
        }

        if (this.jTableEntries.getRowCount() == 0) {
            NotifyDescriptor d = new NotifyDescriptor.Message("Your list is empty", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
            return;
        }
        NotifyDescriptor d = new NotifyDescriptor.Confirmation("Do you want to save the germplasm list?", "Save final list",
                NotifyDescriptor.OK_CANCEL_OPTION);
        if (DialogDisplayer.getDefault().notify(d) == NotifyDescriptor.OK_OPTION) {
            saveListIntoDB();
            NotifyDescriptor d2 = new NotifyDescriptor.Message("Your list was saved!", NotifyDescriptor.INFORMATION_MESSAGE);
            DialogDisplayer.getDefault().notify(d2);
            this.jTextFieldDescription.setText("");
        }

        this.jButtonSave.setEnabled(false);
    }


private int findColumn(String col) {
        int myCol;
        try {
            myCol = jTableEntries.getTableHeader().getColumnModel().getColumnIndex(col);
        } catch (NullPointerException ex) {
            myCol = 0;
        }
        return myCol;
    }

 private void saveListIntoDB() {
        changeCursorWaitStatus(true);

        Listnms listnms = new Listnms();
        listnms.setListname(this.jTextFieldListName.getText());
        int fecha = dameFecha();
        listnms.setListdate(fecha);

        listnms.setListtype(Listnms.LIST_TYPE_HARVEST);
        listnms.setListuid(0);
        listnms.setListdesc(this.jTextFieldDescription.getText());
        listnms.setLhierarchy(0);
        listnms.setListstatus(1);
        AppServicesProxy.getDefault().appServices().addListnms(listnms);
        List<Listdata> dataList = new ArrayList<Listdata>();


         int desig = findColumn("CROSS");
         int entryCD = findColumn("ENTRY");
         int source = findColumn("FDESIG");

        int gid = 0;



        for (int i = 0; i < jTableEntries.getRowCount(); i++) {
            Listdata d1 = new Listdata(true);
            ListdataPK pk1 = new ListdataPK(listnms.getListid(), 0);

            d1.setListdataPK(pk1);
            d1.setEntryid(i + 1);
            if (desig > 0) {
                d1.setDesig(this.jTableEntries.getValueAt(i, desig).toString());
            } else {
                d1.setDesig("");
            }
            if (entryCD > 0) {
                d1.setEntrycd(this.jTableEntries.getValueAt(i, entryCD).toString());
            } else {
                d1.setEntrycd("");
            }
                 
               if (source > 0) {
                d1.setSource(this.jTableEntries.getValueAt(i, source).toString());
            } else {
                d1.setSource("SOURC1");
              
            }
  
            d1.setGrpname("grp");
            d1.setLrstatus(0);

            if (gid > 0) {
                d1.setGid(Integer.parseInt(jTableEntries.getValueAt(i, gid).toString()));
            } else {
                d1.setGid(0);
            }
            dataList.add(d1);

        }
        AppServicesProxy.getDefault().appServices().addNewsGermplasm(listnms, dataList, 1);
        changeCursorWaitStatus(false);
    }

 
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

    private int dameFecha() {
        int val;
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
            Date fechaDate = new Date();
            String fecha = formato.format(fechaDate);

            val = ((Integer.parseInt(fecha.substring(0, 4))) * 10000) + ((Integer.parseInt(fecha.substring(4, 6))) * 100)
                    + ((Integer.parseInt(fecha.substring(6, 8))));

        } catch (Exception e) {
            val = 0;
        }
        return val;
    }

 
     public void setModeloEntries(TableModel model) {
       modeloTemporal=(DefaultTableModel) model;
    }

    public void createSelectionList() {
        int total=0;
        DefaultTableModel modeloEntries=(DefaultTableModel) this.jTableEntries.getModel();
        int methodIndex = Integer.parseInt(NbPreferences.forModule(SelectionWizardWizardPanel2.class).get("methodIndex", "0"));
        int convection = Integer.parseInt(NbPreferences.forModule(SelectionWizardWizardPanel2.class).get("convection", "0"));
        metodos.setConvention(convection);
        metodos.setMethod(methodIndex);

        for (int i = 0; i < modeloTemporal.getRowCount(); i++) {

            String femalePedigree = modeloTemporal.getValueAt(i, 4).toString();
            String malePedigree = modeloTemporal.getValueAt(i, 9).toString();
            String newPed=metodos.giveCross(femalePedigree,malePedigree);
      
                modeloEntries.setRowCount(i+1);
                this.jTableEntries.setValueAt(i+1, i, 0);//ENTRY
                this.jTableEntries.setValueAt(modeloTemporal.getValueAt(i, 1), i, 1);//FTID
                this.jTableEntries.setValueAt(modeloTemporal.getValueAt(i, 2), i, 2);//FOCC
                this.jTableEntries.setValueAt(modeloTemporal.getValueAt(i, 3), i, 3);//FENTRY
                this.jTableEntries.setValueAt(modeloTemporal.getValueAt(i, 4), i, 4);//FDESIG
                this.jTableEntries.setValueAt(modeloTemporal.getValueAt(i, 5), i, 5);//FGID
                this.jTableEntries.setValueAt(modeloTemporal.getValueAt(i, 6), i, 6);//MTID
                this.jTableEntries.setValueAt(modeloTemporal.getValueAt(i, 7), i, 7);//MOCC
                this.jTableEntries.setValueAt(modeloTemporal.getValueAt(i, 8), i, 8);//MENTRY
                this.jTableEntries.setValueAt(modeloTemporal.getValueAt(i, 9), i, 9);//MDESIG
                this.jTableEntries.setValueAt(modeloTemporal.getValueAt(i, 10), i, 10);//MGID                     
                this.jTableEntries.setValueAt(newPed,i, 11);//CROSS
             
            
        }

ajustaColumnas();

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

       public void ajustaColumnsTable(JTable table, int margin) {
        for (int c = 0; c < table.getColumnCount(); c++) {
            ajustaColumn(table, c, 2);
        }
    }
       
    private void ajustaColumnas() {
        for (int i = 0; i < 4; i++) {
            ajustaColumnsTable(this.jTableEntries, 2);
        }

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.LEFT);
        for (int col = 0; col < this.jTableEntries.getColumnCount(); col++) {
            this.jTableEntries.getColumnModel().getColumn(col).setCellRenderer(tcr);
        }

        
         this.jTableEntries.getColumnModel().getColumn(4).setCellRenderer(tcr2);
          this.jTableEntries.getColumnModel().getColumn(9).setCellRenderer(tcr2);
          this.jTableEntries.getColumnModel().getColumn(11).setCellRenderer(tcr2);
        
    }
    
    

}



