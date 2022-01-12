package com.RDM.Merger.testcases.BRM;

import com.RDM.Merger.locators.BRMPage;
import com.RDM.Merger.testcases.Aa_Login.ExcelReader;
import com.RDM.Merger.testcases.Aa_Login.LoginPageTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.RDM.Merger.pagefunctions.BRMPageFunctions;
import com.RDM.Merger.pagefunctions.CommonPageFunctions;
import com.idm.pagefunctions.IDMLoginPageFunctions;
import com.idm.testcases.IDMLoginPageTest;
import com.utility.base.WrapperFunctionUtilities;
import com.utility.logger.Log;
import org.testng.asserts.SoftAssert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BRMRuleGroupPageTest extends BRMPageFunctions {

	BRMRuleGroupPageTest brmPageTest;
	CommonPageFunctions cpage;
	String GroupName;
	String EntityName;
	String attribute,value1,operator;
	BRMPage brmPage;
	static boolean result;
	static SoftAssert softAssert;


	public BRMRuleGroupPageTest()
	{
		super();

	}
	
	@Test(groups= {"Smoke"})
	public void verifyBRMPageNavigationSucessfull() throws Exception {
		cpage = new CommonPageFunctions();
		Log.info("Method : verifyBRMPageNavigationSucessfull");
		WrapperFunctionUtilities.waitForTime(2000);
		//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
		WrapperFunctionUtilities.scrollUp(driver);
		CommonPageFunctions.clickTab("BRM","TabName");
		boolean result = WrapperFunctionUtilities.isElementPresent(brmHeader, "Business Rules Page");
		Assert.assertEquals(true, result);
	}

	//Verify Add Rule Group.   --- Smoke
	//Verify that Rule Creation screen is displayed.   --- Smoke
	//Verify that Rule Name Pane is displayed   -- Smoke
	//(New Rule Group Creation) Verify Detail Column of Audit Logs have correct info in case of a new Rule Group is created.   ---- Sanity
	//(New Rule Add) Verify Detail Column of Audit Logs have correct info in case of a new Rule is added.    ---- Sanity
	// RDH-2322	(New Rule Add) Verify Detail Column of Audit Logs have correct info in case of a new Rule is added

	@Test(groups= {"Sanity"})
	public void verifyAddBRMRule() {
		try {
			cpage = new CommonPageFunctions();
			Log.info("Method : verifyAddBRMRule");
			WrapperFunctionUtilities.waitForTime(2000);
			//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
			CommonPageFunctions.selectRDHEnvironmentIfDisplayed("Common","Environment");
			WrapperFunctionUtilities.scrollUp(driver);
			CommonPageFunctions.clickTab("BRM","TabName");
			String groupName = createNewRuleGroup("Entity1");
			openExistingBusinessRuleGroup(groupName);
			addNewRuleInGroup("Rule1", "Attribute1", "Operator1", "Value1", "Action", "Include", true);
			String RuleGrouptitle = ruleGroupTitle.getText();
			String Rulename = ruleNameTitle.getText();
			CommonPageFunctions.clickTab("BRM","TabName");
			//BRMPageFunctions.clickBRMInternalTabs();
			deleteExistingBRGroup(groupName);
			boolean actualresult = verifyAddRuleGroupAuditLog(RuleGrouptitle,Rulename);
			Assert.assertEquals(actualresult, true);
			Log.info("Correct Audit Log For Rule Group and Rule Name");
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
			// TODO: handle exception
		}
	}
	
	
	//RDH-2244 - Verify Duplicate Rule Functionality
	@Test(groups= {"Regression","Sanity"})
	public void verifyDuplicateBRMRule() {
		try {
		cpage = new CommonPageFunctions();
		Log.info("Method : verifyDuplicateBRMRule");
		//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
		WrapperFunctionUtilities.scrollUp(driver);
		CommonPageFunctions.clickTab("BRM","TabName");
		String groupName = createNewRuleGroup("Entity1");
		openExistingBusinessRuleGroup(groupName);
		addNewRuleInGroup("Rule1", "Attribute1", "Operator1", "Value1", "Action", "Include", true);
		String RuleGrouptitle = ruleGroupTitle.getText();
		String Rulename = ruleNameTitle.getText();
		duplicateRule("Rule1","Rule2");
		WrapperFunctionUtilities.waitForTime(1500);
		String Duplicatename = duplicateRuleNameTitle.getText();
		CommonPageFunctions.clickTab("BRM","TabName");
		deleteExistingBRGroup(groupName);
		boolean actualresult = verifyDuplicateRuleGroupAuditLog(RuleGrouptitle,Rulename, Duplicatename);
		Assert.assertEquals(actualresult, true);
		Log.info("Correct Audit Log For Duplicate Rule Name");
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	// RDH-2571 - Verify user able to select the look up table's column value for In and Not In operator for value field and rule summary is updated accordingly
	@Test(groups= {"Sanity"})
	public void verifyAddRuleGroupAdvanceBRMLookUp() 
	{	
		try {
		cpage = new CommonPageFunctions();
		Log.info("Method : verifyAddRuleGroupAdvanceBRMLookUp");
		WrapperFunctionUtilities.waitForTime(2000);
		//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
		WrapperFunctionUtilities.scrollUp(driver);
		CommonPageFunctions.clickTab("BRM","TabName");
		WrapperFunctionUtilities.waitForTime(2000);
		BRMPageFunctions.createRuleGroup("RuleGroup_AdvanceBRM","Entity1");
		WrapperFunctionUtilities.waitForTime(2000);
		BRMPageFunctions.addRuleinGroupAdvanceBRMLookUp("RuleGroup_AdvanceBRM","Rule1","SubjectArea1","Attribute1","Operator3","LookUpTable","LookUpColumn");
		WrapperFunctionUtilities.waitForTime(3000);
		String RuleGrouptitle = ruleGroupTitle.getText();
		String Rulename = ruleNameTitle.getText();
		CommonPageFunctions.clickTab("BRM","TabName");
		/*
		 * BRMPageFunctions.clickBRMInternalTabs();
		 * BRMPageFunctions.clickDelete("RuleGroup1");
		 */ 
		deleteExistingBR("RuleGroup_AdvanceBRM");
		boolean actualresult = verifyAddRuleGroupAuditLog(RuleGrouptitle,Rulename);
		Assert.assertEquals(actualresult, true);
		Log.info("Correct Audit Log For Rule Group and Rule Name");
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	//RDH-2336 (Delete Rule) Verify Detail Column of Audit Logs have correct info in case Rule is Deleted
	@Test(groups= {"Regression","Sanity"})
	public void verifyDeleteBRMRule() {
		try{
		Log.info("Method : verifyDeleteBRMRule");
		//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
		WrapperFunctionUtilities.scrollUp(driver);
		CommonPageFunctions.clickTab("BRM","TabName");
		String groupName = createNewRuleGroup("Entity1");
		openExistingBusinessRuleGroup(groupName);
		addNewRuleInGroup("Rule1", "Attribute1", "Operator1", "Value1", "Action", "Include", true);
		String RuleGrouptitle = ruleGroupTitle.getText();
		String Rulename = ruleNameTitle.getText();
		deleteRule("Rule1");
		CommonPageFunctions.clickTab("BRM","TabName");
		deleteExistingBRGroup(groupName);
		boolean actualresult  =	verifyRuleDeleteAuditLog(RuleGrouptitle,Rulename);
		Assert.assertEquals(actualresult, true);
		Log.info("Correct Audit Log For Rule Delete");
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	//Verify Scenario when User Selects Transform from the Action Dropdown, Attribute and New Attribute fields are displayed --> EDGE-17273
	@Test(groups= {"Regression","Sanity"})
	public void verifyTransformAtrributeAndNewAttributeFields() {
		try {
		cpage = new CommonPageFunctions();
		Log.info("Method : verifyTransformAtrributeAndNewAttributeFields");
		WrapperFunctionUtilities.waitForTime(2000);
		//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
		WrapperFunctionUtilities.scrollUp(driver);
		CommonPageFunctions.clickTab("BRM","TabName");
		WrapperFunctionUtilities.waitForTime(2000);
		BRMPageFunctions.createRuleGroup("RuleGroup1","Entity1");
		WrapperFunctionUtilities.waitForTime(2000);
		BRMPageFunctions.verifyAtrributeAndNewAttributeFields("RuleGroup1","Rule1","Transform_Action");
		boolean actualresult1 = transformAttributes.isDisplayed();
		Assert.assertEquals(true, actualresult1 );
		boolean actualresult2 = newAttributeName.isDisplayed();
		Assert.assertEquals(true, actualresult2);
		BRMPageFunctions.addNewExpression("Attribute1","Operator1","Value1");
		WrapperFunctionUtilities.waitForTime(2000);
		String RuleGrouptitle = ruleGroupTitle.getText();
		String Rulename = ruleNameTitle.getText();
		CommonPageFunctions.clickTab("BRM","TabName");
		/*
		 * BRMPageFunctions.clickBRMInternalTabs();
		 * WrapperFunctionUtilities.waitForTime(1000);
		 * BRMPageFunctions.clickDelete("RuleGroup1");
		 */
		deleteExistingBR("RuleGroup1");
		boolean actualresult = verifyAddRuleGroupAuditLog(RuleGrouptitle,Rulename);
		Assert.assertEquals(actualresult, true);
		Log.info("Correct Audit Log For Rule Group and Rule Name");
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	//Verify Test Rule Works as expected with New Advanced Business Rule Implementation for Transform Actions --> EDGE-17271
	@Test(groups= {"Regression"})
	public void verifyTestRuleForTransformRuleAction() throws Exception {
		cpage = new CommonPageFunctions();
		Log.info("Method : verifyTestRuleForTransformRuleAction");
		WrapperFunctionUtilities.waitForTime(2000);
		//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
		WrapperFunctionUtilities.scrollUp(driver);
		CommonPageFunctions.clickTab("BRM","TabName");
		BRMPageFunctions.createRuleGroup("RuleGroup_AdvanceBRM","Entity1");
		BRMPageFunctions.verifyTransformRuleAction("RuleGroup_AdvanceBRM","Rule1","Attribute1","Attribute3","Operator1","Value1","Transform_Action");
		WrapperFunctionUtilities.waitForTime(2000);
		String RuleGrouptitle = ruleGroupTitle.getText();
		String Rulename = ruleNameTitle.getText();
		CommonPageFunctions.clickTab("BRM","TabName");
		deleteExistingBR("RuleGroup_AdvanceBRM");
		//BRMPageFunctions.clickDelete("RuleGroup_AdvanceBRM");
		boolean actualresult = verifyAddRuleGroupAuditLog(RuleGrouptitle,Rulename);
		Assert.assertEquals(actualresult, true);
		Log.info("Correct Audit Log For Rule Group and Rule Name");
	}

	//Verify Test Rule Works as expected with New Advanced Business Rule Implementation for Include/Exclude Actions --> EDGE-17271
	@Test(groups= {"Regression"})
	public void verifyTestRuleForIncludeExcludeRuleAction() throws Exception {
		cpage = new CommonPageFunctions();
		Log.info("Method : verifyTestRuleForIncludeExcludeRuleAction");
		WrapperFunctionUtilities.waitForTime(2000);
		//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
		WrapperFunctionUtilities.scrollUp(driver);
		CommonPageFunctions.clickTab("BRM","TabName");
		/*WrapperFunctionUtilities.waitForTime(2000);
		BRMPageFunctions.createRuleGroup("RuleGroup1","Entity1");
		WrapperFunctionUtilities.waitForTime(2000);*/
		String groupName = createNewRuleGroup("Entity1");
		openExistingBusinessRuleGroup(groupName);
		BRMPageFunctions.verifyIncludeExcludeRuleAction("Rule1","Attribute1","Attribute3","Operator1","Value1","Value2","Action");
		WrapperFunctionUtilities.waitForTime(2000);
		String RuleGrouptitle = ruleGroupTitle.getText();
		String Rulename = ruleNameTitle.getText();
		CommonPageFunctions.clickTab("BRM","TabName");
		deleteExistingBRGroup(groupName);
		boolean actualresult = verifyAddRuleGroupAuditLog(RuleGrouptitle,Rulename);
		Assert.assertEquals(actualresult, true);
		Log.info("Correct Audit Log For Rule Group and Rule Name");
	}

		//Verify user is able to specify expression with Maths function sum(number1) in select attribute field and value field -->EDGE-17270
		@Test(groups= {"Regression","Sanity"})
		public void verifyMathsFunctionInRuleCondition() {
			try {
			cpage = new CommonPageFunctions();
			Log.info("Method : verifyMathsFunctionInRuleCondition");
			WrapperFunctionUtilities.waitForTime(2000);
			WrapperFunctionUtilities.scrollUp(driver);
			CommonPageFunctions.clickTab("BRM","TabName");
			String groupName = createNewRuleGroup("Entity1");
			openExistingBusinessRuleGroup(groupName);
			BRMPageFunctions.verifyMathFunction("RuleGroup_Entity1","Rule1","Expression11","Expression12","Operator1","Action");
			WrapperFunctionUtilities.waitForTime(2000);
			String RuleGrouptitle = ruleGroupTitle.getText();
			String Rulename = ruleNameTitle.getText();
			CommonPageFunctions.clickTab("BRM","TabName");
			deleteExistingBRGroup(groupName);
			boolean actualresult = verifyAddRuleGroupAuditLog(RuleGrouptitle,Rulename);
			Assert.assertEquals(actualresult, true);
			Log.info("Correct Audit Log For Rule Group and Rule Name");
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				Assert.fail(e.getMessage());
			
			}
		}

	// EDGE-17116 Verify Delete functionality in Tables Tab
	@Test(groups= {"Regression High","Sanity"})
	public void verifyDeleteRuleGroup() {
	try {
		cpage = new CommonPageFunctions();
		Log.info("Method : verifyDeleteRuleGroup");
		WrapperFunctionUtilities.waitForTime(1000);
		//CommonPageFunctions.selectRDHEnvironment("Common","Environment");
		WrapperFunctionUtilities.scrollUp(driver);
		CommonPageFunctions.clickTab("BRM","TabName");
		WrapperFunctionUtilities.waitForTime(1000);
		BRMPageFunctions.createRuleGroup("RuleGroup1","Entity1");
		String ruleGroupCountBeforeDeletion = ruleGroupCountElement.getText();
		WrapperFunctionUtilities.waitForTime(1000);
		//BRMPageFunctions.clickDelete("RuleGroup1");
		BRMPageFunctions.deleteExistingBR("RuleGroup1");
		String ruleGroupCountAfterDeletion = ruleGroupCountElement.getText();
		Log.info("ruleGroupCountBeforeDeletion  " +ruleGroupCountBeforeDeletion + "  ruleGroupCountAfterDeletion  "+ruleGroupCountAfterDeletion );
		Assert.assertNotEquals("ruleGroupCountBeforeDeletion","ruleGroupCountAfterDeletion");
		}	
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@BeforeMethod(groups = {"Sanity","Regression_High_Priority","Smoke","Regression"})
	public void launchURL()
	{
		Log.info("Method : launchURL ");
		cpage = new CommonPageFunctions();
		brmPage = new BRMPage();
		CommonPageFunctions.navigateToURL(prop.getProperty("url"));
		WrapperFunctionUtilities.waitForPageToLoad(30, driver);
		//CommonPageFunctions.selectRDHEnvironmentIfDisplayed("Common","Environment");
		softAssert = new SoftAssert();
	}

	//Verify character limit for Parameter Name in Rule Condition should not exceed 50 characters -- EDGE-17241
	//Verify case for special character in parameter name and that 50 character limit is in place. -- EDGE-17237
	@Test(groups= {"Regression_High_Priority"},priority = -1)
	public void verifyCharacterLimitForParameterNameInRuleConditionShouldNotExceed50Characters() throws Exception {
			Log.info("Method : verifyCharacterLimitForParameterNameInRuleConditionShouldNotExceed50Characters");
			CommonPageFunctions.selectRDHEnvironmentIfDisplayed("Common","Environment");
			CommonPageFunctions.clickTab("BRM","TabName");
			CommonPageFunctions.closeAlertPopUpOnHomeTab();
			openFirstExistingBusinessRuleGroupAndAddRule();
			result = verifyErrorMessageForExpressionNameWordLimit("InvalidExpressionName", "ValidExpressionName1");
			Assert.assertTrue(result);
			result= verifySpecialCharacterCanBeUsedInExpressionName("ExpressionNameWithSpecialCharacters");
			CommonPageFunctions.clickTab("BRM","TabName");
		}

	// Verify Rule Summary gets updated when any Parameter is Edited -- EDGE-17235
	// Verify Rule Summary gets updated when any Parameter is added/Edited or Deleted -- EDGE-17213
	@Test(groups= {"Regression_High_Priority"})
	public void verifyRuleSummaryGetsUpdatedWhenAnyParameterIsEdited() throws Exception {
		Log.info("Method : verifyRuleSummaryGetsUpdatedWhenAnyParameterIsEdited");
		CommonPageFunctions.clickTab("BRM","TabName");
		String groupName = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName);
		addMultipleExpressionsInRule("Rule1","Attribute1","Attribute3","Operator1","Value1","Value3","Action",false);
		result = verifyRuleSummaryText("BRM","Entity3","Attribute1","Attribute3","Operator1","Value1","Value3");
		softAssert.assertTrue(result,"First Assertion Failed");
		clickSaveRuleButton();
		CommonPageFunctions.clickTab("BRM","TabName");
		openExistingBusinessRuleGroup(groupName);
		result = editExistingExpressionForRuleAndVerifyRuleSummary("Rule1","Attribute1","Value3");
		softAssert.assertTrue(result,"Second Assertion Failed");
		CommonPageFunctions.clickTab("BRM","TabName");
		deleteExistingBRGroup(groupName);
		softAssert.assertAll();
	}

	//Verify Rule Summary gets updated when any Parameter is Deleted -- EDGE-17214
	@Test(groups= {"Regression_High_Priority"})
	public void verifyRuleSummaryGetsUpdatedWhenAnyParameterIsDeleted() throws Exception {
		Log.info("Method : verifyRuleSummaryGetsUpdatedWhenAnyParameterIsDeleted");
		CommonPageFunctions.clickTab("BRM","TabName");
		String groupName = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName);
		addMultipleExpressionsInRule("Rule1","Attribute1","Attribute3","Operator1","Value1","Value2","Action",true);
		result = deleteExistingExpressionForRuleAndVerifyRuleSummary("Rule1","Attribute1","Value1");
		Assert.assertTrue(result);
		CommonPageFunctions.clickTab("BRM","TabName");
		deleteExistingBRGroup(groupName);
	}

	//Verify Rule Summary gets generated besides the parameter Name as well -- EDGE-17226
	@Test(groups= {"Regression_High_Priority"})
	public void verifyRuleSummaryGetsGeneratedBesidesTheParameterName() throws Exception {
		Log.info("Method : verifyCharacterLimitForParameterNameInRuleConditionShouldNotExceed50Characters");
		CommonPageFunctions.clickTab("BRM","TabName");
		String groupName = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName);
		addNewRuleInGroup("Rule1", "Attribute1","Operator1","Value1","Action","Include",false);
		result = verifyRuleSummaryBesidesParameterName("BRM","Entity3","Attribute1","Value1","Operator1","Action","Include");
		CommonPageFunctions.clickTab("BRM","TabName");
		deleteExistingBRGroup(groupName);
		Assert.assertTrue(result);
	}

	// Verify case if user toggles between include/exclude and transform , changes are reflected -- EDGE-17236
	@Test(groups= {"Regression_High_Priority"})
	public void verifyChangesAreReflectedWhenUserTogglesBetweenIncludeExcludeAndTransform() throws Exception {
		Log.info("Method : verifyChangesAreReflectedWhenUserTogglesBetweenIncludeExcludeAndTransform");
		CommonPageFunctions.clickTab("BRM","TabName");
		String groupName = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName);
		addNewRuleInGroup("Rule1", "Attribute1","Operator1","Value1","Action","Include",false);
		result = verifyRuleSummaryBesidesParameterName("BRM","Entity3","Attribute1","Value1","Operator1","Action","Include");
		softAssert.assertTrue(result,"verifyRuleSummaryBesidesParameterName Assertion Failed");
		changeRuleActionToTransform("Attribute1","Value3");
		result = verifyRuleSummaryBesidesParameterNameForTransform("BRM","Entity3","Attribute1","Value1","Operator1","Attribute1","Value3");
		softAssert.assertTrue(result,"verifyRuleSummaryBesidesParameterNameForTransform Assertion Failed");
		CommonPageFunctions.clickTab("BRM","TabName");
		deleteExistingBRGroup(groupName);
		softAssert.assertAll();
	}

	//Verify whether without selecting lookup table user should not be able to select the column from the table. -- EDGE-17230
	//Verify Searchable dropdown to be created for lookup table and lookup column -- EDGE-17227
	//Verify Lookup Column should get reset on changing the lookup table. -- EDGE-17219
	@Test(groups= {"Regression_High_Priority"})
	public void verifyWhetherWithoutSelectingLookupTableUserShouldNotBeAbleToSelectTheColumnFromTheTable() throws Exception {
		Log.info("Method : verifyWhetherWithoutSelectingLookupTableUserShouldNotBeAbleToSelectTheColumnFromTheTable");
		CommonPageFunctions.clickTab("BRM","TabName");
		String groupName = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName);
		result = verifyLookupColumnDropDownIsDisabledByDefault("BRM", "Rule1", "Attribute1","Operator3","LookUpTable");
		softAssert.assertTrue(result,"verifyLookupColumnDropDownIsDisabledByDefault Assertion Failed");
		result = verifyLookupTableAndLookupColumnHasSearchBox("BRM","LookUpTable", "LookUpColumn");
		softAssert.assertTrue(result,"verifyLookupTableAndLookupColumnHasSearchBox Assertion Failed");
		result = verifyLookupColumnDropDownResetsOnChangingSourceTable("BRM","LookUpTable1");
		softAssert.assertTrue(result,"verifyLookupColumnDropDownResetsOnChangingSourceTable Assertion Failed");
		CommonPageFunctions.clickTab("BRM","TabName");
		deleteExistingBRGroup(groupName);
		softAssert.assertAll();
	}

	//Verify User is shown an Action Dropdown to chose the intended action on Rule Page. -- EDGE-17260
	//Verify User is able to Select only 1 Action at a time for a Rule. -- EDGE-17217
	@Test(groups= {"Regression_High_Priority"})
	public void verifyUserIsShownActionDropdownAndOnlyOneActionCanBeSelected() throws Exception {
		Log.info("Method : verifyUserIsShownActionDropdownAndOnlyOneActionCanBeSelected");
		CommonPageFunctions.clickTab("BRM", "TabName");
		openFirstExistingBusinessRuleGroupAndAddRule();
		result = verifyActionDropDownIsNotMultiSelect();
		softAssert.assertTrue(result,"verifyActionDropDownIsNotMultiSelect Assertion Failed");
		result = verifyActionDropDownOptionsOnRuleSummaryPage("BRM","Action","Include");
		softAssert.assertTrue(result,"verifyActionDropDownOptionsOnRuleSummaryPage Assertion Failed");
		softAssert.assertAll();
	}

	// Verify user is able to publish version with version name which exists in other group -- EDGE-17173
		@Test(groups= {"Regression_High_Priority"})
		public void verifyUserIsAbleToPublishVersionWithVersionNameWhichExistsInOtherGroup() throws Exception {
		Log.info("Method : verifyUserIsAbleToPublishVersionWithVersionNameWhichExistsInOtherGroup");
		CommonPageFunctions.clickTab("BRM", "TabName");
		String groupName1 = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName1);
		addNewRuleInGroup("Rule1", "Attribute1","Operator1","Value1","Action","Include",true);
		result = publishNewVersionOfBusinessRule("VersionNumber1", false,"",false);
		softAssert.assertTrue(result,"publishNewVersionOfBusinessRule Assertion Failed");
		CommonPageFunctions.clickTab("BRM", "TabName");
		String groupName2 = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName2);
		addNewRuleInGroup("Rule1", "Attribute1","Operator1","Value1","Action","Include",true);
		result = publishNewVersionOfBusinessRule("VersionNumber1", true,"UniqueVersionNameErrorMessage",false);
		softAssert.assertTrue(result,"publishNewVersionOfBusinessRule Assertion Failed");
		result = verifySuccessToasterMessageForPublishingNewVersion("VersionNumber1");
		softAssert.assertTrue(result, "Failed to verify Assertion : verifySuccessToasterMessageForPublishingNewVersion");
		CommonPageFunctions.clickTab("BRM","TabName");
		deleteExistingBRGroup(groupName1);
		deleteExistingBRGroup(groupName2);
		softAssert.assertAll();
	}

	//Verify unpublished icon is shown on change in description of a rule -- EDGE-17195
	@Test(groups= {"Regression_High_Priority"})
	public void verifyUnpublishedIconIsShownOnChangeInDescriptionOfARule() throws Exception {
		Log.info("Method : verifyUserIsAbleToPublishVersionWithVersionNameWhichExistsInOtherGroup");
		CommonPageFunctions.clickTab("BRM", "TabName");
		String groupName = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName);
		addNewRuleInGroup("Rule1", "Attribute1", "Operator1", "Value1", "Action", "Include", true);
		result = publishNewVersionOfBusinessRule("VersionNumber1", false,"", false);
		softAssert.assertTrue(result, "publishNewVersionOfBusinessRule Assertion Failed");
		CommonPageFunctions.clickTab("BRM", "TabName");
		result = CommonPageFunctions.storeExistenceOfElement(brmPage_UnpublishedChangesOnRuleTile_Locator(groupName),driver);
		softAssert.assertFalse(result, "Unpublished Changes Icon pre-exists on the tile ");
		openExistingBusinessRuleGroup(groupName);
		updateRuleDescription("Rule1", " Updated Description");
		CommonPageFunctions.clickTab("BRM", "TabName");
		result = CommonPageFunctions.storeExistenceOfElement(brmPage_UnpublishedChangesOnRuleTile_Locator(groupName),driver);
		softAssert.assertTrue(result, "Unpublished Changes Icon doesn't exists on the tile ");
		deleteExistingBRGroup(groupName);
		softAssert.assertAll();
	}

	//Verify Else Condition is Visible only for Transform Action -- EDGE-17243
	@Test(groups= {"Regression_High_Priority"})
	public void verifyElseConditionIsVisibleOnlyForTransformAction() throws Exception {
		Log.info("Method : verifyElseConditionIsVisibleOnlyForTransformAction");
		CommonPageFunctions.clickTab("BRM", "TabName");
		String groupName = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName);
		addRuleInGroupWithTransformAction("BRM","Rule1","Attribute1","Attribute1", "Operator1", "Value1","Value2",false);
		addElseCondition("Value3");
		result = changeActionToIncludeExcludeAndVerifyElseConditionIsRemoved("Exclude");
		CommonPageFunctions.clickTab("BRM", "TabName");
		deleteExistingBRGroup(groupName);
		Assert.assertTrue(result);
	}

	//Verify Free-Text in Expression Text Area is allowed to user and it can configure any function whether present in the list or not -- EDGE-17239
	@Test(groups= {"Regression_High_Priority"})
	public void verifyFreeTextInExpressionTextAreaIsAllowedToUser() throws Exception {
		Log.info("Method : verifyFreeTextInExpressionTextAreaIsAllowedToUser");
		CommonPageFunctions.clickTab("BRM", "TabName");
		String groupName = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName);
		result = addNewRuleInGroupThroughExpressionIcon("BRM","Rule1","Expression11","Operator1", "Expression12", "Action","Include",true);
		CommonPageFunctions.clickTab("BRM", "TabName");
		deleteExistingBRGroup(groupName);
		Assert.assertTrue(result);
	}

	//Verify all the selected attributes and values in a Parameter are Saved when the user clicks on the Save button -- EDGE-17222
	// Verify user is able see create new Sub Rule link and Add Else condition after clicking on Add new rule -- EDGE-17276
	@Test(groups= {"Regression_High_Priority"})
	public void verifyAllTheSelectedAttributesAndValuesInAParameterAreSaved() throws Exception {
		Log.info("Method : verifyAllTheSelectedAttributesAndValuesInAParameterAreSaved");
		CommonPageFunctions.clickTab("BRM", "TabName");
		String groupName = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName);
		addAdvanceRuleInGroup("BRM","Rule1","Transform_Action","Attribute1","Attribute1", "Operator3", "LookUpTable","LookUpColumn","Value2",false);
		result = addNewExpression("BRM","Expression1","Operator1","Value3","Value2");
		softAssert.assertTrue(result, " Add New Expression Element Doesn't Exist");
		result =  addElseCondition("Value3");
		softAssert.assertTrue(result, " Add else Element Doesn't Exist");
		clickSaveRuleButton();

		result = verifyAllTheSelectedAttributesInRule("Rule1","Attribute1", "Operator3", "LookUpTable",
								"LookUpColumn","Value2", "Expression1","Operator1",
										"Value3","Value2","Value3");

		CommonPageFunctions.clickTab("BRM", "TabName");
		deleteExistingBRGroup(groupName);
		softAssert.assertTrue(result,"verifyAllTheSelectedAttributesInRule : All The Selected Attributes & values In Rule are not getting retained ");
		softAssert.assertAll();
	}

	//Verify Lookup entity should not be visible for creating business rule groups via Add Rule Group Feature -- EDGE-17240
	@Test(groups= {"Regression_High_Priority"})
	public void verifyLookupEntityShouldNotBeVisibleForCreatingBusinessRuleGroups() throws Exception {
		Log.info("Method : verifyLookupEntityShouldNotBeVisibleForCreatingBusinessRuleGroups");
		CommonPageFunctions.clickTab("BRM", "TabName");
		String groupName = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName);
		addAdvanceRuleInGroup("BRM", "Rule1", "Action", "", "Attribute1", "Operator3", "LookUpTable", "LookUpColumn", "", false);
		List<String> lookUpTableList = storeLookUpTableDropDownOptions();
		clickSaveRuleButton();
		CommonPageFunctions.clickTab("BRM", "TabName");
		result = verifyLookUpEntityIsNotPresentInRuleGroupEntityDropDown(lookUpTableList);
		WrapperFunctionUtilities.refreshPageWithPageLoadTimeout(driver);
		deleteExistingBRGroup(groupName);
		Assert.assertTrue(result,"Failed to verify Assertion : verifyLookUpEntityIsNotPresentInRuleGroupEntityDropDown");
	}

	//Verify Data in Cards for Card Tab -- EDGE-17111
	//Verify Search functionality for both Cards/Table layout -- EDGE-17112
	@Test(groups= {"Regression_High_Priority"})
	public void verifyDataInCardsForCardTab() throws Exception {
		Log.info("Method : verifyDataInCardsForCardTab");
		CommonPageFunctions.clickTab("BRM", "TabName");
		String groupName = createNewRuleGroup("Entity3","Group Description");
		openExistingBusinessRuleGroup(groupName);
		addNewRuleInGroup("Rule1", "Attribute1", "Operator1", "Value1", "Action", "Include", true);
		SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");//dd/MM/yyyy
		Date currentDate = new Date();
		String expectedDateFormat = sdfDate.format(currentDate);
		CommonPageFunctions.clickTab("BRM", "TabName");
		result = verifyDataOnRuleGroupCard(groupName,"Entity3","Group Description",expectedDateFormat,"1");
		softAssert.assertTrue(result,"Failed to verify Assertion : verifyDataOnRuleGroupCard");
		result = verifySearchFunctionalityOnCardsTab(groupName,"Entity3","Group Description");
		softAssert.assertTrue(result,"Failed to verify Assertion : verifySearchFunctionalityOnCardsTab");
		CommonPageFunctions.clickTab("BRM", "TabName");
		deleteExistingBRGroup(groupName);
		softAssert.assertAll();
	}

	//Verify Table Tab on Rule Group Screen -- EDGE-17110
	@Test(groups= {"Regression_High_Priority"})
		public void verifyTableTabOnRuleGroupScreen() throws Exception {
		Log.info("Method : verifyTableTabOnRuleGroupScreen");
		CommonPageFunctions.clickTab("BRM", "TabName");
		result = verifyColumnHeadersOnTableTabAndEditDeleteIconScreen();
		Assert.assertTrue(result,"Failed to verify assertion : verifyColumnHeadersOnTableTabAndEditDeleteIconScreen");
	}

	//Verify character limit & special characters on version name -- EDGE-17181
	@Test(groups= {"Regression_High_Priority"})
	public void verifyCharacterLimitAndSpecialCharactersOnVersionName() throws Exception {
		Log.info("Method : verifyCharacterLimitAndSpecialCharactersOnVersionName");
		CommonPageFunctions.clickTab("BRM", "TabName");
		String groupName1 = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName1);
		addNewRuleInGroup("Rule1", "Attribute1", "Operator1", "Value1", "Action", "Include", true);
		String maxCharVersionName = ExcelReader.getValue("BRM", "MaxCharVersionName", "TestData");
		result = publishNewVersionOfBusinessRule(maxCharVersionName, true, "MaxCharVersionErrorMessage", true);
		softAssert.assertTrue(result, "publishNewVersionOfBusinessRule Assertion Failed 3");
		clickCancelPublishNewVersion();
		String VersionNameWithSpecialChar = ExcelReader.getValue("BRM", "VersionNameWithSpecialCharacters", "TestData");
		publishNewVersionOfBusinessRule(VersionNameWithSpecialChar, false, "MaxCharVersionErrorMessage", false);
		result = verifySuccessToasterMessageForPublishingNewVersion(VersionNameWithSpecialChar);
		softAssert.assertTrue(result, "Failed to verify Assertion : verifySuccessToasterMessageForPublishingNewVersion");
		CommonPageFunctions.clickTab("BRM", "TabName");
		deleteExistingBRGroup(groupName1);
		softAssert.assertAll();
	}

	//Verify that username is updated when rule is duplicated by another user -- EDGE-17128
	@Test(groups= {"Regression_High_Priority","MultiUserScenario"})
	public void VerifyUsernameIsUpdatedWhenRuleIsDuplicatedByAnotherUser() throws Exception {
		Log.info("Method : VerifyUsernameIsUpdatedWhenRuleIsDuplicatedByAnotherUser");
		CommonPageFunctions.clickTab("BRM", "TabName");
		String groupName = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName);
		addNewRuleInGroup("Rule1", "Attribute1", "Operator1", "Value1", "Action", "Include", true);
		LoginPageTest.logOutFromExistingUser(driver);
		CommonPageFunctions.navigateToURL(prop.getProperty("url"));
		LoginPageTest.loginAdminUser2();
		String username = prop.getProperty("admin_user2");
		CommonPageFunctions.selectRDHEnvironmentIfDisplayed("Common","Environment");
		CommonPageFunctions.clickTab("BRM", "TabName");
		openExistingBusinessRuleGroup(groupName);
		duplicateRule("Rule1","Rule2");
		String ruleName = ExcelReader.getValue("BRM", "Rule2", "TestData");
		result = verifyRuleCreatedByAndLastEditByUserInformation(ruleName,username);
		softAssert.assertTrue(result,"Failed to Verify Assertion : verifyRuleCreatedByAndLastEditByUserInformation");
		CommonPageFunctions.clickTab("BRM", "TabName");
		result = verifyLastEditByUsernameOnRuleGroupCard(groupName, username);
		softAssert.assertTrue(result,"Failed to Verify Assertion : verifyLastEditByUsernameOnRuleGroupCard");
		deleteExistingBRGroup(groupName);
		LoginPageTest.logOutFromExistingUser(driver);
		CommonPageFunctions.navigateToURL(prop.getProperty("url"));
		LoginPageTest.loginTest();
		CommonPageFunctions.selectRDHEnvironmentIfDisplayed("Common","Environment");
		softAssert.assertAll();
	}

	//Verify Username is updated if any change is made in Rule definition -- EDGE-17125
	@Test(groups= {"Regression_High_Priority","MultiUserScenario"})
	public void verifyUsernameIsUpdatedIfAnyChangeIsMadeInRuleDefinition() throws Exception {
		Log.info("Method : verifyUsernameIsUpdatedIfAnyChangeIsMadeInRuleDefinition");
		CommonPageFunctions.clickTab("BRM","TabName");
		String groupName = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName);
		addMultipleExpressionsInRule("Rule1","Attribute1","Attribute3","Operator1","Value1","Value2","Action",true);
		LoginPageTest.logOutFromExistingUser(driver);
		CommonPageFunctions.navigateToURL(prop.getProperty("url"));
		LoginPageTest.loginAdminUser2();
		String username = prop.getProperty("admin_user2");
		CommonPageFunctions.selectRDHEnvironmentIfDisplayed("Common","Environment");
		CommonPageFunctions.clickTab("BRM", "TabName");
		openExistingBusinessRuleGroup(groupName);
		result = deleteExistingExpressionForRuleAndVerifyRuleSummary("Rule1","Attribute1","Value1");
		softAssert.assertTrue(result,"Failed to perform : deleteExistingExpressionForRuleAndVerifyRuleSummary");
		String ruleName = ExcelReader.getValue("BRM", "Rule1", "TestData");
		result = verifyRuleLastEditByUserInformation(ruleName,username);
		softAssert.assertTrue(result,"Failed to Verify : verifyRuleLastEditByUserInformation");
		CommonPageFunctions.clickTab("BRM","TabName");
		deleteExistingBRGroup(groupName);
		LoginPageTest.logOutFromExistingUser(driver);
		CommonPageFunctions.navigateToURL(prop.getProperty("url"));
		LoginPageTest.loginTest();
		CommonPageFunctions.selectRDHEnvironmentIfDisplayed("Common","Environment");
		softAssert.assertAll();
	}

	// Verify unpublished icon is shown on Editing of Start Date and End Date of rule -- EDGE-17194
	@Test(groups= {"Regression_High_Priority"})
	public void verifyUnpublishedIconIsShownOnEditingOfStartDateAndEndDateOfRule() throws Exception {
		Log.info("Method : verifyUnpublishedIconIsShownOnEditingOfStartDateAndEndDateOfRule");
		CommonPageFunctions.clickTab("BRM", "TabName");
		String groupName = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName);
		addNewRuleInGroup("Rule1", "Attribute1", "Operator1", "Value1", "Action", "Include", true);
		result = publishNewVersionOfBusinessRule("VersionNumber1", false, "", false);
		result = verifySuccessToasterMessageForPublishingNewVersion("VersionNumber1");
		softAssert.assertTrue(result, "verifySuccessToasterMessageForPublishingNewVersion Assertion Failed");
		CommonPageFunctions.clickTab("BRM", "TabName");
		result = CommonPageFunctions.storeExistenceOfElement(brmPage_UnpublishedChangesOnRuleTile_Locator(groupName), driver);
		softAssert.assertFalse(result, "Unpublished Changes Icon pre-exists on the tile ");
		openExistingBusinessRuleGroup(groupName);
		result = editExpirationDateForRule("Rule1");
		softAssert.assertTrue(result, "Changes arent saved Successfully for editExpirationDateForRule");
		CommonPageFunctions.clickTab("BRM", "TabName");
		result = CommonPageFunctions.storeExistenceOfElement(brmPage_UnpublishedChangesOnRuleTile_Locator(groupName),driver);
		softAssert.assertTrue(result, "Unpublished Changes Icon does not exists on the tile ");
		openExistingBusinessRuleGroup(groupName);
		result = publishNewVersionOfBusinessRule("VersionNumber2", false, "", false);
		result = verifySuccessToasterMessageForPublishingNewVersion("VersionNumber2");
		softAssert.assertTrue(result, "verifySuccessToasterMessageForPublishingNewVersion Assertion Failed");
		result = editStartDateForRule("Rule1");
		softAssert.assertTrue(result, "Changes arent saved Successfully for editStartDateForRule");
		CommonPageFunctions.clickTab("BRM", "TabName");
		result = CommonPageFunctions.storeExistenceOfElement(brmPage_UnpublishedChangesOnRuleTile_Locator(groupName),driver);
		softAssert.assertTrue(result, "Unpublished Changes Icon does not exists on the tile ");
		deleteExistingBRGroup(groupName);
		softAssert.assertAll();
	}

	// Verify unpublished changes icon is shown on selecting/Unselecting the subject area -- EDGE-17188
	@Test(groups= {"Regression_High_Priority"})
	public void verifyUnpublishedIconIsShownOnSelectingUnselectingTheSubjectArea() throws Exception {
		Log.info("Method : verifyUnpublishedIconIsShownOnSelectingUnselectingTheSubjectArea");
		CommonPageFunctions.clickTab("BRM", "TabName");
		String groupName = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName);
		addNewRuleInGroup("Rule1", "Attribute1", "Operator1", "Value1", "Action", "Include", true);
		result = publishNewVersionOfBusinessRule("VersionNumber1", false, "", false);
		result = verifySuccessToasterMessageForPublishingNewVersion("VersionNumber1");
		softAssert.assertTrue(result, "verifySuccessToasterMessageForPublishingNewVersion Assertion Failed");
		CommonPageFunctions.clickTab("BRM", "TabName");
		result = CommonPageFunctions.storeExistenceOfElement(brmPage_UnpublishedChangesOnRuleTile_Locator(groupName), driver);
		softAssert.assertFalse(result, "Unpublished Changes Icon pre-exists on the tile ");
		openExistingBusinessRuleGroup(groupName);
		result = selectFirstDropDownOptionFromSubjectArea("Rule1");
		softAssert.assertTrue(result, "Changes arent saved Successfully for editExpirationDateForRule");
		CommonPageFunctions.clickTab("BRM", "TabName");
		result = CommonPageFunctions.storeExistenceOfElement(brmPage_UnpublishedChangesOnRuleTile_Locator(groupName),driver);
		softAssert.assertTrue(result, "Unpublished Changes Icon does not exists on the tile ");
		openExistingBusinessRuleGroup(groupName);
		result = publishNewVersionOfBusinessRule("VersionNumber2", false, "", false);
		result = verifySuccessToasterMessageForPublishingNewVersion("VersionNumber2");
		softAssert.assertTrue(result, "verifySuccessToasterMessageForPublishingNewVersion Assertion Failed");
		result = selectFirstDropDownOptionFromSubjectArea("Rule1");
		softAssert.assertTrue(result, "Changes arent saved Successfully for editStartDateForRule");
		CommonPageFunctions.clickTab("BRM", "TabName");
		result = CommonPageFunctions.storeExistenceOfElement(brmPage_UnpublishedChangesOnRuleTile_Locator(groupName),driver);
		softAssert.assertTrue(result, "Unpublished Changes Icon does not exists on the tile ");
		deleteExistingBRGroup(groupName);
		softAssert.assertAll();
	}

	// Verify unpublished changes icon is shown when user changes action from Action dropdown -- EDGE-17183
	@Test(groups= {"Regression_High_Priority"})
	public void verifyUnpublishedChangesIconIsShownWhenUserChangesActionFromActionDropdown() throws Exception {
		Log.info("Method : verifyUnpublishedChangesIconIsShownWhenUserChangesActionFromActionDropdown");
		CommonPageFunctions.clickTab("BRM", "TabName");
		String groupName = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName);
		addNewRuleInGroup("Rule1", "Attribute1", "Operator1", "Value1", "Action", "Include", true);
		publishNewVersionOfBusinessRule("VersionNumber1", false, "", false);
		result = verifySuccessToasterMessageForPublishingNewVersion("VersionNumber1");
		softAssert.assertTrue(result, "verifySuccessToasterMessageForPublishingNewVersion Assertion Failed");
		CommonPageFunctions.clickTab("BRM", "TabName");
		result = CommonPageFunctions.storeExistenceOfElement(brmPage_UnpublishedChangesOnRuleTile_Locator(groupName),driver);
		softAssert.assertFalse(result, "Unpublished Changes Icon exists on the tile ");
		openExistingBusinessRuleGroup(groupName);
		editExistingRule("Rule1");
		changeExistingRuleActionToTransform("Attribute1", "Value3");
		clickSaveRuleButton();
		CommonPageFunctions.clickTab("BRM", "TabName");
		result = CommonPageFunctions.storeExistenceOfElement(brmPage_UnpublishedChangesOnRuleTile_Locator(groupName),driver);
		softAssert.assertTrue(result, "Unpublished Changes Icon does not exists on the tile ");
		deleteExistingBRGroup(groupName);
		softAssert.assertAll();
	}

	// Verify unpublished changes icon is shown if order of rules is changed -- EDGE-17192
	@Test(groups= {"Regression_High_Priority"})
	public void verifyUnpublishedChangesIconIsShownIfOrderOfRulesIsChanged() throws Exception {
		Log.info("Method : verifyUnpublishedChangesIconIsShownIfOrderOfRulesIsChanged");
		CommonPageFunctions.clickTab("BRM", "TabName");
		String groupName = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName);
		addNewRuleInGroup("Rule1", "Attribute1", "Operator1", "Value1", "Action", "Include", true);
		addNewRuleInGroup("Rule2", "Attribute1", "Operator1", "Value1", "Action", "Include", true);
		publishNewVersionOfBusinessRule("VersionNumber1", false, "", false);
		result = verifySuccessToasterMessageForPublishingNewVersion("VersionNumber1");
		softAssert.assertTrue(result, "verifySuccessToasterMessageForPublishingNewVersion Assertion Failed");
		result = reorderRulesInRuleGroup("Rule1", "Rule2");
		softAssert.assertTrue(result,"Failed to verify assertion : reorderRulesInRuleGroup");
		CommonPageFunctions.clickTab("BRM", "TabName");
		result = CommonPageFunctions.storeExistenceOfElement(brmPage_UnpublishedChangesOnRuleTile_Locator(groupName),driver);
		softAssert.assertTrue(result, "Unpublished Changes Icon does not exists on the tile ");
		deleteExistingBRGroup(groupName);
		softAssert.assertAll();
	}

	// Verify unpublished changed icon is show after changing New Attribute Name in transform action of a rule -- EDGE-17197
	@Test(groups= {"Regression_High_Priority"})
	public void verifyUnpublishedChangesIconIsShownAfterChangingNewAttributeNameInTransformActionOfARule() throws Exception {
		Log.info("Method : verifyUnpublishedChangesIconIsShownAfterChangingNewAttributeNameInTransformActionOfARule");
		CommonPageFunctions.clickTab("BRM", "TabName");
		String groupName = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName);
		addRuleInGroupWithTransformAction("BRM","Rule1","Attribute1","Attribute1", "Operator1", "Value1","Value2",false);
		setNewAttributeName("NewAttributeName1");
		publishNewVersionOfBusinessRule("VersionNumber1", false, "", false);
		result = verifySuccessToasterMessageForPublishingNewVersion("VersionNumber1");
		softAssert.assertTrue(result, "verifySuccessToasterMessageForPublishingNewVersion Assertion Failed");
		editExistingRule("Rule1");
		setNewAttributeName("UpdatedAttributeName1");
		CommonPageFunctions.clickTab("BRM", "TabName");
		result = CommonPageFunctions.storeExistenceOfElement(brmPage_UnpublishedChangesOnRuleTile_Locator(groupName),driver);
		softAssert.assertTrue(result, "Unpublished Changes Icon does not exists on the tile ");
		deleteExistingBRGroup(groupName);
		softAssert.assertAll();
	}

	// Verify unpublished icon is shown when user changes any attributes/conditions inside a test rule -- EDGE-17190
	@Test(groups= {"Regression_High_Priority"})
	public void verifyUnpublishedChangesIconIsShownWhenUserChangesAnyAttributesOrConditionsInsideATestRule() throws Exception {
		Log.info("Method : verifyUnpublishedChangesIconIsShownWhenUserChangesAnyAttributesOrConditionsInsideATestRule");
		CommonPageFunctions.clickTab("BRM","TabName");
		String groupName = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName);
		addMultipleExpressionsInRule("Rule1","Attribute1","Attribute3","Operator1","Value1","Value3","Action",true);
		publishNewVersionOfBusinessRule("VersionNumber1", false, "", false);
		result = verifySuccessToasterMessageForPublishingNewVersion("VersionNumber1");
		softAssert.assertTrue(result, "verifySuccessToasterMessageForPublishingNewVersion Assertion Failed");
		CommonPageFunctions.clickTab("BRM","TabName");
		result = CommonPageFunctions.storeExistenceOfElement(brmPage_UnpublishedChangesOnRuleTile_Locator(groupName),driver);
		softAssert.assertFalse(result, "Unpublished Changes Icon exists on the tile ");
		openExistingBusinessRuleGroup(groupName);
		result = editExistingExpressionForRuleAndVerifyRuleSummary("Rule1","Attribute1","Value3");
		softAssert.assertTrue(result,"Second Assertion Failed");
		CommonPageFunctions.clickTab("BRM","TabName");
		result = CommonPageFunctions.storeExistenceOfElement(brmPage_UnpublishedChangesOnRuleTile_Locator(groupName),driver);
		softAssert.assertTrue(result, "Unpublished Changes Icon does not exists on the tile ");
		deleteExistingBRGroup(groupName);
		softAssert.assertAll();
	}

	// Verify filter cases in Rule listing page. -- EDGE-17122
	@Test(groups= {"Regression_High_Priority"})
	public void verifyFilterCasesInRuleListingPage() throws Exception {
		Log.info("Method : verifyFilterCasesInRuleListingPage");
		CommonPageFunctions.clickTab("BRM", "TabName");
		String groupName = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName);
		addNewRuleInGroup("Rule1", "Attribute1", "Operator1", "Value1", "Action", "Include", true);
		addNewRuleInGroup("TestRuleName", "Attribute1", "Operator1", "Value1", "Action", "Include", true);
		editStartDateAndExpirationDateForRule("Rule1");
		selectAndStoreFirstDropDownOptionFromSubjectArea("Rule1");
		result = storeExistenceOfAddRuleButton();
		softAssert.assertTrue(result,"Failed to Save Rule Changes in Expected Time");
		result = verifySearchRulesFilterTextBoxFunctionality("TestRuleName");
		softAssert.assertTrue(result, "Failed for : verifySearchRulesFilterTextBoxFunctionality");
		result = verifySubjectAreaFilterFunctionality();
		softAssert.assertTrue(result, "Failed for : verifySubjectAreaFilterFunctionality");
		result = verifyStartDateFilterFunctionality("Rule1");
		softAssert.assertTrue(result, "Failed for : verifyStartDateFilterFunctionality");
		result = verifyExpirationDateFilterFunctionality("TestRuleName");
		softAssert.assertTrue(result, "Failed for : verifyExpirationDateFilterFunctionality");
		CommonPageFunctions.clickTab("BRM", "TabName");
		deleteExistingBRGroup(groupName);
		softAssert.assertAll();
	}

	// Verify Test rule is run on rule marked as inactive -- EDGE-17153
	@Test(groups= {"Regression_High_Priority"})
	public void verifyTestRuleIsRunOnRuleMarkedAsInactive() throws Exception {
		Log.info("Method : verifyTestRuleIsRunOnRuleMarkedAsInactive");
		CommonPageFunctions.clickTab("BRM", "TabName");
		String groupName = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName);
		addNewRuleInGroup("Rule1", "Attribute1", "Operator1", "Value1", "Action", "Include", true);
		activateOrDeactivateRule("Rule1");
		editExistingRule("Rule1");
		WrapperFunctionUtilities.waitForTime(2000);//Takes time to load entire conditions on page
		result = runTestRule();
		softAssert.assertTrue(result,"Test rule didn't run successfully");
		CommonPageFunctions.clickTab("BRM", "TabName");
		deleteExistingBRGroup(groupName);
		softAssert.assertAll();
	}

	// Verify Test rule results are updated with change in the rule -- EDGE-17167
	@Test(groups= {"Regression_High_Priority"})
	public void verifyTestRuleResultsAreUpdatedWithChangeInTheRule() throws Exception {
		Log.info("Method : verifyTestRuleResultsAreUpdatedWithChangeInTheRule");
		CommonPageFunctions.clickTab("BRM", "TabName");
		String groupName = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName);
		addNewRuleInGroup("Rule1", "BrandProd", "Operator1", "BrandProd_Average", "Action", "Include", false);
		result = runTestRule();
		softAssert.assertTrue(result,"Test rule didn't run successfully");
		result = verifyTestRulesAreUpdatedWithChanges("BrandProd_Bad");
		softAssert.assertTrue(result,"Failed to Verify : verifyTestRulesAreUpdatedWithChanges ");
		CommonPageFunctions.clickTab("BRM", "TabName");
		deleteExistingBRGroup(groupName);
		softAssert.assertAll();
	}

	// Verify new attribute specified in the rule configuration are displayed as a column in the test rules output --  EDGE-17163
	@Test(groups= {"Regression_High_Priority"})
	public void verifyNewAttributeSpecifiedInRuleConfigurationAreDisplayedAsAColumnInTheTestRulesOutput() throws Exception {
		Log.info("Method : verifyNewAttributeSpecifiedInRuleConfigurationAreDisplayedAsAColumnInTheTestRulesOutput");
		CommonPageFunctions.clickTab("BRM", "TabName");
		String groupName = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName);
		addRuleInGroupWithTransformAction("BRM", "Rule1", "Attribute1", "Attribute1", "Operator1", "Value2", "Value1", false);
		setNewAttributeNameWithoutSave("NewAttributeName1");
		runTestRule();
		result = verifyTransformationColumnNameInTestRuleResult("NewAttributeName1");
		softAssert.assertTrue(result,"Failed to Verify : verifyTransformationColumnNameInTestRuleResult");
		CommonPageFunctions.clickTab("BRM", "TabName");
		deleteExistingBRGroup(groupName);
		softAssert.assertAll();
	}
	// Verify unpublished changes icon is shown on adding new condition/child condition/Delete condition -- EDGE-17184
	@Test(groups= {"Regression_High_Priority"})
	public void verifyUnpublishedChangesIconIsShownOnAddingNewConditionAndChildConditionAndDeletingCondition() throws Exception {
		Log.info("Method : verifyUnpublishedChangesIconIsShownOnAddingNewConditionAndChildConditionAndDeletingCondition");
		CommonPageFunctions.clickTab("BRM", "TabName");
		String groupName = createNewRuleGroup("Entity3");
		openExistingBusinessRuleGroup(groupName);
		addRuleInGroupWithTransformAction("BRM","Rule1","Attribute1","Attribute1", "Operator1", "Value2","Value1",true);
		publishNewVersionOfBusinessRule("VersionNumber1", false, "", false);
		result = verifySuccessToasterMessageForPublishingNewVersion("VersionNumber1");
		softAssert.assertTrue(result, "verifySuccessToasterMessageForPublishingNewVersion Assertion Failed");
		editExistingRule("Rule1");
		WrapperFunctionUtilities.waitForTime(2000);//Takes time to load entire conditions on page
		addElseCondition("Value3");
		clickSaveRuleButton();
		result = storeExistenceOfAddRuleButton();
		softAssert.assertTrue(result,"Failed to Save Rule Changes in Expected Time #1");
		CommonPageFunctions.clickTab("BRM", "TabName");
		result = CommonPageFunctions.storeExistenceOfElement(brmPage_UnpublishedChangesOnRuleTile_Locator(groupName),driver);
		softAssert.assertTrue(result, "Unpublished Changes Icon does not exists on the tile ");
		openExistingBusinessRuleGroup(groupName);
		publishNewVersionOfBusinessRule("VersionNumber2", false, "", false);
		result = verifySuccessToasterMessageForPublishingNewVersion("VersionNumber2");
		softAssert.assertTrue(result, "verifySuccessToasterMessageForPublishingNewVersion Assertion Failed");
		editExistingRule("Rule1");
		WrapperFunctionUtilities.waitForTime(2000);//Takes time to load entire conditions on page
		deleteConditionFromRule("Else");
		clickSaveRuleButton();
		result = storeExistenceOfAddRuleButton();
		softAssert.assertTrue(result,"Failed to Save Rule Changes in Expected Time #2");
		CommonPageFunctions.clickTab("BRM", "TabName");
		result = CommonPageFunctions.storeExistenceOfElement(brmPage_UnpublishedChangesOnRuleTile_Locator(groupName),driver);
		softAssert.assertTrue(result, "Unpublished Changes Icon does not exists on the tile ");
		openExistingBusinessRuleGroup(groupName);
		publishNewVersionOfBusinessRule("VersionNumber3", false, "", false);
		result = verifySuccessToasterMessageForPublishingNewVersion("VersionNumber3");
		softAssert.assertTrue(result, "verifySuccessToasterMessageForPublishingNewVersion Assertion Failed");
		editExistingRule("Rule1");
		WrapperFunctionUtilities.waitForTime(2000);//Takes time to load entire conditions on page
		expandExpression1();
		addChildCondition("Attribute3","Value3");
		clickSaveRuleButton();
		WrapperFunctionUtilities.waitForTime(2000);//Takes time to load entire conditions on page
		result = storeExistenceOfAddRuleButton();
		softAssert.assertTrue(result,"Failed to Save Rule Changes in Expected Time #3");
		CommonPageFunctions.clickTab("BRM", "TabName");
		result = CommonPageFunctions.storeExistenceOfElement(brmPage_UnpublishedChangesOnRuleTile_Locator(groupName),driver);
		softAssert.assertTrue(result, "Unpublished Changes Icon does not exists on the tile ");
		deleteExistingBRGroup(groupName);
		softAssert.assertAll();
	}
}