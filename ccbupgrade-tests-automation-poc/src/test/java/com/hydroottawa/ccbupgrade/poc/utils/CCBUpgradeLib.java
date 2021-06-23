package com.hydroottawa.ccbupgrade.poc.utils;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.cucumber.listener.Reporter;
import com.hydroottawa.ccbupgrade.poc.page.ref.POC_PageRef;
import com.hydroottawa.ccbupgrade.poc.stepdefs.POC_Step_Definition;
import com.itextpdf.text.log.SysoCounter;
import com.itextpdf.text.log.SysoLogger;



public class CCBUpgradeLib implements POC_PageRef{

	/**
	 * Function to select item from Search By option
	 * 
	 * @param searchitem
	 * @param searchpage
	 * @throws InterruptedException
	 */
    public static String MR_STAGE_UP_ID = null;
    public static String ADJUSTMENT_ID = null;
    public static String ACCOUNT_ID = null;
    public static String INSTALLMENT_AMOUNT =null;
    public static String parent_window_id = null;
    public static String PREVIOUS_METER_READ_ID =null;   
    public static String CURRENT_METER_READ_ID =null;
    public static String METER_CONFIGURATION_ID=null;
    public static String SERVICE_POINT_ID=null;
    public static String PREVIOUS_Meter_Read_Date=null;
    public static String SCHEDULED_TRIGERRED_DATE=null;
    public static String DB_TESTDATA=null;
    public static String Collection_Process_ID=null; 
    public static ArrayList<Float> REGISTERREADS = new ArrayList<Float>();
    
	public static void CC_SearchBySelection(String searchitem,String searchlabel,String searchpage) throws InterruptedException

	{
		// CommonFunctions.driver.switchTo().frame("tabPage");
		WebElement searchBY=null;
		WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,50);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='multiQueryZoneFilters1']")));
		//WebElement searchBY = CommonFunctions.driver.findElement(By.xpath("//*[@id='multiQueryZoneFilters1']"));
		if (searchlabel.equalsIgnoreCase("Search BY")) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(listSearchBy));
		searchBY = CommonFunctions.driver.findElement(listSearchBy);
		}
		else if(searchlabel.equalsIgnoreCase("CIS DIVISION")) {
		// WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,50);
		wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//select[@id='cisDivision1']")));
		searchBY = CommonFunctions.driver.findElement(By.xpath("//select[@id='cisDivision1']"));
		}
		switch (searchpage) {
		case "Control Central":
			Select items = new Select(searchBY);
			items.selectByVisibleText(searchitem);
			break;
		default:
			System.out.println("Nothing is selected under" + searchpage);
		}
		//Thread.sleep(2000);
	}

	public static void selectDropDown_ftabpage(String labelvalue, String labelname)
	{
		if(labelname.equalsIgnoreCase("CIS DIVISION")) {
		WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='cisDivision1']")));
		WebElement cisdivision = CommonFunctions.driver.findElement(By.xpath("//select[@id='cisDivision1']"));
		Select items = new Select(cisdivision);
		System.out.println(labelvalue);
		items.selectByVisibleText(labelvalue);
		}
		else if(labelname.equalsIgnoreCase("TO DO TYPE FILTER"))
		{
			CommonFunctions.driver.switchTo().frame("tabPage");
			WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,50);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='TD_TYPE_FILTER_FLG']")));
			WebElement cisdivision = CommonFunctions.driver.findElement(By.xpath("//select[@id='TD_TYPE_FILTER_FLG']"));
			Select items = new Select(cisdivision);
			System.out.println(labelvalue);
			items.selectByVisibleText(labelvalue);
			CommonFunctions.driver.switchTo().parentFrame(); // Switching tabpage--> Main frame
		}
				
	}
	
	public static void CC_SearchOperation(String searchValue, String searchpage) throws IOException, InterruptedException {

		System.out.println("The value of serach is " + searchValue);
		WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,50);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='accountId1']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(inputAccountID));
		//Thread.sleep(5000);
		JavascriptExecutor jse= (JavascriptExecutor)CommonFunctions.driver;
		jse.executeScript("document.getElementById('accountId1').value='"+searchValue+"';");
		//CommonFunctions.driver.findElement(By.xpath("//*[@id='accountId1']")).click();
		//CommonFunctions.driver.findElement(By.xpath("//*[@id='accountId1']")).sendKeys(searchValue);
		wait.until(ExpectedConditions.visibilityOfElementLocated(inputSearch));
		//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
		//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
		//Date : 14th June 2019
				CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
				POC_Step_Definition.scenario.embed(CommonFunctions.snapshot, "image/png");
		CommonFunctions.driver.findElement(inputSearch).click();
		Thread.sleep(3000);
		//boolean flag =CommonFunctions.driver.findElement(inputSearch).isEnabled();
		
		

	}

	/**
	 * This function has been written to navigate pages inside CC&B application
	 * @param pageName
	 * @param navigation
	 * @param row
	 * @throws InterruptedException
	 */
	
	public static void navigation_fmenu(String pageName, String navigation, int row) throws InterruptedException {
		parent_window_id= CommonFunctions.driver.getWindowHandle();
		WebDriverWait  wait = new WebDriverWait(CommonFunctions.driver,30);
		switch (row) {
		case 0:
			//CommonFunctions.driver.findElement(By.xpath("//*[text()='" + navigation + "']")).click();
			//String str= "//*[text()='%s']";
			 
			if(pageName.equalsIgnoreCase("Adjustment"))
			{
				//CommonFunctions.driver.switchTo().parentFrame();  // Switching to main frame from tabPage
				Thread.sleep(1000);
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(strNavigate,navigation))));
			CommonFunctions.driver.findElement(By.xpath(String.format(strNavigate,navigation))).click();
			Thread.sleep(1000);
			break;
		case 1:
			//CommonFunctions.driver.findElement(By.xpath("//*[text()='" + navigation + "']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(strNavigate,navigation))));
			CommonFunctions.driver.findElement(By.xpath(String.format(strNavigate,navigation))).click();
			Thread.sleep(1000);
			break;
		case 2:
			//CommonFunctions.driver.findElement(By.xpath("//*[text()='" + navigation + "']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(strNavigate,navigation))));
			CommonFunctions.driver.findElement(By.xpath(String.format(strNavigate,navigation))).click();
			Thread.sleep(1000);
			break;
		case 3:
			//CommonFunctions.driver.findElement(By.xpath("//*[text()='" + navigation + "']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(strNavigate,navigation))));
			CommonFunctions.driver.findElement(By.xpath(String.format(strNavigate,navigation))).click();
			//Thread.sleep(2000);
			break;
		default:
			System.out.println("Check feature file. Might be navigation path is not correctly mentioned");
			Assert.fail("Check feature file. "+navigation+" is not available in the application. ");

		}
	}

	/**
	 * This function is used to enter value in input fields for any specific page.
	 * @param page
	 * @param field_name
	 * @param field_value
	 * @throws InterruptedException
	 */
	public static void entervalues_ftabPage(String page, String field_name, String field_value) throws InterruptedException

	{
        // Page Name :: Adjustment
		if (page.equalsIgnoreCase("Adjustment")) {
			switch (field_name) {
			case "ADJUSTMENT TYPE": {
				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ADJ_TYPE_CD']")));
				CommonFunctions.driver.findElement(By.xpath("//input[@id='ADJ_TYPE_CD']")).sendKeys(field_value);
				CommonFunctions.snapshot = CommonFunctions.getSnapshot(CommonFunctions.driver, "screenshot");
				POC_Step_Definition.scenario.embed(CommonFunctions.snapshot, "image/png");
				CommonFunctions.driver.findElement(By.xpath("//input[@id='ADJ_TYPE_CD']")).sendKeys(Keys.TAB);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ADJ_TYPE_INFO']")));
				boolean flag = CommonFunctions.driver.findElement(By.xpath("//*[@id='ADJ_TYPE_INFO']")).isDisplayed();
				// boolean flag=
				// CommonFunctions.driver.findElement(By.xpath("//*[@id='ADJ_TYPE_INFO']")).isEnabled();
				// boolean flag=
				// CommonFunctions.driver.findElement(By.xpath("//*[@id='ADJ_TYPE_INFO']")).isSelected();
				// System.out.println("The value of flag is :: " +flag);
				// wait.until(ExpectedConditions.visibilityOfElementLocated(labelBatchDescription));
				// boolean flag=
				// CommonFunctions.driver.findElement(labelBatchDescription).isDisplayed();
				// if(flag == false)
				{
					// Thread.sleep(3000);
					// Alert x =CommonFunctions.driver.switchTo().alert();
					// Alert a = new Alert();
					// CommonFunctions.driver.switchTo().a
					// CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
					// POC_Step_Definition.scenario.embed(CommonFunctions.snapshot, "image/png");
				}
				Assert.assertEquals("Adjustment Type " + field_value + " is not available in Adjustment Type List ",
						true, flag);
				break;
			}
			case "AMOUNT": {
				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='BASE_AMT_WRK']")));
				CommonFunctions.driver.findElement(By.xpath("//input[@id='BASE_AMT_WRK']")).clear();
				CommonFunctions.driver.findElement(By.xpath("//input[@id='BASE_AMT_WRK']")).sendKeys(field_value);
				break;
			}
			default:
				Assert.fail("Required field " + field_name + " is not available in feature file");
			}
		} 
		// Page Name :: Batch Job Submission
		else if (page.equalsIgnoreCase("Batch Job Submission")) {
			switch (field_name) {
			case "BATCH CODE": {
				// CommonFunctions.driver.findElement(By.xpath("//input[@id='BATCH_CD']")).sendKeys(field_value);
				// CommonFunctions.driver.findElement(By.xpath("//input[@id='BATCH_CD']")).sendKeys(Keys.TAB);
				CommonFunctions.driver.findElement(inputBatchCode).sendKeys(field_value);
				CommonFunctions.driver.findElement(inputBatchCode).sendKeys(Keys.TAB);
				// Thread.sleep(2000);
				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='BATCH_DESCR']")));
				// boolean flag=
				// CommonFunctions.driver.findElement(By.xpath("//*[@id='BATCH_DESCR']")).isDisplayed();
				wait.until(ExpectedConditions.visibilityOfElementLocated(labelBatchDescription));
				boolean flag = CommonFunctions.driver.findElement(labelBatchDescription).isDisplayed();
				Assert.assertEquals("Batch Code description is missing under Batch Job Submission page ", true, flag);
				break;
			}
			case "THREAD COUNT": {
				CommonFunctions.driver.findElement(By.xpath("//input[@id='BATCH_THREAD_CNT']")).sendKeys(field_value);
				// CommonFunctions.driver.findElement(inputBatchCode).sendKeys(field_value);
				break;
			}
			case "BATCH BUSINESS DATE": {
				if (field_value.equalsIgnoreCase("%RUNTIMEVALUE%")) {
				//	CommonFunctions.driver.findElement(By.xpath("//input[@id='PROCESS_DT']")).clear();
				//	CommonFunctions.driver.findElement(By.xpath("//input[@id='PROCESS_DT']"))
				//			.sendKeys(SCHEDULED_TRIGERRED_DATE);
					
					CommonFunctions.driver.findElement(inputBatchBusinessDate).clear();
					CommonFunctions.driver.findElement(inputBatchBusinessDate)
							.sendKeys(SCHEDULED_TRIGERRED_DATE);
				}

				else {
					CommonFunctions.driver.findElement(By.xpath("//input[@id='PROCESS_DT']")).clear();
					CommonFunctions.driver.findElement(By.xpath("//input[@id='PROCESS_DT']")).sendKeys(field_value);
				}
				// CommonFunctions.driver.findElement(inputBatchCode).sendKeys(field_value);
				break;
			}
			case "Thread Pool Name": {
				CommonFunctions.driver.switchTo().frame("BJP"); // Switching tabPage--> BJP
				CommonFunctions.driver.findElement(By.xpath("//*[text()='DIST-THD-POOL']//following::input[1]"))
						.sendKeys(field_value);
				CommonFunctions.driver.switchTo().parentFrame(); // Switching BJP--> tabPage
				break;
			}
			case "Account ID": {
				CommonFunctions.driver.switchTo().frame("BJP"); // Switching tabPage--> BJP
				if (field_value.equalsIgnoreCase("%RUNTIMEVALUE%")) {
					
					CommonFunctions.driver.findElement(By.xpath("//*[text()='ACCTID']//following::input[1]")).clear();
					CommonFunctions.driver.findElement(By.xpath("//*[text()='ACCTID']//following::input[1]"))
					.sendKeys(ACCOUNT_ID);
				}

				else {
					CommonFunctions.driver.findElement(By.xpath("//*[text()='ACCTID']//following::input[1]")).clear();
					CommonFunctions.driver.findElement(By.xpath("//*[text()='ACCTID']//following::input[1]"))
							.sendKeys(field_value);
				}
				CommonFunctions.driver.switchTo().parentFrame(); // Switching BJP--> tabPage
				break;
			}
			case "METER READ ID": {
				CommonFunctions.driver.switchTo().frame("BJP"); // Switching tabPage--> BJP
				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 10);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='meterReadId']//following::input[1]")));
				WebElement obj = CommonFunctions.driver.findElement(By.xpath("//*[text()='meterReadId']//following::input[1]"));
				JavascriptExecutor je = (JavascriptExecutor)CommonFunctions.driver;
				je.executeScript("arguments[0].scrollIntoView(true);", obj);
				CommonFunctions.driver.findElement(By.xpath("//*[text()='meterReadId']//following::input[1]")).clear();
				if (field_value.equalsIgnoreCase("%RUNTIMEVALUE%")) {										
					CommonFunctions.driver.findElement(By.xpath("//*[text()='meterReadId']//following::input[1]"))
					.sendKeys(CURRENT_METER_READ_ID);
				}
				else {
					//CommonFunctions.driver.findElement(By.xpath("//*[text()='meterReadId']//following::input[1]")).clear();
					CommonFunctions.driver.findElement(By.xpath("//*[text()='meterReadId']//following::input[1]"))
							.sendKeys(field_value);
				}
				CommonFunctions.driver.switchTo().parentFrame(); // Switching BJP--> tabPage
				break;
			}
			case "SP ID": {
				CommonFunctions.driver.switchTo().frame("BJP"); // Switching tabPage--> BJP
				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 10);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='SP_ID']//following::input[1]")));
				WebElement obj = CommonFunctions.driver.findElement(By.xpath("//*[text()='SP_ID']//following::input[1]"));
				JavascriptExecutor je = (JavascriptExecutor)CommonFunctions.driver;
				je.executeScript("arguments[0].scrollIntoView(true);", obj);
				CommonFunctions.driver.findElement(By.xpath("//*[text()='SP_ID']//following::input[1]")).clear();
				if (field_value.equalsIgnoreCase("%RUNTIMEVALUE%")) {										
					CommonFunctions.driver.findElement(By.xpath("//*[text()='SP_ID']//following::input[1]"))
					.sendKeys(SERVICE_POINT_ID);
				}
				else {
					//CommonFunctions.driver.findElement(By.xpath("//*[text()='meterReadId']//following::input[1]")).clear();
					CommonFunctions.driver.findElement(By.xpath("//*[text()='SP_ID']//following::input[1]"))
							.sendKeys(field_value);
				}
				CommonFunctions.driver.switchTo().parentFrame(); // Switching BJP--> tabPage
				break;
			}
			default:
				Assert.fail("Required field " + field_name + " is not available in feature file");
			}
		}
		// Page Name :: MDMR BQ Request
		else if (page.equalsIgnoreCase("MDMR BQ Request")) {
			WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
			switch (field_name) {
			case "METER READ ID": {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='filter1.F1']")));
				if (field_value.equalsIgnoreCase("%RUNTIMEVALUE%"))
					CommonFunctions.driver.findElement(By.xpath("//input[@id='filter1.F1']")).sendKeys(CURRENT_METER_READ_ID);
				else
					CommonFunctions.driver.findElement(By.xpath("//input[@id='filter1.F1']")).sendKeys(field_value);
				break;
			}
			default:
				Assert.fail("Required field " + field_name + " is not available in feature file");
			}
		}
		// Page Name :: Meter/Item Search
		else if (page.equalsIgnoreCase("Meter/Item Search")) {
			WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
			switch (field_name) {
			case "Account ID": {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ACCT_NBR']")));
				if (field_value.equalsIgnoreCase("%RUNTIMEVALUE%"))
					CommonFunctions.driver.findElement(By.xpath("//input[@id='ACCT_NBR']")).sendKeys(ACCOUNT_ID);
				else if (field_value.equalsIgnoreCase("%DB_TESTDATA%")) {
					CommonFunctions.driver.findElement(By.xpath("//input[@id='ACCT_NBR']"))
							.sendKeys(CCBUpgradeLib.DB_TESTDATA);
				} else
					CommonFunctions.driver.findElement(By.xpath("//input[@id='ACCT_NBR']")).sendKeys(field_value);
				break;
			}
			default:
				Assert.fail("Required field " + field_name + " is not available in feature file");
			}
		}
		// Page Name :: MR Upload Staging
		else if (page.equalsIgnoreCase("MR Upload Staging") || page.equalsIgnoreCase("Meter Read") ) {
			switch (field_name) {
			case "BADGE NUMBER": {
				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='BADGE_NBR']")));
				// CommonFunctions.driver.findElement(By.xpath("//input[@id='BADGE_NBR']")).sendKeys(field_value);
				wait.until(ExpectedConditions.visibilityOfElementLocated(inputBatchNumber));
				CommonFunctions.driver.findElement(inputBatchNumber).sendKeys(field_value);
				break;
			}
			case "DATE": {
				System.out.println("The value of Date is :: " + field_value);
				// CommonFunctions.driver.findElement(By.xpath("//input[@id='READ_DTTM_FWDTTM_P1']")).clear();
				// CommonFunctions.driver.findElement(By.xpath("//input[@id='READ_DTTM_FWDTTM_P1']"))
				// .sendKeys(field_value);
				if (field_value.equalsIgnoreCase("%TODAY_DATE%")) {
					/*
					 * Date date = Calendar.getInstance().getTime(); DateFormat dateFormat = new
					 * SimpleDateFormat("yyyy-MM-dd"); String strDate = dateFormat.format(date);
					 */
					String currentdate = CommonFunctions.currentDate("yyyy-MM-dd");
					CommonFunctions.driver.findElement(inputReadDate).clear();
					CommonFunctions.driver.findElement(inputReadDate).sendKeys(currentdate);

				} 
				else if(field_value.equalsIgnoreCase("%FUTURE_DATE%"))
				{
					String futuredate = CommonFunctions.futureDate("yyyy-MM-dd",30);;
					CommonFunctions.driver.findElement(inputReadDate).clear();
					CommonFunctions.driver.findElement(inputReadDate).click();
					CommonFunctions.driver.findElement(inputReadDate).sendKeys(futuredate);
				}
				else {
				CommonFunctions.driver.findElement(inputReadDate).clear();
				CommonFunctions.driver.findElement(inputReadDate).sendKeys(field_value);
				}
				break;
			}
			case "TIME": {
				System.out.println("The value of Time is :: " + field_value);
				// CommonFunctions.driver.findElement(By.xpath("//input[@id='READ_DTTM_FWDTTM_P2']")).clear();
				// CommonFunctions.driver.findElement(By.xpath("//input[@id='READ_DTTM_FWDTTM_P2']")).click();
				// CommonFunctions.driver.findElement(By.xpath("//input[@id='READ_DTTM_FWDTTM_P2']"))
				// .sendKeys(field_value);
				if(field_value.equalsIgnoreCase("%TODAY_DATE%"))
				{
					String currentdate = CommonFunctions.currentDate("yyyy-MM-dd");
					CommonFunctions.driver.findElement(inputTime).clear();
					CommonFunctions.driver.findElement(inputTime).click();
					CommonFunctions.driver.findElement(inputTime).sendKeys(currentdate);
				}
				else if(field_value.equalsIgnoreCase("%FUTURE_DATE%"))
				{
					String futuredate = CommonFunctions.futureDate("yyyy-MM-dd",30);;
					CommonFunctions.driver.findElement(inputTime).clear();
					CommonFunctions.driver.findElement(inputTime).click();
					CommonFunctions.driver.findElement(inputTime).sendKeys(futuredate);
				}
				else
				{
				CommonFunctions.driver.findElement(inputTime).clear();
				CommonFunctions.driver.findElement(inputTime).click();
				CommonFunctions.driver.findElement(inputTime).sendKeys(field_value);
				}
				break;
			}
			case "MR SOURCE": {

				// WebElement MR_SOURCE=
				// CommonFunctions.driver.findElement(By.xpath("//Select[@id='MR_SOURCE_CD']"));
				WebElement MR_SOURCE = CommonFunctions.driver.findElement(selectMeterSource);
				Select S1 = new Select(MR_SOURCE);
				S1.selectByVisibleText(field_value);
				break;
			}
			default:
				Assert.fail("Required field " + field_name + " is not available in feature file");
			}
		}
		// Page Name :: Payment Arrangement
		else if (page.equalsIgnoreCase("Payment Arrangement")) {
			WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 30);
			switch (field_name) {
			case "INSTALLMENTS": {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='INSTALLMENT']")));
				CommonFunctions.driver.findElement(By.xpath("//input[@id='INSTALLMENT']")).sendKeys(field_value);
				CommonFunctions.driver.findElement(By.xpath("//input[@id='INSTALLMENT']")).sendKeys(Keys.TAB);
				// CommonFunctions.driver.findElement(inputBatchCode).sendKeys(field_value);
				// CommonFunctions.driver.findElement(inputBatchCode).sendKeys(Keys.TAB);
				// Thread.sleep(2000);
				INSTALLMENT_AMOUNT = CommonFunctions.driver.findElement(By.xpath("//input[@id='RCR_CHG_AMT']"))
						.getAttribute("value");
				System.out.println("The installment amount is :: " + INSTALLMENT_AMOUNT);
				POC_Step_Definition.scenario.write("The installment amount is :: " + INSTALLMENT_AMOUNT);
				break;
			}
			case "CIS DIVISION": {

				WebElement cis_div = CommonFunctions.driver.findElement(By.xpath("//select[@id='CIS_DIVISION']"));
				Select s_cis_div = new Select(cis_div);
				s_cis_div.selectByVisibleText(field_value);
				break;
			}
			case "SA TYPE": {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='SA_TYPE_CD']")));
				CommonFunctions.driver.findElement(By.xpath("//input[@id='SA_TYPE_CD']")).sendKeys(field_value);
				CommonFunctions.driver.findElement(By.xpath("//input[@id='SA_TYPE_CD']")).sendKeys(Keys.TAB);
				break;
			}
			default:
				Assert.fail("Required field " + field_name + " is not available in feature file");
			}
		} 
		// Page Name :: To Do Search
		else if (page.equalsIgnoreCase("To Do Search")) {
			switch (field_name) {
			case "TO DO TYPE": {
				CommonFunctions.driver.findElement(By.xpath("//input[@id='TD_TYPE_CD']")).sendKeys(field_value);
				CommonFunctions.driver.findElement(By.xpath("//input[@id='TD_TYPE_CD']")).sendKeys(Keys.TAB);
				// CommonFunctions.driver.findElement(inputBatchCode).sendKeys(field_value);
				// CommonFunctions.driver.findElement(inputBatchCode).sendKeys(Keys.TAB);
				// Thread.sleep(2000);
				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='TD_TYPE_DESCR']")));
				boolean flag = CommonFunctions.driver.findElement(By.xpath("//*[@id='TD_TYPE_DESCR']")).isDisplayed();
				// wait.until(ExpectedConditions.visibilityOfElementLocated(labelBatchDescription));
				// boolean flag=
				// CommonFunctions.driver.findElement(labelBatchDescription).isDisplayed();
				Assert.assertEquals("To Do Type description is missing under To Do Search page ", true, flag);
				break;
			}
			case "STATUS FILTER": {
				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 30);
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='TD_STAT_FILTER_FLG']")));
				WebElement status = CommonFunctions.driver.findElement(By.xpath("//select[@id='TD_STAT_FILTER_FLG']"));
				JavascriptExecutor je = (JavascriptExecutor) CommonFunctions.driver;
				je.executeScript("arguments[0].scrollIntoView(true);", status);
				Select s = new Select(status);
				s.selectByVisibleText("Open");
				break;
			}
			default:
				Assert.fail("Required field " + field_name + " is not available in feature file");
			}
		}
	}

