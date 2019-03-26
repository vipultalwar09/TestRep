$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("C:/Users/Vipul/eclipse-workspace/BDD/src/main/java/Feature/App.feature");
formatter.feature({
  "name": "My Store Application",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "Summary Page",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@third"
    }
  ]
});
formatter.step({
  "name": "User opens the My Store Application into the browser",
  "keyword": "Given "
});
formatter.step({
  "name": "User after verifying logo and title clicks on the Login Button",
  "keyword": "When "
});
formatter.step({
  "name": "User enters \"\u003cuser\u003e\" and \"\u003cpassword\u003e\"",
  "keyword": "Then "
});
formatter.step({
  "name": "User clicks on the Sign In button",
  "keyword": "Then "
});
formatter.step({
  "name": "User is on the Accounts Page",
  "keyword": "Then "
});
formatter.step({
  "name": "User clicks on Dresses button",
  "keyword": "And "
});
formatter.step({
  "name": "Chooses a dress to buy",
  "keyword": "And "
});
formatter.step({
  "name": "User clicks on Add To Cart Button",
  "keyword": "Then "
});
formatter.step({
  "name": "Proceed for CheckOut",
  "keyword": "Then "
});
formatter.step({
  "name": "Lands on the Summary Page",
  "keyword": "And "
});
formatter.step({
  "name": "Moves forward to the payments page",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "user",
        "password"
      ]
    },
    {
      "cells": [
        "patlibalondi@gmail.com",
        "test@123"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Summary Page",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@third"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "User opens the My Store Application into the browser",
  "keyword": "Given "
});
formatter.match({
  "location": "StepDef1.user_opens_the_My_Store_Application_into_the_browser()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User after verifying logo and title clicks on the Login Button",
  "keyword": "When "
});
formatter.match({
  "location": "StepDef1.user_clicks_on_the_Login_Button()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User enters \"patlibalondi@gmail.com\" and \"test@123\"",
  "keyword": "Then "
});
formatter.match({
  "location": "StepDef1.user_enters_and(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User clicks on the Sign In button",
  "keyword": "Then "
});
formatter.match({
  "location": "StepDef1.user_clicks_on_the_Sign_In_button()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User is on the Accounts Page",
  "keyword": "Then "
});
formatter.match({
  "location": "StepDef1.user_is_on_the_Accounts_Page()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User clicks on Dresses button",
  "keyword": "And "
});
formatter.match({
  "location": "StepDef1.user_clicks_on_Dresses_button()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Chooses a dress to buy",
  "keyword": "And "
});
formatter.match({
  "location": "StepDef1.chooses_a_dress_to_buy()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User clicks on Add To Cart Button",
  "keyword": "Then "
});
formatter.match({
  "location": "StepDef1.user_clicks_on_Add_To_Cart_Button()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Proceed for CheckOut",
  "keyword": "Then "
});
formatter.match({
  "location": "StepDef1.proceed_for_CheckOut()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Lands on the Summary Page",
  "keyword": "And "
});
formatter.match({
  "location": "StepDef1.lands_on_the_Summary_Page()"
});
formatter.result({
  "error_message": "java.lang.NullPointerException\r\n\tat Pages.SummaryPage.ProceedToCheckOutButton(SummaryPage.java:26)\r\n\tat StepDefinition.StepDef1.lands_on_the_Summary_Page(StepDef1.java:161)\r\n\tat ✽.Lands on the Summary Page(C:/Users/Vipul/eclipse-workspace/BDD/src/main/java/Feature/App.feature:46)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "Moves forward to the payments page",
  "keyword": "And "
});
formatter.match({
  "location": "StepDef1.moves_forward_to_the_payments_page()"
});
formatter.result({
  "status": "skipped"
});
});