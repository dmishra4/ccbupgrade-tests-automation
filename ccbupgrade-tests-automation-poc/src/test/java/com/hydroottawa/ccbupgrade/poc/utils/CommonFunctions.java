package com.hydroottawa.ccbupgrade.poc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hydroottawa.ccbupgrade.poc.page.ref.POC_PageRef;



public class CommonFunctions implements POC_PageRef{
	
	public static String glbpath = null;
	
	public static WebDriver driver;
	
	public static String screenshotfilename;
	
	public static byte[] snapshot;
	/**
	 * Function to take the snapshot
	 * @param driver
	 * @param snapshotName
	 * @return
	 */
	
	public static String getSnapshotBase64(WebDriver driver, String snapshotName) {
		String timeStamp =null;
		FileInputStream fileInputStreamReader = null;
		String encodedBase64 = null;
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			String dir = System.getProperty("user.dir");
			glbpath = dir + "\\Screenshots\\";
			File dst = new File(glbpath +snapshotName + "_" + timeStamp + ".png");
			FileUtils.copyFile(src, dst);
			fileInputStreamReader = new FileInputStream(dst);
			byte[] bytes = new byte[(int)dst.length()];
			fileInputStreamReader.read(bytes);
			encodedBase64 = new String(Base64.encodeBase64(bytes));						
		} catch (Exception e) {

			e.getMessage();
		}
	//return (snapshotName + "_" + timeStamp + ".png");
		return "data:image/png;base64,"+encodedBase64;
	}
	
	/**
	 * Function for open new browser.
	 * @param browserName
	 */
	public static void openBrowser(String browserName) {
		if (browserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "Drivers\\geckodriver.exe");			
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("Chrome")) {
			//System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver_v2.37.exe");
			//System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver_v73.exe");
			try {
			System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver_75_140.exe");
			driver = new ChromeDriver();}
			catch (Exception e)
			{
				ChromeOptions chromeOptions= new ChromeOptions();
				chromeOptions.setBinary("C:\\Users\\DeepakKumar\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
				System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver_v75.exe");
				driver = new ChromeDriver(chromeOptions);
			}
		} else if (browserName.equalsIgnoreCase("IE")) {
			//System.setProperty("webdriver.ie.driver", "drivers\\IEDriverServer_v3.4.0_win32.exe");
			System.setProperty("webdriver.ie.driver", "Drivers\\IEDriverServer_Win32_3.11.1.exe");
			driver = new InternetExplorerDriver();
		}
		driver.manage().window().maximize(); // Maximize window
	}
	
	/**
	 * Function to navigate to the URL
	 * 
	 * @param Environment
	 *            - environment name e.g. dev1 / dev3
	 */
	public static void openCCBURL(String Environment) {
		try {
			if (Environment.equalsIgnoreCase("dev")) {
			//driver.get("http://10.166.188.149:6600/ouaf");       // Ignite
			driver.get("http://10.166.188.148:7003/CCBSIT/loginPage.jsp");   // SIT Keta
			//driver.get("https://10.182.8.67:6601/ouaf/cis.jsp");   // CC&B 2.7 Dev ENV
			//driver.get("https://10.182.8.101:6601/ouaf/cis.jsp"); 
			//driver.get("https://10.182.8.88:6601/ouaf/cis.jsp"); 

			} else if (Environment.equalsIgnoreCase("QA")) {
				driver.get("http://10.166.188.149:6600/ouaf");
				//driver.get("http://10.166.188.148:7003/CCBSIT/loginPage.jsp");
				Thread.sleep(5000);
			} else {
				System.out.println("Mentioned enviroment is not yet defined");
			}
		} catch (Exception e) {
			System.out.println("Mentioned environment is not yet defined");
			e.getMessage();
		}
	}
	
	/**
	 * Gets the system environment value for Environment name
	 * @param env
	 * @return
	 */

	public static String EnvironmentUtility (String env)
	{
		String ENV_KEY = "Environment_Name";
		String ENV_NAME = System.getenv(ENV_KEY);
		System.out.println("The value of ENV_Name is " +ENV_NAME);
		String ENV_KEY_REPLACEMENT_WORD = "%ENV%";			
		return env.replace(ENV_KEY_REPLACEMENT_WORD, ENV_NAME);
		
	}
	
	/**
	 * Function to login in the CCB 2.7 
	 * 
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	public static void login(String username, String password) throws Exception

	{
		
		
		  //ConfigReader configobj = new ConfigReader();
		  //driver.findElement(By.xpath("//input[@id='userId']")).sendKeys(configobj. getUsername(username));
		  //System.out.println("Username :: "+ configobj.getUsername(username));
		  //driver.findElement(By.xpath("//input[@id='password']")).clear();
		  //System.out.println("Password :: "+configobj.getPassword(password));
		 
		 // driver.findElement(By.xpath("//input[@id='password']")).sendKeys(configobj.getPassword(password)); 
		  
		  //System.out.println("Password :: "+configobj.getPassword(password));
		//  Thread.sleep(10000);
		 // driver.findElement(By.xpath("//input[@id='loginButton']")).click();
		 // Thread.sleep(10000); //CCBPageValidation();
		WebDriverWait wait = new WebDriverWait(driver,25);	
		//driver.findElement(By.xpath("//input[@id='userId']")).sendKeys("IGNITE1");
		//driver.findElement(inputUserID).sendKeys("IGNITE1");
		//driver.findElement(inputUserID).sendKeys("SYSUSER");
		driver.findElement(inputUserID).sendKeys("CADEEPAK");
		//driver.findElement(inputUserID).sendKeys("CADEEPAK");
		//driver.findElement(By.xpath("//input[@id='password']")).sendKeys("IgIBM123#");
		//driver.findElement(inputPassword).sendKeys("IgIBM123#");
		//driver.findElement(inputPassword).sendKeys("weblogic");
		driver.findElement(inputPassword).sendKeys("Rd5MrMNFas3N");
		//driver.findElement(inputPassword).sendKeys("Rd5MrMNFas3N");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='loginButton']")));
		//driver.findElement(By.xpath("//input[@id='loginButton']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnLogin));
		driver.findElement(btnLogin).click(); 
		//Thread.sleep(15000);
		//driver.switchTo().frame("main");
		
	}
	
	public static void CCBPageValidation(String Title) {

		// driver.switchTo().frame("topMenu");
		WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ptitle']")));
		//String PageTitle = driver.findElement(By.xpath("//*[@id='ptitle']")).getText();
		wait.until(ExpectedConditions.visibilityOfElementLocated(hCtrlCentralTitle));
		String PageTitle = driver.findElement(hCtrlCentralTitle).getText();
		System.out.println("Page Title is :: " + PageTitle);
		Assert.assertEquals("Page Title did not match ", Title, PageTitle);

	}
	
	/**
	 * Functions to delete old screenshots.
	 */

	public static void cleanup_screenshots()
	{
		String path = System.getProperty("user.dir") + "\\Screenshots\\";
		File deleteOldScreenshots = new File(path);
		try {
			FileUtils.cleanDirectory(deleteOldScreenshots);
		} catch (Exception e) {
			System.out.println("Ensure scrrenshot path is correctly mentioned");
			e.getMessage();
		}
	}
	
	/**
	 * Function to take the snapshot
	 * @param driver
	 * @param snapshotName
	 * @return
	 */
	
	public static byte[] getSnapshot(WebDriver driver, String snapshotName) {
		String timeStamp =null;
		FileInputStream fileInputStreamReader = null;
		String encodedBase64 = null;
		byte[] bytes=null;
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			String dir = System.getProperty("user.dir");
			glbpath = dir + "\\Screenshots\\";
			File dst = new File(glbpath +snapshotName + "_" + timeStamp + ".png");
			FileUtils.copyFile(src, dst);
			fileInputStreamReader = new FileInputStream(dst);
			bytes = new byte[(int)dst.length()];
			fileInputStreamReader.read(bytes);
			//encodedBase64 = new String(Base64.encodeBase64(bytes));						
		} catch (Exception e) {

			e.getMessage();
		}
	//return (snapshotName + "_" + timeStamp + ".png");
		return bytes;
	}
	
	/**
	 * Function to get current calendar date
	 * @param dateformat
	 * @return
	 */
	public static String currentDate(String dateformat) {
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat(dateformat);
		String todayDate = dateFormat.format(date);
		System.out.println("Today's date is  :: "+ todayDate);
		return todayDate;
	}

	/**
	 * Function to retrieve future date
	 * @param dateformat
	 * @param days
	 * @return
	 */
	public static String futureDate(String dateformat, int days) {
		Date date = Calendar.getInstance().getTime();
		//System.out.println(date);
		DateFormat dateFormat = new SimpleDateFormat(dateformat);
		String todayDate = dateFormat.format(date);
		//System.out.println("Current Date :: " + todayDate);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(dateFormat.parse(todayDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.add(Calendar.DAY_OF_MONTH, days);
		String futureDate = dateFormat.format(c.getTime());
		System.out.println("The value of future date is :: "+ futureDate);
		return futureDate;
	}
	
	/**
	 * Function to retrieve past date
	 * @param dateformat
	 * @param days
	 * @return
	 */
	
	public static String pastDate(String dateformat, int days) {
		Date date = Calendar.getInstance().getTime();
		//System.out.println(date);
		DateFormat dateFormat = new SimpleDateFormat(dateformat);
		String todayDate = dateFormat.format(date);
		//System.out.println("Current Date :: " + todayDate);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(dateFormat.parse(todayDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.add(Calendar.DAY_OF_MONTH, days);
		String pastDate = dateFormat.format(c.getTime());
		System.out.println("The value of past date is :: "+ pastDate);
		return pastDate;
	}


	public static void teardown() {
		
		driver.manage().deleteAllCookies();
		driver.close();
		driver.quit();
		driver=null;
	}
	

}
