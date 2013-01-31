
package ibfb.studyeditor.exportwizard;

import java.util.ResourceBundle;
import javax.swing.JPanel;
import org.openide.util.NbBundle;

public final class exportVisualPanel2 extends JPanel {
    private ResourceBundle bundle = NbBundle.getBundle(exportVisualPanel2.class);

   private String name= bundle.getString("exportVisualPanel2.title");//"Data";
    
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
        lblTo = new javax.swing.JLabel();
        jSpinnerTrialEnd = new javax.swing.JSpinner();
        jRadioButtonFrom = new javax.swing.JRadioButton();
        lblImgDisk = new javax.swing.JLabel();

        buttonGroupTrials.add(jRadioButtonSelected);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonSelected, org.openide.util.NbBundle.getMessage(exportVisualPanel2.class, "exportVisualPanel2.jRadioButtonSelected.text")); // NOI18N

        buttonGroupTrials.add(jRadioButtonAll);
        jRadioButtonAll.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonAll, org.openide.util.NbBundle.getMessage(exportVisualPanel2.class, "exportVisualPanel2.jRadioButtonAll.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblTo, org.openide.util.NbBundle.getMessage(exportVisualPanel2.class, "exportVisualPanel2.lblTo.text")); // NOI18N

        buttonGroupTrials.add(jRadioButtonFrom);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonFrom, org.openide.util.NbBundle.getMessage(exportVisualPanel2.class, "exportVisualPanel2.jRadioButtonFrom.text")); // NOI18N

        lblImgDisk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/saveOptions.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblImgDisk, org.openide.util.NbBundle.getMessage(exportVisualPanel2.class, "exportVisualPanel2.lblImgDisk.text")); // NOI18N

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
                        .addComponent(jSpinnerTrialBegin, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTo)
                        .addGap(10, 10, 10)
                        .addComponent(jSpinnerTrialEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButtonAll)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButtonSelected)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinnerTrial, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                        .addComponent(lblImgDisk)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jRadioButtonAll)
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinnerTrial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButtonSelected)))
                    .addComponent(lblImgDisk))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinnerTrialEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTo)
                    .addComponent(jRadioButtonFrom)
                    .addComponent(jSpinnerTrialBegin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jRadioButtonSelected, jSpinnerTrial});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jSpinnerTrialBegin, lblTo});

    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupTrials;
    public javax.swing.JRadioButton jRadioButtonAll;
    public javax.swing.JRadioButton jRadioButtonFrom;
    public javax.swing.JRadioButton jRadioButtonSelected;
    public javax.swing.JSpinner jSpinnerTrial;
    public javax.swing.JSpinner jSpinnerTrialBegin;
    public javax.swing.JSpinner jSpinnerTrialEnd;
    private javax.swing.JLabel lblImgDisk;
    private javax.swing.JLabel lblTo;
    // End of variables declaration//GEN-END:variables
}
