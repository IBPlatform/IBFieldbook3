
package ibfb.query.classes;

import java.awt.Color;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;


public class DialogoAviso1 extends javax.swing.JDialog {

   
    public DialogoAviso1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
SimpleAttributeSet aSet = new SimpleAttributeSet();

StyleConstants.setForeground(aSet, Color.black);
StyleConstants.setBackground(aSet,Color.LIGHT_GRAY);
StyleConstants.setAlignment(aSet,StyleConstants.ALIGN_CENTER);
lbltitulo1.setCharacterAttributes(aSet,true);
    }
    public DialogoAviso1(java.awt.Frame parent, boolean modal, String t1, String m3) {
        super(parent, modal);
        initComponents();
        int mitop;
        mitop=0;
        this.txtmensaje3.setVisible(false);
        this.lbltitulo1.setVisible(false);
        if (!t1.equals("")){
           this.lbltitulo1.setVisible(true);
           this.lbltitulo1.setText(t1);           
           this.lbltitulo1.setLocation(0,mitop);
           mitop+=10;
        }
        if (!m3.equals("")){
           this.txtmensaje3.setVisible(true);
           this.txtmensaje3.setText(m3);
           this.txtmensaje3.setLocation(0,mitop);
           mitop+=25;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnokMessage = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtmensaje3 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        lbltitulo1 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnokMessage.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnokMessage.setText("OK");
        btnokMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnokMessageActionPerformed(evt);
            }
        });

        txtmensaje3.setColumns(20);
        txtmensaje3.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        txtmensaje3.setLineWrap(true);
        txtmensaje3.setRows(5);
        txtmensaje3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtmensaje3.setFocusable(false);
        txtmensaje3.setOpaque(false);
        txtmensaje3.setSelectionColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(txtmensaje3);

        lbltitulo1.setBorder(null);
        lbltitulo1.setEditable(false);
        lbltitulo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitulo1.setFocusable(false);
        lbltitulo1.setOpaque(false);
        lbltitulo1.setSelectionColor(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(lbltitulo1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
                    .addComponent(btnokMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnokMessage)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnokMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnokMessageActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btnokMessageActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnokMessage;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane lbltitulo1;
    private javax.swing.JTextArea txtmensaje3;
    // End of variables declaration//GEN-END:variables

}
