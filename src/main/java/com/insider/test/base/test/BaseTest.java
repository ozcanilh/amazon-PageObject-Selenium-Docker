package com.insider.test.base.test;

import org.testng.annotations.AfterClass;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.insider.test.base.common.Commons;


public class BaseTest extends Commons {

	/**
	 * Logger Object
	 */
	private static final Logger logger = LogManager.getLogger(BaseTest.class);

	/**
	 * WebDriver Object
	 */
	public WebDriver driver;
	DesiredCapabilities capabilities = null;
	
	/**
	 * This method will execute before each Test tag in testng.xml
	 * 
	 * @param browser Browser name as parameter. Should be defined in testng.xml
	 * @throws Exception
	 */
	

	@BeforeClass
	@Parameters("browser")  
	public void initalizeTests(@Optional("chrome") String browser) throws Exception { 
		if (browser.equalsIgnoreCase("firefox")) {
			logger.info("Creating Firefox instance...");
			System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);	
			driver = new FirefoxDriver(capabilities);

		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();	
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			
			ChromeOptions options = new ChromeOptions();
		    options.addArguments("disable-infobars");
		    options.addArguments("--disable-extensions");
		    options.addArguments("--disable-notifications");
		    options.addArguments("--start-maximized");
	        options.addArguments("--disable-web-security");
	        options.addArguments("--no-proxy-server");
		    options.addArguments("--enable-automation");
		    options.addArguments("--disable-save-password-bubble");	
			
			
			Map<String, Object> prefs = new HashMap<String, Object>();
		    prefs.put("credentials_enable_service", false);
		    prefs.put("profile.password_manager_enabled", false);
		    options.setExperimentalOption("prefs", prefs);
			
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setPlatform(Platform.WIN10);
			logger.info("Creating Chrome instance...");
			driver = new ChromeDriver(capabilities);
		
		} else if (browser.equalsIgnoreCase("ie")) {
			// set path to IE.exe
			System.setProperty("webdriver.ie.driver", "src\\main\\resources\\IEDriverServer.exe");
			// create IE instance
			logger.info("Creating IE instance...");
			driver = new InternetExplorerDriver();
		} else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		// Set global timeout as 3 seconds
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
/*
	  @BeforeClass
	    @Parameters({"browser","remoteurl",})
	    public void initalizeTests(String browser, String remoteurl) throws Exception {
	 
	        if (browser.equalsIgnoreCase("firefox")) {
	            capabilities = DesiredCapabilities.firefox();
	        } else if (browser.equalsIgnoreCase("chrome")) {
	            capabilities = DesiredCapabilities.chrome();       
	        } else {
	            // If no browser passed throw exception
	            throw new Exception("Browser is not correct");
	        }
	 
	        driver = new RemoteWebDriver(new URL(remoteurl), capabilities);
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    } 
	*/
	
	/**
	 * This method will execute after each Test tag in testng.xml
	 */
	@AfterClass
	public void finalizeTests() {
		logger.trace("Finalizing tests.");
		driver.quit();		
	}
	
	@AfterSuite
	public void killChromeDriverProcess(){
		logger.trace("All Chrome Driver processes are killed.");
		try {
		    Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	
}