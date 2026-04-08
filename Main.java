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
        return username + " – Balance: " + balance;
    }
}

// САМОПИСНЫЙ СТЕК (Task 3)
class MyStack {
    private class Node {
        String data;
        Node next;
        Node(String data) { this.data = data; }
    }

    private Node top;

    public void push(String data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }

    public String pop() {
        if (isEmpty()) return null;
        String data = top.data;
        top = top.next;
        return data;
    }

    public String peek() {
        return isEmpty() ? null : top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}

public class Main {
    private static LinkedList<BankAccount> accounts = new LinkedList<>();
    private static MyStack transactionHistory = new MyStack(); // Наш стек
    private static Queue<String> billQueue = new LinkedList<>(); // Стандартная очередь
    private static Queue<String> accountRequests = new LinkedList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Физическая структура (массив) - Task 6
        BankAccount[] physicalArray = new BankAccount[2];
        physicalArray[0] = new BankAccount("101", "Ali", 150000);
        physicalArray[1] = new BankAccount("102", "Sara", 220000);
        Collections.addAll(accounts, physicalArray);

        while (true) {
            System.out.println("\n1.Bank 2.ATM 3.Admin 4.Exit");
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

    private static void bankMenu() {
        System.out.println("1.Request Account 2.Deposit 3.Withdraw 4.Undo Last");
        int op = scanner.nextInt();
        scanner.nextLine();

        if (op == 1) {
            System.out.print("Enter name: ");
            accountRequests.add(scanner.nextLine());
        } else if (op == 2 || op == 3) {
            System.out.print("Name: ");
            BankAccount acc = findAccount(scanner.nextLine());
            if (acc != null) {
                System.out.print("Amount: ");
                double amt = scanner.nextDouble();
                if (op == 2) acc.balance += amt; else acc.balance -= amt;
                transactionHistory.push((op == 2 ? "Deposit " : "Withdraw ") + amt + " for " + acc.username);
                System.out.println("New balance: " + acc.balance);
            }
        } else if (op == 4) {
            String undone = transactionHistory.pop();
            System.out.println(undone != null ? "Removed: " + undone : "History empty");
        }
    }

    private static void atmMenu() {
        System.out.print("Enter name: ");
        BankAccount acc = findAccount(scanner.nextLine());
        if (acc != null) {
            System.out.println("Balance: " + acc.balance);
        }
    }

    private static void adminMenu() {
        System.out.println("1.Process Account 2.View All");
        int op = scanner.nextInt();
        if (op == 1 && !accountRequests.isEmpty()) {
            accounts.add(new BankAccount("10" + (accounts.size()+1), accountRequests.poll(), 0));
            System.out.println("Account opened.");
        } else if (op == 2) {
            for (BankAccount a : accounts) System.out.println(a);
        }
    }

    private static BankAccount findAccount(String name) {
        for (BankAccount a : accounts) {
            if (a.username.equalsIgnoreCase(name)) return a;
        }
        return null;
    }
}