//Autor Kamil Pajączkowski
import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public Account createAccount(String accountNumber, String password) {
        if (!accounts.containsKey(accountNumber)) {
            Account account = new Account(accountNumber, password);
            accounts.put(accountNumber, account);
            return account;
        }
        return null;
    }

    public Account login(String accountNumber, String password) {
        Account account = accounts.get(accountNumber);
        if (account != null && account.login(password)) {
            return account;
        }
        return null;
    }

    public boolean transfer(String fromAccount, String toAccount, double amount) {
        Account sender = accounts.get(fromAccount);
        Account receiver = accounts.get(toAccount);

        if (sender != null && receiver != null && sender.withdraw(amount)) {
            receiver.deposit(amount);
            sender.getTransactionHistory().add("Przelew do " + toAccount + ": " + amount);
            receiver.getTransactionHistory().add("Przelew od " + fromAccount + ": " + amount);
            return true;
        }
        return false;
    }
}
