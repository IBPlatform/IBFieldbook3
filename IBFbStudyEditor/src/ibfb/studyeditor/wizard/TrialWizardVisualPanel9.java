package ibfb.studyeditor.wizard;

import ibfb.domain.core.DesignBean;
import ibfb.studyeditor.core.StudyEditorTopComponent;
import ibfb.studyeditor.core.model.DesignTableModel;
import ibfb.studyeditor.core.model.JTableUtils;
import ibfb.studyeditor.designs.DesignsUtils;
import ibfb.studyeditor.roweditors.ColumnFitAdapter;
import java.util.ResourceBundle;
import java.util.regex.PatternSyntaxException;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpinnerModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.openide.util.NbBundle;

public final class TrialWizardVisualPanel9 extends JPanel {

    private ResourceBundle bundle = NbBundle.getBundle(TrialWizardVisualPanel9.class);
    StudyEditorTopComponent studyWindow = TrialWizardWizardIterator.studyTopComponent;
    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
    TableRowSorter<TableModel> sorter;
    DesignTableModel model = new DesignTableModel();
    private int fila = 0;
    private DesignsUtils designsUtils;
    private TrialWizardWizardPanel9 trialWizardWizardPanel9;

    public TrialWizardVisualPanel9(TrialWizardWizardPanel9 trialWizardWizardPanel9) {
        initComponents();
        model = (DesignTableModel) this.jTableDesign.getModel();
        sorter = new TableRowSorter<TableModel>(model);
        this.jTableDesign.setRowSorter(sorter);
        deshabilitaSorters();
        this.jTableDesign.getTableHeader().addMouseListener(new ColumnFitAdapter());
        designsUtils = new DesignsUtils(jTableDesign, jTextFieldEntries);
        designsUtils.setGermplasmEntries(0);
        this.trialWizardWizardPanel9 = trialWizardWizardPanel9;
        jSpinnerTrial.setVisible(false);
        jRadioButtonFilter4.setVisible(false);
        jRadioButtonViewAll.setVisible(false);
        jLabel5.setVisible(false);
    }

    @Override
    public String getName() {
        return bundle.getString("TrialWizardVisualPanel9.title");
    }

    public JTextField getjTextFieldEntries() {
        return jTextFieldEntries;
    }

    public void setjTextFieldEntries(JTextField jTextFieldEntries) {
        this.jTextFieldEntries = jTextFieldEntries;
    }

    public JTable getjTableDesign() {
        return jTableDesign;
    }

