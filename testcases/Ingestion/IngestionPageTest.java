package com.RDM.Merger.testcases.Ingestion;

import com.RDM.Merger.locators.CommonPage;
import com.RDM.Merger.testcases.Aa_Login.ExcelReader;
import com.RDM.Merger.testcases.Aa_Login.LoginPageTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.RDM.Merger.locators.IngestionPage;
import com.RDM.Merger.pagefunctions.CommonPageFunctions;
import com.RDM.Merger.pagefunctions.IngestionPageFunctions;
import com.idm.pagefunctions.IDMLoginPageFunctions;
import com.idm.testcases.IDMLoginPageTest;
import com.utility.base.WrapperFunctionUtilities;
import com.utility.logger.Log;

public class IngestionPageTest extends IngestionPageFunctions {


	CommonPageFunctions common_page;
	IngestionPage ingestionPage;
	String ingestionID;
	boolean result;
	public IngestionPageTest()
	{
		super();
	}


	@BeforeMethod(groups = {"Smoke","Regression_Medium_Priority","Sanity","Regression High"})
	public void initialization(){
		ingestionPage = new IngestionPage();
		common_page = new CommonPageFunctions();
		CommonPageFunctions.navigateToURL(prop.getProperty("url"));
		WrapperFunctionUtilities.waitForPageToLoad(30, driver);
		//CommonPageFunctions.selectRDHEnvironmentIfDisplayed("Common","Environment");
	}

	@Test(groups = {"Smoke"})
	public void verifyIngestionPageNavigationSucessfull()
	{
			Log.info("Method : verifyIngestionPageNavigationSucessfull");
			common_page = new CommonPageFunctions();
			ingestionPage = new IngestionPage();
			//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
			WrapperFunctionUtilities.waitForTime(2000);
			CommonPageFunctions.selectDataMenuOptions("DataMenu", "ingestionTabName");
			WrapperFunctionUtilities.waitForTime(1000);
			boolean result = WrapperFunctionUtilities.isElementPresent(dataCenterHeader, "Data Center Page");
			Assert.assertTrue(result);
		}
	
