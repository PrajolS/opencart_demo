package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class)
	public void test_loginDDT(String email, String password, String exp)
	{
		logger.info("***Starting TC_003_LoginDDT****");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount");
		hp.clickLogin();
		logger.info("Clicked on Login");
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		logger.info("Entered mail address");
		lp.setPassword(password);
		logger.info("Entered Password");
		lp.clickLogin();
		
		MyAccountPage mp = new MyAccountPage(driver);
		boolean targetPage=mp.isAccountPageExists();
		
		if (exp.equals("Valid"))
		{
			if(targetPage==true)
			{
				driver.switchTo().alert().dismiss();
				mp.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if (exp.equals("Invalid"))
		{
			if(targetPage==true)
			{
				mp.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}	
		logger.info("**** Finished TC_003_LoginDDt");
		}
		catch(Exception e) {
			Assert.fail();
		}
	}
	
	

}
