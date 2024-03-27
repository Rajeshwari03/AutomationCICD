package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterEngine {
public static ExtentReports getReportObject() {
	String path=System.getProperty("user.dir")+"\\reports\\index.html";
	ExtentSparkReporter esr=new ExtentSparkReporter(path);
	esr.config().setReportName("Web Automation Results");
	esr.config().setDocumentTitle("Test Results");
	
	ExtentReports er=new ExtentReports();
	er.attachReporter(esr);
	er.setSystemInfo("Tester", "Rajeshwari Kathar");
	return er;
}
}
