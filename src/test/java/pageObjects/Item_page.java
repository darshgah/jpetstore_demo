package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Item_page extends BasePage{

	public Item_page(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath = "//tbody//td//a[text() = 'Add to Cart']") WebElement btn_cart;
	
	public void select_item() {
		btn_cart.click();
	}
}
