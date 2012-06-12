
package ibfb.workbook.ui;

import ibfb.workbook.renders.ColorRenderer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;

@ConvertAsProperties(dtd = "-//ibfb.workbook.ui//CreateTemplate//EN",
autostore = false)
@TopComponent.Description(preferredID = "CreateTemplateTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "ibfb.workbook.ui.CreateTemplateTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_CreateTemplateAction",
preferredID = "CreateTemplateTopComponent")
public final class CreateTemplateTopComponent extends TopComponent {

    DefaultTableModel studyModel;
    DefaultTableModel conditionModel;
    DefaultTableModel factorModel;
    DefaultTableModel constantModel;
    DefaultTableModel variateModel;

    public CreateTemplateTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(CreateTemplateTopComponent.class, "CTL_CreateTemplateTopComponent"));
        setToolTipText(NbBundle.getMessage(CreateTemplateTopComponent.class, "HINT_CreateTemplateTopComponent"));
        configTables();
        assignCellEditors();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItemAdd = new javax.swing.JMenuItem();
        jMenuItemDelete = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableConditions = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableFactors = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableConstants = new javax.swing.JTable();
        jScrollPaneVariates = new javax.swing.JScrollPane();
        jTableVariates = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableStudy = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButtonNew = new javax.swing.JButton();
        jButtonClose = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemAdd, org.openide.util.NbBundle.getMessage(CreateTemplateTopComponent.class, "CreateTemplateTopComponent.jMenuItemAdd.text")); // NOI18N
        jMenuItemAdd.setToolTipText(org.openide.util.NbBundle.getMessage(CreateTemplateTopComponent.class, "CreateTemplateTopComponent.jMenuItemAdd.toolTipText")); // NOI18N
        jMenuItemAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAddActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemAdd);

        org.openide.awt.Mnemonics.setLocalizedText(jMenuItemDelete, org.openide.util.NbBundle.getMessage(CreateTemplateTopComponent.class, "CreateTemplateTopComponent.jMenuItemDelete.text")); // NOI18N
        jPopupMenu1.add(jMenuItemDelete);

        jScrollPane1.setComponentPopupMenu(jPopupMenu1);

        jTableConditions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "CONDITION", "DESCRIPTION", "PROPERTY", "SCALE", "METHOD", "DATA TYPE", "VALUE", "LABEL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableConditions.setComponentPopupMenu(jPopupMenu1);
        jTableConditions.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableConditions);

        jScrollPane2.setComponentPopupMenu(jPopupMenu1);
        jScrollPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTableFactors.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FACTOR", "DESCRIPTION", "PROPERTY", "SCALE", "METHOD", "DATA TYPE", "NESTED IN", "LABEL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableFactors.setComponentPopupMenu(jPopupMenu1);
        jTableFactors.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTableFactors);

        jTableConstants.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CONSTANT", "DESCRIPTION", "PROPERTY", "SCALE", "METHOD", "DATA TYPE", "VALUE", "LABEL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableConstants.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTableConstants);

        jTableVariates.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "VARIATE", "DESCRIPTION", "PROPERTY", "SCALE", "METHOD", "DATA TYPE", "SAMPLE LEVEL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableVariates.getTableHeader().setReorderingAllowed(false);
        jScrollPaneVariates.setViewportView(jTableVariates);

        jTableStudy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"STUDY", null},
                {"TITLE", null},
                {"PMKEY", null},
                {"OBJECTIVE", null},
                {"START DATE", null},
                {"END DATE", null},
                {"STUDY TYPE", null}
            },
            new String [] {
                "PROPERTY", "VALUE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableStudy.setOpaque(false);
        jScrollPane5.setViewportView(jTableStudy);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jButtonNew, org.openide.util.NbBundle.getMessage(CreateTemplateTopComponent.class, "CreateTemplateTopComponent.jButtonNew.text")); // NOI18N
        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonClose, org.openide.util.NbBundle.getMessage(CreateTemplateTopComponent.class, "CreateTemplateTopComponent.jButtonClose.text")); // NOI18N
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonNew, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jButtonClose, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonNew, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 902, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 902, Short.MAX_VALUE)
                            .addComponent(jScrollPaneVariates, javax.swing.GroupLayout.DEFAULT_SIZE, 902, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
                        .addGap(12, 12, 12))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneVariates, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        if (this.jButtonClose.getText().equals("CLOSE")) {
            this.close();
        } else {
            this.createNewModels();
            this.jButtonNew.setText("NEW");
            this.jButtonClose.setText("CLOSE");
            this.jTableStudy.setEnabled(false);
            this.jTableConditions.setEnabled(false);
            this.jTableConstants.setEnabled(false);
            this.jTableFactors.setEnabled(false);
            this.jTableVariates.setEnabled(false);
        }

    }//GEN-LAST:event_jButtonCloseActionPerformed

    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActionPerformed
        if (this.jButtonNew.getText().equals("NEW")) {


            this.jTableStudy.setEnabled(true);
            this.jTableConditions.setEnabled(true);
            this.jTableConstants.setEnabled(true);
            this.jTableFactors.setEnabled(true);
            this.jTableVariates.setEnabled(true);
            this.jButtonNew.setText("CREATE TEMPLATE");
            this.jButtonClose.setText("CANCEL");
        } else {
            saveTemplate();
        }

    }//GEN-LAST:event_jButtonNewActionPerformed

    private void jMenuItemAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAddActionPerformed
  
    }//GEN-LAST:event_jMenuItemAddActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonNew;
    private javax.swing.JMenuItem jMenuItemAdd;
    private javax.swing.JMenuItem jMenuItemDelete;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPaneVariates;
    private javax.swing.JTable jTableConditions;
    private javax.swing.JTable jTableConstants;
    private javax.swing.JTable jTableFactors;
    private javax.swing.JTable jTableStudy;
    private javax.swing.JTable jTableVariates;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {

        createNewModels();
        this.jButtonNew.setText("NEW");
        this.jButtonClose.setText("CLOSE");
        this.jTableStudy.setEnabled(false);
        this.jTableConditions.setEnabled(false);
        this.jTableConstants.setEnabled(false);
        this.jTableFactors.setEnabled(false);
        this.jTableVariates.setEnabled(false);
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

    private void saveTemplate() {
    }

    private void createNewModels() {
        //JTableHeader header = jTableStudy.getTableHeader();
        //header.setVisible(false);

        this.studyModel = (DefaultTableModel) jTableStudy.getModel();

        this.jTableStudy.clearSelection();

        for (int i = 0; i < studyModel.getRowCount(); i++) {
            studyModel.setValueAt("", i, 1);
        }
        
        //this.conditionModel = (DefaultTableModel) jTableConditions.getModel();
       // conditionModel.setRowCount(0);

        this.factorModel = (DefaultTableModel) jTableFactors.getModel();
        factorModel.setRowCount(0);

        this.constantModel = (DefaultTableModel) jTableConstants.getModel();
        constantModel.setRowCount(0);

        this.variateModel = (DefaultTableModel) jTableVariates.getModel();
        variateModel.setRowCount(0);

    }

    private void configTables() {
        Color colorVerde = new Color(0, 100, 0);
        Color colorAzul = new Color(72, 61, 139);
        jTableConditions.getTableHeader().setBackground(colorVerde);
        jTableConditions.getTableHeader().setForeground(Color.WHITE);
        jTableFactors.getTableHeader().setBackground(colorVerde);
        jTableFactors.getTableHeader().setForeground(Color.WHITE);
        jTableConstants.getTableHeader().setBackground(colorAzul);
        jTableConstants.getTableHeader().setForeground(Color.WHITE);
        jTableVariates.getTableHeader().setBackground(colorAzul);
        jTableVariates.getTableHeader().setForeground(Color.WHITE);      
        ColorRenderer cr=new ColorRenderer(Color.RED);        
        this.jTableStudy.getColumnModel().getColumn(0).setCellRenderer(cr);                 
    }
    
 @SuppressWarnings("unchecked")   
        private void assignCellEditors() {
        JComboBox propertyJCB = new JComboBox();
        ArrayList propertyList = new ArrayList();
        propertyList.add("TRIAL");
        propertyList.add("SEASON");
        propertyList.add("PRINCIPAL INVESTIGATOR");
        propertyList.add("TRIAL INSTANCE");
        propertyList.add("PERSON");
        propertyList.add("TRIAL LOCATION");
        propertyList.add("PLANTING DATE");
        propertyList.add("FERTILIZER");
        propertyList.add("GERMPLASM ENTRY");
        propertyList.add("GERMPLASM ID");
        propertyList.add("FIELD PLOT");
        propertyList.add("REPLICATION");
        propertyList.add("PLOT");
        propertyList.add("ROW IN LAYOUT");
        propertyList.add("COLUMN IN LAYOUT");
        Collections.sort(propertyList);
        
            for (int i = 0; i < propertyList.size(); i++) {
            propertyJCB.addItem(propertyList.get(i).toString());
            }

       propertyJCB.setSelectedItem(propertyJCB.getItemAt(0));
        
       jTableConditions.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(propertyJCB));
       jTableFactors.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(propertyJCB));
       jTableConstants.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(propertyJCB));
       jTableVariates.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(propertyJCB)); 
        
        
       // AutoCompleteJCB autoCompleteProperty = new AutoCompleteJCB(propertyJCB);
       
       
        JComboBox scaleJCB = new JComboBox();
        ArrayList scaleList = new ArrayList();
        scaleList.add("NUMBER");
        scaleList.add("CODE");
        scaleList.add("DBID");
        scaleList.add("DBCV");
        scaleList.add("DATE");
        
        Collections.sort(scaleList);
        
            for (int i = 0; i < scaleList.size(); i++) {
            scaleJCB.addItem(scaleList.get(i).toString());
            }

       scaleJCB.setSelectedItem(scaleJCB.getItemAt(0));
        
       jTableConditions.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(scaleJCB));
      // jTableFactors.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(propertyJCB));
      // jTableConstants.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(propertyJCB));
      // jTableVariates.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(propertyJCB)); 
       
      //----------------------------------------------------------------------------------------------------
       
        JComboBox methodJCB = new JComboBox();
        ArrayList methodList = new ArrayList();
        methodList.add("TRIAL METHOD");
        methodList.add("ASSIGNED");
        methodList.add("ENUMERATED");
        methodList.add("APPLIED");
        methodList.add("FIELD APPLICATION");
        methodList.add("FIELD OBSERVATION");
        methodList.add("COMPUTED");
        methodList.add("FIRST APPLICATION");
        methodList.add("SECOND APPLICATION");
        methodList.add("THIRD APPLICATION");
        methodList.add("NOT ESPECIFIED");
        
        Collections.sort(methodList);
        
            for (int i = 0; i < methodList.size(); i++) {
            methodJCB.addItem(methodList.get(i).toString());
            }

       methodJCB.setSelectedItem(methodJCB.getItemAt(0));
        
       jTableConditions.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(methodJCB));
       jTableFactors.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(methodJCB));
       jTableConstants.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(methodJCB));
       //jTableVariates.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(propertyJCB)); 
      
     //----------------------------------------------------------------------------------------------------
       
        JComboBox dataTypeJCB = new JComboBox();
        ArrayList dataTypeList = new ArrayList();
        dataTypeList.add("N");
        dataTypeList.add("C");
        
        
        Collections.sort(dataTypeList);
        
            for (int i = 0; i < dataTypeList.size(); i++) {
            dataTypeJCB.addItem(dataTypeList.get(i).toString());
            }

       dataTypeJCB.setSelectedItem(dataTypeJCB.getItemAt(0));
       dataTypeJCB.setSelectedIndex(0);
        
       jTableConditions.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(dataTypeJCB));
       jTableFactors.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(dataTypeJCB));
       jTableConstants.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(dataTypeJCB));
       jTableVariates.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(dataTypeJCB)); 
       
     
        //----------------------------------------------------------------------------------------------------
       
        JComboBox labelJCB = new JComboBox();
        ArrayList labelList = new ArrayList();
        labelList.add("STUDY");
        labelList.add("TRIAL");
        labelList.add("FERTLE");
        labelList.add("ENTRY");
        labelList.add("PLOT");
        
        
        Collections.sort(labelList);
        
            for (int i = 0; i < labelList.size(); i++) {
            labelJCB.addItem(labelList.get(i).toString());
            }

       labelJCB.setSelectedItem(labelJCB.getItemAt(0));
        
         jTableConditions.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(labelJCB));
       //jTableFactors.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(labelJCB));
       //jTableConstants.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(labelJCB));
       //jTableVariates.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(labelJCB)); 
     
    }
    
}
