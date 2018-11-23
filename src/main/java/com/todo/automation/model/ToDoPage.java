package com.todo.automation.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ToDoPage extends PageObject {

	private static final String URL = "http://todomvc.com/examples/react/#/";
	
    private final ItemsListComponent todoItemsList;
    private final AddItemComponent addTodoItemComponent;
    
    @FindBy(css = "label[for='toggle-all']")
	private WebElement toggleAll;
	
	public ToDoPage(WebDriver driver) {
		super(driver);
		todoItemsList = new ItemsListComponent(driver);
        addTodoItemComponent = new AddItemComponent(driver);
	}

	public ToDoPage get() {
		driver.get(URL);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return isInitialized();
			}
		});
		return this;
	}
	
	public ToDoPage selectAll() {
        findElementWithText("All").click();
        return this;
    }
 
	public ToDoPage selectActive() {
        findElementWithText("Active").click();
        return this;
    }
 
	public ToDoPage selectCompleted() {
        findElementWithText("Completed").click();
        return this;
    }
 
	public ToDoPage resetTodoList() {
		toggleAll.click();
		findElementWithText("Clear completed").click();
        return this;
    }
	
	public ToDoPage addTodo(String todoName) {
        addTodoItemComponent.addItem(todoName);
        return this;
    }
 
    public ItemsListComponent getTodoList() {
        return todoItemsList;
    }
 
    private WebElement findElementWithText(String text) {
        return driver.findElement(getConditionForText(text));
    }
 
    private By getConditionForText(String text) {
        return By.xpath(String.format("//*[text()='%s']", text));
    }
    
	@Override
	public boolean isInitialized() {
		return addTodoItemComponent.isInitialized() && addTodoItemComponent.isInitialized();
	}
}
