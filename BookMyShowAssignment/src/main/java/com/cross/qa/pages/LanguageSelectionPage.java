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

public class LanguageSelectionPage extends TestBase{
	 // Initializing the Page Objects: 
	  public LanguageSelectionPage() {
	  PageFactory.initElements(androidDriver, this); }

	@FindBy(xpath = "//android.widget.RadioButton[@text='English']")
	WebElement englishLanguageRadioButton;
	
	public void selectEnglishLanguage() {
		englishLanguageRadioButton.click();
		logger.log(LogStatus.INFO, "Clicked on englishLanguageRadioButton");
	}
	
	@FindBy(id = "language_text_view_label")
	WebElement letsGetsStartedButton;
	
	public void clickOnletsGetsStartedButton() {
		letsGetsStartedButton.click();
		logger.log(LogStatus.INFO, "Clicked on letsGetsStartedButton");
	}
	
	
	
}
