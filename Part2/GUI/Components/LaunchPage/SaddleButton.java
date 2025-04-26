package GUI.Components.LaunchPage;

import java.awt.Color;

import javax.swing.JButton;

import Primary.Horse;

/**
 * Implementation of [JButton] that flips between has saddle and doesn't have
 * sadle
 */
public class SaddleButton extends JButton {
    private Horse selectedHorse;

    /// Creates a custom instance of [SadleButton]
    public SaddleButton(Horse selectedHorse) {
        this.selectedHorse = selectedHorse;

        // Set the button display to show the default state
        if (selectedHorse.getSaddle()) {
            setEnabled();
        } else {
            setDisabled();
        }

    }

    /// Switch the button state
    public void updateState() {
        updateState(!selectedHorse.getSaddle());
    }

    private void updateState(boolean newState) {
        selectedHorse.setSaddle(newState);
        if (selectedHorse.getSaddle()) {
            setEnabled();
        } else {
            setDisabled();
        }
    }

    private void setEnabled() {
        setText("Has Saddle");
        setBackground(Color.GREEN);
        setForeground(Color.BLACK);
    }

    private void setDisabled() {
        setText("No Saddle");
        setBackground(Color.BLUE);
        setForeground(Color.WHITE);
    }

    /**
     * Accessor method to get the current selected value.
     * 
     * @return The selected [HorseColour] value
     */
    public boolean getSelected() {
        return selectedHorse.getSaddle();
    }
}
