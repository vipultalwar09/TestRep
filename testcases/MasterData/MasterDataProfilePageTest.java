package com.RDM.Merger.testcases.MasterData;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.RDM.Merger.locators.MasterDataPage;
import com.RDM.Merger.pagefunctions.CommonPageFunctions;
import com.RDM.Merger.pagefunctions.MasterDataPageFunctions;
import com.idm.pagefunctions.IDMLoginPageFunctions;
import com.idm.testcases.IDMLoginPageTest;
import com.utility.base.WrapperFunctionUtilities;
import com.utility.logger.Log;


public class MasterDataProfilePageTest extends MasterDataPageFunctions {


	CommonPageFunctions common_page;
	MasterDataPage masterDataPage;
	

	public MasterDataProfilePageTest()
	{
		super();

	}
	@Test(groups = {"Smoke"})
	public void verifyMDMNavigationSucessfull()

	{
			Log.info("Method : verifyMDMNavigationSucessfull");
			common_page = new CommonPageFunctions();
			masterDataPage = new MasterDataPage();
			//WrapperFunctionUtilities.waitForTime(2000);
			//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
			WrapperFunctionUtilities.waitForTime(2000);
			CommonPageFunctions.selectDataMenuOptions("DataMenu", "MDMTabName");
			WrapperFunctionUtilities.waitForTime(1000);
			boolean result = WrapperFunctionUtilities.isElementPresent(mdmheader, "Master Data Page");
			Assert.assertEquals(true, result);
	}

	//Verify for HCO-HCP affiliation, all the HCP profiles in the hierarchy should be clickable and navigate user to the selected HCP profile --RDH-4243
	@Test(groups = {"Sanity"})
	public void verifyHCOHCPAffiliation()

	{
		try
		{	
			Log.info("Method : verifyHCOHCPAffiliation");
			common_page = new CommonPageFunctions();
			masterDataPage = new MasterDataPage();
			//WrapperFunctionUtilities.waitForTime(2000);
			//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
			WrapperFunctionUtilities.waitForTime(2000);
			CommonPageFunctions.selectDataMenuOptions("DataMenu", "MDMTabName");
			WrapperFunctionUtilities.waitForTime(3000);
			MasterDataPage.hco_radioButton.click();
			WrapperFunctionUtilities.waitForTime(2000);
			MasterDataPageFunctions.verifyAffiliation("MasterData","HCO");
			MasterDataPage.hcp_details.click();
			WrapperFunctionUtilities.waitForTime(2000);
			MasterDataPage.hcp_profile.click();
			WrapperFunctionUtilities.waitForTime(3000);
			boolean profileName = MasterDataPage.hcp_profile_name.isDisplayed();
			Assert.assertEquals(profileName, true);

		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}


	}

	//Verify for HCP-HCO affiliation, all the HCO profiles in the hierarchy should be clickable and navigate user to the selected HCO profile --RDH-4247
	@Test(groups = {"Sanity"})
	public void verifyHCPHCOAffiliation()

	{
		try
		{
			Log.info("Method : verifyHCPHCOAffiliation");
			common_page = new CommonPageFunctions();
			masterDataPage = new MasterDataPage();
			//WrapperFunctionUtilities.waitForTime(2000);
			//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
			WrapperFunctionUtilities.waitForTime(2000);
			CommonPageFunctions.selectDataMenuOptions("DataMenu", "MDMTabName");
			WrapperFunctionUtilities.waitForTime(3000);
			MasterDataPage.hcp_radioButton.click();
			WrapperFunctionUtilities.waitForTime(2000);
			MasterDataPageFunctions.verifyAffiliation("MasterData","HCP");
			MasterDataPage.hco_details.click();
			WrapperFunctionUtilities.waitForTime(2000);
			WrapperFunctionUtilities.jsClick(hco_profile, driver);
			//MasterDataPage.hco_profile.click();
			WrapperFunctionUtilities.waitForTime(3000);
			boolean profileName = MasterDataPage.hco_profile_name.isDisplayed();
			Assert.assertEquals(profileName, true);

		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}


	}


	//Verify on clicking MDM ID user is navigated to Profile tab of it. --RDH-3517
		@Test(groups = {"Sanity"})
		public void verifyProfileDetails()

		{
			try
			{
				Log.info("Method : verifyProfileDetails");
				common_page = new CommonPageFunctions();
				masterDataPage = new MasterDataPage();
				//WrapperFunctionUtilities.waitForTime(2000);
				//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
				WrapperFunctionUtilities.waitForTime(2000);
				MasterDataPageFunctions.clickMDMId();
				WrapperFunctionUtilities.waitForTime(2000);
				boolean profileDetails = MasterDataPage.profile_details.isDisplayed();
				Assert.assertEquals(profileDetails, true);
				Log.info("Profile details are successfully displayed");
			}
			catch (Exception e) {
				e.printStackTrace();
				Assert.fail(e.getMessage());
			}


		}





		//Verify case/scenario when suspected match gets merged by user --RDH-3623
		public void verifySuspectedMatchesGetMerged()

		{
			try
			{
				Log.info("Method : verifySuspectedMatchesGetMerged");
				common_page = new CommonPageFunctions();
				masterDataPage = new MasterDataPage();
				//WrapperFunctionUtilities.waitForTime(2000);
				//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
				WrapperFunctionUtilities.waitForTime(2000);
				MasterDataPageFunctions.clickMDMId();
				WrapperFunctionUtilities.waitForTime(2000);
				MasterDataPageFunctions.mergeSuspectedMatches();
				boolean matchNotFound = MasterDataPage.noSuspectedMatchesFound.isDisplayed();
				Assert.assertEquals(matchNotFound, true);
				Log.info("No suspected matches found");
				String mergeStatus = MasterDataPage.mergeStatusValidation.getText();
				boolean status = mergeStatus.contains("Success:");
				Assert.assertEquals(status, true);
				Log.info("Profile merged successfully");

			}
			catch (Exception e) {
				e.printStackTrace();
				Assert.fail(e.getMessage());
			}


		}

		@BeforeMethod(groups = {"Sanity"})
		public void launchURL()
		{
			Log.info("Method : launchURL ");
			//WrapperFunctionUtilities.getURL(driver, prop.getProperty("url"));
			CommonPageFunctions.navigateToURL(prop.getProperty("url"));
			WrapperFunctionUtilities.waitForPageToLoad(30, driver);
			CommonPageFunctions.selectRDHEnvironmentIfDisplayed("Common","Environment");
			CommonPageFunctions.closeAlertPopUpOnHomeTab(); // Added piece of Code to Handle Bug -- EDGE-19023
		}
		

}

