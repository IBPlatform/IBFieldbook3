package ibfb.nursery.exportwizard;

import ibfb.domain.core.Variate;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.openide.util.NbBundle;

public final class exportVisualPanelGYTrait extends JPanel {

    private ResourceBundle bundle = NbBundle.getBundle(exportVisualPanelGYTrait.class);
    Variate elTrait = null;

    public JLabel getjLabelSelected() {
        return jLabelSelected;
    }

    public void setjLabelSelected(JLabel jLabelSelected) {
        this.jLabelSelected = jLabelSelected;
    }

    public Variate getElTrait() {
        return elTrait;
    }

    
    public int getSelectedTrait(){
        return this.jList1.getSelectedIndex();
        
    }
    public void setElTrait(Variate elTrait) {
        this.elTrait = elTrait;
    }

    public exportVisualPanelGYTrait() {
        initComponents();
        jLabelSelected.setText("");


    }

    @Override
    public String getName() {
        return bundle.getString("exportVisualPanelGTTrait.title");
    }

    public void fillTraitList(List<Variate> traits) {
        DefaultListModel listModel = new DefaultListModel();
        for (int i = 0; i < traits.size(); i++) {
            listModel.addElement(traits.get(i));            
        }

        this.jList1.setModel(listModel);
        if (listModel.size() > 0) {
            this.jList1.getSelectionModel().setLeadSelectionIndex(0);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jLabelSelected = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(exportVisualPanelGYTrait.class, "exportVisualPanelGYTrait.jLabel1.text")); // NOI18N

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(exportVisualPanelGYTrait.class, "exportVisualPanelGYTrait.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelSelected, org.openide.util.NbBundle.getMessage(exportVisualPanelGYTrait.class, "exportVisualPanelGYTrait.jLabelSelected.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(exportVisualPanelGYTrait.class, "exportVisualPanelGYTrait.jLabel2.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabelSelected))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        try{
        elTrait = (Variate) this.jList1.getSelectedValue();
        jLabelSelected.setText(elTrait.getVariateName());
        exportWizardIterator.indexTrait=this.jList1.getSelectedIndex();
        }catch(Exception ex){
            
             exportWizardIterator.indexTrait=-1;
        }
    }//GEN-LAST:event_jList1ValueChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelSelected;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    void setTraitInList(int indexTrait) {
        if(indexTrait>=0){
            this.jList1.setSelectedIndex(indexTrait);
        }else{
             this.jList1.getSelectionModel().clearSelection();
        }
     
    }
}
