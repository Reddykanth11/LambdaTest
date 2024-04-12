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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Scenario1 {


	@Test
	public void test()throws InterruptedException, MalformedURLException {
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
        String message = "Welcome to LambdaTest";

        // Run tests on different browsers
        for (Browser browser : Browser.values()) {
             driver = null;

            switch (browser) {
                case CHROME:
                    driver = new ChromeDriver();
                    break;
                case FIREFOX:
                    driver = new FirefoxDriver();
                    break;
                case EDGE:
                    driver = new EdgeDriver();
                    break;
                
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            if (driver != null) {
                try {
                    driver.get(baseUrl);

                    // Click "Simple Form Demo"
                    driver.findElement(By.linkText("Simple Form Demo")).click();

                    // Validate URL contains "simple-form-demo"
                    if (!driver.getCurrentUrl().contains("simple-form-demo")) {
                        System.out.println("Error: Expected URL to contain 'simple-form-demo' for " + browser);
                    }

                    // Enter message in textbox
                    WebElement messageBox = driver.findElement(By.id("user-message"));
                    messageBox.sendKeys(message);

                    // Click "Get Checked Value" button
                    driver.findElement(By.xpath("(//button[@type='button'])[1]")).click();

                    // Wait for message to be displayed
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));

                    // Validate message is displayed
                    WebElement messageDisplay = driver.findElement(By.id("message"));
                    String displayedMessage = messageDisplay.getText();
                    if (!displayedMessage.equals(message)) {
                        System.out.println("Error: Expected message '" + message + "' not displayed for " + browser);
                    } else {
                        System.out.println(displayedMessage + browser);
                    }
                } finally {
                    // Close browser
                    driver.quit();
                }
            }
        }
    }

    enum Browser {
        CHROME,
        FIREFOX,
        EDGE
        
    }

}

