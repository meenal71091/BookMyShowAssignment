package com.cross.qa.pages;

import java.util.List;

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

public class SynopsisPage extends TestBase {
	// Initializing the Page Objects:
	public SynopsisPage() {
		PageFactory.initElements(androidDriver, this);
	}

	@FindBy(xpath = "//android.view.View[@resource-id='app'][1]/android.view.View/android.view.View[3]/android.view.View")
	WebElement sportName;

	public String getSportNameOnSynopsisPage() {
		String getSportNameOnSynopsisPage = sportName.getText();
		logger.log(LogStatus.INFO, "retrieved value of sportName: " + getSportNameOnSynopsisPage);
		return getSportNameOnSynopsisPage;
	}

	@FindBy(xpath = "(//android.widget.Button//preceding-sibling::android.view.View[2])[2]")
	WebElement sportPrice;

	public String getSportPriceOnSynopsisPage() {
		String getSportPriceOnSynopsisPage = sportPrice.getText();
		logger.log(LogStatus.INFO, "retrieved value of sportPrice: " + getSportPriceOnSynopsisPage);
		return getSportPriceOnSynopsisPage;
	}

	@FindBy(xpath = "//android.widget.Button[@text='Proceed']//preceding-sibling::android.view.View[2]")
	WebElement sportPriceOnTicketsPage;

	public String getSportPriceOnTicketsSelctionPage() {
		String getSportPriceOnTicketsSelctionPage = sportPriceOnTicketsPage.getText();
		logger.log(LogStatus.INFO, "retrieved value of sportPriceOnTicketsPage: " + getSportPriceOnTicketsSelctionPage);
		return getSportPriceOnTicketsSelctionPage;
	}

	@FindBy(xpath = "//android.widget.Button[@text='Proceed']//preceding-sibling::android.view.View[1]")
	WebElement ticketCountLabel;

	public String getTicketCount() {
		String getTicketCount = ticketCountLabel.getText();
		logger.log(LogStatus.INFO, "retrieved value of ticketCountLabel: " + getTicketCount);
		return getTicketCount;
	}

	@FindBy(xpath = "//android.widget.Button[@text='Register']")
	WebElement registerButton;

	public void clickOnregisterButton() {
		registerButton.click();
		logger.log(LogStatus.INFO, "Clicked on registerButton");

	}

	@FindBy(xpath = "//android.widget.Button[@text='Proceed']//preceding-sibling::android.view.View[2]")
	public List<WebElement> priceLabelList;

	@FindBy(xpath = "//android.view.View[@text='Add']")
	WebElement firstAddButton;

	public void addFirstTicket() {
		firstAddButton.click();
		logger.log(LogStatus.INFO, "Clicked on firstAddButton");

	}

	@FindBy(xpath = "//android.view.View[@resource-id='app']/android.view.View[2]//following::android.widget.Image[2]")
	WebElement firstPlusImageButton;

	public void increseTicketQuantity() {
		firstPlusImageButton.click();
		logger.log(LogStatus.INFO, "Clicked on firstPlusImageButton");
	}

}
