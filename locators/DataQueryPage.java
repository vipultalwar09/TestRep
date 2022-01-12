package com.RDM.Merger.locators;

import com.utility.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DataQueryPage extends TestBase {

    public DataQueryPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[normalize-space(text())='Create New Data Query']")
    public static WebElement dqPage_CreateNewDataQuery_WebElement;

    @FindBy(xpath = "//label[contains(text(),'Cluster Type')]//following::div[1]")
    public static WebElement dqPage_ClusterType_DropDown;

    @FindBy(xpath ="//label[normalize-space(text())='Adaptor']//following-sibling::p/div[text()='Select']")
    public static WebElement dqPage_selectAdaptor_DropDown;

    @FindBy(xpath ="//label[normalize-space(text())='Database']//following-sibling::p/div[text()='Select']")
    public static WebElement dqPage_selectDatabase_DropDown;

    @FindBy(xpath ="//label[normalize-space(text())='Schema and Table']//following-sibling::p/div[1]")
    public static WebElement dqPage_SchemaAndTable_DropDown;

    @FindBy(xpath = "//label[normalize-space(text())='Schema and Table']//following-sibling::p//input")
    public static WebElement dqPage_SchemaAndTableSearch_TextBox;

    @FindBy(xpath = "//app-zs-select//p//div[text()='Load Existing']")
    public static WebElement dqPage_ExtractionLocation_DropDown;

    @FindBy(xpath = "//input[@placeholder='Name']")
    public static WebElement dqPage_DataQueryName_TextBox;

    @FindBy(xpath = "//label[normalize-space(text())='File Format']//following-sibling::p/div[1]")
    public static WebElement dqPage_FileFormat_DropDown;

    @FindBy(xpath = "//span[text()='Schedule Recurring Extraction (Optional)']//preceding-sibling::label//input")
    public static WebElement dqPage_scheduleRecurringExtraction_Checkbox;

    @FindBy(xpath = "//button[contains(text(),'Next')]")
    public static WebElement dqPage_Next_Button;

    @FindBy(xpath = "//button[contains(text(),'Finish')]")
    public static WebElement dqPage_finish_Button;

    @FindBy(xpath = "//button[text()='Save as draft']")
    public static WebElement DataQueryPage_SaveAsDraft_Button;

    @FindBy(xpath = "//label[normalize-space(text())='Schema and Table']//following-sibling::p//div[1]")
    public static WebElement DataQueryPage_RedshiftTableSelected_WebElement;

    @FindBy(xpath = "//label[normalize-space(text())='Time']//following-sibling::p//input")
    public static WebElement dqPage_RecurringTime_TextBox;

    @FindBy(xpath = "(//div[@role='row']//div//a[contains(@class,'name-color')])[last()]")
    public static WebElement dqPage_lastVisibleDataQueryName;

    @FindBy(xpath = "//zs-tab[normalize-space(text())='Data Queries']")
    public static WebElement dataQueryPage_DataQueries_link;

    @FindBy(xpath = "//h4[text()='Define Extraction Location']/following::app-adaptor-data-source//p/div/label/input")
    public static WebElement dataQueryPage_SelectAdaptorDropdown_Input;

    @FindBy(xpath = "//h4[text()='Define Extraction Location']/following::app-adaptor-data-source//p/div[1]")
    public static WebElement dataQueryPage_SelectAdaptor_Dropdown;

    @FindBy(xpath = "(//div[@role='row']//div//a[contains(@class,'zs-icon-play-circle') and @disabled ='true'])[last()]")
    public static WebElement dqPage_lastdisabledDataQueryRunButton;

    @FindBy(xpath = "(//span[normalize-space(text())='Complete']//ancestor::div[@role='row']//div//a[contains(@class,'zs-icon-play-circle') and not(@disabled)])[1]")
    public static WebElement dqPage_FirstSuccessfulDataQueryRunButton_WebElement;

    @FindBy(xpath = "(//span[normalize-space(text())='Complete']//ancestor::div[@role='row']//div//a[contains(@class,'zs-icon-play-circle') and not(@disabled)])[1]//ancestor::div[@role='row']//a[@class='name-color']//span")
    public static WebElement runFirstSuccessfulDataQueryName_WebElement;

    @FindBy(xpath = "//span[text()='Process re-triggered successfully']")
    public static WebElement dqPage_processRetriggeredInformatonBanner_WebElement;

    @FindBy(xpath = "//button[@next]")
    public static WebElement dpPage_RightArrow_WebButton;

    @FindBy(xpath = "//input[@name='isCustomSQL']//following-sibling::span")
    public static WebElement dpPage_CustomSQL_CheckBox;

    @FindBy(xpath = "//app-zs-textarea[@name='customQuery']//textarea[@input]")
    public static WebElement dpPage_CustomQuery_TextBox;

    @FindBy(xpath ="//label[normalize-space(text())='Table']//following-sibling::p/div[1]")
    public static WebElement dqPage_Table_DropDown;

    @FindBy(xpath = "//th//a[text()='Column Name']")
    public static WebElement dqPage_ColumnName_Header;

    @FindBy(xpath = "//th//a[text()='Type']")
    public static WebElement dqPage_DataType_Header;

    @FindBy(xpath = "//div//app-extraction-filter//h4[text()='Filter']")
    public static WebElement dqPage_Filter_Header;

    @FindBy(xpath = "//input[@placeholder='Search']")
    public static WebElement dqPage_searchFilter_TextBox;

    @FindBy(xpath = "//input[@placeholder='Search']//following-sibling::a[contains(@class,'zs-icon-close-circle-fill')]")
    public static WebElement dqPage_clearSearchFilterTextBox;

    @FindBy(xpath = "//a[@class='add-condition-btn']//span")
    public static WebElement dqPage_AddFilterCondition_Button;

    @FindBy(xpath = "//input[@type='radio' and @ng-reflect-value='AND']")
    public static WebElement dqPage_AndFilterCondition_RadioButton;

    @FindBy(xpath = "//input[@type='radio' and @ng-reflect-value='OR']")
    public static WebElement dataQueryPage_OrFilterCondition_RadioButton;

    @FindBy(xpath = "//app-extraction-filter//div[@class='if-condition-padding']")
    public static WebElement dataQueryPage_FilterConditionText_WebElement;

    @FindBy(xpath = "//input[@name='checkAllColunm']")
    public static WebElement dataQueryPage_CheckAllColumns_CheckBox;

    @FindBy(xpath = "//button[text()='Previous']")
    public static WebElement dataQueryPage_Previous_Button;

    @FindBy(xpath = "//p[text()='No items match your search.']")
    public static WebElement dataQueryPage_NoResultsFound_WebElement;

    @FindBy(xpath = "//span[text()='Recurrence (Optional)']//preceding-sibling::input")
    public static WebElement dataQueryPage_RecurrenceOptional_CheckBox;

    @FindBy(xpath = "//span[text()='End after']//preceding-sibling::input")
    public static WebElement dataQueryPage_EndAfter_RadioButton;

    @FindBy(xpath = "//span[text()='End after']//ancestor::label/following-sibling::div//input")
    public static WebElement dataQueryPage_EndAfter_TextBox;

    @FindBy(xpath ="//input[@ng-reflect-value='everyday']//following-sibling::span")
    public static WebElement dataQueryPage_EveryDay_RadioButton;

    @FindBy(xpath = "//span[@class='dbTableSelect']//p[@disabled]")
    public static List<WebElement> dataQueryPage_TableDropDown_Disabled;

    @FindBy(xpath = "//label[normalize-space(text())='Schema and Table']//following-sibling::p[@disabled]")
    public static List<WebElement> dataQueryPage_SchemaAndTableDropDown_Disabled;

    @FindBy(xpath = "//input[@placeholder='Delimiter']")
    public static WebElement dataQueryPage_Delimeter_TextBox;

    @FindBy(xpath = "//button//span[text()='Preview']")
    public static WebElement dataQueryPage_Preview_Button;

    @FindBy(xpath = "//h4[text()='Table Preview']")
    public static WebElement dataQueryPage_TablePreview_Header;

    @FindBy(xpath = "//input[@placeholder='File Location']")
    public static WebElement dataQueryPage_FileLocation_TextBox;

    @FindBy(xpath = "(//button[text()='Cancel'])[last()]")
    public static WebElement dataQueryPage_Cancel_Button;

    @FindBy(xpath = "//td[1]//div[text()='Select']")
    public static List<WebElement> dataQueryPage_SourceTableMapping_DropDown;

    @FindBy(xpath ="//label[normalize-space(text())='Target']//following-sibling::p/div[1]")
    public static WebElement dqPage_TargetTable_DropDown;

    @FindBy(xpath ="//label[normalize-space(text())='Target']//following-sibling::p//input")
    public static WebElement dqPage_TargetTableSearch_TextBox;

    @FindBy(xpath = "//td[1]//div[text()='Select']//following::p[text()='Value required.']")
    public static WebElement dataQueryPage_MissingSourceTableMapping_ErrorMessage;

    @FindBy(xpath = "//input[@placeholder='Search']")
    public static WebElement dataQueryPage_SearchFilter_TextBox;

    @FindBy(xpath ="//label[normalize-space(text())='Show']//following-sibling::p/div[1]")
    public static WebElement dataQueryPage_Show_DropDown;

    @FindBy(xpath ="//label[normalize-space(text())='Created']//following-sibling::p/div[1]")
    public static WebElement dataQueryPage_CreatedBy_DropDown;

    @FindBy(xpath ="//button[normalize-space(text())='Apply']")
    public static WebElement dataQueryPage_ApplyFilters_Button;

    @FindBy(xpath ="//zs-pagination[@class='zs-pagination']")
    public static WebElement dataQueryPage_Pagination_WebElement;

    @FindBy(xpath ="//zs-tab[text()='Logs']")
    public static WebElement dataQueryPage_LogsTab_WebElement;

    @FindBy(xpath ="//input[@placeholder='No. of Part Files']")
    public static WebElement dataQueryPage_NoOfPartitionFiles_TextBox;

    @FindBy(xpath = "//input[@validationmessagetext='Value contains illegal characters']")
    public static WebElement dataQueryPage_NoOfPartitionFiles_ErrorMessage;

    @FindBy(xpath = "//input[@ng-reflect-value='insertOverWrite']//following-sibling::span")
    public static WebElement dataQueryPage_InsertOverWrite_RadioButton;

    @FindBy(xpath = "//div[contains(@class,'zs-search-dropdown') and not(contains(@style,'display: none;'))]//input[@type='text']")
    public static WebElement dataQueryPage_SourceTableMappingSearch_TextBox;

    @FindBy(xpath = "//span[@id='pageSelect']//select")
    public static WebElement dataQueryPage_Pagination_DropDown;

    public static By dqPage_FirstSuccessfulDataQueryRunButton_Locator = By.xpath("(//span[normalize-space(text())='Complete']//ancestor::div[@role='row']//div//a[contains(@class,'zs-icon-play-circle') and not(@disabled)])[1]");
    public static By dqPage_LoadingSymbolPreviewTable_Text = By.xpath("//p[contains(text(),'Your results are loading')]");
    public static By dataQueryPage_SelectAdaptorDropDown_Locator = By.xpath("//label[normalize-space(text())='Adaptor']//following-sibling::p/div[text()='Select']");
    public static By dataQueryPage_SelectDatabaseDropDown_Locator = By.xpath("//label[normalize-space(text())='Database']//following-sibling::p/div[text()='Select']");


    public static WebElement dqPage_RecurrenceToogleButton_WebElement(String extractionName)
    {
        return driver.findElement(By.xpath("(//span[contains(.,'"+extractionName+"')])[3]//ancestor::div[@role='row']//app-zs-toggle//p"));
    }

    public static By dqPage_SchemaAndTableDropdownOptions_xpathLocator(String tableName)
    {
        return By.xpath("//a//span[@zs-dropdown-option and text()='"+tableName+"']");
    }

    public static WebElement DataQueryPage_EditDataQuery_Icon(String dataQueryName){
        return driver.findElement(By.xpath("(//span[normalize-space(text())='"+dataQueryName+"']/following::div/span//a)[3]"));
    }

    public static WebElement DataQueryPage_DeleteDataQuery_Icon(String dataQueryName) {
        return driver.findElement(By.xpath("(//span[normalize-space(text())='" + dataQueryName + "']/following::div/span//a)[4]"));
    }

    public static List<WebElement> dqPage_FirstSuccessfulDataQueryRunButton_WebElementList()
    {
        return driver.findElements(dqPage_FirstSuccessfulDataQueryRunButton_Locator);
    }

    public static WebElement statusOfDataQuery(String dataQueryName,String status)
    {
        WebElement statusElement = driver.findElement(By.xpath("//span[text()='"+dataQueryName+"']//ancestor::div[@role='gridcell']//following-sibling::div//app-idata-job-status//span[contains(text(),'"+status+"')]"));
        return statusElement;
    }

    public static List<WebElement> dqPage_columnNames_WebElementList()
    {
        return driver.findElements(By.xpath("//tbody//tr//td[2]"));
    }

    public static List<WebElement> dqPage_dataTypeForColumns_WebElementList()
    {
        return driver.findElements(By.xpath("//tbody//tr//td[3]"));
    }

    public static WebElement dqPage_RecurrenceClockIcon_WebElement(String extractionName)
    {
        return driver.findElement(By.xpath("//span[text()='"+extractionName+"']//parent::a//following-sibling::span[contains(@class,'zs-icon-clock-pending')]"));
    }

    public static WebElement dqPage_DuplicateDataQueryErrorMessage_WebElement(String extractionName)
    {
        return driver.findElement(By.xpath("//span[text()='Redshift Extraction with name "+extractionName+" already exists!']"));
    }

    public static WebElement dqPage_ColumnNameAndDataType_WebElement(String columnName, String columnDataType)
    {
        return driver.findElement(By.xpath("//td[normalize-space(text())='"+columnName+"']//following-sibling::td[normalize-space(text())='"+columnDataType+"']"));
    }

    public static WebElement dqPage_ColumnFilterByIndex_DropDown(int index){
        return driver.findElement(By.xpath("(//label[normalize-space(text())='Column']/following-sibling::p[@is='zs-multiselect']//div[1])["+index+"]"));
    }

    public static WebElement dqPage_ColumnFilterByIndex_TextBox(int index){
        return driver.findElement(By.xpath("(//label[normalize-space(text())='Column']/following-sibling::p[@is='zs-multiselect']//input)["+index+"]"));
    }

    public static WebElement dqPage_ConditionFilterByIndex_DropDown(int index){
        return driver.findElement(By.xpath("(//label[normalize-space(text())='Condition']/following-sibling::p[@is='zs-multiselect']//div[1])["+index+"]"));
    }
    public static WebElement dqPage_ConditionFilterByIndex_TextBox(int index){
        return driver.findElement(By.xpath("(//label[normalize-space(text())='Condition']/following-sibling::p[@is='zs-multiselect']//input)["+index+"]"));
    }

    public static WebElement dqPage_FilterValueByIndex_TextBox(int index){
        return driver.findElement(By.xpath("(//input[@placeholder='Value'])["+index+"]"));
    }

    public static WebElement dataQueryPage_editIconByDataQueryName(String dataQueryName)
    {
        WebElement editIconForIngestion = driver.findElement(By.xpath("//span[text()='"+dataQueryName+"']//ancestor::div[@role='row']//div//a[contains(@class,'zs-icon-edit')]"));

        return editIconForIngestion;
    }

    public static List<WebElement> dataQueryPage_deleteFilterCondition_Button()
    {
        return driver.findElements(By.xpath("//app-extraction-filter-row//a[contains(@class,'zs-icon-delete')]"));

    }

    public static WebElement dataQueryPage_SelectColumnName_CheckBox(String columnName)
    {
        return driver.findElement(By.xpath("//td[normalize-space(text())='"+columnName+"']//preceding-sibling::td//input"));

    }

    public static By dqPage_DataQueryName_Locator(String dataQueryName)
    {

        return By.xpath(" //a[@class='name-color']//span[text()='"+dataQueryName+"']");
    }

    public static WebElement dataQueryPage_ErrorMessage_Text(String labelName)
    {
        return driver.findElement(By.xpath("//label[normalize-space(text())='"+labelName+"']//parent::div//following-sibling::p[text()='Value required.']"));
    }

    public static WebElement dataQueryPage_ColumnName_WebElement(String columnName)
    {
        return driver.findElement(By.xpath("//div[@role='presentation']//span[@ref='eText' and text()='"+columnName+"']"));
    }

    public static WebElement dataQueryPage_RecurrenceStopped_WebElement(String dataQueryName)
    {
        return driver.findElement(By.xpath("//span[text()='Recurrence of "+dataQueryName+" stopped.']"));
    }

    public static WebElement dataQueryPage_RecurrenceStarted_WebElement(String dataQueryName)
    {
        return driver.findElement(By.xpath("//span[text()='Recurrence of "+dataQueryName+" started.']"));
    }

    public static WebElement dataQueryPage_SourceTableMappingSearchResults_WebElement(String columnName)
    {
        return driver.findElement(By.xpath("//a//span//mark[text()='"+columnName+"']"));
    }

    public static List<WebElement> dataQueryPage_RowsOnLandingPage_WebElement(int rowId)
    {
        return driver.findElements(By.xpath("(//div[@role='row' and @row-id='"+rowId+"']//div//a[contains(@class,'name-color')])[last()]"));
    }

    public static List<WebElement> dataQueryPage_RowsOnPastRunsTab_WebElement(int rowId)
    {
        return driver.findElements(By.xpath("(//div[@role='row' and @row-id='"+rowId+"']//div//app-job-status)[last()]"));
    }

    public static WebElement dataQueryPage_DataQueryName_WebElement(String dataQueryName)
    {
        return driver.findElement(By.xpath("//a//span[text()='"+dataQueryName+"']"));
    }
}
