package Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

/**
 * Simple class to take an image path and create a [JPanel] with that image as
 * the background at the desired scale.
 * It will then load 3 versions of this image so it can be extended a bit beyond
 * the screen.
 */
public class ImagePanelLoader extends JPanel {

    protected int scaleFactor;
    private BufferedImage image; // The image to load
    private int x1, x2, x3; // X positions for the three background images

    public ImagePanelLoader(String imagePath, int scaleFactor) throws FileNotFoundException {
        this.scaleFactor = scaleFactor;

        try {
            // Load images
            this.image = loadImage(imagePath);
            this.x1 = 0;
            this.x2 = image.getWidth();
            this.x3 = this.x2 * 2;

        } catch (Exception e) {
            e.printStackTrace();
            throw new FileNotFoundException("Image could not be loaded:" + e.getMessage());
        }
    }

    // Utility method to load and scale image using nearest neighbor
    protected BufferedImage loadImage(String path) throws Exception {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();

        int scaledWidth = img.getWidth(null) * scaleFactor;
        int scaledHeight = img.getHeight(null) * scaleFactor;

        BufferedImage bufferedImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2d.drawImage(img, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        return bufferedImage;
    }

    // Paint method to draw the images on the panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the backdrop images
        g.drawImage(image, x1, 0, null);
        g.drawImage(image, x2, 0, null);
        g.drawImage(image, x3, 0, null);
        // Initial X positions for background and floor
    }

    ////////////////////////////////////////////////////////////////////////
    // Define some public methods to be used by the child class - backdrop /
    ////////////////////////////////////////////////////////////////////////

    /// Basic Accessor method for getting the image width
    protected int getImageWidth() {
        return this.image.getWidth();
    }

    /// Basic Accessor method for getting the image height
    protected int getImageHeight() {
        return this.image.getHeight();
    }

    // Define seters and getters for the axis values

    protected void setX1(int value) {
        this.x1 = value;
    }

    protected void setX2(int value) {
        this.x2 = value;
    }

    protected void setX3(int value) {
        this.x3 = value;
    }

    protected int getX1() {
        return this.x1;
    }

    protected int getX2() {
        return this.x2;
    }

    protected int getX3() {
        return this.x3;
    }

}
