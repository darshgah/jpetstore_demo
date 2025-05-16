package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends BasePage{

	public Homepage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy (xpath = "//a[text() = 'Sign In']") WebElement btn_signin;
	
	
	public void sign_in() 
	{
		btn_signin.click();
	}
	

}
