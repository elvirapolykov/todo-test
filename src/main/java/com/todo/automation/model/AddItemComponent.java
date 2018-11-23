package com.todo.automation.model;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddItemComponent extends PageObject {

	@FindBy(css = "input.new-todo")
	private WebElement newToDoInput;
	
	public AddItemComponent(WebDriver driver) {
		super(driver);
	}

	public AddItemComponent addItem(String todo) {
		newToDoInput.clear();
		newToDoInput.sendKeys(todo);
		newToDoInput.sendKeys(Keys.ENTER);
        return this;
    }
	
	@Override
	public boolean isInitialized() {
		return newToDoInput.isDisplayed();
	}
}
