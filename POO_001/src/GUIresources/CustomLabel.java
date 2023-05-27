package GUIresources;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CustomLabel extends JLabel {
    private BufferedImage image;

    public CustomLabel() {
        setOpaque(true);
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            // Calcula el tamaño de la imagen ajustada al tamaño del JLabel
            int width = getWidth();
            int height = getHeight();
            double aspectRatio = (double) image.getWidth() / image.getHeight();

            if (width / (double) height > aspectRatio) {
                width = (int) (height * aspectRatio);
            } else {
                height = (int) (width / aspectRatio);
            }

            // Dibuja la imagen en el centro del JLabel
            int x = (getWidth() - width) / 2;
            int y = (getHeight() - height) / 2;

            g.drawImage(image, x, y, width, height, null);
        }
    }
}

