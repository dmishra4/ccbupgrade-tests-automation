package com.hydroottawa.ccbupgrade.poc.utils;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class login_ach {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
       // Configuring the system properties of chrome driver
		System.setProperty("webdriver.chrome.driver","./src/test/resources/com/hydroottawa/ccbupgrade/drivers/chromedriver80.0.3987.106.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");  // To avoid unwanted message on console
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
		options.addArguments("enable-automation");
		options.addArguments("--disable-features=NetworkService");
		options.addArguments("--dns-prefetch-disable");
		options.addArguments("--disable-extensions");
		//Initializing the browser driver
		WebDriver driver = new ChromeDriver(options);
		
	//	System.setProperty("webdriver.gecko.driver","./src/test/resources/com/hydroottawa/ccbupgrade/drivers/geckodriverv0.26.0-win64.exe");
	//	WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Navigating through a particular website
		driver.get("http://localhost:10039/wps/portal");
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Log In']")));
		driver.findElement(By.xpath("//a[text()='Log In']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='userID']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='userID']")).sendKeys("wpsadmin");
		//Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("wpsadmin");
		driver.findElement(By.xpath("//input[@id='login.button.login']")).click();
	//driver.quit();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='wpToolbarSitesNavMenu']/img[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='Saudi Payments']")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Service Request")).click();
		//driver.findElement(By.xpath("//ul[@id='wpSideNav']/li[2]/a")).click();
		Thread.sleep(2000);
		//driver.findElement(By.xpath("//li[@class='hasSubMenu active']//ul[@class='subMenu']//li[1]/a")).click();
		//driver.findElement(By.partialLinkText("Debit Cap Management")).click();
		driver.findElement(By.linkText("Debit Cap Management")).click();
		driver.findElement(By.xpath("//button[@id='changeDebitCap']")).click();
		driver.findElement(By.xpath("//input[@id='debCap']")).sendKeys("5000500");
		driver.findElement(By.xpath("//textarea[@id='reason']")).sendKeys("Business Requirement");
		driver.findElement(By.xpath("//form[@name='changeDebitCapForm']//button[@value='Submit']")).click();
		driver.findElement(By.xpath("//button[@id='yesModal']")).click();
		String DebitCapAlertMsg= driver.findElement(By.xpath("//a[@class='alert-link']")).getText();
		System.out.println("Messgae :: "+DebitCapAlertMsg);
		String DebitCapAlertMsg1= driver.findElement(By.xpath("//*[@id='successMesg']")).getText();
		System.out.println("Messgae1 :: "+DebitCapAlertMsg1);

	}

}
