package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class Week6_CompleteSHubTesting {
    static WebDriver driver;
    static WebDriverWait wait;
    static int totalTests = 0;
    static int passed = 0;
    static int failed = 0;

    static class TestUser {
        String email;
        String password;
        String role;
        String expectedUrl;

        TestUser(String email, String password, String role, String expectedUrl) {
            this.email = email;
            this.password = password;
            this.role = role;
            this.expectedUrl = expectedUrl;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("🎯 WEEK 6: COMPLETE S-HUB TESTING PROGRAM");
        System.out.println("Testing: Login, Redirects, Form Fields, User Roles");
        System.out.println("=".repeat(70) + "\n");

        setUp();

        TestUser[] testUsers = {
                new TestUser("admin@admin.com", "admin123@", "ADMIN", "AdminDashboard"),
                new TestUser("teacher@teacher.com", "admin123@", "TEACHER", "TeacherRegistration"),
                new TestUser("manager@manager.com", "admin123@", "MANAGER", "ManagerDashboard"),
                new TestUser("subadmin@subadmin.com", "admin123@", "SUBADMIN", "SubAdminDashboard")
        };

        System.out.println("📋 TEST PLAN:");
        System.out.println("├─ Test 1: Login Form Fields Visibility");
        System.out.println("├─ Test 2-5: Login with each user role");
        System.out.println("├─ Test 6-9: Verify correct URL redirect per role");
        System.out.println("└─ Test 10-13: Page title validation per role\n");

        testFormFieldsOnLoginPage();

        for (TestUser user : testUsers) {
            testLoginWithRedirect(user);
            testPageTitleAfterLogin(user);
        }

        tearDown();

        System.out.println("\n" + "=".repeat(70));
        System.out.println("📊 FINAL TEST RESULTS");
        System.out.println("=".repeat(70));
        System.out.println("Total Tests: " + totalTests);
        System.out.println("✅ PASSED: " + passed);
        System.out.println("❌ FAILED: " + failed);
        if (totalTests > 0) {
            System.out.println("Success Rate: " + ((passed * 100) / totalTests) + "%");
        }
        System.out.println("=".repeat(70) + "\n");
    }

    public static void setUp() {
        System.out.println("🔧 SETUP: Browser khol rhy hain...");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        System.out.println("✅ Browser successfully khul gya!\n");
    }

    public static void testFormFieldsOnLoginPage() throws InterruptedException {
        System.out.println("─".repeat(70));
        System.out.println("🧪 TEST 1: Login Form Fields Visibility");
        System.out.println("─".repeat(70));
        try {
            driver.get("https://shub.jeuxtesting.com");
            Thread.sleep(2000);

            totalTests++;
            System.out.println("\n  ├─ Checking EMAIL field...");
            try {
                WebElement emailField = wait.until(
                        ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='email']"))
                );
                if (emailField.isDisplayed()) {
                    System.out.println("     ✅ PASS: Email field found and visible");
                    passed++;
                } else {
                    System.out.println("     ❌ FAIL: Email field not visible");
                    failed++;
                }
            } catch (Exception e) {
                System.out.println("     ❌ FAIL: Email field not found");
                failed++;
            }

            totalTests++;
            System.out.println("  ├─ Checking PASSWORD field...");
            try {
                WebElement passwordField = wait.until(
                        ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='password']"))
                );
                if (passwordField.isDisplayed()) {
                    System.out.println("     ✅ PASS: Password field found and visible");
                    passed++;
                } else {
                    System.out.println("     ❌ FAIL: Password field not visible");
                    failed++;
                }
            } catch (Exception e) {
                System.out.println("     ❌ FAIL: Password field not found");
                failed++;
            }

            totalTests++;
            System.out.println("  └─ Checking SIGN IN button...");
            try {
                WebElement signInBtn = wait.until(
                        ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(), 'Sign in')]"))
                );
                if (signInBtn.isDisplayed()) {
                    System.out.println("     ✅ PASS: Sign In button found and visible\n");
                    passed++;
                } else {
                    System.out.println("     ❌ FAIL: Sign In button not visible\n");
                    failed++;
                }
            } catch (Exception e) {
                System.out.println("     ❌ FAIL: Sign In button not found\n");
                failed++;
            }

        } catch (Exception e) {
            System.out.println("❌ FAIL: Form fields test error\n");
            failed += 3;
            totalTests += 3;
        }
    }

    public static void testLoginWithRedirect(TestUser user) throws InterruptedException {
        System.out.println("─".repeat(70));
        System.out.println("🧪 TEST " + (totalTests + 1) + ": Login as " + user.role + " (" + user.email + ")");
        System.out.println("─".repeat(70));
        try {
            driver.navigate().to("https://shub.jeuxtesting.com");
            Thread.sleep(2000);

            WebElement emailField = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='email']"))
            );
            emailField.clear();
            emailField.sendKeys(user.email);
            System.out.println("  ├─ Email entered: " + user.email);

            WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
            passwordField.clear();
            passwordField.sendKeys(user.password);
            System.out.println("  ├─ Password entered: ****");

            WebElement signInBtn = driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]"));
            signInBtn.click();
            System.out.println("  ├─ Sign In button clicked");

            Thread.sleep(4000);

            String currentUrl = driver.getCurrentUrl();
            System.out.println("  └─ Current URL: " + currentUrl);

            totalTests++;
            if (currentUrl.contains(user.expectedUrl)) {
                System.out.println("✅ PASS: Redirected to " + user.expectedUrl + "\n");
                passed++;
            } else {
                System.out.println("❌ FAIL: Expected '" + user.expectedUrl + "' but got: " + currentUrl + "\n");
                failed++;
            }

        } catch (Exception e) {
            System.out.println("❌ FAIL: Login error - " + e.getMessage() + "\n");
            totalTests++;
            failed++;
        }
    }

    public static void testPageTitleAfterLogin(TestUser user) {
        System.out.println("🧪 TEST " + (totalTests + 1) + ": Page Title for " + user.role);
        System.out.println("─".repeat(70));
        try {
            String title = driver.getTitle();
            totalTests++;

            if (title != null && !title.isEmpty() && !title.equals("about:blank")) {
                System.out.println("✅ PASS: Page title = \"" + title + "\"\n");
                passed++;
            } else {
                System.out.println("❌ FAIL: Invalid page title\n");
                failed++;
            }

        } catch (Exception e) {
            System.out.println("❌ FAIL: Title test error\n");
            totalTests++;
            failed++;
        }
    }

    public static void tearDown() {
        System.out.println("🔧 CLEANUP: Browser close kr rhy hain...");
        if (driver != null) {
            try {
                driver.quit();
                System.out.println("✅ Browser successfully band ho gya!");
            } catch (Exception e) {
                System.out.println("⚠️  Browser close error");
            }
        }
    }
}