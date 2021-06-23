Feature: Adjustment Rejection for Residentail Customer Class-REFUNDAP
Background:
Given Connect to "2.7 SIT" database and execute below SQL query to retrieve required "Account ID"
"""
SELECT ACCT_ID from cisadm.ci_sa where acct_id in (select acct_id from cisadm.ci_acct where cust_cl_cd='R') and sa_status_flg='20' 

"""
@Adjustment_Rejection_L3
Scenario Outline: Adjustment Approval till level 2 and then Reject for Residentail customer class- REFUNDAP
Given Open browser "Chrome"
When Login to CCB under "%ENV%" environment with user as "username" and password as "password"
Then Navigate to below list from top to bottom in order to reach "Account Search" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Menu                |
  |Customer Information|
  |Account             |
  |Search              |
And Enter "Account ID" as "<RESIDENTIAL_ACCOUNT_ID>" then click on "Search" button in opened window
When Click on "Show Account Context" under "Current Context" dashboard
Then Navigate to below list from top to bottom in order to reach "Adjustment" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Go To Adjustment    |
  |Add                 |
And Click on the "Active" "Master" "Service Agreement" in newly opened window
Then The value of "SA ID" is displayed as "%NOTNULL%" under "Adjustment" page
And Enter below values in "Adjustment" page
#-----------------------------------------------------------------
# | Field              |       Values             |
#-----------------------------------------------------------------
  |ADJUSTMENT TYPE     |      <ADJUSTMENT_TYPE>   |
  |AMOUNT              |      <ADJUSTMENT_AMOUNT> |
And Click on "Generate" button in "Adjustment" tabpage
And Enter "ACCOUNTING DATE" as "<ACCOUNTING_DATE>" then click on "Calculate" button in opened window
Then The value of "Adjustment ID" is displayed as "%NOTNULL%" under "Adjustment" page
Then Click on "Submit for Approval" button in "Adjustment" tabpage
And Click on "Approval" tab in "Adjustment" menupage
And Click on "TO DO" "link" under "Approval Request" zone in "Adjustment" Page
And Click on "Keys/Values" tab in "TO DO Entry" menupage
Then The value of "TO DO ID" is displayed as "%NOTNULL%" under "To Do Entry" page
Then The value of "SORT KEYS" is displayed as "<ADJUSTMENT_AMOUNT>" under "To Do Entry" page
Then The value of "DRILL KEYS" is displayed as "%NOTNULL%" under "To Do Entry" page
And Close the browser
Given Open browser "Chrome"
When Login to CCB under "%ENV%" environment with user as "username" and password as "password"
Then Ensure user will be landed to "Control Central" after successful login
And Navigate to below list from top to bottom in order to reach "Adjustment Search" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Menu                |
  |Financial           |
  |Adjustment          |
  |Search              |
Then Enter the runtime value of "ADJUSTMENT ID" and click on "Search" button
Then The value of "Adjustment ID" is displayed as "%NOTNULL%" under "Adjustment" page
And Click on "Approval" tab in "Adjustment" menupage
And Click on "Approve" "link" under "Approval Request Level 1" zone in "Adjustment" Page
And Close the browser
Given Open browser "Chrome"
When Login to CCB under "%ENV%" environment with user as "username" and password as "password"
Then Ensure user will be landed to "Control Central" after successful login
And Navigate to below list from top to bottom in order to reach "Adjustment Search" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Menu                |
  |Financial           |
  |Adjustment          |
  |Search              |
Then Enter the runtime value of "ADJUSTMENT ID" and click on "Search" button
Then The value of "Adjustment ID" is displayed as "%NOTNULL%" under "Adjustment" page
And Click on "Approval" tab in "Adjustment" menupage
And Click on "Approve" "link" under "Approval Request Level 2" zone in "Adjustment" Page
And Close the browser
Given Open browser "Chrome"
When Login to CCB under "%ENV%" environment with user as "username" and password as "password"
Then Ensure user will be landed to "Control Central" after successful login
And Navigate to below list from top to bottom in order to reach "Adjustment Search" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Menu                |
  |Financial           |
  |Adjustment          |
  |Search              |
