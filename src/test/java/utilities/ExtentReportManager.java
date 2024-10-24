package utilities;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
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

import testBase.Baseclass;

public class ExtentReportManager implements ITestListener
{

	public ExtentSparkReporter sparkreporter; //UI of the report
		public ExtentReports extent;     //populate common info of the report
		public ExtentTest test;  //creating test case entries in the report and update status of the test methods
		String repname;
		
	public	void onStart(ITestContext testContext)
		{
		
		/*SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt = new Date();
		String currentdatetimestamp=df.format(dt);*/   //instead of writing this we can write in single line
		
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		repname="Test-Report-"+timestamp+".html";
		
		sparkreporter = new ExtentSparkReporter(".\\reports\\"+repname);//specify the location of the report

			sparkreporter.config().setDocumentTitle(" Open Automation Report");//title of the report
			sparkreporter.config().setReportName("opencart functional testing");//name of the report
			sparkreporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkreporter);
		
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("module", "Admin");
		extent.setSystemInfo("Sub module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name") );
		extent.setSystemInfo("Environment", "QA");
		
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
	     extent.setSystemInfo("Operating System",os);
	
	     String browser = testContext.getCurrentXmlTest().getParameter("browser");
	     extent.setSystemInfo("Browser",browser);
	
        List<String> includeGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if(!includeGroups.isEmpty()) {
        	extent.setSystemInfo("Groups", includeGroups.toString());
        }
	
		}
	
	public   void onTestSuccess(ITestResult result) 
	{
		   test = extent.createTest(result.getTestClass().getName());  //create a new entry in report
		   test.assignCategory(result.getMethod().getGroups());
		   test.log(Status.PASS, result.getName()+"got sucessfully executed");
	}


	public  void onTestFailure(ITestResult result) 
	{
		test = extent.createTest(result.getTestClass().getName()); 
		 test.assignCategory(result.getMethod().getGroups());
		 
		 test.log(Status.FAIL,result.getName() +"Test case FAILED is:");
		 test.log(Status.FAIL, result.getThrowable().getMessage()+"Test case FAILED cause is:");
		
		 try {
			 String imgpath =new Baseclass().captureScreen(result.getName());
			 test.addScreenCaptureFromPath(imgpath);
			 
			 }
			 catch (Exception e1) {
				 e1.printStackTrace();
			 }
	}
		 
		  public void onTestSkipped(ITestResult result) 
		  {
			  test = extent.createTest(result.getTestClass().getName()); 
			  test.assignCategory(result.getMethod().getGroups());
		     test.log(Status.SKIP, "Test case SKIPPED is:"+result.getName());
		     test.log(Status.INFO, result.getThrowable().getMessage());
		  }


		  public void onFinish(ITestContext testContext) 
		  {
			 
			  extent.flush();
			  String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repname;
			  File extentReport = new File(pathOfExtentReport);
			  try {
				  Desktop.getDesktop().browse(extentReport.toURI());
				    }
			  catch(IOException e) {
				  e.printStackTrace();
			  }
			  
			  }
		  }

	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

