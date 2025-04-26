package GUI.Components.FinishingPage;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Primary.User;
import Utils.UserInstances;

/**
 * This class is responsible for displaying the winnings for a specific user 
 * on the betting panel in the GUI.
 */
public class ShowWinnings {

    /**
     * Adds a label displaying the user's winnings to the betting panel.
     * 
     * @param bodyFont The font to be used for the label text.
     * @param bettingPanel The panel where the label will be added.
     * @param user The identifier of the user whose winnings are to be displayed.
     */
    public static void addWinningsLabel(Font bodyFont, JPanel bettingPanel, int user) {
            // Create a new JLabel
        JLabel winningsLabel = new JLabel();
        
        // Set the font for the label text
        winningsLabel.setFont(bodyFont);
        
        // Get the selected user based on the 1-based index provided
        // The user index is adjusted by subtracting 1 since the list is 0-based
        User selectedUser = UserInstances.getInstance().getUsers().get(user - 1);
        
        // Calculate the user's total balance (winnings)
        double winningsAmount = selectedUser.totalBalance();
        
        // Set the label text to display the user's ID and formatted winnings amount
        // The amount is formatted with commas and 2 decimal places
        winningsLabel.setText(String.format("User %s: £%,.2f", user, winningsAmount));

        // Add the winnings label to the betting panel
        bettingPanel.add(winningsLabel);
    }
}
