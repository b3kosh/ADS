import java.util.*;

class BankAccount {
    String accountNumber;
    String username;
    double balance;

    public BankAccount(String accountNumber, String username, double balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return username + " (ID: " + accountNumber + ") – Balance: " + balance;
    }
}

public class Main {
    private static LinkedList<BankAccount> accounts = new LinkedList<>();
    private static Stack<String> transactionHistory = new Stack<>();
    private static Queue<String> billQueue = new LinkedList<>();
    private static Queue<String> accountRequests = new LinkedList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        predefinePhysicalData();

        while (true) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1 – Enter Bank");
            System.out.println("2 – Enter ATM");
            System.out.println("3 – Admin Area");
            System.out.println("4 – Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> bankMenu();
                case 2 -> atmMenu();
                case 3 -> adminMenu();
                case 4 -> System.exit(0);
            }
        }
    }

    private static void predefinePhysicalData() {
        BankAccount[] physicalArray = new BankAccount[3];
        physicalArray[0] = new BankAccount("101", "Ali", 150000);
        physicalArray[1] = new BankAccount("102", "Sara", 220000);
        physicalArray[2] = new BankAccount("103", "Ivan", 50000);
        Collections.addAll(accounts, physicalArray);
    }

    private static void bankMenu() {
        System.out.println("\n--- BANK MENU ---");
        System.out.println("1. Request New Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Pay a Bill");
        System.out.println("5. Undo Last Transaction");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("Enter your name: ");
            accountRequests.add(scanner.nextLine());
            System.out.println("Request submitted.");
        } else if (choice == 2 || choice == 3) {
            System.out.print("Enter username: ");
            String name = scanner.nextLine();
            BankAccount account = findAccount(name);
            if (account != null) {
                System.out.print("Enter amount: ");
                double amount = scanner.nextDouble();
                if (choice == 2) {
                    account.balance += amount;
                    transactionHistory.push("Deposit " + amount + " to " + name);
                } else {
                    account.balance -= amount;
                    transactionHistory.push("Withdraw " + amount + " from " + name);
                }
                System.out.println("Success. New balance: " + account.balance);
            }
        } else if (choice == 4) {
            System.out.print("Enter Bill Name (e.g. Internet): ");
            billQueue.add(scanner.nextLine());
            System.out.println("Bill added to queue.");
        } else if (choice == 5) {
            if (!transactionHistory.isEmpty()) {
                System.out.println("Undo: " + transactionHistory.pop() + " removed.");
            }
        }
    }

    private static void atmMenu() {
        System.out.print("\nEnter username: ");
        String name = scanner.nextLine();
        BankAccount account = findAccount(name);
        if (account != null) {
            System.out.println("1. Enquiry Balance\n2. Withdraw");
            int choice = scanner.nextInt();
            if (choice == 1) System.out.println("Balance: " + account.balance);
            else {
                System.out.print("Amount: ");
                double amt = scanner.nextDouble();
                account.balance -= amt;
                transactionHistory.push("Withdraw " + amt + " from " + name);
                System.out.println("Done.");
            }
        }
    }

    private static void adminMenu() {
        System.out.println("\n--- ADMIN MENU ---");
        System.out.println("1. Process Account Request");
        System.out.println("2. Process Bill Payment");
        System.out.println("3. View All Accounts");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1 && !accountRequests.isEmpty()) {
            String name = accountRequests.poll();
            String id = String.valueOf(100 + accounts.size() + 1);
            accounts.add(new BankAccount(id, name, 0));
            System.out.println("Account created for: " + name);
        } else if (choice == 2 && !billQueue.isEmpty()) {
            System.out.println("Processing: " + billQueue.poll());
        } else if (choice == 3) {
            accounts.forEach(System.out::println);
        }
    }

    private static BankAccount findAccount(String name) {
        for (BankAccount acc : accounts) {
            if (acc.username.equalsIgnoreCase(name)) return acc;
        }
        System.out.println("Account not found.");
        return null;
    }
}