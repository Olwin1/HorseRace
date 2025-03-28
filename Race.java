import java.util.concurrent.TimeUnit;
import java.lang.Math;
import java.util.ArrayList;

/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @author McRaceface
 * @author Oliver Munn
 * @version 1.1
 */
public class Race
{
    private int raceLength;
    /// An ArrayList storing each lane.  Can be either of type [Horse] or `null` to indicate weather a horse is on a lane or it is empty.  
    private ArrayList<Horse> lanes;

    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the lanes
     * 
     * @param distance the length of the racetrack (in metres/yards...)
     * @exception IllegalArgumentException If the distance is 0 or less.  
     */
    public Race(int distance) throws IllegalArgumentException
    {
        // If the distance is 0 or negative then throw an exception
        if(distance <= 0) {
            throw new IllegalArgumentException("Distance must be at least 1 or more. ");
        }
        // initialise instance variables
        raceLength = distance;
        lanes = new ArrayList<>();
    }
    
    /**
     * Adds a horse to the race in a given lane
     * 
     * @param theHorse the horse to be added to the race
     * @param laneNumber the lane that the horse will be added to
     */
    public void addHorse(Horse theHorse, int laneNumber)
    {

        // Ensure the array list is at least big enough to handle the laneNumber.  
        while(laneNumber > lanes.size()) {
            lanes.add(null);
        }

        // Set the lane at the specified `laneNumber` to the [Horse] provided
        lanes.set(laneNumber - 1, theHorse);
    }
    
    /**
     * Start the race
     * The horse are brought to the start and
     * then repeatedly moved forward until the 
     * race is finished
     */
    public void startRace()
    {
        // declare a local variable to tell us when the race is finished.
        // This is an object so it's state can be maintained within lambda functions.  
        BooleanWrapper finished = new BooleanWrapper(false);
                
                //reset all the lanes (all horses not fallen and back to 0). 
                // loop through each lane individually.  
                lanes.forEach((Horse horse) -> {
                    // If the lane is empty then doesn't have a horse to reset.  
                    if(horse != null) {
                        horse.goBackToStart();
                    }
                });
                   
                // While no hoese has finished yet. (Race is still running)
                while (!finished.getFlag())
                {
                    //move each horse by looping through ArrayList
                    lanes.forEach((Horse horse) -> {
                        if(horse != null) {
                            moveHorse(horse);;
                        }
                    });
                                
                    //print the race positions
                    printRace();
                    
                    //if any of the three horses has won the race is finished
                    lanes.forEach((Horse horse) -> {
                        if(horse != null) {
                            if(raceWonBy(horse)) {
                                // Set the finished value to `true`.  
                                finished.setFlag(true);
                                return;
                    }
                }
            });
           
            //wait for 100 milliseconds
            try{ 
                TimeUnit.MILLISECONDS.sleep(100);
            }catch(Exception e){}
        }
    }
    
    /**
     * Randomly make a horse move forward or fall depending
     * on its confidence rating
     * A fallen horse cannot move
     * 
     * @param theHorse the horse to be moved
     */
    private void moveHorse(Horse theHorse)
    {
        //if the horse has fallen it cannot move, 
        //so only run if it has not fallen
        if  (!theHorse.hasFallen())
        {
            //the probability that the horse will move forward depends on the confidence;
            if (Math.random() < theHorse.getConfidence())
            {
               theHorse.moveForward();
            }
            
            //the probability that the horse will fall is very small (max is 0.1)
            //but will also will depends exponentially on confidence 
            //so if you double the confidence, the probability that it will fall is *2
            if (Math.random() < (0.1*theHorse.getConfidence()*theHorse.getConfidence()))
            {
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
    private boolean raceWonBy(Horse theHorse)
    {
        if (theHorse.getDistanceTravelled() == raceLength)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /***
     * Print the race on the terminal
     */
    private void printRace()
    {
        System.out.print('\u000C');  //clear the terminal window
        
        multiplePrint('=',raceLength+3); //top edge of track
        System.out.println();

        // Print the lanes through a loop to handle different numbers of lanes and horses.  
        lanes.forEach((Horse horse) -> {
            printLane(horse);
            System.out.println();
        });

        
        multiplePrint('=',raceLength+3); //bottom edge of track
        System.out.println();    
    }
    
    /**
     * print a horse's lane during the race
     * for example
     * |           X                      |
     * to show how far the horse has run
     */
    private void printLane(Horse theHorse)
    {
        // Handle empty lanes
        if(theHorse == null) {
            System.out.print('|');
            multiplePrint(' ', raceLength + 1);
            System.out.print('|');
            return;
        }


        //calculate how many spaces are needed before
        //and after the horse
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();
        
        //print a | for the beginning of the lane
        System.out.print('|');
        
        //print the spaces before the horse
        multiplePrint(' ',spacesBefore);
        
        //if the horse has fallen then print dead
        //else print the horse's symbol
        if(theHorse.hasFallen())
        {
            System.out.print('\u2322');
        }
        else
        {
            System.out.print(theHorse.getSymbol());
        }
        
        //print the spaces after the horse
        multiplePrint(' ',spacesAfter);
        
        //print the | for the end of the track
        System.out.print('|');
    }
        
    
    /***
     * print a character a given number of times.
     * e.g. printmany('x',5) will print: xxxxx
     * 
     * @param aChar the character to Print
     */
    private void multiplePrint(char aChar, int times)
    {
        int i = 0;
        while (i < times)
        {
            System.out.print(aChar);
            i = i + 1;
        }
    }
}