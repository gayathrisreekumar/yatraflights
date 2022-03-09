package com.yatra.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.yatra.qa.base.TestBase;
import com.yatra.qa.util.TestUtil;

public class HomePage extends TestBase{
	
	@FindBy(xpath = "//a[@title='Round Trip']")
	WebElement roundTripButton;
	
	@FindBy(xpath = "//a[@title='One Way']")
	WebElement oneWayButton;
	
	@FindBy(id = "BE_flight_origin_city")
	WebElement departFrom;
	
	@FindBy(id = "BE_flight_arrival_city")
	WebElement goingTo;
	
	@FindBy(xpath = "//div[@class = 'ac_results origin_ac']//div[@class='viewport']//li[contains(@class,'active')]")
	WebElement fromDropdownOption;
	
	@FindBy(xpath = "//div[@class = 'ac_results dest_ac']//div[@class='viewport']//li[contains(@class,'active')]")
	WebElement toDropdownOption;
	
	@FindBy(id = "BE_flight_origin_date")
	WebElement departureDate;
	
	@FindBy(id = "BE_flight_arrival_date")
	WebElement arraivalDate;
	
	@FindBy(id = "BE_flight_flsearch_btn")
	WebElement searchFlightsButton;
	
	@FindBy(xpath = "//i[contains(@class, 'arrowpassengerBox')]")
	WebElement travellerDetailsArrow;
	
	@FindBy(xpath = "//span[(@class = 'ddSpinnerPlus')]")
	WebElement plusArrow;
	
	@FindBy(id = "booking_engine_hotels")
	WebElement hotelsIcon;
	
	@FindBy(id = "booking_engine_homestays")
	WebElement villasAndStaysIcon;
	
	@FindBy(id = "booking_engine_buses")
	WebElement busesIcon;
	
	@FindBy(id = "booking_engine_holidays")
	WebElement holidaysIcon;
	
	@FindBy(id = "userLoginBlock")
	WebElement myAccountButton;
	
	@FindBy(id = "signInBtn")
	WebElement loginOption;
	
	@FindBy(id = "SignUpBtn")
	WebElement signUpOption;
	
	@FindBy(id = "login-input")
	WebElement loginEmailID;
	
	@FindBy(id = "login-password")
	WebElement loginPassword;
	
	@FindBy(id = "login-submit-btn")
	WebElement loginButton;
	
	@FindBy(id = "login-continue-btn")
	WebElement continueButton;
	
	@FindBy(xpath = "//a[contains(@class, 'loginUserName')]")
	WebElement loggedInUsername;
	
	@FindBy(xpath = "//a[contains(@class, 'loginUserName')]//following-sibling::div//li/a[@title = 'My Bookings']")
	WebElement myBookingsOption;
	
	public void verifyPageTitle(String pageTitle) {
		String title = driver.getTitle();
		Assert.assertEquals(title, pageTitle);
	}
	
	public void selectTripType(String type) {
		Assert.assertTrue(roundTripButton.isDisplayed(), "Round Trip option is not displayed");
		Assert.assertTrue(oneWayButton.isDisplayed(), "One way option is not displayed");
		if(type == "Round Trip") {
			roundTripButton.click();
		}else if(type == "One Way") {
			oneWayButton.click();
		}
	}	
	
	public void selectFromAndTo(String tripType) {
		if(tripType == "Round Trip") {
			Assert.assertTrue(departFrom.isDisplayed(), "Depart From Field is not displayed");
			Assert.assertTrue(goingTo.isDisplayed(), "Going To Field is not displayed");
			departFrom.click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			departFrom.sendKeys(TestUtil.DEPART_FROM);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<WebElement> fromallOptions = driver.findElements(By.xpath("//div[@class = 'ac_results origin_ac']//div[@class='viewport']//li"));
		    for(int i = 0; i<=fromallOptions.size()-1; i++) {
		    	if(fromallOptions.get(i).getText().contains("Bangalore")) {
		    		fromallOptions.get(i).click();
		    		break;
		    		}
		    	} 
		    goingTo.click();
		    goingTo.sendKeys(TestUtil.GOING_TO);
		    List<WebElement> toallOptions = driver.findElements(By.xpath("//div[@class = 'ac_results dest_ac']//div[@class='viewport']//li"));
		    for(int i = 0; i<=toallOptions.size()-1; i++) {
		    	if(toallOptions.get(i).getText().contains("Chennai")) {
		    		toallOptions.get(i).click();
		    		break;
		    		}
		    	}
		    }
		else {
			System.out.println("One-Way");
		}
     }
	
	public void selectDates(String tripType) {
		if(tripType== "Round Trip") {
			departureDate.click();
			selectDatefromTable(TestUtil.DEPARTURE_DATE);
			selectDatefromTable(TestUtil.RETURN_DATE);   
		}
	}
	
	public void EnterPassengerDetails(Integer number, String flightClass) {
		travellerDetailsArrow.click();
		for(int i=1; i<number; i++) {
			plusArrow.click();
		}
	}

	public void clickSearchFlights() {
		Assert.assertTrue(searchFlightsButton.isDisplayed(), "Search Flight Button not displayed");
		searchFlightsButton.click();
	}
	
	public void navigateToHotels() {
		Assert.assertTrue(hotelsIcon.isDisplayed(), "Hotels Icon is not displayed");
		hotelsIcon.click();
	}
	
	public void navigateToVillasAndStays() {
		Assert.assertTrue(villasAndStaysIcon.isDisplayed(), "Villas and Stays Icon is not displayed");
		villasAndStaysIcon.click();
	}
	
	public void navigateToBuses() {
		Assert.assertTrue(busesIcon.isDisplayed(), "Buses Icon is not displayed");
		busesIcon.click();
	}
	
	public void navigateToHolidays() {
		Assert.assertTrue(holidaysIcon.isDisplayed(), "Holidays Icon is not displayed");
		holidaysIcon.click();
	}

	public void moveToMyAccount() {
		Assert.assertTrue(myAccountButton.isDisplayed(), "My Account Button is not displayed");
		myAccountButton.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void login(String email, String password) {
		WebDriverWait w = new WebDriverWait( driver, 20);
		w.until(ExpectedConditions.visibilityOf(loginOption));
		Assert.assertTrue(loginOption.isDisplayed(), "Login Option is not displayed");
		loginOption.click();
		Assert.assertTrue(loginEmailID.isDisplayed(), "EmailID field is not displayed");
		loginEmailID.sendKeys(email);
		continueButton.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(loginPassword.isDisplayed(), "Password field is not displayed");
		loginPassword.sendKeys(password);
		loginButton.click();
		hotelsIcon = w.until(ExpectedConditions.visibilityOf(searchFlightsButton));
		Assert.assertTrue(loggedInUsername.isDisplayed(), "login not successfull");
	}
	
	public void navigateToMyBooking() {
		Assert.assertTrue(loggedInUsername.isDisplayed(), "UserName is not displayed");
		loggedInUsername.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(myBookingsOption.isDisplayed(), "My Bookings is not displayed");
		myBookingsOption.click();
	}
}
