package com.RDM.Merger.pagefunctions;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.RDM.Merger.locators.DataQueryPage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;

import com.RDM.Merger.locators.DriverDataPage;
import com.RDM.Merger.testcases.Aa_Login.ExcelReader;
import com.utility.base.WrapperFunctionUtilities;
import com.utility.logger.Log;

public class DriverDataPageFunctions extends DriverDataPage {

	public static String username = System.getProperty("user.name");
	DriverDataPage ddPage;

	public DriverDataPageFunctions()
	{
		super();
		//ddPage=new DriverDataPage();
		

	}

	public static void tableView(String ddObject) throws Exception{
		Log.info("clicking Driver Data Table View");
		WrapperFunctionUtilities.waitForTime(2000);
		//WrapperFunctionUtilities.waitForElementVisibility(tableView, 2000, driver);
		/*try {
			tableView.click();
		  } catch (Exception e) {
		     JavascriptExecutor executor = (JavascriptExecutor) driver;
		     executor.executeScript("arguments[0].click();", tableView);
		  }*/
		WrapperFunctionUtilities.waitForElementVisibility(searchDriverData, 1000, driver);
		//getWebElement("//input[@placeholder='Search Driver Data']").sendKeys(ExcelReader.getValue("DriverData", ddObject, "TestData"));
		searchDriverData.sendKeys(ExcelReader.getValue("DriverData", ddObject, "TestData"));
		System.out.println("Enter Data to Search  "+ ExcelReader.getValue("DriverData", ddObject, "TestData"));
		WrapperFunctionUtilities.waitForTime(1000);
		DriverDataPage.selectDriverDataObject(ExcelReader.getValue("DriverData", ddObject, "TestData")).click();
		WrapperFunctionUtilities.waitForTime(1000);
	}

	public static WebElement getWebElement(String locator){
	return	driver.findElement(By.xpath(locator));
	
	}
	public static void driverDataSelect(){
		driverDataSelect.click();
	}

	public static void clickAddRow()
	{
		addRowsClick.click();
	}



