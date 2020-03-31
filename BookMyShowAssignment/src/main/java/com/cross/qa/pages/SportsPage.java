package com.cross.qa.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.cross.qa.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class SportsPage extends TestBase {
	// Initializing the Page Objects:
	public SportsPage() {
		PageFactory.initElements(androidDriver, this);
	}

	@FindBy(xpath = "//android.widget.ImageButton[@resource-id='com.bt.bms:id/fabFilter']")
	WebElement filterIcon;

	public void clickFilterIcon() {
		filterIcon.click();
		logger.log(LogStatus.INFO, "Clicked on filterIcon");
	}

	@FindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@resource-id='com.bt.bms:id/rvList']/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView[@resource-id='com.bt.bms:id/tvTitle']")
	WebElement firstSportName;

	public String getFirstSportName() {
		String getFirstSportName = firstSportName.getText();
		logger.log(LogStatus.INFO, "retrieved value of firstSportName: " + getFirstSportName);
		return getFirstSportName;
	}

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.bt.bms:id/tvPricingInformation'][1]")
	WebElement firstSportPrice;

	public String getFirstSportPrice() {
		String getFirstSportPrice = firstSportPrice.getText();
		logger.log(LogStatus.INFO, "retrieved value of firstSportPrice: " + getFirstSportPrice);
		return getFirstSportPrice;
	}

	@FindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@resource-id='com.bt.bms:id/rvList']/android.widget.FrameLayout/android.view.ViewGroup")
	WebElement firstSportTile;

	public void showFirstSportDetails() {
		firstSportTile.click();
		logger.log(LogStatus.INFO, "Clicked on firstSportTile");
	}

}
