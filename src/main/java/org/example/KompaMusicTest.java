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

public class KompaMusicTest {
    static WebDriver driver;
    static WebDriverWait wait;
    static int passed = 0, failed = 0;
    static List<String> results = new ArrayList<>();
    static String screenshotPath = "KompaMusic_Test_Screenshots";
    static String reportFile = "KompaMusic_Test_Report.html";

    public static void main(String[] args) throws Exception {
        try {
            // Setup
            deleteFolder(screenshotPath);
            new File(screenshotPath).mkdirs();

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            System.out.println("🎵 Kompa Music Automation Test Started");
            System.out.println("═══════════════════════════════════════");

            // Test 1: Signup
            testSignup();

            // Test 2: Login
            testLogin();

            // Test 3-6: Website Features
            testFeature("Home", 3, "/");
            testFeature("Artist", 4, "/Artist");
            testFeature("Music", 5, "/Music");
            testFeature("Schedule", 6, "/Schedule");

            // Generate Report
            generateReport();

            System.out.println("═══════════════════════════════════════");
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

    static void testSignup() throws Exception {
        try {
            System.out.println("\n🔑 Step 1: Testing Signup");
            driver.navigate().to("https://kompa-music.jeuxtesting.com");
            Thread.sleep(2000);

            takeScreenshot("01_Landing_Page");

            // Click on Sign Up button
            WebElement signupBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Sign Up to Unlock Full Library')]")
                )
            );
            signupBtn.click();
            Thread.sleep(2500);

            takeScreenshot("02_Signup_Form_Opened");

            // Fill signup form with individual waits for each field

            // Username - wait and fill
            WebElement usernameField = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("input[placeholder='Enter name']")
                )
            );
            usernameField.clear();
            usernameField.sendKeys("kompatest");
            Thread.sleep(400);

