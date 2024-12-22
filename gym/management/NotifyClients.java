package gym.management;

import gym.DateUtils;
import gym.customers.Client;
import gym.management.sessions.Session;

public class NotifyClients {
    private Gym _gym;
    public NotifyClients(Gym gym) {
        this._gym = gym;
    }
    public void notify(Session session,String message) {
        session.notifyClients(message);
        String formattedDate = DateUtils.formatDate(session.getSessionTime());
        _gym.addAction("A message was sent to everyone registered for session " + session.getSessionType() +
                " on " + formattedDate.replace(" ","T") + " : " + message);
    }
    public void notify(String date, String message) {
        for (Session session : _gym.getSessions()) {
            if (session.getSessionTime().startsWith(date)) {
                session.notifyClients(message);
            }
        }
        //String formattedDate1 = DateUtils.formatDate1(date);
        _gym.addAction("A message was sent to everyone registered for a session on " + date + " : " + message);
    }
    public void notify(String message) {
        for (Client client : _gym.getClients()) {
            client.update(message);
        }
        _gym.addAction("A message was sent to all gym clients: " + message);
    }
}
