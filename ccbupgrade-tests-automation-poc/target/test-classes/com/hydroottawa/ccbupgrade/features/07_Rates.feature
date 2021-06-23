Feature: On Cycle BQ
@07_Rates
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
When Click on "Show Account Context" under "Current Context" dashboard
Then Click on "Rate Info" tab in "Service Agreement" menupage
Then Click on "Interval Info" tab in "Service Agreement" menupage
Then Navigate to below list from top to bottom in order to reach "Bill" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Go To Bill          |
  |Search              |
And Click on the first "BILL ID" row under the "Bill Search" table in newly opened window
Then The value of "BILL STATUS" is displayed as "Complete" under "Bill" page

Examples:
 |Account_ID|  Today_Date    | Today_Time |Request_Status|Future_Date   |BQ_|BQ_REQUEST_STATUS2|Created_Process    |
 |2644742931| %TODAY_DATE%   | 12:57A     |Waiting       |%FUTURE_DATE%|       P          |     B            |  On Cycle         |