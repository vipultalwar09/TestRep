package Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utility.TestUtil;

public class TestBase {

	public static Properties prop;
	public static WebDriver driver;
	
	
	public TestBase() throws IOException {
		
		prop = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\Properties\\config.properties");
		prop.load(ip);
	
	}
	
	public static void initialization(){
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "F:\\Selenium\\Drivers\\chromedriver_win32\\chromedriver.exe");	
			driver = new ChromeDriver(); 
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", "F:\\Selenium\\Drivers\\geckodriver-v0.21.0-win64\\geckodriver.exe");	
			driver = new FirefoxDriver(); 
		}
	
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		
		openUrl();
		
		
	
	}
	
	public static void openUrl()
	{
		driver.get(prop.getProperty("url"));
	}
	
	
	
	
	
}
