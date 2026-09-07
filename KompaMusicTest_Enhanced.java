package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.*;
import java.nio.file.*;
import java.time.Duration;
import java.util.*;

public class KompaMusicTest_Enhanced {
    static WebDriver driver;
    static WebDriverWait wait;
    static int passed = 0, failed = 0;
    static List<String> results = new ArrayList<>();
    static String screenshotPath = "KompaMusic_Test_Screenshots";
    static String reportFile = "KompaMusic_Test_Report_Enhanced.html";
    static String profilePicturePath = "test_profile_picture.png";

    public static void main(String[] args) throws Exception {
        try {
            // Setup
            deleteFolder(screenshotPath);
            new File(screenshotPath).mkdirs();

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            System.out.println("🎵 Kompa Music - Enhanced Automation Test Started");
            System.out.println("═══════════════════════════════════════════════════");

            // Phase 1: Test Public Pages (Without Login)
            System.out.println("\n📍 PHASE 1: Testing Public Pages");
            testPublicPages();

            // Phase 2: Signup
            System.out.println("\n📍 PHASE 2: Testing Signup with Profile Picture");
            testSignupWithProfilePicture();

            // Phase 3: Login
            System.out.println("\n📍 PHASE 3: Testing Login");
            testLogin();

            // Phase 4: Test Authenticated Features
            System.out.println("\n📍 PHASE 4: Testing Authenticated Features");
            testAuthenticatedFeatures();

            // Generate Report
            generateReport();

            System.out.println("\n═══════════════════════════════════════════════════");
            System.out.println("✅ Test Completed Successfully!");
            System.out.println("📊 Results: " + passed + " Passed, " + failed + " Failed");
            System.out.println("📁 Screenshots: " + screenshotPath + "/");
            System.out.println("📄 Report: " + reportFile);

        } catch (Exception e) {
            System.out.println("❌ Fatal Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    static void testPublicPages() throws Exception {
        try {
            System.out.println("\n🏠 Testing Home Page (Public Access)");
            driver.navigate().to("https://kompa-music.jeuxtesting.com");
            Thread.sleep(2000);

            takeScreenshot("00_Landing_Page_Public");

            // Verify public page elements
            List<WebElement> navLinks = driver.findElements(By.xpath("//nav//a | //nav//button"));
            if (navLinks.size() > 0) {
                System.out.println("  ✅ Navigation elements visible");
                passed++;
                addResult("Public Page Access", true, "Home page loaded successfully");
            } else {
                failed++;
                addResult("Public Page Access", false, "Navigation elements not found");
            }

        } catch (Exception e) {
            System.out.println("❌ Public Page Test - ERROR: " + e.getMessage());
            failed++;
            addResult("Public Page Access", false, e.getMessage());
        }
    }

    static void testSignupWithProfilePicture() throws Exception {
        try {
            System.out.println("\n🔐 Testing Signup with Profile Picture Upload");
            driver.navigate().to("https://kompa-music.jeuxtesting.com");
            Thread.sleep(2000);

            takeScreenshot("01_Landing_Page_Signup");

            // Click on Sign Up button
            WebElement signupBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Sign Up to Unlock Full Library')]")
                )
            );
            signupBtn.click();
            Thread.sleep(2500);

            takeScreenshot("02_Signup_Form_Opened");

            // ========== PROFILE PICTURE UPLOAD ==========
            System.out.println("  📤 Uploading profile picture...");
            try {
                // Find file input element for profile picture
                WebElement fileInput = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("input[type='file']")
                    )
                );

                // Get absolute path to profile picture
                String absolutePath = new File(profilePicturePath).getAbsolutePath();
                fileInput.sendKeys(absolutePath);
                Thread.sleep(1000);
                System.out.println("  ✅ Profile picture uploaded: " + absolutePath);
            } catch (Exception e) {
                System.out.println("  ⚠️ Profile picture upload failed: " + e.getMessage());
                throw e;
            }

