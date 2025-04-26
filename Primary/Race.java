package Primary;

import java.util.concurrent.TimeUnit;

import Utils.BooleanWrapper;
import Utils.GameFrame;
import Utils.HorseInstances;
import Utils.HorseMoverInstances;
import Utils.State;

import java.lang.Math;
import java.util.ArrayList;

/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @author McRaceface
 * @author Oliver Munn
 * @version 2.0
 */
public class Race {
    private int raceLength;
    /// An ArrayList storing each lane.  Can be either of type [Horse] or `null` to indicate weather a horse is on a lane or it is empty.  

    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the lanes
     * 
     * @param distance the length of the racetrack (in metres/yards...)
     * @exception IllegalArgumentException If the distance is 0 or less.
     */
    public Race(int distance) throws IllegalArgumentException {
        // If the distance is 0 or negative then throw an exception
        if (distance <= 0) {
            throw new IllegalArgumentException("Distance must be at least 1 or more. ");
        }
        // initialise instance variables
        raceLength = distance;
    }

    /**
     * Start the race
     * The horse are brought to the start and
     * then repeatedly moved forward until the
     * race is finished
     */
    public void startRace(GameFrame gameFrame) {

        // declare a local variable to tell us when the race is finished.
        // This is an object so it's state can be maintained within lambda functions.
        BooleanWrapper finished = new BooleanWrapper(false);

        // reset all the lanes (all horses not fallen and back to 0).
        // loop through each lane individually.
        HorseInstances.getInstance().getHorses().forEach((Horse horse) -> {
            // If the lane is empty then doesn't have a horse to reset.
            if (horse != null) {
                horse.goBackToStart();
            }
        });

        // Set the visual representation to start to reflect this logical start
        gameFrame.startRace();

        // Loop through each horse mover and set its sprite to the running
        HorseMoverInstances.getInstance().horseMovers.forEach(horseMover -> {
            System.out.println(horseMover.getName());
            horseMover.setSprite(State.RUN);
        });

        // While no hoese has finished yet. (Race is still running)
        while (!finished.getFlag()) {
            // Create a flag to determine if all horses have fallen
            BooleanWrapper allFallen = new BooleanWrapper(true);

            // move each horse by looping through ArrayList
            HorseInstances.getInstance().getHorses().forEach((Horse horse) -> {
                if (horse != null) {
                    moveHorse(horse);

                    // Add a check to see if the horse has not fallen
                    if (!horse.hasFallen()) {
                        // if they haven't fallen then all the horses can't have fallen so set the flag
                        // to true
                        allFallen.setFlag(false);
                    }
                }
            });

            // print the race positions
            // printRace();

            // If all horses have fallen then mark the race as finished.
            if (allFallen.getFlag()) {
                finished.setFlag(true);
                // Stop moving UI
                gameFrame.endRace();
            }

            // if any of the three horses has won the race is finished
            HorseInstances.getInstance().getHorses().forEach((Horse horse) -> {
                if (horse != null) {
                    if (raceWonBy(horse)) {
                        // Set the finished value to `true`.
                        finished.setFlag(true);

                        // Stop Moving UI
                        gameFrame.endRace();
                        return;
                    }
                }
            });

            // wait for 100 milliseconds
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (Exception e) {
            }
        }
        printVictor();
    }

    /**
     * Call the startRace method but only after a 3 second countdown
     * 
     * @param gameFrame
     */
    public void startRaceWithCountdown(GameFrame gameFrame) {
        try {
            // Wait 3 seconds
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            // If sleep fails then print stacktrace
            e.printStackTrace();
        }

        // Start the race in the grid
        startRace(gameFrame);
    }

    /**
     * Randomly make a horse move forward or fall depending
     * on its confidence rating
     * A fallen horse cannot move
     * 
     * @param theHorse the horse to be moved
     */
    private void moveHorse(Horse theHorse) {
        // if the horse has fallen it cannot move,
        // so only run if it has not fallen
        if (!theHorse.hasFallen()) {
            // the probability that the horse will move forward depends on the confidence;
            if (Math.random() < theHorse.getConfidence()) {
                theHorse.moveForward();
            }

            // the probability that the horse will fall is very small (max is 0.1)
            // but will also will depends exponentially on confidence
            // so if you double the confidence, the probability that it will fall is *2
            if (Math.random() < (0.001 * theHorse.getConfidence() * theHorse.getConfidence())) {
                theHorse.fall();
            }
        }
    }

    /**
     * Determines if a horse has won the race
     *
     * @param theHorse The horse we are testing
     * @return true if the horse has won, false otherwise.
     */
    private boolean raceWonBy(Horse theHorse) {
        if (theHorse.getDistanceTravelled() == raceLength) {
            return true;
        } else {
            return false;
        }
    }

    // prints the victor of a race
    private void printVictor() {
        // gets a list of all the victors
        ArrayList<Horse> victors = new ArrayList<>();
        HorseInstances.getInstance().getHorses().forEach((horse) -> {
            if (horse != null && horse.getDistanceTravelled() == raceLength) {
                victors.add(horse);
            }
        });

        // Check if there is only 1 victor
        if (victors.size() == 1) {
            // Announce winner
            System.out.println(String.format("%s has won the race!", victors.get(0).getName()));

            // Check if nobody has won
        } else if (victors.isEmpty()) {
            // Announce no winner
            System.out.print("Nobody has won.");

            // Otherwise it must be a draw
        } else {
            // Announce winners
            System.out.print("The race has resulted in a draw! The winners are as follows:\n");
            victors.forEach((horse) -> {
                System.out.println(horse.getName());
            });
            System.out.println("");

        }

    }

    /***
     * Print the race on the terminal
     */
    @Deprecated
    public void printRace() {
        System.out.print('\u000C'); // clear the terminal window

        multiplePrint('=', raceLength + 3); // top edge of track
        System.out.println();

        // Print the lanes through a loop to handle different numbers of lanes and
        // horses.
        HorseInstances.getInstance().getHorses().forEach((Horse horse) -> {
            printLane(horse);
            System.out.println();
        });

        multiplePrint('=', raceLength + 3); // bottom edge of track
        System.out.println();
    }

    /**
     * print a horse's lane during the race
     * for example
     * | X |
     * to show how far the horse has run
     */
    private void printLane(Horse theHorse) {
        // Handle empty lanes
        if (theHorse == null) {
            System.out.print('|');
            multiplePrint(' ', raceLength + 1);
            System.out.print('|');
            return;
        }

        // calculate how many spaces are needed before
        // and after the horse
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();

        // print a | for the beginning of the lane
        System.out.print('|');

        // print the spaces before the horse
        multiplePrint(' ', spacesBefore);

        // if the horse has fallen then print dead
        // else print the horse's symbol
        if (theHorse.hasFallen()) {
            System.out.print('\u2322');
        } else {
            System.out.print(theHorse.getSymbol());
        }

        // print the spaces after the horse
        multiplePrint(' ', spacesAfter);

        // print the | for the end of the track
        System.out.print('|');
    }

    /***
     * print a character a given number of times.
     * e.g. printmany('x',5) will print: xxxxx
     * 
     * @param aChar the character to Print
     */
    private void multiplePrint(char aChar, int times) {
        int i = 0;
        while (i < times) {
            System.out.print(aChar);
            i = i + 1;
        }
    }
}