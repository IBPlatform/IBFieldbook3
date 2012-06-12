/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TestDoubleList.java
 *
 * Created on Jul 6, 2011, 12:24:49 PM
 */
package org.cimmyt.cril.ibwb.test;

import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.commongui.select.list.DoubleListPanel;
import org.cimmyt.cril.ibwb.commongui.select.list.SelectCommand;

/**
 *
 * @author TMSANCHEZ
 */
public class TestDoubleList extends javax.swing.JFrame {

    private List<String> list1 = new ArrayList<String>();
    private List<String> list2 = new ArrayList<String>();
    private SelectCommand cmdUno = new SelectCommand() {

        @Override
        public void execute() {
            System.out.println("comando uno");
        }
    };
    private SelectCommand cmdDos = new SelectCommand() {

        @Override
        public void execute() {
            System.out.println("comando dos");
        }
    };

    /** Creates new form TestDoubleList */
    public TestDoubleList() {
        initComponents();

        list1.add("A");
        list1.add("B");
        list1.add("C");

        //DoubleListPanel<String> doubleListPanel =  new DoubleListPanel<String>(list1,list2);
        DoubleListPanel<String> doubleListPanel = new DoubleListPanel<String>(list1, list2, cmdUno, cmdDos);


        doubleListPanel.setVisible(true);
        
       
       
        //doubleListPanel.setPreferredSize(doubleListPanel.getParent().getSize());
        
 jPanel1.add(doubleListPanel);

  this.pack();
//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addContainerGap()
//                .addComponent(doubleListPanel, doubleListPanel.getSize().width, doubleListPanel.getSize().width, Short.MAX_VALUE)
//                .addContainerGap()
//                )
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addContainerGap()
//                .addComponent(doubleListPanel, doubleListPanel.getSize().height, doubleListPanel.getSize().height, Short.MAX_VALUE)
//                .addContainerGap()
//                )
//        );        
//        
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new TestDoubleList().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