/**
 * 
 * @param colheader
 * @return
 */
	
	public static int MeterReadHeader(String colheader)

{
	//int colcount= CommonFunctions.driver.findElements(By.xpath("//table[@id='headerTable']/thead//th")).size();
	int tableHeaderIndex, HeaderNameIndex = 0;
	List<WebElement> HeaderValues = CommonFunctions.driver.findElements(By.xpath("//table[@id='headerTable']/thead//th"));
	for (tableHeaderIndex = 0; tableHeaderIndex < HeaderValues.size(); tableHeaderIndex++) 
	{
		String strHeader = (HeaderValues.get(tableHeaderIndex).getText()).trim();
		if (strHeader.equalsIgnoreCase(colheader)) 
		{
			System.out.println("The value of strHeader " + strHeader);
			break;
		}
	}

	HeaderNameIndex = tableHeaderIndex + 1;
	System.out.println("The "+colheader+" is located at column number " + HeaderNameIndex);
	return HeaderNameIndex;
}
	
	
	
	public static void MeterRead(String sequence, String read_type, String unit_of_measure, String time_of_use,
			String register_reading, int row) {
		int headercol_S = MeterReadHeader("SEQUENCE");
		// CommonFunctions.driver
		// .findElement(By.xpath("//table[@id='dataTable']//tbody/tr[" + row + "]/td[" +
		// headercol_S + "]//input"))
		// .sendKeys(sequence);
		// CommonFunctions.driver
		// .findElement(By.xpath(String.format("//table[@id='dataTable']//tbody/tr[%s]/td[%s]//input",row,headercol_S))).sendKeys(sequence);

		CommonFunctions.driver.findElement(By.xpath(String.format(tblStringSequence, row, headercol_S))).sendKeys(sequence);
		

		int headercol_RT = MeterReadHeader("READ TYPE");
		// Select s1 = new Select(CommonFunctions.driver.findElement(
		// By.xpath("//table[@id='dataTable']//tbody/tr[" + row + "]/td[" + headercol_RT
		// + "]//Select")));
		Select s1 = new Select(
				CommonFunctions.driver.findElement(By.xpath(String.format(tblSelectdataframe, row, headercol_RT))));
		s1.selectByVisibleText(read_type);
		int headercol_UOM = MeterReadHeader("UNIT OF MEASURE");
		// Select s2 = new Select(CommonFunctions.driver.findElement(
		// By.xpath("//table[@id='dataTable']//tbody/tr[" + row + "]/td[" +
		// headercol_UOM + "]//Select")));
		Select s2 = new Select(
				CommonFunctions.driver.findElement(By.xpath(String.format(tblSelectdataframe, row, headercol_UOM))));
		s2.selectByVisibleText(unit_of_measure);
		int headercol_TOU = MeterReadHeader("TIME OF USE");
		// Select s3 = new Select(CommonFunctions.driver.findElement(
		// By.xpath("//table[@id='dataTable']//tbody/tr[" + row + "]/td[" +
		// headercol_TOU + "]//Select")));
		Select s3 = new Select(
				CommonFunctions.driver.findElement(By.xpath(String.format(tblSelectdataframe, row, headercol_TOU))));
		s3.selectByVisibleText(time_of_use);
		int headercol_RR = MeterReadHeader("REGISTER READING");
		// CommonFunctions.driver
		// By.xpath("//table[@id='dataTable']//tbody/tr[" + row + "]/td[" + headercol_RR
		// + "]//input"))
		// .sendKeys(register_reading);
		CommonFunctions.driver.findElement(By.xpath(String.format(tblStringRegisterReading, row, headercol_RR)))
				.sendKeys(register_reading);

	}

	public static void clickButton_fmenu(String button, String page) throws InterruptedException {
		CommonFunctions.driver.findElement(By.xpath("//input[@name='" + button + "']")).click();
		
		Thread.sleep(4000);
		if (page.equalsIgnoreCase("MR Upload Staging")) {
			CommonFunctions.driver.switchTo().frame("tabPage");  // Switch to tabPage frame from menu frame.
			//MR_STAGE_UP_ID = CommonFunctions.driver.findElement(By.xpath("//input[@id='MR_STAGE_UP_ID']")).getAttribute("value");
			MR_STAGE_UP_ID = CommonFunctions.driver.findElement(inputMRUploadStagingID).getAttribute("value");
			System.out.println("The value of MR Upload Starging ID is :: " + MR_STAGE_UP_ID);
			CommonFunctions.driver.switchTo().parentFrame();  // Switch to tabPage frame from menu frame.
		}
		
	}
	
	public static void selectValue_ftabPage(String menu, String option ,String page) throws InterruptedException {
		
		if(page.equalsIgnoreCase("Batch Job Submission"))
		{
			CommonFunctions.driver.findElement(By.xpath("//img[@id='IM_Main_batCtx']")).click();
			Thread.sleep(3000);
			CommonFunctions.driver.switchTo().parentFrame(); // Switching tabPage frame to Main frame.
			String parent = CommonFunctions.driver.getWindowHandle();
			System.out.println("Parent window is :: "+parent);
			CommonFunctions.driver.findElement(By.xpath("//*[text()='"+option+"']")).click();
			Thread.sleep(3000);
			Set<String> windows= CommonFunctions.driver.getWindowHandles();
			for(String s:windows) {
				if(s.equalsIgnoreCase(parent))
				{
					System.out.println("I am in parent window now");
					
				}
				else
				{
					System.out.println("I am in child window now");
					CommonFunctions.driver.switchTo().window(s);
					Thread.sleep(2000);
					//CommonFunctions.driver.close();
					//CommonFunctions.driver.switchTo().window(parent);
					//System.out.println(" I am in parent wondow again");
					
				}
			}
		}
		
		
	}
	
	public static void verifyBatchJobStatus_ftabPage(String label, String value, String page)
			throws InterruptedException {
		switch (page) {
		case "Batch Job Submission": {

			String batch_job_status = null;
			int batch_job_status_counter =0;
			//batch_job_status = CommonFunctions.driver.findElement(By.xpath("//*[@id='BATCH_JOB_STAT_FLG']")).getText();
			batch_job_status = CommonFunctions.driver.findElement(labelBatchJobStatusFlag).getText();
			System.out.println("The initial batch job status is :: " + batch_job_status);
			while (!batch_job_status.equalsIgnoreCase(value)) {
				//WebElement obj = CommonFunctions.driver.findElement(By.xpath("//*[@id='BATCH_JOB_STAT_FLG']"));
				WebElement obj = CommonFunctions.driver.findElement(labelBatchJobStatusFlag);
				JavascriptExecutor je = (JavascriptExecutor)CommonFunctions.driver;
				je.executeScript("arguments[0].scrollIntoView(true);", obj);
				//batch_job_status = CommonFunctions.driver.findElement(By.xpath("//*[@id='BATCH_JOB_STAT_FLG']"))
					//	.getText();
				batch_job_status = CommonFunctions.driver.findElement(labelBatchJobStatusFlag).getText();
				Thread.sleep(2000);
				System.out.println("The value of batch job status is :: " + batch_job_status);
				CommonFunctions.driver.switchTo().parentFrame(); // Moving to main frame from tabPage frame.
				//CommonFunctions.driver.findElement(By.xpath("//input[@id='IM_REFRESH']")).click();
				CommonFunctions.driver.findElement(btnRefresh).click();
				Thread.sleep(5000);
				CommonFunctions.driver.switchTo().frame("tabPage"); // Moving to tabPage frame from main frame.
				batch_job_status_counter++;
				if(batch_job_status_counter > 21)
				{
					Assert.fail("Please check and ensure TPW(Thread pull worker) is up and running");
				}
				
			}
			CommonFunctions.driver.switchTo().parentFrame(); // Moving to main frame from tabPage frame.
			break;
		}
		
		case "Meter Read":

		{
			WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,25);
			if (label.equalsIgnoreCase("METER CONFIGURATION ID")) {
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='MTR_CONFIG_ID']")));
				wait.until(ExpectedConditions.visibilityOfElementLocated(inputMRConfigurationID));
				//String meter_config_id = CommonFunctions.driver.findElement(By.xpath("//input[@id='MTR_CONFIG_ID']"))
				//		.getAttribute("value");
				METER_CONFIGURATION_ID = CommonFunctions.driver.findElement(inputMRConfigurationID).getAttribute("value");
				System.out.println("The value of Meter Configuration ID is :: " + METER_CONFIGURATION_ID);
				Assert.assertNotNull("METER CONFIGURATION ID is blank", METER_CONFIGURATION_ID);
				CommonFunctions.driver.switchTo().parentFrame(); // Moving to main frame from tabPage frame.
				//Reporter.addStepLog("The value of METER CONFIGURATION ID is :: "+meter_config_id);
				POC_Step_Definition.scenario.write("The value of METER CONFIGURATION ID is :: "+METER_CONFIGURATION_ID);
			}
			else if (label.equalsIgnoreCase("METER READ ID")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(txtMRReadID));
				//String CURRENT_METER_READ_ID = CommonFunctions.driver.findElement(txtMRReadID).getText();
				CURRENT_METER_READ_ID = CommonFunctions.driver.findElement(txtMRReadID).getAttribute("value");
				System.out.println("The value of Current Meter Read ID is :: " + CURRENT_METER_READ_ID);
				Assert.assertNotNull("Current METER READ Date is blank", CURRENT_METER_READ_ID);
				System.out.println("Meter Read ID generated successfully. ");
				CommonFunctions.driver.switchTo().parentFrame(); // Moving to main frame from tabPage frame.
				//Reporter.addStepLog("The value of METER READ ID is :: "+meter_read_id);
				POC_Step_Definition.scenario.write("The value of METER READ ID is :: "+CURRENT_METER_READ_ID);
			}
			else if (label.equalsIgnoreCase("READ DATE")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='READ_DTTM_FWDTTM_P1']")));
				PREVIOUS_Meter_Read_Date = CommonFunctions.driver.findElement(By.xpath("//input[@id='READ_DTTM_FWDTTM_P1']")).getAttribute("value");
				//wait.until(ExpectedConditions.visibilityOfElementLocated(txtMRReadID));
				//String meter_read_id = CommonFunctions.driver.findElement(txtMRReadID).getText();
				System.out.println("The value of previous METER READ Date is :: " + PREVIOUS_Meter_Read_Date);
				Assert.assertNotNull("Previous METER READ Date is blank", PREVIOUS_Meter_Read_Date);
				CommonFunctions.driver.switchTo().parentFrame(); // Moving to main frame from tabPage frame.
				//Reporter.addStepLog("The value of METER READ ID is :: "+meter_read_id);
				POC_Step_Definition.scenario.write("The value of Previous METER READ Date is :: "+PREVIOUS_Meter_Read_Date);
			}
			else if (label.equalsIgnoreCase("REGISTER READING")) {
				CommonFunctions.driver.switchTo().frame("dataframe");  // Switching tabPage-->dataframe
				int column= headerMapping("REGISTER READING");
				int previous_meter_read_rowcount=CommonFunctions.driver.findElements(By.xpath("//table[@summary='Register Reads']/tbody/tr")).size();
				if(previous_meter_read_rowcount<=0) {
					Assert.fail("Register reading information is missing for account ID :: "+ACCOUNT_ID+"");
				}
				for(int i=1;i<=previous_meter_read_rowcount;i++)
				{
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='dataTable']/tbody/tr")));
					String register_reading_str =CommonFunctions.driver.findElement(By
						.xpath(String.format("//table[@id='dataTable']/tbody/tr[%s]/td[%s]/input", i, column))).getAttribute("value");
					System.out.println("The value of Meter Read in row "+i+" is "+register_reading_str);
					float  register_reading_float= Float.parseFloat(register_reading_str);
					REGISTERREADS.add(register_reading_float);
					}
				System.out.println("Register Reading values of previous METER READ ID "+PREVIOUS_METER_READ_ID+" is :: "+REGISTERREADS);
				CommonFunctions.driver.switchTo().parentFrame(); // Moving to dataframe from tabPage frame.
				CommonFunctions.driver.switchTo().parentFrame(); // Moving to tabPage from main frame.
				POC_Step_Definition.scenario.write("Register Reading values of previous METER READ ID "+PREVIOUS_METER_READ_ID+" is :: "+REGISTERREADS);
			}
			break;
		}
		case "MR Upload Staging":

		{
			WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,25);
			if (label.equalsIgnoreCase("METER CONFIGURATION ID")) {
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='MTR_CONFIG_ID']")));
				wait.until(ExpectedConditions.visibilityOfElementLocated(inputMRConfigurationID));
				//String meter_config_id = CommonFunctions.driver.findElement(By.xpath("//input[@id='MTR_CONFIG_ID']"))
				//		.getAttribute("value");
				String meter_config_id = CommonFunctions.driver.findElement(inputMRConfigurationID).getAttribute("value");
				System.out.println("The value of meter configuration id is :: " + meter_config_id);
				Assert.assertNotNull("MUP1 validation failed", meter_config_id);
				System.out.println("MUP1 validation is done successfully. ");
				CommonFunctions.driver.switchTo().parentFrame(); // Moving to main frame from tabPage frame.
				//Reporter.addStepLog("The value of METER CONFIGURATION ID is :: "+meter_config_id);
				POC_Step_Definition.scenario.write("The value of METER CONFIGURATION ID is :: "+meter_config_id);
			}

			else if (label.equalsIgnoreCase("METER READ ID")) {
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='MR_ID']")));
				//String meter_read_id = CommonFunctions.driver.findElement(By.xpath("//*[@id='MR_ID']")).getText();
				wait.until(ExpectedConditions.visibilityOfElementLocated(txtMRReadID));
				String meter_read_id = CommonFunctions.driver.findElement(txtMRReadID).getText();
				System.out.println("The value of meter read id is :: " + meter_read_id);
				Assert.assertNotNull("MUP2 validation failed", meter_read_id);
				System.out.println("MUP2 validation is done successfully. ");
				CommonFunctions.driver.switchTo().parentFrame(); // Moving to main frame from tabPage frame.
				//Reporter.addStepLog("The value of METER READ ID is :: "+meter_read_id);
				POC_Step_Definition.scenario.write("The value of METER READ ID is :: "+meter_read_id);
			}
			break;
		}
		case "Account":
		{
			if (label.equalsIgnoreCase("AUTO PAY SOURCE CODE")) {
				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,50);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ACCT_APAY$APAY_SRC_CD']")));
				String auto_pay_source_code = CommonFunctions.driver.findElement(By.xpath("//input[@id='ACCT_APAY$APAY_SRC_CD']")).getAttribute("value");
				System.out.println("The value of "+label+" is :: " + auto_pay_source_code);							
				Assert.assertEquals("AUTO PAY SOURCE CODE is not blank","",auto_pay_source_code );
				System.out.println("AUTO PAY SOURCE CODE is blank as intended");
				CommonFunctions.driver.switchTo().parentFrame(); // Moving to main frame from tabPage frame.
				
			}
				break;
		}
		case "Bill":
		{
			if (label.equalsIgnoreCase("BILL STATUS")) {
				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,50);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='BILL_STATUS']")));
				String bill_status = CommonFunctions.driver.findElement(By.xpath("//*[@id='BILL_STATUS']")).getText();
				System.out.println("The value of "+label+" is :: " + bill_status);
				Assert.assertEquals("Bill generation failed.","Complete",bill_status );
				System.out.println("Bill generation is completed successfully. ");
				CommonFunctions.driver.switchTo().parentFrame(); // Moving to main frame from tabPage frame.
				
			}
				break;
		}
		case "Bill Segment":
		{
			if (label.equalsIgnoreCase("BILL SEGMENT STATUS")) {
				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,50);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='BSEG_STAT_DESC']")));
				String bill_setment_status = CommonFunctions.driver.findElement(By.xpath("//*[@id='BSEG_STAT_DESC']")).getText();
				System.out.println("The value of "+label+" is :: " + bill_setment_status);
				Assert.assertEquals("The Status of Bill Segment is not displaying as Error","Error",bill_setment_status );
				CommonFunctions.driver.switchTo().parentFrame(); // Moving to main frame from tabPage frame.
				
			}
				break;
		}
		case "Adjustment": {
			if (label.equalsIgnoreCase("Adjustment ID")) {
				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ADJ_ID']")));
				ADJUSTMENT_ID = CommonFunctions.driver.findElement(By.xpath("//input[@id='ADJ_ID']"))
						.getAttribute("value");
				System.out.println("The value of " + label + " is :: " + ADJUSTMENT_ID);
				Assert.assertNotNull("Adjustment ID creation failed", ADJUSTMENT_ID);
				System.out.println("Adjustment ID creation is completed successfully. ");
				POC_Step_Definition.scenario.write("The value of Adjustment ID is :: "+ADJUSTMENT_ID);
				//CommonFunctions.driver.switchTo().parentFrame(); // Moving to main frame from tabPage frame.

			}
			else if (label.equalsIgnoreCase("SA ID")) {
				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='SA_ID']")));
				String SA_ID = CommonFunctions.driver.findElement(By.xpath("//input[@id='SA_ID']"))
						.getAttribute("value");
				System.out.println("The value of " + label + " is :: " + SA_ID);
				Assert.assertNotNull("Active SA ID is missing. Try with Account ID which is having active SA associated with account", SA_ID);
				POC_Step_Definition.scenario.write("The value of active SA ID is :: "+SA_ID);
				CommonFunctions.driver.switchTo().parentFrame(); // Moving to main frame from tabPage frame.

			}
			break;
		}
		case "Collection Process": {
			if (label.equalsIgnoreCase("Collection STATUS")) {
				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='SA_INFO']")));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='COLL_STATUS_FLG']")));
				WebElement collection_status= CommonFunctions.driver.findElement(By.xpath("//select[@id='COLL_STATUS_FLG']"));
				Select s = new Select(collection_status);
				String acc_collection_status= s.getFirstSelectedOption().getText();
				System.out.println("Collection status is :: "+acc_collection_status);
				Assert.assertEquals("Collection status did not match", value,acc_collection_status);
				ACCOUNT_ID=CommonFunctions.driver.findElement(By.xpath("//input[@id='ACCT_ID']")).getAttribute("value");
				System.out.println("The value of Account ID is :: "+ACCOUNT_ID);
				
		    POC_Step_Definition.scenario.write("The value of Account ID is :: "+ACCOUNT_ID);	
			CommonFunctions.driver.switchTo().parentFrame(); // Moving to main frame from tabPage frame.

			}
			break;
		}
		case "Payment Arrangement": {
			if (label.equalsIgnoreCase("SA ID")) {
				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='PA_SA_ID']")));
				String actual_SA_ID = CommonFunctions.driver.findElement(By.xpath("//input[@id='PA_SA_ID']"))
						.getAttribute("value");
				System.out.println("The value of " + label + " is :: " + actual_SA_ID);
				Assert.assertNotNull("SA ID creation failed", actual_SA_ID);
				CommonFunctions.driver.switchTo().parentFrame(); // Moving to main frame from tabPage frame.

			}
			break;
		}
		case "Payment Event": {
			if (label.equalsIgnoreCase("PAYMENT EVENT ID")) {
				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='PAY_EVENT_ID']")));
				String actual_PAYMENT_EVENT_ID = CommonFunctions.driver.findElement(By.xpath("//input[@id='PAY_EVENT_ID']"))
						.getAttribute("value");
				System.out.println("The value of " + label + " is :: " + actual_PAYMENT_EVENT_ID);
				Assert.assertNotNull("PAYMENT EVENT ID creation failed", actual_PAYMENT_EVENT_ID);
				CommonFunctions.driver.switchTo().parentFrame(); // Moving to main frame from tabPage frame.

			}
			break;
		}
		case "Service Agreement": {
			if (label.equalsIgnoreCase("SA STATUS")) {
				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
				String status_info = CommonFunctions.driver.findElement(By.xpath("//*[@id='SA_INFO']")).getText();
				if (status_info.contains("Active")) {
					System.out.println(" SA activated successfully");
					POC_Step_Definition.scenario.write("SA activated successfully");
				} else {
					Assert.fail("Unable to activate SA");
				}

				CommonFunctions.driver.switchTo().parentFrame(); // Moving to main frame from tabPage frame.

			}
			else if (label.equalsIgnoreCase("SERVICE POINT ID")) {
				//CommonFunctions.driver.switchTo().frame("tabPage");  // main frame ---> tabPage frame
				//CommonFunctions.driver.switchTo().frame("dataframe1"); // Switching tabPage frame ---> dataframe1 frame
				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='SA_SP$SP_ID']")));
				String acc_SP_ID = CommonFunctions.driver.findElement(By.xpath("//input[@id='SA_SP$SP_ID']")).getAttribute("value");
				System.out.println("The value of " + label + " is :: " + acc_SP_ID);
				Assert.assertNotNull("SP ID is null", acc_SP_ID);
				SERVICE_POINT_ID=acc_SP_ID;
				//Assert.assertNotNull("Sort Keys creation failed", sortkeys);
				System.out.println("The value of SP ID is :: "+SERVICE_POINT_ID);
				POC_Step_Definition.scenario.write("The value of SP ID is :: "+acc_SP_ID);				
				CommonFunctions.driver.switchTo().parentFrame();// Moving tabPage frame to main frame.
			}
			break;
		}
		case "To Do Entry": {
			if (label.equalsIgnoreCase("TO DO ID")) {
				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='TD_ENTRY_ID']")));
				String TODOID = CommonFunctions.driver.findElement(By.xpath("//input[@id='TD_ENTRY_ID']"))
						.getAttribute("value");
				System.out.println("The value of " + label + " is :: " + TODOID);
				Assert.assertNotNull("TO DO ID creation failed", TODOID);
				System.out.println("TO DO ID creation is completed successfully. ");
				POC_Step_Definition.scenario.write("The value of TO DO ID is :: "+TODOID);
				CommonFunctions.driver.switchTo().parentFrame(); // Moving to main frame from tabPage frame.

			}
			else if (label.equalsIgnoreCase("SORT KEYS")) {
				//CommonFunctions.driver.switchTo().frame("tabPage");  // main frame ---> tabPage frame
				CommonFunctions.driver.switchTo().frame("dataframe1"); // Switching tabPage frame ---> dataframe1 frame
				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='TD_ENTRY_SRTKY:0$KEY_VALUE']")));
				String sortkeys = CommonFunctions.driver.findElement(By.xpath("//*[@id='TD_ENTRY_SRTKY:0$KEY_VALUE']")).getText();
				System.out.println("The value of " + label + " is :: " + sortkeys);
				Assert.assertEquals("Sort Keys value does not match with Adjustment Amount", value, sortkeys);
				//Assert.assertNotNull("Sort Keys creation failed", sortkeys);
				System.out.println("Sort Keys creation is completed successfully. ");
				POC_Step_Definition.scenario.write("The value of SORT KEYS is :: "+sortkeys);
				CommonFunctions.driver.switchTo().parentFrame(); // Moving data-dataframe1 frame to tabPage frame.
				CommonFunctions.driver.switchTo().parentFrame();// Moving tabPage frame to main frame.
			}
			else if (label.equalsIgnoreCase("DRILL KEYS")) {
				
				CommonFunctions.driver.switchTo().frame("dataframe");  // Switching tabPage frame ---> dataframe frame
				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='TD_ENTRY_DRLKY:0$KEY_VALUE']")));
				String drillkeys = CommonFunctions.driver.findElement(By.xpath("//*[@id='TD_ENTRY_DRLKY:0$KEY_VALUE']")).getText();
				System.out.println("The value of " + label + " is :: " + drillkeys);
				Assert.assertNotNull("DRILL KEYS is not created ", drillkeys);
				//Assert.assertEquals("Drill Keys value does not match with Adjustment ID", ADJUSTMENT_ID, drillkeys);
				//Assert.assertNotNull("Drill Keys creation failed", drillkeys);
				System.out.println("Drill Keys creation is completed successfully. ");
				POC_Step_Definition.scenario.write("The value of Drill Keys is :: "+drillkeys);
				CommonFunctions.driver.switchTo().parentFrame(); // Moving tabpage frame to dataframe frame.
				CommonFunctions.driver.switchTo().parentFrame();// Moving main frame to tabpage frame.

			}
            else if (label.equalsIgnoreCase("STATUS")) {				
				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ENTRY_STATUS_FLG']")));
				String todostatus = CommonFunctions.driver.findElement(By.xpath("//*[@id='ENTRY_STATUS_FLG']")).getText();
				System.out.println("The value of " + label + " is :: " + todostatus);
				Assert.assertEquals("To Do status did not change from Open to Being Worked On", "Being Worked On", todostatus);
				System.out.println("TO DO is assigned to the user and status change from open to being worked on");
				POC_Step_Definition.scenario.write("The current status of TO DO is :: "+todostatus);
				CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
				POC_Step_Definition.scenario.embed(CommonFunctions.snapshot, "image/png");
				//CommonFunctions.driver.switchTo().parentFrame(); // Moving to main frame from tabPage frame.
				WebElement obj = CommonFunctions.driver.findElement(By.xpath("//label[text()='Log']"));
				JavascriptExecutor je = (JavascriptExecutor)CommonFunctions.driver;
				je.executeScript("arguments[0].scrollIntoView(true);", obj);

			}
			break;
		}
		default:
			Assert.fail("Case does not match. Check again");

		}
	}
	
	public static void batchvalidation_SearchStagingID_newwindow(String value,String button) throws InterruptedException, IOException
	{
		System.out.println("Parent window ID is :: "+parent_window_id);
		Set<String> windows= CommonFunctions.driver.getWindowHandles();
		for(String s:windows) {
			if(s.equalsIgnoreCase(parent_window_id))
			{
				System.out.println("I am in parent window now");
				
			}
			else
			{
				if(value.equalsIgnoreCase("METER CONFIGURATION ID")) {
				System.out.println("I am in child window now");
				CommonFunctions.driver.switchTo().window(s);
				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,50);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='MR_STAGE_UP_ID']")));
				CommonFunctions.driver.findElement(By.xpath("//input[@id='MR_STAGE_UP_ID']")).sendKeys(MR_STAGE_UP_ID);
				Thread.sleep(2000);
				//Reporter.addStepLog("The value of MR UPLOAD STAGING ID is :: "+ MR_STAGE_UP_ID);
				POC_Step_Definition.scenario.write("The value of MR UPLOAD STAGING ID is :: "+MR_STAGE_UP_ID);
				//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
				//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
				//Date : 14th June 2019
				CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
				POC_Step_Definition.scenario.embed(CommonFunctions.snapshot, "image/png");
				CommonFunctions.driver.findElement(By.xpath("//input[@id='BU_srchKey_srchBtn']")).click();
				CommonFunctions.driver.switchTo().window(parent_window_id);
				Thread.sleep(1000);
				CommonFunctions.driver.switchTo().frame("main");
				//Thread.sleep(4000);
				}
				else if(value.equalsIgnoreCase("ADJUSTMENT ID")) {
					
					System.out.println("I am in child window now");
					CommonFunctions.driver.switchTo().window(s);
					WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,50);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ADJ_ID']")));
					CommonFunctions.driver.findElement(By.xpath("//input[@id='ADJ_ID']")).sendKeys(ADJUSTMENT_ID);
					Thread.sleep(2000);
					//Reporter.addStepLog("The value of MR UPLOAD STAGING ID is :: "+ MR_STAGE_UP_ID);
					POC_Step_Definition.scenario.write("The value of Adjustment ID is :: "+ADJUSTMENT_ID);
					//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
					//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
					//Date : 14th June 2019
					CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
					POC_Step_Definition.scenario.embed(CommonFunctions.snapshot, "image/png");
					CommonFunctions.driver.findElement(By.xpath("//input[@id='BU_Main_mainSrch']")).click();
					CommonFunctions.driver.switchTo().window(parent_window_id);
					Thread.sleep(1000);
					CommonFunctions.driver.switchTo().frame("main");
					
				}
				
			}
		}
	}
