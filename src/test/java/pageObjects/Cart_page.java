package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Cart_page extends BasePage {

	public Cart_page(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath = "//h2[text() = 'Shopping Cart']") WebElement text;
	@FindBy(xpath = "//table//tbody//td//a[text() = 'Remove']") WebElement btn_remove;
	
	public String verify_heading()
	{
		return text.getText();
	}
	
	public void remove() {
		btn_remove.click();
	}
}
