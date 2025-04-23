import java.util.concurrent.TimeUnit;

import GUI.GameGrid;
import GUI.StartPage;
import Primary.Horse;
import Primary.HorseColour;
import Primary.Race;
import Utils.HorseInstances;

public class Main {
    public static void main(String[] args) {
        // Define the initial race distance
        final int raceDistance = 10000;

        // Add horses to the race
        HorseInstances.getInstance().addHorse(new Horse('A', "Adi", 0.5, HorseColour.BLUE), 1);
        HorseInstances.getInstance().addHorse(new Horse('C', "Cob", 0.8, HorseColour.GREEN), 3);
        HorseInstances.getInstance().addHorse(new Horse('B', "Bob", 0.25, HorseColour.PURPLE), 2);

        // Create a grid instance
        // GameGrid grid = new GameGrid(raceDistance);
        // // testHorse();
        //  Race race = new Race(raceDistance);

         StartPage startPage = new StartPage();

        try {
            // Wait 3 seconds
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            // If sleep fails then print stacktrace
            e.printStackTrace();
        }

        // Start the race in the grid
        // race.startRace(grid.getGameFrame());
    }
}
