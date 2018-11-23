package com.todo.automation.regression;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FunctionalTest {
	protected static WebDriver driver;

	static {
		WebDriverManager.chromedriver().setup();
	}
	
	@BeforeClass
	public static void setUp() {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	@AfterClass
    public void quitDriver() {
		driver.quit();
    }
}
