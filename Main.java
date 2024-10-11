//Autor Kamil Pajączkowski
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);
        Account currentAccount = null;

        while (true) {
            System.out.println("1. Utwórz konto");
            System.out.println("2. Zaloguj się");
            System.out.println("3. Zakończ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Konsumowanie nowej linii

            if (choice == 1) {
                System.out.println("Podaj numer konta:");
                String accountNumber = scanner.nextLine();
                System.out.println("Podaj hasło:");
                String password = scanner.nextLine();

                if (bank.createAccount(accountNumber, password) != null) {
                    System.out.println("Konto zostało utworzone.");
                } else {
                    System.out.println("Konto o tym numerze już istnieje.");
                }
            } else if (choice == 2) {
                System.out.println("Podaj numer konta:");
                String accountNumber = scanner.nextLine();
                System.out.println("Podaj hasło:");
                String password = scanner.nextLine();

                currentAccount = bank.login(accountNumber, password);
                if (currentAccount != null) {
                    System.out.println("Zalogowano pomyślnie.");
                    handleAccountOperations(scanner, currentAccount, bank);
                } else {
                    System.out.println("Błędne dane logowania.");
                }
            } else if (choice == 3) {
                break;
            }
        }

        scanner.close();
    }

    private static void handleAccountOperations(Scanner scanner, Account account, Bank bank) {
        while (true) {
            System.out.println("1. Sprawdź saldo");
            System.out.println("2. Wpłać pieniądze");
            System.out.println("3. Wypłać pieniądze");
            System.out.println("4. Przelej pieniądze");
            System.out.println("5. Historia transakcji");
            System.out.println("6. Wyloguj");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Konsumowanie nowej linii

            if (choice == 1) {
                System.out.println("Saldo: " + account.getBalance());
            } else if (choice == 2) {
                System.out.println("Podaj kwotę do wpłaty:");
                double amount = scanner.nextDouble();
                account.deposit(amount);
                System.out.println("Wpłacono: " + amount);
            } else if (choice == 3) {
                System.out.println("Podaj kwotę do wypłaty:");
                double amount = scanner.nextDouble();
                if (account.withdraw(amount)) {
                    System.out.println("Wypłacono: " + amount);
                } else {
                    System.out.println("Niewystarczające środki.");
                }
            } else if (choice == 4) {
                System.out.println("Podaj numer konta odbiorcy:");
                String toAccount = scanner.nextLine();
                System.out.println("Podaj kwotę:");
                double amount = scanner.nextDouble();
                if (bank.transfer(account.getAccountNumber(), toAccount, amount)) {
                    System.out.println("Przelew udany.");
                } else {
                    System.out.println("Przelew nieudany.");
                }
            } else if (choice == 5) {
                System.out.println("Historia transakcji:");
                for (String transaction : account.getTransactionHistory()) {
                    System.out.println(transaction);
                }
            } else if (choice == 6) {
                break;
            }
        }
    }
}
