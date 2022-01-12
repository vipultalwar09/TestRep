package com.RDM.Merger.locators;

import java.util.List;

import com.utility.base.Xls_Reader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import com.RDM.Merger.testcases.Aa_Login.ExcelReader;
import com.utility.base.TestBase;
import com.utility.base.WrapperFunctionUtilities;
import com.utility.logger.Log;

public class BRMPage extends TestBase {
	/*
	 * This class provides locators for BRM Page
	 */

	public static WebElement deletelink;
	public static WebElement duplicateRuleIcon;
	public static WebElement groupLink;
	public static WebElement deleteRulelink;


	@FindBy(xpath ="//button[text()='OK']")
	protected static WebElement environment_click;

	@FindBy(xpath="//button[contains(.,'Add Group')]")
	public static WebElement add_rule_group;

	@FindBy(xpath="//input[@placeholder='Name']")
	protected static WebElement rule_group_name;

	@FindBy(xpath = "//button[contains(.,'Save')]")
	protected static WebElement rule_group_save;

	@FindBy(xpath = "//button[contains(.,'Ok')][1]")
	protected static WebElement rule_group_ok;

	@FindBy(xpath="//span/button[text()='Add Rule']")
	protected static WebElement add_rule;

	@FindBy(xpath = "//h1[@class='display-block-inline rule-name-heading']")
	public static WebElement ruleGroupTitle;

	@FindBy(xpath = "//button[contains(.,'Tables')]")
	public static WebElement tables;
	
	@FindBy(xpath="//input[@placeholder='New Rule Name']")
	protected static WebElement newduplicateRuleName;
	
	@FindBy(xpath="//input[@placeholder='Name']")
	protected static WebElement rule_name;

	@FindBy(xpath="(//app-modaldialog//button[contains(text(),'Cancel')])[last()]")
	protected static WebElement brmPage_CancelPublishNewVersion_Button;

	@FindBy(xpath="(//a[contains(@class,'expression-icon')])[2]")
	public static WebElement expression_icon2;
	
	@FindBy(xpath="(//input[@type='radio'])[8]")
	public static WebElement excludeRadioButton;
	
	@FindBy(xpath="//button[contains(.,'Test Rule')]")
	public static WebElement testRuleButton;
	
	@FindBy(xpath="//span[contains(.,'Add Else Action')]")
	public static WebElement addElseAction;

	@FindBy(xpath="(//input[@placeholder='Value'])[3]")
	public static WebElement elseValue;

	@FindBy(xpath="(//input[@placeholder='Value'])[2]")
	public static WebElement valueExp2;
	
	@FindBy(xpath="(//input[@placeholder='Select Value'])[3]")
	protected static WebElement selectValueExp2;
	
	@FindBy(xpath="(//span[contains(.,'Equals')])[2]")
	public static WebElement equalOperatorExp2;
	
	@FindBy(xpath="(//input[@placeholder='Select Attribute'])[2]")
	protected static WebElement selectAttributeExp2;

	@FindBy(xpath="//span[contains(.,'Add New Expression')]")
	public static WebElement addNewExpression;
	
	@FindBy(xpath="//input[@placeholder='Value']")
	public static WebElement value;
	
	@FindBy(xpath="//label[contains(text(),' Attribute ')]//following::div[contains(text(),'Select Attribute')]")
	public static WebElement selectTransformAttribute;
	
	@FindBy(xpath="//label[@class='select-label'][contains(text(),'Attribute')]")
	public static WebElement transformAttributes;
	
	@FindBy(xpath="//label[@class='text-field-label'][contains(text(),'New Attribute Name ')]")
	public static WebElement newAttributeName;
	
	@FindBy(xpath="//label[text()='Attribute']/following::span[@class='zs-input-icon zs-icon-carat-down zs-interactive-secondary auto-suggest-span'][1]")
	protected static WebElement selectAttribute;
	
	@FindBy(xpath="(//label[text()='Attribute']/following::span[@class='zs-input-icon zs-icon-carat-down zs-interactive-secondary auto-suggest-span']/input)[1]")
	protected static WebElement inputAttribute;
	
	@FindBy(xpath="//div[@class='rule-condition']//p[contains(@class,'zs-margin-0 zs-action-field zs-select')]")
	protected static WebElement selectOperator;
	
	@FindBy(xpath="//div[@class='rule-condition']//label/input")
	protected static WebElement inputOperator;
	
