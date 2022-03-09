package com.yatra.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.yatra.qa.base.TestBase;
import com.yatra.qa.pages.HomePage;
import com.yatra.qa.util.TestUtil;

public class HomePageTest extends TestBase{

	HomePage homePage;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		homePage = new HomePage();
	}
	
	@Test(priority =1)
	public void homePageTest() {
		homePage.verifyPageTitle(TestUtil.HOME_PAGE_TITLE);
	}
	
	@Test(priority =2)
	public void flightSearchTest() {
		homePage.verifyPageTitle(TestUtil.HOME_PAGE_TITLE);
		homePage.selectTripType(TestUtil.TRIP_TYPE);
		homePage.selectFromAndTo(TestUtil.TRIP_TYPE);
		homePage.selectDates(TestUtil.TRIP_TYPE);
		homePage.EnterPassengerDetails(TestUtil.NUMBER_OF_PASSENGERS, TestUtil.FLIGHT_CLASS);
		homePage.clickSearchFlights();
	}
	
	@Test(priority = 3)
	public void navigationTest() {
		homePage.verifyPageTitle(TestUtil.HOME_PAGE_TITLE);
		homePage.navigateToHotels();
		homePage.verifyPageTitle(TestUtil.HOTELS);
		homePage.navigateToVillasAndStays();
		homePage.verifyPageTitle(TestUtil.VILLAS_AND_STAYS);
		homePage.navigateToBuses();
		homePage.verifyPageTitle(TestUtil.BUSES);
		homePage.navigateToHolidays();
		homePage.verifyPageTitle(TestUtil.HOLIDAYS);
	}
	
	@Test(priority = 4)
	public void loginTest() {
		homePage.verifyPageTitle(TestUtil.HOME_PAGE_TITLE);
		homePage.moveToMyAccount();
		homePage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 5)
	public void myBookingTest() {
		homePage.verifyPageTitle(TestUtil.HOME_PAGE_TITLE);
		homePage.moveToMyAccount();
		homePage.login(prop.getProperty("username"), prop.getProperty("password"));
		homePage.navigateToMyBooking();
		homePage.verifyPageTitle(TestUtil.MY_BOOKINGS);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
