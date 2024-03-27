package rahulshettyacademy.testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterEngine;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;
	ExtentReports er=ExtentReporterEngine.getReportObject();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();//for Thread safe execution

	@Override
	public void onTestStart(ITestResult result) {
		test=er.createTest(result.getMethod().getMethodName());
		extentTest.set(test);//assigns unique thread id to test object
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());//extract the assigned id test object
		//result.getTestClass() takes test from testng.xml and getRealClass() gets test class name like SubmitOrder or ErrorValidation
		
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
		} 
		
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
		}
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		er.flush();
	}

}
