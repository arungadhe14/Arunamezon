package testnn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class NewTest1 {

    WebDriver driver;
        @Parameters({"browser"})
        @Test
       public void searchOnAmazon(String browser) throws MalformedURLException, InterruptedException {
		URL remoteUrl = new URL("http://localhost:4444/wd/hub"); // Update this if your hub URL is different

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                driver = new RemoteWebDriver(remoteUrl, chromeOptions);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new RemoteWebDriver(remoteUrl, firefoxOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = new RemoteWebDriver(remoteUrl, edgeOptions);
                break;
            default:
                throw new IllegalArgumentException("Browser is not supported");
        }

        driver.get("https://www.amazon.in/");
        Thread.sleep(2000); // Wait for the page to load

        Thread.sleep(2000); // Wait for results to load

        // Verify search results page
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Selenium WebDriver"), "Title does not contain expected text");
        System.out.println("Search successful on " + browser);

        Thread.sleep(2000); // Wait before closing
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
