
import GUI.StartPage;
import Primary.Horse;
import Primary.HorseColour;
import Utils.HorseInstances;

public class Main {
    public static void main(String[] args) {
        // Define the initial race distance

        // Add horses to the race
        HorseInstances.getInstance().addHorse(new Horse('A', "Adi", 0.5, HorseColour.BLUE), 1);
        HorseInstances.getInstance().addHorse(new Horse('C', "Cob", 0.8, HorseColour.GREEN), 3);
        HorseInstances.getInstance().addHorse(new Horse('B', "Bob", 0.25, HorseColour.PURPLE), 2);

        // Display the start page
        StartPage startPage = new StartPage();
    }
}
