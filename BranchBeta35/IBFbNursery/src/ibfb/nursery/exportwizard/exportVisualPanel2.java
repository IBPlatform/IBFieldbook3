
package ibfb.nursery.exportwizard;

import javax.swing.JPanel;
import org.openide.util.NbBundle;

public final class exportVisualPanel2 extends JPanel {

   private String name=NbBundle.getMessage(exportVisualPanel2.class,"exportVisualPanel2.name");
    
    public exportVisualPanel2() {
        initComponents();
    }

    @Override
    public String getName() {
        return name;
    }
    
    @Override
       public void setName(String nombre) {
        this.name=nombre;
    } 


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupTrials = new javax.swing.ButtonGroup();
        jRadioButtonSelected = new javax.swing.JRadioButton();
        jSpinnerTrial = new javax.swing.JSpinner();
        jRadioButtonAll = new javax.swing.JRadioButton();
        jSpinnerTrialBegin = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jSpinnerTrialEnd = new javax.swing.JSpinner();
        jRadioButtonFrom = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();

        buttonGroupTrials.add(jRadioButtonSelected);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonSelected, org.openide.util.NbBundle.getMessage(exportVisualPanel2.class, "exportVisualPanel2.jRadioButtonSelected.text_1")); // NOI18N

        buttonGroupTrials.add(jRadioButtonAll);
        jRadioButtonAll.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonAll, org.openide.util.NbBundle.getMessage(exportVisualPanel2.class, "exportVisualPanel2.jRadioButtonAll.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(exportVisualPanel2.class, "exportVisualPanel2.jLabel1.text_1")); // NOI18N

        buttonGroupTrials.add(jRadioButtonFrom);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonFrom, org.openide.util.NbBundle.getMessage(exportVisualPanel2.class, "exportVisualPanel2.jRadioButtonFrom.text_1")); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/saveOptions.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(exportVisualPanel2.class, "exportVisualPanel2.jLabel2.text_1")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButtonFrom)
                        .addGap(3, 3, 3)
                        .addComponent(jSpinnerTrialBegin, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jRadioButtonAll)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButtonSelected)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerTrial, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(10, 10, 10)
                        .addComponent(jSpinnerTrialEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButtonAll)
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinnerTrial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButtonSelected))
                        .addGap(73, 73, 73))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinnerTrialEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jRadioButtonFrom)
                    .addComponent(jSpinnerTrialBegin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jRadioButtonSelected, jSpinnerTrial});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jSpinnerTrialBegin});

    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupTrials;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JRadioButton jRadioButtonAll;
    public javax.swing.JRadioButton jRadioButtonFrom;
    public javax.swing.JRadioButton jRadioButtonSelected;
    public javax.swing.JSpinner jSpinnerTrial;
    public javax.swing.JSpinner jSpinnerTrialBegin;
    public javax.swing.JSpinner jSpinnerTrialEnd;
    // End of variables declaration//GEN-END:variables
}
