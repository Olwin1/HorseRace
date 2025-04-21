package GUI.Components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Primary.Horse;
import Utils.CustomFont;
import Utils.LoadImageIcon;

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
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Set the background colour to light lavender
        setBackground(new Color(230, 230, 250));

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

        // Create and add a confidence label
        confidenceLabel = new JLabel(
                String.format("Confidence: %s%%", hasHorse ? String.format("%,.1f", horse.getConfidence() * 100) : ""));
        confidenceLabel.setFont(bodyFont);

        topRow.add(confidenceLabel, BorderLayout.LINE_END);

        // Add the row to the rest of the tab
        add(topRow);

        ////////////////////////////////
        // Below is bottom row of tab //
        ////////////////////////////////

        JPanel bottomRow = new JPanel();
        bottomRow.setLayout(new BorderLayout());

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        PixelatedButton delete = new PixelatedButton(14, new Color(255, 0, 0), new Color(140, 0, 0),
                new Color(55, 0, 0), Color.WHITE);
        PixelatedButton moveUp = new PixelatedButton(14, new Color(0, 0, 255), new Color(0, 0, 140),
                new Color(0, 0, 55), Color.WHITE);
        PixelatedButton moveDown = new PixelatedButton(14, new Color(0, 0, 255), new Color(0, 0, 140),
                new Color(0, 0, 55), Color.WHITE);

        moveUp.setIcon(LoadImageIcon.main("move_up"));
        moveDown.setIcon(LoadImageIcon.main("move_down"));
        delete.setIcon(LoadImageIcon.main("delete"));

        moveUp.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        moveDown.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        moveUp.setMaximumSize(new Dimension(16, 16));
        moveDown.setMaximumSize(new Dimension(16, 16));

        delete.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        delete.setMaximumSize(new Dimension(16, 16));

        rightPanel.add(delete, BorderLayout.LINE_END);

        rightPanel.add(moveUp, BorderLayout.LINE_START);
        rightPanel.add(moveDown, BorderLayout.CENTER);

        bottomRow.add(rightPanel, BorderLayout.LINE_END);
        bottomRow.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        add(bottomRow);

        // Expand to full width
        // setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
    }

}
