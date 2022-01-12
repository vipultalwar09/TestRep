package com.RDM.Merger.pagefunctions;

import org.testng.Assert;

import com.RDM.Merger.locators.CommonPage;
import com.RDM.Merger.locators.AdaptorsPage;
import com.RDM.Merger.testcases.Aa_Login.ExcelReader;
import com.utility.base.WrapperFunctionUtilities;
import com.utility.logger.Log;

import java.util.HashMap;

public class AdaptorsPageFunctions extends AdaptorsPage{

	AdaptorsPage adaptor_page;

	public AdaptorsPageFunctions()
	{
		super();
		adaptor_page=new AdaptorsPage();
		System.out.println("in adaptor page function");

	}


	//This function creates s3 adaptor with I AM USER AWS Credentials
	public static void createS3AdaptorIAMUSER(String adaptorName, String sheetName) throws Exception {

		Log.info("Method : createS3AdaptorIAMUSER");
		HashMap<String,String> testData = ExcelReader.getTestDataFromExcel(sheetName,"TestData");
		WrapperFunctionUtilities.jsClick(s3_data_source,driver);
		adaptor_name.sendKeys(adaptorName);
		CommonPageFunctions.TypeKey(AdaptorsPage.bucket_name, testData.get("S3_User_Bucket"));
		WrapperFunctionUtilities.jsClick(region_name, driver);
		CommonPageFunctions.hyperlinkClick(testData.get("S3_Region_Name"));
		WrapperFunctionUtilities.jsClick(aws_credentials, driver);
		WrapperFunctionUtilities.jsClick(CommonPageFunctions.hyperlink(testData.get("S3_User_AWS_Credentials")),driver);
		CommonPageFunctions.TypeKey(AdaptorsPage.access_key, testData.get("S3_Access_Key"));
		CommonPageFunctions.TypeKey(AdaptorsPage.secret_key, testData.get("S3_Secret_Key"));
	}



