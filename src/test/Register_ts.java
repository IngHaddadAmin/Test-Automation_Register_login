package phpselenuimtest;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class Register_ts {
	WebDriver wd ;
	
	@Test(dataProvider = "data_test")
	public void Resgister_ftest(String First_Name, String Last_Name,String Phone,String Email,String Pw,String CPw)
	{
		wd.findElement(By.linkText("Inscription")).click();
		wd.findElement(By.name("first_name-1207")).sendKeys(First_Name);
		wd.findElement(By.name("last_name-1207")).sendKeys(Last_Name);
		wd.findElement(By.name("phone_number-1207")).sendKeys(Phone);
		wd.findElement(By.name("user_email-1207")).sendKeys(Email);
		wd.findElement(By.name("user_password-1207")).sendKeys(Pw);
		wd.findElement(By.name("confirm_user_password-1207")).sendKeys(CPw);
		WebElement submit = wd.findElement(By.xpath("//*[@id=\"um-submit-btn\"]"));
		JavascriptExecutor java = (JavascriptExecutor) wd ;
		java.executeScript("scroll(0,250)");
		submit.click();
		//WebDriverWait waitt = new WebDriverWait(amine, 200);
		//waitt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"post-1211\"]/header/h1")));
		String welcom_message = wd.findElement(By.xpath("//*[@id=\"post-1211\"]/header/h1")).getText();
		Assert.assertEquals(welcom_message,First_Name+" "+Last_Name);
		wd.findElement(By.linkText("Connexion")).click();
		wd.findElement(By.linkText("Déconnexion")).click();
	}
	// //*[@id="um-submit-btn"]
	
	@BeforeClass
	@Parameters({"browser"})
	public void open_browser(String browser) 
	{ 
		if(browser.equals("chrome")) {
		    System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");
		    wd = new ChromeDriver();
		    wd.get("http://weeets.com/");
		    //wd.manage().window().maximize();
		    }
		if(browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",".\\drivers\\geckodriver.exe");
			wd = new FirefoxDriver();
			wd.get("http://weeets.com/");
			//wd.manage().window().maximize();
			}
		
		
	}
	@AfterClass
	public void close_browser() 
	{   // wd.close();   
	}
	@DataProvider
	public String[][] data_test() throws InvalidFormatException, IOException  { 
		
		read_data_exel data = new read_data_exel();
		return data.read_data() ;
		}
	
	
	
	
	}

