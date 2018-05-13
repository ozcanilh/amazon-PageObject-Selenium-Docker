package com.insider.test.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.insider.test.base.common.SeleniumMethods;
import com.insider.test.base.page.BasePage;

public class WishListPage extends BasePage  {

	public WishListPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	private enum wishListTab {
		   YOURLIST("my-lists-tab"),
		   YOURIDEALIST("discover-lists-tab"),
		   YOURFRIENDS("friends-tab");
		   public String wishListName;
		   private wishListTab (String wishListName) {
		        this.wishListName = wishListName;
		    }

		    public String toString() {
		        return wishListName;
		    }
	}
	
	public String productNameInWishList;
	public String wishListItemCount;
	
	/****Web Elements - Page Objects**/	
	private By DELETE_ITEM = By.xpath("//a[contains(@class, 'a-link-normal a-declarative g-visible-js')]");
	
	private By PRODUCT_NAME_IN_WISHLIST = By.xpath("//div[1]/div[1]/h3/a");
	
	private By WISHLIST_ITEM_COUNT = By.cssSelector(".aok-inline-block > span:nth-child(1)");
	
	/** Functionality  */	
	public void deleteItem() {	
		driver.findElement(DELETE_ITEM).click();
	}
	
	public void getProductNameInWishList() {
		WebElement element = driver.findElement(PRODUCT_NAME_IN_WISHLIST);
	    productNameInWishList = element.getText();
	}
	
	public void checkDeletedItem() {
		driver.navigate().refresh();
		SeleniumMethods.checkElementIsVisible(WISHLIST_ITEM_COUNT);
		wishListItemCount = driver.findElement(WISHLIST_ITEM_COUNT).getText();
		logger.trace(wishListItemCount);
	}

}
