package com.RDM.Merger.pagefunctions;

import org.testng.Assert;
import org.testng.annotations.*;
import com.idm.pagefunctions.*;
import com.RDM.Merger.locators.LoginPage;
import com.idm.locators.*;
import com.utility.base.WrapperFunctionUtilities;
import com.utility.logger.Log;

public class LoginPageFunctions extends LoginPage {

	// You can add your application specific login functions here 

	static LoginPageFunctions login;
	public LoginPageFunctions()
	{

		super();

	}


	public static void setUp(String browser) {
		Log.info("Browser passed as :- " + browser);
		if(browser.equals(""))
		{
			browser = prop.getProperty("browser");
		}
		initialization(browser);

	}
	public static void loginTest() {
		login = new LoginPageFunctions();
		Log.info("UserName "+prop.getProperty("username"));

		username.sendKeys(prop.getProperty("username"));
		Log.info("Username Entered");
		WrapperFunctionUtilities.waitForTime(1000);
		password.sendKeys(prop.getProperty("password"));
		Log.info("Password Entered");
		loginBtn.click();


		// Assert.assertEquals(IDMLoginPageFunctions.validateHomepageUsername(),
		// prop.getProperty("username"));
		// Assert.assertEquals(IDMLoginPageFunctions.validateMyAccountdropdown(), true);
		Log.info("Successfully Logged in with valid user "); }



	// Closing of the browser

	public static void tearDown() {
		Log.info("in Login Page Functions");
		WrapperFunctionUtilities.closeBrowser(driver);

	}
}
