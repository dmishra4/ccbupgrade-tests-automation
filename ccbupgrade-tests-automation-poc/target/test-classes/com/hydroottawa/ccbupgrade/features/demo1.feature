Feature: Customer Balance validation
Background:
Given Connect to "SIT" database and execute below SQL query to retrieve required "Account ID"
"""
select acct_id,acct_balance from sit_acct_table where status is Active
"""
@CustomerBalanceValidation
Scenario Outline:  Verify whether given customer account having balance amount or not
Given Open browser "Chrome"
When Login to CCB under "%ENV%" environment with user as "username" and password as "password"
And Navigate to below list from top to bottom in order to reach "Customer Account Summary" page
#---------------------------------------------------------------
# | Link                        |
#---------------------------------------------------------------
  |Menu                         | 
  |Account Summary              |

And Enter below values in "Customer Account Summary" page
#-----------------------------------------------------------------
# | Field                       |       Values             |
#-----------------------------------------------------------------
  |Account Number                |      <Account_ID>        | 
  | Account Type                 |      <Account_Type>      |
  
And Click on "Balance" button in "Customer Account Summary" tabpage
Then Verify the balance should be "<balance>" in "<currency>"
And Close the browser
Examples: 
 |Account_ID   |Account_Type|balance         |currency     |
 |%DB_TESTDATA%| Saving     |%DB_TESTBALANCE%|   INR       |
 | 4567839201  | Saving     |  5000          |   USD       |