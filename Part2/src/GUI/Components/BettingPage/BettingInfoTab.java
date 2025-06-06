package GUI.Components.BettingPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Primary.Horse;
import Utils.CustomFont;
import Utils.Sprite.SpriteSwitcherPanel;

/**
 * Definition for a tab to display various different configuration options &
 * info for a horse
 */
public class BettingInfoTab extends JPanel {
    private JLabel nameLabel;
    private JLabel symbolLabel;
    private JLabel confidenceLabel;
    private Horse horse;

    private PlaceBet userOneBet;
    private PlaceBet userTwoBet;
    private PlaceBet userThreeBet;

    private static final Color backgroundColour = new Color(230, 230, 250);

    private static Font defaultFont = CustomFont.getFont(18f);
    private static Font bodyFont = CustomFont.getFont(14f);

    private static ArrayList<BettingInfoTab> _instances = new ArrayList<>();

    /**
     * Will create a tab to be used in a scroll view.
     * 
     * @param horse       takes a [Horse] instance or `null` if the horse in that
     *                    lane is
     *                    currently unknown.
     * @param updateLanes a runnable to be called whenever data is changed that will
     *                    update the entire list.
     */
    public BettingInfoTab(int i, Horse horse, Runnable updateLanes) {
        // Register a new instance
        _instances.add(this);

        this.horse = horse;

        // Check if a horse has been provided
        boolean hasHorse = horse != null;

        // Ensure expands correctly
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Set the background colour to light lavender
        setBackground(backgroundColour);

        /////////////////////////////////////
        // Below is the top row of the tab //
        /////////////////////////////////////

        JPanel topRow = new JPanel();
        topRow.setLayout(new BorderLayout());

        // Create and add a symbol label
        symbolLabel = new JLabel(hasHorse ? Character.toString(horse.getSymbol()) : "");
        symbolLabel.setFont(defaultFont);
        // Change the colour of the symbol depending on what colour the horse is.
        symbolLabel.setForeground(hasHorse ? Horse.parseHorseColour(horse.getColour()) : Color.WHITE);
        symbolLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        topRow.add(symbolLabel, BorderLayout.LINE_START);

        // Create and add a name label
        nameLabel = new JLabel(hasHorse ? horse.getName() : "empty");
        nameLabel.setFont(defaultFont);

        topRow.add(nameLabel, BorderLayout.CENTER);

        // Add the row to the rest of the tab
        add(topRow);
        //////////////////////////////////////////
        // Below is the main section of the tab //
        //////////////////////////////////////////

        JPanel mainSection = new SpriteSwitcherPanel(horse.getColour(), horse.getSaddle());
        mainSection.setLayout(new GridLayout(0, 1));
        mainSection.setOpaque(true);
        mainSection.setBackground(Color.DARK_GRAY);
        mainSection.setPreferredSize(
                new Dimension(mainSection.getPreferredSize().width, mainSection.getPreferredSize().width));

        // Add the row to the rest of the tab
        add(mainSection);

        ///////////////////////////////////////
        // Create and add a confidence label //
        ///////////////////////////////////////
        JPanel confidencePanel = new JPanel();
        confidencePanel.setLayout(new BoxLayout(confidencePanel, BoxLayout.X_AXIS));

        confidenceLabel = new JLabel(
                String.format("Confidence: %s%%",
                        hasHorse ? String.format("%,.1f", horse.getConfidence() * 100) : ""));
        confidenceLabel.setFont(bodyFont);
        confidenceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        confidencePanel.add(confidenceLabel);
        add(confidencePanel);

        /////////////////////////////////////////
        // Below is betting section of the tab //
        /////////////////////////////////////////

        JPanel bottomRow = new JPanel();
        bottomRow.setLayout(new BoxLayout(bottomRow, BoxLayout.X_AXIS));

        // Create a panel to put the bet input and text
        JPanel bettingPanel = new JPanel();
        bettingPanel.setLayout(new BoxLayout(bettingPanel, BoxLayout.Y_AXIS));

        // Create a betting input for 3 users
        userOneBet = new PlaceBet(bodyFont, bettingPanel, 1);
        userTwoBet = new PlaceBet(bodyFont, bettingPanel, 2);
        userThreeBet = new PlaceBet(bodyFont, bettingPanel, 3);

        // Add bettingPanel to the bottom row
        bottomRow.add(bettingPanel, BorderLayout.LINE_START);

        bottomRow.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        // Add the bottom row to the tab
        add(bottomRow);

    }

    /**
     * Gets an array of bets placed by each user on a horse
     * 
     * @return
     */
    public ArrayList<Integer> getBet() {
        ArrayList<Integer> bets = new ArrayList<>();
        bets.add(userOneBet.getBet());
        bets.add(userTwoBet.getBet());
        bets.add(userThreeBet.getBet());
        return bets;
    }

    public static ArrayList<BettingInfoTab> getInstances() {
        return _instances;
    }

    public Horse getHorse() {
        return horse;
    }

}
