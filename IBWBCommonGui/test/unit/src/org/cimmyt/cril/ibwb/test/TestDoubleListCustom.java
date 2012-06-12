/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import org.cimmyt.cril.ibwb.commongui.select.list.DoubleListPanel;

/**
 *
 * @author TMSANCHEZ
 */
public class TestDoubleListCustom {

    public static void main(String[] args) {
        List<TestObject> list1 = new ArrayList<TestObject>();
        List<TestObject> list2 = new ArrayList<TestObject>();
        
        list1.add(new TestObject(1,"Test object 1"));
        list1.add(new TestObject(2,"Test object 2"));
        list1.add(new TestObject(3,"Test object 3"));
        list1.add(new TestObject(4,"Test object 4"));

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
       
        frame.setLayout(new FlowLayout());
        final DoubleListPanel<TestObject> doubleListPanel = new DoubleListPanel<TestObject>(list1, list2);
        doubleListPanel.setVisible(true);
        
        JButton button = new JButton("Mostrar seleccionados");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (TestObject testObject: doubleListPanel.getTargetList()) {
                    System.out.println("testObject " + testObject.toString());
                }
            }
        });
        
        frame.add(doubleListPanel);
        frame.add(button);
        frame.pack();
        frame.setVisible(true);
    }
}
