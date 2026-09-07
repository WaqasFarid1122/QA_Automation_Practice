package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.FileWriter;
import java.time.Duration;

public class SauceDemoAdvancedTest {
    static WebDriver driver;
    static String screenshotPath = "SauceDemo_Advanced_Screenshots";
    static String reportPath = "SauceDemo_Advanced_Report.html";
    static int passed = 0, failed = 0;
    static StringBuilder reportHTML = new StringBuilder();

    public static void main(String[] args) throws Exception {
        // Purana folder delete karna
        System.out.println("🧹 Cleaning old files...");
        deleteFolder(new File(screenshotPath));
        new File(reportPath).delete();
        new File(screenshotPath).mkdirs();

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║   🚀 SAUCEDEMO ADVANCED TEST - WEEK 5              ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");

        try {
            // TEST 1: LOGIN WITH EXPLICIT WAIT
            System.out.println("📍 TEST 1: LOGIN WITH EXPLICIT WAIT\n");
            testLogin();

            // TEST 2: DROPDOWN SELECTION
            System.out.println("\n📍 TEST 2: DROPDOWN SELECTION\n");
            testDropdown();

            // TEST 3: CHECKBOX INTERACTION
            System.out.println("\n📍 TEST 3: ADD TO CART\n");
            testAddToCart();

            // TEST 4: ADVANCED LOCATORS
            System.out.println("\n📍 TEST 4: VERIFY ADVANCED LOCATORS\n");
            testAdvancedLocators();

            // REPORT
            System.out.println("\n╔════════════════════════════════════════════════════╗");
            System.out.println("║         📊 TEST SUMMARY                            ║");
            System.out.println("╚════════════════════════════════════════════════════╝\n");
            System.out.println("✅ Passed: " + passed);
            System.out.println("❌ Failed: " + failed);
            System.out.println("📊 Total:  " + (passed + failed));
            if ((passed + failed) > 0) {
                System.out.println("📈 Rate:   " + (100 * passed / (passed + failed)) + "%\n");
            }

            generateReport();
            System.out.println("✅ Report saved: " + reportPath);
            System.out.println("📁 Screenshots saved in: " + screenshotPath + "\n");

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Thread.sleep(2000);
            driver.quit();
            System.out.println("🔒 Browser closed\n");
        }
    }

    // TEST 1: LOGIN WITH EXPLICIT WAIT
    static void testLogin() throws Exception {
        try {
            driver.get("https://www.saucedemo.com/");
            takeScreenshot("01_Login_Page");
            System.out.println("[1/4] Login page loaded ✅");

            // Explicit wait - WebDriverWait se element wait karna
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement usernameField = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.id("user-name"))
            );
            usernameField.sendKeys("standard_user");
            Thread.sleep(1000);
            takeScreenshot("02_Username_Entered");
            System.out.println("[2/4] Username entered ✅");

            // Password field - By.cssSelector use karna
            WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
            passwordField.sendKeys("secret_sauce");
            Thread.sleep(1000);
            takeScreenshot("03_Password_Entered");
            System.out.println("[3/4] Password entered ✅");

            // Login button - By.id() use karna
            WebElement loginButton = driver.findElement(By.id("login-button"));
            loginButton.click();
            Thread.sleep(2000);

