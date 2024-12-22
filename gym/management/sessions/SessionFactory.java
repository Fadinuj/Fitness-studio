package gym.management.sessions;

import gym.management.Instructor;
/**
 * Factory class for creating different types of sessions.
 */
public class SessionFactory {
    private SessionType sessionType; // Type of session being created

    /**
     * Builds a session based on the provided session type.
     *
     * @param sessionTime The time of the session in "yyyy-MM-dd HH:mm" format.
     * @param instructor  The instructor assigned to the session.
     * @param sessionType The type of the session (e.g., Ninja, Pilates, ThaiBoxing, MachinePilates).
     * @param forumType   The forum type for the session (e.g., All, Female, Male, Seniors).
     * @return A specific session object based on the session type.
     * @throws IllegalArgumentException if the session type is null.
     */
    public Session buildSession(String sessionTime, Instructor instructor, SessionType sessionType, ForumType forumType) {
        if (sessionType == null) {
            throw new IllegalArgumentException("Session type cannot be null");
        }
        this.sessionType = sessionType;
        return switch (sessionType) {
            case Ninja -> new Ninja(sessionTime, instructor, forumType);
            case Pilates -> new Pilates(sessionTime, instructor, forumType);
            case ThaiBoxing -> new ThaiBoxing(sessionTime, instructor, forumType);
            case MachinePilates -> new MachinePilates(sessionTime, instructor, forumType);
        };
    }
}
