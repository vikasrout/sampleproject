package com.qa.practisetestng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.utility.ReadDataFromExcel;

public class DynamicXpath {

	WebDriver driver;

	public String sheetname="Registration";

	@BeforeMethod
	public void setup(){
		System.setProperty("webdriver.chrome.driver", "C:/Users/vikas.rout/workspace/PractiseTestNg/Drivers/chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get("https://www.amazon.com/b?ie=UTF8&node=11848201011");
	}



	@DataProvider
	public static Object[][] getdata() {
		 
		return new Object[][] {{"sampleuser1","sampleuser2","sampleuser3","sampleuser4"}};
 
  }
	
	@DataProvider
	public Object[][] getdatafromexcel(){
		Object [][] data=ReadDataFromExcel.getdatafromexcel(sheetname);
		return data;
	}

	@Test(dataProvider="getdatafromexcel")
	public void clickOnRegisterLink(String Name,String Email,String Password,String RePassword){
		driver.findElement(By.xpath("//a[contains(@id,'nav-link-accountList')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Create your Amazon account')]")).click();
		driver.findElement(By.xpath(".//input[@id='ap_customer_name']")).sendKeys(Name);
		driver.findElement(By.xpath(".//input[@id='ap_email']")).sendKeys(Email);
		driver.findElement(By.xpath(".//input[@id='ap_password']")).sendKeys(Password);
		driver.findElement(By.xpath(".//input[@id='ap_password_check']")).sendKeys(RePassword);

	}

	@AfterMethod
	public void teardown(){
		driver.quit();
	}
}
