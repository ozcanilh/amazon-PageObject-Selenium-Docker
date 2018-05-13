package com.insider.test.base.page;

import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.insider.test.base.common.Commons;

public class BasePage extends Commons{
	
	/**
	 * Logger Object
	 */
	public static final Logger logger = LogManager.getLogger(BasePage.class);

	/**
	 * WebDriver Object
	 */
	public WebDriver driver;
	public WebElement myDynamicElement;
	public Boolean myElement;

	/**
	 * WebDriverWait Object
	 */
	public static WebDriverWait wait;


	/**
	 * Constructor
	 * 
	 * @param driver WebDriver Object
	 */
	public BasePage(WebDriver driver) {
		this.driver = driver;
		logger.info("Initializing Page Objects...");
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver,60);
	
	}
	
	public boolean isElementExisting(WebElement we) {
	    try {
	        we.isDisplayed();
	        return true;
	    } catch(NoSuchElementException e) {
	    	logger.info("Element does not exist.");
	        return false;
	    }
	}
}
