package Utils;


import java.awt.*;

public class HorseMover extends SpriteSwitcherPanel {
    private int x; // X position of the panel
    private int y; // Fixed Y position
    private Color color;

    public HorseMover(int startX, int startY, int width, int height, Color color) {
        this.x = startX;
        this.y = startY;
        this.color = color;
        setOpaque(false); // Make panel transparent so only custom painting shows
        setBounds(x, y, width, height);
    }


    /**
     * Updates the X position and repaints the panel.
     * @param newX The new X position.
     */
    public void setXPosition(int newX) {
        this.x = newX;
        setBounds(x, y, this.getWidth(), this.getHeight()); // Update panel position
        repaint();
    }

    // /**
    //  * Override to draw a custom component (e.g., colored rectangle).
    //  */
    // @Override
    // protected void paintComponent(Graphics g) {
    //     super.paintComponent(g);
    //     g.fillRect(0, 0, this.getWidth(), this.getHeight()); // Draw at (0, 0) relative to panel
    // }
}
