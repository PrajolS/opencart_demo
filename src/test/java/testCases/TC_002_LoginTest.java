package testCases;
 import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
 import testBase.BaseClass;
 import pageObjects.LoginPage;
 import pageObjects.MyAccountPage;
public class TC_002_LoginTest extends BaseClass{
	
	@Test(groups={"Sanity","Master"})
	public void  test_login()
	{
		try {
		logger.info("***** Starting TC_002_LoginTest***");
		
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount");
		hp.clickLogin();
		logger.info("Clicked on Login");
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(rb.getString("email"));
		logger.info("Entered mail address");
		lp.setPassword(rb.getString("password"));
		logger.info("Entered Password");
		lp.clickLogin();
		
		MyAccountPage mp = new MyAccountPage(driver);
		boolean targetPage=mp.isAccountPageExists();
		Assert.assertEquals(targetPage, true, "Invalid Login Data");
		
		}
		catch(Exception e)
		{
			Assert.fail();
		}
				logger.info("**** Finished TC_002_LoginTest");
	}
	
	
	
	
	

}
