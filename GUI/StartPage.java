package GUI;

import javax.swing.*;

import GUI.Components.CustomInputs.PixelatedButton;
import Utils.CustomFont;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Simple page to be displayed on initial open.
 */
public class StartPage {
    /**
     * Will update the screen to the start page upon initialisation.
     */
    public StartPage() {
        SwingUtilities.invokeLater(() -> {
            // Get frame instance
            JFrame frame = MainMenu.getInstance().getFrame();

            Container container = frame.getContentPane();

            // Main Screen
            JPanel jPanel = new JPanel();
            // Set the background colour
            jPanel.setBackground(Color.LIGHT_GRAY);

            // Make a grid so children are centered.
            jPanel.setLayout(new GridBagLayout());

            // Create an inner so the children can be stacked
            JPanel innerJPanel = new JPanel();
            // Set the layout to stack the children
            innerJPanel.setLayout(new BoxLayout(innerJPanel, BoxLayout.Y_AXIS));

            // Create a label and centre align it
            CustomFont label = new CustomFont(24f);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setText("Horse Racer");

            // Add it to the inner panel
            innerJPanel.add(label);

            // Create a new button with the desired colours
            PixelatedButton pixelatedButton = new PixelatedButton(32f, new Color(255, 0, 0), new Color(220, 123, 123),
                    new Color(43, 0, 0), Color.WHITE);
            pixelatedButton.setText("Start Game");

            // Ensure it lines up in the middle
            pixelatedButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Handler for when button is pressed
            pixelatedButton.addActionListener(new ActionListener() {

                // When pressed display the race launch page
                @Override
                public void actionPerformed(ActionEvent e) {
                    LaunchPage.displayLaunchPage();
                }

            });

            // Add it to the inner panel. After the label so it appears below.
            innerJPanel.add(pixelatedButton);

            // Add the inner panel to the main panel and then add the main to the container
            jPanel.add(innerJPanel);
            container.add(jPanel);

            // make the frame visible
            frame.setVisible(true);
        });
    }
}