	@FindBy(xpath="(//label[text()='Value']/following::span)[1]")
	protected static WebElement selectValue;
	
	@FindBy(xpath="//label[contains(text(),' Action ')]//following::div[contains(text(),'Include/Exclude')]")
	public static WebElement ruleActionDropdown;
	
	@FindBy(xpath="//div[text()='Include/Exclude']")
	protected static WebElement rule_select_action;
	
	@FindBy(xpath="//button[contains(.,'Save')]")
	protected static WebElement rule_save;
	
	@FindBy(xpath="(//div/span/app-rules-table-cell-renderer/span/a)[5]")
	public static WebElement duplicateRuleNameTitle;

	@FindBy(xpath="(//label[text()='Value']/following::span/input)[1]")
	protected static WebElement inputValue;

	@FindBy(xpath="//table[@class='zs-data-table home-process-table']/tbody/tr[2]/td[5]")
	public static WebElement ruleDuplicateAuditLogValue;

	@FindBy(xpath="//label/input[@placeholder='Search rules']")
	public static WebElement searchRuleGroupbox;

	@FindBy(xpath="//label/input[@placeholder='Search Rules']")
	public static WebElement brmPage_searchRule_TextBox;

	@FindBy(xpath="(//span/app-rules-table-cell-renderer/span/a)[1]")
	public static WebElement ruleNameTitle;
	
	@FindBy(xpath="(//a[contains(@class,'expression-icon')])[1]")
	protected static WebElement expression_icon;
	
	@FindBy(xpath="//footer/div/button[@class='zs-button zs-button-solid zs-border-rounded-0']")
	protected static WebElement expression_OK;
	
	@FindBy(xpath="//textarea[@placeholder='Formula']")
	protected static WebElement expression_formula_textarea;
	
	@FindBy(xpath="//div[@class='zs-radio-group rule-condition']/div[@class='lookup-div'][2]/input")
	protected static WebElement lookupRadio;

	@FindBy(xpath="//label[text()=' Lookup Table ']/following::div[1]")
	protected static WebElement lookuptable;
	
	@FindBy(xpath="//label[text()=' Lookup Column ']/following::div[1]")
	public static WebElement lookupcolumn;

	@FindBy(xpath="//label[text()=' Lookup Column ']/following::div[2]/label/input")
	public static WebElement lookupcolumnSearch;
	
	@FindBy(xpath="//table[@class='zs-data-table home-process-table']/tbody/tr[3]/td[5]")
	public static WebElement ruleGroupAddAuditLogValue;
	
	@FindBy(xpath="//table[@class='zs-data-table home-process-table']/tbody/tr[2]/td[5]")
	public static WebElement ruleAddAuditLogValue;
	
	@FindBy(xpath="//table[@class='zs-data-table home-process-table']/tbody/tr[2]/td[5]")
	public static WebElement ruleDeleteAuditLogValue;
	
	@FindBy(xpath="//h1[text()='Business Rule Management']")
	public static WebElement brmHeader;

	@FindBy(xpath = "//button[text()='Search']")
	public static WebElement groupSearchButton;

	@FindBy(xpath = "//p[@class='rule-group-count']/span[1]")
	public static WebElement ruleGroupCountElement;
	
	@FindBy(xpath = "//button[text()='Delete']")
	public static WebElement deleteConfirmationButton;
	
	@FindBy(xpath = "(//a[contains(@class,'zs-icon-delete')])[1]")
	public static WebElement deleteFirstBR;

	@FindBy(xpath = "(//app-zs-card//a)[1]")
	public static WebElement brmPage_FirstRuleGroupCard_WebElement;

	@FindBy(xpath = "//div[contains(@class,'zs-accordion-bar')]//a[contains(@class,'zs-icon-edit')]")
	public static WebElement brmPage_EditExpressionNameIcon_WebElement;

	@FindBy(xpath = "//app-modaldialog//input")
	public static WebElement brmPage_ExpressionName_TextBox;

	@FindBy(xpath = "(//app-modaldialog//button[text()='Ok'])[1]")
	public static WebElement brmPage_OkButton_WebButton;

	@FindBy(xpath = "//p[text()='The number of characters exceed its specified limit.']")
	public static WebElement brmPage_ErrorMessageExpressionNameWordLimit_WebElement;

	@FindBy(xpath = "//label[normalize-space(text())='Entity']//following-sibling::p/div[1]")
	public static WebElement brmPage_EntityDropDown_WebElement;

