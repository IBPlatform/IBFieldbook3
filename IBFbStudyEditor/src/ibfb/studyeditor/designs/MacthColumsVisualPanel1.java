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
        jLabelRow = new javax.swing.JLabel();
        jTextFieldCol = new javax.swing.JTextField();
        jTextFieldEntry = new javax.swing.JTextField();
        jLabelBlock = new javax.swing.JLabel();
        jTextFieldRow = new javax.swing.JTextField();
        jTextFieldBlock = new javax.swing.JTextField();
        jTextFieldTrial = new javax.swing.JTextField();
        jTextFieldRep = new javax.swing.JTextField();
        jTextFieldPlot = new javax.swing.JTextField();
        jLabelTrial = new javax.swing.JLabel();
        jLabelPlot = new javax.swing.JLabel();
        jLabelCol = new javax.swing.JLabel();
        jLabelRep = new javax.swing.JLabel();
        jLabelEntry = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(MacthColumsVisualPanel1.class, "MacthColumsVisualPanel1.jPanel1.border.title"))); // NOI18N

        jLabelRow.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabelRow, org.openide.util.NbBundle.getMessage(MacthColumsVisualPanel1.class, "MacthColumsVisualPanel1.jLabelRow.text")); // NOI18N

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

        jLabelBlock.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabelBlock, org.openide.util.NbBundle.getMessage(MacthColumsVisualPanel1.class, "MacthColumsVisualPanel1.jLabelBlock.text")); // NOI18N

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

        jLabelTrial.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabelTrial, org.openide.util.NbBundle.getMessage(MacthColumsVisualPanel1.class, "MacthColumsVisualPanel1.jLabelTrial.text")); // NOI18N

        jLabelPlot.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabelPlot, org.openide.util.NbBundle.getMessage(MacthColumsVisualPanel1.class, "MacthColumsVisualPanel1.jLabelPlot.text")); // NOI18N

        jLabelCol.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabelCol, org.openide.util.NbBundle.getMessage(MacthColumsVisualPanel1.class, "MacthColumsVisualPanel1.jLabelCol.text")); // NOI18N

        jLabelRep.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabelRep, org.openide.util.NbBundle.getMessage(MacthColumsVisualPanel1.class, "MacthColumsVisualPanel1.jLabelRep.text")); // NOI18N

        jLabelEntry.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabelEntry, org.openide.util.NbBundle.getMessage(MacthColumsVisualPanel1.class, "MacthColumsVisualPanel1.jLabelEntry.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelRep, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelBlock, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelRow, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelCol, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelPlot, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelEntry, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTrial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(jLabelTrial)
                    .addComponent(jTextFieldTrial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEntry))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPlot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPlot))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelRep))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBlock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBlock))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelRow))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCol))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldTrialMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTrialMousePressed
        if (this.jTextFieldTrial.getText().isEmpty()) {
        } else {
            this.jTextFieldTrial.selectAll();
        }
    }//GEN-LAST:event_jTextFieldTrialMousePressed

    private void jTextFieldEntryMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldEntryMousePressed
        if (this.jTextFieldEntry.getText().isEmpty()) {
        } else {
            this.jTextFieldEntry.selectAll();
        }
    }//GEN-LAST:event_jTextFieldEntryMousePressed

    private void jTextFieldPlotMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldPlotMousePressed
        if (this.jTextFieldPlot.getText().isEmpty()) {
        } else {
            this.jTextFieldPlot.selectAll();
        }
    }//GEN-LAST:event_jTextFieldPlotMousePressed

    private void jTextFieldRepMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldRepMousePressed
        if (this.jTextFieldRep.getText().isEmpty()) {
        } else {
            this.jTextFieldRep.selectAll();
        }
    }//GEN-LAST:event_jTextFieldRepMousePressed

    private void jTextFieldBlockMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBlockMousePressed
        if (this.jTextFieldBlock.getText().isEmpty()) {
        } else {
            this.jTextFieldBlock.selectAll();
        }
    }//GEN-LAST:event_jTextFieldBlockMousePressed

    private void jTextFieldRowMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldRowMousePressed
        if (this.jTextFieldRow.getText().isEmpty()) {
        } else {
            this.jTextFieldRow.selectAll();
        }
    }//GEN-LAST:event_jTextFieldRowMousePressed

    private void jTextFieldColMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldColMousePressed
        if (this.jTextFieldCol.getText().isEmpty()) {
        } else {
            this.jTextFieldCol.selectAll();
        }
    }//GEN-LAST:event_jTextFieldColMousePressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelBlock;
    private javax.swing.JLabel jLabelCol;
    private javax.swing.JLabel jLabelEntry;
    private javax.swing.JLabel jLabelPlot;
    private javax.swing.JLabel jLabelRep;
    private javax.swing.JLabel jLabelRow;
    private javax.swing.JLabel jLabelTrial;
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

    public void loadFieldLabels() {
        //TRIAL, ENTRY, PLOT, BLOCK, REP, COL, ROW 

        DefaultListModel listModel = (DefaultListModel) this.jList1.getModel();

        this.jLabelTrial.setText(MacthColumsWizardIterator.facDesign.get(0).getFactorName());
        if (listModel.contains(MacthColumsWizardIterator.facDesign.get(0).getFactorName())) {
            this.jTextFieldTrial.setText(MacthColumsWizardIterator.facDesign.get(0).getFactorName());
            listModel.removeElement(MacthColumsWizardIterator.facDesign.get(0).getFactorName());
        }

        this.jLabelEntry.setText(MacthColumsWizardIterator.facDesign.get(1).getFactorName());
        if (listModel.contains(MacthColumsWizardIterator.facDesign.get(1).getFactorName())) {
            this.jTextFieldEntry.setText(MacthColumsWizardIterator.facDesign.get(1).getFactorName());
            listModel.removeElement(MacthColumsWizardIterator.facDesign.get(1).getFactorName());
        }

        this.jLabelPlot.setText(MacthColumsWizardIterator.facDesign.get(2).getFactorName());
        if (listModel.contains(MacthColumsWizardIterator.facDesign.get(2).getFactorName())) {
            this.jTextFieldPlot.setText(MacthColumsWizardIterator.facDesign.get(2).getFactorName());
            listModel.removeElement(MacthColumsWizardIterator.facDesign.get(2).getFactorName());
        }

        if (MacthColumsWizardIterator.facDesign.get(3).isFounded()) {
            this.jLabelBlock.setText(MacthColumsWizardIterator.facDesign.get(3).getFactorName());
            this.jLabelBlock.setVisible(true);
            this.jTextFieldBlock.setVisible(true);

            if (listModel.contains(MacthColumsWizardIterator.facDesign.get(3).getFactorName())) {
                this.jTextFieldBlock.setText(MacthColumsWizardIterator.facDesign.get(3).getFactorName());
                listModel.removeElement(MacthColumsWizardIterator.facDesign.get(3).getFactorName());
            }

        } else {
            this.jLabelBlock.setVisible(false);
            this.jTextFieldBlock.setVisible(false);
        }

        
        
        if (MacthColumsWizardIterator.facDesign.get(4).isFounded()) {
            this.jLabelRep.setText(MacthColumsWizardIterator.facDesign.get(4).getFactorName());
            this.jLabelRep.setVisible(true);
            this.jTextFieldRep.setVisible(true);

            if (listModel.contains(MacthColumsWizardIterator.facDesign.get(4).getFactorName())) {
                this.jTextFieldRep.setText(MacthColumsWizardIterator.facDesign.get(4).getFactorName());
                listModel.removeElement(MacthColumsWizardIterator.facDesign.get(4).getFactorName());
            }

        } else {
            this.jLabelRep.setVisible(false);
            this.jTextFieldRep.setVisible(false);
        }

       
        
        
        if (MacthColumsWizardIterator.facDesign.get(5).isFounded()) {
            
            this.jLabelCol.setText(MacthColumsWizardIterator.facDesign.get(5).getFactorName());
            this.jLabelCol.setVisible(true);
            this.jTextFieldCol.setVisible(true);

            if (listModel.contains(MacthColumsWizardIterator.facDesign.get(5).getFactorName())) {
                this.jTextFieldCol.setText(MacthColumsWizardIterator.facDesign.get(5).getFactorName());
                listModel.removeElement(MacthColumsWizardIterator.facDesign.get(5).getFactorName());
            }


        } else {
            this.jLabelCol.setVisible(false);
            this.jTextFieldCol.setVisible(false);
        }


       
        
        if (MacthColumsWizardIterator.facDesign.get(6).isFounded()) {
            this.jLabelRow.setText(MacthColumsWizardIterator.facDesign.get(6).getFactorName());
            this.jLabelRow.setVisible(true);
            this.jTextFieldRow.setVisible(true);

            if (listModel.contains(MacthColumsWizardIterator.facDesign.get(6).getFactorName())) {
                this.jTextFieldRow.setText(MacthColumsWizardIterator.facDesign.get(6).getFactorName());
                listModel.removeElement(MacthColumsWizardIterator.facDesign.get(6).getFactorName());
            }

        } else {
            this.jLabelRow.setVisible(false);
            this.jTextFieldRow.setVisible(false);
        }





    }

    public void loadColumnsIntoList() {

        DefaultListModel listModel = new DefaultListModel();
        

        String[] headers = MacthColumsWizardIterator.headers;
        
       // System.out.println("TAM ITERATOR= "+headers.length);
        
        for (int i = 0; i < headers.length; i++) {
            System.out.println("ELEMENTO: "+headers[i]);
            listModel.addElement(headers[i]);
        }

        this.jList1.setModel(listModel);

    }

    String getTrialString() {
        return this.jTextFieldTrial.getText();
    }

    String getEntryString() {
        return this.jTextFieldEntry.getText();
    }

    String getPlotString() {
        return this.jTextFieldPlot.getText();
    }

    String getRepString() {
        if (jLabelRep.isVisible()) {
            return this.jTextFieldRep.getText();
        } else {
            return "";
        }

    }

    String getBlockString() {
        if (jLabelBlock.isVisible()) {
            return this.jTextFieldBlock.getText();
        } else {
            return "";
        }
    }

    String getRowString() {
        if (jLabelRow.isVisible()) {
            return this.jTextFieldRow.getText();
        } else {
            return "";
        }
    }

    String getColString() {
        if (jLabelCol.isVisible()) {
            return this.jTextFieldCol.getText();
        } else {
            return "";
        }
    }
}
