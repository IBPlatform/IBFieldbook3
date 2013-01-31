package ibfb.nursery.maize;

import java.util.ResourceBundle;
import javax.swing.JPanel;
import org.openide.util.NbBundle;

public final class PolinizationVisualPanel1 extends JPanel {

    private ResourceBundle bundle = NbBundle.getBundle(PolinizationVisualPanel1.class);

    public PolinizationVisualPanel1() {
        initComponents();
        changeLabels();
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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1.class, "PolinizationVisualPanel1.jPanel2.border.title"))); // NOI18N

        buttonGroupPolinization.add(jRadioButtonIndividual);
        jRadioButtonIndividual.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonIndividual, org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1.class, "PolinizationVisualPanel1.jRadioButtonIndividual.text")); // NOI18N
        jRadioButtonIndividual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonIndividualActionPerformed(evt);
            }
        });

        buttonGroupPolinization.add(jRadioButtonBulked);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonBulked, org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1.class, "PolinizationVisualPanel1.jRadioButtonBulked.text")); // NOI18N

        buttonGroupPolinization.add(jRadioButtonSib);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonSib, org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1.class, "PolinizationVisualPanel1.jRadioButtonSib.text")); // NOI18N

        buttonGroupPolinization.add(jRadioButtonColchi);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonColchi, org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1.class, "PolinizationVisualPanel1.jRadioButtonColchi.text")); // NOI18N
        jRadioButtonColchi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonColchiActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxParentesis, org.openide.util.NbBundle.getMessage(PolinizationVisualPanel1.class, "PolinizationVisualPanel1.jCheckBoxParentesis.text")); // NOI18N

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
                        .addComponent(jCheckBoxParentesis))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jRadioButtonIndividual)
                .addGap(40, 40, 40)
                .addComponent(jRadioButtonBulked)
                .addGap(40, 40, 40)
                .addComponent(jRadioButtonSib)
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonColchi)
                    .addComponent(jCheckBoxParentesis))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
    private javax.swing.JCheckBox jCheckBoxParentesis;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButtonBulked;
    private javax.swing.JRadioButton jRadioButtonColchi;
    private javax.swing.JRadioButton jRadioButtonIndividual;
    private javax.swing.JRadioButton jRadioButtonSib;
    // End of variables declaration//GEN-END:variables

    private void changeLabels() {
       
    }
}