Then Enter the runtime value of "ADJUSTMENT ID" and click on "Search" button
Then The value of "Adjustment ID" is displayed as "%NOTNULL%" under "Adjustment" page
And Click on "Approval" tab in "Adjustment" menupage
And Click on "Reject" "link" under "Reject Request Level 3" zone in "Adjustment" Page
And Enter "REJECT REASON" as "<REJECT_REASON>" then click on "OK" button in opened window
# And Click on "Expand zone - Approval Request Log" button in "Adjustment" tabpage
And Close the browser
Examples:
 |RESIDENTIAL_ACCOUNT_ID| CIS_DIVISION    |ADJUSTMENT_TYPE  |ADJUSTMENT_AMOUNT  |ACCOUNTING_DATE| REJECT_REASON       |
#|      7556626856      | Hydro Ottawa    |  REFUNDAP       |    28000.00       |2019-07-15     | Incorrect Adjustment|
#|      1807491437      | Hydro Ottawa    |  REFUNDAP       |    29000.00       |%TODAY_DATE%   | Incorrect Adjustment|
 |      %DB_TESTDATA%   | Hydro Ottawa    |  REFUNDAP       |    29000.00       |%TODAY_DATE%   | Incorrect Adjustment|




###############################################################################################
########################## Adjectment Rejection at Level 2 ####################################
###############################################################################################
@Adjustment_Rejection_L2
Scenario Outline: Adjustment Approval till level 1 and then Reject for Residentail customer class- REFUNDAP
Given Open browser "Chrome"
When Login to CCB under "%ENV%" environment with user as "username" and password as "password"
Then Navigate to below list from top to bottom in order to reach "Account Search" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Menu                |
  |Customer Information|
  |Account             |
  |Search              |
And Enter "Account ID" as "<RESIDENTIAL_ACCOUNT_ID>" then click on "Search" button in opened window
When Click on "Show Account Context" under "Current Context" dashboard
Then Navigate to below list from top to bottom in order to reach "Adjustment" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Go To Adjustment    |
  |Add                 |
And Click on the "Active" "Master" "Service Agreement" in newly opened window
Then The value of "SA ID" is displayed as "%NOTNULL%" under "Adjustment" page
And Enter below values in "Adjustment" page
#-----------------------------------------------------------------
# | Field              |       Values             |
#-----------------------------------------------------------------
  |ADJUSTMENT TYPE     |      <ADJUSTMENT_TYPE>   |
  |AMOUNT              |      <ADJUSTMENT_AMOUNT> |
And Click on "Generate" button in "Adjustment" tabpage
And Enter "ACCOUNTING DATE" as "<ACCOUNTING_DATE>" then click on "Calculate" button in opened window
Then The value of "Adjustment ID" is displayed as "%NOTNULL%" under "Adjustment" page
Then Click on "Submit for Approval" button in "Adjustment" tabpage
And Click on "Approval" tab in "Adjustment" menupage
And Click on "TO DO" "link" under "Approval Request" zone in "Adjustment" Page
And Click on "Keys/Values" tab in "TO DO Entry" menupage
Then The value of "TO DO ID" is displayed as "%NOTNULL%" under "To Do Entry" page
Then The value of "SORT KEYS" is displayed as "<ADJUSTMENT_AMOUNT>" under "To Do Entry" page
Then The value of "DRILL KEYS" is displayed as "%NOTNULL%" under "To Do Entry" page
#And Click on "Approve" "button" under "Approval Request" zone in "Adjustment" Page
And Close the browser
Given Open browser "Chrome"
When Login to CCB under "%ENV%" environment with user as "username" and password as "password"
Then Ensure user will be landed to "Control Central" after successful login
And Navigate to below list from top to bottom in order to reach "Adjustment Search" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Menu                |
  |Financial           |
  |Adjustment          |
  |Search              |
Then Enter the runtime value of "ADJUSTMENT ID" and click on "Search" button
Then The value of "Adjustment ID" is displayed as "%NOTNULL%" under "Adjustment" page
And Click on "Approval" tab in "Adjustment" menupage
And Click on "Approve" "link" under "Approval Request Level 1" zone in "Adjustment" Page
And Close the browser
Given Open browser "Chrome"
When Login to CCB under "%ENV%" environment with user as "username" and password as "password"
Then Ensure user will be landed to "Control Central" after successful login
And Navigate to below list from top to bottom in order to reach "Adjustment Search" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Menu                |
  |Financial           |
  |Adjustment          |
  |Search              |
Then Enter the runtime value of "ADJUSTMENT ID" and click on "Search" button
Then The value of "Adjustment ID" is displayed as "%NOTNULL%" under "Adjustment" page
And Click on "Approval" tab in "Adjustment" menupage
And Click on "Reject" "link" under "Reject Request Level 2" zone in "Adjustment" Page
And Enter "REJECT REASON" as "<REJECT_REASON>" then click on "OK" button in opened window
And Close the browser
Examples:
 |RESIDENTIAL_ACCOUNT_ID| CIS_DIVISION    |ADJUSTMENT_TYPE  |ADJUSTMENT_AMOUNT  |ACCOUNTING_DATE| REJECT_REASON       |
