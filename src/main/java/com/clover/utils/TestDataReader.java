package com.clover.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class TestDataReader {
    private static Properties properties;

    public static void loadTestDataProperties(String environment) {
        properties = new Properties();
        String environmentConfigPath = "src/main/resources/testdata/" + environment.toUpperCase() + "/testdata.properties";

        try (InputStream input = Files.newInputStream(Paths.get(environmentConfigPath))) {
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load environment properties", ex);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
