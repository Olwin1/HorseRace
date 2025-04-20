package Utils;

import Primary.Horse;
/**
 * Simple helper class to translate the HorseRacing logic to a visual representation. 
 */
public class HorseRacer {
    private int width;
    private int totalTrackDistance;

    HorseRacer(int width, int totalTrackDistance) {
        this.width = width;
        this.totalTrackDistance = totalTrackDistance;
    }

    /**
     * Method to convert the horse distance travelled to an actual coordinate that can be displayed on-screen.  
     * @param horse - The horse to determine the coords of.  
     * @return - The X coordinate. 
     */
    public int getCoordinate(int timePassed, int delay, Horse horse) {
    
        // How far the backdrop has moved (i.e., how many pixels the "camera" has moved)
        int backdropShift = timePassed / delay;
    
        // Get the horse's relative distance from the starting point in metres
        double horsePositionInMetres = horse.getDistanceTravelled() * 8;
    
        // Convert that to a pixel position relative to the full track
        double horsePositionInPixels = (horsePositionInMetres / (double) totalTrackDistance) * width;
    
        // Apply the backdrop shift — move everything left as the background scrolls
        double finalPosition = horsePositionInPixels - backdropShift;

        if(horse.getName() == "Cob") {
        System.out.println(String.format("Horse: %s, position: %s, backdropshift: %s, horsepospixel: %s", horse.getName(), finalPosition, backdropShift, horsePositionInPixels));
        }
        return (int) finalPosition;
    }
    

}
