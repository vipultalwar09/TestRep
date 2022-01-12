package com.RDM.Merger.pagefunctions;


import java.util.*;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.RDM.Merger.locators.*;
import com.RDM.Merger.testcases.Aa_Login.ExcelReader;
import com.utility.base.*;
import com.utility.logger.*;

public class BRMPageFunctions extends BRMPage {

	public static WebElement tab;
	static boolean value, editValue, duplicateValue, deleteValue, conditionValue;
	static int ruleTestCount = 10;
	public static String username = System.getProperty("user.name");
	public static boolean logicalResultFlag = true;


	public BRMPageFunctions() {
		super();
		System.out.println("In BRM Page function");

	}

	String attribute, value1, operator, id, transformvalue;

	public BRMPageFunctions(String id, String attribute, String operator, String value1, String transformvalue) {
		this.id = id;
		this.attribute = attribute;
		this.value1 = value1;
		this.operator = operator;
		this.transformvalue = transformvalue;
	}

	//Returns tab Name
	public static WebElement tabName(String tabname)
	{
		tab = driver.findElement(By.xpath("//a[text()='" + tabname + "']"));
		//WrapperFunctionUtilities.isElementPresent(tab, tabname);
		return tab;
	}

	//This function creates new Rule Group
	public static void createRuleGroup(String groupName, String entityName) {
		try
		{
			WrapperFunctionUtilities.waitForTime(1000);
			add_rule_group.click();
			WrapperFunctionUtilities.waitForTime(2000);
			CommonPageFunctions.TypeKey(rule_group_name, ExcelReader.getValue("BRM", groupName, "TestData"));
			//rule_group_entity.click();
			driver.findElement(By.xpath("(//div[contains(.,'Select Entity')])[7]")).click();
			WrapperFunctionUtilities.waitForTime(1000);
			CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("BRM", entityName, "TestData"));
			WrapperFunctionUtilities.waitForElementVisibility(rule_group_save, 1000, driver);
			rule_group_save.click();
			WrapperFunctionUtilities.waitForTime(1000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}


	}

