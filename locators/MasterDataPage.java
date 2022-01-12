package com.RDM.Merger.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utility.base.TestBase;
import com.utility.base.WrapperFunctionUtilities;

public class MasterDataPage extends TestBase {


//	MDM IDs for automation - PLEASE DO NOT MERGE
//	HCP - 900000015
//	HCO - 10000017651
	
	
	
	public MasterDataPage() {

		PageFactory.initElements(driver, this);

	}


	public static WebElement id;



	@FindBy(xpath="//h4[contains(.,'Profile Details')]")
	public static WebElement profile_details;

	@FindBy(xpath="//a[contains(@href,'9000')][1]")
	public static WebElement mdmID;

	@FindBy(xpath="//button[contains(.,'Merge/Unmerge')]")
	public static WebElement merge_unmerge_button;

	@FindBy(xpath="//button[@type='button'][contains(.,'Merge')]")
	public static WebElement merge_button;

	@FindBy(xpath = "//span[contains(.,'Success:')]")
	public static WebElement mergeStatusValidation;

	@FindBy(xpath = "//div[@class='zs-margin-1-0-0-0'][contains(.,'No suspected matches found')]")
	public static WebElement noSuspectedMatchesFound;

	@FindBy(xpath = "//input[@placeholder='Search']")
	public static WebElement searchSuspectedMatches;

	@FindBy(xpath = "//zs-tab[contains(.,'HCP Details')]")
	public static WebElement hcp_details;

	@FindBy(xpath = "(//a[contains(.,'shalini kulkarni')])[1]")
	public static WebElement hcp_profile;

	@FindBy(xpath = "//h4[contains(.,'SHALINI KULKARNI')]")
	public static WebElement hcp_profile_name;

	@FindBy(xpath = "//zs-tab[contains(.,'HCO Details')]")
	public static WebElement hco_details;

	@FindBy(xpath = "//a[contains(.,'transylvania regional hospital inc')]")
	public static WebElement hco_profile;

	@FindBy(xpath = "//h4[contains(.,'TRANSYLVANIA REGIONAL HOSPITAL INC')]")
	public static WebElement hco_profile_name;

	@FindBy(xpath = "(//span[contains(.,'HCO')])[2]")
	public static WebElement hco_radioButton;

	@FindBy(xpath = "(//span[contains(.,'HCP')])[2]")
	public static WebElement hcp_radioButton;

	@FindBy(xpath="//h2[text()='Master Data Management']")
	public static WebElement mdmheader;

	@FindBy(xpath = "//button[normalize-space(text())='Search']")
	public static WebElement searchButton;


	public static WebElement mdmID(String tabName)
	{
		id = driver.findElement(By.xpath("//a[contains(.,'"+tabName+"')]"));
		WrapperFunctionUtilities.isElementPresent(id, tabName);
		return id;

	}

}
