Feature: APA Payment Arrangement
@APA_Payment_Arrangement
Scenario Outline: APA Payment Arrangement
Given Open browser "Chrome"
When Login to CCB under "%ENV%" environment with user as "username" and password as "password"
Then Navigate to below list from top to bottom in order to reach "Collection Process Search" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Menu                |
  |Credit & Collection |
  |Collection Process  |
  |Search              |
And Enter "Collection Process ID" as "<Collection_Process_Active>" then click on "Search" button in opened window
Then The value of "Collection STATUS" is displayed as "Active" under "Collection Process" page  
When Click on "Go To Account" under "Current Context" dashboard
And Click on "Show Account Context - Account ID" button in "Account" tabpage
Then Navigate to below list from top to bottom in order to reach "Adjustment" page
#---------------------------------------------------------------
# | Link                        |
#---------------------------------------------------------------
  |Go To Payment Arrangement    |
  |Add                          |
And Enter below values in "Payment Arrangement" page
#-----------------------------------------------------------------
# | Field                |       Values               |
#-----------------------------------------------------------------
  |INSTALLMENTS          |       <INSTALLMENTS>       |
  |CIS DIVISION          |       <CIS DIVISION>       |
  |SA TYPE               |       <SA TYPE>            |
And Click on "Create Pay Arrangement" button in "Payment Arrangement" tabpage
Then The value of "SA ID" is displayed as "%NOTNULL%" under "Payment Arrangement" page
And Click on "Show Account Context - SA ID" button in "Payment Arrangement" tabpage
Then Navigate to below list from top to bottom in order to reach "Service Agreement" page
#---------------------------------------------------------------
# | Link                      |
#---------------------------------------------------------------
  |Go To Service Agreement    |
And Click on "Activate SA" button in "Service Agreement" tabpage
Then The value of "SA STATUS" is displayed as "Active" under "Service Agreement" page 
When Navigate to below list from top to bottom in order to reach "Batch Job Submission" page
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
  |BATCH CODE          |       <BATCH_CODE>       |
  |Thread Pool Name    |       <Thread_Pool_Name> |
  |Account ID          |      %RUNTIMEVALUE%      |
When Click on "Save" button in "Batch Job Submission" page
Then The value of "BATCH JOB STATUS" is displayed as "Ended" under "Batch Job Submission" page
When Navigate to below list from top to bottom in order to reach "Collection Process Search" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Menu                |
  |Credit & Collection |
  |Collection Process  |
  |Search              |
And Enter "Collection Process ID" as "<Collection_Process_Active>" then click on "Search" button in opened window
Then The value of "Collection STATUS" is displayed as "Inactive" under "Collection Process" page

#################################################################################################################
#################################################################################################################
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
  |Account ID          |      1478843000          |
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
Then Ensure "3" Bill Segments have been created including "1" "Payment Arrangement" Bill Segment
When Click on "Show Account Context" under "Current Context" dashboard
Then Navigate to below list from top to bottom in order to reach "Bill" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Go To Payment Event |
  |Add                 |
And Enter below values in newly opened "Payment Event" window and then click on "OK" button
#-----------------------------------------------------------------
# | Field                       |       Values                            |
#-----------------------------------------------------------------
  |PAYMENT AMOUNT               |      %RUNTIME_INSTALLMENTAMOUNT%      |
  |AMOUNT TENDERED              |      %RUNTIME_INSTALLMENTAMOUNT%      |
  |TENDER TYPE                  |      <TENDER_TYPE>                    |
And The value of "PAYMENT EVENT ID" is displayed as "%NOTNULL%" under "Payment Event" page
##########################################################################################
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
  |Account ID          |      1478843000          |
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
  |DATE                |       <Future_Date>       |
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
  |REQUEST START DATE           |      <Today_Date>        |
  |REQUEST END DATE             |      <Future_Date>       |
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
And Enter "CUTOFF DATE" as "<Future_Date>" then click on "Calculate" button in opened window
Then Ensure "3" Bill Segments have been created including "1" "Payment Arrangement" Bill Segment
When Click on "Show Account Context" under "Current Context" dashboard
Then Navigate to below list from top to bottom in order to reach "Bill" page
#---------------------------------------------------------------
# | Link                     |
#---------------------------------------------------------------
  |Go To Payment Arrangement |
  |Search                    |
And Click on the first "Pending Stop" row under the "Payment Arrangement" table in newly opened window
#And Close the browser
Examples:
# Thread_Pool_Name will change if VM will change so ensure you are using right Thread_Pool_Name
|Collection_Process_Active|INSTALLMENTS| CIS DIVISION |SA TYPE |BATCH_CODE|Thread_Pool_Name |Today_Date  |Today_Time|Request_Status|Request_Type|TENDER_TYPE|Future_Date|
|       5061043212        |      2     | Hydro Ottawa | APA-R  | CM-ADM   | CBACHU          |%TODAY_DATE%| 12:57A   |  Received    | Off-Cycle  |  Cash     |%FUTURE_DATE%| 