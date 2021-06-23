/* ----------------------------------------------------------------------------
 * Copyright (c) 2008 by Airbus SAS. All rights reserved. All intellectual
 * property rights in and/or in the computer program and its related
 * documentation and technology are the sole Airbus' property. This computer
 * program is under Airbus copyright and cannot be in whole or in part
 * reproduced, sublicensed, leased, sold or used in any form or by any means,
 * including without limitation graphic, electronic, mechanical, photocopying,
 * recording, taping or information storage and retrieval systems without Airbus
 * prior written consent. The downloading, exporting or reexporting of this
 * computer program or any related documentation or technology is subject to any
 * export rules, including US regulations.
 * ----------------------------------------------------------------------------
 */
package com.hydroottawa.ccbupgrade.poc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * The Class ConfigReader.
 */
public class ConfigReader {

	/** The pro. */
	Properties pro;

	/**
	 * Instantiates a new config reader.
	 */
	public ConfigReader() {
		try {

			File file = new File("./configuration/configSGC.properties");

			FileInputStream fis = new FileInputStream(file);

			// Create a object of properties class

			pro = new Properties();

			// Load Properties file

			pro.load(fis);

		} catch (Exception e) {

			//System.out.println("Hello..I am under catch block");
			e.getMessage();

		}

	}

	/**
	 * Gets the application URL.
	 *
	 * @return the application URL
	 */
	public String getApplicationURL()

	{
		// System.out.println("getApplicationURL"+ pro.getProperty("URL"));
		return pro.getProperty("URL");

	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername(String username)

	{
   
			
		return pro.getProperty(username);

	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public  String getPassword(String password)

	{

		return pro.getProperty(password);

	}
	
	/**
	 * Gets the environment.
	 *
	 * @return the environment
	 */
	public String getEnvironment()

	{

	return pro.getProperty("Environment");

	}

	/**
	 * Gets the selenium ver.
	 *
	 * @return the selenium ver
	 */
	public String getSeleniumVer()

	{

	return pro.getProperty("Selenium");

	}

	/**
	 * Gets the cucumber ver.
	 *
	 * @return the cucumber ver
	 */
	public String getCucumberVer()

	{

	return pro.getProperty("Cucumber");

	}  

}