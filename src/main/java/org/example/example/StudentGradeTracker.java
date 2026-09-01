package org.example.example;

import java.util.ArrayList;

public class StudentGradeTracker {
    // ArrayList ka use karke marks store karenge
    public static void main(String[] args) {
        ArrayList<Integer> grades = new ArrayList<>();

        System.out.println("=== Student Grade Tracker ===\n");

        // Marks add karo
        grades.add(85);
        grades.add(92);
        grades.add(78);
        grades.add(95);
        grades.add(88);

        System.out.println("Total Students: " + grades.size());
        System.out.println("Grades: " + grades);

        // Average calculate karo
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        double average = sum / (double) grades.size();

        System.out.println("\nAverage Grade: " + average);

        // Highest aur Lowest find karo
        int highest = grades.get(0);
        int lowest = grades.get(0);

        for (int grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
            if (grade < lowest) {
                lowest = grade;
            }
        }

        System.out.println("Highest Grade: " + highest);
        System.out.println("Lowest Grade: " + lowest);

        // Fail hone wale nikalo (< 50)
        System.out.println("\nFailed Students:");
        for (int i = 0; i < grades.size(); i++) {
            if (grades.get(i) < 50) {
                System.out.println("Student " + (i + 1) + ": " + grades.get(i));
            }
        }
    }
}