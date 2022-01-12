package com.RDM.Merger.locators;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import com.RDM.Merger.testcases.Aa_Login.ExcelReader;
import com.utility.base.TestBase;
import com.utility.base.WrapperFunctionUtilities;
import com.utility.logger.Log;

public class CommonPage extends TestBase {


	public static WebElement tab ; 
	public static WebElement environment_Name1;
	public static WebElement hyperlink;
	public static WebElement tabNameUnderEllipses;
	public static WebElement step_Name;


	
	@FindBy(xpath ="//button[text()='Ok']")
	public static WebElement environment_click;
	
	@FindBy(xpath ="//span[@class='dropdown-ellipsis zs-icon zs-size-xl zs-icon-more dropdown-ellipsis-inactive']")
	public static WebElement navigationSideBarEllipisis;
	
	@FindBy(xpath="//h5[text()='ZAIDYN DATA HUB']")
	public static WebElement landingPageHeader;

	@FindBy(xpath = "//zs-tab[contains(text(),'Users')]")
	public static WebElement adminUsersTab;

	@FindBy(xpath = "//div[@source-id='users']//div//p/label/input")
	public static WebElement adminUsersTab_Search_TextBox;

	@FindBy(xpath = "//table[@class='zs-data-table user-table']//tbody/tr[1]/td[5]")
	public static WebElement groupsAssignedToUserWebElement;

	@FindBy(xpath = "//button[text()='Delete']")
	public static WebElement deleteConfirmationPopUp_Delete_Button;

	@FindBy(xpath = "//span[contains(@class,'zs-icon-error-triangle-fill')]//following-sibling::a[contains(@class,'zs-icon-close')]")
	public static WebElement closeIconErrorPopUp;

	@FindBy(xpath = "//p[normalize-space(text())='Value required.']")
	public static WebElement ErrorMessage_ValueRequired_WebElement;

	@FindBy(xpath = "//a[text()='Clear Filters']")
	public static WebElement commonPage_ClearFilters_WebElement;

	@FindBy(xpath = "//h2[text()='Past Runs']")
	public static WebElement commonPage_PastRuns_WebElement;

	public static By commonPage_LoadingSymbol_Locator = By.xpath("//zs-loader[contains(@class,'zs-loader')]");
	public static By commonPage_LoadingSymbolPreviewTable_Text = By.xpath("//p[contains(text(),'Your results are loading')]");

	public static WebElement getStepName(String stepName) {
		step_Name = driver.findElement(By.xpath("//span[contains(text(),'"+stepName+"')]"));
		return step_Name;
	}

	public static WebElement tabNameUnderEllipses(String tab) {
		tabNameUnderEllipses = driver.findElement(By.xpath("//div[@class='dropdown-content show']/a[text()='"+tab+"']"));
		return tabNameUnderEllipses;
	}
	public static WebElement tabName(String tabname)
	{
		tab = driver.findElement(By.xpath("//span[normalize-space(text())='"+tabname+"']//preceding-sibling::span"));

		return tab;
	}


	public static WebElement environmentName(String tabName)
	{
		environment_Name1 = driver.findElement(By.xpath("//*[@value='"+tabName+"' and @type='radio']//following-sibling::span[@radio]"));
		WrapperFunctionUtilities.isElementPresent(environment_Name1, tabName);
		return environment_Name1;
	}

	public static WebElement hyperlink(String link)
	{
		hyperlink = driver.findElement(By.xpath("//a[contains(.,'"+link+"')]"));
		WrapperFunctionUtilities.isElementPresent(hyperlink, link);
		return hyperlink;
	}
	
	public static WebElement hyperlink1(String link)
	{
		hyperlink = driver.findElement(By.xpath("//a[@title='"+link+"']"));
		WrapperFunctionUtilities.isElementPresent(hyperlink, link);
		return hyperlink;
	}
		
	public static WebElement DataMenuTabName(String tabname)
	{
		tab = driver.findElement(By.xpath("//a[@href='#/"+tabname+"']"));

		return tab;
	}

	public static WebElement adminMenuSubTabName(String tabname)
	{
		tab = driver.findElement(By.xpath("//zs-tab[text()='"+tabname+"']"));

		return tab;
	}

	public static WebElement commonPage_delete_Message_Toaster(String objectName){
		return driver.findElement(By.xpath("//p/span[text()='Process "+objectName+" deleted' or text()=\"Adaptor '"+objectName+"' deleted successfully\"]" ));
	}
	public CommonPage() {

		PageFactory.initElements(driver, this);

	}

}
