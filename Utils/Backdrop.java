package Utils;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

public class Backdrop extends JPanel {
    private BufferedImage image;      // The image to repeat (background)
    private int x1, x2, x3;           // X positions for the three background images
    private final int imageWidth;      // Width of one image (background)
    private final int panelWidth;      // Width of the panel (viewable area)
    private int panelHeight;           // Height of the panel (including racetrack floor)
    private BufferedImage floorImage; // The floor image (racetrack floor)
    private int floorX1, floorX2, floorX3; // X positions for the floor images
    private final int floorWidth;     // Width of the floor image
    private int scaleFactor;
    // Constructor
    public Backdrop(String imagePath, String floorImagePath, int panelWidth, int panelHeight, int scaleFactor) throws FileNotFoundException {
        this.scaleFactor = scaleFactor;
        try {
            // Load images
            this.image = loadImage(imagePath, scaleFactor);
            this.floorImage = loadImage(floorImagePath, scaleFactor);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileNotFoundException("Image could not be loaded:" + e.getMessage());
        }

        // Set panel size
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;

        // Get the width of one image
        this.imageWidth = image.getWidth();
        this.floorWidth = floorImage.getWidth();

        // Initial X positions for background and floor
        this.x1 = 0;
        this.x2 = imageWidth;
        this.x3 = imageWidth * 2;

        this.floorX1 = 0;
        this.floorX2 = floorWidth;
        this.floorX3 = floorWidth * 2;
    }

    // Load image utility function with scaling
    private BufferedImage loadImage(String path, int scaleFactor) throws Exception {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();

        // Get the scaled width and height
        int scaledWidth = img.getWidth(null) * scaleFactor;
        int scaledHeight = img.getHeight(null) * scaleFactor;

        // Create a scaled image using nearest neighbor scaling to preserve pixel art
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

        // Draw the racetrack floor at the bottom
        
        int floorY = image.getHeight();
        g.drawImage(floorImage, floorX1, floorY, null);
        g.drawImage(floorImage, floorX2, floorY, null);
        g.drawImage(floorImage, floorX3, floorY, null);
    }

    /**
     * Moves the backdrop and floor by 1 pixel and auto-stitches the next image.
     */
    public void movePanel() {
        // Move each background image by 1 pixel
        x1 -= 1;
        x2 -= 1;
        x3 -= 1;

        // When a background image moves off the screen, reposition it
        if (x1 + imageWidth <= 0) {
            x1 = x3 + imageWidth;
        }
        if (x2 + imageWidth <= 0) {
            x2 = x1 + imageWidth;
        }
        if (x3 + imageWidth <= 0) {
            x3 = x2 + imageWidth;
        }

        // Move the floor images horizontally with the backdrop
        floorX1 -= 1;
        floorX2 -= 1;
        floorX3 -= 1;

        // Reposition floor images when they move off the screen
        if (floorX1 + floorWidth <= 0) {
            floorX1 = floorX3 + floorWidth;
        }
        if (floorX2 + floorWidth <= 0) {
            floorX2 = floorX1 + floorWidth;
        }
        if (floorX3 + floorWidth <= 0) {
            floorX3 = floorX2 + floorWidth;
        }

        // Repaint the panel to reflect the changes
        repaint();
    }

    

    /**
     * Sets the height of the racetrack floor to allow space for more horses.
     *
     * @param newHeight The new height for the racetrack floor.
     */
    public void setFloorHeight(int newHeight) {
        this.panelHeight = newHeight;
        repaint();
    }

    /**
     * Gets the current width of the panel (viewable area).
     *
     * @return The current width of the panel.
     */
    public int getPanelWidth() {
        return panelWidth;
    }

    /**
     * Gets the current height of the panel (including floor).
     *
     * @return The current height of the panel.
     */
    public int getPanelHeight() {
        return panelHeight;
    }
}
