package GUI.Components.BettingPage;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Primary.Horse;
import Primary.User;
import Utils.Singletons.HorseInstances;
import Utils.Singletons.UserInstances;

/**
 * Panel to display the lanes that will be used in the race along with the
 * horses within them.
 */
public class BettingPanel extends JPanel {
    private static BettingPanel instance;
    private JPanel listPanel;

    /// Public static method to get the singleton instance
    public static BettingPanel getInstance() {
        if (instance == null) {
            instance = new BettingPanel();
        }
        return instance;
    }

    /**
     * Create an instance of [BettingPanel] is a child of the [JPanel] class and so
     * functions identically.
     */
    private BettingPanel() {
        /////////////////////////
        /// Add betting users ///
        /////////////////////////

        UserInstances userInst = UserInstances.getInstance();
        if (userInst.getUsers().size() == 0) {
            userInst.addUser(new User());
            userInst.addUser(new User());
            userInst.addUser(new User());
        }

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
            BettingInfoTab panel = new BettingInfoTab(i, horseLanes.get(i), () -> updateLanes());
            panel.setAlignmentX(Component.LEFT_ALIGNMENT); // respect box layout
            listPanel.add(panel);
        }

        // Repaint the UI to reflect this new change
        listPanel.revalidate();
        listPanel.repaint();
    }
}
