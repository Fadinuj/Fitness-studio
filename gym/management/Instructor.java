package gym.management;

import gym.customers.Person;
import gym.management.sessions.SessionType;

import java.util.List;
/**
 * Represents an instructor in the gym who conducts specific types of sessions.
 * Extends the GymWorkers class and includes additional attributes for session qualifications.
 */
public class Instructor extends GymWorkers {
    private List<SessionType> qualifiedSessionTypes; // List of session types the instructor is qualified to teach

    /**
     * Constructor to create an Instructor instance.
     *
     * @param person          The Person object representing the instructor's basic details.
     * @param salaryPerHour   The instructor's salary per hour.
     * @param sessionTypes    List of session types the instructor is qualified to teach.
     */
    public Instructor(Person person, int salaryPerHour, List<SessionType> sessionTypes) {
        super(person, salaryPerHour); // Call the GymWorkers constructor
        this.qualifiedSessionTypes = sessionTypes;
    }

    /**
     * Retrieves the list of session types the instructor is qualified to teach.
     *
     * @return A list of qualified session types.
     */
    public List<SessionType> getQualifiedSessionTypes() {
        return qualifiedSessionTypes;
    }

    /**
     * Provides a string representation of the instructor, including personal details,
     * salary, and the session types they are certified to teach.
     *
     * @return A string describing the instructor.
     */
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
                " | Certified Classes: " + qualifiedSessionTypes.toString().replace("[", "").replace("]", "");
    }
}
