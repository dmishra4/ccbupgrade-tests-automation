Feature: Bill a Customer on Budget with Autopay
Background:
Given Connect to "2.7 SIT" database and execute below SQL query to retrieve required "Account ID"
"""
select acct_id from cisadm.ci_acct_apay where end_dt is Null
"""
@05_Autopay
Scenario Outline: Bill a Customer on Budget with Autopay
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
When Click on "Show Account Context" under "Current Context" dashboard
Then Navigate to below list from top to bottom in order to reach "Bill" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Go To Bill          |
  |Search              |
And Click on the first "BILL ID" row under the "Bill Search" table in newly opened window
Then The value of "BILL STATUS" is displayed as "Complete" under "Bill" page
When Click on "Go To Account" under "Current Context" dashboard
And Click on "Auto-Pay Active" under "Alerts" dashboard
Then Click on "Budget" tab in "Account" menupage
And Click on "Recommend Budget" button in "Account" tabpage
And Click on "Save" button in "Account" mainpage
#And Enter below values in "New Budget" column under "SA_BUDGET" table in "Account" page then click on "Save" button
##---------------------------------------------------------------
## | Field               |   Value
##---------------------------------------------------------------
#  |Master_New Budget    |  <MA_Budget>        |
#  |Sub_New Budget       |  <Sub_Budget>       |
Then Click on "Characteristics" tab in "Account" menupage
And Click on first add image button and enter below values under "Characteristics" tab then click on "Save" button
#---------------------------------------------------------------
# | Field               |         Value
#---------------------------------------------------------------
  |Effective Date       | <Today_Date>          |
  |Characteristic Type  |<Characteristic_Type>  |
  |Characteristic Value |<Characteristic_Value> |
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
  |REQUEST START DATE           |      %RUNTIMEVALUE%      |
  |REQUEST END DATE             |      <Today_Date>        |
  |REQUEST STATUS               |      <Request_Status>    |
  |REQUEST TYPE                 |      <Request_Type>      |
When Click on "Go To Account" under "Current Context" dashboard
And Click on "Show Account Context - Account ID" button in "Account" tabpage
Then Navigate to below list from top to bottom in order to reach "Bill" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Go To Bill          |
  |Add                 |
And Click on "Generate" button in "Bill" tabpage
And Enter "CUTOFF DATE" as "<Today_Date>" then click on "Calculate" button in opened window
Then Click on "Freeze" button in "Bill" tabpage
Then Click on "Complete" button in "Bill" tabpage
And Enter "BILL DATE" as "<Today_Date>" then click on "Complete" button in opened window
Then The value of "BILL STATUS" is displayed as "Complete" under "Bill" page
And Close the browser
Examples:
 |Account_ID|MA_Budget|Sub_Budget|Characteristic_Type|Characteristic_Value|  Today_Date    | Today_Time |Request_Status|Request_Type|
#|6825832899|  100.00 | 100.00   | Override DUE DAY  |       5            | %TODAY_DATE%   | 12:57A     |Received      | Off-Cycle  |
#|9440593624|  100.00 | 100.00   | Override DUE DAY  |       5            | %TODAY_DATE%   | 12:57A     |Received      | Off-Cycle  |
#|6860243000|  100.00 | 100.00   | Override DUE DAY  |       5            | %TODAY_DATE%   | 12:57A     |Received      | Off-Cycle  |
#|0194933000|  100.00 | 100.00   | Override DUE DAY  |       5            | %TODAY_DATE%   | 12:57A     |Received      | Off-Cycle  |
#|4747333000|  100.00 | 100.00   | Override DUE DAY  |       5            | %TODAY_DATE%   | 12:57A     |Received      | Off-Cycle  |
#|%DB_TESTDATA%|  100.00 | 100.00| Override DUE DAY  |       5            | %TODAY_DATE%   | 12:57A     |Received      | Off-Cycle  |
 |1612272646|  100.00 | 100.00   | Override DUE DAY  |       5            | %TODAY_DATE%   | 12:57A     |Received      | Off-Cycle  |

#####################Used Data
# |6182778413|  100.00 | 100.00   | Override DUE DAY  |       5            | %TODAY_DATE%   | 12:57A     |Received      | Off-Cycle  |
# |9685553000|  100.00 | 100.00   | Override DUE DAY  |       5            | %TODAY_DATE%   | 12:57A     |Received      | Off-Cycle  |
#|7307720828|  100.00 | 100.00   | Override DUE DAY  |       5            | %TODAY_DATE%   | 12:57A     |Received      | Off-Cycle  |
#|0496953279|  100.00 | 100.00   | Override DUE DAY  |       5            | %TODAY_DATE%   | 12:57A     |Received      | Off-Cycle  |
#|3856756685|  100.00 | 100.00   | Override DUE DAY  |       5            | %TODAY_DATE%   | 12:57A     |Received      | Off-Cycle  |
#|3483475676|  100.00 | 100.00   | Override DUE DAY  |       5            | %TODAY_DATE%   | 12:57A     |Received      | Off-Cycle  |
#|6866885188|  100.00 | 100.00   | Override DUE DAY  |       5            | %TODAY_DATE%   | 12:57A     |Received      | Off-Cycle  |
# SELECT * FROM CISADM.CI_BILL where BILL_STAT_FLG='C' and acct_id in (select acct_id from cisadm.ci_acct_apay)ORDER BY CRE_DTTM DESC
#select acct_id from cisadm.ci_acct_apay where end_dt is Null