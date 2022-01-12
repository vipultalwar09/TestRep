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

public class S3AdaptorPageTest extends AdaptorsPageFunctions {

	CommonPageFunctions commonpage;
	CommonPage common_page;
	AdaptorsPage adaptor_page;


	public S3AdaptorPageTest()
	{
		super();
		Log.info("in S3AdaptorsPageTest constructor");
	}


	@BeforeMethod(groups = {"Sanity","Smoke"})
	public void launchURL()
	{
		CommonPageFunctions.navigateToURL(prop.getProperty("url"));
		WrapperFunctionUtilities.waitForPageToLoad(30, driver);
		CommonPageFunctions.selectRDHEnvironmentIfDisplayed("Common","Environment");
		CommonPageFunctions.closeAlertPopUpOnHomeTab(); // Added piece of Code to Handle Bug -- EDGE-19023
		common_page = new CommonPageFunctions();
		adaptor_page = new AdaptorsPage();
	}

	//Verify S3 adaptor test connection successful for correct details -- RDH-4388
	@Test(groups = {"Sanity"})
	public static String  verifys3adaptorIAMUSER() throws Exception
	{
		//Log.info("Method : verifys3adaptorIAMUSER");
		WrapperFunctionUtilities.waitForTime(2000);
		navigateToAdaptorsTab();
		clickOnCreateNewAdaptorButton();
		String adaptorName = CommonPageFunctions.generateRandomString();
		AdaptorsPageFunctions.createS3AdaptorIAMUSER(adaptorName,"Adaptors");
		AdaptorsPageFunctions.testConnection();
		AdaptorsPage.save.click();
		adaptorPage_toasterClose_CrossIcon(adaptorName).click();
		WrapperFunctionUtilities.waitForTime(2000);
		boolean result = CommonPageFunctions.verifyProcess(adaptorName);
		Assert.assertEquals(result, true);
		return adaptorName;
	}

	//Verify S3 adaptor test connection successful for correct details -- RDH-4388
	@Test(groups = {"Sanity"})
	public void verifys3adaptorIAMROLE()
	{
		try
		{
			Log.info("Method : verifys3adaptorIAMROLE");
			//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
			navigateToAdaptorsTab();
			clickOnCreateNewAdaptorButton();
			String adaptorName = CommonPageFunctions.generateRandomString();
			AdaptorsPageFunctions.createS3AdaptorIAMROLE(adaptorName,"Adaptors");
			AdaptorsPageFunctions.testConnection();
			adaptor_page.save.click();
			adaptorPage_toasterClose_CrossIcon(adaptorName).click();
			WrapperFunctionUtilities.waitForTime(3000);
			boolean result = CommonPageFunctions.verifyProcess(adaptorName);
			Assert.assertEquals(result, true);

		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}


}
