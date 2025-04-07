package Utils;



import javax.swing.*;

import GUI.MainMenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class GameFrame extends JPanel {
    private Backdrop backdrop;

    public GameFrame() {
        setLayout(new BorderLayout()); // Layout to allow expansion

        // Create the backdrop with scaled images and set the panel size
            try {
                backdrop = new Backdrop("./Sprites/Backdrop/Track/top-track.png", "./Sprites/Backdrop/Track/bottom-track.png", 300, 300, 2);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        backdrop.setPreferredSize(new Dimension(800, 600)); // Set the desired size for the backdrop panel

        // Add the backdrop to the GameFrame (which is a JPanel)
        add(backdrop, BorderLayout.CENTER);

        // Set up a timer to move the background every 10 milliseconds
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backdrop.movePanel(); // Move the background
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        // Create a JFrame that will contain the GameFrame (which is a JPanel)
        JFrame frame = MainMenu.getInstance().getFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the GameFrame (which extends JPanel)
        GameFrame gamePanel = new GameFrame();
        
        // Add the GameFrame panel to the JFrame
        frame.add(gamePanel);

        // Set the size of the JFrame
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }
}
