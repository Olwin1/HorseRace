package Utils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;

import Primary.HorseColour;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;

/**
 * Enum representing available character colours for sprite selection.
 */
enum Colour {
    blue,
    green,
    purple,
    red
}

/**
 * A custom JPanel that displays animated character sprites based on state.
 * It supports different animations (idle, run, fall) and switches between them
 * dynamically.
 * Change the sprite by calling `setSprite`
 */
public class SpriteSwitcherPanel extends JPanel {
    private final String path = "./Sprites/Horse/";
    private JLabel spriteLabel;
    private AnimatedSprite idleIcon;
    private AnimatedSprite runIcon;
    private AnimatedSprite fallIcon;
    private String fallPath;
    private String saddleGifFallPath;
    private boolean hasSaddle;

    /**
     * Constructs a SpriteSwitcherPanel and initialises sprite resources.
     */
    public SpriteSwitcherPanel(HorseColour horseColour, boolean hasSaddle) {
        this.hasSaddle = hasSaddle;
        setLayout(new BorderLayout());
        setOpaque(false);

        // Construct file path prefix based on colour (default if null)
        String prefix = path + (horseColour != null ? horseColour.toString().toLowerCase()
                : HorseColour.DEFAULT.toString().toLowerCase());
        String saddlePrefix = path + "Accessories/saddle";
        fallPath = prefix + "_fall.gif";
        saddleGifFallPath = saddlePrefix + "_fall.gif";

        // Load sprite animations
        idleIcon = new AnimatedSprite(prefix + "_idle.gif", hasSaddle, saddlePrefix + "_idle.gif");
        runIcon = new AnimatedSprite(prefix + "_run.gif", hasSaddle, saddlePrefix + "_run.gif");
        fallIcon = new AnimatedSprite(fallPath, hasSaddle, saddleGifFallPath);

        // Initialize sprite display label
        spriteLabel = idleIcon;// new JLabel(idleIcon);
        spriteLabel.setOpaque(false);
        spriteLabel.setHorizontalAlignment(JLabel.CENTER);
        add(spriteLabel, BorderLayout.CENTER);
    }

    /**
     * Updates the displayed sprite animation based on character state.
     *
     * @param state The current state of the character (IDLE, RUN, FALL).
     */
    public void setSprite(State state) {
        remove(spriteLabel); // Remove current sprite component

        // Set the new sprite component
        switch (state) {
            case IDLE:
                spriteLabel = idleIcon;
                break;
            case RUN:
                spriteLabel = runIcon;
                break;
            case FALL:
                playFallOnce();
                break;
        }

        // Update the screen
        spriteLabel.setHorizontalAlignment(JLabel.CENTER);
        spriteLabel.setOpaque(false);
        add(spriteLabel, BorderLayout.CENTER);
        revalidate();
        repaint();

        if (state == State.FALL) {
            playFallOnce();
        }
    }

    /**
     * Plays the fall animation once and freezes on the last frame after completion.
     *
     * @param gifPath The path to the fall animation GIF.
     */
    public void playFallOnce() {
        // Replace the current sprite with the fall animation
        remove(spriteLabel);

        spriteLabel = fallIcon;

        spriteLabel.setHorizontalAlignment(JLabel.CENTER);
        spriteLabel.setOpaque(false);
        add(spriteLabel, BorderLayout.CENTER);
        revalidate();
        repaint();

        // Use a timer to replace the animation with the final frame after delay
        Timer freezeTimer = new Timer(1333, e -> {
            Image lastFrame = getLastFrame(fallPath, saddleGifFallPath);
            // Scale the image down to the correct size
            BufferedImage scaledFrame = AnimatedSprite.scaleImage((BufferedImage) lastFrame, 0.35);
            if (lastFrame != null) {

                // Once fall animation is complete replace it with the static image of the horse
                // fallen.
                remove(spriteLabel);
                spriteLabel = new JLabel(new ImageIcon(scaledFrame));

                spriteLabel.setHorizontalAlignment(JLabel.CENTER);
                spriteLabel.setOpaque(false);
                add(spriteLabel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });
        freezeTimer.setRepeats(false);
        freezeTimer.start();
    }

    /**
     * Retrieves the last frame of a GIF animation, optionally overlaying a saddle.
     *
     * @param gifPath       The path to the main GIF file (e.g., horse).
     * @param hasSaddle     Whether a saddle overlay should be applied.
     * @param saddleGifPath The path to the saddle GIF file (optional, required if
     *                      hasSaddle is true).
     * @return The last frame as an Image, or null if unable to retrieve it.
     */
    private Image getLastFrame(String gifPath, String saddleGifPath) {
        try {
            File gifFile = new File(gifPath);
            ImageInputStream stream = ImageIO.createImageInputStream(gifFile);
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("gif");

            if (readers.hasNext()) {
                ImageReader reader = readers.next();
                reader.setInput(stream, false);
                int lastIndex = reader.getNumImages(true) - 1;
                BufferedImage lastFrame = reader.read(lastIndex);

                // Convert yellow pixels to transparent
                BufferedImage transparentFrame = new BufferedImage(
                        lastFrame.getWidth(), lastFrame.getHeight(), BufferedImage.TYPE_INT_ARGB);
                for (int y = 0; y < lastFrame.getHeight(); y++) {
                    for (int x = 0; x < lastFrame.getWidth(); x++) {
                        int rgba = lastFrame.getRGB(x, y);
                        Color c = new Color(rgba, true);
                        if (c.getRed() == 255 && c.getGreen() == 255 && c.getBlue() == 0) {
                            transparentFrame.setRGB(x, y, 0x00000000);
                        } else {
                            transparentFrame.setRGB(x, y, rgba);
                        }
                    }
                }

                // Overlay saddle frame if `hasSaddle` is true and a path is provided
                if (hasSaddle && saddleGifPath != null) {
                    File saddleFile = new File(saddleGifPath);
                    ImageInputStream saddleStream = ImageIO.createImageInputStream(saddleFile);
                    Iterator<ImageReader> saddleReaders = ImageIO.getImageReadersByFormatName("gif");

                    if (saddleReaders.hasNext()) {
                        ImageReader saddleReader = saddleReaders.next();
                        saddleReader.setInput(saddleStream, false);
                        BufferedImage saddleFrame = saddleReader.read(lastIndex);

                        Graphics2D g = transparentFrame.createGraphics();
                        g.drawImage(saddleFrame, 0, 0, null);
                        g.dispose();

                        saddleReader.dispose();
                        saddleStream.close();
                    }
                }

                reader.dispose();
                stream.close();

                return transparentFrame;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
