package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import GUI.Components.BettingPage.BettingPanel;
import GUI.Components.CustomInputs.PixelatedButton;

/**
 * The page showing all info regarding betting.
 * To display this page call the static `displayBettingPage` method.
 */
public class BettingPage {
    /**
     * Replace the existing page with the BettingPage
     */
    public static void displayBettingPage() {
        SwingUtilities.invokeLater(() -> {
            // Get the global frame instance
            JFrame frame = MainMenu.getInstance().getFrame();

            Container container = frame.getContentPane();

            // Remove all existing elements from the container (in this case the start page)
            container.removeAll();
            container.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            // Main Screen Panel
            // This panel will show the horses and betting for them
            JPanel mainPanel = BettingPanel.getInstance();
            // Set to a grid layout so children expand to fill space
            mainPanel.setLayout(new GridLayout(0, 1));
            mainPanel.setBackground(Color.BLACK); // screen look
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridheight = 2;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            container.add(mainPanel, gbc);

            // Right Previous winners Panel
            JPanel rightPanel = new JPanel();
            rightPanel.setBackground(Color.LIGHT_GRAY);
            rightPanel.setLayout(new GridLayout(0, 1));
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridheight = 1;
            gbc.weightx = 0.25;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            container.add(rightPanel, gbc);

            // Bottom Launch Race Panel
            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new BorderLayout());

            /////////////////
            /// Panel Code //
            /////////////////
            // Create a new Pixelated Button to move to the betting page
            PixelatedButton startRace = new PixelatedButton(16f, new Color(0, 220, 0), new Color(123, 170, 123),
                    new Color(0, 43, 0), Color.WHITE);
            startRace.setText("Start Race");

            // Ensure it lines up in the middle
            startRace.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Handler for when button is pressed
            startRace.addActionListener(new ActionListener() {

                // When pressed display the race betting page
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Start pressed");
                }

            });

            // Add button on the right of bottom UI
            bottomPanel.add(startRace, BorderLayout.LINE_END);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridheight = 1;
            gbc.weightx = 0.25;
            gbc.weighty = 0.05;
            gbc.fill = GridBagConstraints.BOTH;
            container.add(bottomPanel, gbc);

            frame.setVisible(true);
        });
    }
}
