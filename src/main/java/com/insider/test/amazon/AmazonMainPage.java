package com.insider.test.amazon;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import com.insider.test.base.common.SeleniumMethods;
import com.insider.test.base.page.BasePage;
import com.insider.test.properties.insiderTestResourcesKey;

public class AmazonMainPage extends BasePage {

	private static final pageNumberEnum SECOND = pageNumberEnum.SECOND;

	public AmazonMainPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public String resultText;
	public String productNameList;
	public int productResultSize;
	public static int productNumber;
	public String pageNumber;
	public String productName;
	
	private enum pageNumberEnum {
		   FIRST("1"),
		   SECOND("2");
		   public String paginationNumber;
		   private pageNumberEnum (String paginationNumber) {
		        this.paginationNumber = paginationNumber;
		    }

		    public String toString() {
		        return paginationNumber;
		    }
	}
	
	/****Web Elements - Page Objects**/	
	private By BUTTON_SIGN_IN = By.cssSelector("#nav-link-accountList");

	@FindBy(how = How.ID, using = "twotabsearchtextbox") 
	private WebElement INPUT_SEARCH;
	
	@FindBy(how = How.CLASS_NAME, using = "nav-input") 
	private WebElement BUTTON_SEARCH;
	
	@FindBy(how = How.ID, using = "s-result-count") 
	private WebElement RESULT_COUNT;
	
	@FindBy(how = How.ID, using = "") 
	private WebElement PAGE_ID;
	
	@FindBy(how = How.ID, using = "add-to-wishlist-button-submit") 
	private WebElement ADD_LIST;

	@FindBy(how = How.ID, using = "WLHUC_result") 
	private WebElement LIST_POPUP;
	
	@FindBy(how = How.CLASS_NAME, using = "a-popover-wrapper") 
	private WebElement WISH_LIST_POPUP;
	
	@FindBy(how = How.ID, using = "WLNEW_list_type_SL") 
	private WebElement WISH_LIST;
	
	@FindBy(how = How.XPATH, using = "//div[2]/div/div/span/div/label/i")  
	private WebElement WISH_LIST_CHECKBOX;
	
	private By CREATE_LIST = By.xpath("//span[contains(., \"Create List\")]");
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"wl-huc-post-create-msg\"]/div/div[2]/span[1]/span/a") 
	private WebElement VIEW_LIST;
	
	@FindBy(how = How.CLASS_NAME, using = "a-alert-content") 
	private WebElement DELETED_TEXT;
	
	public static String RESULT_NUMBER = "//*[@id=\"result_@value\"]/div/div/div/div[1]";
	
	public static String RESULT_TEXT= "//*[@id=\"result_@value\"]/div/div/div/div[2]/div[1]/div[1]/a/h2";
	
	private By PRODUCT_NAME = By.id("productTitle");
	
	private By SEARCHED_TEXT = By.className("a-color-state");
	
	private By PRODUCTS_NAME = By.cssSelector("a:nth-child(1) > h2:nth-child(1)");
	
	private By PAGE_NUMBER = By.className("pagnLink");
	
	/** Functionality  */	
	/** Opens Login Page by Given URL */
	public AmazonMainPage getLoginPage(String url){
		driver.get(url);
		return this;
	}	
	
	public void confirmCorrectURL(){
		try{
			  Assert.assertEquals(insiderTestResourcesKey.URL, driver.getCurrentUrl());
			  System.out.println("Navigated to correct webpage url");
			}
			catch(Throwable pageNavigationError){
			  System.out.println("Didn't navigate to correct webpage");
			}  
	}
	
	public void confirmPageTitle(){
		try{
			Assert.assertEquals(insiderTestResourcesKey.TITLE, driver.getTitle());
			    logger.trace("Navigated to correct webpage title");
			}
			catch(Throwable pageNavigationError){
				logger.trace("Didn't navigate to correct webpage");
			}
	}
		
	public void clickSignButton(){
		driver.findElement(BUTTON_SIGN_IN).click();
	}
	
	public void searchText(String searchText){
		INPUT_SEARCH.sendKeys(searchText);
		BUTTON_SEARCH.click();	
	}
	
	public void resultsForSearchedText(){
		resultText = driver.findElement(SEARCHED_TEXT).getText();
		resultText = resultText.replaceAll("^\"|\"$", "");
		logger.trace("Find text is" + resultText);
	}
	
	public void getResultsNameOfFirstPage() {
		List<WebElement> products = driver.findElements(PRODUCTS_NAME);
		productResultSize = products.size(); 
		logger.trace(products.size());
		logger.trace("All Products Name in First Page :");
		for(WebElement product : products){
	      productNameList = product.getText();
	      logger.trace(productNameList); }	   
		
	}
		
	public void clickSecondPage() {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		List<WebElement> pages = driver.findElements(PAGE_NUMBER);
		for(WebElement page : pages){
	      pageNumber = page.getText();
	      logger.trace(pageNumber); 
	      if (pageNumber.equals(pageNumberEnum.SECOND.toString()) ) {
				driver.findElement(PAGE_NUMBER).click();
				break;
			}  
		}	  		
			
	}
	
	public void clickThirdProduct(String productValue) {
		WebElement productNumber = driver.findElement(By.xpath(RESULT_NUMBER.replace("@value", productValue)));		
		productNumber.click();
	}
	
	public void addtoWishList() {
		productName = driver.findElement(PRODUCT_NAME).getText();
		logger.trace("Selected product is"+ productName);	
		ADD_LIST.click();	
		SeleniumMethods.waitUntilElementVisible(driver, WISH_LIST);
		WISH_LIST_CHECKBOX.click();
		driver.findElement(CREATE_LIST).click();
	}
	
	public void clickViewList() {
		VIEW_LIST.click();
	}
			
}
