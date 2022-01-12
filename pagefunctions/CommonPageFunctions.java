package com.RDM.Merger.pagefunctions;

import static org.testng.Assert.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date.*;
import java.text.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.RDM.Merger.locators.*;
import com.RDM.Merger.testcases.Aa_Login.ExcelReader;
import com.idm.locators.IDMLoginLandingPage;
import com.utility.base.*;
import com.utility.logger.*;

public class CommonPageFunctions extends CommonPage {

	static StringBuffer buffer = null;
	static boolean status = false;
	static boolean stat = false;
	static CommonPageFunctions comm = new CommonPageFunctions();
	ExcelReader excel_read;
	BRMPage brmPage;

	public CommonPageFunctions() {
		super();

		brmPage = new BRMPage();
	}


	//This function selects the respective RDH Environment.
	public static void selectRDHEnvironment(String sheetName, String tabName) {
		try {
			Log.info("Method : selectRDHEnvironment");
			WrapperFunctionUtilities.waitForTime(2000);
			environmentName(ExcelReader.getValue(sheetName, tabName, "TestData"));
			environment_Name1.click();
			WrapperFunctionUtilities.waitForTime(2000);
			environment_click.click();
			Log.info("Successfully Selected the environment");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void hyperlinkClick(String hyperlinkName) {
		try {
			Log.info("Method : hyperlinkClick");
			//WrapperFunctionUtilities.waitForTime(1000);
			hyperlink(hyperlinkName);
			//WrapperFunctionUtilities.waitForTime(1000);
			hyperlink.click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void hyperlinkClick1(String hyperlinkName) {
		try {
			Log.info("Method : hyperlinkClick");
			WrapperFunctionUtilities.waitForTime(1000);
			hyperlink1(hyperlinkName);
			WrapperFunctionUtilities.waitForTime(1000);
			hyperlink.click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static String generateRandomString() {
		UUID uuid = UUID.randomUUID();
		String randomUUIDString = uuid.toString();
		final String uuid1 = "a" + UUID.randomUUID().toString().replace("-", "").substring(0, 28);
		Log.info(uuid1);
		return uuid1;

	}


	public static void clickTab(String sheetName, String tabName) {
		try {
			Log.info("Method : ClickTab");
			WrapperFunctionUtilities.waitForTime(1000);
			tabName(ExcelReader.getValue(sheetName, tabName, "TestData"));
			tab.click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//This function is used for entering character values in text input fields
	public static void TypeKey(WebElement webElement, String value) {
		try {
			Log.info("Method : TypeKey");
			webElement.clear();
			webElement.click();
			buffer = new StringBuffer();

			for (int i = 0; i < value.length(); i++) {
				char c = value.charAt(i);
				String s = new StringBuilder().append(c).toString();
				webElement.sendKeys(s);
			}

			webElement.sendKeys(buffer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static boolean verifyProcess(String jobName) {
		Log.info("Method : verifyProcess");
		boolean result = CommonPageFunctions.verifySuccess(jobName);
		return result;
	}


	public static boolean verifySuccess(String jobName) {
		try {
			int size = driver.findElements(By.xpath("//span[contains(.,'" + jobName + "')]")).size();
			Log.info("Size for Adaptor/Ingestion/Extraction found is " + size);
			if (size == 0) {
				status = false;
			} else {
				status = true;
			}
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return status;
	}

	//This function is used to select different types of Data Modules
	public static void selectDataMenuOptions(String sheetName, String tabName) {
		try {
			Log.info("Method : selectDataMenu");
			Log.info("Click Data Tab");
			CommonPageFunctions.clickTab("Dashboard", "TabName");
			WrapperFunctionUtilities.waitForTime(1000);
			DataMenuTabName(ExcelReader.getValue(sheetName, tabName, "TestData"));
			WrapperFunctionUtilities.jsClick(tab,driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//This Function is used to switch the screen.
	public static boolean switchscreen(String stepName) throws InterruptedException {

		try {
			boolean result = false;
			System.out.println("isEnabled " + IngestionPage.next_click.isEnabled());
			if ((IngestionPage.next_click.isEnabled()) == true) {
				System.out.println("is visible " + WrapperFunctionUtilities.isElementVisible(3000, IngestionPage.next_click, driver));
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", IngestionPage.next_click);
				WrapperFunctionUtilities.waitForTime(1000);
				result = getStepName(stepName).isEnabled();
				System.out.println(stepName + "  is Displayed Result  " + result);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	// This function helps to navigate to RDH modules which appear after clicking on ellipses on the sidebar
	public static void clickTabUnderEllipsis(String tabName) {

		navigationSideBarEllipisis.click();
		tabNameUnderEllipses(tabName);
		tabNameUnderEllipses.click();
	}

	public static boolean verifyLandingPageHeader() {
		boolean flag = true, result;
		Log.info("Method : verifyLandingPageHeader");
		result = WrapperFunctionUtilities.isElementPresent(landingPageHeader, "landingPageHeader");
		if (!result) {
			flag = false;
		}
		return flag;
	}

	public static boolean verifyGroupMembershipofUser(String userName, String groupsAssigned) throws Exception {
		driver.navigate().to(prop.getProperty("admin_url"));
		CommonPageFunctions.adminUsersTab.click();
		CommonPage.adminUsersTab_Search_TextBox.sendKeys(ExcelReader.getValue("Admin", userName, "TestData"));
		WrapperFunctionUtilities.waitForTime(1000);
		String groupsDisplayed = groupsAssignedToUserWebElement.getText();
		Log.info(groupsDisplayed);
		boolean result = groupsDisplayed.equals(groupsAssigned) ? true : false;
		return result;
	}
		public static void navigateToURL(String URL)
		{
			driver.navigate().to(URL);
		}

		//This function selects the respective RDH Environment.
		public static void selectRDHEnvironmentIfDisplayed(String sheetName, String tabName){
			try {
				Log.info("Method : selectRDHEnvironmentIfDisplayed");
				WrapperFunctionUtilities.waitForTime(2000);
				environmentName(ExcelReader.getValue(sheetName, tabName, "TestData"));
				boolean result = WrapperFunctionUtilities.isElementPresent(environment_Name1, "environment_Name1");
				if (result) {
					environment_Name1.click();
					//WrapperFunctionUtilities.jsClick(environment_Name1,driver);
					WrapperFunctionUtilities.waitForTime(2000);
					environment_click.click();
					Log.info("Successfully Selected the environment");
				}
			} catch (NoSuchElementException e) {
				Log.info("Environment Pop Up not present");
			} catch (Exception e) {
				Log.info(e.getMessage());
			}
		}

	public static boolean storeExistenceOfElement(By locatorOfWebElement, WebDriver driver)
	{
		boolean existenceOfWebElement =  driver.findElements(locatorOfWebElement).size()>0?true:false;
		return existenceOfWebElement;
	}

	// Added piece of Code to Handle Bug -- EDGE-19023
	public static void closeAlertPopUpOnHomeTab(){
		try {
			Log.info("Method : closeAlertPopUpOnHomeTab");
			boolean result = WrapperFunctionUtilities.isElementPresent(closeIconErrorPopUp, "closeIconErrorPopUp");
			if (result) {
				closeIconErrorPopUp.click();
				Log.info("Alert Pop Up Successfully closed");
			}
		} catch (NoSuchElementException e) {
			Log.info("Alert Pop Up not present");
		} catch (Exception e) {
			Log.info(e.getMessage());
		}
	}

	public static boolean  verifyValueRequiredErrorMessage(){
		return WrapperFunctionUtilities.isElementPresent(ErrorMessage_ValueRequired_WebElement, "Error Message Value Required");
	}
}

