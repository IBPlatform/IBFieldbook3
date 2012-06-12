/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyeditor.test;

import ibfb.studyeditor.util.SelectLocationPanel;
import javax.swing.JFrame;

/**
 *
 * @author mulat
 */
public class TestSelectLocationPanel {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       
        SelectLocationPanel panel = new SelectLocationPanel();
        frame.add(panel);
        frame.setVisible(true);
             
    }
}
    