	//This function creates s3 adaptor with I AM ROLE AWS Credentials
	public static void createS3AdaptorIAMROLE(String adaptorName,String sheetName) {
		try
		{
			Log.info("Method : createS3AdaptorIAMROLE");
			HashMap<String,String> testData = ExcelReader.getTestDataFromExcel(sheetName,"TestData");
			//s3_data_source.click();
			WrapperFunctionUtilities.jsClick(s3_data_source,driver);
			adaptor_name.sendKeys(adaptorName);
			//bucket_name.sendKeys(ExcelReader.getValue(sheetName,"S3_Role_Bucket","TestData"));
			bucket_name.sendKeys(testData.get("S3_Role_Bucket"));
			WrapperFunctionUtilities.jsClick(region_name, driver);
			CommonPageFunctions.hyperlinkClick(testData.get("S3_Region_Name"));
			//AdaptorsPage.aws_credentials.click();
			WrapperFunctionUtilities.jsClick(aws_credentials, driver);
			//CommonPageFunctions.hyperlinkClick(ExcelReader.getValue(sheetName,"S3_Role_AWS_Credentials","TestData"));
			WrapperFunctionUtilities.jsClick(CommonPageFunctions.hyperlink(testData.get("S3_Role_AWS_Credentials")),driver);
			CommonPageFunctions.TypeKey(AdaptorsPage.s3_i_am_role, ExcelReader.getValue(sheetName,"S3_Role","TestData"));

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}


	public static void createRedShiftAdaptorCredentialBased(String adaptorName, String sheetName) {
		try
		{
			HashMap<String,String> testData = ExcelReader.getTestDataFromExcel(sheetName,"TestData");
			WrapperFunctionUtilities.waitForTime(1000);
			WrapperFunctionUtilities.jsClick(rdbms_datasource,driver);
			adaptor_name.sendKeys(adaptorName);
			WrapperFunctionUtilities.jsClick(rdbms_type,driver);
			WrapperFunctionUtilities.jsClick(CommonPageFunctions.hyperlink(testData.get("RDBMS_Type")),driver);

			WrapperFunctionUtilities.jsClick(rdbms_authentication_type,driver);

			WrapperFunctionUtilities.jsClick(CommonPageFunctions.hyperlink(testData.get("Redshift_Authentication_Type_Credential_Based")),driver);
			CommonPageFunctions.TypeKey(AdaptorsPage.rdbms_hostname, testData.get("Redshift_Hostname"));
			CommonPageFunctions.TypeKey(AdaptorsPage.rdbms_username, testData.get("Redshift_Username"));
			CommonPageFunctions.TypeKey(AdaptorsPage.rdbms_password, testData.get("Redshift_Password"));

			CommonPageFunctions.TypeKey(AdaptorsPage.rdbms_portnumber, testData.get("Redshift_Port_Number"));
			AdaptorsPage.database_refresh.click();

			WrapperFunctionUtilities.jsClick(redshift_database,driver);
			WrapperFunctionUtilities.jsClick(CommonPageFunctions.hyperlink(testData.get("Redshift_Database")),driver);

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}


	public static void createRedShiftAdaptorUserImpersonation(String adaptorName, String sheetName) throws Exception {

		HashMap<String,String> testData = ExcelReader.getTestDataFromExcel(sheetName,"TestData");

		WrapperFunctionUtilities.waitForTime(1000);
		WrapperFunctionUtilities.jsClick(rdbms_datasource,driver);
		WrapperFunctionUtilities.waitForTime(2000);
		adaptor_name.sendKeys(adaptorName);
		WrapperFunctionUtilities.jsClick(rdbms_type,driver);
		WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(testData.get("RDBMS_Type")), driver);
		WrapperFunctionUtilities.jsClick(rdbms_authentication_type,driver);
		WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(testData.get("Redshift_Authentication_Type_User_Impersonation")), driver);
		CommonPageFunctions.TypeKey(AdaptorsPage.rdbms_hostname, testData.get("Redshift_Hostname"));
		CommonPageFunctions.TypeKey(AdaptorsPage.redshift_cluster_identifier, testData.get("Redshift_Cluster_Identifier"));
		WrapperFunctionUtilities.jsClick(redshift_region_name,driver);
		WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(testData.get("Redshift_Region_Name")), driver);
		CommonPageFunctions.TypeKey(AdaptorsPage.rdbms_portnumber, testData.get("Redshift_Port_Number"));
		AdaptorsPage.database_refresh.click();
		WrapperFunctionUtilities.jsClick(redshift_database,driver);
		WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(testData.get("Redshift_Database")), driver);
	}



	//This function verifies the Test connection for the adaptor
	public static boolean testConnection() {
		try {
			Log.info("Method : testConnection");
			WrapperFunctionUtilities.waitForTime(3000);
			test_connection_button.click();
			WrapperFunctionUtilities.waitForTime(1000);
			String connectionstatus = rdbms_connectionstatus.getText();
			boolean status = connectionstatus.contains("Connection successful.");
			Log.info("Status is "+status);
			WrapperFunctionUtilities.waitForTime(1000);
			ok.click();
			WrapperFunctionUtilities.waitForTime(1000);
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	// Create Postgres Adaptor
	public static String createPostgresAdaptor(String sheetName) throws Exception {
		String adaptorName = CommonPageFunctions.generateRandomString();
		//AdaptorsPage.rdbms_datasource.click();
		WrapperFunctionUtilities.jsClick(rdbms_datasource,driver);
		adaptor_name.sendKeys(adaptorName);
		AdaptorsPage.rdbms_type.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue(sheetName,"RDBMS_Postgres_Type","TestData"));
		CommonPageFunctions.TypeKey(AdaptorsPage.rdbms_hostname, ExcelReader.getValue(sheetName,"RDBMS_Postgres_HostName","TestData"));
		CommonPageFunctions.TypeKey(AdaptorsPage.rdbms_username, ExcelReader.getValue(sheetName,"RDBMS_Postgres_Username","TestData"));
		CommonPageFunctions.TypeKey(AdaptorsPage.rdbms_password, ExcelReader.getValue(sheetName,"RDBMS_Postgres_Password","TestData"));
		CommonPageFunctions.TypeKey(AdaptorsPage.rdbms_portnumber, ExcelReader.getValue(sheetName,"RDBMS_Postgres_Port_Number","TestData"));
		database_refresh.click();
		WrapperFunctionUtilities.waitForTime(3000);// it takes time to populate Database values in Dropdown
		adaptorPage_SelectDatabase_Dropdown.click();
		adaptorPage_SelectDatabase_TextBox.sendKeys(ExcelReader.getValue(sheetName,"RDBMS_Postgres_Database","TestData"));
		WrapperFunctionUtilities.jsClick(CommonPageFunctions.hyperlink(ExcelReader.getValue(sheetName,"RDBMS_Postgres_Database","TestData")),driver);
		return adaptorName;
	}

	public static void saveAdaptorSettings()
	{
		save.click();

	}

	public static void clickOnCreateNewAdaptorButton()
	{
		//adaptors_module.click();
		//WrapperFunctionUtilities.getURL(driver, "https://rdh-web.revo.qa.zsservices.com/rdhqa1//#/adaptors");
		create_new_adaptor.click();
	}

	public static void navigateToAdaptorsTab()
	{
		WrapperFunctionUtilities.waitForElementVisibility(CommonPage.DataMenuTabName("admin"),30,driver);
		CommonPage.DataMenuTabName("admin").click();
		WrapperFunctionUtilities.waitForElementVisibility(CommonPage.adminMenuSubTabName("Adaptors"),30,driver);
		CommonPage.adminMenuSubTabName("Adaptors").click();
	}

	public static void  updateRedshiftUserImpersonationAdaptorToCredentialBased(String adaptorName, String sheetName) throws Exception {
		HashMap<String,String> testData = ExcelReader.getTestDataFromExcel(sheetName,"TestData");
		adaptorPage_Edit_Icon(adaptorName).click();
		WrapperFunctionUtilities.jsClick(rdbms_authentication_type,driver);
		WrapperFunctionUtilities.jsClick(CommonPageFunctions.hyperlink(testData.get("Redshift_Authentication_Type_Credential_Based")),driver);
		CommonPageFunctions.TypeKey(AdaptorsPage.rdbms_username, testData.get("Redshift_Username"));
		CommonPageFunctions.TypeKey(AdaptorsPage.rdbms_password, testData.get("Redshift_Password"));
		Assert.assertTrue(testConnection());
		adaptorPage_Update_Button.click();

	}

	public static boolean verifyRedshiftUserImpersonationToCredentialBasedAdaptorChanges(String adaptorName){
		adaptorPage_Edit_Icon(adaptorName).click();
		return  WrapperFunctionUtilities.isElementPresent(adaptorPage_CredentialBasedSelection_dropdownValue, "Credential Based type is shown");
		}

	public static boolean deleteAdaptor(String adaptorName){
		Log.info("In Delete Adaptor");
		navigateToAdaptorsTab();
	    adaptorPage_Delete_Icon(adaptorName).click();
		adaptorPage_Delete_Button.click();
		return WrapperFunctionUtilities.isElementPresent(CommonPage.commonPage_delete_Message_Toaster(adaptorName),"Delete toaster message");
	}
}

