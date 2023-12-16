package testBase;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.core.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public ResourceBundle rb;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters("browser")
	public void setup(String br)
	{
		rb = ResourceBundle.getBundle("config"); //Load config. properties file content into base class
		
		logger = LogManager.getLogger(this.getClass()); //here pass the current class name to know that following logs belongs to which class.
		if(br.equals("chrome"))
		{
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"}); // it will ruemove the page is handled by automated software line
		options.addArguments("--disable-notifications");
		//WebDriverManager.chromedriver().setup(); //from selenium 4.6.0 this declaration is deprecated.
		driver=new ChromeDriver(options);
		}
		
		
		else if(br.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			
			driver=new FirefoxDriver();
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(rb.getString("appURL1"));
		//driver.get("http://localhost/opencart/upload/index.php");
		driver.manage().window().maximize(); 
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void teardown()
	{
		driver.quit();
	}
		public String randomString() {
			String generatedString= RandomStringUtils.randomAlphabetic(5);
			return (generatedString);
		}
		
		public String randomNumber() {
			String generatedString2= RandomStringUtils.randomNumeric(10);
			return (generatedString2);
		}
		
		public String randomAlphaNumberic() {
			String st= RandomStringUtils.randomAlphabetic(3);
			String num= RandomStringUtils.randomNumeric(4);
			return (st+"@"+num);
		}
		
		public String captureScreen(String cs) throws IOException
		{
			String tstamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String dest =  System.getProperty("user.dir")+"\\screenshots\\"+ cs +"_" +tstamp + ".png";
			try {
				FileUtils.copyFile(source,new File(dest));
				
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
			return dest;
			
			
		}
	}

