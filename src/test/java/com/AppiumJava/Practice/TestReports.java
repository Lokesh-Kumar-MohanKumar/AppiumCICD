package com.AppiumJava.Practice;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestReports {

	ExtentSparkReporter reporter;
	static ExtentReports extent;

	@BeforeSuite
	public void reportSetUp() {
		// start reporters
		reporter = new ExtentSparkReporter("extent.html");
		// Configuring
		reporter.config().setReportName("Mobile Automation Reports");
		reporter.config().setDocumentTitle("Test Results");

		// create ExtentReports and attach reporter(s)
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Lokesh");
	}

	@AfterSuite
	public void reportTearDown() {
		extent.flush();
	}

}
