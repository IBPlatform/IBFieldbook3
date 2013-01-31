
package ibfb.studyeditor.exportformaize;

import java.util.ResourceBundle;
import javax.swing.JPanel;
import org.openide.util.NbBundle;

public final class maizeExportVisualPanel2 extends JPanel {
 private ResourceBundle bundle = NbBundle.getBundle(maizeExportVisualPanel2.class);

   private String name= bundle.getString("maizeExportVisualPanel2.name");
   
    
    public maizeExportVisualPanel2() {
        initComponents();
    }

    @Override
    public String getName() {
        return name;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jRadioButtonSelected = new javax.swing.JRadioButton();
        jSpinnerTrial = new javax.swing.JSpinner();
        jRadioButtonAll = new javax.swing.JRadioButton();
        jSpinnerTrialBegin = new javax.swing.JSpinner();
        lblTo = new javax.swing.JLabel();
        jSpinnerTrialEnd = new javax.swing.JSpinner();
        jRadioButtonFrom = new javax.swing.JRadioButton();
        lblImgDisk = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonSelected, org.openide.util.NbBundle.getMessage(maizeExportVisualPanel2.class, "maizeExportVisualPanel2.jRadioButtonSelected.text")); // NOI18N

        jRadioButtonAll.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonAll, org.openide.util.NbBundle.getMessage(maizeExportVisualPanel2.class, "maizeExportVisualPanel2.jRadioButtonAll.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblTo, org.openide.util.NbBundle.getMessage(maizeExportVisualPanel2.class, "maizeExportVisualPanel2.lblTo.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonFrom, org.openide.util.NbBundle.getMessage(maizeExportVisualPanel2.class, "maizeExportVisualPanel2.jRadioButtonFrom.text")); // NOI18N

        lblImgDisk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyeditor/images/saveOptions.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblImgDisk, org.openide.util.NbBundle.getMessage(maizeExportVisualPanel2.class, "maizeExportVisualPanel2.lblImgDisk.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButtonFrom)
                        .addGap(3, 3, 3)
                        .addComponent(jSpinnerTrialBegin, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTo)
                        .addGap(10, 10, 10)
                        .addComponent(jSpinnerTrialEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButtonAll)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jRadioButtonSelected)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinnerTrial, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                        .addComponent(lblImgDisk)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jRadioButtonAll)
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinnerTrial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButtonSelected)))
                    .addComponent(lblImgDisk))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinnerTrialEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTo)
                    .addComponent(jRadioButtonFrom)
                    .addComponent(jSpinnerTrialBegin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 485, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 10, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 11, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
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
