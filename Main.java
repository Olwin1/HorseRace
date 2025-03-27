public class Main {
    public static void main(String[] args) {
        // testHorse();
        Race race = new Race(100);
        race.addHorse(new Horse('A', "Adi", 0.5), 1);
        race.addHorse(new Horse('B', "Bob", 0.25), 2);
        race.addHorse(new Horse('C', "Cob", 0.8), 3);
        race.startRace();
    }

    public static void testHorse() {
        System.out.println("START OF TEST 1");
        // TEST ONE
        try {
            // This should be fine and create a horse with all the correct values
            Horse horseOne = new Horse('X', "JIM", 1);
            System.out.println(
                    String.format("Symbol: %s, Name: %s, Confidence: %s", horseOne.getSymbol(), horseOne.getName(),
                            horseOne.getConfidence()));
        } catch (IllegalArgumentException e) {
            System.out.println("This should NOT error");
        }
        System.out.println("END OF TEST 1");


        // TEST TWO
        System.out.println("START OF TEST 2");
        try {
            // This should throw an error because horse confidence is out of range
            Horse horseTwo = new Horse('P', "Piotr", 1.334);
            System.out.println(String.format("Symbol: %s, Name: %s, Confidence: %s", horseTwo.getSymbol(), horseTwo.getName(),
                    horseTwo.getConfidence())); // Should not reach here
        } catch (IllegalArgumentException e) {
            System.out.println("This should error"); // If this is printed then error is correctly raised
        }
        System.out.println("END OF TEST 2");


        Horse horse = new Horse('J', "Jadzia", 0.6);


        // TEST THREE
        System.out.println("START OF TEST 3");
        // Should be 0 here
        System.out.println(horse.getDistanceTravelled());
        horse.moveForward();
        // Should be 1 here
        System.out.println(horse.getDistanceTravelled());
        horse.moveForward();
        // Should be 2 here
        System.out.println(horse.getDistanceTravelled());
        System.out.println("END OF TEST 3");


        // Test FOUR
        System.out.println("START OF TEST 4");
        System.out.println(horse.hasFallen()); // Should not have fallen (false)
        horse.fall();
        System.out.println(horse.hasFallen()); // Should have fallen (true)
        System.out.println("END OF TEST 4");

        // Test FIVE
        System.out.println("START OF TEST 5");
        // Distance should be non-0 and should have fallen
        System.out.println(String.format("Distance: %s, Fallen: %s", horse.getDistanceTravelled(), horse.hasFallen()));
        horse.goBackToStart();

        // Distance should be 0 and should not have fallen
        System.out.println(String.format("Distance: %s, Fallen: %s", horse.getDistanceTravelled(), horse.hasFallen()));

        System.out.println("END OF TEST 5");
        

        // Test SIX
        System.out.println("START OF TEST 6");
        try {
        // Confidence should be between 0 and 1
        System.out.println(String.format("Confidence: %s", horse.getConfidence()));
        horse.setConfidence(0.2);
        // Should be 0.2
        System.out.println(String.format("Confidence: %s", horse.getConfidence()));
        } catch (IllegalArgumentException e) {
            // Should not run
            System.out.println("This should not run");
        }
        System.out.println("END OF TEST 6");



        // Test SEVEN
        System.out.println("START OF TEST 7");
        try {
        // Confidence should be between 0 and 1
        System.out.println(String.format("Confidence: %s", horse.getConfidence()));
        horse.setConfidence(2000);
        // SHOULD NOT REACH HERE
        System.out.println(String.format("Confidence: %s", horse.getConfidence()));
        } catch (IllegalArgumentException e) {
            // SHOULD run
            System.out.println("This SHOULD run");
            // Should not have changed5
            System.out.println(String.format("Confidence: %s", horse.getConfidence()));
    }
        System.out.println("END OF TEST 7");
        

    }
}
