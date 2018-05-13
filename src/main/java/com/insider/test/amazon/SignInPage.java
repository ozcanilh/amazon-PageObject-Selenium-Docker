package com.insider.test.amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.insider.test.base.common.SeleniumMethods;
import com.insider.test.base.page.BasePage;

public class SignInPage extends BasePage {

	public SignInPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	private AmazonMainPage onAmazonMainPage;
	
	/****Web Elements - Page Objects**/
	@FindBy(how = How.ID, using = "ap_email") 
	private WebElement INPUT_EMAIL;
	
	@FindBy(how = How.ID, using = "ap_password") 
	private WebElement INPUT_PASSWORD;
	
	@FindBy(how = How.ID, using = "signInSubmit") 
	private WebElement BUTTON_SIGN_IN;
	
	/** Functionality  */	
	private void clickSignIn(){
		BUTTON_SIGN_IN.click();
	}
	
	private void typeEmail(String username){
		INPUT_EMAIL.clear();
		INPUT_EMAIL.sendKeys(username);		  
	}
	
	private void typePassword(String password){
		INPUT_PASSWORD.clear();
		INPUT_PASSWORD.sendKeys(password);		  
	}
	
	public void signIn(String email, String password){
		typeEmail(email);
		typePassword(password);
		clickSignIn();
	}
}
