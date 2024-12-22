package gym.customers;

import gym.Balance;
import gym.DateUtils;
/**
 * Represents a person with basic attributes such as name, gender, birth date, and balance.
 */
public class Person {
    private static int idCounter = 1111; // Static counter for generating unique IDs
    private int id;                      // Unique ID for each person
    private String name;                 // Name of the person
    private Balance balance;             // Balance of the person
    private Gender gender;               // Gender of the person
    private String birthDate;            // Birth date in format "dd-MM-yyyy"

    /**
     * Constructor to create a new person with specified attributes.
     *
     * @param name      The name of the person.
     * @param balance   The initial balance of the person.
     * @param gender    The gender of the person.
     * @param birthDate The birth date of the person in "dd-MM-yyyy" format.
     */
    public Person(String name, int balance, Gender gender, String birthDate) {
        this.id = idCounter++; // Generate a unique ID
        this.name = name;
        this.balance = new Balance(balance);
        this.gender = gender;
        this.birthDate = birthDate;
    }

    /**
     * Copy constructor to create a duplicate of another person.
     *
     * @param other The person to copy.
     */
    public Person(Person other) {
        this.id = other.id;
        this.name = other.name;
        this.balance = other.balance;
        this.gender = other.gender;
        this.birthDate = other.birthDate;
    }

    /**
     * Gets the unique ID of the person.
     *
     * @return The ID of the person.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the balance of the person.
     *
     * @return The current balance.
     */
    public int getBalance() {
        return this.balance.getBalance();
    }

    /**
     * Gets the gender of the person.
     *
     * @return The gender of the person.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Gets the birth date of the person.
     *
     * @return The birth date in "dd-MM-yyyy" format.
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Calculates and returns the age of the person based on their birth date.
     *
     * @return The calculated age.
     */
    public int getAge() {
        return DateUtils.getAge(this.birthDate);
    }

    /**
     * Adds a specified amount to the person's balance.
     *
     * @param amount The amount to add (can be negative to deduct).
     */
    public void addbalance(int amount) {
        balance.addBalance(amount);
    }

    /**
     * Overrides the toString method to provide a detailed string representation of the person.
     *
     * @return A string representing the person's details.
     */
    @Override
    public String toString() {
        return "ID: " + id +
                " | Name: " + name +
                " | Gender: " + gender +
                " | Birthday: " + birthDate +
                " | Age: " + getAge() +
                " | Balance: " + balance.getBalance();
    }
}

