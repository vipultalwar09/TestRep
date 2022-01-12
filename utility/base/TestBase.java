package com.utility.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.utility.customlisteners.WebEventListener;
import com.utility.logger.Log;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static String PROJECT_PATH = System.getProperty("user.dir");

	// Loading of Property File that contins URL , username , password
	public TestBase() {
		try {
			prop = new Properties();
			//System.out.println("Project Path "+PROJECT_PATH);
			FileInputStream ip = new FileInputStream(
					// dir of property file
					PROJECT_PATH + "/src/test/java/com/RDM/Merger/configs/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public static void initialization(String browserName) {
		System.out.println("Inside Intialiation method"+browserName);
		//String browserName = prop.getProperty("browser");
		// Loading of both chrome and firefox driver
		if (browserName.equalsIgnoreCase("chrome")) {
			
			
			System.setProperty("webdriver.chrome.driver",
					PROJECT_PATH + "/src/test/java/com/RDM/Merger/configs/chromedriver.exe");
			driver = new ChromeDriver();
			System.out.println("###################### DRIVER INTIALIZED ######################");
			System.out.println("ENtered Successfully");
		} else if (browserName.equalsIgnoreCase("FF")) {
			System.setProperty("webdriver.gecko.driver",
					PROJECT_PATH + "/src/test/java/com/RDM/Merger/configs/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("MsEdge")) {
		//	System.out.println("Inside MsEdge");
		//	DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		//	capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			System.setProperty("webdriver.edge.driver",
					PROJECT_PATH + "/src/test/java/com/RDM/Merger/configs/msedgedriver.exe");
			System.out.println("Inside MsEdge");
			driver = new EdgeDriver();
			WrapperFunctionUtilities.getURL(driver, prop.getProperty("url"));
			System.out.println("Outside IE");
		}
	
		

		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with
		// EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);

		WrapperFunctionUtilities.getURL(driver, prop.getProperty("url"));

	}

}
