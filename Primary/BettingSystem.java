package Primary;

import java.util.*;

import GUI.GameGrid;
import Utils.Pair;
import Utils.Sky;

/**
 * The BettingSystem class handles the betting logic for a horse racing game,
 * including calculating odds based on horse performance, track condition,
 * and adjusting odds dynamically based on betting trends.
 */
public class BettingSystem {

    /**
     * Stores the base odds for each horse before adjustments.
     */
    private Map<Horse, Double> baseOdds = new HashMap<>();

    /**
     * Tracks the number of bets placed on each horse.
     */
    private Map<Horse, Integer> userBetCounts = new HashMap<>();

    /**
     * Factor based on track conditions affecting odds.
     */
    private double trackConditionFactor = 1.0;

    private static BettingSystem _instance;

    /**
     * Default constructor for BettingSystem.
     */
    private BettingSystem() {
    }

    public static BettingSystem getInstance() {
        if (_instance != null) {
            return _instance;
        } else {
            return new BettingSystem();
        }
    }

    /**
     * Calculates odds for a horse based on its confidence, recent trends,
     * and current track condition.
     *
     * @param horse the horse for which to calculate odds
     * @param sky   the current track weather
     * @return the calculated odds for the horse
     */
    public double calculateOdds(Horse horse, Sky sky) {
        double confidence = horse.getConfidence(); // Confidence between 0.0 and 1.0
        List<Integer> recentTrends = horse.getRecentTrends();
        Sky trackCondition = sky;

        double trendScore = 1.0; // Average position across recent races
        if (recentTrends != null) {
            int count = 0;
            int total = 0;
            for (Integer pos : recentTrends) {
                if (pos != null) {
                    total += pos;
                    count++;
                }
            }
            if (count > 0)
                trendScore = (double) total / count;
        }

        // Determine track condition impact
        trackConditionFactor = getTrackConditionModifier(trackCondition);

        // Base odds formula: influenced by performance, confidence, and track
        double baseOdd = (1.5 + trendScore) * (1.1 - confidence) * trackConditionFactor;
        baseOdds.put(horse, baseOdd);
        return round(baseOdd);
    }

    /**
     * Places a bet for a user on a given horse and updates betting trends.
     *
     * @param user   the user placing the bet
     * @param horse  the horse the user is betting on
     * @param amount the amount being bet
     * @param sky    the track weather
     */
    public void placeBet(User user, Horse horse, int amount, Sky sky) {
        // Add the bet to the user's betting history with calculated odds
        user.addBet(horse, amount, calculateOdds(horse, sky));

        // Update bet count for the horse
        userBetCounts.put(horse, userBetCounts.getOrDefault(horse, 0) + 1);

        // Adjust odds based on how popular the horse is
        adjustOddsBasedOnBettingTrends(horse);
    }

    /**
     * Adjusts the odds of a horse based on the number of bets placed on it.
     * So its odds reduce slightly as it becomes more popular.
     *
     * @param horse the horse whose odds are to be adjusted
     */
    private void adjustOddsBasedOnBettingTrends(Horse horse) {
        int betsOnHorse = userBetCounts.getOrDefault(horse, 0);
        double popularityFactor = 1.0 + (betsOnHorse * 0.01); // Small reduction in odds per bet

        double newOdds = baseOdds.get(horse) / popularityFactor;
        baseOdds.put(horse, newOdds);
    }

    /**
     * Returns a modifier for the odds based on the track condition.
     *
     * @param condition the current track weather condition
     * @return the corresponding modifier for odds calculation
     */
    private double getTrackConditionModifier(Sky condition) {
        return switch (condition) {
            case Sky.SUNSET -> 1.2;
            case Sky.CLEAR -> 1.0;
            case Sky.RAIN -> 1.3;
            default -> 1.0;
        };
    }

    /**
     * Utility method to round a double to two decimal places.
     *
     * @param value the value to round
     * @return the rounded value
     */
    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    /**
     * Pays out winnings to users based on race results.
     * 
     * For each user, this method checks their placed bets. If a user bet on the
     * winning horse
     * (the one in position 1), they receive winnings calculated as (amount * odds).
     * All bets are cleared after processing.
     *
     * @param horsePositions a map linking each Horse to its final race position (1
     *                       = first place)
     * @param users          a list of users who placed bets in the current race
     */
    public void payOutWinnings(Map<Horse, Integer> horsePositions, List<User> users) {
        for (User user : users) {
            // Retrieve all bets placed by this user
            for (Pair<Horse, Pair<Integer, Integer>> bet : user.getBettingHistory()) {
                Horse horse = bet.getFirst(); // Horse the user bet on
                double amount = bet.getSecond().getFirst(); // Bet amount
                double winnings = bet.getSecond().getSecond(); // Calculated winnings at time of bet

                Integer position = horsePositions.get(horse); // Final race position of this horse

                if (position != null && position == 1) {
                    // Credit winnings to user
                    user.receiveWinnings(winnings);
                } else {
                    // Horse didn’t win — record the loss if needed
                    user.recordLoss(amount);
                }
            }
        }
    }

}
