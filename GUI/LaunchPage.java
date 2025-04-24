package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import GUI.Components.LaunchPage.BottomConfiguration;
import GUI.Components.LaunchPage.HorseLineup;
import GUI.Components.LaunchPage.RightConfiguration;

/**
 * The page displayed just before a race displaying the lane setup and more.
 * To display this page call the static `displayLaunchPage` method.
 */
public class LaunchPage {
    /**
     * Replace the existing page with the LaunchPage
     */
    public static void displayLaunchPage() {
        SwingUtilities.invokeLater(() -> {
            // Get the global frame instance
            JFrame frame = MainMenu.getInstance().getFrame();

            Container container = frame.getContentPane();

            // Remove all existing elements from the container (in this case the start page)
            container.removeAll();
            container.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            // Main Screen Panel
            // This panel will show the lane setup
            JPanel mainPanel = HorseLineup.getInstance();
            // Set to a grid layout so children expand to fill space
            mainPanel.setLayout(new GridLayout(0, 1));
            mainPanel.setBackground(Color.BLACK); // screen look
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            container.add(mainPanel, gbc);

            // Right Betting Panel & horse settings
            JPanel rightPanel = RightConfiguration.getInstance();
            rightPanel.setBackground(Color.LIGHT_GRAY);
            rightPanel.setLayout(new GridLayout(0, 1));
            gbc.gridx = 2;
            gbc.gridy = 1;
            gbc.weightx = 0.5;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            container.add(rightPanel, gbc);

            // Bottom Info Panel
            JPanel bottomPanel = BottomConfiguration.getInstance();
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 3;
            gbc.weightx = 1.0;
            gbc.weighty = 0.2;
            gbc.fill = GridBagConstraints.BOTH;
            container.add(bottomPanel, gbc);

            frame.setVisible(true);
        });
    }
}
