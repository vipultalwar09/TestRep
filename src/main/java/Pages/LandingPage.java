package Pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.TestBase;

public class LandingPage extends TestBase{

	public LandingPage() throws IOException {
		//super();
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	//Page Factory - OR:
			@FindBy(xpath="//img[contains(@class,'logo img-responsive')]")
			WebElement storeLogo;
		
			@FindBy(xpath="//*[@id='header']/div[2]/div/div/nav/div[1]/a")
			WebElement loginBtn;
			
			@FindBy(xpath="//a[@title = 'Contact Us']")
			WebElement contactBtn;
			
			@FindBy(xpath="//a[@class='account']")
			WebElement AccountBtn;
		
			public String validateLandingPageTitle(){
				return driver.getTitle();
			}
					
			public boolean verifyLogo() {
				
				return storeLogo.isDisplayed();
			}
			
			public LoginPage clickOnLoginBtn() throws IOException {
				
				loginBtn.click();
			
				return new LoginPage();
			}
			
			public void clickOnContactBtn() {
				
				contactBtn.click();
			}
			
			public void clickOnAccountButton() throws IOException {
				
				AccountBtn.click();
				
				//return new AccountsPage();
				
			}
	
	
}
