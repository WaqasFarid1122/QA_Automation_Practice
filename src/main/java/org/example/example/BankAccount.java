package org.example.example;

public class BankAccount {
    // Properties (Variables)
    private String accountHolder;
    private double balance;
    private String accountNumber;

    // Constructor - jab object banate hain tab ye call hota hai
    public BankAccount(String accountHolder, String accountNumber, double initialBalance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    // Method: Paise nikalne ke liye
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            balance -= amount;
            System.out.println(amount + " withdrawn. New balance: " + balance);
        }
    }

    // Method: Paise dalने के लिए
    public void deposit(double amount) {
        balance += amount;
        System.out.println(amount + " deposited. New balance: " + balance);
    }

    // Method: Balance check karne ke liye
    public void checkBalance() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
    }

    // Main method - Objects banate hain aur methods call karte hain
    public static void main(String[] args) {
        System.out.println("=== Bank Account System ===\n");

        // Object 1 banao
        BankAccount account1 = new BankAccount("Waqas", "123456", 5000);
        System.out.println("--- Account 1 ---");
        account1.checkBalance();

        // Methods call karo
        System.out.println("\nDepositing 2000...");
        account1.deposit(2000);

        System.out.println("\nWithdrawing 1000...");
        account1.withdraw(1000);

        // Object 2 banao
        System.out.println("\n--- Account 2 ---");
        BankAccount account2 = new BankAccount("Ali", "789012", 3000);
        account2.checkBalance();

        System.out.println("\nWithdrawing 5000...");
        account2.withdraw(5000);
    }
}