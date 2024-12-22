package gym.management.sessions;

import gym.management.Instructor;

public class Pilates extends Session {
    public Pilates(String sessionTime, Instructor instructor, ForumType forumType) {
        super(sessionTime, instructor, 30, 60,forumType,"Pilates"); // 30 max participants, price 60
    }

    @Override
    public String toString() {
        return "Session Type: " + "Pilates | Date: " + sessionTime +
                " | Forum: "+forumType +
                " | Instructor: " + instructor.getName() +
                " | Participants: " + clients.size() + "/" + maxParticipants;
    }
}