	@FindBy(xpath="//div[@class='rule-condition']//p//div[text()='Select Operator']")
	public static WebElement brmPage_selectOperator2_WebElement;

	@FindBy(xpath = "//div[@class='rule-details-accordion']//a[text()='Expression 1']")
	public static WebElement brmPage_Expression1_WebElement;

	@FindBy(xpath = "//div[@class='zs-accordion']//a[text()='Expression 1']//following::a[contains(@class,'zs-icon-delete')][1]")
	public static WebElement brmPage_DeleteIconExpression1_WebElement;

	@FindBy(xpath = "(//span/app-rules-table-cell-renderer/span//a//parent::span)[1]")
	public static WebElement brmPage_FirstRuleGroup_WebElement;

	@FindBy(xpath = "(//span/app-rules-table-cell-renderer/span//a//parent::span)[1]")
	public static WebElement brmPage_SecondRuleGroup_WebElement;

	@FindBy(xpath = "//label[normalize-space(text())='Action']//following::span[contains(text(),'Transform')]")
	public static WebElement brmPage_TransformAction_DropDownOption;

	@FindBy(xpath = "//label[normalize-space(text())='Action']//following::span[contains(text(),'Include/Exclude')]")
	public static WebElement brmPage_IncludeExcludeAction_DropDownOption;

	@FindBy(xpath = "//label[normalize-space(text())='Lookup Column']/ancestor::app-multi-select-dropdown")
	public static WebElement brmPage_LookupColumn_DropDown;

	@FindBy(xpath = "//label[normalize-space(text())='Lookup Table']/following::div[2]/label/input")
	public static WebElement brmPage_LookupTableSearch_TextBox;

	@FindBy(xpath = "//label[normalize-space(text())='Lookup Column']//parent::div//following-sibling::p[text()='Value required.']")
	public static WebElement brmPage_LookupColumnDD_ErrorMessage;

	@FindBy(xpath = "//label[normalize-space(text())='Action']//following::p[1]//select[not(@multiple)][1]")
	public static WebElement brmPage_Action_DropDown;

	@FindBy(xpath = "//button[text()='Publish New Version']")
	public static WebElement brmPage_PublishNewVersion_Button;

	@FindBy(xpath = "//input[@placeholder='Version Name']")
	public static WebElement brmPage_VersionName_TextBox;

	@FindBy(xpath = "//app-modaldialog[@ng-reflect-showmodal='true']//button[contains(.,'Ok')]")
	public static WebElement brmPage_OkButtonDialogBox_Button;

	@FindBy(xpath = "//input[@placeholder='Description']")
	public static WebElement brmPage_RuleDescription_TextBox;

	@FindBy(xpath = "//h2[normalize-space(text())='Rule Summary']//following-sibling::div")
	public static WebElement brmPage_RuleSummary_Text;

	@FindBy(xpath = "//h4[normalize-space(text())='Else']//following::div[@class='rule-parameter-action']//input[@placeholder='Value']")
	public  static WebElement brmPage_ElseValue_TextBox;

	@FindBy(xpath = "//div[@class='rule-details-accordion']//a[text()='Expression 2']")
	public static WebElement brmPage_Expression2_WebElement;

	@FindBy(xpath = "//div[@class='rule-details-accordion']//a[text()='Else']")
	public static WebElement brmPage_ElseExpression_WebElement;

	@FindBy(xpath = "//label[normalize-space(text())='Entity']//following-sibling::p//select[2]")
	public static WebElement brmPage_EntityRuleGroup_DropDown;

	@FindBy(xpath = "//textarea[@placeholder='Description']")
	public static WebElement brmPage_RuleGroupDescription_TextBox;

	@FindBy(xpath = "//app-zs-card")
	public static List<WebElement> brmPage_RuleGroupCards_WebElement;

	@FindBy(xpath = "//p[@class='rule-group-count']//span[normalize-space(text())='1']//following-sibling::span[text()='Rule Groups']")
	public static WebElement brmPage_OneRuleGroups_Text;

	@FindBy(xpath = "//p[@class='rule-group-count']//span[1]")
	public static WebElement brmPage_NumberOfRuleGroups_Text;

	@FindBy(xpath = "//input[@placeholder='Search rules']//following-sibling::a[contains(@class,'zs-icon-close-circle-fill')]")
	public static WebElement brmPage_CloseIconSearchRulesTextBox_WebElement;

