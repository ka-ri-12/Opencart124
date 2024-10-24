package testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.Loginpage;
import pageObjects.ValidationOfMyAccountpageAfterLogin;
import testBase.Baseclass;

public class TC_002LoginTest extends Baseclass
{
	@Test(groups= {"sanity","Master"})
	public void verify_login()
	{
		
		logger.info("************ Starting TC_002LoginTest*********");
	try {	
	//homepage
		Homepage pg= new Homepage(driver);
	      pg.clickmyaccount();
	          pg.clicklogin();
	
  //loginpage
	          
	     Loginpage lp = new Loginpage(driver);
	     lp.setemail(p.getProperty("email"));
	     lp.setpwd(p.getProperty("password"));
	     lp.clicklogin();
	
	//Myaccountpage
	     ValidationOfMyAccountpageAfterLogin mcc=new ValidationOfMyAccountpageAfterLogin (driver);
	  boolean targetpage=   mcc.myaccountexists();
	Assert.assertTrue(targetpage);
	}
	catch(Exception e)
	{
		Assert.fail();
	}
	logger.info("********Finished TC_002Logintest****");
	
	}
	
}
