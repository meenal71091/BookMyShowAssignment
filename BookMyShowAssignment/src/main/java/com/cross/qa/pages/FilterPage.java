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

public class FilterPage extends TestBase{
	 // Initializing the Page Objects: 
	  public FilterPage() {
	  PageFactory.initElements(androidDriver, this); }

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.bt.bms:id/tvLabel'][@text='501 - 2000']")
	WebElement price500To2000Range;
	
	public void selectprice500To2000Range() {
		price500To2000Range.click();
		logger.log(LogStatus.INFO, "Clicked on price500To2000Range");
	}
	
	@FindBy(xpath = "//android.widget.TextView[@text='Apply']")
	WebElement filterApplyButton;
	
	public void applyFilter() {
		filterApplyButton.click();
		logger.log(LogStatus.INFO, "Clicked on filterApplyButton");

	}
	
}