	//Verify Full Local Ingestion with plain csv file with all data type - EMR
	@Test(groups = {"Sanity"})
	public void verifyLocalIngestionWithFullLoadTypeCSV()
	{
		try
		{
			Log.info("Method : verifyLocalIngestionWithFullLoadTypeCSV");
			//common_page = new CommonPageFunctions();
			//ingestionPage = new IngestionPage();
			//WrapperFunctionUtilities.waitForTime(2000);
			//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
			//WrapperFunctionUtilities.waitForTime(7000);
			IngestionPageFunctions.clickAddIngestion();
			//String ingestionID = CommonPageFunctions.generateRandomString();
			result = CommonPageFunctions.switchscreen("Step 1 of 3");
			Assert.assertEquals(result, true);
			/*ingestion_name.sendKeys(ingestionID);
			WrapperFunctionUtilities.waitForTime(1000);
			table_name.sendKeys(ingestionID);
			WrapperFunctionUtilities.waitForTime(1000);*/
			ingestionID = setUniqueIngestionIDAndTableName();
			selectLoadExistingType("Ingestion", "Local");
			IngestionPageFunctions.Define_Data_Source("CSV_FilePath");
			WrapperFunctionUtilities.waitForTime(3000);
			IngestionPageFunctions.Transactional_Info_CSV_File("CSV_FileFormat","CSV_FileType","FullLoadType","SkipHeader");
			result = CommonPageFunctions.switchscreen("Step 2 of 3");
			Assert.assertEquals(result, true);
			setSourceFormatDetails_For_AllDataTypeCSV();
			result = CommonPageFunctions.switchscreen("Step 3 of 3");
			Assert.assertEquals(result, true);
			IngestionPageFunctions.Ingestion_Location("Database","EMR","","");
			IngestionPageFunctions.clickFinishButton();
			WrapperFunctionUtilities.waitForTime(3000);
			boolean result = CommonPageFunctions.verifyProcess(ingestionID);
			Assert.assertEquals(result, true);
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}



	//Verify ingestion from local csv file with all datatypes to RedShift is successful ---EDGE-16951
	@Test(groups = {"Sanity"})
	public void verifyLocalIngestionWithFullLoadTypeCSVToRedshift() throws Exception{
			Log.info("Method : verifyLocalIngestionWithFullLoadTypeCSVToRedshift");
			IngestionPageFunctions.clickAddIngestion();
			ingestionID = setUniqueIngestionIDAndTableName();
			selectLoadExistingType("Ingestion", "Local");
			IngestionPageFunctions.Define_Data_Source("CSV_FilePath");
			WrapperFunctionUtilities.waitForTime(3000);
			IngestionPageFunctions.Transactional_Info_CSV_File("CSV_FileFormat","CSV_FileType","FullLoadType","SkipHeader");
			result = CommonPageFunctions.switchscreen("Step 2 of 3");
			Assert.assertEquals(result, true);
			setSourceFormatDetails_For_AllDataTypeCSV();
			result = CommonPageFunctions.switchscreen("Step 3 of 3");
			Assert.assertEquals(result, true);
			IngestionPageFunctions.Ingestion_Location("","Redshift","Redshift Adaptor Name","SchemaName");
			IngestionPageFunctions.clickFinishButton();
			WrapperFunctionUtilities.waitForTime(3000);
			boolean result = CommonPageFunctions.verifyProcess(ingestionID);
			Assert.assertEquals(result, true);
	}



	//Verify Full Local Ingestion with plain excel file with all data type - EMR --- EDGE-16922
	@Test(groups = {"Regression High"})
	public void verifyLocalIngestionWithFullLoadTypeExcel()
	{
		try
		{
			Log.info("Method : verifyLocalIngestionWithFullLoadTypeExcel");
			//common_page = new CommonPageFunctions();
			//WrapperFunctionUtilities.waitForTime(2000);
			//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
			IngestionPageFunctions.clickAddIngestion();
			//String ingestionID = CommonPageFunctions.generateRandomString();
			result = CommonPageFunctions.switchscreen("Step 1 of 3");
			Assert.assertEquals(result, true);
			//ingestion_name.sendKeys(ingestionID);
			//table_name.sendKeys(ingestionID);
			ingestionID = setUniqueIngestionIDAndTableName();
			selectLoadExistingType("Ingestion", "Local");
			IngestionPageFunctions.Define_Data_Source("Excel_FilePath");
			WrapperFunctionUtilities.waitForTime(2000);
			IngestionPageFunctions.Transactional_Info_Excel_File("Excel_FileFormat","FullLoadType","SheetName","SkipHeader");
			result = CommonPageFunctions.switchscreen("Step 2 of 3");
			Assert.assertEquals(result, true);
			waitForPreviewTableContentToLoad();
			result = CommonPageFunctions.switchscreen("Step 3 of 3");
			Assert.assertEquals(result, true);
			IngestionPageFunctions.Ingestion_Location("Database","EMR","","");
			IngestionPageFunctions.clickFinishButton();
			WrapperFunctionUtilities.waitForTime(3000);
			boolean result = CommonPageFunctions.verifyProcess(ingestionID);
			Assert.assertEquals(result, true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}



	//Verify on Home page Ingestion can be run instantly by clicking on run now button ---EDGE-16985
	@Test(groups = {"Regression High","Sanity"})
	public void verifyIngestionIsTriggeredUsingRunButton()
	{
		try
		{
			Log.info("Method : verifyIngestionIsTriggeredUsingRunButton");
			CommonPageFunctions.selectDataMenuOptions("DataMenu", "ingestionTabName");
			WrapperFunctionUtilities.waitForTime(2000);
			boolean status = runFirstSuccessfulIngestionOnHomePage();
			Assert.assertEquals(status, true,"Failed to run Ingestion");
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}



	//Verify Incremental ingestion if the cluster is EMRF ---EDGE-16939
	@Test(groups = {"Regression High","Sanity"})
	public void verifyLocalIngestionWithIncrementalLoadTypeExcel()
	{
		try
		{
			Log.info("Method : verifyLocalIngestionWithIncrementalLoadTypeExcel");
			//common_page = new CommonPageFunctions();
			//WrapperFunctionUtilities.waitForTime(2000);
			//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
			IngestionPageFunctions.clickAddIngestion();
			//String ingestionID = CommonPageFunctions.generateRandomString();
			result = CommonPageFunctions.switchscreen("Step 1 of 3");
			Assert.assertEquals(result, true);
			//ingestion_name.sendKeys(ingestionID);
			//table_name.sendKeys(ingestionID);
			ingestionID = setUniqueIngestionIDAndTableName();
			selectLoadExistingType("Ingestion", "Local");
			IngestionPageFunctions.Define_Data_Source("Excel_FilePath");
			IngestionPageFunctions.Transactional_Info_Excel_File("Excel_FileFormat","IncrementalLoadType","Sheet Name 2","SkipHeader");
			result = CommonPageFunctions.switchscreen("Step 2 of 3");
			setSourceFormatDetails_For_ExcelAllDataTypes_Sheet2();
			selectIncrementalJoinKeyColumn("Excel1_Sheet2_IncrementalJoinColumn");
			Assert.assertEquals(result, true);
			result = CommonPageFunctions.switchscreen("Step 3 of 3");
			Assert.assertEquals(result, true);
			IngestionPageFunctions.Ingestion_Location("Database","EMR","","");
			IngestionPageFunctions.clickFinishButton();
			WrapperFunctionUtilities.waitForTime(3000);
			boolean result = CommonPageFunctions.verifyProcess(ingestionID);
			Assert.assertEquals(result, true);
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	//RDH-2719	Verify S3 ingestion is successful with IAM role used in S3 adaptor

	@Test(groups = {"Regression High","Sanity"})
	public void verifyS3IngestionWithIAMRoleAdaptor() throws Exception
	{
		Log.info("Method : verifyS3IngestionWithIAMRoleAdaptor");
		IngestionPageFunctions.clickAddIngestion();
		ingestionID = setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptorUsingSearch("Ingestion", "S3IAMRoleAdaptor");
		Transactional_Info_S3_Excel("Excel File Format","FullLoadType", "ExcelFile1_FilePathS3", "Sheet Name","SkipHeader");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertEquals(result, true);
		waitForPreviewTableContentToLoad();
		result = CommonPageFunctions.switchscreen("Step 3 of 3");
		Assert.assertEquals(result, true);
		IngestionPageFunctions.Ingestion_Location("Database","EMR","","");
		IngestionPageFunctions.clickFinishButton();
		WrapperFunctionUtilities.waitForTime(2000);
		boolean result = CommonPageFunctions.verifyProcess(ingestionID);
		Assert.assertEquals(result, true);
	}

//RDH-2719	Verify S3 ingestion is successful with IAM role used in S3 adaptor

	@Test(groups = {"Regression High","Sanity"})
	public void verifyMSSQLIngestionFullLoadType() throws Exception
	{
		Log.info("Method : verifyMSSQLIngestionFullLoadType");
		IngestionPageFunctions.clickAddIngestion();
		ingestionID = setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptor("Ingestion", "MSSQL Adaptor Name 2");
		Transactional_Info_RDBMS_Adaptor("FullLoadType","MSSQL_Table_BigData");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		waitForPreviewTableContentToLoad();
		Assert.assertEquals(result, true);
		result = CommonPageFunctions.switchscreen("Step 3 of 3");
		Assert.assertEquals(result, true);
		IngestionPageFunctions.Ingestion_Location("Database","EMR","","");
		IngestionPageFunctions.clickFinishButton();
		WrapperFunctionUtilities.waitForPageToLoad(30,driver);
		boolean result = CommonPageFunctions.verifyProcess(ingestionID);
		Assert.assertEquals(result, true);

	}
//EDGE-16988	iData smart run - Verify idata Smart run analysis is successful for multiple parquet files

	@Test(groups = {"Regression_Medium_Priority"})
	public void verifyiDataRunWithMultipleParquetfiles() throws Exception
	{
		Log.info("Method : verifyiDataRunWithMultipleParquetfiles");
		//common_page = new CommonPageFunctions();
		//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
		//WrapperFunctionUtilities.waitForTime(1000);
		IngestionPageFunctions.clickAddIngestion();
		//String ingestionID = CommonPageFunctions.generateRandomString();
		result = CommonPageFunctions.switchscreen("Step 1 of 3");
		Assert.assertEquals(result, true);
		//ingestion_name.sendKeys(ingestionID);
		//table_name.sendKeys(ingestionID);
		ingestionID = setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptor("Ingestion", "S3IAMAdaptor");
		Transactional_Info_ParquetFile_S3("Parquet File Format","FullLoadType","Multiple Parquet Files Location","SkipHeader");
		runiData("iData Domain");
		WrapperFunctionUtilities.waitForTime(3000);
		dataCenterLink.click();
		WrapperFunctionUtilities.waitForTime(1000);
		boolean result = CommonPageFunctions.verifyProcess(ingestionID);
		Assert.assertEquals(result, true);
	}
// EDGE-17024	Verify idata run Analysis at step 2 is successful when Redshift adaptor is selected at step 1
    @Test(groups = {"Regression_Medium_Priority"})
	public void verifyiDataRunWithRedshiftAdaptor() throws Exception
	{
		Log.info("Method : verifyiDataRunWithRedshiftAdaptor");
		//common_page = new CommonPageFunctions();
		//ingestionPage = new IngestionPage();
		//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
		//WrapperFunctionUtilities.waitForTime(1000);
		IngestionPageFunctions.clickAddIngestion();
		//String ingestionID = CommonPageFunctions.generateRandomString();
		result = CommonPageFunctions.switchscreen("Step 1 of 3");
		Assert.assertEquals(result, true);
		//ingestion_name.sendKeys(ingestionID);
		//table_name.sendKeys(ingestionID);
		ingestionID = setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptor("Ingestion", "Redshift Adaptor");
		Transactional_Info_RDBMS_Adaptor("FullLoadType","Redshift Table");
		runiData("iData Domain");
		WrapperFunctionUtilities.waitForTime(3000);
		dataCenterLink.click();
		WrapperFunctionUtilities.waitForTime(1000);
		boolean result = CommonPageFunctions.verifyProcess(ingestionID);
		Assert.assertEquals(result, true);
	}

	// EDGE-17019	Verify idata run Analysis at step 2 is successful when Oracle adaptor is selected at step 1
	@Test(groups = {"Regression_Medium_Priority"})
	public void verifyiDataRunWithOracleAdaptor() throws Exception
	{
		Log.info("Method : verifyiDataRunWithOracleAdaptor");
		//common_page = new CommonPageFunctions();
		//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
		//WrapperFunctionUtilities.waitForTime(1000);
		IngestionPageFunctions.clickAddIngestion();
		//String ingestionID = CommonPageFunctions.generateRandomString();
		result = CommonPageFunctions.switchscreen("Step 1 of 3");
		Assert.assertEquals(result, true);
		//ingestion_name.sendKeys(ingestionID);
		//table_name.sendKeys(ingestionID);
		ingestionID = setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptor("Ingestion", "Oracle Automation Adaptor");
		Transactional_Info_RDBMS_Adaptor("FullLoadType","Oracle_Table_Name1");
		runiData("iData Domain");
		WrapperFunctionUtilities.waitForTime(3000);
		dataCenterLink.click();
		WrapperFunctionUtilities.waitForTime(1000);
		boolean result = CommonPageFunctions.verifyProcess(ingestionID);
		Assert.assertEquals(result, true);
	}

	 // EDGE-17020	Verify idata run Analysis at step 2 is successful when Postgres adaptor is selected at step 1
	 @Test(groups = {"Regression_Medium_Priority"})
	 public void verifyiDataRunWithPostgresAdaptor() throws Exception
	 {
		 Log.info("Method : verifyiDataRunWithPostgresAdaptor");
		 //common_page = new CommonPageFunctions();
		// CommonPageFunctions.selectRDHEnvironment("Common","Environment");
		 WrapperFunctionUtilities.waitForTime(1000);
		 IngestionPageFunctions.clickAddIngestion();
		 //String ingestionID = CommonPageFunctions.generateRandomString();
		 result = CommonPageFunctions.switchscreen("Step 1 of 3");
		 Assert.assertEquals(result, true);
		 //ingestion_name.sendKeys(ingestionID);
		 //table_name.sendKeys(ingestionID);
		 ingestionID = setUniqueIngestionIDAndTableName();
		 selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		 selectExistingAdaptor("Ingestion", "Postgres Adaptor");
		 Transactional_Info_RDBMS_Adaptor("FullLoadType","Postgres Table");
		 runiData("iData Domain");
		 WrapperFunctionUtilities.waitForTime(3000);
		 dataCenterLink.click();
		 WrapperFunctionUtilities.waitForTime(1000);
		 boolean result = CommonPageFunctions.verifyProcess(ingestionID);
		 Assert.assertEquals(result, true);
	 }
	@Test(groups = {"Regression_Medium_Priority"})
	// EDGE-17022	Verify idata run Analysis at step 2 is successful when MSSQL adaptor is selected at step 1
	public void verifyiDataRunWithMSSQLAdaptor() throws Exception
	{
		Log.info("Method : verifyiDataRunWithMSSQLAdaptor");
		//common_page = new CommonPageFunctions();
		//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
		IngestionPageFunctions.clickAddIngestion();
		//String ingestionID = CommonPageFunctions.generateRandomString();
		result = CommonPageFunctions.switchscreen("Step 1 of 3");
		Assert.assertEquals(result, true);
		//ingestion_name.sendKeys(ingestionID);
		//table_name.sendKeys(ingestionID);
		ingestionID = setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptor("Ingestion", "MSSQL Adaptor Name 2");
		Transactional_Info_RDBMS_Adaptor("FullLoadType","MSSQL_Table_Name2");
		runiData("iData Domain");
		WrapperFunctionUtilities.waitForTime(3000);
		dataCenterLink.click();
		WrapperFunctionUtilities.waitForTime(1000);
		boolean result = CommonPageFunctions.verifyProcess(ingestionID);
		Assert.assertEquals(result, true);
	}

	// EDGE-17032	Verify at step1 of ingestion after checking the checkbox for Run Smart data Analysis, drop down   has all the domain configured
	@Test(groups = {"Regression_Medium_Priority"})
	public void verifyiDataDomainsfromDropdown() throws Exception{
		Log.info("Method : verifyiDataDomainsfromDropdown");
		//common_page = new CommonPageFunctions();
		//ingestionPage = new IngestionPage();
		//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
		IngestionPageFunctions.clickAddIngestion();
		//String ingestionID = CommonPageFunctions.generateRandomString();
		result = CommonPageFunctions.switchscreen("Step 1 of 3");
		Assert.assertEquals(result, true);
		Assert.assertTrue(verifyiDataDomains("Ingestion","iDataDomains"));
	}
	// EDGE-16956	Verify user cannot specify special characters and space for table name at step 1 of Ingestion
	@Test(groups = {"Regression_Medium_Priority"})
	public void verifyValidationOnSpecialCharactersInTableName() throws Exception {
		Log.info("Method : verifyValidationOnSpecialCharactersInTableName");
		//common_page = new CommonPageFunctions();
		//CommonPageFunctions.selectRDHEnvironment("Common", "Environment");
		//WrapperFunctionUtilities.waitForTime(1000);
		IngestionPageFunctions.clickAddIngestion();
		setTableName(ExcelReader.getValue("Ingestion","Table Name with Special Characters","TestData"));
		//table_name.sendKeys(ExcelReader.getValue("Ingestion","Table Name with Special Characters","TestData"));
		Assert.assertTrue(verifyErrorMessage("Error message Invalid Table Name"));
	}

	/* Verify CSV with tab/Space delimited  		-- EDGE-16934 */
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyCSVIngestionWithTab_SpaceDelimited() {
        try {
            Log.info("Method : verifyCSVIngestionWithTab_SpaceDelimited");
            //common_page = new CommonPageFunctions();
            //ingestionPage = new IngestionPage();
            //CommonPageFunctions.selectRDHEnvironment("Common","Environment");
            IngestionPageFunctions.clickAddIngestion();
			ingestionID = setUniqueIngestionIDAndTableName();
            selectLoadExistingType("Ingestion", "Local");
            IngestionPageFunctions.Define_Data_Source("TabSeparatedCSV_Filepath");
            IngestionPageFunctions.Transactional_Info_Tab_Separated_CSV_File("CSV_FileFormat", "CSV_FileType", "FullLoadType", "SkipHeader");
            result = CommonPageFunctions.switchscreen("Step 2 of 3");
            Assert.assertEquals(result, true);
            setSourceFormatDetails_For_TabSeparatedCSV();
            result = CommonPageFunctions.switchscreen("Step 3 of 3");
            Assert.assertEquals(result, true);
            IngestionPageFunctions.Ingestion_Location("Database", "EMR", "", "");
            IngestionPageFunctions.clickFinishButton();
            WrapperFunctionUtilities.waitForPageToLoad(5, driver);
            boolean result = CommonPageFunctions.verifyProcess(ingestionID);
            Assert.assertEquals(result, true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    /* Verify Excel file containing Multiple Sheet and Ingesting data from 2nd sheet  -- EDGE-16936 */
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyExcelDataIngestionFrom2ndSheet() {
        try {
            Log.info("Method : verifyExcelDataIngestionFrom2ndSheet");
            //common_page = new CommonPageFunctions();
            //ingestionPage = new IngestionPage();
            //CommonPageFunctions.selectRDHEnvironment("Common","Environment");
            IngestionPageFunctions.clickAddIngestion();
			ingestionID = setUniqueIngestionIDAndTableName();
            selectLoadExistingType("Ingestion", "Local");
            IngestionPageFunctions.Define_Data_Source("Excel_FilePath");
            IngestionPageFunctions.Transactional_Info_Excel_File("Excel_FileFormat", "FullLoadType", "Sheet Name 2", "SkipHeader");
            result = CommonPageFunctions.switchscreen("Step 2 of 3");
            Assert.assertEquals(result, true);
            result = verifySourceColumnHeadersinDataSchemaTable("Sheet Name 2", "Excel File Name");
            Assert.assertEquals(result, true);
            setSourceFormatDetails_For_ExcelAllDataTypes_Sheet2();
            result = CommonPageFunctions.switchscreen("Step 3 of 3");
            Assert.assertEquals(result, true);
            IngestionPageFunctions.Ingestion_Location("Database", "EMR", "", "");
            IngestionPageFunctions.clickFinishButton();
            WrapperFunctionUtilities.waitForTime(3000);
            boolean result = CommonPageFunctions.verifyProcess(ingestionID);
            Assert.assertEquals(result, true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    /* Verify Incremental Parquet Ingestion is successful EDGE-16943 */
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyIncrementalParquetIngestion() {
        try {
            Log.info("Method : verifyIncrementalParquetIngestion");
            //common_page = new CommonPageFunctions();
            //ingestionPage = new IngestionPage();
            //CommonPageFunctions.selectRDHEnvironment("Common","Environment");
            IngestionPageFunctions.clickAddIngestion();
			ingestionID = setUniqueIngestionIDAndTableName();
            selectLoadExistingType("Ingestion", "Local");
            IngestionPageFunctions.Define_Data_Source("Parquet File Path");
            IngestionPageFunctions.Transactional_Info_ParquetFile("Parquet_FileFormat", "IncrementalLoadType", "SkipHeader");
            result = CommonPageFunctions.switchscreen("Step 2 of 3");
            Assert.assertEquals(result, true);
            setSourceFormatDetails_For_LocalParquetFile();
            selectIncrementalJoinKeyColumn("ParquetIncrementalJoinColumn");
            result = CommonPageFunctions.switchscreen("Step 3 of 3");
            Assert.assertEquals(result, true);
            IngestionPageFunctions.Ingestion_Location("Database", "EMR", "", "");
            IngestionPageFunctions.clickFinishButton();
            WrapperFunctionUtilities.waitForTime(3000);
            boolean result = CommonPageFunctions.verifyProcess(ingestionID);
            Assert.assertEquals(result, true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    /* Verify Incremental Ingestion using CSV file with Pipe separated 		-- EDGE-16929 */
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyIncrementalIngestionusingCSVFilewithPipeseparated() {
        try {
            Log.info("Method : verifyIncrementalIngestionusingCSVFilewithPipeseparated");
            //common_page = new CommonPageFunctions();
            // 	ingestionPage = new IngestionPage();
            /*
             * CommonPageFunctions.selectRDHEnvironment("Common","Environment");
             * WrapperFunctionUtilities.waitForTime(7000);
             */
            IngestionPageFunctions.clickAddIngestion();
			ingestionID = setUniqueIngestionIDAndTableName();
            selectLoadExistingType("Ingestion", "Local");
            IngestionPageFunctions.Define_Data_Source("PipeSeparatedCSV_Filepath");
            IngestionPageFunctions.Transactional_Info_CSV_File_With_Delimiter("CSV_FileFormat", "CSV_FileType", "|", "IncrementalLoadType", "SkipHeader");
            result = CommonPageFunctions.switchscreen("Step 2 of 3");
            Assert.assertEquals(result, true);
            setSourceFormatDetails_For_PipeSeparatedCSV();
            selectIncrementalJoinKeyColumn("PipeSeparatedIncrementalJoinColumn");
            result = CommonPageFunctions.switchscreen("Step 3 of 3");
            Assert.assertEquals(result, true);
            IngestionPageFunctions.Ingestion_Location("Database", "EMR", "", "");
            IngestionPageFunctions.clickFinishButton();
            WrapperFunctionUtilities.waitForTime(3000);
            boolean result = CommonPageFunctions.verifyProcess(ingestionID);
            Assert.assertEquals(result, true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    /* Verify Parquet file with all data type EDGE-16947 */
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyParquetIngestionWithAllDataType() {
        try {
            Log.info("Method : verifyParquetIngestionWithAllDataType");
            //common_page = new CommonPageFunctions();
            //ingestionPage = new IngestionPage();
            /*
             * CommonPageFunctions.selectRDHEnvironment("Common","Environment");
             * WrapperFunctionUtilities.waitForTime(6000);
             */
            IngestionPageFunctions.clickAddIngestion();
			ingestionID = setUniqueIngestionIDAndTableName();
            selectLoadExistingType("Ingestion", "Local");
            IngestionPageFunctions.Define_Data_Source("Parquet File Path");
            IngestionPageFunctions.Transactional_Info_ParquetFile("Parquet_FileFormat", "FullLoadType", "SkipHeader");
            result = CommonPageFunctions.switchscreen("Step 2 of 3");
            Assert.assertEquals(result, true);
            selectTypeForSourceColumn_For_AllDataTypeParquetFile();
            setSourceFormatDetails_For_AllDataTypeParquetFile();
            result = CommonPageFunctions.switchscreen("Step 3 of 3");
            Assert.assertEquals(result, true);
            IngestionPageFunctions.Ingestion_Location("Database", "EMR", "", "");
            IngestionPageFunctions.clickFinishButton();
            WrapperFunctionUtilities.waitForTime(3000);
            boolean result = CommonPageFunctions.verifyProcess(ingestionID);
            Assert.assertEquals(result, true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }


    /* Verify plain .txt file with all data type EDGE-17607 */
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyPlainTextFileIngestionWithAllDataType() {
        try {
            Log.info("Method : verifyPlainTextFileIngestionWithAllDataType");
            //common_page = new CommonPageFunctions();
            //ingestionPage = new IngestionPage();
            /*
             * CommonPageFunctions.selectRDHEnvironment("Common","Environment");
             * WrapperFunctionUtilities.waitForTime(7000);
             *
             */
            IngestionPageFunctions.clickAddIngestion();
			ingestionID = setUniqueIngestionIDAndTableName();
            selectLoadExistingType("Ingestion", "Local");
            IngestionPageFunctions.Define_Data_Source("AllDataTextFile_Filepath");
            IngestionPageFunctions.Transactional_Info_CSV_File_With_Delimiter("CSV_FileFormat", "CSV_FileType", "\\t", "FullLoadType", "SkipHeader");
            result = CommonPageFunctions.switchscreen("Step 2 of 3");
            Assert.assertEquals(result, true);
            selectTypeForSourceColumn_For_AllDataTextFile();
            setSourceFormatDetails_For_AllDataTextFile();
            result = CommonPageFunctions.switchscreen("Step 3 of 3");
            Assert.assertEquals(result, true);
            IngestionPageFunctions.Ingestion_Location("Database", "EMR", "", "");
            IngestionPageFunctions.clickFinishButton();
            WrapperFunctionUtilities.waitForTime(3000);
            boolean result = CommonPageFunctions.verifyProcess(ingestionID);
            Assert.assertEquals(result, true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    /* Verify file with limited column -- EDGE-17608*/
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyFileIngestionWithLimitedColumn() {
        try {

            Log.info("Method : verifyFileIngestionWithLimitedColumn");
            //common_page = new CommonPageFunctions();
            //ingestionPage = new IngestionPage();
            /*
             * CommonPageFunctions.selectRDHEnvironment("Common","Environment");
             * WrapperFunctionUtilities.waitForTime(7000);
             */
            IngestionPageFunctions.clickAddIngestion();
			ingestionID = setUniqueIngestionIDAndTableName();
            selectLoadExistingType("Ingestion", "Local");
            IngestionPageFunctions.Define_Data_Source("TabSeparatedCSV_Filepath");
            IngestionPageFunctions.Transactional_Info_Tab_Separated_CSV_File("CSV_FileFormat", "CSV_FileType", "FullLoadType", "SkipHeader");
            result = CommonPageFunctions.switchscreen("Step 2 of 3");
            Assert.assertEquals(result, true);

            deselectSourceColumnFromIngestion_For_TabSeparatedCSV();

            setSourceFormatDetails(ExcelReader.getValue("TabSeparatedCSV", "sourceColumnName_3", "IngestionData"),
                    ExcelReader.getValue("TabSeparatedCSV", "sourceColumnFormat_3", "IngestionData"));

            result = CommonPageFunctions.switchscreen("Step 3 of 3");
            Assert.assertEquals(result, true);
            IngestionPageFunctions.Ingestion_Location("Database", "EMR", "", "");
            IngestionPageFunctions.clickFinishButton();
            WrapperFunctionUtilities.waitForPageToLoad(5, driver);
            boolean result = CommonPageFunctions.verifyProcess(ingestionID);
            Assert.assertEquals(result, true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    /* Verify error message is displayed if user uploads file with size greater than 100MB -- EDGE-16926 */
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyErrorMessageForFileIngestionWithGreaterFileSize() {
        try {
            Log.info("Method : verifyCSVIngestionWithTab_SpaceDelimited");
            //common_page = new CommonPageFunctions();
            //ingestionPage = new IngestionPage();
            IngestionPageFunctions.clickAddIngestion();
			ingestionID = setUniqueIngestionIDAndTableName();
            selectLoadExistingType("Ingestion", "Local");
            result = verifyErrorMessageWhenFileIngestionIsOfGreaterSize("BigTestData_FilePath");
            Assert.assertEquals(result, true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    /* Verify Excel file containing UTF8 and UTF 16 character -- EDGE-16921*/
    @Test(groups = {"Regression_Medium_Priority"})
    public void VerifyExcelFileIngestionContainingUTF8() {
        try {
            Log.info("Method : VerifyExcelFileIngestionContainingUTF8");
            //common_page = new CommonPageFunctions();
            //ingestionPage = new IngestionPage();
            /*
             * CommonPageFunctions.selectRDHEnvironment("Common","Environment");
             * WrapperFunctionUtilities.waitForTime(7000);
             */
            IngestionPageFunctions.clickAddIngestion();
			ingestionID = setUniqueIngestionIDAndTableName();
            selectLoadExistingType("Ingestion", "Local");
            IngestionPageFunctions.Define_Data_Source("ExcelWithUTF8_Filepath");
            IngestionPageFunctions.Transactional_Info_Excel_File("Excel_FileFormat", "FullLoadType", "ExcelWithUTF8_SheetName", "SkipHeader");
            result = CommonPageFunctions.switchscreen("Step 2 of 3");
            Assert.assertEquals(result, true);
			waitForPreviewTableContentToLoad();
            result = CommonPageFunctions.switchscreen("Step 3 of 3");
            Assert.assertEquals(result, true);
            IngestionPageFunctions.Ingestion_Location("Database", "EMR", "", "");
            IngestionPageFunctions.clickFinishButton();
            WrapperFunctionUtilities.waitForPageToLoad(5, driver);
            boolean result = CommonPageFunctions.verifyProcess(ingestionID);
            Assert.assertEquals(result, true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    /* Verify multiple Excel file Ingestion -- EDGE-16931 */

    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyMultipleExcelFilesIngestion() {
        try {
            Log.info("Method : verifyMultipleExcelFilesIngestion");
            //common_page = new CommonPageFunctions();
            //ingestionPage = new IngestionPage();
            /*
             * CommonPageFunctions.selectRDHEnvironment("Common", "Environment");
             * WrapperFunctionUtilities.waitForTime(7000);
             */
            IngestionPageFunctions.clickAddIngestion();
			ingestionID = setUniqueIngestionIDAndTableName();
            selectLoadExistingType("Ingestion", "Load Existing Adaptor");
            selectExistingAdaptor("Ingestion", "S3IAMAdaptor");
            Transactional_Info_S3_Excel("Excel File Format", "FullLoadType", "MultipleExcel_FilePathS3",
                    "Sheet Name", "SkipHeader");
            result = CommonPageFunctions.switchscreen("Step 2 of 3");
            Assert.assertEquals(result, true);
			waitForPreviewTableContentToLoad();
            result = CommonPageFunctions.switchscreen("Step 3 of 3");
            Assert.assertEquals(result, true);
            IngestionPageFunctions.Ingestion_Location("Database", "EMR", "", "");
            IngestionPageFunctions.clickFinishButton();
            WrapperFunctionUtilities.waitForPageToLoad(5, driver);
            boolean result = CommonPageFunctions.verifyProcess(ingestionID);
            Assert.assertEquals(result, true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    /* Verify multiple CSV file Ingestion  		-- EDGE-16932 */
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyMultipleCSVFilesIngestion() {
        try {
            Log.info("Method : verifyMultipleCSVFilesIngestion");
            //common_page = new CommonPageFunctions();
            //ingestionPage = new IngestionPage();
            /*
             *
             * CommonPageFunctions.selectRDHEnvironment("Common","Environment");
             * WrapperFunctionUtilities.waitForTime(7000);
             *
             */
            IngestionPageFunctions.clickAddIngestion();
			ingestionID = setUniqueIngestionIDAndTableName();
            selectLoadExistingType("Ingestion", "Load Existing Adaptor");
            selectExistingAdaptor("Ingestion", "S3IAMAdaptor");
            Transactional_Info_S3_CSV("CSV_FileFormat", "CSV_FileType", "FullLoadType", "Multiple CSV Source FilePath S3", "\\t", "SkipHeader");
            result = CommonPageFunctions.switchscreen("Step 2 of 3");
            Assert.assertEquals(result, true);
            setSourceFormatDetails_For_TabSeparatedCSV();
            result = CommonPageFunctions.switchscreen("Step 3 of 3");
            Assert.assertEquals(result, true);
            IngestionPageFunctions.Ingestion_Location("Database", "EMR", "", "");
            IngestionPageFunctions.clickFinishButton();
            WrapperFunctionUtilities.waitForPageToLoad(5, driver);
            boolean result = CommonPageFunctions.verifyProcess(ingestionID);
            Assert.assertEquals(result, true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    /*
     * Verify Cancel button at the below right side of the page and after clicking
     * on cancel user navigate back to home page -- EDGE-16886
     */
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyCancelButtonFunctionalityOnIngestion() {
        try {
            Log.info("Method : verifyCancelButtonFunctionalityOnIngestion");
            //common_page = new CommonPageFunctions();
            //ingestionPage = new IngestionPage();
            // CommonPageFunctions.selectRDHEnvironment("Common","Environment");
            IngestionPageFunctions.clickAddIngestion();
            result = clickCancelButton();
            Assert.assertEquals(result, true);
            IngestionPageFunctions.clickAddIngestion();
			ingestionID = setUniqueIngestionIDAndTableName();
            selectLoadExistingType("Ingestion", "Local");
            IngestionPageFunctions.Define_Data_Source("TabSeparatedCSV_Filepath");
            IngestionPageFunctions.Transactional_Info_Tab_Separated_CSV_File("CSV_FileFormat", "CSV_FileType",
                    "FullLoadType", "SkipHeader");
            result = CommonPageFunctions.switchscreen("Step 2 of 3");
            Assert.assertEquals(result, true);
            result = clickCancelButton();
            Assert.assertEquals(result, true);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    /*
     * Verify source format text box should only editable in case of timestamp and
       decimal column -- EDGE-16898
     */
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifySourceFormatTextboxEditability() {
        try {
            Log.info("Method : verifySourceFormatTextboxEditability");
            //common_page = new CommonPageFunctions();
            //ingestionPage = new IngestionPage();
            // CommonPageFunctions.selectRDHEnvironment("Common", "Environment");
            IngestionPageFunctions.clickAddIngestion();
			ingestionID = setUniqueIngestionIDAndTableName();
            selectLoadExistingType("Ingestion", "Local");
            IngestionPageFunctions.Define_Data_Source("AllDataTextFile_Filepath");
            IngestionPageFunctions.Transactional_Info_CSV_File_With_Delimiter("CSV_FileFormat", "CSV_FileType", "\\t", "FullLoadType", "SkipHeader");
            result = CommonPageFunctions.switchscreen("Step 2 of 3");
            Assert.assertEquals(result, true);
            result = verifySourceFormatTextboxEnability("sourceColumnName_5");
            Assert.assertEquals(result, true);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    /* Verify is user select file format as Text File, under Delimter drop down two values Delimeter
        and Fixed width and after selecting delimeter text box to enter the delimeter should get
        enabled	 -- EDGE-16889*/
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyDelimeterTextBoxEnabilityForTextFileType(){
        try {
            Log.info("Method : verifyDelimeterTextBoxEnabilityForTextFileType");
            //common_page = new CommonPageFunctions();
            //   ingestionPage = new IngestionPage();
            //  CommonPageFunctions.selectRDHEnvironment("Common", "Environment");
            IngestionPageFunctions.clickAddIngestion();
            result = verifyFileTypeDropdownValuesForTextFile();
            Assert.assertEquals(result,true,"Failed to verify Delimeter Textbox");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    /* Verify user cannot specify special characters and space for table name at step 1 of Ingestion
    -- EDGE-16956*/
    @Test(groups = {"Regression_Medium_Priority"})
    public void VerifyUserCannotSpecifySpecialCharactersAndSpaceForTableNameOfIngestion()throws Exception{
        Log.info("Method : VerifyUserCannotSpecifySpecialCharactersAndSpaceForTableNameOfIngestion");
        //common_page = new CommonPageFunctions();
        //ingestionPage = new IngestionPage();
        //CommonPageFunctions.selectRDHEnvironment("Common", "Environment");
        IngestionPageFunctions.clickAddIngestion();
        String ingestionID = CommonPageFunctions.generateRandomString();
        setIngestionID(ingestionID);
        setTableName(ExcelReader.getValue("Ingestion","Table Name with Special Characters","TestData"));
        Assert.assertTrue(verifyErrorMessage("Error message Invalid Table Name"),"Invalid Table Name error message is not displayed");
       /* String ingestionID = CommonPageFunctions.generateRandomString();
        setIngestionID(ingestionID);
        StringBuilder sbtemp = new StringBuilder(ingestionID);
        sbtemp.insert(3,"~!@#$%^");
        String ingestionIDtemp = sbtemp.toString();
        setTableName(ingestionIDtemp);
        result = verifyInvalidTableNamErrorMessage();
        Assert.assertEquals(result,true,"Invalid Table Name error message is not displayed");
        sbtemp = new StringBuilder(ingestionID);
        WrapperFunctionUtilities.refreshPageWithPageLoadTimeout(driver);
        ingestionIDtemp = sbtemp.insert(3," ").toString();
        setIngestionID(ingestionID);
        setTableName(ingestionIDtemp);*/
        selectLoadExistingType("Ingestion", "Local");
        IngestionPageFunctions.Define_Data_Source("TabSeparatedCSV_Filepath");
        IngestionPageFunctions.Transactional_Info_Tab_Separated_CSV_File("CSV_FileFormat", "CSV_FileType", "FullLoadType", "SkipHeader");
        result = verifyStepNumberVisibilityAfterClick("Step 1 of 3");
        Assert.assertEquals(result,true,"Successfully navigated to Preview page with invalid table Name");
    }

    /* Verify after clicking on the check box for Schedule recurring Ingestion user can
    see the option start date, Recurrence Pattern, Recur day and Range of Recurrence
    	 -- EDGE-16905*/
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyScheduleRecurringIngestionCheckboxFeature(){
        try {
            Log.info("Method : verifyScheduleRecurringIngestionCheckboxFeature");
            //common_page = new CommonPageFunctions();
            //  ingestionPage = new IngestionPage();
            // CommonPageFunctions.selectRDHEnvironment("Common", "Environment");
            IngestionPageFunctions.clickAddIngestion();
			ingestionID = setUniqueIngestionIDAndTableName();
            selectLoadExistingType("Ingestion", "Local");
            IngestionPageFunctions.Define_Data_Source("TabSeparatedCSV_Filepath");
            IngestionPageFunctions.Transactional_Info_Tab_Separated_CSV_File("CSV_FileFormat", "CSV_FileType", "FullLoadType", "SkipHeader");
            result = CommonPageFunctions.switchscreen("Step 2 of 3");
            Assert.assertEquals(result, true);
            setSourceFormatDetails_For_TabSeparatedCSV();
            result = CommonPageFunctions.switchscreen("Step 3 of 3");
            Assert.assertEquals(result, true);
            IngestionPageFunctions.Ingestion_Location("Database", "EMR", "", "");
            result= verifyScheduleRecurringIngestionCheckbox();
            Assert.assertEquals(result, true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    /* Verify excel preview is correct for any valid numeric value of skip header mentioned  -- EDGE-16858
     * Verify preview excel file having multiple sheets and sheet name specified  -- EDGE-16854
     * Verify of excel file is shown for both .xls and .xlsx format -- EDGE-16852 (Covered XLSX verification under it) */
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyExcelPreviewForValidNumericSkipHeaderValue() {
        try {
            Log.info("Method : verifyExcelPreviewForValidNumericSkipHeaderValue");
            /*common_page = new CommonPageFunctions();
            ingestionPage = new IngestionPage();
            CommonPageFunctions.selectRDHEnvironment("Common","Environment");*/
            IngestionPageFunctions.clickAddIngestion();
			ingestionID = setUniqueIngestionIDAndTableName();
            selectLoadExistingType("Ingestion", "Local");
            IngestionPageFunctions.Define_Data_Source("Excel_FilePath_2");
            IngestionPageFunctions.Transactional_Info_Excel_File("Excel_FileFormat", "FullLoadType", "Sheet Name 2", "SkipHeader_2");
            result = CommonPageFunctions.switchscreen("Step 2 of 3");
			Assert.assertTrue(result);
            result = verifySourceColumnHeadersInPreviewTable("Sheet Name 2", "Excel File Name 2","SkipHeader_2","Excel_XLSX_Format");
			Assert.assertTrue(result);
			result = verifySourceDataInPreviewTable("Sheet Name 2", "Excel File Name 2","RowNum3","Excel_XLSX_Format");
			Assert.assertTrue(result);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    //Verify Redshift ingestion with fixed width type -- EDGE-16955
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyRedshiftIngestionWithFixedWidthType() throws Exception{
        Log.info("Method : verifyRedshiftIngestionWithFixedWidthType");
        common_page = new CommonPageFunctions();
        // ingestionPage = new IngestionPage();
        //CommonPageFunctions.selectRDHEnvironment("Common","Environment");
        IngestionPageFunctions.clickAddIngestion();
		ingestionID = setUniqueIngestionIDAndTableName();
        selectLoadExistingType("Ingestion", "Local");
        IngestionPageFunctions.Define_Data_Source("FixedWidth_FilePath");
        IngestionPageFunctions.Transactional_Info_CSV_File_Fixed_Width("CSV_FileFormat", "FileType_FixedWidth", "FullLoadType", "SkipHeader");
        result = CommonPageFunctions.switchscreen("Step 2 of 3");
        Assert.assertEquals(result, true);
        result = CommonPageFunctions.switchscreen("Step 3 of 3");
        Assert.assertEquals(result, true);
        IngestionPageFunctions.Ingestion_Location("", "Redshift", "Redshift Adaptor Name","SchemaName");
        IngestionPageFunctions.clickFinishButton();
        WrapperFunctionUtilities.waitForTime(3000);
        boolean result = CommonPageFunctions.verifyProcess(ingestionID);
        Assert.assertEquals(result, true);
    }


    /* Verify Preview is not shown for file with size greater than 10 MB  		-- EDGE-16851 */
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyPreviewNotShownForFileWithSizeGreaterThan15MB() throws Exception{

        Log.info("Method : verifyPreviewNotShownForFileWithSizeGreaterThan15MB");
       // common_page = new CommonPageFunctions();
            /*ingestionPage = new IngestionPage();
            CommonPageFunctions.selectRDHEnvironment("Common","Environment");
            WrapperFunctionUtilities.waitForTime(7000);*/
        IngestionPageFunctions.clickAddIngestion();
		ingestionID = setUniqueIngestionIDAndTableName();
        selectLoadExistingType("Ingestion", "Local");
        IngestionPageFunctions.Define_Data_Source("Excel_25MB_FilePath");
        result = waitForNonExistenceOfLoadingSymbol(50);
        Assert.assertEquals(result, true);
        IngestionPageFunctions.Transactional_Info_Excel_File("Excel_FileFormat", "FullLoadType", "Excel 25MB File Name", "SkipHeader");
        result = CommonPageFunctions.switchscreen("Step 2 of 3");
        result = verifyPreviewErrorMessage();
        Assert.assertEquals(result, true);
        result = verifyPreviewErrorMessage();
        Assert.assertEquals(result, true,"Preview Error Message is not displayed");
    }

    // Verify of excel file is shown for both .xls and .xlsx format -- EDGE-16852
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyExcelPreviewForXLSFormat() {
        try {
            Log.info("Method : verifyExcelPreviewForXLSFormat");
            /*common_page = new CommonPageFunctions();
            ingestionPage = new IngestionPage();
            CommonPageFunctions.selectRDHEnvironment("Common","Environment");*/
            IngestionPageFunctions.clickAddIngestion();
			ingestionID = setUniqueIngestionIDAndTableName();
            selectLoadExistingType("Ingestion", "Local");
            IngestionPageFunctions.Define_Data_Source("Excel_FilePath_XLS");
            IngestionPageFunctions.Transactional_Info_Excel_File("Excel_FileFormat", "FullLoadType", "SheetName", "SkipHeader");
            result = CommonPageFunctions.switchscreen("Step 2 of 3");
			Assert.assertTrue(result);
            result = verifySourceColumnHeadersInPreviewTable("SheetName", "Excel File Name XLS","SkipHeader","Excel_XLS_Format");
			Assert.assertTrue(result);
			result = verifySourceDataInPreviewTable("SheetName", "Excel File Name XLS","RowNum2","Excel_XLS_Format");
			Assert.assertTrue(result);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    // Verify excel file preview for large excel file -- EDGE-16856
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyExcelPreviewForLargeExcelFile() {
        try {
            Log.info("Method : verifyExcelPreviewForLargeExcelFile");
            /*common_page = new CommonPageFunctions();
            ingestionPage = new IngestionPage();
            CommonPageFunctions.selectRDHEnvironment("Common","Environment");*/
            IngestionPageFunctions.clickAddIngestion();
			ingestionID = setUniqueIngestionIDAndTableName();
            selectLoadExistingType("Ingestion", "Local");
            IngestionPageFunctions.Define_Data_Source("Excel_15MB_FilePath");
            result = waitForNonExistenceOfLoadingSymbol(50);
            IngestionPageFunctions.Transactional_Info_Excel_File("Excel_FileFormat", "FullLoadType", "SheetName", "SkipHeader");
            result = CommonPageFunctions.switchscreen("Step 2 of 3");
			Assert.assertTrue(result);
			waitForPreviewTableContentToLoad();
			waitForPreviewTableContentToLoad();
            result = verifySourceColumnHeadersInPreviewTable("SheetName", "Excel 15MB File Name","SkipHeader","Excel_XLSX_Format");
			Assert.assertTrue(result);
			result = verifySourceDataInPreviewTable("SheetName", "Excel 15MB File Name","RowNum2","Excel_XLSX_Format");
			Assert.assertTrue(result);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
//EDGE-19229	Data Center | Verify Ingestion Table with file having quotes and escape character should get created correctly
	//Verify Redshift ingestion for file with quote and escape characters
	@Test(groups = {"Regression_Medium_Priority"})
	public void verifyS3IngestionWithQuoteAndEscapeCSVToRedshift()
	{
		try
		{
			Log.info("verifyS3IngestionWithQuoteAndEscapeCSVToRedshift");
			common_page = new CommonPageFunctions();
			CommonPageFunctions.selectRDHEnvironment("Common","Environment");
			IngestionPageFunctions.clickAddIngestion();
			String ingestionID = CommonPageFunctions.generateRandomString();
			result = CommonPageFunctions.switchscreen("Step 1 of 3");
			Assert.assertEquals(result, true);
			setIngestionID(ingestionID);
			setTableName(ingestionID);
			selectLoadExistingType("Ingestion", "Load Existing Adaptor");
			selectExistingAdaptor("Ingestion", "S3IAMAdaptor");
			Transactional_Info_S3_CSV("CSV_FileFormat", "CSV_FileType", "FullLoadType", "QuoteNEscape_CSV_S3FilePath", ",", "SkipHeader");
			enterQuoteEscapeCharacter("QuoteCharacter","EscapeCharacter");
			result = CommonPageFunctions.switchscreen("Step 2 of 3");
			Assert.assertEquals(result, true);
			//setSourceFormatDetails_For_AllDataTypeCSV();
			result = CommonPageFunctions.switchscreen("Step 3 of 3");
			Assert.assertEquals(result, true);
			IngestionPageFunctions.Ingestion_Location("","Redshift","AdaptorName","SchemaName");
			IngestionPageFunctions.clickFinishButton();
			boolean result = CommonPageFunctions.verifyProcess(ingestionID);
			Assert.assertEquals(result, true);
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
//EDGE-16963 Verify Redshift ingestion with file with NULL data

	@Test(groups = {"Regression_Medium_Priority"})
	public void verifyS3IngestionWithNullCSVToRedshift()
	{
		try
		{
			Log.info("verifyS3IngestionWithNullCSVToRedshift");
			common_page = new CommonPageFunctions();
			//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
			IngestionPageFunctions.clickAddIngestion();
			String ingestionID = CommonPageFunctions.generateRandomString();
			result = CommonPageFunctions.switchscreen("Step 1 of 3");
			Assert.assertEquals(result, true);
			setIngestionID(ingestionID);
			setTableName(ingestionID);
			selectLoadExistingType("Ingestion", "Load Existing Adaptor");
			selectExistingAdaptor("Ingestion", "S3IAMAdaptor");
			Transactional_Info_S3_CSV("CSV_FileFormat", "CSV_FileType", "FullLoadType", "NullFileS3Path", ",", "SkipHeader");
			result = CommonPageFunctions.switchscreen("Step 2 of 3");
			Assert.assertEquals(result, true);
			result = CommonPageFunctions.switchscreen("Step 3 of 3");
			Assert.assertEquals(result, true);
			IngestionPageFunctions.Ingestion_Location("","Redshift","AdaptorName","SchemaName");
			IngestionPageFunctions.clickFinishButton();
			boolean result = CommonPageFunctions.verifyProcess(ingestionID);
			Assert.assertEquals(result, true);
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	// EDGE-18175 Target System Governance - Verify Admin user is able to see both the target systems even if not part of any user permission group
	@Test(groups = {"Regression_Medium_Priority"})
	public void verifyAdminUserHasAccessOnBothTargetSystemForIngestion() throws Exception {
		try {
			Log.info("verifyS3IngestionWithNullCSVToRedshift");
			//common_page = new CommonPageFunctions();
			//CommonPageFunctions.selectRDHEnvironment("Common", "Environment");
			Assert.assertEquals((CommonPageFunctions.verifyGroupMembershipofUser("Admin_User","UserPermissionGroupsAssigned")), true);
			Log.info("No group assigned to Admin User");
			IngestionPageFunctions.clickAddIngestion();
			CommonPageFunctions.generateRandomString();
			result = CommonPageFunctions.switchscreen("Step 1 of 3");
			Assert.assertEquals(result, true);
			ingestionID = setUniqueIngestionIDAndTableName();
			selectLoadExistingType("Ingestion", "Load Existing Adaptor");
			selectExistingAdaptor("Ingestion", "S3IAMAdaptor");
			Transactional_Info_S3_CSV("CSV_FileFormat", "CSV_FileType", "FullLoadType", "NullFileS3Path", ",", "SkipHeader");
			result = CommonPageFunctions.switchscreen("Step 2 of 3");
			Assert.assertEquals(result, true);
			result = CommonPageFunctions.switchscreen("Step 3 of 3");
			Assert.assertEquals(result, true);
			Assert.assertTrue(verifyClusterTypeDropDownValues("EMR", "Redshift"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/* NA: Verify by default value of Quote character
	as \" and Escape character as \\ is displayed in the respective text boxes -- EDGE-16890 */
	@Test(groups = {"Regression_Medium_Priority"})
	public void verifyDefaultValuesForEscapeCharacterAndQuoteCharactersTextboxes() throws Exception {

		Log.info("Method : verifyDefaultValuesForEscapeCharacterAndQuoteCharactersTextboxes");
		/*common_page = new CommonPageFunctions();
		ingestionPage = new IngestionPage();*/
		CommonPageFunctions.selectRDHEnvironment("Common", "Environment");
		IngestionPageFunctions.clickAddIngestion();
		setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptor("Ingestion", "MSSQL Adaptor Name");
		result = verifyDefaultContentOfEscapeCharacterAndQuoteCharacterTextboxes();
		Assert.assertEquals(result, true, "Escape Character are not present in TextBoxes");
	}

	/*Verify Parquet preview is correct when skip header is mentioned and multiple files are specified. -- EDGE-16876
	* Verify Parquet preview is correct for any valid numeric value of skip header mentioned  -- EDGE-16878  */
	@Test(groups = {"Regression_Medium_Priority"}) /*Failed Due to Application Error -- RDH-386 Defect ID */
	public void verifyParquetPreviewForValidNumericSkipHeaderValue() throws Exception {
		Log.info("Method : verifyParquetPreviewForValidNumericSkipHeaderValue");
		//common_page = new CommonPageFunctions();
		/*ingestionPage = new IngestionPage();
		CommonPageFunctions.selectRDHEnvironment("Common", "Environment");
		WrapperFunctionUtilities.waitForTime(7000);*/
		IngestionPageFunctions.clickAddIngestion();
		ingestionID = setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptor("Ingestion", "S3IAMAdaptor");
		IngestionPageFunctions.Transactional_Info_ParquetFile("Parquet_FileFormat", "FullLoadType", "SkipHeader_2");
		setSourceFileLocation("Multiple_Parquet_FilePath");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertTrue(result);
		waitForPreviewTableContentToLoad();
		result = verifySourceColumnHeadersInPreviewTable("PqData_SheetName", "IngestionData_FileName","SkipHeader_2","Excel_XLSX_Format");
		Assert.assertTrue(result,"Failed to Verify : verifySourceColumnHeadersInPreviewTable Due to RDH-386");
		result = verifySourceDataInPreviewTable("PqData_SheetName", "IngestionData_FileName","RowNum3","Excel_XLSX_Format");
		Assert.assertTrue(result);
	}

	/* Verify preview of excel file is successful if column names has spaces.  -- EDGE-16861  */
	@Test(groups = {"Regression_Medium_Priority"})
	public void verifyPreviewOfExcelFileIfColumnNamesHasSpaces() throws Exception {
		Log.info("Method : verifyPreviewOfExcelFileIfColumnNamesHasSpaces");
		//common_page = new CommonPageFunctions();
		/*ingestionPage = new IngestionPage();
		CommonPageFunctions.selectRDHEnvironment("Common", "Environment");
		WrapperFunctionUtilities.waitForTime(7000);*/
		IngestionPageFunctions.clickAddIngestion();
		ingestionID = setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptor("Ingestion", "S3IAMAdaptor");
		Transactional_Info_S3_Excel("Excel File Format", "FullLoadType", "ExcelWithSpace_FilePathS3",
				"Sheet Name", "SkipHeader");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertTrue(result);
		result = verifySourceColumnHeadersInPreviewTable("ExcelWithSpace_SheetName", "IngestionData_FileName","RowNum2","Excel_XLSX_Format");
		Assert.assertTrue(result);
		result = verifySourceDataInPreviewTable("ExcelWithSpace_SheetName", "IngestionData_FileName","RowNum3","Excel_XLSX_Format");
		Assert.assertTrue(result);
	}

	/* Verify excel preview is correct when skip header is mentioned and multiple files are specified. -- EDGE-16855 */
	@Test(groups = {"Regression_Medium_Priority"})
	public void verifyExcelPreviewForMultipleFilesAndSkipHeader() throws Exception {
			Log.info("Method : verifyExcelPreviewForMultipleFilesAndSkipHeader");
			//common_page = new CommonPageFunctions();
			/*ingestionPage = new IngestionPage();
			CommonPageFunctions.selectRDHEnvironment("Common", "Environment");
			WrapperFunctionUtilities.waitForTime(7000);*/
			IngestionPageFunctions.clickAddIngestion();
			ingestionID = setUniqueIngestionIDAndTableName();
			selectLoadExistingType("Ingestion", "Load Existing Adaptor");
			selectExistingAdaptor("Ingestion", "S3IAMAdaptor");
			Transactional_Info_S3_Excel("Excel File Format", "FullLoadType", "MultipleExcel_FilePathS3",
					"Sheet Name", "SkipHeader_2");
			result = CommonPageFunctions.switchscreen("Step 2 of 3");
			result = verifySourceColumnHeadersInPreviewTable("MultipleExcelPreview_SheetName", "IngestionData_FileName","RowNum2","Excel_XLSX_Format");
			Assert.assertTrue(result);
			result = verifySourceDataInPreviewTable("MultipleExcelPreview_SheetName", "IngestionData_FileName","RowNum3","Excel_XLSX_Format");
			Assert.assertTrue(result);
	}

	/* Verify for saved ingestion if excel file is changed, preview for new changed file is shown. -- EDGE-16850 */
	@Test(groups = {"Regression_Medium_Priority"})
	public void verifyUpdatedExcelPreviewForSavedIngestion() throws Exception {
		Log.info("Method : verifyUpdatedExcelPreviewForSavedIngestion");
		//common_page = new CommonPageFunctions();
		//ingestionPage = new IngestionPage();
		/*CommonPageFunctions.selectRDHEnvironment("Common", "Environment");
		WrapperFunctionUtilities.waitForTime(7000);*/
		IngestionPageFunctions.clickAddIngestion();
		ingestionID = setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptor("Ingestion", "S3IAMAdaptor");
		Transactional_Info_S3_Excel("Excel File Format", "FullLoadType", "ExcelFile1_FilePathS3",
				"Sheet Name", "SkipHeader");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertEquals(result, true,"Failed to Switch Screen");
		result = verifySourceColumnHeadersInPreviewTable("Excel1Preview_SheetName", "IngestionData_FileName","RowNum2","Excel_XLSX_Format");
		Assert.assertEquals(result, true,"Failed to Verify Column Headers");
		result = verifySourceDataInPreviewTable("Excel1Preview_SheetName", "IngestionData_FileName","RowNum3","Excel_XLSX_Format");
		Assert.assertTrue(result);
		result = CommonPageFunctions.switchscreen("Step 3 of 3");
		Assert.assertEquals(result, true,"Failed to Switch Screen");
		IngestionPageFunctions.Ingestion_Location("Database", "EMR", "", "");
		clickSaveAsDraftButton();
		boolean result = CommonPageFunctions.verifyProcess(ingestionID);
		Assert.assertEquals(result, true);
		editExistingIngestion(ingestionID);
		WrapperFunctionUtilities.waitForTime(3000);
		setSourceFileLocation("ExcelFile2_FilePathS3");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertEquals(result, true,"Failed to Switch Screen");
		result = verifySourceColumnHeadersInPreviewTable("Excel2Preview_SheetName", "IngestionData_FileName","RowNum2","Excel_XLSX_Format");
		Assert.assertEquals(result, true,"Failed to Verify Column Headers");
		result = verifySourceDataInPreviewTable("Excel2Preview_SheetName", "IngestionData_FileName","RowNum3","Excel_XLSX_Format");
		Assert.assertEquals(result, true,"Failed to Verify Column Data");
	}

	/* Verify Multiple CSV file preview the first file with limited record   -- EDGE-16869*/
	@Test(groups = {"Regression_Medium_Priority"})
	public void verifyMultipleCSVFileIngestionPreviewFirstFile() throws Exception {
			Log.info("Method : verifyMultipleCSVFileIngestionPreviewFirstFile");
			//common_page = new CommonPageFunctions();
			/*ingestionPage = new IngestionPage();
			CommonPageFunctions.selectRDHEnvironment("Common","Environment");
			WrapperFunctionUtilities.waitForTime(7000);*/
			IngestionPageFunctions.clickAddIngestion();
			ingestionID = setUniqueIngestionIDAndTableName();
			selectLoadExistingType("Ingestion", "Load Existing Adaptor");
			selectExistingAdaptor("Ingestion", "S3IAMAdaptor");
			Transactional_Info_S3_CSV("CSV_FileFormat", "CSV_FileType", "FullLoadType", "Multiple CSV Source FilePath S3", "\\t", "SkipHeader");
			result = CommonPageFunctions.switchscreen("Step 2 of 3");
			Assert.assertEquals(result, true);
			result = verifySourceColumnHeadersInPreviewTable("CSV0Preview_SheetName", "IngestionData_FileName","SkipHeader_2","Excel_XLSX_Format");
			Assert.assertEquals(result, true);
			result = verifySourceDataInPreviewTable("CSV0Preview_SheetName", "IngestionData_FileName","RowNum3","Excel_XLSX_Format");
			Assert.assertEquals(result, true);
	}

	/* DQM: Verify Edit DQM pop up have drop down 'If data does not pass check' dropdown shows value
	Continue Ingestion with erroneous data, remove erroneous data and continue Ingestion with respect to every column
	-- EDGE-16894 */
	@Test(groups = {"Regression_Medium_Priority"})
	public void verifyDQMDropdownValuesForEveryColumn() throws Exception {
		Log.info("Method : verifyDQMDropdownValuesForEveryColumn");
		//common_page = new CommonPageFunctions();
		/*ingestionPage = new IngestionPage();
		CommonPageFunctions.selectRDHEnvironment("Common","Environment");
		WrapperFunctionUtilities.waitForTime(7000);*/
		IngestionPageFunctions.clickAddIngestion();
		ingestionID = setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptor("Ingestion", "S3IAMAdaptor");
		Transactional_Info_S3_CSV("CSV_FileFormat", "CSV_FileType", "FullLoadType", "Multiple CSV Source FilePath S3", "\\t", "SkipHeader");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertEquals(result, true);
		result = verifyDQMChecksDropdownForEachColumn();
		Assert.assertEquals(result, true);
	}

	/* Verify Excel file preview with more than 200 column and more than 200 rows -- EDGE-16857 */
	@Test(groups = {"Regression_Medium_Priority"})
	public void verifyExcelPreviewForFileMoreThan200ColumnsAndRows() throws Exception {
		Log.info("Method : verifyExcelPreviewForFileMoreThan200ColumnsAndRows");
		//common_page = new CommonPageFunctions();
		//ingestionPage = new IngestionPage();
		//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
		//WrapperFunctionUtilities.waitForTime(7000);
		IngestionPageFunctions.clickAddIngestion();
		ingestionID = setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptor("Ingestion", "S3IAMAdaptor");
		Transactional_Info_S3_Excel("Excel File Format", "FullLoadType", "ExcelFile200Columns_FilePathS3",
				"Sheet Name", "SkipHeader");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertEquals(result, true);
		result = verifySourceColumnHeadersInPreviewTable("ExcelFile200Columns_SheetName", "IngestionData_FileName","RowNum2","Excel_XLSX_Format");
		Assert.assertEquals(result, true);
		clickOnPreviousButton();
		WrapperFunctionUtilities.waitForTime(3000);
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertTrue(result);
		result = verifySourceDataInPreviewTable("ExcelFile200Columns_SheetName", "IngestionData_FileName","RowNum3","Excel_XLSX_Format");
		Assert.assertTrue(result);
	}

	/* Verify schedule Ingestion using excel file which runs Daily -- EDGE-16979 */
	@Test(groups = {"Regression_Medium_Priority"})
	public void verifyScheduleIngestionUsingExcelWhichRunsDaily() throws Exception {
		Log.info("Method : verifyScheduleIngestionUsingExcelWhichRunsDaily");
		//common_page = new CommonPageFunctions();
		/*ingestionPage = new IngestionPage();
		CommonPageFunctions.selectRDHEnvironment("Common","Environment");
		WrapperFunctionUtilities.waitForTime(7000);*/
		IngestionPageFunctions.clickAddIngestion();
		ingestionID = setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptor("Ingestion", "S3IAMAdaptor");
		Transactional_Info_S3_Excel("Excel File Format", "FullLoadType", "ExcelFile1_FilePathS3",
				"Sheet Name", "SkipHeader");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertEquals(result, true);
		String columnHeaderRow = "SkipHeader_2";
		result = verifySourceColumnHeadersInPreviewTable("Excel1Preview_SheetName", "IngestionData_FileName",columnHeaderRow,"Excel_XLSX_Format");
		Assert.assertEquals(result, true);
		result = CommonPageFunctions.switchscreen("Step 3 of 3");
		Assert.assertEquals(result, true);
		IngestionPageFunctions.Ingestion_Location("Database", "EMR", "", "");
		int numOfOccurrences=Integer.parseInt(ExcelReader.getValue("Ingestion", "NumberOfOccurrences_3", "TestData"));
		scheduleADailyIngestion(numOfOccurrences);
		IngestionPageFunctions.clickFinishButton();
		WrapperFunctionUtilities.waitForTime(3000);
		result = CommonPageFunctions.verifyProcess(ingestionID);
		Assert.assertEquals(result, true);
		result = verifyIngestionRecurrenceToggleIsActive(ingestionID);
		Assert.assertEquals(result, true);
	}

	/* Verify Parquet file preview with more than 200 column and more than 200 rows -- EDGE-16875 */
	@Test(groups = {"Regression_Medium_Priority"})
	public void verifyParquetPreviewForFileMoreThan200ColumnsAndRows() throws Exception {
		Log.info("Method : verifyParquetPreviewForFileMoreThan200ColumnsAndRows");
		//common_page = new CommonPageFunctions();
		/*ingestionPage = new IngestionPage();
		CommonPageFunctions.selectRDHEnvironment("Common","Environment");
		WrapperFunctionUtilities.waitForTime(7000);*/
		IngestionPageFunctions.clickAddIngestion();
		setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptor("Ingestion", "S3IAMAdaptor");
		IngestionPageFunctions.Transactional_Info_ParquetFile("Parquet_FileFormat", "FullLoadType", "SkipHeader");
		setSourceFileLocation("ParquetlFile200Columns_FilePathS3");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertTrue(result);
		result = verifySourceColumnHeadersInPreviewTable("ParquetFile200Columns_SheetName", "IngestionData_FileName","RowNum2","Excel_XLSX_Format");
		Assert.assertTrue(result);
		clickOnPreviousButton();
		WrapperFunctionUtilities.waitForTime(3000);
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertTrue(result);
		result = verifySourceDataInPreviewTable("ParquetFile200Columns_SheetName", "IngestionData_FileName","RowNum3","Excel_XLSX_Format");
		Assert.assertTrue(result);
	}

	/* Verify scheduled Daily Recurring Incremental Ingestion with limited recurrence like 2 -- EDGE-16981 */
	@Test(groups = {"Regression_Medium_Priority"})
	public void verifyScheduledDailyRecurringIncrementalIngestion() throws Exception{
			Log.info("Method : verifyScheduledDailyRecurringIncrementalIngestion");
			/* common_page = new CommonPageFunctions();
			ingestionPage = new IngestionPage();
			CommonPageFunctions.selectRDHEnvironment("Common","Environment");
			WrapperFunctionUtilities.waitForTime(7000);*/
			IngestionPageFunctions.clickAddIngestion();
			ingestionID = setUniqueIngestionIDAndTableName();
			selectLoadExistingType("Ingestion", "Load Existing Adaptor");
			selectExistingAdaptor("Ingestion", "S3IAMAdaptor");
			IngestionPageFunctions.Transactional_Info_ParquetFile("Parquet_FileFormat", "IncrementalLoadType", "SkipHeader");
			setSourceFileLocation("ParquetFile10Columns_FilePathS3");
			result = CommonPageFunctions.switchscreen("Step 2 of 3");
			Assert.assertTrue(result);
			selectIncrementalJoinKeyColumn("ParquetFile10Columns_IncJoinKey");
			result = CommonPageFunctions.switchscreen("Step 3 of 3");
			Assert.assertTrue(result);
			IngestionPageFunctions.Ingestion_Location("Database", "EMR", "", "");
			int numOfOccurrences=Integer.parseInt(ExcelReader.getValue("Ingestion", "NumberOfOccurrences_2", "TestData"));
			scheduleADailyIngestion(numOfOccurrences);
			IngestionPageFunctions.clickFinishButton();
			WrapperFunctionUtilities.waitForTime(3000);
			boolean result = CommonPageFunctions.verifyProcess(ingestionID);
			Assert.assertTrue(result);
			result = verifyIngestionRecurrenceToggleIsActive(ingestionID);
			Assert.assertTrue(result);
	}

	/* Verify preview for Parquet file with 10 rows and 10 columns  -- EDGE-16879 */
	@Test(groups = {"Regression_Medium_Priority"})
	public void verifyPreviewForParquetFileWith10ColumnsAndRows() throws Exception {
		Log.info("Method : verifyPreviewForParquetFileWith10ColumnsAndRows");
		/* common_page = new CommonPageFunctions();
		ingestionPage = new IngestionPage();*/
		CommonPageFunctions.selectRDHEnvironment("Common","Environment");
		WrapperFunctionUtilities.waitForTime(7000);
		IngestionPageFunctions.clickAddIngestion();
		ingestionID = setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptor("Ingestion", "S3IAMAdaptor");
		IngestionPageFunctions.Transactional_Info_ParquetFile("Parquet_FileFormat", "FullLoadType", "SkipHeader");
		setSourceFileLocation("ParquetFile10Columns_FilePathS3");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertTrue(result);
		result = verifySourceColumnHeadersInPreviewTable("ParquetFile10Columns_SheetName", "IngestionData_FileName","SkipHeader_2","Excel_XLSX_Format");
		Assert.assertTrue(result);
		result = verifySourceDataInPreviewTable("ParquetFile10Columns_SheetName", "IngestionData_FileName","RowNum3","Excel_XLSX_Format");
		Assert.assertTrue(result);
	}
	/* Verify Excel file containing UTF8 and UTF 16 character -- EDGE-16921 */
	@Test(groups = {"Regression_Medium_Priority"})
	public void VerifyExcelFileIngestionContainingUTF16Characters() throws Exception {
		Log.info("Method : VerifyExcelFileIngestionContainingUTF16Characters");
		/*common_page = new CommonPageFunctions();
		ingestionPage = new IngestionPage();
		CommonPageFunctions.selectRDHEnvironment("Common","Environment");
		WrapperFunctionUtilities.waitForTime(7000);*/
		IngestionPageFunctions.clickAddIngestion();
		ingestionID = setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptor("Ingestion", "S3IAMAdaptor");
		Transactional_Info_S3_Excel("Excel File Format", "FullLoadType", "ExcelWithUTF16_FilepathS3",
				"Sheet Name", "SkipHeader");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertTrue(result);
		waitForPreviewTableContentToLoad();
		result = CommonPageFunctions.switchscreen("Step 3 of 3");
		Assert.assertTrue(result);
		IngestionPageFunctions.Ingestion_Location("Database", "EMR", "", "");
		IngestionPageFunctions.clickFinishButton();
		WrapperFunctionUtilities.waitForPageToLoad(5, driver);
		boolean result = CommonPageFunctions.verifyProcess(ingestionID);
		Assert.assertTrue(result);
	}
	/* Verify Preview for other delimited file like |, /t, space  -- EDGE-16870 */
	@Test(groups = {"Regression_Medium_Priority"})
	public void verifyPreviewForCSVIngestionWithPipeAndTab_SpaceDelimited() throws Exception{
			Log.info("Method : verifyPreviewForCSVIngestionWithPipeAndTab_SpaceDelimited");
			//common_page = new CommonPageFunctions();
			//ingestionPage = new IngestionPage();
			//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
			IngestionPageFunctions.clickAddIngestion();
			setUniqueIngestionIDAndTableName();
			selectLoadExistingType("Ingestion", "Local");
			IngestionPageFunctions.Define_Data_Source("TabSeparatedCSV_Filepath");
			IngestionPageFunctions.Transactional_Info_Tab_Separated_CSV_File("CSV_FileFormat", "CSV_FileType", "FullLoadType", "SkipHeader");
			result = CommonPageFunctions.switchscreen("Step 2 of 3");
			Assert.assertEquals(result, true);
			String columnHeaderRow = "SkipHeader_2";
			result = verifySourceColumnHeadersInPreviewTable("TabSeparatedCSVPreview_SheetName", "IngestionData_FileName",columnHeaderRow,"Excel_XLSX_Format");
			Assert.assertEquals(result, true);
			result = verifySourceDataInPreviewTable("TabSeparatedCSVPreview_SheetName", "IngestionData_FileName","RowNum3","Excel_XLSX_Format");
			Assert.assertTrue(result);
			result = clickCancelButton();
			Assert.assertTrue(result);
			IngestionPageFunctions.clickAddIngestion();
			setUniqueIngestionIDAndTableName();
			selectLoadExistingType("Ingestion", "Local");
			IngestionPageFunctions.Define_Data_Source("PipeSeparatedCSV_Filepath");
			IngestionPageFunctions.Transactional_Info_CSV_File_With_Delimiter("CSV_FileFormat", "CSV_FileType", "|", "IncrementalLoadType", "SkipHeader");
			result = CommonPageFunctions.switchscreen("Step 2 of 3");
			Assert.assertEquals(result, true);
			result = verifySourceColumnHeadersInPreviewTable("PipeSeparatedCSVPreview_SheetName", "IngestionData_FileName",columnHeaderRow,"Excel_XLSX_Format");
			Assert.assertEquals(result, true);
			result = verifySourceDataInPreviewTable("PipeSeparatedCSVPreview_SheetName", "IngestionData_FileName","RowNum3","Excel_XLSX_Format");
			Assert.assertTrue(result);
	}
	// Verify Excel file preview with Null, Special Character and UTF character -- EDGE-16860
	@Test(groups = {"Regression_Medium_Priority"})
	public void verifyExcelFilePreviewWithNull_Special_UTFCharacters() throws Exception{
		Log.info("Method : verifyExcelFilePreviewWithNull_Special_UTFCharacters");
		IngestionPageFunctions.clickAddIngestion();
		ingestionID = setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Local");
		IngestionPageFunctions.Define_Data_Source("ExcelWithUFT_SpecialChar_Null_FilePath");
		IngestionPageFunctions.Transactional_Info_Excel_File("Excel_FileFormat", "FullLoadType", "Sheet Name", "SkipHeader");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertTrue(result);
		waitForPreviewTableContentToLoad();
		result = verifySourceColumnHeadersInPreviewTable("Sheet Name", "ExcelWithUFT_SpecialChar_Null_FileName","SkipHeader","Excel_XLSX_Format");
		Assert.assertTrue(result);
		result = verifySourceDataInPreviewTable("Sheet Name", "ExcelWithUFT_SpecialChar_Null_FileName","RowNum2","Excel_XLSX_Format");
		Assert.assertTrue(result);
		result = verifyExistenceOfNullCharacterInPreviewTable("TestData","Ingestion","NullColumn_ExcelWithUFT_SpecialChar");
		Assert.assertTrue(result);
		clickCancelButton();
	}

	// Verify on step 2 Schema page, type column has dropdown with all the datatypes -- EDGE-16901
	@Test(groups = {"Regression_High_Priority"})
	public void verifyTypeColumnHasDropdownWithAllDataTypes() throws Exception{
		Log.info("Method : verifyTypeColumnHasDropdownWithAllDataTypes");
		CommonPageFunctions.selectRDHEnvironment("Common","Environment");
		IngestionPageFunctions.clickAddIngestion();
		setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Local");
		IngestionPageFunctions.Define_Data_Source("PipeSeparatedCSV_Filepath");
		IngestionPageFunctions.Transactional_Info_CSV_File_With_Delimiter("CSV_FileFormat", "CSV_FileType", "|", "IncrementalLoadType", "SkipHeader");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertTrue(result);
		result = verifySourceColumnTypeDropDownValues();
		Assert.assertTrue(result);
		clickCancelButton();
	}

	// Verify the user selection for predicted column names or source column name is retained
	// even when user navigates previous or next steps of ingestion -- EDGE-17028
	@Test(groups = {"Regression_High_Priority"})
	public void verifySelectionForSourceColumnsAreRetainedDuringNavigation() throws Exception{
		Log.info("Method : verifySelectionForSourceColumnsAreRetainedDuringNavigation");
		CommonPageFunctions.selectDataMenuOptions("DataMenu", "ingestionTabName");
		ingestionID = ExcelReader.getValue("Ingestion", "iDataLoadedDraftIngestion", "TestData");
		searchAndOpenExistingIngestion(ingestionID);
		WrapperFunctionUtilities.waitForTime(2000);
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertTrue(result);
		result = verifyColumnSelectionIsRetainedWhileNavigation();
		Assert.assertTrue(result);
		clickCancelButton();
	}

	// Verify the user selection for predicted column names or source column name is retained after ingestion run or saved as draft -- EDGE-17029
	@Test(groups = {"Regression_High_Priority"})
	public void verifySelectionForSourceColumnsAreRetainedAfterIngestionRunOrSaveDraft() throws Exception{
		Log.info("Method : verifySelectionForSourceColumnsAreRetainedAfterIngestionRunOrSaveDraft");
		CommonPageFunctions.selectDataMenuOptions("DataMenu", "ingestionTabName");
		ingestionID = ExcelReader.getValue("Ingestion", "iDataLoadedRunIngestion", "TestData");
		searchAndOpenExistingIngestion(ingestionID);
		result = verifySourceColumnSelectionIsRetainedOnSavingDraftAndIngestionRun(ingestionID);
		Assert.assertTrue(result);
		clickCancelButton();
	}

	//EDGE-16897	step 2 DQM: Verify user can able to select the Primary key column and there will be a drop down 'If data does not pass check'
	// dropdown shows value Continue Ingestion with erroneous data, remove erroneous data and continue Ingestion
	@Test(groups = {"Regression_High_Priority"})
	public void verifyPrimaryKeyDropdownAndIfDataDoesNotPassCheckDropdownInSchema() throws Exception{
		Log.info("Method : verifyPrimaryKeyDropdownAndIfDataDoesNotPassCheckDropdownInSchema");
		IngestionPageFunctions.clickAddIngestion();
		ingestionID = setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Local");
		IngestionPageFunctions.Define_Data_Source("Excel_FilePath_2");
		IngestionPageFunctions.Transactional_Info_Excel_File("Excel_FileFormat","FullLoadType","Sheet Name 2","SkipHeader");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertTrue(result);
		result = verifyPrimaryKeyDropdownInDQM("Sheet Name 2","Excel File Name 2","SkipHeader","Excel_XLSX_Format");
		Assert.assertTrue(result);
		result = verifyIfDataDoesNotPassCheckDropDownValues();
		Assert.assertTrue(result);
		clickCancelButton();
	}

	// Verify Ingestion with DQM check condition Continue with error data -- EDGE-17754
	@Test(groups = {"Regression_High_Priority"})
	public void verifyIngestionWithDQMCheckConditionContinueWithErrorData() throws Exception {
		Log.info("Method : verifyIngestionWithDQMCheckConditionContinueWithErrorData");
		IngestionPageFunctions.clickAddIngestion();
		ingestionID = setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Local");
		IngestionPageFunctions.Define_Data_Source("TabSeparatedCSV_Filepath");
		IngestionPageFunctions.Transactional_Info_Tab_Separated_CSV_File("CSV_FileFormat", "CSV_FileType", "FullLoadType", "SkipHeader");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertEquals(result, true);
		selectContinueIngestionWithErrorneousDataOption();
		setSourceFormatDetails_For_TabSeparatedCSV();
		result = CommonPageFunctions.switchscreen("Step 3 of 3");
		Assert.assertEquals(result, true);
		IngestionPageFunctions.Ingestion_Location("Database", "EMR", "", "");
		IngestionPageFunctions.clickFinishButton();
		WrapperFunctionUtilities.waitForPageToLoad(5, driver);
		boolean result = CommonPageFunctions.verifyProcess(ingestionID);
		Assert.assertEquals(result, true);
	}

	//Verify Oracle Ingestion with Limited columns in schema, e.g In schema defination target table should be having less columns that source table in Oracle -- EDGE-17733
	@Test(groups = {"Regression_Medium_Priority"})
	public void verifyOracleIngestionWithLimitedColumns() throws Exception
	{
		Log.info("Method : verifyOracleIngestionWithLimitedColumns");
		IngestionPageFunctions.clickAddIngestion();
		ingestionID = setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptor("Ingestion", "Oracle Automation Adaptor");
		Transactional_Info_RDBMS_Adaptor("FullLoadType","Oracle_Table_Name1");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertEquals(result, true);
		setSourceFormatDetails_For_OracleLargeTable();
		deselectSourceColumns_For_OracleLargeTable();
		result = CommonPageFunctions.switchscreen("Step 3 of 3");
		Assert.assertEquals(result, true);
		IngestionPageFunctions.Ingestion_Location("Database", "EMR", "", "");
		IngestionPageFunctions.clickFinishButton();
		WrapperFunctionUtilities.waitForPageToLoad(5, driver);
		boolean result = CommonPageFunctions.verifyProcess(ingestionID);
		Assert.assertEquals(result, true);
	}

	//Verify Scheduled Oracle Ingestion with INCREMENTAL Load type -- EDGE-17762
	@Test(groups = {"Regression_Medium_Priority"})
	public void verifyScheduledOracleIngestionWithIncrementalLoadType() throws Exception
	{
		Log.info("Method : verifyScheduledOracleIngestionWithIncrementalLoadType");
		IngestionPageFunctions.clickAddIngestion();
		ingestionID = setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptor("Ingestion", "Oracle Automation Adaptor");
		Transactional_Info_RDBMS_Adaptor("IncrementalLoadType","Oracle_Table_Name1");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertEquals(result, true);
		setSourceFormatDetails_For_OracleLargeTable();
		selectIncrementalJoinKeyColumn("Oracle_Table1_IncrementalJoinColumn");
		result = CommonPageFunctions.switchscreen("Step 3 of 3");
		Assert.assertEquals(result, true);
		IngestionPageFunctions.Ingestion_Location("Database", "EMR", "", "");
		int numOfOccurrences=Integer.parseInt(ExcelReader.getValue("Ingestion", "NumberOfOccurrences_3", "TestData"));
		scheduleADailyIngestion(numOfOccurrences);
		IngestionPageFunctions.clickFinishButton();
		WrapperFunctionUtilities.waitForTime(3000);
		result = CommonPageFunctions.verifyProcess(ingestionID);
		Assert.assertEquals(result, true);
		result = verifyIngestionRecurrenceToggleIsActive(ingestionID);
		Assert.assertEquals(result, true);
	}

	//Verify preview of oracle table with 200 column and rows table -- EDGE-17737
	@Test(groups = {"Regression_High_Priority"})
	public void verifyPreviewOfOracleTableWith200ColumnsAndRows() throws Exception {
		Log.info("Method : verifyPreviewOfOracleTableWith200ColumnsAndRows");
		IngestionPageFunctions.clickAddIngestion();
		setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptor("Ingestion", "Oracle Automation Adaptor");
		WrapperFunctionUtilities.waitForTime(3000);
		Transactional_Info_RDBMS_Adaptor("FullLoadType","Oracle_Table_BigData");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertTrue(result);
		waitForPreviewTableContentToLoad();
		result = verifySourceColumnHeadersInPreviewTable("Oracle_BigData_SheetName", "IngestionData_FileName","RowNum2","Excel_XLSX_Format");
		Assert.assertTrue(result);
		clickOnPreviousButton();
		WrapperFunctionUtilities.waitForTime(3000);
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertTrue(result);
		result = verifySourceDataInPreviewTable("Oracle_BigData_SheetName", "IngestionData_FileName","RowNum3","Excel_XLSX_Format");
		Assert.assertTrue(result);
	}

	// Verify preview of MSSQL table with 200 column and rows table -- EDGE-17748
	@Test(groups = {"Regression_High_Priority"})
	public void verifyPreviewOfMSSQLTableWith200ColumnsAndRows() throws Exception {
		Log.info("Method : verifyPreviewOfMSSQLTableWith200ColumnsAndRows");
		IngestionPageFunctions.clickAddIngestion();
		setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptor("Ingestion", "MSSQL Adaptor Name 2");
		WrapperFunctionUtilities.waitForTime(3000);
		Transactional_Info_RDBMS_Adaptor("FullLoadType","MSSQL_Table_BigData");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertTrue(result);
		waitForPreviewTableContentToLoad();
		result = verifySourceColumnHeadersInPreviewTable("MSSQL_BigData_SheetName", "IngestionData_FileName","RowNum2","Excel_XLSX_Format");
		Assert.assertTrue(result);
		clickOnPreviousButton();
		WrapperFunctionUtilities.waitForTime(3000);
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertTrue(result);
		result = verifySourceDataInPreviewTable("MSSQL_BigData_SheetName", "IngestionData_FileName","RowNum3","Excel_XLSX_Format");
		Assert.assertTrue(result);
	}

	// Verify Ingestion with Limited columns in schema, e.g In schema defination target table should be having less columns that source table schema -- EDGE-17744
	@Test(groups = {"Regression_Medium_Priority"})
	public void verifyMSSQLIngestionWithLimitedColumns() throws Exception
	{
		Log.info("Method : verifyMSSQLIngestionWithLimitedColumns");
		IngestionPageFunctions.clickAddIngestion();
		ingestionID = setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptor("Ingestion", "MSSQL Adaptor Name 2");
		Transactional_Info_RDBMS_Adaptor("FullLoadType","MSSQL_Table_Name2");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertTrue(result);
		deselectSourceColumns_For_MSSQLTable();
		result = CommonPageFunctions.switchscreen("Step 3 of 3");
		Assert.assertTrue(result);
		IngestionPageFunctions.Ingestion_Location("Database", "EMR", "", "");
		IngestionPageFunctions.clickFinishButton();
		WrapperFunctionUtilities.waitForPageToLoad(5, driver);
		boolean result = CommonPageFunctions.verifyProcess(ingestionID);
		Assert.assertTrue(result);
	}

	// Verify preview of Redshift table with 200 column and rows table -- EDGE-18184
	@Test(groups = {"Regression_High_Priority"})
	public void verifyPreviewOfRedshiftTableWith200ColumnsAndRows() throws Exception {
		Log.info("Method : verifyPreviewOfRedshiftTableWith200ColumnsAndRows");
		IngestionPageFunctions.clickAddIngestion();
		setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptor("Ingestion", "Redshift Adaptor Name");
		WrapperFunctionUtilities.waitForTime(3000);
		Transactional_Info_RDBMS_Adaptor_UsingSearchTextBox("FullLoadType","Redshift_Table_BigData");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertTrue(result);
		waitForPreviewTableContentToLoad();
		result = verifySourceColumnHeadersInPreviewTable("Redshift_BigData_SheetName", "IngestionData_FileName","RowNum2","Excel_XLSX_Format");
		Assert.assertTrue(result);
		clickOnPreviousButton();
		WrapperFunctionUtilities.waitForTime(3000);
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertTrue(result);
		result = verifySourceDataInPreviewTable("Redshift_BigData_SheetName", "IngestionData_FileName","RowNum3","Excel_XLSX_Format");
		Assert.assertTrue(result);
	}

	// Verify Oracle Ingestion with table contain Quote Character and escape character -- EDGE-17735
	@Test(groups = {"Regression_High_Priority"})
	public void verifyOracleIngestionWithTableContainQuoteCharacterAndEscapeCharacter() throws Exception
	{
		Log.info("Method : verifyOracleIngestionWithTableContainQuoteCharacterAndEscapeCharacter");
		IngestionPageFunctions.clickAddIngestion();
		ingestionID = setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptor("Ingestion", "Oracle Automation Adaptor");
		enterQuoteEscapeCharacter("Quote Character","Escape Character");
		WrapperFunctionUtilities.waitForTime(3000);//Database options take time to load on the screen
		Transactional_Info_RDBMS_Adaptor_UsingSearchTextBox("FullLoadType","Oracle_Table_Name2");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertTrue(result);
		selectTypeForSourceColumn(ExcelReader.getValue("Ingestion", "Oracle_Table2_SourceColumnName", "TestData"),
				ExcelReader.getValue("Ingestion", "Oracle_Table2_SourceColumnType", "TestData"));
		result = CommonPageFunctions.switchscreen("Step 3 of 3");
		Assert.assertTrue(result);
		IngestionPageFunctions.Ingestion_Location("Database", "EMR", "", "");
		IngestionPageFunctions.clickFinishButton();
		WrapperFunctionUtilities.waitForTime(3000);
		result = CommonPageFunctions.verifyProcess(ingestionID);
		Assert.assertTrue(result);
	}

	// Verify Ingestion with table contain Quote Character and escape character -- EDGE-17747
	@Test(groups = {"Regression_High_Priority"})
	public void verifyMSSQLIngestionWithTableContainQuoteCharacterAndEscapeCharacter() throws Exception
	{
		Log.info("Method : verifyMSSQLIngestionWithTableContainQuoteCharacterAndEscapeCharacter");
		IngestionPageFunctions.clickAddIngestion();
		ingestionID = setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptor("Ingestion", "MSSQL Adaptor Name 2");
		enterQuoteEscapeCharacter("Quote Character","Escape Character");
		WrapperFunctionUtilities.waitForTime(3000);//Database options take time to load on the screen
		Transactional_Info_RDBMS_Adaptor_UsingSearchTextBox("FullLoadType","MSSQL_Table_Name3");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertTrue(result);
		setSourceFormatDetails(ExcelReader.getValue("Ingestion", "MSSQL_Table3_SourceColumnName", "TestData"),
				ExcelReader.getValue("Ingestion", "MSSQL_Table3_SourceColumnFormat", "TestData"));
		result = CommonPageFunctions.switchscreen("Step 3 of 3");
		Assert.assertTrue(result);
		IngestionPageFunctions.Ingestion_Location("Database", "EMR", "", "");
		IngestionPageFunctions.clickFinishButton();
		WrapperFunctionUtilities.waitForTime(3000);
		result = CommonPageFunctions.verifyProcess(ingestionID);
		Assert.assertTrue(result);
	}

	//Verify S3 ingestion with S3 adaptor with IAM role to Redshift  is successful -- EDGE-16941
	@Test(groups = {"Regression_High_Priority"})
	public void verifyS3IngestionWithS3AdaptorWithIAMRoleToRedshift() throws Exception
	{
		Log.info("verifyS3IngestionWithS3AdaptorWithIAMRoleToRedshift");
		IngestionPageFunctions.clickAddIngestion();
		ingestionID = setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptorUsingSearch("Ingestion", "S3IAMRoleAdaptor");
		Transactional_Info_S3_CSV("CSV_FileFormat", "CSV_FileType", "FullLoadType", "Multiple CSV Source FilePath S3", "\\t", "SkipHeader");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertTrue(result);
		setSourceFormatDetails_For_TabSeparatedCSV();
		result = CommonPageFunctions.switchscreen("Step 3 of 3");
		Assert.assertTrue(result);
		IngestionPageFunctions.Ingestion_Location("","Redshift","Redshift Adaptor Name","SchemaName");
		IngestionPageFunctions.clickFinishButton();
		boolean result = CommonPageFunctions.verifyProcess(ingestionID);
		Assert.assertTrue(result);
	}

	//Target System Governance - Verify users belonging to a group having access to only Redshift Target system will be able to see only Redshift option in ingestions and
	// Extractions as target system -- EDGE-18179
	@Test(groups = {"Regression_High_Priority","MultiUserScenario"},priority = 10)
	public void verifyUsersBelongingToGroupHavingAccessToOnlyRedshiftTargetSystemWillSeeOnlyRedshiftOption(){
		try {
			Log.info("verifyUsersBelongingToGroupHavingAccessToOnlyRedshiftTargetSystemWillSeeOnlyRedshiftOption");
			LoginPageTest.logOutFromExistingUser(driver);
			CommonPageFunctions.navigateToURL(prop.getProperty("url"));
			LoginPageTest.loginIngesterUser2();
			IngestionPageFunctions.clickAddIngestion();
			ingestionID = setUniqueIngestionIDAndTableName();
			selectLoadExistingType("Ingestion", "Local");
			IngestionPageFunctions.Define_Data_Source("TabSeparatedCSV_Filepath");
			IngestionPageFunctions.Transactional_Info_Tab_Separated_CSV_File("CSV_FileFormat", "CSV_FileType", "FullLoadType", "SkipHeader");
			result = CommonPageFunctions.switchscreen("Step 2 of 3");
			Assert.assertEquals(result, true);
			setSourceFormatDetails_For_TabSeparatedCSV();
			result = CommonPageFunctions.switchscreen("Step 3 of 3");
			Assert.assertTrue(result);
			result = verifyClusterTypeDropdownOptions("Redshift");
			Assert.assertTrue(result);
			clickCancelButton();
			LoginPageTest.logOutFromExistingUser(driver);
			CommonPageFunctions.navigateToURL(prop.getProperty("url"));
			LoginPageTest.loginTest();
			CommonPageFunctions.selectRDHEnvironmentIfDisplayed("Common","Environment");
		}catch (Exception e){
			e.printStackTrace();
			LoginPageTest.logOutFromExistingUser(driver);
			CommonPageFunctions.navigateToURL(prop.getProperty("url"));
			LoginPageTest.loginTest();
			Assert.assertTrue(false);
			CommonPageFunctions.selectRDHEnvironmentIfDisplayed("Common","Environment");
		}
	}

	//Target System Governance - Verify users belonging to a group having access to only EMR Taget system will be able to see only EMR option in ingestions
	// and Extractions as target system -- EDGE-18178
	@Test(groups = {"Regression_High_Priority","MultiUserScenario"},priority = 10)
	public void verifyUsersBelongingToGroupHavingAccessToOnlyEMRTargetSystemWillSeeOnlyRedshiftOption() {
		try{
			Log.info("verifyUsersBelongingToGroupHavingAccessToOnlyEMRTargetSystemWillSeeOnlyRedshiftOption");
			LoginPageTest.logOutFromExistingUser(driver);
			CommonPageFunctions.navigateToURL(prop.getProperty("url"));
			LoginPageTest.loginIngesterUser1();
			IngestionPageFunctions.clickAddIngestion();
			ingestionID = setUniqueIngestionIDAndTableName();
			selectLoadExistingType("Ingestion", "Local");
			IngestionPageFunctions.Define_Data_Source("TabSeparatedCSV_Filepath");
			IngestionPageFunctions.Transactional_Info_Tab_Separated_CSV_File("CSV_FileFormat", "CSV_FileType", "FullLoadType", "SkipHeader");
			result = CommonPageFunctions.switchscreen("Step 2 of 3");
			Assert.assertEquals(result, true);
			setSourceFormatDetails_For_TabSeparatedCSV();
			result = CommonPageFunctions.switchscreen("Step 3 of 3");
			Assert.assertTrue(result);
			result = verifyClusterTypeDropdownOptions("EMR");
			Assert.assertTrue(result);
			clickCancelButton();
			LoginPageTest.logOutFromExistingUser(driver);
			CommonPageFunctions.navigateToURL(prop.getProperty("url"));
			LoginPageTest.loginTest();
		} catch (Exception e){
			e.printStackTrace();
			LoginPageTest.logOutFromExistingUser(driver);
			CommonPageFunctions.navigateToURL(prop.getProperty("url"));
			LoginPageTest.loginTest();
			Assert.assertTrue(false);
		}
	}

////EDGE-16896 DQM: Verify after clicking on + button on DQM columns user should be able to apply following DQM check Inclusion, Length, Not Null, Range, Inclusion, Exclusion, Length, Unique on 'Edit DQM' pop up
	@Test(groups = {"Regression_Medium_Priority"})
	public void verifyUserIsAbleToApplyMultipleDQMChecks() {
		try {
			Log.info("Method : verifyUserIsAbleToApplyMultipleDQMChecks");
			//common_page = new CommonPageFunctions();
			//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
			IngestionPageFunctions.clickAddIngestion();
			ingestionID = setUniqueIngestionIDAndTableName();
			selectLoadExistingType("Ingestion", "Load Existing Adaptor");
			selectExistingAdaptor("Ingestion", "S3IAMAdaptor");
			Transactional_Info_S3_CSV("CSV_FileFormat", "CSV_FileType", "FullLoadType", "Multiple CSV Source FilePath S3", "\\t", "SkipHeader");
			result = CommonPageFunctions.switchscreen("Step 2 of 3");
			Assert.assertEquals(result, true);
			setSourceFormatDetails_For_TabSeparatedCSV();
			addDQM("DQMList","IngestionData","InclusionValue");
			Assert.assertTrue(verifyDQMsAddedAtSchemaDefinePage("DQMList","IngestionData"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	//EDGE-16904 DQM: Verify after adding multiple check for datataype, or similar type, all DQMs selected are shown
	@Test(groups = {"Regression_Medium_Priority"})
	public void verifyMultipleDQMChecksAppliedAtSchemaDefinitionPageIngestion() {
		try {
			Log.info("Method : verifyUserIsAbleToApplyMultipleDQMChecks");
			//common_page = new CommonPageFunctions();
			//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
			IngestionPageFunctions.clickAddIngestion();
			ingestionID = setUniqueIngestionIDAndTableName();
			selectLoadExistingType("Ingestion", "Load Existing Adaptor");
			selectExistingAdaptor("Ingestion", "S3IAMAdaptor");
			Transactional_Info_S3_CSV("CSV_FileFormat", "CSV_FileType", "FullLoadType", "Multiple CSV Source FilePath S3", "\\t", "SkipHeader");
			result = CommonPageFunctions.switchscreen("Step 2 of 3");
			Assert.assertEquals(result, true);
			setSourceFormatDetails_For_TabSeparatedCSV();
			addDQM("DQMList", "IngestionData", "InclusionValue");
			Assert.assertTrue(verifyDQMsAddedAtSchemaDefinePage("DQMList", "IngestionData"));
			result = CommonPageFunctions.switchscreen("Step 3 of 3");
			Assert.assertEquals(result, true);
			IngestionPageFunctions.Ingestion_Location("Database", "EMR", "", "");
			IngestionPageFunctions.clickFinishButton();
			WrapperFunctionUtilities.waitForPageToLoad(5, driver);
			boolean result = CommonPageFunctions.verifyProcess(ingestionID);
			Assert.assertEquals(result, true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
//EDGE-16877 Verify Parquet file preview with Null, Special Character and UTF character
	@Test(groups = {"Regression_High_Priority"})
	public void verifyUTF8FilePreviewHavingNullSpecialCharacter() throws Exception {
		Log.info("Method : verifyUTF8FilePreviewHavingNullSpecialCharacter");
		IngestionPageFunctions.clickAddIngestion();
		ingestionID = setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptor("Ingestion", "S3IAMAdaptor");
		Transactional_Info_ParquetFile_S3("Parquet File Format","FullLoadType","S3locationParquetUtf8Null","SkipHeader");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertEquals(result, true);
		result = verifySourceColumnHeadersInPreviewTable("SheetParquetUtf8NullSpecialCharacter", "IngestionData_FileName","SkipHeader_2","Excel_XLSX_Format");
		Assert.assertTrue(result);
		result = verifySourceDataInPreviewTable("SheetParquetUtf8NullSpecialCharacter", "IngestionData_FileName","RowNum3","Excel_XLSX_Format");
		Assert.assertTrue(result);
		}

	//EDGE-19226 Data Center | Verify User should not be able to delete ingestion/extraction that is in the running state
	@Test(groups = "Regression_High_Priority")
	public  void  verifyUnableToDeleteIngestionInRunningState() throws Exception {
			{
				Log.info("Method : verifyUnableToDeleteIngestionInRunningState");
				IngestionPageFunctions.clickAddIngestion();
				ingestionID = setUniqueIngestionIDAndTableName();
				selectLoadExistingType("Ingestion", "Load Existing Adaptor");
				selectExistingAdaptorUsingSearch("Ingestion", "S3IAMRoleAdaptor");
				Transactional_Info_S3_Excel("Excel File Format","FullLoadType", "ExcelFile1_FilePathS3", "Sheet Name","SkipHeader");
				result = CommonPageFunctions.switchscreen("Step 2 of 3");
				Assert.assertEquals(result, true);
				//waitForPreviewTableContentToLoad();
				result = CommonPageFunctions.switchscreen("Step 3 of 3");
				Assert.assertEquals(result, true);
				IngestionPageFunctions.Ingestion_Location("Database","EMR","","");
				IngestionPageFunctions.clickFinishButton();
				WrapperFunctionUtilities.waitForTime(2000);
				boolean result = CommonPageFunctions.verifyProcess(ingestionID);
				Assert.assertEquals(result, true);
				WrapperFunctionUtilities.waitForElementVisibility(DataCenterPage_RunningStatus_WebElement(ingestionID),50000,driver);
				Assert.assertTrue(verifyIngestionDeletionInRunningState(ingestionID));
			}
		}

	// EDGE-19223 Data Center | Verify user added column value should be retained in data center step 2
	@Test(groups = "Regression_High_Priority")
	public void verifyUserAddedColumnIsRetainedAtStep2DataCenter() throws Exception {
		Log.info("Method : verifyUserAddedColumnIsRetainedAtStep2DataCenter");
		String aliasName =ExcelReader.getValue("Ingestion","AliasName","TestData");
		String datatype = ExcelReader.getValue("Ingestion","Datatype","TestData");
		IngestionPageFunctions.clickAddIngestion();
		ingestionID = setUniqueIngestionIDAndTableName();
		selectLoadExistingType("Ingestion", "Load Existing Adaptor");
		selectExistingAdaptorUsingSearch("Ingestion", "S3IAMRoleAdaptor");
		Transactional_Info_S3_Excel("Excel File Format","FullLoadType", "ExcelFile1_FilePathS3", "Sheet Name","SkipHeader");
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertEquals(result, true);
		addNewSourceColumn(aliasName,datatype);
		WrapperFunctionUtilities.waitForTime(1000);
		clickOnPreviousButton();
		WrapperFunctionUtilities.waitForTime(1000);
		result = CommonPageFunctions.switchscreen("Step 2 of 3");
		Assert.assertEquals(result, true);
		Assert.assertTrue(verifyUserAddedSourceColumnDetailsRetained(aliasName,datatype));
		result = CommonPageFunctions.switchscreen("Step 3 of 3");
		Assert.assertEquals(result, true);
		WrapperFunctionUtilities.waitForTime(1000);
		clickOnPreviousButton();
		Assert.assertTrue(verifyUserAddedSourceColumnDetailsRetained(aliasName,datatype));

	}

	}