package com.insider.test.amazon.test;

import static org.testng.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.insider.test.amazon.AmazonMainPage;
import com.insider.test.amazon.SignInPage;
import com.insider.test.amazon.WishListPage;
import com.insider.test.base.common.Commons;
import com.insider.test.base.test.BaseTest;
import com.insider.test.properties.insiderTestResourcesKey;


public class AmazonTest extends BaseTest{
	
	/**
	 * Logger Object
	 */
	private static final Logger logger = LogManager.getLogger(Commons.class);
	public AmazonMainPage onAmazonMainPage;
	public SignInPage onSignInPage;
	public WishListPage onWishListPage;
	
	@BeforeClass
	public void testSetUp() throws Exception {
        
    	logger.info("STARTING TEST: Browser is opening..");
    	onAmazonMainPage = new AmazonMainPage(driver);
    	onSignInPage = new SignInPage(driver);
    	onWishListPage = new WishListPage(driver);
	} 
	
	@AfterMethod
	public void caseSetUp() throws Exception {
   /* Not need any function after method for this scenario */
	}
	
	@Test (priority = 1)
	public void loginURL() {
			 logger.trace("Test Step: Navigate to login page");
			 onAmazonMainPage.getLoginPage(insiderTestResourcesKey.URL);		 		 	 
		    }
	 
	/**Confirm URL and Title*/
	@Test (priority = 2)
	public void confirmURLandTitle() {
		logger.trace("Test Step: Confirm URL and Title");
		onAmazonMainPage.confirmCorrectURL();	
		onAmazonMainPage.confirmPageTitle();	
	 }
	
	/**Login*/
	@Test (priority = 3)
	public void login() {
		logger.trace("Test Step: Click Sign In Button");
		onAmazonMainPage.clickSignButton();
		
		logger.trace("Test Step: Login with valid credantials");
		onSignInPage.signIn(PRODUCTION_EMAIL, PRODUCTION_PASSWORD);
	 }
	
	/**Search Text*/
	@Test (priority = 4)
	public void searchText() { 
		logger.trace("Test Step: Search Text");
		onAmazonMainPage.searchText(insiderTestResourcesKey.SEARCH_TEXT);
	 }
	
	/**Result For Searched Text*/
	@Test (priority = 5)
	public void resultsForSearchedText() {
		logger.trace("Test Step: Results For Searched Text");
		onAmazonMainPage.resultsForSearchedText();
		Assert.assertTrue(onAmazonMainPage.resultText.equals(insiderTestResourcesKey.SEARCH_TEXT));
		logger.trace("Result Text:" +onAmazonMainPage.resultText + " Searched Text:" +insiderTestResourcesKey.SEARCH_TEXT);
	 }
	
	/**Get Products Name of First Page*/
	@Test (priority = 6)
	public void getResultsNameOfFirstPage() {
		logger.trace("Test Step: Get Products Name");
		onAmazonMainPage.getResultsNameOfFirstPage();		
	 }
	
	/**Click Second Page*/
	@Test (priority = 7)
	public void clickSecondPage() {
		logger.trace("Test Step: Click Second Page");
		onAmazonMainPage.clickSecondPage();		
	 }
	
	/**Add to list Third Product*/
	@Test (priority = 8)
	public void clickThirdProduct() {
		logger.trace("Test Step: Click Third Product");
		onAmazonMainPage.clickThirdProduct("18");		
	 }
	
	/**Add to WishList*/
	@Test (priority = 9)
	public void addtoWishList() {
		logger.trace("Test Step: Add to Wish List");
		onAmazonMainPage.addtoWishList();		
		onAmazonMainPage.clickViewList();
	 }
	
	/**Confirm Product in Wish List which selected */
	@Test (priority = 10)
	public void confirmSelectedProductInWishList() {
		logger.trace("Test Step: Confirm Selected Product In Wish List");
		onWishListPage.getProductNameInWishList();
		logger.trace("Product Name in WishList:"+onWishListPage.productNameInWishList + " Selected Product:"+onAmazonMainPage.productName);
		Assert.assertTrue(onWishListPage.productNameInWishList.equals(onAmazonMainPage.productName));
	 }
	
	/**Delete Item in WishList*/
	@Test (priority = 11)
	public void deleteItem() {
		logger.trace("Test Step: Delete Item in WishList");
		onWishListPage.deleteItem();
	}
	
	/**Check Deleted Item in WishList*/
	@Test (priority = 12)
	public void checkDeletedItemInWishList() {
		logger.trace("Test Step: Check Deleted Item in WishList");
		onWishListPage.checkDeletedItem();
		Assert.assertTrue(onWishListPage.wishListItemCount.equals("0 items in this view"));
	}
}
