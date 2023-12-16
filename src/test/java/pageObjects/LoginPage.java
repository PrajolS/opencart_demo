package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}

	//Locators
	
	@FindBy(name = "email")
	WebElement em;
	
	@FindBy(name = "password")
	WebElement pas;
	
	@FindBy(xpath = "//button[text()='Login']")
	WebElement clkLogin;
	
	//Invoke Methods
	
	public void setEmail(String email)
	{
		em.sendKeys(email);
	}
	
	public void setPassword(String pwd)
	{
		pas.sendKeys(pwd);
	}
	
	public void clickLogin()
	{
		clkLogin.click();
	}
}
