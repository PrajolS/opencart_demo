package testCases;


//import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegisteration_Test  extends BaseClass{

	
	
	@Test(groups= {"Master","Regression"})
	public void test_Account_Registeration() throws InterruptedException
	{
		logger.debug("Client server communication log, Handled by dev team-Applicatin logs");
		logger.info("*** Starting TC_001_AccountRegisteration_Test ***");
		boolean checked = true;
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account");
		hp.clickRegister();
		logger.info("Clicked on Registration page");
		
		AccountRegisterationPage regPage=new AccountRegisterationPage(driver);
		logger.info("Proceeding on Registration page");
		logger.info("Providing customer details");
		regPage.setFirstName(randomString().toUpperCase());
		regPage.setLastName(randomString().toUpperCase());
		regPage.setEmail(randomString()+"@gmail.com");
		regPage.setPassword(randomAlphaNumberic());
		regPage.setPrivacyPolicy(checked);
		regPage.clickContinue();
		String confmsg=regPage.getConfirmationMessage();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		
		logger.info("*** Finished TC_001_AccountRegisteration_Test ***");
	}
}
