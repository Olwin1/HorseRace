package GUI;

import javax.swing.*;

import Utils.GameFrame;
import Utils.Pair;

import java.awt.*;
import Utils.Sky;

/**
 * Simple grid class to create a grid display for during the race.
 */
public class GameGrid {
    private static GameGrid _instance;
    private GameFrame screenPanel;
    private int raceDistance;
    private int raceCount;

    public GameGrid(int raceDistance, int raceCount) {
        this.raceDistance = raceDistance;
        this.raceCount = raceCount;
    }

    public void showGameGrid(Sky customSky, Runnable onUIReady) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = MainMenu.getInstance().getFrame();

            Container container = frame.getContentPane();
            container.removeAll();
            
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

            frame.validate();
            // refresh to actually show changes
            frame.repaint();
            frame.setVisible(true);

            if (onUIReady != null) {
                onUIReady.run(); // NOW start the race
            }
        });
    }

    public GameFrame getGameFrame() {
        return this.screenPanel;
    }

    /**
     * Get the number of races to run
     * 
     * @return
     */
    public int getRaceCount() {
        return this.raceCount;
    }

    /**
     * Decrease the number of races left by one
     */
    public void decreaseRaceCount() {
        this.raceCount--;
    }

    /**
     * Get gamegrid instance
     * if options are provided then create a new instance
     * 
     * @param raceOptions takes a Pair of Integers (raceDistance, raceCount)
     * @return [GameGrid] instance
     */
    public static GameGrid getInstance(Pair<Integer, Integer> raceOptions) {
        if (raceOptions != null) {
            _instance = new GameGrid(raceOptions.getFirst(), raceOptions.getSecond());
        } else if (_instance == null) {
            _instance = new GameGrid(100, 1);
        }

        return _instance;
    }

    /**
     * Get the distance of the race
     * 
     * @return
     */
    public int getDistance() {
        return this.raceDistance;
    }
}
