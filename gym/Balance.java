package gym;

public class Balance {
    private int balance;
    public Balance(int balance) {
        this.balance = balance;
    }
    public int getBalance() {
        return balance;
    }
    public void addBalance(int balance) {
        this.balance += balance;
    }
}
