import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.Set;

public class Week5_MultipleWindows {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            System.out.println("🔄 WEEK 5 - MULTIPLE WINDOWS HANDLING");
            System.out.println("================================");

            testGetWindowHandle(driver);
            testOpenNewWindow(driver);
            testGetAllWindowHandles(driver);
            testSwitchBetweenWindows(driver);
            testCloseWindow(driver);

            System.out.println("================================");
            System.out.println("✅ WEEK 5 MULTIPLE WINDOWS TESTING COMPLETED!");

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
            System.out.println("✔️ Browser closed");
        }
    }

    static void testGetWindowHandle(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 1: Get Current Window Handle");
            driver.get("https://www.saucedemo.com/");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            String currentHandle = driver.getWindowHandle();
            System.out.println("   ✔️ Current window handle: " + currentHandle);
            System.out.println("   ✔️ Syntax: String handle = driver.getWindowHandle()");
            System.out.println("   ✔️ Each window/tab has unique handle");
            System.out.println("   ✔️ Handle looks like: CDwindow-XXXXXXXXXXXXX");

        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }

    static void testOpenNewWindow(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 2: Open New Window");
            driver.get("https://www.google.com/");

            System.out.println("   ✔️ To open new window/tab:");
            System.out.println("   ✔️ Syntax: driver.switchTo().newWindow(WindowType.WINDOW)");
            System.out.println("   ✔️ Or: driver.switchTo().newWindow(WindowType.TAB)");
            System.out.println("   ✔️ Example:");
            System.out.println("      // Open new tab");
            System.out.println("      driver.switchTo().newWindow(WindowType.TAB);");
            System.out.println("      driver.get('https://www.example.com');");
            System.out.println("   ✔️ Or click link with target='_blank':");
            System.out.println("      WebElement link = driver.findElement(By.linkText('Link'));");
            System.out.println("      link.click();  // Opens in new window");

        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }

    static void testGetAllWindowHandles(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 3: Get All Window Handles");
            driver.get("https://www.saucedemo.com/");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            Set<String> allHandles = driver.getWindowHandles();
            System.out.println("   ✔️ Total windows/tabs: " + allHandles.size());
            System.out.println("   ✔️ Syntax: Set<String> handles = driver.getWindowHandles()");
            System.out.println("   ✔️ Returns set of all window handles");
            System.out.println("   ✔️ Example:");
            System.out.println("      Set<String> allHandles = driver.getWindowHandles();");
            System.out.println("      for (String handle : allHandles) {");
            System.out.println("          System.out.println(handle);");
            System.out.println("      }");

        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }

    static void testSwitchBetweenWindows(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 4: Switch Between Windows");
            driver.get("https://www.saucedemo.com/");

            String mainWindowHandle = driver.getWindowHandle();
            System.out.println("   ✔️ Main window handle: " + mainWindowHandle);
            System.out.println("   ✔️ To switch to another window:");
            System.out.println("   ✔️ Syntax: driver.switchTo().window(windowHandle)");
            System.out.println("   ✔️ Example:");
            System.out.println("      String mainWindow = driver.getWindowHandle();");
            System.out.println("      Set<String> allHandles = driver.getWindowHandles();");
            System.out.println("      for (String handle : allHandles) {");
            System.out.println("          if (!handle.equals(mainWindow)) {");
            System.out.println("              driver.switchTo().window(handle);");
            System.out.println("              // Work in new window");
            System.out.println("              break;");
            System.out.println("          }");
            System.out.println("      }");
            System.out.println("      // Switch back to main");
            System.out.println("      driver.switchTo().window(mainWindow);");

        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }

    static void testCloseWindow(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 5: Close Window/Tab");
            driver.get("https://www.google.com/");

            System.out.println("   ✔️ To close current window:");
            System.out.println("   ✔️ Syntax: driver.close()");
            System.out.println("   ✔️ Example:");
            System.out.println("      String mainWindow = driver.getWindowHandle();");
            System.out.println("      // Open and work in new window");
            System.out.println("      driver.switchTo().newWindow(WindowType.TAB);");
            System.out.println("      driver.get('https://www.example.com');");
            System.out.println("      // Do some work...");
            System.out.println("      // Close new window");
            System.out.println("      driver.close();");
            System.out.println("      // Switch back to main");
            System.out.println("      driver.switchTo().window(mainWindow);");
            System.out.println("   ⚠️  Note: driver.quit() closes ALL windows and ends session");

        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }
}

/*
 * 📚 KEY CONCEPTS - MULTIPLE WINDOWS/TABS:
 *
 * 1. GET CURRENT WINDOW HANDLE:
 *    String handle = driver.getWindowHandle();
 *
 * 2. GET ALL WINDOW HANDLES:
 *    Set<String> allHandles = driver.getWindowHandles();
 *
 * 3. SWITCH TO WINDOW:
 *    driver.switchTo().window(windowHandle);
 *
 * 4. OPEN NEW WINDOW:
 *    driver.switchTo().newWindow(WindowType.WINDOW);
 *    driver.switchTo().newWindow(WindowType.TAB);
 *
 * 5. CLOSE CURRENT WINDOW:
 *    driver.close();
 *
 * 6. QUIT ALL WINDOWS:
 *    driver.quit();  // Closes all and ends session
 *
 * 7. COMMON PATTERN:
 *    String mainWindow = driver.getWindowHandle();
 *    // Open new window
 *    driver.switchTo().newWindow(WindowType.TAB);
 *    driver.get("https://example.com");
 *    // Work in new window
 *    // Close new window
 *    driver.close();
 *    // Switch back to main
 *    driver.switchTo().window(mainWindow);
 *
 * 8. ITERATE THROUGH WINDOWS:
 *    for (String handle : driver.getWindowHandles()) {
 *        driver.switchTo().window(handle);
 *        if (driver.getTitle().contains("Expected Title")) {
 *            // Found the window
 *            break;
 *        }
 *    }
 *
 * ✅ BEST PRACTICES:
 *    - Store main window handle before opening new windows
 *    - Switch back to main window when done
 *    - Use close() only for unwanted windows, quit() ends session
 *    - Wait for window to load before interacting
 *    - Use window title to identify correct window
 *    - Handle windows by iterating through all handles
 *
 * ⚠️ COMMON ERRORS:
 *    - NoSuchWindowException: Window handle not found
 *    - Forgot to switch to new window before interacting
 *    - Using quit() instead of close() when you want to continue
 *    - Not storing main window handle before opening new windows
 */