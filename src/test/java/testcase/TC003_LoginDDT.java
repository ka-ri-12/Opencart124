package testcase;



import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.Loginpage;
import pageObjects.ValidationOfMyAccountpageAfterLogin;
import testBase.Baseclass;
import utilities.DataProviders;


/*Data is valid  - login success - test pass  - logout
 					login failed - test fail

Data is invalid - login success - test fail  - logout
 					login failed - test pass
*/


public class TC003_LoginDDT extends Baseclass {

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups="Datadriven")// getting data provider from different class
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException
	{
		logger.info("***** stating TC_003_LoginDDT ******");
		
		try
		{
		//HomePage
		Homepage hp=new Homepage(driver);
		hp.clickmyaccount();
		hp.clicklogin();
		
		//Login
		Loginpage lp=new Loginpage(driver);
		lp.setemail(email);
		lp.setpwd(pwd);
		lp.clicklogin();
			
		//MyAccount
		ValidationOfMyAccountpageAfterLogin mcc=new ValidationOfMyAccountpageAfterLogin (driver);
		  boolean targetpage=   mcc.myaccountexists();
		
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetpage==true)
			{			
				mcc.clickLogout();
				Assert.assertTrue(true);
				
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetpage==true)
			{
				mcc.clickLogout();
				Assert.assertTrue(false);
				
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
		}catch(Exception e)
		{
			Assert.fail();
		}
		Thread.sleep(3000);
		logger.info("***** Finished TC_003_LoginDDT ******");
		
	}
	
}
