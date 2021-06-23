Feature: Manual BQ
@test
Scenario Outline: Manual BQ
Given Open browser "Chrome"
When Login to CCB under "%ENV%" environment with user as "username" and password as "password"
#Then Navigate to below list from top to bottom in order to reach "Account" page
##---------------------------------------------------------------
## | Link               |
##---------------------------------------------------------------
#  |Menu                |
#  |Customer Information|
#  |Account             |
#  |Search              |
#And Enter "Account ID" as "<Account_ID>" then click on "Search" button in opened window
#When Click on "Show Account Context" under "Current Context" dashboard
#Then Navigate to below list from top to bottom in order to reach "Service Agreement" page
##---------------------------------------------------------------
## | Link                   |
##---------------------------------------------------------------
#  |Go To Service Agreement |
#  |Search                  |
#And Click on the "Active" "Master" "Service Agreement" in newly opened window
#When Click on "Show Account Context" under "Current Context" dashboard
#Then Navigate to below list from top to bottom in order to reach "Bill" page
##---------------------------------------------------------------
## | Link               |
##---------------------------------------------------------------
#  |Go To Bill          |
#  |Search              |
#And Click on the first "BILL ID" row under the "Bill Search" table in newly opened window
#Then The value of "BILL STATUS" is displayed as "Complete" under "Bill" page
#When Click on "Go To Account" under "Current Context" dashboard
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
And The value of "REGISTER READING" is displayed as "%NOTNULL%" under "Meter Read" page
When Click on "Meter Configuration Context Menu" button in "Meter Read" tabpage
Then Navigate to below list from top to bottom in order to reach "Go To Meter Read Search" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Go To Meter Read    |
  |Add                 |
And Enter below values in "Meter Read" page
#-----------------------------------------------------------------
# | Field              |       Values             |
#-----------------------------------------------------------------
  |DATE                |       <Today_Date>       |
  |TIME                |       <Today_Time>       |
  |MR SOURCE           |       MDMR Read          |
And Select "USE ON BILL" check box
And Select "READ TYPE" and add below reading in previous "REGISTER READING" in "Meter Read" page
#--------------------------------------------------------------------------------------------------------------
# |    READ TYPE         |   REGISTER READING    |
#--------------------------------------------------------------------------------------------------------------
  |    Regular           |        100            |
  |    Regular           |         20            |
  |    Regular           |         20            |
  |    Regular           |         20            |
  |    Regular           |         20            |
And Click on "Characteristics" tab in "Meter Read" menupage
Then Enter below values in "Characteristics" tab under "Meter Read" page
#-----------------------------------------------------------------
# | Column               |       Value                   |
#-----------------------------------------------------------------
  |Characteristic Type   | User Comment for Off Cycle BQ |   
  |Sequence              | 1                             | 
  |Characteristic Value  | Manual_Reading                |                    
And Click on "Save" button in "Meter Read" mainpage
Then The value of "METER READ ID" is displayed as "%NOTNULL%" under "Meter Read" page
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
  |REQUEST START DATE           |      2019-07-20          |
  |REQUEST END DATE             |      2019-09-03          |
  |REQUEST STATUS               |      <Request_Status>    |
  |REQUEST TYPE                 |      <Request_Type>      |
