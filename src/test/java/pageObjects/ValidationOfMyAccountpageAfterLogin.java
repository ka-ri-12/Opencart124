package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ValidationOfMyAccountpageAfterLogin extends Basepage
{
	public ValidationOfMyAccountpageAfterLogin(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//h2[text()='My Account']")
	WebElement validationofmyaccount;
	
	@FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']")   //added in step6
	WebElement lnkLogout;
	
	public boolean myaccountexists()
	{
		try {
		return(validationofmyaccount.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
	}
		public void clickLogout()
		{
			lnkLogout.click();
		}
		
	}
	
	
	
	
	
	
	
	
	
	

