package com.clover.pages;

import com.clover.utils.ElementLocatorReader;
import com.clover.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleSearchResultsPage extends SeleniumUtils {
    private final String pageName = "GoogleSearchResultsPage";


    public GoogleSearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getFirstResultLink() {
        By resultsLinkLocator = ElementLocatorReader.readLocator(pageName, "resultsLink");
        return waitForElementToBeVisible(resultsLinkLocator);
    }
}
