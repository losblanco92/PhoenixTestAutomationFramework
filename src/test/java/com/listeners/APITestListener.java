package com.listeners;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class APITestListener implements ITestListener {

	private static final Logger LOGGER = LogManager.getLogger(APITestListener.class);
			
	
	public void onTestStart(ITestResult result) {
		LOGGER.info("************************************************************");
		LOGGER.info("============Starting test {}=============", result.getName());
		LOGGER.info(result.getMethod().getTestClass());
		LOGGER.info("Description:{}",result.getMethod().getDescription());
		LOGGER.info("Groups{}", Arrays.toString(result.getMethod().getGroups()));
		LOGGER.info("************************************************************");
	  }	
	
	public void onTestSuccess(ITestResult result) {
		LOGGER.info("************************************************************");
	long totalDurationTaken	= result.getStartMillis() - result.getEndMillis();
	   LOGGER.info("Duarion taken: {} ms", totalDurationTaken);
		LOGGER.info("{} Test Passed!!!!!", result.getName());
	  }
	
	public void onTestFailure(ITestResult result) {
		LOGGER.info("************************************************************");
		LOGGER.error("{} Test Failed!!!!!", result.getName());
		LOGGER.error(result.getThrowable());
	
	}
	
	public void onTestSkipped(ITestResult result) {
		LOGGER.info("************************************************************");
		LOGGER.info("{} Test Skipped!!!!!", result.getName());
		LOGGER.error(result.getThrowable());
	  }
	
	public void onStart(ITestContext context) {
		LOGGER.info("************************************************************");
		LOGGER.info("Phoenix Test Automation Framework Started");
	  }

	  
	  public void onFinish(ITestContext context) {
		  LOGGER.info("************************************************************");
			LOGGER.info("Phoenix Test Automation Framework Ended");
	  }
}
