package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import GUI.Components.FinishingPage.FinishingPanel;
import GUI.Components.FinishingPage.ShowWinnings;
import Utils.CustomFont;
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
            gbc.weighty = 0.75;
            gbc.fill = GridBagConstraints.BOTH;
            container.add(rightPanel, gbc);

            // Bottom Bet results

            // Create a panel to put the bet input and text
            JPanel bettingPanel = new JPanel();
            bettingPanel.setLayout(new BoxLayout(bettingPanel, BoxLayout.Y_AXIS));

            // Create a label for each of the 3 users
            final Font bodyFont = CustomFont.getFont(14);
            ShowWinnings.addWinningsLabel(bodyFont, bettingPanel, 1);
            ShowWinnings.addWinningsLabel(bodyFont, bettingPanel, 2);
            ShowWinnings.addWinningsLabel(bodyFont, bettingPanel, 3);

            /////////////////
            /// Panel Code //
            /////////////////
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridheight = 1;
            gbc.weightx = 0.25;
            gbc.weighty = 0.25;
            gbc.fill = GridBagConstraints.BOTH;
            container.add(bettingPanel, gbc);

            frame.revalidate();
            frame.repaint();
            frame.setVisible(true);
        });
    }
}
