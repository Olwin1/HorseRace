package GUI.Components.BettingPage;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Primary.Horse;
import Utils.CustomFont;
import Utils.Singletons.HorseInstances;

public class PreviousWinners extends JPanel {
    private static final Font customFont = CustomFont.getFont(14);
    private JPanel listPanel;
    private JPanel positions;

    public PreviousWinners() {
        ///////////////////
        // Create Layout //
        ///////////////////
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create the list that will be the scroll pane
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        // Create a title for the section
        JLabel previousWinnersTitle = new JLabel();
        previousWinnersTitle.setText("Previous Results:");
        previousWinnersTitle.setFont(CustomFont.getFont(15));

        listPanel.add(previousWinnersTitle);

        // Create the Scroll pane wrapping the listPanel
        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // smoother scrolling

        add(scrollPane);

        // Update the positions
        positions = new JPanel();
        positions.setLayout(new BoxLayout(positions, BoxLayout.Y_AXIS));
        // Adding custom panels
        updateResults();

        listPanel.add(positions);

    }

    /**
     * Method to update the positions displayed.
     * Call this when a race result changes
     * (i.e a new race has finished)
     */
    public void updateResults() {
        // Clear the previous entries
        positions.removeAll();

        // Get the current horses and create a new ArrayList of the same size to order
        // the horses into
        ArrayList<Horse> currentHorses = HorseInstances.getInstance().getHorses();
        ArrayList<Horse> orderedWinners = new ArrayList<>(currentHorses.size());
        for (int i = 0; i < currentHorses.size(); i++) {
            orderedWinners.add(null);
        }

        // Loop through each horse and add them to an index corresponding to the
        // positions they achieved
        for (Horse horse : currentHorses) {
            Integer position = horse.getMostRecentPosition();
            if (position == null) {
                continue;
            }
            orderedWinners.add(position - 1, horse);
        }

        // Now loop through the participants in order
        boolean isEmpty = true;
        for (Horse horse : orderedWinners) {
            if (horse == null) {
                continue;
            }
            isEmpty = false;
            JPanel positionEntry = new JPanel();
            positionEntry.setLayout(new BoxLayout(positionEntry, BoxLayout.X_AXIS));

            // Create a text entry displaying the format position. name e.g. 1. HorseName
            JLabel positionText = new JLabel();
            positionText.setFont(customFont);
            positionText.setText(String.format("%s. %s", horse.getMostRecentPosition(), horse.getName()));

            positionEntry.add(positionText);

            positions.add(positionEntry);

        }
        // If the winners array is of size 0 then just put no data.
        if (orderedWinners.size() == 0 || isEmpty) {
            JLabel positionText = new JLabel();
            positionText.setFont(customFont);
            positionText.setText("No data yet.");

            positions.add(positionText);
        }
        positions.revalidate();
        positions.repaint();
    }
}
