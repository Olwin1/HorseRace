package GUI.Components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Primary.Horse;
import Utils.CustomFont;

/**
 * Definition for a tab to display various different configuration options &
 * info for a horse
 */
public class HorseInfoTab extends JPanel {
    private JLabel nameLabel;
    private JLabel symbolLabel;
    private JLabel confidenceLabel;

    private static Font defaultFont = CustomFont.getFont(18f);
    private static Font bodyFont = CustomFont.getFont(14f);

    /**
     * Will create a tab to be used in a scroll view.
     * 
     * @param horse takes a [Horse] instance or `null` if the horse in that lane is
     *              currently unknown.
     */
    public HorseInfoTab(Horse horse) {
        // Check if a horse has been provided
        boolean hasHorse = horse != null;

        // Ensure expands correctly
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Set the background colour to light lavender
        setBackground(new Color(230, 230, 250));

        // Create and add a symbol label
        symbolLabel = new JLabel(hasHorse ? Character.toString(horse.getSymbol()) : "");
        symbolLabel.setFont(defaultFont);
        // Change the colour of the symbol depending on what colour the horse is.
        symbolLabel.setForeground(hasHorse ? Horse.parseHorseColour(horse.getColour()) : Color.WHITE);
        symbolLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(symbolLabel, BorderLayout.LINE_START);

        // Create and add a name label
        nameLabel = new JLabel(hasHorse ? horse.getName() : "empty");
        nameLabel.setFont(defaultFont);

        add(nameLabel, BorderLayout.CENTER);

        // Create and add a confidence label
        confidenceLabel = new JLabel(
                String.format("Confidence: %s%%", hasHorse ? String.format("%,.1f", horse.getConfidence() * 100) : ""));
                confidenceLabel.setFont(bodyFont);

        add(confidenceLabel, BorderLayout.LINE_END);

        // Expand to full width
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
    }

}
