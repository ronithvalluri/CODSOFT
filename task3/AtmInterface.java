package task3;
import java.util.Scanner;

class BankAccount {
    String name;
    String userName;
    String password;
    String accountNo;
    float balance = 100000f;
    int transactions = 0;
    String transactionHistory = "";

    public BankAccount(String name, String userName, String password, String accountNo) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.accountNo = accountNo;
    }

    public void withdraw() {
        System.out.print("\nEnter amount to withdraw: ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        try {
            if (balance >= amount) {
                transactions++;
                balance -= amount;
                System.out.println("\nWithdrawal successful");
                String str = String.format("%.2f Rs Withdrawn\n", amount);
                transactionHistory = transactionHistory.concat(str);
            } else {
                System.out.println("\nInsufficient Balance");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Enter a valid amount.");
        }
    }

    public void deposit() {
        System.out.print("\nEnter amount to deposit: ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();

        try {
            if (amount <= 100000f) {
                transactions++;
                balance += amount;
                System.out.println("\nDeposit successful");
                String str = String.format("%.2f Rs Deposited\n", amount);
                transactionHistory = transactionHistory.concat(str);
            } else {
                System.out.println("\nSorry, the deposit limit is 100000.00");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Enter a valid amount.");
        }
    }

    public void checkBalance() {
        System.out.println("\nCurrent Balance: " + balance + " Rs");
    }

    public void transHistory() {
        if (transactions == 0) {
            System.out.println("\nTransaction History: Empty");
        } else {
            System.out.println("\nTransaction History:");
            String[] transactions = transactionHistory.split("\n");
            for (String transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }
}

public class AtmInterface {

    public static int takeIntegerInput(int limit) {
        int input = 0;
        boolean flag = false;

        while (!flag) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();

                if (input >= 1 && input <= limit) {
                    flag = true;
                } else {
                    System.out.println("Invalid choice. Please choose a number between 1 and " + limit);
                    System.out.print("Enter Your Choice - ");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Enter a valid integer.");
                System.out.print("Enter Your Choice - ");
            }
        }
        return input;
    }

    public static void stylishWelcome() {
        System.out.println("\n=============================================");
        System.out.println("               WELCOME TO ATM");
        System.out.println("=============================================");
    }

    public static void main(String[] args) {

        stylishWelcome();

        BankAccount account = new BankAccount("John Doe", "john_doe123", "password", "1234567890");

        boolean isFinished = false;
        while (!isFinished) {
            System.out.println("\n1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.print("Enter Your Choice - ");
            int choice = takeIntegerInput(5);
            switch (choice) {
                case 1:
                    account.withdraw();
                    break;
                case 2:
                    account.deposit();
                    break;
                case 3:
                    account.checkBalance();
                    break;
                case 4:
                    account.transHistory();
                    break;
                case 5:
                    isFinished = true;
                    System.out.println("\nExiting ATM. Thank You!");
                    break;
            }
        }
    }
}
