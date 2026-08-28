package org.example;

public class CalculatorMethods {
    // Method 1: Addition
    public static int add(int a, int b) {
        return a + b;
    }

    // Method 2: Subtraction
    public static int subtract(int a, int b) {
        return a - b;
    }

    // Method 3: Multiplication
    public static int multiply(int a, int b) {
        return a * b;
    }

    // Method 4: Division
    public static int divide(int a, int b) {
        if (b == 0) {
            System.out.println("Error: Cannot divide by zero!");
            return 0;
        }
        return a / b;
    }

    public static void main(String[] args) {
        System.out.println("=== Calculator Methods ===\n");

        int num1 = 20;
        int num2 = 5;

        System.out.println(num1 + " + " + num2 + " = " + add(num1, num2));
        System.out.println(num1 + " - " + num2 + " = " + subtract(num1, num2));
        System.out.println(num1 + " * " + num2 + " = " + multiply(num1, num2));
        System.out.println(num1 + " / " + num2 + " = " + divide(num1, num2));
    }
}