package com.cross.qa.testcases;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;
import com.cross.qa.base.TestBase;
import com.cross.qa.pages.FilterPage;
import com.cross.qa.pages.HomePage;
import com.cross.qa.pages.LanguageSelectionPage;
import com.cross.qa.pages.LoginPage;
import com.cross.qa.pages.SportsPage;
import com.cross.qa.pages.SynopsisPage;
import com.cross.qa.util.TestUtil;
import com.google.common.base.CharMatcher;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class BookMovieTestLatestTrue extends TestBase {

	TestUtil testUtil;
	LoginPage loginPage;
	LanguageSelectionPage languageSelectionPage;
	HomePage homePage;
	SportsPage sportsPage;
	FilterPage filterPage;
	SynopsisPage synopsisPage;

	public BookMovieTestLatestTrue() {
		super();
	}

	@BeforeMethod
	public void setUp(Method method) {
		logger = extent.startTest(method.getName());
		logger.assignCategory("Book Show Journey");
		initialization();
		testUtil = new TestUtil();
		languageSelectionPage = new LanguageSelectionPage();
		loginPage = new LoginPage();
		homePage = new HomePage();
		sportsPage = new SportsPage();
		filterPage = new FilterPage();
		synopsisPage = new SynopsisPage();
	}

	@Test(enabled = true)
	@Parameters("ticketsNeeded")
	public void runBookMyShowInActualDeviceWhileDeviceConnectedThroughUSB(int ticketsNeeded) {
		try {
			// Select English language
			languageSelectionPage.selectEnglishLanguage();

			// Click on 'Let's Get Started Button
			languageSelectionPage.clickOnletsGetsStartedButton();

			// Skip Login
			loginPage.skipLoginLink();

			// Deny location selection
			final WebElement denyButton = androidDriver
					.findElement(By.id("com.android.packageinstaller:id/permission_deny_button"));
			denyButton.click();

			// Click on Mumbai and select All subregion
			homePage.selectMumbaiCity();
			homePage.selectSubRegionAsAll();

			// In home page , scroll down to Sports section
			homePage.scrollTillSportsSection();
			System.out.println("Scrolled till Sports");

			// click View All option of Sports section
			homePage.clickSportsViewAllLink();
			System.out.println("View All clicked");

			// Click on Filter icon
			sportsPage.clickFilterIcon();
			System.out.println("Filter icon clicked");

			// Select Price range above of 0-500 and click the Apply button
			filterPage.selectprice500To2000Range();
			filterPage.applyFilter();

			// Select any card from the listing page
			String sportNameOnListingPage = sportsPage.getFirstSportName();
			String priceOnListingPage = sportsPage.getFirstSportPrice();

			sportsPage.showFirstSportDetails();

			/*
			 * Validate the following :
			 * 
			 * i. sports name in the listing page and synopsis page should be the same ii.
			 * Price displayed in listing and synopsis page should be same
			 */
			String sportNameOnSysnopsisPage = synopsisPage.getSportNameOnSynopsisPage();
			System.out.println("sportNameOnSysnopsisPage" + sportNameOnSysnopsisPage);
			Assert.assertEquals(sportNameOnSysnopsisPage, sportNameOnListingPage,
					"Sport name on listing and sysnopsis page does not match");
			logger.log(LogStatus.PASS, "Sport name on listing and sysnopsis page are same");

			String priceOnSysnopsisPage = synopsisPage.getSportPriceOnSynopsisPage();
			Assert.assertEquals(priceOnSysnopsisPage.replaceAll(" ", ""),
					priceOnListingPage.substring(0, priceOnListingPage.indexOf(" onwards")).replaceAll(" ", ""),
					"Sport Price on listing and sysnopsis page does not match");
			logger.log(LogStatus.PASS, "Sport Price on listing and sysnopsis page are same",
					logger.addBase64ScreenShot(TestUtil.takeScreenshot()));

			System.out.println("PASS--Sport name and prices on sysnopsis page matches with that on listing page");

			// Click Register button
			synopsisPage.clickOnregisterButton();

			// Verify that No price is displaying untill event a single ticket/seat is added
			Assert.assertTrue(synopsisPage.priceLabelList.size() == 0,
					"Ticket is already added on page load without adding the ticket");
			logger.log(LogStatus.PASS, "By Default there is no ticket added",
					logger.addBase64ScreenShot(TestUtil.takeScreenshot()));

			validatePriceAndTicketsCountInFooterWithRespectToTicketsAddition(ticketsNeeded, priceOnSysnopsisPage);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void validatePriceAndTicketsCountInFooterWithRespectToTicketsAddition(int ticketsNeeded,
			String priceOnSysnopsisPage) {
		try {
			for (int i = 1; i <= ticketsNeeded; i++) {
				// Click on 'Add' button
				if (i == 1)
					synopsisPage.addFirstTicket();
				else
					synopsisPage.increseTicketQuantity();

				// Validate Total Price for selected seats/tickets
				String totalPriceLabel = synopsisPage.getSportPriceOnTicketsSelctionPage();
				totalPriceLabel = totalPriceLabel.substring(totalPriceLabel.indexOf(" ") + 1);
				totalPriceLabel = totalPriceLabel.replaceAll(",", "");
				float totalPrice = Float.valueOf(totalPriceLabel);
				float expectedPrice = Float
						.valueOf(priceOnSysnopsisPage.substring(priceOnSysnopsisPage.indexOf(" ") + 1));
				Assert.assertEquals(totalPrice, expectedPrice * i,
						"Ticket Price is showing wrong on Seats/tickets selection page");
				logger.log(LogStatus.PASS,
						"Ticket Price is showing correctly on Seats/tickets selection page for Ticket Count:" + i,
						logger.addBase64ScreenShot(TestUtil.takeScreenshot()));

				// Validate Tickets for selected with respect to tickets addition
				String totalTicketsLabel = synopsisPage.getTicketCount();
				totalTicketsLabel = totalTicketsLabel.substring(0, totalTicketsLabel.indexOf("Ticket"));
				totalTicketsLabel = totalTicketsLabel.replaceAll("\u00A0", "");
				int actualTicketsCount = Integer.valueOf(totalTicketsLabel);
				Assert.assertEquals(actualTicketsCount, i,
						"Ticket Count is showing wrong on Seats/tickets selection page");
				logger.log(LogStatus.PASS,
						"Ticket Count is showing correctly on Seats/tickets selection page for Ticket Count:" + i,
						logger.addBase64ScreenShot(TestUtil.takeScreenshot()));

			}
			logger.log(LogStatus.PASS,
					"Ticket Price and Ticket Count is increasing correctly with respect to Seats/tickets selection",
					logger.addBase64ScreenShot(TestUtil.takeScreenshot()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void tearDown() {
		extent.endTest(logger);
		extent.flush();
		androidDriver.quit();
	}

}
