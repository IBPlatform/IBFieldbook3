package ibfb.studyeditor.wizard;

import ibfb.domain.core.Condition;
import ibfb.domain.core.Workbook;
import ibfb.studyeditor.core.StudyEditorTopComponent;
import ibfb.studyeditor.core.model.StudyConditionsTableModel;
import ibfb.studyeditor.roweditors.ColumnFitAdapter;
import ibfb.studyeditor.roweditors.ConditionsRowEditor;
import ibfb.studyeditor.roweditors.ExcelCopyPaste;
import ibfb.studyeditor.util.LookupUtil;
import java.awt.Color;
import java.util.ResourceBundle;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.utils.ToolTipUtils;
import org.openide.util.NbBundle;

public final class TrialWizardVisualPanel2 extends JPanel {
    private ResourceBundle bundle = NbBundle.getBundle(TrialWizardVisualPanel2.class);

    private SpinnerNumberModel modeloinstances = new SpinnerNumberModel(1, 1, 900, 1);
    private int fila = 0;
    
    private int instances = 1;
    private BalloonTip tip1;
    private BalloonTip tip2;
    private static final int PROPERTY_COLUMN = 2;
    private static final int SCALE_COLUMN = 3;
    private static final int VALUE_COLUMN = 4;

    public int getInstances() {
        return instances;
    }

    public void setInstances(int instances) {
        this.instances = instances;
    }

    public TrialWizardVisualPanel2() {
        initComponents();
        this.jSpinnerInstances.setModel(modeloinstances);
        JFormattedTextField ftf = getTextField(jSpinnerInstances);
        if (ftf != null) {
            ftf.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
        }
        
        this.jTableStudyConditions.getTableHeader().addMouseListener(new ColumnFitAdapter());
        ExcelCopyPaste myAd = new ExcelCopyPaste(this.jTableStudyConditions);
        createBallonTips();

        
    }

    @Override
    public String getName() {
        return bundle.getString("TrialWizardVisualPanel2.title");
    }

