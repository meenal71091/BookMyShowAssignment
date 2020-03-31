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

public class LoginPage extends TestBase{
	 // Initializing the Page Objects: 
	  public LoginPage() {
	  PageFactory.initElements(androidDriver, this); }

	@FindBy(id = "launcher_tv_for_skip")
	WebElement skipLink;
	
	public void skipLoginLink() {
		skipLink.click();
		logger.log(LogStatus.INFO, "Clicked on skipLink");
	}		
}
