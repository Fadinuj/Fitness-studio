package gym.management.sessions;

import gym.management.Instructor;

public class Ninja extends Session {
    // Constructor
    public Ninja(String sessionTime, Instructor instructor, ForumType forumType) {
        super(sessionTime, instructor, 5, 150,forumType,"Ninja"); // 5 max participants, price 150
    }


    @Override
    public String toString() {
        return "Session Type: " + "Ninja | Date: " + sessionTime +
                " | Forum: "+forumType +
                " | Instructor: " + instructor.getName() +
                " | Participants: " + clients.size() + "/" + maxParticipants;
    }
}
