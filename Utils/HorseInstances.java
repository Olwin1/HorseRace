package Utils;

import java.util.ArrayList;

import Primary.Horse;

/**
 * Basic singleton to keep track of Horse instances.  This will ensure the number of horses and tracks is maintained throughout the program.  
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
}
