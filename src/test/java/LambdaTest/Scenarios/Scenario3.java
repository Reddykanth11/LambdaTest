package LambdaTest.Scenarios;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;



public class Scenario3 {
@Test
    public void test() throws InterruptedException, MalformedURLException {
    	ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("124.0");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "creddykanth");
		ltOptions.put("accessKey", "6ztvM0OXvX7RsklZKyjNtNwT7b8rHWW6CSxfrfBwX5r5tX7Dhs");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("resolution", "1920x1080");
		ltOptions.put("build", "seleniumbulid");
		ltOptions.put("project", "selenium");
		ltOptions.put("name", "Task1");
		ltOptions.put("tunnel", true);
		ltOptions.put("selenium_version", "4.18.1");
		ltOptions.put("w3c", true);
		ltOptions.put("plugin", "java-java");
		browserOptions.setCapability("LT:Options", ltOptions);
		
		WebDriver driver= new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"), browserOptions);
		driver.get("https://www.lambdatest.com/selenium-playground");
        String baseUrl = "https://www.lambdatest.com/selenium-playground";
        

        for (Browser browser : Browser.values()) {
            driver = createWebDriver(browser);

            try {
                driver.get(baseUrl);

                // Click "Input Form Submit"
                driver.findElement(By.linkText("Input Form Submit")).click();
                    
                 // Fill in form details
                    WebElement nameInput = driver.findElement(By.id("name"));
                    nameInput.sendKeys("Test");

                    WebElement emailInput = driver.findElement(By.id("inputEmail4"));
                    emailInput.sendKeys("test@example.com");
                    
                    WebElement passwordInput = driver.findElement(By.id("inputPassword4"));
                    passwordInput.sendKeys("test@123");

                    WebElement companyInput = driver.findElement(By.id("company"));
                    companyInput.sendKeys("Company");
                    
                    
                    // Select "United States" from Country dropdown
                    WebElement countrySelect = driver.findElement(By.name("country"));
                    Select countryDropdown = new Select(countrySelect);
                    countryDropdown.selectByVisibleText("United States");
                    
                    WebElement websiteInput = driver.findElement(By.id("websitename"));
                    websiteInput.sendKeys("Tpt.com");
                    
                    
                    WebElement cityInput = driver.findElement(By.id("inputCity"));
                    cityInput.sendKeys("Tpt");
                    
                    WebElement Add1Input = driver.findElement(By.id("inputAddress1"));
                    Add1Input.sendKeys("Tpt");
                    
                    WebElement Add2Input = driver.findElement(By.id("inputAddress2"));
                    Add2Input.sendKeys("Tpt");
                    
                    WebElement stateInput = driver.findElement(By.id("inputState"));
                    stateInput.sendKeys("Tpt");
                    
                    WebElement zipInput = driver.findElement(By.id("inputZip"));
                    zipInput.sendKeys("12345");
                    
                    

                    // Fill other fields (add logic for remaining fields as needed)
                    // ...

                    // Click "Submit" again
                    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
               
                // Validate success message
                 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='success-msg hidden']")));
                String successText = successMessage.getText();
                if (successText.contains("Thanks for contacting us")) {
                    System.out.println(successText + browser);
                } else {
                    System.out.println("Error: Expected success message not found for " + browser);
                }
            } finally {
                // Close browser
                driver.quit();
            }
        }
    }

    private static WebDriver createWebDriver(Browser browser) {
    	WebDriver driver =null;
        switch (browser) {
            case CHROME:
            	 driver = new ChromeDriver();
                return new ChromeDriver();
            case FIREFOX:
                 driver =new FirefoxDriver();
                return new FirefoxDriver();
            case EDGE:
                driver =new EdgeDriver();
                return new EdgeDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    enum Browser {
        CHROME,
        FIREFOX,
        EDGE
    }
}
