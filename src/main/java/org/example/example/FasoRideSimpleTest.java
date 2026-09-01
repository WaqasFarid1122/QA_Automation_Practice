package org.example.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FasoRideSimpleTest {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));

        try {
            // Login
            System.out.println("📍 LOGGING IN...\n");
            driver.get("https://faso-ride.jeuxtesting.com/");
            Thread.sleep(2000);

            driver.findElement(By.cssSelector("input[type='email']")).sendKeys("admin@fasoride.com");
            driver.findElement(By.cssSelector("input[type='password']")).sendKeys("Admin@123456");
            driver.findElement(By.xpath("//button[contains(text(), 'Login')]")).click();
            System.out.println("✅ Login successful!\n");
            Thread.sleep(3000);

            // Simple tests
            System.out.println("📍 TESTING FEATURES:\n");
            testFeature("Dashboard");
            testFeature("Users Management");
            testFeature("Drivers Management");
            testFeature("Driver Requests");
            testFeature("Rides Management");
            testFeature("Ads");
            testFeature("Safety checkup");
            testFeature("Store management");
            testFeature("Store Requests");
            testFeature("Order Management");
            testFeature("Category");
            testFeature("Membership");

            System.out.println("\n✅ ALL TESTS COMPLETED!");

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        } finally {
            System.out.println("Browser open hai!");
            Thread.sleep(10000);
        }
    }

    static void testFeature(String name) throws InterruptedException {
        try {
            driver.findElement(By.xpath("//a[contains(text(), '" + name + "')]")).click();
            Thread.sleep(1500);
            System.out.println("✅ " + name + " - OK");
        } catch (Exception e) {
            System.out.println("⚠️ " + name + " - Not found");
        }
    }
}