	public static HashMap  getTestDataFromExcel(String sheetName, String filename) throws Exception {
		FileInputStream file = null;
		Workbook book= null;
		Sheet sheet;
		int test =0;
		ArrayList a1 = new ArrayList();
		ArrayList a2 = new ArrayList();
		HashMap hmap =new HashMap();
		try {
			file = new FileInputStream(PROJECT_PATH + "\\src\\test\\java\\com\\RDM\\Merger\\testdata\\"+filename+".xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		Log.info("Row Count "+rowCount);
		//Create a loop to print cell values in a row
		while(test<=rowCount)
		{	
			Row row = sheet.getRow(test);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				//Print Excel data in console
				//addition of Keys
				if(test==0)
				{
					Log.info("test value "+test+" cell value "+row.getCell(j).getStringCellValue());
					a1.add(row.getCell(j).getStringCellValue());
				}
				else
				{
					//Add Values
					Log.info("test value "+test+" cell value "+row.getCell(j).getStringCellValue());
					a2.add(row.getCell(j).getStringCellValue());
				}
			}
			test++;
			System.out.println(test);
			//	return data;
		}
		for(int l=0;l<a1.size();l++)
		{
			hmap.put(a1.get(l).toString().trim(), a2.get(l).toString().trim());
		}
		return hmap;
	}




	public static void addNewRow(HashMap hmap,String topFilter1,String topFilter2) throws Exception
	{
		List count = driver.findElements(By.xpath("(//span[@role='columnheader'])"));
		WrapperFunctionUtilities.scrollByVisibleElement(driver.findElement(By.xpath("(//span[@role='columnheader'])")), driver);
		System.out.println("List count is "+count.size());
		String tlf1=ExcelReader.getValue("DriverData", topFilter1, "TestData");
		String tlf2=ExcelReader.getValue("DriverData", topFilter2, "TestData");
		String zipID=ExcelReader.getValue("DriverData", "Search Column Name", "TestData");

		for(int i =1;i<count.size()+1;i++)
		{
			String colName = (driver.findElement(By.xpath("(//span[@role='columnheader'])["+i+"]")).getText()).trim();
			Log.info("Column Name "+colName);
			String value = (String) hmap.get(colName);
			if(colName.equals(zipID))
			{
				Random random = new Random();
				int tempValue = random.nextInt(9999);
				value=value+tempValue;
			}
			int j = i+1;
			Log.info("Value is  "+value);
			if((colName.equals(tlf1))||(colName.equals(tlf2)))
			{
				Log.info("In TLF Col");

				/*JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@role='row']/div[@role='gridcell']["+j+"]")));
				js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@role='row']/div[@role='gridcell']["+j+"]//div[@ref='eIcon']")));*/
				WrapperFunctionUtilities.jsClick(driver.findElement(By.xpath("//div[@role='row']/div[@role='gridcell']["+j+"]")),driver);
				WrapperFunctionUtilities.jsClick(driver.findElement(By.xpath("//div[@role='row']/div[@role='gridcell']["+j+"]//div[@ref='eIcon']")),driver);
				//js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@role='row']/div[@role='gridcell']["+j+"]")));
				//driver.findElement(By.xpath("//div[@role='row']/div[@role='gridcell']["+j+"]")).click();
			//	js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@role='row']/div[@role='gridcell']["+j+"]")));
				WrapperFunctionUtilities.waitForTime(1000);
				WrapperFunctionUtilities.isElementPresent(driver.findElement(By.xpath("//div[contains(@class,'ag-select')]//following::div//span[text()='"+value+"']")), "TLF");
				//driver.findElement(By.xpath("//div[@class='ag-virtual-list-container ag-rich-select-virtual-list-container']/div/div[text()='"+value+"']")).click();
				//driver.findElement(By.xpath("//div[//div[contains(@class='ag-select')]//following::div//span[text()='"+value+"']")).click();
				WrapperFunctionUtilities.jsClick(driver.findElement(By.xpath("//div[contains(@class,'ag-select')]//following::div//span[text()='"+value+"']")), driver);
			}
			else
			{
				WrapperFunctionUtilities.waitForTime(2000);
				driver.findElement(By.xpath("//div[@role='row']/div[@role='gridcell']["+j+"]")).click();
				driver.findElement(By.xpath("//div[@role='row']/div[@role='gridcell']["+j+"]")).sendKeys(value);
			}
		}
	}

	public static void addMappedNewRow(HashMap hmap,String topFilter1,String topFilter2,String foreignKeyColumnName, String	referredColumnName, String	foreignKeyColumnValue, String referredColumnValue) throws Exception {
		List count = driver.findElements(By.xpath("(//span[@role='columnheader'])"));
		System.out.println("List count is "+count.size());
		String tlf1=ExcelReader.getValue("DriverData", topFilter1, "TestData");
		String tlf2=ExcelReader.getValue("DriverData", topFilter2, "TestData");
		for(int i =1;i<count.size()+1;i++)
		{
			String colName = (driver.findElement(By.xpath("(//span[@role='columnheader'])["+i+"]")).getText()).trim();
			Log.info("Column Name "+colName);
			String value = (String) hmap.get(colName);
			int j = i+1;
			Log.info("Value is  "+value);
			if((colName.equals(tlf1))||(colName.equals(tlf2)))
			{
				Log.info("In TLF Col");
				//JavascriptExecutor js = (JavascriptExecutor) driver;
				//js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@role='row']/div[@role='gridcell']["+j+"]")));
				WrapperFunctionUtilities.jsClick(driver.findElement(By.xpath("//div[@role='row']/div[@role='gridcell']["+j+"]")),driver);
				//driver.findElement(By.xpath("//div[@role='row']/div[@role='gridcell']["+j+"]")).click();
				//js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@role='row']/div[@role='gridcell']["+j+"]//div[@ref='eIcon']")));
				WrapperFunctionUtilities.jsClick(driver.findElement(By.xpath("//div[@role='row']/div[@role='gridcell']["+j+"]//div[@ref='eIcon']")),driver);
				WrapperFunctionUtilities.waitForTime(1000);
				WrapperFunctionUtilities.isElementPresent(driver.findElement(By.xpath("//div[contains(@class,'ag-select')]//following::div//span[text()='"+value+"']")), "TLF");
				//driver.findElement(By.xpath("//div[@class='ag-virtual-list-container ag-rich-select-virtual-list-container']/div/div[text()='"+value+"']")).click();
				WrapperFunctionUtilities.jsClick(driver.findElement(By.xpath("//div[contains(@class,'ag-select')]//following::div//span[text()='"+value+"']")), driver);
			}
			else if((colName.contains(ExcelReader.getValue("MappingObject", foreignKeyColumnName, "DriverData"))))
			{
				Log.info("Selecting Foreign Key");
				WrapperFunctionUtilities.waitForTime(1000);
				WebElement foreignKey = driver.findElement(By.xpath("//div[@role='row']/div[@role='gridcell']["+j+"]"));
								foreignKey.sendKeys(value);
				//driver.findElement(By.xpath("(//div[@class='autocomplete ag-cell-editor-autocomplete']/div/span/strong[text()='"+value+"'])[1]")).click();
		   		WrapperFunctionUtilities.jsClick(driver.findElement(By.xpath("(//div[@class='autocomplete ag-cell-editor-autocomplete']/div/span/strong[text()='"+value+"'])[1]")), driver);
			}
			else if((colName.contains(ExcelReader.getValue("MappingObject", referredColumnName, "DriverData"))))
			{
				WrapperFunctionUtilities.waitForTime(1000);
				Log.info("In Referred Column");
				boolean test = WrapperFunctionUtilities.isElementPresent(driver.findElement(By.xpath("//div[@role='row']/div[text()='"+ExcelReader.getValue("MappingObject", referredColumnValue, "DriverData")+"']")), "Referred Value");
				Log.info("Boolean value is "+test);
			}else
			{
				Log.info("In loop 3, i is "+ i);
				i--;
				driver.findElement(By.xpath("//div[@role='row']/div[@role='gridcell']["+j+"]")).click();
				WrapperFunctionUtilities.waitForTime(1000);
				Log.info(value);
				driver.findElement(By.xpath("//div[@role='row']/div[@role='gridcell']["+j+"]")).sendKeys(value);
				i++;
			}
		}

	}

	public static void clickSaveNewRow()
	{
		try
		{
			saveNewRow.click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	public static void verifyNewlyAddedRow()
	{
		try
		{
			WrapperFunctionUtilities.waitForTime(3000);
			String msg= msgNewRow.getText();
			Assert.assertEquals(msg, "Record added successfully");
			Log.info("Assert Successful ,Record added successfully");
			WrapperFunctionUtilities.waitForTime(1000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void AddDelete(int getCount){
		String countVal = dataRowInDriverDataTable.getText();
		System.out.println("Newly Added Row - ");
		System.out.println(countVal);
		String[] countValSub = countVal.split("\\R");
		String b = countValSub[2];
		System.out.println(b);
		driver.findElement(By.xpath("//div[text()='"+b+"']/preceding::input[@type='checkbox'][1]")).click();
		DriverDataPage.deleteRowClick.click();
		WrapperFunctionUtilities.waitForTime(2000);
	}

	public static void deleteAddedRow(String sheet, String value) throws Exception {
		String searchText = ExcelReader.getValue(sheet,value, "DriverData");
		ddColumnFilter.click();
		ddColumnFilterSearchBox.sendKeys(searchText);
		WrapperFunctionUtilities.waitForTime(1000);
		ddColumnFilterSearchClick.click();
		WrapperFunctionUtilities.waitForTime(1000);
		//ddColumnFilter.click();
		ddFirstRowCheckBox.click();
		deleteRowClick.click();
	}



	public static void verifyRowAddAuditLog() throws Exception{

		String AddValue = "1 new row(s) added in " + ddTitle.getText() + " object";
		Log.info(AddValue);
		CommonPageFunctions.clickTabUnderEllipsis("Audit Log");
		WrapperFunctionUtilities.waitForTime(2000);
		String LogValue = auditLogValueRow2.getText();
		Assert.assertEquals(AddValue, LogValue);
		System.out.println("Correct Audit Log");

	}
	public static boolean isFileDownloaded(String downloadPath, boolean fileName) {
		boolean flag = false;
		downloadPath = "C:\\Users\\" + username + "\\Downloads";
		    File dir = new File(downloadPath);
		    File[] dir_contents = dir.listFiles();
		    for (int i = 0; i < dir_contents.length; i++) 
		    {
		        if (dir_contents[i].getName().equals(fileName))
		            return flag=true;
		        	Log.info("File Found");
		    } 
	        
	        return flag;
		
		}
	
	public static void DriverDataDownload(String ddObjectName){
		Log.info("Method :  DriverDataDownload");
		WebDriverWait wait = new WebDriverWait(driver, 3);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(driverDataDownload)); 
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		WrapperFunctionUtilities.waitForTime(5000);
		//DriverDataPage.driverDataDownload.click();
		String download = "C:\\Users\\" + username + "\\Downloads";
		String file = ddObjectName+"_March_2021";
		boolean file1 = file.startsWith(file);
		isFileDownloaded(download, file1);
		
		}

	//(Download Version) Verify Detail Column of Audit Logs have correct info in case of DD version been downloaded.
		public static void verifyDDDownloadAuditLog() throws Exception{
			
			String DownloadValue = "Data downloaded for " + ddTitle.getText() + " object";
			Log.info(DownloadValue);
			CommonPageFunctions.clickTabUnderEllipsis("Audit Log");
			WrapperFunctionUtilities.waitForTime(1000);
			String LogValue = auditLogValue.getText();
			Assert.assertEquals(DownloadValue, LogValue);
			System.out.println("Correct Audit Log");
		
		}
		
		public static void DriverDataUpload(String filename) throws Exception{
			int rowBefore = getRowCount();
			driverDataUpload.click();
			driver.getTitle();
			WrapperFunctionUtilities.waitForTime(2000);
			System.out.println("Row Count Before " + rowBefore);
			WrapperFunctionUtilities.waitForTime(2000);
			uploadLink.sendKeys(PROJECT_PATH + "\\src\\test\\java\\com\\RDM\\Merger\\testdata\\"+filename+".xlsx");
			WrapperFunctionUtilities.waitForElementVisibility(uploadButton, 2000, driver);
			uploadButton.click();
			WrapperFunctionUtilities.waitForTime(8000);
			String successMessage = "Driver data uploaded successfully.";
			//int rowAfter = getRowCount();
			//System.out.println("Row Count After " + rowAfter);
			//if(rowAfter > rowBefore){
				Log.info("New Rows Added");
				Assert.assertEquals(getUploadSuccessToasterMessage.getText(),successMessage);
				Log.info(getUploadSuccessToasterMessage.getText());
			/*}
			else{
				Log.info("No Change");
				Log.info(uploadSuccessToaster.getText());
			}
			WrapperFunctionUtilities.waitForTime(1000); */
			
		}
		public static void DriverDeleteInsertUpload(String filename) throws Exception{
			int rowBefore = getRowCount();
			driverDataUpload.click();
			driver.getTitle();
			WrapperFunctionUtilities.waitForTime(2000);
			System.out.println("Row Count Before " + rowBefore);
			uploadDeleteInsert.click();
			WrapperFunctionUtilities.waitForTime(2000);
			uploadLink.sendKeys(PROJECT_PATH + "\\src\\test\\java\\com\\RDM\\Merger\\testdata\\"+filename+".xlsx");
			WrapperFunctionUtilities.waitForElementVisibility(uploadButton, 2000, driver);
			uploadButton.click();
			WrapperFunctionUtilities.waitForTime(8000);
			String successMessage = "Driver data uploaded successfully.";
			int rowAfter = getRowCount();
			System.out.println("Row Count After " + rowAfter);
			if(rowAfter > rowBefore){
				Log.info("New Rows Added");
				Assert.assertEquals(getUploadSuccessToasterMessage.getText(),successMessage);
				Log.info(getUploadSuccessToasterMessage.getText());
			}
			else{
				Log.info("No Change");
				Log.info(getUploadSuccessToasterMessage.getText());
			}
			WrapperFunctionUtilities.waitForTime(1000); 
			
		}
		//(Upload Row - Append) Verify Detail Column of Audit Logs have correct info in case of DD Rows being Uploaded.
		public static void verifyUploadAppendAuditLog() throws Exception{
			
			String UploadAppend = "Data uploaded in append mode for " + ddTitle.getText() + " object";
			Log.info(UploadAppend);
			CommonPageFunctions.clickTabUnderEllipsis("Audit Log");
			WrapperFunctionUtilities.waitForTime(2000);
			String LogValue = auditLogValue.getText();
			Assert.assertEquals(UploadAppend, LogValue);
			System.out.println("Correct Audit Log");
		
		}
		
		//(Upload Row - Insert and Delete) Verify Detail Column of Audit Logs have correct info in case of DD Rows being Uploaded.
		public static void verifyUploadInsertDeleteAuditLog(String ddObject) throws Exception{
			String driverDataObject = ExcelReader.getValue("DriverData", ddObject, "TestData");
			String UploadAppend = "Data uploaded in delete and insert mode for " + driverDataObject + " object";
			Log.info(UploadAppend);
			CommonPageFunctions.clickTabUnderEllipsis("Audit Log");
			WrapperFunctionUtilities.waitForElementVisibility(auditLogValue, 30, driver);
			WrapperFunctionUtilities.waitForTime(2000);
			String LogValue = auditLogValue.getText();
			Assert.assertEquals(UploadAppend, LogValue);
			System.out.println("Correct Audit Log");
			}

		public  static boolean  verifyDriverDataPreviousVersionNavigation(){
			versionDropDown.click();
			versionSelect.click();
			WrapperFunctionUtilities.waitForTime(1000);
			return disabledUploadDataToFileButton.getAttribute("class").contains("zs-disabled");
			}

}
		

