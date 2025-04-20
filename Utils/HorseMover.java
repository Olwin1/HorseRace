package Utils;


import Primary.Horse;

public class HorseMover extends SpriteSwitcherPanel {
    private int x; // X position of the panel
    private int y; // Fixed Y position
    private Horse horse;
    private boolean previousFallenState = false;

    public HorseMover(int startX, int startY, int width, int height, Horse horse) {
        super(horse.getColour());
        this.x = startX;
        this.y = startY;
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
}
