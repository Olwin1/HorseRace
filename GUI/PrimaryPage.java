package GUI;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PrimaryPage {
    public static void main(String[] args) {
        JFrame frame = MainMenu.getInstance().getFrame();
                // Create a JPanel with BorderLayout
        JPanel panel = new JPanel(new BorderLayout());

        // Add buttons to different regions
        panel.add(new JButton("North Button"), BorderLayout.NORTH);
        panel.add(new JButton("South Button"), BorderLayout.SOUTH);
        panel.add(new JButton("East Button"), BorderLayout.EAST);
        panel.add(new JButton("West Button"), BorderLayout.WEST);
        panel.add(new JButton("Center Button"), BorderLayout.CENTER);

        // Add the panel to the frame
        frame.add(panel);

        // Set the frame visible
        frame.setVisible(true);
    }
}