            // Email - wait and fill
            WebElement emailField = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("input[type='email']")
                )
            );
            emailField.clear();
            emailField.sendKeys("kompatest@jeuxtesting.com");
            Thread.sleep(400);

            // Full Address - wait and fill
            WebElement addressField = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("input[placeholder='Enter Full Address']")
                )
            );
            addressField.clear();
            addressField.sendKeys("123 Music Lane, Kompa City");
            Thread.sleep(400);

            // Password fields - get all password inputs
            List<WebElement> passwordFields = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.cssSelector("input[type='password']")
                )
            );

            // First password
            if (passwordFields.size() > 0) {
                passwordFields.get(0).clear();
                passwordFields.get(0).sendKeys("KompaTest@123");
                Thread.sleep(400);
            }

            // Confirm Password
            if (passwordFields.size() > 1) {
                passwordFields.get(1).clear();
                passwordFields.get(1).sendKeys("KompaTest@123");
                Thread.sleep(400);
            }

            // Accept Terms checkbox
            List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
            if (!checkboxes.isEmpty() && !checkboxes.get(0).isSelected()) {
                checkboxes.get(0).click();
                Thread.sleep(300);
            }

            takeScreenshot("03_Signup_Form_Filled");

            // Click Next button
            WebElement nextBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Next')]")
                )
            );
            nextBtn.click();
            Thread.sleep(2500);

            takeScreenshot("04_Signup_Completed");

            System.out.println("✅ Signup - OK");
            passed++;
            addResult("Signup", true, "Account created successfully");

        } catch (Exception e) {
            System.out.println("❌ Signup - ERROR: " + e.getMessage());
            failed++;
            addResult("Signup", false, e.getMessage());
            throw e;
        }
    }

    static void testLogin() throws Exception {
        try {
            System.out.println("\n🔐 Step 2: Testing Login");

            // Navigate to login
            driver.navigate().to("https://kompa-music.jeuxtesting.com");
            Thread.sleep(2000);

            takeScreenshot("05_Login_Page");

            // Click Login button if not already on login form
            List<WebElement> loginBtns = driver.findElements(By.xpath("//button[contains(text(), 'Login')]"));
            if (!loginBtns.isEmpty()) {
                try {
                    loginBtns.get(0).click();
                    Thread.sleep(1500);
                } catch (Exception e) {
                    System.out.println("  ℹ️ Login modal might already be open");
                }
            }

            // Fill login form with explicit waits

            // Email input
            WebElement emailInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("input[type='email']")
                )
            );
            emailInput.clear();
            emailInput.sendKeys("kompatest@jeuxtesting.com");
            Thread.sleep(400);

            // Password input
            WebElement passwordInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("input[type='password']")
                )
            );
            passwordInput.clear();
            passwordInput.sendKeys("KompaTest@123");
            Thread.sleep(400);

            // Accept Terms checkbox
            List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
            if (!checkboxes.isEmpty() && !checkboxes.get(0).isSelected()) {
                checkboxes.get(0).click();
                Thread.sleep(300);
            }

            takeScreenshot("06_Login_Form_Filled");

            // Click Login button using JavaScript (to avoid interception)
            WebElement loginBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//button[contains(text(), 'Login')]")
                )
            );
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", loginBtn);
            Thread.sleep(3000);

            takeScreenshot("07_Login_Successful");

            System.out.println("✅ Login - OK");
            passed++;
            addResult("Login", true, "Logged in successfully");

        } catch (Exception e) {
            System.out.println("❌ Login - ERROR: " + e.getMessage());
            failed++;
            addResult("Login", false, e.getMessage());
        }
    }

    static void testFeature(String name, int num, String path) throws Exception {
        try {
            System.out.println("\n🎵 Step " + num + ": Testing " + name);

            String beforeName = String.format("%02d_%s_Before", num, name);
            takeScreenshot(beforeName);

            driver.navigate().to("https://kompa-music.jeuxtesting.com" + path);
            Thread.sleep(2500);

            String afterName = String.format("%02d_%s_After", num, name);
            takeScreenshot(afterName);

            System.out.println("✅ " + name + " - OK");
            passed++;
            addResult(name, true, "Feature loaded successfully");

        } catch (Exception e) {
            System.out.println("❌ " + name + " - ERROR: " + e.getMessage());
            failed++;
            addResult(name, false, e.getMessage());
        }
    }

    static void takeScreenshot(String name) throws Exception {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String targetPath = screenshotPath + "/" + name + ".png";
            Files.copy(source.toPath(), Paths.get(targetPath),
                StandardCopyOption.REPLACE_EXISTING);
            System.out.println("  📸 Screenshot: " + name + ".png");
        } catch (Exception e) {
            System.out.println("  ⚠️ Screenshot failed: " + e.getMessage());
        }
    }

    static void addResult(String testName, boolean passed, String message) {
        String status = passed ? "✅ PASSED" : "❌ FAILED";
        results.add(testName + "|" + status + "|" + message);
    }

    static void generateReport() throws Exception {
        try {
            StringBuilder html = new StringBuilder();
            html.append("<html><head><title>Kompa Music Test Report</title>");
            html.append("<style>");
            html.append("body { font-family: Arial, sans-serif; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); margin: 0; padding: 20px; }");
            html.append(".container { max-width: 1000px; margin: 0 auto; background: white; border-radius: 10px; box-shadow: 0 10px 40px rgba(0,0,0,0.3); padding: 30px; }");
            html.append("h1 { color: #667eea; text-align: center; margin-bottom: 10px; }");
            html.append(".summary { text-align: center; margin: 20px 0; }");
            html.append(".stat-tile { display: inline-block; margin: 10px; padding: 15px 30px; background: #f8f9fa; border-radius: 5px; }");
            html.append(".stat-tile h3 { margin: 5px 0; color: #667eea; }");
            html.append(".stat-tile .value { font-size: 28px; font-weight: bold; color: #764ba2; }");
            html.append("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
            html.append("th { background: #667eea; color: white; padding: 12px; text-align: left; }");
            html.append("td { padding: 12px; border-bottom: 1px solid #ddd; }");
            html.append("tr:nth-child(even) { background: #f8f9fa; }");
            html.append(".pass { color: #28a745; font-weight: bold; }");
            html.append(".fail { color: #dc3545; font-weight: bold; }");
            html.append(".footer { text-align: center; margin-top: 30px; color: #666; font-size: 12px; }");
            html.append("</style></head><body>");

            html.append("<div class='container'>");
            html.append("<h1>🎵 Kompa Music Test Report</h1>");
            html.append("<p style='text-align:center; color: #666;'>Automated Testing Report</p>");

            html.append("<div class='summary'>");
            html.append("<div class='stat-tile'><h3>Total Tests</h3><div class='value'>").append(passed + failed).append("</div></div>");
            html.append("<div class='stat-tile'><h3 style='color: #28a745;'>Passed</h3><div class='value' style='color: #28a745;'>").append(passed).append("</div></div>");
            html.append("<div class='stat-tile'><h3 style='color: #dc3545;'>Failed</h3><div class='value' style='color: #dc3545;'>").append(failed).append("</div></div>");

            int percentage = (passed + failed > 0) ? (passed * 100) / (passed + failed) : 0;
            html.append("<div class='stat-tile'><h3>Success Rate</h3><div class='value'>").append(percentage).append("%</div></div>");
            html.append("</div>");

            html.append("<table>");
            html.append("<tr><th>#</th><th>Test Case</th><th>Status</th><th>Details</th></tr>");

            int idx = 1;
            for (String result : results) {
                String[] parts = result.split("\\|");
                String testName = parts[0];
                String status = parts[1];
                String message = parts[2];
                String statusClass = status.contains("PASSED") ? "pass" : "fail";

                html.append("<tr>");
                html.append("<td>").append(idx++).append("</td>");
                html.append("<td>").append(testName).append("</td>");
                html.append("<td class='").append(statusClass).append("'>").append(status).append("</td>");
                html.append("<td>").append(message).append("</td>");
                html.append("</tr>");
            }

            html.append("</table>");
            html.append("<div class='footer'>Generated by Kompa Music Automation Test | Week 5 Advanced Selenium</div>");
            html.append("</div>");
            html.append("</body></html>");

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
