package com.RDM.Merger.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import com.utility.base.TestBase;
import com.utility.base.WrapperFunctionUtilities;
import com.utility.logger.Log;

import com.utility.base.TestBase;

import java.util.List;

public class IngestionPage extends TestBase{


	public IngestionPage() {

		PageFactory.initElements(driver, this);

	}
	public static WebElement ingestionTab;
	public static WebElement adhocRunButton;
	public static WebElement errorMessageElement;
	public static WebElement sourceFormatTxtbox;
	public static WebElement sourceColumnName;
	public static WebElement incrementalJoinKey;
	public static WebElement columnTypeDropdown;
	public static WebElement columnTypeOption;
	public static WebElement disabledSourceFormatTxtbox;
	public static WebElement enabledSourceFormatTxtbox;


	@FindBy(xpath="//span[contains(.,'Create New Data Source')]")
	public static WebElement new_Ingestion;

	@FindBy(xpath="//input[contains(@placeholder,'Ingestion Name')]")
	public static WebElement ingestion_name;

	@FindBy(xpath="//input[contains(@placeholder,'Table Name')]")
	public static WebElement table_name;

	@FindBy(xpath="//div[@class='grid-item-dropdwon']//select[1]//following::div[contains(text(),'Load Existing')]")
	public static WebElement load_existing;

	@FindBy(xpath ="//app-multi-select-dropdown[contains(@class,'adaptor-select')]//p//input")
	public static WebElement selectExistingAdaptorInput;

	@FindBy(xpath="//input[@type='file']")
	public static WebElement local_upload;

	@FindBy(xpath="//label[contains(text(),' Format ')]//following::div[contains(text(),'Select File Format')]")
	public static WebElement format_dropdown;

	@FindBy(xpath="//div[contains(text(),'Select Load Type')]")
	public static WebElement file_load_type_dropdown;

	@FindBy(xpath="//input[@placeholder='Sheet Name']")
	public static WebElement sheet_name;

	@FindBy(xpath="//span[contains(.,'Skip Header')]")
	public static WebElement skip_header_checkbox;

	@FindBy(xpath="//div[@class='skip-header-padding-top']//following::input[@autocomplete='off']")
	public static WebElement skip_header_text;

	@FindBy(xpath = "//button[contains(.,'Next')]")
	public static WebElement next_click;

	@FindBy(xpath = "//label[contains(text(),'Database')]//following::div[contains(text(),'Select')][1]")
	public static WebElement database_select;

	@FindBy(xpath = "//button[contains(.,'Finish')]")
	public static WebElement finish_button;

	@FindBy(xpath = "//label[normalize-space(text())='File Type']//parent::div//following-sibling::p//div[1]")
	public static WebElement file_type_dropdown;

	@FindBy(xpath = "//input[@placeholder='Delimiter']")
	public static WebElement delimiter;

	@FindBy(xpath = "//label[contains(text(),' Cluster Type ')]//following::div[contains(text(),'Select')][1]")
	public static WebElement cluster_type_dropdwn;

	@FindBy(xpath = "//label[contains(text(),'Schema ')]//following::div[contains(text(),'Select')][1]")
	public static WebElement redshift_schema_select;

	@FindBy(xpath = "//label[contains(text(),' Adaptor ')]//following::p[@is=\"zs-multiselect\"][1]//div[1]")
	public static WebElement redshift_warehouse_select;

	@FindBy(xpath = "//span[contains(.,'Success:')]")
	public static WebElement ingestionStatusValidation;
	
	@FindBy(xpath = "//h2[text()='Data Center']")
	public static WebElement dataCenterHeader;

	@FindBy(xpath ="//app-multi-select-dropdown[contains(@class,'adaptor-select')]//div/p/div[text()='Select']")
	public static WebElement selectAdaptor;

	@FindBy(xpath="//input[@placeholder='Source File Location']")
	public static WebElement sourceFileLocation;
	
	@FindBy(xpath="//input[@placeholder='Sheet Name']")
	public static WebElement sheetNameExcel;

	@FindBy(xpath = "//p[@empty-text='Select Table']")
	public static WebElement selectTableClick;

	@FindBy(xpath = "//p[@empty-text='Select Table']//input[@type='text']")
	public static WebElement tableDropDownInput;

	@FindBy(xpath = "(//div[@class='idata']//span)[1]")
	public static WebElement runiDataCheckbox;

	@FindBy(xpath = "//button[contains(@class,'zs-button zs-button-solid zs-border-rounded-0')]/span")
	public static WebElement runButtoniData;

