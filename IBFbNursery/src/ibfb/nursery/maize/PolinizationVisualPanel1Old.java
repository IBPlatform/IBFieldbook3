package ibfb.nursery.maize;

import java.util.ResourceBundle;
import javax.swing.JPanel;
import org.openide.util.NbBundle;

public final class PolinizationVisualPanel1Old extends JPanel {

    private ResourceBundle bundle = NbBundle.getBundle(PolinizationVisualPanel1Old.class);

    public PolinizationVisualPanel1Old() {
        initComponents();
    }

    @Override
    public String getName() {
        return bundle.getString("PolinizationVisualPanel1.name");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupPolinization = new javax.swing.ButtonGroup();
        buttonGroupDelimitador = new javax.swing.ButtonGroup();
        buttonGroupProbador = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jRadioButtonIndividual = new javax.swing.JRadioButton();
        jRadioButtonBulked = new javax.swing.JRadioButton();
        jRadioButtonSib = new javax.swing.JRadioButton();
        jRadioButtonColchi = new javax.swing.JRadioButton();
        jCheckBoxParentesis = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jRadioButtonCruzasPares = new javax.swing.JRadioButton();
        jRadioButtonCruzasImpares = new javax.swing.JRadioButton();
        jRadioButtonProbador = new javax.swing.JRadioButton();
        jRadioButtonHaploide = new javax.swing.JRadioButton();
        jCheckBoxReciprocos = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jRadioButtonDiagonal = new javax.swing.JRadioButton();
        jRadioButtonDobleDiagonal = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jRadioButtonDesconocido = new javax.swing.JRadioButton();
        jRadioButtonConocido = new javax.swing.JRadioButton();
        jTextFieldPedigree = new javax.swing.JTextField();
        jComboBoxPlot = new javax.swing.JComboBox();
        jComboBoxNurseryID = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jRadioButtonRecombinacion = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldStockName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDescription = new javax.swing.JTextArea();
        jButtonNextStockName = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jPanel2.border.title"))); // NOI18N

        buttonGroupPolinization.add(jRadioButtonIndividual);
        jRadioButtonIndividual.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonIndividual, org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jRadioButtonIndividual.text")); // NOI18N
        jRadioButtonIndividual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonIndividualActionPerformed(evt);
            }
        });

        buttonGroupPolinization.add(jRadioButtonBulked);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonBulked, org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jRadioButtonBulked.text")); // NOI18N

        buttonGroupPolinization.add(jRadioButtonSib);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonSib, org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jRadioButtonSib.text")); // NOI18N

        buttonGroupPolinization.add(jRadioButtonColchi);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonColchi, org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jRadioButtonColchi.text")); // NOI18N
        jRadioButtonColchi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonColchiActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxParentesis, org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jCheckBoxParentesis.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButtonIndividual)
                            .addComponent(jRadioButtonBulked)
                            .addComponent(jRadioButtonSib))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jRadioButtonColchi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBoxParentesis))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jRadioButtonIndividual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonBulked)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonSib)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonColchi)
                    .addComponent(jCheckBoxParentesis)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jPanel3.border.title"))); // NOI18N

        buttonGroupPolinization.add(jRadioButtonCruzasPares);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonCruzasPares, org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jRadioButtonCruzasPares.text")); // NOI18N

        buttonGroupPolinization.add(jRadioButtonCruzasImpares);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonCruzasImpares, org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jRadioButtonCruzasImpares.text")); // NOI18N

        buttonGroupPolinization.add(jRadioButtonProbador);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonProbador, org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jRadioButtonProbador.text")); // NOI18N

        buttonGroupPolinization.add(jRadioButtonHaploide);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonHaploide, org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jRadioButtonHaploide.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxReciprocos, org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jCheckBoxReciprocos.text")); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jPanel4.border.title"))); // NOI18N

        buttonGroupDelimitador.add(jRadioButtonDiagonal);
        jRadioButtonDiagonal.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonDiagonal, org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jRadioButtonDiagonal.text")); // NOI18N

        buttonGroupDelimitador.add(jRadioButtonDobleDiagonal);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonDobleDiagonal, org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jRadioButtonDobleDiagonal.text")); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonDiagonal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jRadioButtonDobleDiagonal)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonDiagonal)
                    .addComponent(jRadioButtonDobleDiagonal))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jPanel5.border.title"))); // NOI18N

        buttonGroupProbador.add(jRadioButtonDesconocido);
        jRadioButtonDesconocido.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonDesconocido, org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jRadioButtonDesconocido.text")); // NOI18N
        jRadioButtonDesconocido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonDesconocidoActionPerformed(evt);
            }
        });

        buttonGroupProbador.add(jRadioButtonConocido);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonConocido, org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jRadioButtonConocido.text")); // NOI18N

        jTextFieldPedigree.setText(org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jTextFieldPedigree.text")); // NOI18N

        jComboBoxPlot.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxNurseryID.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jLabel2.text")); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonConocido)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextFieldPedigree)
                        .addComponent(jRadioButtonDesconocido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxNurseryID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxPlot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jRadioButtonDesconocido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldPedigree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonConocido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxPlot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxNurseryID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButtonCruzasPares)
                            .addComponent(jRadioButtonCruzasImpares)
                            .addComponent(jRadioButtonProbador))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxReciprocos, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 10, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jRadioButtonHaploide)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonCruzasPares)
                    .addComponent(jCheckBoxReciprocos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jRadioButtonCruzasImpares)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonProbador))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonHaploide)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jPanel1.border.title"))); // NOI18N

        buttonGroupPolinization.add(jRadioButtonRecombinacion);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonRecombinacion, org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jRadioButtonRecombinacion.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonRecombinacion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jRadioButtonRecombinacion)
                .addGap(0, 87, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jPanel6.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jLabel3.text")); // NOI18N

        jTextFieldStockName.setText(org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jTextFieldStockName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jLabel4.text")); // NOI18N

        jTextAreaDescription.setColumns(20);
        jTextAreaDescription.setRows(5);
        jScrollPane1.setViewportView(jTextAreaDescription);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonNextStockName, org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1Old.class, "PolinizationVisualPanel1Old.jButtonNextStockName.text")); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel3))
                            .addComponent(jButtonNextStockName)
                            .addComponent(jTextFieldStockName))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldStockName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonNextStockName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public int getMethodIndex() {

        int resp = 0;
        if (jRadioButtonIndividual.isSelected()) {
            return 0;
        }
        if (jRadioButtonBulked.isSelected()) {
            return 1;
        }
        if (jRadioButtonSib.isSelected()) {
            return 2;
        }
        if (jRadioButtonColchi.isSelected()) {

            if (jCheckBoxParentesis.isSelected()) {
            }
            return 3;
        }

        return resp;
    }

    public int getMethodID() {

        int resp = 0;

        if (jRadioButtonIndividual.isSelected()) {
            return 205;
        }
        if (jRadioButtonBulked.isSelected()) {
            return 206;
        }
        if (jRadioButtonSib.isSelected()) {
            return 509;
        }
        if (jRadioButtonColchi.isSelected()) {
            return 202;
        }

        return resp;
    }

    public String getMaizeTooltip() {

        String resp = "";
        if (jRadioButtonIndividual.isSelected()) {
            return "Autofecundadas y mazorcas desgranadas individuamente (-)";
        }
        if (jRadioButtonBulked.isSelected()) {
            return "Autofecundadas y mazorcas bulked (-B)";
        }
        if (jRadioButtonSib.isSelected()) {
            return "Sib-Increased (-#)";
        }
        if (jRadioButtonColchi.isSelected()) {
            return "Colchicinize";
        }

        return resp;
    }

    public String getMethodName() {
        String resp = "";
        if (jRadioButtonIndividual.isSelected()) {
            return "Autofecundadas individuales (-)";
        }
        if (jRadioButtonBulked.isSelected()) {
            return "Autofecundadas  bulked (-B)";
        }
        if (jRadioButtonSib.isSelected()) {
            return "Sib-Increased (-#)";
        }
        if (jRadioButtonColchi.isSelected()) {
            return "Colchicinize";
        }

        return resp;
    }

    public String getMethodNameForMaize() {
        String resp = "";
        if (jRadioButtonIndividual.isSelected()) {
            return "Single plant selection SF";
        }
        if (jRadioButtonBulked.isSelected()) {
            return "Selected bulk SF";
        }
        if (jRadioButtonSib.isSelected()) {
            return "Half mass selection";
        }
        if (jRadioButtonColchi.isSelected()) {
            return "Double Haploid Line";
        }

        return resp;
    }

    public int getParentheses() {//0=false,  1=true
        if (this.jCheckBoxParentesis.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }

    private void jRadioButtonDesconocidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonDesconocidoActionPerformed
    }//GEN-LAST:event_jRadioButtonDesconocidoActionPerformed

    private void jRadioButtonColchiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonColchiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonColchiActionPerformed

    private void jRadioButtonIndividualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonIndividualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonIndividualActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupDelimitador;
    private javax.swing.ButtonGroup buttonGroupPolinization;
    private javax.swing.ButtonGroup buttonGroupProbador;
    private javax.swing.JButton jButtonNextStockName;
    private javax.swing.JCheckBox jCheckBoxParentesis;
    private javax.swing.JCheckBox jCheckBoxReciprocos;
    private javax.swing.JComboBox jComboBoxNurseryID;
    private javax.swing.JComboBox jComboBoxPlot;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButtonBulked;
    private javax.swing.JRadioButton jRadioButtonColchi;
    private javax.swing.JRadioButton jRadioButtonConocido;
    private javax.swing.JRadioButton jRadioButtonCruzasImpares;
    private javax.swing.JRadioButton jRadioButtonCruzasPares;
    private javax.swing.JRadioButton jRadioButtonDesconocido;
    private javax.swing.JRadioButton jRadioButtonDiagonal;
    private javax.swing.JRadioButton jRadioButtonDobleDiagonal;
    private javax.swing.JRadioButton jRadioButtonHaploide;
    private javax.swing.JRadioButton jRadioButtonIndividual;
    private javax.swing.JRadioButton jRadioButtonProbador;
    private javax.swing.JRadioButton jRadioButtonRecombinacion;
    private javax.swing.JRadioButton jRadioButtonSib;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaDescription;
    private javax.swing.JTextField jTextFieldPedigree;
    private javax.swing.JTextField jTextFieldStockName;
    // End of variables declaration//GEN-END:variables
}
