package com.RDM.Merger.locators;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utility.base.TestBase;

public class DriverDataPage extends TestBase {

	private static WebElement driverDataobject;

	@FindBy(xpath ="(//button[@class='zs-button zs-border-rounded-0'])[2]")
	protected static WebElement tableView;


	@FindBy(xpath ="//input[@placeholder='Search Driver Data']")
	public static WebElement searchDriverData;


	@FindBy(xpath ="//a[contains(.,'ZIP')]")
	protected static WebElement driverDataSelect;


	@FindBy(xpath ="//div[@class='add-rows-btn']/button[@class='zs-button zs-border-rounded-0']")
	protected static WebElement addRowsClick;


	@FindBy(xpath ="//button[contains(.,'Save new rows')]")
	protected static WebElement saveNewRow;


	@FindBy(xpath ="//span[contains(.,'Record added successfully')]")
	protected static WebElement msgNewRow;


	@FindBy(xpath ="//main[@class='dd-detail zs-master-style']")
	protected static WebElement ddDetailPage;


	@FindBy(xpath ="//zs-tab[contains(.,'User Permissioning')]")
	protected static WebElement userPermissioningTab;


	@FindBy(xpath ="//span[text()='Delete Selected Row(1)']")
	protected static WebElement deleteRowClick;

	@FindBy(xpath ="//span[@class='object-name']/h4")
	protected static WebElement ddTitle;


	@FindBy(xpath ="//table[@class='zs-data-table home-process-table']/tbody[1]/tr[1]/td[5]")
	protected static WebElement auditLogValue;

	@FindBy(xpath="(//button[@class='zs-button zs-border-rounded-0'])[2]")
	public  static WebElement driverDataDownload;
	
	@FindBy(xpath="//h2[text()='Driver Data']")
	public static WebElement driverDataHeader;
	
	@FindBy(xpath="//span[text()='Upload Data to File']")
	public static WebElement driverDataUpload;
	
	@FindBy(xpath="//input[@type='file']")
	public static WebElement uploadLink;
	
	@FindBy(xpath="//button[@class='zs-button zs-button-action zs-border-rounded-0']")
	public static WebElement uploadButton;
	
	@FindBy(xpath="//span[text()='Success:']")
	public static WebElement uploadSuccessToaster;

	@FindBy(xpath="//span[@class='zs-radio-group']/input[2]")
	public static WebElement uploadDeleteInsert;

	@FindBy(xpath = "(//span[@class='ag-icon ag-icon-menu'])[1]")
	public static WebElement ddColumnFilter;

	@FindBy(xpath = "//app-editable-table//input[@type='text']")
	public static WebElement ddColumnFilterSearchBox;

	@FindBy(xpath = "//span[@class='zs-checkbox']/span/mark")
	public static WebElement ddColumnFilterSearchClick;

	@FindBy(xpath = "//div[@class='ag-selection-checkbox']//input[@type='checkbox']")
	public static WebElement ddFirstRowCheckBox;

	@FindBy(xpath = "//div[text()='Active']")
	public static WebElement versionDropDown;

	@FindBy(xpath = "//a[@title='V8']")
	public static WebElement versionSelect;

	@FindBy(xpath = "//span[text()='Upload Data to File']/ancestor::button")
	public static WebElement disabledUploadDataToFileButton;

	@FindBy(xpath ="//table[@class='zs-data-table home-process-table']/tbody[1]/tr[2]/td[5]")
	protected static WebElement auditLogValueRow2;

	@FindBy(xpath = "//span[text()='Driver data uploaded successfully.']")
	protected static WebElement getUploadSuccessToasterMessage;

	@FindBy(xpath = "//div[@class='ag-center-cols-container']/div[@role='row']")
	protected static WebElement dataRowInDriverDataTable;

	public static int getRowCount()
	{

		List rows = driver.findElements(By.xpath("//div[@class='ag-center-cols-container']"));
		return rows.size();

	}

	public static WebElement selectDriverDataObject(String ddObject) {
		driverDataobject = driver.findElement(By.xpath("//span[@data-title='"+ddObject+"']"));
		return driverDataobject;
	}

	public DriverDataPage() {

		PageFactory.initElements(driver, this);
		System.out.println("in Driver Data locator class");
	}
	
	public static int getDDRowCount() {
		List row_count = driver.findElements(By.xpath("//div[@class='ag-center-cols-container']/div[@role='row']"));
		return row_count.size();
	}



}
