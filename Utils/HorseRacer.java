package Utils;

import Primary.Horse;
/**
 * Simple helper class to translate the HorseRacing logic to a visual representation. 
 */
public class HorseRacer {
    private int width;

    HorseRacer(int width) {
        this.width = width;
    }

    /**
     * Method to convert the horse distance travelled to an actual coordinate that can be displayed on-screen.  
     * @param horse - The horse to determine the coords of.  
     * @return - The X coordinate. 
     */
    public int getCoordinate(Horse horse) {
        // Get the leading horse
        Horse leadingHorse = HorseInstances.getInstance().getLeadingHorse();
        // Calculate where the upper quater of the screen is
        int leadingXCoord = width / 4;

        // Work out the difference between the selected horse and the leader.  
        int difference = horse.getDistanceTravelled() - leadingHorse.getDistanceTravelled();

        // Return a new coordinate specifying where the horse should be on the screen.  
        return leadingXCoord + difference;
    }

}
