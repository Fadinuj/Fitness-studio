package gym.management;

import gym.Balance;
import gym.customers.Client;
import gym.customers.Person;
import gym.management.sessions.Session;

import java.util.ArrayList;
import java.util.List;

public class Gym {
    private static Gym instance; // Singleton instance of gym.management.Gym

    private String name; // Name of the gym
    private Secretary secretary; // gym.management.Gym secretary
    private Balance balance; // gym.Balance of the gym
    private List<Client> clients; // List of clients
    private List<Instructor> instructors; // List of instructors
    private List<Session> sessions; // List of sessions
    private List<String> actionsHistory; // History log of actions

    // Private constructor for Singleton pattern
    private Gym() {
        this.clients = new ArrayList<>();
        this.instructors = new ArrayList<>();
        this.sessions = new ArrayList<>();
        this.actionsHistory = new ArrayList<>();
        this.balance = new Balance(0);
    }

    // Get the single instance of gym.management.Gym
    public static Gym getInstance() {
        if (instance == null) {
            instance = new Gym();
        }
        return instance;
    }

    // Set gym name
    public void setName(String name) {
        this.name = name;
    }

    // Get gym name
    public String getName() {
        return name;
    }

    // Set a secretary for the gym
    public void setSecretary(Person person, int salary) {
        this.secretary = new Secretary(person, this, salary);
    }

    // Get the secretary of the gym
    public Secretary getSecretary() {
        return secretary;
    }

    // Add a client to the gym
    public void addClient(Client client) {
        clients.add(client);
    }

    // Remove a client from the gym
    public void removeClient(Client client) {
        clients.remove(client);
    }

    // Get the list of clients
    public List<Client> getClients() {
        return clients;
    }

    // Add an instructor to the gym
    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
    }

    // Get the list of instructors
    public List<Instructor> getInstructors() {
        return instructors;
    }

    // Add a session to the gym
    public void addSession(Session session) {
        sessions.add(session);
    }

    // Get the list of sessions
    public List<Session> getSessions() {
        return sessions;
    }

    // Add funds to the gym balance
    public void addBalance(int amount) {
        balance.addBalance(amount);
    }

    // Get the current balance of the gym
    public double getBalance() {
        return this.balance.getBalance();
    }

    // Add an action to the actions history log
    public void addAction(String action) {
        actionsHistory.add(action);
    }

    // Get the actions history
    public List<String> getActionsHistory() {
        return actionsHistory;
    }

    // Print all gym information
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gym Name: ").append(name).append("\n");
        sb.append("Gym Secretary: ").append(secretary).append("\n");
        sb.append("Gym Balance: ").append(balance.getBalance()).append("\n\n");

        sb.append("Clients Data:\n");
        for (Client client : clients) {
            sb.append(client).append("\n");
        }

        sb.append("\nEmployees Data:\n");
        for (Instructor instructor : instructors) {
            sb.append(instructor).append("\n");
        }
        sb.append(secretary).append("\n");

        sb.append("\nSessions Data:\n");
        for (Session session : sessions) {
            sb.append(session).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
