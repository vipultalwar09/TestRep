package com.RDM.Merger.testcases;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.utility.base.TestBase;

public class FunctionsUtilities extends TestBase{

	public static ArrayList<String> getColumnDataWithStringValues(String sheetName,String colName,String filepath) throws IOException
	{
		FileInputStream fis=new FileInputStream(filepath);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet;
		ArrayList<String> str=new ArrayList<String>();
		int sheets=wb.getNumberOfSheets();
		System.out.println(sheets);
		int ColumnIndex=0;
		int i;
		for(i=0;i<sheets;i++)
		{
			sheet=wb.getSheetAt(i);

			if(wb.getSheetName(i).equalsIgnoreCase(sheetName))
			{
				System.out.println("inside "+sheetName);

				Iterator<Row>  rows = sheet.iterator();
				Row firstrow=rows.next();

				Iterator<Cell> cell=firstrow.cellIterator();
				int index=0;
				while(cell.hasNext())
				{
					Cell value=cell.next();
					if(value.getStringCellValue().equalsIgnoreCase(colName))
					{
						System.out.println("found "+value.getStringCellValue());
						ColumnIndex=index;
					}
					index++;
				}


				String s;
				while(rows.hasNext())
				{
					Row r=rows.next();
					str.add(r.getCell(ColumnIndex).getStringCellValue());
				}           

			}
		}


		return str;



	}
	public static ArrayList<Integer> getColumnDataWithNumericValues(String sheetName,String colName,String filepath) throws IOException
	{
		FileInputStream fis=new FileInputStream(filepath);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet;
		ArrayList<Integer> num=new ArrayList<Integer>();
		int sheets=wb.getNumberOfSheets();
		System.out.println(sheets);
		int ColumnIndex=0;
		int i;
		for(i=0;i<sheets;i++)
		{
			sheet=wb.getSheetAt(i);

			if(wb.getSheetName(i).equalsIgnoreCase(sheetName))
			{
				System.out.println("inside "+sheetName);

				Iterator<Row>  rows = sheet.iterator();
				Row firstrow=rows.next();

				Iterator<Cell> cell=firstrow.cellIterator();
				int index=0;
				while(cell.hasNext())
				{
					Cell value=cell.next();
					if(value.getStringCellValue().equalsIgnoreCase(colName))
					{
						System.out.println("found "+value.getStringCellValue());
						ColumnIndex=index;
					}
					index++;
				}


				while(rows.hasNext())
				{
					Row r=rows.next();
					int n=(int) r.getCell(ColumnIndex).getNumericCellValue();
					num.add(n);
				}           

			}
		}


		return num;


	}
}


