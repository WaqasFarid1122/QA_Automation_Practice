package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class Week6_SHubLoginTest {

    static WebDriver driver;
    static WebDriverWait wait;
    static String baseURL = "https://shub.jeuxtesting.com";
    static String emailXPath = "//input[@type='email']";
    static String passwordXPath = "//input[@type='password']";
    static String signInXPath = "//button[contains(text(), 'Sign in')]";

    public static void main(String[] args) throws InterruptedException {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("🎯 WEEK 6: S-HUB LOGIN TESTING PROGRAM");
        System.out.println("=".repeat(70) + "\n");

        setUp();

        testEmailField();
        testPasswordField();
        testSignInButton();
        testAdminLogin();
        testTeacherLogin();
        testManagerLogin();
        testSubAdminLogin();

        tearDown();

        System.out.println("\n" + "=".repeat(70));
        System.out.println("✅ ALL TESTS COMPLETED!");
        System.out.println("=".repeat(70) + "\n");
    }

    public static void setUp() {
        System.out.println("🔧 SETUP: Browser khol rhy hain...");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        System.out.println("✅ Browser successfully khul gya!\n");
    }

    public static void tearDown() {
        System.out.println("\n🔧 CLEANUP: Browser close kr rhy hain...");
        if (driver != null) {
            driver.quit();
            System.out.println("✅ Browser successfully band ho gya!\n");
        }
    }

    public static void testEmailField() {
        System.out.println("🧪 TEST 1: Email Field Check");
        System.out.println("─".repeat(70));
        try {
            driver.get(baseURL);
            System.out.println("✓ Website khola: " + baseURL);

            System.out.print("✓ Email field check kr rhy hain... ");
            WebElement emailField = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(emailXPath))
            );
            if (emailField.isDisplayed()) {
                System.out.println("✅ PASS\n");
            } else {
                System.out.println("❌ FAIL\n");
            }
        } catch (Exception e) {
            System.out.println("❌ FAIL - " + e.getMessage() + "\n");
        }
        System.out.println("─".repeat(70) + "\n");
    }

    public static void testPasswordField() {
        System.out.println("🧪 TEST 2: Password Field Check");
        System.out.println("─".repeat(70));
        try {
            driver.get(baseURL);

            System.out.print("✓ Password field check kr rhy hain... ");
            WebElement passwordField = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(passwordXPath))
            );
            if (passwordField.isDisplayed()) {
                System.out.println("✅ PASS\n");
            } else {
                System.out.println("❌ FAIL\n");
            }
        } catch (Exception e) {
            System.out.println("❌ FAIL - " + e.getMessage() + "\n");
        }
        System.out.println("─".repeat(70) + "\n");
    }

    public static void testSignInButton() {
        System.out.println("🧪 TEST 3: Sign In Button Check");
        System.out.println("─".repeat(70));
        try {
            driver.get(baseURL);

            System.out.print("✓ Sign In button check kr rhy hain... ");
            WebElement signInBtn = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(signInXPath))
            );
            if (signInBtn.isDisplayed()) {
                System.out.println("✅ PASS\n");
            } else {
                System.out.println("❌ FAIL\n");
            }
        } catch (Exception e) {
            System.out.println("❌ FAIL - " + e.getMessage() + "\n");
        }
        System.out.println("─".repeat(70) + "\n");
    }

    public static void testAdminLogin() throws InterruptedException {
        System.out.println("🧪 TEST 4: Admin Login & Redirect");
        System.out.println("─".repeat(70));
        try {
            driver.get(baseURL);
            System.out.println("✓ Login page khola");

            System.out.print("✓ Admin email enter kr rhy hain... ");
            WebElement emailField = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(emailXPath))
            );
            emailField.sendKeys("admin@admin.com");
            System.out.println("✅ admin@admin.com");

            System.out.print("✓ Password enter kr rhy hain... ");
            WebElement passwordField = driver.findElement(By.xpath(passwordXPath));
            passwordField.sendKeys("admin123@");
            System.out.println("✅ ••••••••••");

            System.out.print("✓ Sign In button click kr rhy hain... ");
            WebElement signInBtn = driver.findElement(By.xpath(signInXPath));
            signInBtn.click();
            System.out.println("✅ CLICKED");

            Thread.sleep(4000);

            System.out.print("✓ AdminDashboard URL verify kr rhy hain... ");
            String currentURL = driver.getCurrentUrl();
            if (currentURL.contains("AdminDashboard")) {
                System.out.println("✅ PASS - " + currentURL + "\n");
            } else {
                System.out.println("❌ FAIL - Expected AdminDashboard but got: " + currentURL + "\n");
            }
        } catch (Exception e) {
            System.out.println("❌ FAIL - " + e.getMessage() + "\n");
        }
        System.out.println("─".repeat(70) + "\n");
    }

    public static void testTeacherLogin() throws InterruptedException {
        System.out.println("🧪 TEST 5: Teacher Login & Redirect");
        System.out.println("─".repeat(70));
        try {
            driver.get(baseURL);
            System.out.println("✓ Login page khola");

            System.out.print("✓ Teacher email enter kr rhy hain... ");
            WebElement emailField = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(emailXPath))
            );
            emailField.sendKeys("teacher@teacher.com");
            System.out.println("✅ teacher@teacher.com");

            System.out.print("✓ Password enter kr rhy hain... ");
            WebElement passwordField = driver.findElement(By.xpath(passwordXPath));
            passwordField.sendKeys("admin123@");
            System.out.println("✅ ••••••••••");

            System.out.print("✓ Sign In button click kr rhy hain... ");
            WebElement signInBtn = driver.findElement(By.xpath(signInXPath));
            signInBtn.click();
            System.out.println("✅ CLICKED");

            Thread.sleep(4000);

            System.out.print("✓ TeacherRegistration URL verify kr rhy hain... ");
            String currentURL = driver.getCurrentUrl();
            if (currentURL.contains("TeacherRegistration")) {
                System.out.println("✅ PASS - " + currentURL + "\n");
            } else {
                System.out.println("❌ FAIL - Expected TeacherRegistration but got: " + currentURL + "\n");
            }
        } catch (Exception e) {
            System.out.println("❌ FAIL - " + e.getMessage() + "\n");
        }
        System.out.println("─".repeat(70) + "\n");
    }

    public static void testManagerLogin() throws InterruptedException {
        System.out.println("🧪 TEST 6: Manager Login & Redirect");
        System.out.println("─".repeat(70));
        try {
            driver.get(baseURL);
            System.out.println("✓ Login page khola");

            System.out.print("✓ Manager email enter kr rhy hain... ");
            WebElement emailField = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(emailXPath))
            );
            emailField.sendKeys("manager@manager.com");
            System.out.println("✅ manager@manager.com");

            System.out.print("✓ Password enter kr rhy hain... ");
            WebElement passwordField = driver.findElement(By.xpath(passwordXPath));
            passwordField.sendKeys("admin123@");
            System.out.println("✅ ••••••••••");

            System.out.print("✓ Sign In button click kr rhy hain... ");
            WebElement signInBtn = driver.findElement(By.xpath(signInXPath));
            signInBtn.click();
            System.out.println("✅ CLICKED");

            Thread.sleep(4000);

            System.out.print("✓ ManagerDashboard URL verify kr rhy hain... ");
            String currentURL = driver.getCurrentUrl();
            if (currentURL.contains("ManagerDashboard")) {
                System.out.println("✅ PASS - " + currentURL + "\n");
            } else {
                System.out.println("❌ FAIL - Expected ManagerDashboard but got: " + currentURL + "\n");
            }
        } catch (Exception e) {
            System.out.println("❌ FAIL - " + e.getMessage() + "\n");
        }
        System.out.println("─".repeat(70) + "\n");
    }

    public static void testSubAdminLogin() throws InterruptedException {
        System.out.println("🧪 TEST 7: SubAdmin Login & Redirect");
        System.out.println("─".repeat(70));
        try {
            driver.get(baseURL);
            System.out.println("✓ Login page khola");

            System.out.print("✓ SubAdmin email enter kr rhy hain... ");
            WebElement emailField = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(emailXPath))
            );
            emailField.sendKeys("subadmin@subadmin.com");
            System.out.println("✅ subadmin@subadmin.com");

            System.out.print("✓ Password enter kr rhy hain... ");
            WebElement passwordField = driver.findElement(By.xpath(passwordXPath));
            passwordField.sendKeys("admin123@");
            System.out.println("✅ ••••••••••");

            System.out.print("✓ Sign In button click kr rhy hain... ");
            WebElement signInBtn = driver.findElement(By.xpath(signInXPath));
            signInBtn.click();
            System.out.println("✅ CLICKED");

            Thread.sleep(4000);

            System.out.print("✓ SubAdminDashboard URL verify kr rhy hain... ");
            String currentURL = driver.getCurrentUrl();
            if (currentURL.contains("SubAdminDashboard")) {
                System.out.println("✅ PASS - " + currentURL + "\n");
            } else {
                System.out.println("❌ FAIL - Expected SubAdminDashboard but got: " + currentURL + "\n");
            }
        } catch (Exception e) {
            System.out.println("❌ FAIL - " + e.getMessage() + "\n");
        }
        System.out.println("─".repeat(70) + "\n");
    }
}