    public void setLabelInstances(String label) {
        
        String cad =  bundle.getString("TrialWizardVisualPanel2.specifyNumberOfInstances") + " " + label;
        this.jLabelInstances.setText(cad);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrllTable = new javax.swing.JScrollPane();
        jTableStudyConditions = new javax.swing.JTable();
        pnlInstances = new javax.swing.JPanel();
        jLabelInstances = new javax.swing.JLabel();
        jSpinnerInstances = new javax.swing.JSpinner();

        jTableStudyConditions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Study Condition", "Description", "Property", "Scale", "Value"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableStudyConditions.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableStudyConditionsPropertyChange(evt);
            }
        });
        scrllTable.setViewportView(jTableStudyConditions);

        pnlInstances.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlInstances.setToolTipText(org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel2.class, "TrialWizardVisualPanel2.pnlInstances.toolTipText")); // NOI18N

        jLabelInstances.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabelInstances, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel2.class, "TrialWizardVisualPanel2.jLabelInstances.text")); // NOI18N

        jSpinnerInstances.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        jSpinnerInstances.setToolTipText(org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel2.class, "TrialWizardVisualPanel2.jSpinnerInstances.toolTipText")); // NOI18N
        jSpinnerInstances.setRequestFocusEnabled(false);
        jSpinnerInstances.setValue(1);
        jSpinnerInstances.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerInstancesStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlInstancesLayout = new javax.swing.GroupLayout(pnlInstances);
        pnlInstances.setLayout(pnlInstancesLayout);
        pnlInstancesLayout.setHorizontalGroup(
            pnlInstancesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInstancesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelInstances, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinnerInstances, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(165, Short.MAX_VALUE))
        );
        pnlInstancesLayout.setVerticalGroup(
            pnlInstancesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInstancesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInstancesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinnerInstances, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jLabelInstances, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrllTable, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                    .addComponent(pnlInstances, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrllTable, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlInstances, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTableStudyConditionsPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableStudyConditionsPropertyChange

        if (this.jTableStudyConditions.getCellEditor() != null) {
            jTableStudyConditions.getCellEditor().stopCellEditing();
        }


        this.fila = this.jTableStudyConditions.getEditingRow();

        if (fila == -1) {
            return;
        }

        try {
            if (evt.getOldValue() == null) {
                return;
            }


            if (evt.getNewValue() == null) {
                Condition condition = ((StudyConditionsTableModel)jTableStudyConditions.getModel()).getStudyConditions().get(fila);
                if (condition.getValue() == null) {
                    return;
                }
                //Object valor = this.jTableStudyConditions.getValueAt(this.fila, 4).toString().toUpperCase();
                //if (valor.equals("")) {
                //    return;
                //}
                Object valor = condition.getValue();

                Object property = this.jTableStudyConditions.getValueAt(this.fila, PROPERTY_COLUMN).toString().toUpperCase();
                Object scale = this.jTableStudyConditions.getValueAt(this.fila,SCALE_COLUMN).toString().toUpperCase();
                if (property.equals(LookupUtil.PERSON) && scale.equals(LookupUtil.DBID)) {
                    LookupUtil.lookupPersonName(jTableStudyConditions, valor, PROPERTY_COLUMN, SCALE_COLUMN,VALUE_COLUMN);
                }

                if (property.equals(LookupUtil.PERSON) && scale.equals(LookupUtil.DBCV)) {
                    LookupUtil.lookupPersonId(jTableStudyConditions, valor, PROPERTY_COLUMN, SCALE_COLUMN,VALUE_COLUMN);
                }

                if (property.equals(LookupUtil.LOCATION) && scale.equals(LookupUtil.DBID)) {
                    LookupUtil.lookupLocationName(jTableStudyConditions, valor, PROPERTY_COLUMN, SCALE_COLUMN,VALUE_COLUMN);
                }

                if (property.equals(LookupUtil.LOCATION) && scale.equals(LookupUtil.DBCV)) {
                    LookupUtil.lookupLocationId(jTableStudyConditions, valor, PROPERTY_COLUMN, SCALE_COLUMN,VALUE_COLUMN);
                }
                
                

            }


        } catch (NullPointerException error) {
            error.printStackTrace();
        }
    }//GEN-LAST:event_jTableStudyConditionsPropertyChange

    private void jSpinnerInstancesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerInstancesStateChanged

        this.setInstances(Integer.valueOf(this.jSpinnerInstances.getValue().toString()));

}//GEN-LAST:event_jSpinnerInstancesStateChanged

    @SuppressWarnings("unchecked")
    public void fillData(Workbook workbook) {
        StudyConditionsTableModel studyConditionsTableModel = new StudyConditionsTableModel(workbook.getStudyConditions());
        jTableStudyConditions.setModel(studyConditionsTableModel);
        jTableStudyConditions.getTableHeader().setReorderingAllowed(false);
        if (workbook.getStudyConditions().size() > 0) {
            this.jTableStudyConditions.setRowSelectionInterval(0, 0);
        }
        assignCellEditor();
        jSpinnerInstances.setEnabled(workbook.hasPropertyTrialInstance());
    }

    public void copyValues() {

        StudyEditorTopComponent studyWindow = TrialWizardWizardIterator.studyTopComponent;
        StudyConditionsTableModel model = (StudyConditionsTableModel)this.jTableStudyConditions.getModel();
        studyWindow.assignStudyConditions(model.getStudyConditions());
    }

    public JSpinner getjSpinnerInstances() {
        return jSpinnerInstances;
    }

    public void setjSpinnerInstances(JSpinner jSpinnerInstances) {
        this.jSpinnerInstances = jSpinnerInstances;
    }

    private void assignCellEditor() {
        ConditionsRowEditor conditionsRowEditor = new ConditionsRowEditor(this.jTableStudyConditions);
        TableColumn valueColumn = this.jTableStudyConditions.getColumnModel().getColumn(VALUE_COLUMN);
        valueColumn.setCellEditor(conditionsRowEditor);
        TableColumn columnValue = jTableStudyConditions.getColumnModel().getColumn(4);
        DefaultTableCellRenderer valueCellRenderer =  new DefaultTableCellRenderer();
        valueCellRenderer.setToolTipText(bundle.getString("TrialWizardVisualPanel2.fillThisValue"));
        valueCellRenderer.setBackground(Color.YELLOW);
        columnValue.setCellRenderer(valueCellRenderer);        
    }

    public JFormattedTextField getTextField(JSpinner spinner) {
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            return ((JSpinner.DefaultEditor) editor).getTextField();
        } else {
            System.err.println("Unexpected editor type: "
                    + spinner.getEditor().getClass());
            return null;
        }
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelInstances;
    private javax.swing.JSpinner jSpinnerInstances;
    public javax.swing.JTable jTableStudyConditions;
    private javax.swing.JPanel pnlInstances;
    private javax.swing.JScrollPane scrllTable;
    // End of variables declaration//GEN-END:variables

    private void createBallonTips() {
        tip1 = new BalloonTip(jTableStudyConditions, bundle.getString("TrialWizardVisualPanel2.tipStudyConditions"));
        ToolTipUtils.balloonToToolTip(tip1, 500, 3000);
        tip2 = new BalloonTip(jSpinnerInstances, bundle.getString("TrialWizardVisualPanel2.tipStudyInstances"));
        ToolTipUtils.balloonToToolTip(tip2, 500, 3000);
    }
}
