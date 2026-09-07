import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.NoSuchElementException;
import java.time.Duration;

public class Week5_AdvancedWaits {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            System.out.println("🔄 WEEK 5 - ADVANCED WAITS");
            System.out.println("================================");

            testImplicitWait(driver);
            testExplicitWait(driver);
            testExpectedConditions(driver);
            testFluentWait(driver);
            testWaitBestPractices(driver);

            System.out.println("================================");
            System.out.println("✅ WEEK 5 ADVANCED WAITS TESTING COMPLETED!");

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
            System.out.println("✔️ Browser closed");
        }
    }

    static void testImplicitWait(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 1: Implicit Wait");
            driver.get("https://www.saucedemo.com/");

            System.out.println("   ✔️ Implicit wait waits for ALL elements");
            System.out.println("   ✔️ Applied globally to every element search");
            System.out.println("   ✔️ Syntax: driver.manage().timeouts().implicitlyWait()");
            System.out.println("   ✔️ Example:");
            System.out.println("      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));");
            System.out.println("      // Now every findElement() waits up to 10 seconds");
            System.out.println("      WebElement element = driver.findElement(By.id('element'));");
            System.out.println("   ⚠️  Use implicit wait with caution - applies to all searches");

        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }

    static void testExplicitWait(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 2: Explicit Wait (WebDriverWait)");
            driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_timing_settimeout");

            System.out.println("   ✔️ Explicit wait waits for SPECIFIC condition");
            System.out.println("   ✔️ More flexible and reliable than implicit");
            System.out.println("   ✔️ Syntax:");
            System.out.println("      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));");
            System.out.println("      WebElement element = wait.until(");
            System.out.println("          ExpectedConditions.presenceOfElementLocated(By.id('element'))");
            System.out.println("      );");
            System.out.println("   ✔️ Best practice: Use explicit wait for critical elements");

        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }

    static void testExpectedConditions(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 3: ExpectedConditions");
            driver.get("https://www.saucedemo.com/");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            System.out.println("   ✔️ Common ExpectedConditions:");
            System.out.println("      1. presenceOfElementLocated() - Element exists in DOM");
            System.out.println("         wait.until(ExpectedConditions.presenceOfElementLocated(By.id('id')));");
            System.out.println("      2. visibilityOfElementLocated() - Element is visible");
            System.out.println("         wait.until(ExpectedConditions.visibilityOfElementLocated(By.id('id')));");
            System.out.println("      3. elementToBeClickable() - Element is clickable");
            System.out.println("         wait.until(ExpectedConditions.elementToBeClickable(By.id('id')));");
            System.out.println("      4. textToBePresentInElement() - Text appears in element");
            System.out.println("         wait.until(ExpectedConditions.textToBePresentInElement(element, 'Text'));");
            System.out.println("      5. invisibilityOfElement() - Element becomes invisible");
            System.out.println("         wait.until(ExpectedConditions.invisibilityOfElement(element));");
            System.out.println("      6. alertIsPresent() - Alert appears");
            System.out.println("         wait.until(ExpectedConditions.alertIsPresent());");

        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }

    static void testFluentWait(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 4: Fluent Wait");
            driver.get("https://www.saucedemo.com/");

            System.out.println("   ✔️ Fluent wait checks condition repeatedly");
            System.out.println("   ✔️ More customizable than WebDriverWait");
            System.out.println("   ✔️ Syntax:");
            System.out.println("      FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)");
            System.out.println("          .withTimeout(Duration.ofSeconds(10))");
            System.out.println("          .pollingEvery(Duration.ofMillis(500))");
            System.out.println("          .ignoring(NoSuchElementException.class);");
            System.out.println("      ");
            System.out.println("      WebElement element = fluentWait.until(driver -> ");
            System.out.println("          driver.findElement(By.id('element'))");
            System.out.println("      );");
            System.out.println("   ⚠️  Polling: How often to check (default 500ms)");
            System.out.println("   ⚠️  Ignoring: Exceptions to ignore during wait");

        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }

    static void testWaitBestPractices(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 5: Wait Best Practices");
            driver.get("https://www.saucedemo.com/");

            System.out.println("   ✔️ BEST PRACTICES:");
            System.out.println("      1. Use explicit waits > implicit waits");
            System.out.println("      2. Wait for visibility, not just presence");
            System.out.println("      3. Use appropriate ExpectedCondition");
            System.out.println("      4. Don't mix implicit and explicit waits");
            System.out.println("      5. Set realistic timeouts (usually 10-30 seconds)");
            System.out.println("   ");
            System.out.println("   ✔️ PATTERN - Wait for element to be clickable:");
            System.out.println("      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));");
            System.out.println("      WebElement button = wait.until(");
            System.out.println("          ExpectedConditions.elementToBeClickable(By.id('button'))");
            System.out.println("      );");
            System.out.println("      button.click();");
            System.out.println("   ");
            System.out.println("   ✔️ PATTERN - Wait for text to appear:");
            System.out.println("      WebElement message = driver.findElement(By.id('message'));");
            System.out.println("      wait.until(");
            System.out.println("          ExpectedConditions.textToBePresentInElement(message, 'Success')");
            System.out.println("      );");
            System.out.println("   ✔️ Always use try-catch for timeout exceptions");

        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }
}

/*
 * 📚 KEY CONCEPTS - ADVANCED WAITS:
 *
 * 1. IMPLICIT WAIT (Global):
 *    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
 *    - Applies to all element searches
 *    - Default: None
 *
 * 2. EXPLICIT WAIT (Specific):
 *    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
 *    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id")));
 *
 * 3. IMPORTANT EXPECTEDCONDITIONS:
 *    - presenceOfElementLocated(By locator) - Element in DOM
 *    - visibilityOfElementLocated(By locator) - Element visible
 *    - elementToBeClickable(By locator) - Element clickable
 *    - textToBePresentInElement(WebElement, String text)
 *    - invisibilityOfElement(WebElement)
 *    - alertIsPresent()
 *    - numberOfElementsToBe(By locator, int count)
 *
 * 4. FLUENT WAIT:
 *    new FluentWait<>(driver)
 *        .withTimeout(Duration.ofSeconds(10))
 *        .pollingEvery(Duration.ofMillis(500))
 *        .ignoring(NoSuchElementException.class)
 *        .until(driver -> driver.findElement(By.id("id")));
 *
 * 5. TIMEOUT EXCEPTION:
 *    TimeoutException: Element not found within timeout
 *    - Wrap in try-catch to handle
 *
 * ✅ BEST PRACTICES:
 *    - Explicit > Implicit > No wait
 *    - Wait for visibility/clickability, not just presence
 *    - Use appropriate ExpectedCondition
 *    - Don't mix implicit and explicit waits
 *    - Set reasonable timeouts (10-30 seconds)
 *    - Handle TimeoutException gracefully
 *
 * ⚠️ COMMON ERRORS:
 *    - TimeoutException: Element not found within timeout
 *    - Mixing implicit and explicit: Can cause unexpected behavior
 *    - Wrong condition: Element present but not visible/clickable
 *    - Too short timeout: Element needs more time to load
 *    - No wait at all: Element not ready when accessed
 */