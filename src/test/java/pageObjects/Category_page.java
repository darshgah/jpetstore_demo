package pageObjects;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Category_page extends BasePage {

	public Category_page(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath ="//div[@id = \"Welcome\"]") WebElement welcometext;
	@FindBy(xpath = "//div[@id = \"SidebarContent\"]//img[@src = \"../images/dogs_icon.gif\"]") WebElement category;
	@FindBy(xpath = "//div//a[text() = 'Sign Out']") WebElement sign_out;
	
	public String welcomemsg() {
		return welcometext.getText();
	}
	
	public void select_category() {
		category.click();
	}
	
	public void logout() {
		sign_out.click();
	}
	
	
	
}
