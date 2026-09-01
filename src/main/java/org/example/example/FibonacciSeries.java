package org.example.example;

public class FibonacciSeries {
    public static void main(String[] args) {
        System.out.println("=== Fibonacci Series (First 10 Numbers) ===");

        int num1 = 0;
        int num2 = 1;

        System.out.println("Number 1: " + num1);
        System.out.println("Number 2: " + num2);

        for (int i = 3; i <= 10; i++) {
            int nextNumber = num1 + num2;
            System.out.println("Number " + i + ": " + nextNumber);

            num1 = num2;
            num2 = nextNumber;
        }
    }
}