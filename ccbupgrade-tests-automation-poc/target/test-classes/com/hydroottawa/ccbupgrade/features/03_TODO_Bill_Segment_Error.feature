Feature: To Do Bill Segment Error
@Bill_Segment_Error
Scenario Outline: Creating an Open to-do for the Bill segments that are in error status.
Given Open browser "Chrome"
When Login to CCB under "%ENV%" environment with user as "username" and password as "password"
Then Navigate to below list from top to bottom in order to reach "Bill Segment Search" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Menu                |
  |Financial           |
  |Bill Segment        |
  |Search              |
And Enter "BILL SEGMENT ID" as "<BILL_SEGMENT_ERROR>" then click on "Search" button in opened window
Then The value of "BILL SEGMENT STATUS" is displayed as "Error" under "Bill Segment" page
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
  |THREAD COUNT        |       <THREAD_COUNT>     |
When Click on "Save" button in "Batch Job Submission" page
Then The value of "BATCH JOB STATUS" is displayed as "Ended" under "Batch Job Submission" page
When Navigate to below list from top to bottom in order to reach "To Do Search" page
#---------------------------------------------------------------
# | Link               |
#---------------------------------------------------------------
  |Menu                |
  |To Do               |
  |To Do Search        |
Then Select "<FILTER TYPE>" from "TO DO TYPE FILTER" drop down list
And Enter below values in "To Do Search" page
#-----------------------------------------------------------------
# | Field              |       Values             |
#-----------------------------------------------------------------
  |TO DO TYPE          |       <BATCH_CODE>       |
  |STATUS FILTER       |       <STATUS_FILTER>    |
And Click on "Search" button in "To Do Search" tabpage
Then list of "open" status "TO DOs" are displayed in tabular format
When Click on "Create Date/Time" hyperlink under "dataTable"
And Click on "Keys/Values" tab in "TO DO Entry" menupage
Then The value of "TO DO ID" is displayed as "%NOTNULL%" under "To Do Entry" page
Then The value of "DRILL KEYS" is displayed as "%NOTNULL%" under "To Do Entry" page
When Click on "Main" tab in "TO DO Entry" menupage
And Click on "Forward" button in "To Do Entry" tabpage
When Enter "USER" as "<User_Name>" then click on "OK" button in opened window
Then The value of "STATUS" is displayed as "<TODO_STATUS>" under "To Do Entry" page
And Close the browser

Examples:
|BILL_SEGMENT_ERROR|BILL_SEGMENT_ERROR|BATCH_CODE   | THREAD_COUNT  | FILTER TYPE       |STATUS_FILTER|User_Name|TODO_STATUS    |
|    005575039687  |  Error           |TD-BSERR     |      4        |Specific To Do Type|Open         |SYSUSER  |Being Worked On|