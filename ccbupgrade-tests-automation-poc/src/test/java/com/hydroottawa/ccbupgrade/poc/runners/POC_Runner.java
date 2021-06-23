package com.hydroottawa.ccbupgrade.poc.runners;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.plexus.util.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;
import com.github.mkolisnyk.cucumber.reporting.CucumberResultsOverview;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import com.hydroottawa.ccbupgrade.poc.stepdefs.POC_Step_Definition;
import com.hydroottawa.ccbupgrade.poc.utils.CommonFunctions;


import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.cli.Main;
import cucumber.api.junit.Cucumber;

import gherkin.formatter.Formatter;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;

@RunWith(Cucumber.class)
/*
 * @ExtendedCucumberOptions( jsonReport =
 * "./target/Cucumber-Report/cucumber.json", jsonUsageReport =
 * "build/cucumber-usage.json", outputFolder = "./target/", detailedReport =
 * true, detailedAggregatedReport = true, overviewReport = true, usageReport =
 * true, coverageReport = false, retryCount = 0, screenShotLocation =
 * "screenshots/", screenShotSize = "300px", toPDF = true )
 */
@CucumberOptions(features = "./src/test/resources/com/hydroottawa/ccbupgrade/features", glue = {
		"com.hydroottawa.ccbupgrade.poc.stepdefs" }, plugin = { "pretty", "html:Reports/Cucumber-StandardReport",
				"json:target/Cucumber-Report/cucumber.json",
				// "json:Drivers/cucumber.json",
				// "usage:target/Cucumber-Report/cucumber-usage.json",
				//"com.cucumber.listener.ExtentCucumberFormatter:Reports/Cucumber-AdvanceReport/report.html" 
				}, 
				tags = {
						// "@Login"
						// "@MeterRead,@GenerateBill"
						// "@GenerateBill"
						//"@ReportTest" 
						//"@Adjustment_Approval_L3"
						"@test"
					  //	"@Adjustment_Approval_L3,@Adjustment_Approval_L2,@Adjustment_Approval_L1,@Adjustment_Rejection_L3,@Adjustment_Rejection_L2,@Adjustment_Rejection_L1"
					//  	"@Adjustment_Approval_L3,@Adjustment_Rejection_L3"
					//	"@Bill_Segment_Error"
					//	"@05_Autopay"
					//	"@Adjustment_Rejection_L3"
					//	"@06_Without_Autopay"
					//"@07_AddNextBQ1"
					//	"@08_ManualBQ"
					//	"@OnCycleBQ"
					//	"@07_Rates"
						}

)
public class POC_Runner extends Main {

	@AfterClass
	public static void generateReportCust() throws InterruptedException {

		System.out.println("Start generating HTML report folder");
		File reportOutputDirectory = new File("target");
		//Thread.sleep(60000);
		List<String> jsonFiles = new ArrayList<>();
		// File f = new
		// File("C:\\HydroOttawa\\CCB-Upgrade\\ccbworkspace\\ccbupgrade-tests-automation\\ccbupgrade-tests-automation-poc\\target\\Cucumber-Report\\cucumber.json");
		// System.out.println("Is file exist :: "+f.exists());
		// System.out.println("Can execute :: "+f.canExecute());
		jsonFiles.add(
				"C:\\HydroOttawa\\CCB-Upgrade\\ccbworkspace\\ccbupgrade-tests-automation\\ccbupgrade-tests-automation-poc\\target\\Cucumber-Report\\cucumber.json");
		// jsonFiles.add("C:\\HydroOttawa\\CCB-Upgrade\\ccbworkspace\\ccbupgrade-tests-automation\\ccbupgrade-tests-automation-poc\\Drivers\\cucumber.json");
		// jsonFiles.add("cucumber-report-2.json");

		String buildNumber = "1";
		String projectName = "CC&B Upgrade";
		// boolean runWithJenkins = true;

		Configuration configuration = new Configuration(reportOutputDirectory, projectName);
		// optional configuration - check javadoc
		// configuration.setRunWithJenkins(runWithJenkins);
		configuration.setBuildNumber(buildNumber);

		// additional metadata presented on main page
		configuration.addClassifications("Operating System", System.getProperty("os.name"));
		configuration.addClassifications("Browser", POC_Step_Definition.browser);
		configuration.addClassifications("Java", System.getProperty("java.version"));
		configuration.addClassifications("Selenium", "3.11");
		configuration.addClassifications("Cucumber", "4.2.0");

		// optionally add metadata presented on main page via properties file
		// List<String> classificationFiles = new ArrayList<>();
		// classificationFiles.add("properties-1.properties");
		// classificationFiles.add("properties-2.properties");
		// configuration.addClassificationFiles(classificationFiles);

		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
		Thread.sleep(1000);
		try {
			reportBuilder.generateReports();
		}

		catch (Exception e) {
			jsonFiles.add(
					"C:\\HydroOttawa\\CCB-Upgrade\\ccbworkspace\\ccbupgrade-tests-automation\\ccbupgrade-tests-automation-poc\\target\\Cucumber-Report\\cucumber.json");
			ReportBuilder reportBuilder1 = new ReportBuilder(jsonFiles, configuration);
			reportBuilder1.generateReports();
		}

	}

	@BeforeClass
	public static void deleteOldScreenshots() {
		CommonFunctions.cleanup_screenshots();
	}

}
