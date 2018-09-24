package com.qa.practisetestng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Dependenciestest {
	
	WebDriver driver;
	
	@BeforeClass()
	public void setup(){
		System.setProperty("webdriver.chrome.driver", "C:/Users/vikas.rout/workspace/PractiseTestNg/Drivers/chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://pos-stage.nextdayblinds.com/login");
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	
	@Test
	public void login(){
		driver.findElement(By.xpath("//input[@id='username']")).clear();
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("Pos-sahtest");
		driver.findElement(By.xpath("//input[@id='password']")).clear();
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("N0tS3cur3!");
		driver.findElement(By.xpath("//button[@class='btn-outline']")).click();
	}

	@Test(dependsOnMethods="login")
	public void homepage() throws InterruptedException{
		Thread.sleep(10000);
		String logoname=driver.findElement(By.xpath("//img[@class='create-cutomer-logo']")).getText();
		System.out.println("The log name is : "+logoname);
	}
	
	@Test(dependsOnMethods="login")
	public void logout(){
	
		System.out.println(driver.findElement(By.xpath("//i[@class='icon-plus']")).getText());			
		
	}
	
}
