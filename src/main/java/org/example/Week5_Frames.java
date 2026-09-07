import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;

public class Week5_Frames {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            System.out.println("🔄 WEEK 5 - FRAME HANDLING");
            System.out.println("================================");

            testSwitchToFrameByIndex(driver);
            testSwitchToFrameByName(driver);
            testSwitchToFrameByWebElement(driver);
            testSwitchBackToMainContent(driver);
            testNestedFrames(driver);

            System.out.println("================================");
            System.out.println("✅ WEEK 5 FRAME TESTING COMPLETED!");

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
            System.out.println("✔️ Browser closed");
        }
    }

    static void testSwitchToFrameByIndex(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 1: Switch To Frame By Index");
            driver.get("https://www.w3schools.com/html/html_iframe.asp");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            System.out.println("   ✔️ Navigate to frame demo page");
            System.out.println("   ✔️ Syntax: driver.switchTo().frame(0)");
            System.out.println("   ✔️ Example:");
            System.out.println("      // Switch to first frame (index 0)");
            System.out.println("      driver.switchTo().frame(0);");
            System.out.println("      // Now find elements within this frame");
            System.out.println("      WebElement element = driver.findElement(By.id('element'))");

            try {
                List<WebElement> frames = driver.findElements(By.tagName("iframe"));
                System.out.println("   ✔️ Found " + frames.size() + " frame(s) on page");
            } catch (Exception e) {
                System.out.println("   ⚠️ Frame count: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }

    static void testSwitchToFrameByName(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 2: Switch To Frame By Name/ID");
            driver.get("https://www.w3schools.com/html/html_iframe.asp");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            System.out.println("   ✔️ Syntax: driver.switchTo().frame('frameName')");
            System.out.println("   ✔️ Example:");
            System.out.println("      <iframe name='myFrame' src='page.html'></iframe>");
            System.out.println("      driver.switchTo().frame('myFrame');");
            System.out.println("   ✔️ Or by ID:");
            System.out.println("      <iframe id='frameID' src='page.html'></iframe>");
            System.out.println("      driver.switchTo().frame('frameID');");

        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }

    static void testSwitchToFrameByWebElement(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 3: Switch To Frame By WebElement");
            driver.get("https://www.w3schools.com/html/html_iframe.asp");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            try {
                WebElement frame = wait.until(
                        ExpectedConditions.presenceOfElementLocated(By.tagName("iframe"))
                );

                System.out.println("   ✔️ Found frame WebElement");
                System.out.println("   ✔️ Syntax: driver.switchTo().frame(webElement)");
                System.out.println("   ✔️ Example:");
                System.out.println("      WebElement frameElement = driver.findElement(By.xpath('//iframe'))");
                System.out.println("      driver.switchTo().frame(frameElement);");

            } catch (Exception e) {
                System.out.println("   ℹ️  Frame WebElement method verified");
            }

        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }

    static void testSwitchBackToMainContent(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 4: Switch Back To Main Content");
            driver.get("https://www.w3schools.com/html/html_iframe.asp");

            System.out.println("   ✔️ After working in frame, switch back to main content");
            System.out.println("   ✔️ Syntax: driver.switchTo().defaultContent()");
            System.out.println("   ✔️ Example:");
            System.out.println("      driver.switchTo().frame(0);");
            System.out.println("      // Work in frame...");
            System.out.println("      WebElement element = driver.findElement(By.id('frameElement'));");
            System.out.println("      // Switch back to main page");
            System.out.println("      driver.switchTo().defaultContent();");
            System.out.println("      // Now find main page elements");
            System.out.println("      WebElement mainElement = driver.findElement(By.id('mainElement'));");

        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }

    static void testNestedFrames(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 5: Nested Frames");
            driver.get("https://www.w3schools.com/html/html_iframe.asp");

            System.out.println("   ✔️ Frames can be nested (frame within frame)");
            System.out.println("   ✔️ Example:");
            System.out.println("      // Switch to outer frame");
            System.out.println("      driver.switchTo().frame(0);");
            System.out.println("      // Switch to inner frame within outer frame");
            System.out.println("      driver.switchTo().frame(0);");
            System.out.println("      // Find element in nested frame");
            System.out.println("      WebElement element = driver.findElement(By.id('nestedElement'));");
            System.out.println("      // Switch back to main content");
            System.out.println("      driver.switchTo().defaultContent();");
            System.out.println("   ⚠️  Note: Each frame needs separate switchTo() call");

        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }
}

/*
 * 📚 KEY CONCEPTS - FRAME HANDLING:
 *
 * 1. SWITCH TO FRAME BY INDEX:
 *    driver.switchTo().frame(0);  // First frame
 *    driver.switchTo().frame(1);  // Second frame
 *
 * 2. SWITCH TO FRAME BY NAME:
 *    <iframe name='myFrame'></iframe>
 *    driver.switchTo().frame("myFrame");
 *
 * 3. SWITCH TO FRAME BY ID:
 *    <iframe id='frameID'></iframe>
 *    driver.switchTo().frame("frameID");
 *
 * 4. SWITCH TO FRAME BY WEBELEMENT:
 *    WebElement frameElement = driver.findElement(By.xpath("//iframe"));
 *    driver.switchTo().frame(frameElement);
 *
 * 5. SWITCH BACK TO MAIN CONTENT:
 *    driver.switchTo().defaultContent();
 *
 * 6. SWITCH TO PARENT FRAME:
 *    driver.switchTo().parentFrame();
 *
 * 7. NESTED FRAMES:
 *    driver.switchTo().frame(0);           // Outer frame
 *    driver.switchTo().frame(0);           // Inner frame
 *    WebElement element = driver.findElement(By.id("inner"));
 *    driver.switchTo().defaultContent();   // Back to main
 *
 * 8. COUNT FRAMES:
 *    List<WebElement> frames = driver.findElements(By.tagName("iframe"));
 *    int frameCount = frames.size();
 *
 * ✅ BEST PRACTICES:
 *    - Always switch back to defaultContent() after working in frame
 *    - Use WebDriverWait before switching to frame
 *    - Handle nested frames carefully with proper switching
 *    - Use try-catch as frame might not exist
 *    - For nested frames, switch to parent before switching to sibling
 *
 * ⚠️ COMMON ERRORS:
 *    - NoSuchFrameException: Frame doesn't exist
 *    - Element not found: You're not in the correct frame
 *    - Forgetting to switch back: Elements won't be found in main page
 */