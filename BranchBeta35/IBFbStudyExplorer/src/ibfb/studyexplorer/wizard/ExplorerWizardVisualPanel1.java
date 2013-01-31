package ibfb.studyexplorer.wizard;

import ibfb.domain.core.Study;
import ibfb.studyexplorer.explorer.StudyExplorerTopComponent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.ConvertUtils;
import org.openide.util.NbBundle;
import org.openide.windows.WindowManager;

public final class ExplorerWizardVisualPanel1 extends JPanel {

    private List<Study> studyList;
    private List<Study> selectedStudyList;
    private String selected = "";
    private ResourceBundle bundle = NbBundle.getBundle(ExplorerWizardVisualPanel1.class);

    public ExplorerWizardVisualPanel1() {
        initComponents();
        configList();
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    @Override
    public String getName() {
        return bundle.getString("ExplorerWizardVisualPanel1.name");
    }

    public List<Study> getSelectedStudyList() {
        return selectedStudyList;
    }

    public void setSelectedStudyList(List<Study> selectedStudyList) {
        this.selectedStudyList = selectedStudyList;
    }

    public void fillStudies() {

        studyList = new ArrayList<Study>();

        DefaultListModel listModel = new DefaultListModel();
        List<org.cimmyt.cril.ibwb.domain.Study> studyDtoList = AppServicesProxy.getDefault().appServices().getStudyListByParent(0, Study.S_TYPE_EXPERIMENT);

        for (org.cimmyt.cril.ibwb.domain.Study studyDto : studyDtoList) {
            // if(!studyDto.getSname().startsWith("19")){
            studyList.add(castStudy(studyDto));
            listModel.addElement(new CheckListItem(studyDto.getSname()));
            // }
        }



        this.jList1.setModel(listModel);
    }

    public void fillSelection(String selected) {
        if (selected.isEmpty()) {
            selectALL();
            return;
        }

        DefaultListModel listModel = (DefaultListModel) this.jList1.getModel();

        if (selected.length() == listModel.getSize()) {


            for (int i = 0; i < listModel.getSize(); i++) {
                CheckListItem item = (CheckListItem) jList1.getModel().getElementAt(i);

                if (selected.charAt(i) == '1') {
                    item.setSelected(true);
                } else {
                    item.setSelected(false);
                }


            }
            jList1.repaint();
        } else {
            selectALL();
            return;
        }
    }

    public void configList() {
        jList1.setCellRenderer(new CheckListRenderer());
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent event) {
                JList list = (JList) event.getSource();
                int index = list.locationToIndex(event.getPoint());
                CheckListItem item = (CheckListItem) list.getModel().getElementAt(index);
                item.setSelected(!item.isSelected());
                list.repaint(list.getCellBounds(index, index));
            }
        });
    }

    public Study castStudy(org.cimmyt.cril.ibwb.domain.Study studyDto) {
        Study study = new Study();
        study.setUserid(studyDto.getUserid());
        study.setStudyid(studyDto.getStudyid());
        study.setStudy(studyDto.getSname());
        study.setTitle(studyDto.getTitle());
        study.setObjective(studyDto.getObjectiv());
        study.setEndDate(ConvertUtils.getIntegerAsDate(studyDto.getEdate()));
        study.setStarDate(ConvertUtils.getIntegerAsDate(studyDto.getEdate()));
        study.setPmkey(studyDto.getPmkey().toString());
        study.setShierarchy(studyDto.getShierarchy());
        study.setStudyType(studyDto.getStype());
        return study;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jButtonSelect = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(ExplorerWizardVisualPanel1.class, "ExplorerWizardVisualPanel1.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButtonSelect, org.openide.util.NbBundle.getMessage(ExplorerWizardVisualPanel1.class, "ExplorerWizardVisualPanel1.jButtonSelect.text")); // NOI18N
        jButtonSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelectActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonClear, org.openide.util.NbBundle.getMessage(ExplorerWizardVisualPanel1.class, "ExplorerWizardVisualPanel1.jButtonClear.text")); // NOI18N
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/studyexplorer/images/Config_100.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(ExplorerWizardVisualPanel1.class, "ExplorerWizardVisualPanel1.jLabel2.text")); // NOI18N
        jLabel2.setToolTipText(org.openide.util.NbBundle.getMessage(ExplorerWizardVisualPanel1.class, "ExplorerWizardVisualPanel1.jLabel2.toolTipText")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButtonSelect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jButtonSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jButtonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel2))
                    .addComponent(jScrollPane1))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonClear, jButtonSelect});

    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelectActionPerformed

        selectALL();
    }//GEN-LAST:event_jButtonSelectActionPerformed

    private void selectALL() {
        DefaultListModel listModel = (DefaultListModel) this.jList1.getModel();
        for (int i = 0; i < listModel.getSize(); i++) {
            CheckListItem item = (CheckListItem) jList1.getModel().getElementAt(i);
            item.setSelected(true);
        }
        jList1.repaint();
    }

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        DefaultListModel listModel = (DefaultListModel) this.jList1.getModel();
        for (int i = 0; i < listModel.getSize(); i++) {
            CheckListItem item = (CheckListItem) jList1.getModel().getElementAt(i);
            item.setSelected(false);
        }
        jList1.repaint();
    }//GEN-LAST:event_jButtonClearActionPerformed

    public void verifySelectedStudies() {
        selected = "";
        selectedStudyList = new ArrayList<Study>();

        DefaultListModel listModel = (DefaultListModel) this.jList1.getModel();
        for (int i = 0; i < listModel.getSize(); i++) {
            CheckListItem item = (CheckListItem) jList1.getModel().getElementAt(i);
            if (item.isSelected()) {
                selectedStudyList.add(studyList.get(i));
                selected = selected + "1";
            } else {
                selected = selected + "0";
            }
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonSelect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
