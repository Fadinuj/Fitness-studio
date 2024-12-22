package gym.management;

import gym.customers.Person;
import gym.management.sessions.SessionType;

import java.util.List;

public class Instructor extends GymWorkers {
    private List<SessionType> qualifiedSessionTypes; // List of qualified session types

    // Constructor
    public Instructor(Person person, int salaryPerHour, List<SessionType> sessionTypes) {
        super(person,salaryPerHour);
        this.qualifiedSessionTypes = sessionTypes;
    }
    // Get qualified session types
    public List<SessionType> getQualifiedSessionTypes() {
        return qualifiedSessionTypes;
    }

    @Override
    public String toString() {
        return "ID: " + getId() +
                " | Name: " + getName() +
                " | Gender: " + getGender() +
                " | Birthday: " + getBirthDate() +
                " | Age: " + getAge() +
                " | Balance: " + this.getBalance() +
                " | Role: " + this.getClass().getSimpleName() +
                " | Salary per Hour: " + getSalary() +
                " | Certified Classes: " + qualifiedSessionTypes.toString().replace("[","").replace("]","");
    }
}
