package com.RDM.Merger.pagefunctions;

import com.RDM.Merger.locators.MasterDataPage;
import com.RDM.Merger.testcases.Aa_Login.ExcelReader;
import com.utility.base.WrapperFunctionUtilities;
import com.utility.logger.Log;

public class MasterDataPageFunctions extends MasterDataPage{


	public MasterDataPageFunctions()
	{
		super();
	}



	//This method clicks on MDM IDs
	public static void clickMDMId()
	{
		try
		{
			Log.info("Method : clickMDMId");
			WrapperFunctionUtilities.waitForTime(3000);
			CommonPageFunctions.selectDataMenuOptions("DataMenu", "MDMTabName");
			WrapperFunctionUtilities.waitForTime(3000);
			mdmID.click();
			Log.info("Clicked on MDM ID");
			WrapperFunctionUtilities.waitForTime(2000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	//This method verifies affiliation
	public static void verifyAffiliation(String sheetName, String tabName)
	{
		try
		{
			Log.info("Method : verifyAffiliation");
			Log.info("Verifying affiliation");
			WrapperFunctionUtilities.waitForElementVisibility(searchSuspectedMatches, 30, driver);
			searchSuspectedMatches.sendKeys(ExcelReader.getValue(sheetName, tabName, "TestData"));
			WrapperFunctionUtilities.waitForElementVisibility(searchButton, 30, driver);
			searchButton.click();
			mdmID(ExcelReader.getValue(sheetName, tabName, "TestData")).click();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	//This method merges suspected matches
	public static void mergeSuspectedMatches()
	{
		try
		{
			Log.info("Method : mergeSuspectedMatches");
			WrapperFunctionUtilities.waitForTime(3000);
			Log.info("Merge Suspected Matches");
			merge_unmerge_button.click();
			merge_button.click();
			WrapperFunctionUtilities.waitForTime(2000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
