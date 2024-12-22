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

public class Secretary extends GymWorkers {
    private Gym gym;           // Singleton gym.management.Gym instance
    NotifyClients notifyClients;
    SessionFactory sessionFactory;
    // Constructor
    public Secretary(Person person, Gym gym, int salary) {
        super(person,salary); // Use gym.customers.Person constructor
        this.gym = gym;
        sessionFactory = new SessionFactory();
        notifyClients = new NotifyClients(this.gym);
        gym.addAction("A new secretary has started working at the gym: " + this.getName());
    }


    // Register a new client
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

    // Unregister a client
    public void unregisterClient(Client client) throws ClientNotRegisteredException {
        if (!gym.getClients().contains(client)) {
            throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");
        }

        gym.removeClient(client);
        gym.addAction("Unregistered client: " + client.getName());
    }

    // Hire a new instructor
    public Instructor hireInstructor(Person person, int salary, List<SessionType> sessionTypes) {
        Instructor instructor = new Instructor(person, salary, sessionTypes);
        gym.addInstructor(instructor);
        gym.addAction("Hired new instructor: " + person.getName() + " with salary per hour: " + salary);
        return instructor;
    }

    // Add a session
    public Session addSession(SessionType sessionType, String date, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        if (!instructor.getQualifiedSessionTypes().contains(sessionType)) {
            throw new InstructorNotQualifiedException();
        }

        // Create and add the session
        Session session = sessionFactory.buildSession(date,instructor,sessionType,forumType);


        gym.addSession(session);
        String formattedDate = DateUtils.formatDate(date);
        gym.addAction("Created new session: " + sessionType + " on " + formattedDate.replace(" ","T") + " with instructor: " + instructor.getName());
        return session;
    }

    // Register a client to a lesson
    public void registerClientToLesson(Client client, Session session) throws DuplicateClientException, ClientNotRegisteredException {
        // Check if the secretary is still active
        validateActiveSecretary();
        boolean erorFlag=false;
        // Check if client is registered in the gym
        if (!gym.getClients().contains(client)) {
            throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");
        }

        // Check if client is already registered for the session
        if (session.getClients().contains(client)) {
            throw new DuplicateClientException("Error: The client is already registered for this lesson");
        }
        if (DateUtils.isExpiredDate(session.getSessionTime()))
        {
            gym.addAction("Failed registration: Session is not in the future");
            erorFlag=true;
        }

        // Check if the session is full
        if (session.isFull()) {
            gym.addAction("Failed registration: No available spots for session");
            erorFlag=true;
        }

        // Check if the client meets the session requirements
        if (!session.isForumCorrect(client)) {
            if (session.isForumGender()) {
                gym.addAction("Failed registration: Client's gender doesn't match the session's gender requirements");
                erorFlag=true;
            }
            else
            {
                gym.addAction("Failed registration: Client doesn't meet the age requirements for this session (" + session.getForumType() + ")");
                erorFlag=true;
            }
        }

        // Check if client has enough balance
        if (!session.hasBalance(client)) {
            gym.addAction("Failed registration: Client doesn't have enough balance");
            erorFlag=true;
        }

        // Register the client
        if (!erorFlag) {
            client.chargeClient(session.getPrice());
            session.addClient(client);
            String formattedDate = DateUtils.formatDate(session.getSessionTime());
            gym.addBalance(session.getPrice());
            gym.addAction("Registered client: " + client.getName() + " to session: "
                    + session.getClass().getSimpleName() + " on " + formattedDate.replace(" ","T")
                    + " for price: " + session.getPrice());
        }
    }
    private void validateActiveSecretary() {
        if (this != gym.getSecretary()) {
            throw new NullPointerException();
        }
    }

    // Pay salaries to all employees
    public void paySalaries() {
        int totalSalaries = 0;

        for (Session session : gym.getSessions()) {
            totalSalaries += session.getInstructor().getSalary();
            session.getInstructor().addbalance(session.getInstructor().getSalary());
        }
        this.addbalance(this.getSalary()); //pay gym.management.Secretary Salary
        totalSalaries += this.getSalary();
        gym.addBalance(-totalSalaries);
        gym.addAction("Salaries have been paid to all employees");
    }

    // Send notifications to all clients in a session
    public void notify(Session session, String message) {
        notifyClients.notify(session, message);
    }

    // Send notifications to clients registered for a specific date
    public void notify(String date, String message) {
        notifyClients.notify(date, message);
    }

    // Send notifications to all clients
    public void notify(String message) {
        notifyClients.notify(message);
    }
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
                " | Birthday: " + getBirthDate()+
                " | Age: " + getAge() +
                " | Balance: " + getBalance() +
                " | Role: " + this.getClass().getSimpleName() +
                " | Salary per Month: " + this.getSalary();
    }
}

