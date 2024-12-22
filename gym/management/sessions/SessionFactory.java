package gym.management.sessions;

import gym.management.Instructor;

public class SessionFactory {
    SessionType sessionType;
    public Session buildSession(String sessionTime, Instructor instructor, SessionType sessionType, ForumType forumType) {
        if (sessionType == null) {
            throw new IllegalArgumentException("Session type cannot be null");
        }
        this.sessionType = sessionType;
        switch (sessionType) {
            case Ninja:
                return new Ninja(sessionTime, instructor, forumType);

            case Pilates:
                return new Pilates(sessionTime, instructor, forumType);

            case ThaiBoxing:
                return new ThaiBoxing(sessionTime, instructor, forumType);

            case MachinePilates:
                return new MachinePilates(sessionTime, instructor, forumType);

        }
        return null;
    }
}
