$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("./src/test/resources/com/hydroottawa/ccbupgrade/features/Manual_BQ.feature");
formatter.feature({
  "name": "Manual BQ",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "Manual BQ",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@test"
    }
  ]
});
formatter.step({
  "name": "Open browser \"Chrome\"",
  "keyword": "Given "
});
formatter.step({
  "name": "Login to CCB under \"%ENV%\" environment with user as \"username\" and password as \"password\"",
  "keyword": "When "
});
formatter.step({
  "name": "Navigate to below list from top to bottom in order to reach \"Meter/Item Search\" page",
  "keyword": "When ",
  "rows": [
    {
      "cells": [
        "Menu"
      ]
    },
    {
      "cells": [
        "Meter"
      ]
    },
    {
      "cells": [
        "Meter/Item Search"
      ]
    }
  ]
});
formatter.step({
  "name": "Enter below values in \"Meter/Item Search\" page",
  "keyword": "And ",
  "rows": [
    {
      "cells": [
        "Account ID",
        "\u003cAccount_ID\u003e"
      ]
    }
  ]
});
formatter.step({
  "name": "Click on \"Search - ACCOUNT ID\" button in \"Meter/Item Search\" tabpage",
  "keyword": "And "
});
formatter.step({
  "name": "Click on \"Meter Context Menu\" image button for \"Active\" meter under \"METER/ITEM INFORMATION\" column in \"Meter/Item Search\" page",
  "keyword": "And "
});
formatter.step({
  "name": "Navigate to below list from top to bottom in order to reach \"Go To Meter Read Search\" page",
  "keyword": "Then ",
  "rows": [
    {
      "cells": [
        "Go To Meter Read"
      ]
    },
    {
      "cells": [
        "Search"
      ]
    }
  ]
});
formatter.step({
  "name": "Click on the first \"METER READ ID\" row under the \"Meter Reading\" table in newly opened window",
  "keyword": "And "
});
formatter.step({
  "name": "The value of \"METER CONFIGURATION ID\" is displayed as \"%NOTNULL%\" under \"Meter Read\" page",
  "keyword": "Then "
});
formatter.step({
  "name": "The value of \"READ DATE\" is displayed as \"%NOTNULL%\" under \"Meter Read\" page",
  "keyword": "And "
});
formatter.step({
  "name": "The value of \"REGISTER READING\" is displayed as \"%NOTNULL%\" under \"Meter Read\" page",
  "keyword": "And "
});
formatter.step({
  "name": "Click on \"Meter Configuration Context Menu\" button in \"Meter Read\" tabpage",
  "keyword": "When "
});
formatter.step({
  "name": "Navigate to below list from top to bottom in order to reach \"Go To Meter Read Search\" page",
  "keyword": "Then ",
  "rows": [
    {
      "cells": [
        "Go To Meter Read"
      ]
    },
    {
      "cells": [
        "Add"
      ]
    }
  ]
});
formatter.step({
  "name": "Enter below values in \"Meter Read\" page",
  "keyword": "And ",
  "rows": [
    {
      "cells": [
        "DATE",
        "\u003cToday_Date\u003e"
      ]
    },
    {
      "cells": [
        "TIME",
        "\u003cToday_Time\u003e"
      ]
    },
    {
      "cells": [
        "MR SOURCE",
        "MDMR Read"
      ]
    }
  ]
});
formatter.step({
  "name": "Select \"USE ON BILL\" check box",
  "keyword": "And "
});
formatter.step({
  "name": "Select \"READ TYPE\" and add below reading in previous \"REGISTER READING\" in \"Meter Read\" page",
  "keyword": "And ",
  "rows": [
    {
      "cells": [
        "Regular",
        "100"
      ]
    },
    {
      "cells": [
        "Regular",
        "20"
      ]
    },
    {
      "cells": [
        "Regular",
        "20"
      ]
    },
    {
      "cells": [
        "Regular",
        "20"
      ]
    },
    {
      "cells": [
        "Regular",
        "20"
      ]
    }
  ]
});
formatter.step({
  "name": "Click on \"Characteristics\" tab in \"Meter Read\" menupage",
  "keyword": "And "
});
formatter.step({
  "name": "Enter below values in \"Characteristics\" tab under \"Meter Read\" page",
  "keyword": "Then ",
  "rows": [
    {
      "cells": [
        "Characteristic Type",
        "User Comment for Off Cycle BQ"
      ]
    },
    {
      "cells": [
        "Sequence",
        "1"
      ]
    },
    {
      "cells": [
        "Characteristic Value",
        "Manual_Reading"
      ]
    }
  ]
});
formatter.step({
  "name": "Click on \"Save\" button in \"Meter Read\" mainpage",
  "keyword": "And "
});
formatter.step({
  "name": "The value of \"METER READ ID\" is displayed as \"%NOTNULL%\" under \"Meter Read\" page",
  "keyword": "Then "
});
formatter.step({
  "name": "Navigate to below list from top to bottom in order to reach \"MDMR BQ Request\" page",
  "keyword": "When ",
  "rows": [
    {
      "cells": [
        "Menu"
      ]
    },
    {
      "cells": [
        "MDMR Testing"
      ]
    },
    {
      "cells": [
        "MDMR BQ Request"
      ]
    }
  ]
});
formatter.step({
  "name": "Enter below values in \"MDMR BQ Request\" page",
  "keyword": "And ",
  "rows": [
    {
      "cells": [
        "METER READ ID",
        "%RUNTIMEVALUE%"
      ]
    }
  ]
});
formatter.step({
  "name": "Click on \"Search\" button in \"MDMR BQ Request\" tabpage",
  "keyword": "And "
});
formatter.step({
  "name": "Click on \"BROADCAST\" button in \"MDMR BQ Request\" tabpage",
  "keyword": "And "
});
formatter.step({
  "name": "Enter below values in \"MDMR BQ Request_UI MAP\" UI pop up page and then click on \"Save\" button",
  "keyword": "And ",
  "rows": [
    {
      "cells": [
        "REQUEST START DATE",
        "2019-07-20"
      ]
    },
    {
      "cells": [
        "REQUEST END DATE",
        "2019-09-03"
      ]
    },
    {
      "cells": [
        "REQUEST STATUS",
        "\u003cRequest_Status\u003e"
      ]
    },
    {
      "cells": [
        "REQUEST TYPE",
        "\u003cRequest_Type\u003e"
      ]
    }
  ]
});
formatter.step({
  "name": "Click on \"Go To Account\" under \"Current Context\" dashboard",
  "keyword": "When "
});
formatter.step({
  "name": "Click on \"Show Account Context - Account ID\" button in \"Account\" tabpage",
  "keyword": "And "
});
formatter.step({
  "name": "Navigate to below list from top to bottom in order to reach \"Bill\" page",
  "keyword": "Then ",
  "rows": [
    {
      "cells": [
        "Go To Bill"
      ]
    },
    {
      "cells": [
        "Add"
      ]
    }
  ]
});
formatter.step({
  "name": "Click on \"Generate\" button in \"Bill\" tabpage",
  "keyword": "And "
});
formatter.step({
  "name": "Enter \"CUTOFF DATE\" as \"\u003cCutoff_Date\u003e\" then click on \"Calculate\" button in opened window",
  "keyword": "And "
});
formatter.step({
  "name": "Click on \"Freeze\" button in \"Bill\" tabpage",
  "keyword": "Then "
});
formatter.step({
  "name": "Click on \"Complete\" button in \"Bill\" tabpage",
  "keyword": "Then "
});
formatter.step({
  "name": "Enter \"BILL DATE\" as \"\u003cCutoff_Date\u003e\" then click on \"Complete\" button in opened window",
  "keyword": "And "
});
formatter.step({
  "name": "The value of \"BILL STATUS\" is displayed as \"Complete\" under \"Bill\" page",
  "keyword": "Then "
});
formatter.step({
  "name": "Close the browser",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "Account_ID",
        "Today_Date",
        "Today_Time",
        "Request_Status",
        "Future_Date",
        "BQ_REQUEST_STATUS1",
        "BQ_REQUEST_STATUS2",
        "BQ_REQUEST_STATUS3",
        "Request_Type",
        "Cutoff_Date"
      ]
    },
    {
      "cells": [
        "2511391967",
        "2019-09-03",
        "12:57A",
        "Received",
        "%FUTURE_DATE%",
        "W",
        "P",
        "B",
        "Off-Cycle",
        "2019-09-03"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Manual BQ",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@test"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "Open browser \"Chrome\"",
  "keyword": "Given "
});
formatter.match({
  "location": "POC_Step_Definition.open_browser(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Login to CCB under \"%ENV%\" environment with user as \"username\" and password as \"password\"",
  "keyword": "When "
});
formatter.match({
  "location": "POC_Step_Definition.login_to_CCB_under_environment_with_user_as_and_password_as(String,String,String)"
});
formatter.embedding("image/png", "embedded0.png");
formatter.embedding("image/png", "embedded1.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Navigate to below list from top to bottom in order to reach \"Meter/Item Search\" page",
  "rows": [
    {
      "cells": [
        "Menu"
      ]
    },
    {
      "cells": [
        "Meter"
      ]
    },
    {
      "cells": [
        "Meter/Item Search"
      ]
    }
  ],
  "keyword": "When "
});
formatter.match({
  "location": "POC_Step_Definition.navigate_to_below_list_from_top_to_botton_in_order_to_reach_page(String,String\u003e)"
});
formatter.embedding("image/png", "embedded2.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Enter below values in \"Meter/Item Search\" page",
  "rows": [
    {
      "cells": [
        "Account ID",
        "2511391967"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "POC_Step_Definition.enter_below_values_in_page(String,String\u003e\u003e)"
});
formatter.embedding("image/png", "embedded3.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Click on \"Search - ACCOUNT ID\" button in \"Meter/Item Search\" tabpage",
  "keyword": "And "
});
formatter.match({
  "location": "POC_Step_Definition.click_on_button_in_tabpage(String,String)"
});
formatter.embedding("image/png", "embedded4.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Click on \"Meter Context Menu\" image button for \"Active\" meter under \"METER/ITEM INFORMATION\" column in \"Meter/Item Search\" page",
  "keyword": "And "
});
formatter.match({
  "location": "POC_Step_Definition.click_on_image_button_for_meter_under_column_in_page(String,String,String,String)"
});
formatter.embedding("image/png", "embedded5.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Navigate to below list from top to bottom in order to reach \"Go To Meter Read Search\" page",
  "rows": [
    {
      "cells": [
        "Go To Meter Read"
      ]
    },
    {
      "cells": [
        "Search"
      ]
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "POC_Step_Definition.navigate_to_below_list_from_top_to_botton_in_order_to_reach_page(String,String\u003e)"
});
formatter.embedding("image/png", "embedded6.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Click on the first \"METER READ ID\" row under the \"Meter Reading\" table in newly opened window",
  "keyword": "And "
});
formatter.match({
  "location": "POC_Step_Definition.click_on_the_first_row_under_the_table_in_newly_opened_window(String,String)"
});
formatter.embedding("image/png", "embedded7.png");
formatter.write("Previous Meter ID for the account null is ::  428117082915");
formatter.embedding("image/png", "embedded8.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "The value of \"METER CONFIGURATION ID\" is displayed as \"%NOTNULL%\" under \"Meter Read\" page",
  "keyword": "Then "
});
formatter.match({
  "location": "POC_Step_Definition.the_value_of_is_displayed_as_under_page(String,String,String)"
});
formatter.write("The value of METER CONFIGURATION ID is :: 4286239777");
formatter.embedding("image/png", "embedded9.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "The value of \"READ DATE\" is displayed as \"%NOTNULL%\" under \"Meter Read\" page",
  "keyword": "And "
});
formatter.match({
  "location": "POC_Step_Definition.the_value_of_is_displayed_as_under_page(String,String,String)"
});
formatter.write("The value of Previous METER READ Date is :: 2019-07-20");
formatter.embedding("image/png", "embedded10.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "The value of \"REGISTER READING\" is displayed as \"%NOTNULL%\" under \"Meter Read\" page",
  "keyword": "And "
});
formatter.match({
  "location": "POC_Step_Definition.the_value_of_is_displayed_as_under_page(String,String,String)"
});
formatter.write("Register Reading values of previous METER READ ID  428117082915 is :: [437.0, 106.0, 10.0, 10.0, 0.0]");
formatter.embedding("image/png", "embedded11.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Click on \"Meter Configuration Context Menu\" button in \"Meter Read\" tabpage",
  "keyword": "When "
});
formatter.match({
  "location": "POC_Step_Definition.click_on_button_in_tabpage(String,String)"
});
formatter.embedding("image/png", "embedded12.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Navigate to below list from top to bottom in order to reach \"Go To Meter Read Search\" page",
  "rows": [
    {
      "cells": [
        "Go To Meter Read"
      ]
    },
    {
      "cells": [
        "Add"
      ]
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "POC_Step_Definition.navigate_to_below_list_from_top_to_botton_in_order_to_reach_page(String,String\u003e)"
});
formatter.embedding("image/png", "embedded13.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Enter below values in \"Meter Read\" page",
  "rows": [
    {
      "cells": [
        "DATE",
        "2019-09-03"
      ]
    },
    {
      "cells": [
        "TIME",
        "12:57A"
      ]
    },
    {
      "cells": [
        "MR SOURCE",
        "MDMR Read"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "POC_Step_Definition.enter_below_values_in_page(String,String\u003e\u003e)"
});
formatter.embedding("image/png", "embedded14.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Select \"USE ON BILL\" check box",
  "keyword": "And "
});
formatter.match({
  "location": "POC_Step_Definition.select_check_box(String)"
});
formatter.embedding("image/png", "embedded15.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Select \"READ TYPE\" and add below reading in previous \"REGISTER READING\" in \"Meter Read\" page",
  "rows": [
    {
      "cells": [
        "Regular",
        "100"
      ]
    },
    {
      "cells": [
        "Regular",
        "20"
      ]
    },
    {
      "cells": [
        "Regular",
        "20"
      ]
    },
    {
      "cells": [
        "Regular",
        "20"
      ]
    },
    {
      "cells": [
        "Regular",
        "20"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "POC_Step_Definition.select_and_add_below_reading_in_previous_in_page(String,String,String,String\u003e\u003e)"
});
formatter.embedding("image/png", "embedded16.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Click on \"Characteristics\" tab in \"Meter Read\" menupage",
  "keyword": "And "
});
formatter.match({
  "location": "POC_Step_Definition.click_on_tab_in_menupage(String,String)"
});
formatter.embedding("image/png", "embedded17.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Enter below values in \"Characteristics\" tab under \"Meter Read\" page",
  "rows": [
    {
      "cells": [
        "Characteristic Type",
        "User Comment for Off Cycle BQ"
      ]
    },
    {
      "cells": [
        "Sequence",
        "1"
      ]
    },
    {
      "cells": [
        "Characteristic Value",
        "Manual_Reading"
      ]
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "POC_Step_Definition.enter_below_values_in_tab_under_page(String,String,String\u003e\u003e)"
});
formatter.embedding("image/png", "embedded18.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Click on \"Save\" button in \"Meter Read\" mainpage",
  "keyword": "And "
});
formatter.match({
  "location": "POC_Step_Definition.click_on_button_in_mainpage(String,String)"
});
formatter.embedding("image/png", "embedded19.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "The value of \"METER READ ID\" is displayed as \"%NOTNULL%\" under \"Meter Read\" page",
  "keyword": "Then "
});
formatter.match({
  "location": "POC_Step_Definition.the_value_of_is_displayed_as_under_page(String,String,String)"
});
formatter.write("The value of METER READ ID is :: 428013657958");
formatter.embedding("image/png", "embedded20.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Navigate to below list from top to bottom in order to reach \"MDMR BQ Request\" page",
  "rows": [
    {
      "cells": [
        "Menu"
      ]
    },
    {
      "cells": [
        "MDMR Testing"
      ]
    },
    {
      "cells": [
        "MDMR BQ Request"
      ]
    }
  ],
  "keyword": "When "
});
formatter.match({
  "location": "POC_Step_Definition.navigate_to_below_list_from_top_to_botton_in_order_to_reach_page(String,String\u003e)"
});
formatter.embedding("image/png", "embedded21.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Enter below values in \"MDMR BQ Request\" page",
  "rows": [
    {
      "cells": [
        "METER READ ID",
        "%RUNTIMEVALUE%"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "POC_Step_Definition.enter_below_values_in_page(String,String\u003e\u003e)"
});
formatter.embedding("image/png", "embedded22.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Click on \"Search\" button in \"MDMR BQ Request\" tabpage",
  "keyword": "And "
});
formatter.match({
  "location": "POC_Step_Definition.click_on_button_in_tabpage(String,String)"
});
formatter.embedding("image/png", "embedded23.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Click on \"BROADCAST\" button in \"MDMR BQ Request\" tabpage",
  "keyword": "And "
});
formatter.match({
  "location": "POC_Step_Definition.click_on_button_in_tabpage(String,String)"
});
formatter.embedding("image/png", "embedded24.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Enter below values in \"MDMR BQ Request_UI MAP\" UI pop up page and then click on \"Save\" button",
  "rows": [
    {
      "cells": [
        "REQUEST START DATE",
        "2019-07-20"
      ]
    },
    {
      "cells": [
        "REQUEST END DATE",
        "2019-09-03"
      ]
    },
    {
      "cells": [
        "REQUEST STATUS",
        "Received"
      ]
    },
    {
      "cells": [
        "REQUEST TYPE",
        "Off-Cycle"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "POC_Step_Definition.enter_below_values_in_UI_pop_up_page_and_then_click_on_button(String,String,String\u003e\u003e)"
});
formatter.embedding("image/png", "embedded25.png");
formatter.embedding("image/png", "embedded26.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Click on \"Go To Account\" under \"Current Context\" dashboard",
  "keyword": "When "
});
formatter.match({
  "location": "POC_Step_Definition.click_on_under_dashboard(String,String)"
});
formatter.embedding("image/png", "embedded27.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Click on \"Show Account Context - Account ID\" button in \"Account\" tabpage",
  "keyword": "And "
});
formatter.match({
  "location": "POC_Step_Definition.click_on_button_in_tabpage(String,String)"
});
formatter.embedding("image/png", "embedded28.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Navigate to below list from top to bottom in order to reach \"Bill\" page",
  "rows": [
    {
      "cells": [
        "Go To Bill"
      ]
    },
    {
      "cells": [
        "Add"
      ]
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "POC_Step_Definition.navigate_to_below_list_from_top_to_botton_in_order_to_reach_page(String,String\u003e)"
});
formatter.embedding("image/png", "embedded29.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Click on \"Generate\" button in \"Bill\" tabpage",
  "keyword": "And "
});
formatter.match({
  "location": "POC_Step_Definition.click_on_button_in_tabpage(String,String)"
});
formatter.embedding("image/png", "embedded30.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Enter \"CUTOFF DATE\" as \"2019-09-03\" then click on \"Calculate\" button in opened window",
  "keyword": "And "
});
formatter.match({
  "location": "POC_Step_Definition.enter_as_then_click_on_button_in_opened_window(String,String,String)"
});
formatter.embedding("image/png", "embedded31.png");
formatter.embedding("image/png", "embedded32.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Click on \"Freeze\" button in \"Bill\" tabpage",
  "keyword": "Then "
});
formatter.match({
  "location": "POC_Step_Definition.click_on_button_in_tabpage(String,String)"
});
formatter.embedding("image/png", "embedded33.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Click on \"Complete\" button in \"Bill\" tabpage",
  "keyword": "Then "
});
formatter.match({
  "location": "POC_Step_Definition.click_on_button_in_tabpage(String,String)"
});
formatter.embedding("image/png", "embedded34.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Enter \"BILL DATE\" as \"2019-09-03\" then click on \"Complete\" button in opened window",
  "keyword": "And "
});
formatter.match({
  "location": "POC_Step_Definition.enter_as_then_click_on_button_in_opened_window(String,String,String)"
});
formatter.embedding("image/png", "embedded35.png");
formatter.embedding("image/png", "embedded36.png");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "The value of \"BILL STATUS\" is displayed as \"Complete\" under \"Bill\" page",
  "keyword": "Then "
});
formatter.match({
  "location": "POC_Step_Definition.the_value_of_is_displayed_as_under_page(String,String,String)"
});
formatter.result({
  "error_message": "org.junit.ComparisonFailure: Bill generation failed. expected:\u003c[Complete]\u003e but was:\u003c[Pending]\u003e\r\n\tat org.junit.Assert.assertEquals(Assert.java:115)\r\n\tat com.hydroottawa.ccbupgrade.poc.utils.CCBUpgradeLib.verifyBatchJobStatus_ftabPage(CCBUpgradeLib.java:805)\r\n\tat com.hydroottawa.ccbupgrade.poc.stepdefs.POC_Step_Definition.the_value_of_is_displayed_as_under_page(POC_Step_Definition.java:323)\r\n\tat ✽.The value of \"BILL STATUS\" is displayed as \"Complete\" under \"Bill\" page(./src/test/resources/com/hydroottawa/ccbupgrade/features/Manual_BQ.feature:244)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "Close the browser",
  "keyword": "And "
});
formatter.match({
  "location": "POC_Step_Definition.close_the_browser()"
});
formatter.result({
  "status": "skipped"
});
});