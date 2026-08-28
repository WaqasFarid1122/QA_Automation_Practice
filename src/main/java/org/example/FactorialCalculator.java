package org.example;

public class FactorialCalculator {
    // Recursive method: Factorial nikalne ke liye
    public static int factorial(int n) {
        // Base case: jab n 0 ho to 1 return karo
        if (n == 0 || n == 1) {
            return 1;
        }
        // Recursive case: n * factorial(n-1)
        return n * factorial(n - 1);
    }

    // Recursive method: Fibonacci series
    public static int fibonacci(int n) {
        // Base cases
        if (n == 1 || n == 2) {
            return 1;
        }
        // Recursive case: fib(n-1) + fib(n-2)
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        System.out.println("=== Factorial Calculator ===\n");

        // Factorial test
        System.out.println("Factorial:");
        for (int i = 1; i <= 6; i++) {
            System.out.println(i + "! = " + factorial(i));
        }

        // Fibonacci test
        System.out.println("\nFibonacci Series (First 8 numbers):");
        for (int i = 1; i <= 8; i++) {
            System.out.print(fibonacci(i) + " ");
        }
        System.out.println();
    }
}