	@FindBy(xpath = "//div[text()='Select Domain']")
	public static WebElement idataDomainClick;

	@FindBy(xpath = "//p[@title='Select Domain']/div[@class='zs-search-dropdown zs-menu zs-action-field zs-action-field-solid']/nav/a")
	public static WebElement idataDomainDropDownValues;

	@FindBy(xpath = "//a[text()=' Data Center ']")
	public static WebElement dataCenterLink;
	
	@FindBy(xpath = "//div[contains(@class,'toasters')]//span[normalize-space(text())='Please upload file with size upto 100MB']")
	public static WebElement uploadFileSizeError;
	
	@FindBy(xpath = "(//button[text()='Cancel'])[last()]")
	public static WebElement cancelButton;
	
	@FindBy(xpath = "(//tr[1]//td[4]//select)[1]")
	public static WebElement firstColumnTypeDropdown;

	@FindBy(xpath = "//label[normalize-space(text())='Table Name']//following-sibling::p//p[text()='Value contains illegal characters']")
	public static WebElement invalidTableNameError;

	@FindBy(xpath = "//app-schedular-details//div[@disabled='true']")
	public static WebElement scheduleRecurringIngestionDisabled;

	@FindBy(xpath = "//span[text()='Schedule Recurring Ingestion (optional)']//preceding-sibling::label//input")
	public static WebElement scheduleRecurringIngestionCheckbox;

	@FindBy(xpath ="//span[text()='Start date']//following-sibling::app-zs-date-picker//p[1]")
	public static WebElement startDatePicker;

	@FindBy(xpath ="//label[normalize-space(text())='Recurrence Pattern']//following-sibling::p[1]//div[2]")
	public static WebElement recurrencePatternDropdown;

	@FindBy(xpath = "//div[@class='ag-body-horizontal-scroll-viewport']")
	public static WebElement horizontalScrollBarPreviewTable;

	@FindBy(xpath = "//div[contains(@class,'toasters')]//span[text()='File size must be lesser than 15MB for preview and schema detection']")
	public static WebElement previewErrorMessage;

	public static By fileUploadLoadingSymbol = By.xpath("//zs-loader[@class='zs-loader zs-inverse']");
	public static By delimiterTextBoxXpath =  By.xpath("//input[@placeholder='Delimiter']");
	public static By scheduleRecurringIngestionDisabledXpath = By.xpath("//app-schedular-details//div[@disabled='true']");
	public static By xpathFirstSuccessfulIngestionRunButton = By.xpath("(//span[normalize-space(text())='Complete']//ancestor::div[@role='row']//div//a[contains(@class,'zs-icon-play-circle') and not(@disabled)])[1]");
	public static By ingestionPage_LoadingSymbolPreviewTable_Text = By.xpath("//p[contains(text(),'Your results are loading')]");

	@FindBy(xpath = "//button[@next and @class='zs-button zs-fab']")
	public static WebElement nextButtonSchemaTable;
	
	@FindBy(xpath = "(//span[normalize-space(text())='Complete']//ancestor::div[@role='row']//div//a[contains(@class,'zs-icon-play-circle') and not(@disabled)])[1]")
	public static WebElement runFirstSuccessfulIngestionRunButton;

	@FindBy(xpath = "(//div[@role='row']//div//a[contains(@class,'name-color')])[last()]")
	public static WebElement lastVisibleIngestionName;

	@FindBy(xpath = "(//span[normalize-space(text())='Complete']//ancestor::div[@role='row']//div//a[contains(@class,'zs-icon-play-circle') and not(@disabled)])[1]//ancestor::div[@role='row']//a[@class='name-color']//span")
	public static WebElement runFirstSuccessfulIngestionName;

	@FindBy(xpath = "//input[@placeholder='Quote Character']")
	public static  WebElement quoteCharacterInput;

	@FindBy(xpath = "//input[@placeholder='Escape Character']")
	public static  WebElement escapeCharacterInput;

	@FindBy(xpath = "//label[contains(text(),'Quote Character')]//following-sibling::p//input")
	public static WebElement quoteCharacterTextbox;

	@FindBy(xpath = "//label[contains(text(),'Escape Character')]//following-sibling::p//input")
	public static WebElement escapeCharacterTextbox;

	@FindBy(xpath = "//button[text()='Save as draft']")
	public static WebElement saveAsDraft;

	@FindBy(xpath = "//label[text()='Add Check']")
	public static WebElement ingestionPage_addCheckDQMPopup_WebElement;

