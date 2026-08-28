package org.example;

public class LoginValidator {
    public static void main(String[] args) {
        String correctUsername = "admin";
        String correctPassword = "password123";

        String enteredUsername = "admin";
        String enteredPassword = "password123";

        System.out.println("=== Login Validator ===");
        System.out.println("Username: " + enteredUsername);
        System.out.println("Password: " + enteredPassword);

        if (enteredUsername.equals(correctUsername) && enteredPassword.equals(correctPassword)) {
            System.out.println("✅ Login Successful!");
        } else {
            System.out.println("❌ Invalid Credentials!");
        }
    }
}