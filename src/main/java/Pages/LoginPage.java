package Pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.TestBase;

public class LoginPage extends TestBase{

	public LoginPage() throws IOException {
		super();
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	//Page Factory - OR:
		@FindBy(xpath="//input[@class='is_required validate account_input form-control'][@id='email']")
		WebElement email;
		
		@FindBy(xpath="//input[@class='is_required validate account_input form-control'][@id='passwd']")
		WebElement password;
		
		@FindBy(xpath="//button[@type='submit'][@id='SubmitLogin']")
		WebElement signInBtn;
		
		@FindBy(xpath="//h1[@class='page-heading']")
		WebElement pageHead;
		
		@FindBy(xpath="//a[@class='account']")
		WebElement AccountBtn;
		
		
		
		public String validateLoginPageTitle(){
			return driver.getTitle();
		}
		
		public boolean validatePageHeader() {
			return pageHead.isDisplayed();
		}
		
		public void enterUsernameAndPassword(String username, String passwd) {
			
			email.sendKeys(username);
			password.sendKeys(passwd);
			
		}
		
		public AccountsPage clickOnSignInButton() throws IOException {
			
			signInBtn.click();
			
			return new AccountsPage();
			
		}
		
		public AccountsPage clickOnAccountButton() throws IOException {
			
			AccountBtn.click();
			
			return new AccountsPage();
			
		}
		
		
		
}
