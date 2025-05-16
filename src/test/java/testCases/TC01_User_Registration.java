package testCases;



import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import TestBase.BaseClass;
import pageObjects.Homepage;
import pageObjects.Registration_page;
import pageObjects.Signin_page;
import pageObjects.EnterStore_page;
import utilities.Excelutils;


public class TC01_User_Registration extends BaseClass {
	

	@Test(groups = "sanity")
	public void Registration() throws InterruptedException, IOException 
	{
		logger.info("----Starting User Registration----");
		
		try {
		logger.info("Navigating to welcome page");
			EnterStore_page wp = new EnterStore_page(driver);
			wp.btn_welcomepage();
			
		logger.info("Clicking on signin");
			Homepage hp = new Homepage(driver);
			hp.sign_in();
			
		logger.info("Clicking on register");
			Signin_page sp = new Signin_page(driver);
			sp.register();
			
		logger.info("Filling user registeration details..");
			Registration_page reg = new Registration_page(driver);
			Faker fake = new Faker();
			String uname = fake.name().username();
			reg.setuser(uname);
			String password = fake.internet().password(6, 12, true, true);
			reg.setpassword(password);
			reg.repeatpwd(password);
			
			String fname= fake.name().firstName();
			reg.first_name(fname);
			
			reg.last_name(fake.name().lastName());
			
			reg.email(fake.internet().emailAddress());
			
			reg.phone(fake.phoneNumber().cellPhone());
			
			reg.address1(fake.address().streetAddress());
		
			reg.address2(fake.address().secondaryAddress());
			
			reg.city(fake.address().city());
			
			reg.state(fake.address().state());
			
			reg.zip(fake.address().zipCode());
		
			reg.country(fake.address().country());
			
			reg.language();
			
			reg.fav_category();
			
			reg.mylist();
		
			reg.banner();
			
			logger.info("Save details..");
			reg.save_info();	
			
			logger.info("Validating successful registration");
			String Act_url = "https://petstore.octoperf.com/actions/Catalog.action";
			String expt_url = driver.getCurrentUrl();
			if(Act_url.equalsIgnoreCase(expt_url))
			{
				Excelutils.writeCredentials(uname, password);
				Assert.assertEquals(expt_url, Act_url);
			}
			else{
				logger.error("Incorrect url. Expected:" +expt_url +"Actual:" +Act_url);
			}
			
		}
		catch(Exception e) {
			logger.error("Exception occured",e);
			logger.debug("Debug logs");
			Assert.fail();
			
		}
		logger.info("------Completed Registration-----");
	}

	
}
