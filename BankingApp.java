import java.util.Scanner;

class Account {
    private int accountNumber; // Unique ID for each account
    private String accountHolderName; // Name of the customer
    private double balance; // Current account balance
    private String email; // Customer's email
    private String phoneNumber; // Customer's phone

    public Account(int accountNumber, String accountHolderName, double balance, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void deposit(double Amount) {
        if (Amount > 0) {
            balance += Amount;
            System.out.println("Deposited : " + Amount + " New balance : " + balance);
        } else {
            System.out.println("Deposit Amount must be positive");
        }
    }

    public void withdraw(double Amount) {
        if (Amount > 0 && Amount <= balance) {
            balance -= Amount;
            System.out.println(" Withdrawn : " + Amount + " New balance : " + balance);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
    }

    public void updateContactDetails(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        System.out.println("Contact details updated.");
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}

class UserInterface {
    private Account[] accounts = new Account[100];
    private int accountCount = 0;
    private Scanner sc = new Scanner(System.in);

    // Create a new account
    public void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();

        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = sc.nextDouble();
        sc.nextLine(); // consume newline

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        int accountNumber = 1000 + accountCount + 1; // unique ID
        Account newAccount = new Account(accountNumber, name, initialDeposit, email, phone);
        accounts[accountCount] = newAccount;
        accountCount++;

        System.out.println("Account created successfully! Account Number: " + accountNumber);
    }

    // Deposit money
    public void performDeposit() {
        System.out.print("Enter account number: ");
        int accNum = sc.nextInt();
        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        Account acc = findAccount(accNum);
        if (acc != null) {
            acc.deposit(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    // Withdraw money
    public void performWithdrawal() {
        System.out.print("Enter account number: ");
        int accNum = sc.nextInt();
        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        Account acc = findAccount(accNum);
        if (acc != null) {
            acc.withdraw(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    // Show details
    public void showAccountDetails() {
        System.out.print("Enter account number: ");
        int accNum = sc.nextInt();
        sc.nextLine();

        Account acc = findAccount(accNum);
        if (acc != null) {
            acc.displayAccountDetails();
        } else {
            System.out.println("Account not found!");
        }
    }

    // Update contact
    public void updateContact() {
        System.out.print("Enter account number: ");
        int accNum = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new email: ");
        String email = sc.nextLine();
        System.out.print("Enter new phone: ");
        String phone = sc.nextLine();

        Account acc = findAccount(accNum);
        if (acc != null) {
            acc.updateContactDetails(email, phone);
        } else {
            System.out.println("Account not found!");
        }
    }

    // Menu loop
    public void mainMenu() {
        int choice;
        do {
            System.out.println("\n=== Banking Application ===");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    performDeposit();
                    break;
                case 3:
                    performWithdrawal();
                    break;
                case 4:
                    showAccountDetails();
                    break;
                case 5:
                    updateContact();
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 6);
    }

    // Helper to search account
    private Account findAccount(int accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return accounts[i];
            }
        }
        return null;
    }
}

public class BankingApp {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.mainMenu();

    }
}