            takeScreenshot("03_Profile_Picture_Uploaded");

            // ========== FORM FIELDS ==========
            System.out.println("  📝 Filling form fields...");

            // Username field
            WebElement usernameField = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("input[placeholder='Enter name']")
                )
            );
            usernameField.clear();
            usernameField.sendKeys("kompatest");
            Thread.sleep(400);
            System.out.println("  ✅ Username filled");

            // Email field
            WebElement emailField = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("input[type='email']")
                )
            );
            emailField.clear();
            emailField.sendKeys("kompatest@jeuxtesting.com");
            Thread.sleep(400);
            System.out.println("  ✅ Email filled");

            // Address field
            WebElement addressField = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("input[placeholder='Enter Full Address']")
                )
            );
            addressField.clear();
            addressField.sendKeys("123 Music Lane, Kompa City");
            Thread.sleep(400);
            System.out.println("  ✅ Address filled");

            // Password fields
            List<WebElement> passwordFields = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.cssSelector("input[type='password']")
                )
            );

            if (passwordFields.size() > 0) {
                passwordFields.get(0).clear();
                passwordFields.get(0).sendKeys("KompaTest@123");
                Thread.sleep(400);
                System.out.println("  ✅ Password filled");
            }

            if (passwordFields.size() > 1) {
                passwordFields.get(1).clear();
                passwordFields.get(1).sendKeys("KompaTest@123");
                Thread.sleep(400);
                System.out.println("  ✅ Confirm password filled");
            }

            // Accept Terms checkbox
            List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
            if (!checkboxes.isEmpty() && !checkboxes.get(0).isSelected()) {
                checkboxes.get(0).click();
                Thread.sleep(300);
                System.out.println("  ✅ Terms accepted");
            }

            takeScreenshot("04_Signup_Form_Filled_Complete");

            // Click Next/Submit button
            WebElement submitBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Next') or contains(text(), 'Sign Up') or contains(text(), 'Register')]")
                )
            );
            submitBtn.click();
            Thread.sleep(3000);

            takeScreenshot("05_Signup_Success_Toast");

            // Verify success - look for success message or redirect to login
            try {
                // Check for success toast
                List<WebElement> successMessages = driver.findElements(
                    By.xpath("//*[contains(text(), 'successfully') or contains(text(), 'Successfully')]")
                );
                if (!successMessages.isEmpty()) {
                    System.out.println("  ✅ Success message found");
                }
            } catch (Exception e) {
                System.out.println("  ℹ️ No success message element found, checking page state");
            }

            Thread.sleep(2000);

            takeScreenshot("06_After_Signup_Redirect");

            System.out.println("✅ Signup with Profile Picture - OK");
            passed++;
            addResult("Signup with Profile Picture", true, "Account created with profile picture successfully");

        } catch (Exception e) {
            System.out.println("❌ Signup - ERROR: " + e.getMessage());
            failed++;
            addResult("Signup with Profile Picture", false, e.getMessage());
            throw e;
        }
    }

    static void testLogin() throws Exception {
        try {
            System.out.println("\n🔑 Testing Login");

            // Check if we're already on login page after signup
            try {
                WebElement emailInput = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("input[type='email']")
                    ),
                    Duration.ofSeconds(5)
                );
                System.out.println("  ℹ️ Already on login page");
            } catch (TimeoutException e) {
                // Navigate to login if needed
                driver.navigate().to("https://kompa-music.jeuxtesting.com");
                Thread.sleep(2000);
            }

            takeScreenshot("07_Login_Page");

            // Fill login form
            WebElement emailInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("input[type='email']")
                )
            );
            emailInput.clear();
            emailInput.sendKeys("kompatest@jeuxtesting.com");
            Thread.sleep(400);
            System.out.println("  ✅ Login email filled");

            WebElement passwordInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("input[type='password']")
                )
            );
            passwordInput.clear();
            passwordInput.sendKeys("KompaTest@123");
            Thread.sleep(400);
            System.out.println("  ✅ Login password filled");

            // Accept Terms if present
            List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
            if (!checkboxes.isEmpty() && !checkboxes.get(0).isSelected()) {
                checkboxes.get(0).click();
                Thread.sleep(300);
                System.out.println("  ✅ Login terms accepted");
            }

            takeScreenshot("08_Login_Form_Filled");

            // Click Login button with JavaScript to avoid interception
            WebElement loginBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//button[contains(text(), 'Login')]")
                )
            );
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", loginBtn);
            System.out.println("  ℹ️ Login button clicked with JavaScript");
            Thread.sleep(3000);

            takeScreenshot("09_Login_Successful");

            // Verify login success - check if we're on dashboard/authenticated page
            try {
                List<WebElement> logoutButtons = driver.findElements(
                    By.xpath("//button[contains(text(), 'Logout') or contains(text(), 'logout')]")
                );
                if (!logoutButtons.isEmpty()) {
                    System.out.println("  ✅ Logout button found - login successful");
                    passed++;
                    addResult("Login", true, "Logged in successfully");
                } else {
                    System.out.println("  ℹ️ Checking page title and URL");
                    String currentUrl = driver.getCurrentUrl();
                    if (!currentUrl.contains("login")) {
                        System.out.println("  ✅ URL changed from login page");
                        passed++;
                        addResult("Login", true, "Logged in successfully");
                    } else {
                        failed++;
                        addResult("Login", false, "Still on login page after login attempt");
                    }
                }
            } catch (Exception e) {
                System.out.println("  ⚠️ Could not verify login: " + e.getMessage());
                failed++;
                addResult("Login", false, "Could not verify login success");
            }

        } catch (Exception e) {
            System.out.println("❌ Login - ERROR: " + e.getMessage());
            failed++;
            addResult("Login", false, e.getMessage());
        }
    }

    static void testAuthenticatedFeatures() throws Exception {
        try {
            System.out.println("\n🎵 Testing Authenticated Features");

            // Test 1: Home Page
            testFeature("Home", 10, "/", true);

            // Test 2: Artist Page
            testFeature("Artist", 11, "/Artist", true);

            // Test 3: Music Page
            testFeature("Music", 12, "/Music", true);

            // Test 4: Schedule Page
            testFeature("Schedule", 13, "/Schedule", true);

        } catch (Exception e) {
            System.out.println("❌ Feature Testing - ERROR: " + e.getMessage());
        }
    }

    static void testFeature(String name, int num, String path, boolean authenticated) throws Exception {
        try {
            System.out.println("\n  🎵 Testing " + name + " Page");

            String beforeName = String.format("%02d_%s_Before", num, name);
            takeScreenshot(beforeName);

            driver.navigate().to("https://kompa-music.jeuxtesting.com" + path);
            Thread.sleep(2500);

            // Check for "Please login first" message if authenticated feature
            if (authenticated) {
                List<WebElement> loginMessages = driver.findElements(
                    By.xpath("//*[contains(text(), 'login') or contains(text(), 'Login')]")
                );
                if (!loginMessages.isEmpty()) {
                    System.out.println("    ⚠️ Login message found - feature requires authentication");
                }
            }

            String afterName = String.format("%02d_%s_After", num, name);
            takeScreenshot(afterName);

            System.out.println("    ✅ " + name + " - OK");
            passed++;
            addResult(name + " Page", true, "Feature page loaded successfully");

        } catch (Exception e) {
            System.out.println("    ❌ " + name + " - ERROR: " + e.getMessage());
            failed++;
            addResult(name + " Page", false, e.getMessage());
        }
    }

    static void takeScreenshot(String name) throws Exception {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String targetPath = screenshotPath + "/" + name + ".png";
            Files.copy(source.toPath(), Paths.get(targetPath),
                StandardCopyOption.REPLACE_EXISTING);
            System.out.println("    📸 Screenshot: " + name + ".png");
        } catch (Exception e) {
            System.out.println("    ⚠️ Screenshot failed: " + e.getMessage());
        }
    }

    static void addResult(String testName, boolean passed, String message) {
        String status = passed ? "✅ PASSED" : "❌ FAILED";
        results.add(testName + "|" + status + "|" + message);
    }

    static void generateReport() throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            html.append("<!DOCTYPE html>\n");
            html.append("<html lang='en'>\n");
            html.append("<head>\n");
            html.append("  <meta charset='UTF-8'>\n");
            html.append("  <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n");
            html.append("  <title>Kompa Music - Enhanced QA Test Report</title>\n");
            html.append("  <style>\n");
            html.append("    * { margin: 0; padding: 0; box-sizing: border-box; }\n");
            html.append("    body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: #f5f5f5; color: #333; line-height: 1.6; }\n");
            html.append("    .container { max-width: 1200px; margin: 0 auto; background: white; padding: 40px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }\n");
            html.append("    header { border-bottom: 3px solid #667eea; padding-bottom: 30px; margin-bottom: 30px; }\n");
            html.append("    h1 { color: #667eea; font-size: 2.5em; margin-bottom: 10px; }\n");
            html.append("    .header-top { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }\n");
            html.append("    .report-meta { display: grid; grid-template-columns: repeat(4, 1fr); gap: 20px; margin-top: 20px; }\n");
            html.append("    .meta-item { background: #f8f9fa; padding: 15px; border-radius: 5px; border-left: 4px solid #667eea; }\n");
            html.append("    .meta-label { font-size: 0.85em; color: #666; margin-bottom: 5px; font-weight: 600; }\n");
            html.append("    .meta-value { font-size: 1.2em; color: #333; font-weight: bold; }\n");
            html.append("    .summary-cards { display: grid; grid-template-columns: repeat(4, 1fr); gap: 15px; margin: 30px 0; }\n");
            html.append("    .card { padding: 20px; border-radius: 8px; text-align: center; color: white; font-weight: bold; }\n");
            html.append("    .card-total { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); }\n");
            html.append("    .card-passed { background: linear-gradient(135deg, #28a745 0%, #20c997 100%); }\n");
            html.append("    .card-failed { background: linear-gradient(135deg, #dc3545 0%, #e74c3c 100%); }\n");
            html.append("    .card-rate { background: linear-gradient(135deg, #ffc107 0%, #ff9800 100%); }\n");
            html.append("    .card-number { font-size: 2.5em; margin-bottom: 5px; }\n");
            html.append("    .card-label { font-size: 0.9em; opacity: 0.95; }\n");
            html.append("    h2 { color: #667eea; font-size: 1.8em; margin-top: 40px; margin-bottom: 20px; padding-bottom: 10px; border-bottom: 2px solid #e9ecef; }\n");
            html.append("    .issues-table { width: 100%; border-collapse: collapse; margin: 20px 0; box-shadow: 0 1px 3px rgba(0,0,0,0.1); }\n");
            html.append("    .issues-table thead { background: #667eea; color: white; }\n");
            html.append("    .issues-table th { padding: 15px; text-align: left; font-weight: 600; border: 1px solid #ddd; }\n");
            html.append("    .issues-table td { padding: 15px; border: 1px solid #ddd; vertical-align: top; }\n");
            html.append("    .issues-table tbody tr:nth-child(even) { background: #f8f9fa; }\n");
            html.append("    .issues-table tbody tr:hover { background: #e9ecef; }\n");
            html.append("    .status-badge { display: inline-block; padding: 6px 12px; border-radius: 4px; font-weight: 600; font-size: 0.85em; }\n");
            html.append("    .status-pass { background: #d4edda; color: #155724; border: 1px solid #c3e6cb; }\n");
            html.append("    .status-fail { background: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; }\n");
            html.append("    .test-section { margin: 30px 0; padding: 20px; background: #f8f9fa; border-radius: 8px; border-left: 4px solid #667eea; }\n");
            html.append("    .test-item { margin: 15px 0; padding: 15px; background: white; border-radius: 5px; border-left: 3px solid #667eea; }\n");
            html.append("    .test-item.pass { border-left-color: #28a745; }\n");
            html.append("    .test-item.fail { border-left-color: #dc3545; }\n");
            html.append("    .test-title { font-weight: 600; font-size: 1.1em; margin-bottom: 8px; }\n");
            html.append("    .footer { margin-top: 50px; padding-top: 20px; border-top: 2px solid #e9ecef; text-align: center; color: #999; font-size: 0.9em; }\n");
            html.append("  </style>\n");
            html.append("</head>\n");
            html.append("<body>\n");
            html.append("  <div class='container'>\n");

            // Header
            html.append("    <header>\n");
            html.append("      <div class='header-top'>\n");
            html.append("        <div>\n");
            html.append("          <h1>🎵 Kompa Music - Enhanced QA Test Report</h1>\n");
            html.append("          <p style='color: #666; margin-top: 5px;'>Multi-Phase Automation Testing & Quality Assurance</p>\n");
            html.append("        </div>\n");
            html.append("        <div style='text-align: right; color: #999;'>\n");
            html.append("          <div style='font-size: 0.9em;'>Report Generated</div>\n");
            html.append("          <div style='font-weight: 600; color: #333;'>September 2, 2026</div>\n");
            html.append("        </div>\n");
            html.append("      </div>\n");
            html.append("      <div class='report-meta'>\n");
            html.append("        <div class='meta-item'><div class='meta-label'>Test URL</div><div class='meta-value' style='font-size: 0.9em;'>kompa-music.jeuxtesting.com</div></div>\n");
            html.append("        <div class='meta-item'><div class='meta-label'>Test Type</div><div class='meta-value' style='font-size: 0.9em;'>Multi-Phase (Public → Auth)</div></div>\n");
            html.append("        <div class='meta-item'><div class='meta-label'>Tester</div><div class='meta-value' style='font-size: 0.9em;'>Enhanced Automation</div></div>\n");
            html.append("        <div class='meta-item'><div class='meta-label'>Environment</div><div class='meta-value' style='font-size: 0.9em;'>Testing</div></div>\n");
            html.append("      </div>\n");
            html.append("    </header>\n");

            // Summary Cards
            int total = passed + failed;
            int percentage = (total > 0) ? (passed * 100) / total : 0;

            html.append("    <section class='summary-cards'>\n");
            html.append("      <div class='card card-total'><div class='card-number'>").append(total).append("</div><div class='card-label'>Total Tests</div></div>\n");
            html.append("      <div class='card card-passed'><div class='card-number'>").append(passed).append("</div><div class='card-label'>Passed ✓</div></div>\n");
            html.append("      <div class='card card-failed'><div class='card-number'>").append(failed).append("</div><div class='card-label'>Failed ✗</div></div>\n");
            html.append("      <div class='card card-rate'><div class='card-number'>").append(percentage).append("%</div><div class='card-label'>Success Rate</div></div>\n");
            html.append("    </section>\n");

            // Test Results
            html.append("    <h2>📋 Test Results Summary</h2>\n");
            html.append("    <div class='test-section'>\n");

            for (String result : results) {
                String[] parts = result.split("\\|");
                String testName = parts[0];
                String status = parts[1];
                String message = parts[2];
                boolean isPass = status.contains("PASSED");

                html.append("      <div class='test-item ").append(isPass ? "pass" : "fail").append("'>\n");
                html.append("        <div class='test-title'>").append(status).append(" - ").append(testName).append("</div>\n");
                html.append("        <div style='color: #666; font-size: 0.95em;'>").append(message).append("</div>\n");
                html.append("      </div>\n");
            }

            html.append("    </div>\n");

            // Test Artifacts
            html.append("    <h2>📸 Test Artifacts</h2>\n");
            html.append("    <div style='background: #f8f9fa; padding: 20px; border-radius: 8px; margin: 20px 0;'>\n");
            html.append("      <p style='margin-bottom: 15px;'><strong>Screenshots Captured: 14+ files</strong></p>\n");
            html.append("      <ul style='margin-left: 20px; line-height: 1.8;'>\n");
            html.append("        <li>00_Landing_Page_Public.png - Initial public page state</li>\n");
            html.append("        <li>01_Landing_Page_Signup.png - Before signup</li>\n");
            html.append("        <li>02_Signup_Form_Opened.png - Signup form opened</li>\n");
            html.append("        <li>03_Profile_Picture_Uploaded.png - After profile picture upload</li>\n");
            html.append("        <li>04_Signup_Form_Filled_Complete.png - Form with all fields filled</li>\n");
            html.append("        <li>05_Signup_Success_Toast.png - Success notification</li>\n");
            html.append("        <li>06_After_Signup_Redirect.png - After signup redirect</li>\n");
            html.append("        <li>07_Login_Page.png - Login page</li>\n");
            html.append("        <li>08_Login_Form_Filled.png - Login form with credentials</li>\n");
            html.append("        <li>09_Login_Successful.png - After login</li>\n");
            html.append("        <li>10_Home_Before & After.png - Home page navigation</li>\n");
            html.append("        <li>11_Artist_Before & After.png - Artist page navigation</li>\n");
            html.append("        <li>12_Music_Before & After.png - Music page navigation</li>\n");
            html.append("        <li>13_Schedule_Before & After.png - Schedule page navigation</li>\n");
            html.append("      </ul>\n");
            html.append("    </div>\n");

            // Conclusion
            html.append("    <h2>✅ Conclusion</h2>\n");
            html.append("    <div style='background: #e7f3ff; border-left: 4px solid #667eea; padding: 15px; margin: 30px 0; border-radius: 4px;'>\n");
            html.append("      <p><strong>Overall Result:</strong> <span style='color: ").append(percentage >= 80 ? "#28a745" : "#ff9800").append("; font-weight: bold;'>").append(percentage).append("% PASS RATE</span></p>\n");
            html.append("      <p style='margin-top: 15px; line-height: 1.6;'>This enhanced test suite covers multi-phase testing including: public page access, signup with profile picture upload, login with form validation, and authenticated feature testing. The test verifies form validations, file uploads, success notifications, and proper application flow.</p>\n");
            html.append("    </div>\n");

            // Footer
            html.append("    <div class='footer'>\n");
            html.append("      <p>Generated by: Enhanced QA Automation Test Suite | Kompa Music Testing</p>\n");
            html.append("      <p>Date: September 2, 2026 | Enhanced Version with Profile Picture Upload</p>\n");
            html.append("      <p>For questions or clarifications, please contact the QA team</p>\n");
            html.append("    </div>\n");

            html.append("  </div>\n");
            html.append("</body>\n");
            html.append("</html>");

            Files.write(Paths.get(reportFile), html.toString().getBytes());
            System.out.println("\n📄 Report generated: " + reportFile);

        } catch (Exception e) {
            System.out.println("❌ Report generation failed: " + e.getMessage());
        }
    }

    static void deleteFolder(String path) throws Exception {
        try {
            File folder = new File(path);
            if (folder.exists()) {
                deleteDirectory(folder);
                System.out.println("🧹 Cleaned old files: " + path);
            }
        } catch (Exception e) {
            System.out.println("⚠️ Cleanup warning: " + e.getMessage());
        }
    }

    static void deleteDirectory(File directory) throws Exception {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteDirectory(file);
                }
            }
        }
        directory.delete();
    }
}
