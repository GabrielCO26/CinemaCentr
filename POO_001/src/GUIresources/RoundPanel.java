package GUIresources;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class RoundPanel extends JPanel {

    private Color color;

    public RoundPanel() {
        setOpaque(false);
        color = Color.WHITE;
    }

    public void setColor(Color color) {
        this.color = color;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 25, 25));
    }
}