            // Wait for inventory page to load
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("inventory_container")));
            takeScreenshot("04_Dashboard_Loaded");
            System.out.println("[4/4] Dashboard loaded ✅");

            passed++;
            addResult("LOGIN WITH EXPLICIT WAIT", true, "Successfully logged in using WebDriverWait");

        } catch (Exception e) {
            System.out.println("❌ Login Test Failed: " + e.getMessage());
            failed++;
            addResult("LOGIN WITH EXPLICIT WAIT", false, e.getMessage());
        }
    }

    // TEST 2: DROPDOWN SELECTION
    static void testDropdown() throws Exception {
        try {
            takeScreenshot("05_Dropdown_Before");
            System.out.println("[1/2] Before dropdown selection ✅");

            // Dropdown ko Select class se handle karna
            WebElement dropdownElement = driver.findElement(By.className("product_sort_container"));
            Select dropdown = new Select(dropdownElement);

            // Dropdown mein "Price (high to low)" select karna
            dropdown.selectByValue("hilo");
            Thread.sleep(1500);

            takeScreenshot("06_Dropdown_After");
            System.out.println("[2/2] Dropdown selected (High to Low) ✅");

            passed++;
            addResult("DROPDOWN SELECTION", true, "Successfully selected 'Price (high to low)' from dropdown");

        } catch (Exception e) {
            System.out.println("❌ Dropdown Test Failed: " + e.getMessage());
            failed++;
            addResult("DROPDOWN SELECTION", false, e.getMessage());
        }
    }

    // TEST 3: ADD TO CART
    static void testAddToCart() throws Exception {
        try {
            takeScreenshot("07_Before_AddToCart");
            System.out.println("[1/2] Before adding to cart ✅");

            // XPath se button find karna - advanced locator
            String xpathButton = "//button[@data-test='add-to-cart-sauce-labs-fleece-jacket']";
            WebElement addButton = driver.findElement(By.xpath(xpathButton));
            addButton.click();
            Thread.sleep(1500);

            takeScreenshot("08_After_AddToCart");
            System.out.println("[2/2] Item added to cart ✅");

            passed++;
            addResult("ADD TO CART", true, "Successfully added item to cart using XPath locator");

        } catch (Exception e) {
            System.out.println("❌ Add to Cart Failed: " + e.getMessage());
            failed++;
            addResult("ADD TO CART", false, e.getMessage());
        }
    }

    // TEST 4: ADVANCED LOCATORS
    static void testAdvancedLocators() throws Exception {
        try {
            // CSS Selector - attribute-based
            WebElement productName = driver.findElement(By.cssSelector("div.inventory_item_name"));
            String name = productName.getText();
            System.out.println("[1/2] Product name (CSS): " + name + " ✅");

            // XPath with contains() - advanced technique
            WebElement price = driver.findElement(By.xpath("//div[@class='inventory_item_price']"));
            String priceText = price.getText();
            System.out.println("[2/2] Product price (XPath): " + priceText + " ✅");

            takeScreenshot("09_Advanced_Locators");

            passed++;
            addResult("ADVANCED LOCATORS", true, "CSS Selector and XPath with contains() working correctly");

        } catch (Exception e) {
            System.out.println("❌ Advanced Locators Failed: " + e.getMessage());
            failed++;
            addResult("ADVANCED LOCATORS", false, e.getMessage());
        }
    }

    // Folder recursively delete karna
    static void deleteFolder(File folder) {
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteFolder(file);
                    } else {
                        file.delete();
                    }
                }
            }
            folder.delete();
            System.out.println("✅ Old folder deleted");
        }
    }

    // Screenshot lena
    static void takeScreenshot(String name) throws Exception {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String path = screenshotPath + File.separator + name + ".png";
        Files.copy(source.toPath(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
    }

    // Report mein result add karna
    static void addResult(String test, boolean pass, String message) {
        String status = pass ? "✅ PASS" : "❌ FAIL";
        String color = pass ? "green" : "red";
        reportHTML.append("<div style='border-left: 4px solid ").append(color).append("; padding: 10px; margin: 10px 0; background: #f5f5f5;'>");
        reportHTML.append("<strong>").append(status).append(" - ").append(test).append("</strong><br>");
        reportHTML.append(message).append("</div>\n");
    }

    // HTML report generate karna
    static void generateReport() throws Exception {
        String html = "<html><head><title>SauceDemo Advanced Report</title><style>" +
                "body { font-family: Arial; background: #f5f5f5; padding: 20px; }" +
                ".container { max-width: 900px; margin: 0 auto; background: white; padding: 30px; border-radius: 8px; }" +
                "h1 { color: #333; border-bottom: 3px solid #3498db; padding-bottom: 10px; }" +
                ".stats { display: grid; grid-template-columns: 1fr 1fr 1fr 1fr; gap: 10px; margin: 20px 0; }" +
                ".stat { background: #3498db; color: white; padding: 20px; border-radius: 5px; text-align: center; }" +
                ".stat.pass { background: #27ae60; }" +
                ".stat.fail { background: #e74c3c; }" +
                ".stat-num { font-size: 28px; font-weight: bold; }" +
                "</style></head><body>" +
                "<div class='container'>" +
                "<h1>🚀 SauceDemo Advanced Test Report (Week 5)</h1>" +
                "<div class='stats'>" +
                "<div class='stat'><div class='stat-num'>" + (passed + failed) + "</div><div>Total</div></div>" +
                "<div class='stat pass'><div class='stat-num'>" + passed + "</div><div>Passed</div></div>" +
                "<div class='stat fail'><div class='stat-num'>" + failed + "</div><div>Failed</div></div>" +
                "<div class='stat'><div class='stat-num'>" + (passed + failed > 0 ? (100 * passed / (passed + failed)) : 0) + "%</div><div>Success</div></div>" +
                "</div>" +
                "<h2>📋 Results:</h2>" +
                reportHTML.toString() +
                "<hr><p style='text-align: center; color: #999;'>Advanced Selenium Testing - Week 5</p>" +
                "</div></body></html>";

        FileWriter writer = new FileWriter(reportPath);
        writer.write(html);
        writer.close();
    }
}