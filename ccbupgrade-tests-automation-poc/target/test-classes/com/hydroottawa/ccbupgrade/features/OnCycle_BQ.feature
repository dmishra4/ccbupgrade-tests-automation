Feature: On Cycle BQ
@OnCycleBQ
Scenario Outline: On Cycle BQ
Given Open browser "Chrome"
When Login to CCB under "%ENV%" environment with user as "username" and password as "password"
Then Navigate to below list from top to bottom in order to reach "Account" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Menu                |
  |Customer Information|
  |Account             |
  |Search              |
And Enter "Account ID" as "<Account_ID>" then click on "Search" button in opened window
When Click on "Show Account Context" under "Current Context" dashboard
Then Navigate to below list from top to bottom in order to reach "Service Agreement" page
#---------------------------------------------------------------
# | Link                   |
#---------------------------------------------------------------
  |Go To Service Agreement |
  |Search                  |
And Click on the "Active" "Master" "Service Agreement" in newly opened window
Then Click on "SA/SP" tab in "Service Agreement" menupage
And The value of "SERVICE POINT ID" is displayed as "%NOTNULL%" under "Service Agreement" page
Then Navigate to below list from top to bottom in order to reach "Batch Job Submission" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Menu                |
  |Tools               |
  |Batch Job Submission|
  |Add                 |
And Enter below values in "Batch Job Submission" page
#-----------------------------------------------------------------
# | Field              |       Values             |
#-----------------------------------------------------------------
  |BATCH CODE          |       CM-CREBQ           |
  |BATCH BUSINESS DATE |     2019-08-15           |
  |SP ID               |      %RUNTIMEVALUE%      |
When Click on "Save" button in "Batch Job Submission" page
Then The value of "BATCH JOB STATUS" is displayed as "Ended" under "Batch Job Submission" page
When Navigate to below list from top to bottom in order to reach "Meter/Item Search" page
#---------------------------------------------------------------
# | Link                        |
#---------------------------------------------------------------
  |Menu                         | 
  |Meter                        |
  |Meter/Item Search            |
And Enter below values in "Meter/Item Search" page
#-----------------------------------------------------------------
# | Field              |       Values             |
#-----------------------------------------------------------------
  |Account ID          |      <Account_ID>        |
And Click on "Search - ACCOUNT ID" button in "Meter/Item Search" tabpage
And Click on "Meter Context Menu" image button for "Active" meter under "METER/ITEM INFORMATION" column in "Meter/Item Search" page
Then Navigate to below list from top to bottom in order to reach "Go To Meter Read Search" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Go To Meter Read    |
  |Search              |
And Click on the first "METER READ ID" row under the "Meter Reading" table in newly opened window
Then The value of "METER CONFIGURATION ID" is displayed as "%NOTNULL%" under "Meter Read" page
And The value of "READ DATE" is displayed as "%NOTNULL%" under "Meter Read" page
Then The value of "METER READ ID" is displayed as "%NOTNULL%" under "Meter Read" page
And Click on "Meter Read Context Menu - Meter Read ID" button in "Meter Read" tabpage
Then Navigate to below list from top to bottom in order to reach "Display Meter Read BQ Data" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Display Meter Read BQ Data          |
Then The value of "Request Status" is displayed as "<BQ_REQUEST_STATUS1>" under "Meter Read" main page 
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
  |METER READ ID                |      %RUNTIMEVALUE%      |
And Click on "Search" button in "MDMR BQ Request" tabpage
And Click on "BROADCAST" button in "MDMR BQ Request" tabpage
And Enter below values in "MDMR BQ Request_UI MAP" UI pop up page and then click on "Save" button
#-----------------------------------------------------------------
# | Field                       |       Values             |
#-----------------------------------------------------------------
#  |REQUEST START DATE           |      %RUNTIMEVALUE%      |
#  |REQUEST END DATE             |      <Today_Date>        |
#  |REQUEST STATUS                |      <Request_Status>    |
   |Created Process              |      <Created_Process>      |
Then Navigate to below list from top to bottom in order to reach "Batch Job Submission" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Menu                |
  |Tools               |
  |Batch Job Submission|
  |Add                 |
And Enter below values in "Batch Job Submission" page
#-----------------------------------------------------------------
# | Field              |       Values             |
#-----------------------------------------------------------------
  |BATCH CODE          |       CM-UPDBQ           |
  |METER READ ID       |      %RUNTIMEVALUE%      |
When Click on "Save" button in "Batch Job Submission" page
Then The value of "BATCH JOB STATUS" is displayed as "Ended" under "Batch Job Submission" page
Then Navigate to below list from top to bottom in order to reach "Meter Read Search" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Menu                |
  |Meter Read          |
  |Meter Read          |
  |Search              |
And Enter "METER READ ID" as "%RUNTIMEVALUE%" then click on "Search" button in opened window
And Click on "Meter Read Context Menu - Meter Read ID" button in "Meter Read" tabpage
Then Navigate to below list from top to bottom in order to reach "Display Meter Read BQ Data" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Display Meter Read BQ Data          |
Then The value of "Request Status" is displayed as "<BQ_REQUEST_STATUS2>" under "Meter Read" main page 
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
  |METER READ ID                |      %RUNTIMEVALUE%      |
And Click on "Search" button in "MDMR BQ Request" tabpage
And Click on "BROADCAST" button in "MDMR BQ Request" tabpage
And Enter below values in "MDMR BQ Request_UI MAP" UI pop up page and then click on "Save" button
#-----------------------------------------------------------------
# | Field                       |       Values             |
#-----------------------------------------------------------------
#  |REQUEST START DATE           |      %RUNTIMEVALUE%      |
#  |REQUEST END DATE             |      <Today_Date>        |
#  |REQUEST STATUS               |      <Request_Status>   |
   |Created Process              |      <Created_Process>   |
#And Close the browser
Examples:
  |Account_ID |  Today_Date    | Today_Time |Request_Status|Future_Date   |BQ_REQUEST_STATUS1|BQ_REQUEST_STATUS2|Created_Process    |
  |1281443000 | %TODAY_DATE%    | 12:57A      |Waiting      |%FUTURE_DATE%|       P          |     B            |  On Cycle         |
# |6862666064 | %TODAY_DATE%    | 12:57A      |Waiting      |%FUTURE_DATE%|       P          |     B            |  On Cycle         |
# |0682858780 | %TODAY_DATE%   | 12:57A     |Waiting       |%FUTURE_DATE%|       P          |     B            |  On Cycle         |
# |5619643000 | %TODAY_DATE%   | 12:57A     |Waiting       |%FUTURE_DATE%|       P          |     B            |  On Cycle         |
# |0682858780| %TODAY_DATE%   | 12:57A     |Waiting       |%FUTURE_DATE% |       P          |     B            |  On Cycle         |
# |4856058360| %TODAY_DATE%   | 12:57A     |Waiting       |%FUTURE_DATE% |       B          |     P            |       B           |
# |0000382016| %TODAY_DATE%   | 12:57A     |Exception     |%FUTURE_DATE% |       E          |     P            |       B           |
# |0693333000| %TODAY_DATE%   | 12:57A     |Cancelled     |%FUTURE_DATE% |       X          |     P            |       B           |
# |7283653000 | %TODAY_DATE%  | 12:57A     |BQ Data       |%FUTURE_DATE% |       B          |     P            |       B           |
# |0000382016| %TODAY_DATE%   | 12:57A     |Received      |%FUTURE_DATE%|       R           |     P            |       B           |
#|%DB_TESTDATA%| Override DUE DAY|       5            | %TODAY_DATE%   | 12:57A     |Received |