	@FindBy(xpath = "//input[@placeholder='Search Rules']//following-sibling::a[contains(@class,'zs-icon-close-circle-fill')]")
	public static WebElement brmPage_CloseIconSearchRuleTextBox_WebElement;

	@FindBy(xpath = "//app-zs-card//span[contains(text(),'Entity')]//ancestor::div[1]")
	public static List<WebElement> brmPage_EntityNameRuleGroupCard_List;

	@FindBy(xpath = "//app-zs-card//div[@class='description']")
	public static List<WebElement> brmPage_DescriptionRuleGroupCard_List;

	@FindBy(xpath = "//span[text()='Tables']")
	public static WebElement brmPage_TablesTab_WebElement;

	@FindBy(xpath = "//div[@role='row']//a[@class='name-color']")
	public static List<WebElement> brmPage_RuleGroupNames_List;

	@FindBy(xpath = "//div[@role='row']//a[contains(@class,'zs-icon-edit')]")
	public static List<WebElement> brmPage_RuleGroupEditIcon_List;

	@FindBy(xpath = "//div[@role='row']//a[contains(@class,'zs-icon-delete')]")
	public static List<WebElement> brmPage_RuleGroupDeleteIcon_List;

	@FindBy(xpath = "//label[normalize-space(text())='Start Date']//following-sibling::p[@is='zs-data-picker']")
	public static WebElement brmPage_StartDateRule_WebElement;

	@FindBy(xpath = "//label[normalize-space(text())='Expiration Date']//following-sibling::p[@is='zs-data-picker']")
	public static WebElement brmPage_ExpirationDateRule_WebElement;

	@FindBy(xpath = "(//td[@today][1]/following-sibling::td[1])[1]")
	public static WebElement brmPage_NextDayForCurrentStartDate_WebElement;

	@FindBy(xpath = "(//td[@today][1]/following-sibling::td[3])[2]")
	public static WebElement brmPage_CurrentDayPlusTwoForEndDate_WebElement;

	@FindBy(xpath = "(//td[@today][1]/following-sibling::td[2])[2]")
	public static WebElement brmPage_CurrentDayPlusOneForEndDate_WebElement;

	@FindBy(xpath = "//label[normalize-space(text())='Subject Area']//following-sibling::p/div[1]")
	public static WebElement brmPage_SubjectArea_DropDown;

	@FindBy(xpath = "(//label[normalize-space(text())='Subject Area']//following-sibling::p//span[@zs-dropdown-option])[1]")
	public static WebElement brmPage_SubjectAreaFirstDropDown_Option;

	@FindBy(xpath = "//span[text()='Rule order updated successfully.']")
	public static WebElement brmPage_RuleOrderUpdatedSuccessfullyToaster_WebElement;

	@FindBy(xpath = "//input[@placeholder='New Attribute Name']")
	public static WebElement brmPage_NewAttributeName_TextBox;

	@FindBy(xpath = "//div[@aria-colindex='2' and @role='gridcell']//a")
	public static List<WebElement> brmPage_ListOfRuleNames_WebList;

	@FindBy(xpath = "//span[contains(@class,'zs-icon-check-circle-fill')]//following-sibling::span[contains(text(),'Successful')]")
	public static WebElement brmPage_TestRunSuccessful_Text;

	@FindBy(xpath = "//span[contains(@class,'zs-icon-error-triangle-fill')]//following-sibling::span[contains(text(),'Error')]")
	public static List<WebElement> brmPage_ErrorIcon_Text;

	@FindBy(xpath = "//button[text()='Search']")
	public static WebElement brmPage_Search_WebButton;

	@FindBy(xpath = "//button[text()='Search']//preceding-sibling::app-zs-textbox//input")
	public static WebElement brmPage_SearchTestRuleResults_TextBox;

	@FindBy(xpath = "//ag-grid-angular//div[@role='rowgroup']//div[@role='row']")
	public static List<WebElement> brmPage_TestRuleResultsRow_WebList;

	@FindBy(xpath = "//ag-grid-angular//div[@role='columnheader']//div[@role='presentation']//span[@ref='eText']")
	public static List<WebElement> brmPage_ColumnHeadersTestRuleResults_WebList;

	@FindBy(xpath = "(//ag-grid-angular//div[@role='columnheader']//div[@role='presentation']//span[@ref='eText'])[last()]")
	public static WebElement brmPage_LastColumnHeader_WebElement;

	@FindBy(xpath = "//a[@class='menuLabelClass']")
	public static WebElement brmPage_RuleConditionMenu_WebElement;

