package org.example.example;

public class EmailValidator {
    // Method jo email check karega
    public static boolean isValidEmail(String email) {
        // Check ke "@" aur "." dono hain ya nahi
        boolean hasAt = email.contains("@");
        boolean hasDot = email.contains(".");

        return hasAt && hasDot;
    }

    public static void main(String[] args) {
        System.out.println("=== Email Validator ===\n");

        // Different emails test karo
        String[] emails = {"user@gmail.com", "invalid.email", "test@outlook.com", "noatsign.com"};

        for (int i = 0; i < emails.length; i++) {
            String email = emails[i];
            boolean valid = isValidEmail(email);

            System.out.println((i + 1) + ". " + email + " → " + (valid ? "Valid" : "Invalid"));
        }
    }
}