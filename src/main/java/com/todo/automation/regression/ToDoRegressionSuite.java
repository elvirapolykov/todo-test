package com.todo.automation.regression;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.todo.automation.model.ToDoPage;

public class ToDoRegressionSuite extends FunctionalTest {
	
	private ToDoPage todoPage;
	
	@AfterMethod
	public void clear() {
		todoPage.resetTodoList();
	}

	@Test
    public void testCreateTodos() {
		// GIVEN
		todoPage = new ToDoPage(driver).get();
 
        // WHEN
		todoPage
	        .addTodo("Buy milk")
	        .addTodo("Wash car")
	 
	         // THEN
	        .getTodoList()
	        .verifyItemShown("Buy milk", false)
	        .verifyItemShown("Wash car", false);
    }
	
	@Test
    public void testRemoveTodo() {
		// GIVEN
		todoPage = new ToDoPage(driver).get();
		todoPage
	        .addTodo("Buy milk")
	        .addTodo("Wash car")
	        .getTodoList()
	        
	        // WHEN
	        .removeItem("Buy milk")
	 
	         // THEN
	        .verifyItemNotShown("Buy milk")
	        .verifyItemShown("Wash car", false);
    }
	
	@Test
    public void testCompleteTodo() {
        // GIVEN
		todoPage = new ToDoPage(driver).get();
		todoPage
			.addTodo("Buy milk")
	        .addTodo("Wash car")
            .getTodoList()
 
            // WHEN
            .clickOnItem("Wash car")
 
            // THEN
            .verifyItemShown("Buy milk", false)
            .verifyItemShown("Wash car", true);
    }
	
	@Test
    public void testSelectTodosActive() {
        // GIVEN
		todoPage = new ToDoPage(driver).get();
        todoPage
	        .addTodo("Buy milk")
	        .addTodo("Wash car")
            .getTodoList()
            .clickOnItem("Buy milk");
 
        // WHEN
        todoPage
            .selectActive()
 
            // THEN
            .getTodoList()
            .verifyItemNotShown("Buy milk")
            .verifyItemShown("Wash car", false);
    }

	@Test
    public void testSelectTodosCompleted() {
		// GIVEN
		todoPage = new ToDoPage(driver).get();
        todoPage
		    .addTodo("Buy milk")
		    .addTodo("Wash car")
            .getTodoList()
            .clickOnItem("Wash car");
 
        // WHEN
        todoPage
            .selectCompleted()
 
            // THEN
            .getTodoList()
            .verifyItemShown("Wash car", true)
            .verifyItemNotShown("Buy milk");
    }
	
	@Test
    public void testSelectTodosAll() {
		// GIVEN
		todoPage = new ToDoPage(driver).get();
        todoPage
		    .addTodo("Buy milk")
		    .addTodo("Wash car")
            .getTodoList()
            .clickOnItem("Buy milk");
        todoPage
            .selectCompleted()
 
            // WHEN
            .selectAll()
 
            // THEN
            .getTodoList()
            .verifyItemShown("Buy milk", true)
            .verifyItemShown("Wash car", false);
    }
}
