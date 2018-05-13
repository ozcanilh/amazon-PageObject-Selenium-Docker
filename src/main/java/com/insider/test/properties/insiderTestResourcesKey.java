package com.insider.test.properties;

import org.openqa.selenium.WebDriver;

import com.insider.test.base.common.Commons;
import com.insider.test.base.page.BasePage;

public class insiderTestResourcesKey extends BasePage{
	
	public Commons onCommons;
	
	public insiderTestResourcesKey(WebDriver driver) {
		super(driver);
		onCommons = new Commons();
		// TODO Auto-generated constructor stub
	}

	public final static String WEBAPP_PARAMETER_link = "webapp.parameter.link";
	public final static String URL = "https://www.amazon.com/";
	public final static String TITLE = "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more";
			
	/** AMAZON PORTAL */
	public final static String SEARCH_TEXT  		    		= "samsung";

	
}
