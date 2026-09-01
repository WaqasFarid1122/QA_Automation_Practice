package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class FasoRideTestFixed {
    static WebDriver driver;
    static String screenshotPath = "FasoRide_Test_Screenshots";
    static String reportPath = "FasoRide_Test_Report.html";
    static int passed = 0, failed = 0;
    static StringBuilder reportHTML = new StringBuilder();

    // Menu items ke hrefs ka mapping
    static Map<String, String> featureUrls = new HashMap<>();

    static {
        featureUrls.put("Dashboard", "/dashboard");
        featureUrls.put("Users Management", "/user-management");
        featureUrls.put("Drivers Management", "/driver-management");
        featureUrls.put("Driver Requests", "/driver-requests");
        featureUrls.put("Rides Management", "/rides-management");
        featureUrls.put("Ads", "/ads");
        featureUrls.put("Safety checkup", "/safetycheckup");
        featureUrls.put("Store management", "/store-management");
        featureUrls.put("Store Requests", "/store-request");
        featureUrls.put("Order Management", "/order-management");
        featureUrls.put("Category", "/category");
        featureUrls.put("Membership", "/membership-management");
    }

    public static void main(String[] args) throws Exception {
        // Purana folder delete karna
        System.out.println("🧹 Cleaning old files...");
        deleteFolder(new File(screenshotPath));
        new File(reportPath).delete();

        // Naya folder create karna
        new File(screenshotPath).mkdirs();

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   🚀 FASORIDE TEST WITH SCREENSHOTS    ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        try {
            // LOGIN
            System.out.println("📍 LOGIN TEST\n");
            driver.get("https://faso-ride.jeuxtesting.com/");
            takeScreenshot("01_Login_Page");
            System.out.println("[1/5] Login page ✅ Screenshot: 01_Login_Page");

            Thread.sleep(2000);
            driver.findElement(By.cssSelector("input[type='email']")).sendKeys("admin@fasoride.com");
            takeScreenshot("02_Email_Entered");
            System.out.println("[2/5] Email entered ✅ Screenshot: 02_Email_Entered");

            Thread.sleep(1000);
            driver.findElement(By.cssSelector("input[type='password']")).sendKeys("Admin@123456");
            takeScreenshot("03_Password_Entered");
            System.out.println("[3/5] Password entered ✅ Screenshot: 03_Password_Entered");

            Thread.sleep(1000);
            driver.findElement(By.xpath("//button[contains(text(), 'Login')]")).click();
            takeScreenshot("04_Login_Clicked");
            System.out.println("[4/5] Login clicked ✅ Screenshot: 04_Login_Clicked");

            Thread.sleep(3000);
            takeScreenshot("05_Dashboard");
            System.out.println("[5/5] Dashboard loaded ✅ Screenshot: 05_Dashboard");

            passed++;
            addResult("LOGIN", true, "Login successful");

            // FEATURES TEST
            System.out.println("\n📍 FEATURE TESTS\n");

            testFeature("Dashboard", 6);
            testFeature("Users Management", 7);
            testFeature("Drivers Management", 8);
            testFeature("Driver Requests", 9);
            testFeature("Rides Management", 10);
            testFeature("Ads", 11);
            testFeature("Safety checkup", 12);
            testFeature("Store management", 13);
            testFeature("Store Requests", 14);
            testFeature("Order Management", 15);
            testFeature("Category", 16);
            testFeature("Membership", 17);

            // REPORT
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║         📊 TEST SUMMARY                ║");
            System.out.println("╚════════════════════════════════════════╝\n");
            System.out.println("✅ Passed: " + passed);
            System.out.println("❌ Failed: " + failed);
            System.out.println("📊 Total:  " + (passed + failed));
            System.out.println("📈 Rate:   " + (100 * passed / (passed + failed)) + "%\n");

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

    // Feature test karna
    static void testFeature(String name, int num) throws Exception {
        try {
            // Screenshot before
            String beforeName = String.format("%02d_%s_Before", num, name.replace(" ", "_"));
            takeScreenshot(beforeName);

            // Feature URL se navigate karna
            String href = featureUrls.get(name);
            if (href != null) {
                driver.navigate().to("https://faso-ride.jeuxtesting.com" + href);
                Thread.sleep(2000);
            } else {
                throw new Exception("Feature URL not found for: " + name);
            }

            // Screenshot after
            String afterName = String.format("%02d_%s_After", num, name.replace(" ", "_"));
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
        String html = "<html><head><title>FasoRide Report</title><style>" +
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
                "<h1>🚀 FasoRide Test Report</h1>" +
                "<div class='stats'>" +
                "<div class='stat'><div class='stat-num'>" + (passed + failed) + "</div><div>Total</div></div>" +
                "<div class='stat pass'><div class='stat-num'>" + passed + "</div><div>Passed</div></div>" +
                "<div class='stat fail'><div class='stat-num'>" + failed + "</div><div>Failed</div></div>" +
                "<div class='stat'><div class='stat-num'>" + (100 * passed / (passed + failed)) + "%</div><div>Success</div></div>" +
                "</div>" +
                "<h2>📋 Results:</h2>" +
                reportHTML.toString() +
                "<hr><p style='text-align: center; color: #999;'>Report generated for FasoRide Testing</p>" +
                "</div></body></html>";

        FileWriter writer = new FileWriter(reportPath);
        writer.write(html);
        writer.close();
    }
}