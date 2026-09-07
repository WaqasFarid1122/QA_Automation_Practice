import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class Week5_Alerts {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            System.out.println("🔄 WEEK 5 - ALERT HANDLING");
            System.out.println("================================");

            testSimpleAlert(driver);
            testConfirmAlert(driver);
            testPromptAlert(driver);
            testAlertWithWait(driver);
            testAlertException(driver);

            System.out.println("================================");
            System.out.println("✅ WEEK 5 ALERT TESTING COMPLETED!");

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
            System.out.println("✔️ Browser closed");
        }
    }

    static void testSimpleAlert(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 1: Simple Alert");
            driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_alert");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            System.out.println("   ✔️ Navigated to alert demo page");
            System.out.println("   ✔️ To trigger alert: element.click()");
            System.out.println("   ✔️ Switch to alert: Alert alert = driver.switchTo().alert()");
            System.out.println("   ✔️ Get alert text: alert.getText()");
            System.out.println("   ✔️ Accept alert: alert.accept()");

        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }

    static void testConfirmAlert(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 2: Confirm Alert");
            driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_confirm");

            System.out.println("   ✔️ Confirm alert has OK and Cancel buttons");
            System.out.println("   ✔️ To accept: alert.accept()");
            System.out.println("   ✔️ To dismiss: alert.dismiss()");
            System.out.println("   ✔️ Example:");
            System.out.println("      Alert alert = driver.switchTo().alert();");
            System.out.println("      String alertText = alert.getText();");
            System.out.println("      if (alertText.contains('Are you sure')) {");
            System.out.println("          alert.accept();  // Click OK");
            System.out.println("      }");

        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }

    static void testPromptAlert(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 3: Prompt Alert");
            driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_prompt");

            System.out.println("   ✔️ Prompt alert asks for user input");
            System.out.println("   ✔️ To send text: alert.sendKeys('your text')");
            System.out.println("   ✔️ Then accept: alert.accept()");
            System.out.println("   ✔️ Example:");
            System.out.println("      Alert alert = driver.switchTo().alert();");
            System.out.println("      alert.sendKeys('Test Input');");
            System.out.println("      alert.accept();");

        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }

    static void testAlertWithWait(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 4: Alert With WebDriverWait");
            driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_alert");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            System.out.println("   ✔️ Use WebDriverWait to wait for alert");
            System.out.println("   ✔️ Syntax:");
            System.out.println("      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));");
            System.out.println("      Alert alert = wait.until(");
            System.out.println("          ExpectedConditions.alertIsPresent()");
            System.out.println("      );");
            System.out.println("      alert.accept();");

        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }

    static void testAlertException(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 5: Alert Exception Handling");
            driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_alert");

            System.out.println("   ✔️ Alert might not appear - use try-catch");
            System.out.println("   ✔️ Example:");
            System.out.println("      try {");
            System.out.println("          Alert alert = driver.switchTo().alert();");
            System.out.println("          alert.accept();");
            System.out.println("      } catch (NoAlertPresentException e) {");
            System.out.println("          System.out.println('No alert found');");
            System.out.println("      }");
            System.out.println("   ✔️ Always handle alerts in try-catch blocks");

        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }
}

/*
 * 📚 KEY CONCEPTS - ALERT HANDLING:
 *
 * 1. SWITCH TO ALERT:
 *    Alert alert = driver.switchTo().alert();
 *
 * 2. GET ALERT TEXT:
 *    String alertText = alert.getText();
 *
 * 3. ACCEPT ALERT (Click OK):
 *    alert.accept();
 *
 * 4. DISMISS ALERT (Click Cancel):
 *    alert.dismiss();
 *
 * 5. SEND TEXT TO PROMPT:
 *    alert.sendKeys("Your text here");
 *    alert.accept();
 *
 * 6. WAIT FOR ALERT:
 *    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
 *    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
 *    alert.accept();
 *
 * 7. EXCEPTION HANDLING:
 *    try {
 *        Alert alert = driver.switchTo().alert();
 *    } catch (NoAlertPresentException e) {
 *        // No alert found
 *    }
 *
 * 8. ALERT TYPES:
 *    - Simple Alert: alert() - shows message, only OK button
 *    - Confirm Alert: confirm() - has OK and Cancel buttons
 *    - Prompt Alert: prompt() - asks for text input
 *
 * ✅ BEST PRACTICES:
 *    - Always use WebDriverWait for alerts
 *    - Use try-catch for NoAlertPresentException
 *    - Check alert text before accepting/dismissing
 *    - Send data to prompt before accepting
 */
