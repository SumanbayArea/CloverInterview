package com.clover.pages;

import com.clover.utils.ElementLocatorReader;
import com.clover.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class GoogleHomePage extends SeleniumUtils {
    private final String pageName = "GoogleHomePage";


    public GoogleHomePage(WebDriver driver) {
        super(driver);
    }

    public void enterSearchTerm(String searchTerm) {
        By searchBoxLocator = ElementLocatorReader.readLocator(pageName, "searchBox");
        sendKeysToElement(searchBoxLocator, searchTerm);
        waitFor(5);
    }

    public void selectAutoSuggestionWithGivenTest(String searchTerm) {
        By searchBoxLocator = ElementLocatorReader.readLocator(pageName, "autoSuggestionResults");
        List<WebElement> autoSuggestions = findElements(searchBoxLocator);
        boolean isSearchTermFound = false;
        for (WebElement suggestion : autoSuggestions) {
            if (suggestion.getText().equals(searchTerm)) {
                clickElement(suggestion);
                isSearchTermFound = true;
                break;
            }
        }
        if (!isSearchTermFound) {
            Assert.fail("Given Search term " + searchTerm + " is not found in autosuggestions");
        }

    }
}
