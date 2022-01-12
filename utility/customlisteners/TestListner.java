package com.utility.customlisteners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.utility.base.TestUtils;
import com.utility.logger.Log;

public class TestListner implements ITestListener {

	public void onTestStart(ITestResult result) {

		Log.info("Test Execution started for -> " + result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		Log.info("Test Execution PASSED for -> " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		TestUtils.takeScreenshotAtEndOfTest(result.getName());
		Log.info("Test Execution FAILED for -> " + result.getName());
	}

	public void onTestSkipped(ITestResult result) {
		Log.info("Test Execution SKIPPED for -> " + result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext context) {
		Log.info("Staring Suite  -> " + context.getSuite().getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
