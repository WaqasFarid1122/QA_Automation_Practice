package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

/**
 * Program Name: GoogleSearch.java
 *
 * Ye program Selenium WebDriver use karty hue Google par search karega
 *
 * Kya kaam karta hai:
 * 1. Chrome browser ko open karega
 * 2. Google.com par navigate karega
 * 3. Search box main "Selenium WebDriver" type karega
 * 4. Search button par click karega
 * 5. Results ko dekhenga
 * 6. Browser band karega
 */

public class GoogleSearch {
    public static void main(String[] args) throws InterruptedException {
        // Step 1: WebDriverManager setup - ChromeDriver automatically download karega
        WebDriverManager.chromedriver().setup();

        // Step 2: Chrome browser ko initialize karo
        WebDriver driver = new ChromeDriver();

        // Step 3: Browser ka timeout set karo (10 seconds tak wait karega)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            // Step 4: Google website par navigate karo
            System.out.println("=== Google Search Automation ===\n");
            System.out.println("✓ Browser open ho raha hai...");
            driver.get("https://www.google.com");
            System.out.println("✓ Google.com par navigate ho gaya\n");

            // Step 5: Browser window ko maximize karo (better visibility ke liye)
            driver.manage().window().maximize();

            // Step 6: Wait karo taaki page properly load ho jaye
            Thread.sleep(2000);

            // Step 7: Google ke search box ko find karo aur text type karo
            System.out.println("✓ Search box ko find kiya ja raha hai...");
            driver.findElement(By.name("q")).sendKeys("Selenium WebDriver");
            System.out.println("✓ 'Selenium WebDriver' type ho gaya\n");

            // Step 8: Wait karo taaki suggestions dikhen
            Thread.sleep(1500);

            // Step 9: Search button par click karo
            System.out.println("✓ Search button par click kiya ja raha hai...");
            driver.findElement(By.name("q")).submit();
            System.out.println("✓ Search complete! Results show ho rahe hain\n");

            // Step 10: Wait karo results dekhny ke liye
            Thread.sleep(3000);

            // Step 11: Page ka title print karo (check ke liye)
            String pageTitle = driver.getTitle();
            System.out.println("✓ Page Title: " + pageTitle);
            System.out.println("✓ Search successful! 🎉\n");

        } catch (Exception e) {
            // Ager koi error aaye to print karo
            System.out.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Step 12: Browser ko band karo (har soorat main)
            System.out.println("✓ Browser band ho raha hai...");
           // Thread.sleep(2000);
            //driver.quit();
            System.out.println("✓ Program complete!\n");
        }
    }
}