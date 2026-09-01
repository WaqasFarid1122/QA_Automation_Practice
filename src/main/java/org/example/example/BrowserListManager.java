package org.example.example;

public class BrowserListManager {
    public static void main(String[] args) {
        // Array mein 4 browsers hain
        String[] browsers = {"Chrome", "Firefox", "Safari", "Edge"};

        System.out.println("=== Browser List Manager ===\n");

        // Har browser ko print karo
        for (int i = 0; i < browsers.length; i++) {
            String name = browsers[i];
            int length = name.length();
            String upper = name.toUpperCase();

            System.out.println((i + 1) + ". " + upper + " (" + length + " characters)");
        }
    }
}