package GUI.Components;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Primary.Horse;
import Primary.HorseColour;
import Utils.HorseInstances;

/**
 * Panel to display the lanes that will be used in the race along with the
 * horses within them.
 */
public class HorseLineup extends JPanel {
    /**
     * Create an instance of [HorseLineup] is a child of the [JPanel] class and so
     * functions identically.
     */
    public HorseLineup() {
        // Main container panel with vertical layout
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        // Scroll pane wrapping the listPanel
        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // smoother scrolling

        // Adding custom panels
        updateLanes(listPanel);

        add(scrollPane);
    }

    private void updateLanes(JPanel listPanel) {
        // Remove outdated lanes
        listPanel.removeAll();

        // Re-add all lanes
        ArrayList<Horse> horseLanes = HorseInstances.getInstance().getHorses();
        for (int i = 0; i < horseLanes.size(); i++) {
            HorseInfoTab panel = new HorseInfoTab(i, horseLanes.get(i), () -> updateLanes(listPanel));
            panel.setAlignmentX(Component.LEFT_ALIGNMENT); // respect box layout
            listPanel.add(panel);
        }

        // Repaint the UI to reflect this new change
        listPanel.revalidate();
        listPanel.repaint();
    }
}
