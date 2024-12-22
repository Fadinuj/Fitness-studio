package gym.management;

import gym.DateUtils;
import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InstructorNotQualifiedException;
import gym.Exception.InvalidAgeException;
import gym.customers.Client;
import gym.customers.Person;
import gym.management.sessions.*;

import java.util.List;
/**
 * Represents a secretary in the gym, responsible for managing clients, sessions, and notifications.
 */
public class Secretary extends GymWorkers {
    private Gym gym; // Singleton gym instance
    private NotifyClients notifyClients; // Utility for sending notifications
    private SessionFactory sessionFactory; // Factory for creating sessions

    /**
     * Constructor to create a Secretary.
     *
     * @param person  The person details for the secretary.
     * @param gym     The gym instance the secretary belongs to.
     * @param salary  The salary of the secretary.
     */
    public Secretary(Person person, Gym gym, int salary) {
        super(person, salary);
        this.gym = gym;
        sessionFactory = new SessionFactory();
        notifyClients = new NotifyClients(this.gym);
        gym.addAction("A new secretary has started working at the gym: " + this.getName());
    }

    /**
     * Registers a new client to the gym.
     *
     * @param person The person details of the client.
     * @return The registered client.
     * @throws InvalidAgeException       If the client's age is less than 18.
     * @throws DuplicateClientException  If the client is already registered.
     */
    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        if (person.getAge() < 18) {
            throw new InvalidAgeException();
        }

        Client client = new Client(person);
        if (gym.getClients().contains(client)) {
            throw new DuplicateClientException("Error: The client is already registered");
        }

        gym.addClient(client);
        gym.addAction("Registered new client: " + client.getName());
        return client;
    }

    /**
     * Unregisters a client from the gym.
     *
     * @param client The client to be removed.
     * @throws ClientNotRegisteredException If the client is not registered.
     */
    public void unregisterClient(Client client) throws ClientNotRegisteredException {
        if (!gym.getClients().contains(client)) {
            throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");
        }

        gym.removeClient(client);
        gym.addAction("Unregistered client: " + client.getName());
    }

    /**
     * Hires a new instructor for the gym.
     *
     * @param person        The person details for the instructor.
     * @param salary        The salary per hour for the instructor.
     * @param sessionTypes  The list of session types the instructor is qualified for.
     * @return The hired instructor.
     */
    public Instructor hireInstructor(Person person, int salary, List<SessionType> sessionTypes) {
        Instructor instructor = new Instructor(person, salary, sessionTypes);
        gym.addInstructor(instructor);
        gym.addAction("Hired new instructor: " + person.getName() + " with salary per hour: " + salary);
        return instructor;
    }

    /**
     * Adds a new session to the gym.
     *
     * @param sessionType The type of the session.
     * @param date        The date of the session.
     * @param forumType   The forum type of the session.
     * @param instructor  The instructor for the session.
     * @return The created session.
     * @throws InstructorNotQualifiedException If the instructor is not qualified to conduct the session type.
     */
    public Session addSession(SessionType sessionType, String date, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        if (!instructor.getQualifiedSessionTypes().contains(sessionType)) {
            throw new InstructorNotQualifiedException();
        }

        Session session = sessionFactory.buildSession(date, instructor, sessionType, forumType);
        gym.addSession(session);
        String formattedDate = DateUtils.formatDate(date);
        gym.addAction("Created new session: " + sessionType + " on " + formattedDate.replace(" ", "T") + " with instructor: " + instructor.getName());
        return session;
    }

    /**
     * Registers a client to a session.
     *
     * @param client  The client to be registered.
     * @param session The session the client wants to join.
     * @throws DuplicateClientException      If the client is already registered for the session.
     * @throws ClientNotRegisteredException If the client is not registered with the gym.
     */
    public void registerClientToLesson(Client client, Session session) throws DuplicateClientException, ClientNotRegisteredException {
        validateActiveSecretary();
        boolean errorFlag = false;

        if (!gym.getClients().contains(client)) {
            throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");
        }

        if (session.getClients().contains(client)) {
            throw new DuplicateClientException("Error: The client is already registered for this lesson");
        }

        if (DateUtils.isExpiredDate(session.getSessionTime())) {
            gym.addAction("Failed registration: Session is not in the future");
            errorFlag = true;
        }

        if (session.isFull()) {
            gym.addAction("Failed registration: No available spots for session");
            errorFlag = true;
        }

        if (!session.isForumCorrect(client)) {
            if (session.isForumGender()) {
                gym.addAction("Failed registration: Client's gender doesn't match the session's gender requirements");
            } else {
                gym.addAction("Failed registration: Client doesn't meet the age requirements for this session (" + session.getForumType() + ")");
            }
            errorFlag = true;
        }

        if (!session.hasBalance(client)) {
            gym.addAction("Failed registration: Client doesn't have enough balance");
            errorFlag = true;
        }

        if (!errorFlag) {
            client.chargeClient(session.getPrice());
            session.addClient(client);
            String formattedDate = DateUtils.formatDate(session.getSessionTime());
            gym.addBalance(session.getPrice());
            gym.addAction("Registered client: " + client.getName() + " to session: "
                    + session.getClass().getSimpleName() + " on " + formattedDate.replace(" ", "T")
                    + " for price: " + session.getPrice());
        }
    }

    /**
     * Validates if the secretary is still active in the gym.
     */
    private void validateActiveSecretary() {
        if (this != gym.getSecretary()) {
            throw new NullPointerException();
        }
    }

    /**
     * Pays salaries to all employees in the gym.
     */
    public void paySalaries() {
        int totalSalaries = 0;

        for (Session session : gym.getSessions()) {
            totalSalaries += session.getInstructor().getSalary();
            session.getInstructor().addbalance(session.getInstructor().getSalary());
        }

        this.addbalance(this.getSalary());
        totalSalaries += this.getSalary();
        gym.addBalance(-totalSalaries);
        gym.addAction("Salaries have been paid to all employees");
    }

    /**
     * Sends notifications to all clients in a session.
     *
     * @param session The session to send notifications to.
     * @param message The notification message.
     */
    public void notify(Session session, String message) {
        notifyClients.notify(session, message);
    }

    /**
     * Sends notifications to clients registered for a specific date.
     *
     * @param date    The date to send notifications for.
     * @param message The notification message.
     */
    public void notify(String date, String message) {
        notifyClients.notify(date, message);
    }

    /**
     * Sends notifications to all clients in the gym.
     *
     * @param message The notification message.
     */
    public void notify(String message) {
        notifyClients.notify(message);
    }

    /**
     * Prints the action history of the gym.
     */
    public void printActions() {
        List<String> actions = gym.getActionsHistory();
        for (String action : actions) {
            System.out.println(action);
        }
    }

    @Override
    public String toString() {
        return "ID: " + getId() +
                " | Name: " + getName() +
                " | Gender: " + getGender() +
                " | Birthday: " + getBirthDate() +
                " | Age: " + getAge() +
                " | Balance: " + getBalance() +
                " | Role: " + this.getClass().getSimpleName() +
                " | Salary per Month: " + this.getSalary();
    }
}

