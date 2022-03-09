package com.yatra.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.yatra.qa.util.TestUtil;


public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:/Users/Shyam/eclipse-workspace/YatraHome/src/main/java/com/yatra/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Initializing Page Objects
		PageFactory.initElements(driver, this);
	}

	public static void initialization() {
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:/Users/Shyam/Downloads/chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver", "C:/Users/Shyam/Downloads/geckodriver.exe");
			driver = new FirefoxDriver();  
		}
		
	
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

	driver.get(prop.getProperty("url"));
	}
	
	public static void selectDatefromTable(String date) {
		List<WebElement> dates = driver.findElements(By.xpath("//table[@class='days-head day-container-table']//tr/td"));
	    for(int i = 0; i<=dates.size()-1; i++) {
	    	if(dates.get(i).getAttribute("title").contains(date)) {
	    		dates.get(i).click();
	    		break;
	    		}
	    	}
	}
	
}
