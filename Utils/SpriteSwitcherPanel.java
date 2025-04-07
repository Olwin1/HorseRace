package Utils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
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
 * It supports different animations (idle, run, fall) and switches between them dynamically.
 * Change the sprite by calling `setSprite`
 */
public class SpriteSwitcherPanel extends JPanel {
    private final String path = "./Sprites/";
    private Colour colour;
    private JLabel spriteLabel;
    private ImageIcon idleIcon;
    private ImageIcon runIcon;
    private ImageIcon fallIcon;
    private String fallPath;

    /**
     * Constructs a SpriteSwitcherPanel and initialises sprite resources.
     */
    public SpriteSwitcherPanel() {
        setLayout(new BorderLayout());

        // Construct file path prefix based on colour (default if null)
        String prefix = path + (colour != null ? colour.name() : "default");
        fallPath = prefix + "_fall.gif";

        // Load sprite animations
        idleIcon = new ImageIcon(prefix + "_idle.gif");
        runIcon = new ImageIcon(prefix + "_run.gif");
        fallIcon = new ImageIcon(fallPath);

        // Initialize sprite display label
        spriteLabel = new JLabel(idleIcon);
        spriteLabel.setHorizontalAlignment(JLabel.CENTER);
        add(spriteLabel, BorderLayout.CENTER);
    }

    /**
     * Updates the displayed sprite animation based on character state.
     *
     * @param state The current state of the character (IDLE, RUN, FALL).
     */
    public void setSprite(State state) {
        switch (state) {
            case State.IDLE:
                spriteLabel.setIcon(idleIcon);
                break;
            case State.RUN:
                spriteLabel.setIcon(runIcon);
                break;
            case State.FALL:
                playFallOnce(fallPath);
                break;
        }
    }

    /**
     * Plays the fall animation once and freezes on the last frame after completion.
     *
     * @param gifPath The path to the fall animation GIF.
     */
    private void playFallOnce(String gifPath) {
        spriteLabel.setIcon(fallIcon);

        // Use a timer to replace the animation with the final frame after delay
        Timer freezeTimer = new Timer(1333, e -> {
            Image lastFrame = getLastFrame(gifPath);
            if (lastFrame != null) {
                spriteLabel.setIcon(new ImageIcon(lastFrame));
            }
        });
        freezeTimer.setRepeats(false);
        freezeTimer.start();
    }

    /**
     * Retrieves the last frame of a GIF animation.
     *
     * @param gifPath The path to the GIF file.
     * @return The last frame as an Image, or null if unable to retrieve it.
     */
    private Image getLastFrame(String gifPath) {
        try {
            File gifFile = new File(gifPath);
            ImageInputStream stream = ImageIO.createImageInputStream(gifFile);
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("gif");

            if (readers.hasNext()) {
                ImageReader reader = readers.next();
                reader.setInput(stream, false);
                int lastIndex = reader.getNumImages(true) - 1;
                BufferedImage lastFrame = reader.read(lastIndex);
                reader.dispose();
                return lastFrame;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
