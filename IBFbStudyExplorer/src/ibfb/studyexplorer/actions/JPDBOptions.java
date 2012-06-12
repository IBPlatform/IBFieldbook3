package ibfb.studyexplorer.actions;

public class JPDBOptions extends javax.swing.JPanel {

    private boolean wantTraits = false;
    private boolean wantGsm = false;
    private boolean wantMethods = false;
    private boolean wantScales = false;

    public JPDBOptions() {
        initComponents();
    }

    public boolean wantGsm() {
        return wantGsm;
    }

    public void setWantGsm(boolean wantGsm) {
        this.wantGsm = wantGsm;
    }

    public boolean wantMethods() {
        return wantMethods;
    }

    public void setWantMethods(boolean wantMethods) {
        this.wantMethods = wantMethods;
    }

    public boolean wantScales() {
        return wantScales;
    }

    public void setWantScales(boolean wantScales) {
        this.wantScales = wantScales;
    }

    public boolean wantTraits() {
        return wantTraits;
    }

    public void setWantTraits(boolean wantTraits) {
        this.wantTraits = wantTraits;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jCheckBoxTraits = new javax.swing.JCheckBox();
        jCheckBoxGermoplasm = new javax.swing.JCheckBox();
        jCheckBoxMethods = new javax.swing.JCheckBox();
        jCheckBoxScales = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyexplorer/images/traits.png"))); // NOI18N
        jLabel4.setText(org.openide.util.NbBundle.getMessage(JPDBOptions.class, "JPDBOptions.jLabel4.text")); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyexplorer/images/germplasm2.png"))); // NOI18N
        jLabel5.setText(org.openide.util.NbBundle.getMessage(JPDBOptions.class, "JPDBOptions.jLabel5.text")); // NOI18N

        jCheckBoxTraits.setText(org.openide.util.NbBundle.getMessage(JPDBOptions.class, "JPDBOptions.jCheckBoxTraits.text")); // NOI18N
        jCheckBoxTraits.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxTraitsItemStateChanged(evt);
            }
        });

        jCheckBoxGermoplasm.setText(org.openide.util.NbBundle.getMessage(JPDBOptions.class, "JPDBOptions.jCheckBoxGermoplasm.text")); // NOI18N
        jCheckBoxGermoplasm.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxGermoplasmItemStateChanged(evt);
            }
        });
        jCheckBoxGermoplasm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxGermoplasmActionPerformed(evt);
            }
        });

        jCheckBoxMethods.setText(org.openide.util.NbBundle.getMessage(JPDBOptions.class, "JPDBOptions.jCheckBoxMethods.text")); // NOI18N
        jCheckBoxMethods.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxMethodsItemStateChanged(evt);
            }
        });

        jCheckBoxScales.setText(org.openide.util.NbBundle.getMessage(JPDBOptions.class, "JPDBOptions.jCheckBoxScales.text")); // NOI18N
        jCheckBoxScales.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxScalesItemStateChanged(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyexplorer/images/scales.png"))); // NOI18N
        jLabel6.setText(org.openide.util.NbBundle.getMessage(JPDBOptions.class, "JPDBOptions.jLabel6.text")); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyexplorer/images/methods.png"))); // NOI18N
        jLabel7.setText(org.openide.util.NbBundle.getMessage(JPDBOptions.class, "JPDBOptions.jLabel7.text")); // NOI18N

        jLabel1.setText(org.openide.util.NbBundle.getMessage(JPDBOptions.class, "JPDBOptions.jLabel1.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBoxGermoplasm)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addGap(93, 93, 93)
                                .addComponent(jCheckBoxScales)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBoxTraits)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                                .addComponent(jCheckBoxMethods)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)))
                        .addGap(48, 48, 48))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jCheckBoxMethods))
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jCheckBoxTraits)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jCheckBoxScales))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jCheckBoxGermoplasm)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxGermoplasmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxGermoplasmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxGermoplasmActionPerformed

    private void jCheckBoxTraitsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxTraitsItemStateChanged
        if (jCheckBoxTraits.isSelected()) {
            this.setWantTraits(true);
        } else {
            this.setWantTraits(false);
        }
    }//GEN-LAST:event_jCheckBoxTraitsItemStateChanged

    private void jCheckBoxGermoplasmItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxGermoplasmItemStateChanged
        if (jCheckBoxGermoplasm.isSelected()) {
            this.setWantGsm(true);
        } else {
            this.setWantGsm(false);
        }
    }//GEN-LAST:event_jCheckBoxGermoplasmItemStateChanged

    private void jCheckBoxMethodsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxMethodsItemStateChanged
        if (jCheckBoxMethods.isSelected()) {
            this.setWantMethods(true);
        } else {
            this.setWantMethods(false);
        }
    }//GEN-LAST:event_jCheckBoxMethodsItemStateChanged

    private void jCheckBoxScalesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxScalesItemStateChanged
        if (jCheckBoxScales.isSelected()) {
            this.setWantScales(true);
        } else {
            this.setWantScales(false);
        }
    }//GEN-LAST:event_jCheckBoxScalesItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox jCheckBoxGermoplasm;
    private javax.swing.JCheckBox jCheckBoxMethods;
    private javax.swing.JCheckBox jCheckBoxScales;
    private javax.swing.JCheckBox jCheckBoxTraits;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
