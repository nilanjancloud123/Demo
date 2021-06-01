package com.mydemo;

import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.testng.Assert;
import org.testng.IAlterSuiteListener;
import org.testng.ITest;
import org.testng.annotations.Test;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;


public class SauceTestUpdated extends BaseTestUpdated implements IAlterSuiteListener {
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	public void Login() {
		driver.get("https://www.saucedemo.com");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
	}
	
	
	@Test(priority = 1)
	//@TestCase(key = "QTDEM-T1")
	public void checkInventoryItemTest() {
		System.out.println("******************************Test Name ********************************"+testName.get());
		Login();
		//ctx.getCurrentXmlTest().addParameter("Target","Target");
		
		
		
	}

	@Test(priority = 2)
	//@TestCase(key = "QTDEM-T2")
	public void checkAddToCartButtonTest() {
		Login();
		//ctx.getCurrentXmlTest().addParameter("Target","Target");
		System.out.println("*********Size of Add To Cart********" +driver.findElements(By.xpath("//button[text()='Add to cart']")).size());
		
		Assert.assertTrue(driver.findElements(By.xpath("//button[text()='Add to cart']")).size() == 6);
	}


	
	/*
	 * @Override public String getTestName() { // TODO Auto-generated method stub
	 * return testName.get(); }
	 */

	@Override
	public void alter(List<XmlSuite> suites) {
		// TODO Auto-generated method stub
		XmlSuite suite = suites.get(0);
        XmlTest xmlTest = new XmlTest(suite);
        xmlTest.setName(testName.get());
        String packages = System.getProperty("package", suite.getParameter("package"));
        XmlPackage xmlPackage = new XmlPackage(packages);
        xmlTest.setXmlPackages(Collections.singletonList(xmlPackage));
	}

}

