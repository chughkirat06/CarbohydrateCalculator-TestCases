/* Verify if the result is displayed after inputting all the fields 
and clicking on the calculate button */

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CarbohydrateCalculatorTest {
    public static void main(String[] args) {

        // Initialize the ChromeDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Maximize the browser window
            driver.manage().window().maximize();

            // Navigate to the Carbohydrate Calculator website
            driver.get("https://www.calculator.net/carbohydrate-calculator.html");

            // Input age
            WebElement ageInput = driver.findElement(By.id("cage"));
            ageInput.clear();
            ageInput.sendKeys("25");

            // Select gender
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", driver.findElement(By.id("csex2")));

            // Input height in cm
            WebElement heightInput = driver.findElement(By.id("cheightmeter"));
            heightInput.clear();
            heightInput.sendKeys("180");

            // Input weight in kg
            WebElement weightInput = driver.findElement(By.id("ckg"));
            weightInput.clear();
            weightInput.sendKeys("75");

            // Select activity level
            Select activityLevelSelect = new Select(driver.findElement(By.id("cactivity")));
            activityLevelSelect.selectByVisibleText("Light: exercise 1-3 times/week");

            // Click the "Calculate" button
            WebElement calculateButton = driver.findElement(By.xpath("//input[@value='Calculate']"));
            calculateButton.click();

            // Verify the carbohydrate calculation result is calculated and displayed
            WebElement resultElement = driver.findElement(By.xpath("//*[@id=\"content\"]/h2"));
            String resultText = resultElement.getText();
            if (resultText.startsWith("R")) {
                System.out.println("Test Passed");
            } else {
                throw new AssertionError("Carbohydrate calculation result is not displayed correctly.");
            }

        } catch (Exception e) {
            System.out.println("Test Failed: " + e.getMessage());
        } finally {
            // Close the browser
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
