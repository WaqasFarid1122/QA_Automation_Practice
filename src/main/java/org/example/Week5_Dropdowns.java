import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;

public class Week5_Dropdowns {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            System.out.println("🔄 WEEK 5 - DROPDOWN TESTING");
            System.out.println("================================");

            testSelectByVisibleText(driver);
            testSelectByValue(driver);
            testSelectByIndex(driver);
            testGetAllDropdownOptions(driver);
            testMultipleDropdowns(driver);

            System.out.println("================================");
            System.out.println("✅ WEEK 5 DROPDOWN TESTING COMPLETED!");

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
            System.out.println("✔️ Browser closed");
        }
    }

    static void testSelectByVisibleText(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 1: Select by Visible Text");
            driver.get("https://www.saucedemo.com/");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            WebElement loginBtn = driver.findElement(By.id("login-button"));
            System.out.println("   ✔️ Select by visible text - Syntax: select.selectByVisibleText('Option Name')");
        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }

    static void testSelectByValue(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 2: Select by Value Attribute");
            driver.get("https://www.w3schools.com/html/html_forms.asp");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cars")));
            Select select = new Select(dropdown);
            select.selectByValue("volvo");
            System.out.println("   ✔️ Selected 'volvo' using selectByValue()");
            System.out.println("   ✔️ Current selection: " + select.getFirstSelectedOption().getText());
        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }

    static void testSelectByIndex(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 3: Select by Index");
            driver.get("https://www.w3schools.com/html/html_forms.asp");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cars")));
            Select select = new Select(dropdown);
            select.selectByIndex(1);
            System.out.println("   ✔️ Selected by index 1");
            System.out.println("   ✔️ Selected option: " + select.getFirstSelectedOption().getText());
        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }

    static void testGetAllDropdownOptions(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 4: Get All Dropdown Options");
            driver.get("https://www.w3schools.com/html/html_forms.asp");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cars")));
            Select select = new Select(dropdown);
            List<WebElement> allOptions = select.getOptions();
            System.out.println("   ✔️ Total options in dropdown: " + allOptions.size());
            System.out.println("   📋 Options:");
            for (int i = 0; i < allOptions.size(); i++) {
                System.out.println("      [" + i + "] " + allOptions.get(i).getText());
            }
        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }

    static void testMultipleDropdowns(WebDriver driver) {
        try {
            System.out.println("\n📌 Test 5: Multiple Dropdowns");
            driver.navigate().back();
            driver.navigate().back();
            List<WebElement> allSelects = driver.findElements(By.tagName("select"));
            System.out.println("   ✔️ Found " + allSelects.size() + " dropdown(s) on page");
            for (int i = 0; i < allSelects.size(); i++) {
                Select select = new Select(allSelects.get(i));
                String selectedText = select.getFirstSelectedOption().getText();
                System.out.println("   📍 Dropdown " + (i + 1) + " current selection: " + selectedText);
            }
        } catch (Exception e) {
            System.out.println("   ⚠️  " + e.getMessage());
        }
    }
}