	@FindBy(xpath = "(//zs-dialog//app-zs-select)[2]//p")
	public static WebElement dqmDropdownInDialogBox;

	@FindBy(xpath = "(//div[contains(@class,'zs-search-dropdown')]//span[text()='Continue ingestion with errorneous data']//parent::a)[last()]")
	public static WebElement continueIngestionWithErrorneousData;

	@FindBy(xpath = "(//div[contains(@class,'zs-search-dropdown')]//span[text()='Remove errorneous data and continue ingestion']//parent::a)[last()]")
	public static WebElement removeErrorneousData;

	@FindBy(xpath = "//app-modaldialog//button[text()='Cancel']")
	public static WebElement cancelButtonAddDQMDialogBox;

	@FindBy(xpath = "//span[text()='Recurrence (Optional)']//preceding-sibling::input")
	public static WebElement recurrenceOptionalCheckBox;

	@FindBy(xpath = "//span[text()='End after']//preceding-sibling::input")
	public static WebElement endAfterRadioButton;

	@FindBy(xpath = "//span[text()='End after']//parent::label/following-sibling::span//input")
	public static WebElement endAfterTextBox;

	@FindBy(xpath = "//button[text()='Previous']")
	public static WebElement ingestionPage_Previous_Button;

	@FindBy(xpath = "//label[normalize-space(text())='Primary Key']//ancestor::app-multi-select-dropdown//p//div[1]")
	public static WebElement ingestionPage_PrimaryKey_DropDown;

	@FindBy(xpath = "//label[normalize-space(text())='If data does not pass check']//ancestor::app-multi-select-dropdown//p//div[1]")
	public static WebElement ingestionPage_IfDataDoesNotPassCheck_DropDown;

	@FindBy(xpath = "(//td[@class='select-class']/span//div)[2]")
	public static WebElement ingestionDefineSchemaPage_dqmSelection_Dropdown;

	@FindBy(xpath = "//div[@class='dqm-table']//tbody/tr/td[2]/div//p/label/input")
	public static WebElement ingestionDefineSchemaPage_dqmInclusion_TextBox;

	@FindBy(xpath = "//button[text()='Save']")
	public static WebElement ingestionDefineSchemaPageDQMPOPUP_Save_Button;

	@FindBy(xpath = "//input[@placeholder='Search']")
	public static WebElement ingestionPage_SearchIngestion_Textbox;

	@FindBy(xpath = "//button[text()='Apply']")
	public static WebElement ingestionPage_ApplyFilter_Button;

	@FindBy(xpath = "//label[normalize-space(text())='Table']/following-sibling::p[@is='zs-multiselect']//div[1]")
	public static WebElement ingestionPage_SelectTable_Dropdown;

	@FindBy(xpath = "//label[normalize-space(text())='Cluster Type']//following-sibling::p//select[2]")
	public static WebElement ingestionPage_ClusterType_Dropdown;

	@FindBy(xpath = "//button[@next]")
	public static WebElement ingestionPage_RightArrow_WebButton;

	@FindBy(xpath = "//label[normalize-space(text())='Add Column']")
	public static WebElement dataCenterPage_AddColumn_Link;

	@FindBy(xpath = "//div[@class='normal-ingestion-table']//tbody/tr")
	public static WebElement dataCenterPage_NumberOfSourceColumns_WebElement;

	public static WebElement ingestionTabName(String tabName)
	{
		ingestionTab = driver.findElement(By.xpath("//span[contains(.,'"+tabName+"')]"));
		WrapperFunctionUtilities.isElementPresent(ingestionTab, tabName);
		return ingestionTab;

	}

	public static WebElement adhocIngestionRunButton(String ingestionName)
	{
		adhocRunButton = driver.findElement(By.xpath("(//span[contains(.,'"+ingestionName+"')])[3]//following::a[@class='zs-icon zs-icon-large zs-icon-play-circle'][1]"));
		WrapperFunctionUtilities.isElementPresent(adhocRunButton, ingestionName);
		return adhocRunButton;

	}
	public static WebElement getErrorMessage(String message){
		errorMessageElement =driver.findElement(By.xpath("//p[contains(@class,'zs-message zs-error') and normalize-space(text())='"+message+"']"));
		return  errorMessageElement;
	}

	public static List getidatadomains(){
	List <WebElement> actual_domain_values = driver.findElements(By.xpath("//p[@title='Select Domain']/div[@class='zs-search-dropdown zs-menu zs-action-field zs-action-field-solid']/nav/a"));
	return  actual_domain_values;
	}