#|      7556626856      | Hydro Ottawa    |  REFUNDAP       |    28000.00       |2019-07-15     | Incorrect Adjustment|
#|      1807491437      | Hydro Ottawa    |  REFUNDAP       |    29000.00       |%TODAY_DATE%   | Incorrect Adjustment|
 |      %DB_TESTDATA%   | Hydro Ottawa    |  REFUNDAP       |    29000.00       |%TODAY_DATE%   | Incorrect Adjustment|






###############################################################################################
########################## Adjustment Rejection at Level 1 ####################################
###############################################################################################
@Adjustment_Rejection_L1
Scenario Outline: Adjustment Rejection at level 1 for Residentail customer class- REFUNDAP
Given Open browser "Chrome"
When Login to CCB under "%ENV%" environment with user as "username" and password as "password"
Then Navigate to below list from top to bottom in order to reach "Account Search" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Menu                |
  |Customer Information|
  |Account             |
  |Search              |
And Enter "Account ID" as "<RESIDENTIAL_ACCOUNT_ID>" then click on "Search" button in opened window
When Click on "Show Account Context" under "Current Context" dashboard
Then Navigate to below list from top to bottom in order to reach "Adjustment" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Go To Adjustment    |
  |Add                 |
And Click on the "Active" "Master" "Service Agreement" in newly opened window
Then The value of "SA ID" is displayed as "%NOTNULL%" under "Adjustment" page
And Enter below values in "Adjustment" page
#-----------------------------------------------------------------
# | Field              |       Values             |
#-----------------------------------------------------------------
  |ADJUSTMENT TYPE     |      <ADJUSTMENT_TYPE>   |
  |AMOUNT              |      <ADJUSTMENT_AMOUNT> |
And Click on "Generate" button in "Adjustment" tabpage
And Enter "ACCOUNTING DATE" as "<ACCOUNTING_DATE>" then click on "Calculate" button in opened window
Then The value of "Adjustment ID" is displayed as "%NOTNULL%" under "Adjustment" page
Then Click on "Submit for Approval" button in "Adjustment" tabpage
And Click on "Approval" tab in "Adjustment" menupage
And Click on "TO DO" "link" under "Approval Request" zone in "Adjustment" Page
And Click on "Keys/Values" tab in "TO DO Entry" menupage
Then The value of "TO DO ID" is displayed as "%NOTNULL%" under "To Do Entry" page
Then The value of "SORT KEYS" is displayed as "<ADJUSTMENT_AMOUNT>" under "To Do Entry" page
Then The value of "DRILL KEYS" is displayed as "%NOTNULL%" under "To Do Entry" page
#And Click on "Approve" "button" under "Approval Request" zone in "Adjustment" Page
And Close the browser
Given Open browser "Chrome"
When Login to CCB under "%ENV%" environment with user as "username" and password as "password"
Then Ensure user will be landed to "Control Central" after successful login
And Navigate to below list from top to bottom in order to reach "Adjustment Search" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Menu                |
  |Financial           |
  |Adjustment          |
  |Search              |
Then Enter the runtime value of "ADJUSTMENT ID" and click on "Search" button
Then The value of "Adjustment ID" is displayed as "%NOTNULL%" under "Adjustment" page
And Click on "Approval" tab in "Adjustment" menupage
And Click on "Reject" "link" under "Reject Request Level 1" zone in "Adjustment" Page
And Enter "REJECT REASON" as "<REJECT_REASON>" then click on "OK" button in opened window
And Close the browser
Examples:
 |RESIDENTIAL_ACCOUNT_ID| CIS_DIVISION    |ADJUSTMENT_TYPE  |ADJUSTMENT_AMOUNT  |ACCOUNTING_DATE| REJECT_REASON       |
#|      7556626856      | Hydro Ottawa    |  REFUNDAP       |    28000.00       |2019-07-15     | Incorrect Adjustment|
#|      1807491437      | Hydro Ottawa    |  REFUNDAP       |    29000.00       |2019-07-15     | Incorrect Adjustment|
 |      %DB_TESTDATA%   | Hydro Ottawa    |  REFUNDAP       |    29000.00       |%TODAY_DATE%   | Incorrect Adjustment|
 