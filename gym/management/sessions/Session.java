package gym.management.sessions;

import gym.customers.Client;
import gym.customers.Gender;
import gym.management.Instructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class for all session types in the gym.
 */
public abstract class Session {

    protected String sessionTime;       // Time of the session in "yyyy-MM-dd HH:mm" format
    protected Instructor instructor;    // Instructor assigned to the session
    protected List<Client> clients;     // List of registered clients
    protected ForumType forumType;      // Forum type for the session
    protected int maxParticipants;      // Maximum number of participants allowed
    protected int price;                // Price for the session
    protected String sessionType;       // Type of the session

    /**
     * Constructor to initialize a session.
     *
     * @param sessionTime     The time of the session.
     * @param instructor      The instructor leading the session.
     * @param maxParticipants The maximum number of participants allowed.
     * @param price           The price for the session.
     * @param forumType       The type of forum (e.g., All, Male, Female, Seniors).
     * @param sessionType     The name of the session type.
     */
    public Session(String sessionTime, Instructor instructor, int maxParticipants, int price, ForumType forumType, String sessionType) {
        this.sessionTime = sessionTime;
        this.sessionType = sessionType;
        this.instructor = instructor;
        this.clients = new ArrayList<>();
        this.maxParticipants = maxParticipants;
        this.forumType = forumType;
        this.price = price;
    }

    /**
     * Adds a client to the session if there is space,and they meet the forum requirements.
     *
     * @param client The client to be added.
     */
    public void addClient(Client client) {
        if (isFull()) {
            throw new IllegalStateException("Error: No available spots for session.");
        }
        if (!isForumCorrect(client)) {
            throw new IllegalStateException("Error: Client does not meet the session's requirements.");
        }
        clients.add(client);
    }

    /**
     * Checks if the session forum is restricted by gender.
     *
     * @return True if the forum is gender-restricted, otherwise false.
     */
    public boolean isForumGender() {
        return (forumType.equals(ForumType.Male) || forumType.equals(ForumType.Female));
    }

    /**
     * Checks if the session is full.
     *
     * @return True if the session is full, otherwise false.
     */
    public boolean isFull() {
        return clients.size() >= maxParticipants;
    }

    /**
     * Checks if a client meets the forum requirements of the session.
     *
     * @param client The client to be checked.
     * @return True if the client meets the requirements, otherwise false.
     */
    public boolean isForumCorrect(Client client) {
        return switch (forumType) {
            case All -> true; // All clients can participate
            case Female -> client.getGender() == Gender.Female;
            case Male -> client.getGender() == Gender.Male;
            case Seniors -> client.getAge() >= 65; // Only clients aged 65 and above can participate
            default -> false;
        };
    }

    /**
     * Sends a notification message to all registered clients.
     *
     * @param message The message to be sent.
     */
    public void notifyClients(String message) {
        for (Client client : clients) {
            client.update(message);
        }
    }

    /**
     * Checks if a client has sufficient balance to register for the session.
     *
     * @param client The client to be checked.
     * @return True if the client has enough balance, otherwise false.
     */
    public boolean hasBalance(Client client) {
        return client.getBalance() >= this.getPrice();
    }

    // Getters
    public String getSessionTime() {
        return sessionTime;
    }

    public String getSessionType() {
        return sessionType;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public List<Client> getClients() {
        return clients;
    }

    public int getPrice() {
        return price;
    }

    public ForumType getForumType() {
        return forumType;
    }

    /**
     * Overrides the toString method to provide a descriptive representation of the session.
     *
     * @return A string representation of the session details.
     */
    @Override
    public String toString() {
        return "Session Type: " + this.getClass().getSimpleName() +
                " | Time: " + sessionTime +
                " | Instructor: " + instructor.getName() +
                " | Participants: " + clients.size() + "/" + maxParticipants;
    }
}
