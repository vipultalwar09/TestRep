package Pages;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Base.TestBase;

public class SummaryPage extends TestBase {

	public SummaryPage() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="(//a[@title='Proceed to checkout'])[2]")
	WebElement ProceedToCheckOut;
		
	
	public void ProceedToCheckOutButton() throws IOException {
		
		//JavascriptExecutor js3 = (JavascriptExecutor) driver;
		//js3.executeScript("arguments[0].click();", ProceedToCheckOut);
		ProceedToCheckOut.click();
		
		
	

}

	

}
