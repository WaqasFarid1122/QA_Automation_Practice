package org.example;

import java.util.ArrayList;

public class StudentManager {
    // Inner class: Student
    public static class Student {
        private String name;
        private int rollNumber;
        private double marks;

        // Constructor
        public Student(String name, int rollNumber, double marks) {
            this.name = name;
            this.rollNumber = rollNumber;
            this.marks = marks;
        }

        // Method: Grade calculate karo
        public String getGrade() {
            if (marks >= 80) return "A";
            else if (marks >= 60) return "B";
            else if (marks >= 40) return "C";
            else return "F";
        }

        // Method: Student ki details
        public void displayInfo() {
            System.out.println("Roll: " + rollNumber + " | Name: " + name + " | Marks: " + marks + " | Grade: " + getGrade());
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Student Management System ===\n");

        // ArrayList mein Students store karo
        ArrayList<Student> students = new ArrayList<>();

        // Students add karo
        students.add(new Student("Waqas", 1, 85));
        students.add(new Student("Ali", 2, 72));
        students.add(new Student("Sara", 3, 92));
        students.add(new Student("Ahmed", 4, 55));
        students.add(new Student("Fatima", 5, 88));

        // Sab students display karo
        System.out.println("--- All Students ---");
        for (Student s : students) {
            s.displayInfo();
        }

        // Pass students nikalo (marks >= 60)
        System.out.println("\n--- Pass Students ---");
        for (Student s : students) {
            if (s.marks >= 60) {
                s.displayInfo();
            }
        }

        // Top student nikalo
        System.out.println("\n--- Top Student ---");
        Student topStudent = students.get(0);
        for (Student s : students) {
            if (s.marks > topStudent.marks) {
                topStudent = s;
            }
        }
        topStudent.displayInfo();

        // Statistics
        System.out.println("\n--- Statistics ---");
        double totalMarks = 0;
        for (Student s : students) {
            totalMarks += s.marks;
        }
        double average = totalMarks / students.size();
        System.out.println("Total Students: " + students.size());
        System.out.println("Average Marks: " + average);
    }
}