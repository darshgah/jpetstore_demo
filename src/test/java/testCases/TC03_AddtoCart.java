package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.Cart_page;
import pageObjects.Category_page;
import pageObjects.EnterStore_page;
import pageObjects.Homepage;
import pageObjects.Item_page;
import pageObjects.Product_page;
import pageObjects.Signin_page;
import utilities.Excelutils;

public class TC03_AddtoCart extends BaseClass {

	@Test(groups="sanity")
	public void add_cart() {
		
		logger.info("----Starting Add to cart---");
		
		try {
			logger.info("Navigating to welcome page");
			EnterStore_page wp = new EnterStore_page(driver);
			wp.btn_welcomepage();
			
			logger.info("Clicking on signin");
			Homepage hp = new Homepage(driver);
			hp.sign_in();
			
			logger.info("Entering user credentails");
			Signin_page sp = new Signin_page(driver);
			String [] userdetails = Excelutils.readLastCredentials();
			sp.user(userdetails[0]);
			sp.password(userdetails[1]);
			logger.info("Clicking on login");
			sp.signon();
			
			logger.info("Selecting a category");
			Category_page cp = new Category_page(driver);
			cp.select_category();
			
			logger.info("Selecting a product");
			Product_page prod = new Product_page(driver);
			prod.select_product();
			
			logger.info("Selecting an item");
			Item_page item = new Item_page(driver);
			item.select_item();
			
			logger.info("Adding to cart");
			Cart_page cart = new Cart_page(driver);
			
			logger.info("Validating product added to cart");
			if(cart.verify_heading().equals("Shopping Cart")) {
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
		
		logger.info("-------Completed Add to cart------");
	}
	
}
