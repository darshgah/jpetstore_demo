package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class EnterStore_page extends BasePage {

	public EnterStore_page(WebDriver driver) 
	{
		super(driver);
		
	}
	
	//web elements
	@FindBy(xpath = "//a[text() = 'Enter the Store']") WebElement enterpage;
	
	
	//action methods
	public void btn_welcomepage() {
		enterpage.click();
	}
	
}
