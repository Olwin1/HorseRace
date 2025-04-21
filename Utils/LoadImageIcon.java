package Utils;

import java.io.File;

import javax.swing.ImageIcon;

/**
 * Simple helper class to load an image icon
 */
public class LoadImageIcon {
    public static ImageIcon main(String iconName) {
        // Load the desired image file
        File imageFile = new File(String.format("./Sprites/Icons/%s.png", iconName));

        // Create an ImageIcon instance from this image
        ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());

        // Return to caller
        return icon;
    }
}
