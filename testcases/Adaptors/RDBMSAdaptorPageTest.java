package com.RDM.Merger.testcases.Adaptors;

import com.RDM.Merger.locators.AdaptorsPage;
import com.RDM.Merger.locators.CommonPage;
import com.RDM.Merger.locators.IngestionPage;
import com.RDM.Merger.pagefunctions.AdaptorsPageFunctions;
import com.RDM.Merger.pagefunctions.CommonPageFunctions;
import com.idm.pagefunctions.IDMLoginPageFunctions;
import com.idm.testcases.IDMLoginPageTest;
import com.utility.base.WrapperFunctionUtilities;
import com.utility.logger.Log;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RDBMSAdaptorPageTest extends AdaptorsPageFunctions {

	CommonPageFunctions common_page;
	AdaptorsPage adaptor_page;

	@BeforeMethod(groups = {"Sanity","Regression_Medium_Priority"})
	public void launchURL()
	{
		Log.info("Method : launchURL ");
		CommonPageFunctions.navigateToURL(prop.getProperty("url"));
		WrapperFunctionUtilities.waitForPageToLoad(30, driver);
		CommonPageFunctions.selectRDHEnvironmentIfDisplayed("Common","Environment");
		CommonPageFunctions.closeAlertPopUpOnHomeTab(); // Added piece of Code to Handle Bug -- EDGE-19023
		common_page = new CommonPageFunctions();
		adaptor_page = new AdaptorsPage();

	}

	public RDBMSAdaptorPageTest()
	{
		super();
		Log.info("in RDBMSAdaptorPageTest constructor");
	}

	//EDGE-17760	Validate Adaptor save setting - Postgres
	@Test(groups = {"Regression_Medium_Priority"},priority = -1)
	public void verifyAdaptorSaveSettingForPostgres() throws Exception {
			Log.info("Method : verifyAdaptorSaveSettingForPostgres");
			navigateToAdaptorsTab();
			clickOnCreateNewAdaptorButton();
			String adaptorName = createPostgresAdaptor("Adaptors");
			boolean testConnectionResult = testConnection();
			Assert.assertEquals(testConnectionResult, true, "Test Successful is not Connection");
			saveAdaptorSettings();
			boolean result = CommonPageFunctions.verifyProcess(adaptorName);
			Assert.assertEquals(result, true);
	}			

}
