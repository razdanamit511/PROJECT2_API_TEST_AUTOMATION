package Listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestListener implements ITestListener, ISuiteListener {

	ExtentSparkReporter reporter;
	ExtentReports reports;
	ExtentTest test;


	@Override
	public void onTestSuccess(ITestResult result) {
		test = reports.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
		test.assignAuthor(result.getTestContext().getCurrentXmlTest().getParameter("Author"));
		test.assignDevice(result.getTestContext().getCurrentXmlTest().getParameter("Device"));
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, "TEST PASSED");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test = reports.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
		test.assignAuthor(result.getTestContext().getCurrentXmlTest().getParameter("Author"));
		test.assignDevice(result.getTestContext().getCurrentXmlTest().getParameter("Device"));
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "TEST FAILED");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test = reports.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
		test.assignAuthor(result.getTestContext().getCurrentXmlTest().getParameter("Author"));
		test.assignDevice(result.getTestContext().getCurrentXmlTest().getParameter("Device"));
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "TEST SKIPPED");

	}

	@Override
	public void onStart(ISuite suite) {
		
		reporter = new ExtentSparkReporter(
				"C:\\Users\\user\\eclipse-workspace\\API-Automation\\Rest-Assured\\Projects\\PROJECT2_PRACTICE\\ExtentReports\\Report1.html");
		reports = new ExtentReports();

		reports.attachReporter(reporter);
	}

	@Override
	public void onFinish(ISuite suite) {
		
		reports.flush();
	}



}
