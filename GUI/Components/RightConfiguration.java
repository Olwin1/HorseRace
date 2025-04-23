package GUI.Components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Primary.Horse;
import Primary.HorseColour;
import Utils.CustomFont;
import Utils.HorseInstances;

/**
 * Singleton that can be accessed by `getInstance()`.
 * Will create a custom instance of [JPanel] to be used as the Right GUI panel.
 */
public class RightConfiguration extends JPanel {
    private static RightConfiguration instance;
    private Integer selection;
    private static final Font customFont = CustomFont.getFont(13);
    JPanel listPanel;

    // Private constructor to prevent direct instantiation
    private RightConfiguration() {
        setLayout(new BorderLayout());
        // Main container panel with vertical layout
        listPanel = new JPanel();
        listPanel.setBackground(Color.CYAN);

        listPanel.setLayout(new BorderLayout());

        // Scroll pane wrapping the listPanel
        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // smoother scrolling

        updateSelection(selection);

        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Update the GUI to show the info of the selected index
     * 
     * @param i index of selected lane
     */
    public void updateSelection(Integer i) {
        this.selection = i;
        listPanel.removeAll();

        HorseInstances horseInstances = HorseInstances.getInstance();
        Horse currentHorse = selection != null ? horseInstances.getHorses().get(selection) : null;
        JPanel mainPanel = new JPanel();

        if (currentHorse == null) {
            // Create a new pixelated button to allow for the addition of a new horse
            PixelatedButton pixelatedButton = new PixelatedButton(16f, new Color(255, 0, 0), new Color(220, 123, 123),
                    new Color(43, 0, 0), Color.WHITE);
            // Set the text in the button
            pixelatedButton.setText("Add horse");
            // Make the button centred
            pixelatedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            // Set the button size
            pixelatedButton.setMaximumSize(new Dimension(100, 100));

            // Add an onClick listener
            pixelatedButton.addActionListener(e -> {
                // Calculate the horse lane that it should add
                Integer horseLane = selection != null ? selection : horseInstances.getHorses().size() + 1;
                // Create a new empty [Horse] instance and add it to the lanes
                horseInstances.addHorse(new Horse('X', "Unnamed horse", 0f, HorseColour.DEFAULT), horseLane);
                // call self with the new lane to update the GUI to reflect the change
                updateSelection(horseLane - 1);
            });

            // Add to the UI
            mainPanel.add(pixelatedButton, BorderLayout.CENTER);
            listPanel.add(mainPanel, BorderLayout.CENTER);

        } else {
            // Set the layout so it stacks downwards
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

            /////////////////////////////
            // Character input section //
            /////////////////////////////

            // Create a new panel for the section
            JPanel characterPanel = new JPanel();
            characterPanel.setLayout(new BoxLayout(characterPanel, BoxLayout.Y_AXIS));

            // Create a new label
            JLabel characterInputTitle = new JLabel();
            // Set the text to match the section
            characterInputTitle.setText("Character");
            characterInputTitle.setFont(customFont);

            // Create a single character input
            SingleCharInput characterInput = new SingleCharInput();
            // Set the max size so it isn't bigger than it needs to be
            characterInput.setMaximumSize(new Dimension(35, 25));
            // Set the font to match everything else
            characterInput.setFont(customFont);

            // Set the existing value to match the horse
            characterInput.setText(Character.toString(currentHorse.getSymbol()));

            // Add to the section panel
            characterPanel.add(characterInputTitle);
            characterPanel.add(characterInput);
            // Add to the main panel
            mainPanel.add(characterPanel);

            ////////////////////////
            // Horse name section //
            ////////////////////////

            // Create a new panel for the section
            JPanel horseNamePanel = new JPanel();
            horseNamePanel.setLayout(new BoxLayout(horseNamePanel, BoxLayout.Y_AXIS));

            JLabel horseNameTitle = new JLabel();
            horseNameTitle.setText("Horse Name");
            horseNameTitle.setFont(customFont);
            JTextField horseName = new JTextField(13); // give it a preferred size
            horseName.setMaximumSize(new Dimension(horseName.getMaximumSize().width, 25));
            horseName.setFont(customFont);

            // Set the existing value to match the horse
            horseName.setText(currentHorse.getName());

            // Add to the section panel
            characterPanel.add(horseNameTitle);
            characterPanel.add(horseName);

            // Add to the main panel
            mainPanel.add(horseNamePanel);

            //////////////////////////
            // Horse colour section //
            //////////////////////////

            // Create a new panel for the section
            JPanel horseColourPanel = new JPanel();
            horseColourPanel.setLayout(new BoxLayout(horseColourPanel, BoxLayout.Y_AXIS));

            JLabel horseColourTitle = new JLabel();
            horseColourTitle.setText("Horse Colour");
            horseColourTitle.setFont(customFont);
            ColourButton horseColour = new ColourButton();

            horseColour.setFont(customFont);

            // Set the existing value to match the horse
            horseColour.setSelected(currentHorse.getColour());

            // Add to the section panel
            horseColourPanel.add(horseColourTitle);
            horseColourPanel.add(horseColour);

            // Add to the main panel
            mainPanel.add(horseColourPanel);

            /////////////////////////////////
            // Horse settings save section //
            /////////////////////////////////

            // Create a new panel for the section
            JPanel horseSavePanel = new JPanel();
            horseSavePanel.setLayout(new BoxLayout(horseSavePanel, BoxLayout.Y_AXIS));

            JLabel horseSaveTitle = new JLabel();
            horseSaveTitle.setText("Save horse settings");
            horseSaveTitle.setFont(customFont);

            PixelatedButton horseSaveButton = new PixelatedButton(16f, new Color(0, 150, 0), new Color(123, 220, 123),
                    new Color(0, 43, 0), Color.WHITE);
            horseSaveButton.setText("Save");

            horseSaveButton.addActionListener(new ActionListener() {

                // When pressed move the horse down
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentHorse.setSymbol(characterInput.getText().charAt(0));
                    currentHorse.setName(horseName.getText());
                    currentHorse.setColour(horseColour.getSelected());
                    HorseLineup.getInstance().updateLanes();
                }

            });

            // Add to the section panel
            horseSavePanel.add(horseSaveTitle);
            horseSavePanel.add(horseSaveButton);

            // Add to the main panel
            mainPanel.add(horseSavePanel);

            //////////////////////////////
            // Horse accessories section //
            //////////////////////////////

            // Create a new panel for the section
            JPanel horseAccessoriesPanel = new JPanel();
            horseAccessoriesPanel.setLayout(new BoxLayout(horseAccessoriesPanel, BoxLayout.Y_AXIS));

            JLabel horseAccessoriesTitle = new JLabel();
            horseAccessoriesTitle.setText("Horse Confidence");
            horseAccessoriesTitle.setFont(customFont);

            SaddleButton saddleButton = new SaddleButton(currentHorse);
            saddleButton.setFont(customFont);

            // Declare horse confidence now so it can be updated in listener
            JLabel horseConfidence = new JLabel();

            // Add a listener for on click
            saddleButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Depending on the current state switch it to the next value along so it
                    // cycles.
                    saddleButton.updateState();
System.out.println(String.format("%,.1f%%", currentHorse.getConfidence() * 100));
                    horseConfidence.setText(String.format("%,.1f%%", currentHorse.getConfidence() * 100));
                    horseConfidence.revalidate();
                    horseConfidence.repaint();
                }
            });

            // Add to the section panel
            horseAccessoriesPanel.add(horseAccessoriesTitle);
            horseAccessoriesPanel.add(saddleButton);

            horseAccessoriesPanel.setBorder(BorderFactory.createEmptyBorder(55, 0, 0, 0));

            // Add to the main panel
            mainPanel.add(horseAccessoriesPanel);

            //////////////////////////////
            // Horse confidence section //
            //////////////////////////////

            // Create a new panel for the section
            JPanel horseConfidencePanel = new JPanel();
            horseConfidencePanel.setLayout(new BoxLayout(horseConfidencePanel, BoxLayout.Y_AXIS));

            JLabel horseConfidenceTitle = new JLabel();
            horseConfidenceTitle.setText("Horse Confidence");
            horseConfidenceTitle.setFont(customFont);

            horseConfidence.setFont(customFont);

            // Set the value to match the horse
            horseConfidence.setText(String.format("%,.1f%%", currentHorse.getConfidence() * 100));

            // Add to the section panel
            horseConfidencePanel.add(horseConfidenceTitle);
            horseConfidencePanel.add(horseConfidence);

            horseConfidencePanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

            // Add to the main panel
            mainPanel.add(horseConfidencePanel);

            // Add all the sections to the listPanel
            listPanel.add(mainPanel, BorderLayout.CENTER);
        }

        // Update the panel on the screen
        listPanel.revalidate();
        listPanel.repaint();

        // Notify the main section on the screen
        HorseLineup.getInstance().updateLanes();
    }

    /// Accessor method to get the singleton instance.  
    public static RightConfiguration getInstance() {
        if (instance == null) {
            instance = new RightConfiguration();
        }
        return instance;
    }
}