#When Click on "Go To Account" under "Current Context" dashboard
#Then Navigate to below list from top to bottom in order to reach "Meter Read Search" page
##---------------------------------------------------------------
## | Link               |
##---------------------------------------------------------------
#  |Menu                |
#  |Meter Read          |
#  |Meter Read          |
#  |Search              |
#And Enter "METER READ ID" as "%RUNTIMEVALUE%" then click on "Search" button in opened window
#And Click on "Meter Read Context Menu - Meter Read ID" button in "Meter Read" tabpage
#Then Navigate to below list from top to bottom in order to reach "Display Meter Read BQ Data" page
##---------------------------------------------------------------
## | Link               |
##---------------------------------------------------------------
#  |Display Meter Read BQ Data          |
#Then The value of "Request Status" is displayed as "<BQ_REQUEST_STATUS1>" under "Meter Read" main page 
##Then The value of "Request Start Date" is displayed as "%RUNTIMEVALUE%" under "Meter Read" main page 
###########################################################################################
#When Navigate to below list from top to bottom in order to reach "Meter/Item Search" page
##---------------------------------------------------------------
## | Link                        |
##---------------------------------------------------------------
#  |Menu                         | 
#  |Meter                        |
#  |Meter/Item Search            |
#And Enter below values in "Meter/Item Search" page
##-----------------------------------------------------------------
## | Field              |       Values             |
##-----------------------------------------------------------------
#  |Account ID          |      <Account_ID>        |
#And Click on "Search - ACCOUNT ID" button in "Meter/Item Search" tabpage
#And Click on "Meter Context Menu" image button for "Active" meter under "METER/ITEM INFORMATION" column in "Meter/Item Search" page
#Then Navigate to below list from top to bottom in order to reach "Go To Meter Read Search" page
##---------------------------------------------------------------
## | Link               |
##---------------------------------------------------------------
#  |Go To Meter Read    |
#  |Search              |
#And Click on the first "METER READ ID" row under the "Meter Reading" table in newly opened window
#Then The value of "METER CONFIGURATION ID" is displayed as "%NOTNULL%" under "Meter Read" page
#And The value of "READ DATE" is displayed as "%NOTNULL%" under "Meter Read" page
#And The value of "REGISTER READING" is displayed as "%NOTNULL%" under "Meter Read" page
#When Click on "Meter Configuration Context Menu" button in "Meter Read" tabpage
#Then Navigate to below list from top to bottom in order to reach "Go To Meter Read Search" page
##---------------------------------------------------------------
## | Link               |
##---------------------------------------------------------------
#  |Go To Meter Read    |
#  |Add                 |
#And Enter below values in "Meter Read" page
##-----------------------------------------------------------------
## | Field              |       Values             |
##-----------------------------------------------------------------
#  |DATE                |       <Future_Date>       |
#  |TIME                |       <Today_Time>       |
#  |MR SOURCE           |       MDMR Read          |
#And Select "USE ON BILL" check box
#And Select "READ TYPE" and add below reading in previous "REGISTER READING" in "Meter Read" page
##--------------------------------------------------------------------------------------------------------------
## |    READ TYPE         |   REGISTER READING    |
##--------------------------------------------------------------------------------------------------------------
#  |    Regular           |        100            |
#  |    Regular           |         20            |
#  |    Regular           |         20            |
#  |    Regular           |         20            |
#  |    Regular           |         20            |
#And Click on "Characteristics" tab in "Meter Read" menupage
#Then Enter below values in "Characteristics" tab under "Meter Read" page
##-----------------------------------------------------------------
## | Column               |       Value                   |
##-----------------------------------------------------------------
#  |Characteristic Type   | User Comment for Off Cycle BQ |   
#  |Sequence              | 1                             | 
#  |Characteristic Value  | Manual_Reading                |                    
#And Click on "Save" button in "Meter Read" mainpage
#Then The value of "METER READ ID" is displayed as "%NOTNULL%" under "Meter Read" page
#And Click on "Meter Read Context Menu - Meter Read ID" button in "Meter Read" tabpage
#Then Navigate to below list from top to bottom in order to reach "Display Meter Read BQ Data" page
##---------------------------------------------------------------
## | Link               |
##---------------------------------------------------------------
#  |Display Meter Read BQ Data          |
#Then The value of "Request Status" is displayed as "<BQ_REQUEST_STATUS2>" under "Meter Read" main page 
##Then The value of "Request Start Date" is displayed as "%RUNTIMEVALUE%" under "Meter Read" main page 
#Then Navigate to below list from top to bottom in order to reach "Batch Job Submission" page
##---------------------------------------------------------------
## | Link               |
##---------------------------------------------------------------
#  |Menu                |
#  |Tools               |
#  |Batch Job Submission|
#  |Add                 |
#And Enter below values in "Batch Job Submission" page
##-----------------------------------------------------------------
## | Field              |       Values             |
##-----------------------------------------------------------------
#  |BATCH CODE          |       CM-UPDBQ           |
#  |METER READ ID       |      %RUNTIMEVALUE%      |
#When Click on "Save" button in "Batch Job Submission" page
#Then The value of "BATCH JOB STATUS" is displayed as "Ended" under "Batch Job Submission" page
#Then Navigate to below list from top to bottom in order to reach "Meter Read Search" page
##---------------------------------------------------------------
## | Link               |
##---------------------------------------------------------------
#  |Menu                |
#  |Meter Read          |
#  |Meter Read          |
#  |Search              |
#And Enter "METER READ ID" as "%RUNTIMEVALUE%" then click on "Search" button in opened window
#And Click on "Meter Read Context Menu - Meter Read ID" button in "Meter Read" tabpage
#Then Navigate to below list from top to bottom in order to reach "Display Meter Read BQ Data" page
##---------------------------------------------------------------
## | Link               |
##---------------------------------------------------------------
#  |Display Meter Read BQ Data          |
#Then The value of "Request Status" is displayed as "<BQ_REQUEST_STATUS3>" under "Meter Read" main page 
##Then The value of "Request Start Date" is displayed as "%RUNTIMEVALUE%" under "Meter Read" main page 
When Click on "Go To Account" under "Current Context" dashboard
And Click on "Show Account Context - Account ID" button in "Account" tabpage
Then Navigate to below list from top to bottom in order to reach "Bill" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Go To Bill          |
  |Add                 |
