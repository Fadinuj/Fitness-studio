package gym.management.sessions;

import gym.management.Instructor;
/**
 * Represents a Ninja session, a high-intensity gym session with specific configurations.
 */
public class Ninja extends Session {

    /**
     * Constructs a Ninja session.
     *
     * @param sessionTime The time of the session in the format "yyyy-MM-dd HH:mm".
     * @param instructor  The instructor assigned to the session.
     * @param forumType   The type of forum (e.g., All, Female, Male, Seniors).
     */
    public Ninja(String sessionTime, Instructor instructor, ForumType forumType) {
        super(sessionTime, instructor, 5, 150, forumType, "Ninja"); // 5 max participants, price 150
    }

    /**
     * Overrides the toString method to provide a descriptive string representation of the session.
     *
     * @return A string representation of the Ninja session, including its details.
     */
    @Override
    public String toString() {
        return "Session Type: " + "Ninja | Date: " + sessionTime +
                " | Forum: " + forumType +
                " | Instructor: " + instructor.getName() +
                " | Participants: " + clients.size() + "/" + maxParticipants;
    }
}
