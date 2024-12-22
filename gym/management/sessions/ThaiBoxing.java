package gym.management.sessions;

import gym.management.Instructor;
/**
 * Represents a Thai Boxing session at the gym.
 */
public class ThaiBoxing extends Session {

    /**
     * Constructs a new ThaiBoxing session with the specified parameters.
     *
     * @param sessionTime The time of the session in the format "yyyy-MM-dd HH:mm".
     * @param instructor  The instructor leading the session.
     * @param forumType   The forum type (e.g., All, Male, Female, Seniors).
     */
    public ThaiBoxing(String sessionTime, Instructor instructor, ForumType forumType) {
        super(sessionTime, instructor, 20, 100, forumType, "ThaiBoxing"); // 20 max participants, price 100
    }

    /**
     * Returns a string representation of the ThaiBoxing session.
     *
     * @return A string describing the session, including its type, date, forum, instructor, and participant count.
     */
    @Override
    public String toString() {
        return "Session Type: " + "ThaiBoxing | Date: " + sessionTime +
                " | Forum: " + forumType +
                " | Instructor: " + instructor.getName() +
                " | Participants: " + clients.size() + "/" + maxParticipants;
    }
}
