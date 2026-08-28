package org.example;

public class UserProfile {
    public static void main(String[] args) {
        // User information
        String firstName = "Waqas";
        String lastName = "Farid";
        int age = 28;
        String email = "waqas@example.com";
        boolean isActive = true;
        double accountBalance = 5000.50;

        // Print profile
        System.out.println("=== User Profile ===");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Age: " + age);
        System.out.println("Email: " + email);
        System.out.println("Active: " + isActive);
        System.out.println("Balance: " + accountBalance);
    }
}