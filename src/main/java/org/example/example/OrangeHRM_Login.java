package org.example.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

/**
 * Program Name: OrangeHRM_Login.java (Updated for SauceDemo)
 *
 * Ye program Selenium WebDriver use karty hue SauceDemo website par login karega
 *
 * Kya kaam karta hai:
 * 1. Chrome browser ko open karega
 * 2. SauceDemo login page par navigate karega
 * 3. Username field main "standard_user" enter karega
 * 4. Password field main "secret_sauce" enter karega
 * 5. Login button par click karega
 * 6. Successful login verify karega
 * 7. Browser open rehega taaki dashboard dekh sake
 */

public class OrangeHRM_Login {
    public static void main(String[] args) throws InterruptedException {
        // Step 1: WebDriverManager setup
        WebDriverManager.chromedriver().setup();

        // Step 2: Chrome browser ko initialize karo
        WebDriver driver = new ChromeDriver();

        // Step 3: Browser ka timeout set karo
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            // Step 4: SauceDemo login page par navigate karo
            System.out.println("=== SauceDemo Login Automation ===\n");
            System.out.println("✓ Browser open ho raha hai...");
            driver.get("https://www.saucedemo.com/");
            System.out.println("✓ SauceDemo login page par navigate ho gaya\n");

            // Step 5: Browser window ko maximize karo
            driver.manage().window().maximize();

            // Step 6: Wait karo page load hone ka liye
            Thread.sleep(2000);

            // Step 7: Username field ko find karo aur "standard_user" enter karo
            System.out.println("✓ Username field ko find kiya ja raha hai...");
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            System.out.println("✓ Username 'standard_user' enter ho gaya\n");

            // Step 8: Wait karo
            Thread.sleep(1000);

            // Step 9: Password field ko find karo aur "secret_sauce" enter karo
            System.out.println("✓ Password field ko find kiya ja raha hai...");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            System.out.println("✓ Password 'secret_sauce' enter ho gaya\n");

            // Step 10: Wait karo
            Thread.sleep(1000);

            // Step 11: Login button ko find karo aur click karo
            System.out.println("✓ Login button ko find kiya ja raha hai...");
            driver.findElement(By.id("login-button")).click();
            System.out.println("✓ Login button par click kiya\n");

            // Step 12: Wait karo login process complete hone ka liye
            Thread.sleep(3000);

            // Step 13: Page ka title check karo (verify login successful)
            String pageTitle = driver.getTitle();
            System.out.println("✓ Page Title: " + pageTitle);

            // Step 14: Dashboard par successful login verify karo
            String pageURL = driver.getCurrentUrl();
            System.out.println("✓ Current URL: " + pageURL);

            // Step 15: Check if inventory page (dashboard) opened
            if (pageURL.contains("inventory")) {
                System.out.println("✓ Login Successful! Products page dikha! 🎉\n");
            } else {
                System.out.println("⚠️ Login ke baad alag page par pahunch gaye\n");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Browser ko open rakhne ke liye comment kar diya
            System.out.println("✓ Program complete! Browser open hai, tu website dekh sakta hai!\n");
            // Thread.sleep(3000);
            // driver.quit();
        }
    }
}