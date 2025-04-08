package Utils;

import java.util.ArrayList;

import Primary.Horse;

/**
 * Basic singleton to keep track of Horse instances. This will ensure the number
 * of horses and tracks is maintained throughout the program.
 * 
 */
public class HorseInstances {
    private static HorseInstances _instance;

    public ArrayList<Horse> horses;

    /// Get the stored instance of the [HorseInstances] class.  If there is no existing one then a new instance is created.  
    public static HorseInstances getInstance() {
        if (_instance == null) {
            _instance = new HorseInstances();
        }
        return _instance;
    }

    /// Accessor method for the [ArrayList] of type [Horse]
    public ArrayList<Horse> getHorses() {
        return this.horses;
    }

    // Instantiate horse list in constructor.
    HorseInstances() {
        horses = new ArrayList<>();
    }

    public static void main(String[] args) {
        HorseInstances.getInstance();
    }

    /**
     * Adds a horse to the race in a given lane
     * 
     * @param theHorse   the horse to be added to the race
     * @param laneNumber the lane that the horse will be added to
     */
    public void addHorse(Horse theHorse, int laneNumber) {

        // Ensure the array list is at least big enough to handle the laneNumber.
        while (laneNumber > horses.size()) {
            horses.add(null);
        }

        // Set the lane at the specified `laneNumber` to the [Horse] provided
        horses.set(laneNumber - 1, theHorse);
    }

    /**
     * Method to get the horse that is currently winning the race.
     * 
     * @return The leading [Horse] object.
     */
    public Horse getLeadingHorse() {
        Horse leadingHorse = null;
        // Loop through each horse in the race
        for (Horse horse : horses) {
            // If the leading horse is not set or the current horse is further than the
            // current leading then set that to leading.
            if (leadingHorse == null
                    || (horse != null && leadingHorse.getDistanceTravelled() < horse.getDistanceTravelled())) {
                leadingHorse = horse;
            }
        }
        // Return the leading horse to the caller.
        return leadingHorse;
    }

}
