package com.hydroottawa.ccbupgrade.poc.stepdefs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;
import com.hydroottawa.ccbupgrade.poc.page.ref.POC_PageRef;
import com.hydroottawa.ccbupgrade.poc.utils.CCBUpgradeLib;
import com.hydroottawa.ccbupgrade.poc.utils.CommonFunctions;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class POC_Step_Definition implements POC_PageRef {

	
	public static Scenario scenario;
	public static String browser;
	public static Connection con;
	private static String dburlSIT = "jdbc:oracle:thin:@10.166.188.163:1521:CCBSIT";
	private static String dburlDEV = "jdbc:oracle:thin:@10.166.188.168:1521:CCBDEV27";
	private static String dbuser = "deepku";
	private static String dbpassword = "Passw0rd2019";
	//private static ATUTestRecorder recorder;
	
	  @Before
	    public void beforeScenario(Scenario scenario){
	       this.scenario = scenario;
		/*
		 * try { recorder = new
		 * ATUTestRecorder("Reports","TestScriptExecutionrecodring",true);
		 * recorder.start(); } catch (ATUTestRecorderException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
	  }
	
	  @Given("Connect to {string} database and execute below SQL query to retrieve required {string}")
	  public void connect_to_database_and_execute_below_SQL_query_to_retrieve_required(String environment, String testdata, String sqlQuery) {
	      // Write code here that turns the phrase above into concrete actions		  
		try {
			if (environment.equalsIgnoreCase("2.7 SIT")) {
				con = DriverManager.getConnection(dburlSIT, dbuser, dbpassword);
			} else if (environment.equalsIgnoreCase("2.7 DEV")) {
				con = DriverManager.getConnection(dburlDEV, dbuser, dbpassword);
			} else {
				Assert.fail("Enter either 2.7 SIT or 2.7 DEV environment of CC&B Upgrade");
			}
			System.out.println(environment+" database connection established successfully");
			System.out.println("sqlQuery is :: " + sqlQuery);
			PreparedStatement PS = con.prepareStatement(sqlQuery);
			ResultSet RS = PS.executeQuery();
			while (RS.next()) {
				
				CCBUpgradeLib.DB_TESTDATA=RS.getString(1);
				Assert.assertNotNull("There is no data available in the "+environment+" database for query :: "+sqlQuery+"", RS.getString(1));				
				System.out.println("The "+testdata+" is :: " + CCBUpgradeLib.DB_TESTDATA);
				scenario.write("The "+testdata+" is :: " + CCBUpgradeLib.DB_TESTDATA);
				// System.out.println("The Account Number is :: " + RS.getString("ACCT_ID"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
				System.out.println(environment+" database connection closed successfully");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}               
	     
	  }
	  
	  @Given("^Open browser \"([^\"]*)\"$")
	public void open_browser(String browserName) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		browser=browserName;
		// Below function will open the browser which will be mentioned in Feature File
		CommonFunctions.openBrowser(browserName);
		
	   
	}

	@When("^Login to CCB under \"([^\"]*)\" environment with user as \"([^\"]*)\" and password as \"([^\"]*)\"$")
	public void login_to_CCB_under_environment_with_user_as_and_password_as(String environment, String username, String password) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//CommonFunctions.openCCBURL(CommonFunctions.EnvironmentUtility(environment));
		CommonFunctions.openCCBURL("dev");
		//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "openCCBURL");
		//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
		//Date : 14th June 2019
		CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
		scenario.embed(CommonFunctions.snapshot, "image/png");
		CommonFunctions.login(username,password);
		//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
		//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
		//Date : 14th June 2019
		CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
		scenario.embed(CommonFunctions.snapshot, "image/png");
		Thread.sleep(7000);
		CommonFunctions.driver.switchTo().frame("main");
		//Thread.sleep(8000);
	}

	@Then("^Ensure user will be landed to \"([^\"]*)\" after successful login$")
	public void ensure_user_will_be_landed_to_after_successful_login(String Title) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		// User must be in 'main' frame to execute below function.
		CommonFunctions.CCBPageValidation(Title);
		//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
		//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
		//Date : 14th June 2019
		CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
		scenario.embed(CommonFunctions.snapshot, "image/png");
	   
	}

	@When("^Select \"([^\"]*)\" from \"([^\"]*)\" drop down list under \"([^\"]*)\"$")
	public void select_from_drop_down_list_under(String searchitem, String searchlabel, String searchpage) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions		
		CommonFunctions.driver.switchTo().frame("tabPage");
		CCBUpgradeLib.CC_SearchBySelection(searchitem,searchlabel, searchpage);
		//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
		//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
		//Date : 14th June 2019
		CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
		scenario.embed(CommonFunctions.snapshot, "image/png");
	}
    
	@When("Select {string} from {string} drop down list")
	public void select_from_drop_down_list(String value, String label) throws Exception {
		Thread.sleep(2000);
		// User must be in tabPage frame here
		CCBUpgradeLib.selectDropDown_ftabpage(value,label);  
	}
	@When("^Enter \"([^\"]*)\" in the Account ID input field and click on Search button$")
	public void enter_in_the_Account_ID_input_field_and_click_on_Search_button(String searchValue) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		CCBUpgradeLib.CC_SearchOperation(searchValue, "searchpage");
		//Thread.sleep(3000);
		boolean flag;
		//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
		//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
		//Thread.sleep(3000);
	    WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,50);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='analyticsFilterText1']//*[text()='"+searchValue+"']")));
		flag=CommonFunctions.driver.findElement(By.xpath("//*[@id='analyticsFilterText1']//*[text()='"+searchValue+"']")).isDisplayed();
		Assert.assertTrue(flag);
		System.out.println("Account Validation Done");
		//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
		//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
		//Date : 14th June 2019
		CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
		scenario.embed(CommonFunctions.snapshot, "image/png");
	//	CommonFunctions.driver.switchTo().parentFrame();//Back to tabPage frame from zoneMapFrame_2 frame
		Thread.sleep(3000); // Enterted for Mozilla
		CommonFunctions.driver.switchTo().parentFrame(); // Moved to main frame from tabPage frame
	}

	@When("^Navigate to below list from top to bottom in order to reach \"([^\"]*)\" page$")
	public void navigate_to_below_list_from_top_to_botton_in_order_to_reach_page(String pageName, List<String> navlist) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		// We must be in main frame to use this step definition.
		Thread.sleep(2000);
		for(int i=0;i<navlist.size();i++)
		{
			//System.out.println(navlist.get(i));
			CCBUpgradeLib.navigation_fmenu(pageName,navlist.get(i),i);
		}
		
		Thread.sleep(2000);
		//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
		//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
		//Date : 14th June 2019
		CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
		scenario.embed(CommonFunctions.snapshot, "image/png");
	    
	}

	@When("^Enter below values in \"([^\"]*)\" page$")
	public void enter_below_values_in_page(String page, List<List<String>> listOflists) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		CommonFunctions.driver.switchTo().frame("tabPage");
		for(List<String> listString : listOflists)
		{       
			String field = listString.get(0);
			//System.out.println("The value of field is :: "+field);
			String value = listString.get(1);
			//System.out.println("The value of value is :: "+value);
			CCBUpgradeLib.entervalues_ftabPage(page,field, value);
			}
		Thread.sleep(4000);
		//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
		//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
		//Date : 14th June 2019
				CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
				scenario.embed(CommonFunctions.snapshot, "image/png");

		}

	

	@When("^Select \"([^\"]*)\" check box$")
	public void select_check_box(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		boolean flag;
		//flag=CommonFunctions.driver.findElement(By.xpath("//input[@id='USE_ON_BILL_SW']")).isSelected();
		flag=CommonFunctions.driver.findElement(checkboxUSE_ON_BILL).isSelected();
		if (flag!=true)
		{
			//CommonFunctions.driver.findElement(By.xpath("//input[@id='USE_ON_BILL_SW']")).click();
			CommonFunctions.driver.findElement(checkboxUSE_ON_BILL).click();
		}
		
		//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
		//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
		//Date : 14th June 2019
		CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
		scenario.embed(CommonFunctions.snapshot, "image/png");
	}

	@When("^Fill the \"([^\"]*)\" as mentioned below ::$")
	public void fill_the_as_mentioned_below(String arg1, List<List<String>> listoflists) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		CommonFunctions.driver.switchTo().frame("dataframe"); // Parent of dataframe is tabpage
		int nooflist = listoflists.size();
		for(int row =1 ;row<nooflist; row++)
		{
			JavascriptExecutor je = (JavascriptExecutor)CommonFunctions.driver;
			//WebElement obj= CommonFunctions.driver.findElement(By.xpath("//img[@id='IM_STU:0$RegList_plusBtn']"));
			WebElement obj= CommonFunctions.driver.findElement(tableImgPlusBtn);
			je.executeScript("arguments[0].scrollIntoView(true);", obj);
			//CommonFunctions.driver.findElement(By.xpath("//img[@id='IM_STU:0$RegList_plusBtn']")).click();
			CommonFunctions.driver.findElement(tableImgPlusBtn).click();
		}
		int row=1;
		for (List<String> listStr : listoflists)
		{
			    
				String sequence = listStr.get(0);
				String read_type = listStr.get(1);
				String unit_of_measure = listStr.get(2);
				String time_of_use = listStr.get(3);
				String register_reading= listStr.get(4);
				CCBUpgradeLib.MeterRead(sequence, read_type,unit_of_measure,time_of_use,register_reading,row);
			    row+=1;
		}
		
		
	//	CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
	//	Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
		//Date : 14th June 2019
				CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
				scenario.embed(CommonFunctions.snapshot, "image/png");
		CommonFunctions.driver.switchTo().parentFrame();  //Switch to tabPage as tabPage is parent of dataframe.
		
	    
	}

	@When("^Click on \"([^\"]*)\" button$")
	public void click_on_button(String button) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		// We must be in main frame to use this step definition.
	//	CommonFunctions.driver.switchTo().parentFrame(); // Switch to menu frame from tabPage frame..
	//	CCBUpgradeLib.clickButton_fmenu(button);
	//	Thread.sleep(2000);
	//	CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
	//	Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
	    
	}
	
	@When("^Click on \"([^\"]*)\" button in \"([^\"]*)\" page$")
	public void click_on_button_in_page(String button, String page) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		CommonFunctions.driver.switchTo().parentFrame(); // Switch to menu frame from tabPage frame..
		CCBUpgradeLib.clickButton_fmenu(button,page);
		Thread.sleep(2000);
		//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
		//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
		//Date : 14th June 2019
				CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
				scenario.embed(CommonFunctions.snapshot, "image/png");
		
	}
	
	@Then("^The value of \"([^\"]*)\" is displayed as \"([^\"]*)\" under \"([^\"]*)\" page$")
	public void the_value_of_is_displayed_as_under_page(String label, String value, String page) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		CommonFunctions.driver.switchTo().frame("tabPage"); // Switching main frame to tabPage frame.
		CCBUpgradeLib.verifyBatchJobStatus_ftabPage(label,value,page);
		//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
		//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
		//Date : 14th June 2019
				CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
				scenario.embed(CommonFunctions.snapshot, "image/png");
	    
	}
	
	@Then("^Click on \"([^\"]*)\" and then select \"([^\"]*)\" in \"([^\"]*)\" page$")
	public void click_on_and_then_select_in_page(String menu, String option, String page) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//CommonFunctions.driver.switchTo().frame("tabPage"); // Switching main frame to tabPage frame.
		CCBUpgradeLib.selectValue_ftabPage(menu, option, page);
		//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
		//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
		//Date : 14th June 2019
				CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
				scenario.embed(CommonFunctions.snapshot, "image/png");
	    
	}
	
	@Then("^Enter the runtime value of \"([^\"]*)\" and click on \"([^\"]*)\" button$")
	public void enter_the_runtime_value_of_and_click_on_button(String mr_stage_up_id, String button) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		CCBUpgradeLib.batchvalidation_SearchStagingID_newwindow(mr_stage_up_id,button);
		//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
		//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
		//Date : 14th June 2019
				CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
				scenario.embed(CommonFunctions.snapshot, "image/png");
	    
	}
	
//##############################################@GenerateBill ###################################################
	@Then("^Click on \"([^\"]*)\" under \"([^\"]*)\" dashboard$")
	public void click_on_under_dashboard(String button, String page) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//CommonFunctions.driver.switchTo().parentFrame();
		//// User must be in main frame to use this function.
		Thread.sleep(1000);
		CommonFunctions.driver.switchTo().frame("dashboard");  // Switching to main frame to dashboard frame.
		CCBUpgradeLib.clickSingleButton_fdashboard(button, page);
		Thread.sleep(1000);
	//	CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
	//	Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
		//Date : 14th June 2019
				CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
				scenario.embed(CommonFunctions.snapshot, "image/png");
		CommonFunctions.driver.switchTo().parentFrame();   // Switching to dashboard frame to main frame.
	}



	@Then("^Click on \"([^\"]*)\" button in \"([^\"]*)\" tabpage$")
	public void click_on_button_in_tabpage(String button, String page) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		// User must be in tabFrame frame to use this function.
		//CommonFunctions.driver.switchTo().parentFrame();
		Thread.sleep(3000);  //// Entered for Mozilla Browser
		if(page.equalsIgnoreCase("Bill") || page.equalsIgnoreCase("Account"))
		{
		CommonFunctions.driver.switchTo().frame("tabPage"); // Switching to main frame to tabPage frame.
		}
		CCBUpgradeLib.clickSingleButton_ftabPage(button, page);
		//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
		//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
		//Date : 14th June 2019
				CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
				scenario.embed(CommonFunctions.snapshot, "image/png");
	    
	}

	@Then("^Enter \"([^\"]*)\" as \"([^\"]*)\" then click on \"([^\"]*)\" button in opened window$")
	public void enter_as_then_click_on_button_in_opened_window(String label, String cutoffvalue, String button) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		CCBUpgradeLib.billgeneration_calculation(label, cutoffvalue, button);
		//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
		//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
		//Date : 14th June 2019
				CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
				scenario.embed(CommonFunctions.snapshot, "image/png");
	    
	}
	
	@Then("Click on {string} tab in {string} menupage")
	public void click_on_tab_in_menupage(String tabname, String pagename) throws Exception {
	    // User must be in main frame
		//CommonFunctions.driver.switchTo().parentFrame(); // Switching to main frame from tabPage frame.
		//CommonFunctions.driver.switchTo().frame("tabMenu");  // Switching menu to tabMenu
		CCBUpgradeLib.clickSingleButton_fmenu(tabname,pagename);
		CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
		scenario.embed(CommonFunctions.snapshot, "image/png");
	}

	@Then("Click on {string} {string} under {string} zone in {string} Page")
	public void click_on_under_zone_in_Page(String tagname, String tag, String zone, String pagename) throws InterruptedException {
		CommonFunctions.driver.switchTo().frame("tabPage"); // Switching main to tabPage
		CommonFunctions.driver.switchTo().frame("zoneMapFrame_2"); // Switching tabpage to zoneMapFrame_2
		CCBUpgradeLib.clickSingleButton_fzoneMap(tagname,tag,zone,pagename); 
		CommonFunctions.driver.switchTo().parentFrame(); // Switching zoneMapFrame_2 to tabpage
		CommonFunctions.driver.switchTo().parentFrame(); // Switching tabpage to main
		CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
		scenario.embed(CommonFunctions.snapshot, "image/png");
	}
	@Then("list of {string} status {string} are displayed in tabular format")
	public void list_of_status_are_displayed_in_tabular_format(String status, String tablelabel) {
	    // Write code here that turns the phrase above into concrete actions
		CommonFunctions.driver.switchTo().frame("dataframe"); // Parent of dataframe is tabpage
		CCBUpgradeLib.tablecontentoverview_fdataframe(status,tablelabel);
		CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
		scenario.embed(CommonFunctions.snapshot, "image/png");
	    
	}

	@When("Click on {string} hyperlink under {string}")
	public void click_on_hyperlink_under(String hyperlinkcolumn, String tableID) {
	    // Write code here that turns the phrase above into concrete actions
	    // user must be in data frame here
		CCBUpgradeLib.tablecontenthyperlink_fdataframe(hyperlinkcolumn,tableID);
		CommonFunctions.driver.switchTo().parentFrame();  // Switching dataframe-->tabPage
		CommonFunctions.driver.switchTo().parentFrame();  // Switching tabPage-->main
		CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
		scenario.embed(CommonFunctions.snapshot, "image/png");
		
	}
	@When("Click on {string} image button for {string} meter under {string} column in {string} page")
	public void click_on_image_button_for_meter_under_column_in_page(String btnname, String status, String header, String page) {
	    // Write code here that turns the phrase above into concrete actions
		// user must be in data frame here
		CommonFunctions.driver.switchTo().frame("dataframe"); // Parent of dataframe is tabpage
		CCBUpgradeLib.meterstaus_fdataframe(btnname,status,header,page);
		CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
		scenario.embed(CommonFunctions.snapshot, "image/png");
		CommonFunctions.driver.switchTo().parentFrame(); // Switching dataframe--->tabPage
		CommonFunctions.driver.switchTo().parentFrame(); // Switching tabPage ----> main
	}
	@Then("Click on the first {string} row under the {string} table in newly opened window")
	public void click_on_the_first_row_under_the_table_in_newly_opened_window(String header, String tablename) throws InterruptedException, IOException {
	    // Write code here that turns the phrase above into concrete actions
		CCBUpgradeLib.dataselection_newlyopenedwindow(header, tablename);
		CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
		scenario.embed(CommonFunctions.snapshot, "image/png");
	}
	
	@Then("Select {string} and add below reading in previous {string} in {string} page")
	public void select_and_add_below_reading_in_previous_in_page(String readtype, String registerreading ,String pagename, List<List<String>> listoflist) {
	    // Write code here that turns the phrase above into concrete actions
	
	CommonFunctions.driver.switchTo().frame("dataframe"); // Switching tabpage --> dataframe
	int rowcount= CommonFunctions.driver.findElements(By.xpath("//table[@summary='Register Reads']/tbody/tr")).size();
	System.out.println("The total row available in rowcount is :: "+rowcount);
	int row=1;
	int index=0;
	for(List<String> liststring : listoflist)
	{
		if(row<=rowcount)
		{
		readtype=liststring.get(0);
		registerreading=liststring.get(1);
		CCBUpgradeLib.newMeterReading(readtype,registerreading,row,index);
		row++;
		index++;
		}
		else {
			break;
		}
	}
	CommonFunctions.driver.switchTo().parentFrame();  // Switching dataframe--> tabPage
	CommonFunctions.driver.switchTo().parentFrame();  // Switching tabPage---> main
	CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
	scenario.embed(CommonFunctions.snapshot, "image/png");
	
	}
	
	
	@Then("Enter below values in {string} tab under {string} page")
	public void enter_below_values_in_tab_under_page(String string, String page,List<List<String>> listoflist) {
	    // Write code here that turns the phrase above into concrete actions
		
		CommonFunctions.driver.switchTo().frame("tabPage"); // Switching main to tabPage
		CommonFunctions.driver.switchTo().frame("MR_CHAR"); // Switching tabpage to MR_CHAR
		for(List<String> liststring :listoflist )
		{
			String column_name =liststring.get(0);
			String column_value= liststring.get(1);
			CCBUpgradeLib.characteristics_meterread(column_name,column_value);
		}
		CommonFunctions.driver.switchTo().parentFrame();  // Switching MR_CHAR--> tabPage frame
		CommonFunctions.driver.switchTo().parentFrame();  // Switching tabPage---> main frame
		CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
		scenario.embed(CommonFunctions.snapshot, "image/png");

	}
	@Then("Click on {string} button in {string} mainpage")
	public void click_on_button_in_mainpage(String btnname, String pagename) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    // User must be in main frame to use below function.
		
		CCBUpgradeLib.clickSingleButton_fmain(btnname,pagename);
		CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
		scenario.embed(CommonFunctions.snapshot, "image/png");
	}
	
	@When("Enter below values in {string} UI pop up page and then click on {string} button")
	public void enter_below_values_in_UI_pop_up_page_and_then_click_on_button(String pagename, String btnname,
			List<List<String>> listoflist) throws InterruptedException {
         String acc_column_name=null;
		CommonFunctions.driver.switchTo().frame("uiMap"); // Switching main to uiMap frame

		for (List<String> liststring : listoflist) {
			String column_name = liststring.get(0);
			acc_column_name=column_name;
			String column_value = liststring.get(1);
			CCBUpgradeLib.uipopup_MDMR(column_name, column_value);
		}
		Thread.sleep(4000);
		if (!acc_column_name.equalsIgnoreCase("Created Process")) {
			CommonFunctions.snapshot = CommonFunctions.getSnapshot(CommonFunctions.driver, "screenshot");
			scenario.embed(CommonFunctions.snapshot, "image/png");
			System.out.println("Column name is :::::: " + acc_column_name);
			CommonFunctions.driver.findElement(By.xpath("//table[@id='MDMRBQRequest']//input[@value='Save']")).click();
			// Thread.sleep(1000);
			Alert alert = CommonFunctions.driver.switchTo().alert();
			String alertMessage = CommonFunctions.driver.switchTo().alert().getText();
//		CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
//		scenario.embed(CommonFunctions.snapshot, "image/png");
			// Displaying alert message
			System.out.println("MDMR BQ Request " + alertMessage);
			try {
				alert.accept();
			} catch (NoAlertPresentException e) {
				// Nothing to do
				e.getMessage();
			}
			System.out.println("Alert accepted");
		}
		Thread.sleep(2000);  //img[@id='scriptClose2']
		CommonFunctions.driver.switchTo().parentFrame();  // Switching uiMap frame -->main frame
		CommonFunctions.driver.findElement(By.xpath("//img[@id='scriptClose2']")).click();
		CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
		scenario.embed(CommonFunctions.snapshot, "image/png");
	}
	
	@Then("Ensure {string} Bill Segments have been created including {string} {string} Bill Segment")
	public void ensure_Bill_Segments_have_been_created_including_Bill_Segment(String totalsegmentscount, String pa_segementcount, String billsegmentname) {
	    // Write code here that turns the phrase above into concrete actions
		
		CommonFunctions.driver.switchTo().frame("tabPage"); // Switching main to tabPage
		CommonFunctions.driver.switchTo().frame("FinlSumm_bsegGrid"); // Switching tabPage to FinlSumm_bsegGrid
		int totalsegment = Integer.parseInt(totalsegmentscount);
		int pa_segment = Integer.parseInt(pa_segementcount);
		CCBUpgradeLib.verifyBillSegmentCreation(totalsegment,pa_segment,billsegmentname);
		CommonFunctions.driver.switchTo().parentFrame(); // Switching FinlSumm_bsegGrid frame to tabPage
		CommonFunctions.driver.switchTo().parentFrame(); // Switching tabPage frame to main
		CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
		scenario.embed(CommonFunctions.snapshot, "image/png");
	   
	}
	
	@Then("Enter below values in newly opened {string} window and then click on {string} button")
	public void enter_below_values_in_newly_opened_window_and_then_click_on_button(String string, String btnname,List<List<String>> listoflist) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	   
		Set<String> allHandles = CommonFunctions.driver.getWindowHandles();
	       Iterator<String> iter = allHandles.iterator();
	      // allHandles.remove(CCBUpgradeLib.parent_window_id);
	       while(iter.hasNext()) {
	       String subWindowHandler =iter.next();
	       System.out.println("The window ID of second window :: "+subWindowHandler);
	       System.out.println("The window ID of parent window :: "+CCBUpgradeLib.parent_window_id);
	       if(!subWindowHandler.equalsIgnoreCase(CCBUpgradeLib.parent_window_id))
	       {
	       System.out.println("The value of subWindowHandler is :: "+subWindowHandler);
	       CommonFunctions.driver.switchTo().window(subWindowHandler);
	       }
	       }
		for (List<String> liststring : listoflist) {
			String column_name = liststring.get(0);
			String column_value = liststring.get(1);
			CCBUpgradeLib.newwindow_PaymentEvent(column_name, column_value);
		}
		// Click on OK Button
		CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
		POC_Step_Definition.scenario.embed(CommonFunctions.snapshot, "image/png");
		CommonFunctions.driver.switchTo().defaultContent();
		CommonFunctions.driver.findElement(By.xpath("//input[@id='OK_LBL_LBL']")).click();
		CommonFunctions.driver.switchTo().window(CCBUpgradeLib.parent_window_id);
		Thread.sleep(1000);
		CommonFunctions.driver.switchTo().frame("main");
		CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
		scenario.embed(CommonFunctions.snapshot, "image/png");
	}
	
	@Then("Click on the {string} {string} {string} in newly opened window")
	public void click_on_the_in_newly_opened_window(String status, String sa_division, String page) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		//WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,30);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("dataframe"));
		CCBUpgradeLib.newwindow_SASelect(status,sa_division,page );
		CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
		scenario.embed(CommonFunctions.snapshot, "image/png");
	    
	}
	@Then("Enter below values in {string} column under {string} table in {string} page then click on {string} button")
	public void enter_below_values_in_column_under_table_in_page_then_click_on_button(String columnname, String table_frame, String page, String btnSave, List<List<String>> listoflist) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		
		CommonFunctions.driver.switchTo().frame("tabPage"); // Switching main to tabPage
		WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("SA_BUDGET"));
		//CommonFunctions.driver.switchTo().frame("SA_BUDGET"); // Switching tabPage to SA_BUDGET
		int row=1;
		for (List<String> liststring : listoflist) {
			String field = liststring.get(0);
			System.out.println("Field is :: "+field);			
			String value = liststring.get(1);
			System.out.println("Value is :: "+value);
			CCBUpgradeLib.budgetValueEntry_fSA_BUDGET(field, value,columnname,page,row);
			row++;
		}
		CommonFunctions.driver.switchTo().parentFrame(); // Switching SA_BUDGET frame to tabPage
		CommonFunctions.driver.switchTo().parentFrame(); // Switching tabPage frame to main
		CommonFunctions.driver.findElement(By.xpath("//input[@id='IM_SAVE']")).click();
		Thread.sleep(5000);
		CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
		scenario.embed(CommonFunctions.snapshot, "image/png");
	  
	}
	
	@Then("Click on first add image button and enter below values under {string} tab then click on {string} button")
	public void click_on_first_add_image_button_and_enter_below_values_under_tab_then_click_on_button(String tabname, String btnSave, List<List<String>> listoflist) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		
		CommonFunctions.driver.switchTo().frame("tabPage"); // Switching main to tabPage
		WebDriverWait wait = new WebDriverWait(CommonFunctions.driver,30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("CharGrid_character"));
		int column= CCBUpgradeLib.headerMapping("Characteristic Type");
		int column1 = CCBUpgradeLib.headerMapping("Effective Date");
		int rowcount = CommonFunctions.driver.findElements(By.xpath("//table[@id='dataTable']/tbody[@id='dataTableBody']/tr")).size();
		int row=0;
		//boolean flag=true;
		for(row=1;row<=rowcount;row++)
		{
			WebElement obj= CommonFunctions.driver.findElement(By.xpath(String.format("//table[@id='dataTable']/tbody[@id='dataTableBody']/tr[%s]/td[%s]//select",row,column)));
			Select s = new Select(obj);
			String CharacteristicType= s.getFirstSelectedOption().getText().trim();
			System.out.println("The Value of Characteristic Type is :: "+CharacteristicType);
			if(CharacteristicType.equalsIgnoreCase("Override DUE DAY")) {
				String currentdate = CommonFunctions.currentDate("yyyy-MM-dd");
				CommonFunctions.driver
				.findElement(
						By.xpath(String.format("//table[@id='dataTable']/tbody/tr[%s]/td[%s]/input",row, column1)))
				.clear();
				CommonFunctions.driver
						.findElement(
								By.xpath(String.format("//table[@id='dataTable']/tbody/tr[%s]/td[%s]/input",row, column1)))
						.sendKeys(currentdate);
				System.out.println("Override DUE DAY is already available under Characteristic Type ");
				//flag=false;
				break;
			}
		}
		if (row > rowcount) {
			CommonFunctions.driver.findElement(By.xpath("//table[@id='dataTable']/tbody/tr[1]/td[1]/img")).click();
			Thread.sleep(3000);
			for (List<String> liststring : listoflist) {
				String field = liststring.get(0);
				System.out.println("Field is :: " + field);
				String value = liststring.get(1);
				System.out.println("Value is :: " + value);
				CCBUpgradeLib.characteristicsValueEntry_fCharGrid_character(field, value, btnSave);
			}
		}
		CommonFunctions.driver.switchTo().parentFrame(); // Switching CharGrid_character frame to tabPage
		CommonFunctions.driver.switchTo().parentFrame(); // Switching tabPage frame to main
		//if (flag = true) {
			CommonFunctions.driver.findElement(By.xpath("//input[@id='IM_SAVE']")).click();
		//}
		Thread.sleep(5000);
		CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
		scenario.embed(CommonFunctions.snapshot, "image/png");
	  
	}
	
//	@Then("The value of {string} is displayed as {string} under Meter Read\" main page")
//	public void the_value_of_is_displayed_as_under_Meter_Read_main_page(String string, String string2) {
	@Then("^The value of \"([^\"]*)\" is displayed as \"([^\"]*)\" under \"([^\"]*)\" main page$")
	public void the_value_of_is_displayed_as_under_main_page(String label, String value, String page) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		CCBUpgradeLib.verifyBQData_fmainPage(label,value,page);
		//CommonFunctions.screenshotfilename=CommonFunctions.getSnapshotBase64(CommonFunctions.driver, "CCBHomePage");
		//Reporter.addScreenCaptureFromPath(CommonFunctions.screenshotfilename);
		//Date : 14th June 2019
				CommonFunctions.snapshot=CommonFunctions.getSnapshot(CommonFunctions.driver,"screenshot");
				scenario.embed(CommonFunctions.snapshot, "image/png");
	    
	} 
	
	@When("^Close the browser$")
	public void close_the_browser() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		CommonFunctions.teardown();
		
	    
	}
	
	/**
	 * Taking a screenshot when a scenario fails
	 * @param scenario
	 */
//	@After
//	public void afterScenario(Scenario scenario)
//	{
//	    this.scenario=scenario;
//		if (scenario.isFailed()) {
//	    byte[] screenshot = ((TakesScreenshot) CommonFunctions.driver).getScreenshotAs(OutputType.BYTES);
//	    scenario.embed(screenshot, "image/png");
//	}
//		try {
//			recorder.stop();
//		} catch (ATUTestRecorderException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
}
