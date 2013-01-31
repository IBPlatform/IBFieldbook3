package ibfb.nursery.mainwizard;

import ibfb.domain.core.Condition;
import ibfb.domain.core.Workbook;
import ibfb.nursery.editors.ConditionsRowEditor;
import ibfb.nursery.models.StudyConditionsTableModel;
import ibfb.nursery.utils.ColumnFitAdapter;
import ibfb.nursery.utils.ExcelCopyPaste;
import ibfb.nursery.utils.LookupUtil;
import java.awt.Color;
import java.util.ResourceBundle;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.utils.ToolTipUtils;
import org.openide.util.NbBundle;

public final class NurseryVisualPanel2 extends JPanel {

    private BalloonTip tip1;
    private static final int PROPERTY_COLUMN = 2;
    private static final int SCALE_COLUMN = 3;
    private static final int VALUE_COLUMN = 4;
    private int fila = 0;
    private ResourceBundle bundle = NbBundle.getBundle(NurseryVisualPanel2.class);

    public NurseryVisualPanel2() {
        initComponents();


        this.jTableStudyConditions.getTableHeader().addMouseListener(new ColumnFitAdapter());
        ExcelCopyPaste myAd = new ExcelCopyPaste(this.jTableStudyConditions);
        createBallonTips();
    }

    @Override
    public String getName() {
        return bundle.getString("NurseryVisualPanel2.name");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableStudyConditions = new javax.swing.JTable();

        jTableStudyConditions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
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
        jScrollPane1.setViewportView(jTableStudyConditions);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addContainerGap())
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
                Condition condition = ((StudyConditionsTableModel) jTableStudyConditions.getModel()).getStudyConditions().get(fila);
                if (condition.getValue() == null) {
                    return;
                }

                Object valor = condition.getValue();
                Object property = this.jTableStudyConditions.getValueAt(this.fila, PROPERTY_COLUMN).toString().toUpperCase();
                Object scale = this.jTableStudyConditions.getValueAt(this.fila, SCALE_COLUMN).toString().toUpperCase();
                if (property.equals(LookupUtil.PERSON) && scale.equals(LookupUtil.DBID)) {
                    LookupUtil.lookupPersonName(jTableStudyConditions, valor, PROPERTY_COLUMN, SCALE_COLUMN, VALUE_COLUMN);
                }

                if (property.equals(LookupUtil.PERSON) && scale.equals(LookupUtil.DBCV)) {
                    LookupUtil.lookupPersonId(jTableStudyConditions, valor, PROPERTY_COLUMN, SCALE_COLUMN, VALUE_COLUMN);
                }

                if (property.equals(LookupUtil.LOCATION) && scale.equals(LookupUtil.DBID)) {
                    LookupUtil.lookupLocationName(jTableStudyConditions, valor, PROPERTY_COLUMN, SCALE_COLUMN, VALUE_COLUMN);
                }

                if (property.equals(LookupUtil.LOCATION) && scale.equals(LookupUtil.DBCV)) {
                    LookupUtil.lookupLocationId(jTableStudyConditions, valor, PROPERTY_COLUMN, SCALE_COLUMN, VALUE_COLUMN);
                }
                
                 
                    if (property.equals(LookupUtil.METHOD) && scale.equals(LookupUtil.DBID)) {
                        LookupUtil.lookupMethodName(jTableStudyConditions, valor, PROPERTY_COLUMN, SCALE_COLUMN, VALUE_COLUMN);
                    }
                    if (property.equals(LookupUtil.METHOD) && scale.equals(LookupUtil.DBCV)) {
                        LookupUtil.lookupMethodId(jTableStudyConditions, valor, PROPERTY_COLUMN, SCALE_COLUMN, VALUE_COLUMN);
                    }
                    
                
            }

        } catch (NullPointerException error) {
            System.out.println("NULLPOINTER ERROR. " + error);
        }
    }//GEN-LAST:event_jTableStudyConditionsPropertyChange

    private void createBallonTips() {
        tip1 = new BalloonTip(jTableStudyConditions, bundle.getString("NurseryVisualPanel2.specify"));
        ToolTipUtils.balloonToToolTip(tip1, 500, 3000);
    }

    @SuppressWarnings("unchecked")
    public void fillData(Workbook workbook) {
        StudyConditionsTableModel studyConditionsTableModel = new StudyConditionsTableModel(workbook.getStudyConditions());
        jTableStudyConditions.setModel(studyConditionsTableModel);
        jTableStudyConditions.getTableHeader().setReorderingAllowed(false);
        if (workbook.getStudyConditions().size() > 0) {
            this.jTableStudyConditions.setRowSelectionInterval(0, 0);
        }
        assignCellEditor();
        
        
    }

    private void assignCellEditor() {
        ConditionsRowEditor conditionsRowEditor = new ConditionsRowEditor(this.jTableStudyConditions);
        TableColumn valueColumn = this.jTableStudyConditions.getColumnModel().getColumn(VALUE_COLUMN);
        valueColumn.setCellEditor(conditionsRowEditor);
        
        TableColumn columnValue = jTableStudyConditions.getColumnModel().getColumn(4);
        DefaultTableCellRenderer valueCellRenderer =  new DefaultTableCellRenderer();
        valueCellRenderer.setToolTipText(bundle.getString("NurseryVisualPanel2.fill"));
        valueCellRenderer.setBackground(Color.YELLOW);
        columnValue.setCellRenderer(valueCellRenderer);     
    }

    public void copyValues() {

        StudyConditionsTableModel model = (StudyConditionsTableModel)this.jTableStudyConditions.getModel();
        NurseryWizardIterator.nurseryTopComponent.assignStudyConditions(model.getStudyConditions());
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableStudyConditions;
    // End of variables declaration//GEN-END:variables
}
