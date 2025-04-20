package Utils;

import java.awt.*;

import Primary.Horse;

public class HorseMover extends SpriteSwitcherPanel {
    private int x; // X position of the panel
    private int y; // Fixed Y position
    private Color color;
    private Horse horse;
    private boolean previousFallenState = false;

    public HorseMover(int startX, int startY, int width, int height, Color color, Horse horse) {
        this.x = startX;
        this.y = startY;
        this.color = color;
        this.horse = horse;
        setOpaque(false); // Make panel transparent so only custom painting shows
        setBounds(x, y, width, height);
    }

    /**
     * Accessor method for horse
     * 
     * @return The [Horse] instance
     */
    public Horse getHorse() {
        return this.horse;
    }

    public boolean getPreviousFallenState() {
        return this.previousFallenState;
    }
    public void setPreviousFallenState() {
        this.previousFallenState = true;
    }

    /**
     * Updates the X position and repaints the panel.
     * 
     * @param newX The new X position.
     */
    public void setXPosition(int newX) {
        this.x = newX;
        setBounds(x, y, this.getWidth(), this.getHeight()); // Update panel position
        repaint();
    }

    // /**
    // * Override to draw a custom component (e.g., colored rectangle).
    // */
    // @Override
    // protected void paintComponent(Graphics g) {
    // super.paintComponent(g);
    // g.fillRect(0, 0, this.getWidth(), this.getHeight()); // Draw at (0, 0)
    // relative to panel
    // }
}
