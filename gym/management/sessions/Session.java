package gym.management.sessions;

import gym.customers.Client;
import gym.customers.Gender;
import gym.management.Instructor;

import java.util.ArrayList;
import java.util.List;

public abstract class Session {
    protected String sessionTime;       // Time of the session
    protected Instructor instructor;    // gym.management.Instructor assigned to the session
    protected List<Client> clients;     // List of registered clients
    protected ForumType forumType;      //
    protected int maxParticipants;      // Maximum number of participants
    protected int price;             // Price for the session
    protected String sessionType;

    // Constructor
    public Session(String sessionTime, Instructor instructor, int maxParticipants, int price, ForumType forumType, String sessionType) {
        this.sessionTime = sessionTime;
        this.sessionType = sessionType;
        this.instructor = instructor;
        this.clients = new ArrayList<>();
        this.maxParticipants = maxParticipants;
        this.forumType = forumType;
        this.price = price;
    }

    // Add a client to the session
    public void addClient(Client client) {
        if (isFull()) {
            throw new IllegalStateException("Error: No available spots for session.");
        }
        if (!isForumCorrect(client)) {
            throw new IllegalStateException("Error: gym.customers.Client does not meet the session's requirements.");
        }
        clients.add(client);
    }
    public boolean isForumGender() {
        return ((forumType.equals(ForumType.Male))||forumType.equals(ForumType.Female));
    }

    // Check if the session is full
    public boolean isFull() {
        return clients.size() >= maxParticipants;
    }
    public ForumType getForumType() {
        return forumType;
    }

    // Notify all registered clients
    public void notifyClients(String message) {
        for (Client client : clients) {
            client.update(message);
        }
    }
    public boolean isForumCorrect(Client client) {
        switch (forumType) {
            case All:
                return true; // כל הלקוחות יכולים להשתתף
            case Female:
                return client.getGender() == Gender.Female;
            case Male:
                return client.getGender() == Gender.Male;
            case Seniors:
                return client.getAge() >= 60; // תנאי לגילאי 60 ומעלה
            default:
                return false;
        }
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

    public boolean hasBalance(Client client) {
        return client.getBalance() >= this.getPrice();
    }

    @Override
    public String toString() {
        return "Session Type:"+this.getClass().getSimpleName() +
                " | Time: " + sessionTime +
                " | Instructor: " + instructor.getName() +
                " | Participants: " + clients.size() + "/" + maxParticipants;
    }
}
