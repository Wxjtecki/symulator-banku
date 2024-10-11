//Autor Kamil Pajączkowski
import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNumber;
    private String password;
    private double balance;
    private List<String> transactionHistory;

    public Account(String accountNumber, String password) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
        addTransaction("Konto utworzone.");
    }

    public boolean login(String password) {
        return this.password.equals(password);
    }

    public void deposit(double amount) {
        balance += amount;
        addTransaction("Wpłata: " + amount);
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            addTransaction("Wypłata: " + amount);
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    private void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }
}
