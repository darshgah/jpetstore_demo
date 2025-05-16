package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Product_page extends BasePage{

	public Product_page(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath = "//tbody//td/a[text() = 'K9-RT-01']") WebElement product;
	
	public void select_product() {
		product.click();
	}
	
}
	
