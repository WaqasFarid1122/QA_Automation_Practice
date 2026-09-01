package org.example.example;

public class TestResults {
    public static void main(String[] args) {
        String testName = "Login Test";
        boolean isPassed = true;
        int score = 95;
        int totalMarks = 100;
        double percentage = (score * 100.0) / totalMarks;

        System.out.println("=== Test Results ===");
        System.out.println("Test Name: " + testName);
        System.out.println("Passed: " + isPassed);
        System.out.println("Score: " + score + "/" + totalMarks);
        System.out.println("Percentage: " + percentage + "%");
    }
}