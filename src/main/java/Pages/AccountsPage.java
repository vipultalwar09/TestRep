package Pages;

import java.io.IOException;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Base.TestBase;

public class AccountsPage extends TestBase{
	

	public AccountsPage() throws IOException {
		//super();
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//a[@class='account']")
	WebElement AccountBtn;
	
	
	@FindBy(xpath="(//a[@title='Dresses'])[2]")
	WebElement DressBtn;
	
	@FindBy(xpath="(//a[@title='Casual Dresses'])[1]")
	WebElement CasualDress;
	
	@FindBy(xpath="//a[@title='Evening Dresses']")
	WebElement EveningDress;
	
	@FindBy(xpath="//a[@title='Summer Dresses']")
	WebElement SummerDress;
	
	@FindBy(xpath="//div[@class='product-image-container']")
	WebElement PrintedDress;
	
	@FindBy(xpath="(//a[@data-field-qty='qty'])[2]")
	WebElement PlusBtn;
	
	@FindBy(xpath="//select[@id='group_1']")
	WebElement SizeList;
	
	@FindBy(xpath="//button[@type='submit' and @class='exclusive']")
	WebElement AddToCartBtn;
	
	@FindBy(xpath="//iframe[contains(@id,'fancybox-frame')]")
	WebElement Modal1;
	
	@FindBy(xpath="//a[@title='Proceed to checkout']")
	WebElement CheckOut;
	

	
	public void clickOnAccountButton() throws IOException {
		
		AccountBtn.click();
	
	}
	
	
	public void clickOnCasualDressButton() throws IOException, InterruptedException {
		
		Actions action = new Actions(driver);
		action.moveToElement(DressBtn).build().perform();
		action.moveToElement(CasualDress).build().perform();
		Thread.sleep(3000);
	
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", CasualDress);
		//CasualDress.click();
	}
	
	public void clickOnPrintedDress() throws IOException, InterruptedException {
		
		PrintedDress.click();
		Thread.sleep(3000);
		
		driver.switchTo().frame(Modal1);
		Actions action = new Actions(driver);
		action.moveToElement(PlusBtn).build().perform();
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("arguments[0].click();", PlusBtn);
		//CasualDress.click();
		//PlusBtn.click();
		
		Select size = new Select(SizeList);
		size.selectByVisibleText("M");
		
			
		
	}
	
	public void addToCartButton() throws IOException {
			
		AddToCartBtn.click();
		
	}
	
	public SummaryPage CheckOutButton() throws IOException {
		
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("arguments[0].click();", CheckOut);
		
		return new SummaryPage();
		
	}
	
	
	
	
	
	
	
	

}
