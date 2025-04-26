package Primary;

import java.util.ArrayList;
import java.util.List;

import Utils.Pair;

/**
 * Class to define a betting user
 * Currently this should only be instantiated once since there is only one user.
 * However, this is designed to the betting system can be scaled to more than
 * one user.
 */
public class User {
    private List<Pair<Horse, Pair<Integer, Integer>>> betHistory = new ArrayList<>();
    private List<Double> winningsAndLosses = new ArrayList<>();

    /**
     * Adds a new bet to the user's betting history.
     *
     * @param horse  the horse being bet on
     * @param amount the amount of the bet
     * @param odds   the odds at the time of the bet
     */
    public void addBet(Horse horse, int amount, double odds) {
        // Store potential winnings (odds * amount) as an integer
        int amt = amount;
        int potentialWinnings = (int) (amount * odds);

        // Create a new pair for this bet
        Pair<Horse, Pair<Integer, Integer>> bet = new Pair<>(horse, new Pair<>(amt, potentialWinnings));

        // Add the bet map to the bet history list
        betHistory.add(bet);
    }

    public List<Pair<Horse, Pair<Integer, Integer>>> getBettingHistory() {
        return betHistory;
    }

    /**
     * Method to add the user's winnings to their account
     * 
     * @param amount the amount that was won.
     */
    public void receiveWinnings(double amount) {
        winningsAndLosses.add(amount);
    }

    /**
     * Method to record a loss to the user's account
     * Simply calls the `receiveWinnings` method with a negative value of the
     * `amount` paramater taken
     * 
     * @param amount the amount to take from the user
     */
    public void recordLoss(double amount) {
        receiveWinnings(-amount);
    }

    /**
     * Method to calculate the user's overall balance.
     * 
     * @return the user's overall balance
     */
    public double totalBalance() {
        // Loops through and totals each individual win & loss.
        double total = 0;
        for (Double item : winningsAndLosses) {
            total += item;
        }
        // Return the total back to the user
        return total;
    }
}