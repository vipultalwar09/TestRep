package com.utility.base;

import java.util.Hashtable;

public class DataUtil {

	public static Object[][] getTestData(String testName, Xls_Reader xls)
	{
		//Find the testCase row number 
		
				int testCaseNameRowNum = 1;          //GETTING THE DATA FROM DATA SHEET
				while(! xls.getCellData("Data", 0, testCaseNameRowNum).equals(testName))    
				{
					testCaseNameRowNum++;
				}
				System.out.println("Row number of the test case is -- " + testCaseNameRowNum);
				
				//Find the total number of cols for the test case
				int totalColCount = 0;
				int colStartRowNum = testCaseNameRowNum + 1;
				
				while(! xls.getCellData("Data", totalColCount, colStartRowNum).equals(""))
				{
					totalColCount++;
				}
				System.out.println("Total columns for the test case are -- " +totalColCount);
				
				//Find total number of data rows for the test case
				
				int dataStartRowNum = testCaseNameRowNum + 2;
				int totalDataRows = 0;
				
				while(! xls.getCellData("Data", 0, dataStartRowNum).equals(""))
				{
					totalDataRows++;
					dataStartRowNum++;
				}
				System.out.println("Total number of data rows for the test case is -- " + totalDataRows);
				
				
				//Read Data for test case from xls
				
				dataStartRowNum = testCaseNameRowNum + 2;
				int finalRows = dataStartRowNum + totalDataRows;
				Hashtable<String, String> table = null;
				Object[][] myData = new Object[totalDataRows][1];    //Initializing object array to size of row num to total no of data rows and size of cols in object array to 1
				
				int i = 0;
				for(int rNum = dataStartRowNum; rNum < finalRows; rNum ++)
				{
					table = new Hashtable<String, String>();
					for(int cNum = 0; cNum < totalColCount; cNum ++)
					{
						String data = xls.getCellData("Data", cNum, rNum);
						String key = xls.getCellData("Data", cNum, colStartRowNum);
						System.out.println(key+" ---- "+data);
						table.put(key, data);
					}
					System.out.println(table);
					myData[i][0] = table;                         // Storing data of 1 row from table to object array
					System.out.println("---------------------");
					i++;
				}
				
				return myData;
	}
	
	//Function to check the runmode of a test case. If runmode = false then dont execute the test cases 
	//this function will return - true - means testcase not to be executed
	//this function will return - false - means test case to be executed

	
	public static boolean isSkip(String testName, Xls_Reader xls)
	{
		int rows = xls.getRowCount("TestCases");
		
		for(int rNum=2; rNum<=rows; rNum++)
		{
			String tcid = xls.getCellData("TestCases", "TCID", rNum);
			if(tcid.equals(testName))
			{//test case found
				String runmode = xls.getCellData("TestCases", "Runmode", rNum);
				
				if(runmode.equals("N"))
					return true;
				else
					return false;
			}
		}
		return true;
	} 
}
