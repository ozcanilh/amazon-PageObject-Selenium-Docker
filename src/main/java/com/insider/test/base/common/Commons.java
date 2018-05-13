package com.insider.test.base.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Commons {
	/**
	 * Logger Object
	 */
	public static final Logger logger = LogManager.getLogger(Commons.class);
	
	// Application specific properties
	//Amazon Portal
	public static String PRODUCTION_EMAIL;
	public static String PRODUCTION_PASSWORD;

	public Commons() {
		String propFilePath = "C:/insider.properties";
		
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(propFilePath));
			logger.error(properties.toString());
			PRODUCTION_EMAIL = properties.getProperty("email_production"); 
			PRODUCTION_PASSWORD = properties.getProperty("password_production"); 
		} catch (IOException e) {
			logger.error("Unable to read from ".concat(propFilePath));
			e.printStackTrace();
		}
	}
	

}
