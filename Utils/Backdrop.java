package Utils;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

public class Backdrop extends ImagePanelLoader {
    private final int imageWidth;      // Width of one image (background)
    private final int panelWidth;      // Width of the panel (viewable area)
    private int panelHeight;           // Height of the panel (including racetrack floor)
    private BufferedImage floorImage; // The floor image (racetrack floor)
    private int floorX1, floorX2, floorX3; // X positions for the floor images
    private final int floorWidth;     // Width of the floor image
    private int cameraX;
    // Constructor
    public Backdrop(String imagePath, String floorImagePath, int panelWidth, int panelHeight, int scaleFactor) throws FileNotFoundException {
        super(imagePath, scaleFactor);


        setOpaque(false);
        this.scaleFactor = scaleFactor;
        try {
            // Load images
            this.floorImage = loadImage(floorImagePath);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileNotFoundException("Image could not be loaded:" + e.getMessage());
        }

        // Set panel size
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;

        // Get the width of one image
        this.imageWidth = getImageWidth();
        this.floorWidth = floorImage.getWidth();

        // Initial X positions for floor

        this.floorX1 = 0;
        this.floorX2 = floorWidth;
        this.floorX3 = floorWidth * 2;

        // Keep track of where logical camera is
        this.cameraX = 0;
    }

    // Paint method to draw the images on the panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the racetrack floor at the bottom
        
        int floorY = getImageHeight();
        g.drawImage(floorImage, floorX1, floorY, null);
        g.drawImage(floorImage, floorX2, floorY, null);
        g.drawImage(floorImage, floorX3, floorY, null);
    }

    /**
     * Moves the backdrop and floor by 1 pixel and auto-stitches the next image.
     */
    public void movePanel() {
        cameraX += 1;
        // Move each background image by 1 pixel
        setX1(getX1() - 1);
        setX2(getX2() - 1);
        setX3(getX3() - 1);

        // When a background image moves off the screen, reposition it
        if (getX1() + imageWidth <= 0) {
            setX1(getX3() + imageWidth);
        }
        if (getX2() + imageWidth <= 0) {
            setX2(getX1() + imageWidth);
        }
        if (getX3() + imageWidth <= 0) {
            setX3(getX2() + imageWidth);
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

    public int getCameraX() {
        return cameraX;
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
