package com.clover.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumUtils {
    private WebDriver driver;
    private WebDriverWait wait;

    public SeleniumUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public List<WebElement> findElements(By locator) {
        waitForElementToBeVisible(locator);
        return driver.findElements(locator);
    }

    public void clickElement(By locator) {
        waitForElementToBeClickable(locator).click();
    }
    public void clickElement(WebElement element) {
        element.click();
    }

    public void sendKeysToElement(By locator, String text) {
        WebElement element = waitForElementToBeVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (Exception e) {

        }
    }

}
