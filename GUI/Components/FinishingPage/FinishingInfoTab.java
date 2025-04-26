package GUI.Components.FinishingPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Primary.Horse;
import Utils.CustomFont;
import Utils.SpriteSwitcherPanel;

/**
 * Definition for a tab to display various different configuration options &
 * info for a horse
 */
public class FinishingInfoTab extends JPanel {
    private JLabel nameLabel;
    private JLabel symbolLabel;

    private static final Color backgroundColour = new Color(230, 230, 250);

    private static Font defaultFont = CustomFont.getFont(18f);
    private static Font bodyFont = CustomFont.getFont(14f);

    /**
     * Will create a tab to be used in a scroll view.
     * 
     * @param horse takes a [Horse] instance or `null` if the horse in that
     *              lane is
     *              currently unknown.
     */
    public FinishingInfoTab(int i, Horse horse) {

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
        // Create and add a graph label //
        ///////////////////////////////////////
        JPanel historyPanel = new JPanel();
        historyPanel.setLayout(new BorderLayout());

        JLabel historyLabel = new JLabel(
                "Pos History: (Y: Position)");
        historyLabel.setFont(bodyFont);
        historyLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        historyPanel.add(historyLabel, BorderLayout.PAGE_START);

        historyPanel.add(new VictoryGraph(horse), BorderLayout.CENTER);
        add(historyPanel);

        /////////////////////////////////////////
        // Below is betting section of the tab //
        /////////////////////////////////////////

        JPanel bottomRow = new JPanel();
        bottomRow.setLayout(new BoxLayout(bottomRow, BoxLayout.X_AXIS));

        // Create a panel to put the bet input and text
        JPanel bettingPanel = new JPanel();
        bettingPanel.setLayout(new BoxLayout(bettingPanel, BoxLayout.Y_AXIS));

        // Create a label for each of the 3 users
        ShowWinnings.addWinningsLabel(bodyFont, bettingPanel, 1);
        ShowWinnings.addWinningsLabel(bodyFont, bettingPanel, 2);
        ShowWinnings.addWinningsLabel(bodyFont, bettingPanel, 3);

        bottomRow.add(bettingPanel);
        bottomRow.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        // Add the bottom row to the tab
        add(bottomRow);

    }

}
