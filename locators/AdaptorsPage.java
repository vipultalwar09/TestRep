package com.RDM.Merger.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utility.base.TestBase;
import com.utility.base.WrapperFunctionUtilities;

public class AdaptorsPage extends TestBase{



	public AdaptorsPage() {

		PageFactory.initElements(driver, this);
		System.out.println("in adaptor page");
	}

	@FindBy(xpath="//a[@href='#/adaptors']")
	public static WebElement adaptors_module;

	@FindBy(xpath="//span[contains(.,'Create New Adaptor')]")
	public static WebElement create_new_adaptor;

	@FindBy(xpath="(//input[contains(@type,'radio')])[3]")
	public static WebElement s3_data_source;

	@FindBy(xpath="//input[contains(@placeholder,'Adaptor Name')]")
	public static WebElement adaptor_name;

	@FindBy(xpath="//input[contains(@placeholder,'Bucket Name')]")
	public static WebElement bucket_name;

	@FindBy(xpath="//input[contains(@placeholder,'IAM Role')]")
	public static WebElement iam_role;

	@FindBy(xpath="//label[normalize-space(text())='Region Name']//following-sibling::p//div[1]")
	public static WebElement region_name;

	@FindBy(xpath="//label[normalize-space(text())='AWS Credential']//following-sibling::p//div[1]")
	public static WebElement aws_credentials;

	@FindBy(xpath="//input[@placeholder='Access Key']")
	public static WebElement access_key;

	@FindBy(xpath="//input[@placeholder='Secret Key']")
	public static WebElement secret_key;

	@FindBy(xpath="//button[contains(.,'Test Connection')]")
	public static WebElement test_connection_button;

	@FindBy(xpath="//h4[contains(.,'Test Connection')]/following::section")
	public static WebElement test_connection_status;

	@FindBy(xpath="(//button[contains(.,'Ok')])[1]")
	public static WebElement ok;

	@FindBy(xpath="//span[contains(.,'Save')]")
	public static WebElement save;

	@FindBy(xpath ="(//input[contains(@type,'radio')])[2]")
	public static WebElement rdbms_datasource;

	@FindBy(xpath="(//div[contains(.,'Postgresql')])[7]")
	public static WebElement rdbms_type;

	@FindBy(xpath="(//div[contains(.,'Credential based')])[8]")
	public static WebElement rdbms_authentication_type;

	@FindBy(xpath="//input[@placeholder='Host Name']")
	public static WebElement rdbms_hostname;

	@FindBy(xpath="//input[@placeholder='Username']")
	public static WebElement rdbms_username;

	@FindBy(xpath="//input[@placeholder='Password']")
	public static WebElement rdbms_password;

	@FindBy(xpath="//input[@placeholder='Port Number']")
	public static WebElement rdbms_portnumber;

	@FindBy(xpath="//input[@placeholder='Schema']")
	public static WebElement rdbms_schema;

	@FindBy(xpath="//label[normalize-space(text())='Database']//following-sibling::p//div[1]")
	public static WebElement redshift_database;

	@FindBy(xpath="//input[@placeholder='Cluster Indentifier']")
	public static WebElement redshift_cluster_identifier;

	@FindBy(xpath="(//div[contains(.,'eu-west-3')])[8]")
	public static WebElement redshift_region_name;

	@FindBy(xpath="//input[@placeholder='IAM Role']")
	public static WebElement s3_i_am_role;

	@FindBy(xpath = "//section[contains(.,'Connection successful.')]")
	public static WebElement rdbms_connectionstatus;

	@FindBy(xpath="//a[contains(@class,'zs-icon-refresh refresh')]")
	public static WebElement database_refresh;

	@FindBy(xpath= "//h2[text()='Adaptors']")
	public static WebElement adaptorHeader;

	@FindBy(xpath = "//label[normalize-space(text())='Database']/following-sibling::p[@is='zs-multiselect']//div[1]")
	public static WebElement adaptorPage_SelectDatabase_Dropdown;

	@FindBy(xpath = "//label[normalize-space(text())='Database']/following-sibling::p[@is='zs-multiselect']//div//input")
	public static WebElement adaptorPage_SelectDatabase_TextBox;

	@FindBy(xpath = "//button/span[normalize-space(text())='Update']")
	public static  WebElement adaptorPage_Update_Button;

	@FindBy(xpath = "//label[normalize-space(text())='Authentication Type']/following::div[normalize-space(text())='Credential based']")
	public static WebElement adaptorPage_CredentialBasedSelection_dropdownValue;

	@FindBy(xpath = "(//button/span[normalize-space(text())='Delete'])[1]")
	public static WebElement adaptorPage_Delete_Button;

	public static WebElement adaptorPage_Edit_Icon(String adaptorName)
	{
		return driver.findElement(By.xpath("//div/span[normalize-space(text())='"+adaptorName+"']/following::div[7]//a[1]"));
	}
	public static WebElement adaptorPage_Delete_Icon(String adaptorName)
	{
		return driver.findElement(By.xpath("//div/span[normalize-space(text())='"+adaptorName+"']/following::div[7]//a[2]"));
	}

	public static WebElement adaptorPage_toasterClose_CrossIcon(String adaptorName)
	{
		return driver.findElement(By.xpath("//p/span[contains(.,'"+adaptorName+"')]/following::a[1]"));
	}


}
