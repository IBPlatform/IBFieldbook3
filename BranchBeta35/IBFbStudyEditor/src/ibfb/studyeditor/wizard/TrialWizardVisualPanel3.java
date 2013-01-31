package ibfb.studyeditor.wizard;

import com.jidesoft.plaf.basic.Resource;
import ibfb.domain.core.Condition;
import ibfb.domain.core.Workbook;
import ibfb.studyeditor.core.StudyEditorTopComponent;
import ibfb.studyeditor.core.model.TrialConditionsTableModel;
import ibfb.studyeditor.roweditors.ColumnFitAdapter;
import ibfb.studyeditor.roweditors.TrialConditionsRowEditor;
import ibfb.studyeditor.util.DateUtil;
import ibfb.studyeditor.util.LookupUtil;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.PatternSyntaxException;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.utils.ToolTipUtils;
import org.openide.util.NbBundle;

public final class TrialWizardVisualPanel3 extends JPanel {
    private ResourceBundle bundle = NbBundle.getBundle(TrialWizardVisualPanel3.class);

    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
    TableRowSorter<TableModel> sorter;
    private List<Condition> trialConditions = new ArrayList<Condition>();
    TrialConditionsTableModel model = new TrialConditionsTableModel(trialConditions);
    private int fila = 0;
    private static final int VALUE_COLUMN = 5;
    private static final int PROPERTY_COL = 3;
    private static final int SCALE_COL = 4;
    private static final int INSTANCE_COL = 0;

    public TrialWizardVisualPanel3() {
        initComponents();
        //model = (DefaultTableModel) this.jTableTrialConditions.getModel();
        model = (TrialConditionsTableModel) this.jTableTrialConditions.getModel();
        sorter = new TableRowSorter<TableModel>(model);
        this.jTableTrialConditions.setRowSorter(sorter);

        this.jTableTrialConditions.getTableHeader().addMouseListener(new ColumnFitAdapter());
        createBallonTips();
    }

    @Override
    public String getName() {
        return bundle.getString("TrialWizardVisualPanel3.title");
    }

    public JSpinner getjSpinnerTrial() {
        return jSpinnerTrial;
    }

    public void setjSpinnerTrial(JSpinner jSpinnerTrial) {
        this.jSpinnerTrial = jSpinnerTrial;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlFilter = new javax.swing.JPanel();
        jRadioButtonFilter = new javax.swing.JRadioButton();
        jRadioButtonAllTrials = new javax.swing.JRadioButton();
        lblTrial = new javax.swing.JLabel();
        jSpinnerTrial = new javax.swing.JSpinner();
        scrlTable = new javax.swing.JScrollPane();
        jTableTrialConditions = new javax.swing.JTable();

        pnlFilter.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlFilter.setToolTipText(org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel3.class, "TrialWizardVisualPanel3.pnlFilter.toolTipText")); // NOI18N

