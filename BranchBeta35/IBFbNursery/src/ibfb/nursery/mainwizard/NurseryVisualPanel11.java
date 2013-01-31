
package ibfb.nursery.mainwizard;


import ibfb.nursery.core.NurseryEditorTopComponent;
import java.util.ResourceBundle;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.openide.util.NbBundle;


public final class NurseryVisualPanel11 extends JPanel {
    
    private ResourceBundle bundle = NbBundle.getBundle(NurseryVisualPanel11.class);

    NurseryEditorTopComponent nurseryWindow = NurseryWizardIterator.nurseryTopComponent;


    public NurseryVisualPanel11() {
        initComponents();
    }

    @Override
    public String getName() {
         return bundle.getString("NurseryVisualPanel11.name");
    }


    public JLabel getjLabelEntries() {
        return jLabelEntries;
    }

    public void setjLabelEntries(JLabel jLabelEntries) {
        this.jLabelEntries = jLabelEntries;
    }

    public JTextField getjTextFieldEntries() {
        return jTextFieldEntries;
    }

    public void setjTextFieldEntries(JTextField jTextFieldEntries) {
        this.jTextFieldEntries = jTextFieldEntries;
    }

    

   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabelEntries = new javax.swing.JLabel();
        jTextFieldEntries = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jRadioButtonNormal = new javax.swing.JRadioButton();
        jRadioButtonRandom = new javax.swing.JRadioButton();

        org.openide.awt.Mnemonics.setLocalizedText(jLabelEntries, org.openide.util.NbBundle.getMessage(NurseryVisualPanel11.class, "NurseryVisualPanel11.jLabelEntries.text")); // NOI18N

        jTextFieldEntries.setEditable(false);
        jTextFieldEntries.setFont(new java.awt.Font("Lucida Grande", 0, 12));
        jTextFieldEntries.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldEntries.setText(org.openide.util.NbBundle.getMessage(NurseryVisualPanel11.class, "NurseryVisualPanel11.jTextFieldEntries.text")); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/random.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(NurseryVisualPanel11.class, "NurseryVisualPanel11.jLabel1.text")); // NOI18N

        buttonGroup1.add(jRadioButtonNormal);
        jRadioButtonNormal.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonNormal, org.openide.util.NbBundle.getMessage(NurseryVisualPanel11.class, "NurseryVisualPanel11.jRadioButtonNormal.text")); // NOI18N
        jRadioButtonNormal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jRadioButtonNormalPropertyChange(evt);
            }
        });

        buttonGroup1.add(jRadioButtonRandom);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonRandom, org.openide.util.NbBundle.getMessage(NurseryVisualPanel11.class, "NurseryVisualPanel11.jRadioButtonRandom.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelEntries)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldEntries, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jRadioButtonNormal)
                    .addComponent(jRadioButtonRandom))
                .addGap(48, 48, 48))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEntries)
                    .addComponent(jTextFieldEntries))
                .addGap(59, 59, 59)
                .addComponent(jRadioButtonNormal)
                .addGap(42, 42, 42)
                .addComponent(jRadioButtonRandom)
                .addGap(78, 78, 78))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButtonNormalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jRadioButtonNormalPropertyChange
      if(this.jRadioButtonNormal.isSelected()){
          nurseryWindow.selectNormalDesign();
      }else{
          nurseryWindow.selectRandomDesign();
      }
    }//GEN-LAST:event_jRadioButtonNormalPropertyChange

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabelEntries;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButtonNormal;
    private javax.swing.JRadioButton jRadioButtonRandom;
    public javax.swing.JTextField jTextFieldEntries;
    // End of variables declaration//GEN-END:variables
}
