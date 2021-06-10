package com.mydemo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.cloudExecution.CloudExecution;
import RestPost.CustomAutomationBot;


public class BaseTestUpdated implements ITest {
	public WebDriver driver;
	public  String USERNAME = "";
	public  String AUTOMATE_KEY = "";
	public  String URL = "";
	public Boolean BROWSERSTACK_LOCAL=false;
    public String BROWSERSTACK_LOCAL_IDENTIFIER="";
    public Properties prop;
    public String EndPoint="";
    public String filepath="";
    public ThreadLocal<String> testName = new ThreadLocal<>();
    public static String cycleid;
    public String tkn ="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzZGY1MGMzNC00NTgwLTMwYzYtYTJhMi00NTRmODUwNjc1ODciLCJjb250ZXh0Ijp7ImJhc2VVcmwiOiJodHRwczpcL1wvaW5ub3ZhdGlvbnF0LmF0bGFzc2lhbi5uZXQiLCJ1c2VyIjp7ImFjY291bnRJZCI6IjYwOWU1M2VmNDdiYTAyMDA2ZmNmZjk2YiJ9fSwiaXNzIjoiY29tLmthbm9haC50ZXN0LW1hbmFnZXIiLCJleHAiOjE2NTI4NzY3NzIsImlhdCI6MTYyMTM0MDc3Mn0.Z5SOIUCSyf30Fo8ZYW0j1idyNxRV4-u9t3w-y5AtJC0";
    public String EndPointCloud="https://api.adaptavist.io/tm4j/v2";
    public BaseTestUpdated(){
	   	    try	
	   	    {
	   	    	
	    	prop= new Properties();
	    	FileInputStream ip= new FileInputStream(System.getProperty("user.dir")+"/properties/config.properties");
	    	prop.load(ip);
	    	EndPoint = prop.getProperty("Endpoint");
	    	tkn =prop.getProperty("Tkn");
	    	filepath=prop.getProperty("FilePath");
	   	    }catch(FileNotFoundException e) {
	    	e.printStackTrace();
	   	    }catch(IOException e) {
	    	e.printStackTrace();
	    }
   }
    
	 // This method accepts the status, reason and WebDriver instance and marks the test on BrowserStack
	public void markTestStatus(String status, String reason) {  // the same WebDriver instance should be passed that is being used to run the test in the calling funtion
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \""+status+"\", \"reason\": \""+reason+"\"}}");
		}
	
	public void DeleteFile() {

	    
	        Path path = FileSystems.getDefault().getPath("./reports");
	       
	        try {
	            Files.deleteIfExists(path);
	        } catch (NoSuchFileException x) {
	            System.err.format("%s: no such" + " file or directory%n", path);
	        } catch (DirectoryNotEmptyException x) {
	            System.err.format("%s not empty%n", path);
	        } catch (IOException x) {
	            System.err.println(x);
	        }
	    
	}
	public static void deleteDirectory(File file)
    {
        // store all the paths of files and folders present
        // inside directory
        for (File subfile : file.listFiles()) {
  
            // if it is a subfolder,e.g Rohan and Ritik,
            // recursiley call function to empty subfolder
            if (subfile.isDirectory()) {
                deleteDirectory(subfile);
            }
  
            // delete files and empty subfolders
            subfile.delete();
        }
    }
	public void deletefile(String filePathN) {
		String localDir = System.getProperty("user.dir");
		
		File file = new File(localDir + filePathN);
	    // call deleteDirectory function to delete
	    // subdirectory and files
	    deleteDirectory(file);
	    // delete main GFG folder
	    //file.delete();
	}
	
	/*
	 * public class SauceTestUpdated implements ITest {
	 * 
	 * 
	 * @Override public String getTestName() { // TODO Auto-generated method stub
	 * return testName.get(); }
	 */
//		@Override
//		public String 
		
