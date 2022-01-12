package com.RDM.Merger.pagefunctions;

import com.RDM.Merger.locators.CommonPage;
import com.RDM.Merger.locators.DataQueryPage;
import com.RDM.Merger.testcases.Aa_Login.ExcelReader;
import com.utility.base.WrapperFunctionUtilities;
import com.utility.logger.Log;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

public class DataQueryFunctions extends DataQueryPage {

    public static boolean logicalResultFlag = true;
    public DataQueryFunctions() {
        super();
    }

    //This method clicks on create new ingestion link
    public static void createNewDataQuery() {
            Log.info("Method : createNewDataQuery");
            CommonPageFunctions.selectDataMenuOptions("DataMenu", "DataQueryTabName");
            WrapperFunctionUtilities.waitForElementVisibility(dqPage_CreateNewDataQuery_WebElement,10,driver);
            dqPage_CreateNewDataQuery_WebElement.click();
    }

    //This method used to enter Data Source Information For RedShift Type of Data Query
    public static void enterDataSourceInformationForRedShift(String clusterType,String adaptorName, String tableName) throws Exception {
        Log.info("Method : enterDataSourceInformationForRedShift");
        HashMap<String,String> dataQuerySheetData =  ExcelReader.getTestDataFromExcelSheetWithRow("DataQuery","TestData",2,"xlsx");
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_ClusterType_DropDown,10,driver);
        WrapperFunctionUtilities.jsClick(dqPage_ClusterType_DropDown,driver);
        WrapperFunctionUtilities.jsClick(CommonPageFunctions.hyperlink(dataQuerySheetData.get(clusterType)), driver);
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_selectAdaptor_DropDown,10,driver);
        dqPage_selectAdaptor_DropDown.click();
        WrapperFunctionUtilities.scrollByVisibleElement(CommonPageFunctions.hyperlink(dataQuerySheetData.get(adaptorName)), driver);
        CommonPageFunctions.hyperlinkClick(dataQuerySheetData.get(adaptorName));
        WrapperFunctionUtilities.waitForTime(3000);
        dqPage_SchemaAndTable_DropDown.click();
        dqPage_SchemaAndTableSearch_TextBox.sendKeys(dataQuerySheetData.get(tableName));
        WrapperFunctionUtilities.waitForTime(2000);//waiting for results to populate
        WrapperFunctionUtilities.jsClick(CommonPageFunctions.hyperlink(dataQuerySheetData.get(tableName)),driver);
    }

    // This method enters Transactional Information for Local Parquet Extraction
    public static String TransactionalInfo_Local_Parquet_Extraction(String extractionLocation,String fileFormat) throws Exception {
        Log.info("Method : TransactionalInfo_Local_Parquet_Extraction");
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_ExtractionLocation_DropDown,10,driver);
        dqPage_ExtractionLocation_DropDown.click();
        CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("DataQuery", extractionLocation, "TestData"));
        String dataQueryID = CommonPageFunctions.generateRandomString();
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_DataQueryName_TextBox, 5, driver);
        dqPage_DataQueryName_TextBox.sendKeys(dataQueryID);
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_FileFormat_DropDown,10,driver);
        WrapperFunctionUtilities.jsClick(dqPage_FileFormat_DropDown,driver);
        WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(ExcelReader.getValue("DataQuery", fileFormat, "TestData")),driver);
        return dataQueryID;
    }

    public static String TransactionalInfo_Local_CSV_DataQuery_location(String extractionLocation,String fileFormat, String delimeter) throws Exception {
        Log.info("Method : TransactionalInfo_Local_CSV_Extraction");
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_ExtractionLocation_DropDown,10,driver);
        dqPage_ExtractionLocation_DropDown.click();
        CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("DataQuery", extractionLocation, "TestData"));
        String dataQueryID = CommonPageFunctions.generateRandomString();
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_DataQueryName_TextBox, 5, driver);
        dqPage_DataQueryName_TextBox.sendKeys(dataQueryID);
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_FileFormat_DropDown,10,driver);
        WrapperFunctionUtilities.jsClick(dqPage_FileFormat_DropDown,driver);
        WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(ExcelReader.getValue("DataQuery", fileFormat, "TestData")),driver);
        dataQueryPage_Delimeter_TextBox.sendKeys(delimeter);
        return dataQueryID;

    }

    // This method schudules recurring extraction
    public static void scheduleRecurringExtraction(String recurrenceTime) throws Exception {
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_scheduleRecurringExtraction_Checkbox,10,driver);
        WrapperFunctionUtilities.scrollByVisibleElement(dqPage_scheduleRecurringExtraction_Checkbox, driver);
        WrapperFunctionUtilities.jsClick(dqPage_scheduleRecurringExtraction_Checkbox,driver);
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_RecurringTime_TextBox,10,driver);
        dqPage_RecurringTime_TextBox.click();
        WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(ExcelReader.getValue("DataQuery", recurrenceTime, "TestData")),driver);
    }

    public static boolean verifyDataQueryRecurrenceToggleIsActive(String dataQueryName) {
        Log.info("Method :  verifyDataQueryRecurrenceToggleIsActive");
        String toggleStatus = dqPage_RecurrenceToogleButton_WebElement(dataQueryName).getAttribute("active");
        boolean flag = toggleStatus != null ? true : false;
        return flag;
    }

    public static void clickFinishButton()
    {
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_finish_Button,10,driver);
        dqPage_finish_Button.click();
    }

    public static boolean switchScreen(String stepName){
        try
        {
            boolean result=false;
            System.out.println("isEnabled "+ dqPage_Next_Button.isEnabled());
            if((dqPage_Next_Button.isEnabled())==true){
                System.out.println("is visible " + WrapperFunctionUtilities.isElementVisible(3000, dqPage_Next_Button,driver));
                JavascriptExecutor executor = (JavascriptExecutor)driver;
                executor.executeScript("arguments[0].click();", dqPage_Next_Button);
                WrapperFunctionUtilities.waitForTime(1000);
                result= CommonPage.getStepName(stepName).isEnabled();
                System.out.println(stepName+ "  is Displayed Result  " + result);
                return result;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean verifyEMRDatabaseIsnNotPopulatedInsideRedShiftAdaptor(String clusterType,String adaptorName, String tableName) throws Exception {
        Log.info("Method : verifyEMRDatabaseIsnNotPopulatedInsideRedShiftAdaptor");
        HashMap<String,String> dataQuerySheetData =  ExcelReader.getTestDataFromExcelSheetWithRow("DataQuery","TestData",2,"xlsx");
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_ClusterType_DropDown,10,driver);
        WrapperFunctionUtilities.jsClick(dqPage_ClusterType_DropDown,driver);
        WrapperFunctionUtilities.jsClick(CommonPageFunctions.hyperlink(dataQuerySheetData.get(clusterType)), driver);
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_selectAdaptor_DropDown,10,driver);
        dqPage_selectAdaptor_DropDown.click();
        WrapperFunctionUtilities.scrollByVisibleElement(CommonPageFunctions.hyperlink(dataQuerySheetData.get(adaptorName)), driver);
        CommonPageFunctions.hyperlinkClick(dataQuerySheetData.get(adaptorName));
        WrapperFunctionUtilities.waitForTime(3000);
        dqPage_SchemaAndTable_DropDown.click();
        dqPage_SchemaAndTableSearch_TextBox.sendKeys(dataQuerySheetData.get(tableName));
        boolean flag = WrapperFunctionUtilities.waitForElementToBeInvisibileBy(dqPage_SchemaAndTableDropdownOptions_xpathLocator(tableName), 10, driver);
        dqPage_SchemaAndTableSearch_TextBox.clear();
        return flag;
    }

    public static void selectTableAndSchemaNameForRedShiftAdaptor(String tableName) throws Exception {
        Log.info("Method : selectTableAndSchemaNameForRedShiftAdaptor");
        WrapperFunctionUtilities.jsClick(DataQueryPage_RedshiftTableSelected_WebElement,driver);
        //DataQueryPage_RedshiftTableSelected_WebElement.click();
        dqPage_SchemaAndTableSearch_TextBox.sendKeys(ExcelReader.getValue("DataQuery",tableName,"TestData"));
        WrapperFunctionUtilities.waitForTime(2000);//waiting for results to populate
        WrapperFunctionUtilities.jsClick(CommonPageFunctions.hyperlink(ExcelReader.getValue("DataQuery",tableName,"TestData")),driver);
    }

   public static void editDataQuery(String dataQueryName,String tableName) throws Exception {
        DataQueryPage_EditDataQuery_Icon(dataQueryName).click();
        selectTableAndSchemaNameForRedShiftAdaptor(tableName);
    }

    public static boolean verifyDataQueryRedshiftTableUpdatedOnEdit(String dataQueryName,String tableName) throws Exception {
        DataQueryPage_EditDataQuery_Icon(dataQueryName).click();
        WrapperFunctionUtilities.waitForTime(1500);
        String tableSelected = DataQueryPage_RedshiftTableSelected_WebElement.getText();
        Log.info("Table selected "+ tableSelected + "Table Provided "+ ExcelReader.getValue("DataQuery",tableName,"TestData"));
        return tableSelected.equals(ExcelReader.getValue("DataQuery",tableName,"TestData"));
    }
    public static boolean deleteDataQuery(String dataQueryName){
        dataQueryPage_DataQueries_link.click();
        WrapperFunctionUtilities.waitForTime(1000);
        DataQueryPage_DeleteDataQuery_Icon(dataQueryName).click();
        CommonPage.deleteConfirmationPopUp_Delete_Button.click();
        WrapperFunctionUtilities.waitForTime(2000);
        return WrapperFunctionUtilities.isElementPresent(CommonPage.commonPage_delete_Message_Toaster(dataQueryName),"Delete Toaster");
    }

    public static boolean verifyAdaptorIsPresentAtDataQueryExtractionLocationAdaptorDropdown(String adaptorName){
        Log.info("In verifyAdaptorIsPresentAtDataQueryExtractionLocationAdaptorDropdown");
        dataQueryPage_SelectAdaptor_Dropdown.click();
        dataQueryPage_SelectAdaptorDropdown_Input.sendKeys(adaptorName);
        WrapperFunctionUtilities.waitForTime(1000);
        return WrapperFunctionUtilities.isElementPresent(CommonPageFunctions.hyperlink(adaptorName),"Adaptor Added");
    }
    public static boolean verifyAdaptorIsNotPresentAtDataQueryExtractionLocationAdaptorDropdown(String adaptorName){
        Log.info("In verifyAdaptorIsNotPresentAtDataQueryExtractionLocationAdaptorDropdown");
        dataQueryPage_SelectAdaptor_Dropdown.click();
        dataQueryPage_SelectAdaptorDropdown_Input.sendKeys(adaptorName);
        WrapperFunctionUtilities.waitForTime(1000);
        return WrapperFunctionUtilities.isElementPresent(dataQueryPage_NoResultsFound_WebElement,"No Results");
    }

    public static boolean runFirstSuccessfulDataQueryOnHomePage() throws Exception {
        System.out.println("Method :  runFirstSuccessfulDataQueryOnHomePage");
        Log.info("Method : runFirstSuccessfulDataQueryOnHomePage");
        int counter=0;
        //dpPage_RightArrow_WebButton.click();
        List<WebElement> countOfActiveIngestionRunButtons = dqPage_FirstSuccessfulDataQueryRunButton_WebElementList();
        Log.info("countOfActiveIngestionRunButtons  " + countOfActiveIngestionRunButtons.size());
        while (countOfActiveIngestionRunButtons.size() <= 0) {
            counter++;
            WrapperFunctionUtilities.scrollByVisibleElement(dqPage_lastVisibleDataQueryName, driver);
            countOfActiveIngestionRunButtons = dqPage_FirstSuccessfulDataQueryRunButton_WebElementList();
            Log.info("countOfActiveIngestionRunButtons  " + countOfActiveIngestionRunButtons.size());
            if(counter==3)
            {
                dpPage_RightArrow_WebButton.click();
                counter=0;
            }
        }

        WrapperFunctionUtilities.scrollByVisibleElement(dqPage_FirstSuccessfulDataQueryRunButton_WebElement, driver);
        String name = runFirstSuccessfulDataQueryName_WebElement.getText();
        dqPage_FirstSuccessfulDataQueryRunButton_WebElement.click();
        //WrapperFunctionUtilities.jsClick(dqPage_FirstSuccessfulDataQueryRunButton_WebElement, driver);
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_processRetriggeredInformatonBanner_WebElement, 30, driver);
        WrapperFunctionUtilities.waitForElementVisibility(statusOfDataQuery(name, "Pending"),60, driver);
        boolean flag = WrapperFunctionUtilities.isElementPresent(statusOfDataQuery(name, "Pending"), "Pending Status");
        return flag;
    }

    public static void createNewCustomQuery(String clusterType, String databaseName, String customQuery) throws Exception {
        Log.info("Method : createNewCustomQuery");
        HashMap<String,String> dataQuerySheetData =  ExcelReader.getTestDataFromExcelSheetWithRow("DataQuery","TestData",2,"xlsx");
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_ClusterType_DropDown,10,driver);
        WrapperFunctionUtilities.jsClick(dqPage_ClusterType_DropDown,driver);
        WrapperFunctionUtilities.jsClick(CommonPageFunctions.hyperlink(dataQuerySheetData.get(clusterType)), driver);
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_selectDatabase_DropDown,10,driver);
        WrapperFunctionUtilities.jsClick(dqPage_selectDatabase_DropDown, driver);
        WrapperFunctionUtilities.scrollByVisibleElement(CommonPageFunctions.hyperlink(dataQuerySheetData.get(databaseName)), driver);
        CommonPageFunctions.hyperlinkClick(dataQuerySheetData.get(databaseName));
        dpPage_CustomSQL_CheckBox.click();
        dpPage_CustomQuery_TextBox.sendKeys(dataQuerySheetData.get(customQuery));
    }


    //This method used to enter Data Source Information For RedShift Type of Data Query
    public static void enterDataSourceInformationForEMR(String clusterType, String databaseName, String tableName) throws Exception {
        Log.info("Method : enterDataSourceInformationForEMR");
        HashMap<String,String> dataQuerySheetData =  ExcelReader.getTestDataFromExcelSheetWithRow("DataQuery","TestData",2,"xlsx");
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_ClusterType_DropDown,10,driver);
        WrapperFunctionUtilities.jsClick(dqPage_ClusterType_DropDown,driver);
        WrapperFunctionUtilities.jsClick(CommonPageFunctions.hyperlink(dataQuerySheetData.get(clusterType)), driver);
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_selectDatabase_DropDown,10,driver);
        WrapperFunctionUtilities.jsClick(dqPage_selectDatabase_DropDown, driver);
        WrapperFunctionUtilities.scrollByVisibleElement(CommonPageFunctions.hyperlink(dataQuerySheetData.get(databaseName)), driver);
        CommonPageFunctions.hyperlinkClick(dataQuerySheetData.get(databaseName));
        dqPage_Table_DropDown.click();
      //  dqPage_SchemaAndTableSearch_TextBox.sendKeys(dataQuerySheetData.get(tableName));
        WrapperFunctionUtilities.waitForTime(2000);//waiting for results to populate
        WrapperFunctionUtilities.jsClick(CommonPageFunctions.hyperlink(dataQuerySheetData.get(tableName)),driver);
    }
    public static boolean verifyExistenceOfColumnName_DataType_FilterSection()
    {
        Log.info("Method : verifyExistenceOfColumnName_DataType_FilterSection");
        boolean flag = true;
        flag = WrapperFunctionUtilities.isElementPresent(dqPage_ColumnName_Header, "dqPage_ColumnName_Header")?flag:false;
        flag = WrapperFunctionUtilities.isElementPresent(dqPage_DataType_Header, "dqPage_DataType_Header")?flag:false;
        flag = WrapperFunctionUtilities.isElementPresent(dqPage_Filter_Header, "dqPage_Filter_Header")?flag:false;
        return flag;
    }

    public static boolean verifySortingOfColumnNameFields()
    {
        Log.info("Method : verifySortingOfColumnNameFields");
        boolean flag = true;
        flag = WrapperFunctionUtilities.isElementPresent(dqPage_ColumnName_Header, "dqPage_ColumnName_Header")?flag:false;
        List<String> actualColumnNameList = new ArrayList<>();
        List<String> expectedListAfterSorting = new ArrayList<>();
        for(WebElement columnName : dqPage_columnNames_WebElementList())
        {
            expectedListAfterSorting.add(columnName.getText());
        }
        System.out.println("Actual Column List" + expectedListAfterSorting);

        //Ascending  Sort Verification

        dqPage_ColumnName_Header.click();
        for(WebElement columnName : dqPage_columnNames_WebElementList())
        {
            actualColumnNameList.add(columnName.getText());
        }

        Collections.sort(expectedListAfterSorting);
        flag = expectedListAfterSorting.equals(actualColumnNameList)?flag:false;

        System.out.println("Expected Column List  " + expectedListAfterSorting);
        System.out.println("Actual Column List  " + actualColumnNameList);
        System.out.println("Value of Flag After Ascending Sort  " + flag);

        //Descending Sort Verification
        dqPage_ColumnName_Header.click();
        actualColumnNameList.clear();
        for(WebElement columnName : dqPage_columnNames_WebElementList())
        {
            actualColumnNameList.add(columnName.getText());
        }

        Collections.sort(expectedListAfterSorting, Collections.reverseOrder());
        flag = expectedListAfterSorting.equals(actualColumnNameList)?flag:false;

        System.out.println("Expected Column List  " + expectedListAfterSorting);
        System.out.println("Actual Column List  " + actualColumnNameList);
        System.out.println("Value of Flag After Descending Sort  " + flag);
        return flag;
    }

    public static boolean verifySortingOfDataTypeColumn()
    {
        Log.info("Method : verifySortingOfDataTypeColumn");
        boolean flag = true;
        flag = WrapperFunctionUtilities.isElementPresent(dqPage_DataType_Header, "dqPage_DataType_Header")?flag:false;
        List<String> actualDataTypeList = new ArrayList<>();
        List<String> expectedListAfterSorting = new ArrayList<>();
        for(WebElement columnName : dqPage_dataTypeForColumns_WebElementList())
        {
            expectedListAfterSorting.add(columnName.getText().toUpperCase());
        }
        System.out.println("Actual DataType List" + expectedListAfterSorting);

        //Ascending  Sort Verification

        dqPage_DataType_Header.click();
        for(WebElement columnName : dqPage_dataTypeForColumns_WebElementList())
        {
            actualDataTypeList.add(columnName.getText().toUpperCase());
        }

        Collections.sort(expectedListAfterSorting);
        flag = expectedListAfterSorting.equals(actualDataTypeList)?flag:false;

        System.out.println("Expected DataType List  " + expectedListAfterSorting);
        System.out.println("Actual DataType List  " + actualDataTypeList);
        System.out.println("Value of Flag After Ascending Sort  " + flag);

        //Descending Sort Verification
        dqPage_DataType_Header.click();
        actualDataTypeList.clear();
        for(WebElement columnName : dqPage_dataTypeForColumns_WebElementList())
        {
            actualDataTypeList.add(columnName.getText().toUpperCase());
        }

        Collections.sort(expectedListAfterSorting, Collections.reverseOrder());
        flag = expectedListAfterSorting.equals(actualDataTypeList)?flag:false;

        System.out.println("Expected DataType List  " + expectedListAfterSorting);
        System.out.println("Actual DataType List  " + actualDataTypeList);
        System.out.println("Value of Flag After Descending Sort  " + flag);
        return flag;
    }

    public static boolean verifySearchTextBoxFunctionalityOnDefineSchemaPage()
    {
        Log.info("Method : verifySearchTextBoxFunctionalityOnDefineSchemaPage");
        boolean flag = true;

        String columnName = dqPage_columnNames_WebElementList().get(1).getText();
        dqPage_searchFilter_TextBox.clear();
        dqPage_searchFilter_TextBox.click();
        dqPage_searchFilter_TextBox.sendKeys(columnName);
       // flag = dqPage_columnNames_WebElementList().size()==1?flag:false;
        for(WebElement columnNameWebElement : dqPage_columnNames_WebElementList() )
        {
            if(!columnNameWebElement.getText().contains(columnName))
            {
                flag = false;
            }
        }
        Log.info("Value of flag after search result comparison "+ flag);
      //  flag = dqPage_columnNames_WebElementList().get(0).getText().equals(columnName)?flag:false;
        columnName = columnName.concat("RandomString");
        dqPage_searchFilter_TextBox.clear();
        dqPage_clearSearchFilterTextBox.click();
        dqPage_searchFilter_TextBox.click();
        dqPage_searchFilter_TextBox.sendKeys(columnName);
        flag = dqPage_columnNames_WebElementList().size()==0?flag:false;
        Log.info("Value of flag after Invalid search "+ flag);
        return flag;
    }
    public static boolean verifyDataQueryRecurrenceClockIcon(String dataQueryName) {
        Log.info("Method :  verifyDataQueryRecurrenceClockIcon");
        boolean toggleStatus = dqPage_RecurrenceClockIcon_WebElement(dataQueryName).isDisplayed()? true : false;
        return toggleStatus;
    }

    public static void ClickOnSaveAsDraft()
    {
        WrapperFunctionUtilities.waitForElementVisibility(DataQueryPage_SaveAsDraft_Button,10,driver);
        DataQueryPage_SaveAsDraft_Button.click();
    }


    public static boolean verifyDuplicateDataQueryNameValidationError(String dataQueryName, String extractionLocation,String fileFormat) throws Exception {
        Log.info("Method : verifyDuplicateDataQueryNameValidationError");
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_ExtractionLocation_DropDown,10,driver);
        dqPage_ExtractionLocation_DropDown.click();
        CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("DataQuery", extractionLocation, "TestData"));
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_DataQueryName_TextBox, 5, driver);
        dqPage_DataQueryName_TextBox.sendKeys(dataQueryName);
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_FileFormat_DropDown,10,driver);
        WrapperFunctionUtilities.jsClick(dqPage_FileFormat_DropDown,driver);
        WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(ExcelReader.getValue("DataQuery", fileFormat, "TestData")),driver);
        clickFinishButton();
        boolean existenceOfMessage = WrapperFunctionUtilities.isElementPresent(dqPage_DuplicateDataQueryErrorMessage_WebElement(dataQueryName), "dqPage_DuplicateDataQueryErrorMessage_WebElement")?true:false;
        return existenceOfMessage;
    }

    public static void waitForColumnNamesToLoad() {
        Log.info("Method :  waitForPreviewTableContentToLoad");
        WrapperFunctionUtilities.waitForElementToBeInvisibileBy(dqPage_LoadingSymbolPreviewTable_Text, 10, driver);
    }

    public static boolean verifyColumnNamesAndDataTypeOnDefineSchemaPage(String sheetName,String fileName,String excelFormat,String columnNameRowNum,String columnDataTypeRowNum) throws Exception {
        boolean flag = true, existenceOfElement;
        Log.info("Method :  verifyColumnNamesAndDataTypeOnDefineSchemaPage");
        HashMap<String,String> testData = ExcelReader.getTestDataFromExcel("Ingestion","TestData");
        sheetName = testData.get(sheetName);
        fileName = testData.get(fileName);
        excelFormat = testData.get(excelFormat);
        columnNameRowNum = testData.get(columnNameRowNum);
        columnDataTypeRowNum = testData.get(columnDataTypeRowNum);
        LinkedHashMap<String, String> columnNames = ExcelReader.getTestDataFromExcelSheetWithRow(sheetName, fileName, Integer.parseInt(columnNameRowNum), excelFormat);
        LinkedHashMap<String, String> columnDataTypes = ExcelReader.getTestDataFromExcelSheetWithRow(sheetName, fileName, Integer.parseInt(columnDataTypeRowNum), excelFormat);

        for (String keyName: columnNames.keySet())
        {
            existenceOfElement = WrapperFunctionUtilities.isElementPresent(dqPage_ColumnNameAndDataType_WebElement(columnNames.get(keyName).toLowerCase(),columnDataTypes.get(keyName)),"dqPage_ColumnNameAndDataType_WebElement for"+columnNames.get(keyName));
            flag = !existenceOfElement?false:flag;
        }
        return flag;
    }

    // This method adds multiple conditions for Data Query
    public static void addMultipleFiltersOnTheDifferentColumns(String sheetName, String columnName1, String condition1, String value1, String linkingCondition, String columnName2, String condition2, String value2) throws Exception {
        Log.info("Method :  verifyMultipleFiltersOnTheDifferentColumns");
        HashMap<String,String> testData = ExcelReader.getTestDataFromExcel(sheetName,"TestData");
        dqPage_AddFilterCondition_Button.click();
        dqPage_ColumnFilterByIndex_DropDown(1).click();
        dqPage_ColumnFilterByIndex_TextBox(1).sendKeys(testData.get(columnName1));
        CommonPageFunctions.hyperlinkClick(testData.get(columnName1));

        dqPage_ConditionFilterByIndex_DropDown(1).click();
        dqPage_ConditionFilterByIndex_TextBox(1).sendKeys(testData.get(condition1));
        CommonPageFunctions.hyperlinkClick(testData.get(condition1));

        dqPage_FilterValueByIndex_TextBox(1).sendKeys(testData.get(value1));

        dqPage_AddFilterCondition_Button.click();

        if(linkingCondition.equals("AND")) {

            WrapperFunctionUtilities.jsClick(dqPage_AndFilterCondition_RadioButton, driver);

        } else if(linkingCondition.equals("OR"))
        {
            WrapperFunctionUtilities.jsClick(dataQueryPage_OrFilterCondition_RadioButton, driver);
        }

        dqPage_ColumnFilterByIndex_DropDown(2).click();
        dqPage_ColumnFilterByIndex_TextBox(2).sendKeys(testData.get(columnName2));
        CommonPageFunctions.hyperlinkClick(testData.get(columnName2));

        dqPage_ConditionFilterByIndex_DropDown(2).click();
        dqPage_ConditionFilterByIndex_TextBox(2).sendKeys(testData.get(condition2));
        CommonPageFunctions.hyperlinkClick(testData.get(condition2));

        dqPage_FilterValueByIndex_TextBox(2).sendKeys(testData.get(value2));

    }

    public static void editExistingIngestion(String dataQueryName) throws Exception {
        WrapperFunctionUtilities.scrollByVisibleElement(dataQueryPage_editIconByDataQueryName(dataQueryName), driver);
        dataQueryPage_editIconByDataQueryName(dataQueryName).click();
    }

    // This method verifies user is able to delete existing Filter condition
    public static boolean verifyExistingFilterConditionIsGettingDeleted() throws Exception {

        Log.info("Method :  verifyExistingFilterConditionIsGettingDeleted");

        int countOfFilterConditions = dataQueryPage_deleteFilterCondition_Button().size();
        Log.info("countOfFilterConditions : "+countOfFilterConditions);

        String expectedValueAttributeOfFilter1 =  dqPage_FilterValueByIndex_TextBox(2).getAttribute("value");
        Log.info("expectedValueAttributeOfFilter1 : "+expectedValueAttributeOfFilter1);

        dataQueryPage_deleteFilterCondition_Button().get(0).click();

        int countOfConditionsAfterDeletion = dataQueryPage_deleteFilterCondition_Button().size();
        Log.info("countOfConditionsAfterDeletion : "+countOfConditionsAfterDeletion);

        boolean verificationResult = countOfConditionsAfterDeletion<countOfFilterConditions?true:false;
        Log.info("verificationResult  : "+ verificationResult);

        String updatedValueAttributeOfFilter1 =  dqPage_FilterValueByIndex_TextBox(1).getAttribute("value");
        Log.info("updatedValueAttributeOfFilter1 : "+updatedValueAttributeOfFilter1);

        verificationResult = expectedValueAttributeOfFilter1.equals(updatedValueAttributeOfFilter1) ? verificationResult : false;
        Log.info("verificationResult  : "+ verificationResult);

        return verificationResult;
    }

    // This method verifies user is able to update existing Filter condition
    public static boolean verifyExistingFilterConditionIsGettingUpdated(String sheetName, String updatedColumnName, String updatedCondition, String updatedValue) throws Exception {

        Log.info("Method :  verifyExistingFilterConditionIsGettingUpdated");

        HashMap<String,String> testData = ExcelReader.getTestDataFromExcel(sheetName,"TestData");

        dqPage_ColumnFilterByIndex_DropDown(1).click();
        dqPage_ColumnFilterByIndex_TextBox(1).clear();
        dqPage_ColumnFilterByIndex_TextBox(1).sendKeys(testData.get(updatedColumnName));
        CommonPageFunctions.hyperlinkClick(testData.get(updatedColumnName));

        dqPage_ConditionFilterByIndex_DropDown(1).click();
        dqPage_ConditionFilterByIndex_TextBox(1).clear();
        dqPage_ConditionFilterByIndex_TextBox(1).sendKeys(testData.get(updatedCondition));
        CommonPageFunctions.hyperlinkClick(testData.get(updatedCondition));

        dqPage_FilterValueByIndex_TextBox(1).sendKeys(testData.get(updatedValue));

        boolean verificationResult = dqPage_ColumnFilterByIndex_DropDown(1).getText().equals(testData.get(updatedColumnName))?true:false;
        verificationResult= dqPage_ConditionFilterByIndex_DropDown(1).getText().equals(testData.get(updatedCondition))?verificationResult:false;
        verificationResult=  dqPage_FilterValueByIndex_TextBox(1).getAttribute("value").equals(testData.get(updatedValue))?verificationResult:false;

        //Filter Summary Verification

        String actualFilterText = dataQueryPage_FilterConditionText_WebElement.getText().trim();
        String expectedFilterText = "If "+ dqPage_ColumnFilterByIndex_DropDown(1).getText()+" "+dqPage_ConditionFilterByIndex_DropDown(1).getText()+" "+ dqPage_FilterValueByIndex_TextBox(1).getAttribute("value");

        verificationResult = actualFilterText.equals(expectedFilterText)?verificationResult:false;
        Log.info(" actualFilterText : "+ actualFilterText);
        Log.info(" expectedFilterText : "+ expectedFilterText);

        return verificationResult;
    }

    public static boolean verifyUserIsAbleToDeselectColumnNames(String sheetName, String columnName1, String columnName2) throws Exception {

        Log.info("Method :  verifyUserIsAbleToDeselectColumnNames");
        boolean flag = true;

        HashMap<String,String> testData = ExcelReader.getTestDataFromExcel(sheetName,"TestData");

        WrapperFunctionUtilities.scrollByVisibleElement(dataQueryPage_SelectColumnName_CheckBox(testData.get(columnName1)), driver);

        WrapperFunctionUtilities.jsClick(dataQueryPage_SelectColumnName_CheckBox(testData.get(columnName1)),driver);

        flag =  dataQueryPage_SelectColumnName_CheckBox(testData.get(columnName1)).isSelected()?false:true;

        dqPage_searchFilter_TextBox.click();
        dqPage_searchFilter_TextBox.sendKeys(testData.get(columnName2));

        WrapperFunctionUtilities.jsClick(dataQueryPage_SelectColumnName_CheckBox(testData.get(columnName2)),driver);

        dqPage_searchFilter_TextBox.clear();
        dqPage_clearSearchFilterTextBox.click();

        WrapperFunctionUtilities.scrollByVisibleElement(dataQueryPage_SelectColumnName_CheckBox(testData.get(columnName2)), driver);

        flag =  dataQueryPage_SelectColumnName_CheckBox(testData.get(columnName2)).isSelected()?false:flag;

        return flag;
    }

    //This method used to toggle in between And & OR Condition
    public static boolean verifyUserIsAbleToToggleBetweenAndandORCondtion() throws Exception {

        Log.info("Method :  verifyUserIsAbleToToggleBetweenAndandORCondtion");
        boolean flag = true;

        WrapperFunctionUtilities.jsClick(dataQueryPage_OrFilterCondition_RadioButton,driver);
        flag = dataQueryPage_OrFilterCondition_RadioButton.isSelected()?true:false;

        WrapperFunctionUtilities.jsClick(dqPage_AndFilterCondition_RadioButton,driver);
        flag = dqPage_AndFilterCondition_RadioButton.isSelected()?flag:false;

        return flag;
    }

    //This method verifies the filter rule summary is getting generated for Multiple Filter conditions
    public static boolean verifyFilterRuleQueryForMultipleFiltersConditions(String sheetName, String columnName1, String condition1, String value1, String linkingCondition, String columnName2, String condition2, String value2) throws Exception {

        Log.info("Method :  verifyFilterRuleQueryForMultipleFiltersConditions");
        boolean flag = true;
        HashMap<String,String> testData = ExcelReader.getTestDataFromExcel(sheetName,"TestData");
        String actualFilterText = dataQueryPage_FilterConditionText_WebElement.getText().trim();

        String expectedCondition1 = "If "+ testData.get(columnName1)+" "+testData.get(condition1)+" "+ testData.get(value1);
        String expectedCondition2 = testData.get(columnName2)+" "+testData.get(condition2)+" "+ testData.get(value2);

        String expectedFilterText = expectedCondition1 + " " + linkingCondition + " " + expectedCondition2;

        flag = actualFilterText.equals(expectedFilterText)?true:false;

        return flag;
    }

    // This method verifies Filter summary after deleting one of the filter conidtion
    public static boolean verifyFilterRuleQueryAfterDeletingFilterCondition() throws Exception {

        Log.info("Method :  verifyFilterRuleQueryAfterDeletingFilterCondition");

        String expectedColumnNameOfFilter =  dqPage_ColumnFilterByIndex_DropDown(2).getText();
        Log.info("expectedFilterConditionOfFilter : "+expectedColumnNameOfFilter);

        String expectedFilterCondition=  dqPage_ConditionFilterByIndex_DropDown(2).getText();
        Log.info("expectedFilterCondition : "+expectedFilterCondition);

        String expectedValueAttributeOfFilter =  dqPage_FilterValueByIndex_TextBox(2).getAttribute("value");
        Log.info("expectedValueAttributeOfFilter : "+expectedValueAttributeOfFilter);

        dataQueryPage_deleteFilterCondition_Button().get(0).click();

        String actualFilterText = dataQueryPage_FilterConditionText_WebElement.getText().trim();
        String expectedFilterText = "If "+ expectedColumnNameOfFilter+" "+expectedFilterCondition+" "+ expectedValueAttributeOfFilter;

        boolean verificationResult = actualFilterText.equals(expectedFilterText)?true:false;
        Log.info(" actualFilterText : "+ actualFilterText);
        Log.info(" expectedFilterText : "+ expectedFilterText);
        return verificationResult;
    }

    //This Method Clicks on Previous Button and navigates user to previous step
    public static void navigateToPreviousPage() throws Exception {
        WrapperFunctionUtilities.scrollByVisibleElement(dataQueryPage_Previous_Button, driver);
        dataQueryPage_Previous_Button.click();
        WrapperFunctionUtilities.waitForPageToLoad(30, driver);
    }

    //Unchecks all the select columns on Define Schema Page
    public static void uncheckAllColumnNamesFromDefineSchema() throws Exception {
        WrapperFunctionUtilities.waitForElementVisibility(dataQueryPage_CheckAllColumns_CheckBox,30,driver);
        WrapperFunctionUtilities.scrollByVisibleElement(dataQueryPage_CheckAllColumns_CheckBox, driver);
        if(dataQueryPage_CheckAllColumns_CheckBox.isSelected())
        {
            WrapperFunctionUtilities.jsClick(dataQueryPage_CheckAllColumns_CheckBox, driver);
        }else
        {
            WrapperFunctionUtilities.jsClick(dataQueryPage_CheckAllColumns_CheckBox, driver);
            WrapperFunctionUtilities.jsClick(dataQueryPage_CheckAllColumns_CheckBox, driver);
        }
    }

    //This Method Sets Daily Recurrence Pattern with Limited Days
    public static void setDailyRecurrencePattern(String endAfterDays) throws Exception {
        Log.info("Method :  setDailyRecurrencePattern");
        int numberOfDays = Integer.parseInt(ExcelReader.getValue("DataQuery", endAfterDays, "TestData"));
        WrapperFunctionUtilities.scrollByVisibleElement(dataQueryPage_RecurrenceOptional_CheckBox, driver);
        WrapperFunctionUtilities.jsClick(dataQueryPage_RecurrenceOptional_CheckBox, driver);
        WrapperFunctionUtilities.jsClick(dataQueryPage_EveryDay_RadioButton, driver);
        WrapperFunctionUtilities.jsClick(dataQueryPage_EndAfter_RadioButton, driver);
        dataQueryPage_EndAfter_TextBox.sendKeys(Integer.toString(numberOfDays));
    }

    public static boolean waitForInvisibilityOfDataQuery(String dataQueryName)
    {
        return WrapperFunctionUtilities.waitForElementToBeInvisibileBy(dqPage_DataQueryName_Locator(dataQueryName), 30, driver);
    }

    public static boolean verifyClusterTypeDropDownForRedShift_AdaptorDropdown_TableDropDownEnability(String clusterType1,String adaptorName, String tableName) throws Exception {

        Log.info("Method : verifyClusterTypeDropDownForRedShift_AdaptorDropdown_TableDropDownEnability");
        boolean flag , existenceOfElement;
        HashMap<String,String> dataQuerySheetData =  ExcelReader.getTestDataFromExcelSheetWithRow("DataQuery","TestData",2,"xlsx");
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_ClusterType_DropDown,10,driver);

        // user must only be able to select the Adaptor once after he has selected the Cluster Type
        existenceOfElement =  CommonPageFunctions.storeExistenceOfElement(dataQueryPage_SelectAdaptorDropDown_Locator,driver);
        flag = !existenceOfElement?true:false;

        //Verification for Redshift Cluster Type
        WrapperFunctionUtilities.jsClick(dqPage_ClusterType_DropDown,driver);
        WrapperFunctionUtilities.jsClick(CommonPageFunctions.hyperlink(dataQuerySheetData.get(clusterType1)), driver);

        // user must only be able to select the Adaptor once after he has selected the Cluster Type
        existenceOfElement =  CommonPageFunctions.storeExistenceOfElement(dataQueryPage_SelectAdaptorDropDown_Locator,driver);
        flag = existenceOfElement?flag:false;

        //user must only be able to select the schema & table once after he has selected the Cluster Type as Redshift and any adaptor with successful connection
        flag = dataQueryPage_SchemaAndTableDropDown_Disabled.size()>0?flag:false;

        dqPage_selectAdaptor_DropDown.click();
        WrapperFunctionUtilities.scrollByVisibleElement(CommonPageFunctions.hyperlink(dataQuerySheetData.get(adaptorName)), driver);
        CommonPageFunctions.hyperlinkClick(dataQuerySheetData.get(adaptorName));
        WrapperFunctionUtilities.waitForTime(3000);
        dqPage_SchemaAndTable_DropDown.click();
        dqPage_SchemaAndTableSearch_TextBox.sendKeys(dataQuerySheetData.get(tableName));
        WrapperFunctionUtilities.waitForTime(2000);//waiting for results to populate
        WrapperFunctionUtilities.jsClick(CommonPageFunctions.hyperlink(dataQuerySheetData.get(tableName)),driver);

        // Verifying Schema & Table Dropdown got enabled after selecting the Adaptor Name
        flag = !(dataQueryPage_SchemaAndTableDropDown_Disabled.size()>0)?flag:false;

        return flag;
    }

    public static boolean verifyClusterTypeDropDownForEMR_DatabaseDropdown_TableDropDownEnability(String clusterType2, String databaseName) throws Exception
    {
        boolean flag = true, existenceOfElement = true;
        HashMap<String,String> dataQuerySheetData =  ExcelReader.getTestDataFromExcelSheetWithRow("DataQuery","TestData",2,"xlsx");

        WrapperFunctionUtilities.jsClick(dqPage_ClusterType_DropDown,driver);
        WrapperFunctionUtilities.jsClick(CommonPageFunctions.hyperlink("Select"), driver);


        existenceOfElement =  CommonPageFunctions.storeExistenceOfElement(dataQueryPage_SelectDatabaseDropDown_Locator,driver);
        flag = !existenceOfElement?flag:false;

        //Verification For EMR Cluster Type
        WrapperFunctionUtilities.jsClick(dqPage_ClusterType_DropDown,driver);
        WrapperFunctionUtilities.jsClick(CommonPageFunctions.hyperlink(dataQuerySheetData.get(clusterType2)), driver);

        // user must only be able to select the database once after he has selected the Cluster Type
        existenceOfElement =  CommonPageFunctions.storeExistenceOfElement(dataQueryPage_SelectDatabaseDropDown_Locator,driver);
        flag = existenceOfElement?flag:false;


        //user must only be able to select the Table once after he has selected the Cluster Type as EMR and any database
        flag = dataQueryPage_TableDropDown_Disabled.size()>0?flag:false;

        WrapperFunctionUtilities.waitForElementVisibility(dqPage_selectDatabase_DropDown,10,driver);
        WrapperFunctionUtilities.jsClick(dqPage_selectDatabase_DropDown, driver);
        WrapperFunctionUtilities.scrollByVisibleElement(CommonPageFunctions.hyperlink(dataQuerySheetData.get(databaseName)), driver);
        CommonPageFunctions.hyperlinkClick(dataQuerySheetData.get(databaseName));

        WrapperFunctionUtilities.waitForTime(3000); //waiting For Enability of Table Dropdown
        // Verifying Table Dropdown got enabled after selecting the DataBaseName
        flag = !(dataQueryPage_TableDropDown_Disabled.size()>0)?flag:false;

        return flag;
    }


    public static boolean verifyDataQueryDefineSourceEMRTableDropDownIsNotPresent() {
        boolean result;
        try {
            Log.info("verifyDataQueryDefineSourceEMRTableDropDownIsPresent");
            result = WrapperFunctionUtilities.isElementPresent(dqPage_Table_DropDown, "Table Dropdown");
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Table Dropdown is not present");
            result =false;
        }
        return result;
    }

    public static boolean verifyDataQueryDefineSourceEMRTableDropDownIsPresent(){
        boolean result;
        Log.info("verifyDataQueryDefineSourceEMRTableDropDownIsPresent");
        dpPage_CustomSQL_CheckBox.click();
        result = WrapperFunctionUtilities.isElementPresent(dqPage_Table_DropDown, "Table Dropdown");
        System.out.println("Table Dropdown Present is "+ result);
        return result;
    }

    public static void clickOnPreviewTableButton() throws Exception {
        Log.info("Method : clickOnPreviewTableButton");
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_SchemaAndTable_DropDown, 30, driver);
        WrapperFunctionUtilities.waitForElementVisibility(dataQueryPage_Preview_Button, 30, driver);
        dataQueryPage_Preview_Button.click();
        WrapperFunctionUtilities.waitForElementVisibility(dataQueryPage_TablePreview_Header, 30, driver);

    }

    public static boolean verifyErrorMessageForEmptyFileLocation(String extractionLocation,String adaptorName, String fileFormat) throws Exception {
        Log.info("Method : TransactionalInfo_Local_Parquet_Extraction");
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_ExtractionLocation_DropDown,10,driver);
        dqPage_ExtractionLocation_DropDown.click();
        CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("DataQuery", extractionLocation, "TestData"));
        selectExistingAdaptor("DataQuery",adaptorName);
        String dataQueryID = CommonPageFunctions.generateRandomString();
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_DataQueryName_TextBox, 5, driver);
        dqPage_DataQueryName_TextBox.sendKeys(dataQueryID);
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_FileFormat_DropDown,10,driver);
        WrapperFunctionUtilities.jsClick(dqPage_FileFormat_DropDown,driver);
        WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(ExcelReader.getValue("DataQuery", fileFormat, "TestData")),driver);
        clickFinishButton();
        boolean flag =  WrapperFunctionUtilities.isElementPresent(dataQueryPage_ErrorMessage_Text("File Location"),"Error Message For Location Name");
        return flag;
    }

    public static void selectExistingAdaptor(String sheetName, String adaptorName) throws Exception {
        Log.info("Method : selectExistingAdaptor");
        //selectAdaptor.click();
        WrapperFunctionUtilities.jsClick(dataQueryPage_SelectAdaptor_Dropdown, driver);
        dataQueryPage_SelectAdaptorDropdown_Input.sendKeys(ExcelReader.getValue(sheetName,adaptorName,"TestData"));
        WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(ExcelReader.getValue(sheetName, adaptorName, "TestData")), driver);
    }

    public static boolean verifyErrorMessageForNotSelectingFileFormatForS3() throws Exception {
        Log.info("Method : verifyErrorMessageForNotSelectingFileFormat");
        dataQueryPage_FileLocation_TextBox.sendKeys("TempValue");
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_ExtractionLocation_DropDown,10,driver);
        WrapperFunctionUtilities.jsClick(dqPage_FileFormat_DropDown,driver);
        CommonPageFunctions.hyperlinkClick("Select File Format");
        clickFinishButton();
        boolean flag =  WrapperFunctionUtilities.isElementPresent(dataQueryPage_ErrorMessage_Text("File Format"),"Error Message For Location Name");
        return flag;
    }

    public static void clickOnCancelButton()
    {
        Log.info("Method : clickOnCancelButton");
        WrapperFunctionUtilities.waitForElementVisibility(dataQueryPage_Cancel_Button, 30, driver);
        dataQueryPage_Cancel_Button.click();
    }

    public static boolean verifyErrorMessageForNotSelectingFileFormatForLocalExtraction(String extractionLocation) throws Exception {
        Log.info("Method : verifyErrorMessageForNotSelectingFileFormatForLocalExtraction");
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_ExtractionLocation_DropDown,10,driver);
        dqPage_ExtractionLocation_DropDown.click();
        CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("DataQuery", extractionLocation, "TestData"));
        String dataQueryID = CommonPageFunctions.generateRandomString();
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_DataQueryName_TextBox, 5, driver);
        dqPage_DataQueryName_TextBox.sendKeys(dataQueryID);
        WrapperFunctionUtilities.jsClick(dqPage_FileFormat_DropDown,driver);
        CommonPageFunctions.hyperlinkClick("Select File Format");
        clickFinishButton();
        boolean flag =  WrapperFunctionUtilities.isElementPresent(dataQueryPage_ErrorMessage_Text("File Format"),"Error Message For Location Name");
        return flag;
    }

    public static boolean verifyErrorMessageForEmptySourceColumnMappingDropDown(String extractionLocation,String adaptorName, String targetTableName, String mappingColumnName) throws Exception {
        Log.info("Method : verifyErrorMessageForEmptySourceColumnMappingDropDown");
        HashMap<String,String> dataQuerySheetTestData =  ExcelReader.getTestDataFromExcelSheetWithRow("DataQuery","TestData",2,"xlsx");
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_ExtractionLocation_DropDown,10,driver);
        dqPage_ExtractionLocation_DropDown.click();
        CommonPageFunctions.hyperlinkClick(dataQuerySheetTestData.get(extractionLocation));
        selectExistingAdaptor("DataQuery",adaptorName);
        String dataQueryID = CommonPageFunctions.generateRandomString();
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_DataQueryName_TextBox, 5, driver);
        dqPage_DataQueryName_TextBox.sendKeys(dataQueryID);
        WrapperFunctionUtilities.waitForTime(2000);//waiting for results to populate
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_TargetTable_DropDown,10,driver);
        WrapperFunctionUtilities.jsClick(dqPage_TargetTable_DropDown,driver);
        dqPage_TargetTableSearch_TextBox.sendKeys(dataQuerySheetTestData.get(targetTableName));
        WrapperFunctionUtilities.waitForTime(2000);//waiting for results to populate
        WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(dataQuerySheetTestData.get(targetTableName)),driver);
        int count = 0;
        for (WebElement tableDropdownByIndex : dataQueryPage_SourceTableMapping_DropDown)
        {
            if(count>=2)
            {
               break;
            }
        WrapperFunctionUtilities.scrollByVisibleElement(tableDropdownByIndex, driver);
        WrapperFunctionUtilities.jsClick(tableDropdownByIndex,driver);
        WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(dataQuerySheetTestData.get(mappingColumnName)),driver);
        count++;
        }
        clickFinishButton();
        boolean flag =  WrapperFunctionUtilities.isElementPresent(dataQueryPage_MissingSourceTableMapping_ErrorMessage,"dataQueryPage_MissingSourceTableMapping_ErrorMessage");
        WrapperFunctionUtilities.scrollByVisibleElement(dataQueryPage_MissingSourceTableMapping_ErrorMessage, driver);
        return flag;
    }

    public static boolean verifyColumnNameOnDataQueryPage()
    {
        Log.info("Method : verifyColumnNameOnDataQueryPage");
        boolean flag =  WrapperFunctionUtilities.isElementPresent(dataQueryPage_ColumnName_WebElement("Name"),"Name");
        flag =  WrapperFunctionUtilities.isElementPresent(dataQueryPage_ColumnName_WebElement("Name"),"Name")?flag:false;
        flag =  WrapperFunctionUtilities.isElementPresent(dataQueryPage_ColumnName_WebElement("Table Name"),"Table Name")?flag:false;
        flag =  WrapperFunctionUtilities.isElementPresent(dataQueryPage_ColumnName_WebElement("Status"),"Status")?flag:false;
        flag =  WrapperFunctionUtilities.isElementPresent(dataQueryPage_ColumnName_WebElement("Last Execution"),"Last Execution")?flag:false;
        flag =  WrapperFunctionUtilities.isElementPresent(dataQueryPage_ColumnName_WebElement("Duration"),"Duration")?flag:false;
        flag =  WrapperFunctionUtilities.isElementPresent(dataQueryPage_ColumnName_WebElement("Next Execution"),"Next Execution")?flag:false;
        flag =  WrapperFunctionUtilities.isElementPresent(dataQueryPage_ColumnName_WebElement("Created by"),"Created by")?flag:false;
        flag =  WrapperFunctionUtilities.isElementPresent(dataQueryPage_ColumnName_WebElement("Recurrence"),"Recurrence")?flag:false;
        flag =  WrapperFunctionUtilities.isElementPresent(dataQueryPage_ColumnName_WebElement("Actions"),"Actions")?flag:false;
        return flag;
    }

    public static boolean verifyElementsOnDataQueryPage()
    {
        Log.info("Method : verifyElementsOnDataQueryPage");
        boolean flag =  WrapperFunctionUtilities.isElementPresent(dataQueryPage_SearchFilter_TextBox,"dataQueryPage_SearchFilter_TextBox");
        flag =  WrapperFunctionUtilities.isElementPresent(dataQueryPage_Show_DropDown,"dataQueryPage_Show_DropDown")?flag:false;
        flag =  WrapperFunctionUtilities.isElementPresent(dataQueryPage_CreatedBy_DropDown,"dataQueryPage_CreatedBy_DropDown")?flag:false;
        flag =  WrapperFunctionUtilities.isElementPresent(dataQueryPage_ApplyFilters_Button,"dataQueryPage_ApplyFilters_Button")?flag:false;
        flag =  WrapperFunctionUtilities.isElementPresent(dataQueryPage_Pagination_WebElement,"Last dataQueryPage_Pagination_WebElement")?flag:false;
        flag =  WrapperFunctionUtilities.isElementPresent(dataQueryPage_LogsTab_WebElement,"dataQueryPage_LogsTab_WebElement")?flag:false;
        dataQueryPage_LogsTab_WebElement.click();
        return flag;
    }

    public static String TransactionalInfo_S3_Extraction(String adaptorName, String fileFormat,String extractionLocation) throws Exception {
        Log.info("Method : TransactionalInfo_S3_Extraction");
        HashMap<String,String> dataQuerySheetTestData =  ExcelReader.getTestDataFromExcelSheetWithRow("DataQuery","TestData",2,"xlsx");
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_ExtractionLocation_DropDown,10,driver);
        selectExistingAdaptor("DataQuery",adaptorName);
        String dataQueryID = CommonPageFunctions.generateRandomString();
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_DataQueryName_TextBox, 5, driver);
        dqPage_DataQueryName_TextBox.sendKeys(dataQueryID);
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_FileFormat_DropDown,10,driver);
        WrapperFunctionUtilities.jsClick(dqPage_FileFormat_DropDown,driver);
        WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(dataQuerySheetTestData.get(fileFormat)),driver);
        dataQueryPage_FileLocation_TextBox.sendKeys(dataQuerySheetTestData.get(extractionLocation));
        return dataQueryID;
    }

    public static boolean verifyDataQueryRecurrenceToggleFunctionlity(String dataQueryName) {
        Log.info("Method :  verifyDataQueryRecurrenceToggleFunctionlity");
        dqPage_RecurrenceToogleButton_WebElement(dataQueryName).click();
        boolean flag = WrapperFunctionUtilities.isElementPresent(dataQueryPage_RecurrenceStopped_WebElement(dataQueryName),"dataQueryPage_RecurrenceStopped_WebElement");
        Log.info("Existence Toaster Message for Stopped "+ flag);
        String toggleStatus = dqPage_RecurrenceToogleButton_WebElement(dataQueryName).getAttribute("active");
         flag = (toggleStatus.equals( "false")) ? flag : false;
        Log.info("Toggle Status : "+ toggleStatus );
         Log.info("Toggle Status Flag : "+ flag );
        dqPage_RecurrenceToogleButton_WebElement(dataQueryName).click();
        flag = WrapperFunctionUtilities.isElementPresent(dataQueryPage_RecurrenceStarted_WebElement(dataQueryName),"dataQueryPage_RecurrenceStarted_WebElement")?flag:false;
        Log.info("Existence Toaster Message for Started "+ flag);
        toggleStatus = dqPage_RecurrenceToogleButton_WebElement(dataQueryName).getAttribute("active");
        flag = toggleStatus != null ? flag : false;
        Log.info("Toggle Status : "+ toggleStatus );
        Log.info("Toggle Status Flag : "+ flag );
        return flag;
    }

    public static boolean verifyDeselectedColumnsShowsInFilterOnDefineSchemaPage(String sheetName, String columnName1, String columnName2) throws Exception {
        Log.info("Method :  verifyDeselectedColumnsShowsInFilterOnDefineSchemaPage");
        HashMap<String,String> testData = ExcelReader.getTestDataFromExcel(sheetName,"TestData");

        WrapperFunctionUtilities.scrollByVisibleElement(dataQueryPage_SelectColumnName_CheckBox(testData.get(columnName1)), driver);
        WrapperFunctionUtilities.jsClick(dataQueryPage_SelectColumnName_CheckBox(testData.get(columnName1)),driver);

        WrapperFunctionUtilities.scrollByVisibleElement(dataQueryPage_SelectColumnName_CheckBox(testData.get(columnName2)), driver);
        WrapperFunctionUtilities.jsClick(dataQueryPage_SelectColumnName_CheckBox(testData.get(columnName2)),driver);

        WrapperFunctionUtilities.scrollByVisibleElement(dqPage_AddFilterCondition_Button,driver);
        dqPage_AddFilterCondition_Button.click();

        dqPage_ColumnFilterByIndex_DropDown(1).click();
        dqPage_ColumnFilterByIndex_TextBox(1).sendKeys(testData.get(columnName1));
        boolean flag = WrapperFunctionUtilities.isElementPresent(CommonPage.hyperlink(testData.get(columnName1)), columnName1)?true:false;
        CommonPageFunctions.hyperlinkClick(testData.get(columnName1));

        dqPage_ColumnFilterByIndex_DropDown(1).click();
        dqPage_ColumnFilterByIndex_TextBox(1).clear();
        dqPage_ColumnFilterByIndex_TextBox(1).sendKeys(testData.get(columnName2));
        flag = WrapperFunctionUtilities.isElementPresent(CommonPage.hyperlink(testData.get(columnName2)), columnName2)?flag:false;
        CommonPageFunctions.hyperlinkClick(testData.get(columnName2));

        return flag;
    }

    public static boolean verifyPartFilesFieldTextBoxShowsErrorMessageForNegativeNumbers() throws Exception {
        Log.info("Method :  verifyPartFilesFieldTextBoxShowsErrorMessageForNegativeNumbers");
        WrapperFunctionUtilities.scrollByVisibleElement(dataQueryPage_NoOfPartitionFiles_TextBox,driver);
        dataQueryPage_NoOfPartitionFiles_TextBox.sendKeys("-1");
        boolean flag = WrapperFunctionUtilities.isElementPresent(dataQueryPage_NoOfPartitionFiles_ErrorMessage, "dataQueryPage_NoOfPartitionFiles_ErrorMessage");
        dataQueryPage_NoOfPartitionFiles_TextBox.clear();
        dataQueryPage_NoOfPartitionFiles_TextBox.sendKeys("1");
        return flag;
    }

    public static List<String> storeColumnNamesFromDefineSchemaTable()
    {
       Log.info("Method :  storeColumnNamesFromDefineSchemaTable");
       List<WebElement> columnNameWebElement =  dqPage_columnNames_WebElementList();
       ArrayList<String> columnNameList = new ArrayList<>();
       for(WebElement columnName : columnNameWebElement)
       {
           columnNameList.add(columnName.getText());
       }
       return columnNameList;
    }

    public static void selectDatabaseColumnMappingForTargetTable(List<String> columnNameList) throws Exception {
        Log.info("Method :  selectDatabaseColumnMappingForTargetTable");
        int count = 0;
        for (WebElement tableDropdownByIndex : dataQueryPage_SourceTableMapping_DropDown)
        {
            WrapperFunctionUtilities.scrollByVisibleElement(tableDropdownByIndex, driver);
            WrapperFunctionUtilities.jsClick(tableDropdownByIndex,driver);
            dataQueryPage_SourceTableMappingSearch_TextBox.sendKeys(columnNameList.get(count));
            WrapperFunctionUtilities.jsClick(dataQueryPage_SourceTableMappingSearchResults_WebElement(columnNameList.get(count)), driver);
            System.out.println("Count "+ count+ "  "+columnNameList.get(count));
            count++;
        }
    }

    public static String TransactionalInfo_Adaptor_Extraction(String adaptorName,String targetTable) throws Exception {
        Log.info("Method : TransactionalInfo_Adaptor_Extraction");
        HashMap<String,String> dataQuerySheetTestData =  ExcelReader.getTestDataFromExcelSheetWithRow("DataQuery","TestData",2,"xlsx");
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_ExtractionLocation_DropDown,10,driver);
        selectExistingAdaptor("DataQuery",adaptorName);
        String dataQueryID = CommonPageFunctions.generateRandomString();
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_DataQueryName_TextBox, 5, driver);
        dqPage_DataQueryName_TextBox.sendKeys(dataQueryID);
        WrapperFunctionUtilities.waitForTime(2000);//waiting for results to populate
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_TargetTable_DropDown,10,driver);
        WrapperFunctionUtilities.jsClick(dqPage_TargetTable_DropDown,driver);
        dqPage_TargetTableSearch_TextBox.sendKeys(dataQuerySheetTestData.get(targetTable));
        WrapperFunctionUtilities.waitForTime(2000);//waiting for results to populate
        WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(dataQuerySheetTestData.get(targetTable)),driver);
        WrapperFunctionUtilities.isElementPresent(dataQueryPage_InsertOverWrite_RadioButton, "dataQueryPage_InsertOverWrite_RadioButton");
        dataQueryPage_InsertOverWrite_RadioButton.click();
        return dataQueryID;
    }

    public static boolean verifyNumberOfRowsDisplayedInTable(int expectedCount) throws Exception {
        Log.info("Method : verifyNumberOfRowsDisplayedInTable");
        for(int rowIndex = 0; rowIndex<expectedCount;rowIndex=rowIndex+6)
        {
            WrapperFunctionUtilities.scrollByVisibleElement(dataQueryPage_RowsOnLandingPage_WebElement(rowIndex).get(0),driver);
           logicalResultFlag =  dataQueryPage_RowsOnLandingPage_WebElement(rowIndex).size()>0?true:false;
           if(!logicalResultFlag)
           {
               return true;
           }
        }
        expectedCount--;
        logicalResultFlag =  dataQueryPage_RowsOnLandingPage_WebElement(expectedCount).size()>0?true:false;
        //Boundary value condition Check
        expectedCount++;
        logicalResultFlag =  dataQueryPage_RowsOnLandingPage_WebElement(expectedCount).size()>0?false:logicalResultFlag;
        return logicalResultFlag;
    }
    public static boolean verifyNumberOfRowsDisplayedInPastRunTable(int expectedCount) throws Exception {
        Log.info("Method : verifyNumberOfRowsDisplayedInPastRunTable");
        for(int rowIndex = 0; rowIndex<expectedCount;rowIndex=rowIndex+6)
        {
            if(dataQueryPage_RowsOnPastRunsTab_WebElement(rowIndex).size()>0) {
                WrapperFunctionUtilities.scrollByVisibleElement(dataQueryPage_RowsOnPastRunsTab_WebElement(rowIndex).get(0), driver);
                logicalResultFlag = dataQueryPage_RowsOnPastRunsTab_WebElement(rowIndex).size() > 0 ? true : false;
                if (!logicalResultFlag) {
                    return true;
                }
            } else
            {
                return true;
            }
        }
        expectedCount--;
        logicalResultFlag =  dataQueryPage_RowsOnPastRunsTab_WebElement(expectedCount).size()>0?true:false;
        //Boundary value condition Check
        expectedCount++;
        logicalResultFlag =  dataQueryPage_RowsOnPastRunsTab_WebElement(expectedCount).size()>0?false:logicalResultFlag;
        return logicalResultFlag;
    }

    public static void selectNumberOfRecordsFromPaginationDropDown(int expectedCount) throws Exception {
        Log.info("Method : selectNumberOfRecordsFromPaginationDropDown");
        Select select = new Select(dataQueryPage_Pagination_DropDown);
        select.selectByValue(Integer.toString(expectedCount));
        WrapperFunctionUtilities.waitForElementToBeInvisibileBy(CommonPage.commonPage_LoadingSymbolPreviewTable_Text, 5, driver);

    }

    public static void navigateToDataQueryLogsTab()
    {
        Log.info("Method : navigateToDataQueryLogsTab");
        WrapperFunctionUtilities.waitForElementVisibility(dataQueryPage_LogsTab_WebElement,10,driver);
        dataQueryPage_LogsTab_WebElement.click();
        //WrapperFunctionUtilities.waitForElementToBeInvisibileBy(CommonPage.commonPage_LoadingSymbolPreviewTable_Text, 10, driver);
        WrapperFunctionUtilities.waitForTime(3000);
    }

    public static void openPastRunForFirstSuccessfulDataQueryOnHomePage() throws Exception {
        System.out.println("Method :  openPastRunForFirstSuccessfulDataQueryOnHomePage");
        Log.info("Method : openPastRunForFirstSuccessfulDataQueryOnHomePage");
        int counter = 0;
        //dpPage_RightArrow_WebButton.click();
        List<WebElement> countOfActiveIngestionRunButtons = dqPage_FirstSuccessfulDataQueryRunButton_WebElementList();
        Log.info("countOfActiveIngestionRunButtons  " + countOfActiveIngestionRunButtons.size());
        while (countOfActiveIngestionRunButtons.size() <= 0) {
            counter++;
            WrapperFunctionUtilities.scrollByVisibleElement(dqPage_lastVisibleDataQueryName, driver);
            countOfActiveIngestionRunButtons = dqPage_FirstSuccessfulDataQueryRunButton_WebElementList();
            Log.info("countOfActiveIngestionRunButtons  " + countOfActiveIngestionRunButtons.size());
            if (counter == 3) {
                dpPage_RightArrow_WebButton.click();
                counter = 0;
            }
        }
        runFirstSuccessfulDataQueryName_WebElement.click();
        WrapperFunctionUtilities.waitForElementVisibility(CommonPage.commonPage_PastRuns_WebElement, 30, driver);
    }

    public static void searchAndOpenPastRunsForExistingDataQuery(String dataQueryName) throws Exception {
        Log.info("Method : searchAndOpenPastRunsForExistingDataQuery");
        dqPage_searchFilter_TextBox.sendKeys(dataQueryName);
        dataQueryPage_ApplyFilters_Button.click();
        WrapperFunctionUtilities.waitForElementVisibility(dqPage_lastVisibleDataQueryName,30,driver);
        try {
            dataQueryPage_DataQueryName_WebElement(dataQueryName).click();
        }catch (Exception exception)
        {
            WrapperFunctionUtilities.refreshPageWithPageLoadTimeout(driver);
            WrapperFunctionUtilities.waitForElementVisibility(dqPage_searchFilter_TextBox,30, driver);
            WrapperFunctionUtilities.scrollByVisibleElement(dataQueryPage_DataQueryName_WebElement(dataQueryName),driver);
            dataQueryPage_DataQueryName_WebElement(dataQueryName).click();
        }
        WrapperFunctionUtilities.waitForElementVisibility(CommonPage.commonPage_PastRuns_WebElement, 30, driver);

    }
}
