package GUI.Components;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Primary.Horse;
import Primary.HorseColour;

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
        for (int i = 0; i < 20; i++) {
            HorseInfoTab panel = new HorseInfoTab(new Horse('0', "Jadzia", 0.5, HorseColour.BLUE));
            panel.setAlignmentX(Component.LEFT_ALIGNMENT); // respect box layout
            listPanel.add(panel);
        }

        add(scrollPane);
    }
}
