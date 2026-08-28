package org.example;

public class DiscountCalculator {
    public static void main(String[] args) {
        double originalPrice = 1000.0;
        double discountPercent = 20.0;
        double discountAmount = (originalPrice * discountPercent) / 100;
        double finalPrice = originalPrice - discountAmount;

        System.out.println("=== Discount Calculator ===");
        System.out.println("Original Price: " + originalPrice);
        System.out.println("Discount: " + discountPercent + "%");
        System.out.println("Discount Amount: " + discountAmount);
        System.out.println("Final Price: " + finalPrice);
    }
}