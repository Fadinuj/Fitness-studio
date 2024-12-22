package gym.management.sessions;

import gym.management.Instructor;

public class ThaiBoxing extends Session {
    public ThaiBoxing(String sessionTime, Instructor instructor, ForumType forumType) {
        super(sessionTime, instructor, 20, 100,forumType,"ThaiBoxing"); // 20 max participants, price 100
    }

    @Override
    public String toString() {
        return "Session Type: " + "ThaiBoxing | Date: " + sessionTime +
                " | Forum: "+forumType +
                " | Instructor: " + instructor.getName() +
                " | Participants: " + clients.size() + "/" + maxParticipants;
    }
}
