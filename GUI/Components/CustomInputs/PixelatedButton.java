package GUI.Components.CustomInputs;

import javax.swing.*;
import javax.swing.border.Border;

import Utils.CustomFont;

import java.awt.*;
import java.awt.event.*;

/**
 * Custom implementation of a [JButton] to simplify button creation with this
 * default style
 */
public class PixelatedButton extends JButton {
    // These values shouldn't need to be changed
    final static int pixelSize = 3;
    final static int radius = 16;

    /**
     * Creates a custom text button
     * 
     * @param fontSize        The size for the text in the button
     * @param defaultColour   The default background colour
     * @param hoverColour     The background colour to use when the mouse is
     *                        hovering over the button
     * @param onPressedColour The background colour to use when the button is
     *                        pressed
     * @param textColour      The colour to use for the text on the button
     */
    public PixelatedButton(float fontSize, Color defaultColour, Color hoverColour, Color onPressedColour,
            Color textColour) {

        // Set pixel font
        Font font = CustomFont.getFont(fontSize);
        setFont(font);

        // Styling
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setBackground(defaultColour);
        setForeground(textColour);
        setBorder((Border) new PixelBorder());

        // Hover effect & button pressed effect
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColour);
            }

            // Set back to original colour
            public void mouseExited(MouseEvent e) {
                setBackground(defaultColour);
            }

            public void mousePressed(MouseEvent e) {
                setBackground(onPressedColour);
            }

            // Set back to original colour
            public void mouseReleased(MouseEvent e) {
                setBackground(hoverColour);
            }
        });
    }

    // Custom Border Class
    static class PixelBorder implements Border {
        // Required implementations for Border class
        public Insets getBorderInsets(Component c) {
            return new Insets(pixelSize, pixelSize, pixelSize, pixelSize);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        // Paint the custom border - this could be simplified but it is laid out so in
        // future a pixelated border radius could be implemented,
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

            g2.setColor(Color.BLACK); // Border color

            // Top and bottom horizontal lines
            g2.fillRect(x, y, width, pixelSize); // Top
            g2.fillRect(x, y + height - pixelSize, width, pixelSize); // Bottom

            // Left and right vertical lines
            g2.fillRect(x, y, pixelSize, height); // Left
            g2.fillRect(x + width - pixelSize, y, pixelSize, height); // Right

            g2.dispose();
        }
    }
}
