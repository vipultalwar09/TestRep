package com.utility.customlisteners;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utility.base.TestUtils;
import com.utility.logger.Log;

public class ExtentReporterNG implements IReporter {
	private ExtentReports extent;
	public static File dir;
	public static String path;
	public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	public static Date date = new Date();
	
	static {
//		path = System.getProperty("user.dir") + "/Reports/extentreport-" + dateFormat.format(date);
		path = "./Reports";
		Arrays.stream(new File(path).listFiles()).forEach(File::delete);
		Log.info("Reports folder is cleaned successfully");
		dir = new File(path);
		System.out.println("Report directory name = " + dir);
		boolean successful = dir.mkdir();
		if (successful) {
			System.out.println("directory was created successfully");
		} else {
			System.out.println("failed trying to create the directory");

		}
	}
	
  //Providing Ouput directory for Extent Report Listener . Can be configured
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		
		extent = new ExtentReports(dir + "/RDH_Selenium_Report.html", true);
		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
				buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
			}
		}

		extent.flush();
		extent.close();
	}

	
	/*
	 * The Following methods gets the status result of the test cases executed
	 */
	private void buildTestNodes(IResultMap tests, LogStatus status) {
		ExtentTest test;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.startTest(result.getMethod().getMethodName());

				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);
				
				if (result.getStatus() == ITestResult.FAILURE) {
					test.log(LogStatus.FAIL, test.addScreenCapture("."+TestUtils.screenshotpath.toString()));
				}

				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable());
				} else {
					test.log(status, "Test " + status.toString().toLowerCase() + "ed");
				}

				extent.endTest(test);
			}
		}
	}
/*
 * Getting the timestamp
 */
	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
}