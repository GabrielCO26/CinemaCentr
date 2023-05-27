package GUIresources;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import javax.swing.JPanel;

public class RoundPanel2 extends JPanel {

    private Color color;
    private int topLeftRadius, topRightRadius, bottomLeftRadius, bottomRightRadius;

    public RoundPanel2() {
        setOpaque(false);
        color = Color.WHITE;
        topLeftRadius = topRightRadius = bottomLeftRadius = bottomRightRadius = 0;
    }

    public void setColor(Color color) {
        this.color = color;
        repaint();
    }
    
    @Override
    public void setCursor(Cursor cursor) {
        super.setCursor(cursor);
    }

    public void setRadius(int radius){
        topLeftRadius = topRightRadius = bottomLeftRadius = bottomRightRadius = radius;
    }
    
    public void setTopLeftRadius(int radius) {
        topLeftRadius = radius;
        repaint();
    }

    public void setTopRightRadius(int radius) {
        topRightRadius = radius;
        repaint();
    }

    public void setBottomLeftRadius(int radius) {
        bottomLeftRadius = radius;
        repaint();
    }

    public void setBottomRightRadius(int radius) {
        bottomRightRadius = radius;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Define la forma del borde
        Path2D.Double borderShape = new Path2D.Double();
        borderShape.moveTo(topLeftRadius, 0);
        borderShape.lineTo(getWidth() - topRightRadius, 0);
        borderShape.quadTo(getWidth(), 0, getWidth(), topRightRadius);
        borderShape.lineTo(getWidth(), getHeight() - bottomRightRadius);
        borderShape.quadTo(getWidth(), getHeight(), getWidth() - bottomRightRadius, getHeight());
        borderShape.lineTo(bottomLeftRadius, getHeight());
        borderShape.quadTo(0, getHeight(), 0, getHeight() - bottomLeftRadius);
        borderShape.lineTo(0, topLeftRadius);
        borderShape.quadTo(0, 0, topLeftRadius, 0);

        // Dibuja el fondo y el borde
        g2d.setColor(color);
        g2d.fill(borderShape);
    }
}
