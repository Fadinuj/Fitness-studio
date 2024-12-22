package gym.customers;

import gym.Balance;
import gym.DateUtils;

public class Person {
    private static int idCounter = 1111; // Static counter for generating unique IDs
    private int id;                      // Unique ID for each person
    private String name;                 // Name of the person
    private Balance balance;              // gym.Balance of the person
    private Gender gender;               // gym.customers.Gender of the person
    private String birthDate;            // Birth date in format "dd-MM-yyyy"

    // Constructor
    public Person(String name, int balance, Gender gender, String birthDate) {
        this.id = idCounter++;
        this.name = name;
        this.balance = new Balance(balance);
        this.gender = gender;
        this.birthDate = birthDate;
    }

    // Copy constructor
    public Person(Person other) {
        this.id = other.id;
        this.name = other.name;
        this.balance = other.balance;
        this.gender = other.gender;
        this.birthDate = other.birthDate;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return this.balance.getBalance();
    }

    public Gender getGender() {
        return gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    // Calculate and return the age based on the birth date
    public int getAge() {
        return DateUtils.getAge(this.birthDate);
    }

    public void addbalance(int amount) {
        balance.addBalance(amount);
    }

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

