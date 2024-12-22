package gym.management;

import gym.Balance;
import gym.customers.Client;
import gym.customers.Person;
import gym.management.sessions.Session;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents a gym that manages clients, instructors, sessions, and finances.
 * Implements the Singleton design pattern to ensure only one instance exists.
 */
public class Gym {
    private static Gym instance; // Singleton instance of Gym

    private String name; // Name of the gym
    private Secretary secretary; // Gym secretary
    private Balance balance; // Financial balance of the gym
    private List<Client> clients; // List of registered clients
    private List<Instructor> instructors; // List of employed instructors
    private List<Session> sessions; // List of available sessions
    private List<String> actionsHistory; // Log of actions performed in the gym

    /**
     * Private constructor to enforce Singleton design pattern.
     */
    private Gym() {
        this.clients = new ArrayList<>();
        this.instructors = new ArrayList<>();
        this.sessions = new ArrayList<>();
        this.actionsHistory = new ArrayList<>();
        this.balance = new Balance(0);
    }

    /**
     * Returns the Singleton instance of the Gym class.
     *
     * @return The single instance of Gym.
     */
    public static Gym getInstance() {
        if (instance == null) {
            instance = new Gym();
        }
        return instance;
    }

    /**
     * Sets the name of the gym.
     *
     * @param name The name of the gym.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the gym.
     *
     * @return The gym's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Assigns a secretary to the gym.
     *
     * @param person The person to assign as secretary.
     * @param salary The salary of the secretary.
     */
    public void setSecretary(Person person, int salary) {
        this.secretary = new Secretary(person, this, salary);
    }

    /**
     * Gets the current secretary of the gym.
     *
     * @return The secretary of the gym.
     */
    public Secretary getSecretary() {
        return secretary;
    }

    /**
     * Adds a new client to the gym.
     *
     * @param client The client to be added.
     */
    public void addClient(Client client) {
        clients.add(client);
    }

    /**
     * Removes an existing client from the gym.
     *
     * @param client The client to be removed.
     */
    public void removeClient(Client client) {
        clients.remove(client);
    }

    /**
     * Gets the list of all registered clients.
     *
     * @return A list of clients.
     */
    public List<Client> getClients() {
        return clients;
    }

    /**
     * Adds a new instructor to the gym.
     *
     * @param instructor The instructor to be added.
     */
    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
    }

    /**
     * Gets the list of all employed instructors.
     *
     * @return A list of instructors.
     */
    public List<Instructor> getInstructors() {
        return instructors;
    }

    /**
     * Adds a new session to the gym's schedule.
     *
     * @param session The session to be added.
     */
    public void addSession(Session session) {
        sessions.add(session);
    }

    /**
     * Gets the list of all scheduled sessions.
     *
     * @return A list of sessions.
     */
    public List<Session> getSessions() {
        return sessions;
    }

    /**
     * Adds funds to the gym's balance.
     *
     * @param amount The amount to be added to the balance.
     */
    public void addBalance(int amount) {
        balance.addBalance(amount);
    }

    /**
     * Gets the current financial balance of the gym.
     *
     * @return The gym's balance.
     */
    public double getBalance() {
        return this.balance.getBalance();
    }

    /**
     * Logs an action to the gym's actions history.
     *
     * @param action The action to be logged.
     */
    public void addAction(String action) {
        actionsHistory.add(action);
    }

    /**
     * Gets the history of actions performed in the gym.
     *
     * @return A list of logged actions.
     */
    public List<String> getActionsHistory() {
        return actionsHistory;
    }

    /**
     * Provides a detailed string representation of the gym's information,
     * including its name, secretary, balance, clients, instructors, and sessions.
     *
     * @return A string describing the gym's data.
     */
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
        sb.deleteCharAt(sb.length() - 1); // Remove the last unnecessary newline character
        return sb.toString();
    }
}
