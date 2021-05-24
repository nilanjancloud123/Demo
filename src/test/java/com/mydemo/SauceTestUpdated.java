package com.mydemo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.testng.Assert;
import org.testng.annotations.Test;


public class SauceTestUpdated extends BaseTestUpdated{
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

}
