package com.RDM.Merger.testcases.Aa_Login;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import com.RDM.Merger.pagefunctions.*;
import com.idm.locators.*;
import com.idm.pagefunctions.IDMLoginPageFunctions;
import com.utility.base.*;
import com.utility.base.WrapperFunctionUtilities;
import com.utility.logger.Log;

public class LoginPageTest extends TestBase {


	LoginPageFunctions loginPage;
	IDMLoginPage page;

	// initializing the drivers

	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	@Parameters("browser")
	public void setUp(String browser) {

		Log.info("Browser passed as :- " + browser);
		initialization(browser);
		page = new IDMLoginPage();

	}

	// Test to verify successful Login


	@Test(groups = {"Smoke","Sanity","Regression"},priority=-1)
	public static void loginTest() {
		Log.info("TEst Commit");
		IDMLoginPageFunctions.signIn(prop.getProperty("username"), prop.getProperty("password"));
		//Assert.assertEquals(IDMLoginPageFunctions.validateHomepageUsername(), prop.getProperty("username"));
		Assert.assertEquals(CommonPageFunctions.verifyLandingPageHeader(), true);
		Log.info("Successfully Logged in with valid user ");
	}

	@Test()
	public  static void loginTestMig() {
		IDMLoginPageFunctions.signIn(prop.getProperty("mig_username"), prop.getProperty("mig_password"));
		Assert.assertEquals(IDMLoginPageFunctions.validateHomepageUsername(), prop.getProperty("username"));
		Log.info("Successfully Logged in with valid user ");
	}

	@Test()
	public  static void loginTestAccess() {
		IDMLoginPageFunctions.ResignIn(prop.getProperty("username2"), prop.getProperty("password2"));
		//Assert.assertEquals(IDMLoginPageFunctions.validateHomepageUsername(), prop.getProperty("username"));
		Log.info("Successfully Logged in with valid user ");
	}

	@Test()
	public  static void dataStewardAccess() {
		IDMLoginPageFunctions.ResignIn(prop.getProperty("data_steward_user"), prop.getProperty("data_steward_password"));
		//Assert.assertEquals(IDMLoginPageFunctions.validateHomepageUsername(), prop.getProperty("username"));
		Log.info("Successfully Logged in with valid user ");
	}

	@Test()
	public  static void loginTestDataSteward() {
		IDMLoginPageFunctions.ResignIn(prop.getProperty("data_steward_user"), prop.getProperty("data_steward_password"));
		//Assert.assertEquals(IDMLoginPageFunctions.validateHomepageUsername(), prop.getProperty("username"));
		Log.info("Successfully Logged in with valid user ");
	}

	@Test()
	public  static void loginApproverUser() {
		IDMLoginPageFunctions.ResignIn(prop.getProperty("approver_user"), prop.getProperty("approver_user_password"));
		//Assert.assertEquals(IDMLoginPageFunctions.validateHomepageUsername(), prop.getProperty("username"));
		Log.info("Successfully Logged in with valid user ");
	}

	public static void logOutFromExistingUser(WebDriver driver)
	{
		driver.manage().deleteAllCookies();
	}

	public static void loginIngesterUser1()
	{
		IDMLoginPageFunctions.ResignIn(prop.getProperty("ingester_user1"), prop.getProperty("ingester_user1_password"));
		Assert.assertEquals(CommonPageFunctions.verifyLandingPageHeader(), true);
		Log.info("Successfully Logged in with valid user ");
	}

	public static void loginIngesterUser2()
	{
		IDMLoginPageFunctions.ResignIn(prop.getProperty("ingester_user2"), prop.getProperty("ingester_user2_password"));
		Assert.assertEquals(CommonPageFunctions.verifyLandingPageHeader(), true);
		Log.info("Successfully Logged in with valid user ");
	}
	public static void loginAdminUser2()
	{
		IDMLoginPageFunctions.ResignIn(prop.getProperty("admin_user2"), prop.getProperty("admin_user2_password"));
		Assert.assertEquals(CommonPageFunctions.verifyLandingPageHeader(), true);
		Log.info("Successfully Logged in with valid user ");
	}


	@AfterSuite(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		WrapperFunctionUtilities.waitForTime(2000);
		WrapperFunctionUtilities.closeBrowser(driver);

	}

}
