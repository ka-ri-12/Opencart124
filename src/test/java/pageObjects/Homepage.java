package pageObjects;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends Basepage
{

	public Homepage (WebDriver driver)
	{
		super(driver);
	}
	
@FindBy(xpath="//span[normalize-space()='My Account']")	
WebElement inkmyaccount;
	
@FindBy(xpath="//a[normalize-space()='Register']")	
WebElement inkregister;
	
@FindBy(linkText="Login")
WebElement inklogin;




public void clickmyaccount()	
{
	inkmyaccount.click();
}
public void clickregister()	
{
	inkregister.click();
}
	
public void clicklogin()	
{
	inklogin.click();
}
	
	
	
	
	
	
	
	
	
	
	
}
