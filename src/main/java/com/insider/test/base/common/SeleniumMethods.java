package com.insider.test.base.common;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.insider.test.base.page.BasePage;

public class SeleniumMethods extends BasePage{
	
	public SeleniumMethods(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**  Checking Element Visibility - Clickable */

    private static final int TIMEOUT_WAIT_SECONDS = 10;

    public static void waitUntilElementVisible(WebDriver driver, WebElement element){
        try {
            WebElement myDynamicElement = (new WebDriverWait(driver, TIMEOUT_WAIT_SECONDS))
                    .until(ExpectedConditions.visibilityOf(element));
        } catch(TimeoutException e) {
            //log (with element description)
            throw e;
        }
    }

    public static void waitUntilElementClickable(WebDriver driver, WebElement element){
        try {
            WebElement myDynamicTextElement = (new WebDriverWait(driver, TIMEOUT_WAIT_SECONDS))
                    .until(ExpectedConditions.elementToBeClickable(element));
        } catch(TimeoutException e) {
            //log
            throw e;
        }
    }
    
    public static void checkElementIsVisible(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
}