	//click BRM Internal Tabs(Cards,Tables)
	public static void clickBRMInternalTabs() {
		try {
			//CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("BRM", tab, "TestData"));
			BRMPage.tables.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//click delete link to delete a group
	public static void clickDelete(String rulegroupName) {
		try {
			String ruleGroup = ExcelReader.getValue("BRM", rulegroupName, "TestData");
			BRMPageFunctions.clickBRMInternalTabs();
			searchRuleGroupbox.sendKeys(ruleGroup);
			groupSearchButton.click();
			getDeleteIconElement(ruleGroup);
			WrapperFunctionUtilities.waitForElementVisibility(deletelink, 2000, driver);
			deletelink.click();
			//WrapperFunctionUtilities.waitForElementVisibility(rule_group_save, 1000, driver);
			rule_group_ok.click();
			driver.navigate().refresh();
			WrapperFunctionUtilities.waitForTime(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//This function is used to duplicate rule
	public static void duplicateRule(String ruleName, String newRule) throws Exception {
		Log.info("Method : duplicateRule");
		ruleName = ExcelReader.getValue("BRM", ruleName, "TestData");
		try {
			getDuplicateRuleIcon(ruleName);
			Actions action = new Actions(driver);
			action.click(getDuplicateRuleIcon(ruleName)).build().perform();
		} catch (Exception e) {
			WrapperFunctionUtilities.refreshPageWithPageLoadTimeout(driver);
			WrapperFunctionUtilities.waitForElementVisibility(getDuplicateRuleIcon(ruleName), 30, driver);
			getDuplicateRuleIcon(ruleName).click();
		}
		newduplicateRuleName.sendKeys(ExcelReader.getValue("BRM", newRule, "TestData"));
		rule_group_ok.click();
	}

//  (Copy/Duplicate of Rule) Verify Detail Column of Audit Logs have correct info in case Rule is Copied/Duplicated.

	public static boolean verifyDuplicateRuleGroupAuditLog(String RuleGroupName, String RuleName, String DuplicateName) throws Exception {
		boolean result = false;
		String DuplicateValue = RuleName + " copied as " + DuplicateName + " in rule group " + RuleGroupName;
		Log.info(DuplicateValue);
		CommonPageFunctions.clickTabUnderEllipsis("Audit Log");
		WrapperFunctionUtilities.waitForTime(2000);
		String actualLogValue = ruleDuplicateAuditLogValue.getText();
		Log.info("actualLogValue: " + actualLogValue);
		if (DuplicateValue.contentEquals(actualLogValue)) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}


	//This function creates new Rule in a group
	public static void addRuleinGroup(String groupName, String ruleName, String attribute, String operator, String value, String action) {
		try {
			BRMPage.getRuleGroupElement(ExcelReader.getValue("BRM", groupName, "TestData"));
			groupLink.click();
			WrapperFunctionUtilities.waitForTime(2000);
			add_rule.click();
			WrapperFunctionUtilities.waitForTime(2000);
			rule_name.sendKeys(ExcelReader.getValue("BRM", ruleName, "TestData"));
			WrapperFunctionUtilities.waitForTime(2000);

			//To select attribute
			selectAttribute.click();
			WrapperFunctionUtilities.waitForElementVisibility(inputAttribute, 1000, driver);
			inputAttribute.sendKeys(ExcelReader.getValue("BRM", attribute, "TestData"));

			//to select operator
			/*WrapperFunctionUtilities.waitForElementVisibility(selectOperator,1000,driver);
			selectOperator.click();
			WrapperFunctionUtilities.waitForTime(2000);
			WrapperFunctionUtilities.waitForElementVisibility(inputOperator,1000,driver);
			inputOperator.sendKeys(ExcelReader.getValue("BRM", operator, "TestData"));
			CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("BRM", operator, "TestData"));*/
			selectOperatorOption(CommonPage.hyperlink(ExcelReader.getValue("BRM", operator, "TestData")));

			//to select value
			WrapperFunctionUtilities.waitForElementVisibility(selectValue, 1000, driver);
			selectValue.click();
			inputValue.sendKeys(ExcelReader.getValue("BRM", value, "TestData"));
			WrapperFunctionUtilities.waitForElementVisibility(rule_select_action, 1000, driver);

			rule_select_action.click();
			CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("BRM", action, "TestData"));
			WrapperFunctionUtilities.waitForElementVisibility(rule_save, 1000, driver);
			rule_save.click();
			WrapperFunctionUtilities.waitForTime(2000);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

	}

	public static void verifyAtrributeAndNewAttributeFields(String groupName, String ruleName, String action) {
		try {
			BRMPage.getRuleGroupElement(ExcelReader.getValue("BRM", groupName, "TestData"));
			groupLink.click();
			WrapperFunctionUtilities.waitForTime(2000);
			add_rule.click();
			WrapperFunctionUtilities.waitForTime(2000);
			rule_name.sendKeys(ExcelReader.getValue("BRM", ruleName, "TestData"));
			WrapperFunctionUtilities.waitForTime(2000);

			//To select attribute

			ruleActionDropdown.click();
			CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("BRM", action, "TestData"));
			WrapperFunctionUtilities.waitForTime(2000);
			selectTransformAttribute.click();
			CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("BRM", "Attribute2", "TestData"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public static void addNewExpression(String attribute, String operator, String value) {
		try {

			//To select attribute
			selectAttribute.click();
			WrapperFunctionUtilities.waitForElementVisibility(inputAttribute, 1000, driver);
			inputAttribute.sendKeys(ExcelReader.getValue("BRM", attribute, "TestData"));


			//to select operator
			/*WrapperFunctionUtilities.waitForElementVisibility(selectOperator,1000,driver);
			selectOperator.click();
			WrapperFunctionUtilities.waitForTime(2000);
			WrapperFunctionUtilities.waitForElementVisibility(inputOperator,1000,driver);
			inputOperator.sendKeys(ExcelReader.getValue("BRM", operator, "TestData"));
			CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("BRM", operator, "TestData"));*/
			selectOperatorOption(CommonPage.hyperlink(ExcelReader.getValue("BRM", operator, "TestData")));

			//to select value
			WrapperFunctionUtilities.waitForElementVisibility(selectValue, 1000, driver);
			selectValue.click();
			inputValue.sendKeys(ExcelReader.getValue("BRM", value, "TestData"));
			WrapperFunctionUtilities.waitForElementVisibility(BRMPage.value, 1000, driver);
			BRMPage.value.click();
			BRMPage.value.sendKeys(ExcelReader.getValue("BRM", "City", "TestData"));
			WrapperFunctionUtilities.waitForElementVisibility(rule_save, 1000, driver);
			rule_save.click();
			WrapperFunctionUtilities.waitForTime(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public static void verifyTransformRuleAction(String groupName, String ruleName, String attribute, String attribute2, String operator, String value, String action) {
		try {
			BRMPage.getRuleGroupElement(ExcelReader.getValue("BRM", groupName, "TestData"));
			groupLink.click();
			WrapperFunctionUtilities.waitForTime(2000);
			add_rule.click();
			WrapperFunctionUtilities.waitForTime(2000);
			rule_name.sendKeys(ExcelReader.getValue("BRM", ruleName, "TestData"));
			WrapperFunctionUtilities.waitForTime(2000);

			ruleActionDropdown.click();
			CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("BRM", action, "TestData"));
			WrapperFunctionUtilities.waitForTime(2000);
			selectTransformAttribute.click();
			CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("BRM", "Attribute2", "TestData"));

			//To select attribute
			selectAttribute.click();
			WrapperFunctionUtilities.waitForElementVisibility(inputAttribute, 1000, driver);
			inputAttribute.sendKeys(ExcelReader.getValue("BRM", attribute, "TestData"));


			//to select operator
			WrapperFunctionUtilities.waitForElementVisibility(selectOperator, 1000, driver);
			selectOperator.click();
			WrapperFunctionUtilities.waitForTime(2000);
			WrapperFunctionUtilities.waitForElementVisibility(inputOperator, 1000, driver);
			inputOperator.sendKeys(ExcelReader.getValue("BRM", operator, "TestData"));
			CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("BRM", operator, "TestData"));

			//to select value
			WrapperFunctionUtilities.waitForElementVisibility(selectValue, 1000, driver);
			selectValue.click();
			inputValue.sendKeys(ExcelReader.getValue("BRM", value, "TestData"));
			WrapperFunctionUtilities.waitForElementVisibility(BRMPage.value, 1000, driver);

			//Then condition
			BRMPage.value.click();
			BRMPage.value.sendKeys(ExcelReader.getValue("BRM", "City", "TestData"));

			WrapperFunctionUtilities.waitForTime(1000);
			addNewExpression.click();
			WrapperFunctionUtilities.waitForTime(1000);

			//To select attribute
			selectAttributeExp2.click();
			WrapperFunctionUtilities.waitForElementVisibility(selectAttributeExp2, 1000, driver);
			selectAttributeExp2.sendKeys(ExcelReader.getValue("BRM", attribute2, "TestData"));


			//to select operator
			//WrapperFunctionUtilities.waitForElementVisibility(selectOperator,1000,driver);
			//selectOperator.click();
			//WrapperFunctionUtilities.waitForElementVisibility(selectOperator,1000,driver);
			//selectOperator.sendKeys(ExcelReader.getValue("BRM", operator, "TestData"));
			brmPage_selectOperator2_WebElement.click();
			WrapperFunctionUtilities.jsClick(equalOperatorExp2, driver);
			//equalOperatorExp2.click();


			//to select value
			WrapperFunctionUtilities.waitForElementVisibility(selectValueExp2, 1000, driver);
			selectValueExp2.click();
			selectValueExp2.sendKeys(ExcelReader.getValue("BRM", value, "TestData"));


			//Then condition
			WrapperFunctionUtilities.waitForElementVisibility(BRMPage.valueExp2, 1000, driver);
			BRMPage.valueExp2.click();
			BRMPage.valueExp2.sendKeys(ExcelReader.getValue("BRM", "City1", "TestData"));


			addElseAction.click();
			WrapperFunctionUtilities.waitForTime(1000);

			elseValue.sendKeys(ExcelReader.getValue("BRM", "ElseValue", "TestData"));
			WrapperFunctionUtilities.waitForTime(2000);


			testRuleButton.click();
			WrapperFunctionUtilities.waitForTime(2000);

			WrapperFunctionUtilities.waitForElementVisibility(rule_save, 2000, driver);
			//rule_save.click();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", rule_save);
			WrapperFunctionUtilities.waitForTime(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public static void verifyIncludeExcludeRuleAction(String ruleName, String attribute, String attribute2, String operator, String value, String value2, String action) {
		try {
			/*BRMPage.getRuleGroupElement(ExcelReader.getValue("BRM", groupName, "TestData"));
			groupLink.click();*/
			HashMap<String, String> testData = ExcelReader.getTestDataFromExcel("BRM", "TestData");
			add_rule.click();
			//rule_name.sendKeys(ExcelReader.getValue("BRM", ruleName, "TestData"));
			rule_name.sendKeys(testData.get(ruleName));


			//To select attribute
			ruleActionDropdown.click();
			CommonPageFunctions.hyperlinkClick(testData.get(action));
			WrapperFunctionUtilities.waitForTime(2000);


			//To select attribute
			selectAttribute.click();
			WrapperFunctionUtilities.waitForElementVisibility(inputAttribute, 1000, driver);
			inputAttribute.sendKeys(testData.get(attribute));


			//to select operator
			/*WrapperFunctionUtilities.waitForElementVisibility(selectOperator,1000,driver);
			selectOperator.click();

			selectOperator.sendKeys(ExcelReader.getValue("BRM", operator, "TestData"));
			CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("BRM", operator, "TestData"));*/

			selectOperatorOption(CommonPage.hyperlink(testData.get(operator)));
			//to select value
			WrapperFunctionUtilities.waitForElementVisibility(selectValue, 1000, driver);
			selectValue.click();
			inputValue.sendKeys(testData.get(value));


			addNewExpression.click();

			//To select attribute
			WrapperFunctionUtilities.waitForElementVisibility(selectAttributeExp2, 1000, driver);
			WrapperFunctionUtilities.scrollByVisibleElement(selectAttributeExp2, driver);
			selectAttributeExp2.click();
			selectAttributeExp2.sendKeys(testData.get(attribute2));


			//to select operator
			WrapperFunctionUtilities.waitForElementVisibility(selectOperator, 1000, driver);
			brmPage_selectOperator2_WebElement.click();
			//WrapperFunctionUtilities.waitForElementVisibility(selectOperator,1000,driver);
			//selectOperator.sendKeys(ExcelReader.getValue("BRM", operator, "TestData"));
			equalOperatorExp2.click();


			//to select value
			WrapperFunctionUtilities.waitForElementVisibility(selectValueExp2, 1000, driver);
			selectValueExp2.click();
			selectValueExp2.sendKeys(ExcelReader.getValue("BRM", value2, "TestData"));

			// click on exclude radio button
			WrapperFunctionUtilities.waitForElementVisibility(BRMPage.excludeRadioButton, 2000, driver);
			excludeRadioButton.click();
			WrapperFunctionUtilities.waitForTime(2000);


			testRuleButton.click();
			WrapperFunctionUtilities.waitForTime(2000);

			WrapperFunctionUtilities.waitForElementVisibility(rule_save, 2000, driver);
			//rule_save.click();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", rule_save);
			WrapperFunctionUtilities.waitForTime(2000);


		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public static void verifyMathFunction(String groupName, String ruleName, String expression1, String expression2, String operator, String action) throws Exception {


		//groupLink.click();
		//WrapperFunctionUtilities.waitForTime(2000);
		add_rule.click();
		WrapperFunctionUtilities.waitForTime(2000);
		rule_name.sendKeys(ExcelReader.getValue("BRM", ruleName, "TestData"));
		WrapperFunctionUtilities.waitForTime(2000);

		//To select attribute
		ruleActionDropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("BRM", action, "TestData"));
		WrapperFunctionUtilities.waitForTime(2000);


		//add attribute expression
		/*
		 * expression_icon.click(); WrapperFunctionUtilities.waitForTime(1000);
		 * expression_formula_textarea.sendKeys(ExcelReader.getValue("BRM", expression1,
		 * "TestData")); WrapperFunctionUtilities.waitForTime(1000);
		 * expression_OK.click();
		 */

		//add attribute expression
		selectAttribute.click();
		WrapperFunctionUtilities.waitForElementVisibility(inputAttribute, 1000, driver);
		inputAttribute.sendKeys(ExcelReader.getValue("BRM", expression1, "TestData"));

		//to select operator
		/*	WrapperFunctionUtilities.waitForElementVisibility(selectOperator,1000,driver);
			selectOperator.click();

			selectOperator.sendKeys(ExcelReader.getValue("BRM", operator, "TestData"));
			CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("BRM", operator, "TestData"));*/
		selectOperatorOption(CommonPage.hyperlink(ExcelReader.getValue("BRM", operator, "TestData")));


		//add value expression

		expression_icon2.click();
		WrapperFunctionUtilities.waitForTime(1000);
		expression_formula_textarea.sendKeys(ExcelReader.getValue("BRM", expression2,
				"TestData"));
		WrapperFunctionUtilities.waitForTime(1000);
		expression_OK.click();

		WrapperFunctionUtilities.waitForTime(2000);
		testRuleButton.click();
		WrapperFunctionUtilities.waitForTime(2000);
		WrapperFunctionUtilities.waitForElementVisibility(rule_save, 2000, driver);
		//rule_save.click();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", rule_save);
		WrapperFunctionUtilities.waitForTime(2000);


	}

	//This function add new rule with lookup table
	public static void addRuleinGroupAdvanceBRMLookUp(String groupName, String ruleName, String subjectArea, String attribute, String operator, String lookupTable, String lookupColumn) throws Exception {
		BRMPage.getRuleGroupElement(ExcelReader.getValue("BRM", groupName, "TestData"));
		groupLink.click();
		WrapperFunctionUtilities.waitForTime(2000);
		add_rule.click();
		WrapperFunctionUtilities.waitForTime(2000);
		rule_name.sendKeys(ExcelReader.getValue("BRM", ruleName, "TestData"));

		//to select attribute
		/*
		 * expression_icon.click(); expression_formula_textarea.sendKeys("sum(ZIP_CD)");
		 * expression_OK.click();
		 */

		selectAttribute.click();
		WrapperFunctionUtilities.scrollByVisibleElement(inputAttribute, driver);
		inputAttribute.sendKeys(ExcelReader.getValue("BRM", "Expression1", "TestData"));

		//to select operator
		WrapperFunctionUtilities.waitForElementVisibility(selectOperator, 1000, driver);
		selectOperator.click();
		WrapperFunctionUtilities.waitForTime(2000);
		WrapperFunctionUtilities.waitForElementVisibility(inputOperator, 1000, driver);
			/*inputOperator.sendKeys(ExcelReader.getValue("BRM", operator, "TestData"));
			CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("BRM", operator, "TestData"));*/
		WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(ExcelReader.getValue("BRM", operator, "TestData")), driver);

		//Lookup radio Button
		lookupRadio.click();
		lookuptable.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("BRM", lookupTable, "TestData"));    //  will be used in case searchable dropdown is present
		lookupcolumn.click();
		lookupcolumnSearch.click();
			/*lookupcolumnSearch.sendKeys(ExcelReader.getValue("BRM", lookupColumn, "TestData"));
			CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("BRM", lookupColumn, "TestData"));*/
		WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(ExcelReader.getValue("BRM", lookupColumn, "TestData")), driver);
		WrapperFunctionUtilities.waitForTime(1000);
		rule_save.click();
		WrapperFunctionUtilities.waitForTime(2000);

	}

	//This function verifies audit log for add rule group
	public static boolean verifyAddRuleGroupAuditLog(String RuleGroupName, String RuleName) throws Exception {
		boolean result = false;
		String RuleGroupValue = "New rule group " + RuleGroupName + " created";
		Log.info(RuleGroupValue);
		String RuleNameValue = "New rule " + RuleName + " added in rule group " + RuleGroupName;
		Log.info(RuleNameValue);
		CommonPageFunctions.clickTabUnderEllipsis("Audit Log");
		WrapperFunctionUtilities.waitForTime(2000);
		String ActualRuleGroupValue = ruleGroupAddAuditLogValue.getText();
		String ActualRuleNameValue = ruleAddAuditLogValue.getText();
		Log.info("ActualRuleGroupValue  " + ActualRuleGroupValue + " ActualRuleNameValue:  " + ActualRuleNameValue);
		if (RuleGroupValue.contentEquals(ActualRuleGroupValue) && (RuleNameValue.contentEquals(ActualRuleNameValue))) {
			result = true;
		} else
			result = false;
		return result;
	}

	//Click any rule group
	public static void clickruleGroup(String ruleGroupName) {
		try {
			BRMPage.getRuleGroupElement(ExcelReader.getValue("BRM", ruleGroupName, "TestData"));
			groupLink.click();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	//This function is used to delete Rule
	public static void deleteRule(String ruleName) throws Exception {
		Log.info("Method : deleteRule");
		try {
			Actions actions = new Actions(driver);
			actions.click(getDeleteRuleElement(ExcelReader.getValue("BRM", ruleName, "TestData"))).build().perform();
		} catch (Exception e) {
			Log.info("Inside Exception Block");
			getDeleteRuleElement(ExcelReader.getValue("BRM", ruleName, "TestData")).click();
		}
		WrapperFunctionUtilities.waitForTime(2000);
		rule_group_ok.click();
	}

	//(Delete Rule) Verify Detail Column of Audit Logs have correct info in case Rule is Deleted
	public static boolean verifyRuleDeleteAuditLog(String RuleGroupName, String RuleName) throws Exception {
		boolean result = false;
		String RuleDeleteValue = "Rule " + RuleName + " is deleted from rule group " + RuleGroupName;
		Log.info(RuleDeleteValue);
		CommonPageFunctions.clickTabUnderEllipsis("Audit Log");
		WrapperFunctionUtilities.waitForTime(3000);
		WrapperFunctionUtilities.waitForElementVisibility(ruleDeleteAuditLogValue, 30, driver);
		String LogValue4 = ruleDeleteAuditLogValue.getText();
		if (RuleDeleteValue.contentEquals(LogValue4)) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	public static void deleteExistingBR(String rulegroupName) throws Exception {

		String ruleGroup = ExcelReader.getValue("BRM", rulegroupName, "TestData");


		WrapperFunctionUtilities.scrollByVisibleElement(getDeleteIconElement(
				ruleGroup), driver);
		getDeleteIconElement(ruleGroup);
		WrapperFunctionUtilities.waitForElementVisibility(deletelink, 2000, driver);
		WrapperFunctionUtilities.jsClick(getDeleteIconElement(ruleGroup), driver);


		// WrapperFunctionUtilities.waitForElementVisibility(rule_group_save, 1000,
		// driver);
		//rule_group_ok.click();
		deleteConfirmationButton.click();
		driver.navigate().refresh();
		WrapperFunctionUtilities.waitForTime(2000);
	}

	public static void selectOperatorOption(WebElement operatorOption) throws Exception {
		Log.info("Method : selectOperatorOption");
		WrapperFunctionUtilities.waitForElementVisibility(selectOperator, 1000, driver);
		selectOperator.click();
		WrapperFunctionUtilities.waitForTime(2000);
		WrapperFunctionUtilities.waitForElementVisibility(inputOperator, 1000, driver);
		WrapperFunctionUtilities.jsClick(operatorOption, driver);
	}

	public static void openFirstExistingBusinessRuleGroupAndAddRule() {
		Log.info("Method : openFirstExistingBusinessRuleGroupAndAddRule");
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_FirstRuleGroupCard_WebElement, 10, driver);
		brmPage_FirstRuleGroupCard_WebElement.click();
		WrapperFunctionUtilities.waitForElementVisibility(add_rule, 10, driver);
		add_rule.click();
		WrapperFunctionUtilities.waitForElementVisibility(rule_name, 10, driver);
	}

	public static boolean verifyErrorMessageForExpressionNameWordLimit(String invalidExpressionName, String validExpressionName) throws Exception {

		boolean flag = true;
		Log.info("Method : verifyErrorMessageForExpressionNameWordLimit");
		invalidExpressionName = ExcelReader.getValue("BRM", invalidExpressionName, "TestData");
		validExpressionName = ExcelReader.getValue("BRM", validExpressionName, "TestData");
		WrapperFunctionUtilities.scrollByVisibleElement(brmPage_EditExpressionNameIcon_WebElement, driver);
		brmPage_EditExpressionNameIcon_WebElement.click();
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_ExpressionName_TextBox, 10, driver);
		brmPage_ExpressionName_TextBox.sendKeys(invalidExpressionName);
		flag = WrapperFunctionUtilities.isElementPresent(brmPage_ErrorMessageExpressionNameWordLimit_WebElement, "brmPage_ErrorMessageExpressionNameWordLimit_WebElement") ? flag : false;
		flag = brmPage_OkButton_WebButton.isEnabled() ? false : flag;
		brmPage_ExpressionName_TextBox.clear();
		brmPage_ExpressionName_TextBox.sendKeys(validExpressionName);
		flag = brmPage_OkButton_WebButton.isEnabled() ? flag : false;
		brmPage_OkButton_WebButton.click();
		flag = WrapperFunctionUtilities.isElementPresent(expressionName(validExpressionName), validExpressionName) ? flag : false;
		return flag;
	}

	public static boolean verifySpecialCharacterCanBeUsedInExpressionName(String expressionNameWithSpecialChar) throws Exception {

		boolean flag = true;
		Log.info("Method : verifySpecialCharacterCanBeUsedInExpressionName");
		expressionNameWithSpecialChar = ExcelReader.getValue("BRM", expressionNameWithSpecialChar, "TestData");
		WrapperFunctionUtilities.scrollByVisibleElement(brmPage_EditExpressionNameIcon_WebElement, driver);
		brmPage_EditExpressionNameIcon_WebElement.click();
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_ExpressionName_TextBox, 10, driver);
		brmPage_ExpressionName_TextBox.clear();
		brmPage_ExpressionName_TextBox.sendKeys(expressionNameWithSpecialChar);
		flag = brmPage_OkButton_WebButton.isEnabled() ? flag : false;
		brmPage_OkButton_WebButton.click();
		flag = WrapperFunctionUtilities.isElementPresent(expressionName(expressionNameWithSpecialChar), expressionNameWithSpecialChar) ? flag : false;
		return flag;
	}


	public static String createNewRuleGroup(String entityName) throws Exception {

		String groupName = "AAA" + CommonPageFunctions.generateRandomString();
		add_rule_group.click();
		WrapperFunctionUtilities.waitForTime(2000);
		CommonPageFunctions.TypeKey(rule_group_name, groupName);
		brmPage_EntityDropDown_WebElement.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("BRM", entityName, "TestData"));
		WrapperFunctionUtilities.waitForElementVisibility(rule_group_save, 1000, driver);
		rule_group_save.click();
		return groupName;
	}

	public static void addNewRuleInGroup(String ruleName, String attribute, String operator, String value, String action, String include_exclude, boolean saveNewRule) throws Exception {
		WrapperFunctionUtilities.waitForElementVisibility(add_rule, 10, driver);
		add_rule.click();
		rule_name.sendKeys(ExcelReader.getValue("BRM", ruleName, "TestData"));
		//To select attribute
		selectAttribute.click();
		WrapperFunctionUtilities.waitForElementVisibility(inputAttribute, 10, driver);
		inputAttribute.sendKeys(ExcelReader.getValue("BRM", attribute, "TestData"));

		selectOperatorOption(CommonPage.hyperlink(ExcelReader.getValue("BRM", operator, "TestData")));
		//to select value
		WrapperFunctionUtilities.waitForElementVisibility(selectValue, 10, driver);
		WrapperFunctionUtilities.scrollByVisibleElement(selectValue, driver);
		//selectValue.click();
		inputValue.click();
		inputValue.sendKeys(ExcelReader.getValue("BRM", value, "TestData"));
		WrapperFunctionUtilities.waitForElementVisibility(rule_select_action, 10, driver);

		rule_select_action.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("BRM", action, "TestData"));

		if (!include_exclude.equals("")) {
			WrapperFunctionUtilities.scrollByVisibleElement(brmPage_RuleAction_RadioButtons(include_exclude), driver);
			brmPage_RuleAction_RadioButtons(include_exclude).click();
		}


		if (saveNewRule) {
			WrapperFunctionUtilities.waitForElementVisibility(rule_save, 1000, driver);
			rule_save.click();
			WrapperFunctionUtilities.waitForTime(2000);
		}
	}

	public static void openExistingBusinessRuleGroup(String groupName) throws Exception {
		Log.info("Method : openExistingBusinessRuleGroup");
		WrapperFunctionUtilities.scrollByVisibleElement(getRuleGroupElement(groupName), driver);
		groupLink.click();
	}

	public static void addMultipleExpressionsInRule(String ruleName, String attribute, String attribute2, String operator, String value, String value2, String action, boolean saveChanges) throws Exception {
		Log.info("Method : addMultipleExpressionsInRule");
		add_rule.click();
		rule_name.sendKeys(ExcelReader.getValue("BRM", ruleName, "TestData"));

		//To select attribute
		ruleActionDropdown.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("BRM", action, "TestData"));


		//To select attribute
		WrapperFunctionUtilities.scrollByVisibleElement(selectAttribute, driver);
		WrapperFunctionUtilities.waitForElementVisibility(selectAttribute, 10, driver);
		inputAttribute.sendKeys(ExcelReader.getValue("BRM", attribute, "TestData"));

		//to select operator
		WrapperFunctionUtilities.waitForElementVisibility(selectOperator, 10, driver);

		//selectOperator.click();
		selectOperatorOption(CommonPage.hyperlink(ExcelReader.getValue("BRM", operator, "TestData")));

		//to select value
		WrapperFunctionUtilities.waitForElementVisibility(selectValue, 10, driver);
		selectValue.click();
		inputValue.sendKeys(ExcelReader.getValue("BRM", value, "TestData"));

		addNewExpression.click();

		//To select attribute
		WrapperFunctionUtilities.waitForElementVisibility(selectAttributeExp2, 10, driver);
		WrapperFunctionUtilities.scrollByVisibleElement(selectAttributeExp2, driver);
		selectAttributeExp2.sendKeys(ExcelReader.getValue("BRM", attribute2, "TestData"));

		//to select operator
		WrapperFunctionUtilities.waitForElementVisibility(selectOperator, 10, driver);
		brmPage_selectOperator2_WebElement.click();
		//brmPage_selectOperator2_WebElement.sendKeys(ExcelReader.getValue("BRM", operator, "TestData"));
		equalOperatorExp2.click();

		//to select value
		WrapperFunctionUtilities.waitForElementVisibility(selectValueExp2, 10, driver);
		selectValueExp2.click();
		selectValueExp2.sendKeys(ExcelReader.getValue("BRM", value2, "TestData"));

		// click on exclude radio button
		WrapperFunctionUtilities.waitForElementVisibility(BRMPage.excludeRadioButton, 10, driver);
		excludeRadioButton.click();

		if (saveChanges) {
			rule_save.click();
			WrapperFunctionUtilities.waitForPageToLoad(30, driver);
			WrapperFunctionUtilities.waitForElementVisibility(add_rule, 10, driver);
		}
	}

	public static boolean editExistingExpressionForRuleAndVerifyRuleSummary(String ruleName, String attributeName, String updatedValue) throws Exception {
		Log.info("Method : editExistingExpressionForRuleAndVerifyRuleSummary");
		try {
			WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(ExcelReader.getValue("BRM", ruleName, "TestData")), driver);
		} catch (Exception e) {
			WrapperFunctionUtilities.refreshPageWithPageLoadTimeout(driver);
			WrapperFunctionUtilities.waitForElementVisibility(brmPage_FirstRuleGroup_WebElement, 30, driver);
			brmPage_FirstRuleGroup_WebElement.click();
		}
		updatedValue = ExcelReader.getValue("BRM", updatedValue, "TestData");
		attributeName = ExcelReader.getValue("BRM", attributeName, "TestData");
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_Expression1_WebElement, 10, driver);
		WrapperFunctionUtilities.scrollByVisibleElement(brmPage_Expression1_WebElement, driver);
		brmPage_Expression1_WebElement.click();
		WrapperFunctionUtilities.waitForElementVisibility(selectValue, 10, driver);
		selectValue.click();
		inputValue.clear();
		inputValue.sendKeys(updatedValue);
		System.out.println("Updated Value" + inputValue.getAttribute("value"));
		brmPage_Expression1_WebElement.click();
		boolean flag = WrapperFunctionUtilities.isElementPresent(brmPage_RuleSummaryText_WebElement(attributeName, updatedValue), "RuleSummaryText");
		WrapperFunctionUtilities.waitForElementVisibility(rule_save, 10, driver);
		rule_save.click();
		WrapperFunctionUtilities.waitForElementVisibility(add_rule, 10, driver);
		return flag;
	}

	public static void deleteExistingBRGroup(String ruleGroupName) throws Exception {
		Log.info("Method : deleteExistingBRGroup");
		WrapperFunctionUtilities.scrollByVisibleElement(getDeleteIconElement(
				ruleGroupName), driver);
		getDeleteIconElement(ruleGroupName);
		WrapperFunctionUtilities.waitForElementVisibility(deletelink, 2000, driver);
		WrapperFunctionUtilities.jsClick(getDeleteIconElement(ruleGroupName), driver);
		deleteConfirmationButton.click();
		driver.navigate().refresh();
	}

	public static boolean deleteExistingExpressionForRuleAndVerifyRuleSummary(String ruleName, String attributeName, String value) throws Exception {
		boolean flag = true;
		Log.info("Method : deleteExistingExpressionForRuleAndVerifyRuleSummary");
		try {
			WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(ExcelReader.getValue("BRM", ruleName, "TestData")), driver);
		} catch (Exception e) {
			WrapperFunctionUtilities.refreshPageWithPageLoadTimeout(driver);
			WrapperFunctionUtilities.waitForElementVisibility(brmPage_FirstRuleGroup_WebElement, 30, driver);
			brmPage_FirstRuleGroup_WebElement.click();
		}
		value = ExcelReader.getValue("BRM", value, "TestData");
		attributeName = ExcelReader.getValue("BRM", attributeName, "TestData");
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_Expression1_WebElement, 10, driver);
		WrapperFunctionUtilities.scrollByVisibleElement(brmPage_Expression1_WebElement, driver);
		flag = WrapperFunctionUtilities.isElementPresent(brmPage_RuleSummaryText_WebElement(attributeName, value), "RuleSummaryText");
		brmPage_DeleteIconExpression1_WebElement.click();
		flag = WrapperFunctionUtilities.waitForElementToBeInvisibileBy(brmPage_ruleSummaryText_Locator(attributeName, value), 10, driver) ? flag : false;
		WrapperFunctionUtilities.waitForElementVisibility(rule_save, 10, driver);
		rule_save.click();
		return flag;
	}

	public static boolean verifyRuleSummaryBesidesParameterName(String sheetName, String entityName, String attribute, String value, String operator, String action, String include_exclude) {
		Log.info("Method : verifyRuleSummaryBesidesParameterName");
		HashMap<String, String> testData = ExcelReader.getTestDataFromExcel(sheetName, "TestData");
		boolean flag = true;
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_RuleSummary_Operator_Expression1_WebElement(testData.get(entityName), testData.get(attribute)), 30, driver);
		flag = WrapperFunctionUtilities.isElementPresent(brmPage_RuleSummary_Operator_Expression1_WebElement(testData.get(entityName), testData.get(attribute)), "Rule Summary Expression1") ? flag : false;
		String parameterSummaryText = brmPage_RuleSummary_Operator_Expression1_WebElement(testData.get(entityName), testData.get(attribute)).getText();
		flag = parameterSummaryText.contains(testData.get(operator)) ? flag : false;
		flag = WrapperFunctionUtilities.isElementPresent(brmPage_RuleSummary_Action_Expression1_WebElement(testData.get(value)), "Rule Summary Expression1") ? flag : false;
		parameterSummaryText = brmPage_RuleSummary_Action_Expression1_WebElement(testData.get(value)).getText();
		flag = parameterSummaryText.contains("Then (" + include_exclude + ")") ? flag : false;
		return flag;
	}

