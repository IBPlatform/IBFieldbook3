package ibfb.nursery.mainwizard;

import ibfb.domain.core.Condition;
import ibfb.domain.core.Workbook;
import ibfb.nursery.models.NurseryConditionsTableModel;
import ibfb.nursery.utils.LookupUtil;
import ibfb.nursery.editors.NurseryConditionsRowEditor;
import ibfb.nursery.utils.DateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.utils.ToolTipUtils;
import org.openide.util.NbBundle;

public final class NurseryVisualPanel3 extends JPanel {

    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
    TableRowSorter<TableModel> sorter;
    private List<Condition> nurseryConditions = new ArrayList();
    NurseryConditionsTableModel model = new NurseryConditionsTableModel(nurseryConditions);
    private int fila = 0;
    private static final int VALUE_COLUMN = 5;
    private static final int PROPERTY_COL = 3;
    private static final int SCALE_COL = 4;
    private static final int INSTANCE_COL = 0;
    private ResourceBundle bundle = NbBundle.getBundle(NurseryVisualPanel3.class);


    public NurseryVisualPanel3() {
        initComponents();
        createBallonTips();
    }

    @Override
    public String getName() {
        return bundle.getString("NurseryVisualPanel3.name");
    }

    private void createBallonTips() {
        BalloonTip tip = new BalloonTip(jTableNurseryConditions,bundle.getString("NurseryVisualPanel3.specify"));
        ToolTipUtils.balloonToToolTip(tip, 500, 3000);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableNurseryConditions = new javax.swing.JTable();

        jTableNurseryConditions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableNurseryConditions.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableNurseryConditionsPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(jTableNurseryConditions);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
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

    public int giveMeTotalConditions() {
        return this.jTableNurseryConditions.getRowCount();
    }

    private void jTableNurseryConditionsPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableNurseryConditionsPropertyChange
        if (this.jTableNurseryConditions.getCellEditor() != null) {
            jTableNurseryConditions.getCellEditor().stopCellEditing();
        }
        this.fila = this.jTableNurseryConditions.getEditingRow();
        if (fila == -1) {
            return;
        }
        try {
            if (evt.getOldValue() == null) {

                System.out.println("NO CAMBIO VALOR \n");
                return;
            }


            if (evt.getNewValue() == null) {

                Object valor = this.jTableNurseryConditions.getValueAt(this.fila, 5).toString();
                if (valor.equals("")) {
                    return;
                }

                Object condition = this.jTableNurseryConditions.getValueAt(this.fila, 1).toString().toUpperCase();
                Object property = this.jTableNurseryConditions.getValueAt(this.fila, PROPERTY_COL).toString().toUpperCase();
                Object scale = this.jTableNurseryConditions.getValueAt(this.fila, SCALE_COL).toString().toUpperCase();
                int instancia = Integer.parseInt(this.jTableNurseryConditions.getValueAt(this.fila, INSTANCE_COL).toString());

                if (property.equals(LookupUtil.PERSON) && scale.equals(LookupUtil.DBID)) {
                    LookupUtil.lookupPersonName(jTableNurseryConditions, instancia, valor, PROPERTY_COL, SCALE_COL, VALUE_COLUMN);
                    return;
                }

                if (property.equals(LookupUtil.PERSON) && scale.equals(LookupUtil.DBCV)) {
                    LookupUtil.lookupPersonId(jTableNurseryConditions, instancia, valor, PROPERTY_COL, SCALE_COL, VALUE_COLUMN);
                    return;
                }

                if (property.equals(LookupUtil.LOCATION) && scale.equals(LookupUtil.DBID)) {
                    LookupUtil.lookupLocationName(jTableNurseryConditions, instancia, valor, PROPERTY_COL, SCALE_COL, VALUE_COLUMN);
                    return;
                }

                if (property.equals(LookupUtil.LOCATION) && scale.equals(LookupUtil.DBCV)) {
                    LookupUtil.lookupLocationId(jTableNurseryConditions, instancia, valor, PROPERTY_COL, SCALE_COL, VALUE_COLUMN);
                    return;
                }

                if (condition.equals("PDATE-2 LATE")) {
                    DateUtil.compareDates(instancia, jTableNurseryConditions, fila);
                    return;
                }

                if (property.equals("PLANTING DATE") && scale.equals("DATE")) {
                    DateUtil.compareDates(instancia, jTableNurseryConditions, fila);
                    return;
                }


            }


        } catch (NullPointerException error) {
            System.out.println("NULLPOINTER NURSERY VISUAL PANEL 3 " + error);
        }
    }//GEN-LAST:event_jTableNurseryConditionsPropertyChange

    private int encuentraComplemento(String myConditionToSearch, int instancia) {
        int filaComplemento = -1;
        for (int i = 0; i < this.jTableNurseryConditions.getRowCount(); i++) {
            Object instance = this.jTableNurseryConditions.getValueAt(i, 0);
            Object condition = this.jTableNurseryConditions.getValueAt(i, 1).toString().toUpperCase();
            if (condition.equals(myConditionToSearch) && (instancia == instance)) {
                filaComplemento = i;
                break;
            }
        }
        return filaComplemento;
    }

    @SuppressWarnings("unchecked")
    public void fillData(Workbook workbook) {
        nurseryConditions = new ArrayList();
        int instance = 1;

        for (Condition condition : workbook.getConditions()) {

            if (!condition.getConditionName().equals(condition.getLabel())) {
                Condition condToAdd = new Condition();
                condToAdd.setConditionName(condition.getConditionName());
                condToAdd.setDescription(condition.getDescription());
                condToAdd.setProperty(condition.getProperty());
                condToAdd.setScale(condition.getScale());
                condToAdd.setMethod(condition.getMethod());
                condToAdd.setDataType(condition.getDataType());
                condToAdd.setLabel(condition.getLabel());
                condToAdd.setInstance(instance);
                nurseryConditions.add(condToAdd);
            }

        }


        NurseryConditionsTableModel tableModel = new NurseryConditionsTableModel(nurseryConditions);
        this.jTableNurseryConditions.setModel(tableModel);

        model = (NurseryConditionsTableModel) this.jTableNurseryConditions.getModel();
        sorter = new TableRowSorter<TableModel>(model);
        this.jTableNurseryConditions.setRowSorter(sorter);

        for (int col = 0; col < this.jTableNurseryConditions.getColumnCount(); col++) {
            sorter.setSortable(col, false);
        }

        jTableNurseryConditions.getTableHeader().setReorderingAllowed(false);
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        this.jTableNurseryConditions.getColumnModel().getColumn(0).setCellRenderer(tcr);
        if (this.jTableNurseryConditions.getRowCount() > 0) {
            this.jTableNurseryConditions.setRowSelectionInterval(0, 0);
        }
        assignCellEditor();
    }

    private void assignCellEditor() {
        NurseryConditionsRowEditor nurseryConditionsRowEditor = new NurseryConditionsRowEditor(jTableNurseryConditions);
        TableColumn valueColumn = this.jTableNurseryConditions.getColumnModel().getColumn(VALUE_COLUMN);
        valueColumn.setCellEditor(nurseryConditionsRowEditor);
    }

    public void copyValues() {
        NurseryWizardIterator.nurseryTopComponent.assignNurseryConditions(nurseryConditions);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableNurseryConditions;
    // End of variables declaration//GEN-END:variables
}
