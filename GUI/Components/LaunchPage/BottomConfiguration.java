package GUI.Components.LaunchPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.Components.CustomInputs.NumberInput;
import GUI.Components.CustomInputs.PixelatedButton;
import Utils.CustomFont;

/**
 * Singleton that can be accessed by `getInstance()`.
 * Will create a custom instance of [JPanel] to be used as the Bottom GUI panel.
 */
public class BottomConfiguration extends JPanel {
    private static BottomConfiguration instance;
    private static final Font customFont = CustomFont.getFont(13);

    // Private constructor to prevent direct instantiation
    private BottomConfiguration() {
        setLayout(new BorderLayout());

        createMainSection();

    }

    private void createMainSection() {

        ///////////////////////////////////////////////
        // Left panel for number of races & distance //
        ///////////////////////////////////////////////
        JPanel leftPanel = new JPanel();

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));

        /// Race count section

        // Create a new panel to wrap the title and input one below the other
        JPanel raceCountPanel = new JPanel();
        raceCountPanel.setLayout(new BoxLayout(raceCountPanel, BoxLayout.Y_AXIS));

        // Create a label to display the text for number of races & add to panel
        JLabel numberOfRaces = new JLabel();
        numberOfRaces.setText("Number of Races: (1-6)");
        numberOfRaces.setFont(customFont);
        raceCountPanel.add(numberOfRaces);

        // Create a custom input that will only accept a range of integers
        NumberInput raceCountInput = new NumberInput(3, 6, 1);
        raceCountInput.setFont(customFont);
        raceCountPanel.add(raceCountInput);

        // Add it to the left panel
        leftPanel.add(raceCountPanel);

        /// Race Distance Section

        // Create a new panel to wrap the title and input one below the other
        JPanel raceDistancePanel = new JPanel();
        raceDistancePanel.setLayout(new BoxLayout(raceDistancePanel, BoxLayout.Y_AXIS));
        raceDistancePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        // Create a label to display the text for distance of races & add to panel
        JLabel raceDistanceLabel = new JLabel();
        raceDistanceLabel.setText("Race Distance (100-1000)");
        raceDistanceLabel.setFont(customFont);
        raceDistancePanel.add(raceDistanceLabel);

        // Create a custom input that will only accept a range of integers
        NumberInput raceDistanceInput = new NumberInput(100, 1000, 100);
        raceDistanceInput.setFont(customFont);
        raceDistancePanel.add(raceDistanceInput);

        // Add it to the left panel
        leftPanel.add(raceDistancePanel);

        // Add the left panel to the rest of the bottom UI
        add(leftPanel, BorderLayout.LINE_START);

        ///////////////////////////////////////
        // Right panel for starting the race //
        ///////////////////////////////////////

        // Create a new Pixelated Button to move to the betting page
        PixelatedButton startRace = new PixelatedButton(16f, new Color(0, 220, 0), new Color(123, 170, 123),
                new Color(0, 43, 0), Color.WHITE);
        startRace.setText("Start Race");

        // Ensure it lines up in the middle
        startRace.setAlignmentX(Component.CENTER_ALIGNMENT);
        raceDistancePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        // Handler for when button is pressed
        startRace.addActionListener(new ActionListener() {

            // When pressed display the race betting page
            @Override
            public void actionPerformed(ActionEvent e) {
                // LaunchPage.displayLaunchPage();
            }

        });
        // Add button on the right of bottom UI
        add(startRace, BorderLayout.LINE_END);
    }

    /// Accessor method to get the singleton instance.  
    public static BottomConfiguration getInstance() {
        if (instance == null) {
            instance = new BottomConfiguration();
        }
        return instance;
    }
}