	public static void changeRuleActionToTransform(String transformAttributeName, String transformValue) throws Exception {

		Log.info("Method : changeRuleActionToTransform");
		WrapperFunctionUtilities.scrollByVisibleElement(rule_select_action, driver);
		WrapperFunctionUtilities.jsClick(rule_select_action, driver);
		WrapperFunctionUtilities.jsClick(brmPage_TransformAction_DropDownOption, driver);

		WrapperFunctionUtilities.jsClick(selectTransformAttribute, driver);
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("BRM", transformAttributeName, "TestData"));
		BRMPage.value.click();
		BRMPage.value.sendKeys(ExcelReader.getValue("BRM", transformValue, "TestData"));
		selectValue.click();
	}

	public static boolean verifyRuleSummaryBesidesParameterNameForTransform(String sheetName, String entityName, String attribute, String value, String operator, String transformAttribute, String transformValue) {
		Log.info("Method : verifyRuleSummaryBesidesParameterNameForTransform");
		HashMap<String, String> testData = ExcelReader.getTestDataFromExcel(sheetName, "TestData");

		boolean flag = true;
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_RuleSummary_Operator_Expression1_WebElement(testData.get(entityName), testData.get(attribute)), 30, driver);
		flag = WrapperFunctionUtilities.isElementPresent(brmPage_RuleSummary_Operator_Expression1_WebElement(testData.get(entityName), testData.get(attribute)), "Rule Summary Expression1 Attribute Verification") ? flag : false;
		String parameterSummaryText = brmPage_RuleSummary_Operator_Expression1_WebElement(testData.get(entityName), testData.get(attribute)).getText();
		flag = parameterSummaryText.contains(testData.get(operator)) ? flag : false;
		flag = WrapperFunctionUtilities.isElementPresent(brmPage_RuleSummary_Action_Expression1_WebElement(testData.get(value)), "Rule Summary Expression1 Value Verification") ? flag : false;
		parameterSummaryText = brmPage_RuleSummary_Action_Expression1_WebElement(testData.get(value)).getText();
		Log.info("Parameter Summary Text" + parameterSummaryText);
		flag = parameterSummaryText.contains("Then (Transform") ? flag : false;
		flag = parameterSummaryText.contains(testData.get(transformAttribute) + " TO ") ? flag : false;
		flag = parameterSummaryText.contains(testData.get(transformValue) + ")") ? flag : false;
		return flag;
	}

	public static boolean verifyLookupColumnDropDownIsDisabledByDefault(String sheetName, String ruleName, String attribute, String operator, String lookupTable) throws Exception {

		Log.info("Method : verifyLookupColumnDropDownIsDisabledByDefault");
		HashMap<String, String> testData = ExcelReader.getTestDataFromExcel(sheetName, "TestData");
		boolean flag = true;

		WrapperFunctionUtilities.waitForElementVisibility(add_rule, 10, driver);
		add_rule.click();
		rule_name.sendKeys(testData.get(ruleName));
		selectAttribute.click();
		WrapperFunctionUtilities.waitForElementVisibility(inputAttribute, 10, driver);
		inputAttribute.sendKeys(testData.get(attribute));
		selectOperatorOption(CommonPage.hyperlink(testData.get(operator)));
		flag = WrapperFunctionUtilities.isElementPresent(lookuptable, "lookuptable");
		lookupRadio.click();
		String dropDownDisability = brmPage_LookupColumn_DropDown.getAttribute("ng-reflect-disabled");
		flag = dropDownDisability.equals("true") ? flag : false;
		Log.info("dropDownDisability Flag  " + flag);

		lookuptable.click();
		//brmPage_LookupTableSearch_TextBox.sendKeys(testData.get(lookupTable));
		CommonPageFunctions.hyperlinkClick(testData.get(lookupTable));
		dropDownDisability = brmPage_LookupColumn_DropDown.getAttribute("ng-reflect-disabled");
		flag = dropDownDisability.equals("false") ? flag : false;
		Log.info("dropDownDisability Flag  " + flag);
		return flag;
	}

	public static boolean verifyLookupTableAndLookupColumnHasSearchBox(String sheetName, String lookupTable, String lookupColumn) throws Exception {

		Log.info("Method : verifyLookupTableAndLookupColumnHasSearchBox");

		HashMap<String, String> testData = ExcelReader.getTestDataFromExcel(sheetName, "TestData");
		boolean flag = true;
		lookuptable.click();
		//brmPage_LookupTableSearch_TextBox.sendKeys(testData.get(lookupTable));
		CommonPageFunctions.hyperlinkClick(testData.get(lookupTable));
		lookupcolumn.click();
		flag = WrapperFunctionUtilities.isElementPresent(lookupcolumnSearch, "Searchable TextBox in Lookup Column Dropdown");
		Log.info("lookupcolumnSearch Flag  " + flag);
		lookupcolumnSearch.click();
		int numberOfColumnsBeforeSearch = brmPage_LookupColumn_DropDownOptions().size();
		Log.info("numberOfColumnsBeforeSearch   " + numberOfColumnsBeforeSearch);
		lookupcolumnSearch.sendKeys(testData.get(lookupColumn));
		WrapperFunctionUtilities.waitForTime(2000);//waiting for results to get dropdown to filter the results
		int numberOfColumnsAfterSearch = brmPage_LookupColumn_DropDownOptions().size();
		Log.info("numberOfColumnsAfterSearch   " + numberOfColumnsAfterSearch);
		flag = numberOfColumnsAfterSearch < numberOfColumnsBeforeSearch ? flag : false;
		Log.info("numberOfColumnsAfterSearch Flag  " + flag);
		WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(testData.get(lookupColumn)), driver);
		return flag;
	}

	public static boolean verifyLookupColumnDropDownResetsOnChangingSourceTable(String sheetName, String lookupTableNew) throws Exception {

		Log.info("Method : verifyLookupColumnDropDownResetsOnChangingSourceTable");

		HashMap<String, String> testData = ExcelReader.getTestDataFromExcel(sheetName, "TestData");
		boolean flag = true;

		lookuptable.click();
		CommonPageFunctions.hyperlinkClick(testData.get(lookupTableNew));
		//flag = WrapperFunctionUtilities.isElementPresent(brmPage_LookupColumnDD_ErrorMessage, "brmPage_LookupColumnDD_ErrorMessage")?flag:false;
		flag = lookupcolumn.getText().equals("Select Value") ? flag : false;
		return flag;
	}

	public static boolean verifyActionDropDownIsNotMultiSelect() throws Exception {
		Log.info("Method : verifyActionDropDownIsNotMultiSelect");
		boolean flag = true;
		WrapperFunctionUtilities.scrollByVisibleElement(rule_select_action, driver);
		//Dropdown Is not multiSelect Dropdown
		Log.info("brmPage_Action_DropDown isElementPresent " + flag);
		Select actionDropDown = new Select(brmPage_Action_DropDown);
		flag = actionDropDown.isMultiple() == false ? flag : false;
		Log.info("actionDropDown.isMultiple " + flag);
		flag = actionDropDown.getOptions().size() == 2 ? flag : false;
		Log.info("actionDropDown Options Size " + flag);
		return flag;
	}

	public static boolean verifyActionDropDownOptionsOnRuleSummaryPage(String sheetName, String action, String include_exclude) throws Exception {

		Log.info("Method : verifyActionDropDownOnRuleSummaryPage");
		HashMap<String, String> testData = ExcelReader.getTestDataFromExcel(sheetName, "TestData");
		boolean flag = true;

		WrapperFunctionUtilities.jsClick(rule_select_action, driver);
		WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(testData.get(action)), driver);
		WrapperFunctionUtilities.scrollByVisibleElement(brmPage_RuleAction_RadioButtons(include_exclude), driver);
		flag = WrapperFunctionUtilities.isElementPresent(brmPage_RuleAction_RadioButtons(include_exclude), "brmPage_RuleAction_RadioButtons") ? flag : false;
		Log.info("brmPage_RuleAction_RadioButtons existence " + flag);
		WrapperFunctionUtilities.jsClick(rule_select_action, driver);
		WrapperFunctionUtilities.jsClick(brmPage_TransformAction_DropDownOption, driver);
		flag = WrapperFunctionUtilities.isElementPresent(selectTransformAttribute, "selectTransformAttribute") ? flag : false;
		;
		Log.info("selectTransformAttribute Dropdown existence " + flag);

		return flag;
	}

	public static boolean publishNewVersionOfBusinessRule(String versionName, boolean verifyErrorMessage, String errorMessageText, boolean expectedExistence) throws Exception {
		boolean flag = true;
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_PublishNewVersion_Button, 30, driver);
		brmPage_PublishNewVersion_Button.click();
		brmPage_VersionName_TextBox.sendKeys(versionName);
		if (verifyErrorMessage) {
			errorMessageText = ExcelReader.getValue("BRM", errorMessageText, "TestData");
			flag = CommonPageFunctions.storeExistenceOfElement(brmPage_VersionNameErrorMessage_Locator(errorMessageText), driver);
			flag = (flag == expectedExistence) ? true : false;
		}
		brmPage_OkButtonDialogBox_Button.click();
		return flag;
	}

	public static void updateRuleDescription(String ruleName, String ruleDescription) {
		Log.info("Method : updateRuleDescription");
		try {
			WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(ExcelReader.getValue("BRM", ruleName, "TestData")), driver);
		} catch (Exception e) {
			WrapperFunctionUtilities.refreshPageWithPageLoadTimeout(driver);
			WrapperFunctionUtilities.waitForElementVisibility(brmPage_FirstRuleGroup_WebElement, 30, driver);
			brmPage_FirstRuleGroup_WebElement.click();
		}
		brmPage_RuleDescription_TextBox.click();
		CommonPageFunctions.TypeKey(brmPage_RuleDescription_TextBox, ruleDescription);
		rule_save.click();
	}

	public static void addRuleInGroupWithTransformAction(String sheetName, String ruleName, String transformAttributeName, String attribute, String operator, String value, String transformValue, boolean saveNewRule) throws Exception {

		Log.info("Method : addRuleInGroupWithTransformAction");
		HashMap<String, String> testData = ExcelReader.getTestDataFromExcel(sheetName, "TestData");
		WrapperFunctionUtilities.waitForElementVisibility(add_rule, 10, driver);
		add_rule.click();

		rule_name.sendKeys(testData.get(ruleName));

		WrapperFunctionUtilities.scrollByVisibleElement(rule_select_action, driver);
		WrapperFunctionUtilities.jsClick(rule_select_action, driver);
		WrapperFunctionUtilities.jsClick(brmPage_TransformAction_DropDownOption, driver);

		WrapperFunctionUtilities.jsClick(selectTransformAttribute, driver);
		CommonPageFunctions.hyperlinkClick(testData.get(transformAttributeName));

		//To select attribute
		selectAttribute.click();
		WrapperFunctionUtilities.waitForElementVisibility(inputAttribute, 10, driver);
		inputAttribute.sendKeys(testData.get(attribute));

		selectOperatorOption(CommonPage.hyperlink(testData.get(operator)));
		//to select value
		WrapperFunctionUtilities.waitForElementVisibility(selectValue, 10, driver);
		WrapperFunctionUtilities.scrollByVisibleElement(selectValue, driver);
		//selectValue.click();
		inputValue.click();
		inputValue.sendKeys(testData.get(value));

		BRMPage.value.click();
		BRMPage.value.sendKeys(testData.get(transformValue));
		selectValue.click();

		if (saveNewRule) {
			WrapperFunctionUtilities.waitForElementVisibility(rule_save, 1000, driver);
			rule_save.click();
			WrapperFunctionUtilities.waitForTime(2000);
		}
	}

	public static boolean addElseCondition(String value) throws Exception {
		Log.info("Method : addElseCondition");
		WrapperFunctionUtilities.waitForElementVisibility(addElseAction, 30, driver);
		WrapperFunctionUtilities.scrollByVisibleElement(addElseAction, driver);
		boolean existenceOfElement = WrapperFunctionUtilities.isElementPresent(addElseAction, "addNewExpression");
		addElseAction.click();
		WrapperFunctionUtilities.scrollByVisibleElement(brmPage_ElseValue_TextBox, driver);
		brmPage_ElseValue_TextBox.sendKeys(ExcelReader.getValue("BRM", value, "TestData"));
		brmPage_RuleSummary_Text.click();
		return existenceOfElement;
	}

	public static boolean changeActionToIncludeExcludeAndVerifyElseConditionIsRemoved(String includeExcludeRadioOption) throws Exception {
		boolean flag = true;
		Log.info("Method : changeActionToIncludeAndVerifyRuleSummaryGetsUpdated");
		String initialRuleSummary = brmPage_RuleSummary_Text.getText();
		Log.info("Initial Rule Summary " + initialRuleSummary);
		String[] initialRuleSummaryArray = initialRuleSummary.split("Then");
		initialRuleSummary = initialRuleSummaryArray[0];
		Log.info("Initial Rule Summary After Split" + initialRuleSummary);
		WrapperFunctionUtilities.scrollByVisibleElement(brmPage_Action_DropDown, driver);
		WrapperFunctionUtilities.jsClick(brmPage_Action_DropDown, driver);
		WrapperFunctionUtilities.jsClick(brmPage_IncludeExcludeAction_DropDownOption, driver);
		//includeExcludeRadioOption = ExcelReader.getValue("BRM", includeExcludeRadioOption, "TestData");
		WrapperFunctionUtilities.scrollByVisibleElement(brmPage_RuleAction_RadioButtons(includeExcludeRadioOption), driver);
		brmPage_RuleAction_RadioButtons(includeExcludeRadioOption).click();
		//flag = CommonPageFunctions.storeExistenceOfElement(brmPage_AddElseAction_Locator, driver);
		flag = WrapperFunctionUtilities.waitForElementToBeInvisibileBy(brmPage_AddElseAction_Locator, 10, driver);
		//flag= flag?true:false;
		String updatedRuleSummary = brmPage_RuleSummary_Text.getText();
		Log.info("Updated Rule Summary " + updatedRuleSummary);
		flag = updatedRuleSummary.equals(initialRuleSummary + "Then (" + includeExcludeRadioOption + ")") ? flag : false;
		return flag;
	}

	public static boolean verifyRuleSummaryText(String sheetName, String entity, String attribute, String attribute2, String operator, String value, String value2) {
		boolean flag = true;
		Log.info("Method : verifyRuleSummaryText");
		HashMap<String, String> testData = ExcelReader.getTestDataFromExcel(sheetName, "TestData");
		String ruleSummaryText = brmPage_RuleSummary_Text.getText();
		Log.info("Initial Rule Summary " + ruleSummaryText);
		flag = ruleSummaryText.contains("If " + testData.get(entity) + "." + testData.get(attribute) + " " + testData.get(operator) + " " + testData.get(value) + " Then (Include)") ? flag : false;
		flag = ruleSummaryText.contains("AND If " + testData.get(entity) + "." + testData.get(attribute2) + " " + testData.get(operator) + " " + testData.get(value2) + " Then (Exclude)") ? flag : false;
		return flag;
	}

	public static void clickSaveRuleButton() {
		Log.info("Method : saveRule ");
		WrapperFunctionUtilities.waitForElementVisibility(rule_save, 1000, driver);
		rule_save.click();
	}

	public static boolean addNewRuleInGroupThroughExpressionIcon(String sheetName, String ruleName, String attribute, String operator, String value, String action, String include_exclude, boolean saveNewRule) throws Exception {
		Log.info("Method : addNewRuleInGroupThroughExpressionIcon");

		boolean flag = true;
		WrapperFunctionUtilities.waitForElementVisibility(add_rule, 10, driver);
		HashMap<String, String> testData = ExcelReader.getTestDataFromExcel(sheetName, "TestData");

		add_rule.click();
		rule_name.sendKeys(testData.get(ruleName));
		//To select attribute
		expression_icon.click();
		WrapperFunctionUtilities.waitForElementVisibility(expression_formula_textarea, 10, driver);
		expression_formula_textarea.clear();
		expression_formula_textarea.sendKeys(testData.get(attribute));
		expression_OK.click();
		flag = inputAttribute.getAttribute("value").equals(testData.get(attribute)) ? flag : false;

		//selectOperatorOption(CommonPage.hyperlink(ExcelReader.getValue("BRM", operator, "TestData")));
		selectOperatorOption(CommonPage.hyperlink(testData.get(operator)));

		//selectValue.click();
		expression_icon2.click();
		expression_formula_textarea.clear();
		expression_formula_textarea.sendKeys(testData.get(value));
		expression_OK.click();

		flag = inputValue.getAttribute("value").equals(testData.get(value)) ? flag : false;

		rule_select_action.click();
		CommonPageFunctions.hyperlinkClick(testData.get(action));

		if (!include_exclude.equals("")) {
			WrapperFunctionUtilities.scrollByVisibleElement(brmPage_RuleAction_RadioButtons(include_exclude), driver);
			brmPage_RuleAction_RadioButtons(include_exclude).click();
		}

		if (saveNewRule) {
			WrapperFunctionUtilities.waitForElementVisibility(rule_save, 1000, driver);
			rule_save.click();
		}

		return flag;
	}


	public static void addAdvanceRuleInGroup(String sheetName, String ruleName, String action, String transformAttributeName, String attribute, String operator, String lookupTable, String lookupColumn, String transformedValue, boolean saveNewRule) throws Exception {

		Log.info("Method : addAdvanceRuleInGroup");
		HashMap<String, String> testData = ExcelReader.getTestDataFromExcel(sheetName, "TestData");
		WrapperFunctionUtilities.waitForElementVisibility(add_rule, 10, driver);
		add_rule.click();

		rule_name.sendKeys(testData.get(ruleName));

		WrapperFunctionUtilities.scrollByVisibleElement(rule_select_action, driver);
		WrapperFunctionUtilities.jsClick(rule_select_action, driver);
		if (testData.get(action).equals("Transform")) {
			WrapperFunctionUtilities.jsClick(brmPage_TransformAction_DropDownOption, driver);
			WrapperFunctionUtilities.jsClick(selectTransformAttribute, driver);
			CommonPageFunctions.hyperlinkClick(testData.get(transformAttributeName));

		} else {
			WrapperFunctionUtilities.jsClick(brmPage_IncludeExcludeAction_DropDownOption, driver);
		}

		//To select attribute
		selectAttribute.click();
		WrapperFunctionUtilities.waitForElementVisibility(inputAttribute, 10, driver);
		inputAttribute.sendKeys(testData.get(attribute));

		selectOperatorOption(CommonPage.hyperlink(testData.get(operator)));

		lookupRadio.click();
		lookuptable.click();
		CommonPageFunctions.hyperlinkClick(testData.get(lookupTable));

		lookupcolumn.click();
		lookupcolumnSearch.click();
		lookupcolumnSearch.sendKeys(testData.get(lookupColumn));
		WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(testData.get(lookupColumn)), driver);

		//Then
		if (testData.get(action).equals("Transform")) {
			BRMPage.value.click();
			BRMPage.value.sendKeys(testData.get(transformedValue));
		}

		if (saveNewRule) {
			WrapperFunctionUtilities.waitForElementVisibility(rule_save, 1000, driver);
			rule_save.click();
			WrapperFunctionUtilities.waitForTime(2000);
		}
	}

	public static boolean addNewExpression(String sheetName, String attribute, String operator, String value, String transformedValue) throws Exception {
		HashMap<String, String> testData = ExcelReader.getTestDataFromExcel(sheetName, "TestData");
		WrapperFunctionUtilities.waitForTime(1000);
		boolean existenceOfElement = WrapperFunctionUtilities.isElementPresent(addNewExpression, "addNewExpression");
		addNewExpression.click();
		WrapperFunctionUtilities.waitForTime(1000);

		//To select attribute
		selectAttributeExp2.click();
		WrapperFunctionUtilities.waitForElementVisibility(selectAttributeExp2, 1000, driver);
		selectAttributeExp2.sendKeys(testData.get(attribute));


		//to select operator
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_selectOperator2_WebElement, 1000, driver);
		brmPage_selectOperator2_WebElement.click();
		WrapperFunctionUtilities.jsClick(brmPage_LastOperator_Option(testData.get(operator)), driver);


		//to select value
		WrapperFunctionUtilities.waitForElementVisibility(selectValueExp2, 1000, driver);
		selectValueExp2.click();
		selectValueExp2.sendKeys(testData.get(value));

		//Then condition
		if (!transformedValue.equals("")) {
			WrapperFunctionUtilities.waitForElementVisibility(BRMPage.valueExp2, 1000, driver);
			BRMPage.valueExp2.click();
			BRMPage.valueExp2.sendKeys(testData.get(transformedValue));
		}

		return existenceOfElement;
	}

	public static boolean verifyAllTheSelectedAttributesInRule(String ruleName, String attribute1, String operator1, String lookupTable,
															   String lookupColumn, String transformedValue, String attribute2,
															   String operator2, String value2, String transformedValue2,
															   String elseConditionValue) throws Exception {

		Log.info("Method : verifyAllTheSelectedAttributesInRule");

		HashMap<String, String> testData = ExcelReader.getTestDataFromExcel("BRM", "TestData");
		boolean flag = true;

		try {
			WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(testData.get(ruleName)), driver);
		} catch (Exception e) {
			WrapperFunctionUtilities.refreshPageWithPageLoadTimeout(driver);
			WrapperFunctionUtilities.waitForElementVisibility(brmPage_FirstRuleGroup_WebElement, 30, driver);
			brmPage_FirstRuleGroup_WebElement.click();
		}

		WrapperFunctionUtilities.scrollByVisibleElement(brmPage_Expression1_WebElement, driver);
		brmPage_Expression1_WebElement.click();

		flag = inputAttribute.getAttribute("value").equals(testData.get(attribute1)) ? flag : false;
		Log.info("Actual Value inputAttribute : " + inputAttribute.getAttribute("value"));
		Log.info("Expecte Value : " + testData.get(attribute1));
		Log.info("inputAttribute flag : " + flag);

		flag = brmPage_OperatorDropDownInExpressionNumber(1).getText().equals(testData.get(operator1)) ? flag : false;
		Log.info("Actual Value OperatorDropDown : " + brmPage_OperatorDropDownInExpressionNumber(1).getText());
		Log.info("Expected Value : " + testData.get(operator1));
		Log.info("OperatorDropDown flag : " + flag);

		flag = lookuptable.getText().equals(testData.get(lookupTable)) ? flag : false;
		Log.info("Actual Value lookuptable : " + lookuptable.getText());
		Log.info("Expected Value : " + testData.get(lookupTable));
		Log.info("lookuptable flag : " + flag);

		flag = lookupcolumn.getText().equals(testData.get(lookupColumn)) ? flag : false;
		Log.info("Actual Value lookupcolumn : " + lookupcolumn.getText());
		Log.info("Expected Value : " + testData.get(lookupColumn));
		Log.info("lookupcolumn flag : " + flag);

		flag = BRMPage.value.getAttribute("value").equals(testData.get(transformedValue)) ? flag : false;
		Log.info("Actual Then TextBox value : " + BRMPage.value.getAttribute("value"));
		Log.info("Expected Value : " + testData.get(transformedValue));
		Log.info("Then TextBox value flag : " + flag);

		WrapperFunctionUtilities.scrollByVisibleElement(brmPage_Expression2_WebElement, driver);
		brmPage_Expression2_WebElement.click();

		flag = selectAttributeExp2.getAttribute("value").equals(testData.get(attribute2)) ? flag : false;
		Log.info("Actual Value inputAttribute  Expression 2 : " + selectAttributeExp2.getAttribute("value"));
		Log.info("Expected Value : " + testData.get(attribute2));
		Log.info("inputAttribute Expression 2 flag : " + flag);

		flag = brmPage_OperatorDropDownInExpressionNumber(2).getText().equals(testData.get(operator2)) ? flag : false;
		Log.info("Actual Value OperatorDropDown Expression 2 : " + brmPage_OperatorDropDownInExpressionNumber(2).getText());
		Log.info("Expected Value : " + testData.get(operator2));
		Log.info("OperatorDropDown Expression 2 flag : " + flag);

		flag = selectValueExp2.getAttribute("value").equals(testData.get(value2)) ? flag : false;
		Log.info("Actual Attribute value Expression 2  : " + selectValueExp2.getAttribute("value"));
		Log.info("Expected Value : " + testData.get(value2));
		Log.info("value Expression 2 flag : " + flag);

		flag = valueExp2.getAttribute("value").equals(testData.get(transformedValue2)) ? flag : false;
		Log.info("Actual Value Trasformed value Expression 2 : " + valueExp2.getAttribute("value"));
		Log.info("Expected Value : " + testData.get(transformedValue2));
		Log.info("Trasformed value Expression 2 flag : " + flag);

		WrapperFunctionUtilities.scrollByVisibleElement(brmPage_ElseExpression_WebElement, driver);
		brmPage_ElseExpression_WebElement.click();

		flag = elseValue.getAttribute("value").equals(testData.get(elseConditionValue)) ? flag : false;
		Log.info("Actual Value Trasformed value Expression 2 : " + elseValue.getAttribute("value"));
		Log.info("Expected Value : " + testData.get(elseConditionValue));
		Log.info("Trasformed value Expression 2 flag : " + flag);

		return flag;
	}

	public static List<String> storeLookUpTableDropDownOptions() {
		Log.info("Method : storeLookUpTableDropDownOptions");
		List<String> lookUpEntityOptionName = new ArrayList<>();
		for (WebElement lookUpEntityOption : brmPage_LookupTable_DropDownOptions()) {
			if (!lookUpEntityOption.getAttribute("title").equals("Select Value"))
				lookUpEntityOptionName.add(lookUpEntityOption.getAttribute("title"));
		}
		return lookUpEntityOptionName;
	}

	public static boolean verifyLookUpEntityIsNotPresentInRuleGroupEntityDropDown(List<String> lookUpEntityOptions) {
		Log.info("Method : verifyLookUpEntityIsNotPresentInRuleGroupEntityDropDown");
		boolean flag = true;
		add_rule_group.click();
		CommonPageFunctions.TypeKey(rule_group_name, "TempGroup");
		brmPage_EntityDropDown_WebElement.click();
		List<String> ruleGroupEnityOptionName = new ArrayList<>();
		for (WebElement entityOptions : brmPage_EntityRuleGroup_DropDown()) {
			ruleGroupEnityOptionName.add(entityOptions.getAttribute("title"));
		}
		System.out.println("lookUpEntityOptions   " + lookUpEntityOptions);
		System.out.println("ruleGroupEntityOptionName   " + ruleGroupEnityOptionName);
		if (ruleGroupEnityOptionName.size() == 1) {
			flag = false;
			Log.info("Only one value is populated in the Enitity Dropdown");
		}
		for (String lookUpEntityOption : lookUpEntityOptions) {
			if (ruleGroupEnityOptionName.contains(lookUpEntityOption)) {
				flag = false;
			}
		}
		return flag;
	}

	//This method creates new unique rule group by providing description
	public static String createNewRuleGroup(String entityName, String description) throws Exception {
		Log.info("Method : createNewRuleGroup");
		String groupName = "AAA" + CommonPageFunctions.generateRandomString();
		add_rule_group.click();
		WrapperFunctionUtilities.waitForTime(2000);
		CommonPageFunctions.TypeKey(rule_group_name, groupName);
		brmPage_EntityDropDown_WebElement.click();
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("BRM", entityName, "TestData"));
		WrapperFunctionUtilities.waitForElementVisibility(rule_group_save, 1000, driver);
		brmPage_RuleGroupDescription_TextBox.sendKeys(ExcelReader.getValue("BRM", description, "TestData"));
		rule_group_save.click();
		return groupName;
	}

	//This method verifies the data present on the Rule group Card
	public static boolean verifyDataOnRuleGroupCard(String ruleGroupName, String entityName, String ruleGroupDescription, String lastEditTime, String rulesCount) throws Exception {

		Log.info("Method : verifyDataOnRuleGroupCard");
		HashMap<String, String> testData = ExcelReader.getTestDataFromExcel("BRM", "TestData");
		WrapperFunctionUtilities.scrollByVisibleElement(getDeleteIconElement(
				ruleGroupName), driver);
		boolean flag = WrapperFunctionUtilities.isElementPresent(brmPage_EntityNameRuleGroupCard_Text(ruleGroupName, testData.get(entityName)), "brmPage_EntityNameRuleGroupCard_Text");
		WrapperFunctionUtilities.scrollByVisibleElement(brmPage_EntityNameRuleGroupCard_Text(ruleGroupName, testData.get(entityName)), driver);
		flag = WrapperFunctionUtilities.isElementPresent(brmPage_DescriptionRuleGroupCard_Text(ruleGroupName, testData.get(ruleGroupDescription)), "brmPage_DescriptionRuleGroupCard_Text") ? flag : false;
		flag = WrapperFunctionUtilities.isElementPresent(brmPage_LastEditRuleGroupCard_Text(ruleGroupName, lastEditTime), "brmPage_LastEditRuleGroupCard_Text") ? flag : false;
		flag = WrapperFunctionUtilities.isElementPresent(brmPage_RuleCountRuleGroupCard_Text(ruleGroupName, rulesCount), "brmPage_RuleCountRuleGroupCard_Text") ? flag : false;
		return flag;
	}

	public static boolean verifySearchFunctionalityOnCardsTab(String ruleGroupName, String entityName, String ruleGroupDescription) throws Exception {

		Log.info("Method : verifySearchFunctionalityOnCardsTab");
		HashMap<String, String> testData = ExcelReader.getTestDataFromExcel("BRM", "TestData");
		String numberOfRuleGroups = brmPage_NumberOfRuleGroups_Text.getText().trim();
		searchRuleGroupbox.sendKeys(ruleGroupName);
		System.out.println("Count of Rule Groups : " + numberOfRuleGroups);
		groupSearchButton.click();

		boolean flag = WrapperFunctionUtilities.isElementPresent(getRuleGroupElement(ruleGroupName), "getRuleGroupElement") ? true : false;
		flag = WrapperFunctionUtilities.isElementPresent(brmPage_OneRuleGroups_Text, "brmPage_OneRuleGroups_Text") ? flag : false;
		flag = brmPage_RuleGroupCards_WebElement.size() == 1 ? flag : false;
		System.out.println("Value of Flag after groupName Search: " + flag);

		brmPage_CloseIconSearchRulesTextBox_WebElement.click();

		searchRuleGroupbox.sendKeys(testData.get(entityName));
		groupSearchButton.click();
		flag = WrapperFunctionUtilities.isElementPresent(brmPage_EntityNameRuleGroupCard_Text(ruleGroupName, testData.get(entityName)), "brmPage_EntityNameRuleGroupCard_Text") ? flag : false;
		flag = brmPage_RuleGroupCards_WebElement.size() < Integer.parseInt(numberOfRuleGroups) ? flag : false;

		for (WebElement entityNameRuleGroup : brmPage_EntityNameRuleGroupCard_List) {
			System.out.println("Entity Name on Rule Groups Tabs After Entity Search: " + entityNameRuleGroup.getText());
			if (!entityNameRuleGroup.getText().contains(testData.get(entityName))) {
				flag = false;
				break;
			}
		}

		System.out.println("Count of Rule Groups After Entity Search: " + brmPage_RuleGroupCards_WebElement.size());
		System.out.println("Value of Flag after Entity Search: " + flag);

		brmPage_CloseIconSearchRulesTextBox_WebElement.click();

		searchRuleGroupbox.sendKeys(testData.get(ruleGroupDescription));
		groupSearchButton.click();
		flag = WrapperFunctionUtilities.isElementPresent(brmPage_DescriptionRuleGroupCard_Text(ruleGroupName, testData.get(ruleGroupDescription)), "brmPage_DescriptionRuleGroupCard_Text") ? flag : false;
		flag = brmPage_RuleGroupCards_WebElement.size() < Integer.parseInt(numberOfRuleGroups) ? flag : false;

		for (WebElement descriptionRuleGroup : brmPage_DescriptionRuleGroupCard_List) {
			System.out.println("Description on Rule Groups After Entity Search: " + descriptionRuleGroup.getAttribute("data-title"));
			if (!descriptionRuleGroup.getAttribute("data-title").contains(testData.get(ruleGroupDescription))) {
				flag = false;
				break;
			}
		}

		System.out.println("Count of Rule Groups After Description Search: " + brmPage_RuleGroupCards_WebElement.size());
		System.out.println("Value of Flag after Description Search: " + flag);
		return flag;
	}

	public static boolean verifyColumnHeadersOnTableTabAndEditDeleteIconScreen() {
		Log.info("Method : verifyColumnHeadersOnTableTabAndEditDeleteIconScreen");
		brmPage_TablesTab_WebElement.click();
		boolean flag = WrapperFunctionUtilities.isElementPresent(searchRuleGroupbox, "searchRuleGroupbox");
		flag = WrapperFunctionUtilities.isElementPresent(groupSearchButton, "groupSearchButton") ? flag : false;
		flag = WrapperFunctionUtilities.isElementPresent(brmPage_ColumnHeaders_WebElement("Name"), "Name") ? flag : false;
		flag = WrapperFunctionUtilities.isElementPresent(brmPage_ColumnHeaders_WebElement("Description"), "Description") ? flag : false;
		flag = WrapperFunctionUtilities.isElementPresent(brmPage_ColumnHeaders_WebElement("Rules Count"), "Rules Count") ? flag : false;
		flag = WrapperFunctionUtilities.isElementPresent(brmPage_ColumnHeaders_WebElement("Entity"), "Entity") ? flag : false;
		flag = WrapperFunctionUtilities.isElementPresent(brmPage_ColumnHeaders_WebElement("Last Modified"), "Last Modified") ? flag : false;
		flag = WrapperFunctionUtilities.isElementPresent(brmPage_ColumnHeaders_WebElement("Actions"), "Actions") ? flag : false;

		flag = (brmPage_RuleGroupNames_List.size() == brmPage_RuleGroupEditIcon_List.size()) && (brmPage_RuleGroupEditIcon_List.size() > 0) ? flag : false;
		flag = (brmPage_RuleGroupNames_List.size() == brmPage_RuleGroupDeleteIcon_List.size()) && (brmPage_RuleGroupDeleteIcon_List.size() > 0) ? flag : false;
		return flag;
	}

	public static boolean verifySuccessToasterMessageForPublishingNewVersion(String versionName) {
		Log.info("Method : verifySuccessToasterMessageForPublishingNewVersion");
		return WrapperFunctionUtilities.isElementPresent(brmPage_RuleGroupVersionPublishedMessage_WebElement(versionName), "brmPage_RuleGroupVersionPublishedMessage_WebElement");
	}

	public static void clickCancelPublishNewVersion() {
		Log.info("Method : clickCancelPublishNewVersion");
		brmPage_CancelPublishNewVersion_Button.click();
	}

	public static boolean verifyLastEditByUsernameOnRuleGroupCard(String ruleGroupName, String userName) throws Exception {
		Log.info("Method : verifyLastEditByUsernameOnRuleGroupCard");
		WrapperFunctionUtilities.scrollByVisibleElement(getRuleGroupElement(ruleGroupName), driver);
		boolean existence = WrapperFunctionUtilities.isElementPresent(brmPage_LastEditedByUserInfo_WebElement(ruleGroupName, userName), "brmPage_LastEditedByUserInfo_WebElement");
		return existence;
	}

	public static boolean verifyRuleCreatedByAndLastEditByUserInformation(String ruleName, String userName) throws Exception {

		Log.info("Method : verifyRuleCreatedByAndLastEditByUserInformation");
		boolean existence = WrapperFunctionUtilities.isElementPresent(brmPage_RuleCreatedBy_WebElement(ruleName, userName), "brmPage_RuleCreatedBy_WebElement");
		existence = WrapperFunctionUtilities.isElementPresent(brmPage_RuleLastEditedBy_WebElement(ruleName, userName), "brmPage_RuleLastEditedBy_WebElement") ? existence : false;
		return existence;
	}

	public static boolean verifyRuleLastEditByUserInformation(String ruleName, String userName) throws Exception {

		Log.info("Method : verifyRuleLastEditByUserInformation");
		boolean existence;
		try {
			existence = WrapperFunctionUtilities.isElementPresent(brmPage_RuleLastEditedBy_WebElement(ruleName, userName), "brmPage_RuleLastEditedBy_WebElement");
		} catch (Exception e) {
			WrapperFunctionUtilities.refreshPageWithPageLoadTimeout(driver);
			existence = WrapperFunctionUtilities.isElementPresent(brmPage_RuleLastEditedBy_WebElement(ruleName, userName), "brmPage_RuleLastEditedBy_WebElement");
		}
		return existence;
	}

	public static boolean editStartDateForRule(String ruleName) throws Exception {
		Log.info("Method : editStartDateForRule");
		try {
			WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(ExcelReader.getValue("BRM", ruleName, "TestData")), driver);
		} catch (Exception e) {
			WrapperFunctionUtilities.refreshPageWithPageLoadTimeout(driver);
			WrapperFunctionUtilities.waitForElementVisibility(brmPage_FirstRuleGroup_WebElement, 30, driver);
			brmPage_FirstRuleGroup_WebElement.click();
		}
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_StartDateRule_WebElement, 10, driver);
		brmPage_StartDateRule_WebElement.click();
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_NextDayForCurrentStartDate_WebElement, 10, driver);
		WrapperFunctionUtilities.waitForTime(2000);
		WrapperFunctionUtilities.jsClick(brmPage_NextDayForCurrentStartDate_WebElement, driver);
		WrapperFunctionUtilities.scrollByVisibleElement(rule_save, driver);
		rule_save.click();
		boolean flag = WrapperFunctionUtilities.isElementPresent(add_rule, "add_rule");
		return flag;
	}

	public static boolean editExpirationDateForRule(String ruleName) throws Exception {
		Log.info("Method : editExpirationDateForRule");
		try {
			WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(ExcelReader.getValue("BRM", ruleName, "TestData")), driver);
		} catch (Exception e) {
			WrapperFunctionUtilities.refreshPageWithPageLoadTimeout(driver);
			WrapperFunctionUtilities.waitForElementVisibility(brmPage_FirstRuleGroup_WebElement, 30, driver);
			brmPage_FirstRuleGroup_WebElement.click();
		}
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_ExpirationDateRule_WebElement, 10, driver);
		brmPage_ExpirationDateRule_WebElement.click();
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_CurrentDayPlusTwoForEndDate_WebElement, 10, driver);
		WrapperFunctionUtilities.waitForTime(2000);
		WrapperFunctionUtilities.jsClick(brmPage_CurrentDayPlusTwoForEndDate_WebElement, driver);
		//brmPage_CurrentDayPlusTwoForEndDate_WebElement.click();
		WrapperFunctionUtilities.scrollByVisibleElement(rule_save, driver);
		rule_save.click();
		boolean flag = WrapperFunctionUtilities.isElementPresent(add_rule, "add_rule");
		return flag;
	}

	public static boolean selectFirstDropDownOptionFromSubjectArea(String ruleName) throws Exception {
		Log.info("Method : selectFirstDropDownOptionFromSubjectArea");
		try {
			WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(ExcelReader.getValue("BRM", ruleName, "TestData")), driver);
		} catch (Exception e) {
			WrapperFunctionUtilities.refreshPageWithPageLoadTimeout(driver);
			WrapperFunctionUtilities.waitForElementVisibility(brmPage_FirstRuleGroup_WebElement, 30, driver);
			brmPage_FirstRuleGroup_WebElement.click();
		}
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_SubjectArea_DropDown, 10, driver);
		WrapperFunctionUtilities.jsClick(brmPage_SubjectArea_DropDown, driver);
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_SubjectAreaFirstDropDown_Option, 10, driver);
		WrapperFunctionUtilities.jsClick(brmPage_SubjectAreaFirstDropDown_Option, driver);
		WrapperFunctionUtilities.scrollByVisibleElement(rule_save, driver);
		rule_save.click();
		boolean flag = WrapperFunctionUtilities.isElementPresent(add_rule, "add_rule");
		return flag;
	}

	public static void editExistingRule(String ruleName) {
		Log.info("Method : editExistingRule");
		try {
			WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(ExcelReader.getValue("BRM", ruleName, "TestData")), driver);
		} catch (Exception e) {
			WrapperFunctionUtilities.refreshPageWithPageLoadTimeout(driver);
			WrapperFunctionUtilities.waitForElementVisibility(brmPage_FirstRuleGroup_WebElement, 30, driver);
			brmPage_FirstRuleGroup_WebElement.click();
		}
	}

	public static void changeExistingRuleActionToTransform(String transformAttributeName, String transformValue) throws Exception {
		Log.info("Method : changeExistingRuleActionToTransform");
		WrapperFunctionUtilities.scrollByVisibleElement(rule_select_action, driver);
		WrapperFunctionUtilities.jsClick(rule_select_action, driver);
		WrapperFunctionUtilities.jsClick(brmPage_TransformAction_DropDownOption, driver);
		WrapperFunctionUtilities.jsClick(selectTransformAttribute, driver);
		CommonPageFunctions.hyperlinkClick(ExcelReader.getValue("BRM", transformAttributeName, "TestData"));
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_Expression1_WebElement, 10, driver);
		WrapperFunctionUtilities.scrollByVisibleElement(brmPage_Expression1_WebElement, driver);
		brmPage_Expression1_WebElement.click();
		BRMPage.value.click();
		BRMPage.value.sendKeys(ExcelReader.getValue("BRM", transformValue, "TestData"));
		selectValue.click();
	}

	public static boolean reorderRulesInRuleGroup(String ruleName1, String ruleName2) throws Exception {
		Log.info("Method : reorderRulesInRuleGroup");
		ruleName1 = ExcelReader.getValue("BRM", ruleName1, "TestData");
		ruleName2 = ExcelReader.getValue("BRM", ruleName2, "TestData");

		Actions action = new Actions(driver);
		action.dragAndDrop(brmPage_RuleNameGrip_WebElement(ruleName1), brmPage_RuleNameGrip_WebElement(ruleName2)).build().perform();
		boolean flag = WrapperFunctionUtilities.isElementPresent(brmPage_RuleOrderUpdatedSuccessfullyToaster_WebElement, "brmPage_RuleOrderUpdatedSuccessfullyToaster_WebElement");
		return flag;
	}

	public static void setNewAttributeName(String setNewAttributeName) throws Exception {
		Log.info("Method : setNewAttributeName");
		setNewAttributeName = ExcelReader.getValue("BRM", setNewAttributeName, "TestData");
		WrapperFunctionUtilities.scrollByVisibleElement(brmPage_NewAttributeName_TextBox, driver);
		brmPage_NewAttributeName_TextBox.sendKeys(setNewAttributeName);
		WrapperFunctionUtilities.scrollByVisibleElement(rule_save, driver);
		rule_save.click();
	}

	public static boolean verifySearchRulesFilterTextBoxFunctionality(String ruleName) throws Exception {
		Log.info("Method : verifySearchRulesFilterTextBoxFunctionality");
		ruleName = ExcelReader.getValue("BRM", ruleName, "TestData");
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_searchRule_TextBox,20,driver);
		WrapperFunctionUtilities.waitForTime(3000);
		//Actions action = new Actions(driver);
		//action.click(brmPage_searchRule_TextBox).sendKeys(ruleName).build().perform();
		brmPage_searchRule_TextBox.sendKeys(ruleName);
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_CloseIconSearchRuleTextBox_WebElement, 10, driver);

		logicalResultFlag = brmPage_ListOfRuleNames_WebList.size() == 1 ? true : false;
		Log.info("Value of Flag after Filter :"+logicalResultFlag );
		logicalResultFlag = brmPage_ListOfRuleNames_WebList.get(0).getText().equals(ruleName) ? logicalResultFlag : false;
		Log.info("Value of Flag after Filter :"+logicalResultFlag );
		brmPage_CloseIconSearchRuleTextBox_WebElement.click();
		return logicalResultFlag;
	}

	public static boolean verifySubjectAreaFilterFunctionality() throws Exception {
		Log.info("Method : verifySubjectAreaFilterFunctionality");

		WrapperFunctionUtilities.waitForElementVisibility(brmPage_SubjectArea_DropDown, 10, driver);
		//WrapperFunctionUtilities.jsClick(brmPage_SubjectArea_DropDown,driver);
		brmPage_SubjectArea_DropDown.click();
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_SubjectAreaFirstDropDown_Option, 10, driver);
		//WrapperFunctionUtilities.jsClick(brmPage_SubjectAreaFirstDropDown_Option,driver);
		brmPage_SubjectAreaFirstDropDown_Option.click();
		brmPage_searchRule_TextBox.click();
		WrapperFunctionUtilities.waitForTime(2000);
		//Verification Part
		logicalResultFlag = brmPage_ListOfRuleNames_WebList.size() == 1 ? true : false;
		Log.info("Value of Flag after Filter :"+logicalResultFlag );
		//logicalResultFlag = brmPage_ListOfRuleNames_WebList.get(0).getText().equals(ruleName) ? logicalResultFlag : false;
		//Log.info("Value of Flag after Filter :"+logicalResultFlag );

		WrapperFunctionUtilities.refreshPageWithPageLoadTimeout(driver);
		WrapperFunctionUtilities.waitForPageToLoad(60, driver);

		return logicalResultFlag;
	}

	public static boolean verifyStartDateFilterFunctionality(String ruleName) throws Exception {
		Log.info("Method : verifyStartDateFilterFunctionality");
		ruleName = ExcelReader.getValue("BRM", ruleName, "TestData");

		WrapperFunctionUtilities.waitForElementVisibility(brmPage_StartDateRule_WebElement, 10, driver);
		brmPage_StartDateRule_WebElement.click();
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_NextDayForCurrentStartDate_WebElement, 10, driver);
		WrapperFunctionUtilities.waitForTime(2000);

		WrapperFunctionUtilities.jsClick(brmPage_NextDayForCurrentStartDate_WebElement, driver);

		WrapperFunctionUtilities.waitForTime(2000);
		logicalResultFlag = brmPage_ListOfRuleNames_WebList.size() == 1 ? true : false;
		Log.info("Value of Flag after Filter :"+logicalResultFlag );
		logicalResultFlag = brmPage_ListOfRuleNames_WebList.get(0).getText().equals(ruleName) ? logicalResultFlag : false;
		Log.info("Value of Flag after Filter :"+logicalResultFlag );

		WrapperFunctionUtilities.refreshPageWithPageLoadTimeout(driver);
		WrapperFunctionUtilities.waitForPageToLoad(60, driver);

		return logicalResultFlag;
	}

	public static boolean verifyExpirationDateFilterFunctionality(String ruleName) throws Exception {
		Log.info("Method : verifyExpirationDateFilterFunctionality");
		ruleName = ExcelReader.getValue("BRM", ruleName, "TestData");

		WrapperFunctionUtilities.waitForElementVisibility(brmPage_ExpirationDateRule_WebElement, 10, driver);
		brmPage_ExpirationDateRule_WebElement.click();
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_CurrentDayPlusOneForEndDate_WebElement, 10, driver);
		WrapperFunctionUtilities.waitForTime(2000);
		WrapperFunctionUtilities.jsClick(brmPage_CurrentDayPlusOneForEndDate_WebElement, driver);
		WrapperFunctionUtilities.waitForTime(2000);
		logicalResultFlag = brmPage_ListOfRuleNames_WebList.size() == 1 ? true : false;
		Log.info("Value of Flag after Filter :"+logicalResultFlag );
		logicalResultFlag = brmPage_ListOfRuleNames_WebList.get(0).getText().equals(ruleName) ? logicalResultFlag : false;
		Log.info("Value of Flag after Filter :"+logicalResultFlag );
		return logicalResultFlag;
	}

	public static String selectAndStoreFirstDropDownOptionFromSubjectArea(String ruleName) throws Exception {
		Log.info("Method : selectAndStoreFirstDropDownOptionFromSubjectArea");
		try {
			WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(ExcelReader.getValue("BRM", ruleName, "TestData")), driver);
		} catch (Exception e) {
			WrapperFunctionUtilities.refreshPageWithPageLoadTimeout(driver);
				WrapperFunctionUtilities.waitForElementVisibility(brmPage_FirstRuleGroup_WebElement, 30, driver);
				brmPage_FirstRuleGroup_WebElement.click();
		}
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_SubjectArea_DropDown, 10, driver);
		WrapperFunctionUtilities.jsClick(brmPage_SubjectArea_DropDown, driver);
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_SubjectAreaFirstDropDown_Option, 10, driver);
		String subjectAreaName = brmPage_SubjectAreaFirstDropDown_Option.getText();
		Log.info("Subject Area Name is " + subjectAreaName);
		WrapperFunctionUtilities.jsClick(brmPage_SubjectAreaFirstDropDown_Option, driver);

		WrapperFunctionUtilities.scrollByVisibleElement(rule_save, driver);
		rule_save.click();
		WrapperFunctionUtilities.isElementPresent(add_rule, "add_rule");
		return subjectAreaName;
	}

	public static void activateOrDeactivateRule(String ruleName) throws Exception {
		Log.info("Method : activateOrDeactivateRule");
		ruleName = ExcelReader.getValue("BRM", ruleName, "TestData");
		try {
			brmPage_RuleToggleButton_WebElement(ruleName).click();
		} catch (Exception e) {
			Log.info("In Catch block");
			WrapperFunctionUtilities.refreshPageWithPageLoadTimeout(driver);
			WrapperFunctionUtilities.waitForPageToLoad(60, driver);
			WrapperFunctionUtilities.jsClick(brmPage_RuleToggleButton_WebElement(ruleName), driver);
		}
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_DeactiavtedRuleToggleButton_WebElement(ruleName),10,driver);
	}

	public static boolean runTestRule() throws Exception {
		Log.info("Method : runTestRule");
		WrapperFunctionUtilities.scrollByVisibleElement(testRuleButton, driver);
		testRuleButton.click();
		WrapperFunctionUtilities.waitForElementToBeInvisibileBy(CommonPage.commonPage_LoadingSymbol_Locator, 60, driver);
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_TestRunSuccessful_Text, 10, driver);
		logicalResultFlag = brmPage_ErrorIcon_Text.size()>0?false:true;
		if(!logicalResultFlag)
		{
			testRuleButton.click();
			WrapperFunctionUtilities.waitForElementToBeInvisibileBy(CommonPage.commonPage_LoadingSymbol_Locator, 60, driver);
			logicalResultFlag =  WrapperFunctionUtilities.isElementPresent(brmPage_Search_WebButton, "brmPage_Search_WebButton");
		}else{
			return logicalResultFlag;
		}
		return logicalResultFlag;
	}

	public static boolean verifyTestRulesAreUpdatedWithChanges(String updatedValue) throws Exception {
		Log.info("Method : verifyTestRulesAreUpdatedWithChanges");
		int totalrows = brmPage_TestRuleResultsRow_WebList.size();
		brmPage_SearchTestRuleResults_TextBox.sendKeys("Pass");
		brmPage_Search_WebButton.click();
		WrapperFunctionUtilities.waitForTime(2000);
		int totalPassResults = brmPage_TestRuleResultsRow_WebList.size();
		logicalResultFlag = totalPassResults < totalrows ? true : false;
		Log.info("Flag before Updating Value" + logicalResultFlag);
		// Updating the values
		WrapperFunctionUtilities.waitForElementVisibility(selectValue, 1000, driver);
		selectValue.click();
		inputValue.clear();
		inputValue.sendKeys(ExcelReader.getValue("BRM", updatedValue, "TestData"));
		selectAttribute.click();

		runTestRule();

		totalrows = brmPage_TestRuleResultsRow_WebList.size();
		brmPage_SearchTestRuleResults_TextBox.sendKeys("Pass");
		brmPage_Search_WebButton.click();
		WrapperFunctionUtilities.waitForTime(2000);
		int totalPassResultsNew = brmPage_TestRuleResultsRow_WebList.size();
		logicalResultFlag = totalPassResults < totalrows ? logicalResultFlag : false;
		Log.info("Flag After Updating Value for TotalPassResults Check" + logicalResultFlag);
		logicalResultFlag = totalPassResults != totalPassResultsNew ? logicalResultFlag : false;
		Log.info("Flag After Updating Value for TotalPassResults Not same as Previous One" + logicalResultFlag);
		return logicalResultFlag;
	}

	public static boolean verifyTransformationColumnNameInTestRuleResult(String setNewAttributeName) throws Exception {
		Log.info("Method : verifyTransformationColumnNameInTestRuleResult");
		setNewAttributeName = ExcelReader.getValue("BRM", setNewAttributeName, "TestData");
		for (WebElement columnHeader : brmPage_ColumnHeadersTestRuleResults_WebList) {
			Log.info("Column Name Scrolled into view  "+columnHeader.getText());
			WrapperFunctionUtilities.scrollByVisibleElement(columnHeader, driver);
				if( columnHeader.getText().equalsIgnoreCase(setNewAttributeName))
				{
					return true;
				}
		}

		for (WebElement columnHeader : brmPage_ColumnHeadersTestRuleResults_WebList) {
			Log.info("Column Name Scrolled into view  "+columnHeader.getText());
			WrapperFunctionUtilities.scrollByVisibleElement(columnHeader, driver);
			if( columnHeader.getText().equalsIgnoreCase(setNewAttributeName))
			{
				return true;
			}
		}

		WrapperFunctionUtilities.scrollByVisibleElement(brmPage_LastColumnHeader_WebElement, driver);
		Log.info("Last Column Name  " + brmPage_LastColumnHeader_WebElement.getText());
		logicalResultFlag = brmPage_LastColumnHeader_WebElement.getText().equalsIgnoreCase(setNewAttributeName) ? true : false;

		return logicalResultFlag;
	}

	public static void setNewAttributeNameWithoutSave(String setNewAttributeName) throws Exception {
		Log.info("Method : setNewAttributeNameWithoutSave");
		setNewAttributeName = ExcelReader.getValue("BRM", setNewAttributeName, "TestData");
		WrapperFunctionUtilities.scrollByVisibleElement(brmPage_NewAttributeName_TextBox, driver);
		brmPage_NewAttributeName_TextBox.sendKeys(setNewAttributeName);
		WrapperFunctionUtilities.scrollByVisibleElement(rule_save, driver);
	}
	public static void deleteConditionFromRule(String expressionName) throws Exception {
		Log.info("Method : deleteConditionFromRule");
		WrapperFunctionUtilities.scrollByVisibleElement(brmPage_ExpressionDeleteButton_WebElement(expressionName), driver);
		brmPage_ExpressionDeleteButton_WebElement(expressionName).click();
	}

	public static void addChildCondition( String attribute, String value) throws Exception {
		Log.info("Method : addChildCondition");
		WrapperFunctionUtilities.scrollByVisibleElement(brmPage_RuleConditionMenu_WebElement, driver);
		WrapperFunctionUtilities.MouseOver(brmPage_RuleConditionMenu_WebElement,driver);
		WrapperFunctionUtilities.jsClick(brmPage_AddChildConditionBelow_WebElement, driver);

		brmPage_SelectCondition_DropDown.click();
		brmPage_AND_DropDownOption.click();

		WrapperFunctionUtilities.waitForElementVisibility(selectAttributeExp2,30,driver);
		//To select attribute
		selectAttributeExp2.click();
		WrapperFunctionUtilities.waitForElementVisibility(selectAttributeExp2, 1000, driver);
		selectAttributeExp2.sendKeys(ExcelReader.getValue("BRM", attribute, "TestData"));

		brmPage_selectOperator2_WebElement.click();
		WrapperFunctionUtilities.jsClick(equalOperatorExp2, driver);

		//to select value
		WrapperFunctionUtilities.waitForElementVisibility(selectValueExp2, 1000, driver);
		selectValueExp2.click();
		Actions actions = new Actions(driver);
		actions.sendKeys(ExcelReader.getValue("BRM", value, "TestData")).build().perform();
	//	selectValueExp2.sendKeys(ExcelReader.getValue("BRM", value, "TestData"));
		selectAttributeExp2.click();

	}

	public static void expandExpression1() throws Exception {
		Log.info("Method : expandExpression1");
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_Expression1_WebElement, 10, driver);
		WrapperFunctionUtilities.scrollByVisibleElement(brmPage_Expression1_WebElement, driver);
		brmPage_Expression1_WebElement.click();
	}

	public static boolean storeExistenceOfAddRuleButton() throws Exception {
		Log.info("Method : storeExistenceOfAddRuleButton");
		logicalResultFlag= WrapperFunctionUtilities.isElementPresent(add_rule, "add_rule");
		return logicalResultFlag;
	}

	public static boolean editStartDateAndExpirationDateForRule(String ruleName) throws Exception {
		Log.info("Method : editStartDateAndExpirationDateForRule");
		try {
			WrapperFunctionUtilities.jsClick(CommonPage.hyperlink(ExcelReader.getValue("BRM", ruleName, "TestData")), driver);
		} catch (Exception e) {
			WrapperFunctionUtilities.refreshPageWithPageLoadTimeout(driver);
			WrapperFunctionUtilities.waitForElementVisibility(brmPage_FirstRuleGroup_WebElement, 30, driver);
			brmPage_FirstRuleGroup_WebElement.click();
		}
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_ExpirationDateRule_WebElement, 10, driver);
		brmPage_ExpirationDateRule_WebElement.click();
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_CurrentDayPlusTwoForEndDate_WebElement, 10, driver);
		WrapperFunctionUtilities.waitForTime(2000);
		WrapperFunctionUtilities.jsClick(brmPage_CurrentDayPlusTwoForEndDate_WebElement, driver);

		WrapperFunctionUtilities.waitForElementVisibility(brmPage_StartDateRule_WebElement, 10, driver);
		brmPage_StartDateRule_WebElement.click();
		WrapperFunctionUtilities.waitForElementVisibility(brmPage_NextDayForCurrentStartDate_WebElement, 10, driver);
		WrapperFunctionUtilities.waitForTime(2000);
		WrapperFunctionUtilities.jsClick(brmPage_NextDayForCurrentStartDate_WebElement, driver);
		WrapperFunctionUtilities.scrollByVisibleElement(rule_save, driver);

		//brmPage_CurrentDayPlusTwoForEndDate_WebElement.click();
		WrapperFunctionUtilities.scrollByVisibleElement(rule_save, driver);
		rule_save.click();
		boolean flag = WrapperFunctionUtilities.isElementPresent(add_rule, "add_rule");
		return flag;
	}
}



