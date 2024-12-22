package gym.management;

import gym.DateUtils;
import gym.customers.Client;
import gym.management.sessions.Session;
/**
 * Handles sending notifications to clients registered in the gym.
 * Supports sending messages to specific sessions, sessions on a specific date, or all clients in the gym.
 */
public class NotifyClients {
    private Gym _gym; // Reference to the gym instance

    /**
     * Constructor to initialize NotifyClients with the gym instance.
     *
     * @param gym The gym instance where notifications are to be sent.
     */
    public NotifyClients(Gym gym) {
        this._gym = gym;
    }

    /**
     * Sends a notification to all clients registered for a specific session.
     *
     * @param session The session to notify clients about.
     * @param message The message to be sent to the clients.
     */
    public void notify(Session session, String message) {
        // Notify all clients registered for the session
        session.notifyClients(message);

        // Format the session time and log the notification action
        String formattedDate = DateUtils.formatDate(session.getSessionTime());
        _gym.addAction("A message was sent to everyone registered for session " + session.getSessionType() +
                " on " + formattedDate.replace(" ", "T") + " : " + message);
    }

    /**
     * Sends a notification to all clients registered for sessions on a specific date.
     *
     * @param date    The date of the sessions in "dd-MM-yyyy" format.
     * @param message The message to be sent to the clients.
     */
    public void notify(String date, String message) {
        // Notify all sessions on the given date
        for (Session session : _gym.getSessions()) {
            if (session.getSessionTime().startsWith(date)) {
                session.notifyClients(message);
            }
        }

        // Format the date and log the notification action
        String formattedDate1 = DateUtils.formatDate1(date);
        _gym.addAction("A message was sent to everyone registered for a session on " + formattedDate1 + " : " + message);
    }

    /**
     * Sends a notification to all clients registered in the gym.
     *
     * @param message The message to be sent to all clients.
     */
    public void notify(String message) {
        // Notify all clients in the gym
        for (Client client : _gym.getClients()) {
            client.update(message);
        }

        // Log the notification action
        _gym.addAction("A message was sent to all gym clients: " + message);
    }
}
