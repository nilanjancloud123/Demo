package Base;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	private static ExtentReports extent;
	
	public static ExtentReports createInstance()  {
		String filename= getReportName();
		InetAddress id = null;
		try {
			id = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	    
		String directory = System.getProperty("user.dir")+"/reports/";
		new File (directory).mkdir();
		String path=directory+filename;
		ExtentHtmlReporter htmlReporter= new ExtentHtmlReporter(path);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Automation Test Result");
		htmlReporter.config().setTheme(Theme.STANDARD);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("UserName", System.getProperty("user.name"));
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("JAVA Version", System.getProperty("java.version"));
		extent.setSystemInfo("OS",  id.getHostName());
		return extent;
		
	}

	public static String getReportName() {
		Date d=new Date();
		String filename="AutomationReport_"+d.toString().replace(":", "_").replace(" ", "_")+".html";
		return filename;
		
	}
}
