package gym.management.sessions;

import gym.management.Instructor;
/**
 * Represents a Pilates session, a popular gym session with specific configurations.
 */
public class Pilates extends Session {

    /**
     * Constructs a Pilates session.
     *
     * @param sessionTime The time of the session in the format "yyyy-MM-dd HH:mm".
     * @param instructor  The instructor assigned to the session.
     * @param forumType   The type of forum (e.g., All, Female, Male, Seniors).
     */
    public Pilates(String sessionTime, Instructor instructor, ForumType forumType) {
        super(sessionTime, instructor, 30, 60, forumType, "Pilates"); // 30 max participants, price 60
    }

    /**
     * Overrides the toString method to provide a descriptive string representation of the session.
     *
     * @return A string representation of the Pilates session, including its details.
     */
    @Override
    public String toString() {
        return "Session Type: " + "Pilates | Date: " + sessionTime +
                " | Forum: " + forumType +
                " | Instructor: " + instructor.getName() +
                " | Participants: " + clients.size() + "/" + maxParticipants;
    }
}