    public void setjTableDesign(JTable jTableDesign) {
        this.jTableDesign = jTableDesign;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupDesign = new javax.swing.ButtonGroup();
        grpBtnDesign = new javax.swing.ButtonGroup();
        scrllTable = new javax.swing.JScrollPane();
        jTableDesign = new javax.swing.JTable();
        pnlFilter = new javax.swing.JPanel();
        jRadioButtonFilter4 = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jSpinnerTrial = new javax.swing.JSpinner();
        jRadioButtonViewAll = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelEntries = new javax.swing.JLabel();
        jTextFieldEntries = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        rbtnIndividualDesign = new javax.swing.JRadioButton();
        rbtnUseSameDesign = new javax.swing.JRadioButton();

        jTableDesign.setModel(new DesignTableModel());
        jTableDesign.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableDesignPropertyChange(evt);
            }
        });
        scrllTable.setViewportView(jTableDesign);

        pnlFilter.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroupDesign.add(jRadioButtonFilter4);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonFilter4, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel9.class, "TrialWizardVisualPanel9.jRadioButtonFilter4.text")); // NOI18N
        jRadioButtonFilter4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonFilter4ItemStateChanged(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel9.class, "TrialWizardVisualPanel9.jLabel5.text")); // NOI18N

        jSpinnerTrial.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerTrialStateChanged(evt);
            }
        });

        buttonGroupDesign.add(jRadioButtonViewAll);
        jRadioButtonViewAll.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonViewAll, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel9.class, "TrialWizardVisualPanel9.jRadioButtonViewAll.text")); // NOI18N
        jRadioButtonViewAll.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonViewAllItemStateChanged(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/design.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel9.class, "TrialWizardVisualPanel9.jLabel1.text")); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelEntries, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel9.class, "TrialWizardVisualPanel9.jLabelEntries.text")); // NOI18N

        jTextFieldEntries.setEditable(false);
        jTextFieldEntries.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jTextFieldEntries.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldEntries.setText(org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel9.class, "TrialWizardVisualPanel9.jTextFieldEntries.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelEntries)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldEntries, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldEntries)
                    .addComponent(jLabelEntries))
                .addGap(15, 15, 15))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel9.class, "TrialWizardVisualPanel9.jPanel1.border.title"))); // NOI18N

        grpBtnDesign.add(rbtnIndividualDesign);
        org.openide.awt.Mnemonics.setLocalizedText(rbtnIndividualDesign, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel9.class, "TrialWizardVisualPanel9.rbtnIndividualDesign.text")); // NOI18N
        rbtnIndividualDesign.setToolTipText(org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel9.class, "TrialWizardVisualPanel9.rbtnIndividualDesign.toolTipText")); // NOI18N

        grpBtnDesign.add(rbtnUseSameDesign);
        rbtnUseSameDesign.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(rbtnUseSameDesign, org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel9.class, "TrialWizardVisualPanel9.rbtnUseSameDesign.text")); // NOI18N
        rbtnUseSameDesign.setToolTipText(org.openide.util.NbBundle.getMessage(TrialWizardVisualPanel9.class, "TrialWizardVisualPanel9.rbtnUseSameDesign.toolTipText")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(rbtnIndividualDesign, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbtnUseSameDesign, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(rbtnUseSameDesign)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtnIndividualDesign)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlFilterLayout = new javax.swing.GroupLayout(pnlFilter);
        pnlFilter.setLayout(pnlFilterLayout);
        pnlFilterLayout.setHorizontalGroup(
            pnlFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFilterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFilterLayout.createSequentialGroup()
                        .addComponent(jRadioButtonFilter4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerTrial, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jRadioButtonViewAll))
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(49, 49, 49)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlFilterLayout.setVerticalGroup(
            pnlFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFilterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addGroup(pnlFilterLayout.createSequentialGroup()
                        .addGroup(pnlFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSpinnerTrial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jRadioButtonFilter4)
                                .addComponent(jLabel5)))
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButtonViewAll))
                    .addGroup(pnlFilterLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlFilterLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel5, jSpinnerTrial});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrllTable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)
                    .addComponent(pnlFilter, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(scrllTable, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTableDesignPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableDesignPropertyChange


        if (this.jTableDesign.getCellEditor() != null) {
            jTableDesign.getCellEditor().stopCellEditing();
        }

        int colEditing = this.jTableDesign.getEditingColumn();

        this.fila = this.jTableDesign.getEditingRow();

        if (fila == -1) {
            return;
        }

        try {
            if (evt.getOldValue() == null) {
                return;
            }


            if (evt.getNewValue() == null) {
                designsUtils.checkDesignTable(colEditing, fila, rbtnUseSameDesign.isSelected());
            }


        } catch (Exception error) {
            error.printStackTrace();
        }

        JTableUtils.ajustaColumnsTable(jTableDesign, 2);
        JTableUtils.ajustaColumnsTable(jTableDesign, 5);
        trialWizardWizardPanel9.change();
    }//GEN-LAST:event_jTableDesignPropertyChange

    private void checkTable() {
        if (this.jTableDesign.getCellEditor() != null) {
            jTableDesign.getCellEditor().stopCellEditing();
        }

        int colEditing = 1;
        this.fila = 0;

        try {
            designsUtils.checkDesignTable(colEditing, fila, rbtnUseSameDesign.isSelected());
        } catch (Exception error) {
            System.out.println("ERROR EN TRIAL VISUAL PANEL 9: " + error);
            error.printStackTrace();
        }


    }

    private void jRadioButtonFilter4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonFilter4ItemStateChanged
        if (this.jRadioButtonFilter4.isSelected()) {
            int num = (Integer) this.jSpinnerTrial.getValue();
            try {
                sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, num, 0));
            } catch (PatternSyntaxException pse) {
                System.err.println("Bad regex pattern");
            }
        }
}//GEN-LAST:event_jRadioButtonFilter4ItemStateChanged

    private void jSpinnerTrialStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerTrialStateChanged
        if (this.jRadioButtonFilter4.isSelected()) {
            int num = (Integer) this.jSpinnerTrial.getValue();
            try {
                sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, num, 0));
            } catch (PatternSyntaxException pse) {
                System.err.println("Bad regex pattern");
            }

        }
}//GEN-LAST:event_jSpinnerTrialStateChanged

    private void jRadioButtonViewAllItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonViewAllItemStateChanged
        if (this.jRadioButtonViewAll.isSelected()) {
            sorter.setRowFilter(null);
        }
}//GEN-LAST:event_jRadioButtonViewAllItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupDesign;
    private javax.swing.ButtonGroup grpBtnDesign;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabelEntries;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButtonFilter4;
    private javax.swing.JRadioButton jRadioButtonViewAll;
    public javax.swing.JSpinner jSpinnerTrial;
    private javax.swing.JTable jTableDesign;
    public javax.swing.JTextField jTextFieldEntries;
    private javax.swing.JPanel pnlFilter;
    private javax.swing.JRadioButton rbtnIndividualDesign;
    private javax.swing.JRadioButton rbtnUseSameDesign;
    private javax.swing.JScrollPane scrllTable;
    // End of variables declaration//GEN-END:variables

    public JSpinner getjSpinnerTrial() {
        return jSpinnerTrial;
    }

    public void setjSpinnerTrial(JSpinner jSpinnerTrial) {
        this.jSpinnerTrial = jSpinnerTrial;
    }

    public void setOtherFactors(String factors) {
    }

    public void fillData(Integer instances, boolean hayFactores) {

        DesignTableModel modeloTabla = new DesignTableModel();
        jTableDesign.setModel(modeloTabla);
        modeloTabla = (DesignTableModel) this.jTableDesign.getModel();
        jTableDesign.setRowSorter(null);


        Integer renglon = 0;
        for (int j = 0; j < instances; j++) {
            DesignBean designBean = new DesignBean();
            designBean.setTrialNumber(j + 1);
            modeloTabla.addDesignBean(designBean);
            renglon++;
        }
        tcr.setHorizontalAlignment(SwingConstants.CENTER);

        if (this.jTableDesign.getRowCount() > 0) {
            this.jTableDesign.setRowSelectionInterval(0, 0);


        }

        int square = (int) Math.sqrt(Integer.parseInt(this.jTextFieldEntries.getText()));
        boolean conLattice = false;
        boolean conAlpha = false;
        designsUtils.setGermplasmEntries(Integer.parseInt(this.jTextFieldEntries.getText()));


        if (!hayFactores) {
            conAlpha = true;
        }

        int numEntries = studyWindow.jTableEntries.getRowCount();
        if (org.cimmyt.cril.ibwb.commongui.MathUtils.isPrime(numEntries)) {
            conAlpha = false;
        }

        if (Math.pow(square, 2) == Integer.parseInt(this.jTextFieldEntries.getText())) {
            conLattice = true;
        }


        conAlpha = designsUtils.alphaIsValid(numEntries);

        String inicio = designsUtils.assignMainCellEditor(conAlpha, conLattice);

        for (int j = 0; j < instances; j++) {
            this.jTableDesign.setValueAt(inicio, j, 1);
            this.jTableDesign.setValueAt(null, j, 2);
            this.jTableDesign.setValueAt(null, j, 3);
            this.jTableDesign.setValueAt(null, j, 4);
            this.jTableDesign.setValueAt(null, j, 5);
        }


        SpinnerModel modeloDesign = jSpinnerTrial.getModel();
        studyWindow.jSpinnerTrialStudy.setModel(modeloDesign);

        if (conAlpha) {
            int selected = designsUtils.assignCellEditorAlpha(numEntries);
            designsUtils.generateBlocksSize(selected);
            designsUtils.assignCellEditorBlockSize();
            trialWizardWizardPanel9.change();
        } else {
            checkTable();
            trialWizardWizardPanel9.change();
        }

        sorter = new TableRowSorter<TableModel>(modeloTabla);
        this.jTableDesign.setRowSorter(sorter);
        deshabilitaSorters();
    }

    /**
     * All designs are filled correctly?
     *
     * @return
     */
    public boolean allDesignsFilled() {
        boolean allDesignsFilled = true;
        DesignTableModel tableModel = (DesignTableModel) jTableDesign.getModel();
        for (DesignBean designBean : tableModel.getDesignList()) {
            if (!designBean.isValidDesign()) {
                allDesignsFilled = false;
                break;
            }
        }

        return allDesignsFilled;
    }

    private void deshabilitaSorters() {
        for (int i = 0; i < 6; i++) {
            sorter.setSortable(i, false);
        }
    }
}
