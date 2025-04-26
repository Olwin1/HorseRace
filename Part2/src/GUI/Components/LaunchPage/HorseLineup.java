package GUI.Components.LaunchPage;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Primary.Horse;
import Utils.Singletons.HorseInstances;

/**
 * Panel to display the lanes that will be used in the race along with the
 * horses within them.
 */
public class HorseLineup extends JPanel {
    private static HorseLineup instance;
    private JPanel listPanel;

        // Public static method to get the singleton instance
        public static HorseLineup getInstance() {
            if (instance == null) {
                instance = new HorseLineup();
            }
            return instance;
        }

    /**
     * Create an instance of [HorseLineup] is a child of the [JPanel] class and so
     * functions identically.
     */
    private HorseLineup() {
        // Main container panel with vertical layout
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        // Scroll pane wrapping the listPanel
        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // smoother scrolling

        // Adding custom panels
        updateLanes();

        add(scrollPane);
    }

    public void updateLanes() {
        // Remove outdated lanes
        listPanel.removeAll();

        // Re-add all lanes
        ArrayList<Horse> horseLanes = HorseInstances.getInstance().getHorses();
        for (int i = 0; i < horseLanes.size(); i++) {
            HorseInfoTab panel = new HorseInfoTab(i, horseLanes.get(i), () -> updateLanes());
            panel.setAlignmentX(Component.LEFT_ALIGNMENT); // respect box layout
            listPanel.add(panel);
        }

        // Repaint the UI to reflect this new change
        listPanel.revalidate();
        listPanel.repaint();
    }
}
