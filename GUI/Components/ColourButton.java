package GUI.Components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Primary.Horse;
import Primary.HorseColour;


/** 
 * Implementation of [JButton] that cycles through the different [HorseColour] values.  
 * Will change the colour of the button to display what colour is selected along with
 * a single character to also show this.  
 */
public class ColourButton extends JButton {
    private HorseColour currentState = HorseColour.DEFAULT;

    /// Creates a custom instance of [ColourButton]
    public ColourButton() {
        // Set the button display to show the default state 
        setText("D");
        setBackground(Horse.parseHorseColour(currentState));
        setForeground(Color.WHITE);


        // Add a listener for on click
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Depending on the current state switch it to the next value along so it cycles.  
                updateState();

            }
        });
    }

    private void updateState() {
        switch (currentState) {
            case BLUE:
                currentState = HorseColour.GREEN;
                setText("G");
                setBackground(Horse.parseHorseColour(currentState));
                break;
            case GREEN:
                currentState = HorseColour.PURPLE;
                setText("P");
                setBackground(Horse.parseHorseColour(currentState));
                break;
            case PURPLE:
                currentState = HorseColour.RED;
                setText("R");
                setBackground(Horse.parseHorseColour(currentState));
                break;
            case RED:
            currentState = HorseColour.DEFAULT;
            setText("D");
            setBackground(Horse.parseHorseColour(currentState));
                break;
            default:
            currentState = HorseColour.BLUE;
            setText("B");
            setBackground(Horse.parseHorseColour(currentState));
                break;
        }
    }

    private void updateState(HorseColour newState) {
        this.currentState = newState;
        switch (currentState) {
            case GREEN:
                currentState = HorseColour.GREEN;
                setText("G");
                setBackground(Horse.parseHorseColour(currentState));
                break;
            case PURPLE:
                currentState = HorseColour.PURPLE;
                setText("P");
                setBackground(Horse.parseHorseColour(currentState));
                break;
            case RED:
                currentState = HorseColour.RED;
                setText("R");
                setBackground(Horse.parseHorseColour(currentState));
                break;
            case DEFAULT:
            currentState = HorseColour.DEFAULT;
            setText("D");
            setBackground(Horse.parseHorseColour(currentState));
                break;
            default:
            currentState = HorseColour.BLUE;
            setText("B");
            setBackground(Horse.parseHorseColour(currentState));
                break;
        }
    }

/**
 * Accessor method to get the current selected value.  
 * @return The selected [HorseColour] value
 */
    public HorseColour getSelected() {
        return this.currentState;
    }

/**
 * Mutator method to set the current selected value.  
 * @param horseColour The new [HorseColour] value
 */
public void setSelected(HorseColour horseColour) {
                updateState(horseColour);
}
}
