package com.RDM.Merger.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utility.base.TestBase;
import com.utility.logger.Log;

public class LoginPage extends TestBase {

	// Page Factory -

	/*
	 * This class provides locators for Login Page
	 */

	@FindBy(id = "signin_button")
	protected static WebElement signIn;

	@FindBy(id = "login_form")
	protected static WebElement loginform;

	@FindBy(name = "ctl00$PageContentPlaceholder$UserNameInput")
	protected static WebElement username;

	@FindBy(name = "ctl00$PageContentPlaceholder$PasswordInput")
	protected static WebElement password;

	@FindBy(name = "ctl00$PageContentPlaceholder$LoginButton")
	public static WebElement loginBtn;

	@FindBy(xpath = "//a[@href='javascript:void(0)']")
	protected static WebElement role;

	@FindBy(id = "lbl_ma")
	protected static WebElement myAcc;

	public LoginPage() {
		
		PageFactory.initElements(driver, this);
	}

}
