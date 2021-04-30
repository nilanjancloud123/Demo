package com.mydemo;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestUpdated {
	public WebDriver driver;
	public  String USERNAME = "";
	public  String AUTOMATE_KEY = "";
	public  String URL = "";
	 // This method accepts the status, reason and WebDriver instance and marks the test on BrowserStack
	public void markTestStatus(String status, String reason) {  // the same WebDriver instance should be passed that is being used to run the test in the calling funtion
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \""+status+"\", \"reason\": \""+reason+"\"}}");
		}
	
	@Parameters({"browserName", "browser_version", "os", "os_version","Target","platform"})
	
	@BeforeMethod
	
	public void setUp(String browserName,String browser_version,@Optional("optional") String os,@Optional("optional") String os_version,String Target,@Optional("optional") String platform, Method name ,ITestContext ctx) {
		ctx.getCurrentXmlTest().addParameter("Target",Target);
		System.out.println("browser name is : " + browserName);
		String methodName = name.getName();
		
		DesiredCapabilities caps = new DesiredCapabilities();
		if(Target.equals("sauce")) {
		URL ="https://ondemand.eu-central-1.saucelabs.com:443/wd/hub";
		MutableCapabilities sauceOpts = new MutableCapabilities();
		sauceOpts.setCapability("name", methodName);
		sauceOpts.setCapability("build", "Java-W3C-Examples");
		sauceOpts.setCapability("seleniumVersion", "3.141.59");
		sauceOpts.setCapability("username", "nislam123");
		sauceOpts.setCapability("accessKey", "bb112c18-f3e4-45ad-bb04-b791dbe81483");
		sauceOpts.setCapability("tags", "w3c-chrome-tests");
		caps.setCapability("sauce:options", sauceOpts);
		caps.setCapability("browserVersion", browser_version);
		caps.setCapability("platformName", platform);
		if ((browserName).toUpperCase().equals("CHROME")) {
			WebDriverManager.chromedriver().setup();
			caps.setCapability("browserName", "Chrome");
		} else if ((browserName).toUpperCase().equals(("FIREFOX"))) {
			WebDriverManager.firefoxdriver().setup();
			caps.setCapability("browserName", "Firefox");
		}
			
		}else if(Target.equals("browserStack")) {
			USERNAME = "nilanjanislam_pMmbWT";
			AUTOMATE_KEY = "A1qJFrxjzTQTqfA6Q3AJ";
			URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
			caps.setCapability("os", os);
			caps.setCapability("os_version", os_version);
			caps.setCapability("browser_version", browser_version);
			caps.setCapability("name", methodName);
			caps.setCapability("browserstack.console", "errors");
			caps.setCapability("browserstack.debug", "true");
			if ((browserName).toUpperCase().equals("CHROME")) {
				WebDriverManager.chromedriver().setup();
				caps.setCapability("browser", "Chrome");
			} else if ((browserName).toUpperCase().equals("FIREFOX")) {
				WebDriverManager.firefoxdriver().setup();
				caps.setCapability("browser", "Firefox");
			}
			
		}
		//
        
		
	
		
		
		try {
			
			//driver = new RemoteWebDriver(new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub"), caps);
			driver = new RemoteWebDriver(new URL(URL), caps);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
		} catch (MalformedURLException e) {
			markTestStatus("failed",e.toString());
			e.printStackTrace();
			
		}

	}

	@AfterMethod(alwaysRun = true)
	
	public void tearDown(ITestResult result, ITestContext ctx) {
		//((JavascriptExecutor) driver).executeScript("job-result=" + (result.isSuccess() ? "passed" : "failed"));
		String target = ctx.getCurrentXmlTest().getParameter("Target");
		
		if (target.equals("browserStack") ) {
			if (result.isSuccess()) {
				markTestStatus("passed"," " + result.getMethod().getMethodName() + "  Success");
			}else
			{
				markTestStatus("failed"," "+ result.getMethod().getMethodName() +" Not Successfull");
			}
		} else if(target.equals("sauce")) {
			((JavascriptExecutor) driver).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
		}
		driver.quit();
	}

}