And Click on "Generate" button in "Bill" tabpage
And Enter "CUTOFF DATE" as "<Cutoff_Date>" then click on "Calculate" button in opened window
Then Click on "Freeze" button in "Bill" tabpage
Then Click on "Complete" button in "Bill" tabpage
And Enter "BILL DATE" as "<Cutoff_Date>" then click on "Complete" button in opened window
Then The value of "BILL STATUS" is displayed as "Complete" under "Bill" page
And Close the browser

Examples:
  |Account_ID|  Today_Date    | Today_Time |Request_Status|Future_Date   |BQ_REQUEST_STATUS1|BQ_REQUEST_STATUS2|BQ_REQUEST_STATUS3|Request_Type|Cutoff_Date|
  |2511391967| 2019-09-03     | 12:57A     |Received      |%FUTURE_DATE% |       W          |     P            |       B           | Off-Cycle|2019-09-03  |
# |3664763771| 2019-08-14     | 12:57A     |Received      |%FUTURE_DATE% |       W          |     P            |       B           | Off-Cycle|2019-08-14  |
# |3392863000| 2019-07-30     | 12:57A     |Received      |%FUTURE_DATE% |       W          |     P            |       B           | Off-Cycle|2019-07-30  |
# |0672824309| 2019-07-20     | 12:57A     |Received      |%FUTURE_DATE% |       W          |     P            |       B           | Off-Cycle|2019-07-20  |
# |9842670274| 2019-09-05     | 12:57A     |Received      |%FUTURE_DATE% |       W          |     P            |       B           | Off-Cycle|2019-09-05  |
# |0124853000| 2019-08-26     | 12:57A     |Received      |%FUTURE_DATE% |       W          |     P            |       B           | Off-Cycle|2019-08-26  |
# |0194933000| 2019-09-16     | 12:57A     |Received      |%FUTURE_DATE% |       W          |     P            |       B           | Off-Cycle|2019-09-16|
# |4856058360| %TODAY_DATE%   | 12:57A     |Waiting       |%FUTURE_DATE% |       W          |     P            |       B           |
# |0000382016| %TODAY_DATE%   | 12:57A     |Exception     |%FUTURE_DATE% |       E          |     P            |       B           |
# |0693333000| %TODAY_DATE%   | 12:57A     |Cancelled     |%FUTURE_DATE% |       X          |     P            |       B           |
# |7283653000 | %TODAY_DATE%  | 12:57A     |BQ Data       |%FUTURE_DATE% |       B          |     P            |       B           |
# |0000382016| %TODAY_DATE%   | 12:57A     |Received      |%FUTURE_DATE%|       R           |     P            |       B           |
#|%DB_TESTDATA%| Override DUE DAY|       5            | %TODAY_DATE%   | 12:57A     |Received |