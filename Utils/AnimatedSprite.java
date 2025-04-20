package Utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class AnimatedSprite extends JLabel {
    private ArrayList<BufferedImage> frames;
    private int frameIndex = 0;
    private Timer timer;
    private int frameDelay = 83;

    public AnimatedSprite(String filePath) {
        try {
            // Convert the animation to a series of images
            this.frames = convertGIF(filePath, 0.35);
        } catch (Exception e) {
            // If errors then print stacktrace and assume there are simply no frames
            e.printStackTrace();
            this.frames = new ArrayList<>();
        }

        // Set the initial icon to the first image of the sequence
        setIcon(new ImageIcon(frames.get(0)));

        // Make sure transparency works and sizing is correct
        setOpaque(false);
        setSize(frames.get(0).getWidth(), frames.get(0).getHeight());

        // Begin the animation
        timer = new Timer(frameDelay, e -> {
            frameIndex = (frameIndex + 1) % frames.size();
            setIcon(new ImageIcon(frames.get(frameIndex)));
        });
        timer.start();
    }

    /**
     * Stop the animation
     */
    public void stop() {
        if (timer != null)
            timer.stop();
    }

    /**
     * Start the animation
     */
    public void start() {
        if (timer != null)
            timer.start();
    }

    /**
     * Set the location of the sprite on the screen.
     * 
     * @param x coordinate
     * @param y coordinate
     */
    public void setPosition(int x, int y) {
        setLocation(x, y);
    }

    /**
     * Method to convert an image without an alpha channel to an image with one.
     * 
     * @param img
     * @return
     */
    public BufferedImage makeBlackTransparent(BufferedImage img) {
        BufferedImage transparentImage = new BufferedImage(
                img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgba = img.getRGB(x, y);
                Color col = new Color(rgba, true);

                if (col.getRed() == 0xff && col.getGreen() == 0xff && col.getBlue() == 0) {
                    // Make black fully transparent
                    transparentImage.setRGB(x, y, 0xFFFF0000);
                } else {
                    transparentImage.setRGB(x, y, rgba);
                }
            }
        }

        return transparentImage;
    }

    /**
     * Method to scale the image to a smaller size
     * 
     * @param original
     * @param scale
     * @return
     */
    public static BufferedImage scaleImage(BufferedImage original, double scale) {
        // Define the new size of the image
        int width = (int) (original.getWidth() * scale);
        int height = (int) (original.getHeight() * scale);

        // Create a new image of this size
        BufferedImage scaled = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Draw the existing image to the scaled smaller image and return it.
        Graphics2D g2d = scaled.createGraphics();
        g2d.drawImage(original, 0, 0, width, height, null);
        g2d.dispose();
        return scaled;
    }

    /**
     * Method to convert a GIF to a sequence of images
     * 
     * @param gifPath file location of the GIF
     * @param scale   how big output should be relative to input
     * @return an image sequence
     * @throws Exception
     */
    public static ArrayList<BufferedImage> convertGIF(String gifPath, double scale) throws Exception {
        // Declares an [ArrayList] of [BufferedImage] to store each frame of the GIF
        ArrayList<BufferedImage> frames = new ArrayList<>();

        // Creates an iterator for the GIF
        ImageInputStream stream = ImageIO.createImageInputStream(new File(gifPath));
        Iterator<ImageReader> readers = ImageIO.getImageReaders(stream);

        if (!readers.hasNext())
            throw new RuntimeException("No reader for: " + gifPath);
        ImageReader reader = readers.next();
        reader.setInput(stream);

        // Loop through each frame
        int numFrames = reader.getNumImages(true);
        for (int i = 0; i < numFrames; i++) {
            // Load the frame
            BufferedImage frame = reader.read(i);
            BufferedImage transparentFrame = new BufferedImage(
                    frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_ARGB);

            // Go through each pixel of the frame and make it transparent if it should be
            for (int y = 0; y < frame.getHeight(); y++) {
                for (int x = 0; x < frame.getWidth(); x++) {
                    int rgba = frame.getRGB(x, y);
                    Color c = new Color(rgba, true);

                    // Replace black with transparent
                    if (c.getRed() == 255 && c.getGreen() == 255 && c.getBlue() == 0) {
                        transparentFrame.setRGB(x, y, 0x00000000);
                    } else {
                        transparentFrame.setRGB(x, y, rgba);
                    }
                }
            }
            // Scale the image to the designated size
            BufferedImage scaledFrame = scaleImage(transparentFrame, scale);

            // Add the frame to the [ArrayList]
            frames.add(scaledFrame);
        }

        // Cleanup
        reader.dispose();
        stream.close();

        // Return list of frames
        return frames;
    }
}
