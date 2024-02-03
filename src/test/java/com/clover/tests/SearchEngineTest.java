package com.clover.tests;

import com.clover.base.BaseTest;
import com.clover.pages.GoogleHomePage;
import com.clover.pages.GoogleSearchResultsPage;
import com.clover.utils.TestDataReader;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchEngineTest extends BaseTest {
    GoogleSearchResultsPage resultsPage;
    GoogleHomePage homePage;
    @BeforeClass
    public void beforeClass() {
        homePage = new GoogleHomePage(driver);
        resultsPage = new GoogleSearchResultsPage(driver);
    }
    @Test
    public void testSearchResults() {
        String searchTerm = TestDataReader.getProperty("searchTerm");
        String expectedUrl = TestDataReader.getProperty("expectedURL");
        loadApplication();
        homePage.enterSearchTerm(searchTerm);
        homePage.selectAutoSuggestionWithGivenTest(searchTerm);
        WebElement firstResultLink = resultsPage.getFirstResultLink();
        String actualResultUrl = firstResultLink.getAttribute("href");
        Assert.assertEquals(actualResultUrl, expectedUrl, "First result URL doesn't match the expected URL");
    }
}
