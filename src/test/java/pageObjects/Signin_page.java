package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Signin_page extends BasePage {
	
	public Signin_page(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath = "//a[contains (text() , 'Register')]") WebElement btn_reg;
	@FindBy(xpath = "//input[@name = 'username']") WebElement txt_uname;
	@FindBy(xpath = "//input[@name = 'password']") WebElement txt_pwd;
	@FindBy(xpath = "//input[@name = 'signon']") WebElement btn_signon;
	@FindBy(xpath = "//div//ul//li[contains(text(), 'Invalid user')]") WebElement errormsg;
	
	
	public void register() 
	{
		btn_reg.click();
	}
	
	
	public void user(String username) 
	{
		txt_uname.sendKeys(username);
	}
	
	public void password(String pwd)
	{
		txt_pwd.clear();
		txt_pwd.sendKeys(pwd);
	}
	
	public void signon()
	{
		btn_signon.click();
	}
	
	public String fail_msg() {
		return errormsg.getText();
	}
}
