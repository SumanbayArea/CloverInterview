package com.clover.base;

import com.clover.utils.EnvironmentReader;
import com.clover.utils.TestDataReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {
    protected static WebDriver driver;

    @BeforeSuite
    @Parameters({"environment", "browser"})
    public void beforeSuite(String environment, String browser) {
        EnvironmentReader.loadEnvironmentProperties(environment);
        TestDataReader.loadTestDataProperties(environment);
        initializeDriver(browser);
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void initializeDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-popup-blocking");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid browser " + browser + " specified in testng.xml");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public void loadApplication() {
        driver.get(EnvironmentReader.getProperty("app.url"));
    }
}
