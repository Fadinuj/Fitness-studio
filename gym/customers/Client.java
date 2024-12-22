package gym.customers;

import gym.management.SessionObserver;

import java.util.ArrayList;
import java.util.List;

public class Client extends Person implements SessionObserver {
    private List<String> notifications; // List to store notifications for the client

    /**
     * Constructor for Client.
     * Initializes a client based on a Person object and sets up a notifications list.
     *
     * @param person The Person object to base the Client on.
     */
    public Client(Person person) {
        super(person); // Calls the copy constructor from the Person class
        this.notifications = new ArrayList<>(); // Initializes the notifications list
    }

    /**
     * Adds a notification to the client's notifications list.
     *
     * @param message The message to add to the notifications list.
     */
    @Override
    public void update(String message) {
        notifications.add(message); // Adds the provided message to the notifications list
    }

    /**
     * Retrieves the client's notifications.
     *
     * @return A list of notification messages.
     */
    public List<String> getNotifications() {
        return notifications; // Returns the list of notifications
    }

    /**
     * Deducts a specified amount from the client's balance to charge for a session.
     * Throws an exception if the client's balance is insufficient.
     *
     * @param amount The amount to deduct from the client's balance.
     * @throws IllegalArgumentException If the client does not have enough balance.
     */
    public void chargeClient(int amount) {
        if (amount > this.getBalance()) {
            throw new IllegalArgumentException("Error: Insufficient balance for this operation.");
        }
        this.addbalance(-amount); // Deducts the amount from the client's balance
    }

    /**
     * Overrides the toString method to provide a string representation of the client.
     *
     * @return A string representation of the client.
     */
    @Override
    public String toString() {
        return super.toString(); // Delegates to the toString method in the Person class
    }

    /**
     * Override equals method to compare clients based on their unique ID.
     *
     * @param obj The object to compare with.
     * @return True if IDs match, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // If the objects are the same instance, return true
        if (obj == null || getClass() != obj.getClass()) return false;
        // If the object is null or not the same class, return false
        Client client = (Client) obj; // Cast the object to Client
        return this.getId() == client.getId(); // Compare the unique IDs of the clients
    }
}
