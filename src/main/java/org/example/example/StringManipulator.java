package org.example.example;

public class StringManipulator {
    // Method 1: String ko reverse karo
    public static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    // Method 2: Palindrome check karo
    public static boolean isPalindrome(String str) {
        String cleaned = str.toLowerCase().replaceAll(" ", "");
        String reversed = reverseString(cleaned);
        return cleaned.equals(reversed);
    }

    // Method 3: Vowels count karo
    public static int countVowels(String str) {
        int count = 0;
        String vowels = "aeiouAEIOU";

        for (char c : str.toCharArray()) {
            if (vowels.contains(String.valueOf(c))) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("=== String Manipulator ===\n");

        String text1 = "Hello World";
        String text2 = "racecar";
        String text3 = "Java";

        // Test 1: Reverse string
        System.out.println("Original: " + text1);
        System.out.println("Reversed: " + reverseString(text1));

        // Test 2: Palindrome check
        System.out.println("\nPalindrome Check:");
        System.out.println(text2 + " is palindrome? " + isPalindrome(text2));
        System.out.println(text1 + " is palindrome? " + isPalindrome(text1));

        // Test 3: Count vowels
        System.out.println("\nVowel Count:");
        System.out.println(text3 + " mein " + countVowels(text3) + " vowels hain");
        System.out.println(text1 + " mein " + countVowels(text1) + " vowels hain");
    }
}