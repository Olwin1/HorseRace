package Utils;

import javax.swing.*;

import GUI.BettingPage;
import GUI.FinishingPage;
import GUI.GameGrid;
import GUI.MainMenu;
import Primary.BettingSystem;
import Primary.Horse;
import Utils.Enums.Sky;
import Utils.Enums.State;
import Utils.Singletons.HorseInstances;
import Utils.Singletons.HorseMoverInstances;
import Utils.Singletons.UserInstances;
import Utils.Sprite.AnimatedSprite;
import Utils.Sprite.Backdrop;
import Utils.Sprite.ImagePanelLoader;

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
    private AnimatedSprite rainSprite;
    private Timer timer;
    final int width = 800;
    final int height = 600;

    public GameFrame(int raceDistance, Sky sky) {
        horseRacer = new HorseRacer(width / 3, raceDistance);
        setOpaque(false);
        setLayout(null); // Layout to allow expansion

        // Create the backdrop with scaled images and set the panel size
        try {
            backdrop = new Backdrop("./Sprites/Backdrop/Track/top-track.png",
                    "./Sprites/Backdrop/Track/bottom-track.png", 300, 300, 2);
            skyPanel = new ImagePanelLoader(
                    String.format("./Sprites/Backdrop/Sky/sky-%s.png", sky.toString().toLowerCase()), 2);
            // If it is raining then add a rain sprite
            rainSprite = sky == Sky.RAIN ? new AnimatedSprite("./Sprites/Backdrop/Sky/rain.gif", false, null, 1) : null;

        } catch (FileNotFoundException e) {
            // If errors then print stacktrace
            e.printStackTrace();
        }
        backdrop.setPreferredSize(new Dimension(800, 600)); // Set the desired size for the backdrop panel
        // Match the backdrop
        skyPanel.setPreferredSize(backdrop.getPreferredSize());

        skyPanel.setBounds(-162, 0, width, height); // Position and size the sky correctly

        /////////////////
        // Horse Logic //
        /////////////////

        // Declare a displacement variable to stop the horses stacking on top of
        // eachother
        int displacement = 0;

        // Loop through each horse and create a GUI element for it.
        for (int i = 0; i < HorseInstances.getInstance().horses.size(); i++) {
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

        // Add the all the horse movers to the GameFrame in reverse order so they are
        // correctly aligned in z-axis.
        HorseMoverInstances.getInstance().getHorseMovers().reversed().forEach(horseMover -> {
            add(horseMover);
        });

        // Add the backdrop to the GameFrame (which is a JPanel)
        // If its raining then add that too
        if (rainSprite != null) {
            add(rainSprite);
        }
        add(backdrop);

        add(skyPanel);

    }

    /**
     * Method to begin the UI display of the race
     */
    public void startRace() {
        // Set up a timer to move the background every 10 milliseconds
        AtomicInteger totalTimePassed = new AtomicInteger(0);
        int delay = 10;
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int timePassed = totalTimePassed.getAndAdd(delay);

                if (true) {
                    // if(HorseInstances.getInstance().getLeadingHorse().getDistanceTravelled() *
                    // width > backdrop.getCameraX()) {
                    backdrop.movePanel(); // Move the background
                }

                // Loop through each horse and update its position
                ArrayList<HorseMover> horseMovers = HorseMoverInstances.getInstance().horseMovers;
                for (int i = 0; i < horseMovers.size(); i++) {
                    HorseMover horseMover = horseMovers.get(i);
                    Horse horse = horseMover.getHorse();

                    Rectangle bounds = horseMover.getBounds();
                    int xCoord = horseRacer.getCoordinate(timePassed, delay, horse);
                    bounds.setLocation(xCoord, bounds.y);
                    horseMover.setBounds(bounds);

                    if (!horseMover.getPreviousFallenState()) {
                        if (horse.hasFallen()) {
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

    /**
     * Stop running the UI of the race. This most noticably will stop the camera
     * scrolling.
     */
    public void endRace() {
        // Clone existing list
        ArrayList<Horse> horses = new ArrayList<>(HorseInstances.getInstance().horses);

        // Sort horses: first by hasFallen(), then by distance (higher distance first)
        horses.sort((h1, h2) -> {
            if (h1.hasFallen() && !h2.hasFallen()) {
                return 1; // h1 after h2
            } else if (!h1.hasFallen() && h2.hasFallen()) {
                return -1; // h1 before h2
            } else {
                // Both fallen or both not fallen -> sort by distance (descending)
                return Double.compare(h2.getDistanceTravelled(), h1.getDistanceTravelled());
            }
        });

        // Add their position scored to their history
        for (int i = 0; i < horses.size(); i++) {
            Horse horse = horses.get(i);
            horse.addNewTrend(i + 1);
        }

        GameGrid gridInstance = GameGrid.getInstance(null);
        // Reduce count by 1
        gridInstance.decreaseRaceCount();
        // If there are still races left then show the betting page again
        if (gridInstance.getRaceCount() != 0) {
            BettingPage.displayBettingPage();
        } else {
            FinishingPage.displayFinshingPage();
        }

        // Pay out winnings
        BettingSystem.getInstance().payOutWinnings(horses, UserInstances.getInstance().getUsers());

        // Now `horses` is sorted correctly: non-fallen sorted by distance, fallen ones
        // at the back

        // Stop the timer
        timer.stop();

        // Shove all horses back to start
        clearHorses();
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

    private void clearHorses() {
        // remove all horse movers
        ArrayList<HorseMover> horseMovers = HorseMoverInstances.getInstance().getHorseMovers();
        horseMovers.clear();
    }
}
