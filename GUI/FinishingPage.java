package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import GUI.Components.FinishingPage.FinishingPanel;
import GUI.Components.BettingPage.PreviousWinners;

/**
 * The page showing all info regarding betting.
 * To display this page call the static `displayFinishingPage` method.
 */
public class FinishingPage {
    /**
     * Replace the existing page with the FinishingPage
     */
    public static void displayFinshingPage() {
        SwingUtilities.invokeLater(() -> {
            // Get the global frame instance
            JFrame frame = MainMenu.getInstance().getFrame();

            Container container = frame.getContentPane();

            // Remove all existing elements from the container (in this case the start page)
            container.removeAll();
            container.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            // Main Screen Panel
            // This panel will show the horses and results from them
            JPanel mainPanel = FinishingPanel.getInstance();
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
            PreviousWinners rightPanel = new PreviousWinners();
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

            // Add button on the right of bottom UI
            bottomPanel.add(new JPanel(), BorderLayout.LINE_END);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridheight = 1;
            gbc.weightx = 0.25;
            gbc.weighty = 0.05;
            gbc.fill = GridBagConstraints.BOTH;
            container.add(bottomPanel, gbc);

            frame.revalidate();
            frame.repaint();
            frame.setVisible(true);
        });
    }
}
