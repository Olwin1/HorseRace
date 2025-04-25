package GUI.Components.BettingPage;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.Components.CustomInputs.NumberInput;

/**
 * Simple class to create a betting input for a user and get the number when
 * required.
 */
public class PlaceBet {
    private NumberInput bettingInput;

    /**
     * Add the input section to the provided panel
     * 
     * @param bodyFont     The font to use for all text
     * @param bettingPanel The panel to add the section to
     * @param user         An integer denoting what the user's identifier is
     */
    PlaceBet(Font bodyFont, JPanel bettingPanel, int user) {
        // Create text for specified user
        JLabel bettingUserText = new JLabel();
        bettingUserText.setText(String.format("User %s:", user));
        bettingUserText.setFont(bodyFont);
        bettingPanel.add(bettingUserText);

        // Create text for input of bet
        JLabel bettingInputText = new JLabel();
        bettingInputText.setText("Bet: (£0-£1000)");
        bettingInputText.setFont(bodyFont);
        bettingPanel.add(bettingInputText);

        // Create input for bet
        bettingInput = new NumberInput(0, 1000, 0);
        bettingInput.setMaximumSize(new Dimension(bettingInput.getMaximumSize().width, 30));
        bettingInput.setFont(bodyFont);
        bettingPanel.add(bettingInput);
    }

    /**
     * Get the integer value entered in the bet input
     * 
     * @return an integer
     */
    public int getBet() {
        return bettingInput.getIntegerValue();
    }
}
