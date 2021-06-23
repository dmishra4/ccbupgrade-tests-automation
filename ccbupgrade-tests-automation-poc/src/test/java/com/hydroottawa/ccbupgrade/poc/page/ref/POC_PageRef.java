package com.hydroottawa.ccbupgrade.poc.page.ref;

import org.openqa.selenium.By;

public interface POC_PageRef {

	/***************** Login Page ******************************/
	


	public static final By inputUserID = By.xpath("//input[@id='userId']");
	public static final By inputPassword = By.xpath("//input[@id='password']");
	public static final By btnLogin = By.xpath("//input[@id='loginButton']");

	/** Control Central Heading ( frame : menu) */
	public static final By hCtrlCentralTitle = By.xpath("//*[@id='ptitle']");
	public static final By btnRefresh = By.xpath("//input[@id='IM_REFRESH']");
	public static final String strNavigate= "//*[text()='%s']";
	
	
	/** Control Central Main tab ( frame : tabPage) */
	public static final By listSearchBy = By.xpath("//*[@id='multiQueryZoneFilters1']");
	public static final By inputAccountID = By.xpath("//*[@id='accountId1']");
	public static final By inputSearch = By.xpath("//*[@value='Search']");
	// MR Upload Staging ( frame : tabPage)
	public static final By inputBatchNumber = By.xpath("//input[@id='BADGE_NBR']");
	public static final By inputReadDate = By.xpath("//input[@id='READ_DTTM_FWDTTM_P1']");
	public static final By inputTime = By.xpath("//input[@id='READ_DTTM_FWDTTM_P2']");
	public static final By selectMeterSource = By.xpath("//Select[@id='MR_SOURCE_CD']");
	public static final By checkboxUSE_ON_BILL = By.xpath("//Select[@id='MR_SOURCE_CD']");
	public static final By inputMRUploadStagingID = By.xpath("//input[@id='MR_STAGE_UP_ID']");
	public static final By inputMRConfigurationID = By.xpath("//input[@id='MTR_CONFIG_ID']");
	public static final By txtMRReadID = By.xpath("//*[@id='MR_ID']");
	
	// MR Upload Staging ( frame : dataframe)  // Parent of dataframe is tabpage
	
	// Note :: Meter Read (frame :: MR_CHAR) // Parent of MR_CHAR is tabpage
	
	public static final By tableImgPlusBtn = By.xpath("//img[@id='IM_STU:0$RegList_plusBtn']");
	public static final String tblStringSequence= "//table[@id='dataTable']//tbody/tr[%s]/td[%s]//input";
	public static final String  tblSelectdataframe = "//table[@id='dataTable']//tbody/tr[%s]/td[%s]//Select";
	public static final String  tblStringRegisterReading = "//table[@id='dataTable']//tbody/tr[%s]/td[%s]//input";
	public static final String tblStringCharacteristicValue= "//table[@id='dataTable']//tbody/tr[%s]/td[%s]//input";
	

	
	//Batch Job Submission ( frame : tabPage)
	public static final By inputBatchCode = By.xpath("//input[@id='BATCH_CD']");
	public static final By labelBatchDescription = By.xpath("//*[@id='BATCH_DESCR']");
	public static final By labelBatchJobStatusFlag = By.xpath("//*[@id='BATCH_JOB_STAT_FLG']");
	public static final By inputBatchBusinessDate = By.xpath("//input[@id='PROCESS_DT']");
	
	//Bill ( frame : tabPage)
	
	public static final By btnGenerate = By.xpath("//input[@value='Generate']");
	public static final By btnFreezeComplete = By.xpath("//input[@id='ACTION_FRZ_CMPL_SW']");
	public static final By btnFreeze = By.xpath("//input[@id='ACTION_FREEZE_SW']");
	public static final By btnbillComplete = By.xpath("//input[@id='ACTION_COMPLETE_SW']");
	
	// New Windows 
	
	public static final By inputCutoffDate = By.xpath("//input[@id='CUTOFF_DT']");
	public static final By inputBillDate = By.xpath("//input[@id='OBC_BILL_DT']");
	public static final By btnComplete = By.xpath("//input[@id='ACTION_COMPLETE_SW']");
	
	public static final By btnCollectionsearch = By.xpath("//input[@id='BU_Main_search']"); 

	
	
	
	

}
