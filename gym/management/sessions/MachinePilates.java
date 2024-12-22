package gym.management.sessions;

import gym.management.Instructor;

public class MachinePilates extends Session {
    // Constructor
    public MachinePilates(String sessionTime, Instructor instructor, ForumType forumType) {
        super(sessionTime, instructor, 10, 80,forumType,"MachinePilates"); // 10 max participants, price 80
    }


    @Override
    public String toString() {
        return "Session Type: " + "MachinePilates | Date: " + sessionTime +
                " | Forum: "+forumType +
                " | Instructor: " + instructor.getName() +
                " | Participants: " + clients.size() + "/" + maxParticipants;
    }
}
