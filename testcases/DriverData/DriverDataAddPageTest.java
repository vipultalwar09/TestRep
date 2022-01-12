package com.RDM.Merger.testcases.DriverData;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.RDM.Merger.locators.DriverDataPage;
import com.RDM.Merger.pagefunctions.CommonPageFunctions;
import com.RDM.Merger.pagefunctions.DriverDataPageFunctions;
import com.RDM.Merger.testcases.Aa_Login.ExcelReader;
import com.RDM.Merger.testcases.BRM.BRMRuleGroupPageTest;
import com.idm.pagefunctions.IDMLoginPageFunctions;
import com.idm.testcases.IDMLoginPageTest;
import com.utility.base.WrapperFunctionUtilities;
import com.utility.base.Xls_Reader;
import com.utility.logger.Log;

import com.utility.base.TestBase;

public class DriverDataAddPageTest extends DriverDataPageFunctions {

	DriverDataPage ddPage;
	DriverDataPageFunctions ddPageFunction;
	CommonPageFunctions cpage;
	DriverDataAddPageTest ddPageTest;


	public DriverDataAddPageTest() {
		super();
		//ddPage = new DriverDataPage();

	}

	@Test(groups = {"Smoke"})
	public void verifyDriverDataPageNavigation() {
		
		cpage = new CommonPageFunctions();
		ddPage = new DriverDataPage();
		Log.info("Method : verifyDriverDataPageNavigation");
		//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
		WrapperFunctionUtilities.waitForTime(3000);
		CommonPageFunctions.selectDataMenuOptions("DataMenu", "driverdataTabName");
		WrapperFunctionUtilities.waitForTime(2000);
		boolean result = WrapperFunctionUtilities.isElementPresent(driverDataHeader, "Driver Data Page");
		System.out.println(result);
		WrapperFunctionUtilities.waitForTime(2000);
		Assert.assertEquals(true, result);

	}

