package gym;
/**
 * Represents a balance that can be adjusted by adding or retrieving funds.
 */
public class Balance {
    private int balance; // The current balance

    /**
     * Constructor to initialize the balance.
     *
     * @param balance The initial balance.
     */
    public Balance(int balance) {
        this.balance = balance;
    }

    /**
     * Gets the current balance.
     *
     * @return The current balance.
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Adds the specified amount to the balance.
     * The amount can be positive (add funds) or negative (deduct funds).
     *
     * @param balance The amount to add or deduct.
     */
    public void addBalance(int balance) {
        this.balance += balance;
    }
}
