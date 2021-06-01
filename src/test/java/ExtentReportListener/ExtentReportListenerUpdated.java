package ExtentReportListener;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.mydemo.SauceTestUpdated;

import Base.ExtentManager;

public class ExtentReportListenerUpdated implements ITestListener{
	
	
	private static ExtentReports extent=ExtentManager.createInstance();
	private static ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	
	
	@Override
	public void onTestStart(ITestResult result) {
		
		//extent.createTest(result.getTestContext().getAttribute("testName").toString());

		ExtentTest test = extent.createTest(result.getTestClass().getName()+ "  ::  "+result.getTestContext().getAttribute("testName").toString());
		extentTest.set(test);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		WebDriver driver=((SauceTestUpdated)result.getInstance()).driver;
		String path = ".."+takescreenshot(driver,result.getMethod().getMethodName());
		System.out.println("****************Path*************" + path);
		try {
			extentTest.get().pass("<b><font color>" + "Screenshot of Passed Step"+"</font></b>",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}catch(IOException e) {
			extentTest.get().pass("Test Passed , cannot attach screenshot");
		}
		
		String logText="<b>Test Method "+ result.getMethod().getMethodName() + "Successfull</b>";
		Markup m =MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS, m);
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		extentTest.get().fail("<details><summary><b><font color=red>" + "Exceptoion Occured, click to see details :"+ "</font></b></summary>"+ exceptionMessage.replaceAll(",", "<br>")+"</details> \n");
		WebDriver driver=((SauceTestUpdated)result.getInstance()).driver;
		String path = ".." + takescreenshot(driver,result.getMethod().getMethodName());
		
		try {
			extentTest.get().fail("<b><font color>" + "Screenshot of failure"+"</font></b>",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}catch(IOException e) {
			extentTest.get().fail("Test Failed , cannot attach screenshot");
		}
		
		String logText="<b>Test Method "+ result.getMethod().getMethodName() + "Failed</b>";
		Markup m =MarkupHelper.createLabel(logText, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, m);
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		String logText="<b>Test Method "+ result.getMethod().getMethodName() + "Skipped</b>";
		Markup m =MarkupHelper.createLabel(logText, ExtentColor.YELLOW	);
		extentTest.get().log(Status.FAIL, m);
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub


	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
		if(extent!=null) {
			extent.flush();
		}
		
	}
	public String takescreenshot(WebDriver driver,String methodname) {
		String filename = getScreenshotName(methodname);
		String directory = System.getProperty("user.dir")+"/screenshot/";
		//String directory = "../screenshot/";
		new File (directory).mkdir();
		String path1 = "/screenshot/"+ filename;
		String path=directory+filename;
		try {
			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(path));
			System.out.println("**********************************");
			System.out.println("Screenshot stored at: " + path);
			System.out.println("**********************************");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return path1;
		
	}
	public static String getScreenshotName(String methodname) {
		Date d=new Date();
		String filename="AutomationReport_"+d.toString().replace(":", "_").replace(" ", "_")+ ".png";
		return filename;
		
	}
}
