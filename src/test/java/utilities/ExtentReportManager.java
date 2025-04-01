package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;//Ui of the report
	public ExtentReports extent;//populate common info on the report
	public ExtentTest test;//creating test case entries in the report and update status of the test methods
	String repName;
	public void onStart(ITestContext testcontext) {
		/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentdatestimestamp=df.format(dt);*/
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-Report-"+timeStamp+".html";
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);//specify location
		sparkReporter.config().setDocumentTitle("opencart Automation Report");//Title of report
		sparkReporter.config().setReportName(" opencart Functional Testing");//name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application","opencart");
		extent.setSystemInfo("Module","Admin");
		extent.setSystemInfo("Sub Module","Customer");
		extent.setSystemInfo("User Name",System.getProperty("user.name"));
		extent.setSystemInfo("Environment","QA");
		String os=testcontext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		String browser=testcontext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		List<String> includedGroups=testcontext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
		
	}
	public void onTestSuccess(ITestResult result) {
		test= extent.createTest(result.getTestClass().getName());//create a new entry in the report
		test.assignCategory(result.getMethod().getGroups());//to display groups in report
		test.log(Status.PASS,result.getName()+"Got successfully executed");//update status p/f/s
	}
	public void onTestFailures(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL,result.getName()+" Got failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
		try {
			String impath=new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(impath);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,result.getName()+" Got Skipped");
		test.log(Status.INFO,result.getThrowable().getMessage());
		
	}
	public void onFinish(ITestContext context) {
	extent.flush();
   String PathOfExtentReport=System.getProperty(".reports/"+repName);
   File extentReport=new File(PathOfExtentReport);
   try {
	   Desktop.getDesktop().browse(extentReport.toURI());
   }catch (Exception e) {
	e.printStackTrace();
}
}
	}
