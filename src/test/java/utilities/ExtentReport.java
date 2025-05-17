package utilities;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import TestBase.BaseClass;

public class ExtentReport implements ITestListener {
		
		
		public ExtentSparkReporter sparkReporter;
		public ExtentReports extent; 
		public ExtentTest test;
		
		String repName;
		
		public void onStart(ITestContext testContext) 
		
		{
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report" + timeStamp + ".html";
		String folder = System.getProperty("user.dir");
		sparkReporter = new ExtentSparkReporter(folder +File.separator + "reports" +File.separator + repName);
		
		sparkReporter.config().setDocumentTitle("JPetStore_Automation Report"); // Title of report
		sparkReporter.config().setReportName("JPetStore Testing"); // name of the report
	
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application", "JPetStore");
		extent.setSystemInfo("User name", System.getProperty("user.name"));
		
		}
		
		public void onTestSuccess(ITestResult result) 
		{
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.PASS, result.getName() + "got successfully executed");
			
		
		}
		
		
			public void onTestFailure(ITestResult result) 
			{
				
				test = extent.createTest(result.getTestClass().getName());
				test.assignCategory(result.getMethod().getGroups());
				
				test.log(Status.FAIL, result.getName() + "got failed");
			    test.log(Status.INFO, result.getThrowable().getMessage());
			    
			  
			    try
			    {
			    	String imgPath = new BaseClass().captureScreen(result.getName());
			    	test.addScreenCaptureFromPath(imgPath);
			    }
			    catch(IOException e1)
			    {
			    	e1.printStackTrace();
			    }
			}
			
			
			
			public void onTestSkipped(ITestResult result)
			
			{
			
				test = extent.createTest(result.getTestClass().getName());
				test.assignCategory(result.getMethod().getGroups());
				test.log(Status.SKIP, result.getName() + "got skipped");
			    test.log(Status.INFO, result.getThrowable().getMessage());
			
			}
			
			
			
			public void onFinish(ITestContext testContext)
			{
				
			extent.flush();
			
	}

		
	 
		
}
