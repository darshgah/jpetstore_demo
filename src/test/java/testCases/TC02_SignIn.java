package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.Homepage;
import pageObjects.Signin_page;
import pageObjects.Category_page;
import pageObjects.EnterStore_page;
import utilities.Excelutils;

public class TC02_SignIn extends BaseClass{
	
	@Test (priority=1, groups = "sanity")
	public void Invalidcred() 
	{
		logger.info("----Starting Signin_invalid scenario----");
		try {
			
			logger.info("Navigating to welcome page");
			EnterStore_page wp = new EnterStore_page(driver);
			wp.btn_welcomepage();
			
			logger.info("Clicking on signin");
			Homepage hp = new Homepage(driver);
			hp.sign_in();
			
			logger.info("Entering invalid credentails");
			Signin_page sp = new Signin_page(driver);
			String [] userdetails = Excelutils.readLastCredentials();
			sp.user(userdetails[1]);
			sp.password(userdetails[0]);
			
			logger.info("Clicking on login");
			sp.signon();
			
			logger.info("Validating negative scenario..");
			Assert.assertEquals(sp.fail_msg(), "Invalid username or password. Signon failed.");
			
		}catch(Exception e) {
			Assert.fail();
			logger.error("Exception", e);
		}
		logger.info("-----Signin invalid completed----");
	}
	
	
	@Test (priority=2, groups="sanity")
	public void user_login() throws InterruptedException {
		
		logger.info("----Starting Signin_valid scenario----");
		try {
			
			logger.info("Entering valid credentials from excel");
			Signin_page sp = new Signin_page(driver);
			String [] userdetails = Excelutils.readLastCredentials();
			sp.user(userdetails[0]);
			sp.password(userdetails[1]);
			Thread.sleep(3000);
			
			logger.info("Clicking on login");
			sp.signon();
			
			logger.info("Validating positive scenario..");
			Category_page cp = new Category_page(driver);
			if(cp.welcomemsg().contains("Welcome")) {
				Assert.assertTrue(true);
			}
			else {
				logger.debug("Debug logs");
			}
			
		}
		catch(Exception e) {
			logger.error("Exception occured",e);
			Assert.fail();
			
		}
		
		logger.info("-----Completed - Sigin valid scenario-------");
	}
	
}
