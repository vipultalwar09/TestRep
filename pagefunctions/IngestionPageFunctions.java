package com.RDM.Merger.pagefunctions;



import java.util.*;

import com.RDM.Merger.locators.CommonPage;

import com.utility.base.Xls_Reader;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.RDM.Merger.locators.IngestionPage;
import com.RDM.Merger.testcases.Aa_Login.ExcelReader;
import com.utility.base.WrapperFunctionUtilities;
import com.utility.logger.Log;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class IngestionPageFunctions extends IngestionPage {

	static Xls_Reader excel;

	public IngestionPageFunctions() {
		super();
	}


	//This method clicks on create new ingestion link
	public static void clickAddIngestion() {
		try {
			Log.info("Method : clickAddIngestion");
			WrapperFunctionUtilities.waitForTime(3000);
			Log.info("Click Add Ingestion");
			CommonPageFunctions.selectDataMenuOptions("DataMenu", "ingestionTabName");
			WrapperFunctionUtilities.waitForTime(3000);
			ingestionTab = IngestionPage.ingestionTabName("Create New Data Source");
			ingestionTab.click();
			WrapperFunctionUtilities.waitForTime(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//Defining data source for local
	public static void selectLoadExistingType(String sheetName, String tabName) throws Exception {

		load_existing.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue(sheetName, tabName, "TestData"));

	}

	public static void selectExistingAdaptor(String sheetName, String adaptorName) throws Exception {
		//selectAdaptor.click();
		WrapperFunctionUtilities.jsClick(selectAdaptor, driver);
		selectExistingAdaptorInput.sendKeys(ExcelReader.getValue(sheetName,adaptorName,"TestData"));
		WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(ExcelReader.getValue(sheetName, adaptorName, "TestData")), driver);
		//CommonPageFunctions.hyperlinkClick(ExcelReader.getValue(sheetName,adaptorName,"TestData"));
	}


	//Defining data source for local ingestion
	public static void Define_Data_Source(String path) throws Exception {

		WrapperFunctionUtilities.waitForTime(1000);
		Log.info(ExcelReader.getValue("Ingestion", path, "TestData"));
		String filePath = PROJECT_PATH + ExcelReader.getValue("Ingestion", path, "TestData");
		Log.info("File path " + filePath);
		local_upload.sendKeys(filePath);
		WrapperFunctionUtilities.waitForTime(6000);

	}

	//Entering Transactional info for Local Excel File
	public static void Transactional_Info_Excel_File(String format, String loadType, String sheetname, String Skipheader) throws Exception {

		format_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", format, "TestData"));
		file_load_type_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", loadType, "TestData"));
		sheet_name.sendKeys(ExcelReader.getValue("Ingestion", sheetname, "TestData"));
		//skip_header_checkbox.click();
		WrapperFunctionUtilities.jsClick(skip_header_checkbox,driver);
		skip_header_text.sendKeys(ExcelReader.getValue("Ingestion", Skipheader, "TestData"));
	}

	public static void Transactional_Info_S3_Excel(String format, String loadType, String fileLocation, String sheetName, String skipHeader) throws Exception {
		format_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", format, "TestData"));
		file_load_type_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", loadType, "TestData"));
		sourceFileLocation.sendKeys(ExcelReader.getValue("Ingestion", fileLocation, "TestData"));
		sheetNameExcel.sendKeys(ExcelReader.getValue("Ingestion", sheetName, "TestData"));
		skip_header_checkbox.click();
		WrapperFunctionUtilities.waitForTime(1000);
		skip_header_text.sendKeys(ExcelReader.getValue("Ingestion", skipHeader, "TestData"));
	}

	public static void Transactional_Info_RDBMS_Adaptor(String loadType, String tableName) throws Exception {
		WrapperFunctionUtilities.waitForElementVisibility(file_load_type_dropdown,10,driver);
		file_load_type_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", loadType, "TestData"));
		WrapperFunctionUtilities.scrollByVisibleElement(selectTableClick,driver);
		WrapperFunctionUtilities.waitForTime(3000);// waiting for DB connections to load table
		//selectTableClick.click();
		WrapperFunctionUtilities.jsClick(selectTableClick, driver);
		WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(ExcelReader.getValue("Ingestion", tableName, "TestData")), driver);
		//tableDropDownInput.sendKeys(ExcelReader.getValue("Ingestion", tableName, "TestData"));
		//CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", tableName, "TestData"));
	}

	//Entering Transactional info for Local CSV File
	public static void Transactional_Info_Tab_Separated_CSV_File(String format, String fileType, String loadType, String Skipheader) throws Exception {

		WrapperFunctionUtilities.waitForElementVisibility(format_dropdown, 5, driver);
		format_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", format, "TestData"));
		WrapperFunctionUtilities.waitForElementVisibility(file_type_dropdown, 5, driver);
		file_type_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", fileType, "TestData"));
		delimiter.sendKeys("\\t");
		WrapperFunctionUtilities.waitForElementVisibility(file_load_type_dropdown, 5, driver);
		file_load_type_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", loadType, "TestData"));
		skip_header_checkbox.click();
		skip_header_text.sendKeys(ExcelReader.getValue("Ingestion", Skipheader, "TestData"));

	}
	//Entering Transactional info for Local CSV File

	public static void Transactional_Info_CSV_File(String format, String fileType, String loadType, String Skipheader) throws Exception {

		WrapperFunctionUtilities.waitForTime(2000);
		format_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", format, "TestData"));
		WrapperFunctionUtilities.waitForTime(1000);
		file_type_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", fileType, "TestData"));
		WrapperFunctionUtilities.waitForTime(1000);
		delimiter.sendKeys(",");
		WrapperFunctionUtilities.waitForTime(1000);
		file_load_type_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", loadType, "TestData"));
		WrapperFunctionUtilities.waitForTime(1000);
		skip_header_checkbox.click();
		WrapperFunctionUtilities.waitForTime(1000);
		skip_header_text.sendKeys(ExcelReader.getValue("Ingestion", Skipheader, "TestData"));
		WrapperFunctionUtilities.waitForTime(1000);

	}


	//Defining Ingestion location for Local
	public static void Ingestion_Location(String database, String clusterType, String adaptorName, String schemaName) throws Exception {
		if (clusterType == "Redshift") {
			WrapperFunctionUtilities.isElementPresent(cluster_type_dropdwn, clusterType);
			Log.info("Cluster Type DropDown Present");
			WrapperFunctionUtilities.waitForElementVisibility(cluster_type_dropdwn,30,driver);
			cluster_type_dropdwn.click();
			CommonPageFunctions.hyperlinkClick(clusterType);
			WrapperFunctionUtilities.waitForTime(1000);
			WrapperFunctionUtilities.isElementPresent(redshift_warehouse_select, ExcelReader.getValue("Ingestion", adaptorName, "TestData"));
			Log.info("Warehouse DropDown Present");
			//redshift_warehouse_select.click();
			WrapperFunctionUtilities.jsClick(redshift_warehouse_select, driver);
			CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", adaptorName, "TestData"));
			WrapperFunctionUtilities.waitForTime(1000);
			WrapperFunctionUtilities.isElementPresent(redshift_schema_select, ExcelReader.getValue("Ingestion", schemaName, "TestData"));
			Log.info("Schema DropDown Present");
			redshift_schema_select.click();
			CommonPageFunctions.hyperlinkClick1(ExcelReader.getValue("Ingestion", schemaName, "TestData"));
		} else {
			WrapperFunctionUtilities.isElementPresent(cluster_type_dropdwn, clusterType);
			WrapperFunctionUtilities.waitForElementVisibility(cluster_type_dropdwn,30,driver);
			Log.info("Cluster Type DropDown Present");
			cluster_type_dropdwn.click();
			CommonPageFunctions.hyperlinkClick(clusterType);
			WrapperFunctionUtilities.waitForTime(2000);
			database_select.click();
			CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", database, "TestData"));
		}

	}


	//This method is used for clicking finish button
	public static void clickFinishButton() throws InterruptedException {
		try {
			IngestionPage.finish_button.click();
			WrapperFunctionUtilities.waitForTime(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//This method is used to run old ingestions
	public static void runIngestion(String ingestionName) {
		try {
			WebElement playButton = adhocIngestionRunButton(ingestionName);
			playButton.click();
			WrapperFunctionUtilities.waitForTime(1000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Transactional_Info_ParquetFile_S3(String format, String loadType, String location, String skipHeader) throws Exception {
		System.out.println("Method :  Transactional_Info_ParquetFile");
		//Log.info("Method : Transactional_Info_ParquetFile");
		format_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", format, "TestData"));
		file_load_type_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", loadType, "TestData"));
		sourceFileLocation.sendKeys((ExcelReader.getValue("Ingestion", location, "TestData")));
		skip_header_checkbox.click();
		WrapperFunctionUtilities.waitForTime(1000);
		skip_header_text.sendKeys(ExcelReader.getValue("Ingestion", skipHeader, "TestData"));
	}

	public static void runiData(String domain) throws Exception {
		runiDataCheckbox.click();
		idataDomainClick.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", domain, "TestData"));
		WrapperFunctionUtilities.jsClick(runButtoniData, driver);
		Log.info("Clicked iData Run button");
	}

	public static boolean verifyiDataDomains(String SheetName, String ColumnName) {
		boolean result;
		excel = new Xls_Reader(PROJECT_PATH + "/src/test/java/com/RDM/Merger/testdata/TestData.xlsx");
		int row_count = excel.getRowCount(SheetName);
		List<WebElement> actual_domain_values = getidatadomains();
		List<String> actual_domains_String_value = new ArrayList<String>();
		runiDataCheckbox.click();
		idataDomainClick.click();
		for (WebElement a : actual_domain_values) {
			actual_domains_String_value.add(a.getAttribute("title"));
			System.out.println("Actual domain list : " + a.getAttribute("title"));
		}
		List expected_domains = new ArrayList();
		for (int i = 2; i <= row_count; i++) {
			System.out.println("Expected domain list: " + excel.getCellData(SheetName, ColumnName, i));
			expected_domains.add(excel.getCellData(SheetName, ColumnName, i));
		}
		return result = expected_domains.equals(actual_domains_String_value);
	}

	public static boolean verifyErrorMessage(String message) throws Exception {
		return WrapperFunctionUtilities.isElementPresent(getErrorMessage(ExcelReader.getValue("Ingestion", message, "TestData")), "Error message");
	}

	// This method is used to Enter SourceFormat Details
	public static void setSourceFormatDetails(String sourceColumnName, String sourceColumnFormat) throws Exception{
			Log.info("Entering SourceFormat Details for " + sourceColumnName);
			WrapperFunctionUtilities.scrollByVisibleElement(sourceFormatTextbox(sourceColumnName), driver);
			sourceFormatTextbox(sourceColumnName).clear();

			sourceFormatTextbox(sourceColumnName).sendKeys(sourceColumnFormat);

	}

	// This method is used to verify Source Column Headers in Data Schema Table
	public static boolean verifySourceColumnHeadersinDataSchemaTable(String sheetName, String fileName) throws Exception {
		boolean flag = true, existenceOfColumn;
		System.out.println("Method :  verifySourceColumnHeadersinDataSchemaTable");
		Log.info("Method : verifySourceColumnHeadersinDataSchemaTable");
		WrapperFunctionUtilities.waitForTime(3000);
		sheetName = ExcelReader.getValue("Ingestion", sheetName, "TestData");
		fileName = ExcelReader.getValue("Ingestion", fileName, "TestData");
		HashMap<String, String> columnHeadersInExcel = ExcelReader.getTestDataFromExcel(sheetName, fileName);
		Set<String> columnNames = columnHeadersInExcel.keySet();
		System.out.println("ColumnNames Set" + columnNames);
		for (String columnName : columnNames) {
			System.out.println(columnName);

			existenceOfColumn = WrapperFunctionUtilities.isElementPresent(sourceColumnNames(columnName),
					columnName);

			if (!existenceOfColumn) {
				flag = false;
			}
		}
		return flag;
	}

	public static void Transactional_Info_ParquetFile(String format, String loadType, String skipHeader) throws Exception {

		System.out.println("Method :  Transactional_Info_ParquetFile");
		//Log.info("Method : Transactional_Info_ParquetFile");
		format_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", format, "TestData"));
		file_load_type_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", loadType, "TestData"));
		skip_header_checkbox.click();
		WrapperFunctionUtilities.waitForTime(1000);
		skip_header_text.sendKeys(ExcelReader.getValue("Ingestion", skipHeader, "TestData"));
	}

	public static void selectIncrementalJoinKeyColumn(String columnName) throws Exception {

		System.out.println("Method :  selectIncrementalJoinKeyColumn");
		// Log.info("Method : selectIncrementalJoinKeyColumn");
		columnName = ExcelReader.getValue("Ingestion", columnName, "TestData");
		WrapperFunctionUtilities.waitForElementVisibility(incrementalJoinKeyColumn(columnName), 10, driver);
		WrapperFunctionUtilities.scrollByVisibleElement(incrementalJoinKeyColumn(columnName), driver);
		WrapperFunctionUtilities.jsClick(incrementalJoinKeyColumn(columnName), driver);

	}

	//Entering Transactional info for Local CSV File With Different Delimeter
	public static void Transactional_Info_CSV_File_With_Delimiter(String format, String fileType, String delimiterUsed, String loadType, String Skipheader) throws Exception {
		WrapperFunctionUtilities.waitForElementVisibility(format_dropdown, 5, driver);
		format_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", format, "TestData"));
		file_type_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", fileType, "TestData"));
		delimiter.sendKeys(delimiterUsed);
		file_load_type_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", loadType, "TestData"));
		//skip_header_checkbox.click();
		WrapperFunctionUtilities.jsClick(skip_header_checkbox, driver);
		skip_header_text.sendKeys(ExcelReader.getValue("Ingestion", Skipheader, "TestData"));
	}

	// This method sets Ingestion ID
	public static void setIngestionID(String ingestionID) {
		System.out.println("Method :  setIngestionID");
		// Log.info("Method : setIngestionID");
		WrapperFunctionUtilities.waitForElementVisibility(ingestion_name, 5, driver);
		ingestion_name.sendKeys(ingestionID);

	}

	// This method sets Table Name for an Ingestion
	public static void setTableName(String tableName) {
		System.out.println("Method :  setIngestionID");
		// Log.info("Method : setIngestionID");
		WrapperFunctionUtilities.waitForElementVisibility(table_name, 5, driver);
		table_name.sendKeys(tableName);

	}

	//Select Column Type for Source Column
	public static void selectTypeForSourceColumn(String sourceColumnName, String type) throws Exception {
		System.out.println("Method :  selectTypeForSourceColumn");
		Log.info("Method : selectTypeForSourceColumn");
		Log.info("Selecting Type  for " + sourceColumnName);
		WrapperFunctionUtilities.waitForElementVisibility(sourceColumnTypeDropdown(sourceColumnName), 5, driver);
		WrapperFunctionUtilities.scrollByVisibleElement(sourceColumnTypeDropdown(sourceColumnName), driver);
		sourceColumnTypeDropdown(sourceColumnName).click();
		WrapperFunctionUtilities.jsClick(sourceColumnTypeDropdownOption(type), driver);
	}

	// Deselect Source Column from Ingestion
	public static void deselectSourceColumnFromIngestion(String sourceColumnName) throws Exception {
		System.out.println("Method :  deselectSourceColumnFromIngestion");
		Log.info("Method : deselectSourceColumnFromIngestion");
		Log.info("DeSelecting " + sourceColumnName);
		WrapperFunctionUtilities.scrollByVisibleElement(selectSourceColumnCheckbox(sourceColumnName), driver);
		WrapperFunctionUtilities.jsClick(selectSourceColumnCheckbox(sourceColumnName), driver);

	}

	// verify Error Message When User uploads File for Ingestion Greater than 100 MB Size
	public static boolean verifyErrorMessageWhenFileIngestionIsOfGreaterSize(String filePath) throws Exception {

		System.out.println("Method :  verifyErrorMessageWhenFileIngestionIsOfGreaterSize");
		Log.info("Method : verifyErrorMessageWhenFileIngestionIsOfGreaterSize");
		boolean flag = true, existenceOfError;

		Log.info(ExcelReader.getValue("Ingestion", filePath, "TestData"));
		String actualFilePath = PROJECT_PATH + ExcelReader.getValue("Ingestion", filePath, "TestData");
		Log.info("File path " + actualFilePath);
		local_upload.sendKeys(actualFilePath);
		WrapperFunctionUtilities.waitForTime(3000);

		existenceOfError = WrapperFunctionUtilities.isElementPresent(uploadFileSizeError, "uploadFileSizeError");

		if (!existenceOfError) {
			flag = false;
		}

		return flag;
	}

	// Transactional Information S3 CSV

	public static void Transactional_Info_S3_CSV(String format, String fileType, String loadType,
												 String fileLocation, String delimiterUsed, String skipHeader) throws Exception {

		System.out.println("Method :  Transactional_Info_S3_CSV");
		Log.info("Method : Transactional_Info_S3_CSV");

		format_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", format, "TestData"));
		WrapperFunctionUtilities.waitForTime(1000);
		file_type_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", fileType, "TestData"));
		delimiter.sendKeys(delimiterUsed);
		WrapperFunctionUtilities.waitForTime(1000);
		file_load_type_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", loadType, "TestData"));
		sourceFileLocation.sendKeys(ExcelReader.getValue("Ingestion", fileLocation, "TestData"));
		skip_header_checkbox.click();
		WrapperFunctionUtilities.waitForTime(1000);
		skip_header_text.sendKeys(ExcelReader.getValue("Ingestion", skipHeader, "TestData"));
	}

	public static void setSourceFormatDetails_For_TabSeparatedCSV() throws Exception {

		System.out.println("Method :  setSourceFormatDetails_For_TabSeparatedCSV");
		Log.info("Method : setSourceFormatDetails_For_TabSeparatedCSV");
		/*
		 * setSourceFormatDetails("salary", "(6,2)"); setSourceFormatDetails("dob",
		 * "dd/mm/yyyy"); setSourceFormatDetails("aggregate", "(10,8)");
		 */

		setSourceFormatDetails(ExcelReader.getValue("TabSeparatedCSV", "sourceColumnName_1", "IngestionData"),
				ExcelReader.getValue("TabSeparatedCSV", "sourceColumnFormat_1", "IngestionData"));

		setSourceFormatDetails(ExcelReader.getValue("TabSeparatedCSV", "sourceColumnName_2", "IngestionData"),
				ExcelReader.getValue("TabSeparatedCSV", "sourceColumnFormat_2", "IngestionData"));

		setSourceFormatDetails(ExcelReader.getValue("TabSeparatedCSV", "sourceColumnName_3", "IngestionData"),
				ExcelReader.getValue("TabSeparatedCSV", "sourceColumnFormat_3", "IngestionData"));
	}

	public static void setSourceFormatDetails_For_ExcelAllDataTypes_Sheet2() throws Exception {

		System.out.println("Method :  setSourceFormatDetails_For_ExcelAllDataTypes_Sheet2");
		Log.info("Method : setSourceFormatDetails_For_ExcelAllDataTypes_Sheet2");

		/*
		 * setSourceFormatDetails("Date_2", "dd/mm/yyyy");
		 * setSourceFormatDetails("Raise_2", "(4,2)");
		 * setSourceFormatDetails("Location_2", "(10,8)");
		 */

		setSourceFormatDetails(
				ExcelReader.getValue("ExcelAllDataTypes_Sheet2", "sourceColumnName_1", "IngestionData"),
				ExcelReader.getValue("ExcelAllDataTypes_Sheet2", "sourceColumnFormat_1", "IngestionData"));

		setSourceFormatDetails(
				ExcelReader.getValue("ExcelAllDataTypes_Sheet2", "sourceColumnName_2", "IngestionData"),
				ExcelReader.getValue("ExcelAllDataTypes_Sheet2", "sourceColumnFormat_2", "IngestionData"));

		setSourceFormatDetails(
				ExcelReader.getValue("ExcelAllDataTypes_Sheet2", "sourceColumnName_3", "IngestionData"),
				ExcelReader.getValue("ExcelAllDataTypes_Sheet2", "sourceColumnFormat_3", "IngestionData"));
	}

	public static void setSourceFormatDetails_For_LocalParquetFile() throws Exception {

		System.out.println("Method :  setSourceFormatDetails_For_LocalParquetFile");
		Log.info("Method : setSourceFormatDetails_For_LocalParquetFile");

		/*
		 * setSourceFormatDetails("DOB", "yyyy-mm-dd"); setSourceFormatDetails("Salary",
		 * "(5,2)");
		 */

		setSourceFormatDetails(
				ExcelReader.getValue("AllDataTypeParquetFile", "sourceColumnName_1", "IngestionData"),
				ExcelReader.getValue("AllDataTypeParquetFile", "sourceColumnFormat_1", "IngestionData"));

		setSourceFormatDetails(
				ExcelReader.getValue("AllDataTypeParquetFile", "sourceColumnName_2", "IngestionData"),
				ExcelReader.getValue("AllDataTypeParquetFile", "sourceColumnFormat_2", "IngestionData"));
	}

	public static void setSourceFormatDetails_For_PipeSeparatedCSV() throws Exception {

		/*
		 * setSourceFormatDetails("Created", "mm/dd/yyyy");
		 * setSourceFormatDetails("Column_Decimal", "(10,8)");
		 */
		System.out.println("Method :  setSourceFormatDetails_For_PipeSeparatedCSV");
		Log.info("Method : setSourceFormatDetails_For_PipeSeparatedCSV");

		setSourceFormatDetails(ExcelReader.getValue("PipeSeparatedCSV", "sourceColumnName_1", "IngestionData"),
				ExcelReader.getValue("PipeSeparatedCSV", "sourceColumnFormat_1", "IngestionData"));

		setSourceFormatDetails(ExcelReader.getValue("PipeSeparatedCSV", "sourceColumnName_2", "IngestionData"),
				ExcelReader.getValue("PipeSeparatedCSV", "sourceColumnFormat_2", "IngestionData"));
	}

	public static void setSourceFormatDetails_For_AllDataTypeParquetFile() throws Exception {

		System.out.println("Method :  setSourceFormatDetails_For_AllDataTypeParquetFile");
		Log.info("Method : setSourceFormatDetails_For_AllDataTypeParquetFile");

		/*
		 * setSourceFormatDetails("DOB", "yyyy-mm-dd");
		 * setSourceFormatDetails("JoinedDate", "yyyy-mm-dd");
		 * setSourceFormatDetails("Salary", "(5,2)");
		 */

		setSourceFormatDetails(
				ExcelReader.getValue("AllDataTypeParquetFile", "sourceColumnName_1", "IngestionData"),
				ExcelReader.getValue("AllDataTypeParquetFile", "sourceColumnFormat_1", "IngestionData"));

		setSourceFormatDetails(
				ExcelReader.getValue("AllDataTypeParquetFile", "sourceColumnName_3", "IngestionData"),
				ExcelReader.getValue("AllDataTypeParquetFile", "sourceColumnFormat_3", "IngestionData"));

		setSourceFormatDetails(
				ExcelReader.getValue("AllDataTypeParquetFile", "sourceColumnName_2", "IngestionData"),
				ExcelReader.getValue("AllDataTypeParquetFile", "sourceColumnFormat_2", "IngestionData"));
	}

	public static void selectTypeForSourceColumn_For_AllDataTypeParquetFile() throws Exception {

		System.out.println("Method :  selectTypeForSourceColumn_For_AllDataTypeParquetFile");
		Log.info("Method : selectTypeForSourceColumn_For_AllDataTypeParquetFile");

		/*
		 * selectTypeForSourceColumn("EmployeeNo", "INT");
		 * selectTypeForSourceColumn("JoinedDate", "TIMESTAMP");
		 */

		selectTypeForSourceColumn(
				ExcelReader.getValue("AllDataTypeParquetFile", "sourceColumnName_4", "IngestionData"),
				ExcelReader.getValue("AllDataTypeParquetFile", "sourceColumnType_4", "IngestionData"));

		selectTypeForSourceColumn(
				ExcelReader.getValue("AllDataTypeParquetFile", "sourceColumnName_3", "IngestionData"),
				ExcelReader.getValue("AllDataTypeParquetFile", "sourceColumnType_3", "IngestionData"));
	}

	public static void selectTypeForSourceColumn_For_AllDataTextFile() throws Exception {
		/*
		 * selectTypeForSourceColumn("Issue_Number", "INT");
		 * selectTypeForSourceColumn("Location", "DOUBLE");
		 */

		System.out.println("Method :  selectTypeForSourceColumn_For_AllDataTextFile");
		Log.info("Method : selectTypeForSourceColumn_For_AllDataTextFile");

		selectTypeForSourceColumn(
				ExcelReader.getValue("TextFileAllDataTypes", "sourceColumnName_3", "IngestionData"),
				ExcelReader.getValue("TextFileAllDataTypes", "sourceColumnType_3", "IngestionData"));

		selectTypeForSourceColumn(
				ExcelReader.getValue("TextFileAllDataTypes", "sourceColumnName_4", "IngestionData"),
				ExcelReader.getValue("TextFileAllDataTypes", "sourceColumnType_4", "IngestionData"));

	}

	public static void setSourceFormatDetails_For_AllDataTextFile() throws Exception {
		/*
		 * setSourceFormatDetails("Created", "mm/dd/yyyy");
		 * setSourceFormatDetails("Decimal", "(5,2)");
		 */

		System.out.println("Method :  setSourceFormatDetails_For_AllDataTextFile");
		Log.info("Method : setSourceFormatDetails_For_AllDataTextFile");

		setSourceFormatDetails(ExcelReader.getValue("TextFileAllDataTypes", "sourceColumnName_1", "IngestionData"),
				ExcelReader.getValue("TextFileAllDataTypes", "sourceColumnFormat_1", "IngestionData"));

		setSourceFormatDetails(ExcelReader.getValue("TextFileAllDataTypes", "sourceColumnName_2", "IngestionData"),
				ExcelReader.getValue("TextFileAllDataTypes", "sourceColumnFormat_2", "IngestionData"));

	}

	public static void deselectSourceColumnFromIngestion_For_TabSeparatedCSV() throws Exception {

		System.out.println("Method :  deselectSourceColumnFromIngestion_For_TabSeparatedCSV");
		Log.info("Method : deselectSourceColumnFromIngestion_For_TabSeparatedCSV");

		deselectSourceColumnFromIngestion(
				ExcelReader.getValue("TabSeparatedCSV", "sourceColumnName_1", "IngestionData"));
		deselectSourceColumnFromIngestion(
				ExcelReader.getValue("TabSeparatedCSV", "sourceColumnName_2", "IngestionData"));
		deselectSourceColumnFromIngestion(
				ExcelReader.getValue("TabSeparatedCSV", "sourceColumnName_4", "IngestionData"));
	}

	//Clicks on Cancel button and Verifies Data Center HomePage
	public boolean clickCancelButton() throws Exception {

		System.out.println("Method :  clickCancelButton");
		Log.info("Method : clickCancelButton");
		boolean flag = true, tempFlag1, tempFlag2;
		WrapperFunctionUtilities.scrollByVisibleElement(cancelButton, driver);
		cancelButton.click();
		WrapperFunctionUtilities.waitForPageToLoad(20, driver);
		tempFlag1 = WrapperFunctionUtilities.isTextPresent("Data Center", driver);
		tempFlag2 = WrapperFunctionUtilities.isElementPresent(
				IngestionPage.ingestionTabName("Create New Data Source"), "Create New Data Source");
		if (!(tempFlag1 && tempFlag2)) {
			flag = false;
		}
		return flag;
	}

	//verify Source Format Textbox Enability For Different Data Types
	public boolean verifySourceFormatTextboxEnability(String sourceName) throws Exception {

		sourceName = ExcelReader.getValue("TextFileAllDataTypes", sourceName, "IngestionData");
		System.out.println("Method :  verifySourceFormatTextboxEnability");
		Log.info("Method : verifySourceFormatTextboxEnability");
		boolean flag = true;
		List<WebElement> options = getsourceColumnTypeDropdownOptions(firstColumnTypeDropdown);
		for (WebElement webElement : options) {
			String optionName = webElement.getAttribute("text");
			System.out.println("Option Name --> " + optionName);
			Log.info("Option Name --> " + optionName);
			if (!(optionName.equals("DECIMAL") || optionName.equals("TIMESTAMP"))) {
				System.out.println("Selecting Option Name --> " + optionName);
				Log.info("Selecting Option Name --> " + optionName);
				selectTypeForSourceColumn(sourceName, optionName);
				boolean tempFlag = WrapperFunctionUtilities.isElementPresent(
						sourceFormatTextboxDisabled(sourceName), sourceName + " is Disabled");

				if (!tempFlag) {
					flag = false;
				}
			}
		}
		selectTypeForSourceColumn(sourceName, "DECIMAL");
		boolean tempFlag6 = (WrapperFunctionUtilities.isElementPresent(sourceFormatTextbox(sourceName),
				sourceName + " Textbox"))
				&& (WrapperFunctionUtilities.isElementPresent(sourceFormatTextboxEnabled(sourceName),
				sourceName));

		selectTypeForSourceColumn(sourceName, "TIMESTAMP");
		boolean tempFlag7 = (WrapperFunctionUtilities.isElementPresent(sourceFormatTextbox(sourceName),
				sourceName + " Textbox"))
				&& (WrapperFunctionUtilities.isElementPresent(sourceFormatTextboxEnabled(sourceName),
				sourceName));
		if (!(tempFlag6 && tempFlag7 && flag)) {
			flag = false;
		}
		return flag;
	}

	public boolean verifyFileTypeDropdownValuesForTextFile() throws Exception {
		System.out.println("Method :  verifyFileTypeDropdownValuesForTextFile");
		Log.info("Method : verifyFileTypeDropdownValuesForTextFile");
		String csvTypeOption = ExcelReader.getValue("Ingestion", "CSV_FileType", "TestData");
		String fixedWidthTypeOption = ExcelReader.getValue("Ingestion", "FileType_FixedWidth", "TestData");
		boolean flag = true, tempFlag;
		format_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", "CSV_FileFormat", "TestData"));
		file_type_dropdown.click();
		WrapperFunctionUtilities.isElementVisible(10, CommonPage.hyperlink(csvTypeOption), driver);
		WrapperFunctionUtilities.isElementVisible(10, CommonPage.hyperlink(fixedWidthTypeOption), driver);
		WrapperFunctionUtilities.isElementVisible(10, CommonPage.hyperlink(fixedWidthTypeOption), driver);
		CommonPageFunctions.hyperlinkClick(fixedWidthTypeOption);
		//tempFlag = WrapperFunctionUtilities.waitForElementToBeInvisibileBy(delimiterTextBoxXpath(), 5, driver);
		tempFlag = WrapperFunctionUtilities.waitForElementToBeInvisibileBy(delimiterTextBoxXpath, 5, driver);
		System.out.println("TempFlag Visible" + tempFlag);
		if (!tempFlag) {
			flag = false;
		}
		file_type_dropdown.click();
		CommonPageFunctions.hyperlinkClick(csvTypeOption);
		tempFlag = WrapperFunctionUtilities.isElementVisible(3, delimiter, driver);

		if (!tempFlag) {
			flag = false;
		}
		delimiter.sendKeys(",");
		return flag;
	}

	public static boolean verifyInvalidTableNamErrorMessage() throws Exception {
		Log.info("Method : verifyInvalidTableNamErrorMessage");
		boolean temp = WrapperFunctionUtilities.isElementVisible(10, invalidTableNameError, driver);
		return temp;
	}

	public static boolean verifyStepNumberVisibilityAfterClick(String stepName) throws Exception {
		Log.info("Method : verifyStepNumberVisibilityAfterClick");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", IngestionPage.next_click);
		WrapperFunctionUtilities.waitForTime(1000);
		boolean temp = WrapperFunctionUtilities.isElementPresent(CommonPage.getStepName(stepName), stepName);
		return temp;
	}

	public static boolean verifyScheduleRecurringIngestionCheckbox() throws Exception {
		Log.info("Method : verifyScheduleRecurringIngestionCheckbox");
		boolean result = true, temp;
		temp = WrapperFunctionUtilities.isElementPresent(scheduleRecurringIngestionDisabled, "scheduleRecurringIngestionDisabled");
		if (!temp) {
			result = false;
		}
		WrapperFunctionUtilities.jsClick(scheduleRecurringIngestionCheckbox, driver);
		//temp = WrapperFunctionUtilities.waitForElementToBeInvisibileBy(scheduleRecurringIngestionDisabledXpath(), 10, driver);
		temp = WrapperFunctionUtilities.waitForElementToBeInvisibileBy(scheduleRecurringIngestionDisabledXpath, 10, driver);
		if (!temp) {
			result = false;
		}
		temp = WrapperFunctionUtilities.isElementPresent(startDatePicker, "startDatePicker");
		if (!temp) {
			result = false;
		}
		WrapperFunctionUtilities.scrollByVisibleElement(recurrencePatternDropdown, driver);

		Log.info("Returing From Method : verifyScheduleRecurringIngestionCheckbox");
		return result;
	}

	public static boolean verifySourceColumnHeadersInPreviewTable(String sheetName, String fileName, String Skipheader, String excelFormat) throws Exception {
		boolean flag = true, existenceOfColumn;
		int columnIndex = 0;
		System.out.println("Method :  verifySourceColumnHeadersInPreviewTable");
		Log.info("Method : verifySourceColumnHeadersInPreviewTable");
		sheetName = ExcelReader.getValue("Ingestion", sheetName, "TestData");
		fileName = ExcelReader.getValue("Ingestion", fileName, "TestData");
		excelFormat = ExcelReader.getValue("Ingestion", excelFormat, "TestData");
		String rowNum = ExcelReader.getValue("Ingestion", Skipheader, "TestData");
		LinkedHashMap<String, String> columnHeadersInExcel = ExcelReader.getTestDataFromExcelSheetWithRow(sheetName, fileName, Integer.parseInt(rowNum), excelFormat);
		System.out.println("columnHeadersInExcel HashMap" + columnHeadersInExcel);
		Set<String> columnNameHeader = columnHeadersInExcel.keySet();
		String actualColumnHeader = "";
		System.out.println("ColumnNames Set" + columnNameHeader);
		for (String columnName : columnNameHeader) {
			columnIndex++;
			actualColumnHeader = columnHeadersInExcel.get(columnName);
			System.out.println(actualColumnHeader);
			/*if (actualColumnHeader.contains(".")) {
				actualColumnHeader = actualColumnHeader.replace(".", " ");
			}*/
			WrapperFunctionUtilities.scrollByVisibleElement(dataPreviewColumnHeader(actualColumnHeader, columnIndex), driver);
			existenceOfColumn = WrapperFunctionUtilities.isElementPresent(dataPreviewColumnHeader(actualColumnHeader, columnIndex),
					actualColumnHeader);

			if (!existenceOfColumn) {
				flag = false;
			}
		}
		return flag;
	}

	//Entering Transactional info for Local CSV File Fixed Width
	public static void Transactional_Info_CSV_File_Fixed_Width(String format, String fileType, String loadType, String skipheader) throws Exception {

		format_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", format, "TestData"));
		file_type_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", fileType, "TestData"));
		file_load_type_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", loadType, "TestData"));
		skip_header_checkbox.click();
		skip_header_text.sendKeys(ExcelReader.getValue("Ingestion", skipheader, "TestData"));
	}

	//Wait For Non-Existence Of Loading Symbol while uploading file
	public static boolean waitForNonExistenceOfLoadingSymbol(int waitTime) throws Exception {
		boolean result = WrapperFunctionUtilities.waitForElementToBeInvisibileBy(fileUploadLoadingSymbol, waitTime, driver);
		return result;
	}

	public static boolean verifyPreviewErrorMessage() throws Exception {
		boolean result = WrapperFunctionUtilities.isElementPresent(previewErrorMessage, "previewErrorMessage");
		return result;
	}

	public static void setSourceFormatDetails_For_AllDataTypeCSV() throws Exception {

		System.out.println("Method :  setSourceFormatDetails_For_AllDataTypeCSV");
		Log.info("Method : setSourceFormatDetails_For_AllDataTypeCSV");

		/*
		 * setSourceFormatDetails("fundedDate", "mm/dd/yyyy");
		 * setSourceFormatDetails("temp", "(4,2)"); nextButtonSchemaTable.click();
		 * setSourceFormatDetails("location", "(10,8)");
		 */

		setSourceFormatDetails(ExcelReader.getValue("AllDataTypeCSV", "sourceColumnName_1", "IngestionData"),
				ExcelReader.getValue("AllDataTypeCSV", "sourceColumnFormat_1", "IngestionData"));

		setSourceFormatDetails(ExcelReader.getValue("AllDataTypeCSV", "sourceColumnName_2", "IngestionData"),
				ExcelReader.getValue("AllDataTypeCSV", "sourceColumnFormat_2", "IngestionData"));

		nextButtonSchemaTable.click();

		setSourceFormatDetails(ExcelReader.getValue("AllDataTypeCSV", "sourceColumnName_3", "IngestionData"),
				ExcelReader.getValue("AllDataTypeCSV", "sourceColumnFormat_3", "IngestionData"));

	}

	public static boolean runFirstSuccessfulIngestionOnHomePage() throws Exception {
		System.out.println("Method :  runFirstSuccessfulIngestionOnHomePage");
		Log.info("Method : runFirstSuccessfulIngestionOnHomePage");
		List<WebElement> countOfActiveIngestionRunButtons = driver.findElements(xpathFirstSuccessfulIngestionRunButton);
		Log.info("countOfActiveIngestionRunButtons  " + countOfActiveIngestionRunButtons.size());
		int counter=0;

		while (countOfActiveIngestionRunButtons.size() <= 0) {
			counter++;
			WrapperFunctionUtilities.scrollByVisibleElement(lastVisibleIngestionName, driver);
			countOfActiveIngestionRunButtons = driver.findElements(xpathFirstSuccessfulIngestionRunButton);
			Log.info("countOfActiveIngestionRunButtons  " + countOfActiveIngestionRunButtons.size());

			if(counter==5)
			{
				ingestionPage_RightArrow_WebButton.click();
				counter=0;
			}
		}

		WrapperFunctionUtilities.scrollByVisibleElement(runFirstSuccessfulIngestionRunButton, driver);
		String name = runFirstSuccessfulIngestionName.getText();
		runFirstSuccessfulIngestionRunButton.click();
		//WrapperFunctionUtilities.jsClick(runFirstSuccessfulIngestionRunButton, driver);
		WrapperFunctionUtilities.waitForElementVisibility(statusOfIngestion(name, "Pending"),60, driver);
		boolean flag = WrapperFunctionUtilities.isElementPresent(statusOfIngestion(name, "Pending"), "Pending Status");
		return flag;
	}

	public static boolean verifyDefaultContentOfEscapeCharacterAndQuoteCharacterTextboxes() throws Exception {
		boolean flag = true, actualResult;
		System.out.println("Method :  verifyDefaultContentOfEscapeCharacterAndQuoteCharacterTextboxes");
		Log.info("Method : verifyDefaultContentOfEscapeCharacterAndQuoteCharacterTextboxes");
		String valueInTextbox = quoteCharacterTextbox.getAttribute("value").trim();
		System.out.println("Value in Quote    " + valueInTextbox);
		String defaultQuoteCharacters = "\\\"";
		System.out.println("Value in defaultQuoteCharacters    " + defaultQuoteCharacters);
		actualResult = (valueInTextbox.contains(defaultQuoteCharacters)) ? true : false;
		if (!actualResult) {
			flag = false;
		}
		System.out.println("value of Flag after QuoteCharacter " + flag);
		valueInTextbox = escapeCharacterTextbox.getAttribute("value").trim();
		System.out.println("Value in escape    " + valueInTextbox);
		String defaultEscapeCharacters = "\\\\";
		System.out.println("Value in defaultEscapeCharacters    " + defaultEscapeCharacters);
		actualResult = (valueInTextbox.contains(defaultEscapeCharacters)) ? true : false;
		if (!actualResult) {
			flag = false;
		}
		System.out.println("value of Flag after Escape " + flag);

		return flag;
	}

	public static void setSourceFileLocation(String fileLocation) throws Exception {
		sourceFileLocation.clear();
		sourceFileLocation.sendKeys(ExcelReader.getValue("Ingestion", fileLocation, "TestData"));
	}

	// click on Save As Draft Button
	public static void clickSaveAsDraftButton() throws Exception {

		IngestionPage.saveAsDraft.click();
		WrapperFunctionUtilities.waitForTime(1000);
	}

	// click on Save As Draft Button
	public static void editExistingIngestion(String ingestionName) throws Exception {

		WrapperFunctionUtilities.scrollByVisibleElement(editIconByIngestionName(ingestionName), driver);
		editIconByIngestionName(ingestionName).click();

	}

	//Method Verifies the data in Preview Table by comparing the data read from Excel
	public static boolean verifySourceDataInPreviewTable(String sheetName, String fileName, String rowNumber, String excelFormat) throws Exception {
		boolean flag = true, existenceOfColumn;
		int columnIndex = 0;

		System.out.println("Method :  verifySourceColumnHeadersInPreviewTable");
		Log.info("Method : verifySourceColumnHeadersInPreviewTable");
		sheetName = ExcelReader.getValue("Ingestion", sheetName, "TestData");
		fileName = ExcelReader.getValue("Ingestion", fileName, "TestData");
		excelFormat = ExcelReader.getValue("Ingestion", excelFormat, "TestData");
		String rowNum = ExcelReader.getValue("Ingestion", rowNumber, "TestData");
		LinkedHashMap<String, String> columnHeadersInExcel = ExcelReader.getTestDataFromExcelSheetWithRow(sheetName, fileName, Integer.parseInt(rowNum), excelFormat);
		System.out.println("columnHeadersInExcel HashMap" + columnHeadersInExcel);
		Set<String> columnNameHeader = columnHeadersInExcel.keySet();
		String actualColumnData = "";
		System.out.println("ColumnNames Set" + columnNameHeader);
		for (String columnName : columnNameHeader) {
			columnIndex++;
			actualColumnData = columnHeadersInExcel.get(columnName);
			if (actualColumnData.equals("true")) {
				actualColumnData = actualColumnData.toUpperCase();
			}
			System.out.println(actualColumnData);
			WrapperFunctionUtilities.scrollByVisibleElement(dataPreviewCellValue(actualColumnData, columnIndex), driver);
			existenceOfColumn = WrapperFunctionUtilities.isElementPresent(dataPreviewCellValue(actualColumnData, columnIndex),
					actualColumnData);

			if (!existenceOfColumn) {
				flag = false;
			}
		}
		return flag;
	}

	public static boolean verifyDQMChecksDropdownForEachColumn() throws Exception {
		boolean flag = true, existenceOfDQMDDValue;
		Log.info("Method :  verifyDQMChecksDropdownForEachColumn");
		if (selectColumnCheckboxes().size() != ingestionPage_dqmAddIcon_WebList().size()) {
			flag = false;
			return flag;
		}
		for (WebElement qualityCheckForColumn : ingestionPage_dqmAddIcon_WebList()) {
			WrapperFunctionUtilities.scrollByVisibleElement(qualityCheckForColumn, driver);
			qualityCheckForColumn.click();
			ingestionPage_addCheckDQMPopup_WebElement.click();
			dqmDropdownInDialogBox.click();
			existenceOfDQMDDValue = WrapperFunctionUtilities.isElementPresent(continueIngestionWithErrorneousData, "continueIngestionWithErrorneousData");
			if (!existenceOfDQMDDValue) {
				flag = false;
			}
			existenceOfDQMDDValue = WrapperFunctionUtilities.isElementPresent(removeErrorneousData, "removeErrorneousData");
			if (!existenceOfDQMDDValue) {
				flag = false;
			}
			cancelButtonAddDQMDialogBox.click();
		}
		return flag;
	}

	public static void scheduleADailyIngestion(int numberOfDays) throws Exception {
		Log.info("Method :  scheduleADailyIngestion");
		WrapperFunctionUtilities.jsClick(scheduleRecurringIngestionCheckbox, driver);
		WrapperFunctionUtilities.scrollByVisibleElement(recurrenceOptionalCheckBox, driver);
		WrapperFunctionUtilities.jsClick(recurrenceOptionalCheckBox, driver);
		WrapperFunctionUtilities.jsClick(endAfterRadioButton, driver);
		endAfterTextBox.sendKeys(Integer.toString(numberOfDays));
	}

	public static boolean verifyIngestionRecurrenceToggleIsActive(String ingestionName) {
		Log.info("Method :  verifyIngestionRecurrenceIsActive");
		String toggleStatus = recurrenceButtonForIngestion(ingestionName).getAttribute("active");
		boolean flag = toggleStatus != null ? true : false;
		return flag;
	}

	//This method sets unique IngestionID and TableName for Ingestion
	public static String setUniqueIngestionIDAndTableName() throws Exception {
		Log.info("Method :  setUniqueIngestionIDAndTableName");
		String ingestionID = CommonPageFunctions.generateRandomString();
		WrapperFunctionUtilities.waitForElementVisibility(ingestion_name, 5, driver);
		ingestion_name.sendKeys(ingestionID);
		WrapperFunctionUtilities.waitForElementVisibility(table_name, 5, driver);
		table_name.sendKeys(ingestionID);
		return ingestionID;
	}

	public static void waitForPreviewTableContentToLoad() {
		Log.info("Method :  waitForPreviewTableContentToLoad");
		WrapperFunctionUtilities.waitForElementToBeInvisibileBy(ingestionPage_LoadingSymbolPreviewTable_Text, 10, driver);

	}

	// Click on Previous Button to navigate to previous screen of Ingestion
	public static void clickOnPreviousButton() {
		Log.info("Method :  clickOnPreviousButton");
		WrapperFunctionUtilities.waitForElementVisibility(ingestionPage_Previous_Button, 10, driver);
		ingestionPage_Previous_Button.click();
	}

	public static boolean verifyExistenceOfNullCharacterInPreviewTable(String fileName, String sheetName, String columnNumber) throws Exception {
		Log.info("Method :  verifyExistenceOfNullCharacterInPreviewTable");
		boolean flag = true, existenceOfColumn = true;
		String columnIndex = ExcelReader.getValue(sheetName, columnNumber, fileName);
		existenceOfColumn = WrapperFunctionUtilities.isElementPresent(ingestionPage_NullCharacterInPreviewTable_WebElement(columnIndex),
				"Null Character");
		if (!existenceOfColumn) {
			flag = false;
		}
		return flag;
	}

	// This method verifies all the dropdown values in Source column Type dropdown
	public static boolean verifySourceColumnTypeDropDownValues() throws Exception {
		Log.info("Method :  verifySourceColumnTypeDropDownValues");
		String columnTypeDDValue = "";
		boolean flag = true, columnExistence = false;
		HashMap<String, String> columnHeadersInExcel = ExcelReader.getTestDataFromExcel("DataTypesInColumnTypeDropDown", "IngestionData");
		String sourceColumnName = columnHeadersInExcel.get("sourceColumnName_1");
		for (String columnType : columnHeadersInExcel.keySet()) {
			if (columnType.contains("sourceColumnType")) {
				columnTypeDDValue = columnHeadersInExcel.get(columnType);
				WrapperFunctionUtilities.waitForElementVisibility(sourceColumnTypeDropdown(sourceColumnName), 5, driver);
				WrapperFunctionUtilities.scrollByVisibleElement(sourceColumnTypeDropdown(sourceColumnName), driver);
				sourceColumnTypeDropdown(sourceColumnName).click();
				columnExistence = WrapperFunctionUtilities.isElementPresent(sourceColumnTypeDropdownOption(columnTypeDDValue), columnTypeDDValue);
				if (!columnExistence) {
					flag = false;
				}
				WrapperFunctionUtilities.jsClick(sourceColumnTypeDropdownOption(columnTypeDDValue), driver);
			}
		}
		return flag;
	}

	public static boolean verifySelectionOfColumns(ArrayList<String> selectedColumnList, ArrayList<String> deselectedColumnList) throws Exception {
		boolean flag = true;
		for (String columnName : selectedColumnList) {
			WrapperFunctionUtilities.scrollByVisibleElement(selectSourceColumnCheckbox(columnName),driver);
			if (!selectSourceColumnCheckbox(columnName).isSelected()) {
				Log.info(columnName + " is not Selected, changes in selection observed");
				flag = false;
			}
		}
		for (String columnName : deselectedColumnList) {
			WrapperFunctionUtilities.scrollByVisibleElement(selectSourceColumnCheckbox(columnName),driver);
			if (selectSourceColumnCheckbox(columnName).isSelected()) {
				Log.info(columnName + " is Selected, changes in selection observed");
				flag = false;
			}
		}
		return flag;
	}

	public static boolean verifySourceColumnSelectionIsRetainedOnSavingDraftAndIngestionRun(String ingestionID, String sheetName, String fileName,String columnHeaderRowNum,String excelFormat) throws Exception {
		Log.info("Method :  verifySourceColumnSelectionIsRetainedOnSavingDraftAndIngestionRun");
		int count = 0, numberOfColumns;
		boolean flag = true, selectionOfColumnResult = false;
		ArrayList<String> selectedColumns = new ArrayList<>();
		ArrayList<String> deselectedColumns = new ArrayList<>();
		sheetName = ExcelReader.getValue("Ingestion", sheetName, "TestData");
		fileName = ExcelReader.getValue("Ingestion", fileName, "TestData");
		excelFormat = ExcelReader.getValue("Ingestion", excelFormat, "TestData");
		int columnHeaderRowNumber = Integer.parseInt(ExcelReader.getValue("Ingestion", columnHeaderRowNum, "TestData"));
		LinkedHashMap<String, String> columnHeadersInExcel = ExcelReader.getTestDataFromExcelSheetWithRow(sheetName, fileName,columnHeaderRowNumber,excelFormat);
		numberOfColumns = columnHeadersInExcel.size();
		for (String columnName : columnHeadersInExcel.keySet()) {
			count++;
			if (count > numberOfColumns / 2) {
				deselectSourceColumnFromIngestion(columnName);
				deselectedColumns.add(columnName);
			} else {
				selectedColumns.add(columnName);
			}
		}
		CommonPageFunctions.switchscreen("Step 3 of 3");
		IngestionPageFunctions.Ingestion_Location("", "Redshift", "AdaptorName", "SchemaName");
		clickSaveAsDraftButton();
		CommonPageFunctions.verifyProcess(ingestionID);
		editExistingIngestion(ingestionID);
		WrapperFunctionUtilities.waitForElementVisibility(ingestion_name, 10, driver);
		CommonPageFunctions.switchscreen("Step 2 of 3");
		selectionOfColumnResult = verifySelectionOfColumns(selectedColumns,deselectedColumns);
		if(!selectionOfColumnResult)
		{
			flag = false;
		}
		CommonPageFunctions.switchscreen("Step 3 of 3");
		IngestionPageFunctions.clickFinishButton();
		WrapperFunctionUtilities.waitForTime(3000);
		CommonPageFunctions.verifyProcess(ingestionID);
		editExistingIngestion(ingestionID);
		WrapperFunctionUtilities.waitForElementVisibility(ingestion_name, 10, driver);
		CommonPageFunctions.switchscreen("Step 2 of 3");
		selectionOfColumnResult = verifySelectionOfColumns(selectedColumns,deselectedColumns);
		if(!selectionOfColumnResult)
		{
			flag = false;
		}
		return flag;
}
		public static boolean verifyClusterTypeDropDownValues(String emrCluster, String Redshift){
			cluster_type_dropdwn.click();
			WrapperFunctionUtilities.waitForTime(1000);
			if((CommonPage.hyperlink(emrCluster).isDisplayed())==true && (CommonPage.hyperlink(Redshift).isDisplayed())==true){
				return true;
			}
			return false;
	}

	public  static void enterQuoteEscapeCharacter(String quoteCharacter, String escapeCharacter) throws Exception {
		quoteCharacterInput.clear();
		quoteCharacterInput.sendKeys(ExcelReader.getValue("Ingestion", quoteCharacter, "TestData"));
		escapeCharacterInput.clear();
		escapeCharacterInput.sendKeys(ExcelReader.getValue("Ingestion", escapeCharacter, "TestData"));
	}

	public static boolean verifyPrimaryKeyDropdownInDQM(String sheetName, String fileName,String columnHeaderRowNum,String excelFormat) throws Exception {
		Log.info("Method :  verifyPrimaryKeyDropdownInDQM");
		boolean flag = true;
		sheetName = ExcelReader.getValue("Ingestion", sheetName, "TestData");
		fileName = ExcelReader.getValue("Ingestion", fileName, "TestData");
		excelFormat = ExcelReader.getValue("Ingestion", excelFormat, "TestData");
		int columnHeaderRowNumber = Integer.parseInt(ExcelReader.getValue("Ingestion", columnHeaderRowNum, "TestData"));
		LinkedHashMap<String, String> columnHeadersInExcel = ExcelReader.getTestDataFromExcelSheetWithRow(sheetName, fileName,columnHeaderRowNumber,excelFormat);
		WrapperFunctionUtilities.scrollByVisibleElement(ingestionPage_PrimaryKey_DropDown,driver);
		WrapperFunctionUtilities.jsClick(ingestionPage_PrimaryKey_DropDown, driver);
		WrapperFunctionUtilities.waitForTime(2000);
		for (String columnName:columnHeadersInExcel.keySet())
		{
			WrapperFunctionUtilities.scrollByVisibleElement(ingestionPage_PrimaryKeyDropDownOptions_WebElement(columnName), driver);
			WrapperFunctionUtilities.jsClick(ingestionPage_PrimaryKeyDropDownOptions_WebElement(columnName), driver);

		}
		return flag;
	}

	public static boolean verifyIfDataDoesNotPassCheckDropDownValues() throws Exception {
		Log.info("Method :  verifyPrimaryKeyDropdownInDQM");
		boolean flag = true,existenceOfOption=false;
		WrapperFunctionUtilities.scrollByVisibleElement(ingestionPage_IfDataDoesNotPassCheck_DropDown,driver);
		WrapperFunctionUtilities.jsClick(ingestionPage_IfDataDoesNotPassCheck_DropDown, driver);
		existenceOfOption = WrapperFunctionUtilities.isElementPresent(continueIngestionWithErrorneousData,"continueIngestionWithErrorneousData");
		if(!existenceOfOption)
		{
			flag=false;
		}
		WrapperFunctionUtilities.jsClick(continueIngestionWithErrorneousData, driver);
		WrapperFunctionUtilities.jsClick(ingestionPage_IfDataDoesNotPassCheck_DropDown, driver);
		existenceOfOption= WrapperFunctionUtilities.isElementPresent(removeErrorneousData,"removeErrorneousData");
		WrapperFunctionUtilities.jsClick(removeErrorneousData, driver);
		if(!existenceOfOption)
		{
			flag=false;
		}
		return flag;
	}

	public static void selectContinueIngestionWithErrorneousDataOption() throws Exception {
		WrapperFunctionUtilities.scrollByVisibleElement(ingestionPage_IfDataDoesNotPassCheck_DropDown,driver);
		WrapperFunctionUtilities.jsClick(ingestionPage_IfDataDoesNotPassCheck_DropDown, driver);
		WrapperFunctionUtilities.jsClick(continueIngestionWithErrorneousData, driver);
	}
	public static void setSourceFormatDetails_For_OracleLargeTable() throws Exception {

		System.out.println("Method :  setSourceFormatDetails_For_OracleLargeTable");
		Log.info("Method : setSourceFormatDetails_For_OracleLargeTable");
		setSourceFormatDetails(ExcelReader.getValue("OracleIngestionWithLimitedCol", "sourceColumnName_1", "IngestionData"),
				ExcelReader.getValue("OracleIngestionWithLimitedCol", "sourceColumnFormat_1", "IngestionData"));
	}

	public static void deselectSourceColumns_For_OracleLargeTable() throws Exception {
		System.out.println("Method :  deselectSourceColumns_For_OracleLargeTable");
		Log.info("Method : deselectSourceColumns_For_OracleLargeTable");
		deselectSourceColumnFromIngestion(ExcelReader.getValue("OracleIngestionWithLimitedCol", "deselectSourceColumn_1", "IngestionData"));
		deselectSourceColumnFromIngestion(ExcelReader.getValue("OracleIngestionWithLimitedCol", "deselectSourceColumn_2", "IngestionData"));
		deselectSourceColumnFromIngestion(ExcelReader.getValue("OracleIngestionWithLimitedCol", "deselectSourceColumn_3", "IngestionData"));
	}


	public  static void addDQM(String SheetName, String FileName, String inclusionValue) throws Exception {
		excel = new Xls_Reader(prop.getProperty("testDataFilePath"));
		HashMap<String, String> columnDqmValues = ExcelReader.getTestDataFromExcel(SheetName,FileName);
		for(String i : columnDqmValues.keySet()){
			WrapperFunctionUtilities.jsClick(clickAddDQMIcon(i),driver);
			ingestionPage_addCheckDQMPopup_WebElement.click();
			ingestionDefineSchemaPage_dqmSelection_Dropdown.click();
			CommonPageFunctions.hyperlinkClick(columnDqmValues.get(i));
			if(columnDqmValues.get(i).contains("Inclusion")){
				ingestionDefineSchemaPage_dqmInclusion_TextBox.sendKeys(ExcelReader.getValue("Ingestion",inclusionValue,"TestData"));
			}
			ingestionDefineSchemaPageDQMPOPUP_Save_Button.click();
		}
	}

	public static boolean verifyDQMsAddedAtSchemaDefinePage(String SheetName, String FileName) {
		excel = new Xls_Reader(prop.getProperty("testDataFilePath"));
		boolean result = false;
		HashMap<String, String> columnDqmValues = ExcelReader.getTestDataFromExcel(SheetName, FileName);
		for (String i : columnDqmValues.keySet()) {
			if (columnDqmValues.get(i).equals("Decimal") || columnDqmValues.get(i).equals("Boolean")) {
				columnDqmValues.put(i, "Data Type");
			}
			result = WrapperFunctionUtilities.isElementPresent(verifyDQMAdded(i, columnDqmValues.get(i)), "DQM for " + i + " is " + columnDqmValues.get(i));
		}
		return result;
	}
	public static void searchAndOpenExistingIngestion(String ingestionName) throws Exception {
		Log.info("Method : searchAndOpenExistingIngestion");
		ingestionPage_SearchIngestion_Textbox.sendKeys(ingestionName);
		ingestionPage_ApplyFilter_Button.click();
		WrapperFunctionUtilities.waitForElementVisibility(editIconByIngestionName(ingestionName),30,driver);
		WrapperFunctionUtilities.jsClick(editIconByIngestionName(ingestionName),driver);
		WrapperFunctionUtilities.waitForElementVisibility(ingestion_name, 30, driver);
	}

	public static boolean verifyColumnSelectionIsRetainedWhileNavigation() throws Exception {
		Log.info("Method :  verifySourceColumnSelectionIsRetainedWhileNavigation");
		int rowIndex = 0;
		boolean flag = true, selectionOfColumnResult = false;
		HashMap<Integer,String> columnSelectionStatus = new HashMap<>();
		for (WebElement sourceColumn: ingestionPage_SourceColumnName_RadioButton())
		{
			rowIndex++;
			boolean statusOfSelectionOfSourceColumn = sourceColumn.isSelected();
			if (statusOfSelectionOfSourceColumn==false) {
				Log.info("Inside If 1");
				WrapperFunctionUtilities.scrollByVisibleElement(sourceColumn, driver);
				WrapperFunctionUtilities.jsClick(sourceColumn, driver);
				columnSelectionStatus.put(rowIndex, "SourceColumn");

			} else {
				Log.info("Inside else");
				WrapperFunctionUtilities.scrollByVisibleElement(ingestionPage_PredictedColumnByRowIndex_RadioButton(rowIndex), driver);
				WrapperFunctionUtilities.jsClick(ingestionPage_PredictedColumnByRowIndex_RadioButton(rowIndex), driver);
				columnSelectionStatus.put(rowIndex, "PredictedColumn");
			}
		}

		clickOnPreviousButton();
		WrapperFunctionUtilities.waitForTime(3000);
		CommonPageFunctions.switchscreen("Step 2 of 3");
		selectionOfColumnResult = verifySelectionOfSourceAndPredictedColumns(columnSelectionStatus);
		if(!selectionOfColumnResult)
		{
			flag = false;
		}
		CommonPageFunctions.switchscreen("Step 3 of 3");
		clickOnPreviousButton();
		selectionOfColumnResult = verifySelectionOfSourceAndPredictedColumns(columnSelectionStatus);
		if(!selectionOfColumnResult)
		{
			flag = false;
		}
		return flag;
	}

	public static boolean verifySelectionOfSourceAndPredictedColumns(HashMap<Integer,String> columnSelectionStatus) throws Exception {
		boolean flag = true;
		for(Integer rowIndex:columnSelectionStatus.keySet())
		{
			if(columnSelectionStatus.get(rowIndex).equals("SourceColumn"))
			{
				WrapperFunctionUtilities.scrollByVisibleElement(ingestionPage_SourceColumnByRowIndex_RadioButton(rowIndex),driver);
				if (!ingestionPage_SourceColumnByRowIndex_RadioButton(rowIndex).isSelected()) {
					Log.info("sourceColumnRadioButton at row "+rowIndex+" is not Selected, changes in selection observed");
					flag = false;
				}
			}else{
				WrapperFunctionUtilities.scrollByVisibleElement(ingestionPage_PredictedColumnByRowIndex_RadioButton(rowIndex),driver);
				if (!ingestionPage_PredictedColumnByRowIndex_RadioButton(rowIndex).isSelected()) {
					Log.info("PredictedRadioButton at row "+rowIndex+" is not Selected, changes in selection observed");
					flag = false;
				}
			}
		}
		return flag;
	}

	public static boolean verifySourceColumnSelectionIsRetainedOnSavingDraftAndIngestionRun(String ingestionIDForRunningIngestion) throws Exception {
		Log.info("Method :  verifySourceColumnSelectionIsRetainedOnSavingDraftAndIngestionRun");
		int rowIndex = 0;
		boolean flag = true, selectionOfColumnResult = false, statusOfSelectionOfSourceColumn= false;
		HashMap<Integer,String> columnSelectionStatus = new HashMap<>();
		WrapperFunctionUtilities.waitForTime(3000);
		CommonPageFunctions.switchscreen("Step 2 of 3");
		for (WebElement sourceColumn: ingestionPage_SourceColumnName_RadioButton())
		{
			rowIndex++;
			 statusOfSelectionOfSourceColumn = sourceColumn.isSelected();
			if (statusOfSelectionOfSourceColumn==false) {
				Log.info("Inside If 1");
				WrapperFunctionUtilities.scrollByVisibleElement(sourceColumn, driver);
				WrapperFunctionUtilities.jsClick(sourceColumn, driver);
				columnSelectionStatus.put(rowIndex, "SourceColumn");

			} else {
				Log.info("Inside else");
				WrapperFunctionUtilities.scrollByVisibleElement(ingestionPage_PredictedColumnByRowIndex_RadioButton(rowIndex), driver);
				WrapperFunctionUtilities.jsClick(ingestionPage_PredictedColumnByRowIndex_RadioButton(rowIndex), driver);
				columnSelectionStatus.put(rowIndex, "PredictedColumn");
			}
		}

		CommonPageFunctions.switchscreen("Step 3 of 3");
		WrapperFunctionUtilities.waitForTime(2000);
		clickSaveAsDraftButton();
		CommonPageFunctions.verifyProcess(ingestionIDForRunningIngestion);
		editExistingIngestion(ingestionIDForRunningIngestion);
		WrapperFunctionUtilities.waitForElementVisibility(ingestion_name, 10, driver);
		WrapperFunctionUtilities.waitForTime(3000);
		CommonPageFunctions.switchscreen("Step 2 of 3");
		rowIndex = 0;
		selectionOfColumnResult =  verifySelectionOfSourceAndPredictedColumns(columnSelectionStatus);
		if(!selectionOfColumnResult)
		{
			flag = false;
		}
		for (WebElement sourceColumn: ingestionPage_SourceColumnName_RadioButton())
		{
			rowIndex++;
			 statusOfSelectionOfSourceColumn = sourceColumn.isSelected();
			if (statusOfSelectionOfSourceColumn==false) {
				Log.info("Inside If 1");
				WrapperFunctionUtilities.scrollByVisibleElement(sourceColumn, driver);
				WrapperFunctionUtilities.jsClick(sourceColumn, driver);
				columnSelectionStatus.put(rowIndex, "SourceColumn");

			} else {
				Log.info("Inside else");
				WrapperFunctionUtilities.scrollByVisibleElement(ingestionPage_PredictedColumnByRowIndex_RadioButton(rowIndex), driver);
				WrapperFunctionUtilities.jsClick(ingestionPage_PredictedColumnByRowIndex_RadioButton(rowIndex), driver);
				columnSelectionStatus.put(rowIndex, "PredictedColumn");
			}
		}
		WrapperFunctionUtilities.waitForTime(3000);
		CommonPageFunctions.switchscreen("Step 3 of 3");
		IngestionPageFunctions.clickFinishButton();
		WrapperFunctionUtilities.waitForTime(2000);
		CommonPageFunctions.verifyProcess(ingestionIDForRunningIngestion);
		editExistingIngestion(ingestionIDForRunningIngestion);
		WrapperFunctionUtilities.waitForElementVisibility(ingestion_name, 10, driver);
		WrapperFunctionUtilities.waitForTime(2000);
		CommonPageFunctions.switchscreen("Step 2 of 3");
		selectionOfColumnResult = verifySelectionOfSourceAndPredictedColumns(columnSelectionStatus);
		if(!selectionOfColumnResult)
		{
			flag = false;
		}
		return flag;
	}
	public static void deselectSourceColumns_For_MSSQLTable() throws Exception {
		System.out.println("Method :  deselectSourceColumns_For_MSSQLTable");
		Log.info("Method : deselectSourceColumns_For_MSSQLTable");
		deselectSourceColumnFromIngestion(ExcelReader.getValue("MSSQLIngestionWithLimitedCol", "deselectSourceColumn_1", "IngestionData"));
		deselectSourceColumnFromIngestion(ExcelReader.getValue("MSSQLIngestionWithLimitedCol", "deselectSourceColumn_2", "IngestionData"));
		deselectSourceColumnFromIngestion(ExcelReader.getValue("MSSQLIngestionWithLimitedCol", "deselectSourceColumn_3", "IngestionData"));
	}

	public static void Transactional_Info_RDBMS_Adaptor_UsingSearchTextBox(String loadType, String tableName) throws Exception {
		Log.info("Method : Transactional_Info_RDBMS_Adaptor_UsingSearchTextBox");
		WrapperFunctionUtilities.waitForElementVisibility(file_load_type_dropdown,10,driver);
		file_load_type_dropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("Ingestion", loadType, "TestData"));
		WrapperFunctionUtilities.scrollByVisibleElement(selectTableClick,driver);
		ingestionPage_SelectTable_Dropdown.click();
		tableDropDownInput.sendKeys(ExcelReader.getValue("Ingestion", tableName, "TestData"));
		WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(ExcelReader.getValue("Ingestion", tableName, "TestData")), driver);
	}

	public static void selectExistingAdaptorUsingSearch(String sheetName, String adaptorName) throws Exception {
		//selectAdaptor.click();
		WrapperFunctionUtilities.jsClick(selectAdaptor, driver);
		selectExistingAdaptorInput.sendKeys(ExcelReader.getValue(sheetName,adaptorName,"TestData"));
		WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(ExcelReader.getValue(sheetName, adaptorName, "TestData")), driver);
		//CommonPageFunctions.hyperlinkClick(ExcelReader.getValue(sheetName,adaptorName,"TestData"));
	}
	public static boolean verifyClusterTypeDropdownOptions(String clusterType) throws Exception {
		Log.info("Method : verifyClusterTypeDropdownOptions");
		boolean flag= true;
		Select clusterTypeDropdown = new Select(ingestionPage_ClusterType_Dropdown);
		flag = clusterTypeDropdown.getOptions().size()!=2?false:flag;
		Log.info("Value of Flag after verifying number of Options " + flag);
		flag = !clusterTypeDropdown.getOptions().get(0).getAttribute("title").equals("Select")?false:flag;
		Log.info("Value of Flag after verifying First Options " + flag);
		WrapperFunctionUtilities.isElementPresent(cluster_type_dropdwn, clusterType);
		Log.info("Cluster Type DropDown Present");
		cluster_type_dropdwn.click();
		CommonPageFunctions.hyperlinkClick(clusterType);
		WrapperFunctionUtilities.waitForTime(1000);
		return flag;
	}

	public static boolean verifyIngestionDeletionInRunningState(String dataCenterName){
		WrapperFunctionUtilities.waitForTime(2000);
		boolean result = WrapperFunctionUtilities.isElementPresent(DataCenterPage_DeleteDataCenter_Icon(dataCenterName),"Disabled delete icon");
		System.out.println("Delete icon present"+ result);
		return result;
	}

	public static void addNewSourceColumn(String aliasName, String dataType) throws Exception {
		WrapperFunctionUtilities.waitForTime(1000);
		WrapperFunctionUtilities.waitForElementVisibility(dataCenterPage_AddColumn_Link,2000,driver);
		WrapperFunctionUtilities.jsClick(dataCenterPage_AddColumn_Link,driver);
		List<WebElement> numberOfColumns = getNumberOfColumnsDefineSchemaPage();
		int lastColumnNumber = numberOfColumns.size();
		DataCenterPage_AliasName_input(lastColumnNumber).sendKeys(aliasName);
		DataCenterPage_UserAddedSourceColumn_DatatypeDropDown(lastColumnNumber).click();
		WrapperFunctionUtilities.jsClick(DataCenterPage_UserAddedSourceColumn_DatatypeDropDown(lastColumnNumber),driver);
		WrapperFunctionUtilities.waitForTime(1000);
		DataCenterPage_SelectDatatypeCustomCol_link(dataType,lastColumnNumber).click();
	}
	public static boolean verifyUserAddedSourceColumnDetailsRetained(String aliasName, String dataType) throws Exception {
		List<WebElement> numberOfColumns = getNumberOfColumnsDefineSchemaPage();
		int lastColumnNumber = numberOfColumns.size();
		WrapperFunctionUtilities.waitForTime(1000);
		String aliasNameDisplayed = DataCenterPage_AliasName_input(lastColumnNumber).getAttribute("value");
		String dataTypeDisplayed = DataCenterPage_UserAddedSourceColumn_DatatypeDropDown(lastColumnNumber).getText();
		System.out.println("Alias name displayed: "+ aliasNameDisplayed+ "  DataType displayed: " + dataTypeDisplayed );
		if(aliasNameDisplayed.equals(aliasName)&&dataTypeDisplayed.equals(dataType)){
			return true;
		}
		return false;
	}
}

