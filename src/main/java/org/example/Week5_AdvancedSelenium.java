package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.Set;

public class Week5_AdvancedSelenium {
    static WebDriver driver;
    static WebDriverWait wait;

    public static void main(String[] args) {
        System.out.println("🚀 WEEK 5: ADVANCED SELENIUM & WAITS");
        System.out.println("=" .repeat(60));

        // Initialize WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Test 1: Advanced Locators (XPath, CSS)
            testAdvancedLocators();

            // Test 2: Dropdowns
            testDropdownHandling();

            // Test 3: Checkboxes
            testCheckboxes();

            // Test 4: Alerts
            testAlerts();

            // Test 5: Frames
            testFrames();

            // Test 6: Multiple Windows
            testMultipleWindows();

            // Test 7: Advanced Waits
            testAdvancedWaits();

            System.out.println("\n✅ WEEK 5 TESTING COMPLETED!");

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close browser
            if (driver != null) {
                driver.quit();
                System.out.println("Browser closed.");
            }
        }
    }

    // ========== TEST 1: ADVANCED LOCATORS ==========
    private static void testAdvancedLocators() {
        System.out.println("\n📍 TEST 1: Advanced Locators (XPath, CSS)");
        System.out.println("-" .repeat(60));

        try {
            driver.get("https://www.saucedemo.com");

            // CSS Selector - Attribute based
            // syntax: tagname[attribute='value']
            System.out.println("✓ CSS Selector with attribute: input[name='user-name']");
            WebElement usernameCSS = driver.findElement(By.cssSelector("input[name='user-name']"));
            usernameCSS.sendKeys("standard_user");

            // XPath - Advanced with contains()
            // syntax: //*[contains(@attribute, 'value')]
            System.out.println("✓ XPath with contains(): //*[contains(@placeholder, 'Password')]");
            WebElement passwordXPath = driver.findElement(By.xpath("//*[contains(@placeholder, 'Password')]"));
            passwordXPath.sendKeys("secret_sauce");

            // XPath - with text()
            // syntax: //*[text()='value']
            System.out.println("✓ XPath with text(): //button[text()='Login']");
            WebElement loginBtn = driver.findElement(By.xpath("//button[text()='Login']"));
            loginBtn.click();

            Thread.sleep(2000);
            System.out.println("✅ Advanced Locators Test PASSED");

        } catch (Exception e) {
            System.out.println("❌ Advanced Locators Test FAILED: " + e.getMessage());
        }
    }

    // ========== TEST 2: DROPDOWN HANDLING ==========
    private static void testDropdownHandling() {
        System.out.println("\n📋 TEST 2: Dropdown Handling");
        System.out.println("-" .repeat(60));

        try {
            // Wait for dropdown to be clickable
            WebElement sortDropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(By.className("product_sort_container"))
            );

            // Create Select object
            Select select = new Select(sortDropdown);

            // Method 1: Select by visible text
            System.out.println("✓ Select by visible text: 'Price (low to high)'");
            select.selectByVisibleText("Price (low to high)");
            Thread.sleep(1000);

            // Method 2: Select by value
            System.out.println("✓ Select by value");
            select.selectByValue("hlo");
            Thread.sleep(1000);

            // Method 3: Select by index
            System.out.println("✓ Select by index: 2");
            select.selectByIndex(2);
            Thread.sleep(1000);

            // Get selected option
            WebElement selectedOption = select.getFirstSelectedOption();
            System.out.println("✓ Selected option: " + selectedOption.getText());

            System.out.println("✅ Dropdown Handling Test PASSED");

        } catch (Exception e) {
            System.out.println("❌ Dropdown Handling Test FAILED: " + e.getMessage());
        }
    }

    // ========== TEST 3: CHECKBOXES ==========
    private static void testCheckboxes() {
        System.out.println("\n☑️ TEST 3: Checkboxes");
        System.out.println("-" .repeat(60));

        try {
            // Navigate to checkbox demo site
            driver.get("https://the-internet.herokuapp.com/checkboxes");
            Thread.sleep(1000);

            // Find all checkboxes
            java.util.List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
            System.out.println("✓ Found " + checkboxes.size() + " checkboxes");

            // Check first checkbox
            if (!checkboxes.get(0).isSelected()) {
                System.out.println("✓ Checking checkbox 1");
                checkboxes.get(0).click();
            }

            // Uncheck second checkbox
            if (checkboxes.get(1).isSelected()) {
                System.out.println("✓ Unchecking checkbox 2");
                checkboxes.get(1).click();
            }

            // Verify state
            System.out.println("✓ Checkbox 1 is selected: " + checkboxes.get(0).isSelected());
            System.out.println("✓ Checkbox 2 is selected: " + checkboxes.get(1).isSelected());

            System.out.println("✅ Checkboxes Test PASSED");

        } catch (Exception e) {
            System.out.println("❌ Checkboxes Test FAILED: " + e.getMessage());
        }
    }

    // ========== TEST 4: ALERTS ==========
    private static void testAlerts() {
        System.out.println("\n⚠️ TEST 4: Alerts");
        System.out.println("-" .repeat(60));

        try {
            // Navigate to alerts page
            driver.get("https://the-internet.herokuapp.com/javascript_alerts");
            Thread.sleep(1000);

            // Test 1: Simple Alert
            System.out.println("✓ Testing Simple Alert...");
            driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
            Thread.sleep(1000);

            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("✓ Alert text: " + alert.getText());
            alert.accept(); // Click OK
            Thread.sleep(1000);

            // Test 2: Confirm Alert
            System.out.println("✓ Testing Confirm Alert...");
            driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
            Thread.sleep(1000);

            alert = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("✓ Confirm text: " + alert.getText());
            alert.dismiss(); // Click Cancel
            Thread.sleep(1000);

            // Test 3: Prompt Alert
            System.out.println("✓ Testing Prompt Alert...");
            driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
            Thread.sleep(1000);

            alert = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("✓ Prompt text: " + alert.getText());
            alert.sendKeys("Hello Selenium!"); // Type in prompt
            alert.accept(); // Click OK
            Thread.sleep(1000);

            System.out.println("✅ Alerts Test PASSED");

        } catch (Exception e) {
            System.out.println("❌ Alerts Test FAILED: " + e.getMessage());
        }
    }

    // ========== TEST 5: FRAMES ==========
    private static void testFrames() {
        System.out.println("\n🖼️ TEST 5: Frames & iFrames");
        System.out.println("-" .repeat(60));

        try {
            // Navigate to frames page
            driver.get("https://the-internet.herokuapp.com/frames");
            Thread.sleep(1000);

            // Switch to frame by index
            System.out.println("✓ Switching to frame by index: 0");
            driver.switchTo().frame(0);

            String content = driver.findElement(By.tagName("h1")).getText();
            System.out.println("✓ Frame content: " + content);

            // Switch back to main page
            System.out.println("✓ Switching back to main page");
            driver.switchTo().defaultContent();

            // Switch to nested frame by name/ID
            System.out.println("✓ Switching to frame by name");
            driver.switchTo().frame("iframeResult");
            Thread.sleep(500);

            // Switch back
            driver.switchTo().defaultContent();

            System.out.println("✅ Frames Test PASSED");

        } catch (Exception e) {
            System.out.println("❌ Frames Test FAILED: " + e.getMessage());
        }
    }

    // ========== TEST 6: MULTIPLE WINDOWS ==========
    private static void testMultipleWindows() {
        System.out.println("\n🪟 TEST 6: Multiple Windows/Tabs");
        System.out.println("-" .repeat(60));

        try {
            // Navigate to page with links
            driver.get("https://the-internet.herokuapp.com/windows");
            Thread.sleep(1000);

            // Get current window handle
            String mainWindow = driver.getWindowHandle();
            System.out.println("✓ Main window: " + mainWindow);

            // Click link to open new window
            driver.findElement(By.linkText("Click Here")).click();
            Thread.sleep(1000);

            // Get all window handles
            Set<String> allWindows = driver.getWindowHandles();
            System.out.println("✓ Total windows: " + allWindows.size());

            // Switch to new window
            for (String window : allWindows) {
                if (!window.equals(mainWindow)) {
                    System.out.println("✓ Switching to new window: " + window);
                    driver.switchTo().window(window);

                    String title = driver.getTitle();
                    System.out.println("✓ New window title: " + title);

                    // Close new window
                    driver.close();
                    break;
                }
            }

            // Switch back to main window
            System.out.println("✓ Switching back to main window");
            driver.switchTo().window(mainWindow);

            System.out.println("✅ Multiple Windows Test PASSED");

        } catch (Exception e) {
            System.out.println("❌ Multiple Windows Test FAILED: " + e.getMessage());
        }
    }

    // ========== TEST 7: ADVANCED WAITS ==========
    private static void testAdvancedWaits() {
        System.out.println("\n⏱️ TEST 7: Advanced Waits");
        System.out.println("-" .repeat(60));

        try {
            driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
            Thread.sleep(1000);

            // Wait 1: elementToBeClickable
            System.out.println("✓ Wait for element to be clickable");
            WebElement startBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("start"))
            );
            startBtn.click();
            Thread.sleep(1000);

            // Wait 2: visibilityOfElementLocated
            System.out.println("✓ Wait for element to be visible");
            WebElement loadingElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("finish"))
            );
            System.out.println("✓ Element visible: " + loadingElement.getText());

            // Wait 3: presenceOfElementLocated
            System.out.println("✓ Wait for element presence");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish")));

            // Wait 4: Custom wait condition
            System.out.println("✓ Custom wait example");
            wait.until(driver2 -> driver2.findElement(By.id("finish")).isDisplayed());

            System.out.println("✅ Advanced Waits Test PASSED");

        } catch (Exception e) {
            System.out.println("❌ Advanced Waits Test FAILED: " + e.getMessage());
        }
    }
}