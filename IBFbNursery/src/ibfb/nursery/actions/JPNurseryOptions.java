
package ibfb.nursery.actions;

import java.util.ResourceBundle;
import org.openide.util.NbBundle;

public class JPNurseryOptions extends javax.swing.JPanel {

    private ResourceBundle bundle = NbBundle.getBundle(JPNurseryOptions.class);
    
    public JPNurseryOptions() {
        initComponents();
    }
    
    private int option=0; //0=wizard  1=quick

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jLabel1.setText(org.openide.util.NbBundle.getMessage(JPNurseryOptions.class, "JPNurseryOptions.jLabel1.text")); // NOI18N

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText(org.openide.util.NbBundle.getMessage(JPNurseryOptions.class, "JPNurseryOptions.jRadioButton1.text")); // NOI18N
        jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton1ItemStateChanged(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText(org.openide.util.NbBundle.getMessage(JPNurseryOptions.class, "JPNurseryOptions.jLabel2.text")); // NOI18N

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText(org.openide.util.NbBundle.getMessage(JPNurseryOptions.class, "JPNurseryOptions.jRadioButton2.text")); // NOI18N
        jRadioButton2.setActionCommand(org.openide.util.NbBundle.getMessage(JPNurseryOptions.class, "JPNurseryOptions.jRadioButton2.actionCommand")); // NOI18N
        jRadioButton2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton2ItemStateChanged(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText(org.openide.util.NbBundle.getMessage(JPNurseryOptions.class, "JPNurseryOptions.jLabel3.text")); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/quick.png"))); // NOI18N
        jLabel5.setText(org.openide.util.NbBundle.getMessage(JPNurseryOptions.class, "JPNurseryOptions.jLabel5.text")); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/wizard.png"))); // NOI18N
        jLabel4.setText(org.openide.util.NbBundle.getMessage(JPNurseryOptions.class, "JPNurseryOptions.jLabel4.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)
                            .addComponent(jRadioButton2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(65, 65, 65))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(31, 31, 31)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel4)))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton1ItemStateChanged

        if (jRadioButton1.isSelected()) {             this.setOption(0);         }     }//GEN-LAST:event_jRadioButton1ItemStateChanged

    private void jRadioButton2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton2ItemStateChanged

        if (jRadioButton2.isSelected()) {             this.setOption(1);         }     }//GEN-LAST:event_jRadioButton2ItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    // End of variables declaration//GEN-END:variables
}
