package Utils;



import javax.swing.*;

import GUI.MainMenu;
import Primary.Horse;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class GameFrame extends JPanel {
    private Backdrop backdrop;

    private JPanel skyPanel;
    private HorseRacer horseRacer;
    final int width = 800;
    final int height = 600;

    public GameFrame(int raceDistance, Sky sky) {
        horseRacer = new HorseRacer(width / 3, raceDistance);
        setOpaque(false);
        setLayout(null); // Layout to allow expansion

        // Create the backdrop with scaled images and set the panel size
            try {
                backdrop = new Backdrop("./Sprites/Backdrop/Track/top-track.png", "./Sprites/Backdrop/Track/bottom-track.png", 300, 300, 2);
                skyPanel = new ImagePanelLoader(String.format("./Sprites/Backdrop/Sky/sky-%s.png", sky.toString().toLowerCase()), 2);
                
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        backdrop.setPreferredSize(new Dimension(800, 600)); // Set the desired size for the backdrop panel
        skyPanel.setPreferredSize(backdrop.getPreferredSize());
        skyPanel.setBounds(-162, 0, width, height); // Position and size the backdrop correctly


        /////////////////
        // Horse Logic //
        /////////////////

        int displacement = 0;

        // Loop through each horse and create a GUI element for it.  
        for(int i = 0; i < HorseInstances.getInstance().horses.size(); i++) {
            // Get the horse
            Horse horse = HorseInstances.getInstance().horses.get(i);

            // Create a new HorseMover instance
            HorseMover horseMover = new HorseMover(0, 0, 40, 40, horse);
            // Increase the displacement so the next horse will appear below this one.  
            displacement += 35;


            horseMover.setBounds(0, -190 + displacement, 400, 400); // Set the position of the horse

            // Add the horse GUI element to the singleton that stores them all.  
            HorseMoverInstances.getInstance().addHorse(horseMover);
    }
        
    backdrop.setBounds(0, 0, width, height); // Position and size the backdrop correctly

        // Add the all the horse movers to the GameFrame in reverse order so they are correctly aligned in z-axis.  
        HorseMoverInstances.getInstance().getHorseMovers().reversed().forEach(horseMover -> {
            add(horseMover);
        });
        
        // Add the backdrop to the GameFrame (which is a JPanel)
        add(backdrop);

        add(skyPanel);


    }
    public void startRace() {
                // Set up a timer to move the background every 10 milliseconds
                AtomicInteger totalTimePassed = new AtomicInteger(0);
                int delay = 10;
                Timer timer = new Timer(delay, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int timePassed = totalTimePassed.getAndAdd(delay);
        
                         if(true) {
                            // if(HorseInstances.getInstance().getLeadingHorse().getDistanceTravelled() * width > backdrop.getCameraX()) {
                        backdrop.movePanel(); // Move the background
                         }
        
        
                        // Loop through each horse and update its position
                        ArrayList<HorseMover> horseMovers = HorseMoverInstances.getInstance().horseMovers;
                        for(int i = 0; i < horseMovers.size(); i++) {
                            HorseMover horseMover = horseMovers.get(i);
                            Horse horse = horseMover.getHorse();
        
                            Rectangle bounds = horseMover.getBounds();
                            int xCoord = horseRacer.getCoordinate(timePassed, delay, horse);
                            bounds.setLocation(xCoord, bounds.y);
                            horseMover.setBounds(bounds);
        
                            if(!horseMover.getPreviousFallenState()) {
                                if(horse.hasFallen()) {
                                    horseMover.setSprite(State.FALL);
                                    System.out.println("setfalling");
                                    horseMover.setPreviousFallenState();
                                }
                            }
                        }
                    }
                });
                timer.start();
    }

    public static void main(String[] args) {
        // Create a JFrame that will contain the GameFrame (which is a JPanel)
        JFrame frame = MainMenu.getInstance().getFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the GameFrame (which extends JPanel)
        GameFrame gamePanel = new GameFrame(1000, Sky.CLEAR);
        
        // Add the GameFrame panel to the JFrame
        frame.add(gamePanel);

        // Set the size of the JFrame
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }
}
