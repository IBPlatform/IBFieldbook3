package ibfb.branding.core;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import javax.swing.JRootPane;
import org.openide.util.ImageUtilities;

public class RootFrame extends javax.swing.JFrame {

    private final Paint bannerPaint = makeFondo();

    public RootFrame() {
        setRootPane(new CustomRootPane());
        initComponents();
        setName("NbMainWindow");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    static void init() {
        new RootFrame();
    }

    private Paint makeFondo() {
        BufferedImage img = (BufferedImage) ImageUtilities.loadImage("ibfb/branding/core/world.jpg");
        return new TexturePaint(img, new Rectangle(0, 0, img.getWidth() + 120, img.getHeight()));
    }

    private class CustomRootPane extends JRootPane {

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setPaint(bannerPaint);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
