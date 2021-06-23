Feature: I want to login to CC&B 2.7.1
Background:
Given Connect to "2.7 SIT" database and execute below SQL query to retrieve required "Account ID"
"""
select acct_id from cisadm.ci_acct_apay where end_dt is Null
"""
@Login
Scenario:  Login to CC&B with valid credential
Given Open browser "Chrome"
When Login to CCB under "%ENV%" environment with user as "username" and password as "password"
When Navigate to below list from top to bottom in order to reach "MDMR BQ Request" page
#---------------------------------------------------------------
# | Link                        |
#---------------------------------------------------------------
  |Menu                         | 
  |MDMR Testing                 |
  |MDMR BQ Request              |
And Enter below values in "MDMR BQ Request" page
#-----------------------------------------------------------------
# | Field                       |       Values             |
#-----------------------------------------------------------------
  |METER READ ID                |      265325904520        | 
And Click on "Search" button in "MDMR BQ Request" tabpage
And Click on "BROADCAST" button in "MDMR BQ Request" tabpage
And Enter below values in "MDMR BQ Request_UI MAP" UI pop up page and then click on "Save" button
#-----------------------------------------------------------------
# | Field                       |       Values             |
#-----------------------------------------------------------------
  |REQUEST START DATE           |      2019-07-12          |
  |REQUEST END DATE             |      2019-08-15          |
  |REQUEST STATUS               |      Received            |
  |REQUEST TYPE                 |      Off-Cycle           |
#####################################################################
#And Close the browser

#2019-08-19 



# //table[@id='MDMRBQRequest']//input[@value='Save']
# //table[@id='dataExplorerTable1']/thead//th
# //*[@id='dataExplorerTable1']/tbody/tr[1]/td[3]//img