        buttonGroup1.add(jRadioButtonFilter);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonFilter, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel3.class, "TrialWizardVisualPanel3.jRadioButtonFilter.text")); // NOI18N
        jRadioButtonFilter.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonFilterItemStateChanged(evt);
            }
        });

        buttonGroup1.add(jRadioButtonAllTrials);
        jRadioButtonAllTrials.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonAllTrials, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel3.class, "TrialWizardVisualPanel3.jRadioButtonAllTrials.text")); // NOI18N
        jRadioButtonAllTrials.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonAllTrialsItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lblTrial, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel3.class, "TrialWizardVisualPanel3.lblTrial.text")); // NOI18N

        jSpinnerTrial.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerTrialStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlFilterLayout = new javax.swing.GroupLayout(pnlFilter);
        pnlFilter.setLayout(pnlFilterLayout);
        pnlFilterLayout.setHorizontalGroup(
            pnlFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFilterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonFilter)
                .addGap(26, 26, 26)
                .addComponent(lblTrial)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinnerTrial, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                .addComponent(jRadioButtonAllTrials)
                .addGap(63, 63, 63))
        );
        pnlFilterLayout.setVerticalGroup(
            pnlFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFilterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jRadioButtonAllTrials)
                    .addComponent(jSpinnerTrial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButtonFilter)
                        .addComponent(lblTrial)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTableTrialConditions.setModel(new TrialConditionsTableModel(trialConditions));
        jTableTrialConditions.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableTrialConditionsPropertyChange(evt);
            }
        });
        scrlTable.setViewportView(jTableTrialConditions);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlFilter, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrlTable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrlTable, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButtonFilterItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonFilterItemStateChanged
        if (this.jRadioButtonFilter.isSelected()) {
            int num = (Integer) this.jSpinnerTrial.getValue();
            try {
                sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, num, 0));
            } catch (PatternSyntaxException pse) {
                System.err.println("Bad regex pattern");
            }
        }
}//GEN-LAST:event_jRadioButtonFilterItemStateChanged

    private void jRadioButtonAllTrialsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonAllTrialsItemStateChanged
        if (this.jRadioButtonAllTrials.isSelected()) {
            sorter.setRowFilter(null);
        }
}//GEN-LAST:event_jRadioButtonAllTrialsItemStateChanged

    private void jSpinnerTrialStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerTrialStateChanged
        if (this.jRadioButtonFilter.isSelected()) {
            int num = (Integer) this.jSpinnerTrial.getValue();
            try {
                sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, num, 0));
            } catch (PatternSyntaxException pse) {
                System.err.println("Bad regex pattern");
            }
        }
}//GEN-LAST:event_jSpinnerTrialStateChanged

    private void jTableTrialConditionsPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableTrialConditionsPropertyChange

        if (this.jTableTrialConditions.getCellEditor() != null) {
            jTableTrialConditions.getCellEditor().stopCellEditing();
        }
        this.fila = this.jTableTrialConditions.getEditingRow();
        if (fila == -1) {
            return;
        }
        try {
            if (evt.getOldValue() == null) {

                System.out.println("NO CAMBIO VALOR \n");
                return;
            }


            if (evt.getNewValue() == null) {

                Object valor = this.jTableTrialConditions.getValueAt(this.fila, 5).toString();
                if (valor.equals("")) {
                    return;
                }
                System.out.println("FILA: " + fila);
                System.out.println("Valor: " + valor + "\n");

                Object condition = this.jTableTrialConditions.getValueAt(this.fila, 1).toString().toUpperCase();
                Object property = this.jTableTrialConditions.getValueAt(this.fila, PROPERTY_COL).toString().toUpperCase();
                Object scale = this.jTableTrialConditions.getValueAt(this.fila, SCALE_COL).toString().toUpperCase();
                int instancia = Integer.parseInt(this.jTableTrialConditions.getValueAt(this.fila, INSTANCE_COL).toString());

                if (property.equals(LookupUtil.PERSON) && scale.equals(LookupUtil.DBID)) {
                    LookupUtil.lookupPersonName(jTableTrialConditions, instancia, valor, PROPERTY_COL, SCALE_COL, VALUE_COLUMN);
                    return;
                }

                if (property.equals(LookupUtil.PERSON) && scale.equals(LookupUtil.DBCV)) {
                    LookupUtil.lookupPersonId(jTableTrialConditions, instancia, valor, PROPERTY_COL, SCALE_COL, VALUE_COLUMN);
                    return;
                }

                if (property.equals(LookupUtil.LOCATION) && scale.equals(LookupUtil.DBID)) {
                    LookupUtil.lookupLocationName(jTableTrialConditions, instancia, valor, PROPERTY_COL, SCALE_COL, VALUE_COLUMN);
                    return;
                }

                if (property.equals(LookupUtil.LOCATION) && scale.equals(LookupUtil.DBCV)) {
                    LookupUtil.lookupLocationId(jTableTrialConditions, instancia, valor, PROPERTY_COL, SCALE_COL, VALUE_COLUMN);
                    return;
                }

                if (condition.equals("PDATE-1 LATE")) {
                    //comparaFechas(0, valor, instancia, fila);
                   DateUtil.compareDates(instancia, jTableTrialConditions, fila);
                    return;
                }
                
                if (condition.equals("PDATE-2 LATE")) {
                    //comparaFechas(0, valor, instancia, fila);
                    DateUtil.compareDates(instancia,jTableTrialConditions, fila);
                    return;
                }


            }


        } catch (NullPointerException error) {
        }
    }//GEN-LAST:event_jTableTrialConditionsPropertyChange

    @SuppressWarnings("unchecked")
    public void fillData(Workbook workbook, Integer instances) {
        trialConditions = new ArrayList();
        int instance = 1;
        for (int j = 0; j < instances; j++) {
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
                    trialConditions.add(condToAdd);
                }

            }
            instance++;
        }
        TrialConditionsTableModel tableModel = new TrialConditionsTableModel(trialConditions);
        this.jTableTrialConditions.setModel(tableModel);

        model = (TrialConditionsTableModel) this.jTableTrialConditions.getModel();
        sorter = new TableRowSorter<TableModel>(model);
        this.jTableTrialConditions.setRowSorter(sorter);

        for (int col = 0; col < this.jTableTrialConditions.getColumnCount(); col++) {
            sorter.setSortable(col, false);
        }
        jTableTrialConditions.getTableHeader().setReorderingAllowed(false);
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        this.jTableTrialConditions.getColumnModel().getColumn(0).setCellRenderer(tcr);
        if (this.jTableTrialConditions.getRowCount() > 0) {
            this.jTableTrialConditions.setRowSelectionInterval(0, 0);
        }
        assignCellEditor();
    }

    private void assignCellEditor() {
        TrialConditionsRowEditor trialConditionsRowEditor = new TrialConditionsRowEditor(jTableTrialConditions);
        TableColumn valueColumn = this.jTableTrialConditions.getColumnModel().getColumn(VALUE_COLUMN);
        valueColumn.setCellEditor(trialConditionsRowEditor);
        TableColumn columnValue = jTableTrialConditions.getColumnModel().getColumn(VALUE_COLUMN);
        DefaultTableCellRenderer valueCellRenderer =  new DefaultTableCellRenderer();
        valueCellRenderer.setToolTipText(bundle.getString("TrialWizardVisualPanel3.fillThisValue"));
        valueCellRenderer.setBackground(Color.YELLOW);
        columnValue.setCellRenderer(valueCellRenderer);    
    }

    public void copyValues() {
        StudyEditorTopComponent studyWindow = TrialWizardWizardIterator.studyTopComponent;
        studyWindow.assignTrialConditions(trialConditions);
    }

    private void createBallonTips() {
        BalloonTip tip = new BalloonTip(jTableTrialConditions, bundle.getString("TrialWizardVisualPanel3.tipTrialConditions"));
        ToolTipUtils.balloonToToolTip(tip, 500, 3000);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton jRadioButtonAllTrials;
    private javax.swing.JRadioButton jRadioButtonFilter;
    private javax.swing.JSpinner jSpinnerTrial;
    public javax.swing.JTable jTableTrialConditions;
    private javax.swing.JLabel lblTrial;
    private javax.swing.JPanel pnlFilter;
    private javax.swing.JScrollPane scrlTable;
    // End of variables declaration//GEN-END:variables
}