	public static List getNumberOfColumnsDefineSchemaPage(){
		List<WebElement> numberOfColumns = driver.findElements(By.xpath("//div[@class='normal-ingestion-table']//tbody/tr"));
		return numberOfColumns;
	}
	public static WebElement sourceFormatTextbox(String sourceName)
	{
		sourceFormatTxtbox = driver.findElement(By.xpath("//div[contains(text(),'"+sourceName+"')]//parent::td//following-sibling::td[3]//input"));
		WrapperFunctionUtilities.isElementPresent(sourceFormatTxtbox, sourceName+" Textbox");
		return sourceFormatTxtbox;
	}
	
	public static WebElement sourceColumnNames(String sourceColumn)
	{
		sourceColumnName = driver.findElement(By.xpath("//div[normalize-space(text())='"+sourceColumn+"']//parent::td"));
		return sourceColumnName;
	}
	
	public static WebElement incrementalJoinKeyColumn(String sourceColumn)
	{
		incrementalJoinKey = driver.findElement(By.xpath("//div[normalize-space(text())='"+sourceColumn+"']//parent::td/following-sibling::td[4]//input"));
		return incrementalJoinKey;
	}
	
	public static WebElement sourceColumnTypeDropdown(String sourceColumn)
	{
		columnTypeDropdown = driver.findElement(By.xpath("//div[normalize-space(text())='"+sourceColumn+"']//parent::td/following-sibling::td[2]//p/div"));
		return columnTypeDropdown;
	}
	
	public static WebElement sourceColumnTypeDropdownOption(String type)
	{
		columnTypeOption = driver.findElement(By.xpath("(//a[@class='zs-link-action']//span[text()='"+type+"'])[last()]"));
		return columnTypeOption;
	}
	
	public static WebElement selectSourceColumnCheckbox(String sourceColumn)
	{
		WebElement selectSourceColumnCheckboxs = driver.findElement(By.xpath("//div[normalize-space(text())='"+sourceColumn+"']//parent::td/preceding-sibling::td[1]//input"));
		return selectSourceColumnCheckboxs;
	}
	
	public static WebElement sourceFormatTextboxDisabled(String sourceName)
	{
		disabledSourceFormatTxtbox = driver.findElement(By.xpath("//div[contains(text(),'"+sourceName+"')]//parent::td//following-sibling::td[3][@disabled]"));
		
		return disabledSourceFormatTxtbox;
	}
	
	public static WebElement sourceFormatTextboxEnabled(String sourceName)
	{
		enabledSourceFormatTxtbox = driver.findElement(By.xpath("//div[contains(text(),'"+sourceName+"')]//parent::td//following-sibling::td[3][not(@disabled)]"));
		
		return enabledSourceFormatTxtbox;
	}
	
	public static List<WebElement> getsourceColumnTypeDropdownOptions(WebElement dropdown)
	{
		Select select = new Select(dropdown);
		List<WebElement> options =  select.getOptions();
		
		return options;
	}

	/*public static By delimiterTextBoxXpath()
	{
		return By.xpath("//input[@placeholder='Delimiter']");
	}

	public static By scheduleRecurringIngestionDisabledXpath()
	{
		return By.xpath("//app-schedular-details//div[@disabled='true']");
	}*/


	public static WebElement dataPreviewColumnHeader(String columnHeader,int columnIndex)
	{
		WebElement dataPreviewColumnHeader = driver.findElement(By.xpath("//div[@aria-colindex='"+columnIndex+"']//div[@role='presentation']//span[text()='"+columnHeader+"']"));

		return dataPreviewColumnHeader;
	}

	/*public static WebElement sourceFormatTextbox(String sourceName)
	{
		sourceFormatTxtbox = driver.findElement(By.xpath("//div[contains(text(),'"+sourceName+"')]//parent::td//following-sibling::td[3]//input"));
		WrapperFunctionUtilities.isElementPresent(sourceFormatTxtbox, sourceName+" Textbox");
		return sourceFormatTxtbox;
	}*/
	
	public static WebElement statusOfIngestion(String ingestionName,String status)
	{
		WebElement statusElement = driver.findElement(By.xpath("//span[text()='"+ingestionName+"']//ancestor::div[@role='gridcell']//following-sibling::div//app-idata-job-status//span[contains(text(),'"+status+"')]"));
		return statusElement;
	}

