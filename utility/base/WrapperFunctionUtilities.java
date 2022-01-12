package com.utility.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utility.customlisteners.WebEventListener;
import com.utility.logger.Log;

public class WrapperFunctionUtilities extends TestBase {
	public static WebDriver dr;
	public static EventFiringWebDriver driver;
	public WebEventListener eventListener;
	public static String TESTDATA_SHEET_PATH = PROJECT_PATH + "/src/test/java/com/RDH/testdata/TestData.xlsx";

	public static void waitForElementVisibility(WebElement element, int time, WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.visibilityOf(element));
			Log.info("Element is visible");
		} catch (ElementNotVisibleException e) {
			Log.info("Element is not visible");
		}

		catch (Exception e) {
			Log.info("Element is not visible");
		}
	}

	public static void waitforElementToLoad(long timeoutseconds, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, timeoutseconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitForPageToLoad(int time, WebDriver driver) {

		driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
		Log.info("Page is loaded successfully");
	}

	public static void waitForTime(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getURL(WebDriver driver, String url) {
		Log.info("Navigating to the URL:" + url);
		driver.get(url);
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	// Method retrieves all the windows open for a driver in a iterator
	public static Iterator<String> getAllWindowssID(WebDriver driver) {
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itr = windows.iterator();
		return itr;
	};

	public static void closeBrowser(WebDriver driver) {
		driver.close();
		Log.info("Browser Closed");
	}

	public static void driverQuit(WebDriver driver) {
		driver.quit();
		Log.info("Browser Thread Closed");

	}

	public static void findElementByVisibleText(WebElement element, String Text, WebDriver driver) {
		Select findText = new Select(element);
		findText.selectByVisibleText(Text);

	}

	public static boolean isTextPresent(String text, WebDriver driver) {
		Log.info("Checking the presence of Text");
		@SuppressWarnings("unused")
		Boolean element = null;
		try {
			driver.getPageSource().contains(text);
			Log.info("Text is present...");

			return true;
		} catch (Exception e) {
			Log.info("Text is not present...");
		}
		return false;
	}

	public static boolean isElementPresent(WebElement ele, String name) {
		//WrapperFunctionUtilities.waitForTime(2000);
		if (ele.isDisplayed()) {
			Log.info(name + " is displayed");
			return true;
		} else {
			Log.info(name + " is Not displayed");
			return false;
		}

	}

	public static boolean isElementVisible(int maxwaittime, WebElement ele, WebDriver driver) {
		Log.info("Checking the visiblity of Element");
		@SuppressWarnings("unused")
		WebElement element = null;
		try {
			// driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, maxwaittime);
			element = wait.until(ExpectedConditions.visibilityOf(ele));
			Log.info("Element is visible...");

			return true;
		} catch (org.openqa.selenium.TimeoutException e) {
			Log.info("Element not visible...");
		}
		return false;
	}

	// Switching to the frame
	public static void switchFrame(WebDriver driver, String frame) {
		try {
			driver.switchTo().frame(frame);
		} catch (NoSuchFrameException e) {
			Log.info("No frame exception : ");
		}

		catch (StaleElementReferenceException e) {
			Log.info("Stale element exception : ");
		}

		catch (NoSuchElementException e) {
			Log.info("No Such Element exception : ");
		}

		catch (ElementNotVisibleException e) {
			Log.info("Element Not Visible exception : ");
		}

		catch (Exception e) {
			Log.info("Element Not Visible");
		}

	}

	public static boolean isElementVisibleForTime(WebElement ele, int time, WebDriver driver) {
		Log.info("Checking the visibility of Element ");
		@SuppressWarnings("unused")
		WebElement element = null;
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, time);
			element = wait.until(ExpectedConditions.visibilityOf(ele));
			Log.info("Element is visible...");
			return true;
		} catch (org.openqa.selenium.TimeoutException e) {
			Log.info("Element not visible...");
		}
		return false;
	}

	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public static void MouseOver(WebElement we, WebDriver driver) throws Exception {
		Actions actObj = new Actions(driver);
		actObj.moveToElement(we).build().perform();

	}

	public static void selectObjectByIndexValue(WebElement we, int indexvalue) throws Exception {
		Select selObj = new Select(we);
		selObj.selectByIndex(indexvalue);
	}

	public static void selectObjectByStringValue(WebElement we, String value) throws Exception {
		Select selObj = new Select(we);
		selObj.selectByValue(value);
	}

	// Scroll to the element
	public static void scrollByVisibleElement(WebElement we, WebDriver driver) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", we);
	}

	// Scroll till the end of the page
	public static void scrollDown(WebDriver driver) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	// Scroll Up in Vertical Direction
	public static void scrollUp(WebDriver driver) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}

	// Click By JavaScipt
	public static void jsClick(WebElement ele, WebDriver driver) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele);
	}

	// Get the system current date and time
	public static String getCurrentDateTime() throws Exception {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		return date;
	}

	// Accepting the Popup
	public static void acceptAlert(WebDriver driver) throws Exception {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	// Rejecting the Popup
	public static void dismissAlert(WebDriver driver) throws Exception {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public static String readUsername() {
		XSSFWorkbook workbook = null;
		try {
			FileInputStream fis = new FileInputStream(TESTDATA_SHEET_PATH);
			workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheetAt(0);
		Row row = sheet.getRow(1);
		Cell cell = row.getCell(0);
		String cellVal = cell.getStringCellValue();
		return cellVal;

	}

	public static String readPassword() {
		XSSFWorkbook workbook = null;
		try {
			FileInputStream fis = new FileInputStream(TESTDATA_SHEET_PATH);
			workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheetAt(0);
		Row row = sheet.getRow(1);
		Cell cell = row.getCell(1);
		String cellVal = cell.getStringCellValue();
		return cellVal;
	}
	//Following wait can be used even when the element is not present in DOM
	public static boolean waitForElementToBeInvisibileBy(By by, int time, WebDriver driver) {
		try {
			Log.info("Method : waitForElementInVisibility");
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
			Log.info("Element is invisible");
			return true;
		}
		catch (Exception e) {
			Log.info("Element is visible but expected is Invisible");
			return false;
		}
	}

	public static boolean refreshPageWithPageLoadTimeout(WebDriver driver) {
		try {
			Log.info("Method : refresPage");
			driver.navigate().refresh();
			waitForPageToLoad(20,driver);
			Log.info("Page Refreshed Successfully");
			return true;
		}
		catch (Exception e) {
			Log.info("Page didnot refresh in expected time");
			return false;
		}
	}

	// Scroll horizontally
	public static void scrollHorizontally(WebDriver driver,WebElement ele) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollLeft += 1000", ele);
	}

	public static boolean waitForInvisibilityOfElement(WebElement ele, int time, WebDriver driver) {
		try {
			Log.info("Method : waitForElementInVisibility");
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.invisibilityOf(ele));
			Log.info("Element is invisible");
			return true;
		}
		catch (Exception e) {
			Log.info("Element is visible but expected is Invisible");
			return false;
		}
	}
}
