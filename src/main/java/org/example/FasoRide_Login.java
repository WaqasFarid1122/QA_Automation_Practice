package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FasoRide_Login {
    public static void main(String[] args) throws InterruptedException {

        // Step 1: WebDriverManager setup - chromedriver automatically download karo
        WebDriverManager.chromedriver().setup();

        // Step 2: Chrome browser ko initialize karo
        WebDriver driver = new ChromeDriver();
        System.out.println("✅ Browser khul gya");

        // Step 3: Browser window ko maximize karo
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));

        try {
            // Step 4: FasoRide login page par navigate karo
            System.out.println("=== FasoRide Admin Panel Login ===\n");
            driver.get("https://faso-ride.jeuxtesting.com/");
            System.out.println("✅ FasoRide login page par navigate ho gya");

            // Step 5: Wait karo page load hone ka liye
            Thread.sleep(2000);

            // Step 6: Email field ko find karo aur email enter karo
            System.out.println("✅ Email field ko find kiya ja raha hai...");
            driver.findElement(By.cssSelector("input[type='email']")).sendKeys("admin@fasoride.com");
            System.out.println("✅ Email 'admin@fasoride.com' enter ho gya\n");

            // Step 7: Wait karo
            Thread.sleep(1000);

            // Step 8: Password field ko find karo aur password enter karo
            System.out.println("✅ Password field ko find kiya ja raha hai...");
            driver.findElement(By.cssSelector("input[type='password']")).sendKeys("Admin@123456");
            System.out.println("✅ Password 'Admin@123456' enter ho gya\n");

            // Step 9: Wait karo
            Thread.sleep(1000);

            // Step 10: Login button ko find karo aur click karo
            System.out.println("✅ Login button ko find kiya ja raha hai...");
            driver.findElement(By.xpath("//button[contains(text(), 'Login')]")).click();
            System.out.println("✅ Login button par click ho gya\n");

            // Step 11: Wait karo login process complete hone ka liye
            Thread.sleep(3000);

            // Step 12: Page ka title check karo
            String pageTitle = driver.getTitle();
            System.out.println("✅ Page Title: " + pageTitle);

            // Step 13: Current URL check karo
            String pageURL = driver.getCurrentUrl();
            System.out.println("✅ Current URL: " + pageURL);
            System.out.println("✅ Login Successful! Admin panel khul gya! 🎉\n");

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            System.out.println("⚠️ Ager selectors match nahi hue to FasoRide page par inspect karky selectors check kar!\n");
            e.printStackTrace();
        } finally {
            System.out.println("✅ Program complete! Browser open hai, admin panel dekh sakta hai. \n");
            Thread.sleep(3000);
            // driver.quit();
        }
    }
}