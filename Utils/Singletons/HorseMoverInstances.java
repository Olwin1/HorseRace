package Utils.Singletons;

import java.util.ArrayList;

import Utils.HorseMover;


/**
 * Basic singleton to keep track of HorseMover instances. This will ensure the horses can be moved across the track
 * 
 */
public class HorseMoverInstances {
    private static HorseMoverInstances _instance;

    public ArrayList<HorseMover> horseMovers;

    /// Get the stored instance of the [HorseMoverInstances] class.  If there is no existing one then a new instance is created.  
    public static HorseMoverInstances getInstance() {
        if (_instance == null) {
            _instance = new HorseMoverInstances();
        }
        return _instance;
    }

    /// Accessor method for the [ArrayList] of type [HorseMover]
    public ArrayList<HorseMover> getHorseMovers() {
        return this.horseMovers;
    }

    // Instantiate horse list in constructor.
    HorseMoverInstances() {
        horseMovers = new ArrayList<>();
    }

    public static void main(String[] args) {
        HorseMoverInstances.getInstance();
    }

    /**
     * Adds a horse to the race in a given lane
     * 
     * @param horseMover the horseMover to add to the list
     */
    public void addHorse(HorseMover horseMover) {
        horseMovers.add(horseMover);
    }
}
