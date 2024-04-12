
package LambdaTest.Scenarios;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;




public class Scenario2 {


	public void test2()  throws InterruptedException, MalformedURLException {
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
                    driver.findElement(By.linkText("Drag & Drop Sliders")).click();

                 
                    // Select slider "Default value 95"
                    WebElement slider = driver.findElement(By.id("slider3"));
                    WebElement slider1 = driver.findElement(By.xpath("//output[text()='95']"));
                    // Create Actions class instance
                    Actions actions = new Actions(driver);

                    // Drag and drop the slider (target is not needed here)
                    actions.dragAndDrop(slider, slider1).build().perform();

                    // Validate range value (assuming the range element is next to the slider)
                    WebElement rangeValue = slider.findElement(By.xpath("(//input[@class='sp__range'])[3]"));
                    String actualValue = rangeValue.getText();

                    // Check for expected value (adjust the expected value based on slider range)
                    int expectedValue = 95;  // Modify as needed for your slider range
                    if (actualValue.equals(String.valueOf(expectedValue))) {
                        System.out.println("Success: Slider 3 value reached " + expectedValue + "!");
                    } else {
                        System.out.println("Error: Expected slider 3 value to be " + expectedValue + ", but found " + actualValue);
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



