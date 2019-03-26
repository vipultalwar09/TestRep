package StepDefinition;

import java.io.IOException;

import org.junit.Assert;

import Base.TestBase;
import Pages.AccountsPage;
import Pages.LandingPage;
import Pages.LoginPage;
import Pages.SummaryPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDef1 extends TestBase{

	LandingPage land ;
	LoginPage login ;
	AccountsPage account ;
	SummaryPage summary;
	
	
	public StepDef1() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Before
	public void setUp() {
		/*if(TestBase.driver == null)
		 	initialization();
		else
			TestBase.openUrl();
		System.out.println(driver.getTitle());*/
	}
	
	
	
	@Given("User opens the My Store Application into the browser")
	public void user_opens_the_My_Store_Application_into_the_browser() throws IOException {
	   initialization();
	   land  = new LandingPage();
	   login = new LoginPage();
	   account = new AccountsPage();
	   summary = new SummaryPage();
	   
	}

	@When("User after verifying logo and title clicks on the Login Button")
	public void user_clicks_on_the_Login_Button() throws IOException {
	
		
		String landingTitle = land.validateLandingPageTitle();
		Assert.assertEquals("My Store", landingTitle);
		System.out.println(landingTitle);
		
		
		boolean flag = land.verifyLogo();
		Assert.assertTrue(flag);
		//System.out.println(flag);
		
		login = land.clickOnLoginBtn();
		
	}

	@Then("User is redirected to Login Page")
	public void user_is_redirected_to_Login_Page() {
	   
		String loginTitle = login.validateLoginPageTitle();
		Assert.assertEquals("Login - My Store", loginTitle);
		System.out.println(loginTitle);
		
		boolean flag1 = login.validatePageHeader();
		Assert.assertTrue(flag1);
		System.out.println(flag1);
	}

	@Then("User enters {string} and {string}")
	public void user_enters_and(String username, String password) {
	    
		login.enterUsernameAndPassword(username, password);
		
	}

	@Then("User clicks on the Sign In button")
	public void user_clicks_on_the_Sign_In_button() throws IOException {
	   
		account = login.clickOnSignInButton();
		
	}

	@Then("User lands on the My Account Page")
	public void user_lands_on_the_My_Account_Page() {
	   
		System.out.println(driver.getTitle());
		
	}
	
	/*@Given("user logs in {string} and {string}")
	public void user_logs_in_and(String username, String password) throws IOException {
	   
		login = land.clickOnLoginBtn();
		Assert.assertEquals("Login - My Store", login.validateLoginPageTitle());
		login.enterUsernameAndPassword(username, password);
		account = login.clickOnSignInButton();		
	}*/
	//-------- Scenario - 2 --------------------
	
	
	@Given("User is on the Accounts Page")
	public void user_is_on_the_Accounts_Page() throws IOException {
	    
		String AccountTitle = driver.getTitle();
		System.out.println(AccountTitle);
		
		//Assert.assertEquals("My account - My Store", AccountTitle);
		//System.out.println(AccountTitle);
		
	}

	@When("User clicks on Dresses button")
	public void user_clicks_on_Dresses_button() throws IOException, InterruptedException {
	  
		//System.out.println("Nice Dress");
		account.clickOnCasualDressButton();
		
		
		
	}
	
	@Then("Chooses a dress to buy")
	public void chooses_a_dress_to_buy() throws IOException, InterruptedException {

		account.clickOnPrintedDress();
		
		
	}

	@Then("User clicks on Add To Cart Button")
	public void user_clicks_on_Add_To_Cart_Button() throws IOException {
	 
		account.addToCartButton();
		
	}

	@Then("Proceed for CheckOut")
	public void proceed_for_CheckOut() throws IOException {
	  
		account.CheckOutButton();		
		
	}
	
	
	
	@Then("Lands on the Summary Page")
	public void lands_on_the_Summary_Page() throws IOException {
	    
		summary.ProceedToCheckOutButton();
		
		
	}

	@Then("Moves forward to the payments page")
	public void moves_forward_to_the_payments_page() {
	   
	}
	
	

		
		
	@After("@second")
	public void tear_Down() {
		//driver.quit();
	}


	
	
	
	
}
