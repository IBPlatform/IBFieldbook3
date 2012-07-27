package ibfb.studyeditor.designs;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public final class MacthColumsVisualPanel1 extends JPanel {
    
    private File excelFile;

    public MacthColumsVisualPanel1() {
        initComponents();
        loadColumnsIntoList();
        assignHandlers();
        disableKeyboard();
    }

    @Override
    public String getName() {
        return "Match columns";
    }

    public File getExcelFile() {
        return excelFile;
    }

    public void setExcelFile(File excelFile) {
        this.excelFile = excelFile;
    }

    
   
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldCol = new javax.swing.JTextField();
        jTextFieldEntry = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldRow = new javax.swing.JTextField();
        jTextFieldBlock = new javax.swing.JTextField();
        jTextFieldTrial = new javax.swing.JTextField();
        jTextFieldRep = new javax.swing.JTextField();
        jTextFieldPlot = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(MacthColumsVisualPanel1.class, "MacthColumsVisualPanel1.jPanel1.border.title"))); // NOI18N

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(MacthColumsVisualPanel1.class, "MacthColumsVisualPanel1.jLabel6.text")); // NOI18N

        jTextFieldCol.setEditable(false);
        jTextFieldCol.setText(org.openide.util.NbBundle.getMessage(MacthColumsVisualPanel1.class, "MacthColumsVisualPanel1.jTextFieldCol.text")); // NOI18N
        jTextFieldCol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldColMousePressed(evt);
            }
        });

        jTextFieldEntry.setEditable(false);
        jTextFieldEntry.setText(org.openide.util.NbBundle.getMessage(MacthColumsVisualPanel1.class, "MacthColumsVisualPanel1.jTextFieldEntry.text")); // NOI18N
        jTextFieldEntry.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldEntryMousePressed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(MacthColumsVisualPanel1.class, "MacthColumsVisualPanel1.jLabel5.text")); // NOI18N

        jTextFieldRow.setEditable(false);
        jTextFieldRow.setText(org.openide.util.NbBundle.getMessage(MacthColumsVisualPanel1.class, "MacthColumsVisualPanel1.jTextFieldRow.text")); // NOI18N
        jTextFieldRow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldRowMousePressed(evt);
            }
        });

        jTextFieldBlock.setEditable(false);
        jTextFieldBlock.setText(org.openide.util.NbBundle.getMessage(MacthColumsVisualPanel1.class, "MacthColumsVisualPanel1.jTextFieldBlock.text")); // NOI18N
        jTextFieldBlock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldBlockMousePressed(evt);
            }
        });

        jTextFieldTrial.setEditable(false);
        jTextFieldTrial.setText(org.openide.util.NbBundle.getMessage(MacthColumsVisualPanel1.class, "MacthColumsVisualPanel1.jTextFieldTrial.text")); // NOI18N
        jTextFieldTrial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldTrialMousePressed(evt);
            }
        });

        jTextFieldRep.setEditable(false);
        jTextFieldRep.setText(org.openide.util.NbBundle.getMessage(MacthColumsVisualPanel1.class, "MacthColumsVisualPanel1.jTextFieldRep.text")); // NOI18N
        jTextFieldRep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldRepMousePressed(evt);
            }
        });

        jTextFieldPlot.setEditable(false);
        jTextFieldPlot.setText(org.openide.util.NbBundle.getMessage(MacthColumsVisualPanel1.class, "MacthColumsVisualPanel1.jTextFieldPlot.text")); // NOI18N
        jTextFieldPlot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldPlotMousePressed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(MacthColumsVisualPanel1.class, "MacthColumsVisualPanel1.jLabel1.text")); // NOI18N

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(MacthColumsVisualPanel1.class, "MacthColumsVisualPanel1.jLabel3.text")); // NOI18N

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(MacthColumsVisualPanel1.class, "MacthColumsVisualPanel1.jLabel7.text")); // NOI18N

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(MacthColumsVisualPanel1.class, "MacthColumsVisualPanel1.jLabel4.text")); // NOI18N

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(MacthColumsVisualPanel1.class, "MacthColumsVisualPanel1.jLabel2.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTrial, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPlot, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldRep, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldRow, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCol, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldTrial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPlot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBlock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(MacthColumsVisualPanel1.class, "MacthColumsVisualPanel1.jPanel2.border.title"))); // NOI18N

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.setDragEnabled(true);
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldTrialMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTrialMousePressed
       if(this.jTextFieldTrial.getText().isEmpty()){
           
       }else{
           this.jTextFieldTrial.selectAll();
       }
    }//GEN-LAST:event_jTextFieldTrialMousePressed

    private void jTextFieldEntryMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldEntryMousePressed
 if(this.jTextFieldEntry.getText().isEmpty()){
           
       }else{
           this.jTextFieldEntry.selectAll();
       }       
    }//GEN-LAST:event_jTextFieldEntryMousePressed

    private void jTextFieldPlotMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldPlotMousePressed
        if(this.jTextFieldPlot.getText().isEmpty()){
           
       }else{
           this.jTextFieldPlot.selectAll();
       }
    }//GEN-LAST:event_jTextFieldPlotMousePressed

    private void jTextFieldRepMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldRepMousePressed
         if(this.jTextFieldRep.getText().isEmpty()){
           
       }else{
           this.jTextFieldRep.selectAll();
       }
    }//GEN-LAST:event_jTextFieldRepMousePressed

    private void jTextFieldBlockMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBlockMousePressed
       if(this.jTextFieldBlock.getText().isEmpty()){
           
       }else{
           this.jTextFieldBlock.selectAll();
       }
    }//GEN-LAST:event_jTextFieldBlockMousePressed

    private void jTextFieldRowMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldRowMousePressed
        if(this.jTextFieldRow.getText().isEmpty()){
           
       }else{
           this.jTextFieldRow.selectAll();
       }
    }//GEN-LAST:event_jTextFieldRowMousePressed

    private void jTextFieldColMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldColMousePressed
        if(this.jTextFieldCol.getText().isEmpty()){
           
       }else{
           this.jTextFieldCol.selectAll();
       }
    }//GEN-LAST:event_jTextFieldColMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldBlock;
    private javax.swing.JTextField jTextFieldCol;
    private javax.swing.JTextField jTextFieldEntry;
    private javax.swing.JTextField jTextFieldPlot;
    private javax.swing.JTextField jTextFieldRep;
    private javax.swing.JTextField jTextFieldRow;
    private javax.swing.JTextField jTextFieldTrial;
    // End of variables declaration//GEN-END:variables

    private void assignHandlers() {
        ListTransferHandler tranfer = new ListTransferHandler();
        this.jList1.setTransferHandler(tranfer);
        this.jTextFieldTrial.setDragEnabled(true);
        this.jTextFieldBlock.setDragEnabled(true);
        this.jTextFieldCol.setDragEnabled(true);
        this.jTextFieldEntry.setDragEnabled(true);
        this.jTextFieldPlot.setDragEnabled(true);
        this.jTextFieldRep.setDragEnabled(true);
        this.jTextFieldRow.setDragEnabled(true);
        this.jTextFieldTrial.setTransferHandler(new JTextFieldTransferHandler());
        this.jTextFieldBlock.setTransferHandler(new JTextFieldTransferHandler());
        this.jTextFieldCol.setTransferHandler(new JTextFieldTransferHandler());
        this.jTextFieldEntry.setTransferHandler(new JTextFieldTransferHandler());
        this.jTextFieldPlot.setTransferHandler(new JTextFieldTransferHandler());
        this.jTextFieldRep.setTransferHandler(new JTextFieldTransferHandler());
        this.jTextFieldRow.setTransferHandler(new JTextFieldTransferHandler());
    }

    private void disableKeyboard() {
        this.jTextFieldTrial.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();


                if ((c > 0) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_8) || (c == KeyEvent.VK_CLEAR) || (c == 8)) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();

                if ((c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_8) || (c == KeyEvent.VK_CLEAR) || (c == 8)) {
                    e.consume();
                }
            }
        });




        this.jTextFieldBlock.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c > 0) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_8) || (c == KeyEvent.VK_CLEAR) || (c == 8)) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_8) || (c == KeyEvent.VK_CLEAR) || (c == 8)) {
                    e.consume();
                }
            }
        });


        this.jTextFieldCol.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c > 0) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_8) || (c == KeyEvent.VK_CLEAR) || (c == 8)) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_8) || (c == KeyEvent.VK_CLEAR) || (c == 8)) {
                    e.consume();
                }
            }
        });

        this.jTextFieldEntry.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c > 0) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_8) || (c == KeyEvent.VK_CLEAR) || (c == 8)) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_8) || (c == KeyEvent.VK_CLEAR) || (c == 8)) {
                    e.consume();
                }
            }
        });


        this.jTextFieldPlot.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c > 0) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_8) || (c == KeyEvent.VK_CLEAR) || (c == 8)) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_8) || (c == KeyEvent.VK_CLEAR) || (c == 8)) {
                    e.consume();
                }
            }
        });


        this.jTextFieldRep.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c > 0) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_8) || (c == KeyEvent.VK_CLEAR) || (c == 8)) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_8) || (c == KeyEvent.VK_CLEAR) || (c == 8)) {
                    e.consume();
                }
            }
        });


        this.jTextFieldRow.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c > 0) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_8) || (c == KeyEvent.VK_CLEAR) || (c == 8)) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_8) || (c == KeyEvent.VK_CLEAR) || (c == 8)) {
                    e.consume();
                }
            }
        });




    }

    public JTextField getjTextFieldBlock() {
        return jTextFieldBlock;
    }

    public void setjTextFieldBlock(JTextField jTextFieldBlock) {
        this.jTextFieldBlock = jTextFieldBlock;
    }

    public JTextField getjTextFieldCol() {
        return jTextFieldCol;
    }

    public void setjTextFieldCol(JTextField jTextFieldCol) {
        this.jTextFieldCol = jTextFieldCol;
    }

    public JTextField getjTextFieldEntry() {
        return jTextFieldEntry;
    }

    public void setjTextFieldEntry(JTextField jTextFieldEntry) {
        this.jTextFieldEntry = jTextFieldEntry;
    }

    public JTextField getjTextFieldPlot() {
        return jTextFieldPlot;
    }

    public void setjTextFieldPlot(JTextField jTextFieldPlot) {
        this.jTextFieldPlot = jTextFieldPlot;
    }

    public JTextField getjTextFieldRep() {
        return jTextFieldRep;
    }

    public void setjTextFieldRep(JTextField jTextFieldRep) {
        this.jTextFieldRep = jTextFieldRep;
    }

    public JTextField getjTextFieldRow() {
        return jTextFieldRow;
    }

    public void setjTextFieldRow(JTextField jTextFieldRow) {
        this.jTextFieldRow = jTextFieldRow;
    }

    public JTextField getjTextFieldTrial() {
        return jTextFieldTrial;
    }

    public void setjTextFieldTrial(JTextField jTextFieldTrial) {
        this.jTextFieldTrial = jTextFieldTrial;
    }

    
    public void loadColumnsIntoList() {
    
    DefaultListModel listModel = new DefaultListModel();
    
    String[] headres=MacthColumsWizardIterator.headers;
    
        for (int i = 0; i < headres.length; i++) {
           listModel.addElement(headres[i]);
            
        }
        
       if(listModel.contains("TRIAL")){
           this.jTextFieldTrial.setText("TRIAL");
           listModel.removeElement("TRIAL");
       }
       if(listModel.contains("ENTRY")){
           this.jTextFieldEntry.setText("ENTRY");
           listModel.removeElement("ENTRY");
       }
       if(listModel.contains("PLOT")){
           this.jTextFieldPlot.setText("PLOT");
           listModel.removeElement("PLOT");
       }
       if(listModel.contains("REP")){
           this.jTextFieldRep.setText("REP");
           listModel.removeElement("REP");
       }
       if(listModel.contains("BLOCK")){
           this.jTextFieldBlock.setText("BLOCK");
           listModel.removeElement("BLOCK");
       }
       if(listModel.contains("ROW")){
           this.jTextFieldRow.setText("ROW");
           listModel.removeElement("ROW");
       }
       if(listModel.contains("COLUMN")){
           this.jTextFieldCol.setText("COLUMN");
           listModel.removeElement("COLUMN");
       }
                
        this.jList1.setModel(listModel);
                
        
    }
    
   
    
    
    
    
    
}
