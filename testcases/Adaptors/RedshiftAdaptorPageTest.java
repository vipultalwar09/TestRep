package com.RDM.Merger.testcases.Adaptors;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.RDM.Merger.locators.AdaptorsPage;
import com.RDM.Merger.locators.CommonPage;
import com.RDM.Merger.pagefunctions.AdaptorsPageFunctions;
import com.RDM.Merger.pagefunctions.CommonPageFunctions;
import com.idm.pagefunctions.IDMLoginPageFunctions;
import com.idm.testcases.IDMLoginPageTest;
import com.utility.base.WrapperFunctionUtilities;
import com.utility.logger.Log;

public class RedshiftAdaptorPageTest extends AdaptorsPageFunctions {

	CommonPageFunctions commonpage;
	CommonPage common_page;
	AdaptorsPage adaptor_page;

	@BeforeMethod(groups = {"Sanity","Smoke","Regression_High_Priority"})
	public void launchURL()
	{
		CommonPageFunctions.navigateToURL(prop.getProperty("url"));
		WrapperFunctionUtilities.waitForPageToLoad(30, driver);
		CommonPageFunctions.selectRDHEnvironmentIfDisplayed("Common","Environment");
		CommonPageFunctions.closeAlertPopUpOnHomeTab(); // Added piece of Code to Handle Bug -- EDGE-19023
		common_page = new CommonPageFunctions();
		adaptor_page = new AdaptorsPage();
	}

	public RedshiftAdaptorPageTest()
	{
		super();
		Log.info("in RedshiftAdaptorPageTest constructor");
	}

	@Test(groups = {"Smoke"},priority=-1)
	public void verifyAdaptorPageNavigationSucessfull()
	{
		try
		{
			Log.info("Method : verifyAdaptorPageNavigationSucessfull");
			WrapperFunctionUtilities.waitForTime(2000);
			CommonPageFunctions.selectRDHEnvironmentIfDisplayed("Common","Environment");
			WrapperFunctionUtilities.waitForTime(3000);
			//AdaptorsPage.adaptors_module.click();
			navigateToAdaptorsTab();
			boolean result = WrapperFunctionUtilities.isElementPresent(create_new_adaptor, "Adaptor Page");
			Assert.assertEquals(true, result);
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.assertEquals(true, false);
		}
	}

	//Verify Redshift adaptor test connection successful for correct details: Credential based -- RDH-4390

	@Test(groups = {"Sanity"},priority =0)
	public void verifyRedShiftAdaptorCredentialBased() throws Exception{

		Log.info("Method : verifyRedShiftAdaptorCredentialBased");

		//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
		//WrapperFunctionUtilities.waitForTime(3000);
		CommonPageFunctions.selectRDHEnvironmentIfDisplayed("Common","Environment");
		//AdaptorsPage.adaptors_module.click();
		navigateToAdaptorsTab();
		clickOnCreateNewAdaptorButton();
		String adaptorName = CommonPageFunctions.generateRandomString();
		AdaptorsPageFunctions.createRedShiftAdaptorCredentialBased(adaptorName,"Adaptors");
		boolean testConnectionResult = AdaptorsPageFunctions.testConnection();
		Assert.assertEquals(testConnectionResult, true, "Test Connection is not Successful");
		adaptor_page.save.click();
		WrapperFunctionUtilities.waitForTime(2000);
		boolean result = CommonPageFunctions.verifyProcess(adaptorName);
		Assert.assertEquals(result, true);

	}

	//Verify Redshift adaptor test connection successful for correct details: User Impersonation -- RDH-4395
	@Test(groups = {"Sanity"}, priority = 0)
	public String verifyRedShiftAdaptorUserImpersonation()
	{
		try
		{
			Log.info("Method : verifyRedShiftAdaptorUserImpersonation");
			//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
			//WrapperFunctionUtilities.waitForTime(2000);
			//AdaptorsPage.adaptors_module.click();
			navigateToAdaptorsTab();
			clickOnCreateNewAdaptorButton();
			String adaptorName = CommonPageFunctions.generateRandomString();
			AdaptorsPageFunctions.createRedShiftAdaptorUserImpersonation(adaptorName,"Adaptors");
			boolean testConnectionResult = AdaptorsPageFunctions.testConnection();
			Assert.assertEquals(testConnectionResult, true, "Test Connection is not Successful");
			adaptor_page.save.click();
			WrapperFunctionUtilities.waitForTime(2000);
			boolean result = CommonPageFunctions.verifyProcess(adaptorName);
			Assert.assertEquals(result, true);
			return adaptorName;
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
			return null;
		}

	}

	// EDGE-18433 Verify on editing of adaptor or changing the value of existing one, changes are saved.
	@Test(groups = {"Regression_High_Priority"}, priority = 0)
	public void verifyChangesInSavedAdaptorAreUpdated()
	{
		try
		{
			Log.info("Method : verifyChangesInSavedAdaptorAreUpdated");
			String adaptorNameToEdit = verifyRedShiftAdaptorUserImpersonation();
			updateRedshiftUserImpersonationAdaptorToCredentialBased(adaptorNameToEdit,"Adaptors");
			Assert.assertTrue(verifyRedshiftUserImpersonationToCredentialBasedAdaptorChanges(adaptorNameToEdit));
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
}