	public static WebElement editIconByIngestionName(String ingestionName)
	{
		WebElement editIconForIngestion = driver.findElement(By.xpath("//span[text()='"+ingestionName+"']//ancestor::div[@role='row']//div//a[contains(@class,'zs-icon-edit')]"));

		return editIconForIngestion;
	}

	public static WebElement dataPreviewCellValue(String cellValue,int columnIndex)
	{
		WebElement dataPreviewColumnHeader = driver.findElement(By.xpath("//div[@role='row']//div[@role='gridcell' and text()='"+cellValue+"' and @aria-colindex='"+columnIndex+"']"));

		return dataPreviewColumnHeader;
	}

	public static List<WebElement> selectColumnCheckboxes()
	{
		return driver.findElements(By.xpath("//tbody//tr//label[@class='zs-checkbox']"));
	}

	public static List<WebElement> ingestionPage_dqmAddIcon_WebList()
	{
		return driver.findElements(By.xpath("//td[contains(@class,'quality-checks-value')]//a"));
	}

	public static WebElement recurrenceButtonForIngestion(String ingestioName)
	{
		return driver.findElement(By.xpath("(//span[contains(.,'"+ingestioName+"')])[3]//ancestor::div[@role='row']//app-zs-toggle//p"));
	}

	public static WebElement ingestionPage_NullCharacterInPreviewTable_WebElement(String columnIndex)
	{
		return driver.findElement(By.xpath("//div[@role='row']//div[@role='gridcell' and @aria-colindex='"+columnIndex+"' and not(text())]"));
	}

	public static WebElement ingestionPage_PrimaryKeyDropDownOptions_WebElement(String columnName)
	{
		return driver.findElement(By.xpath("//label[normalize-space(text())='Primary Key']//ancestor::app-multi-select-dropdown//p//span[text()='"+columnName+"']//preceding-sibling::input"));
	}

	public static WebElement clickAddDQMIcon(String columnName){
		return  driver.findElement(By.xpath("//div[text()= ' " + columnName + " ']/following::td[4]/a"));
	}

	public static WebElement verifyDQMAdded(String columnName, String dqm) {
		return driver.findElement(By.xpath("//div[text()= ' " + columnName + " ']/following::td[4]/a[text()='" + dqm + "']"));
	}
	public static List<WebElement> ingestionPage_SourceColumnName_RadioButton()
	{
		return driver.findElements(By.xpath("//td[2]//input[@type='radio']"));
	}

	public static List<WebElement> ingestionPage_PredictedColumnName_RadioButton()
	{
		return driver.findElements(By.xpath("//td[3]//input[@type='radio']"));
	}

	public static WebElement ingestionPage_SourceColumnByRowIndex_RadioButton(int rowNum)
	{
		return driver.findElement(By.xpath("//tbody//tr["+rowNum+"]//td[2]//input"));
	}

	public static WebElement ingestionPage_PredictedColumnByRowIndex_RadioButton(int rowNum)
	{
		return driver.findElement(By.xpath("//tbody//tr["+rowNum+"]//td[3]//input"));
	}

	public static WebElement DataCenterPage_DeleteDataCenter_Icon(String dataCenterName) {
		return driver.findElement(By.xpath("//span[normalize-space(text())='" + dataCenterName + "']/following::div/span//a[@disabled='true'][2]"));
	}

	public static WebElement DataCenterPage_RunningStatus_WebElement(String dataCenterName) {
		return driver.findElement(By.xpath("//span[normalize-space(text())='"+dataCenterName+"']/following::div/span//span/span[normalize-space(text())='Running']"));
	}

	public static WebElement DataCenterPage_AliasName_input(int rowNumber){
		return driver.findElement(By.xpath("//div[@class='normal-ingestion-table']//tbody/tr["+rowNumber+"]/td[3]//label/input"));
	}

	public static WebElement DataCenterPage_UserAddedSourceColumn_DatatypeDropDown(int rowNumber){
		return driver.findElement(By.xpath("//div[@class='normal-ingestion-table']//tbody/tr["+rowNumber+"]/td[4]//p//div[1]"));
	}

	public static WebElement DataCenterPage_SelectDatatypeCustomCol_link(String dataType, int rowNumber){
		return  driver.findElement(By.xpath("//div[@class='normal-ingestion-table']//tbody/tr["+rowNumber+"]/td[4]//following::div[@class='zs-search-dropdown zs-menu zs-action-field zs-action-field-solid'][4]//a[@title='"+dataType+"']"));
	}
}

