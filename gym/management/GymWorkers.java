package gym.management;

import gym.customers.Person;

/**
 * Represents a worker in the gym, extending the base Person class.
 * Includes additional attributes specific to workers, such as salary.
 */
public class GymWorkers extends Person {
    private int salary; // Salary of the gym worker

    /**
     * Constructor to create a GymWorker instance.
     *
     * @param person The person object representing the worker's basic details.
     * @param salary The salary of the worker.
     */
    public GymWorkers(Person person, int salary) {
        super(person); // Call the copy constructor of the Person class
        this.salary = salary;
    }

    /**
     * Gets the salary of the gym worker.
     *
     * @return The salary of the worker.
     */
    public int getSalary() {
        return salary;
    }

    /**
     * Provides a string representation of the gym worker, including personal details,
     * role, and salary.
     *
     * @return A string describing the gym worker.
     */
    @Override
    public String toString() {
        return "ID: " + getId() +
                " | Name: " + getName() +
                " | Gender: " + getGender() +
                " | Birthday: " + getBirthDate() +
                " | Age: " + getAge() +
                " | Balance: " + getBalance() +
                " | Role: " + this.getClass() +
                " | Salary per Hour: " + getSalary();
    }
}
