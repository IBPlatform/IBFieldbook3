/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider;

import java.awt.Component;
import java.awt.Cursor;
import javax.swing.JFrame;
import org.cimmyt.cril.ibwb.api.AppServices;
import org.openide.util.Mutex;
import org.openide.windows.WindowManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author TMSANCHEZ
 */
public class IBServices {

    private static IBServices iBServices;
    private AppServices services;

    private IBServices() {
        changeCursorWaitStatus(true);
        String[] configFile = {"ibwbApplicationContext.xml"};
        //String[] configFile = {"ibwbApplicationContextMemory.xml"};
        ApplicationContext context = new ClassPathXmlApplicationContext(configFile);
        services = (AppServices) context.getBean("ibwbAppServices");
        changeCursorWaitStatus(false);

    }

    public static IBServices instance() {
        if (iBServices == null) {
           
            iBServices = new IBServices();
        }
        return iBServices;
    }

    public AppServices services() {
        return services;
    }
    
    
     private static void changeCursorWaitStatus(final boolean isWaiting) {
        Mutex.EVENT.writeAccess(new Runnable() {

            @Override
            public void run() {
                try {
                    JFrame mainFrame =
                            (JFrame) WindowManager.getDefault().getMainWindow();
                    Component glassPane = mainFrame.getGlassPane();
                    if (isWaiting) {
                        glassPane.setVisible(true);

                        glassPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    } else {
                        glassPane.setVisible(false);

                        glassPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    }
                } catch (Exception e) {
                }
            }
        });
    }

    
}


