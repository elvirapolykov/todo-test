package com.todo.automation.model;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ItemsListComponent extends PageObject {

	public ItemsListComponent(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "ul.todo-list")
	private WebElement toDoList;
	
	public ItemsListComponent clickOnItem(String todoItem) {
        findElementWithText(todoItem).findElement(By.cssSelector("input.toggle")).click();
        return this;
    }
	
	public ItemsListComponent removeItem(String todoItem) {
        WebElement todoElement = findElementWithText(todoItem);
        Actions action = new Actions(driver);
        action.moveToElement(todoElement).build().perform();
		todoElement.findElement(By.cssSelector("button.destroy")).click();
        return this;
    }
 
	public ItemsListComponent verifyItemShown(String todoItem, boolean expectedStrikethrough) {
        WebElement todoElement = findElementWithText(todoItem);
        assertNotNull(todoElement);
        assertTrue(todoElement.isDisplayed());
        boolean actualStrikethrough = todoElement.findElement(By.cssSelector("label")).getCssValue("text-decoration").contains("line-through");
        assertEquals(expectedStrikethrough, actualStrikethrough);
        return this;
    }
 
	public ItemsListComponent verifyItemNotShown(String todoItem) {
        assertTrue(findElementsWithText(todoItem).isEmpty());
        return this;
    }
    
    @Override
	public boolean isInitialized() {
		return toDoList.isDisplayed();
	}
 
    private WebElement findElementWithText(String text) {
        return driver.findElement(getConditionForText(text));
    }
 
    private List<WebElement> findElementsWithText(String text) {
        return driver.findElements(getConditionForText(text));
    }
 
    private By getConditionForText(String text) {
        return By.xpath(String.format(".//li//label[text()='%s']/../..", text));
    }
}
