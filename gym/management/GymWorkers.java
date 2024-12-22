package gym.management;

import gym.customers.Person;

public class GymWorkers extends Person {
    private int salary;
    public GymWorkers(Person person, int salary) {
        super(person);
        this.salary = salary;
    }
    public int getSalary() {
        return salary;
    }
    @Override
    public String toString() {
        return "ID: " + getId() +
                " | Name: " + getName() +
                " | Gender: " + getGender() +
                " | Birthday: " + getBirthDate() +
                " | Age: " + getAge() +
                " | Balance: " + getBalance() +
                " | Role: " + this.getClass()+
                " | Salary per Hour: " + getSalary();
    }
}