//	}

	
	@BeforeSuite
	public void fileclear() throws IOException {
		String filepath = "\\screenshot";
		deletefile(filepath);
		String filepath1 = "\\reports";
		deletefile(filepath1);
		String filepath2 = "\\email\\screenshot";
		deletefile(filepath2);
		String filepath3 = "\\email\\reports";
		deletefile(filepath3);
		
	// store file path
    //String filepath = "C:\\Users\\nilanjan.islam\\eclipse-workspace\\Demo\\Demo\\screenshot";
		 
		
	  cycleid = CustomAutomationBot.createCloudTesCycle(tkn,"QTDEM",EndPointCloud,"Automation Regression Demo C1") ;
	  System.out.println("*****CycleID *****" + cycleid);
	  
	  

	}
	public void filecopy(String source,String dest) {
		String localDir = System.getProperty("user.dir");
		File source1 = new File(localDir + source);
		File dest1 = new File(localDir + dest);
		try {
		    FileUtils.copyDirectory(source1, dest1);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
	
	
	
	@AfterSuite
	public void fileemail() throws IOException, InterruptedException 
	{
		
		//JiraExecution.testExecution("user","Pwd","Endpoint","QTDEM", payloadString);
		try {
			filecopy("\\screenshot","\\email\\screenshot");
			filecopy("\\reports","\\email\\reports");
			filecopy("\\screenshot","\\HistoricalReports\\screenshot");
			filecopy("\\reports","\\HistoricalReports\\reports");
			String localDir = System.getProperty("user.dir");
			(new ZipUtils()).zipfile( localDir+ "\\email",localDir+ "\\email.zip");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
			
		
		
		
		
	}
	
		
		@Parameters({"browserName", "browser_version", "os", "os_version","Target","platform"})
	
//	@BeforeMethod
//	public void BeforeMethod(Method method,String browserName ){
//		   testName.set(method.getName() + "_" + browserName);
//		}
		
	@BeforeMethod
	public void setUp(String browserName,String browser_version,@Optional("optional") String os,@Optional("optional") String os_version,String Target,@Optional("optional") String platform, Method name ,ITestContext ctx) {
		ctx.getCurrentXmlTest().addParameter("Target",Target);
		System.out.println("browser name is : " + browserName);
		String methodName = name.getName();
		testName.set(methodName+"_"+browserName);
		
		ctx.setAttribute("testName", testName.get());
		if(Target.equals("sauce")) {
			URL ="https://ondemand.eu-central-1.saucelabs.com:443/wd/hub";
			driver=CloudExecution.SauceLabSetUp("nislam1234", "360f9b55-3aa0-40a5-b8d4-30a8a206f074", platform, URL, browserName, browser_version, testName.get());

			
		}else if(Target.equals("browserStack")) {
			USERNAME = "nilanjanislam_QvESjU";
			AUTOMATE_KEY = "yzfpaoLLrHRhxANoJ3Tj";
			driver=CloudExecution.browserStackSetUp(USERNAME, AUTOMATE_KEY, os, os_version, browserName, browser_version, testName.get());
		}
	
	}
	
	@AfterMethod(alwaysRun = true)
	
	public void tearDown(ITestResult result, ITestContext ctx) {
		//((JavascriptExecutor) driver).executeScript("job-result=" + (result.isSuccess() ? "passed" : "failed"));
		String target = ctx.getCurrentXmlTest().getParameter("Target");
		
		String TestName= (String) ctx.getAttribute("testName");
		String exceptionMessage1="";
		String TestCaseKey="";
		String Comment="";
		String Status="";
		//String Endpoint = "https://api.adaptavist.io/tm4j/v2";
		
		if (TestName.equals("checkInventoryItemTest_Chrome")) {
			TestCaseKey="QTDEM-T4";
		}
		if (TestName.equals("checkAddToCartButtonTest_Chrome")) {
			TestCaseKey="QTDEM-T5";
		}
		if (TestName.equals("checkInventoryItemTest_Firefox")) {
			TestCaseKey="QTDEM-T6";
		}
		if (TestName.equals("checkAddToCartButtonTest_Firefox")) {
			TestCaseKey="QTDEM-T7";
		}
		result.setAttribute(target, target);
		result.setAttribute(TestName, TestName);
		
		if (result.isSuccess()) {
			Comment=result.getMethod().getMethodName() + "  Success";
			Status = "pass";
			CustomAutomationBot.testExecutionCloud(tkn,"QTDEM",EndPointCloud, TestCaseKey, cycleid, Status, Comment);
			
		}else
		{
			exceptionMessage1 = Arrays.toString(result.getThrowable().getStackTrace());
			Comment=exceptionMessage1.replaceAll(",", "<br>");
			Status = "fail";
			CustomAutomationBot.testExecutionCloud(tkn,"QTDEM",EndPointCloud, TestCaseKey, cycleid, Status, Comment);
			
		}
		if (target.equals("browserStack") ) {
			if (result.isSuccess()) {
				markTestStatus("passed"," " + Comment);
				
			}else
			{
				markTestStatus("passed"," " + Comment);
			}
		} else if(target.equals("sauce")) {
			((JavascriptExecutor) driver).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
		}
		
		
		driver.quit();
	}
	

	@Override
	public String getTestName() {
		// TODO Auto-generated method stub
		return testName.get();
	}
	
//	public void genaratereport() throws IOException {
//		String localDir = System.getProperty("user.dir");
//		String FilePathupdated = localDir + filepath;
//		(new RestPost()).executeMultiPartRequest(EndPoint, new File(FilePathupdated),tkn);
//	}
	
	
}
