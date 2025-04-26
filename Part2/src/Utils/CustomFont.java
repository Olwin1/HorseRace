package Utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;

/**
 * Custom implementation of [JLabel] to allow for custom font styles to match
 * the application.
 */
public class CustomFont extends JLabel {
    /**
     * Creates an instance of [JLabel] with the custom font pre-applied.
     * 
     * @param fontSize The fontSize for the label. E.g. 24f
     */
    public CustomFont(float fontSize) {
        Font font;

        font = getFont(fontSize);
        setFont(font);
    }

    /**
     * Static implementation of `getFont` to allow for other components to apply the
     * custom font.
     * 
     * @param size The font size to return the font instance as. e.g: 24f
     */
    public static Font getFont(float size) {
        try {
            // Load font from file with provided font size.
            return Font.createFont(Font.TRUETYPE_FONT, new File(CustomFont.class.getResource("/Sprites/Fonts/PixelFont.ttf").getPath()))
                    .deriveFont(size);
        } catch (FontFormatException | IOException e) {
            // If for whatever reason the font cannot be loaded then return a default font.
            return new Font("Monospace", Font.PLAIN, 12);
        }
    }
}