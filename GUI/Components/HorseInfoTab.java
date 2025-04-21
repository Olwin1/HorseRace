package GUI.Components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

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
                                String.format("Confidence: %s%%",
                                                hasHorse ? String.format("%,.1f", horse.getConfidence() * 100) : ""));
                confidenceLabel.setFont(bodyFont);

                topRow.add(confidenceLabel, BorderLayout.LINE_END);

                // Add the row to the rest of the tab
                add(topRow);

                ////////////////////////////////
                // Below is bottom row of tab //
                ////////////////////////////////

                JPanel bottomRow = new JPanel();
                bottomRow.setLayout(new BorderLayout());

                // Create an inner panel to allow all three buttons to be aligned together on
                // the right
                JPanel rightPanel = new JPanel();
                rightPanel.setLayout(new BorderLayout());

                // Create 3 new buttons for the lane actions.
                PixelatedButton delete = new PixelatedButton(14, new Color(255, 0, 0), new Color(140, 0, 0),
                                new Color(55, 0, 0), Color.WHITE);
                PixelatedButton moveUp = new PixelatedButton(14, new Color(0, 0, 255), new Color(0, 0, 140),
                                new Color(0, 0, 55), Color.WHITE);
                PixelatedButton moveDown = new PixelatedButton(14, new Color(0, 0, 255), new Color(0, 0, 140),
                                new Color(0, 0, 55), Color.WHITE);

                // Set the icons of the buttons
                moveUp.setIcon(LoadImageIcon.main("move_up"));
                moveDown.setIcon(LoadImageIcon.main("move_down"));
                delete.setIcon(LoadImageIcon.main("delete"));

                // Ensure they are correctly spaced and sized
                Border border = BorderFactory.createEmptyBorder(2, 5, 2, 5);
                Dimension dimensions = new Dimension(16, 16);

                moveUp.setBorder(border);
                moveDown.setBorder(border);
                moveUp.setMaximumSize(dimensions);
                moveDown.setMaximumSize(dimensions);

                delete.setBorder(border);
                delete.setMaximumSize(dimensions);

                // Add buttons to the inner panel
                rightPanel.add(delete, BorderLayout.LINE_END);

                rightPanel.add(moveUp, BorderLayout.LINE_START);
                rightPanel.add(moveDown, BorderLayout.CENTER);

                // Add the inner panel to the bottom row and add a bit of padding so the buttons
                // aren't right against the bottom
                bottomRow.add(rightPanel, BorderLayout.LINE_END);
                bottomRow.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

                // Add the bottom row to the tab
                add(bottomRow);
        }

}
