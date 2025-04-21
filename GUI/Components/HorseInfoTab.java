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
    private static Font defaultFont = CustomFont.getFont(18f);

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

        // Create and add a name label
        nameLabel = new JLabel(hasHorse ? horse.getName() : "empty");
        nameLabel.setFont(defaultFont);

        add(nameLabel, BorderLayout.CENTER);

        // Expand to full width
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
    }

}
