package gym.customers;

import gym.management.SessionObserver;

import java.util.ArrayList;
import java.util.List;

public class Client extends Person implements SessionObserver {
    private List<String> notifications; // List to store notifications for the client
    // Constructor
    public Client(Person person) {
        super(person); // Call to gym.customers.Person (Copy Constructor)
        this.notifications = new ArrayList<>();
    }

    // Add a notification to the client
    @Override
    public void update(String message) {
        notifications.add(message);
    }

    // Get the list of notifications
    public List<String> getNotifications() {
        return notifications;
    }




    // Charge the client for a session
    public void chargeClient(int amount) {
        if (amount > this.getBalance()) {
            throw new IllegalArgumentException("Error: Insufficient balance for this operation.");
        }
        this.addbalance(-amount);
    }

    @Override
    public String toString() {
        return super.toString();
    }
    /**
     * Override equals method to compare clients based on their unique ID.
     *
     * @param obj The object to compare with.
     * @return True if IDs match, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // If same object, return true
        if (obj == null || getClass() != obj.getClass()) return false; // If null or different class, return false
        Client client = (Client) obj; // Cast to gym.customers.Client
        return this.getId() == client.getId(); // Compare IDs
    }

}
