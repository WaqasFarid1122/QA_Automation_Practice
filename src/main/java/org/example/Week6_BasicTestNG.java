package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class Week6_BasicTestNG {
    static WebDriver driver;
    static WebDriverWait wait;
    static int passed = 0;
    static int failed = 0;

    public static void main(String[] args) {
        System.out.println("\n🔄 WEEK 6: TestNG Style Tests (Pure Selenium)\n");
        
        setUp();
        
        testLoginSuccess();
        testPageTitle();
        testEmailFieldExists();
        testPasswordFieldExists();
        
        tearDown();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📊 TEST RESULTS");
        System.out.println("=".repeat(50));
        System.out.println("✅ PASSED: " + passed);
        System.out.println("❌ FAILED: " + failed);
        System.out.println("=".repeat(50) + "\n");
    }

    public static void setUp() {
        System.out.println("🔄 SETUP: Browser khol rhy hain...");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        System.out.println("✅ Browser khul gya\n");
    }

    public static void testLoginSuccess() {
        System.out.println("🧪 TEST 1: Login Success - Priority 1");
        try {
            driver.get("https://shub.jeuxtesting.com/");
            
            WebElement emailField = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//input[@type='email']")
                )
            );
            emailField.clear();
            emailField.sendKeys("admin@admin.com");
            
            WebElement passwordField = driver.findElement(
                By.cssSelector("input[type='password']")
            );
            passwordField.clear();
            passwordField.sendKeys("admin123@");
            
            WebElement loginBtn = driver.findElement(
                By.xpath("//button[contains(text(), 'Sign in')]")
            );
            loginBtn.click();
            
            Thread.sleep(3000);
            String currentUrl = driver.getCurrentUrl();
            
            if (currentUrl.contains("dashboard")) {
                System.out.println("✅ PASS: Login successful, dashboard URL found\n");
                passed++;
            } else {
                System.out.println("❌ FAIL: Dashboard not found. Current URL: " + currentUrl + "\n");
                failed++;
            }
        } catch (Exception e) {
            System.out.println("❌ FAIL: " + e.getMessage() + "\n");
            failed++;
        }
    }

    public static void testPageTitle() {
        System.out.println("🧪 TEST 2: Page Title - Priority 2");
        try {
            String title = driver.getTitle();
            if (title != null && !title.isEmpty()) {
                System.out.println("✅ PASS: Page title exists: " + title + "\n");
                passed++;
            } else {
                System.out.println("❌ FAIL: Page title is empty\n");
                failed++;
            }
        } catch (Exception e) {
            System.out.println("❌ FAIL: " + e.getMessage() + "\n");
            failed++;
        }
    }

    public static void testEmailFieldExists() {
        System.out.println("🧪 TEST 3: Email Field Exists - Priority 3");
        try {
            WebElement emailField = driver.findElement(
                By.xpath("//input[@type='email']")
            );
            
            if (emailField.isDisplayed()) {
                System.out.println("✅ PASS: Email field is displayed\n");
                passed++;
            } else {
                System.out.println("❌ FAIL: Email field exists but not displayed\n");
                failed++;
            }
        } catch (Exception e) {
            System.out.println("❌ FAIL: Email field not found\n");
            failed++;
        }
    }

    public static void testPasswordFieldExists() {
        System.out.println("🧪 TEST 4: Password Field Exists - Priority 4");
        try {
            WebElement passwordField = driver.findElement(
                By.cssSelector("input[type='password']")
            );
            
            if (passwordField.isDisplayed()) {
                System.out.println("✅ PASS: Password field is displayed\n");
                passed++;
            } else {
                System.out.println("❌ FAIL: Password field exists but not displayed\n");
                failed++;
            }
        } catch (Exception e) {
            System.out.println("❌ FAIL: Password field not found\n");
            failed++;
        }
    }

    public static void tearDown() {
        System.out.println("🔄 CLEANUP: Browser band kr rhy hain...");
        if (driver != null) {
            driver.quit();
        }
        System.out.println("✅ Browser band ho gya\n");
    }
}