// Generate Bill###################################
	
	public static void clickSingleButton_fdashboard(String button, String page) throws InterruptedException {
		// User must be in dashboard frame to use this function.							
		if (page.equalsIgnoreCase("Current Context")) {
			switch (button) {
			case "Show Account Context": {

				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 20);
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//*[@id='oraTableContextContainer']//img[@title='Show Account Context']")));
				CommonFunctions.driver
						.findElement(By.xpath("//*[@id='oraTableContextContainer']//img[@title='" + button + "']"))
						.click();
				System.out.println(" Button " + button + " has been clicked");
				break;

			}
			case "Show Premise Context Menu": {

				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 20);
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//img[@title='Show Premise Context Menu']")));
				CommonFunctions.driver
						.findElement(By.xpath("//img[@title='" + button + "']"))
						.click();
				System.out.println(" Button " + button + " has been clicked");
				break;

			}
			case "Go To Account": {

				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 20);
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//*[@id='oraTableContextContainer']//img[@title='Go To Account']")));
				CommonFunctions.driver
						.findElement(By.xpath("//*[@id='oraTableContextContainer']//img[@title='" + button + "']"))
						.click();
				System.out.println(" Button " + button + " has been clicked");
				break;

			}
			
			}
		}
		
		else if(page.equalsIgnoreCase("Alerts"))
		{
			switch(button)
			{
			case "Auto-Pay Active":
				WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 30);
				try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//a[text()='Auto-Pay Active']")));}
				catch(TimeoutException e)
				{
					System.out.println(e.getMessage());
					Assert.fail("Auto-Pay Active is not visible");
				}
				CommonFunctions.driver
						.findElement(By.xpath("//a[text()='Auto-Pay Active']"))
						.click();
				System.out.println(" Button " + button + " has been clicked");
				break;
			}
		}
	}
    
	public static void clickSingleButton_fmain(String btnname, String pagename) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='"+btnname+"']")));
		CommonFunctions.driver.findElement(By.xpath("//input[@name='"+btnname+"']")).click();
		
		//CommonFunctions.driver.findElement(By.xpath("//input[@name='"+btnname+"']")).cl;
		System.out.println(" Button " + btnname + " has been clicked");
		Thread.sleep(10000);
		
		/*
		 * if(wait.until(ExpectedConditions.alertIsPresent())!=null){ Alert alert =
		 * CommonFunctions.driver.switchTo().alert(); String alertMessage =
		 * CommonFunctions.driver.switchTo().alert().getText(); // Displaying alert
		 * message System.out.println("Alert Message: "+alertMessage); alert.accept();
		 * System.out.println("Alert accepted"); Thread.sleep(3000); } else {
		 * System.out.println("Alert not present"); }
		 */ 
		
		
		try
				{
			
			
			wait.until(ExpectedConditions.alertIsPresent());					
			Alert alert = CommonFunctions.driver.switchTo().alert();
			String alertMessage = CommonFunctions.driver.switchTo().alert().getText();
			// Displaying alert message
			System.out.println("Alert Message: "+alertMessage);
			alert.accept();
			System.out.println("Alert accepted");
			Thread.sleep(3000);
				}
		catch(TimeoutException e)
		{
			System.out.println("Alert not present");
		}
	}
	
	public static void clickSingleButton_fmenu(String tabname, String pageName) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,30);
		switch(tabname)
		{
		case "Approval":			
			CommonFunctions.driver.switchTo().parentFrame(); // Switching to main frame from tabPage frame.
			CommonFunctions.driver.switchTo().frame("tabMenu");  // Switching menu to tabMenu
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Approval']")));
			CommonFunctions.driver.findElement(By.xpath("//*[text()='Approval']")).click();
			CommonFunctions.driver.switchTo().parentFrame(); // Switching tabMenu to menu
			break;
           case "Auto Pay":					
			CommonFunctions.driver.switchTo().frame("tabMenu");  // Switching menu to tabMenu
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Auto Pay']")));
			CommonFunctions.driver.findElement(By.xpath("//*[text()='Auto Pay']")).click();
			CommonFunctions.driver.switchTo().parentFrame(); // Switching tabMenu to menu
			break;
           case "Rate Info":					
   			CommonFunctions.driver.switchTo().frame("tabMenu");  // Switching menu to tabMenu
   			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Rate Info']")));
   			CommonFunctions.driver.findElement(By.xpath("//*[text()='Rate Info']")).click();
   			CommonFunctions.driver.switchTo().parentFrame(); // Switching tabMenu to menu
   			break;
           case "Interval Info":					
      			CommonFunctions.driver.switchTo().frame("tabMenu");  // Switching menu to tabMenu
      			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Interval Info']")));
      			CommonFunctions.driver.findElement(By.xpath("//*[text()='Interval Info']")).click();
      			CommonFunctions.driver.switchTo().parentFrame(); // Switching tabMenu to menu
      			break;
           case "Budget":			
   			CommonFunctions.driver.switchTo().frame("tabMenu");  // Switching menu to tabMenu
   			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Budget']")));
   			CommonFunctions.driver.findElement(By.xpath("//*[text()='Budget']")).click();
   			Thread.sleep(2000);
   			CommonFunctions.driver.switchTo().parentFrame(); // Switching tabMenu to menu
   			break;
           case "Characteristics":			
   			CommonFunctions.driver.switchTo().frame("tabMenu");  // Switching menu to tabMenu
   			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Characteristics']")));
   			CommonFunctions.driver.findElement(By.xpath("//*[text()='Characteristics']")).click();
   			Thread.sleep(2000);
   			CommonFunctions.driver.switchTo().parentFrame(); // Switching tabMenu to menu
   			break;
           case "SA/SP":			
      			CommonFunctions.driver.switchTo().frame("tabMenu");  // Switching menu to tabMenu
      			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='SA/SP']")));
      			CommonFunctions.driver.findElement(By.xpath("//*[text()='SA/SP']")).click();
      			Thread.sleep(2000);
      			CommonFunctions.driver.switchTo().parentFrame(); // Switching tabMenu to menu
      			break;
		case "Keys/Values":			
			CommonFunctions.driver.switchTo().frame("tabMenu");  // Switching menu to tabMenu
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Keys/Values']")));
			CommonFunctions.driver.findElement(By.xpath("//*[text()='Keys/Values']")).click();
			Thread.sleep(2000);
			CommonFunctions.driver.switchTo().parentFrame(); // Switching tabMenu to menu
			break;
		case "Main":			
			CommonFunctions.driver.switchTo().frame("tabMenu");  // Switching menu to tabMenu
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Main']")));
			CommonFunctions.driver.findElement(By.xpath("//*[text()='Main']")).click();
			Thread.sleep(2000);
			CommonFunctions.driver.switchTo().parentFrame(); // Switching tabMenu to menu
			break;
		
		default:
			Assert.fail(" Tab " + tabname + " has not been found");
		
		}
	}
	
	public static void clickSingleButton_fzoneMap(String tagname,String tag,String zone,String pagename) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,30);
		switch(tagname){
		
		case "TO DO":			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Adjustment')]")));
			CommonFunctions.driver.findElement(By.xpath("//a[contains(text(),'Adjustment')]")).click();
			break;
		case "Approve":	
			if(zone.equalsIgnoreCase("Approval Request Level 1"))
			{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Approve']")));
			boolean flag= CommonFunctions.driver.findElement(By.xpath("//input[@value='Approve']")).isEnabled();
			Assert.assertEquals("Unable to approve Adjustment Level 1 request as "+tagname+" button is disable", true, flag);
			CommonFunctions.driver.findElement(By.xpath("//input[@value='Approve']")).click();
			Thread.sleep(5000);
			}
			if(zone.equalsIgnoreCase("Approval Request Level 2"))
			{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Approve']")));
			boolean flag= CommonFunctions.driver.findElement(By.xpath("//input[@value='Approve']")).isEnabled();
			Assert.assertEquals("Unable to approve Adjustment Level 2 request as "+tagname+" button is disable", true, flag);
			CommonFunctions.driver.findElement(By.xpath("//input[@value='Approve']")).click();
			Thread.sleep(5000);
			}
			if(zone.equalsIgnoreCase("Approval Request Level 3"))
			{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Approve']")));
			boolean flag= CommonFunctions.driver.findElement(By.xpath("//input[@value='Approve']")).isEnabled();
			Assert.assertEquals("Unable to approve Adjustment Level 3 request as "+tagname+" button is disable", true, flag);
			CommonFunctions.driver.findElement(By.xpath("//input[@value='Approve']")).click();
			Thread.sleep(5000);
		
			}
			break;
		case "Reject":			
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Reject']")));
			//CommonFunctions.driver.findElement(By.xpath("//input[@value='Reject']")).click();
			if(zone.equalsIgnoreCase("Reject Request Level 1"))
			{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Reject']")));
			boolean flag= CommonFunctions.driver.findElement(By.xpath("//input[@value='Reject']")).isEnabled();
			Assert.assertEquals("Unable to reject Adjustment Level 1 request as "+tagname+" button is disable", true, flag);
			CommonFunctions.driver.findElement(By.xpath("//input[@value='Reject']")).click();
			Thread.sleep(5000);
			}
			if(zone.equalsIgnoreCase("Reject Request Level 2"))
			{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Reject']")));
			boolean flag= CommonFunctions.driver.findElement(By.xpath("//input[@value='Reject']")).isEnabled();
			Assert.assertEquals("Unable to reject Adjustment Level 2 request as "+tagname+" button is disable", true, flag);
			CommonFunctions.driver.findElement(By.xpath("//input[@value='Reject']")).click();
			Thread.sleep(5000);
			}
			if(zone.equalsIgnoreCase("Reject Request Level 3"))
			{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Reject']")));
			boolean flag= CommonFunctions.driver.findElement(By.xpath("//input[@value='Reject']")).isEnabled();
			Assert.assertEquals("Unable to reject Adjustment Level 3 request as "+tagname+" button is disable", true, flag);
			CommonFunctions.driver.findElement(By.xpath("//input[@value='Reject']")).click();
			Thread.sleep(5000);
		
			}
			break;
		default:
			Assert.fail(" "+tag+"" + tagname + " has not been found");
			
		}
		
		
	}
	
	public static void clickSingleButton_ftabPage(String button, String page) throws InterruptedException {
		// User must be in tabPage frame to use this function.
		WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
		System.out.println("The value of button is :: "+page);
		System.out.println("The value of button is :: "+button);
		switch (button) {
		
		case "Activate SA": {
			CommonFunctions.driver.switchTo().frame("tabPage");  // Switching main to tabPage
			//WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ACTIVATE_SW']")));
			CommonFunctions.driver.findElement(By.xpath("//input[@id='ACTIVATE_SW']")).click();
			System.out.println(" Button " + button + " has been clicked");
			CommonFunctions.driver.switchTo().parentFrame();  // Switching tabPage to menu			
			break;
		}
		case "Meter Read Context Menu - Meter Read ID": {
			
			CommonFunctions.driver.switchTo().frame("tabPage");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@title='Meter Read Context Menu - Meter Read ID']")));
			CommonFunctions.driver.findElement(By.xpath("//img[@title='Meter Read Context Menu - Meter Read ID']")).click();
			System.out.println(" Button " + button + " has been clicked");
			CommonFunctions.driver.switchTo().parentFrame();  // Switching tabPage to menu
			break;	
		} 
       case "Show Premise Context Menu - Mailing Premise ID": {
			
			//CommonFunctions.driver.switchTo().frame("tabPage");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@title='Show Premise Context Menu - Mailing Premise ID']")));
			CommonFunctions.driver.findElement(By.xpath("//img[@title='Show Premise Context Menu - Mailing Premise ID']")).click();
			System.out.println(" Button " + button + " has been clicked");
			CommonFunctions.driver.switchTo().parentFrame();  // Switching tabPage to menu
			break;	
		}
		
		case "Generate": {
			parent_window_id = CommonFunctions.driver.getWindowHandle();
			//WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Generate']")));
			//CommonFunctions.driver.findElement(By.xpath("//input[@value='Generate']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(btnGenerate));
			WebElement obj = CommonFunctions.driver.findElement(btnGenerate);
			JavascriptExecutor je = (JavascriptExecutor) CommonFunctions.driver;
			je.executeScript("arguments[0].scrollIntoView(true);", obj);
			Thread.sleep(3000);
			//je.executeScript("arguments[0].click();", obj);
			CommonFunctions.driver.findElement(btnGenerate).click();
			CommonFunctions.driver.findElement(btnGenerate).sendKeys(Keys.ENTER);
			Thread.sleep(3000);
			System.out.println(" Button " + button + " has been clicked");
			break;
		}
		case "Freeze/Complete": {
			System.out.println("I am in Freeze/Complete section");
			//WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ACTION_FRZ_CMPL_SW']")));
			//Boolean flag = CommonFunctions.driver.findElement(By.xpath("//input[@id='ACTION_FRZ_CMPL_SW']"))
			//		.isEnabled();
			//WebElement obj = CommonFunctions.driver.findElement(By.xpath("//*[@id='ACTION_FRZ_CMPL_SW']"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(btnFreezeComplete));
			Boolean flag = CommonFunctions.driver.findElement(btnFreezeComplete).isEnabled();
			WebElement obj = CommonFunctions.driver.findElement(btnFreezeComplete);
			JavascriptExecutor je = (JavascriptExecutor) CommonFunctions.driver;
			je.executeScript("arguments[0].scrollIntoView(true);", obj);
			Assert.assertEquals("Button Freeze/Complete is disable. Something wrong with bill status ", true, flag);
			Thread.sleep(2000);
			//CommonFunctions.driver.findElement(By.xpath("//input[@id='ACTION_FRZ_CMPL_SW']")).click();
			CommonFunctions.driver.findElement(btnFreezeComplete).click();
			System.out.println(" Button " + button + " has been clicked");
			//CommonFunctions.driver.switchTo().parentFrame();  // Switching tabPage to menu
			break;
		}
		case "Freeze": {
			System.out.println("I am in Freeze/Complete section");
			//WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ACTION_FRZ_CMPL_SW']")));
			//Boolean flag = CommonFunctions.driver.findElement(By.xpath("//input[@id='ACTION_FRZ_CMPL_SW']"))
			//		.isEnabled();
			//WebElement obj = CommonFunctions.driver.findElement(By.xpath("//*[@id='ACTION_FRZ_CMPL_SW']"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(btnFreeze));
			Boolean flag = CommonFunctions.driver.findElement(btnFreeze).isEnabled();
			WebElement obj = CommonFunctions.driver.findElement(btnFreeze);
			JavascriptExecutor je = (JavascriptExecutor) CommonFunctions.driver;
			je.executeScript("arguments[0].scrollIntoView(true);", obj);
			Assert.assertEquals("Button Freeze is disable. Something wrong with bill status ", true, flag);
			Thread.sleep(2000);
			//CommonFunctions.driver.findElement(By.xpath("//input[@id='ACTION_FRZ_CMPL_SW']")).click();
			CommonFunctions.driver.findElement(btnFreeze).click();
			System.out.println(" Button " + button + " has been clicked");
			CommonFunctions.driver.switchTo().parentFrame();  // Switching tabPage to menu
			break;
		}
		
		case "Complete": {
			System.out.println("I am in Complete section");
			//WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ACTION_FRZ_CMPL_SW']")));
			//Boolean flag = CommonFunctions.driver.findElement(By.xpath("//input[@id='ACTION_FRZ_CMPL_SW']"))
			//		.isEnabled();
			//WebElement obj = CommonFunctions.driver.findElement(By.xpath("//*[@id='ACTION_FRZ_CMPL_SW']"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(btnbillComplete));
			Boolean flag = CommonFunctions.driver.findElement(btnbillComplete).isEnabled();
			WebElement obj = CommonFunctions.driver.findElement(btnbillComplete);
			JavascriptExecutor je = (JavascriptExecutor) CommonFunctions.driver;
			je.executeScript("arguments[0].scrollIntoView(true);", obj);
			Assert.assertEquals("Button Complete is disable. Something wrong with bill status ", true, flag);
			Thread.sleep(2000);
			//CommonFunctions.driver.findElement(By.xpath("//input[@id='ACTION_FRZ_CMPL_SW']")).click();
			CommonFunctions.driver.findElement(btnbillComplete).click();
			System.out.println(" Button " + button + " has been clicked");
			CommonFunctions.driver.switchTo().parentFrame();  // Switching tabPage to menu
			break;
		}
		
		case "Show Account Context - Account ID": {
			//parent_window_id = CommonFunctions.driver.getWindowHandle();
			//WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Generate']")));
			//CommonFunctions.driver.findElement(By.xpath("//input[@value='Generate']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@id='IM_Main_acctCntxt']")));
			CommonFunctions.driver.findElement(By.xpath("//img[@id='IM_Main_acctCntxt']")).click();
			System.out.println(" Button " + button + " has been clicked");
			CommonFunctions.driver.switchTo().parentFrame();  // Switching tabPage to menu
			break;
		}
		case "Show Account Context - SA ID": {
			CommonFunctions.driver.switchTo().frame("tabPage");  // Switching main to tabPage
			//WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Generate']")));
			//CommonFunctions.driver.findElement(By.xpath("//input[@value='Generate']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@id='IM_Main_saCnxt']")));
			CommonFunctions.driver.findElement(By.xpath("//img[@id='IM_Main_saCnxt']")).click();
			System.out.println(" Button " + button + " has been clicked");
			CommonFunctions.driver.switchTo().parentFrame();  // Switching tabPage to menu
			break;
		}
		case "Submit for Approval": {
			//WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='SUBMIT_APPR_SW']")));
			CommonFunctions.driver.findElement(By.xpath("//input[@id='SUBMIT_APPR_SW']")).click();
			System.out.println(" Button " + button + " has been clicked");
			break;
		}
		case "Expand zone - Approval Request Log":
		{
			CommonFunctions.driver.switchTo().frame("tabPage");  // Switching main to tabPage
			//WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
			boolean flag=true;
			try {			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@title='Collapse zone - Approval Request Log']")));
			}
			catch(TimeoutException e)
			{
				flag=false;
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@title='Expand zone - Approval Request Log']")));
			}
			if(flag == false)
			{
			CommonFunctions.driver.findElement(By.xpath("//img[@title='Expand zone - Approval Request Log']")).click();
			System.out.println("Button " + button + " has been clicked");
			}
			
			Thread.sleep(3000);
			WebElement approval_log=CommonFunctions.driver.findElement(By.xpath("//table[@summary='Approval Request Log']/tbody/tr[1]"));
			JavascriptExecutor je = (JavascriptExecutor)CommonFunctions.driver;
			je.executeScript("arguments[0].scrollIntoView(true);", approval_log);
			break;	
		}
		case "Search": {
			//WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Search']")));
			CommonFunctions.driver.findElement(By.xpath("//input[@value='Search']")).click();
			System.out.println("Button " + button + " has been clicked");
			break;
		}
		case "Search - ACCOUNT ID": {
			//WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='BU_section3_search']")));
			CommonFunctions.driver.findElement(By.xpath("//input[@id='BU_section3_search']")).click();
			System.out.println("Button " + button + " has been clicked");
			break;
		}
		case "Forward": {
			CommonFunctions.driver.switchTo().frame("tabPage");  // Switching main to tabPage
			//WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 50);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Forward']")));
			CommonFunctions.driver.findElement(By.xpath("//input[@value='Forward']")).click();
			System.out.println("Button " + button + " has been clicked");
			break;
		}
		case "Create Pay Arrangement": {
			//WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='CREATE_SW']")));
			CommonFunctions.driver.findElement(By.xpath("//input[@id='CREATE_SW']")).click();
			System.out.println("Button " + button + " has been clicked");
			CommonFunctions.driver.switchTo().parentFrame(); // Switching tabPage -->main
			break;
		}
		case "Meter Configuration Context Menu": {
			CommonFunctions.driver.switchTo().frame("tabPage");  // Switching main to tabPage
			//WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@id='IM_Data_mcIdBtn']")));
			CommonFunctions.driver.findElement(By.xpath("//img[@id='IM_Data_mcIdBtn']")).click();
			System.out.println("Button " + button + " has been clicked");
			CommonFunctions.driver.switchTo().parentFrame(); // Switching tabPage -->main
			break;
		}
		case "BROADCAST": {
			//CommonFunctions.driver.switchTo().frame("tabPage");  // Switching main to tabPage
			//WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='dataExplorerTable1']/tbody/tr[1]/td[3]//img")));
			CommonFunctions.driver.findElement(By.xpath("//table[@id='dataExplorerTable1']/tbody/tr[1]/td[3]//img")).click();
			System.out.println("Button " + button + " has been clicked");
			Thread.sleep(7000);
			CommonFunctions.driver.switchTo().parentFrame(); // Switching tabPage -->main
			break;
		}
		case "Recommend Budget": {
			//CommonFunctions.driver.switchTo().frame("tabPage");  // Switching main to tabPage
			//WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='RECOMMEND_SW']")));
			CommonFunctions.driver.findElement(By.xpath("//input[@id='RECOMMEND_SW']")).click();
			System.out.println("Button " + button + " has been clicked");
			Thread.sleep(10000);
			CommonFunctions.driver.switchTo().parentFrame(); // Switching tabPage -->main
			break;
		}
		
		default:
			Assert.fail(" Button " + button + " has not been found");
		}
	}
	
	public static void billgeneration_calculation(String label,String label_value,String button ) throws InterruptedException, IOException
	{
		System.out.println("The "+label+" value is ::"+label_value);
		System.out.println("Parent window ID is :: "+parent_window_id);
		Set<String> windows= CommonFunctions.driver.getWindowHandles();
		System.out.println("Windows IDs are :: "+windows);
		for(String s:windows) {
			if(s.equalsIgnoreCase(parent_window_id))
			{
				System.out.println("I am in parent window now");
				
			}
			else
			{
				System.out.println("I am in child window now");
				CommonFunctions.driver.switchTo().window(s);
				if(label.equalsIgnoreCase("ACCOUNTING DATE")) {
					WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,25);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ACCOUNTING_DT']")));
					CommonFunctions.driver.findElement(By.xpath("//input[@id='ACCOUNTING_DT']")).clear();
					CommonFunctions.driver.findElement(By.xpath("//input[@id='ACCOUNTING_DT']")).click();
					
					if (label_value.equalsIgnoreCase("%TODAY_DATE%")) {
						String currentdate = CommonFunctions.currentDate("yyyy-MM-dd");
						CommonFunctions.driver.findElement(By.xpath("//input[@id='ACCOUNTING_DT']")).sendKeys(currentdate);

					} else {
						CommonFunctions.driver.findElement(By.xpath("//input[@id='ACCOUNTING_DT']")).sendKeys(label_value);
					}
					Thread.sleep(2000);
					//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
					//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
					//Date : 14th June 2019
					CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
					POC_Step_Definition.scenario.embed(CommonFunctions.snapshot, "image/png");
					CommonFunctions.driver.findElement(By.xpath("//input[@value='Calculate']")).click();
					//CommonFunctions.driver.findElement(btnComplete).click();
				}
				else if(label.equalsIgnoreCase("Account ID")) {
					// Enter Value in Collection Process ID input field in newly opened windows				
					WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,25);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ACCT_ID']")));
					CommonFunctions.driver.findElement(By.xpath("//input[@id='ACCT_ID']")).clear();
					CommonFunctions.driver.findElement(By.xpath("//input[@id='ACCT_ID']")).click();					
					if (label_value.equalsIgnoreCase("%TODAY_DATE%")) {
						String currentdate = CommonFunctions.currentDate("yyyy-MM-dd");
						CommonFunctions.driver.findElement(By.xpath("//input[@id='ACCT_ID']")).sendKeys(currentdate);

					} else if (label_value.equalsIgnoreCase("%DB_TESTDATA%")) {

						CommonFunctions.driver.findElement(By.xpath("//input[@id='ACCT_ID']"))
								.sendKeys(CCBUpgradeLib.DB_TESTDATA);

					}

					else {
						CommonFunctions.driver.findElement(By.xpath("//input[@id='ACCT_ID']")).sendKeys(label_value);
					}
					Thread.sleep(2000);
					//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
					//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
					//Date : 14th June 2019
					CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
					POC_Step_Definition.scenario.embed(CommonFunctions.snapshot, "image/png");
					CommonFunctions.driver.findElement(By.xpath("//input[@id='BU_Main_search']")).click();
					}
				else if(label.equalsIgnoreCase("BILL DATE")) {
					WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,25);
					//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='OBC_BILL_DT']")));
					//CommonFunctions.driver.findElement(By.xpath("//input[@id='OBC_BILL_DT']")).clear();
					//CommonFunctions.driver.findElement(By.xpath("//input[@id='OBC_BILL_DT']")).click();
					//CommonFunctions.driver.findElement(By.xpath("//input[@id='OBC_BILL_DT']")).sendKeys(date);
					wait.until(ExpectedConditions.visibilityOfElementLocated(inputBillDate));
					CommonFunctions.driver.findElement(inputBillDate).clear();
					CommonFunctions.driver.findElement(inputBillDate).click();
					if (label_value.equalsIgnoreCase("%TODAY_DATE%")) {
						/*
						 * Date date = Calendar.getInstance().getTime(); DateFormat dateFormat = new
						 * SimpleDateFormat("yyyy-MM-dd"); String strDate = dateFormat.format(date);
						 */
						String currentdate = CommonFunctions.currentDate("yyyy-MM-dd");
						CommonFunctions.driver.findElement(inputBillDate).sendKeys(currentdate);

					} else {
						CommonFunctions.driver.findElement(inputBillDate).sendKeys(label_value);
					}
					Thread.sleep(2000);
					//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
					//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
					//Date : 14th June 2019
					CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
					POC_Step_Definition.scenario.embed(CommonFunctions.snapshot, "image/png");
					//CommonFunctions.driver.findElement(By.xpath("//input[@id='ACTION_FRZ_CMPL_SW']")).click();
					CommonFunctions.driver.findElement(btnComplete).click();
				}
				else if(label.equalsIgnoreCase("BILL SEGMENT ID")) {
					WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,25);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='BSEG_ID']")));
					CommonFunctions.driver.findElement(By.xpath("//input[@id='BSEG_ID']")).clear();
					CommonFunctions.driver.findElement(By.xpath("//input[@id='BSEG_ID']")).click();
					CommonFunctions.driver.findElement(By.xpath("//input[@id='BSEG_ID']")).sendKeys(label_value);
					//wait.until(ExpectedConditions.visibilityOfElementLocated(inputCutoffDate));
					//CommonFunctions.driver.findElement(inputCutoffDate).clear();
					//CommonFunctions.driver.findElement(inputCutoffDate).click();
					//CommonFunctions.driver.findElement(inputCutoffDate).sendKeys(date);
					Thread.sleep(2000);
					//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
					//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
					//Date : 14th June 2019
					CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
					POC_Step_Definition.scenario.embed(CommonFunctions.snapshot, "image/png");
					CommonFunctions.driver.findElement(By.xpath("//input[@id='BU_BSEG_ID']")).click();
					}
				
				else if(label.equalsIgnoreCase("METER READ ID")) {
					// Enter Value in Collection Process ID input field in newly opened windows				
					WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,25);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='MR_ID']")));
					CommonFunctions.driver.findElement(By.xpath("//input[@id='MR_ID']")).clear();
					CommonFunctions.driver.findElement(By.xpath("//input[@id='MR_ID']")).click();
					//CommonFunctions.driver.findElement(By.xpath("//input[@id='COLL_PROC_ID']")).sendKeys(label_value);
					//CommonFunctions.driver.findElement(By.xpath("//input[@id='COLL_PROC_ID']")).sendKeys(Collection_Process_ID);
					if (label_value.equalsIgnoreCase("%RUNTIMEVALUE%")) {
						CommonFunctions.driver.findElement(By.xpath("//input[@id='MR_ID']")).sendKeys(CURRENT_METER_READ_ID);
					}
					else {
					CommonFunctions.driver.findElement(By.xpath("//input[@id='MR_ID']")).sendKeys(label_value);
					}
					
					Thread.sleep(2000);
					//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
					//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
					//Date : 14th June 2019
					CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
					POC_Step_Definition.scenario.embed(CommonFunctions.snapshot, "image/png");
					CommonFunctions.driver.findElement(btnCollectionsearch).click();
					} 
				
				else if(label.equalsIgnoreCase("Collection Process ID")) {
					// Enter Value in Collection Process ID input field in newly opened windows				
					WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,25);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='COLL_PROC_ID']")));
					CommonFunctions.driver.findElement(By.xpath("//input[@id='COLL_PROC_ID']")).clear();
					CommonFunctions.driver.findElement(By.xpath("//input[@id='COLL_PROC_ID']")).click();
					if (label_value.equalsIgnoreCase("%RUNTIMEVALUE%")) {
						CommonFunctions.driver.findElement(By.xpath("//input[@id='COLL_PROC_ID']")).sendKeys(Collection_Process_ID);
					}
					else {
					CommonFunctions.driver.findElement(By.xpath("//input[@id='COLL_PROC_ID']")).sendKeys(label_value);
					}
					//wait.until(ExpectedConditions.visibilityOfElementLocated(inputCutoffDate));
					//CommonFunctions.driver.findElement(inputCutoffDate).clear();
					//CommonFunctions.driver.findElement(inputCutoffDate).click();
					//CommonFunctions.driver.findElement(inputCutoffDate).sendKeys(date);
					Thread.sleep(2000);
					//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
					//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
					//Date : 14th June 2019
					CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
					POC_Step_Definition.scenario.embed(CommonFunctions.snapshot, "image/png");
					CommonFunctions.driver.findElement(By.xpath("//input[@id='BU_Main_search']")).click();
					}
				else if(label.equalsIgnoreCase("CUTOFF DATE")) {
					WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,25);
					//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='CUTOFF_DT']")));
					//CommonFunctions.driver.findElement(By.xpath("//input[@id='CUTOFF_DT']")).clear();
					//CommonFunctions.driver.findElement(By.xpath("//input[@id='CUTOFF_DT']")).click();
					//CommonFunctions.driver.findElement(By.xpath("//input[@id='CUTOFF_DT']")).sendKeys(date);
					wait.until(ExpectedConditions.visibilityOfElementLocated(inputCutoffDate));
					CommonFunctions.driver.findElement(inputCutoffDate).clear();
					CommonFunctions.driver.findElement(inputCutoffDate).click();
					if (label_value.equalsIgnoreCase("%TODAY_DATE%")) {
//						Date date = Calendar.getInstance().getTime();
//						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//						String strDate = dateFormat.format(date);
						String currentdate = CommonFunctions.currentDate("yyyy-MM-dd");
						CommonFunctions.driver.findElement(inputCutoffDate).sendKeys(currentdate);

					} else {
					CommonFunctions.driver.findElement(inputCutoffDate).sendKeys(label_value);
					}
					Thread.sleep(2000);
					//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
					//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
					//Date : 14th June 2019
					CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
					POC_Step_Definition.scenario.embed(CommonFunctions.snapshot, "image/png");
					CommonFunctions.driver.findElement(By.xpath("//input[@title='"+button+"']")).click();
					}
				else if(label.equalsIgnoreCase("REJECT REASON")) {
					WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,25);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='rejectReason']")));
					WebElement obj =CommonFunctions.driver.findElement(By.xpath("//select[@id='rejectReason']"));
					Select rr = new Select(obj);
					rr.selectByVisibleText("Incorrect Adjustment");
					Thread.sleep(2000);
					//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
					//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
					//Date : 14th June 2019
					CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
					POC_Step_Definition.scenario.embed(CommonFunctions.snapshot, "image/png");
					WebElement obj_OK = CommonFunctions.driver.findElement(By.xpath("//input[@id='OK']"));
					JavascriptExecutor je = (JavascriptExecutor)CommonFunctions.driver;
					je.executeScript("arguments[0].scrollIntoView(true);", obj_OK);
					CommonFunctions.driver.findElement(By.xpath("//input[@id='OK']")).click();
					Thread.sleep(3000);
					
				}
				else if(label.equalsIgnoreCase("USER")) {
					WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,25);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='FWD_ASSIGNED_TO']")));
					CommonFunctions.driver.findElement(By.xpath("//input[@id='FWD_ASSIGNED_TO']")).clear();
					CommonFunctions.driver.findElement(By.xpath("//input[@id='FWD_ASSIGNED_TO']")).sendKeys(label_value);
					CommonFunctions.driver.findElement(By.xpath("//input[@id='FWD_ASSIGNED_TO']")).sendKeys(Keys.TAB);
					CommonFunctions.driver.findElement(By.xpath("//textarea[@id='LOG_DETAILS']")).sendKeys("Assign TO DO to user");
					CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
					POC_Step_Definition.scenario.embed(CommonFunctions.snapshot, "image/png");
					WebElement obj_OK = CommonFunctions.driver.findElement(By.xpath("//input[@id='OK_BTTN']"));
					JavascriptExecutor je = (JavascriptExecutor)CommonFunctions.driver;
					je.executeScript("arguments[0].scrollIntoView(true);", obj_OK);
					CommonFunctions.driver.findElement(By.xpath("//input[@id='OK_BTTN']")).click();
					Thread.sleep(3000);
					
				}

				
				CommonFunctions.driver.switchTo().window(parent_window_id);
				Thread.sleep(3000);
				try {
				CommonFunctions.driver.switchTo().frame("main");
				//CommonFunctions.driver.switchTo().fram
				}
				catch (Exception e) {
					System.out.println(e.toString());
					
					Assert.fail("An account may have one and only one Bill that is in "
							+ "currently in pending status.A Bill in pending status already exists for this account. "
							+ "Hence,another bill may not be added");
				}
				//Thread.sleep(4000);
				
				
			}
		}
	}

