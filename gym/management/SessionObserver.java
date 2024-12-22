package gym.management;
/**
 * Interface for observing sessions and receiving updates.
 * Classes implementing this interface can receive notifications about session-related events.
 */
public interface SessionObserver {

    /**
     * Method to receive an update or notification.
     *
     * @param message The message or notification to be sent to the observer.
     */
    public void update(String message);
}