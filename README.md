# Clover Interview Project

This is the Clover Interview project for Suman.

## Overview

The project is a Java-based automation project using TestNG, Maven, and Selenium. It includes test scenario for Create a test framework using any language and tool(s) that does the following:
1. Visit one or more search engine(s); i.e google, bing, yahoo, etc...
2. Submit a search term
3. On the results page, take the first returned item and assert it as the expected result.


## Prerequisites

Make sure you have the following software installed on your machine:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- WebDriver (e.g., [ChromeDriver](https://sites.google.com/chromium.org/driver/))

```bash
# Example for Java and Maven installation on Ubuntu
sudo apt-get install default-jdk
sudo apt-get install maven
```


## Clone the Repository
```bash
# Copy code
git clone https://github.com/SumanbayArea/CloverInterview.git
cd CloverInterview
```
## Run the Tests
```bash
mvn clean test
```
This will execute the TestNG tests using Maven.

## Additional Configuration
Modify the src/test/resources/testng.xml file for any specific configurations or parameters needed for your tests.

## Project Structure

__src/main/java__: Contains the main Java source code.

__src/test/java__: Contains the TestNG test classes.

__src/test/resources__: Contains the TestNG XML configuration file (testng.xml).

__TestNG.xml Configuration__

_The testng.xml file is configured to run specific test suites, classes, or methods. Modify it according to your test requirements_
