/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.test;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import org.cimmyt.cril.ibwb.commongui.select.location.SelectLocationPanel;

/**
 *
 * @author tmsanchez
 */
public class TestSelectLocation  {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout( new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        SelectLocationPanel slp = new SelectLocationPanel();
        frame.add(slp);
        
        frame.pack();
        
        frame.setVisible(true);
    }
    
}
