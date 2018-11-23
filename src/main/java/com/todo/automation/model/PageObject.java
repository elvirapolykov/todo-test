package com.todo.automation.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageObject {
	
	protected WebDriver driver;
	
	protected final WebDriverWait wait;

	public PageObject(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 10);
		PageFactory.initElements(driver, this);
	}

	public abstract boolean isInitialized();
}