	@FindBy(xpath = "//span[text()='Add child condition below']")
	public static WebElement brmPage_AddChildConditionBelow_WebElement;

	@FindBy(xpath = "//label[normalize-space(text())='Condition']//following-sibling::p/div[1]")
	public static WebElement brmPage_SelectCondition_DropDown;

	@FindBy(xpath = "//a[@title='AND']")
	public static WebElement brmPage_AND_DropDownOption;

	public static By brmPage_AddElseAction_Locator = By.xpath("//span[contains(.,'Add Else Action')]");

	public static WebElement getDeleteIconElement(String groupName)

	{
		deletelink = driver.findElement(By.xpath("//a[normalize-space(text())='"+groupName+"']/following::a[contains(@class,'zs-icon-delete')][1]"));
		WrapperFunctionUtilities.isElementPresent(deletelink, groupName);
		return deletelink;

	}

	public static WebElement getDuplicateRuleIcon(String ruleName) {
		return driver.findElement(By.xpath("//span/a[text()='"+ruleName+"']/following::a[contains(@class,'zs-icon-file-copy')][1]"));
	}

	public static WebElement getRuleGroupElement(String groupName) throws NoSuchElementException
	{
		//groupLink = driver.findElement(By.xpath("//span[@title='"+groupName+"']"));
		groupLink = driver.findElement(By.xpath("//a[normalize-space(text())='"+groupName+"']"));
		WrapperFunctionUtilities.isElementPresent(groupLink, groupName);
		return groupLink;
		
	}
	public static WebElement getDeleteRuleElement(String ruleName)
	{
		deleteRulelink =  driver.findElement(By.xpath("(//a[text()='"+ruleName+"']/following::a)[3]"));
		return deleteRulelink;
	}
	public BRMPage() {

		PageFactory.initElements(driver, this);
		System.out.println("in BRM Page");
	}

	public static WebElement expressionName(String expressionName)
	{
		return driver.findElement(By.xpath("//div[@class=\"rule-details-accordion\"]//a[text()='"+expressionName+"']"));
	}

	public static WebElement brmPage_RuleSummaryText_WebElement(String attribute, String value)
	{
		return driver.findElement(By.xpath("//h2[text()='Rule Summary']//following-sibling::div[@class='summary-div']//span[contains(text(),'"+attribute+"')]//following-sibling::span[text()='"+value+"']"));
	}

	public static By brmPage_ruleSummaryText_Locator(String attribute, String value)
	{
		return By.xpath("//h2[text()='Rule Summary']//following-sibling::div[@class='summary-div']//span[contains(text(),'"+attribute+"')]//following-sibling::span[text()='"+value+"']");
	}

	public static WebElement brmPage_RuleSummary_Operator_Expression1_WebElement(String entityName, String attributeName)
	{
		return driver.findElement(By.xpath("//a[text()='Expression 1']//parent::div//following-sibling::app-rule-summary//div[contains(text(),'If')]//span[contains(text(),'."+attributeName+"')]//span[text()='"+entityName+"']//ancestor::div[1]"));
	}

	public static WebElement brmPage_RuleSummary_Action_Expression1_WebElement(String value)
	{
		return driver.findElement(By.xpath("//a[text()='Expression 1']//parent::div//following-sibling::app-rule-summary//span[@class='value' and contains(text(),'"+value+"')]//ancestor::div[1]"));
	}

	public static WebElement brmPage_RuleAction_RadioButtons(String radioButtonText)
	{
		return driver.findElement(By.xpath("//label[normalize-space(text())='"+radioButtonText+"']//input"));
	}

	public static List<WebElement> brmPage_LookupColumn_DropDownOptions()
	{
		return driver.findElements(By.xpath("//label[normalize-space(text())='Lookup Column']//following::p[1]//nav//a"));
	}

	public static By brmPage_UnpublishedChangesOnRuleTile_Locator(String ruleGroupName)
	{
		return By.xpath("//a[@data-title='"+ruleGroupName+"']//following::section[1]//span[@class='zs-badge-counter' and contains(text(),'Unpublished changes')]");
	}

	public static By brmPage_VersionNameErrorMessage_Locator(String errorMessage)
	{
		return By.xpath("//p[text()='"+errorMessage+"']");
	}

	public static WebElement brmPage_LastOperator_Option(String operator)
	{
		return driver.findElement(By.xpath("(//span[contains(text(),'"+operator+"')])[last()]"));
	}