//#########################################################################################################
	/**
	 * 
	 * @param header
	 * @param tablename
	 * @throws InterruptedException
	 * @throws IOException
	 */
	
	public static void dataselection_newlyopenedwindow(String header, String tablename)
			throws InterruptedException, IOException {
		System.out.println("The " + header + " value is ::" + header);
		System.out.println("Parent window ID is :: " + parent_window_id);
		Set<String> windows = CommonFunctions.driver.getWindowHandles();
		for (String s : windows) {
			if (s.equalsIgnoreCase(parent_window_id)) {
				System.out.println("I am in parent window now");

			} else {
				System.out.println("I am in child window now");
				CommonFunctions.driver.switchTo().window(s);
				if (tablename.equalsIgnoreCase("Meter Reading")) {
					CommonFunctions.driver.switchTo().frame("dataframe");
					int column = headerMapping("METER READ ID");
					CommonFunctions.snapshot = CommonFunctions.getSnapshot(CommonFunctions.driver, "screenshot");
					POC_Step_Definition.scenario.embed(CommonFunctions.snapshot, "image/png");
					int previous_meter_read_rowcount=CommonFunctions.driver.findElements(By.xpath("//table[@id='dataTable']/tbody/tr")).size();
					if(previous_meter_read_rowcount<=0) {
						Assert.fail("Previous Meter Read information is missing for account ID :: "+ACCOUNT_ID+"");
					}
					PREVIOUS_METER_READ_ID=CommonFunctions.driver.findElement(By
							.xpath(String.format("//table[@id='dataTable']/tbody/tr[%s]/td[%s]", 1, column))).getText();
					System.out.println("Previous Meter ID for the account "+ACCOUNT_ID+" is :: "+ PREVIOUS_METER_READ_ID);
					POC_Step_Definition.scenario.write("Previous Meter ID for the account "+ACCOUNT_ID+" is :: "+ PREVIOUS_METER_READ_ID);
					CommonFunctions.driver.findElement(By
									.xpath(String.format("//table[@id='dataTable']/tbody/tr[%s]/td[%s]", 1, column))).click();
				}
				else if (tablename.equalsIgnoreCase("Bill Search")) {
					CommonFunctions.driver.switchTo().frame("dataframe");
					int column = headerMapping("Bill Status");
					CommonFunctions.snapshot = CommonFunctions.getSnapshot(CommonFunctions.driver, "screenshot");
					POC_Step_Definition.scenario.embed(CommonFunctions.snapshot, "image/png");
					int previous_meter_read_rowcount=CommonFunctions.driver.findElements(By.xpath("//table[@id='dataTable']/tbody/tr")).size();
					if(previous_meter_read_rowcount<=0) {
						Assert.fail("Previous Bill information is missing for account ID :: "+ACCOUNT_ID+"");
					}
					String acc_bill_status=CommonFunctions.driver.findElement(By
							.xpath(String.format("//table[@id='dataTable']/tbody/tr[%s]/td[%s]", 1, column))).getText();
					System.out.println("Bill status in first row is  :: "+ acc_bill_status);
					CommonFunctions.driver.findElement(By
									.xpath(String.format("//table[@id='dataTable']/tbody/tr[%s]/td[%s]", 1, column))).click();
				}
				else if (tablename.equalsIgnoreCase("Payment Arrangement")) {
					CommonFunctions.driver.switchTo().frame("dataframe");
					int column = headerMapping("SA Status");
					int row;
					CommonFunctions.snapshot = CommonFunctions.getSnapshot(CommonFunctions.driver, "screenshot");
					POC_Step_Definition.scenario.embed(CommonFunctions.snapshot, "image/png");
					int Payment_Arrangement_rowcount = CommonFunctions.driver
							.findElements(By.xpath("//table[@id='dataTable']/tbody/tr")).size();
					if (Payment_Arrangement_rowcount <= 0) {
						Assert.fail("Payment Arrangement information is missing for account ID :: " + ACCOUNT_ID + "");
					}
					for (row = 1; row <= Payment_Arrangement_rowcount; row++) {
						String sa_status = CommonFunctions.driver
								.findElement(By.xpath(
										String.format("//table[@id='dataTable']/tbody/tr[%s]/td[%s]", row, column)))
								.getText();
						System.out.println("Value of SA status is :: " + sa_status);
						
						if (header.equalsIgnoreCase("Pending Stop")) {
							CommonFunctions.driver
									.findElement(By.xpath(
											String.format("//table[@id='dataTable']/tbody/tr[%s]/td[%s]", row, column)))
									.click();
							POC_Step_Definition.scenario.write("Payment Arrangement is in Pending Stop status");
						}
					}
					if (row > Payment_Arrangement_rowcount) {
						Assert.fail("Payment Arrangement is not in Pending Stop status for account ID :: " + ACCOUNT_ID
								+ "");

					}
				}

				CommonFunctions.driver.switchTo().window(parent_window_id);
				Thread.sleep(3000);
				try {
					CommonFunctions.driver.switchTo().frame("main");
				} catch (Exception e) {
					System.out.println(e.toString());

					Assert.fail("Unable the select data. Check the data table in open newly windows");
				}
				// Thread.sleep(4000);

			}
		}
	}
	/**
	 * Function to get Header Mapping for To Do Search
	 * @param HeaderName
	 * @return
	 * @throws InterruptedException 
	 */
	public static int headerMapping(String HeaderName) 

	{

		// Code to get index value for "STATUS" header.
		int tableHeaderIndex, HeaderNameIndex = 0;
		WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='headerTable']/thead/tr/th")));
		List<WebElement> HeaderValues = CommonFunctions.driver
				.findElements(By.xpath("//table[@id='headerTable']/thead/tr/th"));
		
		
		System.out.println("Total Column count is :: "+HeaderValues.size());
		for (tableHeaderIndex = 0; tableHeaderIndex < HeaderValues.size(); tableHeaderIndex++) {
			WebElement header = CommonFunctions.driver.findElement(By.xpath(String.format("//table[@id='headerTable']/thead/tr/th[%s]",tableHeaderIndex+1)));
			JavascriptExecutor je = (JavascriptExecutor) CommonFunctions.driver;
			String strHeader =null;
			je.executeScript("arguments[0].scrollIntoView(true);", header);
			try {
			strHeader = HeaderValues.get(tableHeaderIndex).getText().trim();
			}
			catch(StaleElementReferenceException e)
			{
				
				try{Thread.sleep(3000);}
				catch (Exception e1)
				{
					e1.getMessage();
				}
				strHeader = HeaderValues.get(tableHeaderIndex).getText().trim();
			}
			System.out.println("The value of strHeader:"+strHeader);
			if (strHeader.equalsIgnoreCase(HeaderName)) {
				// System.out.println("The value of strHeader " + strHeader);
				break;
			}

		}

		HeaderNameIndex = tableHeaderIndex + 1;
		System.out.println("The " + HeaderName + " is located at column number " + HeaderNameIndex);
		return HeaderNameIndex;

	}

	
	public static void tablecontentoverview_fdataframe(String status, String tablelable) {
		
		int rowcount=0;
		rowcount=CommonFunctions.driver.findElements(By.xpath("//table[@id='dataTable']/tbody/tr")).size();
		System.out.println("The total rows count is ::  "+rowcount);
		if(rowcount<=0)
		{
			Assert.fail("O records found after batchrun TD-BSERR");
		}
		
		
	}
	
	
	/**
	 * 
	 * @param hyperlinkcolumn
	 * @param tableID
	 */
	public static void tablecontenthyperlink_fdataframe(String hyperlinkcolumn, String tableID)
	{
		
		int column= headerMapping("CREATE DATE/TIME");
		CommonFunctions.driver.findElement(By.xpath(String.format("//table[@id='dataTable']/tbody/tr[%s]/td[%s]/a",1,column))).click();
		
	}
	
	/**
	 * 
	 * @param btnname
	 * @param status
	 * @param header
	 * @param page
	 */
	public static void meterstaus_fdataframe(String btnname,String status, String header,String page )
	{
		
		int column= headerMapping("Meter/Item Information");
		int rowcount = CommonFunctions.driver.findElements(By.xpath("//table[@id='dataTable']/tbody[@id='dataTableBody']/tr")).size();
		int row=0;
		if(rowcount<=0)
		{
			Assert.fail("Meter/Item information is missing for account ID :: "+ACCOUNT_ID+"");
		}
		for(row=1;row<=rowcount;row++)
		{
			//String meterstatus= CommonFunctions.driver.findElement(By.xpath(String.format("//table[@id='dataTable']/tbody[@id='dataTableBody']/tr[%s]/td[%s]",row,column))).getAttribute("value");
			String meterstatus= CommonFunctions.driver.findElement(By.xpath(String.format("//table[@id='dataTable']/tbody[@id='dataTableBody']/tr[%s]/td[%s]",row,column))).getText();
			System.out.println("The Value of meterstatus is :: "+meterstatus);
			if(meterstatus.contains("Active")) {
				CommonFunctions.driver.findElement(By.xpath(String.format("//table[@id='dataTable']/tbody[@id='dataTableBody']/tr[%s]/td[%s]//img",row,column))).click();
				break;
			}
		}
		if(row>rowcount)
		{
			Assert.fail("There is no active meter for this account ID :: "+ACCOUNT_ID+"");
		}
				
	}
	
	/**
	 * 
	 * @param read_type
	 * @param register_reading
	 * @param row
	 * @param index
	 */
	public static void newMeterReading(String read_type, String register_reading,int row,int index)
	{
		int headercol_RT = headerMapping("READ TYPE");
		// Select s1 = new Select(CommonFunctions.driver.findElement(
		// By.xpath("//table[@id='dataTable']//tbody/tr[" + row + "]/td[" + headercol_RT
		// + "]//Select")));
		Select s1 = new Select(
				CommonFunctions.driver.findElement(By.xpath(String.format(tblSelectdataframe, row, headercol_RT))));
		s1.selectByVisibleText(read_type);
		
		int headercol_RR = MeterReadHeader("REGISTER READING");
		// CommonFunctions.driver
		// By.xpath("//table[@id='dataTable']//tbody/tr[" + row + "]/td[" + headercol_RR
		// + "]//input"))
		// .sendKeys(register_reading);
		Float  register_reading_float= Float.parseFloat(register_reading)+REGISTERREADS.get(index);
		System.out.println("The float value of Register Reading is :: "+register_reading_float);
		String register_reading_str=register_reading_float.toString();  
		System.out.println("The string value of Register Reading is :: "+register_reading_str);
		CommonFunctions.driver.findElement(By.xpath(String.format(tblStringRegisterReading, row, headercol_RR)))
		.clear();
		CommonFunctions.driver.findElement(By.xpath(String.format(tblStringRegisterReading, row, headercol_RR)))
				.sendKeys(register_reading_str);
	}
	/**
	 * This function to enter value in characteristics tab under Meter Read page.
	 * @param column_name
	 * @param column_value
	 */
	
	public static void characteristics_meterread(String column_name, String column_value) {
		int column = headerMapping(column_name);
		WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='dataTable']/tbody/tr")));
		switch (column_name) {

		case "Characteristic Type": {
			Select s1 = new Select(
					CommonFunctions.driver.findElement(By.xpath(String.format(tblSelectdataframe, 1, column))));
			s1.selectByVisibleText(column_value);
			break;
		}
		case "Sequence": {
			CommonFunctions.driver.findElement(By.xpath(String.format(tblStringSequence, 1, column)))
					.sendKeys(column_value);
			break;
		}
		case "Characteristic Value": {
			CommonFunctions.driver.findElement(By.xpath(String.format(tblStringCharacteristicValue, 1, column)))
					.sendKeys(column_value);
			break;
		}
		default:
			Assert.fail("Case does not match. Check again");
		}
	}
	/**
	 * Enter values in MDMR UI POP UP
	 * @param label
	 * @param value
	 */
	public static void uipopup_MDMR(String label, String value) {
		switch (label) {
		case "REQUEST START DATE": {
			if (value.equalsIgnoreCase("%RUNTIMEVALUE%")) {
				
				CommonFunctions.driver
				.findElement(By.xpath("//*[text()='Request Start Date']//following::input[@id='_date'][1]"))
				.clear();
				CommonFunctions.driver
						.findElement(By.xpath("//*[text()='Request Start Date']//following::input[@id='_date'][1]"))
						.sendKeys(PREVIOUS_Meter_Read_Date);
			} 
			else if (value.equalsIgnoreCase("%TODAY_DATE%"))
			{
				String currentdate = CommonFunctions.currentDate("yyyy-MM-dd");
				CommonFunctions.driver
				.findElement(By.xpath("//*[text()='Request Start Date']//following::input[@id='_date'][1]"))
				.clear();
				CommonFunctions.driver
						.findElement(By.xpath("//*[text()='Request Start Date']//following::input[@id='_date'][1]"))
						.sendKeys(currentdate);
			}
			
			else {
//				CommonFunctions.driver
//				.findElement(By.xpath("//*[text()='Request Start Date']//following::input[@id='_date'][1]"))
//				.clear();
//				CommonFunctions.driver
//						.findElement(By.xpath("//*[text()='Request Start Date']//following::input[@id='_date'][1]"))
//						.sendKeys(value);
				CommonFunctions.driver
				.findElement(By.xpath("//input[@orafield='cmRequestStartDateTime'][1]"))
				.clear();
				CommonFunctions.driver
						.findElement(By.xpath("//input[@orafield='cmRequestStartDateTime'][1]"))
						.sendKeys(value);
			}

			break;
		}
		case "REQUEST END DATE": {
			if (value.equalsIgnoreCase("%TODAY_DATE%")) {
//				Date date = Calendar.getInstance().getTime();
//				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//				String strDate = dateFormat.format(date);
				String currentdate = CommonFunctions.currentDate("yyyy-MM-dd");
				CommonFunctions.driver
				.findElement(By.xpath("//*[text()='Request End Date']//following::input[@id='_date'][1]"))
				.clear();
				CommonFunctions.driver
						.findElement(By.xpath("//*[text()='Request End Date']//following::input[@id='_date'][1]"))
						.sendKeys(currentdate);
				CommonFunctions.driver
				.findElement(By.xpath("//*[text()='Request End Date']//following::input[@id='_date'][1]"))
				.sendKeys(Keys.TAB);
			} 
			else if(value.equalsIgnoreCase("%FUTURE_DATE%"))
			{
				String futuredate = CommonFunctions.futureDate("yyyy-MM-dd",30);
				CommonFunctions.driver
				.findElement(By.xpath("//*[text()='Request End Date']//following::input[@id='_date'][1]"))
				.clear();
			CommonFunctions.driver
					.findElement(By.xpath("//*[text()='Request End Date']//following::input[@id='_date'][1]"))
					.sendKeys(futuredate);
			CommonFunctions.driver
			.findElement(By.xpath("//*[text()='Request End Date']//following::input[@id='_date'][1]"))
			.sendKeys(Keys.TAB);
			}
			
			else {
//				CommonFunctions.driver
//				.findElement(By.xpath("//*[text()='Request End Date']//following::input[@id='_date'][1]"))
//				.clear();
//			CommonFunctions.driver
//					.findElement(By.xpath("//*[text()='Request End Date']//following::input[@id='_date'][1]"))
//					.sendKeys(value);
			CommonFunctions.driver
			.findElement(By.xpath("//input[@orafield='cmRequestEndDateTime'][1]"))
			.clear();
		CommonFunctions.driver
				.findElement(By.xpath("//input[@orafield='cmRequestEndDateTime'][1]"))
				.sendKeys(value);
		CommonFunctions.driver
		.findElement(By.xpath("//input[@orafield='cmRequestEndDateTime'][1]"))
		.sendKeys(Keys.TAB);

			}

			break;
		}
		case "REQUEST STATUS": {
//			WebElement request_status = CommonFunctions.driver
//					.findElement(By.xpath("//*[text()='Request Status']//following::select[1]"));
			WebElement request_status = CommonFunctions.driver
					.findElement(By.xpath("//select[@orafield='cmRequestStatus']"));
			Select s = new Select(request_status);
			s.selectByVisibleText(value);
			break;
		}
		case "Created Process": {
//			WebElement request_status = CommonFunctions.driver
//					.findElement(By.xpath("//*[text()='Request Status']//following::select[1]"));
			WebElement request_status = CommonFunctions.driver
					.findElement(By.xpath("//select[@orafield='cmCreatedProcess']"));
			Select s = new Select(request_status);
			String acc_createdProcess= s.getFirstSelectedOption().getText().trim();
			Assert.assertEquals("Created process did not match",value, acc_createdProcess);
			break;
		}
		case "REQUEST TYPE": {

//			WebElement request_type = CommonFunctions.driver
//					.findElement(By.xpath("//*[text()='Request Type']//following::select[1]"));
			WebElement request_type = CommonFunctions.driver
					.findElement(By.xpath("//select[@orafield='cmRequestType']"));
			Select s = new Select(request_type);
			s.selectByVisibleText(value);
			break;
		}
		default:
			Assert.fail("Case does not match. Check again");

		}
	}
	
	public static void verifyBillSegmentCreation(int totalsegment, int pasegment, String billsegmentname )
	
	{
		int billsegment = headerMapping("Bill Segment");
		int current_amount = headerMapping("Current Amount");
		int rowcount= CommonFunctions.driver.findElements(By.xpath("//table[@id='dataTable']/tbody/tr")).size();
		System.out.println("Total "+rowcount+" bill segment has been created");
		//Assert.assertEquals("3 Segments did not create", rowcount, totalsegment);
		for(int i=1;i<=rowcount;i++)
		{
			String billsegmentinfo=CommonFunctions.driver.findElement(By.xpath(String.format("//table[@id='dataTable']/tbody/tr[%s]/td[%s]",i,billsegment))).getText();
			System.out.println("Bill Segment Info is :: "+billsegmentinfo);
			if(billsegmentinfo.equalsIgnoreCase(billsegmentname))
			{
			String acc_currentamount=CommonFunctions.driver.findElement(By.xpath(String.format("//table[@id='dataTable']/tbody/tr[%s]/td[%s]",i,current_amount))).getText();
			System.out.println("The value of Payment Arrangement billsegment amount is ::  "+acc_currentamount);
			Assert.assertEquals("Payment Arrangement billsegment amount does not match with installment amount stored earlier.", INSTALLMENT_AMOUNT, acc_currentamount);
			}
		}
		
	}
	
	public static void newwindow_PaymentEvent(String label, String value) {
		
	    WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,30);  
		switch (label) {
		case "PAYMENT AMOUNT": {
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='PAYMENT_AMT']")));
			if (value.equalsIgnoreCase("%RUNTIME_INSTALLMENTAMOUNT%")) {
				
				CommonFunctions.driver
				.findElement(By.xpath("//input[@id='PAYMENT_AMT']"))
				.clear();
				CommonFunctions.driver
						.findElement(By.xpath("//input[@id='PAYMENT_AMT']"))
						.sendKeys(INSTALLMENT_AMOUNT);
			} else {
				CommonFunctions.driver
				.findElement(By.xpath("//input[@id='PAYMENT_AMT']"))
				.clear();
				CommonFunctions.driver
						.findElement(By.xpath("//input[@id='PAYMENT_AMT']"))
						.sendKeys(value);
			}

			break;
		}
		case "AMOUNT TENDERED": {
			CommonFunctions.driver.switchTo().frame("tendersGrid"); // Switching to tendersGrid frame in new opened window
			int column = headerMapping("Amount Tendered");
			if (value.equalsIgnoreCase("%RUNTIME_INSTALLMENTAMOUNT%")) {
				CommonFunctions.driver
				.findElement(By.xpath(String.format("//table[@id='dataTable']/tbody/tr[1]/td[%s]/input", column)))
				.clear();
				CommonFunctions.driver
						.findElement(By.xpath(String.format("//table[@id='dataTable']/tbody/tr[1]/td[%s]/input", column)))
						.sendKeys(INSTALLMENT_AMOUNT);
			} else {
				CommonFunctions.driver
				.findElement(By.xpath(String.format("//table[@id='dataTable']/tbody/tr[1]/td[%s]/input", column)))
				.clear();
			CommonFunctions.driver
					.findElement(By.xpath(String.format("//table[@id='dataTable']/tbody/tr[1]/td[%s]/input", column)))
					.sendKeys(value);				

			}

			break;
		}
		case "TENDER TYPE": {
			int column = headerMapping("Tender Type");
			WebElement tender_type = CommonFunctions.driver
					.findElement(By.xpath(String.format("//table[@id='dataTable']/tbody/tr[1]/td[%s]/select", column)));
			Select s = new Select(tender_type);
			s.selectByVisibleText(value);
			break;
		}
		default:
			Assert.fail("Case does not match. Check again");

		}
	}
	/**
	 * This function has been written to select SA status in new window
	 * @param status
	 * @param sa_division
	 * @param page
	 * @throws InterruptedException
	 */
	
	public static void newwindow_SASelect(String status, String sa_division, String page) throws InterruptedException {

		Set<String> list =CommonFunctions.driver.getWindowHandles();
		list.remove(parent_window_id);
		System.out.println(list);
		String subwindow = list.iterator().next();
		System.out.println("The window ID of subwindow is :: "+subwindow);
		CommonFunctions.driver.switchTo().window(subwindow);
		
		switch (page)

		{
		case "Service Agreement":
			WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,30);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("dataframe"));
			int column_SA_Type = headerMapping("SA Type");
			int column_SA_Status = headerMapping("SA Status");
			int rowcount = CommonFunctions.driver.findElements(By.xpath("//table[@id='dataTable']/tbody/tr")).size();
			int i;
			for (i = 1; i <= rowcount; i++) {
				String act_SA_Status = CommonFunctions.driver
						.findElement(By.xpath(
								String.format("//table[@id='dataTable']/tbody/tr[%s]/td[%s]", i, column_SA_Status)))
						.getText().trim();
				String act_SA_Type = CommonFunctions.driver
						.findElement(By.xpath(
								String.format("//table[@id='dataTable']/tbody/tr[%s]/td[%s]", i, column_SA_Type)))
						.getText();
//				System.out.println("SA status is :: " + act_SA_Status+ "SA type is :: "+act_SA_Type);
//				System.out.println("The value of SA division is :: "+sa_division);
//				System.out.println("1"+act_SA_Type.contains(sa_division));
//				System.out.println("2"+act_SA_Status.equalsIgnoreCase(status));
//				System.out .println("3"+(act_SA_Status.equalsIgnoreCase(status) && act_SA_Type.contains(sa_division)));
				if (act_SA_Status.equalsIgnoreCase(status) && act_SA_Type.contains(sa_division))						 
						{
					System.out.println("Clicked on Master SA having status active");
					CommonFunctions.driver
							.findElement(By.xpath(
									String.format("//table[@id='dataTable']/tbody/tr[%s]/td[%s]", i, column_SA_Status)))
							.click();
					System.out.println("Clicked on Master SA having status active");
					break;
				}

			}
			if (i > rowcount) {
				Assert.fail("There is no Master SA having status active");

			}
			break;
		default:
			Assert.fail("Case does not match. Check again");
		}
		
		CommonFunctions.driver.switchTo().window(parent_window_id);
		Thread.sleep(3000);
		try {
			CommonFunctions.driver.switchTo().frame("main");
		} catch (Exception e) {
			System.out.println(e.toString());

			Assert.fail("Unable the select data. Check the data table in open newly windows");
		}

	}
	
	public static void budgetValueEntry_fSA_BUDGET(String field,String value,String columnname,String page,int row)
	{
		//int column_new_budget = headerMapping(columnname);
	List<WebElement> list=CommonFunctions.driver.findElements(By.xpath("//table[@id='headerTable']/thead/tr/th"));
	int total_header= CommonFunctions.driver.findElements(By.xpath("//table[@id='headerTable']/thead/tr/th")).size();
	int column=0;
	for(column=0;column<total_header;column++)
	{
		String column_header = list.get(column).getText();
		if(column_header.equalsIgnoreCase(columnname))
		{
			column++;
			break;
		}
	}
	System.out.println("New Budget is located at "+column+"th column");	
	CommonFunctions.driver
		.findElement(By.xpath(
				String.format("//table[@id='dataTable']/tbody/tr[%s]/td[%s]/input", row, column)))
		.clear();
		CommonFunctions.driver
		.findElement(By.xpath(
				String.format("//table[@id='dataTable']/tbody/tr[%s]/td[%s]/input", row, column)))
		.sendKeys(value);
	}
	
	public static void characteristicsValueEntry_fCharGrid_character(String field,String value,String btnSave) throws InterruptedException
	{
		 WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,30);  
			switch (field) {
			case "Effective Date": {
				int column = headerMapping("Effective Date");
				CommonFunctions.driver
				.findElement(By.xpath(String.format("//table[@id='dataTable']/tbody/tr[2]/td[%s]/input", column)))
				.clear();
			if (value.equalsIgnoreCase("%TODAY_DATE%")) {
				String currentdate = CommonFunctions.currentDate("yyyy-MM-dd");
				CommonFunctions.driver
						.findElement(
								By.xpath(String.format("//table[@id='dataTable']/tbody/tr[2]/td[%s]/input", column)))
						.sendKeys(currentdate);
			} else {
				CommonFunctions.driver
						.findElement(
								By.xpath(String.format("//table[@id='dataTable']/tbody/tr[2]/td[%s]/input", column)))
						.sendKeys(value);
			}

				break;
			}
			case "Characteristic Type": {
				int column = headerMapping("Characteristic Type");
				WebElement characteristic_type = CommonFunctions.driver
						.findElement(By.xpath(String.format("//table[@id='dataTable']/tbody/tr[2]/td[%s]/select", column)));
				Select s = new Select(characteristic_type);
				s.selectByVisibleText(value);
				Thread.sleep(3000);
				CommonFunctions.driver
				.findElement(By.xpath(String.format("//table[@id='dataTable']/tbody/tr[2]/td[%s]/select", column))).sendKeys(Keys.TAB);
				break;
			}
			case "Characteristic Value": {
				int column = headerMapping("Characteristic Value");	
//				WebElement obj = CommonFunctions.driver.findElement(By.xpath(String.format("//table[@id='dataTable']/tbody/tr[2]/td[%s]/input", column)));
//				JavascriptExecutor executor = (JavascriptExecutor)CommonFunctions.driver;
//				executor.executeScript("arguments[0].click();", obj);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//table[@id='dataTable']/tbody/tr[2]/td[%s]/input[2]", column))));
				CommonFunctions.driver
				.findElement(By.xpath(String.format("//table[@id='dataTable']/tbody/tr[2]/td[%s]/input[2]", column)))
				.clear();
			CommonFunctions.driver
					.findElement(By.xpath(String.format("//table[@id='dataTable']/tbody/tr[2]/td[%s]/input[2]", column)))
					.sendKeys(value);
			break;
			}
			default:
				Assert.fail("Case does not match. Check again");

			}
	}
	
	
	public static void verifyBQData_fmainPage(String label, String value, String page)
			throws InterruptedException {
		switch (page) {
		
		case "Meter Read":
		{
			WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,25);
			if (label.equalsIgnoreCase("Request Start Date")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='scriptSteps']//tbody/tr")));
				String request_start_date =CommonFunctions.driver.findElement(By
					.xpath(String.format("//table[@id='scriptSteps']//tbody//tr//td[contains(text(),'Request Start Date:')]/following::td"))).getAttribute("value");
				if (value.equalsIgnoreCase("%RUNTIMEVALUE%")) {
					Assert.assertEquals("Request Failed: Value of Request Start Date is not updated as Read date of Previous BQ", PREVIOUS_Meter_Read_Date, request_start_date);
					System.out.println("The value of "+label+ "is updated successfully as Read date of Previous BQ :: "+request_start_date);
					POC_Step_Definition.scenario.write("The value of "+label+ "is updated successfully as Read date of Previous BQ :: "+request_start_date); 
				}
			}
			else if (label.equalsIgnoreCase("Request Status")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='scriptSteps']//tbody/tr")));
				String request_status = CommonFunctions.driver.findElement(By.xpath("//table[@id='scriptSteps']//tbody//tr//td[contains(text(),'Request Status:')]/following::td")).getText();
				System.out.println("The value of "+label+" is :: " + request_status);
				if(value.equalsIgnoreCase(request_status))
				{
					Assert.assertEquals("Request Failed: Value of Request Status is not updated", value, request_status);
					System.out.println("The value of "+label+ "is updated successfully :: "+request_status);
					POC_Step_Definition.scenario.write("The value of "+label+ "is updated successfully :: "+request_status); 
				}
				else
					Assert.fail("The value of "+label+ "is not updated");
			}
			break;
		}
		default:
			Assert.fail("Case does not match. Check again");

		}
	}
	// End here ::
}
