package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;



public class Registration_page extends BasePage {
	
	
	public Registration_page(WebDriver driver) 
	{
		super(driver);
	}
	
	//web elements
	
	@FindBy(xpath = "//input[@name = 'username']") WebElement txt_username;
	@FindBy(xpath = "//input[@name = 'password']") WebElement txt_newpwd;
	@FindBy(xpath = "//input[@name = 'repeatedPassword']") WebElement txt_repeatpwd;
	@FindBy(xpath = "//input[@name = 'account.firstName']") WebElement txt_fname;
	@FindBy(xpath = "//input[@name = 'account.lastName']") WebElement txt_lname;
	@FindBy(xpath = "//input[@name = 'account.email']") WebElement txt_email;
	@FindBy(xpath = "//input[@name = 'account.phone']") WebElement txt_phone;
	@FindBy(xpath = "//input[@name = 'account.address1']") WebElement txt_addr1;
	@FindBy(xpath = "//input[@name = 'account.address2']") WebElement txt_addr2;
	@FindBy(xpath = "//input[@name = 'account.city']") WebElement txt_city;
	@FindBy(xpath = "//input[@name = 'account.state']") WebElement txt_state;
	@FindBy(xpath = "//input[@name = 'account.zip']") WebElement txt_zip;
	@FindBy(xpath = "//input[@name = 'account.country']") WebElement txt_country;
	@FindBy(xpath = "//select[@name = 'account.languagePreference']") WebElement drp_lang;
	@FindBy(xpath = "//select[@name = 'account.favouriteCategoryId']") WebElement drp_category;
	@FindBy(xpath = "//input[@name = 'account.listOption']") WebElement chk_list;
	@FindBy(xpath = "//input[@name = 'account.bannerOption']") WebElement chk_banner;
	@FindBy(xpath = "//input[@name= 'newAccount']") WebElement btn_save;
	
	
	
	public void setuser(String user) {
		txt_username.sendKeys(user);
	}
	

	public void setpassword(String pwd) {
		txt_newpwd.sendKeys(pwd);
	}
	
	public void repeatpwd(String reppwd) {
		txt_repeatpwd.sendKeys(reppwd);
	}
	
	public void first_name(String fname) {
		txt_fname.sendKeys(fname);
	}
	
	public void last_name(String lname) {
		txt_lname.sendKeys(lname);
	}
	
	public void email(String emailid) {
		txt_email.sendKeys(emailid);
	}
	public void phone(String phone_no) {
		txt_phone.sendKeys(phone_no);
	}
	
	public void address1(String addr1) {
		txt_addr1.sendKeys(addr1);
	}
	
	public void address2(String addr2) {
		txt_addr2.sendKeys(addr2);
	}
	
	public void city(String city) {
		txt_city.sendKeys(city);
	}
	
	public void state(String state) {
		txt_state.sendKeys(state);
	}
	
	public void zip(String zip) {
		txt_zip.sendKeys(zip);
	}
	
	public void country(String country) {
		txt_country.sendKeys(country);
	}
	
	public void language() {
		Select lang = new Select(drp_lang);
		lang.selectByVisibleText("english");
	}
	
	public void fav_category() {
		Select category = new Select(drp_category);
		category.selectByVisibleText("DOGS");
	}
	
	public void mylist() {
		chk_list.click();
	}
	
	public void banner() {
		chk_banner.click();
	}
	
	public void save_info() {
		btn_save.click();
	}
	

	
}