	public static WebElement brmPage_OperatorDropDownInExpressionNumber(int index)
	{
		return driver.findElement(By.xpath("(//div[@class='rule-definition-section'])["+index+"]//label[normalize-space(text())='Operator']//following::p//div[@overlay]"));
	}

	public static List<WebElement> brmPage_LookupTable_DropDownOptions()
	{
		return driver.findElements(By.xpath("//label[normalize-space(text())='Lookup Table']//following::p[1]//nav//a"));
	}

	public static List<WebElement> brmPage_EntityRuleGroup_DropDown()
	{
		return driver.findElements(By.xpath("//label[normalize-space(text())='Entity']//following::p[1]//nav//a"));
	}

	public static WebElement brmPage_EntityNameRuleGroupCard_Text(String ruleGroupName, String entityName)
	{
		return driver.findElement(By.xpath("//a[normalize-space(text())='"+ruleGroupName+"']//ancestor::app-zs-card//div[contains(text(),'"+entityName+"')]"));
	}

	public static WebElement brmPage_DescriptionRuleGroupCard_Text(String ruleGroupName, String description)
	{
		return driver.findElement(By.xpath("//a[normalize-space(text())='"+ruleGroupName+"']//ancestor::app-zs-card//div[@class='description' and contains(@data-title, '"+description+"')]"));
	}

	public static WebElement brmPage_LastEditRuleGroupCard_Text(String ruleGroupName, String lastEditTime)
	{
		return driver.findElement(By.xpath("//a[normalize-space(text())='"+ruleGroupName+"']//ancestor::app-zs-card//span[contains(text(), '"+lastEditTime+"')]"));
	}

	public static WebElement brmPage_RuleCountRuleGroupCard_Text(String ruleGroupName, String ruleCount)
	{
		return driver.findElement(By.xpath("//a[normalize-space(text())='"+ruleGroupName+"']//ancestor::app-zs-card//div[contains(text(), '"+ruleCount+"')]//span[text()='Rules']"));
	}

	public static WebElement brmPage_ColumnHeaders_WebElement(String columnName)
	{
		return driver.findElement(By.xpath("//div[@role='presentation']//span[@ref='eText' and text()='"+columnName+"']"));
	}

	public static WebElement brmPage_RuleGroupVersionPublishedMessage_WebElement(String versionName)
	{
		return driver.findElement(By.xpath("//span[text()=\"Rule group version '"+versionName+"' published successfully\"]"));
	}

	public static WebElement brmPage_LastEditedByUserInfo_WebElement(String ruleGroupName, String userName)
	{
		return driver.findElement(By.xpath("//a[normalize-space(text())='"+ruleGroupName+"']//ancestor::zs-card//span[@class='brg-edited-info']//span[contains(text(),'"+userName+"')]"));
	}

	public static WebElement brmPage_RuleLastEditedBy_WebElement(String ruleName, String userName)
	{
		return driver.findElement(By.xpath("//a[text()='"+ruleName+"']//ancestor::div[@role='row']//div[@aria-colindex='7']//span[contains(text(),'"+userName+"')]"));
	}

	public static WebElement brmPage_RuleCreatedBy_WebElement(String ruleName, String userName)
	{
		return driver.findElement(By.xpath("//a[text()='"+ruleName+"']//ancestor::div[@role='row']//div[@aria-colindex='6']//span[contains(text(),'"+userName+"')]"));
	}

	public static WebElement brmPage_RuleNameGrip_WebElement(String ruleName)
	{
		return driver.findElement(By.xpath("//a[text()='"+ruleName+"']//preceding::span[contains(@class,'ag-icon-grip')][1]"));
	}

	public static WebElement brmPage_RuleToggleButton_WebElement(String ruleName)
	{
		return driver.findElement(By.xpath("//a[text()='"+ruleName+"']//ancestor::div[@role='row']//div[@class='zs-toggle-button']"));
	}

	public static WebElement brmPage_DeactiavtedRuleToggleButton_WebElement(String ruleName)
	{
		return driver.findElement(By.xpath("//a[text()='"+ruleName+"']//ancestor::div[@role='row']//app-zs-toggle//p[@is='zs-toggle' and not(@active)]"));
	}

	public static WebElement brmPage_ExpressionDeleteButton_WebElement(String expressionName)
	{
		return driver.findElement(By.xpath("//a[text()='"+expressionName+"']//ancestor::div[@class='rule-details-accordion']//a[contains(@class,'zs-icon-delete')]"));
	}

}