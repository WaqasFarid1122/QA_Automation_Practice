package org.example;

public class Car {
    // Properties
    private String brand;
    private String model;
    private String color;
    private int speed;
    private boolean isRunning;

    // Constructor
    public Car(String brand, String model, String color) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.speed = 0;
        this.isRunning = false;
    }

    // Method: Car start karega
    public void start() {
        if (isRunning) {
            System.out.println(brand + " " + model + " already running!");
        } else {
            isRunning = true;
            System.out.println(brand + " " + model + " started!");
        }
    }

    // Method: Car band karega
    public void stop() {
        if (!isRunning) {
            System.out.println(brand + " " + model + " already stopped!");
        } else {
            isRunning = false;
            speed = 0;
            System.out.println(brand + " " + model + " stopped!");
        }
    }

    // Method: Speed badhega
    public void accelerate(int increment) {
        if (!isRunning) {
            System.out.println("Start the car first!");
            return;
        }
        speed += increment;
        System.out.println(brand + " accelerated. Current speed: " + speed + " km/h");
    }

    // Method: Speed kam hoga
    public void brake(int decrement) {
        if (!isRunning) {
            System.out.println("Car is not running!");
            return;
        }
        speed -= decrement;
        if (speed < 0) speed = 0;
        System.out.println(brand + " braked. Current speed: " + speed + " km/h");
    }

    // Method: Car ki details
    public void displayInfo() {
        System.out.println("\n--- Car Information ---");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Color: " + color);
        System.out.println("Speed: " + speed + " km/h");
        System.out.println("Status: " + (isRunning ? "Running" : "Stopped"));
    }

    public static void main(String[] args) {
        System.out.println("=== Car Management System ===\n");

        // Car 1 banao
        Car car1 = new Car("Toyota", "Fortuner", "Black");
        car1.displayInfo();

        // Car 1 chalao
        System.out.println("\n--- Car 1 Operations ---");
        car1.start();
        car1.accelerate(50);
        car1.accelerate(30);
        car1.brake(20);
        car1.stop();

        // Car 2 banao
        Car car2 = new Car("Honda", "Civic", "Silver");
        car2.displayInfo();

        System.out.println("\n--- Car 2 Operations ---");
        car2.start();
        car2.accelerate(60);
        car2.brake(15);
        car2.displayInfo();
    }
}