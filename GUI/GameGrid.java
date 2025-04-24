package GUI;

import javax.swing.*;

import Utils.GameFrame;

import java.awt.*;
import Utils.Sky;

/**
 * Simple grid class to create a grid display for during the race.  
 */
public class GameGrid {
    private GameFrame screenPanel;
    private Sky customSky;
    public GameGrid(int raceDistance, Sky customSky) {
        this.customSky = customSky;
        SwingUtilities.invokeLater(() -> {
            JFrame frame = MainMenu.getInstance().getFrame();

            Container container = frame.getContentPane();
            container.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            // Central Screen Panel
            // This panel will show the race itself
            screenPanel = new GameFrame(raceDistance, customSky);
            screenPanel.setBackground(Color.BLACK); // screen look
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            container.add(screenPanel, gbc);

            // Left Info Panel
            JPanel leftPanel = new JPanel();
            leftPanel.setBackground(Color.LIGHT_GRAY);
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.weightx = 0.2;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            container.add(leftPanel, gbc);

            // Right Info Panel
            JPanel rightPanel = new JPanel();
            rightPanel.setBackground(Color.LIGHT_GRAY);
            gbc.gridx = 2;
            gbc.gridy = 1;
            gbc.weightx = 0.2;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            container.add(rightPanel, gbc);

            // Bottom Info Panel
            JPanel bottomPanel = new JPanel();
            bottomPanel.setBackground(Color.GRAY);
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

    public GameFrame getGameFrame() {
        return this.screenPanel;
    }

    public Sky getTrackWeather() {
        return this.customSky;
    }
}
