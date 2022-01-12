package com.RDM.Merger.testcases.Aa_Login;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;


import com.utility.base.TestBase;
import com.utility.base.Xls_Reader;
import com.utility.logger.Log;

public class ExcelReader extends TestBase{

	public static String path =  PROJECT_PATH + "/src/test/java/com/RDM/Merger/testdata/TestData.xlsx";
	static DataFormatter formatter = new DataFormatter(Locale.US);

	public static HashMap  getTestDataFromExcel(String sheetName, String filename) {
		FileInputStream file = null;
		Workbook book= null;
		org.apache.poi.ss.usermodel.Sheet sheet;
		org.apache.poi.ss.usermodel.Row row;
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
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		Log.info("Row Count "+rowCount);
		//Create a loop to print cell values in a row
		while(test<=rowCount)
		{	
			row = sheet.getRow(test);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				//Print Excel data in console
				//addition of Keys
				if(test==0)
				{
					//Log.info("test value "+test+ " j value " + j +  " key value "+ row.getCell(j).getStringCellValue());
					a1.add(formatter.formatCellValue(row.getCell(j)));
				}
				else
				{
					//Add Values

					//a2.add((row.getCell(j).getStringCellValue()).toString());
					a2.add(formatter.formatCellValue(row.getCell(j)));
				}

			}
			test++;
			//System.out.println("Test value" + test);
			//	return data;
		}
		for(int l=0;l<a1.size()&&l<a2.size();l++)
		{
			{
				hmap.put(a1.get(l).toString().trim(), a2.get(l).toString().trim());
				//Log.info("hmap put" + a1.get(l) + a2.get(l));
			}
		}

		return hmap;
	}

	public static String getValue(String sheetName, String colName, String fileName) throws Exception {

		Map<String, String> myVal= (Map<String, String>) getTestDataFromExcel(sheetName, fileName);
		String retValue = (String) myVal.get(colName);
		System.out.println("get value returns " + retValue );
		return retValue;

	}

	public static LinkedHashMap  getTestDataFromExcelSheetWithRow(String sheetName, String filename, int rowNum, String excelFormat) {
		FileInputStream file = null;
		Workbook book= null;
		Sheet sheet;
		Row row;
		int test =0;
		ArrayList a1 = new ArrayList();
		ArrayList a2 = new ArrayList();
		LinkedHashMap hmap =new LinkedHashMap();
		try {
			file = new FileInputStream(PROJECT_PATH + "\\src\\test\\java\\com\\RDM\\Merger\\testdata\\"+filename+"."+excelFormat);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		row = sheet.getRow(0);
		for (int j = 0; j < row.getLastCellNum(); j++) {
			a1.add(formatter.formatCellValue(row.getCell(j)));
		}
		row = sheet.getRow(rowNum-1);
		for (int j = 0; j < row.getLastCellNum(); j++) {
			a2.add(formatter.formatCellValue(row.getCell(j)));
		}

		for(int l=0;l<a1.size()&&l<a2.size();l++)
		{
			{
				hmap.put(a1.get(l).toString().trim(), a2.get(l).toString().trim());
			}
		}

		return hmap;
	}

}