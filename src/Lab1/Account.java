package Lab1;

public class Account {

    private String name;
    private double balance;

    // Constructor
    public Account(String name, double balance) {
        this.name = name;

        if(balance < 0) {
            this.balance = 0;
        } else {
            this.balance = balance;
        }
    }

    public void deposit(double amount) {
        if(amount <= 0) {
            System.out.println("Invalid deposit amount !");
        } else {
            balance += amount;
        }
    }

    public void withdraw(double amount) {

        if(amount <= 0) {
            System.out.println("Invalid withdrawal amount !");
        }
        else if(amount > balance) {
            System.out.println("Insufficient balance !");
        }
        else {
            balance -= amount;
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }
}
