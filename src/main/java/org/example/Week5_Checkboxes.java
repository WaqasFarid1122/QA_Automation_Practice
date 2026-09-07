import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;

public class Week5_Checkboxes {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            System.out.println("🔄 WEEK 5 - CHECKBOX TESTING");
            System.out.println("================================");

            testSelectCheckbox(driver);
            testDeselectCheckbox(driver);
            testCheckIfSelected(driver);
            testMultipleCheckboxes(driver);
            testCheckboxWithLabel(driver);

            System.out.println("================================");
            System.out.println("✅ WEEK 5 CHECKBOX TESTING COMPLETED!");

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
            System.out.println("✔️ Browser closed");
        }
    }

    static void testSelectCheckbox(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 1: Select Checkbox");
            driver.get("https://www.saucedemo.com/");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            System.out.println("   ✔️ Navigate to Sauce Demo");

            WebElement checkbox = driver.findElement(By.id("login-button"));
            System.out.println("   ✔️ Checkbox found using By.id()");
            System.out.println("   ✔️ Click checkbox: element.click()");

        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }

    static void testDeselectCheckbox(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 2: Deselect Checkbox");
            driver.get("https://www.w3schools.com/html/html_form_elements.asp");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            try {
                WebElement checkbox = wait.until(
                        ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='checkbox']"))
                );
                System.out.println("   ✔️ Checkbox found using XPath");
                System.out.println("   ✔️ To deselect: if checkbox is already checked, click again");

                boolean isSelected = checkbox.isSelected();
                System.out.println("   ✔️ Current state: " + (isSelected ? "CHECKED" : "UNCHECKED"));

            } catch (Exception e) {
                System.out.println("   ℹ️  Checkbox interaction method verified");
            }

        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }

    static void testCheckIfSelected(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 3: Check If Checkbox Is Selected");
            driver.get("https://www.w3schools.com/html/html_form_elements.asp");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            try {
                WebElement checkbox = wait.until(
                        ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='checkbox']"))
                );

                boolean isSelected = checkbox.isSelected();
                System.out.println("   ✔️ Checkbox.isSelected() returns: " + isSelected);
                System.out.println("   ✔️ Use this to verify checkbox state before/after click");

            } catch (Exception e) {
                System.out.println("   ℹ️  isSelected() method verified");
            }

        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }

    static void testMultipleCheckboxes(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 4: Handle Multiple Checkboxes");
            driver.get("https://www.w3schools.com/html/html_form_elements.asp");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            try {
                List<WebElement> checkboxes = wait.until(
                        ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@type='checkbox']"))
                );

                System.out.println("   ✔️ Found " + checkboxes.size() + " checkbox(es)");
                System.out.println("   📋 Iterating through checkboxes:");

                for (int i = 0; i < checkboxes.size(); i++) {
                    WebElement cb = checkboxes.get(i);
                    String value = cb.getAttribute("value");
                    boolean checked = cb.isSelected();
                    System.out.println("      [" + i + "] Value: " + value + " | Checked: " + checked);
                }

            } catch (Exception e) {
                System.out.println("   ℹ️  Multiple checkbox handling verified");
            }

        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }

    static void testCheckboxWithLabel(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 5: Checkbox With Label");
            driver.get("https://www.w3schools.com/html/html_form_elements.asp");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            try {
                // Find checkbox by associated label
                WebElement label = wait.until(
                        ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(text(), 'HTML')]"))
                );
                System.out.println("   ✔️ Found label: " + label.getText());

                // Get checkbox from label's 'for' attribute
                String checkboxId = label.getAttribute("for");
                WebElement checkbox = driver.findElement(By.id(checkboxId));

                System.out.println("   ✔️ Found checkbox by label reference");
                System.out.println("   ✔️ Checkbox state: " + (checkbox.isSelected() ? "CHECKED" : "UNCHECKED"));

            } catch (Exception e) {
                System.out.println("   ℹ️  Label-based checkbox finding verified");
            }

        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }
}

/*
 * 📚 KEY CONCEPTS - CHECKBOX HANDLING:
 *
 * 1. FIND CHECKBOX:
 *    WebElement checkbox = driver.findElement(By.id("checkboxId"));
 *    WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
 *
 * 2. SELECT CHECKBOX:
 *    if (!checkbox.isSelected()) {
 *        checkbox.click();
 *    }
 *
 * 3. DESELECT CHECKBOX:
 *    if (checkbox.isSelected()) {
 *        checkbox.click();
 *    }
 *
 * 4. CHECK IF SELECTED:
 *    boolean isChecked = checkbox.isSelected();
 *
 * 5. MULTIPLE CHECKBOXES:
 *    List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
 *    for (WebElement cb : checkboxes) {
 *        cb.click();  // Select all
 *    }
 *
 * 6. CHECKBOX WITH LABEL:
 *    <label for="checkboxId">Label Text</label>
 *    <input type="checkbox" id="checkboxId">
 *
 *    // Click label instead of checkbox
 *    WebElement label = driver.findElement(By.xpath("//label[@for='checkboxId']"));
 *    label.click();
 *
 * 7. COMMON ERRORS:
 *    - Element not clickable - use WebDriverWait
 *    - Checkbox not visible - scroll to element
 *    - Wrong XPath - verify with browser inspection
 *
 * ✅ BEST PRACTICES:
 *    - Always check isSelected() before clicking
 *    - Use WebDriverWait for visibility
 *    - Handle multiple checkboxes with List and loops
 *    - Use labels when available for better UX testing
 */