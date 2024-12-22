package gym.management.sessions;

import gym.management.Instructor;
/**
 * Represents a Machine Pilates session, a specialized gym session with specific configurations.
 */
public class MachinePilates extends Session {

    /**
     * Constructs a Machine Pilates session.
     *
     * @param sessionTime The time of the session in the format "yyyy-MM-dd HH:mm".
     * @param instructor  The instructor assigned to the session.
     * @param forumType   The type of forum (e.g., All, Female, Male, Seniors).
     */
    public MachinePilates(String sessionTime, Instructor instructor, ForumType forumType) {
        super(sessionTime, instructor, 10, 80, forumType, "MachinePilates"); // 10 max participants, price 80
    }

    /**
     * Overrides the toString method to provide a descriptive string representation of the session.
     *
     * @return A string representation of the Machine Pilates session, including its details.
     */
    @Override
    public String toString() {
        return "Session Type: " + "MachinePilates | Date: " + sessionTime +
                " | Forum: " + forumType +
                " | Instructor: " + instructor.getName() +
                " | Participants: " + clients.size() + "/" + maxParticipants;
    }
}
