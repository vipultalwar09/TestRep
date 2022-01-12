package com.RDM.Merger.testcases.DataQuery;

import com.RDM.Merger.locators.AdaptorsPage;
import com.RDM.Merger.locators.DataQueryPage;
import com.RDM.Merger.locators.IngestionPage;
import com.RDM.Merger.locators.LoginPage;
import com.RDM.Merger.pagefunctions.AdaptorsPageFunctions;
import com.RDM.Merger.pagefunctions.CommonPageFunctions;
import com.RDM.Merger.pagefunctions.DataQueryFunctions;
import com.RDM.Merger.pagefunctions.IngestionPageFunctions;
import com.RDM.Merger.testcases.Aa_Login.ExcelReader;
import com.RDM.Merger.testcases.Aa_Login.LoginPageTest;
import com.RDM.Merger.testcases.Adaptors.S3AdaptorPageTest;
import com.idm.pagefunctions.IDMLoginPageFunctions;
import com.idm.testcases.IDMLoginPageTest;
import com.utility.base.WrapperFunctionUtilities;
import com.utility.logger.Log;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class DataQueryTest extends DataQueryFunctions {

    CommonPageFunctions common_page;
    DataQueryPage dataQueryPage;
    AdaptorsPage adaptorsPage;
    static boolean result;
    static String dataQueryName;
    static SoftAssert softAssert;
    public DataQueryTest()
    {
        super();
    }

    @BeforeMethod(groups = {"Smoke","Regression_Medium_Priority","Sanity","Regression_High_Priority"})
    public void initialization(){
        dataQueryPage = new DataQueryPage();
        common_page = new CommonPageFunctions();
        adaptorsPage =new AdaptorsPage();
        CommonPageFunctions.navigateToURL(prop.getProperty("url"));
        WrapperFunctionUtilities.waitForPageToLoad(30, driver);
        //CommonPageFunctions.selectRDHEnvironmentIfDisplayed("Common","Environment");
        softAssert = new SoftAssert();
    }

    // Verify End to end schedule extraction to local -- RDH-2000
    @Test(groups={"Smoke"})
    public static void verifyEndToEndScheduleExtractionToLocal() throws Exception {
        Log.info("Method : verifyEndToEndScheduleExtractionToLocal");
        createNewDataQuery();
        enterDataSourceInformationForRedShift("ClusterType_RedShift", "RedShift_Adaptor1", "RedShift_TableName1");
        result = switchScreen("Step 2 of 3");
        Assert.assertTrue(result);
        result = switchScreen("Step 3 of 3");
        Assert.assertTrue(result);
        dataQueryName = TransactionalInfo_Local_Parquet_Extraction("ExtractionLocation_Local", "FileFormat_Parquet");
        scheduleRecurringExtraction("RecurrenceTime");
        clickFinishButton();
        WrapperFunctionUtilities.waitForTime(3000);
        boolean result = CommonPageFunctions.verifyProcess(dataQueryName);
        Assert.assertTrue(result);
        result = verifyDataQueryRecurrenceToggleIsActive(dataQueryName);
        Assert.assertTrue(result);
    }

    @Test(groups={"Smoke"})
    //Verify scenario when user selects RedShift from the Source Drop-Down. (RedShift Extraction is successful) -- RDH-2498
    public static void verifyUserSelectsRedShiftFromTheSourceDropDownAndRedShiftExtractionIsSuccessful() throws Exception {
        Log.info("Method : verifyUserSelectsRedShiftFromTheSourceDropDownAndRedShiftExtractionIsSuccessful");
        createNewDataQuery();
        result = verifyEMRDatabaseIsnNotPopulatedInsideRedShiftAdaptor("ClusterType_RedShift", "RedShift_Adaptor1", "EMR_Database1");
        Assert.assertTrue(result);
        selectTableAndSchemaNameForRedShiftAdaptor("RedShift_TableName1");
        result = switchScreen("Step 2 of 3");
        Assert.assertTrue(result);
        result = switchScreen("Step 3 of 3");
        Assert.assertTrue(result);
        dataQueryName = TransactionalInfo_Local_Parquet_Extraction("ExtractionLocation_Local", "FileFormat_Parquet");
        clickFinishButton();
        WrapperFunctionUtilities.waitForTime(3000);
        boolean result = CommonPageFunctions.verifyProcess(dataQueryName);
        Assert.assertTrue(result);
    }

    @Test(groups={"Regression_High_Priority"})
    //EDGE-18522 This test case is to verify that the user is able to edit a data query object from the edit action button on the data query landing page
    public static void verifyEditDataQuery() throws Exception {
        Log.info("Method : verifyEditDataQuery");
        createNewDataQuery();
        enterDataSourceInformationForRedShift("ClusterType_RedShift", "RedShift_Adaptor1", "RedShift_TableName1");
        result = switchScreen("Step 2 of 3");
        Assert.assertTrue(result);
        result = switchScreen("Step 3 of 3");
        Assert.assertTrue(result);
        dataQueryName = TransactionalInfo_Local_Parquet_Extraction("ExtractionLocation_Local", "FileFormat_Parquet");
        ClickOnSaveAsDraft();
        boolean result = CommonPageFunctions.verifyProcess(dataQueryName);
        Assert.assertTrue(result);
        editDataQuery(dataQueryName,"RedShift_TableName2");
        result = switchScreen("Step 2 of 3");
        Assert.assertTrue(result);
        result = switchScreen("Step 3 of 3");
        Assert.assertTrue(result);
        ClickOnSaveAsDraft();
        Assert.assertTrue(verifyDataQueryRedshiftTableUpdatedOnEdit(dataQueryName,"RedShift_TableName2"));
        Assert.assertTrue(deleteDataQuery(dataQueryName));
    }

    //This test case is to verify that the user is able to re-run a data query object from the run action button on the data landing page -- EDGE-18520
    @Test(groups = {"Regression_High_Priority"})
    public void verifyDataQueryIsReTriggeredUsingRunButton() throws Exception {
        Log.info("Method : verifyDataQueryIsReTriggeredUsingRunButton");
        CommonPageFunctions.selectDataMenuOptions("DataMenu", "DataQueryTabName");
        WrapperFunctionUtilities.waitForTime(2000);
        boolean status = runFirstSuccessfulDataQueryOnHomePage();
        Assert.assertEquals(status, true,"Failed to run Ingestion");
    }

    //This test case is to verify that if the user is selecting 'custom query' in Data Query then user must be directly navigated to Step 3 -- EDGE-18588
    @Test(groups = {"Regression_High_Priority"})
    public void verifyForCustomDataQueryUserIsNavigatedToStep3() throws Exception {
        Log.info("Method : verifyForCustomDataQueryUserIsNavigatedToStep3");
        CommonPageFunctions.selectDataMenuOptions("DataMenu", "DataQueryTabName");
        createNewDataQuery();
        createNewCustomQuery("ClusterType_EMR","EMR_Database1", "CustomQuery");
        result = switchScreen("Step 3 of 3");
        Assert.assertTrue(result,"User dint land on Step 3 Page");
    }

    //This test case is to verify that on the Define Schema Page the user is able to see column names, type and apply filter section -- EDGE-18589
    //This test case is to verify that on the Define Schema Page user is able to apply sorting on the column name and datatype -- EDGE-18590
    //This test is to verify that on the Define Schema Page when user searches any incorrect column name in the search filter then no records must be displayed -- EDGE-18599
    //This test is to verify that on the Define Schema Page user is able to search column name using the search edit box -- EDGE-18592
    @Test(groups = {"Regression_High_Priority"})
    public void verifyDefineSchemaPageHasColumnNamesTypeAndApplyFilterSection() throws Exception {
        Log.info("Method : verifyDefineSchemaPageHasColumnNamesTypeAndApplyFilterSection");
        CommonPageFunctions.selectDataMenuOptions("DataMenu", "DataQueryTabName");
        createNewDataQuery();
        //enterDataSourceInformationForEMR("ClusterType_EMR","EMR_Database1","EMR_TableName1");
        enterDataSourceInformationForRedShift("ClusterType_RedShift", "RedShift_Adaptor1", "RedShift_TableName1");
        result = switchScreen("Step 2 of 3");
        softAssert.assertTrue(result);
        result = verifyExistenceOfColumnName_DataType_FilterSection();
        softAssert.assertTrue(result, "Failed to verify Assertion : verifyExistenceOfColumnName_DataType_FilterSection");
        result = verifySortingOfColumnNameFields();
        softAssert.assertTrue(result, "Failed to verify Assertion : verifySortingOfColumnNameFields");
        result = verifySortingOfDataTypeColumn();
        softAssert.assertTrue(result, "Failed to verify Assertion : verifySortingOfDataTypeColumn");
        result = verifySearchTextBoxFunctionalityOnDefineSchemaPage();
        softAssert.assertTrue(result, "Failed to verify Assertion : verifySearchTextBoxFunctionalityOnDefineSchemaPage");
        softAssert.assertAll();
    }

    //This test case is to verify that the user is able to see a clock icon on the data query landing page beside the objects name whose recurrence is set -- EDGE-18523
    //Data Query step 3 : Verify user can schedule extraction and select recurrence pattern -- EDGE-18573
    @Test(groups={"Regression_High_Priority"})
    public static void verifyUserIsAbleToSeeClockOnDataQueryLandingPageBesideTheObjectsName() throws Exception {
        Log.info("Method : verifyUserIsAbleToSeeClockOnDataQueryLandingPageBesideTheObjectsName");
        createNewDataQuery();
        enterDataSourceInformationForRedShift("ClusterType_RedShift", "RedShift_Adaptor1", "RedShift_TableName1");
        result = switchScreen("Step 2 of 3");
        Assert.assertTrue(result);
        result = switchScreen("Step 3 of 3");
        Assert.assertTrue(result);
        dataQueryName = TransactionalInfo_Local_Parquet_Extraction("ExtractionLocation_Local", "FileFormat_Parquet");
        scheduleRecurringExtraction("RecurrenceTime");
        setDailyRecurrencePattern("EndAfter3Days");
        clickFinishButton();
        WrapperFunctionUtilities.waitForTime(3000);
        boolean result = CommonPageFunctions.verifyProcess(dataQueryName);
        softAssert.assertTrue(result,"Failed to verify Data Query Name Name on Data Query Page");
        result = verifyDataQueryRecurrenceClockIcon(dataQueryName);
        softAssert.assertTrue(result,"Failed to verify Assertion : verifyDataQueryRecurrenceClockIcon");
        softAssert.assertAll();
    }

    @Test(groups={"Regression_High_Priority"})
    //EDGE-18549 Data Query Step 3 : Verify new adaptor added is visible in adaptor dropdown
    public static void verifyNewAdaptorAddedIsVisibleAtDataQueryExtractionLocation() throws Exception {
        Log.info("Method : verifyNewAdaptorAddedIsVisibleAtDataQueryExtractionLocation");
        String adaptorName = S3AdaptorPageTest.verifys3adaptorIAMUSER();
        WrapperFunctionUtilities.waitForTime(2000);
        createNewDataQuery();
        enterDataSourceInformationForRedShift("ClusterType_RedShift", "RedShift_Adaptor1", "RedShift_TableName1");
        result = switchScreen("Step 2 of 3");
        Assert.assertTrue(result);
        result = switchScreen("Step 3 of 3");
        Assert.assertTrue(result);
        Assert.assertTrue(verifyAdaptorIsPresentAtDataQueryExtractionLocationAdaptorDropdown(adaptorName));
        Assert.assertTrue(AdaptorsPageFunctions.deleteAdaptor(adaptorName));
    }

    // Data Query Step 3: Verify user is not able to create Data query with duplicate name. -- EDGE-18553
    @Test(groups={"Regression_High_Priority"})
    public static void verifyUserIsNotAbleToCreateDataQueryWithDuplicateName() throws Exception {
        Log.info("Method : verifyUserIsNotAbleToCreateDataQueryWithDuplicateName");
        createNewDataQuery();
        enterDataSourceInformationForRedShift("ClusterType_RedShift", "RedShift_Adaptor1", "RedShift_TableName1");
        result = switchScreen("Step 2 of 3");
        Assert.assertTrue(result);
        waitForColumnNamesToLoad();
        result = switchScreen("Step 3 of 3");
        Assert.assertTrue(result);
        dataQueryName = TransactionalInfo_Local_Parquet_Extraction("ExtractionLocation_Local", "FileFormat_Parquet");
        ClickOnSaveAsDraft();
        WrapperFunctionUtilities.waitForTime(3000);
        boolean result = CommonPageFunctions.verifyProcess(dataQueryName);
        softAssert.assertTrue(result,"Failed to verify Data Query Name Name on Data Query Page");
        createNewDataQuery();
        enterDataSourceInformationForRedShift("ClusterType_RedShift", "RedShift_Adaptor1", "RedShift_TableName1");
        result = switchScreen("Step 2 of 3");
        Assert.assertTrue(result);
        waitForColumnNamesToLoad();
        result = switchScreen("Step 3 of 3");
        Assert.assertTrue(result);
        result = verifyDuplicateDataQueryNameValidationError(dataQueryName,"ExtractionLocation_Local", "FileFormat_Parquet");
        softAssert.assertTrue(result,"Failed to verify Assertion : verifyDuplicateDataQueryNameValidationError");
        softAssert.assertAll();
    }

    //This test is to verify that on the Define Schema Page correct columns & datatype are populated according to the table selected on define datasource page -- EDGE-18593
    @Test(groups={"Regression_High_Priority"})
    public static void verifyDefineSchemaPageCorrectColumnsAndDatatypeArePopulated() throws Exception {
        Log.info("Method : verifyDefineSchemaPageCorrectColumnsAndDatatypeArePopulated");
        createNewDataQuery();
        enterDataSourceInformationForRedShift("ClusterType_RedShift", "RedShift_Adaptor1", "RedShift_TableName1");
        result = switchScreen("Step 2 of 3");
        Assert.assertTrue(result);
        waitForColumnNamesToLoad();
        result = verifyColumnNamesAndDataTypeOnDefineSchemaPage("Redshift_BigData_SheetName", "IngestionData_FileName","Excel_XLSX_Format","RowNum2","RowNum4");
        Assert.assertTrue(result,"Failed to verify Assertion : verifyColumnNamesAndDataTypeOnDefineSchemaPage");
    }

    //This test is to verify that on the Define Schema Page user is able to apply multiple filters on the different columns -- EDGE-18591
    //This test is to verify that on the Define Schema Page user is able to delete a already added filter -- EDGE-18594
    //This test is to verify that on the Define Schema Page user is able to edit an already added filter -- EDGE-18595
    //This test is to verify that on the Define Schema Page user is able to toggle between 'AND' or 'OR' operator while applying the filters -- EDGE-18598
    @Test(groups={"Regression_High_Priority"})
    public static void verifyOnDefineSchemaPageUserIsAbleToApplyMultipleFiltersOnTheDifferentColumns() throws Exception {
        Log.info("Method : verifyOnDefineSchemaPageUserIsAbleToApplyMultipleFiltersOnTheDifferentColumns");
        createNewDataQuery();
        enterDataSourceInformationForRedShift("ClusterType_RedShift", "RedShift_Adaptor1", "RedShift_TableName1");
        result = switchScreen("Step 2 of 3");
        softAssert.assertTrue(result,"Failed to Navigate to Step 2");
        //waitForColumnNamesToLoad();
        addMultipleFiltersOnTheDifferentColumns("DataQuery", "Table1_ColumnName1","Condition_EqualsTo","Value1","AND","Table1_ColumnName2","Condition_NOT_IN","Value2");
        result = verifyUserIsAbleToToggleBetweenAndandORCondtion();
        softAssert.assertTrue(result,"Failed to Verify Assertion : verifyUserIsAbleToToggleBetweenAndandORCondtion");
        result = switchScreen("Step 3 of 3");
        softAssert.assertTrue(result,"Failed to Navigate to Step 3");
        dataQueryName = TransactionalInfo_Local_Parquet_Extraction("ExtractionLocation_Local", "FileFormat_Parquet");
        ClickOnSaveAsDraft();
        boolean result = CommonPageFunctions.verifyProcess(dataQueryName);
        softAssert.assertTrue(result,"Failed to Verify Data Query Name on Data Query Landing Page");
        editExistingIngestion(dataQueryName);
        WrapperFunctionUtilities.waitForTime(3000);
        result = switchScreen("Step 2 of 3");
        softAssert.assertTrue(result,"Failed to Navigate to Step 2");
        result = verifyExistingFilterConditionIsGettingDeleted();
        softAssert.assertTrue(result,"Failed to Verify Assertion : deleteExistingFilterCondition ");
        result = verifyExistingFilterConditionIsGettingUpdated("DataQuery", "Table1_ColumnName1","Condition_EqualsTo","Value1");
        softAssert.assertTrue(result,"Failed to Verify Assertion : verifyExistingFilterConditionIsGettingUpdated ");
        softAssert.assertAll();
    }

    // This test is to verify that on the Define Schema Page user is able to de deselect some of the checkboxes for the columns he does not want in the output -- EDGE-18597
    @Test(groups={"Regression_High_Priority"})
    public static void verifyOnDefineSchemaPageUserIsAbleToDeselectTheDifferentColumns() throws Exception {
        Log.info("Method : verifyOnDefineSchemaPageUserIsAbleToDeselectTheDifferentColumns");
        createNewDataQuery();
        enterDataSourceInformationForRedShift("ClusterType_RedShift", "RedShift_Adaptor1", "RedShift_TableName1");
        result = switchScreen("Step 2 of 3");
        softAssert.assertTrue(result, "Failed to Navigate to Step 2");
        result = verifyUserIsAbleToDeselectColumnNames("DataQuery","Table1_ColumnName1","Table1_ColumnName2");
        softAssert.assertTrue(result, "Failed to verify Assertion : verifyUserIsAbleToDeselectColumnNames");
        result = switchScreen("Step 3 of 3");
        softAssert.assertTrue(result,"Failed to Navigate to Step 3");
        navigateToPreviousPage();
        uncheckAllColumnNamesFromDefineSchema();
        result = switchScreen("Step 3 of 3");
        softAssert.assertFalse(result,"Successfully navigated to Step 3 with All Columns Unchecked");
        softAssert.assertAll();
    }

    // This test is to verify that on the Define Schema Page in the filter section the query is populated according to the columns and condition applied -- EDGE-18600
    @Test(groups={"Regression_High_Priority"})
    public static void verifyFilterSectionQueryIsPopulatedAccordingToTheColumnsAndConditionApplied() throws Exception {
        Log.info("Method : verifyFilterSectionQueryIsPopulatedAccordingToTheColumnsAndConditionApplied");
        createNewDataQuery();
        enterDataSourceInformationForRedShift("ClusterType_RedShift", "RedShift_Adaptor1", "RedShift_TableName1");
        result = switchScreen("Step 2 of 3");
        softAssert.assertTrue(result, "Failed to Navigate to Step 2");
        waitForColumnNamesToLoad();
        addMultipleFiltersOnTheDifferentColumns("DataQuery", "Table1_ColumnName1", "Condition_EqualsTo", "Value1", "OR", "Table1_ColumnName2", "Condition_NOT_IN", "Value2");
        result = verifyFilterRuleQueryForMultipleFiltersConditions("DataQuery", "Table1_ColumnName1", "Condition_EqualsTo", "Value1", "OR", "Table1_ColumnName2", "Condition_NOT_IN", "Value2");
        softAssert.assertTrue(result, "Failed to Verify Assertion : verifyFilterRuleQueryForMultipleFiltersConditions");
        result = verifyFilterRuleQueryAfterDeletingFilterCondition();
        softAssert.assertTrue(result, "Failed to Verify Assertion : verifyFilterRuleQueryAfterDeletingFilterCondition ");
        result = verifyExistingFilterConditionIsGettingUpdated("DataQuery", "Table1_ColumnName1", "Condition_EqualsTo", "Value1");
        softAssert.assertTrue(result, "Failed to Verify Assertion : verifyExistingFilterConditionIsGettingUpdated ");
        softAssert.assertAll();
    }

    @Test(groups={"Regression_High_Priority"})
    // EDGE-18550 Data Query Step 3 : Verify if an adaptor is deleted from all adaptors page, it is not displayed in adaptor dropdown of extraction location page
    public static void verifyAdaptorDeletedIsNotVisibleAtDataQueryExtractionLocation() throws Exception {
        Log.info("Method : verifyNewAdaptorAddedIsVisibleAtDataQueryExtractionLocation");
        String adaptorName = S3AdaptorPageTest.verifys3adaptorIAMUSER();
        Assert.assertTrue(AdaptorsPageFunctions.deleteAdaptor(adaptorName));
        WrapperFunctionUtilities.waitForTime(2000);
        createNewDataQuery();
        enterDataSourceInformationForRedShift("ClusterType_RedShift", "RedShift_Adaptor1", "RedShift_TableName1");
        result = switchScreen("Step 2 of 3");
        Assert.assertTrue(result);
        result = switchScreen("Step 3 of 3");
        Assert.assertTrue(result);
        Assert.assertTrue(verifyAdaptorIsNotPresentAtDataQueryExtractionLocationAdaptorDropdown(adaptorName));
    }

    //EDGE-18563 This test case is to verify that after the user selects the 'custom sql' option the select table dropdown must be hidden
    @Test(groups={"Regression_Medium_Priority"})
    public static void verifyTableDropDownIsHiddenIfCustomSQLSelected() throws Exception {
        Log.info("Method : verifyTableDropDownIsHiddenIfCustomSQLSelected");
        createNewDataQuery();
        createNewCustomQuery("ClusterType_EMR","EMR_Database1", "CustomQuery");
        Assert.assertFalse(verifyDataQueryDefineSourceEMRTableDropDownIsNotPresent());
    }

    //EDGE-18564 This test case is to verify that after the User deselects the 'custom sql' checkbox the select table dropdown must again be visible and in enabled state
    @Test(groups={"Regression_Medium_Priority"})
    public static void verifyTableDropDownIsShownIfCustomSQLIsDeselected() throws Exception {
        Log.info("Method : verifyTableDropDownIsShownIfCustomSQLIsDeselected");
        createNewDataQuery();
        createNewCustomQuery("ClusterType_EMR","EMR_Database1", "CustomQuery");
        Assert.assertTrue(verifyDataQueryDefineSourceEMRTableDropDownIsPresent());
    }

    //EDGE-18558 Data Query step 3 : Verify for CSV file format if delimiter is not specified for S3/SFTP or local adaptor error message is shown on clicking Finish button
    @Test(groups={"Regression_Medium_Priority"})
    public static void verifyErrorMessageShownForEmptyDelimeterAtDataQueryLocationPage() throws Exception {
        Log.info("Method: verifyErrorMessageShownForEmptyDelimeterAtDataQueryLocationPage ");
        createNewDataQuery();
        TransactionalInfo_Local_CSV_DataQuery_location("ExtractionLocation_Local", "FileFormat_CSV", "");
        clickFinishButton();
        Assert.assertTrue(CommonPageFunctions.verifyValueRequiredErrorMessage());
    }

    //This test case is to verify that the user is able to delete a data query object from the download action button on the data query landing page -- EDGE-18521
    @Test(groups = {"Regression_High_Priority"})
    public void verifyUserIsAbleToDeleteDataQueryObject() throws Exception {
        Log.info("Method : verifyUserIsAbleToDeleteDataQueryObject");
        createNewDataQuery();
        enterDataSourceInformationForRedShift("ClusterType_RedShift", "RedShift_Adaptor1", "RedShift_TableName1");
        result = switchScreen("Step 2 of 3");
        Assert.assertTrue(result);
        result = switchScreen("Step 3 of 3");
        Assert.assertTrue(result);
        dataQueryName = TransactionalInfo_Local_Parquet_Extraction("ExtractionLocation_Local", "FileFormat_Parquet");
        ClickOnSaveAsDraft();
        boolean result = CommonPageFunctions.verifyProcess(dataQueryName);
        Assert.assertTrue(result);
        result= deleteDataQuery(dataQueryName);
        softAssert.assertTrue(result,"Failed to verify Assertion : deleteDataQuery");
        result = waitForInvisibilityOfDataQuery(dataQueryName);
        softAssert.assertTrue(result,"Failed to verify Assertion : waitForInvisibilityOfDataQuery");
        softAssert.assertAll();
    }

    //This test case is to verify that the user is able to define the data source with cluster type as EMR -- EDGE-18560
    //This test case is to verify that the user is able to define the data source with cluster type as Redshift -- EDGE-18561
    //This test case is to verify that on the define schema page the user must only be able to select the database once after he has selected the Cluster
    // Type -- EDGE-18567
    //This test case is to verify that on the define schema page the user must only be able to select the Table once after he has selected the Cluster Type as
    // EMR and any database -- EDGE-18568
    //This test case is to verify that on the define schema page the user must only be able to select the schema & table once after he has selected the
    // Cluster Type as Redshift and any adaptor with successful connection -- EDGE-18569
    @Test(groups = {"Regression_High_Priority"})
    public void verifyUserIsAbleDefineClusterType_DatabaseAndTableName() throws Exception {
        Log.info("Method : verifyUserIsAbleDefineClusterType_DatabaseAndTableName");
        createNewDataQuery();
        result = verifyClusterTypeDropDownForRedShift_AdaptorDropdown_TableDropDownEnability("ClusterType_RedShift","RedShift_Adaptor1", "RedShift_TableName1");
        softAssert.assertTrue(result,"Failed to Verify Assertion : verifyClusterTypeDropDownForRedShift_AdaptorDropdown_TableDropDownEnability");
        result = verifyClusterTypeDropDownForEMR_DatabaseDropdown_TableDropDownEnability("ClusterType_EMR","EMR_Database1");
        softAssert.assertTrue(result,"Failed to Verify Assertion : verifyClusterTypeDropDownForEMR_DatabaseDropdown_TableDropDownEnability");
        softAssert.assertAll();
    }

    // This test case is to verify that on the define schema page the user is able to see the preview of the selected table when the cluster type is Redshift -- EDGE-18566
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyUserIsAbleToPreviewSelectedTableForClusterTypeRedShift() throws Exception {
        Log.info("Method : verifyUserIsAbleToPreviewSelectedTableForClusterTypeRedShift");
        createNewDataQuery();
        enterDataSourceInformationForRedShift("ClusterType_RedShift", "RedShift_Adaptor1", "RedShift_TableName1");
        clickOnPreviewTableButton();
        result = IngestionPageFunctions.verifySourceColumnHeadersInPreviewTable("Redshift_BigData_SheetName", "IngestionData_FileName","RowNum2","Excel_XLSX_Format");
        softAssert.assertTrue(result,"Failed to verify : verifySourceColumnHeadersInPreviewTable ");
        result = switchScreen("Step 2 of 3");
        navigateToPreviousPage();
        WrapperFunctionUtilities.waitForTime(2000);//waiting for TableDropDown To Populate Value
        clickOnPreviewTableButton();
        result = IngestionPageFunctions.verifySourceDataInPreviewTable("Redshift_BigData_SheetName", "IngestionData_FileName","RowNum3","Excel_XLSX_Format");
        softAssert.assertTrue(result,"Failed to verify : verifySourceDataInPreviewTable ");
        softAssert.assertAll();
    }

    //Data Query step 3 : Verify if file location is empty for S3 adaptor error message is shown on clicking Finish button -- EDGE-18555
    // Data Query step 3 : Verify if file format is not selected for S3/SFTP or local adaptor error message is shown on clicking Finish button -- EDGE-18557
    //Data Query step 3 : Verify if file format is not selected for S3/SFTP or local adaptor error message is shown on clicking Finish button -- EDGE-18556
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyErrorMessageForEmptyFileLocationOnClickingFinishButton() throws Exception {
        Log.info("Method : verifyErrorMessageForEmptyFileLocationOnClickingFinishButton");
        createNewDataQuery();
        enterDataSourceInformationForRedShift("ClusterType_RedShift", "RedShift_Adaptor1", "RedShift_TableName1");
        result = switchScreen("Step 2 of 3");
        Assert.assertTrue(result);
        result = switchScreen("Step 3 of 3");
        Assert.assertTrue(result);
        result = verifyErrorMessageForEmptyFileLocation("ExtractionLocation_LoadExistingAdaptor","S3IAMAdaptor","FileFormat_Parquet");
        softAssert.assertTrue(result);
        result = verifyErrorMessageForNotSelectingFileFormatForS3();
        softAssert.assertTrue(result);
        clickOnCancelButton();
        createNewDataQuery();
        enterDataSourceInformationForRedShift("ClusterType_RedShift", "RedShift_Adaptor1", "RedShift_TableName1");
        result = switchScreen("Step 2 of 3");
        softAssert.assertTrue(result,"Failed to Verify Assertion : switchScreen");
        result = switchScreen("Step 3 of 3");
        Assert.assertTrue(result,"Failed to Verify Assertion : switchScreen");
        result = verifyErrorMessageForNotSelectingFileFormatForLocalExtraction("ExtractionLocation_Local");
        softAssert.assertTrue(result);
        softAssert.assertAll();
    }
    //Data Query Step 3: Verify on selecting any RDBMS adaptor, error message is shown if column mapping is missing for one column or more columns -- EDGE-18552
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyErrorMessageForMissingOneOrMoreColumnMapping() throws Exception {
        Log.info("Method : verifyErrorMessageForMissingOneOrMoreColumnMapping");
        createNewDataQuery();
        enterDataSourceInformationForRedShift("ClusterType_RedShift", "RedShift_Adaptor1", "RedShift_TableName2");
        result = switchScreen("Step 2 of 3");
        Assert.assertTrue(result);
        result = switchScreen("Step 3 of 3");
        Assert.assertTrue(result);
        result = verifyErrorMessageForEmptySourceColumnMappingDropDown("ExtractionLocation_LoadExistingAdaptor", "RedShift_Adaptor1", "RedShift_TableName2","RedShift_Table2_MappingColumn");
        softAssert.assertTrue(result);
        clickOnCancelButton();
        softAssert.assertAll();
    }
    //This test case is to verify that the Data Query Landing Page must consists of the mentioned columns in the table -- EDGE-18510
    //This test case is to verify that when the user clicks the 'Data Query' Option in menu must land on the Data Queries Page -- EDGE-18509
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyColumnNamesOnDataQueryLandingPage() throws Exception {
        Log.info("Method : verifyColumnNamesOnDataQueryLandingPage");
        CommonPageFunctions.selectDataMenuOptions("DataMenu", "DataQueryTabName");
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_CreateNewDataQuery_WebElement,10,driver);
        result = verifyColumnNameOnDataQueryPage();
        softAssert.assertTrue(result,"Failed to verify assertion : ColumnName is Missing");
        result = verifyElementsOnDataQueryPage();
        softAssert.assertTrue(result,"Failed to verify assertion :verifyElementsOnDataQueryPage");
        softAssert.assertAll();
    }

    // Verify Scheduled Extraction to Parquet using custom query. -- EDGE-18507
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyScheduledExtractionToParquetForCustomDataQuery() throws Exception {
        Log.info("Method : verifyScheduledExtractionToParquetForCustomDataQuery");
        createNewDataQuery();
        createNewCustomQuery("ClusterType_EMR","EMR_Database1", "CustomQuery");
        result = switchScreen("Step 3 of 3");
        Assert.assertTrue(result,"User dint land on Step 3 Page");
        dataQueryName = TransactionalInfo_Local_Parquet_Extraction("ExtractionLocation_Local", "FileFormat_Parquet");
        scheduleRecurringExtraction("RecurrenceTime");
        clickFinishButton();
        WrapperFunctionUtilities.waitForTime(3000);
        boolean result = CommonPageFunctions.verifyProcess(dataQueryName);
        Assert.assertTrue(result);
        result = verifyDataQueryRecurrenceToggleIsActive(dataQueryName);
        Assert.assertTrue(result);
    }

    // Verify Scheduled extraction to S3 with custom query -- EDGE-18460
    // Verify the functionality of Recurrence on-off on home page -- EDGE-18484
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyScheduledS3ExtractionForCustomDataQuery() throws Exception {
        Log.info("Method : verifyScheduledS3ExtractionForCustomDataQuery");
        createNewDataQuery();
        createNewCustomQuery("ClusterType_EMR","EMR_Database1", "CustomQuery");
        result = switchScreen("Step 3 of 3");
        Assert.assertTrue(result,"User dint land on Step 3 Page");
        dataQueryName = TransactionalInfo_S3_Extraction("S3IAMAdaptor","FileFormat_Parquet","Extraction_FileLocation");
        scheduleRecurringExtraction("RecurrenceTime");
        clickFinishButton();
        WrapperFunctionUtilities.waitForTime(3000);
        boolean result = CommonPageFunctions.verifyProcess(dataQueryName);
        softAssert.assertTrue(result,"Failed to verify Assertion : verifyProcess");
        result = verifyDataQueryRecurrenceToggleIsActive(dataQueryName);
        softAssert.assertTrue(result,"Failed to verify Assertion : verifyDataQueryRecurrenceToggleIsActive");
        result =  verifyDataQueryRecurrenceToggleFunctionlity(dataQueryName);
        softAssert.assertTrue(result,"Failed to verify Assertion : verifyDataQueryRecurrenceToggleFunctionlity");
        softAssert.assertAll();
    }

    //Deselected columns in Step 2 Define Schema should appear in Filter under Source column drop down -- EDGE-18479
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyDeselectedColumnsInDefineSchemaShouldAppearInFilterUnderSourceColumnDropDown() throws Exception {
        Log.info("Method : verifyDeselectedColumnsInDefineSchemaShouldAppearInFilterUnderSourceColumnDropDown");
        createNewDataQuery();
        enterDataSourceInformationForRedShift("ClusterType_RedShift", "RedShift_Adaptor1", "RedShift_TableName2");
        result = switchScreen("Step 2 of 3");
        softAssert.assertTrue(result,"Failed to verify assertion : switchScreen to Step 2 of 3 ");
        result = verifyDeselectedColumnsShowsInFilterOnDefineSchemaPage("DataQuery","RedShift_Table2_MappingColumn","RedShift_Table2_MappingColumn2");
        softAssert.assertTrue(result,"Failed to verify assertion : verifyDeselectedColumnsShowsInFilter");
        softAssert.assertAll();
    }

    //Verify Part Files field on Step 4 of Extraction does not accept negative value for S3 -- EDGE-18483
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyPartFilesFieldOfExtractionDoesNotAcceptNegativeValueForS3() throws Exception {
        Log.info("Method : verifyPartFilesFieldOfExtractionDoesNotAcceptNegativeValueForS3");
        createNewDataQuery();
        enterDataSourceInformationForRedShift("ClusterType_RedShift", "RedShift_Adaptor1", "RedShift_TableName2");
        result = switchScreen("Step 2 of 3");
        softAssert.assertTrue(result, "Failed to verify assertion : switchScreen to Step 2 of 3 ");
        result = switchScreen("Step 3 of 3");
        softAssert.assertTrue(result,"Failed to verify assertion : switchScreen to Step 3 of 3 ");
        dataQueryName = TransactionalInfo_S3_Extraction("S3IAMAdaptor","FileFormat_Parquet","Extraction_FileLocation");
        result = verifyPartFilesFieldTextBoxShowsErrorMessageForNegativeNumbers();
        softAssert.assertTrue(result,"Failed to verify Assertion : verifyPartFilesFieldTextBoxShowsErrorMessageForNegativeNumbers");
        clickFinishButton();
        WrapperFunctionUtilities.waitForTime(3000);
        boolean result = CommonPageFunctions.verifyProcess(dataQueryName);
        softAssert.assertTrue(result,"Failed to verify Assertion : verifyProcess");
        softAssert.assertAll();
    }
    // Verify RDBMS MSSQL extraction with overwrite option at step 4 -- EDGE-18443
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyRDBMSMSSQLExtractionWithOverwriteOption() throws Exception {
        Log.info("Method : verifyRDBMSMSSQLExtractionWithOverwriteOption");
        createNewDataQuery();
        enterDataSourceInformationForRedShift("ClusterType_RedShift", "RedShift_Adaptor1", "RedShift_TableName2");
        result = switchScreen("Step 2 of 3");
        softAssert.assertTrue(result, "Failed to verify assertion : switchScreen to Step 2 of 3 ");
        List<String> columnNameList = storeColumnNamesFromDefineSchemaTable();
        System.out.println(columnNameList);
        result = switchScreen("Step 3 of 3");
        softAssert.assertTrue(result,"Failed to verify assertion : switchScreen to Step 3 of 3 ");
        dataQueryName = TransactionalInfo_Adaptor_Extraction("MSSQLAdaptor", "MSSQL_TableName");
        selectDatabaseColumnMappingForTargetTable(columnNameList);
        clickFinishButton();
        WrapperFunctionUtilities.waitForTime(3000);
        boolean result = CommonPageFunctions.verifyProcess(dataQueryName);
        softAssert.assertTrue(result,"Failed to verify Assertion : verifyProcess");
        softAssert.assertAll();
    }

    // Verify RDBMS Redshift extraction with overwrite option at step 4 -- EDGE-18452
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyRDBMSRedShiftExtractionWithOverwriteOption() throws Exception {
        Log.info("Method : verifyRDBMSRedShiftExtractionWithOverwriteOption");
        createNewDataQuery();
        enterDataSourceInformationForRedShift("ClusterType_RedShift", "RedShift_Adaptor1", "RedShift_TableName2");
        result = switchScreen("Step 2 of 3");
        softAssert.assertTrue(result, "Failed to verify assertion : switchScreen to Step 2 of 3 ");
        List<String> columnNameList = storeColumnNamesFromDefineSchemaTable();
        System.out.println(columnNameList);
        result = switchScreen("Step 3 of 3");
        softAssert.assertTrue(result,"Failed to verify assertion : switchScreen to Step 3 of 3 ");
        dataQueryName = TransactionalInfo_Adaptor_Extraction("RedShift_Adaptor1", "RedShiftExtraction_TableName");
        selectDatabaseColumnMappingForTargetTable(columnNameList);
        clickFinishButton();
        WrapperFunctionUtilities.waitForTime(3000);
        boolean result = CommonPageFunctions.verifyProcess(dataQueryName);
        softAssert.assertTrue(result,"Failed to verify Assertion : verifyProcess");
        softAssert.assertAll();
    }

    // Data Query Landing Page : Verify on selecting 50,100,150 in Pagination dropdown, exact number of values get listed in landing page. -- EDGE-18925
    @Test(groups = {"Regression_Medium_Priority"})
    public void verifyPaginationFunctionalityOnDataQueryLandingPage() throws Exception {
        Log.info("Method : verifyPaginationFunctionalityOnDataQueryLandingPage");
        CommonPageFunctions.selectDataMenuOptions("DataMenu", "DataQueryTabName");
        WrapperFunctionUtilities.waitForTime(2000);
        selectNumberOfRecordsFromPaginationDropDown(100);
        result = verifyNumberOfRowsDisplayedInTable(100);
        softAssert.assertTrue(result,"Failed to verify Assertion : verifyNumberOfRowsDisplayedInTable For 100 records");
        selectNumberOfRecordsFromPaginationDropDown(50);
        result = verifyNumberOfRowsDisplayedInTable(50);
        softAssert.assertTrue(result,"Failed to verify Assertion : verifyNumberOfRowsDisplayedInTable For 50 records");
        selectNumberOfRecordsFromPaginationDropDown(150);
        result = verifyNumberOfRowsDisplayedInTable(150);
        softAssert.assertTrue(result,"Failed to verify Assertion : verifyNumberOfRowsDisplayedInTable For 150 records");
        softAssert.assertAll();
    }
    // Data Query Logs Page : Verify on selecting 50,100,150 in Pagination dropdown, exact number of values get listed in logs page. --  EDGE-18926
        @Test(groups = {"Regression_Medium_Priority"})
        public void verifyPaginationFunctionalityOnDataQueryLogsPage() throws Exception {
            Log.info("Method : verifyPaginationFunctionalityOnDataQueryLogsPage");
            CommonPageFunctions.selectDataMenuOptions("DataMenu", "DataQueryTabName");
            WrapperFunctionUtilities.waitForTime(2000);
            navigateToDataQueryLogsTab();
            selectNumberOfRecordsFromPaginationDropDown(100);
            result = verifyNumberOfRowsDisplayedInTable(100);
            softAssert.assertTrue(result,"Failed to verify Assertion : verifyNumberOfRowsDisplayedInTable For 100 records");
            selectNumberOfRecordsFromPaginationDropDown(50);
            result = verifyNumberOfRowsDisplayedInTable(50);
            softAssert.assertTrue(result,"Failed to verify Assertion : verifyNumberOfRowsDisplayedInTable For 50 records");
            selectNumberOfRecordsFromPaginationDropDown(150);
            result = verifyNumberOfRowsDisplayedInTable(150);
            softAssert.assertTrue(result,"Failed to verify Assertion : verifyNumberOfRowsDisplayedInTable For 150 records");
            softAssert.assertAll();
        }

     // Data Query Past Runs Page : Verify on selecting 50,100,150 in Pagination dropdown, exact number of values get listed in past runs page. -- EDGE-18927
            @Test(groups = {"Regression_Medium_Priority"})
            public void verifyPaginationFunctionalityOnDataQueryPastRunsPage() throws Exception {
                Log.info("Method : verifyPaginationFunctionalityOnDataQueryLogsPage");
                CommonPageFunctions.selectDataMenuOptions("DataMenu", "DataQueryTabName");
                WrapperFunctionUtilities.waitForTime(2000);
                //openPastRunForFirstSuccessfulDataQueryOnHomePage();
                String dataQueryName = ExcelReader.getValue("DataQuery", "DataQueryWithPastRuns", "TestData");
                searchAndOpenPastRunsForExistingDataQuery(dataQueryName);
                selectNumberOfRecordsFromPaginationDropDown(100);
                result = verifyNumberOfRowsDisplayedInPastRunTable(100);
                softAssert.assertTrue(result,"Failed to verify Assertion : verifyNumberOfRowsDisplayedInTable For 100 records");
                selectNumberOfRecordsFromPaginationDropDown(50);
                result = verifyNumberOfRowsDisplayedInPastRunTable(50);
                softAssert.assertTrue(result,"Failed to verify Assertion : verifyNumberOfRowsDisplayedInTable For 50 records");
                selectNumberOfRecordsFromPaginationDropDown(150);
                result = verifyNumberOfRowsDisplayedInPastRunTable(150);
                softAssert.assertTrue(result,"Failed to verify Assertion : verifyNumberOfRowsDisplayedInTable For 150 records");
                softAssert.assertAll();
            }

}


