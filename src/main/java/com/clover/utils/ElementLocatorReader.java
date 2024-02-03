package com.clover.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class ElementLocatorReader {
    private static JsonObject jsonObject;

    static {
        try {
            jsonObject = (JsonObject) JsonParser.parseReader(new FileReader("src/main/resources/locators.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static By readLocator(String pageName, String elementName) {
        if (jsonObject != null) {
            JsonObject page = jsonObject.getAsJsonObject(pageName);
            if (page != null) {
                JsonObject element = page.getAsJsonObject(elementName);
                if (element != null) {
                    String locatorType = element.get("LocatorType").getAsString().toLowerCase();
                    String locatorValue = element.get("LocatorValue").getAsString();

                    switch (locatorType) {
                        case "id":
                            return By.id(locatorValue);
                        case "name":
                            return By.name(locatorValue);
                        case "xpath":
                            return By.xpath(locatorValue);
                        case "css":
                        case "cssselector":
                            return By.cssSelector(locatorValue);
                        case "linktext":
                            return By.linkText(locatorValue);
                        case "partiallinktext":
                            return By.partialLinkText(locatorValue);
                        case "classname":
                        case "class":
                            return By.className(locatorValue);
                        default:
                            throw new IllegalArgumentException("Unsupported locator type: " + locatorType);
                    }
                }
            }
        }

        throw new IllegalArgumentException("Element locator not found for Page: " + pageName + ", Element: " + elementName);
    }
}
