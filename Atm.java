import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// User class to store user information
class User {
    private String userId;
    private String pin;
    private Account account;

    public User(String userId, String pin, double initialBalance) {
        this.userId = userId;
        this.pin = pin;
        this.account = new Account(initialBalance);
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }

    public Account getAccount() {
        return account;
    }
}

// Account class to store account details and transaction history
class Account {
    private double balance;
    private List<Transaction> transactionHistory;

    public Account(double initialBalance) {
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        this.transactionHistory.add(new Transaction("Initial Balance", initialBalance));
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount));
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdraw", amount));
            return true;
        } else {
            return false;
        }
    }

    public void addTransaction(String type, double amount) {
        transactionHistory.add(new Transaction(type, amount));
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}

// Transaction class to store details of each transaction
class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return type + ": $" + amount;
    }
}

// ATM class containing core functionalities of the ATM
public class ATM {
    private Map<String, User> users;
    private User currentUser;
    private Scanner scanner;

    public ATM() {
        users = new HashMap<>();
        scanner = new Scanner(System.in);
        initializeUsers();
    }

    private void initializeUsers() {
        users.put("user1", new User("user1", "1234", 1000.0));
        users.put("user2", new User("user2", "5678", 1500.0));
    }

    public void start() {
        System.out.println("Welcome to the ATM!");

        if (authenticateUser()) {
            showMenu();
        } else {
            System.out.println("Invalid user ID or PIN. Exiting.");
        }
    }

    private boolean authenticateUser() {
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        User user = users.get(userId);

        if (user != null && user.getPin().equals(pin)) {
            currentUser = user;
            return true;
        }

        return false;
    }

    private void showMenu() {
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    showTransactionHistory();
                    break;
                case 2:
                    performWithdrawal();
                    break;
                case 3:
                    performDeposit();
                    break;
                case 4:
                    performTransfer();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void showTransactionHistory() {
        System.out.println("\nTransaction History:");
        for (Transaction transaction : currentUser.getAccount().getTransactionHistory()) {
            System.out.println(transaction);
        }
    }

    private void performWithdrawal() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        if (currentUser.getAccount().withdraw(amount)) {
            System.out.println("Withdrawal successful. Your new balance is $" + currentUser.getAccount().getBalance());
        } else {
            System.out.println("Insufficient balance. Withdrawal failed.");
        }
    }

    private void performDeposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        currentUser.getAccount().deposit(amount);
        System.out.println("Deposit successful. Your new balance is $" + currentUser.getAccount().getBalance());
    }

    private void performTransfer() {
        System.out.print("Enter recipient user ID: ");
        String recipientId = scanner.nextLine();
        User recipient = users.get(recipientId);

        if (recipient == null) {
            System.out.println("Recipient user ID not found. Transfer failed.");
            return;
        }

        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        if (currentUser.getAccount().withdraw(amount)) {
            recipient.getAccount().deposit(amount);
            currentUser.getAccount().addTransaction("Transfer to " + recipientId, amount);
            recipient.getAccount().addTransaction("Transfer from " + currentUser.getUserId(), amount);
            System.out.println("Transfer successful. Your new balance is $" + currentUser.getAccount().getBalance());
        } else {
            System.out.println("Insufficient balance. Transfer failed.");
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
