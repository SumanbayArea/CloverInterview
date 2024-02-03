package com.clover.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.clover.base.BaseTest;
import com.clover.utils.ExtentManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class ExtentTestListener implements ITestListener {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        // Initialize Extent Reports
        extent = ExtentManager.getInstance();
    }

    @Override
    public void onFinish(ITestContext context) {
        // Flush Extent Reports
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Create a new Extent Test for each test method
        test.set(extent.createTest(result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log test as passed in Extent Reports
        test.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log test as failed in Extent Reports
        test.get().log(Status.FAIL, "Test failed");
        test.get().log(Status.FAIL, result.getThrowable());

        // Capture screenshot and embed in report
        WebDriver driver = BaseTest.getDriver();
        String screenshotBase64 = captureScreenshotBase64(driver);
        try {
            test.get().log(Status.FAIL, "Screenshot here: ", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Log test as skipped in Extent Reports
        test.get().log(Status.SKIP, "Test skipped");
    }

    private String captureScreenshotBase64(WebDriver driver) {
        try {
            if (driver instanceof TakesScreenshot) {
                TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
                return takesScreenshot.getScreenshotAs(OutputType.BASE64);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
