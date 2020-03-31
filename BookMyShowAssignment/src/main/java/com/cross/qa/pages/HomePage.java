package com.cross.qa.pages;

import java.time.Duration;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.cross.qa.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class HomePage extends TestBase{
	 // Initializing the Page Objects: 
	  public HomePage() {
	  PageFactory.initElements(androidDriver, this); }

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.bt.bms:id/location_pop_cities_name'][@text='Mumbai']")
	WebElement mumbaiCityTile;
	
	public void selectMumbaiCity() {
		mumbaiCityTile.click();
		logger.log(LogStatus.INFO, "Clicked on mumbaiCityTile");

	}
	
	@FindBy(xpath = "//android.widget.TextView[@text='All']")
	WebElement allSubRegionOption;
	
	public void selectSubRegionAsAll() {
		allSubRegionOption.click();
		logger.log(LogStatus.INFO, "Clicked on allSubRegionOption");

	}
	
	@FindBy(xpath = "((//android.widget.TextView[@text='Sports'])[2]//following::android.widget.TextView[@text='View All'])[1]")
	List<WebElement> sportsViewAllLinkList;
	
	public void scrollTillSportsSection() {
		while (sportsViewAllLinkList.size() == 0) {
			Dimension windowsize = androidDriver.manage().window().getSize();
			int endY = (int) (windowsize.getHeight() * 0.9);
			System.out.println("Couldn't find Events..Trying againwith endY:" + endY);
			new TouchAction(androidDriver).press(PointOption.point(0, endY))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(0, 0))
					.release().perform();
		}
		logger.log(LogStatus.INFO, "Scrolled till Sports Section on Home Page");

	}
	
	@FindBy(how=How.XPATH ,using="((//android.widget.TextView[@text='Sports'])[2]//following::android.widget.TextView[@text='View All'])[1]")
	WebElement sportsViewAllLink;
	
	public void clickSportsViewAllLink() {
		sportsViewAllLink.click();
		logger.log(LogStatus.INFO, "Clicked on sportsViewAllLink");

	}
}
