package GUI.Components.FinishingPage;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Primary.Horse;
import Primary.User;
import Utils.HorseInstances;
import Utils.UserInstances;

/**
 * Panel to display the lanes that will be used in the race along with the
 * horses within them.
 */
public class FinishingPanel extends JPanel {
    private static FinishingPanel instance;
    private JPanel listPanel;

    /// Public static method to get the singleton instance
    public static FinishingPanel getInstance() {
        if (instance == null) {
            instance = new FinishingPanel();
        }
        return instance;
    }

    /**
     * Create an instance of [BettingPanel] is a child of the [JPanel] class and so
     * functions identically.
     */
    private FinishingPanel() {
        /////////////////////////
        /// Get betting users ///
        /////////////////////////

        UserInstances userInst = UserInstances.getInstance();

        ///////////////////
        // Create layout //
        ///////////////////

        // Main container panel with horizontal layout
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.X_AXIS));

        // Scroll pane wrapping the listPanel
        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16); // smoother scrolling

        // Adding custom panels
        updateLanes();

        add(scrollPane);
    }

    /**
     * Method to update the displayed lanes to the current state of horse instances
     */
    public void updateLanes() {
        // Remove outdated lanes
        listPanel.removeAll();

        // Re-add all lanes
        ArrayList<Horse> horseLanes = HorseInstances.getInstance().getHorses();
        for (int i = 0; i < horseLanes.size(); i++) {
            FinishingInfoTab panel = new FinishingInfoTab(i, horseLanes.get(i));
            panel.setAlignmentX(Component.LEFT_ALIGNMENT); // respect box layout
            listPanel.add(panel);
        }

        // Repaint the UI to reflect this new change
        listPanel.revalidate();
        listPanel.repaint();
    }
}