	//RDH-2309 (Add Row) Verify Detail Column of Audit Logs have correct info in case of creation of DD Row being added --> RDH-2309
	@Test(groups = {"Sanity"})
	public void verifyDriverDataAddNewRow() {
		try {
			cpage = new CommonPageFunctions();
			ddPage = new DriverDataPage();
			Log.info("Method : verifyDriverDataAddNewRow");
			WrapperFunctionUtilities.waitForTime(1000);
			//CommonPageFunctions.selectRDHEnvironment("Common", "Environment");
			CommonPageFunctions.selectDataMenuOptions("DataMenu", "driverdataTabName");
			WrapperFunctionUtilities.waitForTime(1000);
			//DriverDataPageFunctions.driverDataSelect();
			DriverDataPageFunctions.tableView("DDobject1");
			WrapperFunctionUtilities.waitForTime(1000);
			DriverDataPageFunctions.clickAddRow();
			HashMap hmap = DriverDataPageFunctions.getTestDataFromExcel("Driver", "DriverData");
			DriverDataPageFunctions.addNewRow(hmap, "TopFilter1", "TopFilter2");
			clickSaveNewRow();
			verifyNewlyAddedRow();
			deleteAddedRow("Driver", "ZIP ID");
			DriverDataPageFunctions.verifyRowAddAuditLog();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	// EDGE-17394 -Verify user is able to add new data row and map data for driver data mapping object
	@Test(groups = {"Sanity"})
	public void verifyDriverDataAddNewRowMappingObject() {
		try {
			cpage = new CommonPageFunctions();
			ddPage = new DriverDataPage();
			Log.info("Method : verifyDriverDataAddNewRowMappingObject");
			WrapperFunctionUtilities.waitForTime(1000);
			//CommonPageFunctions.selectRDHEnvironment("Common", "Environment");
			WrapperFunctionUtilities.waitForTime(3000);
			CommonPageFunctions.selectDataMenuOptions("DataMenu", "driverdataTabName");
			WrapperFunctionUtilities.waitForTime(1000);
			//DriverDataPageFunctions.driverDataSelect();
			DriverDataPageFunctions.tableView("DDobject2");
			WrapperFunctionUtilities.waitForTime(1000);
			WrapperFunctionUtilities.scrollDown(driver);
			WrapperFunctionUtilities.waitForTime(1000);
			DriverDataPageFunctions.clickAddRow();
			HashMap hmap = DriverDataPageFunctions.getTestDataFromExcel("MappingObject", "DriverData");
			addMappedNewRow(hmap, "TopFilter1", "TopFilter2", "Foreign Key Column Name", "Referred Column Name", "Foreign Key Column Value", "Referred Column Value");
			clickSaveNewRow();
			verifyNewlyAddedRow();
			deleteAddedRow("MappingObject", "Zip ID");
			WrapperFunctionUtilities.waitForTime(1000);
			DriverDataPageFunctions.verifyRowAddAuditLog();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	//Verify on clicking the Driver Data Object, the user should be navigated to Driver Data Details Page --> RDH-1652
	@Test(groups = {"Sanity"})
	public void verifyDriverDataDetailsPage() {
		try {
			cpage = new CommonPageFunctions();
			ddPage = new DriverDataPage();
			Log.info("Method : verifyDriverDataDetailsPage");
			WrapperFunctionUtilities.waitForTime(2000);
			//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
			WrapperFunctionUtilities.waitForTime(2000);
			CommonPageFunctions.selectDataMenuOptions("DataMenu", "driverdataTabName");
			WrapperFunctionUtilities.waitForTime(1000);
			DriverDataPageFunctions.tableView("DDobject1");
			//DriverDataPageFunctions.driverDataSelect();
			boolean actualresult = ddDetailPage.isDisplayed();
			Assert.assertEquals(true, actualresult);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	// RDH-2314	(Download Version) Verify Detail Column of Audit Logs have correct info in case of DD version been downloaded

	@Test(groups = {"Sanity"})
	public void verifyDriverDataDownloadAuditLog() {
		try {
			cpage = new CommonPageFunctions();
			ddPage = new DriverDataPage();
			Log.info("Method : verifyDriverDataDownloadAuditLog");
			WrapperFunctionUtilities.waitForTime(1000);
			//CommonPageFunctions.selectRDHEnvironment("Common", "Environment");
			CommonPageFunctions.selectDataMenuOptions("DataMenu", "driverdataTabName");
			WrapperFunctionUtilities.waitForTime(1000);
			//DriverDataPageFunctions.driverDataSelect();
			DriverDataPageFunctions.tableView("DDobject1");
			WrapperFunctionUtilities.waitForTime(1000);
			DriverDataPageFunctions.DriverDataDownload("DDobject1");
			verifyDDDownloadAuditLog();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

	}

	//(Upload Row - Append) Verify Detail Column of Audit Logs have correct info in case of DD Rows being Uploaded
	@Test(groups = {"Sanity"})
	public void verifyDriverDataAppendUploadAuditLog() {
		try {
			cpage = new CommonPageFunctions();
			ddPage = new DriverDataPage();
			Log.info("Method : verifyDriverDataDownloadAuditLog");
			WrapperFunctionUtilities.waitForTime(1000);
			//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
			CommonPageFunctions.selectDataMenuOptions("DataMenu", "driverdataTabName");
			DriverDataPageFunctions.tableView("DDobject1");
			int count = ddPage.getDDRowCount();
			System.out.println("DD Row count : " + count);
			//DriverDataPageFunctions.AddDelete(count);
			//DriverDataPageFunctions.DriverDataUpload("Automation_Upload");
			DriverDataPageFunctions.DriverDataUpload("ZipMapping_Active");
			verifyUploadAppendAuditLog();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	// RDH-2313 - (Upload Row - Delete|Insert) Verify Detail Column of Audit Logs have correct info in case of DD Rows being Uploaded
	@Test(groups = {"Sanity"})
	public void verifyDriverDataDeleteInsertUploadAuditLog() {
		try {
			cpage = new CommonPageFunctions();
			ddPage = new DriverDataPage();
			Log.info("Method : verifyDriverDataDeleteInsertUploadAuditLog");
			//CommonPageFunctions.selectRDHEnvironment("Common", "Environment");
			CommonPageFunctions.selectDataMenuOptions("DataMenu", "driverdataTabName");
			DriverDataPageFunctions.tableView("DDObject3");
			int count = ddPage.getDDRowCount();
			System.out.println("DD Row count : " + count);
			DriverDataPageFunctions.AddDelete(count);
			WrapperFunctionUtilities.waitForTime(2000);
			DriverDataPageFunctions.DriverDeleteInsertUpload("BrandMarket4_Insert_Overwrite");
			WrapperFunctionUtilities.waitForTime(2000);
			verifyUploadInsertDeleteAuditLog("DDObject3");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	// EDGE-17400 Verify user is able to view and navigate to previous versions of the driver data object
	@Test(groups = {"Sanity"})
	public void verifyNavigationToPreviousDDVersion() {
		try {
			cpage = new CommonPageFunctions();
			ddPage = new DriverDataPage();
			Log.info("Method : verifyNavigationToPreviousDDVersion");
			WrapperFunctionUtilities.waitForTime(1000);
			//CommonPageFunctions.selectRDHEnvironment("Common", "Environment");
			CommonPageFunctions.selectDataMenuOptions("DataMenu", "driverdataTabName");
			WrapperFunctionUtilities.waitForTime(1000);
			DriverDataPageFunctions.tableView("DDobject1");
			WrapperFunctionUtilities.scrollDown(driver);
			Assert.assertTrue(verifyDriverDataPreviousVersionNavigation(), "UploadFile button is enabled");
		} catch (Exception e) {
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
		//CommonPageFunctions.selectRDHEnvironmentIfDisplayed("Common","Environment");
	}
	
}
