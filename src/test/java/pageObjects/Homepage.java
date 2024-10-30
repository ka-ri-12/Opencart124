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

@FindBy(xpath="//input[@placeholder='Search']")
WebElement txtsearch;

@FindBy(xpath="//div[@id='search']//button[@type='button']")
WebElement btnsearch;







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
	
	public void entertxt(String pname)  //to search the product
	{
		txtsearch.sendKeys(pname);
	}
	
	
	public void clickonsearchsymbol()
	{
		btnsearch.click();
	}
	
	
	
	
	
	
}
