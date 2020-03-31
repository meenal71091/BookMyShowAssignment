package com.cross.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.asserts.IAssertLifecycle;

import com.cross.qa.util.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class TestBase {

	public static AndroidDriver androidDriver;
	public static Properties prop;
	public static WebDriverWait explicitWait;
	public static ExtentReports extent;
	public static ExtentTest logger;

	@BeforeSuite
	public void setUpSuite() {
		TestUtil.readConfig();
		extent = new ExtentReports("Reports\\MyReport.html");
	}

	public static void initialization() {
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("deviceName", prop.getProperty("deviceName"));
			capabilities.setCapability("app", new File(prop.getProperty("apkPath")).getAbsolutePath());
			capabilities.setCapability("appWaitActivity","com.movie.bms.languageselection.views.activities.LanguageSelectionActivity");
			capabilities.setCapability("appWaitDuration", 50000);
			capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
			capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
			capabilities.setCapability("platformName", prop.getProperty("platformName"));
			capabilities.setCapability("automationName", "UiAutomator1");
			capabilities.setCapability("–session-override", true);
			capabilities.setCapability("clearSystemFiles", true);
			androidDriver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
			androidDriver.manage().timeouts().implicitlyWait(Long.parseLong(prop.getProperty("IMPLICIT_WAIT")),
					TimeUnit.SECONDS);
			logger.log(LogStatus.INFO, "App launched successfully",logger.addBase64ScreenShot(TestUtil.takeScreenshot()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
