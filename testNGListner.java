package TestNGListner;

import java.io.File;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import RestPost.RestPost;



public class testNGListner implements ITestListener{
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		//testName.get();
		String TestName1= ctx.getCurrentXmlTest().getParameter("testName");
		result.setAttribute(TestName, TestName1);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
//		String localDir = System.getProperty("user.dir");
//		String FilePathupdated = localDir + filepath;
//		System.out.println("*******************File PAth ****************************************************"+FilePathupdated);
//		(new RestPost()).executeMultiPartRequest(EndPoint, new File(FilePathupdated),tkn);
	}

}
